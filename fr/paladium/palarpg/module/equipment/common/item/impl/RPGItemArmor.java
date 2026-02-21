package fr.paladium.palarpg.module.equipment.common.item.impl;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.lang.CustomLanguageManager;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.equipment.common.EquipmentCommonProxy;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.RPGArmorType;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import fr.paladium.palarpg.module.equipment.common.item.RPGStatApplyType;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGItemData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGArmorItemData;
import fr.paladium.palarpg.module.equipment.common.loader.util.ItemStatMutator;
import fr.paladium.palarpg.module.equipment.common.rune.RPGRuneStat;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.AStatCapability;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatCapabilityMutator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

public class RPGItemArmor extends ItemArmor implements IRPGItem {
  private final RPGArmorItemData itemData;
  
  private final RPGArmorType type;
  
  private final RPGArmorItemData.RPGArmor armor;
  
  public RPGArmorType getType() {
    return this.type;
  }
  
  public RPGArmorItemData.RPGArmor getArmor() {
    return this.armor;
  }
  
  public RPGItemArmor(RPGArmorType type, RPGItemData itemData) {
    super(ItemArmor.ArmorMaterial.DIAMOND, 0, type.ordinal());
    this.itemData = (RPGArmorItemData)itemData;
    this.type = type;
    this.armor = (RPGArmorItemData.RPGArmor)getItemData().getArmors().get(this.type);
    func_77655_b(getId());
    func_77637_a(EquipmentCommonProxy.getRPGArmorTab());
    func_77625_d(1);
    func_77656_e(this.armor.getDurability());
  }
  
  public void register() {
    RegistryUtils.item(new Item[] { (Item)this });
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
      RPGArmorItemData armorItemData = getItemData();
      RPGArmorItemData.RPGArmor armorPieceData = (RPGArmorItemData.RPGArmor)armorItemData.getArmors().get(this.type);
      CustomLanguageManager.register("fr_FR", func_77658_a() + ".name", armorPieceData.getTranslation("fr").trim().replace("{itemName}", getItemData().getTranslation("fr")));
      CustomLanguageManager.register("en_US", func_77658_a() + ".name", armorPieceData.getTranslation("en").trim().replace("{itemName}", getItemData().getTranslation("en")));
    } 
  }
  
  public RPGArmorItemData getItemData() {
    return this.itemData;
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
  
  public String getId() {
    return getItemData().getId().trim() + "_" + this.type.name().toLowerCase();
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon() {
    return getItemData().getIcon(this.type);
  }
  
  public IIcon func_77617_a(int damage) {
    return getIcon();
  }
  
  public boolean isValidArmor(ItemStack stack, int armorType, Entity entity) {
    return (armorType == this.type.ordinal());
  }
  
  public void applyStats(ItemStack stack, EntityLivingBase entity, RPGStatApplyType type) {
    if (!PalaRPGMod.proxy.isDungeonWorld())
      return; 
    RPGProfilePlayerData profile = (RPGProfilePlayerData)RPGPlayer.getData((Entity)entity, "profile");
    if (profile == null)
      return; 
    if (getArmor().getRpgRequiredLevel() > 0) {
      if (profile.getLevel() < getArmor().getRpgRequiredLevel())
        return; 
    } else if (profile.getLevel() < getItemData().getRequiredLevel()) {
      return;
    } 
    RPGArmorItemData.RPGArmor data = (RPGArmorItemData.RPGArmor)getItemData().getArmors().get(this.type);
    RPGStatPlayerData entityStat = (RPGStatPlayerData)RPGPlayer.getData((Entity)entity, "stats");
    if (entityStat == null)
      return; 
    data.getMutators().forEach((equipType, mutList) -> {
          if (type != equipType)
            return; 
          mutList.forEach(());
        });
    if (type == RPGStatApplyType.WEAR && stack.func_77942_o() && getArmor().getRuneSlot() > 0) {
      NBTTagCompound tag = stack.func_77978_p();
      for (int i = 0; i < getArmor().getRuneSlot(); i++) {
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
    RPGArmorItemData.RPGArmor data = (RPGArmorItemData.RPGArmor)getItemData().getArmors().get(this.type);
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
      for (int i = 0; i < getArmor().getRuneSlot(); i++) {
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
  
  public RPGItemRarity getRPGRarity() {
    return getItemData().getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\item\impl\RPGItemArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */