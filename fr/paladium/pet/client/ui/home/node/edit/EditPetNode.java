package fr.paladium.pet.client.ui.home.node.edit;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftTextCallToActionNode;
import fr.paladium.lib.apollon.ui.UIPopup;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.pet.client.ui.home.UIPetHome;
import fr.paladium.pet.client.ui.home.node.edit.child.EditPetButtonNode;
import fr.paladium.pet.client.ui.home.popup.UIChangeSkinPopup;
import fr.paladium.pet.client.ui.utils.PetRenderUtils;
import fr.paladium.pet.client.ui.utils.data.HomeData;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.entity.EntityDummyPet;
import fr.paladium.pet.common.network.packet.pet.CSChangeSkinVisibilityPacket;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class EditPetNode extends ANode {
  private final HomeData data;
  
  private final EntityDummyPet pet;
  
  public EditPetNode(UIPetHome parentUI, double x, double y, double width, double height) {
    super(x, y, width, height);
    this.data = parentUI.getData();
    EditPetButtonNode editPetButtonNode = new EditPetButtonNode(width(85.0F), height(2.0F));
    editPetButtonNode.setCallback(c -> parentUI.openPopup((UIPopup)new UIChangeSkinPopup(parentUI)));
    addChild((ANode)editPetButtonNode);
    this.pet = PetRenderUtils.getPetFromEnum(parentUI.getData().getCurrentSkin().getSkinId());
    String title = this.data.isVisible() ? "Visible" : "Invisible";
    ANode button = (new MinecraftTextCallToActionNode(width(5.5F), height(83.0F), width(89.0F), title)).setCallback(callback -> PetCommonProxy.getInstance().getNetwork().sendToServer((IMessage)new CSChangeSkinVisibilityPacket()));
    addChild(button);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, UIPetHome.BACKGROUND_COLOR);
    double logoSize = this.ui.width(13.49F);
    double logoX = this.x + width(50.0F) - logoSize / 2.0D;
    double logoY = this.y + height(10.0F);
    this.pet.field_70142_S = 0.0D;
    this.pet.field_70137_T = 0.0D;
    this.pet.field_70136_U = 0.0D;
    this.pet.func_70634_a(0.0D, 0.0D, 0.0D);
    this.pet.field_70173_aa = mc.field_71439_g.field_70173_aa;
    GL11.glPushMatrix();
    GL11.glScaled(1.0D, 1.0D, (1.0F / width(40.0F)));
    PetRenderUtils.drawPetOnUI(
        (float)(this.x + width(50.0F)), 
        (float)(this.y + height(70.0F)), 
        width(40.0F), 300.0F, 0.0F, this.pet, 0.0F);
    GL11.glPopMatrix();
  }
  
  public boolean onClick(int i, int i1, int i2) {
    return false;
  }
  
  public void onRelease(int i, int i1, int i2) {}
  
  public void onKeyTyped(char c, int i) {}
  
  public void onHover(int i, int i1) {}
  
  public void onHoverOut(int i, int i1) {}
  
  public void fixedUpdate() {}
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\home\node\edit\EditPetNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */