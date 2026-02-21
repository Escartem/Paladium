package fr.paladium.palatrade.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palatrade.PalaTrade;
import fr.paladium.palatrade.client.ClientProxy;
import fr.paladium.palatrade.client.gui.UITrade;
import fr.paladium.palatrade.lib.odin.modules.packet.lib.ForgePacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;

public class SCOpenTradePacket extends ForgePacket {
  private String player;
  
  public SCOpenTradePacket() {}
  
  public SCOpenTradePacket(String player) {
    this.player = player;
  }
  
  public void write(ByteBuf buf) {
    writeString(buf, this.player);
  }
  
  public void read(ByteBuf buf) {
    this.player = readString(buf);
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {}
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    ((ClientProxy)PalaTrade.proxy).setTarget(this.player);
    if ((Minecraft.func_71410_x()).field_71462_r instanceof UITrade) {
      ((UITrade)(Minecraft.func_71410_x()).field_71462_r).setTarget(this.player);
      (Minecraft.func_71410_x()).field_71462_r.func_73866_w_();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\common\network\SCOpenTradePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */