package fr.paladium.palamod.modules.design.modules.spawnerfinder;

import fr.paladium.helios.client.utils.DrawingContext;
import fr.paladium.helios.module.utils.abstracts.AWidget;
import fr.paladium.helios.module.utils.annotations.Widget;
import fr.paladium.lib.apollon.fontV2.FontObj;
import fr.paladium.lib.apollon.fontV2.FontRenderer;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@Widget(type = RenderGameOverlayEvent.ElementType.TEXT, post = false)
public class WidgetSpawnerFinder extends AWidget {
  private final ResourceLocation noDetected = new ResourceLocation("palamod", "textures/overlay/spawner_finder_no_detected.png");
  
  private final ResourceLocation detected = new ResourceLocation("palamod", "textures/overlay/spawner_finder_detected.png");
  
  public void init(DrawingContext context) {
    setBoundingBox(86.0D, 43.0D, 13.0D, 4.0D);
  }
  
  public void draw(DrawingContext context) {
    Minecraft mc = context.getMinecraft();
    boolean shouldReturn = (mc.field_71439_g.func_70694_bm() == null || !(mc.field_71439_g.func_70694_bm().func_77973_b() instanceof fr.paladium.palamod.modules.miner.item.ItemSpawnerFinder) || !mc.field_71441_e.field_72995_K);
    if (shouldReturn)
      return; 
    preDraw(context, ModuleSpawnerFinder.getInstance());
    int spawners = getChuckSpawners(mc);
    GuiUtils.drawImageTransparent(getX() - width(5.0D), getY() - height(20.0D), (spawners == 0) ? this.noDetected : this.detected, height(150.0D), height(150.0D), false);
    String show = (spawners == 0) ? "§cAucun Spawner" : "§aSpawner trouvé";
    drawStringWithShadow(mc, show, getX() + width(24.0D), getY() + height(30.0D), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 40);
  }
  
  private void drawStringWithShadow(Minecraft mc, String text, double x, double y, Color color, FontObj font, int fontSize) {
    float scaleFactor = Math.min(mc.field_71443_c, 1920) / 1920.0F;
    fontSize = (int)(fontSize * scaleFactor);
    FontRenderer fontrender = new FontRenderer(font.getFontRegular(), font.getFontBold());
    fontrender.drawString(text, color, (float)x, (float)y, 10.0F, mc.field_71443_c / 1920.0F + fontSize / 80.0F, ModuleSpawnerFinder.getInstance().isTextShadow(), new Color(0, 0, 0, 150));
  }
  
  private int getChuckSpawners(Minecraft mc) {
    int radius = 1;
    int count = 0;
    for (int x = -radius; x < radius + 1; x++) {
      for (int z = -radius; z < radius + 1; z++)
        count = (int)(count + (mc.field_71441_e.func_72964_e(mc.field_71439_g.field_70176_ah + x, mc.field_71439_g.field_70164_aj + z)).field_150816_i.values().stream().filter(value -> (value instanceof net.minecraft.tileentity.TileEntityMobSpawner || value instanceof fr.paladium.palaspawner.common.tile.TileEntityEmptyMobSpawner)).count()); 
    } 
    return count;
  }
  
  public boolean canDraw(DrawingContext context) {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\design\modules\spawnerfinder\WidgetSpawnerFinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */