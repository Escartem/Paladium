package fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.dto;

import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.CosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.KillAnimationCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.server.dto.KillAnimationCosmetic;
import fr.paladium.zephyrui.lib.resource.Resource;
import lombok.NonNull;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;

public class KillAnimationCosmeticClient extends CosmeticClient<KillAnimationCosmetic.KillCosmeticProperties> {
  private LindwormModel<LindwormAnimatable> killModel;
  
  private LindwormModel<LindwormAnimatable> playerModel;
  
  public String toString() {
    return "KillAnimationCosmeticClient(killModel=" + getKillModel() + ", playerModel=" + getPlayerModel() + ")";
  }
  
  public LindwormModel<LindwormAnimatable> getKillModel() {
    return this.killModel;
  }
  
  public LindwormModel<LindwormAnimatable> getPlayerModel() {
    return this.playerModel;
  }
  
  public KillAnimationCosmeticClient(@NonNull String id) {
    super(id);
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
  }
  
  public KillAnimationCosmeticClient load(LindwormModel<LindwormAnimatable> killModel, LindwormModel<LindwormAnimatable> playerModel, Resource thumbnail) {
    this.killModel = killModel;
    this.playerModel = playerModel;
    load(thumbnail);
    return this;
  }
  
  @NonNull
  public CosmeticFactory getFactory() {
    return (CosmeticFactory)KillAnimationCosmeticFactory.getInstance();
  }
  
  public boolean hasPlayerModel() {
    return (this.playerModel != null);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killanimation\client\dto\KillAnimationCosmeticClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */