package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemJobTransferor;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Handler implements IMessageHandler<CSJobTransferorPacket, IMessage> {
  public IMessage onMessage(CSJobTransferorPacket message, MessageContext ctx) {
    if (ctx.side == Side.CLIENT)
      return null; 
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    World world = player.field_70170_p;
    ItemStack heldItem = player.func_70694_bm();
    if (heldItem == null || heldItem.func_77973_b() == null || !(heldItem.func_77973_b() instanceof ItemJobTransferor))
      return null; 
    ItemJobTransferor item = (ItemJobTransferor)heldItem.func_77973_b();
    item.apply(player, heldItem, CSJobTransferorPacket.access$000(message), CSJobTransferorPacket.access$100(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\packets\client\CSJobTransferorPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */