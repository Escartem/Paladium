package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.tiles;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.models.blocks.ModelCorruptedHunterPlant;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityHunterCorruptedPlant;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityHunterCorruptedPlantRenderer extends TileEntitySpecialRenderer {
  public static final ModelCorruptedHunterPlant MODEL = new ModelCorruptedHunterPlant();
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  private static final ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/corrupted_hunter_plant.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityHunterCorruptedPlant)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityHunterCorruptedPlant tile, double x, double y, double z, float ticks) {
    float scale = 0.6F;
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 0.9D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glScalef(scale, scale, scale);
    func_147499_a(texture);
    MODEL.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\client\renders\tiles\TileEntityHunterCorruptedPlantRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */