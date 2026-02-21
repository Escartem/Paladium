package fr.paladium.pet.client.ui.home.popup.node;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.pet.client.ui.home.popup.UIChangeSkinPopup;
import fr.paladium.pet.client.ui.utils.PetRenderUtils;
import fr.paladium.pet.client.ui.utils.data.SkinData;
import fr.paladium.pet.common.entity.EntityDummyPet;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class PetSkinNode extends AClickableNode {
  private static final ResourceLocation BACKGROUND = new ResourceLocation("palapet", "textures/ui/home/slot.png");
  
  private static final Color GREEN_FINISH_COLOR = new Color(48, 203, 31);
  
  private final UIChangeSkinPopup popup;
  
  private final SkinData data;
  
  private final EntityDummyPet pet;
  
  public UIChangeSkinPopup getPopup() {
    return this.popup;
  }
  
  public SkinData getData() {
    return this.data;
  }
  
  public EntityDummyPet getPet() {
    return this.pet;
  }
  
  public PetSkinNode(double x, double y, double size, SkinData data, UIChangeSkinPopup popup) {
    super(x, y, size, size);
    this.data = data;
    this.popup = popup;
    this.pet = PetRenderUtils.getPetFromEnum(this.data.getSkinId());
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    double size = this.width;
    double x = this.x;
    double y = this.y;
    GuiUtils.drawImageTransparent(x, y, BACKGROUND, size, size);
    if (isSelected())
      GuiUtils.drawRect(x, y, x + size, y + size, new Color(GREEN_FINISH_COLOR.r, GREEN_FINISH_COLOR.g, GREEN_FINISH_COLOR.b, 0.11F)); 
    this.pet.field_70142_S = 0.0D;
    this.pet.field_70137_T = 0.0D;
    this.pet.field_70136_U = 0.0D;
    this.pet.func_70634_a(0.0D, 0.0D, 0.0D);
    this.pet.field_70173_aa = mc.field_71439_g.field_70173_aa;
    PetRenderUtils.drawPetOnUI(
        (float)(x + width(50.0F)), 
        (float)(y + height(70.0F)), 
        width(40.0F), 300.0F, 0.0F, this.pet, 0.0F);
  }
  
  private boolean isSelected() {
    SkinData selected = this.popup.getSelectedSkin();
    return this.data.getSkinId().equals(selected.getSkinId());
  }
  
  public boolean onClick(int mouseX, int mouseY, int mouseButton) {
    return super.onClick(mouseX, mouseY, mouseButton);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\home\popup\node\PetSkinNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */