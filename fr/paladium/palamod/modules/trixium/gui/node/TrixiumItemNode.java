package fr.paladium.palamod.modules.trixium.gui.node;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollableArea;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.modules.trixium.PTrixium;
import fr.paladium.palamod.modules.trixium.gui.UITrixium;
import fr.paladium.palamod.modules.trixium.network.CSPacketTrixiumClaim;
import fr.paladium.palamod.modules.trixium.utils.TrixiumReward;
import fr.paladium.palamod.util.PaladiumColorCode;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TrixiumItemNode extends AClickableNode {
  private final ResourceLocation itemTexture = new ResourceLocation("palamod", "textures/gui/trixium/item.png");
  
  private final ResourceLocation holoTexture = new ResourceLocation("palamod", "textures/gui/trixium/holo.png");
  
  private final TrixiumReward reward;
  
  private final boolean valid;
  
  private final AtomicReference<ResourceLocation> image;
  
  public TrixiumReward getReward() {
    return this.reward;
  }
  
  public boolean isValid() {
    return this.valid;
  }
  
  public TrixiumItemNode(double x, double y, double width, double height, TrixiumReward reward) {
    super(x, y, width, height);
    this.reward = reward;
    this.valid = (reward.getCommands() != null && !reward.getCommands().isEmpty());
    this.image = downloadImage(reward.getImage());
    setCallback(node -> {
          if (reward.getState() == TrixiumReward.State.AVAILABLE) {
            PTrixium.getNetwork().sendToServer((IMessage)new CSPacketTrixiumClaim(reward.getUuid()));
            reward.setState(TrixiumReward.State.PICKUP);
          } 
        });
    setHoverFont(Fonts.MONTSERRAT_BOLD.getFont());
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    getHovers().clear();
    setHoverColor(null);
    if (this.reward.getState() == TrixiumReward.State.AVAILABLE)
      addHover("Cliquez pour §crécupérer"); 
    if (!this.valid) {
      addHover("Cette récompense n'est pas encore accessible.");
      setHoverColor(PaladiumColorCode.ERROR_COLOR);
    } 
    GL11.glPushMatrix();
    if (this.reward.isBig()) {
      GL11.glTranslated(this.x + this.width / 2.0D, this.y + this.height / 2.0D, 0.0D);
      GL11.glScaled(1.4D, 1.4D, 1.0D);
      GL11.glTranslated(-(this.x + this.width / 2.0D), -(this.y + this.height / 2.0D), 0.0D);
    } 
    GL11.glPushMatrix();
    if (this.reward.getState() == TrixiumReward.State.NOT_AVAILABLE || this.reward.getState() == TrixiumReward.State.AVAILABLE) {
      (new Color(115, 121, 218)).bind();
    } else if (this.reward.getState() == TrixiumReward.State.PICKUP) {
      (new Color(36, 219, 169)).bind();
    } 
    GuiUtils.drawImageTransparent(this.x, this.y, this.itemTexture, this.width, this.height);
    GL11.glPopMatrix();
    if (this.reward.getState() == TrixiumReward.State.AVAILABLE) {
      float mult = this.reward.isBig() ? 1.4F : 1.0F;
      GL11.glPushMatrix();
      GL11.glEnable(3089);
      (new Color(36, 219, 169)).bind();
      GuiUtils.scissor(mc, this.x + width(49.0F), 0.0D, animationValue(width(50.0F * mult)), this.ui.field_146295_m);
      GuiUtils.drawImageTransparent(this.x, this.y, this.itemTexture, this.width, this.height);
      GL11.glDisable(3089);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glEnable(3089);
      (new Color(36, 219, 169)).bind();
      GuiUtils.scissor(mc, this.x + width(51.0F) - animationValue(width(50.0F * mult)), 0.0D, animationValue(width(50.0F * mult)), this.ui.field_146295_m);
      GuiUtils.drawImageTransparent(this.x, this.y, this.itemTexture, this.width, this.height);
      GL11.glDisable(3089);
      GL11.glPopMatrix();
    } 
    GL11.glPushMatrix();
    if (this.reward.getState() == TrixiumReward.State.NOT_AVAILABLE) {
      (new Color(135, 142, 254)).bind();
    } else if (this.reward.getState() == TrixiumReward.State.AVAILABLE) {
      (new Color(36, 219, 169)).bind();
    } 
    if (!this.valid)
      PaladiumColorCode.ERROR_COLOR.bind(); 
    GuiUtils.drawImageTransparent(this.x, this.y, this.holoTexture, this.width, this.height);
    GL11.glPopMatrix();
    double holoSize = 1.2D;
    GL11.glPushMatrix();
    if (this.reward.getState() == TrixiumReward.State.NOT_AVAILABLE || this.reward.getState() == TrixiumReward.State.AVAILABLE) {
      (new Color(122, 128, 225)).bind();
    } else if (this.reward.getState() == TrixiumReward.State.PICKUP) {
      (new Color(36, 219, 169)).bind();
    } 
    if (this.reward.getState() == TrixiumReward.State.AVAILABLE) {
      double holoAnimationValue = (System.currentTimeMillis() % 1000L);
      if (holoAnimationValue < 500.0D) {
        holoSize = 1.2D + holoAnimationValue / 10000.0D;
      } else {
        holoSize = 1.3D - (1000.0D - 1000.0D - holoAnimationValue) / 10000.0D;
      } 
    } 
    GL11.glTranslated(this.x + this.width / 2.0D, this.y + this.height / 2.0D, 0.0D);
    GL11.glScaled(holoSize, holoSize, 1.0D);
    GL11.glTranslated(-(this.x + this.width / 2.0D), -(this.y + this.height / 2.0D), 0.0D);
    GuiUtils.drawImageTransparent(this.x, this.y, this.holoTexture, this.width, this.height);
    GL11.glPopMatrix();
    if (this.image != null && this.image.get() != null) {
      GL11.glPushMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glTranslated(this.x + this.width / 2.0D, this.y + this.width / 2.0D, 0.0D);
      GL11.glScaled(0.6D, 0.6D, 1.0D);
      GL11.glTranslated(-(this.x + this.width / 2.0D), -(this.y + this.width / 2.0D), 0.0D);
      GuiUtils.drawImageTransparent(this.x, this.y, this.image.get(), this.width, this.height);
      GL11.glPopMatrix();
    } 
    GuiUtils.drawCenteredStringWithCustomFont(mc, String.valueOf(this.reward.getValue()), this.x + this.width / 2.0D, this.y + height(120.0F), Color.WHITE, this.reward.isBig() ? Fonts.MONTSERRAT_EXTRABOLD.getFont() : Fonts.MONTSERRAT_BOLD.getFont(), 40);
    GL11.glPopMatrix();
  }
  
  public boolean isHovered(int mouseX, int mouseY) {
    if ((getUi() != null && (getUi()).popup != null) || (getUi() instanceof UITrixium && ((UITrixium)getUi()).isScrolling()))
      return false; 
    if (getUi() instanceof fr.paladium.palamod.modules.trixium.gui.container.UITrixiumContainer)
      return false; 
    ScrollableArea checkArea = (getParent() != null) ? getParent().getArea() : getArea();
    if (checkArea != null && !checkArea.inArea(mouseX, mouseY))
      return false; 
    if (getParent() != null) {
      mouseX = (int)(mouseX - getParent().getX());
      mouseY = (int)(mouseY - getParent().getY());
    } 
    float mult = this.reward.isBig() ? 1.2F : 1.0F;
    return (mouseX > this.x - this.width * (mult - 1.0F) && mouseX < this.x + this.width * mult && mouseY > this.y - this.height * (mult - 1.0F) && mouseY < this.y + this.height * mult);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\gui\node\TrixiumItemNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */