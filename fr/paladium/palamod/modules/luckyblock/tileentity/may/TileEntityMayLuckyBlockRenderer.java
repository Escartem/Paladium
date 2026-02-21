package fr.paladium.palamod.modules.luckyblock.tileentity.may;

import fr.paladium.palamod.modules.luckyblock.blocks.models.may.ModelMayLuckyBlock;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityMayLuckyBlockRenderer extends TileEntitySpecialRenderer {
  private static ModelMayLuckyBlock model = new ModelMayLuckyBlock();
  
  public static ModelMayLuckyBlock getModel() {
    return model;
  }
  
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/may_lucky_block.png");
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityMayLuckyBlock)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityMayLuckyBlock tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\may\TileEntityMayLuckyBlockRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */