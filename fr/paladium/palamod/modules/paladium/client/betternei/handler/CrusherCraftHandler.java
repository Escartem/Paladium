package fr.paladium.palamod.modules.paladium.client.betternei.handler;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.palamod.modules.paladium.client.betternei.ui.UICrusherCraftOverlay;
import fr.paladium.palamod.modules.paladium.common.container.CrusherContainer;
import fr.paladium.palamod.modules.paladium.common.logics.TileCrusher;

public class CrusherCraftHandler extends ACraftHandler<CrusherContainer> {
  public String getId() {
    return "CRUSHER";
  }
  
  public Class<CrusherContainer> getApplicableContainer() {
    return CrusherContainer.class;
  }
  
  public <T extends fr.paladium.betternei.client.ui.overlay.UIItemOverlay<?>> Class<T> getOverlay() {
    return (Class)UICrusherCraftOverlay.class;
  }
  
  public void loadCrafts() {
    for (TileCrusher.EnumCrusherRecipes crushRecipe : TileCrusher.EnumCrusherRecipes.values())
      TileCrusher.EnumCrusherResult enumCrusherResult = crushRecipe.getResult(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betternei\handler\CrusherCraftHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */