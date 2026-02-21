package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.dto;

import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.CosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.EmoteCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.server.dto.EmoteCosmetic;
import lombok.NonNull;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;

public class EmoteCosmeticClient extends CosmeticClient<EmoteCosmetic.EmoteCosmeticProperties> {
  private LindwormModel<LindwormAnimatable> emoteModel;
  
  private LindwormModel<LindwormAnimatable> effectModel;
  
  public String toString() {
    return "EmoteCosmeticClient(emoteModel=" + getEmoteModel() + ", effectModel=" + getEffectModel() + ")";
  }
  
  public LindwormModel<LindwormAnimatable> getEmoteModel() {
    return this.emoteModel;
  }
  
  public LindwormModel<LindwormAnimatable> getEffectModel() {
    return this.effectModel;
  }
  
  public EmoteCosmeticClient(@NonNull String id) {
    super(id);
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
  }
  
  public EmoteCosmeticClient load(LindwormModel<LindwormAnimatable> emoteModel, LindwormModel<LindwormAnimatable> effectModel) {
    this.emoteModel = emoteModel;
    this.effectModel = effectModel;
    load(null);
    return this;
  }
  
  @NonNull
  public CosmeticFactory getFactory() {
    return (CosmeticFactory)EmoteCosmeticFactory.getInstance();
  }
  
  public boolean hasEffectModel() {
    return (this.effectModel != null && this.effectModel.getModel() != null);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\client\dto\EmoteCosmeticClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */