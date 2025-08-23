package kazii.skv.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class Config {
    public static final ConfigClassHandler<Config> GSON = ConfigClassHandler
            .createBuilder(Config.class)
            .id(Identifier.of("simple-key-viewer","config"))
            .serializer(config -> GsonConfigSerializerBuilder
                    .create(config)
                    .setPath(YACLPlatform.getConfigDir().resolve("simple-key-viewer.json5"))
                    .setJson5(true)
                    .build())
            .build();

    @SerialEntry
    public static boolean useTickText = true;


}
