package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PlayerLuckyPassProperties;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketPotionExplose implements IMessage {
  public void fromBytes(ByteBuf buf) {}
  
  public void toBytes(ByteBuf buf) {}
  
  public static class Handler implements IMessageHandler<PacketPotionExplose, IMessage> {
    public IMessage onMessage(PacketPotionExplose message, MessageContext ctx) {
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      if (player.func_82165_m(PLuckyBlock.EXPLOSE.field_76415_H)) {
        long time = PlayerLuckyPassProperties.get((EntityPlayer)player).getTime();
        long now = TimeUtil.now();
        if (now - time >= 1L) {
          player.field_70170_p.func_72876_a((Entity)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 5.0F, true);
          PlayerLuckyPassProperties.get((EntityPlayer)player).setTime(now);
        } 
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketPotionExplose.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */