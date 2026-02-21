package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PlayerLuckyPassProperties;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import io.netty.buffer.ByteBuf;
import java.security.SecureRandom;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import scala.actors.threadpool.Arrays;

public class PacketLuckyEventBypass implements IMessage {
  private int randomEffect = (new SecureRandom()).nextInt((LuckyEvents.values()).length - 1);
  
  public void fromBytes(ByteBuf buf) {
    this.randomEffect = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.randomEffect);
  }
  
  public static class Handler implements IMessageHandler<PacketLuckyEventBypass, IMessage> {
    public IMessage onMessage(PacketLuckyEventBypass message, MessageContext ctx) {
      PlayerLuckyPassProperties properties = PlayerLuckyPassProperties.get((EntityPlayer)(ctx.getServerHandler()).field_147369_b);
      if (properties.isHasLuckyEventBypassNow()) {
        properties.setHasLuckyEventBypassNow(false);
        PacketLuckyEventBypass.sendEffectLuckyBlock(message.randomEffect, (ctx.getServerHandler()).field_147369_b);
      } else if (ctx.side == Side.CLIENT) {
        properties.setHasLuckyEventBypassNow(false);
      } 
      (ctx.getServerHandler()).field_147369_b.func_71053_j();
      return null;
    }
  }
  
  private static void sendEffectLuckyBlock(int luckyEvent, EntityPlayerMP entityPlayerMP) {
    List<LuckyEvents> eventLucky = Arrays.asList((Object[])LuckyEvents.values());
    ((LuckyEvents)eventLucky.get(luckyEvent)).getEvent().perform(entityPlayerMP, (int)entityPlayerMP.field_70165_t, (int)entityPlayerMP.field_70163_u, (int)entityPlayerMP.field_70161_v);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketLuckyEventBypass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */