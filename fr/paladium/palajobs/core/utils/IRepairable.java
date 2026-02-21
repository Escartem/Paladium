package fr.paladium.palajobs.core.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface IRepairable {
  int getRepair(ItemStack paramItemStack);
  
  void repair(ItemStack paramItemStack1, ItemStack paramItemStack2, EntityLivingBase paramEntityLivingBase);
  
  void repair(ItemStack paramItemStack1, ItemStack paramItemStack2);
  
  void damageItem(ItemStack paramItemStack, int paramInt);
  
  int getMaxRepair(ItemStack paramItemStack);
  
  boolean isInChest();
  
  boolean isEndium();
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\cor\\utils\IRepairable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */