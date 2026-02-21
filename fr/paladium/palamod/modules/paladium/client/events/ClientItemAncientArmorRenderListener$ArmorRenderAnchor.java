package fr.paladium.palamod.modules.paladium.client.events;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public enum ArmorRenderAnchor {
  NONE,
  HEAD(0.0F, 1.0F, 0.0F),
  BODY,
  LEFT_ARM,
  RIGHT_ARM,
  LEFT_LEG(0.0F, -3.0F, 3.9F),
  RIGHT_LEG(0.0F, -3.0F, 3.9F),
  LEFT_FOOT(0.0F, -3.0F, 3.9F),
  RIGHT_FOOT(0.0F, -3.0F, 3.9F);
  
  ArmorRenderAnchor(float sneakTranslationX, float sneakTranslationY, float sneakTranslationZ) {
    this.sneakTranslationX = sneakTranslationX;
    this.sneakTranslationY = sneakTranslationY;
    this.sneakTranslationZ = sneakTranslationZ;
  }
  
  private float sneakTranslationX;
  
  private float sneakTranslationY;
  
  private float sneakTranslationZ;
  
  public final ModelRenderer getModel(ModelBiped model) {
    switch (ClientItemAncientArmorRenderListener.null.$SwitchMap$fr$paladium$palamod$modules$paladium$client$events$ClientItemAncientArmorRenderListener$ArmorRenderAnchor[ordinal()]) {
      case 1:
        return model.field_78116_c;
      case 2:
        return model.field_78115_e;
      case 3:
        return model.field_78113_g;
      case 4:
        return model.field_78112_f;
      case 5:
      case 6:
        return model.field_78124_i;
      case 7:
      case 8:
        return model.field_78123_h;
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\events\ClientItemAncientArmorRenderListener$ArmorRenderAnchor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */