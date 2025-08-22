package kazii.skv.utils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;


public class KeystrokeUtils {
    public static MutableText getKeyStrokes() {
        MutableText out = Text.empty();
        String currentText = "";
        GameOptions gamesettings = MinecraftClient.getInstance().options;
        for (int i = 0 ; i < gamesettings.allKeys.length ; i++) {
            KeyBinding binds = gamesettings.allKeys[i];
            if (binds.isPressed()) {
                out.append(binds.getBoundKeyLocalizedText());
                out.append(Text.literal(" "));
            }

            
        }
        return out;
    }
}