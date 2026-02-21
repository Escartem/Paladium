package fr.paladium.palaclicker.server.config.upgrade.dto;

import com.google.gson.internal.LinkedTreeMap;
import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.palaclicker.server.config.building.dto.ClickerBuilding;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.palaforgeutils.lib.time.TimeUtils;
import fr.paladium.palajobs.api.PalaJobsAPI;
import fr.paladium.palajobs.api.player.IJobsPlayer;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ClickerUpgrade {
  private final String id;
  
  private final String image;
  
  private final String item;
  
  private final double price;
  
  private final ClickerUpgradeType type;
  
  private final String target;
  
  private final double modifier;
  
  private final Object additionalData;
  
  private final List<ClickerUpgradeConditionObject> conditions;
  
  private transient ItemStack itemStack;
  
  public void setItemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
  }
  
  public ClickerUpgrade(String id, String image, String item, double price, ClickerUpgradeType type, String target, double modifier, Object additionalData, List<ClickerUpgradeConditionObject> conditions) {
    this.id = id;
    this.image = image;
    this.item = item;
    this.price = price;
    this.type = type;
    this.target = target;
    this.modifier = modifier;
    this.additionalData = additionalData;
    this.conditions = conditions;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getImage() {
    return this.image;
  }
  
  public String getItem() {
    return this.item;
  }
  
  public double getPrice() {
    return this.price;
  }
  
  public ClickerUpgradeType getType() {
    return this.type;
  }
  
  public String getTarget() {
    return this.target;
  }
  
  public double getModifier() {
    return this.modifier;
  }
  
  public Object getAdditionalData() {
    return this.additionalData;
  }
  
  public List<ClickerUpgradeConditionObject> getConditions() {
    return this.conditions;
  }
  
  public ItemStack getItemStack() {
    return this.itemStack;
  }
  
  public void make() {
    if (this.item == null || this.item.isEmpty())
      return; 
    this.itemStack = ItemStackSerializer.read(new String(Base64.getDecoder().decode(this.item.getBytes()), StandardCharsets.UTF_8));
  }
  
  public boolean checkConditions(PlayerClickerData data) {
    if (this.conditions == null || this.conditions.isEmpty())
      return true; 
    for (ClickerUpgradeConditionObject condition : this.conditions) {
      ClickerUpgradeCondition type = condition.getType();
      Object value = condition.getValue();
      if (value == null)
        continue; 
      if (type == ClickerUpgradeCondition.QUANTITY) {
        double mappedValue = ((Double)value).doubleValue();
        if (data.getTotalPoints() < mappedValue)
          return false; 
        continue;
      } 
      if (type == ClickerUpgradeCondition.TIME) {
        long mappedValue = ((Double)value).longValue();
        long days = (TimeUtils.now() - 1763748000L) / 60L / 60L / 24L;
        if (days < mappedValue && days >= 0L)
          return false; 
        continue;
      } 
      if (type == ClickerUpgradeCondition.BUILDING) {
        LinkedTreeMap<String, Object> mappedValue = (LinkedTreeMap<String, Object>)value;
        String buildingId = mappedValue.get("id").toString();
        double buildingCount = Double.parseDouble(mappedValue.get("count").toString());
        if (data.getBuildingCount(buildingId) < buildingCount)
          return false; 
        continue;
      } 
      if (type == ClickerUpgradeCondition.UPGRADE) {
        String mappedValue = (String)value;
        if (!data.hasUpgrade(mappedValue))
          return false; 
      } 
    } 
    return true;
  }
  
  public double applyClick() {
    if (this.type != ClickerUpgradeType.CLICK)
      return 0.0D; 
    return this.modifier;
  }
  
  public boolean isTarget(ClickerBuilding building) {
    if (this.type == ClickerUpgradeType.TERRAIN)
      return building.getCategories().contains(this.target); 
    if (this.type == ClickerUpgradeType.BUILDING || this.type == ClickerUpgradeType.MANY || this.type == ClickerUpgradeType.POSTERIOR)
      return this.target.equals(building.getId()); 
    if (this.type == ClickerUpgradeType.CATEGORY)
      return building.getCategories().contains(this.target); 
    return false;
  }
  
  public double apply(ClickerBuilding building, int count, PlayerClickerData data, List<ClickerBuilding> buildings) {
    if (!isTarget(building) && this.type != ClickerUpgradeType.GLOBAL)
      return 0.0D; 
    if (this.type == ClickerUpgradeType.GLOBAL)
      return this.modifier; 
    if (this.type == ClickerUpgradeType.TERRAIN) {
      Optional<IJobsPlayer> optJobsPlayer = PalaJobsAPI.inst().getJobsPlayer((EntityPlayer)data.getEntity());
      if (!optJobsPlayer.isPresent())
        return 0.0D; 
      return this.modifier * ((IJobsPlayer)optJobsPlayer.get()).getLevel(JobType.valueOf(this.additionalData.toString().toUpperCase()));
    } 
    if (this.type == ClickerUpgradeType.BUILDING)
      return this.modifier; 
    if (this.type == ClickerUpgradeType.MANY)
      return this.modifier * count; 
    if (this.type == ClickerUpgradeType.POSTERIOR) {
      int posteriorIndex = building.getIndex() - 1;
      if (posteriorIndex < 0)
        return 0.0D; 
      Optional<ClickerBuilding> posterior = buildings.stream().filter(b -> (b.getIndex() == posteriorIndex)).findAny();
      if (!posterior.isPresent())
        return 0.0D; 
      return this.modifier * data.getBuildingCount(((ClickerBuilding)posterior.get()).getId());
    } 
    if (this.type == ClickerUpgradeType.CATEGORY)
      return this.modifier; 
    return 0.0D;
  }
  
  public String getName() {
    return TTT.format("clicker.upgrade." + this.id, new Object[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\server\confi\\upgrade\dto\ClickerUpgrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */