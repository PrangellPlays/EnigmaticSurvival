package dev.prangellplays.enigmaticsurvival.client.screen.choicelectern.origin;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.prangellplays.enigmaticsurvival.EnigmaticSurvival;
import dev.prangellplays.enigmaticsurvival.client.screen.choicelectern.classes.RogueChoiceLecternPage3Screen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class BlimpTylerChoiceLecternScreen extends Screen {
    private static final Identifier TEXTURE = new Identifier(EnigmaticSurvival.MOD_ID, "textures/gui/choicelectern/base.png");
    public ButtonWidget closeButton = ButtonWidget.builder(ScreenTexts.DONE, (button) -> {this.close();}).dimensions(this.width / 2 - 100, 370, 200, 20).build();
    public ButtonWidget arrowButton = ButtonWidget.builder(ScreenTexts.DONE, (button) -> {this.close();}).dimensions(this.width / 2 - 55, 335, 18, 10).build();

    public BlimpTylerChoiceLecternScreen() {
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
        }).dimensions(this.width / 2 + 105, 335, 18, 10).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - 290) / 2;
        int y = (height - 180) / 2;
        context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        context.drawTexture(TEXTURE, x, y, 0, 0, 146, 180);
        context.drawTexture(TEXTURE, x + 145, y, 0, 0, 146, 180);
        context.drawTexture(TEXTURE, x + 99, y + 123, 21, 222, 11, 11);
        TextRenderer textRenderer = MinecraftClient.getInstance().inGameHud.getTextRenderer();
        //Page
        context.drawText(textRenderer, "", x + 14, y + 15, 0, false);
        context.drawText(textRenderer, "Origin: BlimpTyler", x + 14, y + 27, 0, false);
        context.drawText(textRenderer, "", x + 14, y + 39, 0, false);
        context.drawText(textRenderer, "Ninja Ligaments:", x + 14, y + 51, 0, false);
        context.drawText(textRenderer, "Your ligaments have", x + 14, y + 63, 0, false);
        context.drawText(textRenderer, "been stretched and", x + 14, y + 75, 0, false);
        context.drawText(textRenderer, "torn in the art of", x + 14, y + 87, 0, false);
        context.drawText(textRenderer, "speed.", x + 14, y + 99, 0, false);
        context.drawText(textRenderer, "", x + 14, y + 112, 0, false);
        context.drawText(textRenderer, "Magical healing:", x + 14, y + 124, 0, false);
        context.drawText(textRenderer, "You have learnt an", x + 14, y + 136, 0, false);
        context.drawText(textRenderer, "", x + 14, y + 148, 0, false);

        //Page 2
        context.drawText(textRenderer, "", x + 159, y + 15, 0, false);
        context.drawText(textRenderer, "ancient art specified", x + 159, y + 27, 0, false);
        context.drawText(textRenderer, "in healing magic.", x + 159, y + 39, 0, false);
        context.drawText(textRenderer, "", x + 159, y + 51, 0, false);
        context.drawText(textRenderer, "Soft Fall:", x + 159, y + 63, 0, false);
        context.drawText(textRenderer, "Your body has", x + 159, y + 75, 0, false);
        context.drawText(textRenderer, "adapted to falling", x + 159, y + 87, 0, false);
        context.drawText(textRenderer, "large distances.", x + 159, y + 99, 0, false);
        context.drawText(textRenderer, "", x + 159, y + 112, 0, false);
        context.drawText(textRenderer, "Agile arms:", x + 159, y + 124, 0, false);
        context.drawText(textRenderer, "Your arms have been", x + 159, y + 136, 0, false);
        context.drawText(textRenderer, "", x + 159, y + 148, 0, false);

        //Button
        if (closeButton.isHovered()) {
            closeButton.drawTexture(context, TEXTURE, this.width / 2 - 100, 370, 0, 201, 0, 200, 20, 256, 256);
        } else {
            closeButton.drawTexture(context, TEXTURE, this.width / 2 - 100, 370, 0, 181, 86, 200, 20, 256, 256);
        }
        context.drawText(textRenderer, "Done", x + 133, 376, 16777215, true);

        arrowButton.drawTexture(context, TEXTURE, this.width / 2 + 105, 335, 0, 222, 0, 18, 10, 256, 256);
    }

    protected void closeScreen() {
        this.client.setScreen((Screen)null);
    }

    protected void changeScreen() {
        this.client.setScreen(new BlimpTylerChoiceLecternPage3Screen());
    }
}