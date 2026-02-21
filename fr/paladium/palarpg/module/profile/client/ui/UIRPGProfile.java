package fr.paladium.palarpg.module.profile.client.ui;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.sw.SwitchNode;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundElementNode;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundNode;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.profile.client.ui.kit.icon.RPGProfileCostIconNode;
import fr.paladium.palarpg.module.profile.client.ui.kit.skilltree.container.RPGProfileSkillTreeMapContainer;
import fr.paladium.palarpg.module.profile.client.util.FakeEntityPlayerMP;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import fr.paladium.palarpg.module.profile.common.skilltree.manager.RPGSkillTreeManager;
import fr.paladium.palarpg.module.profile.common.skilltree.node.SkillTreeNode;
import fr.paladium.palarpg.module.stat.client.constant.RPGStatConstant;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.AStatCapability;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.progress.ProgressNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.sw.SwitchNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.MapSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

@UIData(active = true)
public class UIRPGProfile extends UI {
  private final StringSignal stateSignal = new StringSignal("global");
  
  public StringSignal getStateSignal() {
    return this.stateSignal;
  }
  
  private final IntegerSignal totalCostSignal = new IntegerSignal(0);
  
  public IntegerSignal getTotalCostSignal() {
    return this.totalCostSignal;
  }
  
  private final MapSignal<String, LinkedHashSet<SkillTreeNode>> toBuySignal = new MapSignal(new HashMap<>());
  
  private RPGProfilePlayerData profileData;
  
  private ContainerNode skillTreeContainer;
  
  public MapSignal<String, LinkedHashSet<SkillTreeNode>> getToBuySignal() {
    return this.toBuySignal;
  }
  
  public RPGProfilePlayerData getProfileData() {
    return this.profileData;
  }
  
  public ContainerNode getSkillTreeContainer() {
    return this.skillTreeContainer;
  }
  
  public void init() {
    this.profileData = (RPGProfilePlayerData)RPGPlayer.getData("profile");
    if (this.profileData == null) {
      ZUI.close(this);
      (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue.", "RPG")).send();
      return;
    } 
    BackgroundNode.create()
      .attach(this);
    TextNode.create(80.0D, 46.0D)
      .text(Text.create("profil rpg", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 59.0F, Color.WHITE).shadow(0.0F, 0.0F)).modifier(TextModifier.UPPER_CASE))
      .attach(this);
    TextNode.create(80.0D, 221.0D)
      .text(Text.create((Minecraft.func_71410_x()).field_71439_g.getDisplayName(), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 32.0F, Color.WHITE).shadow(0.0F, 0.0F)).modifier(TextModifier.UPPER_CASE).overflow(TextOverflow.ELLIPSIS))
      .mode(TextMode.OVERFLOW)
      .width(420.0D)
      .attach(this);
    TextNode.create(637.0D, 221.0D)
      .text(Text.create("lv. " + this.profileData.getLevel(), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 32.0F, Color.WHITE).shadow(0.0F, 0.0F)).modifier(TextModifier.UPPER_CASE))
      .anchorX(Align.END)
      .attach(this);
    ProgressNode.create(80.0D, 288.0D, 557.0D, 30.0D)
      .color(Color.decode("#1E1E1E"), Color.decode("#01D68F"))
      .progress((float)this.profileData.getProgressionPercentage())
      .attach(this);
    ((ContainerNode)ContainerNode.create(683.0D, 73.0D, 1164.0D, 934.0D)
      .effect(MaskNodeEffect::create)
      .body(container -> {
          this.skillTreeContainer = container;
          RPGProfileSkillTreeMapContainer.create(container.dw(2.0D) - 1900.0D, container.dh(2.0D) - 1900.0D, 3800.0D, 3800.0D).attach((Node)container);
          RectNode.create(0.0D, 0.0D, container.getWidth(), container.getHeight()).color(Color.BLACK.copyAlpha(0.1F)).attach((Node)container);
          BackgroundElementNode.create(667.0D, 12.0D, 476.0D, 52.0D).cornerSize(5.0D).shadowY(4.0F).zlevel(10.0D).body(()).attach((Node)container);
        })).attach(this);
    RectNode.create(80.0D, 368.0D, 558.0D, 639.0D)
      .color(new Color(23, 23, 23))
      .body(rect -> {
          SwitchNode.create(30.0D, 13.0D, 440.0D, 42.0D).state(Arrays.asList(new String[] { "perso", "global" }, ), (String)this.stateSignal.getOrDefault()).onChange(()).attach(rect);
          ContainerNode.create(25.0D, 72.0D, rect.getWidth() - 50.0D, rect.getHeight() - 72.0D - 25.0D).overflow(OverflowProperty.SCROLL).scrollbar((ScrollbarNode)ScrollbarNode.create(rect.getWidth() - 50.0D - 15.0D, 0.0D, rect.getHeight() - 72.0D - 25.0D, 15.0D)).body(()).attach(rect);
        }).attach(this);
  }
  
  public int getSkillPointsLeft() {
    return this.profileData.getSkillPoints() - ((Integer)this.totalCostSignal.getOrDefault()).intValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\clien\\ui\UIRPGProfile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */