package fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.server.dto;

import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.CosmeticData;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.part.GeckoCosmeticData;
import lombok.NonNull;

public class KillCosmeticData extends CosmeticData {
  private GeckoCosmeticData kill;
  
  private GeckoCosmeticData player;
  
  public void setKill(GeckoCosmeticData kill) {
    this.kill = kill;
  }
  
  public void setPlayer(GeckoCosmeticData player) {
    this.player = player;
  }
  
  public String toString() {
    return "KillAnimationCosmetic.KillCosmeticData(kill=" + getKill() + ", player=" + getPlayer() + ")";
  }
  
  public GeckoCosmeticData getKill() {
    return this.kill;
  }
  
  public GeckoCosmeticData getPlayer() {
    return this.player;
  }
  
  public KillCosmeticData(@NonNull GeckoCosmeticData kill, @NonNull GeckoCosmeticData player, @NonNull String thumbnail, @NonNull String thumbnailAnimation) {
    super(thumbnail, thumbnailAnimation);
    if (kill == null)
      throw new NullPointerException("kill is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (thumbnail == null)
      throw new NullPointerException("thumbnail is marked non-null but is null"); 
    if (thumbnailAnimation == null)
      throw new NullPointerException("thumbnailAnimation is marked non-null but is null"); 
    this.kill = kill;
    this.player = player;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killanimation\server\dto\KillAnimationCosmetic$KillCosmeticData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */