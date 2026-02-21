package fr.paladium.palarpg.module.equipment.common.item.impl;

import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.equipment.common.EquipmentCommonProxy;
import fr.paladium.palarpg.module.equipment.common.item.RPGStatApplyType;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGItemData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGBasicItemData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGConsumableItemData;
import fr.paladium.palarpg.module.equipment.common.loader.util.ItemStatMutator;
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
import net.minecraft.world.World;

public class RPGItemConsumable extends RPGItem {
  public RPGItemConsumable(RPGItemData itemData) {
    super(itemData);
    func_77637_a(EquipmentCommonProxy.getRPGConsumableTab());
    func_77656_e(Math.max(0, getItemData().getDurability()));
  }
  
  public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player) {
    if (!getItemData().getFoodStats().isInfinite())
      if (func_77645_m()) {
        stack.func_77972_a(1, (EntityLivingBase)player);
      } else {
        stack.field_77994_a--;
      }  
    player.func_71024_bL().func_75122_a(getHealAmount(), getSaturationModifier());
    world.func_72956_a((Entity)player, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
    onFoodEaten(stack, world, player);
    return stack;
  }
  
  protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
    if (player.field_70170_p.field_72995_K)
      return; 
    RPGStatPlayerData playerData = (RPGStatPlayerData)RPGPlayer.getData((Entity)player, "stats");
    if (playerData == null)
      return; 
    getItemData().getMutators().forEach((equipType, mutators) -> {
          if (equipType != RPGStatApplyType.CONSUMABLE)
            return; 
          mutators.forEach(());
        });
    playerData.applyAndSync();
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (!PalaRPGMod.proxy.isDungeonWorld())
      return stack; 
    RPGProfilePlayerData profile = (RPGProfilePlayerData)RPGPlayer.getData((Entity)player, "profile");
    if (profile == null || getItemData().getRequiredLevel() > profile.getLevel())
      return stack; 
    RPGStatPlayerData playerData = (RPGStatPlayerData)RPGPlayer.getData((Entity)player, "stats");
    if (playerData == null || playerData.getCapabilities().stream().filter(capa -> (capa.getMutator(getId()) != null)).findFirst().isPresent())
      return stack; 
    if (player.func_71043_e(isAlwaysEdible()))
      player.func_71008_a(stack, func_77626_a(stack)); 
    return stack;
  }
  
  public EnumAction func_77661_b(ItemStack p_77661_1_) {
    return EnumAction.eat;
  }
  
  public boolean isAlwaysEdible() {
    return getItemData().getFoodStats().isAlwaysEdible();
  }
  
  public int func_77626_a(ItemStack itemStack) {
    return getItemData().getFoodStats().getItemUseDuration();
  }
  
  public int getHealAmount() {
    return getItemData().getFoodStats().getHealAmount();
  }
  
  public float getSaturationModifier() {
    return getItemData().getFoodStats().getSaturationModifier();
  }
  
  public RPGConsumableItemData getItemData() {
    return (RPGConsumableItemData)super.getItemData();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\item\impl\RPGItemConsumable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */