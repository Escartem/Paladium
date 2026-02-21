package fr.paladium.palacommunityevent.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palacommunityevent.client.gui.container.UICommunityEvent;
import fr.paladium.palacommunityevent.common.pojo.CommunityEvent;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayerMP;

public class SCOpenCommunityEventHomePacket extends ForgePacket {
  private CommunityEvent communityEventData;
  
  private int playerProgress;
  
  private int globalProgress;
  
  public SCOpenCommunityEventHomePacket() {}
  
  public SCOpenCommunityEventHomePacket(CommunityEvent communityEventData, int playerProgress, int globalProgress) {
    this.communityEventData = communityEventData;
    this.playerProgress = playerProgress;
    this.globalProgress = globalProgress;
  }
  
  public void write(ByteBuf buf) {
    writeNbt(buf, this.communityEventData.writeToNBT());
    writeInt(buf, this.playerProgress);
    writeInt(buf, this.globalProgress);
  }
  
  public void read(ByteBuf buf) {
    this.communityEventData = new CommunityEvent();
    this.communityEventData = this.communityEventData.readFromNBT(readNbt(buf));
    this.playerProgress = readInt(buf);
    this.globalProgress = readInt(buf);
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {}
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    Minecraft.func_71410_x().func_147108_a((GuiScreen)new UICommunityEvent());
    if ((Minecraft.func_71410_x()).field_71462_r instanceof UICommunityEvent) {
      UICommunityEvent ui = (UICommunityEvent)(Minecraft.func_71410_x()).field_71462_r;
      ui.globalProgress = this.globalProgress;
      ui.playerProgress = this.playerProgress;
      ui.setCommunityEventData(this.communityEventData);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\common\network\SCOpenCommunityEventHomePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */