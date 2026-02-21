package fr.paladium.palamod.modules.paladium.common.logics;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.container.impl.TileEntityForgeInventory;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.common.blocks.machine.BlockPaladiumFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;

public class PaladiumFurnaceLogic extends TileEntityForgeInventory implements ISidedInventory {
  private static final int[] slotsTop = new int[] { 0 };
  
  private static final int[] slotsBottom = new int[] { 2, 1 };
  
  private static final int[] slotsSides = new int[] { 1 };
  
  public int furnaceBurnTime;
  
  public int currentItemBurnTime;
  
  public int furnaceCookTime;
  
  public int timeNeeded = 200;
  
  public PaladiumFurnaceLogic() {
    super("tile.PaladiumFurnace", 4);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.furnaceBurnTime = compound.func_74765_d("BurnTime");
    this.furnaceCookTime = compound.func_74765_d("CookTime");
    this.timeNeeded = compound.func_74765_d("workingTimeNeeded");
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74777_a("BurnTime", (short)this.furnaceBurnTime);
    compound.func_74777_a("CookTime", (short)this.furnaceCookTime);
    compound.func_74777_a("workingTimeNeeded", (short)this.timeNeeded);
  }
  
  public int func_70297_j_() {
    return 64;
  }
  
  @SideOnly(Side.CLIENT)
  public int getCookProgressScaled(int value) {
    return this.furnaceCookTime * value / this.timeNeeded;
  }
  
  @SideOnly(Side.CLIENT)
  public float getCookProgress() {
    return this.furnaceCookTime / this.timeNeeded;
  }
  
  @SideOnly(Side.CLIENT)
  public int getBurnTimeRemainingScaled(int value) {
    if (this.currentItemBurnTime == 0)
      this.currentItemBurnTime = 200; 
    return this.furnaceBurnTime * value / this.currentItemBurnTime;
  }
  
  @SideOnly(Side.CLIENT)
  public float getBurnTimeRemaining() {
    if (this.currentItemBurnTime == 0)
      this.currentItemBurnTime = 200; 
    return this.furnaceBurnTime / this.currentItemBurnTime;
  }
  
  public boolean isBurning() {
    return (this.furnaceBurnTime > 0);
  }
  
  public void func_145845_h() {
    int modifier;
    boolean flag = (this.furnaceBurnTime > 0);
    boolean flag1 = false;
    if (getContent()[3] != null && (getContent()[3]).field_77994_a > 0 && 
      getContent()[3].func_77973_b().equals(ItemsRegister.FURNACE_UPGRADE)) {
      modifier = (getContent()[3]).field_77994_a;
      if (modifier > 16)
        modifier = 16; 
    } else {
      modifier = 1;
    } 
    if (this.furnaceBurnTime > 0)
      this.furnaceBurnTime -= modifier; 
    this.timeNeeded = 200 / modifier;
    if ((this.furnaceBurnTime != 0 || (getContent()[0] != null && getContent()[1] != null)) && 
      this.furnaceBurnTime <= 0 && canSmelt()) {
      this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(getContent()[1]);
      if (this.furnaceBurnTime > 0) {
        flag1 = true;
        if (getContent()[1] != null) {
          (getContent()[1]).field_77994_a--;
          if ((getContent()[1]).field_77994_a <= 0)
            getContent()[1] = getContent()[1].func_77973_b().getContainerItem(getContent()[1]); 
        } 
      } 
    } 
    if (!isBurning() && canSmelt())
      this.furnaceCookTime = 1; 
    if (isBurning() && canSmelt() && this.furnaceBurnTime != 0) {
      this.furnaceCookTime++;
      if (this.furnaceCookTime >= this.timeNeeded) {
        this.furnaceCookTime = 0;
        smeltItem();
        flag1 = true;
      } 
    } else {
      this.furnaceCookTime = 0;
    } 
    if (flag != ((this.furnaceBurnTime > 0)))
      BlockPaladiumFurnace.updateFurnaceBlockState((this.furnaceBurnTime > 0), this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e); 
    if (flag1)
      func_70296_d(); 
  }
  
  private boolean canSmelt() {
    if (getContent()[0] == null)
      return false; 
    ItemStack itemstack = FurnaceRecipes.func_77602_a().func_151395_a(getContent()[0]);
    if (itemstack == null)
      return false; 
    if (getContent()[2] == null)
      return true; 
    if (!getContent()[2].func_77969_a(itemstack))
      return false; 
    int result = (getContent()[2]).field_77994_a + itemstack.field_77994_a;
    return (result <= func_70297_j_() && result <= getContent()[2].func_77976_d());
  }
  
  public void smeltItem() {
    if (canSmelt()) {
      ItemStack itemstack = FurnaceRecipes.func_77602_a().func_151395_a(getContent()[0]);
      if (getContent()[2] == null) {
        getContent()[2] = itemstack.func_77946_l();
      } else if (getContent()[2].func_77973_b() == itemstack.func_77973_b()) {
        (getContent()[2]).field_77994_a += itemstack.field_77994_a;
      } 
      (getContent()[0]).field_77994_a--;
      if ((getContent()[0]).field_77994_a <= 0)
        getContent()[0] = null; 
    } 
  }
  
  public static int getItemBurnTime(ItemStack p_145952_0_) {
    if (p_145952_0_ == null)
      return 0; 
    Item item = p_145952_0_.func_77973_b();
    if (item instanceof net.minecraft.item.ItemBlock && Block.func_149634_a(item) != Blocks.field_150350_a) {
      Block block = Block.func_149634_a(item);
      if (block == Blocks.field_150376_bx)
        return 150; 
      if (block.func_149688_o() == Material.field_151575_d)
        return 300; 
      if (block == Blocks.field_150402_ci)
        return 16000; 
    } 
    if ((item instanceof ItemTool && "WOOD".equals(((ItemTool)item).func_77861_e())) || (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).func_150932_j())))
      return 200; 
    if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).func_77842_f()))
      return 200; 
    if (item == Items.field_151055_y)
      return 100; 
    if (item == Items.field_151044_h)
      return 1600; 
    if (item == Items.field_151129_at)
      return 20000; 
    if (item == Item.func_150898_a(Blocks.field_150345_g))
      return 100; 
    if (item == Items.field_151072_bj)
      return 2400; 
    return GameRegistry.getFuelValue(p_145952_0_);
  }
  
  public static boolean isItemFuel(ItemStack p_145954_0_) {
    return (getItemBurnTime(p_145954_0_) > 0);
  }
  
  public boolean func_70300_a(EntityPlayer p_70300_1_) {
    return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : (
      (p_70300_1_.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D));
  }
  
  public int[] func_94128_d(int side) {
    return (side == 0) ? slotsBottom : ((side == 1) ? slotsTop : slotsSides);
  }
  
  public boolean func_102007_a(int side, ItemStack stack, int slot) {
    return func_94041_b(side, stack);
  }
  
  public boolean func_102008_b(int side, ItemStack stack, int slot) {
    return (slot != 0 || side != 1 || stack.func_77973_b() == Items.field_151133_ar);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\PaladiumFurnaceLogic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */