package fr.paladium.palawither.common.wither.condition.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle;
import fr.paladium.palawither.common.wither.base.IWither;
import fr.paladium.palawither.common.wither.condition.IWitherCondition;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class NearbyPlayerCountWitherCondition implements IWitherCondition {
  private final int min;
  
  private final int max;
  
  private final double radius;
  
  private NearbyPlayerCountWitherCondition(int min, int max, double radius) {
    this.min = min;
    this.max = max;
    this.radius = radius;
  }
  
  @NonNull
  public static NearbyPlayerCountWitherCondition create(double radius, int min, int max) {
    return new NearbyPlayerCountWitherCondition(min, max, radius);
  }
  
  @NonNull
  public static NearbyPlayerCountWitherCondition min(double radius, int min) {
    return new NearbyPlayerCountWitherCondition(min, 2147483647, radius);
  }
  
  @NonNull
  public static NearbyPlayerCountWitherCondition max(double radius, int max) {
    return new NearbyPlayerCountWitherCondition(0, max, radius);
  }
  
  @SideOnly(Side.SERVER)
  public void validate(@NonNull World world, @NonNull TileEntityWitherReceptacle receptacle, @NonNull EntityPlayer player, @NonNull ItemStack item, @NonNull IWither wither) throws IWitherCondition.WitherConditionException {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    if (receptacle == null)
      throw new NullPointerException("receptacle is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    if (wither == null)
      throw new NullPointerException("wither is marked non-null but is null"); 
    AxisAlignedBB receptacleBounds = receptacle.func_145838_q().func_149668_a(world, receptacle.field_145851_c, receptacle.field_145848_d, receptacle.field_145849_e);
    int playerCount = world.func_72872_a(EntityPlayer.class, receptacleBounds.func_72314_b(this.radius, this.radius, this.radius)).size();
    if (playerCount < this.min)
      throw new IWitherCondition.WitherConditionException("le nombre de joueurs dans un rayon de " + String.format("%.0f", new Object[] { Double.valueOf(this.radius) }) + " blocs doit être supérieur ou égal à " + this.min + ", il y en a actuellement " + playerCount + "."); 
    if (playerCount > this.max)
      throw new IWitherCondition.WitherConditionException("le nombre de joueurs dans un rayon de " + String.format("%.0f", new Object[] { Double.valueOf(this.radius) }) + "blocs doit être inférieur ou égal à " + this.max + ", il y en a actuellement " + playerCount + "."); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\condition\impl\NearbyPlayerCountWitherCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */