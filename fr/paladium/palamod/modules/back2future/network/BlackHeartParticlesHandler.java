package fr.paladium.palamod.modules.back2future.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.particle.BlackHeartFX;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class BlackHeartParticlesHandler implements IMessageHandler<BlackHeartParticlesMessage, IMessage> {
  public IMessage onMessage(BlackHeartParticlesMessage message, MessageContext ctx) {
    handleMessage(message);
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  private void handleMessage(BlackHeartParticlesMessage message) {
    WorldClient worldClient = (Minecraft.func_71410_x()).field_71441_e;
    double x = message.x;
    double y = message.y;
    double z = message.z;
    (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new BlackHeartFX((World)worldClient, x, y, z));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\network\BlackHeartParticlesHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */