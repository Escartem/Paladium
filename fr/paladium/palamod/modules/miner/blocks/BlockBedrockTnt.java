package fr.paladium.palamod.modules.miner.blocks;

import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.miner.entity.EntityBedrockTNTPrimed;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBedrockTnt extends BlockTNT {
  private final IIcon[] icons = new IIcon[2];
  
  public BlockBedrockTnt() {
    func_149647_a((CreativeTabs)Registry.MINER_TAB);
    func_149663_c("bedrock_tnt");
    func_149658_d("palamod:bedrock_tnt");
    func_149711_c(5.0F);
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a(func_149641_N() + "_side");
    this.icons[1] = register.func_94245_a(func_149641_N() + "_top");
    this.field_149761_L = this.icons[0];
  }
  
  public IIcon func_149673_e(IBlockAccess ba, int x, int y, int z, int dir) {
    if (dir == 0 || dir == 1)
      return this.icons[1]; 
    return this.icons[0];
  }
  
  public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
    if (p_149691_1_ == 0 || p_149691_1_ == 1)
      return this.icons[1]; 
    return this.icons[0];
  }
  
  public void func_150114_a(World world, int x, int y, int z, int metadata, EntityLivingBase placer) {
    if (!world.field_72995_K && (
      metadata & 0x1) == 1) {
      EntityBedrockTNTPrimed entitytntprimed = new EntityBedrockTNTPrimed(world, x + 0.5D, y + 0.5D, z + 0.5D, placer);
      world.func_72838_d((Entity)entitytntprimed);
      world.func_72956_a((Entity)entitytntprimed, "game.tnt.primed", 1.0F, 1.0F);
    } 
  }
  
  public void func_149723_a(World world, int x, int y, int z, Explosion explosion) {
    if (!world.field_72995_K) {
      EntityBedrockTNTPrimed entitytntprimed = new EntityBedrockTNTPrimed(world, x + 0.5D, y + 0.5D, z + 0.5D, explosion.func_94613_c());
      entitytntprimed.field_70516_a = world.field_73012_v.nextInt(entitytntprimed.field_70516_a / 4) + entitytntprimed.field_70516_a / 8;
      world.func_72838_d((Entity)entitytntprimed);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\blocks\BlockBedrockTnt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */