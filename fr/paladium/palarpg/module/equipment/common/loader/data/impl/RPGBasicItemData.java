package fr.paladium.palarpg.module.equipment.common.loader.data.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palarpg.module.equipment.common.EquipmentCommonProxy;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import fr.paladium.palarpg.module.equipment.common.loader.RPGItemLoader;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGItemData;
import fr.paladium.palarpg.module.equipment.common.loader.util.BufferedTextureAtlasSprite;
import fr.paladium.palarpg.module.equipment.common.loader.util.DisplayTransformProperty;
import fr.paladium.palarpg.module.equipment.common.loader.util.TextureFile;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.block.texture.utils.BufferedSimpleTexture;

public class RPGBasicItemData extends RPGItemData {
  @SideOnly(Side.CLIENT)
  private transient ResourceLocation modelTexture;
  
  @SideOnly(Side.CLIENT)
  private transient TextureAtlasSprite textureResource;
  
  private transient DisplayTransformProperty displayProperties;
  
  private transient TextureFile textureFile;
  
  private transient File modelFile;
  
  private transient File animationFile;
  
  private String texture;
  
  private String model;
  
  private String animation;
  
  private String display;
  
  public void setModelTexture(ResourceLocation modelTexture) {
    this.modelTexture = modelTexture;
  }
  
  public void setTextureResource(TextureAtlasSprite textureResource) {
    this.textureResource = textureResource;
  }
  
  public void setDisplayProperties(DisplayTransformProperty displayProperties) {
    this.displayProperties = displayProperties;
  }
  
  public void setTextureFile(TextureFile textureFile) {
    this.textureFile = textureFile;
  }
  
  public void setModelFile(File modelFile) {
    this.modelFile = modelFile;
  }
  
  public void setAnimationFile(File animationFile) {
    this.animationFile = animationFile;
  }
  
  public void setTexture(String texture) {
    this.texture = texture;
  }
  
  public void setModel(String model) {
    this.model = model;
  }
  
  public void setAnimation(String animation) {
    this.animation = animation;
  }
  
  public void setDisplay(String display) {
    this.display = display;
  }
  
  public void setMaxStackSize(int maxStackSize) {
    this.maxStackSize = maxStackSize;
  }
  
  public void setRarity(RPGItemRarity rarity) {
    this.rarity = rarity;
  }
  
  public ResourceLocation getModelTexture() {
    return this.modelTexture;
  }
  
  public TextureAtlasSprite getTextureResource() {
    return this.textureResource;
  }
  
  public DisplayTransformProperty getDisplayProperties() {
    return this.displayProperties;
  }
  
  public TextureFile getTextureFile() {
    return this.textureFile;
  }
  
  public File getModelFile() {
    return this.modelFile;
  }
  
  public File getAnimationFile() {
    return this.animationFile;
  }
  
  public String getTexture() {
    return this.texture;
  }
  
  public String getModel() {
    return this.model;
  }
  
  public String getAnimation() {
    return this.animation;
  }
  
  public String getDisplay() {
    return this.display;
  }
  
  private int maxStackSize = 64;
  
  public int getMaxStackSize() {
    return this.maxStackSize;
  }
  
  private RPGItemRarity rarity = RPGItemRarity.UNKNOWN;
  
  public RPGItemRarity getRarity() {
    return this.rarity;
  }
  
  public void onLoad(File dir) {
    if (this.texture == null)
      throw new RuntimeException("Unable to find texture configuration"); 
    this.textureFile = new TextureFile(new File(dir, this.texture));
    if (!this.textureFile.exists())
      throw new RuntimeException("Unable to find texture file: " + this.texture); 
    if (this.display != null && !this.display.isEmpty()) {
      File displayFile = new File(dir, this.display);
      if (displayFile.exists())
        try {
          this.displayProperties = (DisplayTransformProperty)RPGItemLoader.GSON.fromJson(new String(Files.readAllBytes(displayFile.toPath()), StandardCharsets.UTF_8), DisplayTransformProperty.class);
        } catch (Exception e) {
          e.printStackTrace();
        }  
    } 
    if (this.model != null && !this.model.isEmpty()) {
      this.modelFile = new File(dir, this.model);
      if (!this.modelFile.exists()) {
        this.modelFile = null;
        throw new RuntimeException("Unable to find model file: " + this.model);
      } 
    } 
    if (this.animation != null && !this.animation.isEmpty()) {
      this.animationFile = new File(dir, this.animation);
      if (!this.animationFile.exists())
        this.animationFile = null; 
    } 
  }
  
  public void createDefault(File dir) {
    this.texture = "texture.png";
    this.model = "model.json";
    this.animation = "animation.json";
    this.display = "display.json";
  }
  
  public boolean isModel() {
    return (this.modelFile != null && this.modelFile.exists());
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon() {
    if (this.textureResource != null)
      return (IIcon)this.textureResource; 
    try {
      BufferedImage image = ImageIO.read(new File(EquipmentCommonProxy.getConfigDirectory() + File.separator + getType().getPath() + File.separator + getId() + File.separator + this.texture));
      String name = "rpg_item/" + getType().name().toLowerCase() + "/" + getId() + "/" + this.texture;
      this.textureResource = (TextureAtlasSprite)new BufferedTextureAtlasSprite(name, image);
      ResourceLocation textureLocation = new ResourceLocation(name);
      if (Minecraft.func_71410_x().func_110434_K() != null) {
        Minecraft.func_71410_x().func_110434_K().func_110579_a(textureLocation, (ITextureObject)new BufferedSimpleTexture(textureLocation, image));
        this.modelTexture = textureLocation;
      } 
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    } 
    return (IIcon)this.textureResource;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loader\data\impl\RPGBasicItemData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */