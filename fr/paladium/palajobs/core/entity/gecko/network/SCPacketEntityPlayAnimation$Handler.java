package fr.paladium.palajobs.core.entity.gecko.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.core.entity.gecko.IAnimatedEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class Handler implements IMessageHandler<SCPacketEntityPlayAnimation, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketEntityPlayAnimation message, MessageContext ctx) {
    Entity entity = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_73045_a(SCPacketEntityPlayAnimation.access$000(message));
    if (entity instanceof IAnimatedEntity)
      ((IAnimatedEntity)entity).playAnimation(SCPacketEntityPlayAnimation.access$100(message), SCPacketEntityPlayAnimation.access$200(message), false); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\gecko\network\SCPacketEntityPlayAnimation$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */