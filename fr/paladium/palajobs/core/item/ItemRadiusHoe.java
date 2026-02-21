package fr.paladium.palajobs.core.item;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palajobs.core.achievements.PalaJobsFertilizedDirtHoeAchievement;
import fr.paladium.palajobs.core.registry.ItemsRegistry;
import fr.paladium.palajobs.core.utils.IRepairable;
import fr.paladium.palajobs.utils.forge.location.Location;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class ItemRadiusHoe extends ItemHoe implements IRepairable {
  private final double radius;
  
  public double getRadius() {
    return this.radius;
  }
  
  public ItemRadiusHoe(int maxDamage, int radius, String unlocalizedName) {
    super(Item.ToolMaterial.EMERALD);
    this.radius = radius;
    func_77655_b(unlocalizedName);
    func_111206_d("palajobs:" + unlocalizedName);
    func_77656_e(maxDamage);
  }
  
  public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    Block clicked = world.func_147439_a(x, y, z);
    if ((!clicked.equals(Blocks.field_150349_c) && !clicked.equals(Blocks.field_150346_d) && !clicked.equals(Blocks.field_150458_ak)) || !player.func_82247_a(x, y, z, side, stack))
      return false; 
    if (world.field_72995_K)
      return true; 
    int rf = (int)(this.radius / 2.0D);
    for (int x0 = rf * -1; x0 < rf; x0++) {
      for (int z0 = rf * -1; z0 < rf; z0++) {
        Location location = new Location(world, (x + x0), y, (z + z0));
        if (player.func_82247_a((int)location.getX(), (int)location.getY(), (int)location.getZ(), side, stack)) {
          UseHoeEvent event = new UseHoeEvent(player, stack, world, (int)location.getX(), (int)location.getY(), (int)location.getZ());
          MinecraftForge.EVENT_BUS.post((Event)event);
          if (!event.isCanceled())
            PalaJobsFertilizedDirtHoeAchievement.performCheck(player, 1); 
        } 
      } 
    } 
    stack.func_77972_a(1, (EntityLivingBase)player);
    return true;
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    return false;
  }
  
  public int getRepair(ItemStack item) {
    if (!item.func_77942_o() || !item.func_77978_p().func_74764_b("repaired")) {
      NBTTagCompound tag;
      if (!item.func_77942_o()) {
        tag = new NBTTagCompound();
      } else {
        tag = item.func_77978_p();
      } 
      tag.func_74768_a("repaired", getMaxRepair(item));
      item.func_77982_d(tag);
    } 
    return item.func_77978_p().func_74762_e("repaired");
  }
  
  public void repair(ItemStack item, ItemStack ring, EntityLivingBase entity) {
    if (!item.func_77942_o() || !item.func_77978_p().func_74764_b("repaired")) {
      NBTTagCompound tag;
      if (!item.func_77942_o()) {
        tag = new NBTTagCompound();
      } else {
        tag = item.func_77978_p();
      } 
      tag.func_74768_a("repaired", getMaxRepair(item));
      item.func_77982_d(tag);
    } 
    if (item.func_77960_j() > 0 && ring.func_77960_j() < ring.func_77958_k() && 
      getRepair(item) > 0) {
      ring.func_77972_a(1, entity);
      item.func_77964_b(item.func_77960_j() - 1);
      item.func_77978_p().func_74768_a("repaired", item.func_77978_p().func_74762_e("repaired") - 1);
    } 
  }
  
  public void repair(ItemStack item, ItemStack ring) {
    if (!item.func_77942_o() || !item.func_77978_p().func_74764_b("repaired")) {
      NBTTagCompound tag;
      if (!item.func_77942_o()) {
        tag = new NBTTagCompound();
      } else {
        tag = item.func_77978_p();
      } 
      tag.func_74768_a("repaired", getMaxRepair(item));
      item.func_77982_d(tag);
    } 
    if (item.func_77960_j() > 0 && ring.func_77960_j() < ring.func_77958_k() && 
      getRepair(item) > 0) {
      damageItem(ring, 1);
      item.func_77964_b(item.func_77960_j() - 1);
      if (item.func_77960_j() <= 0)
        item.func_77978_p().func_74768_a("repaired", item.func_77978_p().func_74762_e("repaired") - 1); 
    } 
  }
  
  public void damageItem(ItemStack item, int value) {
    if (item.func_77984_f() && 
      item.func_96631_a(value, new Random())) {
      item.field_77994_a--;
      if (item.field_77994_a < 0)
        item.field_77994_a = 0; 
      item.func_77964_b(0);
    } 
  }
  
  public boolean isInChest() {
    return true;
  }
  
  public int getMaxRepair(ItemStack item) {
    return (ItemsRegistry.ENDIUM_RADIUS_HOE.equals(item.func_77973_b()) || ItemsRegistry.PALADIUM_RADIUS_HOE.equals(item.func_77973_b())) ? 3 : 0;
  }
  
  public boolean isEndium() {
    return equals(ItemsRegistry.ENDIUM_RADIUS_HOE);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\item\ItemRadiusHoe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */