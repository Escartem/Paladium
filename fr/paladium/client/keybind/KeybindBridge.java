package fr.paladium.client.keybind;

import cpw.mods.fml.common.Loader;
import fr.paladium.common.tooltip.ModuleItemTooltip;
import fr.paladium.palakeybind.common.key.KeyTranslate;
import fr.paladium.palakeybind.common.key.impl.IKeyBinding;
import net.minecraft.client.settings.KeyBinding;

public class KeybindBridge {
  public static boolean isPalakeybindLoaded() {
    return Loader.isModLoaded("palakeybind");
  }
  
  public static String getKeyDisplayString() {
    if (isPalakeybindLoaded())
      try {
        return KeyTranslate.getKeyDisplayString(ModuleItemTooltip.getInstance().getKeyWiki());
      } catch (NoClassDefFoundError noClassDefFoundError) {} 
    return "PalaKeyBind not found";
  }
  
  public static void addHeliosSubcategory(KeyBinding keyWiki) {
    if (isPalakeybindLoaded())
      try {
        if (keyWiki instanceof IKeyBinding)
          ((IKeyBinding)keyWiki).setSubCategory("helios"); 
      } catch (NoClassDefFoundError noClassDefFoundError) {} 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\client\keybind\KeybindBridge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */