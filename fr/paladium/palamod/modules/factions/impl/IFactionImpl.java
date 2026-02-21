package fr.paladium.palamod.modules.factions.impl;

import fr.paladium.palamod.modules.factions.dto.leveling.FactionXpChangeReason;
import fr.paladium.palamod.modules.factions.dto.leveling.Tristate;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IFactionImpl {
  void addElo(UUID paramUUID, long paramLong);
  
  void addXp(UUID paramUUID, long paramLong, FactionXpChangeReason paramFactionXpChangeReason);
  
  Tristate hasPermission(EntityPlayer paramEntityPlayer, String paramString, int paramInt1, int paramInt2);
  
  boolean isFactionClaim(World paramWorld, int paramInt1, int paramInt2);
  
  void onItemCraft(EntityPlayer paramEntityPlayer, ItemStack paramItemStack);
  
  void onItemSmelt(EntityPlayer paramEntityPlayer, ItemStack paramItemStack);
  
  void onItemCraftPalamachine(EntityPlayer paramEntityPlayer, ItemStack paramItemStack);
  
  void onItemEnchant(EntityPlayer paramEntityPlayer, ItemStack paramItemStack);
  
  void onGrinderCraft(EntityPlayer paramEntityPlayer, ItemStack paramItemStack);
  
  void onGrinderSmelt(EntityPlayer paramEntityPlayer, ItemStack paramItemStack);
  
  void onItemUse(EntityPlayer paramEntityPlayer, ItemStack paramItemStack, int paramInt);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\factions\impl\IFactionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */