package fr.paladium.palamod.modules.alchimiste.common.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityCauldronCore;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockCauldronCore extends BlockBase implements ITooltipWiki {
  public BlockCauldronCore(String name) {
    super(name);
  }
  
  public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
    return AxisAlignedBB.func_72330_a(p_149668_2_ + this.field_149759_B + 0.0012499999720603228D, p_149668_3_ + this.field_149760_C + 0.0012499999720603228D, p_149668_4_ + this.field_149754_D + 0.0012499999720603228D, p_149668_2_ + this.field_149755_E - 0.0012499999720603228D, p_149668_3_ + this.field_149756_F - 0.0012499999720603228D, p_149668_4_ + this.field_149757_G - 0.0012499999720603228D);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
    if (world.field_72995_K)
      return; 
    for (int xC = -3; xC < 4; xC++) {
      for (int zC = -3; zC < 4; zC++) {
        if (x + xC != x || z + zC != z)
          if (!isCauldronBlock(world, x + xC, y, z + zC))
            return;  
      } 
    } 
    int i;
    for (i = -2; i < 2; i++) {
      if (!isCauldronBlock(world, x + i, y + 1, z + 2))
        return; 
    } 
    for (i = -2; i < 2; i++) {
      if (!isCauldronBlock(world, x + i, y + 1, z - 2))
        return; 
    } 
    for (i = -2; i < 2; i++) {
      if (!isCauldronBlock(world, x + 2, y + 1, z + i))
        return; 
    } 
    for (i = -2; i < 2; i++) {
      if (!isCauldronBlock(world, x - 2, y + 1, z + i))
        return; 
    } 
    if (!world.field_72995_K)
      world.func_72921_c(x, y, z, 1, 2); 
    world.func_147449_b(x - 3, y, z, (Block)BlocksRegister.CAULDRON_TANKSUPPORT);
    world.func_147449_b(x + 3, y, z, (Block)BlocksRegister.CAULDRON_TANKSUPPORT);
    EntityLightningBolt bolt = new EntityLightningBolt(world, x, y, z);
    world.func_72838_d((Entity)bolt);
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
    TileEntityCauldronCore tile = (TileEntityCauldronCore)world.func_147438_o(x, y, z);
    if (tile != null && tile.isMultiBlockCorrect())
      tile.unmount(); 
    super.func_149749_a(world, x, y, z, block, meta);
  }
  
  public void func_149670_a(World w, int x, int y, int z, Entity e) {
    if (!(e instanceof EntityItem))
      return; 
    EntityItem item = (EntityItem)e;
    TileEntityCauldronCore tile = (TileEntityCauldronCore)w.func_147438_o(x, y, z);
    if (!tile.isSulfuricPresent() && item.func_92059_d().func_77973_b() == ItemsRegister.BUCKET_ANGELIC) {
      boolean test = true;
      int xc;
      for (xc = -1; xc < 2; xc++) {
        for (int zc = -1; zc < 2; zc++) {
          if (w.func_147439_a(x + xc, y + 1, z + zc) != Blocks.field_150350_a && w.func_147439_a(x + xc, y + 1, z + zc) != BlocksRegister.CAULDRON_CORE)
            test = false; 
        } 
      } 
      if (test)
        for (xc = -1; xc < 2; xc++) {
          for (int zc = -1; zc < 2; zc++)
            w.func_147449_b(x + xc, y + 1, z + zc, (Block)BlocksRegister.FLUIDS_ANGELICWATER); 
        }  
      w.func_72900_e((Entity)item);
      w.func_72869_a("hugeexplosion", x, y, z, 1.0D, 0.0D, 0.0D);
      w.func_72908_a(x, y, z, "random.explode", 4.0F, (1.0F + (w.field_73012_v
          .nextFloat() - w.field_73012_v.nextFloat()) * 0.2F) * 0.7F);
      for (xc = -1; xc < 3; xc++) {
        for (int zc = -1; zc < 3; zc++)
          w.func_72869_a("flame", (x + xc), (y + 2), (z + zc), 0.0D, 0.0D, 0.0D); 
      } 
    } 
  }
  
  public AxisAlignedBB func_149633_g(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_) {
    return AxisAlignedBB.func_72330_a(p_149633_2_ + this.field_149759_B + 0.0012499999720603228D, p_149633_3_ + this.field_149760_C + 0.0012499999720603228D, p_149633_4_ + this.field_149754_D + 0.0012499999720603228D, p_149633_2_ + this.field_149755_E - 0.0012499999720603228D, p_149633_3_ + this.field_149756_F - 0.0012499999720603228D, p_149633_4_ + this.field_149757_G - 0.0012499999720603228D);
  }
  
  public int func_149692_a(int p_149692_1_) {
    return p_149692_1_;
  }
  
  protected void func_149642_a(World p_149642_1_, int p_149642_2_, int p_149642_3_, int p_149642_4_, ItemStack p_149642_5_) {
    if (!p_149642_1_.field_72995_K && p_149642_1_.func_82736_K().func_82766_b("doTileDrops") && !p_149642_1_.restoringBlockSnapshots) {
      if (((Boolean)this.captureDrops.get()).booleanValue()) {
        ((List<ItemStack>)this.capturedDrops.get()).add(p_149642_5_);
        return;
      } 
      float f = 0.7F;
      double d0 = (p_149642_1_.field_73012_v.nextFloat() * 0.7F) + 0.15000000596046448D;
      double d1 = (p_149642_1_.field_73012_v.nextFloat() * 0.7F) + 0.15000000596046448D;
      double d2 = (p_149642_1_.field_73012_v.nextFloat() * 0.7F) + 0.15000000596046448D;
      EntityItem entityitem = new EntityItem(p_149642_1_, p_149642_2_ + d0, p_149642_3_ + d1, p_149642_4_ + d2, new ItemStack((Block)BlocksRegister.CAULDRON_CORE, 1, 0));
      entityitem.field_145804_b = 10;
      p_149642_1_.func_72838_d((Entity)entityitem);
    } 
  }
  
  public boolean isCauldronBlock(World world, int x, int y, int z) {
    return (world.func_147439_a(x, y, z) == BlocksRegister.CAULDRON_BLOCK || world
      .func_147439_a(x, y, z) instanceof fr.paladium.palamod.modules.alchimiste.common.utils.impl.BlockTankSupport);
  }
  
  public boolean isAngelicWater(World world, int x, int y, int z) {
    return (world.func_147439_a(x, y, z) == BlocksRegister.FLUIDS_ANGELICWATER);
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityCauldronCore();
  }
  
  public Class<? extends TileEntity> getTileEntity() {
    return (Class)TileEntityCauldronCore.class;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/autour-du-chaudron#1.-les-composants-du-chaudron";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\blocks\BlockCauldronCore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */