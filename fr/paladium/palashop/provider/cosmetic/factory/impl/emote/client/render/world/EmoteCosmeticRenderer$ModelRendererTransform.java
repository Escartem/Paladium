package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.render.world;

import lombok.NonNull;
import net.minecraft.client.model.ModelRenderer;

public class ModelRendererTransform {
  private final float rotationX;
  
  private final float rotationY;
  
  private final float rotationZ;
  
  private final float positionX;
  
  private final float positionY;
  
  private final float positionZ;
  
  public ModelRendererTransform(float rotationX, float rotationY, float rotationZ, float positionX, float positionY, float positionZ) {
    this.rotationX = rotationX;
    this.rotationY = rotationY;
    this.rotationZ = rotationZ;
    this.positionX = positionX;
    this.positionY = positionY;
    this.positionZ = positionZ;
  }
  
  public float getRotationX() {
    return this.rotationX;
  }
  
  public float getRotationY() {
    return this.rotationY;
  }
  
  public float getRotationZ() {
    return this.rotationZ;
  }
  
  public float getPositionX() {
    return this.positionX;
  }
  
  public float getPositionY() {
    return this.positionY;
  }
  
  public float getPositionZ() {
    return this.positionZ;
  }
  
  public ModelRendererTransform(@NonNull ModelRenderer model) {
    if (model == null)
      throw new NullPointerException("model is marked non-null but is null"); 
    this.rotationX = model.field_78795_f;
    this.rotationY = model.field_78796_g;
    this.rotationZ = model.field_78808_h;
    this.positionX = model.field_82906_o;
    this.positionY = model.field_82908_p;
    this.positionZ = model.field_82907_q;
  }
  
  public void apply(@NonNull ModelRenderer model) {
    if (model == null)
      throw new NullPointerException("model is marked non-null but is null"); 
    model.field_78795_f = this.rotationX;
    model.field_78796_g = this.rotationY;
    model.field_78808_h = this.rotationZ;
    model.field_82906_o = this.positionX;
    model.field_82908_p = this.positionY;
    model.field_82907_q = this.positionZ;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\client\render\world\EmoteCosmeticRenderer$ModelRendererTransform.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */