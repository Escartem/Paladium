package fr.paladium.palamod.modules.luckyblock.hud.vision.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.hud.vision.ModuleVision;
import fr.paladium.palamod.util.ItemStackSerializer;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Base64;
import net.minecraft.item.ItemStack;

public class ClientVisionPacket implements IMessage {
  private String name;
  
  private String items;
  
  private double health;
  
  public ClientVisionPacket() {}
  
  public ClientVisionPacket(String name, ArrayList<ItemStack> inventory, double health) {
    this.name = name;
    this.health = health;
    String toSplit = "";
    for (ItemStack is : inventory)
      toSplit = toSplit + Base64.getEncoder().encodeToString(ItemStackSerializer.write(is).getBytes()) + ";"; 
    this.items = toSplit;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.name = ByteBufUtils.readUTF8String(buf);
    this.items = ByteBufUtils.readUTF8String(buf);
    this.health = buf.readDouble();
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.name);
    ByteBufUtils.writeUTF8String(buf, this.items);
    buf.writeDouble(this.health);
  }
  
  public static class Handler implements IMessageHandler<ClientVisionPacket, IMessage> {
    public IMessage onMessage(ClientVisionPacket message, MessageContext ctx) {
      String[] splitted = message.items.split(";");
      int i = 0;
      for (String string : splitted) {
        if (!string.isEmpty());
        ItemStack is = ItemStackSerializer.read(new String(Base64.getDecoder().decode(string)));
        ClientProxy.inventoryVision[i] = is;
        i++;
      } 
      ClientProxy.hasVisionSkill = true;
      ClientProxy.healthVision = message.health;
      ClientProxy.nameVision = message.name;
      ModuleVision.getInstance().setLastUpdate(System.currentTimeMillis());
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\hud\vision\packets\ClientVisionPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */