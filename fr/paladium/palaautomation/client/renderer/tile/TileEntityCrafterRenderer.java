package fr.paladium.palaautomation.client.renderer.tile;

import fr.paladium.palaautomation.client.model.ModelCrafter;
import fr.paladium.palaautomation.common.tile.impl.TileEntityCrafter;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityCrafterRenderer extends TileEntitySpecialRenderer {
  public static final ModelCrafter MODEL = new ModelCrafter();
  
  public static final ResourceLocation TEXTURE = new ResourceLocation("palaautomation", "textures/blocks/crafter.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityCrafter)te, x, y, z, ticks);
  }
  
  private void render(double x, double y, double z, float rotX, float rotY, float rotZ) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y - 0.5D, z + 0.5D);
    GL11.glRotatef(rotX, 1.0F, 0.0F, 0.0F);
    GL11.glRotatef(rotY, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(rotZ, 0.0F, 0.0F, 1.0F);
    func_147499_a(TEXTURE);
    MODEL.renderAll();
    GL11.glPopMatrix();
  }
  
  private void renderBlockTileEntityAt(TileEntityCrafter tile, double x, double y, double z, float ticks) {
    render(x, y + 2.0D, z, 180.0F, 0.0F, 0.0F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\client\renderer\tile\TileEntityCrafterRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */