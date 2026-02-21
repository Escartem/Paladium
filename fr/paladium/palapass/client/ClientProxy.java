package fr.paladium.palapass.client;

import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.palapass.common.CommonProxy;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.ItemStack;

public class ClientProxy extends CommonProxy {
  private static Map<String, ItemStack> cachedIcons = new HashMap<>();
  
  public static ItemStack getIcon(String icon) {
    if (cachedIcons.containsKey(icon))
      return cachedIcons.get(icon); 
    ItemStack is = ItemStackSerializer.read(new String(Base64.getDecoder().decode(icon)));
    cachedIcons.put(icon, is);
    return is;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\client\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */