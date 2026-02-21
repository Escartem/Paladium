package fr.paladium.palaautomation.client.renderer.tile;

import fr.paladium.palaautomation.client.model.ModelGiver;
import fr.paladium.palaautomation.common.tile.EnumPipeFacing;
import fr.paladium.palaautomation.common.tile.impl.TileEntityGiver;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityGiverRenderer extends TileEntitySpecialRenderer {
  public static final ModelGiver MODEL = new ModelGiver();
  
  public static final ResourceLocation TEXTURE = new ResourceLocation("palaautomation", "textures/blocks/giver.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityGiver)te, x, y, z, ticks);
  }
  
  private void render(double x, double y, double z, float rotX, float rotY, float rotZ) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y - 0.5D, z + 0.5D);
    GL11.glRotatef(rotX, 1.0F, 0.0F, 0.0F);
    GL11.glRotatef(rotY, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(rotZ, 0.0F, 0.0F, 1.0F);
    func_147499_a(TEXTURE);
    MODEL.renderAll();
    GL11.glPopMatrix();
  }
  
  private void renderBlockTileEntityAt(TileEntityGiver tile, double x, double y, double z, float ticks) {
    switch (tile.getFacing()) {
      case NORTH:
        render(x, y, z, 0.0F, 180.0F, 0.0F);
        break;
      case EAST:
        render(x, y, z, 0.0F, 90.0F, 0.0F);
        break;
      case SOUTH:
        render(x, y, z, 0.0F, 0.0F, 0.0F);
        break;
      case WEST:
        render(x, y, z, 0.0F, 270.0F, 0.0F);
        break;
      case UP:
        render(x, y + 1.0D, z + 1.0D, -90.0F, 0.0F, 0.0F);
        break;
      case DOWN:
        render(x, y + 1.0D, z - 1.0D, 90.0F, 0.0F, 0.0F);
        break;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\client\renderer\tile\TileEntityGiverRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */