package dev.prangellplays.enigmaticsurvival.entity;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
import dev.prangellplays.enigmaticsurvival.EnigmaticSurvival;
import dev.prangellplays.enigmaticsurvival.components.RecoveryPosComponent;
import dev.prangellplays.enigmaticsurvival.init.EnigmaticSurvivalComponents;
import dev.prangellplays.enigmaticsurvival.utils.PlayerClientHead;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

public class PlayerHeadEntity extends Entity {
    private static final TrackedData<String> PLAYER_NAME;
    private static final TrackedData<Optional<UUID>> PLAYER_UUID;
    private final SimpleInventory inventory = new SimpleInventory(54);
    private PlayerEntity player;

    public PlayerHeadEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    public PlayerHeadEntity(World world, PlayerEntity player) {
        super(EnigmaticSurvival.PLAYER_HEAD, world);
        this.setCustomName(player.getName());
        this.setPlayerUuid(player.getUuid());
        this.setPlayerName(player.getGameProfile().getName());
        this.setPosition(player.getPos());

        for(int i = 0; i < player.getInventory().size(); ++i) {
            this.inventory.addStack(player.getInventory().getStack(i));
        }

        TrinketsApi.getTrinketComponent(player).ifPresent((trinkets) -> {
            Iterator var2 = trinkets.getAllEquipped().iterator();

            while(var2.hasNext()) {
                Pair<SlotReference, ItemStack> slotReferenceItemStackPair = (Pair)var2.next();
                this.inventory.addStack((ItemStack)slotReferenceItemStackPair.getRight());
            }

        });
    }

    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        if (this.getPlayerUuid().isPresent()) {
            UUID uuid = (UUID)this.getPlayerUuid().get();
        }

        return super.shouldRender(cameraX, cameraY, cameraZ);
    }

    protected void initDataTracker() {
        this.dataTracker.startTracking(PLAYER_NAME, "rip bozo");
        this.dataTracker.startTracking(PLAYER_UUID, Optional.empty());
    }

    public String getPlayerName() {
        return (String)this.dataTracker.get(PLAYER_NAME);
    }

    public void setPlayerName(String playerName) {
        this.dataTracker.set(PLAYER_NAME, playerName);
    }

    public Optional<UUID> getPlayerUuid() {
        return (Optional)this.dataTracker.get(PLAYER_UUID);
    }

    public void setPlayerUuid(UUID playerUuid) {
        this.dataTracker.set(PLAYER_UUID, Optional.of(playerUuid));
    }

    public boolean isGlowing() {
        return this.getWorld().isClient() && PlayerClientHead.isOwned(this) ? true : super.isGlowing();
    }

    public void tick() {
        if (this.age % 80 == 0) {
            if (this.player == null && this.getPlayerUuid().isPresent()) {
                this.player = this.getWorld().getPlayerByUuid((UUID)this.getPlayerUuid().get());
            }

            if (this.player != null) {
                if (this.player.getWorld() != this.getWorld()) {
                    this.player = null;
                } else {
                    RecoveryPosComponent pos = (RecoveryPosComponent) EnigmaticSurvivalComponents.RECOVERY_POS_COMPONENT.get(this.player);
                    pos.setDeathPos(this.getBlockPos());
                }
            }
        }

        if (!this.getWorld().isClient && this.age % 400 == 0 && this.inventory.isEmpty()) {
            this.kill();
        }

        super.tick();
        this.prevX = this.getX();
        this.prevY = this.getY();
        this.prevZ = this.getZ();
        Vec3d vec3d = this.getVelocity();
        float f = this.getStandingEyeHeight() - 0.11111111F;
        if (this.isTouchingWater() && this.getFluidHeight(FluidTags.WATER) > (double)f) {
            this.applyWaterBuoyancy();
        } else if (this.isInLava() && this.getFluidHeight(FluidTags.LAVA) > (double)f) {
            this.applyLavaBuoyancy();
        } else if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
        }

        if (this.getWorld().isClient) {
            this.noClip = false;
        } else {
            this.noClip = !this.getWorld().isSpaceEmpty(this, this.getBoundingBox().contract(1.0E-7));
            if (this.noClip) {
                this.pushOutOfBlocks(this.getX(), (this.getBoundingBox().minY + this.getBoundingBox().maxY) / 2.0, this.getZ());
            }
        }

        if (!this.isOnGround() || this.getVelocity().horizontalLengthSquared() > 9.999999747378752E-6 || (this.age + this.getId()) % 4 == 0) {
            this.move(MovementType.SELF, this.getVelocity());
            float g = 0.98F;
            if (this.isOnGround()) {
                g = this.getWorld().getBlockState(new BlockPos((int) this.getX(), (int) (this.getY() - 1.0), (int) this.getZ())).getBlock().getSlipperiness() * 0.98F;
            }

            this.setVelocity(this.getVelocity().multiply((double)g, 0.98, (double)g));
            if (this.isOnGround()) {
                Vec3d vec3d2 = this.getVelocity();
                if (vec3d2.y < 0.0) {
                    this.setVelocity(vec3d2.multiply(1.0, -0.5, 1.0));
                }
            }
        }

        this.velocityDirty |= this.updateWaterState();
        if (!this.getWorld().isClient && this.getVelocity().subtract(vec3d).lengthSquared() > 0.01) {
            this.velocityDirty = true;
        }

    }

    public void kill() {
        for(int i = 0; i < this.inventory.size(); ++i) {
            this.dropStack(this.inventory.getStack(i));
        }

        this.discard();
    }

    private void applyWaterBuoyancy() {
        Vec3d vec3d = this.getVelocity();
        this.setVelocity(vec3d.x * 0.9900000095367432, vec3d.y + (double)(vec3d.y < 0.05999999865889549 ? 5.0E-4F : 0.0F), vec3d.z * 0.9900000095367432);
    }

    private void applyLavaBuoyancy() {
        Vec3d vec3d = this.getVelocity();
        this.setVelocity(vec3d.x * 0.949999988079071, vec3d.y + (double)(vec3d.y < 0.05999999865889549 ? 5.0E-4F : 0.0F), vec3d.z * 0.949999988079071);
    }

    public boolean canHit() {
        return true;
    }

    public boolean shouldRenderName() {
        return true;
    }

    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (player.isSneaking()) {
            player.openHandledScreen(new PlayerHeadEntity$SkullScreenFactory(this));
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.PASS;
        }
    }

    public void onPlayerCollision(PlayerEntity player) {
        if (!this.getWorld().isClient && player.getGameProfile().getName().equals(this.getPlayerName())) {
            for(int i = 0; i < this.inventory.size(); ++i) {
                if (!player.getInventory().insertStack(this.inventory.getStack(i))) {
                    player.dropStack(this.inventory.getStack(i));
                } else {
                    this.getWorld().playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((player.getRandom().nextFloat() - player.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
                }
            }

            this.discard();
        }

    }

    protected void readCustomDataFromNbt(NbtCompound nbt) {
        NbtList nbtList = nbt.getList("Inventory", 10);
        this.inventory.readNbtList(nbtList);
        if (nbt.contains("PlayerUuid")) {
            this.setPlayerUuid(nbt.getUuid("PlayerUuid"));
        }

        if (nbt.contains("PlayerName")) {
            this.setPlayerName(nbt.getString("PlayerName"));
        }

    }

    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.put("Inventory", this.inventory.toNbtList());
        nbt.putString("PlayerName", this.getPlayerName());
        this.getPlayerUuid().ifPresent((uuid) -> {
            nbt.putUuid("PlayerUuid", uuid);
        });
    }

    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    public SimpleInventory getInventory() {
        return this.inventory;
    }

    static {
        PLAYER_NAME = DataTracker.registerData(PlayerHeadEntity.class, TrackedDataHandlerRegistry.STRING);
        PLAYER_UUID = DataTracker.registerData(PlayerHeadEntity.class, TrackedDataHandlerRegistry.OPTIONAL_UUID);
    }

    private record PlayerHeadEntity$SkullScreenFactory(PlayerHeadEntity head) implements NamedScreenHandlerFactory {
        private PlayerHeadEntity$SkullScreenFactory(PlayerHeadEntity head) {
            this.head = head;
        }

        public Text getDisplayName() {
            return this.head.getDisplayName().copy().append(Text.literal("'s Remains"));
        }

        public @NotNull ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
            return GenericContainerScreenHandler.createGeneric9x6(syncId, inv, this.head.getInventory());
        }

        public PlayerHeadEntity head() {
            return this.head;
        }
    }
}