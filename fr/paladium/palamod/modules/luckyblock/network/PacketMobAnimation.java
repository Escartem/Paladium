package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.entity.halloween.IAnimation;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class PacketMobAnimation implements IMessage {
  public int entityId;
  
  public int animationType;
  
  public PacketMobAnimation() {}
  
  public PacketMobAnimation(int id, int type) {
    this.entityId = id;
    this.animationType = type;
  }
  
  public void fromBytes(ByteBuf buffer) {
    this.entityId = buffer.readInt();
    this.animationType = buffer.readInt();
  }
  
  public void toBytes(ByteBuf buffer) {
    buffer.writeInt(this.entityId);
    buffer.writeInt(this.animationType);
  }
  
  public static class Handler implements IMessageHandler<PacketMobAnimation, IMessage> {
    public IMessage onMessage(PacketMobAnimation message, MessageContext ctx) {
      List<?> entList = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.field_72996_f;
      for (Object ent : entList) {
        if (((Entity)ent).func_145782_y() == message.entityId && ent instanceof IAnimation) {
          ((IAnimation)ent).performAnimation(message.animationType);
          break;
        } 
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketMobAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */