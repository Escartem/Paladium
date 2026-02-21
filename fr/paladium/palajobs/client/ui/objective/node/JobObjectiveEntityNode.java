package fr.paladium.palajobs.client.ui.objective.node;

import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.label.TextNodeLabel;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.lib.apollon.utils.text.TextOverflow;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.translate.common.texttotranslate.TTT;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class JobObjectiveEntityNode extends JobObjectiveNode {
  private static final ResourceLocation SLOT = new ResourceLocation("palajobs", "textures/gui/popup/slot.png");
  
  private static final ResourceLocation SLOT_LOCKED = new ResourceLocation("palajobs", "textures/gui/popup/slot_locked.png");
  
  private final Entity entity;
  
  private final boolean randomXP;
  
  public Entity getEntity() {
    return this.entity;
  }
  
  public boolean isRandomXP() {
    return this.randomXP;
  }
  
  public JobObjectiveEntityNode(double x, double y, double width, ObjectiveType type, ItemStack icon, double xp, Entity entity, boolean randomXP) {
    super(x, y, width, type, icon, xp);
    this.entity = entity;
    this.randomXP = randomXP;
    int fontHeight = GuiUtils.getFontHeight(Minecraft.func_71410_x(), Fonts.MONTSERRAT_EXTRABOLD.getFont(), 20);
    TextNodeLabel objectiveText = new TextNodeLabel(x + width(27.45F), y + this.height / 2.0D - (fontHeight / 2), TTT.format(type.getTranslate(), new Object[0]).toUpperCase(), Fonts.MONTSERRAT_EXTRABOLD.getFont(), 20, Color.WHITE);
    objectiveText.setTextOverflow(TextOverflow.ELLIPSIS);
    objectiveText.setTextAlign(TextAlign.CENTER);
    objectiveText.setHoverFont(Fonts.MONTSERRAT_BOLD.getFont());
    objectiveText.setWidth(width(50));
    addChild((ANode)objectiveText);
    if (this.entity.func_70005_c_() != null) {
      TextNodeLabel itemText = new TextNodeLabel(x + width(12.0F), y + this.height / 2.0D - (fontHeight / 2), this.entity.func_70005_c_(), Fonts.MONTSERRAT_EXTRABOLD.getFont(), 20, Color.WHITE);
      itemText.setTextOverflow(TextOverflow.ELLIPSIS);
      itemText.setWidth((width(40) - Math.min(width(25.0F), (GuiUtils.getStringWidth(Minecraft.func_71410_x(), TTT.format(type.getTranslate(), new Object[0]).toUpperCase(), Fonts.MONTSERRAT_EXTRABOLD.getFont(), 20) / 2))));
      itemText.setHoverFont(Fonts.MONTSERRAT_BOLD.getFont());
      addChild((ANode)itemText);
    } 
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawImageTransparent(this.x, this.y, isEnabled() ? SLOT : SLOT_LOCKED, this.width, this.height);
    int fontHeight = GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_EXTRABOLD.getFont(), 20);
    String xpStr = String.valueOf(this.xp);
    if (this.randomXP)
      xpStr = "Variable"; 
    GuiUtils.drawStringWithCustomFont(mc, xpStr, this.x + width(91.0F) - GuiUtils.getStringWidth(mc, xpStr, Fonts.MONTSERRAT_EXTRABOLD.getFont(), 20), this.y + this.height / 2.0D - (fontHeight / 2), isEnabled() ? new Color(94, 212, 42) : new Color(102, 102, 102), Fonts.MONTSERRAT_EXTRABOLD.getFont(), 20);
    if (this.entity == null)
      return; 
    double scaleY = 1.0D / this.entity.field_70131_O * 30.0D;
    double scaleX = 1.0D / this.entity.field_70130_N * 20.0D;
    double scale = (scaleY > scaleX) ? scaleX : scaleY;
    GL11.glPushMatrix();
    GL11.glTranslated(this.x + width(6.0F), this.y + height(72.0F), 0.0D);
    GL11.glScaled(scale, scale, 1.0D);
    GL11.glTranslated(-(this.x + width(6.0F)), -(this.y + height(72.0F)), 0.0D);
    GuiUtils.renderEntity(this.x + width(6.0F), this.y + height(72.0F), 1.0D, 0.0F, 0.0F, (EntityLivingBase)this.entity);
    GL11.glPopMatrix();
  }
  
  public boolean match(String search) {
    search = search.toLowerCase();
    if (this.type.toString().toLowerCase().contains(search) || TTT.format(this.type.getTranslate(), new Object[0]).toLowerCase().contains(search))
      return true; 
    if (this.entity.func_70005_c_().toLowerCase().contains(search) || this.entity.func_70005_c_().toLowerCase().contains(search))
      return true; 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\objective\node\JobObjectiveEntityNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */