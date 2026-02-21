package fr.paladium.palamod.modules.luckyblock.network.may;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.VieilleHistoire;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketBooksellerChoice implements IMessage {
  private int rewardId;
  
  public PacketBooksellerChoice() {}
  
  public PacketBooksellerChoice(int rewardId) {
    this.rewardId = rewardId;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.rewardId = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.rewardId);
  }
  
  public static class Handler implements IMessageHandler<PacketBooksellerChoice, IMessage> {
    public IMessage onMessage(PacketBooksellerChoice message, MessageContext ctx) {
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      VieilleHistoire.reward(player, message.rewardId);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\may\PacketBooksellerChoice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */