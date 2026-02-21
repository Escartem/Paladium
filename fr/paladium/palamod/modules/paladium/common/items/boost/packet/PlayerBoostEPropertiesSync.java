package fr.paladium.palamod.modules.paladium.common.items.boost.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.paladium.common.items.boost.PlayerBoostEProperties;
import fr.paladium.palamod.modules.paladium.common.items.boost.hud.ModuleBoost;
import io.netty.buffer.ByteBuf;

public class PlayerBoostEPropertiesSync implements IMessage {
  private long boostXP;
  
  private long boostMinerFou;
  
  public PlayerBoostEPropertiesSync() {}
  
  public PlayerBoostEPropertiesSync(PlayerBoostEProperties prop) {
    this.boostXP = prop.getBoostDoubleXp();
    this.boostMinerFou = prop.getBoostMinerFou();
  }
  
  public void fromBytes(ByteBuf buf) {
    this.boostXP = buf.readLong();
    this.boostMinerFou = buf.readLong();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeLong(this.boostXP);
    buf.writeLong(this.boostMinerFou);
  }
  
  public static class Handler implements IMessageHandler<PlayerBoostEPropertiesSync, IMessage> {
    public IMessage onMessage(PlayerBoostEPropertiesSync message, MessageContext ctx) {
      ModuleBoost.getInstance().setBoostDoubleXp(message.boostXP);
      ModuleBoost.getInstance().setBoostMinerFou(message.boostMinerFou);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\boost\packet\PlayerBoostEPropertiesSync.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */