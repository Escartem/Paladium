package fr.paladium.palamod.modules.ajobs.common.eep;

import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class PalaEnchantEEP extends ExtendedEntityProperties {
  public static final String PROP_NAME = "enchant_eep";
  
  public List<EnchantSeed> enchantSeeds = new ArrayList<>();
  
  public List<EnchantSeed> getEnchantSeeds() {
    return this.enchantSeeds;
  }
  
  public static PalaEnchantEEP get(Entity entity) {
    return (PalaEnchantEEP)entity.getExtendedProperties("enchant_eep");
  }
  
  public EnchantSeed getOrCreateSeed(EnchantSeed enchantSeed) {
    for (EnchantSeed seed : this.enchantSeeds) {
      if (seed.itemStack.func_77973_b().equals(enchantSeed.itemStack.func_77973_b()) && seed.enchantLvl == enchantSeed.enchantLvl && seed.enchantPower == enchantSeed.enchantPower)
        return seed; 
    } 
    this.enchantSeeds.add(enchantSeed);
    return enchantSeed;
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagList enchantSeedsList = new NBTTagList();
    for (EnchantSeed enchantSeed : this.enchantSeeds)
      enchantSeedsList.func_74742_a((NBTBase)enchantSeed.writeToNBT()); 
    compound.func_74782_a("enchantSeeds", (NBTBase)enchantSeedsList);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    NBTTagList enchantSeedsList = compound.func_150295_c("enchantSeeds", 10);
    this.enchantSeeds.clear();
    for (int i = 0; i < enchantSeedsList.func_74745_c(); i++) {
      NBTTagCompound enchantSeedCompound = enchantSeedsList.func_150305_b(i);
      EnchantSeed enchantSeed = new EnchantSeed();
      enchantSeed.readToNBT(enchantSeedCompound);
      this.enchantSeeds.add(enchantSeed);
    } 
  }
  
  public void removeSeed(EnchantSeed enchantSeed) {
    Iterator<EnchantSeed> iterator = this.enchantSeeds.iterator();
    while (iterator.hasNext()) {
      EnchantSeed seed = iterator.next();
      if (seed.itemStack.func_77973_b().equals(enchantSeed.itemStack.func_77973_b()) && seed.enchantLvl == enchantSeed.enchantLvl && seed.enchantPower == enchantSeed.enchantPower)
        iterator.remove(); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\eep\PalaEnchantEEP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */