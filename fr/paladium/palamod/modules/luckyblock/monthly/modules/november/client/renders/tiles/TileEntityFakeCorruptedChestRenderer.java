package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.tiles;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityFakeCorruptedChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityFakeCorruptedChestRenderer extends TileEntitySpecialRenderer {
  public static ModelChest getModel() {
    return model;
  }
  
  private static final ModelChest model = new ModelChest();
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  private static final ResourceLocation texture = new ResourceLocation("palamod", "textures/models/fake_corrupted_chest.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityFakeCorruptedChest)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityFakeCorruptedChest tile, double x, double y, double z, float ticks) {
    int i = 0;
    if (tile.func_145830_o())
      i = tile.func_145832_p(); 
    func_147499_a(texture);
    GL11.glPushMatrix();
    GL11.glEnable(32826);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glTranslatef((float)x, (float)y + 1.0F, (float)z + 1.0F);
    GL11.glScalef(1.0F, -1.0F, -1.0F);
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    short short1 = 0;
    if (i == 2)
      short1 = 180; 
    if (i == 3)
      short1 = 0; 
    if (i == 4)
      short1 = 90; 
    if (i == 5)
      short1 = -90; 
    GL11.glRotatef(short1, 0.0F, 1.0F, 0.0F);
    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    model.func_78231_a();
    GL11.glPopMatrix();
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\client\renders\tiles\TileEntityFakeCorruptedChestRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */