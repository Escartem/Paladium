package fr.paladium.pet.client.ui.home.popup;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.buttons.utils.MinecraftScrollableArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollableArea;
import fr.paladium.lib.apollon.nodes.label.MinecraftSubTitleNodeLabel;
import fr.paladium.lib.apollon.nodes.label.MinecraftTitleNodeLabel;
import fr.paladium.lib.apollon.ui.UIPopup;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.pet.client.ui.home.UIPetHome;
import fr.paladium.pet.client.ui.home.node.skill.child.SkillNode;
import fr.paladium.pet.client.ui.utils.data.HomeData;
import fr.paladium.pet.client.ui.utils.data.SlotClientData;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.packet.pet.BBOpenUIPacket;
import fr.paladium.pet.common.network.packet.skill.CSAssignSkillPacket;
import net.minecraft.client.gui.GuiScreen;

public class UIAssignSkillPopup extends UIPopup {
  public static final String UI_ID = "palapet:POPUP-ASSIGN-SKILL";
  
  private final int slot;
  
  private final HomeData data;
  
  public UIAssignSkillPopup(UIPetHome ui, int slot) {
    super((GuiScreen)ui, "palapet:POPUP-ASSIGN-SKILL");
    this.slot = slot;
    this.data = ui.getData();
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    double areaX = width(31.71F);
    double titleY = height(15.0F);
    double subTitileY = titleY + height(10.0F);
    ANode closeNode = (new MinecraftCloseNode(width(68.0F), height(13.0F))).setCallback(c -> closePopup());
    MinecraftTitleNodeLabel titleNode = new MinecraftTitleNodeLabel(areaX, titleY, PetTranslateEnum.GUI_ASSIGN_SKILL_TITLE.text());
    MinecraftSubTitleNodeLabel subtitleNode = new MinecraftSubTitleNodeLabel(areaX, subTitileY, PetTranslateEnum.GUI_ASSIGN_SKILL_SUBTITLE.text(new Object[] { Integer.valueOf(this.slot + 1) }));
    double areaY = height(30.019F);
    double areaWidth = width(37.99F);
    double areaHeight = height(52.593F);
    double scrollAreaX = areaX + areaWidth - width(1.0F);
    double scrollAreaY = areaY;
    double scrollAreaWidth = width(0.83F);
    double scrollAreaHeight = areaHeight;
    ScrollArea scrollArea = new ScrollArea(scrollAreaX, scrollAreaY, scrollAreaHeight, width(0.8F), height(35.83F));
    MinecraftScrollableArea area = MinecraftScrollableArea.builder().bounds(areaX, areaY, areaX + areaWidth, areaY + areaHeight);
    for (int i = 0; i < this.data.getSelectableSlots().size(); i++) {
      SlotClientData slotData = this.data.getSelectableSlots().get(i);
      SkillNode skillNode = new SkillNode(slotData, width(31.719F), height(31.019F + 10.5F * i), width(35.99F), height(9.352F));
      skillNode.setCallback(callback -> {
            PetCommonProxy.getInstance().getNetwork().sendToServer((IMessage)new CSAssignSkillPacket(this.slot, slotData.getId()));
            PetCommonProxy.getInstance().getNetwork().sendToServer((IMessage)new BBOpenUIPacket());
          });
      addNode((ANode)skillNode);
      skillNode.setArea((ScrollableArea)area);
    } 
    area.setScrollArea(scrollArea);
    addScrollableArea((ScrollableArea)area);
    addNode(closeNode);
    addNode((ANode)titleNode);
    addNode((ANode)subtitleNode);
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    func_146276_q_();
    BackgroundHelper.createMinecraft(
        width(43.021F), 
        height(76.481F));
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {}
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\home\popup\UIAssignSkillPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */