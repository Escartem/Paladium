package fr.paladium.palarpg.module.equipment.common.manager;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import fr.paladium.palarpg.common.api.ItemsRegister;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemArmor;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemRune;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemSword;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGRuneConfigData;
import fr.paladium.palarpg.module.equipment.common.loader.util.ItemStatMutator;
import fr.paladium.palarpg.module.equipment.common.rune.RPGRuneStat;
import fr.paladium.palarpg.module.equipment.common.rune.RPGRuneStatRange;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatMutationType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.NonNull;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class EquipmentRuneManager {
  private static final Random RANDOM = new Random();
  
  private static RPGRuneConfigData config;
  
  public static ItemStack fusion(@NonNull ItemStack rune1, @NonNull ItemStack rune2) {
    if (rune1 == null)
      throw new NullPointerException("rune1 is marked non-null but is null"); 
    if (rune2 == null)
      throw new NullPointerException("rune2 is marked non-null but is null"); 
    if (!areRunesSameTier(rune1, rune2))
      return null; 
    int nextTier = ((RPGItemRune)rune1.func_77973_b()).getItemData().getTier() + 1;
    if (nextTier > 3)
      return null; 
    RPGItemRune nextTierItem = getRuneItemByTier(nextTier);
    if (nextTierItem == null || !areRuneGenerated(rune1) || !areRuneGenerated(rune2))
      return null; 
    ItemStack resultRune = new ItemStack((Item)nextTierItem);
    NBTTagCompound nbt = new NBTTagCompound();
    RPGRuneStat rune1Stat1 = RPGRuneStat.create();
    rune1Stat1.read(rune1.func_77978_p().func_74775_l("stat_0"));
    RPGRuneStat rune1Stat2 = null;
    if (rune1.func_77978_p().func_74764_b("stat_1")) {
      rune1Stat2 = RPGRuneStat.create();
      rune1Stat2.read(rune1.func_77978_p().func_74775_l("stat_1"));
    } 
    RPGRuneStat rune2Stat1 = RPGRuneStat.create();
    rune2Stat1.read(rune2.func_77978_p().func_74775_l("stat_0"));
    RPGRuneStat rune2Stat2 = null;
    if (rune2.func_77978_p().func_74764_b("stat_1")) {
      rune2Stat2 = RPGRuneStat.create();
      rune2Stat2.read(rune2.func_77978_p().func_74775_l("stat_1"));
    } 
    if (rune2Stat1.getStats() == rune1Stat1.getStats() && rune2Stat1.getMutationType() == rune1Stat1.getMutationType()) {
      rune2Stat1.addCoef(rune1Stat1.getCoefs());
    } else if (rune2Stat2 != null && rune1Stat2 != null && rune2Stat1.getStats() == rune1Stat2.getStats() && rune2Stat1.getMutationType() == rune1Stat2.getMutationType()) {
      rune2Stat1.addCoef(rune1Stat2.getCoefs());
    } 
    if (rune1Stat2 != null)
      if (rune2Stat2 == null) {
        rune2Stat2 = rune1Stat2;
      } else if (rune2Stat2.getStats() == rune1Stat1.getStats() && rune2Stat2.getMutationType() == rune1Stat1.getMutationType()) {
        rune2Stat2.addCoef(rune1Stat1.getCoefs());
      } else if (rune2Stat2.getStats() == rune1Stat2.getStats() && rune2Stat2.getMutationType() == rune1Stat2.getMutationType()) {
        rune2Stat2.addCoef(rune1Stat2.getCoefs());
      }  
    NBTTagCompound rune1NBT = new NBTTagCompound();
    rune2Stat1.write(rune1NBT);
    nbt.func_74782_a("stat_0", (NBTBase)rune1NBT);
    if (rune2Stat2 != null) {
      NBTTagCompound rune2NBT = new NBTTagCompound();
      rune2Stat2.write(rune2NBT);
      nbt.func_74782_a("stat_1", (NBTBase)rune2NBT);
    } 
    resultRune.func_77982_d(nbt);
    return resultRune;
  }
  
  public static ItemStack apply(ItemStack rune, ItemStack equipment, int slotIndex) {
    if (!rune.func_77942_o())
      return null; 
    RPGRuneStat runeStat1 = null;
    if (rune.func_77978_p().func_74764_b("stat_0")) {
      runeStat1 = RPGRuneStat.create();
      runeStat1.read(rune.func_77978_p().func_74775_l("stat_0"));
    } 
    RPGRuneStat runeStat2 = null;
    if (rune.func_77978_p().func_74764_b("stat_1")) {
      runeStat2 = RPGRuneStat.create();
      runeStat2.read(rune.func_77978_p().func_74775_l("stat_1"));
    } 
    NBTTagCompound equipmentNBT = equipment.func_77942_o() ? equipment.func_77978_p() : new NBTTagCompound();
    NBTTagCompound runeNBT = new NBTTagCompound();
    runeNBT.func_74768_a("tier", ((RPGItemRune)rune.func_77973_b()).getItemData().getTier());
    if (runeStat1 != null) {
      NBTTagCompound runeStatNBT = new NBTTagCompound();
      runeStat1.write(runeStatNBT);
      runeNBT.func_74782_a("stat_0", (NBTBase)runeStatNBT);
    } 
    if (runeStat2 != null) {
      NBTTagCompound runeStatNBT = new NBTTagCompound();
      runeStat2.write(runeStatNBT);
      runeNBT.func_74782_a("stat_1", (NBTBase)runeStatNBT);
    } 
    equipmentNBT.func_74782_a("rune_" + slotIndex, (NBTBase)runeNBT);
    equipment.func_77982_d(equipmentNBT);
    return equipment;
  }
  
  public static double getValue(RPGRuneStat runeStat, int tier) {
    RPGRuneStatRange statRange = config.getConfig().stream().filter(range -> (range.getMutationType() == runeStat.getMutationType() && range.getStat() == runeStat.getStats())).findFirst().orElse(null);
    if (statRange == null)
      return 0.0D; 
    return (statRange.getMax() - statRange.getMin()) * runeStat.getSumCoef() + statRange.getMin() * Math.pow(2.0D, (int)Math.floor(Math.log(runeStat.getCoefs().size()) / Math.log(2.0D)));
  }
  
  public static RPGRuneStat rollStats(int tier) {
    return rollStats(tier, null, null);
  }
  
  public static RPGRuneStat rollStats(int tier, EnumStats stats, StatMutationType mutationType) {
    RPGRuneStatRange randomStat = config.getConfig().get(RANDOM.nextInt(config.getConfig().size()));
    while (stats != null && randomStat.getStat() == stats && mutationType != null && randomStat.getMutationType() == mutationType)
      randomStat = config.getConfig().get(RANDOM.nextInt(config.getConfig().size())); 
    RPGRuneStat runeStat = RPGRuneStat.create();
    int numRune = (int)Math.pow(2.0D, tier);
    List<Float> coefs = new ArrayList<>();
    for (int i = 0; i < numRune; i++)
      coefs.add(Float.valueOf(RANDOM.nextInt(101) / 100.0F)); 
    runeStat.setCoefs(coefs);
    runeStat.setMutationType(randomStat.getMutationType());
    runeStat.setStats(randomStat.getStat());
    return runeStat;
  }
  
  public static boolean areRuneGenerated(@NonNull ItemStack rune) {
    if (rune == null)
      throw new NullPointerException("rune is marked non-null but is null"); 
    if (!isRune(rune))
      return false; 
    return rune.func_77942_o() ? ((rune.func_77978_p().func_74764_b("stat_0") || rune.func_77978_p().func_74764_b("stat_1"))) : false;
  }
  
  public static boolean areRunesSameTier(@NonNull ItemStack rune1, @NonNull ItemStack rune2) {
    if (rune1 == null)
      throw new NullPointerException("rune1 is marked non-null but is null"); 
    if (rune2 == null)
      throw new NullPointerException("rune2 is marked non-null but is null"); 
    if (isRune(rune1) && isRune(rune2))
      return areRunesSameTier((RPGItemRune)rune1.func_77973_b(), (RPGItemRune)rune2.func_77973_b()); 
    return false;
  }
  
  public static boolean areRunesSameTier(@NonNull RPGItemRune rune1, @NonNull RPGItemRune rune2) {
    if (rune1 == null)
      throw new NullPointerException("rune1 is marked non-null but is null"); 
    if (rune2 == null)
      throw new NullPointerException("rune2 is marked non-null but is null"); 
    int rune1Tier = rune1.getItemData().getTier();
    int rune2Tier = rune2.getItemData().getTier();
    return (rune1Tier == rune2Tier);
  }
  
  public static List<ItemStatMutator> getRuneStats(@NonNull ItemStack item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    List<ItemStatMutator> runeStats = new ArrayList<>();
    if (!item.func_77942_o())
      return runeStats; 
    if (IRPGItem.is(item)) {
      IRPGItem rpgItem = IRPGItem.get(item).get();
      int rune = 0;
      if (rpgItem instanceof RPGItemSword) {
        rune = ((RPGItemSword)rpgItem).getItemData().getRuneSlot();
      } else if (rpgItem instanceof RPGItemArmor) {
        rune = ((RPGItemArmor)rpgItem).getArmor().getRuneSlot();
      } 
      if (rune > 0)
        for (int i = 0; i < rune; i++) {
          if (item.func_77978_p().func_74764_b("rune_" + i)) {
            NBTTagCompound runeNBT = item.func_77978_p().func_74775_l("rune_" + i);
            int tier = runeNBT.func_74762_e("tier");
            if (runeNBT.func_74764_b("stat_0")) {
              RPGRuneStat runeStat1 = RPGRuneStat.create();
              runeStat1.read(runeNBT.func_74775_l("stat_0"));
              ItemStatMutator newMutator = runeStat1.getMutator(tier);
              boolean merged = false;
              for (ItemStatMutator mut : runeStats) {
                if (mut.getStatName() == newMutator.getStatName() && mut.getMutator().getType() == newMutator.getMutator().getType()) {
                  mut.getMutator().setValue((JsonElement)new JsonPrimitive(Double.valueOf(mut.getMutator().getValue().getAsDouble() + newMutator.getMutator().getValue().getAsDouble())));
                  merged = true;
                } 
              } 
              if (!merged)
                runeStats.add(newMutator); 
            } 
            if (runeNBT.func_74764_b("stat_1")) {
              RPGRuneStat runeStat2 = RPGRuneStat.create();
              runeStat2.read(runeNBT.func_74775_l("stat_1"));
              ItemStatMutator newMutator = runeStat2.getMutator(tier);
              boolean merged = false;
              for (ItemStatMutator mut : runeStats) {
                if (mut.getStatName() == newMutator.getStatName() && mut.getMutator().getType() == newMutator.getMutator().getType()) {
                  mut.getMutator().setValue((JsonElement)new JsonPrimitive(Double.valueOf(mut.getMutator().getValue().getAsDouble() + newMutator.getMutator().getValue().getAsDouble())));
                  merged = true;
                } 
              } 
              if (!merged)
                runeStats.add(newMutator); 
            } 
          } 
        }  
    } 
    return runeStats;
  }
  
  public static boolean isRune(@NonNull ItemStack rune) {
    if (rune == null)
      throw new NullPointerException("rune is marked non-null but is null"); 
    return rune.func_77973_b() instanceof RPGItemRune;
  }
  
  public static void setConfig(RPGRuneConfigData configData) {
    config = configData;
  }
  
  public static RPGItemRune getRuneItemByTier(int tier) {
    if (tier == 0)
      return (RPGItemRune)ItemsRegister.DUNGEON_RUNE_TIER_0; 
    if (tier == 1)
      return (RPGItemRune)ItemsRegister.DUNGEON_RUNE_TIER_1; 
    if (tier == 2)
      return (RPGItemRune)ItemsRegister.DUNGEON_RUNE_TIER_2; 
    if (tier == 3)
      return (RPGItemRune)ItemsRegister.DUNGEON_RUNE_TIER_3; 
    return null;
  }
  
  public static int getFirstFreeRuneSlot(ItemStack rpgItem) {
    IRPGItem rpg = IRPGItem.get(rpgItem).get();
    int runeCount = -1;
    if (rpg instanceof RPGItemSword) {
      RPGItemSword sword = (RPGItemSword)rpg;
      runeCount = sword.getItemData().getRuneSlot();
    } 
    if (rpg instanceof RPGItemArmor) {
      RPGItemArmor armor = (RPGItemArmor)rpg;
      runeCount = armor.getArmor().getRuneSlot();
    } 
    if (runeCount <= 0)
      return -1; 
    if (!rpgItem.func_77942_o())
      return 0; 
    for (int i = 0; i < runeCount; i++) {
      if (!rpgItem.func_77978_p().func_74764_b("rune_" + i))
        return i; 
    } 
    return -1;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\manager\EquipmentRuneManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */