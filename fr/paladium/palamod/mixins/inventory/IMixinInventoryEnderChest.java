package fr.paladium.palamod.mixins.inventory;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({InventoryEnderChest.class})
public abstract class IMixinInventoryEnderChest extends InventoryBasic {
  private boolean compiled;
  
  public IMixinInventoryEnderChest(CallbackInfo ci) {
    super("container.enderchest", false, 45);
  }
  
  @Overwrite
  public void func_70486_a(NBTTagList list) {
    if (!this.compiled) {
      try {
        ReflectionHelper.setPrivateValue(InventoryBasic.class, this, Integer.valueOf(45), new String[] { "slotsCount", "field_70481_b" });
        ReflectionHelper.setPrivateValue(InventoryBasic.class, this, new ItemStack[45], new String[] { "inventoryContents", "field_70482_c" });
      } catch (Exception e2) {
        e2.printStackTrace();
      } 
      this.compiled = true;
    } 
    int i;
    for (i = 0; i < 45; i++)
      func_70299_a(i, (ItemStack)null); 
    for (i = 0; i < list.func_74745_c(); i++) {
      NBTTagCompound nbttagcompound = list.func_150305_b(i);
      int j = nbttagcompound.func_74771_c("Slot") & 0xFF;
      if (j >= 0 && j < func_70302_i_())
        func_70299_a(j, ItemStack.func_77949_a(nbttagcompound)); 
    } 
  }
  
  @Overwrite
  public NBTTagList func_70487_g() {
    if (!this.compiled) {
      try {
        ReflectionHelper.setPrivateValue(InventoryBasic.class, this, Integer.valueOf(45), new String[] { "slotsCount", "field_70481_b" });
        ReflectionHelper.setPrivateValue(InventoryBasic.class, this, new ItemStack[45], new String[] { "inventoryContents", "field_70482_c" });
      } catch (Exception e2) {
        e2.printStackTrace();
      } 
      this.compiled = true;
    } 
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < 45; i++) {
      ItemStack itemstack = func_70301_a(i);
      if (itemstack != null) {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.func_74774_a("Slot", (byte)i);
        itemstack.func_77955_b(nbttagcompound);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound);
      } 
    } 
    return nbttaglist;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\inventory\IMixinInventoryEnderChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */