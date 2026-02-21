package fr.paladium.palamod.modules.luckyblock.tileentity.easter;

import fr.paladium.palamod.modules.luckyblock.blocks.models.easter.ModelEasterTrophy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityEasterTrophyRenderer extends TileEntitySpecialRenderer {
  private static ModelEasterTrophy model = new ModelEasterTrophy();
  
  public static ModelEasterTrophy getModel() {
    return model;
  }
  
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/trophy/easter/easter_trophy.png");
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityEasterTrophy)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityEasterTrophy tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
    if (tile.getOwner() != null) {
      GL11.glPushMatrix();
      GL11.glTranslated(x + 0.5D, y + 1.600000023841858D, z + 0.5D);
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\easter\TileEntityEasterTrophyRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */