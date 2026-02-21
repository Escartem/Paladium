package fr.paladium.palamod.modules.luckyblock.tileentity.easter;

import fr.paladium.palamod.modules.luckyblock.blocks.models.easter.ModelEasterEgg;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityEasterEggRenderer extends TileEntitySpecialRenderer {
  private static ModelEasterEgg model = new ModelEasterEgg();
  
  public static ModelEasterEgg getModel() {
    return model;
  }
  
  private static ResourceLocation textureYellow = new ResourceLocation("palamod", "textures/blocks/easter_egg_yellow.png");
  
  public static ResourceLocation getTextureYellow() {
    return textureYellow;
  }
  
  private static ResourceLocation textureBlue = new ResourceLocation("palamod", "textures/blocks/easter_egg_blue.png");
  
  public static ResourceLocation getTextureBlue() {
    return textureBlue;
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityEasterEgg)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityEasterEgg tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    switch (tile.getColor().intValue()) {
      case 1:
        func_147499_a(textureBlue);
        break;
      case 2:
        func_147499_a(textureYellow);
        break;
    } 
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\easter\TileEntityEasterEggRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */