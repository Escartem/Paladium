package fr.paladium.palapass.client.ui.node.home;

import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.progress.ProgressNode;
import fr.paladium.palapass.client.ui.UIPalapass;
import fr.paladium.palapass.client.ui.popup.premium.UIPalapassPremiumPopup;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.network.packet.server.CSPacketPalapassClaimReward;
import fr.paladium.palapass.common.pojo.reward.EnumRewardType;
import fr.paladium.palapass.common.pojo.reward.RewardLevel;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import java.util.List;
import lombok.NonNull;

public class PalapassRewardContainerNode extends Node {
  private final double rewardWidth = 181.0D;
  
  private final double progressWidth = 80.0D;
  
  private final double progressHeight = 29.0D;
  
  private final double progressSpace = 153.0D;
  
  protected PalapassRewardContainerNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    overflow(OverflowProperty.SCROLL);
    scrollSpeed(3.0D);
  }
  
  public static PalapassRewardContainerNode create(double x, double y, double width, double height) {
    return new PalapassRewardContainerNode(x, y, width, height);
  }
  
  public void init(UI ui) {
    clearChildren();
    List<RewardLevel> rewardList = ((UIPalapass)getUi()).getRewardList();
    PalapassPlayer playerData = ((UIPalapass)getUi()).getPlayerData();
    Signal<RewardLevel> selectedReward = ((UIPalapass)getUi()).getSelectedReward();
    getClass();
    ContainerNode.create(0.0D, 0.0D, (181.0D + 80.0D - 28.0D) * rewardList.size() + 14.0D, getHeight())
      .body(container -> {
          getClass();
          getClass();
          FlexNode.horizontal(0.0D, container.dh(2.0D) - 29.0D / 2.0D, 29.0D).margin(153.0D).body(()).attach(container);
          getClass();
          FlexNode.horizontal(80.0D - 14.0D, container.ah(-257.0D), 257.0D).margin(80.0D - 28.0D).body(()).attach(container);
        }).attach(this);
  }
  
  public void update() {
    IntegerSignal page = ((UIPalapass)getUi()).getPage();
    setScrollX(-getDefaultWidth() * ((Integer)page.getOrDefault()).intValue());
  }
  
  public void mouseScroll(double mouseX, double mouseY, int value, @NonNull InternalContext context) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (!isHovered(mouseX, mouseY) || value == 0) {
      super.mouseScroll(mouseX, mouseY, value, context);
      return;
    } 
    IntegerSignal page = ((UIPalapass)getUi()).getPage();
    Signal<RewardLevel> reward = ((UIPalapass)getUi()).getSelectedReward();
    int rewardIndex = ((UIPalapass)getUi()).getRewardList().indexOf(reward.getOrDefault());
    if (value > 0) {
      if (rewardIndex > 0) {
        rewardIndex--;
        reward.set(((UIPalapass)getUi()).getRewardList().get(rewardIndex));
        if (rewardIndex < ((Integer)page.getOrDefault()).intValue() * 6)
          page.set(Integer.valueOf(((Integer)page.getOrDefault()).intValue() - 1)); 
      } 
    } else if (rewardIndex < ((UIPalapass)getUi()).getRewardList().size() - 1) {
      rewardIndex++;
      reward.set(((UIPalapass)getUi()).getRewardList().get(rewardIndex));
      if (rewardIndex > (((Integer)page.getOrDefault()).intValue() + 1) * 6 - 1)
        page.set(Integer.valueOf(((Integer)page.getOrDefault()).intValue() + 1)); 
    } 
    context.cancel();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\clien\\ui\node\home\PalapassRewardContainerNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */