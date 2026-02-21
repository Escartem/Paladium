package fr.paladium.palamod.modules.hunter.renders;

import fr.paladium.palamod.modules.hunter.blocks.ModelBamboo;
import fr.paladium.palamod.modules.hunter.tileentities.TileEntityBamboo;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityBambooRenderer extends TileEntitySpecialRenderer {
  public static ModelBamboo getModel() {
    return model;
  }
  
  private static ModelBamboo model = new ModelBamboo();
  
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/bamboo/bambou_stage_1_1.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityBamboo)te, x, y, z, ticks);
  }
  
  public void renderTileEntity() {
    func_147497_a(TileEntityRendererDispatcher.field_147556_a);
  }
  
  private void renderBlockTileEntityAt(TileEntityBamboo tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\renders\TileEntityBambooRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */