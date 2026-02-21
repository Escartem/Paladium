package fr.paladium.palarpg.module.dungeon.server.config.level;

import com.eliotlash.molang.MolangException;
import com.eliotlash.molang.MolangParser;
import com.google.gson.JsonElement;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\config\level\DungeonLevelLootsConfig$DungeonLevelLootsElementConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */