package fr.paladium.pet.client.ui.feed;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftBackNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.label.MinecraftSubTitleNodeLabel;
import fr.paladium.lib.apollon.nodes.label.MinecraftTitleNodeLabel;
import fr.paladium.lib.apollon.ui.UIContainer;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.pet.client.ui.feed.node.FeedPetBaseSlotNode;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.container.ContainerFeedPet;
import fr.paladium.pet.common.container.InventoryFeedPet;
import fr.paladium.pet.common.network.packet.pet.BBOpenUIPacket;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class UIFeedPetContainer extends UIContainer {
  private final InventoryFeedPet inventory;
  
  private final String title;
  
  private final String subtitle;
  
  public UIFeedPetContainer(ContainerFeedPet container) {
    super(null, "palapet:feed_pet", (Container)container);
    this.inventory = new InventoryFeedPet();
    this.title = PetTranslateEnum.GUI_NODE_FEED_TITLE.text();
    this.subtitle = PetTranslateEnum.GUI_NODE_FEED_SUBTITLE.text();
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    double titleX = width(30.95F);
    double titleY = height(14.0F);
    addNode((ANode)new MinecraftTitleNodeLabel(titleX, titleY, this.title));
    addNode((ANode)new MinecraftSubTitleNodeLabel(titleX, titleY + 
          height(10.0F), this.subtitle));
    addNode((ANode)new FeedPetBaseSlotNode((IInventory)this.inventory, 0, 
          
          width(46.04F), height(29.77F), 
          width(7.916F)));
    double backNodeX = width(65.5F);
    MinecraftBackNode backNode;
    addNode((ANode)(backNode = new MinecraftBackNode(backNodeX, height(13.5F), this.prev)));
    backNode.setCallback(c -> PetCommonProxy.getInstance().getNetwork().sendToServer((IMessage)new BBOpenUIPacket()));
    addNode((ANode)new MinecraftCloseNode(backNodeX + width(2.476F), height(13.0F)));
    double startX = width(34.479F);
    double startY = height(52.87F);
    double slotSpaceX = width(3.5F);
    double slotSpaceY = height(6.5F);
    double slotSize = width(3.125F);
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        addNode((ANode)new FeedPetBaseSlotNode((IInventory)this.field_146297_k.field_71439_g.field_71071_by, j + i * 9 + 9, startX + slotSpaceX * j, startY + slotSpaceY * i, slotSize)); 
    } 
    startX = width(34.479F);
    startY = height(74.63F);
    for (i = 0; i < 9; i++)
      addNode((ANode)new FeedPetBaseSlotNode((IInventory)this.field_146297_k.field_71439_g.field_71071_by, i, startX + slotSpaceX * i, startY, slotSize)); 
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    func_146276_q_();
    drawBackground((width(50.0F) - width(49.745F) / 2.0F), (height(50.0F) - height(79.16F) / 2.0F), width(49.475F), height(79.16F));
  }
  
  public void fixedUpdate() {
    super.fixedUpdate();
  }
  
  private void drawBackground(double x, double y, double width, double height) {
    func_146276_q_();
    BackgroundHelper.createMinecraft(
        width(43.021F), 
        height(76.481F));
  }
  
  public void func_73869_a(char c, int keyCode) {
    if (keyCode == 1) {
      PetCommonProxy.getInstance().getNetwork().sendToServer((IMessage)new BBOpenUIPacket());
      return;
    } 
    super.func_73869_a(c, keyCode);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\feed\UIFeedPetContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */