package fr.paladium.pet.client.ui.home;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.buttons.utils.MinecraftScrollableArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollableArea;
import fr.paladium.lib.apollon.nodes.flex.FlexNode;
import fr.paladium.lib.apollon.nodes.label.MinecraftTitleNodeLabel;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.ui.UIPopup;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.pet.client.ui.home.node.assignment.AssignmentGlobalNode;
import fr.paladium.pet.client.ui.home.node.edit.EditPetNode;
import fr.paladium.pet.client.ui.home.node.skill.SkillGlobalNode;
import fr.paladium.pet.client.ui.home.node.skill.child.SkillNode;
import fr.paladium.pet.client.ui.home.node.stat.HomeStatNode;
import fr.paladium.pet.client.ui.home.popup.UIAssignSkillPopup;
import fr.paladium.pet.client.ui.home.popup.UIChangeSkinPopup;
import fr.paladium.pet.client.ui.home.popup.UISkillRollPopup;
import fr.paladium.pet.client.ui.utils.data.HomeData;
import fr.paladium.pet.client.ui.utils.data.SlotClientData;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.packet.pet.BBUpdateClientConfigPacket;
import java.util.ArrayList;
import java.util.List;

public class UIPetHome extends UI {
  public static final Color BACKGROUND_COLOR = Color.decode("#1A1A1A");
  
  private final HomeData data;
  
  public HomeData getData() {
    return this.data;
  }
  
  public UIPetHome(HomeData data) {
    this.data = data;
    PetCommonProxy.getInstance().getNetwork().sendToServer((IMessage)new BBUpdateClientConfigPacket());
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    MinecraftTitleNodeLabel titleNode = new MinecraftTitleNodeLabel(width(11.146F), height(7.463F), PetTranslateEnum.GUI_HOME_TITLE.text());
    MinecraftCloseNode closeNode = new MinecraftCloseNode(width(88.0F), height(7.0F));
    EditPetNode editPetNode = new EditPetNode(this, width(11.146F), height(16.574F), width(16.574F), height(35.741F));
    double hapinessX = width(31.667F);
    double hapinessY = height(17.222F);
    double statWidth = width(27.865F);
    double statHeight = height(10.594F);
    HomeStatNode happinessNode = new HomeStatNode(this.data, hapinessX, hapinessY, statWidth, statHeight, HomeStatNode.StatType.HAPPINESS, this.data.getHappiness(), this.data.getMaxHappiness());
    double experienceX = hapinessX + statWidth + width(1.823F);
    double experienceY = hapinessY;
    HomeStatNode experienceNode = new HomeStatNode(this.data, experienceX, experienceY, statWidth, statHeight, HomeStatNode.StatType.EXPERIENCE, this.data.getExperience(), this.data.getMaxExperience());
    AssignmentGlobalNode assignmentNode = new AssignmentGlobalNode(this.data, width(31.719F), height(34.537F), width(57.5F), height(17.778F));
    double skillNodeX = width(11.146F);
    double skillNodeY = height(56.204F);
    double skillNodeWidth = width(76.51F);
    double skillNodeHeight = height(31.54F);
    double skillNodeBackgroundY = height(61.296F);
    SkillGlobalNode skillNode = new SkillGlobalNode(this, skillNodeX, skillNodeY, skillNodeWidth, skillNodeHeight, skillNodeBackgroundY);
    addNode((ANode)titleNode);
    addNode((ANode)closeNode);
    addNode((ANode)editPetNode);
    addNode((ANode)happinessNode);
    addNode((ANode)experienceNode);
    addNode((ANode)assignmentNode);
    addNode((ANode)skillNode);
    double scrollAreaX = skillNodeX + skillNodeWidth + width(0.5F);
    ScrollArea scrollArea = new ScrollArea(scrollAreaX, skillNodeBackgroundY, skillNodeHeight, width(0.8F), height(20.83F));
    MinecraftScrollableArea area = MinecraftScrollableArea.builder().bounds(skillNodeX + 
        width(1.1F), skillNodeBackgroundY, skillNodeX + skillNodeWidth + 
        width(1.0F), skillNodeBackgroundY + skillNodeHeight);
    area.setScrollArea(scrollArea);
    addScrollableArea((ScrollableArea)area);
    initSkills(scrollArea, (ScrollableArea)area);
  }
  
  private void initSkills(ScrollArea scrollArea, ScrollableArea area) {
    int count = 0;
    List<List<SlotClientData>> splitSkills = new ArrayList<>();
    for (SlotClientData data : this.data.getSlots()) {
      if (count == 0)
        splitSkills.add(new ArrayList<>()); 
      ((List<SlotClientData>)splitSkills.get(splitSkills.size() - 1)).add(data);
      count++;
      if (count == 2)
        count = 0; 
    } 
    int posIncrement = 0;
    for (List<SlotClientData> data : splitSkills) {
      FlexNode flexNode = FlexNode.horizontal(width(12.292F), height(63.241F + 12.5F * posIncrement++), height(10.556F)).setMargin(width(1.25F));
      for (SlotClientData d : data) {
        SlotClientData.SkillColorInfo info = d.getColorInfo();
        SkillNode skl = new SkillNode(d, 0.0D, 0.0D, width(36.563F), height(10.556F));
        skl.setCallback(callback -> {
              if (info.isCooldown() && !this.data.isBypass()) {
                long remaining = d.getNextChangeMillis() - System.currentTimeMillis();
                PetTranslateEnum.MESSAGE_CHANGE_SKILL_COOLDOWN.notification(new Object[] { DurationConverter.fromMillisToString(remaining) });
                return;
              } 
              if ((info.isCooldown() || info.equals(SlotClientData.SkillColorInfo.LOCKED)) && !this.data.isBypass())
                return; 
              openPopup((UIPopup)new UIAssignSkillPopup(this, d.getSlot()));
            });
        skl.setSolid(false);
        flexNode.addChild((ANode)skl);
      } 
      addNode((ANode)flexNode);
      flexNode.setArea(area);
    } 
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    super.preDraw(mouseX, mouseY, ticks);
    func_146276_q_();
    BackgroundHelper.createMinecraft(width(83.22F), height(88.889F));
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    super.postDraw(mouseX, mouseY, ticks);
  }
  
  public void openSkillRollUI() {
    openPopup((UIPopup)new UISkillRollPopup(this));
  }
  
  public void openEditPetUI() {
    openPopup((UIPopup)new UIChangeSkinPopup(this));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\home\UIPetHome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */