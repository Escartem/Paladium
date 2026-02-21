package fr.paladium.palamod.modules.back2future.items;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.entities.EntityArmourStand;
import fr.paladium.palamod.modules.back2future.entities.Rotations;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ItemArmourStand extends Item implements IConfigurable {
  public ItemArmourStand() {
    func_77625_d(16);
    func_111206_d("wooden_armorstand");
    func_77655_b(Utils.getUnlocalisedName("wooden_armorstand"));
    func_77637_a(Back2Future.enableArmourStand ? Back2Future.creativeTab : null);
  }
  
  public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (side == 0)
      return false; 
    if (side == 1)
      y++; 
    if (side == 2)
      z--; 
    if (side == 3)
      z++; 
    if (side == 4)
      x--; 
    if (side == 5)
      x++; 
    if (!player.func_82247_a(x, y, z, side, stack))
      return false; 
    boolean flag1 = (!world.func_147437_c(x, y, z) && !world.func_147439_a(x, y, z).isReplaceable((IBlockAccess)world, x, y, z));
    int i = flag1 | ((!world.func_147437_c(x, y + 1, z) && !world.func_147439_a(x, y + 1, z).isReplaceable((IBlockAccess)world, x, y + 1, z)) ? 1 : 0);
    if (i != 0)
      return false; 
    double d0 = x;
    double d1 = y;
    double d2 = z;
    List<Entity> list = world.func_72839_b(null, 
        AxisAlignedBB.func_72330_a(d0, d1, d2, d0 + 1.0D, d1 + 2.0D, d2 + 1.0D));
    if (list.size() > 0)
      return false; 
    if (!world.field_72995_K) {
      world.func_147468_f(x, y, z);
      world.func_147468_f(x, y + 1, z);
      EntityArmourStand stand = new EntityArmourStand(world, d0 + 0.5D, d1, d2 + 0.5D);
      float f3 = MathHelper.func_76141_d((
          MathHelper.func_76142_g(player.field_70177_z - 180.0F) + 22.5F) / 45.0F) * 45.0F;
      stand.func_70012_b(d0 + 0.5D, d1, d2 + 0.5D, f3, 0.0F);
      applyRandomRotations(stand, world.field_73012_v);
      NBTTagCompound nbt = stack.func_77978_p();
      if (nbt != null && nbt.func_150297_b("EntityTag", 10)) {
        NBTTagCompound nbt1 = new NBTTagCompound();
        stand.func_70039_c(nbt1);
        merge(nbt1, nbt.func_74775_l("EntityTag"));
        stand.func_70020_e(nbt1);
      } 
      world.func_72838_d((Entity)stand);
    } 
    stack.field_77994_a--;
    return true;
  }
  
  private void applyRandomRotations(EntityArmourStand armorStand, Random rand) {
    Rotations rotations = armorStand.getHeadRotation();
    float f = rand.nextFloat() * 5.0F;
    float f1 = rand.nextFloat() * 20.0F - 10.0F;
    Rotations rotations1 = new Rotations(rotations.getX() + f, rotations.getY() + f1, rotations.getZ());
    armorStand.setHeadRotation(rotations1);
    rotations = armorStand.getBodyRotation();
    f = rand.nextFloat() * 10.0F - 5.0F;
    rotations1 = new Rotations(rotations.getX(), rotations.getY() + f, rotations.getZ());
    armorStand.setBodyRotation(rotations1);
  }
  
  public void merge(NBTTagCompound nbt, NBTTagCompound other) {
    Iterator<String> iterator = other.func_150296_c().iterator();
    while (iterator.hasNext()) {
      String s = iterator.next();
      NBTBase nbtbase = other.func_74781_a(s);
      if (nbtbase.func_74732_a() == 10) {
        if (nbt.func_150297_b(s, 10)) {
          NBTTagCompound nbttagcompound1 = nbt.func_74775_l(s);
          merge(nbttagcompound1, (NBTTagCompound)nbtbase);
          continue;
        } 
        nbt.func_74782_a(s, nbtbase.func_74737_b());
        continue;
      } 
      nbt.func_74782_a(s, nbtbase.func_74737_b());
    } 
  }
  
  public boolean isEnabled() {
    return Back2Future.enableArmourStand;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\ItemArmourStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */