package fr.paladium.palaforgeutils.lib.potion;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.potion.item.CustomItemPotion;
import fr.paladium.palaforgeutils.lib.potion.item.CustomSplashItemPotion;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public abstract class APotion {
  private final String id;
  
  private final boolean isBad;
  
  private final Color color;
  
  private int potionId;
  
  private ResourceLocation iconTexture;
  
  private ItemStack iconItem;
  
  public void setPotionId(int potionId) {
    this.potionId = potionId;
  }
  
  public void setIconTexture(ResourceLocation iconTexture) {
    this.iconTexture = iconTexture;
  }
  
  public void setIconItem(ItemStack iconItem) {
    this.iconItem = iconItem;
  }
  
  public APotion(String id, boolean isBad, Color color) {
    this.id = id;
    this.isBad = isBad;
    this.color = color;
  }
  
  public String getId() {
    return this.id;
  }
  
  public boolean isBad() {
    return this.isBad;
  }
  
  public Color getColor() {
    return this.color;
  }
  
  public int getPotionId() {
    return this.potionId;
  }
  
  public ResourceLocation getIconTexture() {
    return this.iconTexture;
  }
  
  public ItemStack getIconItem() {
    return this.iconItem;
  }
  
  public Item createItem(int duration, int amplifier, boolean splash) {
    if (this.potionId == 0) {
      Minecraft.func_71410_x().func_71404_a(new CrashReport("Cannot register APotion from here", new Exception("APotion have to be instancied before createItem method can be used.")));
      return null;
    } 
    PotionEffect effect = new PotionEffect(this.potionId, duration, amplifier);
    Item item = splash ? (Item)new CustomSplashItemPotion(this, effect) : (Item)new CustomItemPotion(this, effect);
    RegistryUtils.item(new Item[] { item });
    return item;
  }
  
  public void tickAfter(EntityPlayer player, PotionEffect effect) {}
  
  @SideOnly(Side.CLIENT)
  public ResourceLocation getIcon() {
    return this.iconTexture;
  }
  
  public APotion setIcon(ResourceLocation icon) {
    this.iconTexture = icon;
    return this;
  }
  
  public APotion setIcon(ItemStack icon) {
    this.iconItem = icon;
    return this;
  }
  
  public APotion setIcon(Item icon) {
    return setIcon(new ItemStack(icon));
  }
  
  public APotion setIcon(Block icon) {
    return setIcon(new ItemStack(icon));
  }
  
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + ((this.id == null) ? 0 : this.id.hashCode());
    return result;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null)
      return false; 
    if (getClass() != obj.getClass())
      return false; 
    APotion other = (APotion)obj;
    if (this.id == null) {
      if (other.id != null)
        return false; 
    } else if (!this.id.equals(other.id)) {
      return false;
    } 
    return true;
  }
  
  public abstract void consume(EntityPlayer paramEntityPlayer, PotionEffect paramPotionEffect);
  
  public abstract void tick(EntityPlayer paramEntityPlayer, PotionEffect paramPotionEffect);
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\potion\APotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */