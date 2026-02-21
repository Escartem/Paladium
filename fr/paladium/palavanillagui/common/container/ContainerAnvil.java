package fr.paladium.palavanillagui.common.container;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palaforgeutils.lib.container.ForgeContainer;
import fr.paladium.palavanillagui.common.container.inventory.InventoryAnvilInput;
import fr.paladium.palavanillagui.common.container.inventory.InventoryAnvilOutput;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import org.apache.commons.lang3.StringUtils;

public class ContainerAnvil extends ForgeContainer {
  private final InventoryPlayer playerInv;
  
  private final InventoryAnvilInput inputSlots;
  
  private final InventoryAnvilOutput outputSlots;
  
  private final World world;
  
  private final int x;
  
  private final int y;
  
  private final int z;
  
  public InventoryPlayer getPlayerInv() {
    return this.playerInv;
  }
  
  public InventoryAnvilInput getInputSlots() {
    return this.inputSlots;
  }
  
  public InventoryAnvilOutput getOutputSlots() {
    return this.outputSlots;
  }
  
  public World getWorld() {
    return this.world;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getZ() {
    return this.z;
  }
  
  private String repairedItemName = "";
  
  public String getRepairedItemName() {
    return this.repairedItemName;
  }
  
  private int maximumCost = 0;
  
  public int getMaximumCost() {
    return this.maximumCost;
  }
  
  private int stackSizeToBeUsedInRepair = 0;
  
  public int getStackSizeToBeUsedInRepair() {
    return this.stackSizeToBeUsedInRepair;
  }
  
  public ContainerAnvil(InventoryPlayer playerInv, final World world, final int x, final int y, final int z) {
    this.playerInv = playerInv;
    this.inputSlots = new InventoryAnvilInput(this);
    this.outputSlots = new InventoryAnvilOutput();
    this.world = world;
    this.x = x;
    this.y = y;
    this.z = z;
    func_75146_a(new Slot((IInventory)this.inputSlots, 0, 0, 0));
    func_75146_a(new Slot((IInventory)this.inputSlots, 1, 0, 0));
    func_75146_a(new Slot((IInventory)this.outputSlots, 0, 0, 0) {
          public boolean func_75214_a(ItemStack p_75214_1_) {
            return false;
          }
          
          public boolean func_82869_a(EntityPlayer player) {
            return ((player.field_71075_bZ.field_75098_d || player.field_71068_ca >= ContainerAnvil.this.maximumCost) && ContainerAnvil.this.maximumCost > 0 && func_75216_d());
          }
          
          public void func_82870_a(EntityPlayer player, ItemStack p_82870_2_) {
            if (!player.field_71075_bZ.field_75098_d)
              player.func_82242_a(-ContainerAnvil.this.maximumCost); 
            float breakChance = ForgeHooks.onAnvilRepair(player, p_82870_2_, ContainerAnvil.this.inputSlots.func_70301_a(0), ContainerAnvil.this.inputSlots.func_70301_a(1));
            ContainerAnvil.this.inputSlots.func_70299_a(0, (ItemStack)null);
            if (ContainerAnvil.this.stackSizeToBeUsedInRepair > 0) {
              ItemStack itemstack1 = ContainerAnvil.this.inputSlots.func_70301_a(1);
              if (itemstack1 != null && itemstack1.field_77994_a > ContainerAnvil.this.stackSizeToBeUsedInRepair) {
                itemstack1.field_77994_a -= ContainerAnvil.this.stackSizeToBeUsedInRepair;
                ContainerAnvil.this.inputSlots.func_70299_a(1, itemstack1);
              } else {
                ContainerAnvil.this.inputSlots.func_70299_a(1, (ItemStack)null);
              } 
            } else {
              ContainerAnvil.this.inputSlots.func_70299_a(1, (ItemStack)null);
            } 
            ContainerAnvil.this.maximumCost = 0;
            if (!player.field_71075_bZ.field_75098_d && !world.field_72995_K && world.func_147439_a(x, y, z) == Blocks.field_150467_bQ && player.func_70681_au().nextFloat() < breakChance) {
              int i1 = world.func_72805_g(x, y, z);
              int k = i1 & 0x3;
              int l = i1 >> 2;
              l++;
              if (l > 2) {
                world.func_147468_f(x, y, z);
                world.func_72926_e(1020, x, y, z, 0);
              } else {
                world.func_72921_c(x, y, z, k | l << 2, 2);
                world.func_72926_e(1021, x, y, z, 0);
              } 
            } else if (!world.field_72995_K) {
              world.func_72926_e(1021, x, y, z, 0);
            } 
          }
        });
    for (int i = 0; i < 36; i++)
      func_75146_a(new Slot((IInventory)this.playerInv, i, 0, 0)); 
  }
  
  public int getPlayerInventoryPosition() {
    return 3;
  }
  
  public void func_75130_a(IInventory iventory) {
    super.func_75130_a(iventory);
    if (iventory == this.inputSlots)
      updateRepairOutput(); 
  }
  
  private void updateRepairOutput() {
    ItemStack itemstack = this.inputSlots.func_70301_a(0);
    this.maximumCost = 1;
    int i = 0;
    if (itemstack == null) {
      this.outputSlots.func_70299_a(0, null);
      this.maximumCost = 0;
    } else {
      ItemStack itemstack1 = itemstack.func_77946_l();
      ItemStack itemstack2 = this.inputSlots.func_70301_a(1);
      Map<Integer, Integer> map = EnchantmentHelper.func_82781_a(itemstack1);
      boolean flag7 = false;
      int cost = itemstack.func_82838_A() + ((itemstack2 == null) ? 0 : itemstack2.func_82838_A());
      this.stackSizeToBeUsedInRepair = 0;
      if (itemstack2 != null) {
        if (!onAnvilChange(itemstack, itemstack2, (IInventory)this.outputSlots, this.repairedItemName, cost))
          return; 
        flag7 = (itemstack2.func_77973_b() == Items.field_151134_bR && Items.field_151134_bR.func_92110_g(itemstack2).func_74745_c() > 0);
        if (itemstack1.func_77984_f() && itemstack1
          .func_77973_b().func_82789_a(itemstack, itemstack2)) {
          int j = Math.min(itemstack1.func_77960_j(), itemstack1.func_77958_k() / 4);
          if (j <= 0) {
            this.outputSlots.func_70299_a(0, null);
            this.maximumCost = 0;
            return;
          } 
          int k;
          for (k = 0; j > 0 && k < itemstack2.field_77994_a; k++) {
            int l = itemstack1.func_77960_j() - j;
            itemstack1.func_77964_b(l);
            i++;
            j = Math.min(itemstack1.func_77960_j(), itemstack1.func_77958_k() / 4);
          } 
          this.stackSizeToBeUsedInRepair = k;
        } else {
          if (!flag7 && (itemstack1.func_77973_b() != itemstack2.func_77973_b() || 
            !itemstack1.func_77984_f())) {
            this.outputSlots.func_70299_a(0, null);
            this.maximumCost = 0;
            return;
          } 
          if (itemstack1.func_77984_f() && !flag7) {
            int j = itemstack.func_77958_k() - itemstack.func_77960_j();
            int k = itemstack2.func_77958_k() - itemstack2.func_77960_j();
            int l = k + itemstack1.func_77958_k() * 12 / 100;
            int i1 = j + l;
            int j1 = itemstack1.func_77958_k() - i1;
            if (j1 < 0)
              j1 = 0; 
            if (j1 < itemstack1.func_77960_j()) {
              itemstack1.func_77964_b(j1);
              i += 2;
            } 
          } 
          Map<Integer, Integer> map1 = EnchantmentHelper.func_82781_a(itemstack2);
          Iterator<Integer> iterator1 = map1.keySet().iterator();
          while (iterator1.hasNext()) {
            int l = ((Integer)iterator1.next()).intValue();
            Enchantment enchantment = Enchantment.field_77331_b[l];
            if (enchantment != null) {
              int k2, j1 = map.containsKey(Integer.valueOf(l)) ? ((Integer)map.get(Integer.valueOf(l))).intValue() : 0;
              int k1 = ((Integer)map1.get(Integer.valueOf(l))).intValue();
              if (j1 == k1) {
                k2 = ++k1;
              } else {
                k2 = Math.max(k1, j1);
              } 
              k1 = k2;
              boolean flag8 = enchantment.func_92089_a(itemstack);
              if (this.playerInv.field_70458_d.field_71075_bZ.field_75098_d || itemstack.func_77973_b() == Items.field_151134_bR)
                flag8 = true; 
              Iterator<Integer> iterator = map.keySet().iterator();
              while (iterator.hasNext()) {
                int l1 = ((Integer)iterator.next()).intValue();
                Enchantment e2 = Enchantment.field_77331_b[l1];
                if (l1 != l && e2 != null && (
                  !enchantment.func_77326_a(e2) || !e2.func_77326_a(enchantment))) {
                  flag8 = false;
                  i++;
                } 
              } 
              if (flag8) {
                if (k1 > enchantment.func_77325_b())
                  k1 = enchantment.func_77325_b(); 
                map.put(Integer.valueOf(l), Integer.valueOf(k1));
                int j2 = 0;
                switch (enchantment.func_77324_c()) {
                  case 1:
                    j2 = 8;
                    break;
                  case 2:
                    j2 = 4;
                    break;
                  case 5:
                    j2 = 2;
                    break;
                  case 10:
                    j2 = 1;
                    break;
                } 
                if (flag7)
                  j2 = Math.max(1, j2 / 2); 
                i += j2 * k1;
              } 
            } 
          } 
        } 
      } 
      boolean wasFixed = (i > 0);
      boolean wasRenamed = false;
      if (StringUtils.isBlank(this.repairedItemName)) {
        if (itemstack.func_82837_s())
          itemstack1.func_135074_t(); 
      } else if (!this.repairedItemName.equals(itemstack.func_82833_r())) {
        itemstack1.func_151001_c(this.repairedItemName);
        itemstack1.field_77990_d.func_74775_l("display").func_74757_a("Renamed", true);
        i++;
        wasRenamed = true;
      } 
      this.maximumCost = (wasRenamed && !wasFixed) ? 1 : (cost + i);
      if (flag7 && !itemstack1.func_77973_b().isBookEnchantable(itemstack1, itemstack2))
        itemstack1 = null; 
      if (i <= 0)
        itemstack1 = null; 
      if (itemstack1 != null && wasFixed) {
        int repairCost = itemstack1.func_82838_A();
        if (itemstack2 != null && repairCost < itemstack2.func_82838_A())
          repairCost = itemstack2.func_82838_A(); 
        repairCost = repairCost * 2 + 1;
        itemstack1.func_82841_c(repairCost);
        EnchantmentHelper.func_82782_a(map, itemstack1);
      } 
      this.outputSlots.func_70299_a(0, itemstack1);
    } 
  }
  
  public void updateItemName(String name) {
    this.repairedItemName = name;
    if (func_75139_a(2).func_75216_d()) {
      ItemStack itemstack = func_75139_a(2).func_75211_c();
      if (StringUtils.isBlank(name)) {
        itemstack.func_135074_t();
      } else {
        itemstack.func_151001_c(this.repairedItemName);
        itemstack.field_77990_d.func_74775_l("display").func_74757_a("Renamed", true);
      } 
    } 
    updateRepairOutput();
  }
  
  public boolean onAnvilChange(ItemStack left, ItemStack right, IInventory outputSlot, String name, int baseCost) {
    AnvilUpdateEvent e = new AnvilUpdateEvent(left, right, name, baseCost);
    if (MinecraftForge.EVENT_BUS.post((Event)e))
      return false; 
    if (e.output == null)
      return true; 
    outputSlot.func_70299_a(0, e.output);
    this.maximumCost = e.cost;
    this.stackSizeToBeUsedInRepair = e.materialCost;
    return false;
  }
  
  public void onShiftClick(EntityPlayer player, int slotIndex, Slot slot, int defaultStackSize, ItemStack itemStack, IInventory target) {
    if (slot.field_75224_c.func_145825_b().equalsIgnoreCase(this.outputSlots.func_145825_b()))
      slot.func_82870_a(player, itemStack); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\common\container\ContainerAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */