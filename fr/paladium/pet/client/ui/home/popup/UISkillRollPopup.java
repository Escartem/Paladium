package fr.paladium.pet.client.ui.home.popup;

import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.label.MinecraftSubTitleNodeLabel;
import fr.paladium.lib.apollon.nodes.label.MinecraftTitleNodeLabel;
import fr.paladium.lib.apollon.nodes.label.TextNodeLabel;
import fr.paladium.lib.apollon.ui.UIPopup;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.pet.client.ui.home.UIPetHome;
import fr.paladium.pet.client.ui.home.popup.node.SkillHitBoxNode;
import fr.paladium.pet.client.ui.home.popup.node.SkillSlotNode;
import fr.paladium.pet.client.ui.utils.data.HomeData;
import fr.paladium.pet.client.ui.utils.data.SkillRollSlotData;
import fr.paladium.pet.client.ui.utils.data.SlotClientData;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class UISkillRollPopup extends UIPopup {
  public static final String UI_ID = "palapet:POPUP-ASSIGN-SKILL";
  
  public void setSelectedSkill(SkillSlotNode selectedSkill) {
    this.selectedSkill = selectedSkill;
  }
  
  public void setHitBoxes(List<SkillHitBoxNode> hitBoxes) {
    this.hitBoxes = hitBoxes;
  }
  
  private static final ResourceLocation BACKGROUND_ROLL = new ResourceLocation("palapet", "textures/ui/home/skill_roll_background.png");
  
  private final HomeData data;
  
  private SkillSlotNode selectedSkill;
  
  private List<SkillHitBoxNode> hitBoxes;
  
  public HomeData getData() {
    return this.data;
  }
  
  public SkillSlotNode getSelectedSkill() {
    return this.selectedSkill;
  }
  
  public List<SkillHitBoxNode> getHitBoxes() {
    return this.hitBoxes;
  }
  
  public UISkillRollPopup(UIPetHome ui) {
    super((GuiScreen)ui, "palapet:POPUP-ASSIGN-SKILL");
    this.data = ui.getData();
    this.selectedSkill = null;
    this.hitBoxes = new ArrayList<>();
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    double titleX = width(30.95F);
    double titleY = height(14.0F);
    MinecraftTitleNodeLabel titleNode = (new MinecraftTitleNodeLabel(titleX, titleY, PetTranslateEnum.GUI_SKILL_ROLL_TITLE.text())).setFontSize(180);
    MinecraftSubTitleNodeLabel subTitleNode = (new MinecraftSubTitleNodeLabel(titleX, titleY + height(9.0F), PetTranslateEnum.GUI_SKILL_ROLL_SUBTITLE.text())).setFontSize(25);
    MinecraftCloseNode closeNode = new MinecraftCloseNode(width(67.976F), height(13.0F));
    closeNode.setCallback(c -> closePopup());
    TextNodeLabel descriptionNode = new TextNodeLabel(width(31.406F), height(73.611F), PetTranslateEnum.GUI_SKILL_ROLL_DESCRIPTION.text(), Fonts.MONTSERRAT_BOLD.getFont(), 2, Color.WHITE);
    addNode((ANode)titleNode);
    addNode((ANode)subTitleNode);
    addNode((ANode)closeNode);
    addNode((ANode)descriptionNode);
    this.hitBoxes = new ArrayList<>();
    this.hitBoxes.add(new SkillHitBoxNode(this, this.data
          
          .findSkillRollData(0), 
          width(50.5F), height(60.9F), 
          width(9.3F), height(9.5F), 
          width(53.5F), height(64.0F)));
    this.hitBoxes.add(new SkillHitBoxNode(this, this.data
          
          .findSkillRollData(1), 
          width(40.4F), height(60.9F), 
          width(9.3F), height(9.5F), 
          width(44.5F), height(64.0F)));
    this.hitBoxes.add(new SkillHitBoxNode(this, this.data
          
          .findSkillRollData(2), 
          width(38.5F), height(41.5F), 
          width(4.5F), height(16.5F), 
          width(39.3F), height(48.2F)));
    this.hitBoxes.add(new SkillHitBoxNode(this, this.data
          
          .findSkillRollData(3), 
          width(40.5F), height(29.5F), 
          width(9.3F), height(9.5F), 
          width(44.5F), height(32.5F)));
    this.hitBoxes.add(new SkillHitBoxNode(this, this.data
          
          .findSkillRollData(4), 
          width(50.5F), height(29.5F), 
          width(9.3F), height(9.5F), 
          width(54.0F), height(32.5F)));
    this.hitBoxes.add(new SkillHitBoxNode(this, this.data
          
          .findSkillRollData(5), 
          width(57.0F), height(41.5F), 
          width(4.5F), height(16.5F), 
          width(58.5F), height(48.2F)));
    this.hitBoxes.forEach(this::addNode);
    int index = 0;
    double slotStartX = width(31.406F);
    double slotStartY = height(76.315F);
    double slotSize = width(4.948F);
    double space = width(6.25F);
    for (SlotClientData slot : this.data.getSlots()) {
      if (!slot.getColorInfo().isActive())
        continue; 
      boolean found = false;
      for (SkillRollSlotData skillRollSlot : this.data.getSkillRollSlots()) {
        if (skillRollSlot.getSkillId().equals(slot.getId())) {
          found = true;
          break;
        } 
      } 
      if (found)
        continue; 
      SkillSlotNode skillSlotNode = new SkillSlotNode(slotStartX + space * index, slotStartY, slotSize, slot, this, this.hitBoxes);
      skillSlotNode.setCallback(c -> this.selectedSkill = skillSlotNode);
      addNode((ANode)skillSlotNode);
      index++;
    } 
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    func_146276_q_();
    BackgroundHelper.createMinecraft(
        width(43.021F), 
        height(76.481F));
    double backgroundWidth = width(22.708F);
    double backgroundHeight = backgroundWidth;
    double backgroundX = width(50.0F) - backgroundWidth / 2.0D;
    double backgroundY = height(50.0F) - backgroundHeight / 2.0D;
    GuiUtils.drawImageTransparent(backgroundX, backgroundY, BACKGROUND_ROLL, backgroundWidth, backgroundHeight);
  }
  
  protected void func_73864_a(int mouseX, int mouseY, int buttonID) {
    super.func_73864_a(mouseX, mouseY, buttonID);
  }
  
  protected void func_146286_b(int mouseX, int mouseY, int buttonID) {
    super.func_146286_b(mouseX, mouseY, buttonID);
    if (buttonID == 0 && this.selectedSkill != null) {
      for (SkillHitBoxNode hitBox : this.hitBoxes) {
        if (hitBox.onAssign(this, mouseX, mouseY))
          return; 
      } 
      this.selectedSkill = null;
    } 
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {}
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\home\popup\UISkillRollPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */