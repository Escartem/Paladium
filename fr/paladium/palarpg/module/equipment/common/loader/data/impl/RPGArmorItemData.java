package fr.paladium.palarpg.module.equipment.common.loader.data.impl;

import com.google.gson.annotations.SerializedName;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palarpg.module.equipment.common.EquipmentCommonProxy;
import fr.paladium.palarpg.module.equipment.common.item.RPGArmorType;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemType;
import fr.paladium.palarpg.module.equipment.common.item.RPGStatApplyType;
import fr.paladium.palarpg.module.equipment.common.loader.util.BufferedTextureAtlasSprite;
import fr.paladium.palarpg.module.equipment.common.loader.util.ItemStatMutator;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.block.texture.utils.BufferedSimpleTexture;

public class RPGArmorItemData extends RPGBasicItemData {
  private Map<RPGArmorType, RPGArmor> armors;
  
  public Map<RPGArmorType, RPGArmor> getArmors() {
    return this.armors;
  }
  
  @SerializedName("repairItems")
  private final List<String> repairItemsName = new ArrayList<>();
  
  public List<String> getRepairItemsName() {
    return this.repairItemsName;
  }
  
  private transient List<ItemStack> repairItems = new ArrayList<>();
  
  public List<ItemStack> getRepairItems() {
    return this.repairItems;
  }
  
  public void onLoad(File dir) {
    super.onLoad(dir);
    if (this.armors == null)
      throw new RuntimeException("No armors found for " + getId()); 
    this.repairItemsName.forEach(item -> {
          ItemStack stack = ItemUtils.parse(item);
          if (stack != null)
            this.repairItems.add(stack); 
        });
    this.armors.forEach((type, armor) -> armor.onLoad());
  }
  
  @SideOnly(Side.CLIENT)
  public ResourceLocation getModelTexture() {
    if (super.getModelTexture() != null)
      return super.getModelTexture(); 
    try {
      BufferedImage image = ImageIO.read(new File(EquipmentCommonProxy.getConfigDirectory() + File.separator + getType().getPath() + File.separator + getId() + File.separator + getTexture()));
      String name = "rpg_item/" + getType().name().toLowerCase() + "/" + getId() + "/" + getTexture();
      ResourceLocation textureLocation = new ResourceLocation(name);
      if (Minecraft.func_71410_x().func_110434_K() != null) {
        Minecraft.func_71410_x().func_110434_K().func_110579_a(textureLocation, (ITextureObject)new BufferedSimpleTexture(textureLocation, image));
        setModelTexture(textureLocation);
      } 
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return super.getModelTexture();
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(RPGArmorType type) {
    if (this.armors == null)
      return null; 
    RPGArmor armor = this.armors.get(type);
    if (armor == null)
      return null; 
    return armor.getIcon(getId(), type);
  }
  
  public class RPGArmor {
    private String itemName;
    
    private String itemTexture;
    
    private String description;
    
    private String set;
    
    private int durability;
    
    public String getItemName() {
      return this.itemName;
    }
    
    public String getItemTexture() {
      return this.itemTexture;
    }
    
    public String getDescription() {
      return this.description;
    }
    
    public String getSet() {
      return this.set;
    }
    
    public int getDurability() {
      return this.durability;
    }
    
    @SerializedName("requiredLevel")
    private final int rpgRequiredLevel = 0;
    
    public int getRpgRequiredLevel() {
      getClass();
      return 0;
    }
    
    private int runeSlot = 0;
    
    private Map<String, String> translations;
    
    private Map<RPGStatApplyType, List<ItemStatMutator>> mutators;
    
    @SideOnly(Side.CLIENT)
    private transient BufferedTextureAtlasSprite textureResource;
    
    public int getRuneSlot() {
      return this.runeSlot;
    }
    
    public Map<String, String> getTranslations() {
      return this.translations;
    }
    
    public Map<RPGStatApplyType, List<ItemStatMutator>> getMutators() {
      return this.mutators;
    }
    
    public void onLoad() {
      this.mutators.forEach((applyType, mutators) -> mutators.forEach(()));
    }
    
    public String getTranslation(String lang) {
      if (this.translations != null && !this.translations.isEmpty())
        for (String key : this.translations.keySet()) {
          if (key.equalsIgnoreCase(lang))
            return this.translations.get(key); 
        }  
      return (this.itemName != null) ? this.itemName : (!this.translations.isEmpty() ? this.translations.values().iterator().next() : "{itemName}");
    }
    
    public BufferedTextureAtlasSprite getTextureResource() {
      return this.textureResource;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(String id, RPGArmorType type) {
      if (this.textureResource != null)
        return (IIcon)this.textureResource; 
      try {
        BufferedImage image = ImageIO.read(new File(EquipmentCommonProxy.getConfigDirectory() + File.separator + RPGItemType.ARMOR.getPath() + File.separator + id + File.separator + this.itemTexture));
        String name = "rpg_item/" + RPGItemType.ARMOR.name().toLowerCase() + "/" + id + "_" + type.name().toLowerCase() + "/" + this.itemTexture;
        this.textureResource = new BufferedTextureAtlasSprite(name, image);
        ResourceLocation textureLocation = new ResourceLocation(name);
        if (Minecraft.func_71410_x().func_110434_K() != null)
          Minecraft.func_71410_x().func_110434_K().func_110579_a(textureLocation, (ITextureObject)new BufferedSimpleTexture(textureLocation, image)); 
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      } 
      return (IIcon)this.textureResource;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loader\data\impl\RPGArmorItemData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */