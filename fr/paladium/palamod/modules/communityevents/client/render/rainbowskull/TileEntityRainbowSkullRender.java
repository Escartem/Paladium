package fr.paladium.palamod.modules.communityevents.client.render.rainbowskull;

import fr.paladium.palamod.modules.communityevents.client.model.ModelRainbowSkull;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityRainbowSkull;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityRainbowSkullRender extends TileEntitySpecialRenderer {
  public static ModelRainbowSkull model = new ModelRainbowSkull();
  
  public static ResourceLocation texture;
  
  public TileEntityRainbowSkullRender() {
    texture = new ResourceLocation("palamod", "textures/blocks/rainbowskull.png");
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityRainbowSkull)te, x, y, z, ticks);
  }
  
  public void renderTileEntity() {
    func_147497_a(TileEntityRendererDispatcher.field_147556_a);
  }
  
  private void renderBlockTileEntityAt(TileEntityRainbowSkull tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 0.0D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\client\render\rainbowskull\TileEntityRainbowSkullRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */