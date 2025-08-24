package kazii.skv;

import  kazii.skv.config.Config;

import com.mojang.datafixers.kinds.OptionalBox;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import kazii.skv.utils.KeystrokeUtils;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;


public class SimpleKeyViewerClient implements ClientModInitializer {
    private static final Identifier KeyLayer = Identifier.of("simple-key-viewer","hud-keyboard-layer");
    private static final Logger log = LoggerFactory.getLogger(SimpleKeyViewerClient.class);
    static MutableText keyCodes = Text.empty();
    static boolean useTickText=Config.useTickText;
	@Override
	public void onInitializeClient() {
        HudRenderCallback.EVENT.register(SimpleKeyViewerClient::render);
        ClientTickEvents.START_CLIENT_TICK.register(minecraftClient -> startOfTick());
        Config.GSON.load();
    }
    private static void render(DrawContext context, RenderTickCounter tickCounter) {
        if (!useTickText){
            keyCodes = KeystrokeUtils.getKeyStrokes();
        }
        int color = Config.textColor.getRGB(); //Text color

        var client = MinecraftClient.getInstance();
        int backgroundColor = Config.backgroundColor.getRGB();
        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();
        int textWidth=client.textRenderer.getWidth(keyCodes);
        int textHeight = 8;
        int padding = 3;
        int offset = 15;
        int drawTextX = 10;
        int drawTextY = screenHeight-offset;
        int drawBoxX1 = drawTextX-padding;
        int drawBoxY1 = drawTextY-padding;
        int drawBoxX2 = drawTextX+textWidth;
        int drawBoxY2 = drawTextY+textHeight+padding;

        if (textWidth != 0) {
            context.fill(drawBoxX1, drawBoxY1, drawBoxX2, drawBoxY2, backgroundColor);
        }
        context.drawText(client.textRenderer,keyCodes, drawTextX, drawTextY,color,false);
    }
    public void startOfTick(){
        useTickText=Config.useTickText;
        if (useTickText) {
            keyCodes = KeystrokeUtils.getKeyStrokes();
        }
    }
}