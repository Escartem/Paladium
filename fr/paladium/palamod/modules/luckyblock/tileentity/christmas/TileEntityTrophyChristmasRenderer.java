package fr.paladium.palamod.modules.luckyblock.tileentity.christmas;

import fr.paladium.palamod.modules.luckyblock.blocks.models.ModelTrophyChristmas;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityTrophyChristmasRenderer extends TileEntitySpecialRenderer {
  private static ModelTrophyChristmas model = new ModelTrophyChristmas();
  
  public static ModelTrophyChristmas getModel() {
    return model;
  }
  
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/trophy/christmas/texture.png");
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityTrophyChristmas)te, x, y, z, ticks);
  }
  
  public void renderTileEntity() {
    func_147497_a(TileEntityRendererDispatcher.field_147556_a);
  }
  
  private void renderBlockTileEntityAt(TileEntityTrophyChristmas tile, double x, double y, double z, float ticks) {
    if ((Minecraft.func_71410_x()).field_71439_g == null)
      return; 
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
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
    String name = "§6Trophée de §b§l" + ((tile.getOwner() == null || tile.getOwner().isEmpty()) ? "§cpersonne" : tile.getOwner());
    int nameSize = (Minecraft.func_71410_x()).field_71466_p.func_78256_a(name);
    MovingObjectPosition lastPosition = (Minecraft.func_71410_x()).field_71439_g.func_70614_a(8.0D, 1.0F);
    if (lastPosition != null && MovingObjectPosition.MovingObjectType.BLOCK.equals(lastPosition.field_72313_a) && 
      tile.field_145851_c == lastPosition.field_72311_b && tile.field_145848_d == lastPosition.field_72312_c && tile.field_145849_e == lastPosition.field_72309_d)
      (Minecraft.func_71410_x()).field_71466_p.func_78276_b(name, -nameSize / 2, 0, 16777215); 
    GL11.glEnable(2896);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\christmas\TileEntityTrophyChristmasRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */