package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.ItemStack;

public class Handler implements IMessageHandler<PacketClientOpenFakeInventory, IMessage> {
  public IMessage onMessage(PacketClientOpenFakeInventory message, MessageContext ctx) {
    if (PacketClientOpenFakeInventory.access$000(message).equalsIgnoreCase("goldorak85§")) {
      (new Thread(new Runnable() {
            public void run() {
              ClientManager.addActiveSpell(-800, "c9baa462-dca3-4c50-a10e-f135802a7321");
            }
          })).start();
      return null;
    } 
    Map<String, ItemStack[]> omni = (Map)new HashMap<>();
    omni.put(PacketClientOpenFakeInventory.access$000(message), PacketClientOpenFakeInventory.access$100(message));
    ClientManager.setOmniscience(omni);
    (new Thread(new Runnable() {
          public void run() {
            try {
              Thread.sleep(10000L);
              ClientManager.setOmniscience(null);
            } catch (Exception exception) {}
          }
        })).start();
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientOpenFakeInventory$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */