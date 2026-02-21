package fr.paladium.palarpg.module.profile.client.ui.kit.skilltree.container;

import fr.paladium.palarpg.module.profile.client.ui.UIRPGProfile;
import fr.paladium.palarpg.module.profile.client.ui.kit.skilltree.node.RPGSkillNode;
import fr.paladium.palarpg.module.profile.client.ui.kit.skilltree.node.RPGSkillNodeState;
import fr.paladium.palarpg.module.profile.common.skilltree.RPGSkillTreePosition;
import fr.paladium.palarpg.module.profile.common.skilltree.SkillTree;
import fr.paladium.palarpg.module.profile.common.skilltree.node.NodePosition;
import fr.paladium.palarpg.module.profile.common.skilltree.node.SkillTreeNode;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import lombok.NonNull;

public class RPGSkillTreeContainer extends Node {
  private SkillTree skillTree;
  
  protected RPGSkillTreeContainer(double x, double y) {
    super(x, y);
  }
  
  public static RPGSkillTreeContainer create(double x, double y) {
    return new RPGSkillTreeContainer(x, y);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    if (this.skillTree == null)
      return; 
    RPGSkillTreePosition pos = this.skillTree.getPosition();
    double offsetX = (pos == RPGSkillTreePosition.RIGHT || pos == RPGSkillTreePosition.LEFT) ? (pos.getX() * 1.65D) : 0.0D;
    double offsetY = (pos == RPGSkillTreePosition.UP || pos == RPGSkillTreePosition.DOWN) ? (pos.getY() * 1.65D) : 0.0D;
    for (int i = 0; i < this.skillTree.getNodes().size(); i++) {
      SkillTreeNode skillNode = this.skillTree.getNodes().get(i);
      NodePosition nodePos = skillNode.getPosition();
      double nodePosX = 0.0D;
      double nodePosY = 0.0D;
      switch (pos) {
        case UP:
          nodePosX = nodePos.getX() * 81.0D + nodePos.getX() * 26.0D;
          nodePosY = nodePos.getY() * -81.0D + nodePos.getY() * -26.0D;
          break;
        case RIGHT:
          nodePosX = nodePos.getY() * 81.0D + nodePos.getY() * 26.0D;
          nodePosY = nodePos.getX() * 81.0D + nodePos.getX() * 26.0D;
          break;
        case DOWN:
          nodePosX = nodePos.getX() * 81.0D + nodePos.getX() * 26.0D;
          nodePosY = nodePos.getY() * 81.0D + nodePos.getY() * 26.0D;
          break;
        case LEFT:
          nodePosX = nodePos.getY() * -81.0D + nodePos.getY() * -26.0D;
          nodePosY = nodePos.getX() * -81.0D + nodePos.getX() * -26.0D;
          break;
      } 
      double skillNodeX = offsetX + nodePosX;
      double skillNodeY = offsetY + nodePosY;
      RPGSkillNode rpgSkillNode = (RPGSkillNode)((RPGSkillNode)RPGSkillNode.create(skillNodeX, skillNodeY).cornerSize(7.0D).outlineSize(3.0D).skillTreeName(this.skillTree.getId()).skillNode(skillNode).size(81.0D, 81.0D).anchor(Align.CENTER, Align.CENTER).zindex(1).onClick((node, mouseX, mouseY, clickType) -> {
            UIRPGProfile uiRPG = (UIRPGProfile)getUi();
            if (node.getSkillNodeState() == RPGSkillNodeState.UNLOCKABLE) {
              LinkedHashSet<SkillTreeNode> addingList = (LinkedHashSet<SkillTreeNode>)((Map)uiRPG.getToBuySignal().getOrDefault()).getOrDefault(this.skillTree.getId(), new LinkedHashSet());
              addingList.add(skillNode);
              uiRPG.getTotalCostSignal().add(skillNode.getCost());
              uiRPG.getToBuySignal().put(this.skillTree.getId(), addingList);
            } 
          })).attach(this);
      RPGSkillNodeState state = rpgSkillNode.refreshNodeState();
      Color rectColor = RPGSkillNode.LOCKED_OUTLINE_COLOR;
      if (state == RPGSkillNodeState.UNLOCKABLE) {
        rectColor = RPGSkillNode.UNLOCKABLE_OUTLINE_COLOR;
      } else if (state == RPGSkillNodeState.UNLOCKED) {
        rectColor = RPGSkillNode.UNLOCKED_OUTLINE_COLOR;
      } 
      if (skillNode.getRequiredLevels() != null && skillNode.getRequiredLevels().size() > 0) {
        List<SkillTreeNode> requiredNodes = this.skillTree.getNodesByName(skillNode.getRequiredLevels());
        if (requiredNodes.size() > 0)
          for (SkillTreeNode required : requiredNodes) {
            if (pos.isVertical()) {
              if (skillNode.getPosition().getY() != required.getPosition().getY()) {
                double d1 = offsetY + ((pos == RPGSkillTreePosition.UP) ? (required.getPosition().getY() * -81.0D + required.getPosition().getY() * -26.0D) : (required.getPosition().getY() * 81.0D + required.getPosition().getY() * 26.0D));
                double size = skillNodeY - d1;
                RectNode.create(skillNodeX, (size < 0.0D) ? skillNodeY : d1, 3.0D, Math.abs(size))
                  .color(rectColor)
                  .attach(this);
              } 
            } else if (pos.isHorizontal() && 
              skillNode.getPosition().getY() == required.getPosition().getY()) {
              double d1 = offsetY + ((pos == RPGSkillTreePosition.RIGHT) ? (required.getPosition().getX() * 81.0D + required.getPosition().getX() * 26.0D) : (required.getPosition().getX() * -81.0D + required.getPosition().getX() * -26.0D));
              double size = skillNodeY - d1;
              RectNode.create(skillNodeX, (size < 0.0D) ? skillNodeY : d1, 3.0D, Math.abs(size))
                .color(rectColor)
                .attach(this);
            } 
            double requiredSkillNodeX = offsetX;
            double requiredSkillNodeY = offsetY;
            double lineSize = 100.0D;
            if (pos.isVertical()) {
              if (skillNode.getPosition().getY() != required.getPosition().getY())
                continue; 
              int reqX = required.getPosition().getX();
              int reqY = required.getPosition().getY();
              if (reqX > nodePos.getX()) {
                requiredSkillNodeX += nodePosX;
                requiredSkillNodeY += nodePosY;
                lineSize = Math.abs(skillNodeX - offsetX + reqX * 81.0D + reqX * 26.0D);
              } else {
                requiredSkillNodeX += reqX * 81.0D + reqX * 26.0D;
                requiredSkillNodeY += (pos == RPGSkillTreePosition.DOWN) ? (reqY * 81.0D + reqY * 26.0D) : (reqY * -81.0D + reqY * -26.0D);
                lineSize = Math.abs(skillNodeX - requiredSkillNodeX);
              } 
              RectNode.create(requiredSkillNodeX, requiredSkillNodeY, lineSize, 3.0D)
                .color(rectColor)
                .attach(this);
              continue;
            } 
            if (!pos.isHorizontal() || 
              skillNode.getPosition().getX() != required.getPosition().getX())
              continue; 
            requiredSkillNodeX += (pos == RPGSkillTreePosition.RIGHT) ? (required.getPosition().getY() * 81.0D + required.getPosition().getY() * 26.0D) : nodePosX;
            requiredSkillNodeY += (pos == RPGSkillTreePosition.RIGHT) ? (required.getPosition().getX() * 81.0D + required.getPosition().getX() * 26.0D) : nodePosY;
            lineSize = (pos == RPGSkillTreePosition.RIGHT) ? Math.abs(skillNodeX - offsetX + required.getPosition().getY() * 81.0D + required.getPosition().getY() * 26.0D) : Math.abs(requiredSkillNodeX - offsetX + required.getPosition().getY() * -81.0D + required.getPosition().getY() * -26.0D);
            RectNode.create(requiredSkillNodeX, requiredSkillNodeY, lineSize, 3.0D)
              .color(rectColor)
              .attach(this);
          }  
      } 
    } 
  }
  
  public RPGSkillTreeContainer skilltree(@NonNull SkillTree skillTree) {
    if (skillTree == null)
      throw new NullPointerException("skillTree is marked non-null but is null"); 
    this.skillTree = skillTree;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\clien\\ui\kit\skilltree\container\RPGSkillTreeContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */