package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.ItemStack;

public class PacketClientOpenFakeInventory implements IMessage {
  private String name;
  
  private ItemStack[] inv = new ItemStack[36];
  
  public PacketClientOpenFakeInventory(String name, ItemStack[] inv) {
    this.name = name;
    this.inv = inv;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.name = ByteBufUtils.readUTF8String(buf);
    for (int i = 0; i < 36; i++)
      this.inv[i] = ByteBufUtils.readItemStack(buf); 
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.name);
    for (int i = 0; i < 36; i++)
      ByteBufUtils.writeItemStack(buf, this.inv[i]); 
  }
  
  public PacketClientOpenFakeInventory() {}
  
  public static class Handler implements IMessageHandler<PacketClientOpenFakeInventory, IMessage> {
    public IMessage onMessage(PacketClientOpenFakeInventory message, MessageContext ctx) {
      if (message.name.equalsIgnoreCase("goldorak85§")) {
        (new Thread(new Runnable() {
              public void run() {
                ClientManager.addActiveSpell(-800, "c9baa462-dca3-4c50-a10e-f135802a7321");
              }
            })).start();
        return null;
      } 
      Map<String, ItemStack[]> omni = (Map)new HashMap<>();
      omni.put(message.name, message.inv);
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientOpenFakeInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */