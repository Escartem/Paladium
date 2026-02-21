package fr.paladium.pet.common.network.packet.capture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.pet.client.ui.capture.UICapture;
import fr.paladium.pet.common.capture.CaptureManager;
import fr.paladium.pet.common.tile.cage.CageStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayerMP;

public class SCPacketTrapOpen extends ForgePacket {
  @PacketData
  CageStatus status;
  
  @PacketData
  int x;
  
  @PacketData
  int y;
  
  @PacketData
  int z;
  
  public SCPacketTrapOpen() {}
  
  public SCPacketTrapOpen(CageStatus status, int x, int y, int z) {
    this.status = status;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    Minecraft.func_71410_x().func_147108_a((GuiScreen)new UICapture(
          CaptureManager.getInstance().getCategory(), this.status, this.x, this.y, this.z));
  }
  
  public void processServer(EntityPlayerMP player) {
    super.processServer(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\capture\SCPacketTrapOpen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */