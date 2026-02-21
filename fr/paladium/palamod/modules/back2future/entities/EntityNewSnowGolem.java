package fr.paladium.palamod.modules.back2future.entities;

import java.util.ArrayList;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class EntityNewSnowGolem extends EntitySnowman implements IShearable {
  private static final int HAS_PUMPKIN = 12;
  
  public EntityNewSnowGolem(World world) {
    super(world);
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(12, Byte.valueOf((byte)1));
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    this.field_70180_af.func_75692_b(12, Byte.valueOf(nbt.func_74767_n("HasPumpkin") ? 1 : 0));
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74757_a("HasPumpkin", (this.field_70180_af.func_75683_a(12) == 1));
  }
  
  public boolean isShearable(ItemStack stack, IBlockAccess world, int x, int y, int z) {
    return hasPumpkin();
  }
  
  public ArrayList<ItemStack> onSheared(ItemStack stack, IBlockAccess world, int x, int y, int z, int fortune) {
    ArrayList<ItemStack> list = new ArrayList<>();
    list.add(new ItemStack(Blocks.field_150423_aK));
    this.field_70180_af.func_75692_b(12, Byte.valueOf((byte)0));
    return list;
  }
  
  public boolean hasPumpkin() {
    return (this.field_70180_af.func_75683_a(12) == 1);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityNewSnowGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */