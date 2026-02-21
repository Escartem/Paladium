package fr.paladium.palaautomation.common.tile.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class PipeItemData {
  private static final String TAG_ITEM = "item";
  
  private static final String TAG_ID = "id";
  
  private static final String TAG_META = "meta";
  
  private static final String TAG_NBT = "nbt";
  
  private static final String TAG_COUNT = "count";
  
  private PipeItemData(Item item, int meta, NBTTagCompound nbt, int count) {
    this.item = item;
    this.meta = meta;
    this.nbt = nbt;
    this.count = count;
  }
  
  public static int MAX_DATA_SIZE = 10000;
  
  private final Item item;
  
  private final int meta;
  
  private final NBTTagCompound nbt;
  
  private int count;
  
  public Item getItem() {
    return this.item;
  }
  
  public int getMeta() {
    return this.meta;
  }
  
  public NBTTagCompound getNbt() {
    return this.nbt;
  }
  
  public int getCount() {
    return this.count;
  }
  
  public static void toNBT(NBTTagCompound nbt, PipeItemData data) {
    if (data == null)
      return; 
    NBTTagCompound itemNbt = new NBTTagCompound();
    itemNbt.func_74768_a("id", Item.func_150891_b(data.getItem()));
    itemNbt.func_74768_a("meta", data.getMeta());
    itemNbt.func_74782_a("nbt", (NBTBase)data.getNbt());
    itemNbt.func_74768_a("count", data.getCount());
    nbt.func_74782_a("item", (NBTBase)itemNbt);
  }
  
  public static PipeItemData fromNBT(NBTTagCompound nbt) {
    if (!nbt.func_74764_b("item"))
      return null; 
    NBTTagCompound itemNbt = nbt.func_74775_l("item");
    Item item = Item.func_150899_d(itemNbt.func_74762_e("id"));
    NBTTagCompound nbtTag = itemNbt.func_74775_l("nbt");
    int meta = itemNbt.func_74762_e("meta");
    int count = itemNbt.func_74762_e("count");
    return of(item, meta, nbtTag, count);
  }
  
  public static PipeItemData of(Item item, int meta, NBTTagCompound nbt) {
    return of(item, meta, nbt, 1);
  }
  
  public static PipeItemData of(Item item, int count) {
    return of(item, 0, new NBTTagCompound(), count);
  }
  
  public static PipeItemData of(Item item, int count, int meta) {
    return of(item, meta, new NBTTagCompound(), count);
  }
  
  public static PipeItemData of(Item item, int meta, NBTTagCompound nbt, int count) {
    return new PipeItemData(item, meta, (NBTTagCompound)nbt.func_74737_b(), count);
  }
  
  public static PipeItemData of(ItemStack stack) {
    return new PipeItemData(stack.func_77973_b(), stack.func_77960_j(), (stack.func_77978_p() == null) ? new NBTTagCompound() : (NBTTagCompound)stack.func_77978_p().func_74737_b(), stack.field_77994_a);
  }
  
  public ItemStack toMaxSizeItemStack() {
    if (this.item == null || this.count <= 0)
      return null; 
    ItemStack stackTemplate = new ItemStack(this.item, 1, this.meta);
    int maxStackSize = this.item.getItemStackLimit(stackTemplate);
    int stackSize = Math.min(this.count, maxStackSize);
    int target = Math.min(this.count, maxStackSize);
    ItemStack stack = new ItemStack(this.item, stackSize, this.meta);
    if (this.nbt != null && !this.nbt.func_82582_d())
      stack.func_77982_d((NBTTagCompound)this.nbt.func_74737_b()); 
    return stack;
  }
  
  public ItemStack toSingleItemStack() {
    int count = Math.min(this.count, 1);
    if (this.item == null || count <= 0)
      return null; 
    ItemStack stack = new ItemStack(this.item, count, this.meta);
    if (this.nbt != null && !this.nbt.func_82582_d()) {
      stack.func_77982_d((NBTTagCompound)this.nbt.func_74737_b());
      return stack;
    } 
    return stack;
  }
  
  public List<ItemStack> toItemStacks() {
    return toItemStacks(this.count);
  }
  
  public List<ItemStack> toItemStacks(int targetCount) {
    if (this.item == null || this.count <= 0 || targetCount <= 0)
      return new ArrayList<>(); 
    targetCount = Math.min(targetCount, this.count);
    ItemStack stackTemplate = new ItemStack(this.item, 1, this.meta);
    int maxStackSize = this.item.getItemStackLimit(stackTemplate);
    List<ItemStack> itemStacks = new ArrayList<>((int)Math.ceil(targetCount / maxStackSize));
    int remaining;
    for (remaining = targetCount; remaining > 0; ) {
      int stackSize = Math.min(remaining, maxStackSize);
      ItemStack stack = new ItemStack(this.item, stackSize, this.meta);
      if (this.nbt != null && !this.nbt.func_82582_d())
        stack.func_77982_d((NBTTagCompound)this.nbt.func_74737_b()); 
      itemStacks.add(stack);
      remaining -= stackSize;
    } 
    return itemStacks;
  }
  
  public PipeItemData copy(int count) {
    NBTTagCompound nbt = (this.nbt == null) ? new NBTTagCompound() : (NBTTagCompound)this.nbt.func_74737_b();
    count = Math.max(1, count);
    return new PipeItemData(this.item, this.meta, nbt, count);
  }
  
  public PipeItemData copy() {
    return copy(this.count);
  }
  
  public String toString() {
    String itemName = (this.item == null) ? "null" : this.item.func_77658_a();
    return "PipeItemData{item=" + itemName + ", meta=" + this.meta + ", nbt=" + this.nbt + ", count=" + this.count + '}';
  }
  
  public boolean decrement() {
    return decrement(1);
  }
  
  public boolean decrement(int count) {
    this.count -= count;
    return (this.count > 0);
  }
  
  public boolean canIncrement(int count) {
    return (this.count + count <= MAX_DATA_SIZE);
  }
  
  public boolean increment(PipeItemData data) {
    return increment(data, 1);
  }
  
  public boolean increment(PipeItemData data, int count) {
    if (!data.equals(this))
      return false; 
    if (!canIncrement(count))
      return false; 
    this.count += count;
    return true;
  }
  
  public boolean isSimilar(ItemStack stack) {
    if (stack == null || stack.func_77973_b() == null)
      return false; 
    PipeItemData data = of(stack);
    return equals(data);
  }
  
  public int hashCode() {
    if (this.nbt == null || this.nbt.func_82582_d())
      return Objects.hash(new Object[] { this.item, Integer.valueOf(this.meta), Integer.valueOf(this.count) }); 
    return Objects.hash(new Object[] { this.item, Integer.valueOf(this.meta), this.nbt, Integer.valueOf(this.count) });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (!(obj instanceof PipeItemData))
      return false; 
    PipeItemData other = (PipeItemData)obj;
    if (this.item != other.item || this.meta != other.meta)
      return false; 
    if ((this.nbt == null || this.nbt.func_82582_d()) && (other.nbt == null || other.nbt.func_82582_d()))
      return true; 
    if (this.nbt != null && other.nbt != null)
      return this.nbt.equals(other.nbt); 
    return false;
  }
  
  public boolean isEmpty() {
    return (this.count <= 0);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\til\\util\PipeItemData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */