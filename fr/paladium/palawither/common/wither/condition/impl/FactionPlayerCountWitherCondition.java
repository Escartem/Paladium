package fr.paladium.palawither.common.wither.condition.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.factions.api.FactionAPI;
import fr.paladium.factions.api.entity.IRelationshipEntity;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.player.IPlayer;
import fr.paladium.factions.api.type.FactionRelationshipType;
import fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle;
import fr.paladium.palawither.common.wither.base.IWither;
import fr.paladium.palawither.common.wither.condition.IWitherCondition;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FactionPlayerCountWitherCondition implements IWitherCondition {
  private final int min;
  
  private final int max;
  
  private final FactionRelationshipType type;
  
  private FactionPlayerCountWitherCondition(int min, int max, FactionRelationshipType type) {
    this.min = min;
    this.max = max;
    this.type = type;
  }
  
  @NonNull
  public static FactionPlayerCountWitherCondition create(@NonNull FactionRelationshipType type, int min, int max) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return new FactionPlayerCountWitherCondition(min, max, type);
  }
  
  @NonNull
  public static FactionPlayerCountWitherCondition min(@NonNull FactionRelationshipType type, int min) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return new FactionPlayerCountWitherCondition(min, 2147483647, type);
  }
  
  @NonNull
  public static FactionPlayerCountWitherCondition max(@NonNull FactionRelationshipType type, int max) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return new FactionPlayerCountWitherCondition(0, max, type);
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
    IPlayer factionPlayer = FactionAPI.getInstance().getPlayer(player);
    if (factionPlayer == null || !factionPlayer.hasFaction() || factionPlayer.isAdminMode())
      return; 
    IFaction faction = factionPlayer.getFaction();
    IRelationshipEntity relationshipEntity = faction.getRelationshipEntity();
    List<UUID> relations = null;
    switch (this.type) {
      case ALLY:
        relations = relationshipEntity.getFactionAllies();
        break;
      case ENEMY:
        relations = relationshipEntity.getFactionEnemies();
        break;
      case TRUCE:
        relations = relationshipEntity.getFactionTruces();
        break;
    } 
    int playerCount = 0;
    if (relations != null && !relations.isEmpty())
      for (UUID relationUUID : relations) {
        IFaction relation = FactionAPI.getInstance().getFaction(relationUUID);
        if (relation == null)
          continue; 
        playerCount += relation.getMemberEntity().getOnlinePlayers().size();
      }  
    if (playerCount < this.min)
      throw new IWitherCondition.WitherConditionException("le nombre de joueurs en " + TTT.format(this.type.getTranslateKey(), new Object[0]) + " doit être supérieur ou égal à " + this.min + ", il y en a actuellement " + playerCount + "."); 
    if (playerCount > this.max)
      throw new IWitherCondition.WitherConditionException("le nombre de joueurs en " + TTT.format(this.type.getTranslateKey(), new Object[0]) + " doit être inférieur ou égal à " + this.max + ", il y en a actuellement " + playerCount + "."); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\condition\impl\FactionPlayerCountWitherCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */