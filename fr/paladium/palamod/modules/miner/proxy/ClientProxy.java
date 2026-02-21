package fr.paladium.palamod.modules.miner.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.palamod.modules.miner.blocks.BlockPaladiumHopper;
import fr.paladium.palamod.modules.miner.dimminer.client.render.BlockEndiumCaveRenderer;
import fr.paladium.palamod.modules.miner.dimminer.client.render.RenderMinerBoss;
import fr.paladium.palamod.modules.miner.dimminer.common.block.tileentity.TileEntityEndiumCaveBlock;
import fr.paladium.palamod.modules.miner.dimminer.common.entity.EntityMinerBoss;
import fr.paladium.palamod.modules.miner.entity.EntityBedrockTNTPrimed;
import fr.paladium.palamod.modules.miner.entity.EntityGodVillager;
import fr.paladium.palamod.modules.miner.render.RenderBedrockPrimedTNT;
import fr.paladium.palamod.modules.miner.render.RenderGodVillager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class ClientProxy extends CommonProxy {
  public static int renderPaladiumHopper;
  
  public static int renderEndiumCaveBlock;
  
  public static boolean inMinage;
  
  public void registerRenders() {
    renderPaladiumHopper = RenderingRegistry.getNextAvailableRenderId();
    renderEndiumCaveBlock = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler(renderPaladiumHopper, new ISimpleBlockRenderingHandler() {
          public boolean shouldRender3DInInventory(int modelId) {
            return false;
          }
          
          public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
            ClientProxy.this.renderBlockPaladiumHopper(renderer, (BlockPaladiumHopper)block, x, y, z);
            return true;
          }
          
          public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}
          
          public int getRenderId() {
            return ClientProxy.renderPaladiumHopper;
          }
        });
    RenderingRegistry.registerEntityRenderingHandler(EntityBedrockTNTPrimed.class, (Render)new RenderBedrockPrimedTNT());
    RenderingRegistry.registerEntityRenderingHandler(EntityGodVillager.class, (Render)new RenderGodVillager());
    RenderingRegistry.registerEntityRenderingHandler(EntityMinerBoss.class, (Render)new RenderMinerBoss());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEndiumCaveBlock.class, (TileEntitySpecialRenderer)new BlockEndiumCaveRenderer());
  }
  
  public boolean renderBlockPaladiumHopper(RenderBlocks renderer, BlockPaladiumHopper p_147803_1_, int p_147803_2_, int p_147803_3_, int p_147803_4_) {
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78380_c(p_147803_1_.func_149677_c(renderer.field_147845_a, p_147803_2_, p_147803_3_, p_147803_4_));
    int l = p_147803_1_.func_149720_d(renderer.field_147845_a, p_147803_2_, p_147803_3_, p_147803_4_);
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
    return renderBlockPaladiumHopperMetadata(renderer, p_147803_1_, p_147803_2_, p_147803_3_, p_147803_4_, renderer.field_147845_a.func_72805_g(p_147803_2_, p_147803_3_, p_147803_4_), false);
  }
  
  public boolean renderBlockPaladiumHopperMetadata(RenderBlocks renderer, BlockPaladiumHopper p_147799_1_, int p_147799_2_, int p_147799_3_, int p_147799_4_, int p_147799_5_, boolean p_147799_6_) {
    Tessellator tessellator = Tessellator.field_78398_a;
    int i1 = BlockHopper.func_149918_b(p_147799_5_);
    double d0 = 0.625D;
    renderer.func_147782_a(0.0D, 0.625D, 0.0D, 1.0D, 1.0D, 1.0D);
    if (p_147799_6_) {
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
      renderer.func_147768_a((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, renderer.func_147787_a((Block)p_147799_1_, 0, p_147799_5_));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
      renderer.func_147806_b((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, renderer.func_147787_a((Block)p_147799_1_, 1, p_147799_5_));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
      renderer.func_147761_c((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, renderer.func_147787_a((Block)p_147799_1_, 2, p_147799_5_));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
      renderer.func_147734_d((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, renderer.func_147787_a((Block)p_147799_1_, 3, p_147799_5_));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
      renderer.func_147798_e((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, renderer.func_147787_a((Block)p_147799_1_, 4, p_147799_5_));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
      renderer.func_147764_f((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, renderer.func_147787_a((Block)p_147799_1_, 5, p_147799_5_));
      tessellator.func_78381_a();
    } else {
      renderer.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
    } 
    if (!p_147799_6_) {
      tessellator.func_78380_c(p_147799_1_.func_149677_c(renderer.field_147845_a, p_147799_2_, p_147799_3_, p_147799_4_));
      int j1 = p_147799_1_.func_149720_d(renderer.field_147845_a, p_147799_2_, p_147799_3_, p_147799_4_);
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
    IIcon iicon = BlockPaladiumHopper.getHopperIcon("paladium_hopper_outside");
    IIcon iicon1 = BlockPaladiumHopper.getHopperIcon("paladium_hopper_inside");
    float f1 = 0.125F;
    if (p_147799_6_) {
      tessellator.func_78382_b();
      tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
      renderer.func_147764_f((Block)p_147799_1_, (-1.0F + f1), 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
      renderer.func_147798_e((Block)p_147799_1_, (1.0F - f1), 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
      renderer.func_147734_d((Block)p_147799_1_, 0.0D, 0.0D, (-1.0F + f1), iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
      renderer.func_147761_c((Block)p_147799_1_, 0.0D, 0.0D, (1.0F - f1), iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
      renderer.func_147806_b((Block)p_147799_1_, 0.0D, -0.375D, 0.0D, iicon1);
      tessellator.func_78381_a();
    } else {
      renderer.func_147764_f((Block)p_147799_1_, (p_147799_2_ - 1.0F + f1), p_147799_3_, p_147799_4_, iicon);
      renderer.func_147798_e((Block)p_147799_1_, (p_147799_2_ + 1.0F - f1), p_147799_3_, p_147799_4_, iicon);
      renderer.func_147734_d((Block)p_147799_1_, p_147799_2_, p_147799_3_, (p_147799_4_ - 1.0F + f1), iicon);
      renderer.func_147761_c((Block)p_147799_1_, p_147799_2_, p_147799_3_, (p_147799_4_ + 1.0F - f1), iicon);
      renderer.func_147806_b((Block)p_147799_1_, p_147799_2_, (p_147799_3_ - 1.0F) + 0.625D, p_147799_4_, iicon1);
    } 
    renderer.func_147757_a(iicon);
    double d3 = 0.25D;
    double d4 = 0.25D;
    renderer.func_147782_a(0.25D, 0.25D, 0.25D, 0.75D, 0.623D, 0.75D);
    if (p_147799_6_) {
      tessellator.func_78382_b();
      tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
      renderer.func_147764_f((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
      renderer.func_147798_e((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
      renderer.func_147734_d((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
      renderer.func_147761_c((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
      renderer.func_147806_b((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
      renderer.func_147768_a((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
    } else {
      renderer.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
    } 
    if (!p_147799_6_) {
      double d1 = 0.375D;
      double d2 = 0.25D;
      renderer.func_147757_a(iicon);
      if (i1 == 0) {
        renderer.func_147782_a(0.375D, 0.0D, 0.375D, 0.625D, 0.25D, 0.625D);
        renderer.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
      } 
      if (i1 == 2) {
        renderer.func_147782_a(0.375D, 0.25D, 0.0D, 0.625D, 0.5D, 0.25D);
        renderer.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
      } 
      if (i1 == 3) {
        renderer.func_147782_a(0.375D, 0.25D, 0.75D, 0.625D, 0.5D, 1.0D);
        renderer.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
      } 
      if (i1 == 4) {
        renderer.func_147782_a(0.0D, 0.25D, 0.375D, 0.25D, 0.5D, 0.625D);
        renderer.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
      } 
      if (i1 == 5) {
        renderer.func_147782_a(0.75D, 0.25D, 0.375D, 1.0D, 0.5D, 0.625D);
        renderer.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
      } 
    } 
    renderer.func_147771_a();
    return true;
  }
  
  public boolean isMinerDimension() {
    return (CommonModule.getInstance().getConfig().getServerType() == ServerType.DIM_MINER);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\proxy\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */