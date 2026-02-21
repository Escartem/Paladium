package fr.paladium.palaclicker.common.network.data;

import com.google.gson.Gson;
import fr.paladium.palaclicker.PalaClickerMod;
import fr.paladium.palaclicker.server.config.building.dto.ClickerBuilding;
import fr.paladium.palaclicker.server.config.shop.dto.ClickerShopItem;
import fr.paladium.palaclicker.server.config.shop.dto.ClickerShopType;
import fr.paladium.palaclicker.server.config.upgrade.dto.ClickerUpgrade;
import fr.paladium.palaclicker.server.config.upgrade.dto.ClickerUpgradeType;
import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import fr.paladium.palaforgeutils.lib.time.TimeUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class PlayerClickerData extends ExtendedEntityProperties {
  public static final String PROP_NAME = "clicker_data";
  
  public void setFirstUse(boolean firstUse) {
    this.firstUse = firstUse;
  }
  
  public void setBuildings(Map<String, Integer> buildings) {
    this.buildings = buildings;
  }
  
  public void setBuildingProduction(Map<String, Double> buildingProduction) {
    this.buildingProduction = buildingProduction;
  }
  
  public void setUpgrades(Set<String> upgrades) {
    this.upgrades = upgrades;
  }
  
  public void setShopItems(Map<ClickerShopType, List<ClickerShopItem>> shopItems) {
    this.shopItems = shopItems;
  }
  
  public void setShopLastUpdate(Map<ClickerShopType, Long> shopLastUpdate) {
    this.shopLastUpdate = shopLastUpdate;
  }
  
  public void setShopQuantity(Map<ClickerShopType, Map<String, Integer>> shopQuantity) {
    this.shopQuantity = shopQuantity;
  }
  
  public void setLastBuildingBought(String lastBuildingBought) {
    this.lastBuildingBought = lastBuildingBought;
  }
  
  public void setTotalPoints(long totalPoints) {
    this.totalPoints = totalPoints;
  }
  
  public void setRps(double rps) {
    this.rps = rps;
  }
  
  private static final Gson GSON = new Gson();
  
  public boolean isFirstUse() {
    return this.firstUse;
  }
  
  public Map<String, Integer> getBuildings() {
    return this.buildings;
  }
  
  public Map<String, Double> getBuildingProduction() {
    return this.buildingProduction;
  }
  
  public Set<String> getUpgrades() {
    return this.upgrades;
  }
  
  public Map<ClickerShopType, List<ClickerShopItem>> getShopItems() {
    return this.shopItems;
  }
  
  public Map<ClickerShopType, Long> getShopLastUpdate() {
    return this.shopLastUpdate;
  }
  
  public Map<ClickerShopType, Map<String, Integer>> getShopQuantity() {
    return this.shopQuantity;
  }
  
  public String getLastBuildingBought() {
    return this.lastBuildingBought;
  }
  
  public long getTotalPoints() {
    return this.totalPoints;
  }
  
  public double getPoints() {
    return this.points;
  }
  
  public double getRps() {
    return this.rps;
  }
  
  public long getLastClick() {
    return this.lastClick;
  }
  
  private boolean firstUse = true;
  
  private Map<String, Integer> buildings = new ConcurrentHashMap<>();
  
  private Map<String, Double> buildingProduction = new ConcurrentHashMap<>();
  
  private Set<String> upgrades = new LinkedHashSet<>();
  
  private Map<ClickerShopType, List<ClickerShopItem>> shopItems = new ConcurrentHashMap<>();
  
  private Map<ClickerShopType, Long> shopLastUpdate = new ConcurrentHashMap<>();
  
  private Map<ClickerShopType, Map<String, Integer>> shopQuantity = new ConcurrentHashMap<>();
  
  private String lastBuildingBought;
  
  private long totalPoints;
  
  private double points;
  
  private double rps;
  
  private long lastClick;
  
  public static PlayerClickerData get(Entity entity) {
    return (PlayerClickerData)entity.getExtendedProperties("clicker_data");
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    NBTTagCompound data = compound.func_74775_l("clicker_data");
    if (data.func_150297_b("first_use", 1)) {
      this.firstUse = data.func_74767_n("first_use");
    } else {
      this.firstUse = true;
    } 
    this.buildings = new ConcurrentHashMap<>();
    this.buildingProduction = new HashMap<>();
    this.upgrades = new LinkedHashSet<>();
    NBTTagList buildings = data.func_150295_c("buildings", 10);
    NBTTagList buildingsProduction = data.func_150295_c("buildings_production", 10);
    NBTTagList upgrades = data.func_150295_c("upgrades", 8);
    int i;
    for (i = 0; i < buildings.func_74745_c(); i++) {
      NBTTagCompound building = buildings.func_150305_b(i);
      this.buildings.put(building.func_74779_i("id"), Integer.valueOf(building.func_74762_e("count")));
    } 
    for (i = 0; i < buildingsProduction.func_74745_c(); i++) {
      NBTTagCompound buildingProduction = buildingsProduction.func_150305_b(i);
      this.buildingProduction.put(buildingProduction.func_74779_i("id"), Double.valueOf(buildingProduction.func_74769_h("production")));
    } 
    for (i = 0; i < upgrades.func_74745_c(); i++)
      this.upgrades.add(upgrades.func_150307_f(i)); 
    this.shopItems = new HashMap<>();
    this.shopLastUpdate = new HashMap<>();
    this.shopQuantity = new HashMap<>();
    NBTTagCompound shopItems = data.func_74775_l("shop_items");
    NBTTagCompound shopLastUpdate = data.func_74775_l("shop_last_update");
    NBTTagCompound shopQuantity = data.func_74775_l("shop_quantity");
    for (ClickerShopType type : ClickerShopType.values()) {
      if (shopItems.func_150297_b(type.name(), 9)) {
        NBTTagList items = shopItems.func_150295_c(type.name(), 10);
        this.shopItems.put(type, new ArrayList<>());
        for (int j = 0; j < items.func_74745_c(); j++) {
          NBTTagCompound item = items.func_150305_b(j);
          ((List<Object>)this.shopItems.get(type)).add(GSON.fromJson(item.func_74779_i("item"), ClickerShopItem.class));
        } 
        this.shopLastUpdate.put(type, Long.valueOf(shopLastUpdate.func_74763_f(type.name())));
        if (shopQuantity.func_150297_b(type.name(), 9)) {
          NBTTagList quantity = shopQuantity.func_150295_c(type.name(), 10);
          this.shopQuantity.put(type, new HashMap<>());
          for (int k = 0; k < quantity.func_74745_c(); k++) {
            NBTTagCompound item = quantity.func_150305_b(k);
            ((Map<String, Integer>)this.shopQuantity.get(type)).put(item.func_74779_i("id"), Integer.valueOf(item.func_74762_e("count")));
          } 
        } 
      } 
    } 
    this.totalPoints = data.func_74763_f("total_points");
    this.points = data.func_74769_h("points");
    this.rps = data.func_74764_b("rps") ? data.func_74769_h("rps") : 0.0D;
    if (data.func_150297_b("last_building", 8))
      this.lastBuildingBought = data.func_74779_i("last_building"); 
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound data = new NBTTagCompound();
    data.func_74757_a("first_use", this.firstUse);
    NBTTagList buildings = new NBTTagList();
    NBTTagList buildingsProduction = new NBTTagList();
    NBTTagList upgrades = new NBTTagList();
    for (String id : this.buildings.keySet()) {
      NBTTagCompound building = new NBTTagCompound();
      building.func_74778_a("id", id);
      building.func_74768_a("count", ((Integer)this.buildings.get(id)).intValue());
      buildings.func_74742_a((NBTBase)building);
    } 
    for (String id : this.buildingProduction.keySet()) {
      NBTTagCompound buildingProduction = new NBTTagCompound();
      buildingProduction.func_74778_a("id", id);
      buildingProduction.func_74780_a("production", ((Double)this.buildingProduction.get(id)).doubleValue());
      buildingsProduction.func_74742_a((NBTBase)buildingProduction);
    } 
    Iterator<String> iterator = (new LinkedHashSet<>(this.upgrades)).iterator();
    while (iterator.hasNext())
      upgrades.func_74742_a((NBTBase)new NBTTagString(iterator.next())); 
    NBTTagCompound shopItems = new NBTTagCompound();
    NBTTagCompound shopLastUpdate = new NBTTagCompound();
    NBTTagCompound shopQuantity = new NBTTagCompound();
    for (ClickerShopType type : ClickerShopType.values()) {
      if (this.shopItems.containsKey(type)) {
        NBTTagList items = new NBTTagList();
        for (ClickerShopItem item : this.shopItems.get(type)) {
          NBTTagCompound itemCompound = new NBTTagCompound();
          itemCompound.func_74778_a("item", GSON.toJson(item));
          items.func_74742_a((NBTBase)itemCompound);
        } 
        shopItems.func_74782_a(type.name(), (NBTBase)items);
        shopLastUpdate.func_74772_a(type.name(), ((Long)this.shopLastUpdate.get(type)).longValue());
        if (this.shopQuantity.containsKey(type)) {
          NBTTagList quantity = new NBTTagList();
          for (String id : ((Map)this.shopQuantity.get(type)).keySet()) {
            NBTTagCompound itemCompound = new NBTTagCompound();
            itemCompound.func_74778_a("id", id);
            itemCompound.func_74768_a("count", ((Integer)((Map)this.shopQuantity.get(type)).get(id)).intValue());
            quantity.func_74742_a((NBTBase)itemCompound);
          } 
          shopQuantity.func_74782_a(type.name(), (NBTBase)quantity);
        } 
      } 
    } 
    data.func_74782_a("buildings", (NBTBase)buildings);
    data.func_74782_a("buildings_production", (NBTBase)buildingsProduction);
    data.func_74782_a("upgrades", (NBTBase)upgrades);
    data.func_74782_a("shop_items", (NBTBase)shopItems);
    data.func_74782_a("shop_last_update", (NBTBase)shopLastUpdate);
    data.func_74782_a("shop_quantity", (NBTBase)shopQuantity);
    data.func_74772_a("total_points", this.totalPoints);
    data.func_74780_a("points", this.points);
    data.func_74780_a("rps", this.rps);
    if (this.lastBuildingBought != null)
      data.func_74778_a("last_building", this.lastBuildingBought); 
    compound.func_74782_a("clicker_data", (NBTBase)data);
  }
  
  public void addBuilding(String id) {
    this.buildings.put(id, Integer.valueOf(getBuildingCount(id) + 1));
    sync();
  }
  
  public int getBuildingCount(String id) {
    return ((Integer)this.buildings.getOrDefault(id, Integer.valueOf(0))).intValue();
  }
  
  public boolean hasBuilding(String id) {
    return this.buildings.containsKey(id);
  }
  
  public double getBuildingProduction(String id) {
    return ((Double)this.buildingProduction.getOrDefault(id, Double.valueOf(0.0D))).doubleValue();
  }
  
  public void setBuildingProduction(String id, double production) {
    this.buildingProduction.put(id, Double.valueOf(production));
    sync();
  }
  
  public void addBuildingProduction(String id, double production) {
    this.buildingProduction.put(id, Double.valueOf(getBuildingProduction(id) + production));
  }
  
  public void addUpgrade(String id) {
    this.upgrades.add(id);
    sync();
  }
  
  public boolean hasUpgrade(String id) {
    return this.upgrades.contains(id);
  }
  
  public void addPoints(double points) {
    this.points += points;
    if (points > 0.0D)
      this.totalPoints = (long)(this.totalPoints + points); 
    sync();
  }
  
  public void setPoints(double points) {
    this.points = points;
    sync();
  }
  
  public ItemStack getClickItem(List<ClickerUpgrade> upgradeList) {
    ItemStack item = new ItemStack(Items.field_151034_e);
    if (upgradeList == null || upgradeList.isEmpty())
      return item; 
    Iterator<String> iterator = (new LinkedHashSet<>(this.upgrades)).iterator();
    while (iterator.hasNext()) {
      String upgradeId = iterator.next();
      Optional<ClickerUpgrade> optionalUpgrade = upgradeList.stream().filter(u -> u.getId().equals(upgradeId)).findAny();
      if (!optionalUpgrade.isPresent())
        continue; 
      ClickerUpgrade upgrade = optionalUpgrade.get();
      if (upgrade.getType() != ClickerUpgradeType.CLICK)
        continue; 
      item = upgrade.getItemStack();
    } 
    return item;
  }
  
  public double getClickValue() {
    double percentage = 0.0D;
    Iterator<String> iterator = (new LinkedHashSet<>(this.upgrades)).iterator();
    while (iterator.hasNext()) {
      String upgradeId = iterator.next();
      Optional<ClickerUpgrade> upgrade = PalaClickerMod.getServer().getUpgradeConfig().getUpgrade(upgradeId);
      if (!upgrade.isPresent())
        continue; 
      percentage += ((ClickerUpgrade)upgrade.get()).applyClick();
    } 
    return 1.0D * (1.0D + percentage);
  }
  
  public double getRPS(ClickerBuilding building, List<ClickerBuilding> buildingList, List<ClickerUpgrade> upgradeList) {
    if (!hasBuilding(building.getId()))
      return 0.0D; 
    int count = ((Integer)this.buildings.get(building.getId())).intValue();
    if (count <= 0)
      return 0.0D; 
    double percentage = 0.0D;
    Iterator<String> iterator = (new LinkedHashSet<>(this.upgrades)).iterator();
    while (iterator.hasNext()) {
      String upgradeId = iterator.next();
      Optional<ClickerUpgrade> upgrade = upgradeList.stream().filter(u -> u.getId().equals(upgradeId)).findAny();
      if (!upgrade.isPresent())
        continue; 
      percentage += ((ClickerUpgrade)upgrade.get()).apply(building, count, this, buildingList);
    } 
    return (building.getRPS() * count) * (1.0D + percentage);
  }
  
  public double getRPSBase(ClickerBuilding building, List<ClickerBuilding> buildingList, List<ClickerUpgrade> upgradeList) {
    int count = ((Integer)this.buildings.getOrDefault(building.getId(), Integer.valueOf(0))).intValue();
    double percentage = 0.0D;
    Iterator<String> iterator = (new LinkedHashSet<>(this.upgrades)).iterator();
    while (iterator.hasNext()) {
      String upgradeId = iterator.next();
      Optional<ClickerUpgrade> upgrade = upgradeList.stream().filter(u -> u.getId().equals(upgradeId)).findAny();
      if (!upgrade.isPresent())
        continue; 
      percentage += ((ClickerUpgrade)upgrade.get()).apply(building, count, this, buildingList);
    } 
    return building.getRPS() * (1.0D + percentage);
  }
  
  public double getRPS(List<ClickerBuilding> buildingList, List<ClickerUpgrade> upgradeList) {
    double totalRps = 0.5D;
    for (String buildingId : this.buildings.keySet()) {
      Optional<ClickerBuilding> building = buildingList.stream().filter(b -> b.getId().equals(buildingId)).findAny();
      if (!building.isPresent())
        continue; 
      totalRps += getRPS(building.get(), buildingList, upgradeList);
    } 
    return totalRps;
  }
  
  public void setLastClick(long lastClick) {
    this.lastClick = lastClick;
  }
  
  public void clear() {
    this.buildings.clear();
    this.buildingProduction.clear();
    this.upgrades.clear();
    this.shopItems.clear();
    this.shopLastUpdate.clear();
    this.shopQuantity.clear();
    this.totalPoints = 0L;
    this.points = 0.0D;
    sync();
  }
  
  public List<ClickerShopItem> getShopItems(ClickerShopType type) {
    if (!this.shopItems.containsKey(type))
      generateShopItems(type); 
    long now = TimeUtils.now();
    int hours = (int)((now - ((Long)this.shopLastUpdate.get(type)).longValue()) / 60L / 60L);
    if (hours > type.getDuration())
      generateShopItems(type); 
    return this.shopItems.getOrDefault(type, new ArrayList<>());
  }
  
  public void generateShopItems(ClickerShopType type) {
    List<ClickerShopItem> items = new ArrayList<>(PalaClickerMod.getServer().getShopConfig().getRandomItem(type, type.getSize(), new HashMap<>(this.buildings)));
    this.shopItems.put(type, items);
    this.shopLastUpdate.put(type, Long.valueOf(TimeUtils.now()));
    this.shopQuantity.put(type, new HashMap<>());
    sync();
  }
  
  public int getShopQuantity(ClickerShopType type, String id) {
    if (!this.shopQuantity.containsKey(type))
      return 0; 
    return ((Integer)((Map)this.shopQuantity.get(type)).getOrDefault(id, Integer.valueOf(0))).intValue();
  }
  
  public void increaseShopQuantity(ClickerShopType type, String id) {
    if (!this.shopQuantity.containsKey(type))
      this.shopQuantity.put(type, new HashMap<>()); 
    ((Map<String, Integer>)this.shopQuantity.get(type)).put(id, Integer.valueOf(getShopQuantity(type, id) + 1));
    sync();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\common\network\data\PlayerClickerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */