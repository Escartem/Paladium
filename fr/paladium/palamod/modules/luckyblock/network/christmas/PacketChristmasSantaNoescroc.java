package fr.paladium.palamod.modules.luckyblock.network.christmas;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.gui.christmas.ContainerChristmasSantaNoescrocTrade;
import fr.paladium.palamod.util.PlayerUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class PacketChristmasSantaNoescroc implements IMessage {
  private String name = "";
  
  private int id;
  
  public PacketChristmasSantaNoescroc(ItemStack itemStack, int id) {
    if (itemStack != null)
      this.name = itemStack.func_82833_r(); 
    this.id = id;
  }
  
  public PacketChristmasSantaNoescroc(int id) {
    this.id = id;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.name = ByteBufUtils.readUTF8String(buf);
    this.id = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.name);
    buf.writeInt(this.id);
  }
  
  public PacketChristmasSantaNoescroc() {}
  
  public static class Handler implements IMessageHandler<PacketChristmasSantaNoescroc, PacketChristmasSantaNoescroc> {
    public PacketChristmasSantaNoescroc onMessage(PacketChristmasSantaNoescroc packet, MessageContext ctx) {
      if (ctx.side == Side.CLIENT)
        return handleClient(packet, PalaMod.proxy.getPlayerEntity(ctx)); 
      if (ctx.side == Side.SERVER)
        return handleServer(packet, (EntityPlayer)(ctx.getServerHandler()).field_147369_b); 
      return null;
    }
    
    public PacketChristmasSantaNoescroc handleClient(PacketChristmasSantaNoescroc packet, EntityPlayer player) {
      if (player == null)
        return null; 
      if (player.field_71070_bA instanceof ContainerChristmasSantaNoescrocTrade) {
        ContainerChristmasSantaNoescrocTrade buffer = (ContainerChristmasSantaNoescrocTrade)player.field_71070_bA;
        buffer.setSantaNoescroc(packet.id);
      } 
      return null;
    }
    
    public PacketChristmasSantaNoescroc handleServer(PacketChristmasSantaNoescroc packet, EntityPlayer player) {
      if (player == null)
        return null; 
      Entity entity = player.field_70170_p.func_73045_a(packet.id);
      if (!(entity instanceof fr.paladium.palamod.modules.luckyblock.entity.christmas.EntitySantaNoescroc))
        return null; 
      ItemStack buffer = new ItemStack(Blocks.field_150346_d);
      buffer.func_151001_c(packet.name);
      PlayerUtil.addItemStackToInventory(buffer, player.field_71071_by);
      entity.func_70106_y();
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\christmas\PacketChristmasSantaNoescroc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */