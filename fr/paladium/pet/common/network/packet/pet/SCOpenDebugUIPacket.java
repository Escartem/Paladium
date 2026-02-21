package fr.paladium.pet.common.network.packet.pet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSide;
import fr.paladium.pet.client.ui.debug.UIDebugAssignment;
import fr.paladium.pet.client.ui.utils.data.AssignmentClientData;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayerMP;

public class SCOpenDebugUIPacket extends ForgePacket {
  @PacketData(PacketSide.SERVER)
  private List<AssignmentClientData> assignments;
  
  public SCOpenDebugUIPacket(List<AssignmentClientData> assignments) {
    this.assignments = assignments;
  }
  
  public SCOpenDebugUIPacket() {}
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {}
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    Minecraft mc = Minecraft.func_71410_x();
    mc.func_147108_a((GuiScreen)new UIDebugAssignment(this.assignments));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\pet\SCOpenDebugUIPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */