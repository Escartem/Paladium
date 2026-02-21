package fr.paladium.palamod.modules.luckyblock.tileentity.may;

import fr.paladium.palamod.modules.luckyblock.blocks.models.may.ModelMayTrophy;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityMayTrophyRenderer extends TileEntitySpecialRenderer {
  private static ModelMayTrophy model = new ModelMayTrophy();
  
  public static ModelMayTrophy getModel() {
    return model;
  }
  
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/trophy/may/may_trophy.png");
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityMayTrophy)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityMayTrophy tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\may\TileEntityMayTrophyRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */