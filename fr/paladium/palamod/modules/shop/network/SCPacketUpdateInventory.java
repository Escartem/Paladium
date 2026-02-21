package fr.paladium.palamod.modules.shop.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.utils.Packet;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;

@Packet(side = Side.CLIENT)
public class SCPacketUpdateInventory implements IMessage {
  private InventoryPlayer inventoryPlayer;
  
  public SCPacketUpdateInventory(InventoryPlayer inventoryPlayer) {
    this.inventoryPlayer = inventoryPlayer;
  }
  
  public SCPacketUpdateInventory() {}
  
  @SideOnly(Side.CLIENT)
  public void fromBytes(ByteBuf buf) {
    this.inventoryPlayer = (Minecraft.func_71410_x()).field_71439_g.field_71071_by;
    int i;
    for (i = 0; i < this.inventoryPlayer.field_70462_a.length; i++)
      this.inventoryPlayer.field_70462_a[i] = ByteBufUtils.readItemStack(buf); 
    for (i = 0; i < this.inventoryPlayer.field_70460_b.length; i++)
      this.inventoryPlayer.field_70460_b[i] = ByteBufUtils.readItemStack(buf); 
  }
  
  public void toBytes(ByteBuf buf) {
    int i;
    for (i = 0; i < this.inventoryPlayer.field_70462_a.length; i++)
      ByteBufUtils.writeItemStack(buf, this.inventoryPlayer.field_70462_a[i]); 
    for (i = 0; i < this.inventoryPlayer.field_70460_b.length; i++)
      ByteBufUtils.writeItemStack(buf, this.inventoryPlayer.field_70460_b[i]); 
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<SCPacketUpdateInventory, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketUpdateInventory message, MessageContext ctx) {
      (Minecraft.func_71410_x()).field_71439_g.field_71071_by = message.inventoryPlayer;
      (Minecraft.func_71410_x()).field_71439_g.field_71071_by.field_70459_e = true;
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\network\SCPacketUpdateInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */