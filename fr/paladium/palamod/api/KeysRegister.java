package fr.paladium.palamod.api;

import cpw.mods.fml.client.registry.ClientRegistry;
import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.palakeybind.common.key.impl.IKeyBinding;
import net.minecraft.client.settings.KeyBinding;

public class KeysRegister {
  public static final KeyBinding KEY_SHOP = new KeyBinding("Open Shop Menu", 24, "key.categories.faction");
  
  public static final KeyBinding KEY_SPELL_EXIT = new KeyBinding("key.spells.exit", 22, "key.categories.faction");
  
  public static final KeyBinding KEY_SPELL_SELECT = new KeyBinding("key.spells.select", 49, "key.categories.faction");
  
  public static final KeyBinding KEY_SPELL_MENU = new KeyBinding("key.spells.menu", 49, "key.categories.faction");
  
  public static final KeyBinding KEY_SPELL_USE = new KeyBinding("key.spells.use", 22, "key.categories.faction");
  
  public static final KeyBinding KEY_SPELL_USE_1 = new KeyBinding("key.spells.spell1", 2, "key.categories.faction");
  
  public static final KeyBinding KEY_SPELL_USE_2 = new KeyBinding("key.spells.spell2", 3, "key.categories.faction");
  
  public static final KeyBinding KEY_SPELL_USE_3 = new KeyBinding("key.spells.spell3", 4, "key.categories.faction");
  
  public static final KeyBinding[] KEY_SPELL_USES = new KeyBinding[] { KEY_SPELL_USE_1, KEY_SPELL_USE_2, KEY_SPELL_USE_3 };
  
  private static final KeyBinding[] KEYS = new KeyBinding[] { KEY_SHOP, KEY_SPELL_EXIT, KEY_SPELL_SELECT, KEY_SPELL_MENU, KEY_SPELL_USE, KEY_SPELL_USE_1, KEY_SPELL_USE_2, KEY_SPELL_USE_3 };
  
  public static void init() {
    if (KEY_SHOP instanceof IKeyBinding) {
      ((IKeyBinding)KEY_SHOP).setCanUse(key -> (CommonModule.getInstance().getConfig().getServerType() == ServerType.FACTION || CommonModule.getInstance().getConfig().getServerType() == ServerType.MINAGE || CommonModule.getInstance().getConfig().getServerType() == ServerType.RPG));
      ((IKeyBinding)KEY_SHOP).setSubCategory("menu");
    } 
    if (KEY_SPELL_EXIT instanceof IKeyBinding) {
      ((IKeyBinding)KEY_SPELL_EXIT).setCanUse(key -> (CommonModule.getInstance().getConfig().getServerType() == ServerType.FACTION || CommonModule.getInstance().getConfig().getServerType() == ServerType.MINAGE));
      ((IKeyBinding)KEY_SPELL_EXIT).setDefaultCtrl(true);
      ((IKeyBinding)KEY_SPELL_EXIT).setSubCategory("spell");
    } 
    if (KEY_SPELL_SELECT instanceof IKeyBinding) {
      ((IKeyBinding)KEY_SPELL_SELECT).setCanUse(key -> (CommonModule.getInstance().getConfig().getServerType() == ServerType.FACTION || CommonModule.getInstance().getConfig().getServerType() == ServerType.MINAGE));
      ((IKeyBinding)KEY_SPELL_SELECT).setSubCategory("spell");
    } 
    if (KEY_SPELL_MENU instanceof IKeyBinding) {
      ((IKeyBinding)KEY_SPELL_MENU).setCanUse(key -> (CommonModule.getInstance().getConfig().getServerType() == ServerType.FACTION || CommonModule.getInstance().getConfig().getServerType() == ServerType.MINAGE));
      ((IKeyBinding)KEY_SPELL_MENU).setDefaultCtrl(true);
      ((IKeyBinding)KEY_SPELL_MENU).setSubCategory("spell");
    } 
    if (KEY_SPELL_USE instanceof IKeyBinding) {
      ((IKeyBinding)KEY_SPELL_USE).setCanUse(key -> (CommonModule.getInstance().getConfig().getServerType() == ServerType.FACTION || CommonModule.getInstance().getConfig().getServerType() == ServerType.MINAGE));
      ((IKeyBinding)KEY_SPELL_USE).setSubCategory("spell");
    } 
    for (KeyBinding keyBinding : KEY_SPELL_USES) {
      if (keyBinding instanceof IKeyBinding) {
        ((IKeyBinding)keyBinding).setCanUse(key -> (CommonModule.getInstance().getConfig().getServerType() == ServerType.FACTION || CommonModule.getInstance().getConfig().getServerType() == ServerType.MINAGE));
        ((IKeyBinding)keyBinding).setDefaultCtrl(true);
        ((IKeyBinding)keyBinding).setSubCategory("spell");
      } 
    } 
    for (KeyBinding keyBinding : KEYS)
      ClientRegistry.registerKeyBinding(keyBinding); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\api\KeysRegister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */