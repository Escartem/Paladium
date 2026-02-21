package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.PalaMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class OpenGuiPacket implements IMessage {
  private int number;
  
  private boolean side;
  
  private boolean asCoord = false;
  
  private int x = 0;
  
  private int y = 0;
  
  private int z = 0;
  
  private int firstEmptySlot = -1;
  
  public OpenGuiPacket(int number, boolean sideclient) {
    this.number = number;
    this.side = sideclient;
  }
  
  public OpenGuiPacket(int number, boolean sideclient, int firstEmptySlot) {
    this.number = number;
    this.side = sideclient;
    this.firstEmptySlot = firstEmptySlot;
  }
  
  public OpenGuiPacket(int number, boolean sideclient, int x, int y, int z) {
    this.number = number;
    this.side = sideclient;
    this.asCoord = true;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.number = buf.readInt();
    this.side = buf.readBoolean();
    this.asCoord = buf.readBoolean();
    if (this.asCoord) {
      this.x = buf.readInt();
      this.y = buf.readInt();
      this.z = buf.readInt();
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.number);
    buf.writeBoolean(this.side);
    buf.writeBoolean(this.asCoord);
    if (this.asCoord) {
      buf.writeInt(this.x);
      buf.writeInt(this.y);
      buf.writeInt(this.z);
    } 
  }
  
  public OpenGuiPacket() {}
  
  public static class Handler implements IMessageHandler<OpenGuiPacket, IMessage> {
    public IMessage onMessage(OpenGuiPacket message, MessageContext ctx) {
      if (message.side) {
        EntityPlayer player = PalaMod.proxy.getPlayerEntity(ctx);
        if (message.asCoord) {
          player.openGui(PalaMod.instance, message.number, player.field_70170_p, message.x, message.y, message
              .z);
        } else {
          player.openGui(PalaMod.instance, message.number, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
        } 
      } else {
        EntityPlayer player = PalaMod.proxy.getServerPlayerEntity(ctx);
        if (message.asCoord) {
          player.openGui(PalaMod.instance, message.number, player.field_70170_p, message.x, message.y, message
              .z);
        } else {
          player.openGui(PalaMod.instance, message.number, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
        } 
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\OpenGuiPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */