package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.lib.ModSounds;
import fr.paladium.palamod.modules.back2future.lib.RenderIDs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class SlimeBlock extends Block implements IConfigurable {
  public SlimeBlock() {
    super(Material.field_151571_B);
    func_149711_c(0.0F);
    func_149658_d("slime");
    func_149672_a(ModSounds.soundSlime);
    func_149663_c(Utils.getUnlocalisedName("slime"));
    func_149647_a(Back2Future.enableSlimeBlock ? Back2Future.creativeTab : null);
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    float f = 0.125F;
    return AxisAlignedBB.func_72330_a(x, y, z, (x + 1), ((y + 1) - f), (z + 1));
  }
  
  public void func_149746_a(World world, int x, int y, int z, Entity entity, float fallDistance) {
    if (!entity.func_70093_af()) {
      entity.field_70143_R = 0.0F;
      if (entity.field_70181_x < 0.0D)
        entity.getEntityData().func_74780_a("palamod:slime", -entity.field_70181_x); 
    } 
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity entity) {
    NBTTagCompound data = entity.getEntityData();
    if (data.func_74764_b("palamod:slime")) {
      entity.field_70181_x = data.func_74769_h("palamod:slime");
      data.func_82580_o("palamod:slime");
    } 
    if (Math.abs(entity.field_70181_x) < 0.1D && !entity.func_70093_af()) {
      double d = 0.4D + Math.abs(entity.field_70181_x) * 0.2D;
      entity.field_70159_w *= d;
      entity.field_70179_y *= d;
    } 
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149701_w() {
    return 1;
  }
  
  public int func_149645_b() {
    return RenderIDs.SLIME_BLOCK;
  }
  
  public boolean isEnabled() {
    return Back2Future.enableSlimeBlock;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\SlimeBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */