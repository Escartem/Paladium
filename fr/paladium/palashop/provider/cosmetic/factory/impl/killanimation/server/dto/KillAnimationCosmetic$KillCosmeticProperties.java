package fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.server.dto;

import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.CosmeticProperties;
import software.bernie.geckolib3.model.impl.dto.LindwormTransformProperty;

public class KillCosmeticProperties extends CosmeticProperties {
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


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killanimation\server\dto\KillAnimationCosmetic$KillCosmeticProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */