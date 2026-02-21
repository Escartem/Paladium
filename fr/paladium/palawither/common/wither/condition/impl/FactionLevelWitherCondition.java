package fr.paladium.palawither.common.wither.condition.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.factions.api.FactionAPI;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.player.IPlayer;
import fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle;
import fr.paladium.palawither.common.wither.base.IWither;
import fr.paladium.palawither.common.wither.condition.IWitherCondition;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FactionLevelWitherCondition implements IWitherCondition {
  private final int level;
  
  private FactionLevelWitherCondition(int level) {
    this.level = level;
  }
  
  @NonNull
  public static FactionLevelWitherCondition create(int level) {
    return new FactionLevelWitherCondition(level);
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
    if (this.level <= 0)
      return; 
    IPlayer factionPlayer = FactionAPI.getInstance().getPlayer(player);
    if (factionPlayer == null || factionPlayer.isAdminMode())
      return; 
    if (!factionPlayer.hasFaction())
      throw new IWitherCondition.WitherConditionException("vous devez appartenir à une faction pour invoquer ce wither."); 
    IFaction faction = factionPlayer.getFaction();
    if (faction.getLevelEntity().getLevel() < this.level)
      throw new IWitherCondition.WitherConditionException("le niveau de la faction doit être supérieur ou égal à " + this.level + ", il est actuellement de " + faction.getLevelEntity().getLevel() + "."); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\condition\impl\FactionLevelWitherCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */