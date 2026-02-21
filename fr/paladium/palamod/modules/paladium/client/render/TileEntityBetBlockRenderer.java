package fr.paladium.palamod.modules.paladium.client.render;

import fr.paladium.palamod.modules.paladium.client.model.ModelBetBlock;
import fr.paladium.palamod.modules.paladium.common.blocks.TileEntityBetBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityBetBlockRenderer extends TileEntitySpecialRenderer {
  public static ModelBetBlock model = new ModelBetBlock();
  
  public static ResourceLocation texture;
  
  public TileEntityBetBlockRenderer() {
    texture = new ResourceLocation("palamod", "textures/blocks/betblock.png");
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityBetBlock)te, x, y, z, ticks);
  }
  
  public void renderTileEntity() {
    func_147497_a(TileEntityRendererDispatcher.field_147556_a);
  }
  
  private void renderBlockTileEntityAt(TileEntityBetBlock tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
    GL11.glRotated((Minecraft.func_71410_x()).field_71439_g.field_70177_z, 0.0D, 1.0D, 0.0D);
    GL11.glScaled(0.0625D, 0.0625D, 0.0625D);
    GL11.glScaled(0.3D, 0.3D, 0.3D);
    GL11.glDisable(2896);
    String name = "§cFaites vos jeux !";
    int nameSize = (Minecraft.func_71410_x()).field_71466_p.func_78256_a(name);
    (Minecraft.func_71410_x()).field_71466_p.func_78276_b(name, -nameSize / 2, 0, 16777215);
    GL11.glEnable(2896);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\TileEntityBetBlockRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */