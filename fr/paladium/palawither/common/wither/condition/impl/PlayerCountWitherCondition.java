package fr.paladium.palawither.common.wither.condition.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle;
import fr.paladium.palawither.common.wither.base.IWither;
import fr.paladium.palawither.common.wither.condition.IWitherCondition;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class PlayerCountWitherCondition implements IWitherCondition {
  private final int min;
  
  private final int max;
  
  private PlayerCountWitherCondition(int min, int max) {
    this.min = min;
    this.max = max;
  }
  
  @NonNull
  public static PlayerCountWitherCondition create(int min, int max) {
    return new PlayerCountWitherCondition(min, max);
  }
  
  @NonNull
  public static PlayerCountWitherCondition min(int min) {
    return new PlayerCountWitherCondition(min, 2147483647);
  }
  
  @NonNull
  public static PlayerCountWitherCondition max(int max) {
    return new PlayerCountWitherCondition(0, max);
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
    int playerCount = (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b.size();
    if (playerCount < this.min)
      throw new IWitherCondition.WitherConditionException("le nombre de joueurs en ligne doit être supérieur ou égal à " + this.min + ", il y en a actuellement " + playerCount + "."); 
    if (playerCount > this.max)
      throw new IWitherCondition.WitherConditionException("le nombre de joueurs en ligne doit être inférieur ou égal à " + this.max + ", il y en a actuellement " + playerCount + "."); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\condition\impl\PlayerCountWitherCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */