package fr.paladium.pet.client.ui.home.popup;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftTextCallToActionNode;
import fr.paladium.lib.apollon.nodes.buttons.utils.MinecraftScrollableArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollableArea;
import fr.paladium.lib.apollon.nodes.flex.FlexNode;
import fr.paladium.lib.apollon.nodes.label.MinecraftSubTitleNodeLabel;
import fr.paladium.lib.apollon.nodes.label.MinecraftTitleNodeLabel;
import fr.paladium.lib.apollon.ui.UIPopup;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.pet.client.ui.home.UIPetHome;
import fr.paladium.pet.client.ui.home.popup.node.PetSkinNode;
import fr.paladium.pet.client.ui.utils.PetRenderUtils;
import fr.paladium.pet.client.ui.utils.data.HomeData;
import fr.paladium.pet.client.ui.utils.data.SkinData;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.entity.EntityDummyPet;
import fr.paladium.pet.common.network.packet.pet.BBOpenUIPacket;
import fr.paladium.pet.common.network.packet.pet.CSChangePetSkinPacket;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;

public class UIChangeSkinPopup extends UIPopup {
  public static final String UI_ID = "palapet:POPUP-CHANGE-SKIN";
  
  private static final Color BACKGROUND_COLOR = Color.decode("#1A1A1E");
  
  private final HomeData data;
  
  private SkinData selectedSkin;
  
  private EntityDummyPet pet;
  
  public HomeData getData() {
    return this.data;
  }
  
  public SkinData getSelectedSkin() {
    return this.selectedSkin;
  }
  
  public EntityDummyPet getPet() {
    return this.pet;
  }
  
  public UIChangeSkinPopup(UIPetHome ui) {
    super((GuiScreen)ui, "palapet:POPUP-CHANGE-SKIN");
    this.data = ui.getData();
    this.selectedSkin = this.data.getCurrentSkin();
    this.pet = PetRenderUtils.getPetFromEnum(this.selectedSkin.getSkinId());
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    double areaX = width(31.71F);
    double titleY = height(15.0F);
    double subTitleY = titleY + height(10.0F);
    double validateWidth = width(16.51F);
    double validateX = width(50.0F) - validateWidth / 2.0D;
    double validateY = height(81.5F);
    ANode closeNode = (new MinecraftCloseNode(width(68.0F), height(13.0F))).setCallback(c -> closePopup());
    MinecraftTitleNodeLabel titleNode = new MinecraftTitleNodeLabel(areaX, titleY, PetTranslateEnum.GUI_CHANGE_SKIN_TITLE.text());
    MinecraftSubTitleNodeLabel subtitleNode = new MinecraftSubTitleNodeLabel(areaX, subTitleY, PetTranslateEnum.GUI_CHANGE_SKIN_SUBTITLE.text());
    ANode validateNode = (new MinecraftTextCallToActionNode(validateX, validateY, validateWidth, PetTranslateEnum.GUI_CHANGE_SKIN_VALIDATE_TEXT.text())).setCallback(callback -> confirm());
    double scrollX = width(29.052F);
    double scrollY = height(57.5F);
    double skillNodeWidth = width(40.698F);
    double skillNodeHeight = height(23.1F);
    double scrollAreaX = scrollX + skillNodeWidth - width(0.5F);
    ScrollArea scrollArea = new ScrollArea(scrollAreaX, scrollY, skillNodeHeight, width(0.8F), height(14.83F));
    MinecraftScrollableArea area = MinecraftScrollableArea.builder().bounds(scrollX, scrollY, scrollX + skillNodeWidth, scrollY + skillNodeHeight);
    area.setScrollArea(scrollArea);
    addScrollableArea((ScrollableArea)area);
    addNode(closeNode);
    addNode((ANode)titleNode);
    addNode((ANode)subtitleNode);
    addNode(validateNode);
    initSkins((ScrollableArea)area);
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    func_146276_q_();
    BackgroundHelper.createMinecraft(
        width(43.021F), 
        height(76.481F));
    double backgroundX = width(28.497F);
    double backgroundY = height(29.5F);
    double backgroundWidth = width(42.221F);
    double backgroundHeight = height(27.407F);
    GuiUtils.drawRect(backgroundX, backgroundY, backgroundX + backgroundWidth, backgroundY + backgroundHeight, BACKGROUND_COLOR);
    float size = 12.0F;
    float petWidth = width(size);
    float petHeight = width(size);
    float petX = width(50.0F) - petWidth / 2.0F;
    float petY = (float)(backgroundHeight / 2.0D + backgroundY - (petHeight / 2.0F));
    this.pet.field_70142_S = 0.0D;
    this.pet.field_70137_T = 0.0D;
    this.pet.field_70136_U = 0.0D;
    this.pet.func_70634_a(0.0D, 0.0D, 0.0D);
    this.pet.field_70173_aa = this.field_146297_k.field_71439_g.field_70173_aa;
    PetRenderUtils.drawPetOnUI(
        width(50.0F), 
        height(53.0F), 
        width(8.0F), 300.0F, 0.0F, this.pet, 0.0F);
  }
  
  private void confirm() {
    if (this.selectedSkin == null)
      return; 
    PetCommonProxy.getInstance().getNetwork().sendToServer((IMessage)new CSChangePetSkinPacket(this.selectedSkin.getSkinId()));
    PetCommonProxy.getInstance().getNetwork().sendToServer((IMessage)new BBOpenUIPacket());
  }
  
  private void initSkins(ScrollableArea area) {
    int count = 0;
    int skinPerLine = 6;
    List<List<SkinData>> splitSkins = new ArrayList<>();
    for (SkinData skinData : this.data.getSkins()) {
      if (count == 0)
        splitSkins.add(new ArrayList<>()); 
      ((List<SkinData>)splitSkins.get(splitSkins.size() - 1)).add(skinData);
      count++;
      if (count == skinPerLine)
        count = 0; 
    } 
    int posIncrement = 0;
    for (List<SkinData> skinDatas : splitSkins) {
      FlexNode flexNode = FlexNode.horizontal(width(30.052F), height(58.5F + 12.15F * posIncrement++), height(11.8F)).setMargin(width(0.3F));
      for (SkinData skinData : skinDatas) {
        PetSkinNode skl = new PetSkinNode(0.0D, 0.0D, width(6.2F), skinData, this);
        skl.setCallback(callback -> {
              this.selectedSkin = skinData;
              this.pet = PetRenderUtils.getPetFromEnum(this.selectedSkin.getSkinId());
            });
        flexNode.addChild((ANode)skl);
      } 
      addNode((ANode)flexNode);
      flexNode.setArea(area);
    } 
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {}
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\home\popup\UIChangeSkinPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */