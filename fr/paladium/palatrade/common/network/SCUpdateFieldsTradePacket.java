package fr.paladium.palatrade.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palatrade.client.gui.UITrade;
import fr.paladium.palatrade.lib.odin.modules.packet.lib.ForgePacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayerMP;

public class SCUpdateFieldsTradePacket extends ForgePacket {
  private boolean teleport;
  
  private double xp;
  
  private double money;
  
  private double pb;
  
  private boolean self;
  
  public SCUpdateFieldsTradePacket() {}
  
  public SCUpdateFieldsTradePacket(boolean teleport, double xp, double money, double pb) {
    this.teleport = teleport;
    this.xp = xp;
    this.money = money;
    this.pb = pb;
    this.self = false;
  }
  
  public SCUpdateFieldsTradePacket(boolean teleport, double xp, double money, double pb, boolean self) {
    this.teleport = teleport;
    this.xp = xp;
    this.money = money;
    this.pb = pb;
    this.self = self;
  }
  
  public void write(ByteBuf buf) {
    writeBoolean(buf, this.teleport);
    writeDouble(buf, this.xp);
    writeDouble(buf, this.money);
    writeDouble(buf, this.pb);
    writeBoolean(buf, this.self);
  }
  
  public void read(ByteBuf buf) {
    this.teleport = readBoolean(buf);
    this.xp = readDouble(buf);
    this.money = readDouble(buf);
    this.pb = readDouble(buf);
    this.self = readBoolean(buf);
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {}
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    GuiScreen screen = (Minecraft.func_71410_x()).field_71462_r;
    if (!(screen instanceof UITrade))
      return; 
    if (this.self) {
      ((UITrade)screen).updateOwn(this.teleport, this.xp, this.money, this.pb);
    } else {
      ((UITrade)screen).updateTarget(this.teleport, this.xp, this.money, this.pb);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\common\network\SCUpdateFieldsTradePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */