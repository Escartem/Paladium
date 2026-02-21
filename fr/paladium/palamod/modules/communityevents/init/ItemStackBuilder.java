package fr.paladium.palamod.modules.communityevents.init;

import fr.paladium.palamod.modules.luckyblock.monthly.utils.ChatColorUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class ItemStackBuilder {
  private ItemStack stack;
  
  private Item item;
  
  private int amount = 1;
  
  private int damage = 0;
  
  private String displayName;
  
  private Map<Integer, Integer> enchantments = new HashMap<>();
  
  private List<String> lore = new ArrayList<>();
  
  public ItemStackBuilder(Item item) {
    this.item = item;
    this.stack = new ItemStack(item);
  }
  
  public ItemStackBuilder(Item item, int amount) {
    if (amount > 64 || amount < 64) {
      this.amount = 1;
    } else {
      this.amount = amount;
    } 
    this.item = item;
    this.stack = new ItemStack(item, amount);
  }
  
  public ItemStackBuilder(Item item, int amount, String displayName) {
    this.item = item;
    this.amount = (amount > 64 || amount < 64) ? 1 : amount;
    this.displayName = displayName;
    this.stack = new ItemStack(item, amount);
  }
  
  public ItemStackBuilder(Item item, String displayName) {
    this.item = item;
    this.displayName = displayName;
    this.stack = new ItemStack(item);
  }
  
  public ItemStackBuilder(ItemStack item) {
    this.stack = item;
    this.amount = item.field_77994_a;
    this.damage = item.func_77960_j();
    this.enchantments = EnchantmentHelper.func_82781_a(item);
    this.displayName = item.func_82833_r();
    this.lore = getLore(item);
  }
  
  public ItemStackBuilder amount(int amount) {
    if (amount > 64 || amount < 64)
      return this; 
    this.amount = amount;
    return this;
  }
  
  public ItemStackBuilder durability(short damage) {
    this.damage = damage;
    return this;
  }
  
  public ItemStackBuilder enchant(Enchantment enchant, int level) {
    this.enchantments.put(Integer.valueOf(enchant.field_77352_x), Integer.valueOf(level));
    return this;
  }
  
  public ItemStackBuilder enchant(Map<Enchantment, Integer> enchantments) {
    this.enchantments = new HashMap<>();
    for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet())
      this.enchantments.put(Integer.valueOf(((Enchantment)entry.getKey()).field_77352_x), entry.getValue()); 
    return this;
  }
  
  public ItemStackBuilder displayName(String displayName) {
    this.displayName = ChatColorUtils.color(displayName);
    return this;
  }
  
  public ItemStackBuilder lore(String line) {
    this.lore.add(ChatColorUtils.color(line));
    return this;
  }
  
  public ItemStackBuilder lore(List<String> lore) {
    this.lore = lore;
    return this;
  }
  
  public ItemStackBuilder lores(String... lines) {
    for (String line : lines)
      this.lore.add(ChatColorUtils.color(line)); 
    this.lore.addAll(Arrays.asList(lines));
    return this;
  }
  
  public ItemStackBuilder lore(String line, int count) {
    this.lore.set(count, ChatColorUtils.color(line));
    return this;
  }
  
  public String getDisplayName() {
    return this.displayName;
  }
  
  public int getAmount() {
    return this.amount;
  }
  
  public Map<Integer, Integer> getEnchantments() {
    return this.enchantments;
  }
  
  public int getDamage() {
    return this.damage;
  }
  
  public List<String> getLores() {
    return this.lore;
  }
  
  public Item getItem() {
    return this.item;
  }
  
  public ItemStack build() {
    this.stack.field_77994_a = this.amount;
    this.stack.func_77964_b(this.damage);
    if (this.enchantments.size() > 0)
      this.enchantments.forEach((k, v) -> this.stack.func_77966_a(Enchantment.field_77331_b[k.intValue()], v.intValue())); 
    if (this.displayName != null)
      this.stack.func_151001_c(this.displayName); 
    if (this.lore.size() > 0)
      setLore(this.stack, this.lore); 
    return this.stack;
  }
  
  private List<String> getLore(ItemStack item) {
    NBTTagCompound nbttagcompound = (item.field_77990_d == null) ? null : item.field_77990_d.func_74775_l("display");
    if (nbttagcompound == null)
      return new ArrayList<>(); 
    List<String> lore = new ArrayList<>();
    NBTTagList list = nbttagcompound.func_150295_c("Lore", 8);
    if (list.func_74745_c() > 0)
      for (int j = 0; j < list.func_74745_c(); j++)
        lore.add(list.func_150307_f(j));  
    return lore;
  }
  
  private void setLore(ItemStack item, List<String> lore) {
    NBTTagCompound nbttagcompound = (item.field_77990_d == null) ? new NBTTagCompound() : item.field_77990_d;
    NBTTagList list = new NBTTagList();
    if (lore.size() > 0)
      for (int j = 0; j < lore.size(); j++)
        list.func_74742_a((NBTBase)new NBTTagString(lore.get(j)));  
    NBTTagCompound display = nbttagcompound.func_74775_l("Display");
    display.func_74782_a("Lore", (NBTBase)list);
    nbttagcompound.func_74782_a("Display", (NBTBase)display);
    item.field_77990_d = nbttagcompound;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\init\ItemStackBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */