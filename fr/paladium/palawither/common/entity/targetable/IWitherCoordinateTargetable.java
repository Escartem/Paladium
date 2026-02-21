package fr.paladium.palawither.common.entity.targetable;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import net.minecraft.entity.player.EntityPlayer;

public interface IWitherCoordinateTargetable {
  boolean canSetTargetLocation(EntityPlayer paramEntityPlayer);
  
  void setTargetLocation(DoubleLocation paramDoubleLocation);
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\entity\targetable\IWitherCoordinateTargetable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */