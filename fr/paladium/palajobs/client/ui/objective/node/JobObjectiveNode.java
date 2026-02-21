package fr.paladium.palajobs.client.ui.objective.node;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class JobObjectiveNode extends AClickableNode {
  private static final ResourceLocation SLOT = new ResourceLocation("palajobs", "textures/gui/popup/slot.png");
  
  private static final ResourceLocation SLOT_LOCKED = new ResourceLocation("palajobs", "textures/gui/popup/slot_locked.png");
  
  protected final ObjectiveType type;
  
  private final ItemStack icon;
  
  protected final double xp;
  
  public ObjectiveType getType() {
    return this.type;
  }
  
  public ItemStack getIcon() {
    return this.icon;
  }
  
  public double getXp() {
    return this.xp;
  }
  
  public JobObjectiveNode(double x, double y, double width, ObjectiveType type, ItemStack icon, double xp) {
    super(x, y, width, width * 0.10000000149011612D);
    this.type = type;
    this.icon = icon;
    this.xp = xp;
    int fontHeight = GuiUtils.getFontHeight(Minecraft.func_71410_x(), Fonts.MONTSERRAT_EXTRABOLD.getFont(), 20);
    TextNodeLabel objectiveText = new TextNodeLabel(x + width(27.45F), y + this.height / 2.0D - (fontHeight / 2), TTT.format(type.getTranslate(), new Object[0]).toUpperCase(), Fonts.MONTSERRAT_EXTRABOLD.getFont(), 20, Color.WHITE);
    objectiveText.setTextOverflow(TextOverflow.ELLIPSIS);
    objectiveText.setTextAlign(TextAlign.CENTER);
    objectiveText.setHoverFont(Fonts.MONTSERRAT_BOLD.getFont());
    objectiveText.setWidth(width(50));
    addChild((ANode)objectiveText);
    if (this.icon != null && this.icon.func_77973_b() != null && this.icon.func_82833_r() != null) {
      TextNodeLabel itemText = new TextNodeLabel(x + width(12.0F), y + this.height / 2.0D - (fontHeight / 2), this.icon.func_82833_r(), Fonts.MONTSERRAT_EXTRABOLD.getFont(), 20, Color.WHITE);
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
    GuiUtils.drawStringWithCustomFont(mc, xpStr, this.x + width(91.0F) - GuiUtils.getStringWidth(mc, xpStr, Fonts.MONTSERRAT_EXTRABOLD.getFont(), 20), this.y + this.height / 2.0D - (fontHeight / 2), isEnabled() ? new Color(94, 212, 42) : new Color(102, 102, 102), Fonts.MONTSERRAT_EXTRABOLD.getFont(), 20);
    if (this.icon == null || this.icon.func_77973_b() == null)
      return; 
    if (this.type.equals(ObjectiveType.ENTITY_KILL)) {
      GuiUtils.renderEntity(this.x + width(3.7F), this.y + height(27.0F), width(5.0F), 0.0F, 0.0F, (EntityLivingBase)mc.field_71439_g);
    } else {
      GuiUtils.renderScaledItemStackIntoGUI(this.icon, this.x + width(3.7F), this.y + height(27.0F), width(0.3F));
    } 
  }
  
  public boolean match(String search) {
    search = search.toLowerCase();
    if (this.type.toString().toLowerCase().contains(search) || TTT.format(this.type.getTranslate(), new Object[0]).toLowerCase().contains(search))
      return true; 
    if (this.icon.func_82833_r().toLowerCase().contains(search) || this.icon.func_77977_a().toLowerCase().contains(search))
      return true; 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\objective\node\JobObjectiveNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */