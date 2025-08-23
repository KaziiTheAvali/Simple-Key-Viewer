package kazii.skv.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.awt.*;


public class ModMenuIntegration implements ModMenuApi{ ;
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory(){
        return parentScreen -> YetAnotherConfigLib.createBuilder()
                .title(Text.literal("Simple Key Viewer Config"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("All Options"))
                        .tooltip(Text.literal("All options for the mod"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Tick baised options"))
                                .description(OptionDescription.of(Text.literal("These are the tick baised options")))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Use Tick Input Updates"))
                                        .description(OptionDescription.of(Text.literal("This will chose to use tick or frame updates for input text")))
                                        .binding(true, () -> Config.useTickText, newVal -> Config.useTickText = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Color baised options"))
                                .description(OptionDescription.of(Text.literal("These are the color baised options to deside the colour")))
                                .option(Option.<Color>createBuilder()
                                        .name(Text.literal("Color of text"))
                                        .description(OptionDescription.of(Text.literal("Changes the color of text")))
                                        .binding(new Color(0xFFFFFFFF), () -> Config.textColor, newVal -> Config.textColor = newVal)
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.<Color>createBuilder()
                                        .name(Text.literal("Color of background"))
                                        .description(OptionDescription.of(Text.literal("Changes the color of the background to the text.includes alpha")))
                                        .binding(new Color(0x94303030),()-> Config.backgroundColor,newVal -> Config.backgroundColor=newVal)
                                        .controller(opt -> ColorControllerBuilder.create(opt)
                                                .allowAlpha(true))
                                        .build())
                                .build())

                        .build())
                .save(Config.GSON::save)
                .build()
                .generateScreen(parentScreen);
    }
}
