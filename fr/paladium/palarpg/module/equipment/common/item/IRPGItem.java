package fr.paladium.palarpg.module.equipment.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGItemData;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public interface IRPGItem {
  void register();
  
  String getId();
  
  RPGItemRarity getRPGRarity();
  
  <T extends RPGItemData> T getItemData();
  
  @SideOnly(Side.CLIENT)
  IIcon getIcon();
  
  void applyStats(ItemStack paramItemStack, EntityLivingBase paramEntityLivingBase, RPGStatApplyType paramRPGStatApplyType);
  
  void removeStats(ItemStack paramItemStack, EntityLivingBase paramEntityLivingBase, RPGStatApplyType paramRPGStatApplyType);
  
  default boolean hasTag(String tag) {
    if (getItemData() == null)
      return false; 
    return getItemData().getTags().containsKey(tag);
  }
  
  default <T> Optional<T> getTag(String tag) {
    if (!hasTag(tag))
      return Optional.empty(); 
    return Optional.of((T)getItemData().getTags().get(tag));
  }
  
  static boolean is(@NonNull ItemStack itemStack) {
    if (itemStack == null)
      throw new NullPointerException("itemStack is marked non-null but is null"); 
    return is(itemStack.func_77973_b());
  }
  
  static boolean is(Item item) {
    return item instanceof IRPGItem;
  }
  
  static <T extends IRPGItem> Optional<T> get(ItemStack itemStack) {
    if (itemStack == null || itemStack.func_77973_b() == null)
      return Optional.empty(); 
    return get(itemStack.func_77973_b());
  }
  
  static <T extends IRPGItem> Optional<T> get(Item item) {
    if (item == null || !is(item))
      return Optional.empty(); 
    return Optional.ofNullable((T)item);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\item\IRPGItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */