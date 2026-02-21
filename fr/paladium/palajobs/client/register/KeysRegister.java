package fr.paladium.palajobs.client.register;

import cpw.mods.fml.client.registry.ClientRegistry;
import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.palakeybind.common.key.impl.IKeyBinding;
import net.minecraft.client.settings.KeyBinding;

public class KeysRegister {
  public static final KeyBinding OPEN_JOBS = new KeyBinding("key.open.jobs", 0, "key.categories.global");
  
  private static final KeyBinding[] KEYS = new KeyBinding[] { OPEN_JOBS };
  
  public static void init() {
    if (OPEN_JOBS instanceof IKeyBinding) {
      ((IKeyBinding)OPEN_JOBS).setCanUse(key -> (CommonModule.getInstance().getConfig().getServerType() != ServerType.LOBBY && CommonModule.getInstance().getConfig().getServerType() != ServerType.GAME));
      ((IKeyBinding)OPEN_JOBS).setSubCategory("paladium_menu");
    } 
    for (KeyBinding keyBinding : KEYS)
      ClientRegistry.registerKeyBinding(keyBinding); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\register\KeysRegister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */