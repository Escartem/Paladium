package fr.paladium.palamod.modules.paladium.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.common.logics.PaladiumChestLogic;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class PaladiumChestRender extends TileEntitySpecialRenderer {
  public float field_145972_a;
  
  public float field_145975_i;
  
  public int field_145973_j;
  
  private static final ResourceLocation TEXTURE = new ResourceLocation("palamod:textures/models/PaladiumChest.png");
  
  private ModelChest model = new ModelChest();
  
  public void renderTileEntityAt(PaladiumChestLogic tile, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_) {
    int i = 0;
    if (tile.func_145830_o())
      i = tile.func_145832_p(); 
    func_147499_a(TEXTURE);
    GL11.glPushMatrix();
    GL11.glEnable(32826);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glTranslatef((float)p_147500_2_, (float)p_147500_4_ + 1.0F, (float)p_147500_6_ + 1.0F);
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
    if (tile.openning)
      if (tile.lid >= 1.0F || tile.closing == true) {
        tile.openning = false;
      } else {
        tile.lid += 0.05F;
      }  
    if (tile.closing)
      if (tile.lid <= 0.0F || tile.openning == true) {
        tile.closing = false;
      } else {
        tile.lid -= 0.05F;
      }  
    float f1 = tile.lid;
    f1 = 1.0F - f1;
    f1 = 1.0F - f1 * f1 * f1;
    if (f1 < 0.0F)
      f1 = 0.0F; 
    this.model.field_78234_a.field_78795_f = -(f1 * 3.1415927F / 2.0F);
    this.model.func_78231_a();
    GL11.glPopMatrix();
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
  }
  
  public void func_147500_a(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_) {
    renderTileEntityAt((PaladiumChestLogic)p_147500_1_, p_147500_2_, p_147500_4_, p_147500_6_, p_147500_8_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\PaladiumChestRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */