package fr.paladium.palarpg.module.equipment.common.loader.data.impl;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import fr.paladium.palarpg.module.equipment.common.loader.cache.RPGCraftCache;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGCraftData;
import fr.paladium.palarpg.module.equipment.common.loader.util.BufferedTextureAtlasSprite;
import fr.paladium.palarpg.module.equipment.common.loader.util.TextureFile;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Optional;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class RPGParchmentItemData extends RPGBasicItemData {
  private static IIcon defaultIcon;
  
  private String item;
  
  private transient ItemStack itemStack;
  
  public void setItem(String item) {
    this.item = item;
  }
  
  public void setItemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
  }
  
  public String getItem() {
    return this.item;
  }
  
  public ItemStack getItemStack() {
    return this.itemStack;
  }
  
  public void onLoad(File dir) {
    if (getTexture() != null && !getTexture().isEmpty()) {
      setTextureFile(new TextureFile(new File(dir, getTexture())));
      if (!getTextureFile().exists())
        throw new RuntimeException("Unable to find texture file: " + getTexture()); 
    } 
    if (this.item == null || this.item.isEmpty())
      throw new RuntimeException("No item found for the parchment " + getId()); 
    this.itemStack = ItemUtils.parse(this.item);
    if (this.itemStack == null)
      throw new RuntimeException("Unable to parse item for the parchment " + getId() + " : " + this.item); 
    RPGCraftCache.registerCraftData(RPGCraftData.build(this.itemStack, 0, true));
  }
  
  public String getDescription() {
    if (super.getDescription() != null && !super.getDescription().isEmpty())
      return super.getDescription(); 
    String itemName = this.itemStack.func_82833_r();
    if (this.itemStack.func_77973_b() instanceof ItemEnchantedBook) {
      ItemEnchantedBook enchantedBook = (ItemEnchantedBook)this.itemStack.func_77973_b();
      NBTTagList enchantList = enchantedBook.func_92110_g(this.itemStack);
      if (enchantList != null)
        for (int i = 0; i < enchantList.func_74745_c(); i++) {
          short short1 = enchantList.func_150305_b(i).func_74765_d("id");
          short short2 = enchantList.func_150305_b(i).func_74765_d("lvl");
          if (Enchantment.field_77331_b[short1] != null) {
            itemName = Enchantment.field_77331_b[short1].func_77316_c(short2);
            break;
          } 
        }  
    } 
    if (Server.is(new ServerType[] { ServerType.RPG }))
      return "Débloque automatiquement le craft de l'item §b" + itemName + "§r lorsque vous terminez un donjon avec le parchemin dans votre sac à dos."; 
    return "Débloque automatiquement le craft de l'item §b" + itemName + "§r lorsque vous faites un clic droit avec le parchemin en main.";
  }
  
  public RPGItemRarity getRarity() {
    if (super.getRarity() != RPGItemRarity.UNKNOWN)
      return super.getRarity(); 
    Optional<IRPGItem> optionalRPGItem = IRPGItem.get(this.itemStack);
    if (!optionalRPGItem.isPresent())
      return super.getRarity(); 
    return ((IRPGItem)optionalRPGItem.get()).getRPGRarity();
  }
  
  public IIcon getIcon() {
    if (getTextureFile() != null)
      return super.getIcon(); 
    if (defaultIcon == null)
      try {
        ResourceLocation resourceLocation = new ResourceLocation("palarpg", "textures/items/parchment.png");
        InputStream stream = Minecraft.func_71410_x().func_110442_L().func_110536_a(resourceLocation).func_110527_b();
        BufferedImage image = ImageIO.read(stream);
        defaultIcon = (IIcon)new BufferedTextureAtlasSprite(resourceLocation.toString(), image);
      } catch (Exception e) {
        throw new RuntimeException("Unable to load default parchment icon.", e);
      }  
    return defaultIcon;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loader\data\impl\RPGParchmentItemData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */