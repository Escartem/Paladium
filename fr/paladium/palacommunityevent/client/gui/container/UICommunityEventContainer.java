package fr.paladium.palacommunityevent.client.gui.container;

import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftBackNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.label.TextNodeLabel;
import fr.paladium.lib.apollon.ui.UIContainer;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.palacommunityevent.client.gui.container.node.PceMinecraftTitleNodeLabel;
import fr.paladium.palacommunityevent.client.gui.container.slot.UICommunityEventBaseSlot;
import fr.paladium.palacommunityevent.client.gui.container.slot.UICommunityEventSlot;
import fr.paladium.palacommunityevent.common.pojo.CommunityEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class UICommunityEventContainer extends UIContainer {
  public CommunityEvent communityEventData;
  
  public static ContainerCommunityEvent container;
  
  public UICommunityEventContainer() {
    super(null, "palacommunityevent:communityevent", container = new ContainerCommunityEvent((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    func_146276_q_();
    BackgroundHelper.createMinecraft(width(49.475002F), height(79.16F));
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    if (this.communityEventData == null)
      return; 
    addNode((ANode)(new PceMinecraftTitleNodeLabel(width(27.95F), height(12.0F), this.communityEventData.name, this.communityEventData.titleColor, this.communityEventData.titleColorShadow)).setFontSize(200));
    addNode((ANode)(new TextNodeLabel(width(27.6F), height(20.0F), this.communityEventData.instructions, Fonts.PIXEL_NES.getFont(), 80, Color.WHITE)).setTextAlign(TextAlign.CENTER));
    float xOffset = -5.0F;
    addNode((ANode)(new UICommunityEventSlot(new CommunityEventInventory(), 0, width(46.04F), height(34.77F + xOffset), width(7.916D), this.communityEventData.textureFolder + "/depositslot")).setPlaceholder(this.communityEventData.targetedItems.get(0)));
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        addNode((ANode)new UICommunityEventBaseSlot((IInventory)this.field_146297_k.field_71439_g.field_71071_by, j + i * 9 + 9, (width(29.167F) + width(4.74F) * j), (height(54.61F + xOffset) + height(8.42F) * i), width(3.96D))); 
    } 
    for (i = 0; i < 9; i++)
      addNode((ANode)new UICommunityEventBaseSlot((IInventory)this.field_146297_k.field_71439_g.field_71071_by, i, (width(29.167F) + width(4.74F) * i), height(82.5F + xOffset), width(3.96D))); 
    MinecraftBackNode backNode;
    addNode((ANode)(backNode = new MinecraftBackNode(width(67.92375F), height(13.5F), this.prev)));
    backNode.setCallback(c -> (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/pce gui " + this.communityEventData.id));
    addNode((ANode)new MinecraftCloseNode(width(70.4F), height(13.0F)));
  }
  
  public void setCommunityEventData(CommunityEvent communityEventData) {
    this.communityEventData = communityEventData;
    container.setCommunityEventData(communityEventData);
    func_73866_w_();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\client\gui\container\UICommunityEventContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */