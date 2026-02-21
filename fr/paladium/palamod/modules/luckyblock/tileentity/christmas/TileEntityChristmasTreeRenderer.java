package fr.paladium.palamod.modules.luckyblock.tileentity.christmas;

import fr.paladium.palamod.modules.luckyblock.blocks.models.ModelChristmasTree;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityChristmasTreeRenderer extends TileEntitySpecialRenderer {
  private static ModelChristmasTree model = new ModelChristmasTree();
  
  public static ModelChristmasTree getModel() {
    return model;
  }
  
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/christmas_tree.png");
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityChristmasTree)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityChristmasTree tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    func_147499_a(texture);
    model.renderAll(tile.getState());
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\christmas\TileEntityChristmasTreeRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */