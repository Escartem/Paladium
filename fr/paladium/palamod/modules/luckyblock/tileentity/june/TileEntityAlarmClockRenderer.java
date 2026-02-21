package fr.paladium.palamod.modules.luckyblock.tileentity.june;

import fr.paladium.palamod.modules.luckyblock.blocks.models.june.ModelAlarmClock;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityAlarmClockRenderer extends TileEntitySpecialRenderer {
  private static ModelAlarmClock model = new ModelAlarmClock();
  
  public static ModelAlarmClock getModel() {
    return model;
  }
  
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/alarm_clock.png");
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityAlarmClock)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityAlarmClock tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\june\TileEntityAlarmClockRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */