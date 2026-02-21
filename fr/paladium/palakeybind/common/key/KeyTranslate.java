package fr.paladium.palakeybind.common.key;

import fr.paladium.palakeybind.common.key.impl.IKeyBinding;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyTranslate {
  public static Map<Integer, String> translations = new HashMap<>();
  
  static {
    translations.put(Integer.valueOf(78), "+");
    translations.put(Integer.valueOf(181), "/");
    translations.put(Integer.valueOf(13), "=");
    translations.put(Integer.valueOf(26), "]");
    translations.put(Integer.valueOf(27), "[");
    translations.put(Integer.valueOf(56), "ALT");
    translations.put(Integer.valueOf(184), "RIGHT ALT");
    translations.put(Integer.valueOf(55), "*");
    translations.put(Integer.valueOf(141), "=");
    translations.put(Integer.valueOf(52), ".");
    translations.put(Integer.valueOf(29), "CTRL");
    translations.put(Integer.valueOf(157), "RIGHT CTRL");
    translations.put(Integer.valueOf(54), "RIGHT SHIFT");
    translations.put(Integer.valueOf(53), "/");
    translations.put(Integer.valueOf(43), "\\");
    translations.put(Integer.valueOf(74), "-");
  }
  
  public static String getKeyDisplayString(KeyBinding keybinding) {
    if (keybinding.func_151463_i() < 0)
      return I18n.func_135052_a("key.mouseButton", new Object[] { Integer.valueOf(keybinding.func_151463_i() + 101) }); 
    StringBuilder prefix = new StringBuilder();
    if (keybinding instanceof IKeyBinding) {
      IKeyBinding k = (IKeyBinding)keybinding;
      if (k.isCtrl() && keybinding.func_151463_i() != 29)
        prefix.append("ctrl + "); 
      if (k.isRightCtrl() && keybinding.func_151463_i() != 157)
        prefix.append("right ctrl + "); 
      if (k.isShift() && keybinding.func_151463_i() != 42)
        prefix.append("shift + "); 
      if (k.isRightShift() && keybinding.func_151463_i() != 54)
        prefix.append("right shift + "); 
      if (k.isAlt() && keybinding.func_151463_i() != 56)
        prefix.append("alt + "); 
      if (k.isRightAlt() && keybinding.func_151463_i() != 184)
        prefix.append("right alt + "); 
    } 
    return prefix.toString().toUpperCase() + (translations.containsKey(Integer.valueOf(keybinding.func_151463_i())) ? translations.get(Integer.valueOf(keybinding.func_151463_i())) : Keyboard.getKeyName(keybinding.func_151463_i()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palakeybind\common\key\KeyTranslate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */