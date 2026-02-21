package fr.paladium.palamod.modules.communityevents.client.render.barrel;

import fr.paladium.palamod.modules.communityevents.client.model.ModelBarrel;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityBarrelWood;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityBarrelRender extends TileEntitySpecialRenderer {
  public static ModelBarrel model = new ModelBarrel();
  
  public static ResourceLocation texture;
  
  public TileEntityBarrelRender() {
    texture = new ResourceLocation("palamod", "textures/blocks/barrel.png");
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityBarrelWood)te, x, y, z, ticks);
  }
  
  public void renderTileEntity() {
    func_147497_a(TileEntityRendererDispatcher.field_147556_a);
  }
  
  private void renderBlockTileEntityAt(TileEntityBarrelWood tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\client\render\barrel\TileEntityBarrelRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */