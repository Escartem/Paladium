package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.Random;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class ContainerCorruptedEnchantment extends Container {
  public IInventory tableInventory = (IInventory)new InventoryBasic("Enchant", true, 1) {
      private static final String __OBFID = "CL_00001746";
      
      public int func_70297_j_() {
        return 1;
      }
      
      public void func_70296_d() {
        super.func_70296_d();
        ContainerCorruptedEnchantment.this.func_75130_a((IInventory)this);
      }
    };
  
  private World worldPointer;
  
  private int posX;
  
  private int posY;
  
  private int posZ;
  
  private final Random rand = new Random();
  
  public long nameSeed;
  
  public int[] enchantLevels = new int[3];
  
  private static final String __OBFID = "CL_00001745";
  
  public ContainerCorruptedEnchantment(SimpleGHandler.GuiHandlerData data) {
    this(data.getInventory(), data.getWorld(), data.getX(), data.getY(), data.getZ());
  }
  
  public ContainerCorruptedEnchantment(InventoryPlayer p_i1811_1_, World p_i1811_2_, int p_i1811_3_, int p_i1811_4_, int p_i1811_5_) {
    this.worldPointer = p_i1811_2_;
    this.posX = p_i1811_3_;
    this.posY = p_i1811_4_;
    this.posZ = p_i1811_5_;
    func_75146_a(new Slot(this.tableInventory, 0, 25, 47) {
          private static final String __OBFID = "CL_00001747";
          
          public boolean func_75214_a(ItemStack p_75214_1_) {
            return true;
          }
        });
    int l;
    for (l = 0; l < 3; l++) {
      for (int i1 = 0; i1 < 9; i1++)
        func_75146_a(new Slot((IInventory)p_i1811_1_, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18)); 
    } 
    for (l = 0; l < 9; l++)
      func_75146_a(new Slot((IInventory)p_i1811_1_, l, 8 + l * 18, 142)); 
  }
  
  public void func_75132_a(ICrafting p_75132_1_) {
    super.func_75132_a(p_75132_1_);
    p_75132_1_.func_71112_a(this, 0, this.enchantLevels[0]);
    p_75132_1_.func_71112_a(this, 1, this.enchantLevels[1]);
    p_75132_1_.func_71112_a(this, 2, this.enchantLevels[2]);
  }
  
  public void func_75142_b() {
    super.func_75142_b();
    for (int i = 0; i < this.field_75149_d.size(); i++) {
      ICrafting icrafting = this.field_75149_d.get(i);
      icrafting.func_71112_a(this, 0, this.enchantLevels[0]);
      icrafting.func_71112_a(this, 1, this.enchantLevels[1]);
      icrafting.func_71112_a(this, 2, this.enchantLevels[2]);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void func_75137_b(int p_75137_1_, int p_75137_2_) {
    if (p_75137_1_ >= 0 && p_75137_1_ <= 2) {
      this.enchantLevels[p_75137_1_] = p_75137_2_;
    } else {
      super.func_75137_b(p_75137_1_, p_75137_2_);
    } 
  }
  
  public void func_75130_a(IInventory p_75130_1_) {
    if (p_75130_1_ == this.tableInventory) {
      ItemStack itemstack = p_75130_1_.func_70301_a(0);
      if (itemstack != null && itemstack.func_77948_v()) {
        this.nameSeed = this.rand.nextLong();
        if (!this.worldPointer.field_72995_K) {
          int i = 0;
          float power = 0.0F;
          int j;
          for (j = -1; j <= 1; j++) {
            for (int k = -1; k <= 1; k++) {
              if ((j != 0 || k != 0) && this.worldPointer.func_147437_c(this.posX + k, this.posY, this.posZ + j) && this.worldPointer.func_147437_c(this.posX + k, this.posY + 1, this.posZ + j)) {
                power += ForgeHooks.getEnchantPower(this.worldPointer, this.posX + k * 2, this.posY, this.posZ + j * 2);
                power += ForgeHooks.getEnchantPower(this.worldPointer, this.posX + k * 2, this.posY + 1, this.posZ + j * 2);
                if (k != 0 && j != 0) {
                  power += ForgeHooks.getEnchantPower(this.worldPointer, this.posX + k * 2, this.posY, this.posZ + j);
                  power += ForgeHooks.getEnchantPower(this.worldPointer, this.posX + k * 2, this.posY + 1, this.posZ + j);
                  power += ForgeHooks.getEnchantPower(this.worldPointer, this.posX + k, this.posY, this.posZ + j * 2);
                  power += ForgeHooks.getEnchantPower(this.worldPointer, this.posX + k, this.posY + 1, this.posZ + j * 2);
                } 
              } 
            } 
          } 
          for (j = 0; j < 3; j++)
            this.enchantLevels[j] = EnchantmentHelper.func_77514_a(this.rand, j, (int)power, itemstack); 
          func_75142_b();
        } 
      } else {
        for (int i = 0; i < 3; i++)
          this.enchantLevels[i] = 0; 
      } 
    } 
  }
  
  public boolean func_75140_a(EntityPlayer player, int index) {
    ItemStack itemstack = this.tableInventory.func_70301_a(0);
    if (itemstack == null || !itemstack.func_77948_v())
      return false; 
    int sum = (int)(MonthlyUtils.getEnchantmentSum(itemstack) * 1.5D);
    if (sum <= 0)
      return false; 
    if (!MonthlyUtils.removeEnchantments(itemstack))
      return false; 
    player.func_82242_a(sum);
    func_75130_a(this.tableInventory);
    return true;
  }
  
  public void func_75134_a(EntityPlayer p_75134_1_) {
    super.func_75134_a(p_75134_1_);
    if (!this.worldPointer.field_72995_K) {
      ItemStack itemstack = this.tableInventory.func_70304_b(0);
      if (itemstack != null)
        p_75134_1_.func_71019_a(itemstack, false); 
    } 
  }
  
  public boolean func_75145_c(EntityPlayer p_75145_1_) {
    return (this.worldPointer.func_147439_a(this.posX, this.posY, this.posZ) != BlocksRegister.CORRUPTED_ENCHANTMENT_TABLE) ? false : (
      (p_75145_1_.func_70092_e(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D) <= 64.0D));
  }
  
  public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
    ItemStack itemstack = null;
    Slot slot = this.field_75151_b.get(p_82846_2_);
    if (slot != null && slot.func_75216_d()) {
      ItemStack itemstack1 = slot.func_75211_c();
      itemstack = itemstack1.func_77946_l();
      if (p_82846_2_ == 0) {
        if (!func_75135_a(itemstack1, 1, 37, true))
          return null; 
      } else {
        if (((Slot)this.field_75151_b.get(0)).func_75216_d() || !((Slot)this.field_75151_b.get(0)).func_75214_a(itemstack1))
          return null; 
        if (itemstack1.func_77942_o() && itemstack1.field_77994_a == 1) {
          ((Slot)this.field_75151_b.get(0)).func_75215_d(itemstack1.func_77946_l());
          itemstack1.field_77994_a = 0;
        } else if (itemstack1.field_77994_a >= 1) {
          ((Slot)this.field_75151_b.get(0)).func_75215_d(new ItemStack(itemstack1.func_77973_b(), 1, itemstack1.func_77960_j()));
          itemstack1.field_77994_a--;
        } 
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\containers\ContainerCorruptedEnchantment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */