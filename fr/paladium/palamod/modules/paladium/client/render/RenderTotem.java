package fr.paladium.palamod.modules.paladium.client.render;

import fr.paladium.palamod.modules.paladium.client.model.ModelTotem;
import fr.paladium.palamod.modules.paladium.common.logics.TileEntityTotem;
import fr.paladium.zephyrui.lib.color.Color;
import java.awt.Color;
import javax.vecmath.Vector3f;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.Frustrum;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class RenderTotem extends TileEntitySpecialRenderer {
  private static ResourceLocation TEXTURE = new ResourceLocation("palamod:textures/models/totem.png");
  
  private static ResourceLocation TEXTURE_LAYER = new ResourceLocation("palamod:textures/models/totem_layer.png");
  
  private final ModelTotem model = new ModelTotem();
  
  public void func_147500_a(TileEntity entity, double x, double y, double z, float partialtick) {
    if (!(entity instanceof TileEntityTotem))
      return; 
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    TileEntityTotem totem = (TileEntityTotem)entity;
    ItemStack fuel = totem.getFuel();
    float hue = Math.max(0.0F, ((fuel == null) ? 0.0F : (fuel.field_77994_a / 64.0F)) / 3.0F - 0.07F);
    Color color = Color.getHSBColor(hue, 1.0F, 0.7F);
    Tessellator tessellator = Tessellator.field_78398_a;
    GL11.glPushMatrix();
    GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glRotatef((90 * entity.func_145832_p()), 0.0F, 1.0F, 0.0F);
    func_147499_a(TEXTURE);
    this.model.func_78088_a(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    func_147499_a(TEXTURE_LAYER);
    Color defaultColor = new Color(color.getRGB());
    Color greenColor = new Color(37, 226, 81);
    defaultColor.to(greenColor, totem.getTimer() / 60.0F).bind();
    this.model.func_78088_a(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glPopMatrix();
    if (totem.getTimer() > 0 && !totem.blockList.isEmpty())
      for (Vector3f vec : totem.blockList) {
        int blockX = (int)vec.x;
        int blockY = (int)vec.y;
        int blockZ = (int)vec.z;
        Block block = totem.func_145831_w().func_147439_a(blockX, blockY, blockZ);
        if (!(block instanceof fr.paladium.palamod.modules.paladium.common.blocks.BlockConnectedDirt))
          continue; 
        GL11.glEnable(3042);
        OpenGlHelper.func_148821_a(770, 771, 1, 0);
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
        GL11.glLineWidth(4.0F);
        GL11.glDisable(3553);
        GL11.glDepthMask(false);
        float f1 = 0.002F;
        block.func_149719_a((IBlockAccess)totem.func_145831_w(), blockX, blockY, blockZ);
        double d0 = ((EntityPlayer)entityClientPlayerMP).field_70142_S + (((EntityPlayer)entityClientPlayerMP).field_70165_t - ((EntityPlayer)entityClientPlayerMP).field_70142_S) * partialtick;
        double d1 = ((EntityPlayer)entityClientPlayerMP).field_70137_T + (((EntityPlayer)entityClientPlayerMP).field_70163_u - ((EntityPlayer)entityClientPlayerMP).field_70137_T) * partialtick;
        double d2 = ((EntityPlayer)entityClientPlayerMP).field_70136_U + (((EntityPlayer)entityClientPlayerMP).field_70161_v - ((EntityPlayer)entityClientPlayerMP).field_70136_U) * partialtick;
        RenderGlobal.func_147590_a(block.func_149633_g(((EntityPlayer)entityClientPlayerMP).field_70170_p, blockX, blockY, blockZ).func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-d0, -d1, -d2), greenColor.getRGB());
        GL11.glDepthMask(true);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
      }  
    Minecraft mc = Minecraft.func_71410_x();
    Frustrum frustrum = new Frustrum();
    EntityLivingBase cameraEntity = mc.field_71451_h;
    double viewX = cameraEntity.field_70142_S + (cameraEntity.field_70165_t - cameraEntity.field_70142_S) * partialtick;
    double viewY = cameraEntity.field_70137_T + (cameraEntity.field_70163_u - cameraEntity.field_70137_T) * partialtick;
    double viewZ = cameraEntity.field_70136_U + (cameraEntity.field_70161_v - cameraEntity.field_70136_U) * partialtick;
    int size = 10;
    int bgHeight = 6;
    float padding = 2.0F;
    float scale = 0.026666673F;
    int barHeight = 40;
    float s = 0.5F;
    frustrum.func_78547_a(viewX, viewY, viewZ);
    MovingObjectPosition mop = (Minecraft.func_71410_x()).field_71476_x;
    if (mop == null)
      return; 
    if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && mop.field_72311_b == entity.field_145851_c && mop.field_72312_c == entity.field_145848_d && mop.field_72309_d == entity.field_145849_e) {
      float healthSize = 40.0F * ((fuel != null) ? fuel.field_77994_a : 0.0F) / 64.0F;
      GL11.glPushMatrix();
      GL11.glTranslated(x + 0.6D, y + 1.7D, z + 0.6D);
      GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-RenderManager.field_78727_a.field_78735_i, 0.0F, 1.0F, 0.0F);
      GL11.glTranslated(-0.6D, 0.0D, -0.6D);
      GL11.glScalef(-0.026666673F, -0.026666673F, 0.026666673F);
      GL11.glDisable(2896);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      GL11.glDisable(3553);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      tessellator.func_78382_b();
      tessellator.func_78370_a(0, 0, 0, 150);
      tessellator.func_78377_a(-10.0D, -6.0D, 0.0D);
      tessellator.func_78377_a(-10.0D, 42.0D, 0.0D);
      tessellator.func_78377_a(10.0D, 42.0D, 0.0D);
      tessellator.func_78377_a(10.0D, -6.0D, 0.0D);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78370_a(127, 127, 127, 127);
      tessellator.func_78377_a(-5.0D, 0.0D, 0.0D);
      tessellator.func_78377_a(-5.0D, 40.0D, 0.0D);
      tessellator.func_78377_a(5.0D, 40.0D, 0.0D);
      tessellator.func_78377_a(5.0D, 0.0D, 0.0D);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78370_a(color.getRed(), color.getGreen(), color.getBlue(), 127);
      tessellator.func_78377_a(5.0D, 40.0D, 0.0D);
      tessellator.func_78377_a(5.0D, 40.0D - healthSize, 0.0D);
      tessellator.func_78377_a(-5.0D, 40.0D - healthSize, 0.0D);
      tessellator.func_78377_a(-5.0D, 40.0D, 0.0D);
      tessellator.func_78381_a();
      GL11.glEnable(3553);
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, -4.5F, 0.0F);
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      if (fuel != null) {
        String str = "x" + fuel.field_77994_a;
        mc.field_71466_p.func_78276_b(str, -(mc.field_71466_p.func_78256_a(str) / 2), 0, 16777215);
      } 
      GL11.glDepthMask(true);
      GL11.glEnable(2929);
      GL11.glDisable(3042);
      GL11.glEnable(2896);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
      GL11.glPopMatrix();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\RenderTotem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */