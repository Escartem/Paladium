package fr.paladium.palamod.modules.chisel;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.modules.chisel.block.impl.BlockChiselGlass;
import fr.paladium.palamod.modules.chisel.block.impl.BlockChiselMetadata;
import fr.paladium.palamod.modules.chisel.block.impl.BlockChiselPillar;
import fr.paladium.palamod.modules.chisel.block.impl.BlockChiselSlab;
import fr.paladium.palamod.modules.chisel.block.impl.BlockChiselStairs;
import fr.paladium.palamod.modules.chisel.crafting.ChiselRecipes;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ChiselTemplate {
  private final int groupID;
  
  private final String id;
  
  private final String texturePath;
  
  private int currentId;
  
  private final Map<Block, Integer> registers;
  
  private final Map<BlockProperty, Map<BlockChiselMetadata, Integer>> variants;
  
  public int getGroupID() {
    return this.groupID;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getTexturePath() {
    return this.texturePath;
  }
  
  public int getCurrentId() {
    return this.currentId;
  }
  
  public Map<Block, Integer> getRegisters() {
    return this.registers;
  }
  
  public Map<BlockProperty, Map<BlockChiselMetadata, Integer>> getVariants() {
    return this.variants;
  }
  
  public ChiselTemplate(int groupID, @NonNull String id, @NonNull String texturePath) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (texturePath == null)
      throw new NullPointerException("texturePath is marked non-null but is null"); 
    this.groupID = groupID;
    this.id = id;
    this.texturePath = texturePath;
    this.registers = new HashMap<>();
    this.variants = new HashMap<>();
  }
  
  public void registerBlock(@NonNull BlockProperty property, @NonNull String unlocalizedName) {
    if (property == null)
      throw new NullPointerException("property is marked non-null but is null"); 
    if (unlocalizedName == null)
      throw new NullPointerException("unlocalizedName is marked non-null but is null"); 
    registerBlock(property, unlocalizedName, "palamod:" + this.texturePath + "/" + unlocalizedName);
  }
  
  public void registerBlock(@NonNull BlockProperty property, @NonNull String unlocalizedName, @NonNull String texture) {
    if (property == null)
      throw new NullPointerException("property is marked non-null but is null"); 
    if (unlocalizedName == null)
      throw new NullPointerException("unlocalizedName is marked non-null but is null"); 
    if (texture == null)
      throw new NullPointerException("texture is marked non-null but is null"); 
    registerBlock(property.getMaterial(), unlocalizedName, texture, property.getResistance(), property.getHardness(), property.getLightLevel());
  }
  
  public void registerBlock(@NonNull Material material, @NonNull String unlocalizedName, float resistance, float hardness, float lightLevel) {
    if (material == null)
      throw new NullPointerException("material is marked non-null but is null"); 
    if (unlocalizedName == null)
      throw new NullPointerException("unlocalizedName is marked non-null but is null"); 
    registerBlock(material, unlocalizedName, "palamod:" + this.texturePath + "/" + unlocalizedName, resistance, hardness, lightLevel);
  }
  
  public void registerBlock(@NonNull Material material, @NonNull String unlocalizedName, @NonNull String texture, float resistance, float hardness, float lightLevel) {
    if (material == null)
      throw new NullPointerException("material is marked non-null but is null"); 
    if (unlocalizedName == null)
      throw new NullPointerException("unlocalizedName is marked non-null but is null"); 
    if (texture == null)
      throw new NullPointerException("texture is marked non-null but is null"); 
    BlockProperty property = new BlockProperty(material, resistance, hardness, lightLevel);
    if (!this.variants.containsKey(property))
      this.variants.put(property, new HashMap<>()); 
    BlockChiselMetadata currentMetadata = null;
    for (BlockChiselMetadata metadata : ((Map)this.variants.get(property)).keySet()) {
      if (!metadata.isFull()) {
        currentMetadata = metadata;
        break;
      } 
    } 
    if (currentMetadata == null) {
      currentMetadata = new BlockChiselMetadata(material, hardness);
      currentMetadata.func_149752_b(resistance);
      currentMetadata.func_149711_c(hardness);
      currentMetadata.func_149715_a(lightLevel);
      currentMetadata.func_149663_c(getFormat(unlocalizedName));
      currentMetadata.func_149658_d(texture);
    } 
    currentMetadata.addObject(getFormat(unlocalizedName), texture);
    ((Map<BlockChiselMetadata, Integer>)this.variants.get(property)).put(currentMetadata, Integer.valueOf(this.currentId++));
  }
  
  public void registerBlock(@NonNull Block block) {
    if (block == null)
      throw new NullPointerException("block is marked non-null but is null"); 
    this.registers.put(block, Integer.valueOf(this.currentId++));
  }
  
  public void registerBlockGlass(@NonNull String unlocalizedName, float hardness, @NonNull Material material) {
    if (unlocalizedName == null)
      throw new NullPointerException("unlocalizedName is marked non-null but is null"); 
    if (material == null)
      throw new NullPointerException("material is marked non-null but is null"); 
    BlockChiselGlass block = new BlockChiselGlass(material, hardness, this.texturePath + "/" + unlocalizedName);
    block.func_149663_c(getFormat(unlocalizedName));
    this.registers.put(block, Integer.valueOf(this.currentId++));
  }
  
  public void registerBlockPillar(@NonNull String unlocalizedName, float hardness, @NonNull Material material) {
    if (unlocalizedName == null)
      throw new NullPointerException("unlocalizedName is marked non-null but is null"); 
    if (material == null)
      throw new NullPointerException("material is marked non-null but is null"); 
    BlockChiselPillar block = new BlockChiselPillar(material, hardness, this.texturePath, unlocalizedName);
    block.func_149663_c(getFormat(unlocalizedName));
    block.func_149752_b(10.0F);
    this.registers.put(block, Integer.valueOf(this.currentId++));
  }
  
  public void registerBlockPillar(@NonNull String unlocalizedName, float hardness, @NonNull Material material, @NonNull String texture) {
    if (unlocalizedName == null)
      throw new NullPointerException("unlocalizedName is marked non-null but is null"); 
    if (material == null)
      throw new NullPointerException("material is marked non-null but is null"); 
    if (texture == null)
      throw new NullPointerException("texture is marked non-null but is null"); 
    BlockChiselPillar block = new BlockChiselPillar(material, hardness, texture, unlocalizedName);
    block.func_149663_c(getFormat(unlocalizedName));
    block.func_149752_b(10.0F);
    this.registers.put(block, Integer.valueOf(this.currentId++));
  }
  
  public void registerBlockStairs(@NonNull String unlocalizedName, @NonNull Block b, int meta) {
    if (unlocalizedName == null)
      throw new NullPointerException("unlocalizedName is marked non-null but is null"); 
    if (b == null)
      throw new NullPointerException("b is marked non-null but is null"); 
    BlockChiselStairs block = new BlockChiselStairs(b, meta);
    block.func_149663_c(getFormat(unlocalizedName));
    block.func_149752_b(10.0F);
    this.registers.put(block, Integer.valueOf(this.currentId++));
  }
  
  public void registerBlockSlap(@NonNull String unlocalizedName, @NonNull Block b, int meta) {
    if (unlocalizedName == null)
      throw new NullPointerException("unlocalizedName is marked non-null but is null"); 
    if (b == null)
      throw new NullPointerException("b is marked non-null but is null"); 
    BlockChiselSlab block = new BlockChiselSlab(b.func_149688_o(), b, meta, 1);
    block.func_149663_c(getFormat(unlocalizedName));
    block.func_149752_b(10.0F);
    this.registers.put(block, Integer.valueOf(this.currentId++));
  }
  
  public void registerAll() {
    for (Map.Entry<Block, Integer> entry : this.registers.entrySet()) {
      Block block = entry.getKey();
      GameRegistry.registerBlock(block, this.id + "." + entry.getValue());
      ChiselRecipes.getInstance().addRecipe(block, this.groupID);
    } 
    if (this.variants.isEmpty())
      return; 
    for (Map.Entry<BlockProperty, Map<BlockChiselMetadata, Integer>> entry : this.variants.entrySet()) {
      for (Map.Entry<BlockChiselMetadata, Integer> metadata : (Iterable<Map.Entry<BlockChiselMetadata, Integer>>)((Map)entry.getValue()).entrySet()) {
        BlockChiselMetadata block = metadata.getKey();
        GameRegistry.registerBlock((Block)block, BlockChiselMetadata.ItemBlockChiselMetadata.class, this.id + "." + metadata.getValue());
        ChiselRecipes.getInstance().addRecipe((Block)block, this.groupID);
        for (int i = 0; i < block.getObjects().size(); i++)
          ChiselRecipes.getInstance().addRecipe((Block)block, i, this.groupID); 
      } 
    } 
  }
  
  public String getFormat(@NonNull String unlocalizedName) {
    if (unlocalizedName == null)
      throw new NullPointerException("unlocalizedName is marked non-null but is null"); 
    return "chisel." + this.id;
  }
  
  public static class BlockProperty {
    private final Material material;
    
    private float resistance;
    
    private float hardness;
    
    private float lightLevel;
    
    private BlockProperty(Material material, float resistance, float hardness, float lightLevel) {
      this.material = material;
      this.resistance = resistance;
      this.hardness = hardness;
      this.lightLevel = lightLevel;
    }
    
    public Material getMaterial() {
      return this.material;
    }
    
    public float getResistance() {
      return this.resistance;
    }
    
    public float getHardness() {
      return this.hardness;
    }
    
    public float getLightLevel() {
      return this.lightLevel;
    }
    
    @NonNull
    public static BlockProperty create() {
      return new BlockProperty(Material.field_151576_e, 10.0F, 0.0F, 0.0F);
    }
    
    @NonNull
    public static BlockProperty create(@NonNull Material material) {
      if (material == null)
        throw new NullPointerException("material is marked non-null but is null"); 
      return new BlockProperty(material, 10.0F, 0.0F, 0.0F);
    }
    
    @NonNull
    public final BlockProperty resistance(float resistance) {
      this.resistance = resistance;
      return this;
    }
    
    @NonNull
    public final BlockProperty hardness(float hardness) {
      this.hardness = hardness;
      return this;
    }
    
    @NonNull
    public final BlockProperty lightLevel(float lightLevel) {
      this.lightLevel = lightLevel;
      return this;
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { Float.valueOf(this.resistance), Float.valueOf(this.hardness), Float.valueOf(this.lightLevel), Integer.valueOf(this.material.hashCode()) });
    }
    
    public boolean equals(Object obj) {
      if (this == obj)
        return true; 
      if (obj == null || getClass() != obj.getClass())
        return false; 
      BlockProperty other = (BlockProperty)obj;
      return (Float.floatToIntBits(this.resistance) == Float.floatToIntBits(other.resistance) && Float.floatToIntBits(this.hardness) == Float.floatToIntBits(other.hardness) && Float.floatToIntBits(this.lightLevel) == Float.floatToIntBits(other.lightLevel) && this.material == other.material);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\ChiselTemplate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */