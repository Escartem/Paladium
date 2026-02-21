package fr.paladium.palamod.modules.luckyblock.tileentity;

import fr.paladium.palamod.modules.luckyblock.blocks.models.ModelSleepingBag;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntitySleepingBagRenderer extends TileEntitySpecialRenderer {
  private static ModelSleepingBag model = new ModelSleepingBag();
  
  public static ModelSleepingBag getModel() {
    return model;
  }
  
  private static ResourceLocation texture = new ResourceLocation("palamod:textures/blocks/sleeping_bed.png");
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntitySleepingBag)te, x, y, z, ticks);
  }
  
  public void renderTileEntity() {
    func_147497_a(TileEntityRendererDispatcher.field_147556_a);
  }
  
  private void renderBlockTileEntityAt(TileEntitySleepingBag tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    func_147499_a(getTexture());
    tile.func_145832_p();
    if (tile.field_145847_g == 1) {
      GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
      GL11.glRotatef(180.0F, 90.0F, 0.0F, 90.0F);
      model.renderAll();
    } 
    if (tile.field_145847_g == 9) {
      GL11.glTranslated(x + 1.5D, y + 1.5D, z + 0.5D);
      GL11.glRotatef(180.0F, 90.0F, 0.0F, 90.0F);
      model.renderAll();
    } 
    if (tile.field_145847_g == 2) {
      GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      model.renderAll();
    } 
    if (tile.field_145847_g == 10) {
      GL11.glTranslated(x + 0.5D, y - 1.5D, z + 0.5D);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      model.renderAll();
    } 
    if (tile.field_145847_g == 3) {
      GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
      GL11.glRotatef(180.0F, 90.0F, 0.0F, -90.0F);
      model.renderAll();
    } 
    if (tile.field_145847_g == 11) {
      GL11.glTranslated(x - 0.5D, y + 1.5D, z + 0.5D);
      GL11.glRotatef(180.0F, 90.0F, 0.0F, -90.0F);
      model.renderAll();
    } 
    if (tile.field_145847_g == 8) {
      GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      model.renderAll();
    } 
    if (tile.field_145847_g == 0) {
      GL11.glTranslated(x + 0.5D, y + 1.5D, z + 1.5D);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      model.renderAll();
    } 
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntitySleepingBagRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */