package fr.paladium.palamod.modules.alchimiste.common.container;

import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEnchantment extends Container {
  public IInventory tableInventory = (IInventory)new InventoryBasic("EnchantAlchemist", true, 1) {
      private static final String __OBFID = "CL_00001746";
      
      public int func_70297_j_() {
        return 1;
      }
      
      public void func_70296_d() {
        super.func_70296_d();
        ContainerEnchantment.this.func_75130_a((IInventory)this);
      }
    };
  
  public ContainerEnchantment(SimpleGHandler.GuiHandlerData data) {
    this(data.getInventory());
  }
  
  public ContainerEnchantment(InventoryPlayer inventoryPlayer) {
    func_75146_a(new Slot(this.tableInventory, 0, 25, 47));
    int l;
    for (l = 0; l < 3; l++) {
      for (int i1 = 0; i1 < 9; i1++)
        func_75146_a(new Slot((IInventory)inventoryPlayer, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18)); 
    } 
    for (l = 0; l < 9; l++)
      func_75146_a(new Slot((IInventory)inventoryPlayer, l, 8 + l * 18, 142)); 
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return true;
  }
  
  public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
    ItemStack itemstack = null;
    Slot slot = this.field_75151_b.get(p_82846_2_);
    if (slot != null && slot.func_75216_d()) {
      if (slot.func_75211_c().func_77973_b() instanceof fr.paladium.palamod.modules.alchimiste.common.blocks.itemblocks.ItemBlockExtractor)
        return null; 
      ItemStack itemstack1 = slot.func_75211_c();
      itemstack = itemstack1.func_77946_l();
      if (p_82846_2_ == 0) {
        if (!func_75135_a(itemstack1, 1, 37, true))
          return null; 
      } else {
        if (((Slot)this.field_75151_b.get(0)).func_75216_d() || 
          !((Slot)this.field_75151_b.get(0)).func_75214_a(itemstack1))
          return null; 
        if (itemstack1.func_77942_o() && itemstack1.field_77994_a == 1) {
          ((Slot)this.field_75151_b.get(0)).func_75215_d(itemstack1.func_77946_l());
          itemstack1.field_77994_a = 0;
        } else if (itemstack1.field_77994_a >= 1) {
          ((Slot)this.field_75151_b.get(0))
            .func_75215_d(new ItemStack(itemstack1.func_77973_b(), 1, itemstack1.func_77960_j()));
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
  
  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);
    if (!player.field_70170_p.field_72995_K) {
      ItemStack itemstack = func_75139_a(0).func_75211_c();
      if (itemstack != null)
        player.func_71019_a(itemstack, false); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\container\ContainerEnchantment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */