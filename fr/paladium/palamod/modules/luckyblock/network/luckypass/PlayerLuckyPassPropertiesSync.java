package fr.paladium.palamod.modules.luckyblock.network.luckypass;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.PalaMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerLuckyPassPropertiesSync implements IMessage {
  private boolean hasLuckyPass = false;
  
  private int[] wins = new int[3];
  
  private long date;
  
  private String entity;
  
  public PlayerLuckyPassPropertiesSync(PlayerLuckyPassProperties prop) {
    this.hasLuckyPass = prop.isHasLuckyPass();
    this.wins = prop.getWins();
    this.date = prop.getDate();
    this.entity = prop.getEntity().func_70005_c_();
  }
  
  public void fromBytes(ByteBuf buf) {
    this.hasLuckyPass = buf.readBoolean();
    this.date = buf.readLong();
    this.entity = ByteBufUtils.readUTF8String(buf);
    int i = 0;
    while (buf.isReadable()) {
      if (i < 3)
        this.wins[i] = buf.readInt(); 
      i++;
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(this.hasLuckyPass);
    buf.writeLong(this.date);
    ByteBufUtils.writeUTF8String(buf, this.entity);
    for (int i : this.wins)
      buf.writeInt(i); 
  }
  
  public PlayerLuckyPassPropertiesSync() {}
  
  public static class Handler implements IMessageHandler<PlayerLuckyPassPropertiesSync, IMessage> {
    public IMessage onMessage(PlayerLuckyPassPropertiesSync message, MessageContext ctx) {
      EntityPlayer player = PalaMod.proxy.getPlayerEntity(ctx).func_130014_f_().func_72924_a(message.entity);
      if (player == null)
        return null; 
      PlayerLuckyPassProperties.get(player).setHasLuckyPass(message.hasLuckyPass);
      PlayerLuckyPassProperties.get(player).setWins(message.wins);
      PlayerLuckyPassProperties.get(player).setDate(message.date);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\luckypass\PlayerLuckyPassPropertiesSync.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */