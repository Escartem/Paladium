package fr.paladium.palamod.modules.luckyblock.tileentity;

import fr.paladium.palamod.modules.luckyblock.blocks.models.ModelAlarm;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityAlarmRenderer extends TileEntitySpecialRenderer {
  private static ModelAlarm model = new ModelAlarm();
  
  public static ModelAlarm getModel() {
    return model;
  }
  
  private static ResourceLocation[] textures = new ResourceLocation[2];
  
  public static ResourceLocation[] getTextures() {
    return textures;
  }
  
  private long time = 0L;
  
  public TileEntityAlarmRenderer() {
    textures[0] = new ResourceLocation("palamod", "textures/blocks/alarm/alarm_off.png");
    textures[1] = new ResourceLocation("palamod", "textures/blocks/alarm/alarm_on.png");
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityAlarm)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityAlarm tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    if (tile.func_145831_w().func_72864_z(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e)) {
      func_147499_a(textures[1]);
      if (this.time == 0L)
        Minecraft.func_71410_x().func_147118_V().func_147682_a(
            (ISound)PositionedSoundRecord.func_147675_a(new ResourceLocation("palamod", "alarm"), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e)); 
      this.time++;
      if (this.time >= 130L)
        this.time = 0L; 
    } else {
      func_147499_a(textures[0]);
    } 
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityAlarmRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */