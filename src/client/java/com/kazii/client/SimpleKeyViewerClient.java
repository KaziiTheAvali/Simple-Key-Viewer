package com.kazii.client;

import com.kazii.SimpleKeyViewer;
import com.kazii.client.utils.KeystrokeUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;


public class SimpleKeyViewerClient implements ClientModInitializer {

	static MutableComponent keyCodes = Component.empty();
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		HudElementRegistry.attachElementAfter(VanillaHudElements.CHAT, SimpleKeyViewer.id("afterchat"), SimpleKeyViewerClient::extract);
		ClientTickEvents.START_CLIENT_TICK.register(SimpleKeyViewerClient::onStartTick);
	}

	private static void onStartTick(Minecraft minecraft) {
		keyCodes = KeystrokeUtils.getKeySTrokes();
	}


	private static void extract(GuiGraphicsExtractor graphics, DeltaTracker tickCounter) {
		int color = 0xFFFFFFFF;
		var client = Minecraft.getInstance();
		int backgroundColor = 0x94303030;
		int screenWidth = client.getWindow().getGuiScaledWidth();
		int screenHeight = client.getWindow().getGuiScaledHeight();
		int textWidth=client.font.width(keyCodes);
		int textHeight = 8;
		int padding = 3;
		int offset = 15;
		int drawTextX = 10;
		int drawTextY = screenHeight-offset;
		int drawBoxX1 = drawTextX-padding;
		int drawBoxY1 = drawTextY-padding;
		int drawBoxX2 = drawTextX+textWidth;
		int drawBoxY2 = drawTextY+textHeight+padding;

		if (textWidth!= 0){
			graphics.fill(drawBoxX1,drawBoxY1,drawBoxX2,drawBoxY2,backgroundColor);

		}
		graphics.text(client.font, keyCodes, drawTextX, drawTextY,color,false);
	}
}