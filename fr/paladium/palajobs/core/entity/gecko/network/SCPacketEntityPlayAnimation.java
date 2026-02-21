package fr.paladium.palajobs.core.entity.gecko.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.core.entity.gecko.IAnimatedEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class SCPacketEntityPlayAnimation implements IMessage {
  private int entityId;
  
  private String animationName;
  
  private long animationDuration;
  
  public SCPacketEntityPlayAnimation() {}
  
  public SCPacketEntityPlayAnimation(int entityId, String animationName, long animationDuration) {
    this.entityId = entityId;
    this.animationName = animationName;
    this.animationDuration = animationDuration;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.entityId = buf.readInt();
    this.animationName = ByteBufUtils.readUTF8String(buf);
    this.animationDuration = buf.readLong();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.entityId);
    ByteBufUtils.writeUTF8String(buf, this.animationName);
    buf.writeLong(this.animationDuration);
  }
  
  public static class Handler implements IMessageHandler<SCPacketEntityPlayAnimation, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketEntityPlayAnimation message, MessageContext ctx) {
      Entity entity = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_73045_a(message.entityId);
      if (entity instanceof IAnimatedEntity)
        ((IAnimatedEntity)entity).playAnimation(message.animationName, message.animationDuration, false); 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\gecko\network\SCPacketEntityPlayAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */