package fr.paladium.palamod.modules.luckyblock.tileentity.halloween;

import fr.paladium.palamod.modules.luckyblock.blocks.models.ModelTrophyHalloween;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityTrophyHalloweenRenderer extends TileEntitySpecialRenderer {
  private static ModelTrophyHalloween model = new ModelTrophyHalloween();
  
  public static ModelTrophyHalloween getModel() {
    return model;
  }
  
  private static ResourceLocation[] textures = new ResourceLocation[4];
  
  public static ResourceLocation[] getTextures() {
    return textures;
  }
  
  public TileEntityTrophyHalloweenRenderer() {
    for (int i = 0; i < textures.length; i++)
      textures[i] = new ResourceLocation("palamod", "textures/blocks/trophy/halloween/TrophyTier" + i + ".png"); 
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityTrophyHalloween)te, x, y, z, ticks);
  }
  
  public void renderTileEntity() {
    func_147497_a(TileEntityRendererDispatcher.field_147556_a);
  }
  
  private void renderBlockTileEntityAt(TileEntityTrophyHalloween tile, double x, double y, double z, float ticks) {
    if ((Minecraft.func_71410_x()).field_71439_g == null)
      return; 
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    int meta = tile.func_145831_w().func_72805_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    func_147499_a(textures[(meta >= 0 && meta < textures.length) ? meta : 0]);
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\halloween\TileEntityTrophyHalloweenRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */