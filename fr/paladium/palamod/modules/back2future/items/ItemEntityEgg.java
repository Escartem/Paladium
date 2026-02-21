package fr.paladium.palamod.modules.back2future.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.dispenser.DispenserBehaviourSpawnEgg;
import fr.paladium.palamod.modules.back2future.entities.ModEntityList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemEntityEgg extends Item {
  public ItemEntityEgg() {
    func_77627_a(true);
    func_77637_a(CreativeTabs.field_78026_f);
    func_77655_b(Utils.getUnlocalisedName("entity_egg"));
    BlockDispenser.field_149943_a.func_82595_a(this, new DispenserBehaviourSpawnEgg());
  }
  
  public String func_77653_i(ItemStack stack) {
    String s = ("" + StatCollector.func_74838_a(Items.field_151063_bx.func_77658_a() + ".name")).trim();
    String s1 = ModEntityList.getName(stack.func_77960_j());
    if (s1 != null)
      s = s + " " + StatCollector.func_74838_a("entity." + s1 + ".name"); 
    return s;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_82790_a(ItemStack stack, int pass) {
    ModEntityList.EntityData data = ModEntityList.getData(stack.func_77960_j());
    if (data == null)
      return 16777215; 
    return (pass == 0) ? data.eggColour1 : data.eggColour2;
  }
  
  public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return true; 
    Block block = world.func_147439_a(x, y, z);
    x += Facing.field_71586_b[side];
    y += Facing.field_71587_c[side];
    z += Facing.field_71585_d[side];
    double d0 = 0.0D;
    if (side == 1 && block.func_149645_b() == 11)
      d0 = 0.5D; 
    Entity entity = spawnEntity(world, stack.func_77960_j(), x + 0.5D, y + d0, z + 0.5D);
    if (entity != null) {
      if (entity instanceof net.minecraft.entity.EntityLivingBase && stack.func_82837_s())
        ((EntityLiving)entity).func_94058_c(stack.func_82833_r()); 
      if (!player.field_71075_bZ.field_75098_d)
        stack.field_77994_a--; 
    } 
    return true;
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return stack; 
    MovingObjectPosition movingobjectposition = func_77621_a(world, player, true);
    if (movingobjectposition == null)
      return stack; 
    if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
      int i = movingobjectposition.field_72311_b;
      int j = movingobjectposition.field_72312_c;
      int k = movingobjectposition.field_72309_d;
      if (!world.func_72962_a(player, i, j, k))
        return stack; 
      if (!player.func_82247_a(i, j, k, movingobjectposition.field_72310_e, stack))
        return stack; 
      if (world.func_147439_a(i, j, k) instanceof net.minecraft.block.BlockLiquid) {
        Entity entity = spawnEntity(world, stack.func_77960_j(), i, j, k);
        if (entity != null) {
          if (entity instanceof net.minecraft.entity.EntityLivingBase && stack.func_82837_s())
            ((EntityLiving)entity).func_94058_c(stack.func_82833_r()); 
          if (!player.field_71075_bZ.field_75098_d)
            stack.field_77994_a--; 
        } 
      } 
    } 
    return stack;
  }
  
  public static Entity spawnEntity(World world, int id, double x, double y, double z) {
    Entity entity = ModEntityList.createEntityByID(id, world);
    if (entity != null && entity instanceof net.minecraft.entity.EntityLivingBase) {
      EntityLiving entityliving = (EntityLiving)entity;
      entity.func_70012_b(x, y, z, 
          MathHelper.func_76142_g(world.field_73012_v.nextFloat() * 360.0F), 0.0F);
      entityliving.field_70759_as = entityliving.field_70177_z;
      entityliving.field_70761_aq = entityliving.field_70177_z;
      entityliving.func_110161_a((IEntityLivingData)null);
      world.func_72838_d(entity);
      entityliving.func_70642_aH();
    } 
    return entity;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77623_v() {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77618_c(int meta, int pass) {
    return Items.field_151063_bx.func_77618_c(meta, pass);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_150895_a(Item item, CreativeTabs tab, List<ItemStack> list) {
    for (ModEntityList.EntityData data : ModEntityList.getDatasWithEggs())
      list.add(new ItemStack(item, 1, data.id)); 
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister reg) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\ItemEntityEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */