package fr.paladium.pet.client.ui.home.popup.node;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.pet.client.ui.home.popup.UISkillRollPopup;
import fr.paladium.pet.client.ui.utils.data.SlotClientData;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class SkillSlotNode extends AClickableNode {
  private static final ResourceLocation BACKGROUND = new ResourceLocation("palapet", "textures/ui/home/slot.png");
  
  private final SlotClientData data;
  
  private final ResourceLocation logo;
  
  private final UISkillRollPopup parentPopup;
  
  private final List<SkillHitBoxNode> hitBoxes;
  
  public SlotClientData getData() {
    return this.data;
  }
  
  public ResourceLocation getLogo() {
    return this.logo;
  }
  
  public UISkillRollPopup getParentPopup() {
    return this.parentPopup;
  }
  
  public List<SkillHitBoxNode> getHitBoxes() {
    return this.hitBoxes;
  }
  
  public SkillSlotNode(double x, double y, double size, SlotClientData data, UISkillRollPopup parentPopup, List<SkillHitBoxNode> hitBoxes) {
    super(x, y, size, size);
    this.data = data;
    this.logo = data.getLogo();
    this.parentPopup = parentPopup;
    this.hitBoxes = hitBoxes;
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    double size = isHitBoxHovered() ? (this.width * 1.2D) : this.width;
    boolean selected = isSelected();
    double x = selected ? (mouseX - size / 2.0D) : this.x;
    double y = selected ? (mouseY - size / 2.0D) : this.y;
    GuiUtils.drawImageTransparent(x, y, BACKGROUND, size, size);
    double logoX = size * 0.2D;
    double logoY = size * 0.2D;
    double logoSize = size * 0.6D;
    GuiUtils.drawImageTransparent(x + logoX, y + logoY, this.logo, logoSize, logoSize, false);
  }
  
  private boolean isSelected() {
    if (this.parentPopup == null || this.parentPopup.getSelectedSkill() == null)
      return false; 
    return this.parentPopup.getSelectedSkill().equals(this);
  }
  
  private boolean isHitBoxHovered() {
    if (!isSelected())
      return false; 
    for (SkillHitBoxNode hitbox : this.hitBoxes) {
      if (hitbox.isHovered())
        return true; 
    } 
    return false;
  }
  
  public void onRelease(int mouseX, int mouseY, int mouseButton) {
    super.onRelease(mouseX, mouseY, mouseButton);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\home\popup\node\SkillSlotNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */