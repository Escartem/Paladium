package fr.paladium.palatrade.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palatrade.client.gui.UITrade;
import fr.paladium.palatrade.lib.odin.modules.packet.OdinPacketModule;
import fr.paladium.palatrade.lib.odin.modules.packet.lib.ForgePacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayerMP;

public class SCUnvalidateTradePacket extends ForgePacket {
  private String reason;
  
  private int tick;
  
  private boolean self;
  
  public SCUnvalidateTradePacket() {}
  
  public SCUnvalidateTradePacket(String reason, int tick, boolean self) {
    this.reason = reason;
    this.tick = tick;
    this.self = self;
  }
  
  public void write(ByteBuf buf) {
    writeString(buf, this.reason);
    writeInt(buf, this.tick);
    writeBoolean(buf, this.self);
  }
  
  public void read(ByteBuf buf) {
    this.reason = readString(buf);
    this.tick = readInt(buf);
    this.self = readBoolean(buf);
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {}
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    GuiScreen screen = (Minecraft.func_71410_x()).field_71462_r;
    if (!(screen instanceof UITrade))
      return; 
    OdinPacketModule.getInstance().getNetwork().sendToServer((IMessage)new CSValidateTradePacket(false));
    ((UITrade)screen).setValidate(false);
    if (this.self) {
      ((UITrade)screen).setError(true, this.reason, this.tick);
    } else {
      ((UITrade)screen).setTargetError(true, this.reason, this.tick);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\common\network\SCUnvalidateTradePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */