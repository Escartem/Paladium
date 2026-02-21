package fr.paladium.palamod.modules.luckyblock.blocks.supercraftingtable;

import fr.paladium.lib.apollon.container.interfaces.ApollonContainer;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerSuperCraftingTable extends Container implements ApollonContainer {
  public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
  
  public IInventory craftResult = (IInventory)new InventoryCraftResult();
  
  private final World worldObj;
  
  private final int posX;
  
  private final int posY;
  
  private final int posZ;
  
  public ContainerSuperCraftingTable(SimpleGHandler.GuiHandlerData data) {
    this(data.getInventory(), data.getWorld(), data.getX(), data.getY(), data.getZ());
  }
  
  public ContainerSuperCraftingTable(InventoryPlayer p_i1808_1_, World p_i1808_2_, int p_i1808_3_, int p_i1808_4_, int p_i1808_5_) {
    this.worldObj = p_i1808_2_;
    this.posX = p_i1808_3_;
    this.posY = p_i1808_4_;
    this.posZ = p_i1808_5_;
    func_75146_a(new SlotSuperCraftingTable(p_i1808_1_.field_70458_d, (IInventory)this.craftMatrix, this.craftResult, 0, 124, 35, this.worldObj, this.posX, this.posY, this.posZ));
    int l;
    for (l = 0; l < 3; l++) {
      for (int i1 = 0; i1 < 3; i1++)
        func_75146_a(new Slot((IInventory)this.craftMatrix, i1 + l * 3, 30 + i1 * 18, 17 + l * 18)); 
    } 
    for (l = 0; l < 3; l++) {
      for (int i1 = 0; i1 < 9; i1++)
        func_75146_a(new Slot((IInventory)p_i1808_1_, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18)); 
    } 
    for (l = 0; l < 9; l++)
      func_75146_a(new Slot((IInventory)p_i1808_1_, l, 8 + l * 18, 142)); 
    func_75130_a((IInventory)this.craftMatrix);
  }
  
  public void func_75130_a(IInventory p_75130_1_) {
    this.craftResult.func_70299_a(0, 
        SuperCraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
  }
  
  public void func_75134_a(EntityPlayer p_75134_1_) {
    super.func_75134_a(p_75134_1_);
    if (!this.worldObj.field_72995_K)
      for (int i = 0; i < 9; i++) {
        ItemStack itemstack = this.craftMatrix.func_70304_b(i);
        if (itemstack != null)
          p_75134_1_.func_71019_a(itemstack, false); 
      }  
  }
  
  public boolean func_75145_c(EntityPlayer p_75145_1_) {
    return (this.worldObj.func_147439_a(this.posX, this.posY, this.posZ) != BlocksRegister.SUPER_CRAFTING_TABLE) ? false : (
      
      (p_75145_1_.func_70092_e(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D) <= 64.0D));
  }
  
  public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
    ItemStack itemstack = null;
    Slot slot = this.field_75151_b.get(p_82846_2_);
    if (slot != null && slot.func_75216_d()) {
      ItemStack itemstack1 = slot.func_75211_c();
      itemstack = itemstack1.func_77946_l();
      if (p_82846_2_ == 0) {
        if (!func_75135_a(itemstack1, 10, 46, true))
          return null; 
        slot.func_75220_a(itemstack1, itemstack);
      } else if (p_82846_2_ >= 10 && p_82846_2_ < 37) {
        if (!func_75135_a(itemstack1, 37, 46, false))
          return null; 
      } else if (p_82846_2_ >= 37 && p_82846_2_ < 46) {
        if (!func_75135_a(itemstack1, 10, 37, false))
          return null; 
      } else if (!func_75135_a(itemstack1, 10, 46, false)) {
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
  
  public boolean func_94530_a(ItemStack p_94530_1_, Slot p_94530_2_) {
    return (p_94530_2_.field_75224_c != this.craftResult && super.func_94530_a(p_94530_1_, p_94530_2_));
  }
  
  public InventoryCrafting getCraftMatrix() {
    return this.craftMatrix;
  }
  
  public void setCraftMatrix(InventoryCrafting craftMatrix) {
    this.craftMatrix = craftMatrix;
  }
  
  public IInventory getCraftResult() {
    return this.craftResult;
  }
  
  public void setCraftResult(IInventory craftResult) {
    this.craftResult = craftResult;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\supercraftingtable\ContainerSuperCraftingTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */