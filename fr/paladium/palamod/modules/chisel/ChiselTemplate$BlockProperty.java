package fr.paladium.palamod.modules.chisel;

import java.util.Objects;
import lombok.NonNull;
import net.minecraft.block.material.Material;

public class BlockProperty {
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\ChiselTemplate$BlockProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */