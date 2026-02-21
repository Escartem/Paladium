package fr.paladium.pet.server.skill.skill;

import fr.paladium.palaconfiguration.server.strategy.PrintExclude;
import fr.paladium.pet.PetLogger;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.skill.SkillData;
import fr.paladium.pet.server.skill.handler.ASkillHandler;
import fr.paladium.translate.common.texttotranslate.TTT;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class Skill {
  public static final int NO_REQUIRED_LEVEL = -1;
  
  private String id;
  
  private String icon;
  
  private SkillType type;
  
  private SkillCategory category;
  
  private int requiredLevel;
  
  private double value;
  
  private int cooldownInMinutes;
  
  @PrintExclude
  private transient ASkillHandler handler;
  
  public void setId(String id) {
    this.id = id;
  }
  
  public void setIcon(String icon) {
    this.icon = icon;
  }
  
  public void setType(SkillType type) {
    this.type = type;
  }
  
  public void setCategory(SkillCategory category) {
    this.category = category;
  }
  
  public void setRequiredLevel(int requiredLevel) {
    this.requiredLevel = requiredLevel;
  }
  
  public void setValue(double value) {
    this.value = value;
  }
  
  public void setCooldownInMinutes(int cooldownInMinutes) {
    this.cooldownInMinutes = cooldownInMinutes;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof Skill))
      return false; 
    Skill other = (Skill)o;
    if (!other.canEqual(this))
      return false; 
    if (getRequiredLevel() != other.getRequiredLevel())
      return false; 
    if (Double.compare(getValue(), other.getValue()) != 0)
      return false; 
    if (getCooldownInMinutes() != other.getCooldownInMinutes())
      return false; 
    Object this$id = getId(), other$id = other.getId();
    if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id))
      return false; 
    Object this$icon = getIcon(), other$icon = other.getIcon();
    if ((this$icon == null) ? (other$icon != null) : !this$icon.equals(other$icon))
      return false; 
    Object this$type = getType(), other$type = other.getType();
    if ((this$type == null) ? (other$type != null) : !this$type.equals(other$type))
      return false; 
    Object this$category = getCategory(), other$category = other.getCategory();
    return !((this$category == null) ? (other$category != null) : !this$category.equals(other$category));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof Skill;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + getRequiredLevel();
    long $value = Double.doubleToLongBits(getValue());
    result = result * 59 + (int)($value >>> 32L ^ $value);
    result = result * 59 + getCooldownInMinutes();
    Object $id = getId();
    result = result * 59 + (($id == null) ? 43 : $id.hashCode());
    Object $icon = getIcon();
    result = result * 59 + (($icon == null) ? 43 : $icon.hashCode());
    Object $type = getType();
    result = result * 59 + (($type == null) ? 43 : $type.hashCode());
    Object $category = getCategory();
    return result * 59 + (($category == null) ? 43 : $category.hashCode());
  }
  
  public String toString() {
    return "Skill(id=" + getId() + ", icon=" + getIcon() + ", type=" + getType() + ", category=" + getCategory() + ", requiredLevel=" + getRequiredLevel() + ", value=" + getValue() + ", cooldownInMinutes=" + getCooldownInMinutes() + ", handler=" + getHandler() + ")";
  }
  
  public Skill(String id, String icon, SkillType type, SkillCategory category, int requiredLevel, double value, int cooldownInMinutes, ASkillHandler handler) {
    this.id = id;
    this.icon = icon;
    this.type = type;
    this.category = category;
    this.requiredLevel = requiredLevel;
    this.value = value;
    this.cooldownInMinutes = cooldownInMinutes;
    this.handler = handler;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getIcon() {
    return this.icon;
  }
  
  public SkillType getType() {
    return this.type;
  }
  
  public SkillCategory getCategory() {
    return this.category;
  }
  
  public int getRequiredLevel() {
    return this.requiredLevel;
  }
  
  public double getValue() {
    return this.value;
  }
  
  public int getCooldownInMinutes() {
    return this.cooldownInMinutes;
  }
  
  public ASkillHandler getHandler() {
    return this.handler;
  }
  
  public void handle(EntityPlayerMP player, PetPlayer pet, SkillData data) {
    if (this.handler == null) {
      PetTranslateEnum.MESSAGE_SKILL_NO_FOUND.message((ICommandSender)player, new Object[] { this.id });
      PetLogger.error("No handler for skill " + this.id);
      return;
    } 
    this.handler.handle(player, pet, data);
  }
  
  public void setHandler(ASkillHandler handler) {
    this.handler = handler;
    PetLogger.info("SKILL => Set handler " + handler.getClass().getSimpleName() + " for skill " + this.id);
  }
  
  public boolean hasRequiredLevel(int level) {
    return (level >= this.requiredLevel);
  }
  
  public double getPersonalValue(PetPlayer pet) {
    return pet.getSkillValue(this);
  }
  
  public String getName(EntityPlayer player) {
    return TTT.format(player, getTTTName(), new Object[0]);
  }
  
  public String getTTTName() {
    return "pet.skill." + this.id + ".name";
  }
  
  public String getTTTDescription() {
    return "pet.skill." + this.id + ".description";
  }
  
  public String getFormattedDescription(EntityPlayer player, double value) {
    String description = getTTTDescription();
    try {
      String formattedValue = formatNumber(value);
      return TTT.format(player, description, new Object[] { formattedValue });
    } catch (Exception exception) {
      try {
        return TTT.format(player, description, new Object[0]);
      } catch (Exception exception1) {
        return description;
      } 
    } 
  }
  
  private String formatNumber(double number) {
    return (number % 1.0D == 0.0D) ? String.format("%d", new Object[] { Integer.valueOf((int)number) }) : String.format("%.1f", new Object[] { Double.valueOf(number) });
  }
  
  public long getCooldown(long now, long nextUseMillis) {
    return nextUseMillis - now;
  }
  
  public boolean isOnCooldown(long cooldown) {
    return (cooldown > 0L);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\skill\Skill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */