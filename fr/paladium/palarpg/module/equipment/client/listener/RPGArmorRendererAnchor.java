package fr.paladium.palarpg.module.equipment.client.listener;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public enum RPGArmorRendererAnchor {
  NONE,
  HEAD(0.0F, 1.0F, 0.0F),
  BODY,
  LEFT_ARM,
  RIGHT_ARM,
  LEFT_LEG(0.0F, -3.0F, 3.9F),
  RIGHT_LEG(0.0F, -3.0F, 3.9F),
  LEFT_FOOT(0.0F, -3.0F, 3.9F),
  RIGHT_FOOT(0.0F, -3.0F, 3.9F);
  
  RPGArmorRendererAnchor(float sneakTranslationX, float sneakTranslationY, float sneakTranslationZ) {
    this.sneakTranslationX = sneakTranslationX;
    this.sneakTranslationY = sneakTranslationY;
    this.sneakTranslationZ = sneakTranslationZ;
  }
  
  private float sneakTranslationX;
  
  private float sneakTranslationY;
  
  private float sneakTranslationZ;
  
  public float getSneakTranslationX() {
    return this.sneakTranslationX;
  }
  
  public float getSneakTranslationY() {
    return this.sneakTranslationY;
  }
  
  public float getSneakTranslationZ() {
    return this.sneakTranslationZ;
  }
  
  public final ModelRenderer getModel(ModelBiped model) {
    switch (this) {
      case HEAD:
        return model.field_78116_c;
      case BODY:
        return model.field_78115_e;
      case LEFT_ARM:
        return model.field_78113_g;
      case RIGHT_ARM:
        return model.field_78112_f;
      case LEFT_LEG:
      case LEFT_FOOT:
        return model.field_78124_i;
      case RIGHT_LEG:
      case RIGHT_FOOT:
        return model.field_78123_h;
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\client\listener\RPGArmorRendererAnchor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */