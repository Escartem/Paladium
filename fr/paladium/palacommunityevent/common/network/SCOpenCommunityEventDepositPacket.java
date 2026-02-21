package fr.paladium.palacommunityevent.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palacommunityevent.client.gui.container.UICommunityEvent;
import fr.paladium.palacommunityevent.client.gui.container.UICommunityEventContainer;
import fr.paladium.palacommunityevent.common.pojo.CommunityEvent;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayerMP;

public class SCOpenCommunityEventDepositPacket extends ForgePacket {
  private CommunityEvent communityEventData;
  
  private int playerProgress;
  
  private int globalProgress;
  
  public SCOpenCommunityEventDepositPacket() {}
  
  public SCOpenCommunityEventDepositPacket(CommunityEvent communityEventData, int playerProgress, int globalProgress) {
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
    UICommunityEvent uICommunityEvent = new UICommunityEvent(this.communityEventData, this.playerProgress, this.globalProgress);
    if ((Minecraft.func_71410_x()).field_71462_r instanceof UICommunityEventContainer) {
      UICommunityEventContainer uiContainer = (UICommunityEventContainer)(Minecraft.func_71410_x()).field_71462_r;
      uiContainer.prev = (GuiScreen)uICommunityEvent;
      uiContainer.setCommunityEventData(this.communityEventData);
      (Minecraft.func_71410_x()).field_71462_r.func_73866_w_();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\common\network\SCOpenCommunityEventDepositPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */