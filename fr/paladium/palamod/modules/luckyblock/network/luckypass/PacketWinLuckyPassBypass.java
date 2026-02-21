package fr.paladium.palamod.modules.luckyblock.network.luckypass;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class PacketWinLuckyPassBypass implements IMessage {
  public void fromBytes(ByteBuf buf) {}
  
  public void toBytes(ByteBuf buf) {}
  
  public static class Handler implements IMessageHandler<PacketWinLuckyPassBypass, IMessage> {
    public IMessage onMessage(PacketWinLuckyPassBypass message, MessageContext ctx) {
      PlayerLuckyPassProperties properties = PlayerLuckyPassProperties.get((EntityPlayer)(ctx.getServerHandler()).field_147369_b);
      if (ctx.side == Side.SERVER && properties.isHasLuckyEventBypassNow()) {
        (ctx.getServerHandler()).field_147369_b.field_70170_p
          .func_72956_a((Entity)(ctx.getServerHandler()).field_147369_b, "fireworks.launch", 3.0F, 1.0F);
        if (!(ctx.getServerHandler()).field_147369_b.field_71071_by.func_70441_a(new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK))) {
          PlayerUtils.dropItemStack((Entity)(ctx.getServerHandler()).field_147369_b, 
              (ctx.getServerHandler()).field_147369_b.field_70165_t, (ctx.getServerHandler()).field_147369_b.field_70163_u, 
              (ctx.getServerHandler()).field_147369_b.field_70161_v, new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK));
          properties.setHasLuckyPassBypassNow(false);
        } 
      } else if (ctx.side == Side.CLIENT) {
        properties.setHasLuckyPassBypassNow(false);
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\luckypass\PacketWinLuckyPassBypass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */