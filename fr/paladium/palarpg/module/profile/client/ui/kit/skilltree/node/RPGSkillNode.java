package fr.paladium.palarpg.module.profile.client.ui.kit.skilltree.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundElementNode;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundNode;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.profile.client.ui.UIRPGProfile;
import fr.paladium.palarpg.module.profile.client.ui.kit.icon.RPGProfileCostIconNode;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import fr.paladium.palarpg.module.profile.common.skilltree.SkillTree;
import fr.paladium.palarpg.module.profile.common.skilltree.manager.RPGSkillTreeManager;
import fr.paladium.palarpg.module.profile.common.skilltree.node.SkillTreeNode;
import fr.paladium.palarpg.module.stat.client.constant.RPGStatConstant;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.AStatCapability;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.hover.HoverElement;
import fr.paladium.zephyrui.lib.ui.node.hover.impl.NodeHoverElement;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.util.LinkedHashSet;
import java.util.Map;
import lombok.NonNull;

public class RPGSkillNode extends Node {
  private static final TextInfo NODE_INFO = TextInfo.create((IFont)PaladiumFont.MINECRAFT, 20.0F, Color.WHITE);
  
  private static final Color LOCKED_BACKGROUND_COLOR = new Color(40, 40, 40);
  
  private static final Color UNLOCKABLE_BACKGROUND_COLOR = new Color(125, 8, 0);
  
  private static final Color UNLOCKED_BACKGROUND_COLOR = new Color(0, 89, 60);
  
  public static final Color LOCKED_OUTLINE_COLOR = new Color(14, 14, 14);
  
  public static final Color UNLOCKABLE_OUTLINE_COLOR = new Color(225, 52, 41);
  
  public static final Color UNLOCKED_OUTLINE_COLOR = new Color(0, 227, 151);
  
  private double cornerSize = 0.0D;
  
  public double getCornerSize() {
    return this.cornerSize;
  }
  
  private double outlineSize = 0.0D;
  
  public double getOutlineSize() {
    return this.outlineSize;
  }
  
  private String skillTreeName = "";
  
  public String getSkillTreeName() {
    return this.skillTreeName;
  }
  
  private SkillTreeNode skillNode = null;
  
  public SkillTreeNode getSkillNode() {
    return this.skillNode;
  }
  
  private RPGSkillNodeState skillNodeState = RPGSkillNodeState.LOCKED;
  
  public RPGSkillNodeState getSkillNodeState() {
    return this.skillNodeState;
  }
  
  protected RPGSkillNode(double x, double y) {
    super(x, y);
  }
  
  protected RPGSkillNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static RPGSkillNode create(double x, double y) {
    return new RPGSkillNode(x, y);
  }
  
  public static RPGSkillNode create(double x, double y, double width, double height) {
    return new RPGSkillNode(x, y, width, height);
  }
  
  public static RPGSkillNode create(double x, double y, double size) {
    return new RPGSkillNode(x, y, size, size);
  }
  
  public RPGSkillNode cornerSize(double cornerSize) {
    this.cornerSize = cornerSize;
    return this;
  }
  
  public RPGSkillNode outlineSize(double outlineSize) {
    this.outlineSize = outlineSize;
    return this;
  }
  
  public RPGSkillNode skillTreeName(@NonNull String skillTreeName) {
    if (skillTreeName == null)
      throw new NullPointerException("skillTreeName is marked non-null but is null"); 
    this.skillTreeName = skillTreeName;
    return this;
  }
  
  public RPGSkillNode skillNode(@NonNull SkillTreeNode skillNode) {
    if (skillNode == null)
      throw new NullPointerException("skillNode is marked non-null but is null"); 
    this.skillNode = skillNode;
    return this;
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    if (this.skillNode == null)
      return; 
    AStatCapability<Object> stat = ((RPGStatPlayerData)RPGPlayer.getData("stats")).getCapabilityByName(this.skillNode.getStat());
    if (stat == null)
      return; 
    refreshNodeState();
    BackgroundNode hoverNode = (BackgroundNode)BackgroundNode.create(30.0D, -15.0D, 370.0D, 120.0D).innerRadius(4.0D).outerRadius(12.0D).onInit(hover -> {
          TextNode.create(20.0D, 20.0D).text(Text.create(this.skillNode.getStat().getFormattedStatName(), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 16.0F, Color.WHITE).shadow(0.0F, 0.0F))).anchorY(Align.CENTER).attach((Node)hover);
          TextNode.create(250.0D, 20.0D).text(Text.create("cout", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 11.0F, Color.WHITE).shadow(0.0F, 0.0F))).anchorY(Align.CENTER).attach((Node)hover);
          BackgroundElementNode.create(298.0D, 8.0D, 57.0D, 24.0D).cornerSize(4.0D).onInit(()).attach((Node)hover);
          RectNode.create(16.0D, 40.0D, 338.0D, 2.0D).color(Color.BLACK).attach((Node)hover);
          TextNode.create(16.0D, 70.0D).text(Text.create("Augmente " + this.skillNode.getStat().getFormattedStatName() + " de " + stat.displayMutator(this.skillNode.getMutator()), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 12.0F, Color.WHITE).shadow(0.0F, 0.0F))).attach((Node)hover);
        });
    hover((HoverElement)NodeHoverElement.follow((Node)hoverNode));
    TextInfo textInfo = NODE_INFO.copy();
    Resource icon = RPGStatConstant.getIcon(this.skillNode.getStat());
    if (this.skillNodeState == RPGSkillNodeState.LOCKED) {
      textInfo.color(new Color(139, 139, 139));
      icon = RPGStatConstant.getGrayscaleIcon(this.skillNode.getStat());
    } 
    ImageNode.create(dw(2.0D) - 12.0D, dh(2.0D) - 12.0D - 12.0D)
      .resource(icon)
      .size(24.0D, 24.0D)
      .attach(this);
    String mutatorValue = stat.displayMutator(this.skillNode.getMutator()).replace("+", "");
    TextNode.create(dw(2.0D) - textInfo.getWidth(mutatorValue) / 2.0D, dh(2.0D) + 1.0D)
      .text(Text.create("+", textInfo))
      .anchorX(Align.END)
      .attach(this);
    TextNode.create(dw(2.0D), dh(2.0D) + 1.0D)
      .text(Text.create(mutatorValue, textInfo))
      .anchorX(Align.CENTER)
      .attach(this);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (getWidth() <= 0.0D || getHeight() <= 0.0D || this.cornerSize <= 0.0D)
      return; 
    if (this.outlineSize > 0.0D) {
      Color outlineColor = LOCKED_OUTLINE_COLOR;
      if (this.skillNodeState == RPGSkillNodeState.UNLOCKED) {
        outlineColor = UNLOCKED_OUTLINE_COLOR;
      } else if (this.skillNodeState == RPGSkillNodeState.UNLOCKABLE) {
        outlineColor = UNLOCKABLE_OUTLINE_COLOR;
      } 
      DrawUtils.SHAPE.drawRect(getX() + this.cornerSize - this.outlineSize, getY() - this.outlineSize, getWidth() - this.cornerSize * 2.0D + this.outlineSize * 2.0D, this.cornerSize + this.outlineSize, outlineColor);
      DrawUtils.SHAPE.drawRect(getX() - this.outlineSize, getY() + this.cornerSize - this.outlineSize, getWidth() + this.outlineSize * 2.0D, getHeight() - this.cornerSize * 2.0D + this.outlineSize * 2.0D, outlineColor);
      DrawUtils.SHAPE.drawRect(getX() + this.cornerSize - this.outlineSize, getY() + getHeight() - this.cornerSize, getWidth() - this.cornerSize * 2.0D + this.outlineSize * 2.0D, this.cornerSize + this.outlineSize, outlineColor);
    } 
    Color backgroundColor = LOCKED_BACKGROUND_COLOR;
    if (this.skillNodeState == RPGSkillNodeState.UNLOCKABLE) {
      backgroundColor = UNLOCKABLE_BACKGROUND_COLOR;
    } else if (this.skillNodeState == RPGSkillNodeState.UNLOCKED) {
      backgroundColor = UNLOCKED_BACKGROUND_COLOR;
    } 
    DrawUtils.SHAPE.drawRect(getX() + this.cornerSize, getY(), getWidth() - this.cornerSize * 2.0D, this.cornerSize, backgroundColor);
    DrawUtils.SHAPE.drawRect(getX(), getY() + this.cornerSize, getWidth(), getHeight() - this.cornerSize * 2.0D, backgroundColor);
    DrawUtils.SHAPE.drawRect(getX() + this.cornerSize, getY() + getHeight() - this.cornerSize, getWidth() - this.cornerSize * 2.0D, this.cornerSize, backgroundColor);
  }
  
  public boolean isHovered(double mouseX, double mouseY, boolean checkEnabled) {
    ContainerNode containerNode = ((UIRPGProfile)getUi()).getSkillTreeContainer();
    if (mouseX < containerNode.getAbsoluteX() || mouseX > containerNode.getAbsoluteX() + containerNode.getWidth() || mouseY < containerNode.getAbsoluteY() || mouseY > containerNode.getAbsoluteY() + containerNode.getHeight())
      return false; 
    return super.isHovered(mouseX, mouseY, checkEnabled);
  }
  
  public RPGSkillNodeState refreshNodeState() {
    RPGProfilePlayerData pData = ((UIRPGProfile)getUi()).getProfileData();
    if (pData.isUnlocked(this.skillTreeName, this.skillNode) || ((LinkedHashSet)((Map)((UIRPGProfile)getUi()).getToBuySignal().getOrDefault()).getOrDefault(this.skillTreeName, new LinkedHashSet())).contains(this.skillNode)) {
      this.skillNodeState = RPGSkillNodeState.UNLOCKED;
    } else if (((UIRPGProfile)getUi()).getSkillPointsLeft() >= this.skillNode.getCost() && (RPGSkillTreeManager.isPreviousNodeUnlocked(pData, this.skillTreeName, this.skillNode) || isClientPreviousUnlocked(this.skillTreeName, this.skillNode, (UIRPGProfile)getUi()))) {
      this.skillNodeState = RPGSkillNodeState.UNLOCKABLE;
    } else {
      this.skillNodeState = RPGSkillNodeState.LOCKED;
    } 
    return this.skillNodeState;
  }
  
  public static boolean isClientPreviousUnlocked(@NonNull String skillTreeName, @NonNull SkillTreeNode skillNode, @NonNull UIRPGProfile ui) {
    if (skillTreeName == null)
      throw new NullPointerException("skillTreeName is marked non-null but is null"); 
    if (skillNode == null)
      throw new NullPointerException("skillNode is marked non-null but is null"); 
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    boolean isAllUnlocked = true;
    if (skillNode.getRequiredLevels() == null)
      return isAllUnlocked; 
    for (String requiredNode : skillNode.getRequiredLevels())
      isAllUnlocked = (isAllUnlocked && ((LinkedHashSet)((Map)ui.getToBuySignal().getOrDefault()).getOrDefault(skillTreeName, new LinkedHashSet())).contains(((SkillTree)RPGSkillTreeManager.getSkillTrees().get(skillTreeName)).getNodeByName(requiredNode))); 
    return isAllUnlocked;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\clien\\ui\kit\skilltree\node\RPGSkillNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */