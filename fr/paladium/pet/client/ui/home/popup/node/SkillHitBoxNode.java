package fr.paladium.pet.client.ui.home.popup.node;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.pet.client.ui.home.popup.UISkillRollPopup;
import fr.paladium.pet.client.ui.utils.data.SkillRollSlotData;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.network.packet.pet.BBOpenSkillRollUIPacket;
import fr.paladium.pet.common.network.packet.skill.CSAssignRollPacket;
import net.minecraft.client.Minecraft;

public class SkillHitBoxNode extends AClickableNode {
  private final SkillRollSlotData data;
  
  private final double iconPosX;
  
  private final double iconPosY;
  
  public SkillHitBoxNode(UISkillRollPopup parentUI, SkillRollSlotData data, double x, double y, double width, double height, double iconPosX, double iconPosY) {
    super(x, y, width, height);
    this.data = data;
    this.iconPosX = iconPosX;
    this.iconPosY = iconPosY;
    setCallback(callback -> {
          if (parentUI.getSelectedSkill() == null) {
            PetCommonProxy.getInstance().getNetwork().sendToServer((IMessage)new CSAssignRollPacket(this.data.getSlot(), "remove"));
            PetCommonProxy.getInstance().getNetwork().sendToServer((IMessage)new BBOpenSkillRollUIPacket());
            return;
          } 
        });
  }
  
  public boolean onAssign(UISkillRollPopup parentUI, int mouseX, int mouseY) {
    if (!isHovered(mouseX, mouseY) || parentUI.getSelectedSkill() == null)
      return false; 
    PetCommonProxy.getInstance().getNetwork().sendToServer((IMessage)new CSAssignRollPacket(this.data.getSlot(), parentUI.getSelectedSkill().getData().getId()));
    PetCommonProxy.getInstance().getNetwork().sendToServer((IMessage)new BBOpenSkillRollUIPacket());
    return true;
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    double iconSize = GuiUtils.width(2.0F);
    GuiUtils.drawImageTransparent(this.iconPosX, this.iconPosY, this.data.getLogo(), iconSize, iconSize, false);
  }
  
  public boolean isHovered() {
    return super.isHovered();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\home\popup\node\SkillHitBoxNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */