package fr.paladium.palakeybind.common.key;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palakeybind.common.key.impl.IKeyBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;

public class KeyTransformer {
  public static void init() {
    if (FMLCommonHandler.instance().getEffectiveSide() != Side.CLIENT)
      return; 
    Minecraft mc = Minecraft.func_71410_x();
    GameSettings gameSettings = mc.field_71474_y;
    if (!(gameSettings.field_74314_A instanceof IKeyBinding))
      return; 
    KeyBinding[] defaultKeys = gameSettings.field_74324_K;
    gameSettings.field_74314_A = new KeyBinding("key.jump", 57, "key.categories.global");
    gameSettings.field_74311_E = new KeyBinding("key.sneak", 42, "key.categories.global");
    gameSettings.field_74370_x = new KeyBinding("key.left", 16, "key.categories.global");
    gameSettings.field_74366_z = new KeyBinding("key.right", 32, "key.categories.global");
    gameSettings.field_74368_y = new KeyBinding("key.back", 31, "key.categories.global");
    gameSettings.field_74351_w = new KeyBinding("key.forward", 44, "key.categories.global");
    setSubCategory("movement", new KeyBinding[] { gameSettings.field_74314_A, gameSettings.field_74311_E, gameSettings.field_74370_x, gameSettings.field_74366_z, gameSettings.field_74368_y, gameSettings.field_74351_w });
    gameSettings.field_74312_F = new KeyBinding("key.attack", -100, "key.categories.global");
    gameSettings.field_74316_C = new KeyBinding("key.drop", 30, "key.categories.global");
    gameSettings.field_74322_I = new KeyBinding("key.pickItem", -98, "key.categories.global");
    gameSettings.field_151444_V = new KeyBinding("key.sprint", 56, "key.categories.global");
    gameSettings.field_74313_G = new KeyBinding("key.use", -99, "key.categories.global");
    setSubCategory("gameplay", new KeyBinding[] { gameSettings.field_74312_F, gameSettings.field_74316_C, gameSettings.field_74322_I, gameSettings.field_151444_V, gameSettings.field_74313_G });
    gameSettings.field_74321_H = new KeyBinding("key.playerlist", 15, "key.categories.global");
    gameSettings.field_74310_D = new KeyBinding("key.chat", 20, "key.categories.global");
    gameSettings.field_74323_J = new KeyBinding("key.command", 53, "key.categories.global");
    setSubCategory("multiplayer", new KeyBinding[] { gameSettings.field_74321_H, gameSettings.field_74310_D, gameSettings.field_74323_J });
    gameSettings.field_151456_ac = new KeyBinding[] { new KeyBinding("key.hotbar.1", 2, "key.categories.global"), new KeyBinding("key.hotbar.2", 3, "key.categories.global"), new KeyBinding("key.hotbar.3", 4, "key.categories.global"), new KeyBinding("key.hotbar.4", 5, "key.categories.global"), new KeyBinding("key.hotbar.5", 6, "key.categories.global"), new KeyBinding("key.hotbar.6", 7, "key.categories.global"), new KeyBinding("key.hotbar.7", 8, "key.categories.global"), new KeyBinding("key.hotbar.8", 9, "key.categories.global"), new KeyBinding("key.hotbar.9", 10, "key.categories.global") };
    gameSettings.field_151445_Q = new KeyBinding("key.inventory", 18, "key.categories.global");
    setSubCategory("inventory", (KeyBinding[])ArrayUtils.addAll((Object[])gameSettings.field_151456_ac, (Object[])new KeyBinding[] { gameSettings.field_151445_Q }));
    gameSettings.field_151447_Z = new KeyBinding("key.screenshot", 60, "key.categories.global");
    gameSettings.field_151458_ab = new KeyBinding("key.smoothCamera", 0, "key.categories.global");
    gameSettings.field_152395_am = new KeyBinding("key.fullscreen", 87, "key.categories.global");
    gameSettings.field_151457_aa = new KeyBinding("key.togglePerspective", 63, "key.categories.global");
    setSubCategory("misc", new KeyBinding[] { gameSettings.field_151447_Z, gameSettings.field_151458_ab, gameSettings.field_152395_am, gameSettings.field_151457_aa });
    gameSettings.field_152397_ao = new KeyBinding("key.streamPauseUnpause", 0, "key.categories.other");
    setCanUse(gameSettings.field_152397_ao, key -> false);
    gameSettings.field_152399_aq = new KeyBinding("key.streamToggleMic", 0, "key.categories.other");
    setCanUse(gameSettings.field_152399_aq, key -> false);
    gameSettings.field_152398_ap = new KeyBinding("key.streamCommercial", 0, "key.categories.other");
    setCanUse(gameSettings.field_152398_ap, key -> false);
    gameSettings.field_152396_an = new KeyBinding("key.streamStartStop", 0, "key.categories.other");
    setCanUse(gameSettings.field_152396_an, key -> false);
    setSubCategory("streaming", new KeyBinding[] { gameSettings.field_152397_ao, gameSettings.field_152399_aq, gameSettings.field_152398_ap, gameSettings.field_152396_an });
    KeyBinding[] newKeys = (KeyBinding[])ArrayUtils.addAll((Object[])new KeyBinding[] { 
          gameSettings.field_74312_F, gameSettings.field_74313_G, gameSettings.field_74351_w, gameSettings.field_74370_x, gameSettings.field_74368_y, gameSettings.field_74366_z, gameSettings.field_74314_A, gameSettings.field_74311_E, gameSettings.field_74316_C, gameSettings.field_151445_Q, 
          gameSettings.field_74310_D, gameSettings.field_74321_H, gameSettings.field_74322_I, gameSettings.field_74323_J, gameSettings.field_151447_Z, gameSettings.field_151457_aa, gameSettings.field_151458_ab, gameSettings.field_151444_V, gameSettings.field_152396_an, gameSettings.field_152397_ao, 
          gameSettings.field_152398_ap, gameSettings.field_152399_aq, gameSettings.field_152395_am }, (Object[])gameSettings.field_151456_ac);
    List<KeyBinding> toAdd = new ArrayList<>();
    for (KeyBinding key : defaultKeys) {
      boolean found = false;
      for (KeyBinding nkey : newKeys) {
        if (Objects.equals(key.func_151464_g(), nkey.func_151464_g())) {
          found = true;
          break;
        } 
      } 
      if (!found) {
        setSubCategory("optifine", new KeyBinding[] { key });
        toAdd.add(key);
        System.out.println("[BetterKeyBinding] Injecting keybinding : " + key.func_151464_g());
      } 
    } 
    gameSettings.field_74324_K = (KeyBinding[])ArrayUtils.addAll((Object[])newKeys, toAdd.toArray((Object[])new KeyBinding[0]));
  }
  
  @SideOnly(Side.CLIENT)
  private static void setCanUse(KeyBinding keyBinding, Predicate<KeyBinding> predicate) {
    if (keyBinding instanceof IKeyBinding)
      ((IKeyBinding)keyBinding).setCanUse(predicate); 
  }
  
  @SideOnly(Side.CLIENT)
  private static void setSubCategory(String subCategory, KeyBinding... keyBindings) {
    for (KeyBinding keyBinding : keyBindings) {
      if (keyBinding instanceof IKeyBinding)
        ((IKeyBinding)keyBinding).setSubCategory(subCategory); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palakeybind\common\key\KeyTransformer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */