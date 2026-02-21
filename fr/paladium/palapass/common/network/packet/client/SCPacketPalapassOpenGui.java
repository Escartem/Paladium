package fr.paladium.palapass.common.network.packet.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palapass.client.ui.UIPalapass;
import fr.paladium.palapass.common.pojo.quest.Quest;
import fr.paladium.palapass.common.pojo.reward.RewardLevel;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.List;

public class SCPacketPalapassOpenGui extends ForgePacket {
  @PacketData
  private List<RewardLevel> rewardList;
  
  @PacketData
  private List<Quest> dailyQuestList;
  
  @PacketData
  private List<Quest> seasonQuestList;
  
  public SCPacketPalapassOpenGui() {}
  
  public SCPacketPalapassOpenGui(List<RewardLevel> rewardList, List<Quest> dailyQuestList, List<Quest> seasonQuestList) {
    this.rewardList = rewardList;
    this.dailyQuestList = dailyQuestList;
    this.seasonQuestList = seasonQuestList;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    ZUI.open((UI)(new UIPalapass()).data(this.rewardList, this.dailyQuestList, this.seasonQuestList));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\network\packet\client\SCPacketPalapassOpenGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */