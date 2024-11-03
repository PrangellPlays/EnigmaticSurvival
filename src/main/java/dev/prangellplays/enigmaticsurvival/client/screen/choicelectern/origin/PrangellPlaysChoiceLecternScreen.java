package dev.prangellplays.enigmaticsurvival.client.screen.choicelectern.origin;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.prangellplays.enigmaticsurvival.EnigmaticSurvival;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class PrangellPlaysChoiceLecternScreen extends Screen {
    private static final Identifier TEXTURE = new Identifier(EnigmaticSurvival.MOD_ID, "textures/gui/choicelectern/base.png");
    private static final Identifier TEXTURE1 = new Identifier(EnigmaticSurvival.MOD_ID,"textures/gui/choicelectern/widgets.png");
    public ButtonWidget closeButton = ButtonWidget.builder(ScreenTexts.DONE, (button) -> {this.close();}).dimensions(this.width / 2 - 100, 370, 200, 20).build();

    public PrangellPlaysChoiceLecternScreen() {
        super(Text.translatable("container.lectern"));
    }

    @Override
    protected void init() {
        this.addCloseButton();
        addDrawableChild(closeButton);
    }

    protected void addCloseButton() {
        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
            this.close();
        }).dimensions(this.width / 2 - 100, 370, 200, 20).build());
    }

        @Override
        public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - 290) / 2;
        int y = (height - 180) / 2;
        context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        context.drawTexture(TEXTURE, x, y, 1, 1, 146, 180);
        context.drawTexture(TEXTURE, x + 145, y, 1, 1, 146, 180);
        TextRenderer textRenderer = MinecraftClient.getInstance().inGameHud.getTextRenderer();

        //Page 1
        context.drawText(textRenderer, "", x + 14, y + 15, 0, false);
        context.drawText(textRenderer, "Origin: PrangellPlays", x + 14, y + 27, 0, false);
        context.drawText(textRenderer, "", x + 14, y + 39, 0, false);
        context.drawText(textRenderer, "Greater Wings:", x + 14, y + 51, 0, false);
        context.drawText(textRenderer, "Your wings allow you", x + 14, y + 63, 0, false);
        context.drawText(textRenderer, "control over your", x + 14, y + 75, 0, false);
        context.drawText(textRenderer, "flight.", x + 14, y + 87, 0, false);
        context.drawText(textRenderer, "", x + 14, y + 99, 0, false);
        context.drawText(textRenderer, "Swift Wing:", x + 14, y + 112, 0, false);
        context.drawText(textRenderer, "Your wings allow you", x + 14, y + 124, 0, false);
        context.drawText(textRenderer, "to land safely.", x + 14, y + 136, 0, false);
        context.drawText(textRenderer, "", x + 14, y + 148, 0, false);

        //Page 2
        context.drawText(textRenderer, "", x + 159, y + 15, 0, false);
        context.drawText(textRenderer, "Mystic Appetite:", x + 159, y + 27, 0, false);
        context.drawText(textRenderer, "Due to your mystical", x + 159, y + 39, 0, false);
        context.drawText(textRenderer, "form, you have a", x + 159, y + 51, 0, false);
        context.drawText(textRenderer, "reduced appetite.", x + 159, y + 63, 0, false);
        context.drawText(textRenderer, "", x + 159, y + 75, 0, false);
        context.drawText(textRenderer, "Winged Athletics:", x + 159, y + 87, 0, false);
        context.drawText(textRenderer, "Your wings assist", x + 159, y + 99, 0, false);
        context.drawText(textRenderer, "your movement ability.", x + 159, y + 112, 0, false);
        context.drawText(textRenderer, "", x + 159, y + 124, 0, false);
        context.drawText(textRenderer, "", x + 159, y + 136, 0, false);
        context.drawText(textRenderer, "", x + 159, y + 148, 0, false);

        //Button
            if (closeButton.isHovered()) {
                closeButton.drawTexture(context, TEXTURE, this.width / 2 - 100, 370, 0, 201, 0, 200, 20, 256, 256);
            } else {
                closeButton.drawTexture(context, TEXTURE, this.width / 2 - 100, 370, 0, 181, 86, 200, 20, 256, 256);
            }
            context.drawText(textRenderer, "Done", x + 133, 376, 16777215, true);
        }

    protected void closeScreen() {
        this.client.setScreen((Screen)null);
    }
}
