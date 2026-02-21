package fr.paladium.palatrade.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palatrade.client.gui.UITrade;
import fr.paladium.palatrade.lib.odin.modules.packet.lib.ForgePacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayerMP;

public class SCUnvalidateTargetTradePacket extends ForgePacket {
  public void write(ByteBuf buf) {}
  
  public void read(ByteBuf buf) {}
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {}
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    GuiScreen screen = (Minecraft.func_71410_x()).field_71462_r;
    if (!(screen instanceof UITrade))
      return; 
    ((UITrade)screen).setTargetValidate(false);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\common\network\SCUnvalidateTargetTradePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */