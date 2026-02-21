package fr.paladium.achievement.core.pojo;

import fr.paladium.achievement.core.proxy.CommonProxy;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public enum AchievementCategory {
  HOW_TO_START(1, "Premiers pas", CommonProxy.getBase64FromItemStack(new ItemStack(Items.field_151055_y))),
  JOBS(2, "Métiers", CommonProxy.getBase64FromItemStack(new ItemStack(Items.field_151050_s))),
  FACTION(3, "Faction", CommonProxy.getBase64FromItemStack(new ItemStack(Items.field_151048_u))),
  ATTACK_DEFENSE(4, "Pillage & Défense", CommonProxy.getBase64FromItemStack(new ItemStack(Blocks.field_150335_W))),
  ECONOMY(5, "Économie", CommonProxy.getBase64FromItemStack(new ItemStack(Items.field_151043_k))),
  OTHERS(7, "Divers", CommonProxy.getBase64FromItemStack(new ItemStack(Items.field_151079_bi)));
  
  private final int id;
  
  private final String name;
  
  private final String icon;
  
  public int getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getIcon() {
    return this.icon;
  }
  
  AchievementCategory(int id, String name, String icon) {
    this.id = id;
    this.name = name;
    this.icon = icon;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\pojo\AchievementCategory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */