package fr.paladium.palamod.modules.enderchest.render;

import fr.paladium.palamod.modules.enderchest.tileentity.TileEntityPaladiumEnderChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class PaladiumEnderChestRender extends TileEntitySpecialRenderer {
  private static final ResourceLocation TEXTURE = new ResourceLocation("palamod:textures/models/PaladiumEnderChest.png");
  
  private final ModelChest model = new ModelChest();
  
  public float field_145972_a;
  
  public float field_145975_i;
  
  public int field_145973_j;
  
  public void renderTileEntityAt(TileEntityPaladiumEnderChest tile, double x, double y, double z, float ticks) {
    int i = 0;
    if (tile.func_145830_o())
      i = tile.func_145832_p(); 
    func_147499_a(TEXTURE);
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
    this.model.func_78231_a();
    GL11.glPopMatrix();
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
  }
  
  public void func_147500_a(TileEntity tile, double x, double y, double z, float ticks) {
    renderTileEntityAt((TileEntityPaladiumEnderChest)tile, x, y, z, ticks);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\render\PaladiumEnderChestRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */