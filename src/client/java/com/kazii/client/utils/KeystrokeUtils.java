package com.kazii.client.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.client.Options;
import net.minecraft.client.KeyMapping;

public class KeystrokeUtils {
    public static MutableComponent getKeySTrokes(){
        MutableComponent out = Component.empty();
        String currentTest = "";
        Options gamesettings = Minecraft.getInstance().options;
        for (int i = 0 ; i < gamesettings.keyMappings.length ; i++){
            KeyMapping binds = gamesettings.keyMappings[i];
            if (binds.isDown()){
                if (!out.contains(binds.getTranslatedKeyMessage())) { //little hacky but hey. it works and i couldent think of a better way
                    out.append(binds.getTranslatedKeyMessage());
                    out.append(Component.literal(" "));
                }
            }

        }
        return out;
    }
}
