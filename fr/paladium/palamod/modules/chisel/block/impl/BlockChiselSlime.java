package fr.paladium.palamod.modules.chisel.block.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.chisel.block.BlockChisel;
import fr.paladium.palamod.modules.paladium.registerer.PRegisterer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockChiselSlime extends BlockChisel {
  public BlockChiselSlime(Material material, float hardness, String texture) {
    super(material, hardness, texture);
    func_149713_g(3);
    func_149672_a(PRegisterer.SLIME_STEP);
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149701_w() {
    return 1;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity entity) {
    if (entity.field_70181_x < 0.0D) {
      if (entity.field_70181_x < -0.07999999821186066D) {
        BlockChiselSlime blockChiselSlime = this;
        world.func_72908_a((x + 0.5F), (y + 0.5F), (z + 0.5F), ((Block)blockChiselSlime).field_149762_H.field_150501_a, ((Block)blockChiselSlime).field_149762_H
            .func_150497_c() / 2.0F, ((Block)blockChiselSlime).field_149762_H.func_150494_d() * 0.65F);
      } 
      entity.field_70181_x *= -1.2000000476837158D;
    } 
    entity.field_70143_R = 0.0F;
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return AxisAlignedBB.func_72330_a(x, y, z, x + 1.0D, y + 0.625D, z + 1.0D);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\block\impl\BlockChiselSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */