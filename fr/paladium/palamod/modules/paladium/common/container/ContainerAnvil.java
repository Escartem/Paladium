package fr.paladium.palamod.modules.paladium.common.container;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContainerAnvil extends Container {
  private static final Logger logger = LogManager.getLogger();
  
  private IInventory outputSlot = (IInventory)new InventoryCraftResult();
  
  private IInventory inputSlots = (IInventory)new InventoryBasic("Repair", true, 2) {
      private static final String __OBFID = "CL_00001733";
      
      public void func_70296_d() {
        super.func_70296_d();
        ContainerAnvil.this.func_75130_a((IInventory)this);
      }
    };
  
  private World world;
  
  private int x;
  
  private int y;
  
  private int z;
  
  public int maximumCost;
  
  public int stackSizeToBeUsedInRepair;
  
  private String repairedItemName;
  
  private final EntityPlayer thePlayer;
  
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
  
  public ContainerAnvil(InventoryPlayer inventoryPlayer, final World world, final int x, final int y, final int z, EntityPlayer player) {
    this.world = world;
    this.x = x;
    this.y = y;
    this.z = z;
    this.thePlayer = player;
    func_75146_a(new Slot(this.inputSlots, 0, 27, 47));
    func_75146_a(new Slot(this.inputSlots, 1, 76, 47));
    func_75146_a(new Slot(this.outputSlot, 2, 134, 47) {
          public boolean func_75214_a(ItemStack p_75214_1_) {
            return false;
          }
          
          public boolean func_82869_a(EntityPlayer p_82869_1_) {
            return ((p_82869_1_.field_71075_bZ.field_75098_d || p_82869_1_.field_71068_ca >= ContainerAnvil.this.maximumCost) && ContainerAnvil.this.maximumCost > 0 && 
              
              func_75216_d());
          }
          
          public void func_82870_a(EntityPlayer p_82870_1_, ItemStack p_82870_2_) {
            if (!p_82870_1_.field_71075_bZ.field_75098_d)
              p_82870_1_.func_82242_a(-ContainerAnvil.this.maximumCost); 
            float breakChance = ForgeHooks.onAnvilRepair(p_82870_1_, p_82870_2_, ContainerAnvil.this
                .inputSlots.func_70301_a(0), ContainerAnvil.this
                .inputSlots.func_70301_a(1));
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
            if (!p_82870_1_.field_71075_bZ.field_75098_d && !world.field_72995_K && world
              .func_147439_a(x, y, z) instanceof fr.paladium.palamod.modules.paladium.common.blocks.BlockAnvil && p_82870_1_
              
              .func_70681_au().nextFloat() < breakChance) {
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
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)inventoryPlayer, i, 8 + i * 18, 142)); 
  }
  
  public void func_75130_a(IInventory p_75130_1_) {
    super.func_75130_a(p_75130_1_);
    if (p_75130_1_ == this.inputSlots)
      updateRepairOutput(); 
  }
  
  public boolean onAnvilChange(ContainerAnvil container, ItemStack left, ItemStack right, IInventory outputSlot, String name, int baseCost) {
    AnvilUpdateEvent e = new AnvilUpdateEvent(left, right, name, baseCost);
    if (MinecraftForge.EVENT_BUS.post((Event)e))
      return false; 
    if (e.output == null)
      return true; 
    outputSlot.func_70299_a(0, e.output);
    container.maximumCost = e.cost;
    container.stackSizeToBeUsedInRepair = e.materialCost;
    return false;
  }
  
  public void updateRepairOutput() {
    ItemStack itemstack = this.inputSlots.func_70301_a(0);
    this.maximumCost = 0;
    int i = 0;
    byte b0 = 0;
    int j = 0;
    if (itemstack == null) {
      this.outputSlot.func_70299_a(0, (ItemStack)null);
      this.maximumCost = 0;
    } else {
      ItemStack itemstack1 = itemstack.func_77946_l();
      ItemStack itemstack2 = this.inputSlots.func_70301_a(1);
      Map<Integer, Integer> map = EnchantmentHelper.func_82781_a(itemstack1);
      boolean flag = false;
      int k2 = b0 + itemstack.func_82838_A() + ((itemstack2 == null) ? 0 : itemstack2.func_82838_A());
      this.stackSizeToBeUsedInRepair = 0;
      if (itemstack2 != null) {
        if (!onAnvilChange(this, itemstack, itemstack2, this.outputSlot, this.repairedItemName, k2))
          return; 
        flag = (itemstack2.func_77973_b() == Items.field_151134_bR && Items.field_151134_bR.func_92110_g(itemstack2).func_74745_c() > 0);
        if (itemstack1.func_77984_f() && itemstack1
          .func_77973_b().func_82789_a(itemstack, itemstack2)) {
          int m = Math.min(itemstack1.func_77952_i(), itemstack1.func_77958_k() / 4);
          if (m <= 0) {
            this.outputSlot.func_70299_a(0, (ItemStack)null);
            this.maximumCost = 0;
            return;
          } 
          int l;
          for (l = 0; m > 0 && l < itemstack2.field_77994_a; l++) {
            int i1 = itemstack1.func_77952_i() - m;
            itemstack1.func_77964_b(i1);
            i += Math.max(1, m / 100) + map.size();
            m = Math.min(itemstack1.func_77952_i(), itemstack1.func_77958_k() / 4);
          } 
          this.stackSizeToBeUsedInRepair = l;
        } else {
          if (!flag && (itemstack1.func_77973_b() != itemstack2.func_77973_b() || 
            !itemstack1.func_77984_f())) {
            this.outputSlot.func_70299_a(0, (ItemStack)null);
            this.maximumCost = 0;
            return;
          } 
          if (itemstack1.func_77984_f() && !flag) {
            int m = itemstack.func_77958_k() - itemstack.func_77952_i();
            int l = itemstack2.func_77958_k() - itemstack2.func_77952_i();
            int i1 = l + itemstack1.func_77958_k() * 12 / 100;
            int j1 = m + i1;
            int k1 = itemstack1.func_77958_k() - j1;
            if (k1 < 0)
              k1 = 0; 
            if (k1 < itemstack1.func_77960_j()) {
              itemstack1.func_77964_b(k1);
              i += Math.max(1, i1 / 100);
            } 
          } 
          Map map1 = EnchantmentHelper.func_82781_a(itemstack2);
          Iterator<Integer> iterator = map1.keySet().iterator();
          while (iterator.hasNext()) {
            int i3, i1 = ((Integer)iterator.next()).intValue();
            Enchantment enchantment = Enchantment.field_77331_b[i1];
            int k1 = map.containsKey(Integer.valueOf(i1)) ? ((Integer)map.get(Integer.valueOf(i1))).intValue() : 0;
            int l1 = ((Integer)map1.get(Integer.valueOf(i1))).intValue();
            if (k1 == l1) {
              i3 = ++l1;
            } else {
              i3 = Math.max(l1, k1);
            } 
            l1 = i3;
            int i2 = l1 - k1;
            boolean flag1 = enchantment.func_92089_a(itemstack);
            if (this.thePlayer.field_71075_bZ.field_75098_d || itemstack
              .func_77973_b() == Items.field_151134_bR)
              flag1 = true; 
            Iterator<Integer> iterator2 = map.keySet().iterator();
            while (iterator2.hasNext()) {
              int j2 = ((Integer)iterator2.next()).intValue();
              Enchantment e2 = Enchantment.field_77331_b[j2];
              if (j2 != i1 && (
                !enchantment.func_77326_a(e2) || !e2.func_77326_a(enchantment))) {
                flag1 = false;
                i += i2;
              } 
            } 
            if (flag1) {
              if (l1 > enchantment.func_77325_b())
                l1 = enchantment.func_77325_b(); 
              map.put(Integer.valueOf(i1), Integer.valueOf(l1));
              int l2 = 0;
              switch (enchantment.func_77324_c()) {
                case 1:
                  l2 = 8;
                  break;
                case 2:
                  l2 = 4;
                  break;
                case 5:
                  l2 = 2;
                  break;
                case 10:
                  l2 = 1;
                  break;
              } 
              if (flag)
                l2 = Math.max(1, l2 / 2); 
              i += l2 * i2;
            } 
          } 
        } 
      } 
      if (StringUtils.isBlank(this.repairedItemName)) {
        if (itemstack.func_82837_s()) {
          j = itemstack.func_77984_f() ? 7 : (itemstack.field_77994_a * 5);
          i += j;
          itemstack1.func_135074_t();
        } 
      } else if (!this.repairedItemName.equals(itemstack.func_82833_r())) {
        j = itemstack.func_77984_f() ? 7 : (itemstack.field_77994_a * 5);
        i += j;
        if (itemstack.func_82837_s())
          k2 += j / 2; 
        itemstack1.func_151001_c(this.repairedItemName);
      } 
      int k = 0;
      for (Iterator<Integer> iterator1 = map.keySet().iterator(); iterator1.hasNext(); k2 += k + k1 * l1) {
        int i1 = ((Integer)iterator1.next()).intValue();
        Enchantment enchantment = Enchantment.field_77331_b[i1];
        int k1 = ((Integer)map.get(Integer.valueOf(i1))).intValue();
        int l1 = 0;
        k++;
        switch (enchantment.func_77324_c()) {
          case 1:
            l1 = 8;
            break;
          case 2:
            l1 = 4;
            break;
          case 5:
            l1 = 2;
            break;
          case 10:
            l1 = 1;
            break;
        } 
        if (flag)
          l1 = Math.max(1, l1 / 2); 
      } 
      if (flag)
        k2 = Math.max(1, k2 / 2); 
      if (flag && !itemstack1.func_77973_b().isBookEnchantable(itemstack1, itemstack2))
        itemstack1 = null; 
      this.maximumCost = k2 + i;
      if (i <= 0)
        itemstack1 = null; 
      if (j == i && j > 0 && this.maximumCost >= 40)
        this.maximumCost = 39; 
      if (this.maximumCost >= 40 && !this.thePlayer.field_71075_bZ.field_75098_d)
        itemstack1 = null; 
      if (itemstack1 != null) {
        int l = itemstack1.func_82838_A();
        if (itemstack2 != null && l < itemstack2.func_82838_A())
          l = itemstack2.func_82838_A(); 
        if (itemstack1.func_82837_s())
          l -= 9; 
        if (l < 0)
          l = 0; 
        l += 2;
        itemstack1.func_82841_c(l);
        EnchantmentHelper.func_82782_a(map, itemstack1);
      } 
      this.outputSlot.func_70299_a(0, itemstack1);
      func_75142_b();
    } 
  }
  
  public void func_75132_a(ICrafting p_75132_1_) {
    super.func_75132_a(p_75132_1_);
    p_75132_1_.func_71112_a(this, 0, this.maximumCost);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_75137_b(int p_75137_1_, int p_75137_2_) {
    if (p_75137_1_ == 0)
      this.maximumCost = p_75137_2_; 
  }
  
  public void func_75134_a(EntityPlayer p_75134_1_) {
    super.func_75134_a(p_75134_1_);
    if (!this.world.field_72995_K)
      for (int i = 0; i < this.inputSlots.func_70302_i_(); i++) {
        ItemStack itemstack = this.inputSlots.func_70304_b(i);
        if (itemstack != null)
          p_75134_1_.func_71019_a(itemstack, false); 
      }  
  }
  
  public boolean func_75145_c(EntityPlayer p_75145_1_) {
    return !(this.world.func_147439_a(this.x, this.y, this.z) instanceof fr.paladium.palamod.modules.paladium.common.blocks.BlockAnvil) ? false : (
      (p_75145_1_.func_70092_e(this.x + 0.5D, this.y + 0.5D, this.z + 0.5D) <= 64.0D));
  }
  
  public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
    ItemStack itemstack = null;
    Slot slot = this.field_75151_b.get(p_82846_2_);
    if (slot != null && slot.func_75216_d()) {
      ItemStack itemstack1 = slot.func_75211_c();
      itemstack = itemstack1.func_77946_l();
      if (p_82846_2_ == 2) {
        if (!func_75135_a(itemstack1, 3, 39, true))
          return null; 
        slot.func_75220_a(itemstack1, itemstack);
      } else if (p_82846_2_ != 0 && p_82846_2_ != 1) {
        if (p_82846_2_ >= 3 && p_82846_2_ < 39 && !func_75135_a(itemstack1, 0, 2, false))
          return null; 
      } else if (!func_75135_a(itemstack1, 3, 39, false)) {
        return null;
      } 
      if (itemstack1.field_77994_a == 0) {
        slot.func_75215_d((ItemStack)null);
      } else {
        slot.func_75218_e();
      } 
      if (itemstack1.field_77994_a == itemstack.field_77994_a)
        return null; 
      slot.func_82870_a(p_82846_1_, itemstack1);
    } 
    return itemstack;
  }
  
  public void updateItemName(String p_82850_1_) {
    this.repairedItemName = p_82850_1_;
    if (func_75139_a(2).func_75216_d()) {
      ItemStack itemstack = func_75139_a(2).func_75211_c();
      if (StringUtils.isBlank(p_82850_1_)) {
        itemstack.func_135074_t();
      } else {
        itemstack.func_151001_c(this.repairedItemName);
      } 
    } 
    updateRepairOutput();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\ContainerAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */