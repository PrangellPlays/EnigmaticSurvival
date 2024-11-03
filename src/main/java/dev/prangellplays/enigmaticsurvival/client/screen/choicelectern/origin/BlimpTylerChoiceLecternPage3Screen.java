package dev.prangellplays.enigmaticsurvival.client.screen.choicelectern.origin;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.prangellplays.enigmaticsurvival.EnigmaticSurvival;
import dev.prangellplays.enigmaticsurvival.client.screen.choicelectern.classes.ClericChoiceLecternScreen;
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
public class BlimpTylerChoiceLecternPage3Screen extends Screen {
    private static final Identifier TEXTURE = new Identifier(EnigmaticSurvival.MOD_ID, "textures/gui/choicelectern/base.png");
    public ButtonWidget closeButton = ButtonWidget.builder(ScreenTexts.DONE, (button) -> {this.close();}).dimensions(this.width / 2 - 100, 370, 200, 20).build();
    public ButtonWidget arrowButton = ButtonWidget.builder(ScreenTexts.DONE, (button) -> {this.close();}).dimensions(this.width / 2 - 55, 335, 18, 10).build();

    public BlimpTylerChoiceLecternPage3Screen() {
        super(Text.translatable("container.lectern"));
    }

    @Override
    protected void init() {
        this.addCloseButton();
        this.addArrowButton();
        addDrawableChild(closeButton);
        addDrawableChild(arrowButton);
    }

    protected void addCloseButton() {
        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
            this.close();
        }).dimensions(this.width / 2 - 100, 370, 200, 20).build());
    }

    protected void addArrowButton() {
        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
            this.changeScreen();
        }).dimensions(this.width / 2 - 55, 335, 18, 10).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - 146) / 2;
        int y = (height - 180) / 2;
        context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        context.drawTexture(TEXTURE, x, y, 0, 0, 146, 180);
        TextRenderer textRenderer = MinecraftClient.getInstance().inGameHud.getTextRenderer();
        //Page
        context.drawText(textRenderer, "", x + 14, y + 15, 0, false);
        context.drawText(textRenderer, "trained to allow you to", x + 14, y + 27, 0, false);
        context.drawText(textRenderer, "mine and attack much", x + 14, y + 39, 0, false);
        context.drawText(textRenderer, "faster.", x + 14, y + 51, 0, false);
        context.drawText(textRenderer, "", x + 14, y + 63, 0, false);
        context.drawText(textRenderer, "", x + 14, y + 75, 0, false);
        context.drawText(textRenderer, "", x + 14, y + 87, 0, false);
        context.drawText(textRenderer, "", x + 14, y + 99, 0, false);
        context.drawText(textRenderer, "", x + 14, y + 112, 0, false);
        context.drawText(textRenderer, "", x + 14, y + 124, 0, false);
        context.drawText(textRenderer, "", x + 14, y + 136, 0, false);
        context.drawText(textRenderer, "", x + 14, y + 148, 0, false);

        //Button
        if (closeButton.isHovered()) {
            closeButton.drawTexture(context, TEXTURE, this.width / 2 - 100, 370, 0, 201, 0, 200, 20, 256, 256);
        } else {
            closeButton.drawTexture(context, TEXTURE, this.width / 2 - 100, 370, 0, 181, 86, 200, 20, 256, 256);
        }
        context.drawText(textRenderer, "Done", x + 61, 376, 16777215, true);

        arrowButton.drawTexture(context, TEXTURE, this.width / 2 - 55, 335, 0, 235, 0, 18, 10, 256, 256);
    }

    protected void closeScreen() {
        this.client.setScreen((Screen)null);
    }

    protected void changeScreen() {
        this.client.setScreen(new BlimpTylerChoiceLecternScreen());
    }
}