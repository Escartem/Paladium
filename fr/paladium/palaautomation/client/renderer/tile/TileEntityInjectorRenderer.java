package fr.paladium.palaautomation.client.renderer.tile;

import fr.paladium.palaautomation.client.model.ModelInjector;
import fr.paladium.palaautomation.common.tile.impl.TileEntityInjector;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityInjectorRenderer extends TileEntitySpecialRenderer {
  public static final ModelInjector MODEL = new ModelInjector();
  
  public static final ResourceLocation TEXTURE = new ResourceLocation("palaautomation", "textures/blocks/injector.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityInjector)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityInjector tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    func_147499_a(TEXTURE);
    MODEL.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\client\renderer\tile\TileEntityInjectorRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */