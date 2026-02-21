package fr.paladium.palamod.modules.paladium.common.container;

import fr.paladium.lib.apollon.container.abstracts.PaladiumContainer;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.paladium.common.logics.TileCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CrusherContainer extends PaladiumContainer {
  private final TileCrusher tile;
  
  private final int[] tanks;
  
  private final int[] fuelTanks;
  
  private int work;
  
  private int workf;
  
  public CrusherContainer(SimpleGHandler.GuiHandlerData data) {
    this((TileCrusher)data.getTileEntity(TileCrusher.class), data.getPlayer());
  }
  
  public CrusherContainer(TileCrusher tile, EntityPlayer player) {
    this.tile = tile;
    this.tanks = new int[tile.tanks.length];
    this.fuelTanks = new int[tile.fuelTanks.length];
    int delta = 40;
    func_75146_a(new Slot((IInventory)tile, 0, 118, 344) {
          public boolean func_75214_a(ItemStack is) {
            return (is != null && 
              TileCrusher.EnumCrusherRecipes.findRecipeFromFuel(is.func_77973_b()) != null);
          }
        });
    func_75146_a(new Slot((IInventory)tile, 1, 118, 195) {
          public boolean func_75214_a(ItemStack is) {
            return (is != null && TileCrusher.EnumCrusherRecipes.findCurrentRecipe(is.func_77973_b()) != null);
          }
          
          public ResourceLocation getBackgroundIconTexture() {
            return super.getBackgroundIconTexture();
          }
        });
    func_75146_a(new Slot((IInventory)tile, 2, 1308, 534) {
          public boolean func_75214_a(ItemStack is) {
            return false;
          }
        });
    bindPlayerInventory(player.field_71071_by);
  }
  
  private void bindPlayerInventory(InventoryPlayer inventory) {
    int delta = 40;
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)inventory, j + i * 9 + 9, 104 + j * 67 - 40, 483 + i * 67 - 40)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)inventory, i, 104 + i * 67 - 40, 658)); 
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return this.tile.func_70300_a(player);
  }
  
  public void func_75142_b() {
    super.func_75142_b();
    for (int i = 0; i < this.field_75149_d.size(); i++) {
      ICrafting icrafting = this.field_75149_d.get(i);
      if (this.work != this.tile.getWork())
        icrafting.func_71112_a((Container)this, 0, this.tile.getWork()); 
      if (this.workf != this.tile.getWorkf())
        icrafting.func_71112_a((Container)this, 1, this.tile.getWorkf()); 
      int j;
      for (j = 0; j < this.tanks.length; j++) {
        if (this.tanks[j] != this.tile.tanks[j])
          icrafting.func_71112_a((Container)this, 2 + j, this.tile.tanks[j]); 
      } 
      for (j = 0; j < this.fuelTanks.length; j++) {
        if (this.fuelTanks[j] != this.tile.fuelTanks[j])
          icrafting.func_71112_a((Container)this, 2 + this.tile.tanks.length + j, this.tile.fuelTanks[j]); 
      } 
    } 
    this.work = this.tile.getWork();
    this.workf = this.tile.getWorkf();
    System.arraycopy(this.tile.tanks, 0, this.tanks, 0, this.tanks.length);
    System.arraycopy(this.tile.fuelTanks, 0, this.fuelTanks, 0, this.fuelTanks.length);
  }
  
  public void func_75137_b(int id, int value) {
    if (id == 0) {
      this.tile.setWork(value);
    } else if (id == 1) {
      this.tile.setWorkf(value);
    } else if (id >= 2 && id - 2 < this.tile.tanks.length) {
      this.tile.tanks[id - 2] = value;
    } else if (id >= 2 + this.tile.tanks.length && id - 2 - this.tile.tanks.length < this.tile.fuelTanks.length) {
      this.tile.fuelTanks[id - 2 - this.tile.tanks.length] = value;
    } 
  }
  
  public int getPlayerInventoryPosition() {
    return 3;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\container\CrusherContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */