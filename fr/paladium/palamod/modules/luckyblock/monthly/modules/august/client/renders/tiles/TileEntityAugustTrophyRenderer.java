package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.tiles;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.models.blocks.ModelAugustTrophy;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityAugustTrophy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityAugustTrophyRenderer extends TileEntitySpecialRenderer {
  private static final String TROPHY_TEXT = "§eTrophée de§7: §d";
  
  public static ModelAugustTrophy getModel() {
    return model;
  }
  
  private static ModelAugustTrophy model = new ModelAugustTrophy();
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/trophy/august/august_trophy.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderText((TileEntityAugustTrophy)te, x, y, z, ticks);
    renderBlockTileEntityAt((TileEntityAugustTrophy)te, x, y, z, ticks);
  }
  
  private void renderText(TileEntityAugustTrophy tile, double x, double y, double z, float ticks) {
    if ((Minecraft.func_71410_x()).field_71439_g.func_70011_f(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e) > 10.0D)
      return; 
    String ownerName = (tile.getOwnerName() == null) ? "§cInconnu" : tile.getOwnerName();
    String label = "§eTrophée de§7: §d" + ownerName;
    FontRenderer fr = (Minecraft.func_71410_x()).field_71466_p;
    RenderManager renderManager = RenderManager.field_78727_a;
    float scale = 0.02666667F;
    GL11.glPushMatrix();
    GL11.glTranslatef((float)x + 0.25F, (float)y + 0.8F, (float)z + 0.5F);
    GL11.glNormal3f(0.0F, 1.0F, 0.0F);
    GL11.glRotatef(-renderManager.field_78735_i, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(renderManager.field_78732_j, 1.0F, 0.0F, 0.0F);
    GL11.glScalef(-scale, -scale, scale);
    GL11.glDisable(2896);
    GL11.glDepthMask(false);
    GL11.glDisable(2929);
    GL11.glEnable(3042);
    OpenGlHelper.func_148821_a(770, 771, 1, 0);
    float playerNameHeight = 40.0F;
    fr.func_78261_a("§7" + label, -fr.func_78256_a(label) / 2, (int)-playerNameHeight, -1);
    GL11.glEnable(2896);
    GL11.glEnable(2929);
    GL11.glDisable(3042);
    GL11.glDepthMask(true);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glPopMatrix();
  }
  
  private void renderBlockTileEntityAt(TileEntityAugustTrophy tile, double x, double y, double z, float ticks) {
    float scale = 1.1F;
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.65D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    func_147499_a(texture);
    GL11.glScalef(scale, scale, scale);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\client\renders\tiles\TileEntityAugustTrophyRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */