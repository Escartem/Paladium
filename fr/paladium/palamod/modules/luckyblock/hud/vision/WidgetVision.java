package fr.paladium.palamod.modules.luckyblock.hud.vision;

import fr.paladium.helios.client.utils.DrawingContext;
import fr.paladium.helios.module.utils.abstracts.AWidget;
import fr.paladium.helios.module.utils.annotations.Widget;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import net.minecraft.util.ResourceLocation;

@Widget
public class WidgetVision extends AWidget {
  public boolean canDraw(DrawingContext arg0) {
    return ClientProxy.hasVisionSkill;
  }
  
  public void draw(DrawingContext arg0) {
    if (ModuleVision.getInstance().getLastUpdate() + 2500L <= System.currentTimeMillis())
      ClientProxy.hasVisionSkill = false; 
    boolean showInventory = ModuleVision.getInstance().showInventory();
    GuiUtils.drawRoundedRect(getX(), getY(), new Color(0.0F, 0.0F, 0.0F, 0.2F), getWidth(), showInventory ? (getHeight() + height(10.0D)) : height(10.0D), 5.0F);
    GuiUtils.drawCenteredStringWithCustomFont(arg0.getMinecraft(), "Monocle de Vision §c" + ClientProxy.nameVision, getX() + width(50.0D), getY() + 2.0D, Color.white, Fonts.MONTSERRAT_BOLD.getFont(), 20);
    if (showInventory) {
      int idArmor = 0;
      for (int i = 0; i < 4; i++) {
        if (ClientProxy.inventoryVision[i] != null) {
          GuiUtils.renderScaledItemStackIntoGUI(ClientProxy.inventoryVision[i], getX() + width(5.0D), getY() + height(73.0D) - idArmor * height(20.0D), (float)width(0.6D));
          GuiUtils.renderScaledItemStackOverlayIntoGUI(ClientProxy.inventoryVision[i], getX() + width(5.0D), getY() + height(73.0D) - idArmor * height(20.0D), (float)width(0.6D));
        } 
        idArmor++;
      } 
      int idInv = 0;
      int y = 0;
      for (int j = 4; j < ClientProxy.inventoryVision.length; j++) {
        if (ClientProxy.inventoryVision[j] != null) {
          GuiUtils.renderScaledItemStackIntoGUI(ClientProxy.inventoryVision[j], getX() + width(17.0D) + idInv * width(9.0D), getY() + height(73.0D) - y * height(20.0D), (float)width(0.5D));
          GuiUtils.renderScaledItemStackOverlayIntoGUI(ClientProxy.inventoryVision[j], getX() + width(17.0D) + idInv * width(9.0D), getY() + height(73.0D) - y * height(20.0D), (float)width(0.5D));
        } 
        idInv++;
        if (idInv == 9) {
          idInv = 0;
          y++;
        } 
      } 
    } 
    GuiUtils.drawRect(getX(), showInventory ? (getY() + height(100.0D)) : (getY() + height(10.0D)), getX() + getWidth() - width(100.0D - 100.0D * ClientProxy.healthVision / 20.0D), showInventory ? (getY() + height(10.0D) + height(100.0D)) : (getY() + height(10.0D) + height(10.0D)), new Color(0.0F, 255.0F, 0.0F, 0.5F));
    GuiUtils.drawImageTransparent(getX() + width(0.5D), showInventory ? (getY() + height(100.5D)) : (getY() + height(10.5D)), new ResourceLocation("palamod:textures/gui/LuckyBlock/hud/vision/health.png"), width(4.0D), height(9.0D));
  }
  
  public void init(DrawingContext arg0) {
    setBoundingBox(74.5D, 1.0D, 25.0D, 20.0D);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\hud\vision\WidgetVision.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */