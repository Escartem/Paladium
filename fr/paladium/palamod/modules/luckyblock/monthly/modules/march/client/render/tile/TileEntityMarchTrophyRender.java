package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.tile;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.model.block.MarchTrophyModel;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.tile.TileEntityMarchTrophy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityMarchTrophyRender extends TileEntitySpecialRenderer {
  public static MarchTrophyModel getModel() {
    return model;
  }
  
  private static final MarchTrophyModel model = new MarchTrophyModel();
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  private static final ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/march_trophy.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityMarchTrophy)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityMarchTrophy tile, double x, double y, double z, float ticks) {
    int meta = tile.func_145831_w().func_72805_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    float scale = 0.9F;
    switch (meta) {
      case 3:
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 1.35D, z + 0.5D);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(scale, scale, scale);
        func_147499_a(texture);
        model.renderAll();
        GL11.glPopMatrix();
        break;
      case 4:
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 1.35D, z + 0.5D);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(scale, scale, scale);
        func_147499_a(texture);
        model.renderAll();
        GL11.glPopMatrix();
        break;
      case 5:
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 1.35D, z + 0.5D);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(scale, scale, scale);
        func_147499_a(texture);
        model.renderAll();
        GL11.glPopMatrix();
        break;
      default:
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 1.35D, z + 0.5D);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(scale, scale, scale);
        func_147499_a(texture);
        model.renderAll();
        GL11.glPopMatrix();
        break;
    } 
    if (tile.getOwner() != null) {
      GL11.glPushMatrix();
      GL11.glTranslated(x + 0.5D, y + 1.2000000476837158D, z + 0.5D);
      GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
      GL11.glRotated((Minecraft.func_71410_x()).field_71439_g.field_70177_z, 0.0D, 1.0D, 0.0D);
      GL11.glScaled(0.0925D, 0.0925D, 0.0925D);
      GL11.glScaled(0.3D, 0.3D, 0.3D);
      GL11.glDisable(2896);
      String name = "§d" + tile.getOwner();
      int nameSize = (Minecraft.func_71410_x()).field_71466_p.func_78256_a(name);
      (Minecraft.func_71410_x()).field_71466_p.func_78276_b(name, -nameSize / 2, 0, 16777215);
      GL11.glEnable(2896);
      GL11.glPopMatrix();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\render\tile\TileEntityMarchTrophyRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */