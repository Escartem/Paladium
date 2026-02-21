package fr.paladium.palamod.modules.alchimiste.common.init;

import fr.paladium.palamod.modules.alchimiste.common.utils.EnumPortalType;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.PortalRecipe;
import java.util.HashMap;
import java.util.Map;

public class PortalController {
  private static final Map<EnumPortalType, PortalRecipe> RECIPES = new HashMap<>();
  
  public Map<EnumPortalType, PortalRecipe> getRecipes() {
    return RECIPES;
  }
  
  public void addRecipe(EnumPortalType a, PortalRecipe b) {
    RECIPES.put(a, b);
  }
  
  public PortalRecipe getRecipeFor(EnumPortalType type) {
    return RECIPES.get(type);
  }
  
  public PortalRecipe getRecipeFor(int meta) {
    for (EnumPortalType t : EnumPortalType.values()) {
      if (t.getMetaType() == meta)
        return RECIPES.get(t); 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\init\PortalController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */