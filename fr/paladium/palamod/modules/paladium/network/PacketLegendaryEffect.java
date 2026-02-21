package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.utils.LengendaryStoneFx;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class PacketLegendaryEffect implements IMessage {
  private float red;
  
  private float green;
  
  private float blue;
  
  private LegendaryStone.Effect effect;
  
  public PacketLegendaryEffect() {}
  
  public PacketLegendaryEffect(float red, float green, float blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.effect = null;
  }
  
  public PacketLegendaryEffect(float red, float green, float blue, LegendaryStone.Effect effect) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.effect = effect;
  }
  
  public void toBytes(ByteBuf buffer) {
    buffer.writeFloat(this.red);
    buffer.writeFloat(this.green);
    buffer.writeFloat(this.blue);
    if (this.effect != null)
      buffer.writeInt(this.effect.ordinal()); 
  }
  
  public void fromBytes(ByteBuf buffer) {
    this.red = buffer.readFloat();
    this.green = buffer.readFloat();
    this.blue = buffer.readFloat();
    if (buffer.isReadable())
      this.effect = LegendaryStone.Effect.values()[buffer.readInt()]; 
  }
  
  public static class Handler implements IMessageHandler<PacketLegendaryEffect, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(PacketLegendaryEffect message, MessageContext ctx) {
      if (Side.CLIENT.equals(ctx.side)) {
        LengendaryStoneFx.execute(message.red, message.green, message.blue);
        if (message.effect != null)
          if (message.effect == LegendaryStone.Effect.POWER) {
            (Minecraft.func_71410_x()).field_71439_g.getEntityData().func_74772_a("legendary_power", TimeUtil.now());
          } else if (message.effect == LegendaryStone.Effect.CHAOS) {
            (Minecraft.func_71410_x()).field_71439_g.getEntityData().func_74772_a("legendary_chaos", System.currentTimeMillis());
          }  
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\PacketLegendaryEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */