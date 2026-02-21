package fr.paladium.palamod.modules.ajobs.client.renders.blocks;

import fr.paladium.palamod.modules.ajobs.client.renders.models.ModelJobThrone;
import fr.paladium.palamod.modules.ajobs.common.tiles.TileEntityHunterThrone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class TileEntityHunterThroneRenderer extends TileEntitySpecialRenderer {
  private static ModelJobThrone model = new ModelJobThrone();
  
  public static ModelJobThrone getModel() {
    return model;
  }
  
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/thrones/hunter.png");
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityHunterThrone)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityHunterThrone tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    int meta = tile.func_145831_w().func_72805_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    ForgeDirection direction = ForgeDirection.getOrientation(meta & 0x3).getOpposite();
    switch (direction) {
      case NORTH:
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 1.0F);
        break;
      case SOUTH:
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        break;
      case UP:
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        break;
      case DOWN:
        GL11.glRotatef(180.0F, -1.0F, 0.0F, 1.0F);
        break;
      default:
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        break;
    } 
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
    if (tile.getOwner() != null) {
      GL11.glPushMatrix();
      GL11.glTranslated(x + 0.5D, y + 3.200000047683716D, z + 0.5D);
      GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
      GL11.glRotated((Minecraft.func_71410_x()).field_71439_g.field_70177_z, 0.0D, 1.0D, 0.0D);
      GL11.glScaled(0.0625D, 0.0625D, 0.0625D);
      GL11.glScaled(0.3D, 0.3D, 0.3D);
      GL11.glDisable(2896);
      String name = "§b" + tile.getOwner();
      int nameSize = (Minecraft.func_71410_x()).field_71466_p.func_78256_a(name);
      (Minecraft.func_71410_x()).field_71466_p.func_78276_b(name, -nameSize / 2, 0, 16777215);
      GL11.glEnable(2896);
      GL11.glPopMatrix();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\client\renders\blocks\TileEntityHunterThroneRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */