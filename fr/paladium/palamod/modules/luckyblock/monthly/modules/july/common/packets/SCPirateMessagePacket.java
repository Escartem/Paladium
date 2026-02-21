package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.uis.ComputerUI;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class SCPirateMessagePacket implements IMessage {
  public static final int DRY_ID = 0;
  
  public static final int PIRATE_KING_ID = 1;
  
  private int id;
  
  public SCPirateMessagePacket() {}
  
  public SCPirateMessagePacket(int id) {
    this.id = id;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.id = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.id);
  }
  
  public static class Handler implements IMessageHandler<SCPirateMessagePacket, IMessage> {
    public IMessage onMessage(SCPirateMessagePacket message, MessageContext ctx) {
      DoubleLocation location = new DoubleLocation(0.0D, 0.0D, 0.0D);
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new ComputerUI(location));
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\packets\SCPirateMessagePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */