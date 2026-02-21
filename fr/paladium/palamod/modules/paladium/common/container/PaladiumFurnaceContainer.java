package fr.paladium.palamod.modules.paladium.common.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.container.ForgeContainer;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.paladium.common.inventory.slot.SlotSingle;
import fr.paladium.palamod.modules.paladium.common.logics.PaladiumFurnaceLogic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class PaladiumFurnaceContainer extends ForgeContainer {
  private final PaladiumFurnaceLogic tile;
  
  private int lastCookTime;
  
  private int lastBurnTime;
  
  private int lastItemBurnTime;
  
  public PaladiumFurnaceContainer(SimpleGHandler.GuiHandlerData data) {
    this(data.getInventory(), (PaladiumFurnaceLogic)data.getTileEntity(PaladiumFurnaceLogic.class));
  }
  
  public PaladiumFurnaceContainer(InventoryPlayer inventory, PaladiumFurnaceLogic tile) {
    this.tile = tile;
    func_75146_a(new Slot((IInventory)tile, 0, 56, 17) {
          public boolean func_75214_a(ItemStack stack) {
            if (stack.func_77973_b() == ItemsRegister.FURNACE_UPGRADE)
              return false; 
            return true;
          }
        });
    func_75146_a(new Slot((IInventory)tile, 1, 56, 53) {
          public boolean func_75214_a(ItemStack stack) {
            return PaladiumFurnaceLogic.isItemFuel(stack);
          }
        });
    func_75146_a((Slot)new SlotFurnace(inventory.field_70458_d, (IInventory)tile, 2, 116, 35));
    func_75146_a((Slot)new SlotSingle((IInventory)tile, 3, 19, 35, ItemsRegister.FURNACE_UPGRADE));
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)inventory, i, 8 + i * 18, 142)); 
  }
  
  public void func_75132_a(ICrafting p_75132_1_) {
    super.func_75132_a(p_75132_1_);
    p_75132_1_.func_71112_a((Container)this, 0, this.tile.furnaceCookTime);
    p_75132_1_.func_71112_a((Container)this, 1, this.tile.furnaceBurnTime);
    p_75132_1_.func_71112_a((Container)this, 2, this.tile.currentItemBurnTime);
  }
  
  public void func_75142_b() {
    super.func_75142_b();
    for (int i = 0; i < this.field_75149_d.size(); i++) {
      ICrafting icrafting = this.field_75149_d.get(i);
      if (this.lastCookTime != this.tile.furnaceCookTime)
        icrafting.func_71112_a((Container)this, 0, this.tile.furnaceCookTime); 
      if (this.lastBurnTime != this.tile.furnaceBurnTime)
        icrafting.func_71112_a((Container)this, 1, this.tile.furnaceBurnTime); 
      if (this.lastItemBurnTime != this.tile.currentItemBurnTime)
        icrafting.func_71112_a((Container)this, 2, this.tile.currentItemBurnTime); 
    } 
    this.lastCookTime = this.tile.furnaceCookTime;
    this.lastBurnTime = this.tile.furnaceBurnTime;
    this.lastItemBurnTime = this.tile.currentItemBurnTime;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_75137_b(int p_75137_1_, int p_75137_2_) {
    if (p_75137_1_ == 0)
      this.tile.furnaceCookTime = p_75137_2_; 
    if (p_75137_1_ == 1)
      this.tile.furnaceBurnTime = p_75137_2_; 
    if (p_75137_1_ == 2)
      this.tile.currentItemBurnTime = p_75137_2_; 
  }
  
  public boolean func_75145_c(EntityPlayer p_75145_1_) {
    return this.tile.func_70300_a(p_75145_1_);
  }
  
  public int getPlayerInventoryPosition() {
    return 4;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\PaladiumFurnaceContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */