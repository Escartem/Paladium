package fr.paladium.palamod.modules.chisel.block.impl;

public class BlockChiselMetadataObject {
  private final String name;
  
  private final String texture;
  
  public String toString() {
    return "BlockChiselMetadata.BlockChiselMetadataObject(name=" + getName() + ", texture=" + getTexture() + ")";
  }
  
  public BlockChiselMetadataObject(String name, String texture) {
    this.name = name;
    this.texture = texture;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getTexture() {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\block\impl\BlockChiselMetadata$BlockChiselMetadataObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */