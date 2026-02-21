package fr.paladium.palamod.modules.luckyblock.tileentity;

import fr.paladium.palamod.modules.luckyblock.blocks.models.ModelStringTrapOn;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityStringTrapOnRender extends TileEntitySpecialRenderer {
  private static ModelStringTrapOn model = new ModelStringTrapOn();
  
  public static ModelStringTrapOn getModel() {
    return model;
  }
  
  private static ResourceLocation texture = new ResourceLocation("palamod:textures/blocks/ficelle.png");
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityStringTrapOn)te, x, y, z, ticks);
  }
  
  public void renderTileEntity() {
    func_147497_a(TileEntityRendererDispatcher.field_147556_a);
  }
  
  private void renderBlockTileEntityAt(TileEntityStringTrapOn tile, double x, double y, double z, float ticks) {
    int orientation = tile.func_145832_p();
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    func_147499_a(getTexture());
    if (orientation == 1) {
      GL11.glRotatef(180.0F, 1.0F, 0.0F, 1.0F);
    } else if (orientation == 2) {
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 90.0F);
    } else if (orientation == 3) {
      GL11.glRotatef(180.0F, 90.0F, 0.0F, 0.0F);
    } else if (orientation == 4) {
      GL11.glRotatef(180.0F, 1.0F, 0.0F, 1.0F);
    } else if (orientation == 5) {
      GL11.glRotatef(180.0F, -90.0F, 0.0F, 90.0F);
    } 
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityStringTrapOnRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */