package fr.paladium.palamod.modules.back2future.core.utils;

import fr.paladium.palamod.modules.back2future.client.model.FakeModelRenderer;
import fr.paladium.palamod.modules.back2future.client.model.KeyFrame;
import net.minecraft.client.model.ModelRenderer;

public class ModelUtils {
  public static void setPiece(ModelRenderer targetPart, float ax, float ay, float az, float px, float py, float pz) {
    targetPart.field_78795_f = (float)Math.toRadians(ax);
    targetPart.field_78796_g = (float)Math.toRadians(ay);
    targetPart.field_78808_h = (float)Math.toRadians(az);
    targetPart.field_78800_c = px;
    targetPart.field_78797_d = py;
    targetPart.field_78798_e = pz;
  }
  
  public static void teleportPiece(ModelRenderer targetPart, FakeModelRenderer destinationPart) {
    targetPart.field_78795_f = destinationPart.rotateAngleX;
    targetPart.field_78796_g = destinationPart.rotateAngleY;
    targetPart.field_78808_h = destinationPart.rotateAngleZ;
    targetPart.field_78800_c = destinationPart.rotationPointX;
    targetPart.field_78797_d = destinationPart.rotationPointY;
    targetPart.field_78798_e = destinationPart.rotationPointZ;
  }
  
  public static void movePieceOverTime(ModelRenderer targetPart, FakeModelRenderer destinationPart, int ticks) {
    destinationPart.rotateAngleXDist = (destinationPart.rotateAngleXDist == 0.0F) ? (targetPart.field_78795_f - destinationPart.rotateAngleX) : destinationPart.rotateAngleXDist;
    targetPart.field_78795_f = movePart(targetPart.field_78795_f, destinationPart.rotateAngleX, destinationPart.rotateAngleXDist / ticks);
    destinationPart.rotateAngleYDist = (destinationPart.rotateAngleYDist == 0.0F) ? (targetPart.field_78796_g - destinationPart.rotateAngleY) : destinationPart.rotateAngleYDist;
    targetPart.field_78796_g = movePart(targetPart.field_78796_g, destinationPart.rotateAngleY, destinationPart.rotateAngleYDist / ticks);
    destinationPart.rotateAngleZDist = (destinationPart.rotateAngleZDist == 0.0F) ? (targetPart.field_78808_h - destinationPart.rotateAngleZ) : destinationPart.rotateAngleZDist;
    targetPart.field_78808_h = movePart(targetPart.field_78808_h, destinationPart.rotateAngleZ, destinationPart.rotateAngleZDist / ticks);
    destinationPart.rotationPointXDist = (destinationPart.rotationPointXDist == 0.0F) ? (targetPart.field_78800_c - destinationPart.rotationPointX) : destinationPart.rotationPointXDist;
    targetPart.field_78800_c = movePart(targetPart.field_78800_c, destinationPart.rotationPointX, destinationPart.rotationPointXDist / ticks);
    destinationPart.rotationPointYDist = (destinationPart.rotationPointYDist == 0.0F) ? (targetPart.field_78797_d - destinationPart.rotationPointY) : destinationPart.rotationPointYDist;
    targetPart.field_78797_d = movePart(targetPart.field_78797_d, destinationPart.rotationPointY, destinationPart.rotationPointYDist / ticks);
    destinationPart.rotationPointZDist = (destinationPart.rotationPointZDist == 0.0F) ? (targetPart.field_78798_e - destinationPart.rotationPointZ) : destinationPart.rotationPointZDist;
    targetPart.field_78798_e = movePart(targetPart.field_78798_e, destinationPart.rotationPointZ, destinationPart.rotationPointZDist / ticks);
  }
  
  public static float movePart(float part, float destination, float speed) {
    speed = (speed < 0.0F) ? -speed : speed;
    if (part < destination) {
      part += speed;
    } else if (part > destination + speed) {
      part -= speed;
    } else {
      part = destination;
    } 
    return part;
  }
  
  public static void movePiecePos(ModelRenderer targetPart, FakeModelRenderer destinationPart, float speed) {
    targetPart.field_78800_c += (destinationPart.rotationPointX - targetPart.field_78800_c) / speed;
    targetPart.field_78797_d += (destinationPart.rotationPointY - targetPart.field_78797_d) / speed;
    targetPart.field_78798_e += (destinationPart.rotationPointZ - targetPart.field_78798_e) / speed;
  }
  
  public static void movePieceAng(ModelRenderer targetPart, FakeModelRenderer destinationPart, float speed) {
    targetPart.field_78795_f += (destinationPart.rotateAngleX - targetPart.field_78795_f) / speed;
    targetPart.field_78796_g += (destinationPart.rotateAngleY - targetPart.field_78796_g) / speed;
    targetPart.field_78808_h += (destinationPart.rotateAngleZ - targetPart.field_78808_h) / speed;
  }
  
  public float toRadians(float degrees) {
    return degrees * 0.0174533F;
  }
  
  public static void moveParts(int frame, ModelRenderer part, KeyFrame[] keyArray, float partialTick) {
    int keyId = getKeyFrameNum(frame, keyArray);
    KeyFrame curKey = keyArray[keyId];
    if (keyArray.length == 1 || frame == 0 || frame == (keyArray[keyArray.length - 1]).frame) {
      part.field_78800_c = curKey.posX;
      part.field_78797_d = curKey.posY;
      part.field_78798_e = curKey.posZ;
      part.field_78795_f = curKey.rotX * 0.0174533F;
      part.field_78796_g = curKey.rotY * 0.0174533F;
      part.field_78808_h = curKey.rotZ * 0.0174533F;
    } else {
      KeyFrame nextKey = keyArray[keyId + 1];
      float step = (nextKey.posX - curKey.posX) / (nextKey.frame - curKey.frame);
      float position = (frame - curKey.frame) * step;
      float nextPosition = (frame + 1 - curKey.frame) * step;
      part.field_78800_c = curKey.posX + position + partialTick * (nextPosition - position);
      step = (nextKey.posY - curKey.posY) / (nextKey.frame - curKey.frame);
      position = (frame - curKey.frame) * step;
      nextPosition = (frame + 1 - curKey.frame) * step;
      part.field_78797_d = curKey.posY + position + partialTick * (nextPosition - position);
      step = (nextKey.posZ - curKey.posZ) / (nextKey.frame - curKey.frame);
      position = (frame - curKey.frame) * step;
      nextPosition = (frame + 1 - curKey.frame) * step;
      part.field_78798_e = curKey.posZ + position + partialTick * (nextPosition - position);
      step = (nextKey.rotX - curKey.rotX) / (nextKey.frame - curKey.frame);
      position = (frame - curKey.frame) * step;
      nextPosition = (frame + 1 - curKey.frame) * step;
      part.field_78795_f = (curKey.rotX + position + partialTick * (nextPosition - position)) * 0.0174533F;
      step = (nextKey.rotY - curKey.rotY) / (nextKey.frame - curKey.frame);
      position = (frame - curKey.frame) * step;
      nextPosition = (frame + 1 - curKey.frame) * step;
      part.field_78796_g = (curKey.rotY + position + partialTick * (nextPosition - position)) * 0.0174533F;
      step = (nextKey.rotZ - curKey.rotZ) / (nextKey.frame - curKey.frame);
      position = (frame - curKey.frame) * step;
      nextPosition = (frame + 1 - curKey.frame) * step;
      part.field_78808_h = (curKey.rotZ + position + partialTick * (nextPosition - position)) * 0.0174533F;
    } 
  }
  
  public static int getKeyFrameNum(int frame, KeyFrame[] keyArray) {
    if (keyArray.length == 1)
      return 0; 
    for (int x = 0; x < keyArray.length; x++) {
      if (frame == (keyArray[x]).frame)
        return x; 
      if (frame > (keyArray[x]).frame && frame < (keyArray[x + 1]).frame)
        return x; 
    } 
    return 0;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\cor\\utils\ModelUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */