package fr.paladium.palamod.modules.luckyblock.events.june.objetcs;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.EntityDancer;
import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.enums.DanceDirection;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class DancerUser {
  private EntityDancer entity;
  
  private Location lastLocation;
  
  private int startY;
  
  private int danceIndex;
  
  public void setEntity(EntityDancer entity) {
    this.entity = entity;
  }
  
  public void setLastLocation(Location lastLocation) {
    this.lastLocation = lastLocation;
  }
  
  public void setStartY(int startY) {
    this.startY = startY;
  }
  
  public void setDanceIndex(int danceIndex) {
    this.danceIndex = danceIndex;
  }
  
  public EntityDancer getEntity() {
    return this.entity;
  }
  
  public Location getLastLocation() {
    return this.lastLocation;
  }
  
  public int getStartY() {
    return this.startY;
  }
  
  public int getDanceIndex() {
    return this.danceIndex;
  }
  
  public DancerUser(EntityDancer entity, EntityPlayer player) {
    this.entity = entity;
    this.lastLocation = new Location((Entity)player);
    this.startY = (int)this.lastLocation.getY();
    this.danceIndex = 0;
  }
  
  public boolean hasMoved(int x, int y, int z) {
    return ((int)this.lastLocation.getX() != x || 
      (int)this.lastLocation.getY() != y || 
      (int)this.lastLocation.getZ() != z);
  }
  
  public boolean handleFinish(DanceDirection direction, EntityPlayer player) {
    if (direction == DanceDirection.FINISHED) {
      this.danceIndex = 0;
      String text = "§eBien joué ! Tu as réussi à imiter le danseur §d" + this.entity.getDancePattern().getUsername() + "§e !";
      PlayerUtils.sendMessage(player, text);
      if (this.entity.nextPattern()) {
        this.entity.resetPosition();
        text = "§eNouveau danseur : §d" + this.entity.getDancePattern().getUsername();
      } else {
        text = "§eTu as fini le challenge !";
        this.entity.giveReward();
      } 
      PlayerUtils.sendMessage(player, text);
      return true;
    } 
    return false;
  }
  
  public boolean dance(EntityPlayer player) {
    boolean isInCombat = LuckyBlockUtils.isInCombat(player);
    DanceDirection direction = this.entity.getNextDanceDirection(this.danceIndex);
    Location targetLocation = DanceDirection.getRelativeLocation(this.lastLocation, direction);
    Location currentLocation = new Location((Entity)player);
    if (handleFinish(direction, player))
      return true; 
    DanceDirection lastDirection = this.entity.getDanceDirection(this.danceIndex - 1);
    this.lastLocation = currentLocation;
    if (currentLocation.equals(targetLocation)) {
      this.danceIndex++;
      if (!isInCombat)
        PlayerUtils.sendMessage(player, "§eMouvement de danse réalisé ! §7(" + this.danceIndex + "/" + this.entity.getDancePattern().getDirections().size() + ")"); 
      direction = this.entity.getNextDanceDirection(this.danceIndex);
      if (handleFinish(direction, player))
        return true; 
    } else {
      if (lastDirection == DanceDirection.JUMP && (int)currentLocation.getY() == this.startY)
        return true; 
      if (!isInCombat)
        PlayerUtils.sendMessage(player, "§cTu n'as pas réalisé le bon pas de danse, Le Danseur va recommencer."); 
      this.danceIndex = 0;
      this.entity.reset();
      return false;
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\june\objetcs\DancerUser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */