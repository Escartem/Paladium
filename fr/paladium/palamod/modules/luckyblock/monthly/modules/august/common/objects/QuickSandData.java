package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class QuickSandData {
  private DoubleLocation location;
  
  private boolean jumping;
  
  private long creationMillis;
  
  public void setLocation(DoubleLocation location) {
    this.location = location;
  }
  
  public void setJumping(boolean jumping) {
    this.jumping = jumping;
  }
  
  public void setCreationMillis(long creationMillis) {
    this.creationMillis = creationMillis;
  }
  
  public DoubleLocation getLocation() {
    return this.location;
  }
  
  public boolean isJumping() {
    return this.jumping;
  }
  
  public long getCreationMillis() {
    return this.creationMillis;
  }
  
  public QuickSandData(EntityPlayer player) {
    this.location = new DoubleLocation((Entity)player);
    this.jumping = false;
    this.creationMillis = System.currentTimeMillis();
  }
  
  public void update(EntityPlayer player) {
    update(new DoubleLocation((Entity)player));
  }
  
  public void update(DoubleLocation location) {
    this.location = location;
    this.creationMillis = System.currentTimeMillis();
  }
  
  public boolean canUpdate() {
    return (System.currentTimeMillis() - this.creationMillis >= 500L);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\objects\QuickSandData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */