package fr.paladium.palamod.modules.luckyblock.renders;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.entity.halloween.EntityPumpkinGolem;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderPumpkinGolem extends RenderLiving {
  private static final ResourceLocation snowManTextures = new ResourceLocation("palamod", "textures/entity/pumpkingolem.png");
  
  private ModelSnowMan snowmanModel;
  
  private static final String __OBFID = "CL_00001025";
  
  public RenderPumpkinGolem() {
    super((ModelBase)new ModelSnowMan(), 0.5F);
    this.snowmanModel = (ModelSnowMan)this.field_77045_g;
    func_77042_a((ModelBase)this.snowmanModel);
  }
  
  protected void renderEquippedItems(EntityPumpkinGolem p_77029_1_, float p_77029_2_) {
    super.func_77029_c((EntityLivingBase)p_77029_1_, p_77029_2_);
    if (p_77029_1_.hasPumpkin()) {
      ItemStack itemstack = new ItemStack(Blocks.field_150423_aK, 1);
      if (itemstack.func_77973_b() instanceof net.minecraft.item.ItemBlock) {
        GL11.glPushMatrix();
        this.snowmanModel.field_78195_c.func_78794_c(0.0625F);
        IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
        boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D));
        if (is3D || 
          RenderBlocks.func_147739_a(Block.func_149634_a(itemstack.func_77973_b()).func_149645_b())) {
          float f1 = 0.625F;
          GL11.glTranslatef(0.0F, -0.34375F, 0.0F);
          GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
          GL11.glScalef(f1, -f1, f1);
        } 
        this.field_76990_c.field_78721_f.func_78443_a((EntityLivingBase)p_77029_1_, itemstack, 0);
        GL11.glPopMatrix();
      } 
    } 
  }
  
  protected ResourceLocation getEntityTexture(EntityPumpkinGolem p_110775_1_) {
    return snowManTextures;
  }
  
  protected void func_77029_c(EntityLivingBase p_77029_1_, float p_77029_2_) {
    renderEquippedItems((EntityPumpkinGolem)p_77029_1_, p_77029_2_);
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return getEntityTexture((EntityPumpkinGolem)p_110775_1_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderPumpkinGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */