package fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.server.dto;

import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.Cosmetic;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.CosmeticData;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.CosmeticProperties;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.part.GeckoCosmeticData;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.KillAnimationCosmeticFactory;
import lombok.NonNull;
import software.bernie.geckolib3.model.impl.dto.LindwormTransformProperty;

public class KillAnimationCosmetic extends Cosmetic<KillAnimationCosmetic.KillCosmeticProperties> {
  private final KillCosmeticData data;
  
  public String toString() {
    return "KillAnimationCosmetic(data=" + getData() + ")";
  }
  
  public KillCosmeticData getData() {
    return this.data;
  }
  
  public KillAnimationCosmetic(@NonNull String id, @NonNull KillCosmeticProperties properties, @NonNull KillCosmeticData data) {
    super(id, properties);
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (properties == null)
      throw new NullPointerException("properties is marked non-null but is null"); 
    if (data == null)
      throw new NullPointerException("data is marked non-null but is null"); 
    this.data = data;
  }
  
  @NonNull
  public CosmeticFactory getFactory() {
    return (CosmeticFactory)KillAnimationCosmeticFactory.getInstance();
  }
  
  public static class KillCosmeticData extends CosmeticData {
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
  
  public static class KillCosmeticProperties extends CosmeticProperties {
    private long duration;
    
    private LindwormTransformProperty transform;
    
    public void setDuration(long duration) {
      this.duration = duration;
    }
    
    public void setTransform(LindwormTransformProperty transform) {
      this.transform = transform;
    }
    
    public String toString() {
      return "KillAnimationCosmetic.KillCosmeticProperties(duration=" + getDuration() + ", transform=" + getTransform() + ")";
    }
    
    public KillCosmeticProperties() {
      this.transform = new LindwormTransformProperty();
    }
    
    public KillCosmeticProperties(long duration, LindwormTransformProperty transform) {
      this.transform = new LindwormTransformProperty();
      this.duration = duration;
      this.transform = transform;
    }
    
    public long getDuration() {
      return this.duration;
    }
    
    public LindwormTransformProperty getTransform() {
      return this.transform;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killanimation\server\dto\KillAnimationCosmetic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */