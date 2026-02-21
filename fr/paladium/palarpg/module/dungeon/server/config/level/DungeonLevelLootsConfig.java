package fr.paladium.palarpg.module.dungeon.server.config.level;

import com.eliotlash.molang.MolangException;
import com.eliotlash.molang.MolangParser;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonChest;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DungeonLevelLootsConfig {
  private final List<DungeonLevelLootsElementConfig> loots;
  
  private final Map<TileEntityDungeonChest.DungeonChestRarity, DungeonLevelLootsChestElementConfig> chests;
  
  private final JsonElement premium;
  
  private final JsonElement experience;
  
  @SerializedName("loot_count")
  private final Map<String, JsonElement> lootCount;
  
  @SerializedName("loot_probability")
  private final Map<String, JsonElement> lootProbability;
  
  public String toString() {
    return "DungeonLevelLootsConfig(loots=" + getLoots() + ", chests=" + getChests() + ", premium=" + getPremium() + ", experience=" + getExperience() + ", lootCount=" + getLootCount() + ", lootProbability=" + getLootProbability() + ")";
  }
  
  public DungeonLevelLootsConfig(List<DungeonLevelLootsElementConfig> loots, Map<TileEntityDungeonChest.DungeonChestRarity, DungeonLevelLootsChestElementConfig> chests, JsonElement premium, JsonElement experience, Map<String, JsonElement> lootCount, Map<String, JsonElement> lootProbability) {
    this.loots = loots;
    this.chests = chests;
    this.premium = premium;
    this.experience = experience;
    this.lootCount = lootCount;
    this.lootProbability = lootProbability;
  }
  
  public List<DungeonLevelLootsElementConfig> getLoots() {
    return this.loots;
  }
  
  public Map<TileEntityDungeonChest.DungeonChestRarity, DungeonLevelLootsChestElementConfig> getChests() {
    return this.chests;
  }
  
  public JsonElement getPremium() {
    return this.premium;
  }
  
  public JsonElement getExperience() {
    return this.experience;
  }
  
  public Map<String, JsonElement> getLootCount() {
    return this.lootCount;
  }
  
  public Map<String, JsonElement> getLootProbability() {
    return this.lootProbability;
  }
  
  @NonNull
  public List<DungeonLevelLootsElementConfig> getDefaultLoots() {
    return (List<DungeonLevelLootsElementConfig>)this.loots.stream().filter(DungeonLevelLootsElementConfig::isDefaultType).collect(Collectors.toList());
  }
  
  @NonNull
  public List<DungeonLevelLootsElementConfig> getLoots(@NonNull String type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return (List<DungeonLevelLootsElementConfig>)this.loots.stream().filter(loot -> loot.getType().equalsIgnoreCase(type)).collect(Collectors.toList());
  }
  
  @NonNull
  public Optional<JsonElement> getDefaultLootCount() {
    return getLootCount("default");
  }
  
  @NonNull
  public Optional<JsonElement> getLootCount(@NonNull String type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return Optional.ofNullable(this.lootCount.get(type.toLowerCase()));
  }
  
  @NonNull
  public Optional<JsonElement> getDefaultLootProbability() {
    return getLootProbability("default");
  }
  
  @NonNull
  public Optional<JsonElement> getLootProbability(@NonNull String type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return Optional.ofNullable(this.lootProbability.get(type.toLowerCase()));
  }
  
  public class DungeonLevelLootsElementConfig {
    private final String type;
    
    private final String item;
    
    private final JsonElement quantity;
    
    private final JsonElement weight;
    
    private RPGItemRarity rarity;
    
    private transient ItemStack cachedItemStack;
    
    public String toString() {
      return "DungeonLevelLootsConfig.DungeonLevelLootsElementConfig(type=" + getType() + ", item=" + getItem() + ", quantity=" + getQuantity() + ", weight=" + getWeight() + ", rarity=" + getRarity() + ", cachedItemStack=" + getCachedItemStack() + ")";
    }
    
    public DungeonLevelLootsElementConfig(String type, String item, JsonElement quantity, JsonElement weight, RPGItemRarity rarity, ItemStack cachedItemStack) {
      this.type = type;
      this.item = item;
      this.quantity = quantity;
      this.weight = weight;
      this.rarity = rarity;
      this.cachedItemStack = cachedItemStack;
    }
    
    public String getItem() {
      return this.item;
    }
    
    public JsonElement getQuantity() {
      return this.quantity;
    }
    
    public JsonElement getWeight() {
      return this.weight;
    }
    
    public ItemStack getCachedItemStack() {
      return this.cachedItemStack;
    }
    
    public String getType() {
      return (this.type == null || this.type.isEmpty()) ? "default" : this.type;
    }
    
    public boolean isDefaultType() {
      return "default".equalsIgnoreCase(getType());
    }
    
    @NonNull
    public ItemStack create() {
      if (this.cachedItemStack != null)
        return this.cachedItemStack; 
      try {
        this.cachedItemStack = create(null);
        if (this.rarity == null) {
          Optional<IRPGItem> rpgItem = IRPGItem.get(this.cachedItemStack);
          if (rpgItem.isPresent()) {
            this.rarity = ((IRPGItem)rpgItem.get()).getRPGRarity();
          } else {
            this.rarity = RPGItemRarity.UNKNOWN;
          } 
        } 
        return this.cachedItemStack;
      } catch (MolangException e) {
        throw new RuntimeException("Unable to create item stack", e);
      } 
    }
    
    @NonNull
    public ItemStack create(MolangParser parser) throws MolangException {
      ItemStack stack = parse(this.item);
      if (stack == null) {
        stack = new ItemStack(Blocks.field_150348_b);
        stack.func_151001_c("[!] " + this.item);
      } 
      if (parser != null && this.quantity != null)
        stack.field_77994_a = Double.valueOf(parser.parseJson(this.quantity).get()).intValue(); 
      return stack;
    }
    
    private ItemStack parse(@NonNull String item) {
      if (item == null)
        throw new NullPointerException("item is marked non-null but is null"); 
      String[] elements = item.split(" ");
      if (elements.length < 1)
        return null; 
      try {
        String[] split = elements[0].split(":");
        int id = Integer.parseInt(split[0]);
        int damage = (split.length > 1) ? Integer.parseInt(split[1]) : 0;
        int stackSize = (elements.length > 2) ? Integer.parseInt(elements[2]) : 1;
        Item itemById = Item.func_150899_d(id);
        if (itemById == null)
          return null; 
        return new ItemStack(itemById, stackSize, damage);
      } catch (Exception exception) {
        try {
          ItemStack stack = ItemStackSerializer.read(new String(Base64.getDecoder().decode(item), StandardCharsets.UTF_8), null);
          if (stack != null)
            return stack; 
        } catch (Exception exception1) {}
        try {
          String name = elements[0].contains(":") ? elements[0] : ("minecraft:" + elements[0]);
          int damage = (elements.length > 1) ? Integer.parseInt(elements[1]) : 0;
          int stackSize = (elements.length > 2) ? Integer.parseInt(elements[2]) : 1;
          ItemStack stack = GameRegistry.findItemStack(name.split(":")[0], name.split(":")[1], stackSize);
          if (stack != null) {
            stack.func_77964_b(damage);
            stack.field_77994_a = stackSize;
            return stack;
          } 
        } catch (Exception exception1) {}
        return null;
      } 
    }
    
    @NonNull
    public RPGItemRarity getRarity() {
      if (this.rarity == null)
        return RPGItemRarity.UNKNOWN; 
      return this.rarity;
    }
  }
  
  public class DungeonLevelLootsChestElementConfig {
    private final JsonElement bonus;
    
    private final JsonElement weight;
    
    public String toString() {
      return "DungeonLevelLootsConfig.DungeonLevelLootsChestElementConfig(bonus=" + getBonus() + ", weight=" + getWeight() + ")";
    }
    
    public DungeonLevelLootsChestElementConfig(JsonElement bonus, JsonElement weight) {
      this.bonus = bonus;
      this.weight = weight;
    }
    
    public JsonElement getBonus() {
      return this.bonus;
    }
    
    public JsonElement getWeight() {
      return this.weight;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\config\level\DungeonLevelLootsConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */