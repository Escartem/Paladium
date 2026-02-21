package fr.paladium.palamod.modules.egghunt.common.helios;

import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.helios.client.utils.DrawingContext;
import fr.paladium.helios.module.utils.abstracts.AWidget;
import fr.paladium.helios.module.utils.annotations.Widget;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@Widget(type = RenderGameOverlayEvent.ElementType.TEXT, post = false)
public class WidgetEggHunt extends AWidget {
  public void init(DrawingContext context) {
    setBoundingBox(89.0D, 1.0D, 20.0D, 2.0D);
  }
  
  public void draw(DrawingContext context) {
    if (!ModuleEggHunt.getInstance().isActive())
      return; 
    Minecraft mc = context.getMinecraft();
    int fontHeight = GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_BOLD.getFont(), 40);
    String str = "";
    if (ModuleEggHunt.getInstance().isActive()) {
      str = "§aVous êtes en possession de l'oeuf" + (ModuleEggHunt.getInstance().isCanXp() ? "\n§8/§eegg reward §bpour récupérer votre récompense" : "");
    } else {
      str = "§cVous ne possédez pas l'oeuf du dragon";
    } 
    GuiUtils.drawSplittedString(mc, str, getX() + getWidth() / 2.0D, getY() + getHeight() / 2.0D - fontHeight / 2.0D, Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 40, getWidth(), TextAlign.CENTER);
  }
  
  public void drawEdit(DrawingContext context) {
    Minecraft mc = context.getMinecraft();
    int fontHeight = GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_BOLD.getFont(), 40);
    GuiUtils.drawSplittedString(mc, "§aVous êtes en possession de l'oeuf\n§8/§eegg reward §bpour récupérer votre récompense", getX() + getWidth() / 2.0D, getY() + getHeight() / 2.0D - fontHeight / 2.0D, Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 40, getWidth(), TextAlign.CENTER);
  }
  
  public boolean canDraw(DrawingContext context) {
    return (CommonModule.getInstance().getConfig().getServerType() == ServerType.FACTION);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\common\helios\WidgetEggHunt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */