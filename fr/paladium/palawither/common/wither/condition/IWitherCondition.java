package fr.paladium.palawither.common.wither.condition;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle;
import fr.paladium.palawither.common.wither.base.IWither;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IWitherCondition {
  @SideOnly(Side.SERVER)
  void validate(@NonNull World paramWorld, @NonNull TileEntityWitherReceptacle paramTileEntityWitherReceptacle, @NonNull EntityPlayer paramEntityPlayer, @NonNull ItemStack paramItemStack, @NonNull IWither paramIWither) throws WitherConditionException;
  
  public static class WitherConditionException extends RuntimeException {
    private final String message;
    
    private final Throwable cause;
    
    public String getMessage() {
      return this.message;
    }
    
    public Throwable getCause() {
      return this.cause;
    }
    
    public WitherConditionException(String message) {
      this(message, null);
    }
    
    public WitherConditionException(Throwable cause) {
      this(null, cause);
    }
    
    public WitherConditionException(String message, Throwable cause) {
      super((message == null) ? cause.getMessage() : message, cause);
      this.message = message;
      this.cause = cause;
    }
    
    public boolean isException() {
      return (this.cause != null);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\condition\IWitherCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */