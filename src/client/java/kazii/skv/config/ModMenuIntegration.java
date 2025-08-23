package kazii.skv.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;


public class ModMenuIntegration implements ModMenuApi{

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory(){
        return parentScreen -> YetAnotherConfigLib.createBuilder()
                .title(Text.literal("Simple Key Viewer Config"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Tick based options"))
                        .tooltip(Text.literal("This has the options that has the options of eather tick based or frame based updates"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Input Text"))
                                .description(OptionDescription.of(Text.literal("These are the input text baised options")))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Use Tick Input Updates"))
                                        .description(OptionDescription.of(Text.literal("This will chose to use tick or frame updates for input text")))
                                        .binding(true, () -> Config.useTickText, newVal -> Config.useTickText = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
                .save(Config.GSON::save)
                .build()
                .generateScreen(parentScreen);
    }
}
