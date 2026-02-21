package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.tiles;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.models.blocks.plant.IModelPlant;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.models.blocks.plant.ModelFifthCorruptedPlant;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.models.blocks.plant.ModelFirstCorruptedPlant;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.models.blocks.plant.ModelFourthCorruptedPlant;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.models.blocks.plant.ModelSecondCorruptedPlant;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.models.blocks.plant.ModelThirdCorruptedPlant;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityCorruptedPlant;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityCorruptedPlantRenderer extends TileEntitySpecialRenderer {
  public static final ModelBase[] MODELS = new ModelBase[] { (ModelBase)new ModelFirstCorruptedPlant(), (ModelBase)new ModelSecondCorruptedPlant(), (ModelBase)new ModelThirdCorruptedPlant(), (ModelBase)new ModelFourthCorruptedPlant(), (ModelBase)new ModelFifthCorruptedPlant() };
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  private static final ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/corrupted_plant.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityCorruptedPlant)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityCorruptedPlant tile, double x, double y, double z, float ticks) {
    float scale = 1.3F;
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.9D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glScalef(scale, scale, scale);
    func_147499_a(texture);
    getModel(tile).renderAll();
    GL11.glPopMatrix();
  }
  
  public IModelPlant getModel(TileEntityCorruptedPlant tile) {
    int meta = tile.func_145832_p();
    if (meta < 0 || meta >= MODELS.length)
      return (IModelPlant)MODELS[0]; 
    return (IModelPlant)MODELS[meta];
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\client\renders\tiles\TileEntityCorruptedPlantRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */