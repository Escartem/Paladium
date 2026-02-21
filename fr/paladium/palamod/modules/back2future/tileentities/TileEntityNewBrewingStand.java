package fr.paladium.palamod.modules.back2future.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.ModItems;
import fr.paladium.palamod.modules.back2future.recipes.BrewingFuelRegistry;
import fr.paladium.palamod.modules.luckyblock.items.black.ItemFlasque;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionHelper;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraftforge.event.ForgeEventFactory;

public class TileEntityNewBrewingStand extends TileEntityBrewingStand {
  private static final int[] TOP_SLOTS = new int[] { 3 };
  
  private static final int[] SIDE_SLOTS = new int[] { 0, 1, 2 };
  
  private ItemStack[] inventory = new ItemStack[5];
  
  private int brewTime;
  
  private int prevFilledSlots;
  
  private Item ingredientID;
  
  private int fuel;
  
  private int currentFuel;
  
  public int func_70302_i_() {
    return this.inventory.length;
  }
  
  public void func_145845_h() {
    if (this.fuel <= 0 && this.inventory[4] != null) {
      this.fuel = this.currentFuel = BrewingFuelRegistry.getBrewAmount(this.inventory[4]);
      if (--(this.inventory[4]).field_77994_a <= 0)
        this.inventory[4] = this.inventory[4].func_77973_b().hasContainerItem(this.inventory[4]) ? this.inventory[4]
          .func_77973_b().getContainerItem(this.inventory[4]) : null; 
      func_70296_d();
    } 
    if (this.brewTime > 0) {
      this.brewTime--;
      if (this.brewTime == 0) {
        brewPotions();
        func_70296_d();
      } else if (!canBrew()) {
        this.brewTime = 0;
        func_70296_d();
      } else if (this.ingredientID != this.inventory[3].func_77973_b()) {
        this.brewTime = 0;
        func_70296_d();
      } 
    } else if (canBrew()) {
      this.brewTime = 400;
      this.ingredientID = this.inventory[3].func_77973_b();
    } 
    int filledSlots = func_145939_j();
    if (filledSlots != this.prevFilledSlots) {
      this.prevFilledSlots = filledSlots;
      this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, filledSlots, 2);
    } 
  }
  
  public int func_145935_i() {
    return this.brewTime;
  }
  
  private boolean canBrew() {
    if (this.fuel > 0 && this.inventory[3] != null && (this.inventory[3]).field_77994_a > 0) {
      ItemStack itemstack = this.inventory[3];
      if (!itemstack.func_77973_b().func_150892_m(itemstack))
        return false; 
      if (itemstack.func_77973_b() == ModItems.dragon_breath) {
        for (int j = 0; j < 3; j++) {
          if (this.inventory[j] != null && this.inventory[j].func_77973_b() == Items.field_151068_bn && 
            ItemPotion.func_77831_g(this.inventory[j].func_77960_j()))
            return true; 
        } 
        return false;
      } 
      boolean flag = false;
      for (int i = 0; i < 3; i++) {
        if (this.inventory[i] != null && this.inventory[i].func_77973_b() instanceof ItemPotion)
          if (this.inventory[i].func_77973_b() instanceof ItemFlasque) {
            if (ItemFlasque.isEmpty(this.inventory[i]))
              return false; 
            int j = ItemFlasque.getFlasquePotionEffects(this.inventory[i]);
            int k = applyIngredient(j, itemstack);
            if (!ItemPotion.func_77831_g(j) && ItemPotion.func_77831_g(k)) {
              flag = false;
              break;
            } 
            List<?> list = Items.field_151068_bn.func_77834_f(j);
            List<?> list1 = Items.field_151068_bn.func_77834_f(k);
            if ((j <= 0 || list != list1) && (list == null || (
              !list.equals(list1) && list1 != null)) && j != k) {
              flag = true;
              break;
            } 
          } else {
            int j = this.inventory[i].func_77960_j();
            int k = applyIngredient(j, itemstack);
            if (!ItemPotion.func_77831_g(j) && ItemPotion.func_77831_g(k)) {
              flag = true;
              break;
            } 
            List<?> list = Items.field_151068_bn.func_77834_f(j);
            List<?> list1 = Items.field_151068_bn.func_77834_f(k);
            if ((j <= 0 || list != list1) && (list == null || (
              !list.equals(list1) && list1 != null)) && j != k) {
              flag = true;
              break;
            } 
          }  
      } 
      return flag;
    } 
    return false;
  }
  
  private void brewPotions() {
    if (ForgeEventFactory.onPotionAttemptBreaw(new ItemStack[] { this.inventory[0], this.inventory[1], this.inventory[2], this.inventory[3] }))
      return; 
    if (canBrew()) {
      for (int i = 0; i < 3; i++) {
        if (this.inventory[i] != null && this.inventory[i].func_77973_b() instanceof ItemPotion)
          if (ItemPotion.func_77831_g(this.inventory[i].func_77960_j()) && this.inventory[3]
            .func_77973_b() == ModItems.dragon_breath) {
            this.inventory[i] = new ItemStack(ModItems.lingering_potion, (this.inventory[i]).field_77994_a, this.inventory[i]
                .func_77960_j());
          } else {
            ItemStack currentPotion = this.inventory[i];
            if (currentPotion.func_77973_b() instanceof ItemFlasque) {
              int j = ItemFlasque.getFlasquePotionEffects(currentPotion);
              int k = applyIngredient(j, this.inventory[3]);
              List list = Items.field_151068_bn.func_77834_f(j);
              List list1 = Items.field_151068_bn.func_77834_f(k);
              if ((j <= 0 || list != list1) && (list == null || (
                !list.equals(list1) && list1 != null)) && j != k) {
                ItemFlasque.setFlasquePotionEffects(currentPotion, k);
                ItemFlasque.setFlasqueEmpty(currentPotion, false);
              } 
            } else {
              int j = currentPotion.func_77960_j();
              int k = applyIngredient(j, this.inventory[3]);
              List list = Items.field_151068_bn.func_77834_f(j);
              List list1 = Items.field_151068_bn.func_77834_f(k);
              if ((j <= 0 || list != list1) && (list == null || (
                !list.equals(list1) && list1 != null))) {
                if (j != k)
                  currentPotion.func_77964_b(k); 
              } else if (!ItemPotion.func_77831_g(j) && ItemPotion.func_77831_g(k)) {
                currentPotion.func_77964_b(k);
              } 
            } 
          }  
      } 
      boolean hasContainerItem = this.inventory[3].func_77973_b().hasContainerItem(this.inventory[3]);
      if (--(this.inventory[3]).field_77994_a <= 0) {
        this.inventory[3] = hasContainerItem ? this.inventory[3]
          .func_77973_b().getContainerItem(this.inventory[3]) : null;
      } else if (hasContainerItem && !this.field_145850_b.field_72995_K) {
        float f = 0.7F;
        double x = (this.field_145850_b.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
        double y = (this.field_145850_b.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
        double z = (this.field_145850_b.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
        EntityItem entityitem = new EntityItem(this.field_145850_b, this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z, this.inventory[3].func_77973_b().getContainerItem(this.inventory[3]));
        entityitem.field_145804_b = 10;
        this.field_145850_b.func_72838_d((Entity)entityitem);
      } 
      this.fuel--;
      ForgeEventFactory.onPotionBrewed(new ItemStack[] { this.inventory[0], this.inventory[1], this.inventory[2], this.inventory[3] });
      this.field_145850_b.func_72980_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, "palamod:block.brewing_stand.brew", 1.0F, 1.0F, true);
    } 
  }
  
  private int applyIngredient(int meta, ItemStack stack) {
    return (stack == null) ? meta : (
      stack.func_77973_b().func_150892_m(stack) ? 
      PotionHelper.func_77913_a(meta, stack.func_77973_b().func_150896_i(stack)) : meta);
  }
  
  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
    this.inventory = new ItemStack[func_70302_i_()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
      NBTTagCompound nbt1 = nbttaglist.func_150305_b(i);
      byte b0 = nbt1.func_74771_c("Slot");
      if (b0 >= 0 && b0 < this.inventory.length)
        this.inventory[b0] = ItemStack.func_77949_a(nbt1); 
    } 
    this.brewTime = nbt.func_74765_d("BrewTime");
    if (nbt.func_150297_b("Fuel", 2)) {
      this.fuel = nbt.func_74765_d("Fuel");
      if (this.fuel > 0)
        this.currentFuel = 30; 
    } else {
      this.fuel = nbt.func_74762_e("Fuel");
      this.currentFuel = nbt.func_74762_e("CurrentFuel");
    } 
  }
  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74777_a("BrewTime", (short)this.brewTime);
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.inventory.length; i++) {
      if (this.inventory[i] != null) {
        NBTTagCompound nbt1 = new NBTTagCompound();
        nbt1.func_74774_a("Slot", (byte)i);
        this.inventory[i].func_77955_b(nbt1);
        nbttaglist.func_74742_a((NBTBase)nbt1);
      } 
    } 
    nbt.func_74782_a("Items", (NBTBase)nbttaglist);
    nbt.func_74768_a("Fuel", this.fuel);
    nbt.func_74768_a("CurrentFuel", this.currentFuel);
  }
  
  public ItemStack func_70301_a(int slot) {
    return (slot >= 0 && slot < this.inventory.length) ? this.inventory[slot] : null;
  }
  
  public ItemStack func_70298_a(int slot, int size) {
    if (slot >= 0 && slot < this.inventory.length) {
      ItemStack itemstack = this.inventory[slot];
      this.inventory[slot] = null;
      return itemstack;
    } 
    return null;
  }
  
  public ItemStack func_70304_b(int slot) {
    if (slot >= 0 && slot < this.inventory.length) {
      ItemStack itemstack = this.inventory[slot];
      this.inventory[slot] = null;
      return itemstack;
    } 
    return null;
  }
  
  public void func_70299_a(int slot, ItemStack stack) {
    if (slot >= 0 && slot < this.inventory.length)
      this.inventory[slot] = stack; 
  }
  
  public boolean func_94041_b(int slot, ItemStack stack) {
    if (slot == 4)
      return (stack.func_77973_b() == Items.field_151065_br); 
    if (slot == 3)
      return stack.func_77973_b().func_150892_m(stack); 
    return (stack.func_77973_b() instanceof ItemPotion || stack.func_77973_b() == Items.field_151069_bo);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_145938_d(int brewTime) {
    this.brewTime = brewTime;
  }
  
  public int func_145939_j() {
    int i = 0;
    for (int j = 0; j < 3; j++) {
      if (this.inventory[j] != null)
        i |= 1 << j; 
    } 
    return i;
  }
  
  public int[] func_94128_d(int side) {
    return (side == 1) ? TOP_SLOTS : SIDE_SLOTS;
  }
  
  public int getFuel() {
    return this.fuel;
  }
  
  public int getCurrentFuel() {
    return this.currentFuel;
  }
  
  public void setFuel(int fuel) {
    this.fuel = fuel;
  }
  
  public void setCurrentFuel(int currentFuel) {
    this.currentFuel = currentFuel;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\tileentities\TileEntityNewBrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */