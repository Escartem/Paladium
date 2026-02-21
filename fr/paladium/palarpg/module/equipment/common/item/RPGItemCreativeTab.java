package fr.paladium.palarpg.module.equipment.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palarpg.module.equipment.common.loader.cache.RPGItemsCache;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RPGItemCreativeTab extends CreativeTabs {
  private final RPGItemType type;
  
  private final ItemStack defaultIcon;
  
  private final List<IRPGItem> display;
  
  public RPGItemCreativeTab(@NonNull String label, @NonNull RPGItemType type, @NonNull Item defaultIcon) {
    super(label);
    if (label == null)
      throw new NullPointerException("label is marked non-null but is null"); 
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    if (defaultIcon == null)
      throw new NullPointerException("defaultIcon is marked non-null but is null"); 
    this.type = type;
    this.defaultIcon = new ItemStack(defaultIcon);
    this.display = new ArrayList<>();
  }
  
  public void init() {
    this.display.clear();
    this.display.addAll(RPGItemsCache.getItems(this.type));
  }
  
  @SideOnly(Side.CLIENT)
  public ItemStack func_151244_d() {
    if (this.display.isEmpty())
      return this.defaultIcon; 
    return new ItemStack((Item)this.display.get((Minecraft.func_71410_x()).field_71439_g.field_70173_aa / 20 % this.display.size()));
  }
  
  public Item func_78016_d() {
    if (this.display.isEmpty())
      return this.defaultIcon.func_77973_b(); 
    return (Item)this.display.get((Minecraft.func_71410_x()).field_71439_g.field_70173_aa / 20 % this.display.size());
  }
  
  public String func_78024_c() {
    return I18n.func_135052_a("itemGroup." + func_78013_b(), new Object[0]) + " §8(" + this.display.size() + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\item\RPGItemCreativeTab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */