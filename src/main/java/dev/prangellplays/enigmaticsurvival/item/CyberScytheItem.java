package dev.prangellplays.enigmaticsurvival.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Hand;

public class CyberScytheItem extends SwordItem {
    public CyberScytheItem(Settings settings) {
        super(CyberScytheToolMaterial.INSTANCE, 3, -2.8f, settings);
    }

    

    @Override
    public boolean hasGlint(ItemStack stack) {
        return false;
    }

    @Override
    public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }

    public static class CyberScytheToolMaterial implements ToolMaterial {
        public static final CyberScytheItem.CyberScytheToolMaterial INSTANCE = new CyberScytheItem.CyberScytheToolMaterial();

        public CyberScytheToolMaterial() {
        }

        public int getDurability() {
            return 0;
        }

        public float getMiningSpeedMultiplier() {
            return 9.0F;
        }

        public float getAttackDamage() {
            return 4.5F;
        }

        public int getMiningLevel() {
            return 4;
        }

        public int getEnchantability() {
            return 28;
        }

        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(new ItemConvertible[]{Items.AIR});
        }
    }
}
