package fr.paladium.palamod.modules.design.modules.omniscience;

import fr.paladium.helios.client.utils.DrawingContext;
import fr.paladium.helios.module.utils.abstracts.AWidget;
import fr.paladium.helios.module.utils.annotations.Widget;
import fr.paladium.lib.apollon.fontV2.FontObj;
import fr.paladium.lib.apollon.fontV2.FontRenderer;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@Widget(type = RenderGameOverlayEvent.ElementType.TEXT, post = false)
public class WidgetOmniscience extends AWidget {
  public void init(DrawingContext context) {
    setBoundingBox(84.0D, 75.0D, 15.0D, 15.0D);
  }
  
  public void draw(DrawingContext context) {
    Minecraft mc = context.getMinecraft();
    if ((ModuleOmniscience.getInstance()).player == null)
      return; 
    preDraw(context, ModuleOmniscience.getInstance());
    drawCenteredStringWithShadow(mc, "Inventaire de " + (ModuleOmniscience.getInstance()).player, getX() + getWidth() / 2.0D, getY(), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 40);
    float scale = (float)width(0.800000011920929D);
    for (int id = 0; id < 36; id++) {
      if ((ModuleOmniscience.getInstance()).inv[id] != null)
        GuiUtils.renderScaledItemStackIntoGUI((ModuleOmniscience.getInstance()).inv[id], getX() + getWidth() / 2.0D - (scale * 12.0F) * 4.75D + (scale * 12.0F * (id % 9)), getY() + height(13.0D) + (scale * 14.0F * (id / 9)), scale); 
    } 
  }
  
  public void drawEdit(DrawingContext context) {
    Minecraft mc = context.getMinecraft();
    preDraw(context, ModuleOmniscience.getInstance());
    drawCenteredStringWithShadow(mc, "Inventaire de demo", getX() + getWidth() / 2.0D, getY(), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 40);
    float scale = (float)width(0.800000011920929D);
    for (int id = 0; id < 36; id++)
      GuiUtils.renderScaledItemStackIntoGUI(new ItemStack(Items.field_151045_i), getX() + getWidth() / 2.0D - (scale * 12.0F) * 4.75D + (scale * 12.0F * (id % 9)), getY() + height(13.0D) + (scale * 14.0F * (id / 9)), scale); 
  }
  
  private void drawCenteredStringWithShadow(Minecraft mc, String text, double x, double y, Color color, FontObj font, int fontSize) {
    float scaleFactor = Math.min(mc.field_71443_c, 1920) / 1920.0F;
    fontSize = (int)(fontSize * scaleFactor);
    FontRenderer fontrender = new FontRenderer(font.getFontRegular(), font.getFontBold());
    fontrender.drawString(text, color, (float)x - (GuiUtils.getStringWidth(mc, text, font, fontSize) / 2), (float)y, 10.0F, mc.field_71443_c / 1920.0F + fontSize / 80.0F, ModuleOmniscience.getInstance().isTextShadow(), new Color(0, 0, 0, 150));
  }
  
  public boolean canDraw(DrawingContext context) {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\design\modules\omniscience\WidgetOmniscience.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */