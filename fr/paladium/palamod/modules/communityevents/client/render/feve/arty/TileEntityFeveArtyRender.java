package fr.paladium.palamod.modules.communityevents.client.render.feve.arty;

import fr.paladium.palamod.modules.communityevents.client.model.ModelFeveArty;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityFeveArty;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityFeveArtyRender extends TileEntitySpecialRenderer {
  public static ModelFeveArty model = new ModelFeveArty();
  
  public static ResourceLocation texture;
  
  public TileEntityFeveArtyRender() {
    texture = new ResourceLocation("palamod", "textures/blocks/feve_arty.png");
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityFeveArty)te, x, y, z, ticks);
  }
  
  public void renderTileEntity() {
    func_147497_a(TileEntityRendererDispatcher.field_147556_a);
  }
  
  private void renderBlockTileEntityAt(TileEntityFeveArty tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    float rotation = 0.0F;
    switch (tile.func_145832_p()) {
      case 0:
        rotation = 0.0F;
        break;
      case 1:
        rotation = 90.0F;
        break;
      case 2:
        rotation = 180.0F;
        break;
      case 3:
        rotation = -90.0F;
        break;
    } 
    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
    GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\client\render\feve\arty\TileEntityFeveArtyRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */