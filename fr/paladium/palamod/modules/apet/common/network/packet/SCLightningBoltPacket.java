package fr.paladium.palamod.modules.apet.common.network.packet;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SCLightningBoltPacket implements IMessage {
  private double x;
  
  private double y;
  
  private double z;
  
  public SCLightningBoltPacket() {}
  
  public SCLightningBoltPacket(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readDouble();
    this.y = buf.readDouble();
    this.z = buf.readDouble();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeDouble(this.x);
    buf.writeDouble(this.y);
    buf.writeDouble(this.z);
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<SCLightningBoltPacket, IMessage> {
    @SideOnly(Side.CLIENT)
    private void spawnLightningBolt() {
      EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
      if (entityClientPlayerMP == null || ((EntityPlayer)entityClientPlayerMP).field_70170_p == null)
        return; 
      World world = ((EntityPlayer)entityClientPlayerMP).field_70170_p;
      world.func_72942_c((Entity)new EntityLightningBolt(world, ((EntityPlayer)entityClientPlayerMP).field_70165_t, ((EntityPlayer)entityClientPlayerMP).field_70163_u, ((EntityPlayer)entityClientPlayerMP).field_70161_v));
    }
    
    public IMessage onMessage(SCLightningBoltPacket message, MessageContext ctx) {
      if (ctx.side.isServer() || FMLCommonHandler.instance().getEffectiveSide().isServer())
        return null; 
      spawnLightningBolt();
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\apet\common\network\packet\SCLightningBoltPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */