package fr.paladium.palamod.mixins.client;

import fr.paladium.palamod.modules.paladium.utils.IPositionateData;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ChunkCache.class})
public abstract class MixinChunkCache implements IBlockAccess, IPositionateData {
  @Shadow
  private int field_72818_a;
  
  @Shadow
  private int field_72816_b;
  
  public int getX() {
    return this.field_72818_a;
  }
  
  public int getY() {
    return 0;
  }
  
  public int getZ() {
    return this.field_72816_b;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\MixinChunkCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */