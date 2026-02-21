package fr.paladium.palarpg.module.equipment.common.item.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.equipment.common.EquipmentCommonProxy;
import fr.paladium.palarpg.module.equipment.common.item.RPGStatApplyType;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGItemData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGBasicItemData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGSwordItemData;
import fr.paladium.palarpg.module.equipment.common.loader.util.ItemStatMutator;
import fr.paladium.palarpg.module.equipment.common.rune.RPGRuneStat;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.AStatCapability;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatCapabilityMutator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class RPGItemSword extends RPGItem {
  public RPGItemSword(RPGItemData itemData) {
    super(itemData);
    func_77637_a(EquipmentCommonProxy.getRPGWeaponTab());
    func_77625_d(1);
    func_77656_e(getItemData().getDurability());
  }
  
  public boolean func_82789_a(ItemStack self, ItemStack stack2) {
    return !getItemData().getRepairItems().isEmpty() ? getItemData().getRepairItems().stream().filter(stack -> stack.func_77969_a(stack2)).findFirst().isPresent() : false;
  }
  
  public int getItemEnchantability(ItemStack stack) {
    return 0;
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77662_d() {
    return !getItemData().isModel();
  }
  
  public EnumAction func_77661_b(ItemStack stack) {
    return EnumAction.block;
  }
  
  public void applyStats(ItemStack stack, EntityLivingBase entity, RPGStatApplyType type) {
    if (!PalaRPGMod.proxy.isDungeonWorld())
      return; 
    RPGProfilePlayerData profile = (RPGProfilePlayerData)RPGPlayer.getData((Entity)entity, "profile");
    if (profile == null || getItemData().getRequiredLevel() > profile.getLevel())
      return; 
    RPGSwordItemData data = getItemData();
    RPGStatPlayerData entityStat = (RPGStatPlayerData)RPGPlayer.getData((Entity)entity, "stats");
    if (entityStat == null)
      return; 
    data.getMutators().forEach((equipType, mutList) -> {
          if (type != equipType)
            return; 
          mutList.forEach(());
        });
    if (stack.func_77942_o() && getItemData().getRuneSlot() > 0) {
      NBTTagCompound tag = stack.func_77978_p();
      for (int i = 0; i < getItemData().getRuneSlot(); i++) {
        if (tag.func_74764_b("rune_" + i)) {
          NBTTagCompound runeTag = tag.func_74775_l("rune_" + i);
          int tier = runeTag.func_74762_e("tier");
          for (int y = 0; y < 2; y++) {
            if (runeTag.func_74764_b("stat_" + y)) {
              RPGRuneStat runeStat = RPGRuneStat.create();
              runeStat.read(runeTag.func_74775_l("stat_" + y));
              AStatCapability<Object> stat = entityStat.getCapabilityByName(runeStat.getStats());
              if (stat == null)
                return; 
              ItemStatMutator mutator = runeStat.getMutator(tier);
              StatCapabilityMutator<Object> statMutator = mutator.getMutator(getId() + "_rune_" + i + "_stat_" + y + "_type_" + runeStat.getMutationType()).setSaved(false);
              statMutator.setSaved(false);
              stat.addMutator(statMutator);
            } 
          } 
        } 
      } 
    } 
    entityStat.applyAndSync();
  }
  
  public void removeStats(ItemStack stack, EntityLivingBase entity, RPGStatApplyType type) {
    RPGSwordItemData data = getItemData();
    RPGStatPlayerData entityStat = (RPGStatPlayerData)RPGPlayer.getData((Entity)entity, "stats");
    if (entityStat == null)
      return; 
    data.getMutators().forEach((equipType, mutList) -> {
          if (type != equipType)
            return; 
          mutList.forEach(());
        });
    if (stack.func_77942_o()) {
      NBTTagCompound tag = stack.func_77978_p();
      for (int i = 0; i < getItemData().getRuneSlot(); i++) {
        if (tag.func_74764_b("rune_" + i)) {
          NBTTagCompound runeTag = tag.func_74775_l("rune_" + i);
          for (int y = 0; y < 2; y++) {
            if (runeTag.func_74764_b("stat_" + y)) {
              RPGRuneStat runeStat = RPGRuneStat.create();
              runeStat.read(runeTag.func_74775_l("stat_" + y));
              AStatCapability<Object> stat = entityStat.getCapabilityByName(runeStat.getStats());
              if (stat == null)
                return; 
              stat.removeMutator(getId() + "_rune_" + i + "_stat_" + y + "_type_" + runeStat.getMutationType());
            } 
          } 
        } 
      } 
    } 
    entityStat.applyAndSync();
  }
  
  public boolean func_77644_a(ItemStack itemStack, EntityLivingBase entity, EntityLivingBase target) {
    itemStack.func_77972_a(1, target);
    return true;
  }
  
  public int func_77626_a(ItemStack itemStack) {
    return 72000;
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    player.func_71008_a(itemStack, func_77626_a(itemStack));
    return itemStack;
  }
  
  public RPGSwordItemData getItemData() {
    return (RPGSwordItemData)super.getItemData();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\item\impl\RPGItemSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */