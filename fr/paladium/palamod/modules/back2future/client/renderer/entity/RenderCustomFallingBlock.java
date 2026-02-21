package fr.paladium.palamod.modules.back2future.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.asgard.GlStateManager;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityCustomFallingBlock;
import fr.paladium.palamod.util.IBlockState;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderCustomFallingBlock extends Render {
  private static final String __OBFID = "CL_00000994";
  
  public void doRender(EntityCustomFallingBlock p_180557_1_, double p_180557_2_, double p_180557_4_, double p_180557_6_, float p_180557_8_, float p_180557_9_) {
    if (p_180557_1_.getBlock() != null) {
      func_110776_a(TextureMap.field_110575_b);
      IBlockState iblockstate = p_180557_1_.getBlock();
      Block block = iblockstate.getBlock();
      if (block.func_149688_o() != Material.field_151579_a && !(block instanceof net.minecraft.block.BlockStaticLiquid)) {
        BlockPos blockpos = new BlockPos((Entity)p_180557_1_);
        World world = p_180557_1_.getWorldObj();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)p_180557_2_, (float)p_180557_4_, (float)p_180557_6_);
        GL11.glRotatef(p_180557_1_.rotateY, 1.0F, 0.0F, 0.0F);
        GlStateManager.disableLighting();
        GlStateManager.enableNormalize();
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        int i = blockpos.getX();
        int j = blockpos.getY();
        int k = blockpos.getZ();
        tessellator.func_78373_b((-i - 0.5F), -j, (-k - 0.5F));
        GL11.glRotatef(-p_180557_8_, 0.0F, 1.0F, 0.0F);
        (RenderBlocks.getInstance()).field_147845_a = (IBlockAccess)world;
        RenderBlocks.getInstance().func_147769_a(block, i, j, k);
        (RenderBlocks.getInstance()).field_147845_a = null;
        tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
        tessellator.func_78381_a();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
      } 
    } 
  }
  
  protected ResourceLocation getEntityTexture(EntityCustomFallingBlock entity) {
    return TextureMap.field_110575_b;
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return getEntityTexture((EntityCustomFallingBlock)entity);
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
    doRender((EntityCustomFallingBlock)entity, x, y, z, p_76986_8_, partialTicks);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\entity\RenderCustomFallingBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */