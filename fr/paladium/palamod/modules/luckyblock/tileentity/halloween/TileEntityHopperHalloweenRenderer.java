package fr.paladium.palamod.modules.luckyblock.tileentity.halloween;

import fr.paladium.palamod.modules.luckyblock.blocks.halloween.BlockHopperHalloween;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.Frustrum;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class TileEntityHopperHalloweenRenderer extends TileEntitySpecialRenderer {
  private static RenderBlocks instance;
  
  public void func_147500_a(TileEntity entity, double x, double y, double z, float partialtick) {
    if (instance == null)
      instance = new RenderBlocks((IBlockAccess)(Minecraft.func_71410_x()).field_71441_e); 
    Tessellator tessellator = Tessellator.field_78398_a;
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
    int r = 255;
    int g = 165;
    int b = 0;
    float s = 0.5F;
    frustrum.func_78547_a(viewX, viewY, viewZ);
    boolean seen = (entity.func_145831_w().func_72933_a(Vec3.func_72443_a(entity.field_145851_c, (entity.field_145848_d + 2), entity.field_145849_e), 
        Vec3.func_72443_a(cameraEntity.field_70165_t, cameraEntity.field_70163_u + cameraEntity
          .func_70047_e(), cameraEntity.field_70161_v)) == null);
    if (seen) {
      GL11.glPushMatrix();
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glEnable(3553);
      GL11.glEnable(2884);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glTranslated(x + 0.5D, y + 1.7D, z + 0.5D);
      GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-RenderManager.field_78727_a.field_78735_i, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(RenderManager.field_78727_a.field_78732_j, 1.0F, 0.0F, 0.0F);
      GL11.glScalef(-0.026666673F, -0.026666673F, 0.026666673F);
      GL11.glDisable(2896);
      GL11.glDepthMask(false);
      GL11.glDisable(3553);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      tessellator.func_78382_b();
      tessellator.func_78370_a(0, 0, 0, 255);
      tessellator.func_78377_a(-27.0D, -6.0D, 0.0D);
      tessellator.func_78377_a(-27.0D, 6.0D, 0.0D);
      tessellator.func_78377_a(27.0D, 6.0D, 0.0D);
      tessellator.func_78377_a(27.0D, -6.0D, 0.0D);
      tessellator.func_78381_a();
      s = 0.25F;
      GL11.glEnable(3553);
      GL11.glPushMatrix();
      GL11.glTranslatef(-size, -4.5F, 0.0F);
      GL11.glScalef(s, s, s);
      mc.field_71466_p.func_78276_b("Vous devez donner " + ((TileEntityHopperHalloween)entity)
          .getPaladiumNeeded() + " Paladium", -65, 12, 16777215);
      mc.field_71466_p
        .func_78276_b("Il vous reste " + (((TileEntityHopperHalloween)entity)
          .getTimer() - 
          LocalDateTime.now(ZoneId.of("UTC")).toEpochSecond(ZoneOffset.UTC)) + " secondes", -35, 22, 16777215);
      GL11.glDepthMask(true);
      GL11.glEnable(2896);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
      GL11.glPopMatrix();
      s = 0.5F;
    } 
  }
  
  public boolean renderBlockHopper(BlockHopperHalloween p_147803_1_, int p_147803_2_, int p_147803_3_, int p_147803_4_) {
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78380_c(p_147803_1_.func_149677_c(instance.field_147845_a, p_147803_2_, p_147803_3_, p_147803_4_));
    int l = p_147803_1_.func_149720_d(instance.field_147845_a, p_147803_2_, p_147803_3_, p_147803_4_);
    float f = (l >> 16 & 0xFF) / 255.0F;
    float f1 = (l >> 8 & 0xFF) / 255.0F;
    float f2 = (l & 0xFF) / 255.0F;
    if (EntityRenderer.field_78517_a) {
      float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
      float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
      float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
      f = f3;
      f1 = f4;
      f2 = f5;
    } 
    tessellator.func_78386_a(f, f1, f2);
    return renderBlockHopperMetadata(p_147803_1_, p_147803_2_, p_147803_3_, p_147803_4_, instance.field_147845_a
        .func_72805_g(p_147803_2_, p_147803_3_, p_147803_4_), false);
  }
  
  public boolean renderBlockHopperMetadata(BlockHopperHalloween p_147799_1_, int p_147799_2_, int p_147799_3_, int p_147799_4_, int p_147799_5_, boolean p_147799_6_) {
    Tessellator tessellator = Tessellator.field_78398_a;
    int i1 = BlockHopper.func_149918_b(p_147799_5_);
    double d0 = 0.625D;
    instance.func_147782_a(0.0D, d0, 0.0D, 1.0D, 1.0D, 1.0D);
    if (p_147799_6_) {
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
      instance.func_147768_a((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, instance
          .func_147787_a((Block)p_147799_1_, 0, p_147799_5_));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
      instance.func_147806_b((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, instance
          .func_147787_a((Block)p_147799_1_, 1, p_147799_5_));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
      instance.func_147761_c((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, instance
          .func_147787_a((Block)p_147799_1_, 2, p_147799_5_));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
      instance.func_147734_d((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, instance
          .func_147787_a((Block)p_147799_1_, 3, p_147799_5_));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
      instance.func_147798_e((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, instance
          .func_147787_a((Block)p_147799_1_, 4, p_147799_5_));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
      instance.func_147764_f((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, instance
          .func_147787_a((Block)p_147799_1_, 5, p_147799_5_));
      tessellator.func_78381_a();
    } else {
      instance.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
    } 
    if (!p_147799_6_) {
      tessellator.func_78380_c(p_147799_1_.func_149677_c(instance.field_147845_a, p_147799_2_, p_147799_3_, p_147799_4_));
      int j1 = p_147799_1_.func_149720_d(instance.field_147845_a, p_147799_2_, p_147799_3_, p_147799_4_);
      float f = (j1 >> 16 & 0xFF) / 255.0F;
      float f3 = (j1 >> 8 & 0xFF) / 255.0F;
      float f2 = (j1 & 0xFF) / 255.0F;
      if (EntityRenderer.field_78517_a) {
        float f6 = (f * 30.0F + f3 * 59.0F + f2 * 11.0F) / 100.0F;
        float f4 = (f * 30.0F + f3 * 70.0F) / 100.0F;
        float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
        f = f6;
        f3 = f4;
        f2 = f5;
      } 
      tessellator.func_78386_a(f, f3, f2);
    } 
    IIcon iicon = BlockHopperHalloween.getHopperIcon("hopper_outside");
    IIcon iicon1 = BlockHopperHalloween.getHopperIcon("hopper_inside");
    float f1 = 0.125F;
    if (p_147799_6_) {
      tessellator.func_78382_b();
      tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
      instance.func_147764_f((Block)p_147799_1_, (-1.0F + f1), 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
      instance.func_147798_e((Block)p_147799_1_, (1.0F - f1), 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
      instance.func_147734_d((Block)p_147799_1_, 0.0D, 0.0D, (-1.0F + f1), iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
      instance.func_147761_c((Block)p_147799_1_, 0.0D, 0.0D, (1.0F - f1), iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
      instance.func_147806_b((Block)p_147799_1_, 0.0D, -1.0D + d0, 0.0D, iicon1);
      tessellator.func_78381_a();
    } else {
      instance.func_147764_f((Block)p_147799_1_, (p_147799_2_ - 1.0F + f1), p_147799_3_, p_147799_4_, iicon);
      instance.func_147798_e((Block)p_147799_1_, (p_147799_2_ + 1.0F - f1), p_147799_3_, p_147799_4_, iicon);
      instance.func_147734_d((Block)p_147799_1_, p_147799_2_, p_147799_3_, (p_147799_4_ - 1.0F + f1), iicon);
      instance.func_147761_c((Block)p_147799_1_, p_147799_2_, p_147799_3_, (p_147799_4_ + 1.0F - f1), iicon);
      instance.func_147806_b((Block)p_147799_1_, p_147799_2_, (p_147799_3_ - 1.0F) + d0, p_147799_4_, iicon1);
    } 
    instance.func_147757_a(iicon);
    double d3 = 0.25D;
    double d4 = 0.25D;
    instance.func_147782_a(d3, d4, d3, 1.0D - d3, d0 - 0.002D, 1.0D - d3);
    if (p_147799_6_) {
      tessellator.func_78382_b();
      tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
      instance.func_147764_f((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
      instance.func_147798_e((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
      instance.func_147734_d((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
      instance.func_147761_c((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
      instance.func_147806_b((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
      instance.func_147768_a((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
    } else {
      instance.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
    } 
    if (!p_147799_6_) {
      double d1 = 0.375D;
      double d2 = 0.25D;
      instance.func_147757_a(iicon);
      if (i1 == 0) {
        instance.func_147782_a(d1, 0.0D, d1, 1.0D - d1, 0.25D, 1.0D - d1);
        instance.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
      } 
      if (i1 == 2) {
        instance.func_147782_a(d1, d4, 0.0D, 1.0D - d1, d4 + d2, d3);
        instance.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
      } 
      if (i1 == 3) {
        instance.func_147782_a(d1, d4, 1.0D - d3, 1.0D - d1, d4 + d2, 1.0D);
        instance.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
      } 
      if (i1 == 4) {
        instance.func_147782_a(0.0D, d4, d1, d3, d4 + d2, 1.0D - d1);
        instance.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
      } 
      if (i1 == 5) {
        instance.func_147782_a(1.0D - d3, d4, d1, 1.0D, d4 + d2, 1.0D - d1);
        instance.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
      } 
    } 
    instance.func_147771_a();
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\halloween\TileEntityHopperHalloweenRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */