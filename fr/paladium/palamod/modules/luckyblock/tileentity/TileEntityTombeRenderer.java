package fr.paladium.palamod.modules.luckyblock.tileentity;

import fr.paladium.palamod.modules.luckyblock.blocks.models.ModelTombe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityTombeRenderer extends TileEntitySpecialRenderer {
  private static ModelTombe model = new ModelTombe();
  
  public static ModelTombe getModel() {
    return model;
  }
  
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/tombe.png");
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  public void renderTileEntity() {
    func_147497_a(TileEntityRendererDispatcher.field_147556_a);
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityTombe)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityTombe tile, double x, double y, double z, float ticks) {
    if ((Minecraft.func_71410_x()).field_71439_g == null)
      return; 
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.0D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    int meta = tile.func_145831_w().func_72805_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.6D, z + 0.5D);
    GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
    GL11.glRotated((Minecraft.func_71410_x()).field_71439_g.field_70177_z, 0.0D, 1.0D, 0.0D);
    GL11.glScaled(0.0625D, 0.0625D, 0.0625D);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glDisable(2896);
    String name = "§cTombe de §e§l" + ((tile.getOwner() == null || tile.getOwner().isEmpty()) ? "§epersonne" : tile.getOwner());
    int nameSize = (Minecraft.func_71410_x()).field_71466_p.func_78256_a(name);
    MovingObjectPosition lastPosition = (Minecraft.func_71410_x()).field_71439_g.func_70614_a(8.0D, 1.0F);
    if (lastPosition != null && MovingObjectPosition.MovingObjectType.BLOCK.equals(lastPosition.field_72313_a) && 
      tile.field_145851_c == lastPosition.field_72311_b && tile.field_145848_d == lastPosition.field_72312_c && tile.field_145849_e == lastPosition.field_72309_d)
      (Minecraft.func_71410_x()).field_71466_p.func_78276_b(name, -nameSize / 2, 0, 16777215); 
    GL11.glEnable(2896);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityTombeRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */