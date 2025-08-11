package kazii.skv;

import kazii.skv.utils.KeystrokeUtils;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleKeyViewerClient implements ClientModInitializer {
    private static final Identifier KeyLayer = Identifier.of("simple-key-viewer","hud-keyboard-layer");
    private static final Logger log = LoggerFactory.getLogger(SimpleKeyViewerClient.class);
    static MutableText keyCodes = Text.empty();
	@Override
	public void onInitializeClient() {
        HudLayerRegistrationCallback.EVENT.register(layeredDrawer -> layeredDrawer.attachLayerBefore(IdentifiedLayer.CHAT, KeyLayer, SimpleKeyViewerClient::render));
        ClientTickEvents.START_CLIENT_TICK.register(minecraftClient -> {keyCodes = KeystrokeUtils.getKeyStrokes();});
    }
    private static void render(DrawContext context, RenderTickCounter tickCounter) {

        int color = 0xFFFFFFFF; // Red
        int targetColor = 0xFF00FF00; // Green
        var client = MinecraftClient.getInstance();
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
            context.fill(drawBoxX1, drawBoxY1, drawBoxX2, drawBoxY2, 0x94303030);
        }
        context.drawText(client.textRenderer,keyCodes, drawTextX, drawTextY,color,false);
    }
}