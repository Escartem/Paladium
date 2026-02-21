package fr.paladium.palamod.modules.luckyblock.network.christmas;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.gui.christmas.ContainerChristmasSantaNoescrocTrade;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class Handler implements IMessageHandler<PacketChristmasSantaNoescroc, PacketChristmasSantaNoescroc> {
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
      buffer.setSantaNoescroc(PacketChristmasSantaNoescroc.access$000(packet));
    } 
    return null;
  }
  
  public PacketChristmasSantaNoescroc handleServer(PacketChristmasSantaNoescroc packet, EntityPlayer player) {
    if (player == null)
      return null; 
    Entity entity = player.field_70170_p.func_73045_a(PacketChristmasSantaNoescroc.access$000(packet));
    if (!(entity instanceof fr.paladium.palamod.modules.luckyblock.entity.christmas.EntitySantaNoescroc))
      return null; 
    ItemStack buffer = new ItemStack(Blocks.field_150346_d);
    buffer.func_151001_c(PacketChristmasSantaNoescroc.access$100(packet));
    PlayerUtil.addItemStackToInventory(buffer, player.field_71071_by);
    entity.func_70106_y();
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\christmas\PacketChristmasSantaNoescroc$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */