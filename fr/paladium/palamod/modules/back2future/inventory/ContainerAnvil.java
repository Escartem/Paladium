package fr.paladium.palamod.modules.back2future.inventory;

import cpw.mods.fml.relauncher.ReflectionHelper;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import org.apache.commons.lang3.StringUtils;

public class ContainerAnvil extends ContainerRepair {
  private final EntityPlayer player;
  
  private IInventory inputSlots = null;
  
  private IInventory outputSlot = null;
  
  private String repairedItemName;
  
  private final int x;
  
  private final int y;
  
  private final int z;
  
  private final World world;
  
  public ContainerAnvil(SimpleGHandler.GuiHandlerData data) {
    this(data.getPlayer(), data.getWorld(), data.getX(), data.getY(), data.getZ());
  }
  
  public ContainerAnvil(EntityPlayer player, World world, int x, int y, int z) {
    super(player.field_71071_by, world, x, y, z, player);
    this.player = player;
    this.x = x;
    this.y = y;
    this.z = z;
    this.world = world;
    this.inputSlots = (IInventory)ReflectionHelper.getPrivateValue(ContainerRepair.class, this, new String[] { "inputSlots", "field_82853_g" });
    this.outputSlot = (IInventory)ReflectionHelper.getPrivateValue(ContainerRepair.class, this, new String[] { "outputSlot", "field_82852_f" });
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return !(this.world.func_147439_a(this.x, this.y, this.z) instanceof net.minecraft.block.BlockAnvil) ? false : (
      (player.func_70092_e(this.x + 0.5D, this.y + 0.5D, this.z + 0.5D) <= 64.0D));
  }
  
  public void func_82848_d() {
    ItemStack itemstack = this.inputSlots.func_70301_a(0);
    this.field_82854_e = 1;
    int i = 0;
    if (itemstack == null) {
      this.outputSlot.func_70299_a(0, null);
      this.field_82854_e = 0;
    } else {
      ItemStack itemstack1 = itemstack.func_77946_l();
      ItemStack itemstack2 = this.inputSlots.func_70301_a(1);
      Map<Integer, Integer> map = EnchantmentHelper.func_82781_a(itemstack1);
      boolean flag7 = false;
      int cost = itemstack.func_82838_A() + ((itemstack2 == null) ? 0 : itemstack2.func_82838_A());
      this.field_82856_l = 0;
      if (itemstack2 != null) {
        if (!ForgeHooks.onAnvilChange(this, itemstack, itemstack2, this.outputSlot, this.repairedItemName, cost))
          return; 
        flag7 = (itemstack2.func_77973_b() == Items.field_151134_bR && Items.field_151134_bR.func_92110_g(itemstack2).func_74745_c() > 0);
        if (itemstack1.func_77984_f() && itemstack1
          .func_77973_b().func_82789_a(itemstack, itemstack2)) {
          int j = Math.min(itemstack1.func_77960_j(), itemstack1.func_77958_k() / 4);
          if (j <= 0) {
            this.outputSlot.func_70299_a(0, null);
            this.field_82854_e = 0;
            return;
          } 
          int k;
          for (k = 0; j > 0 && k < itemstack2.field_77994_a; k++) {
            int l = itemstack1.func_77960_j() - j;
            itemstack1.func_77964_b(l);
            i++;
            j = Math.min(itemstack1.func_77960_j(), itemstack1.func_77958_k() / 4);
          } 
          this.field_82856_l = k;
        } else {
          if (!flag7 && (itemstack1.func_77973_b() != itemstack2.func_77973_b() || 
            !itemstack1.func_77984_f())) {
            this.outputSlot.func_70299_a(0, null);
            this.field_82854_e = 0;
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
              if (this.player.field_71075_bZ.field_75098_d || itemstack.func_77973_b() == Items.field_151134_bR)
                flag8 = true; 
              Iterator<Integer> iterator = map.keySet().iterator();
              while (iterator.hasNext()) {
                int l1 = ((Integer)iterator.next()).intValue();
                Enchantment e2 = Enchantment.field_77331_b[l1];
                if (l1 != l && (
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
      if (flag7 && !itemstack1.func_77973_b().isBookEnchantable(itemstack1, itemstack2))
        itemstack1 = null; 
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
      this.field_82854_e = (wasRenamed && !wasFixed) ? 1 : (cost + i);
      if (i <= 0)
        itemstack1 = null; 
      if (this.field_82854_e >= 40 && !this.player.field_71075_bZ.field_75098_d)
        itemstack1 = null; 
      if (itemstack1 != null && wasFixed) {
        int repairCost = itemstack1.func_82838_A();
        if (itemstack2 != null && repairCost < itemstack2.func_82838_A())
          repairCost = itemstack2.func_82838_A(); 
        repairCost = repairCost * 2 + 1;
        itemstack1.func_82841_c(repairCost);
        EnchantmentHelper.func_82782_a(map, itemstack1);
      } 
      this.outputSlot.func_70299_a(0, itemstack1);
      func_75142_b();
    } 
  }
  
  public void func_82850_a(String name) {
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
    func_82848_d();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\inventory\ContainerAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */