package fr.paladium.palamod.modules.argus.methods;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import fr.paladium.palamod.api.i;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class ObjectTracer {
  public static double dis;
  
  public static Entity trace(Minecraft mc, Entity cPointedEntity, float q) {
    Entity newPointedEntity = cPointedEntity;
    if (mc == null || mc.field_71451_h == null)
      return newPointedEntity; 
    EntityLivingBase viewEntity = mc.field_71451_h;
    if (mc.field_71441_e == null)
      return newPointedEntity; 
    mc.field_147125_j = null;
    double baseReachDistance = mc.field_71442_b.func_78757_d();
    mc.field_71476_x = viewEntity.func_70614_a(baseReachDistance, q);
    double d1 = baseReachDistance;
    Vec3 entityPosition = viewEntity.func_70666_h(q);
    boolean flag = false;
    if (mc.field_71442_b.func_78749_i()) {
      baseReachDistance = i.b() * i.a();
      d1 = i.b() * i.a();
    } else {
      if (baseReachDistance > i.a()) {
        flag = true;
        d1 = i.a();
      } 
      if (mc.field_71476_x != null && MovingObjectPosition.MovingObjectType.BLOCK.equals(mc.field_71476_x.field_72313_a) && mc.field_71439_g.field_71071_by
        .func_70448_g() != null && mc.field_71439_g.field_71071_by
        .func_70448_g().func_77942_o() && mc.field_71439_g.field_71071_by
        .func_70448_g().func_77978_p().func_74764_b("gadgeto") && mc.field_71439_g.field_71071_by
        .func_70448_g().func_77978_p().func_74767_n("gadgeto"))
        d1++; 
      baseReachDistance = d1;
    } 
    if (mc.field_71476_x != null)
      d1 = mc.field_71476_x.field_72307_f.func_72438_d(entityPosition); 
    Vec3 lookVector = viewEntity.func_70676_i(q);
    double lookVecX = lookVector.field_72450_a * baseReachDistance;
    double lookVecY = lookVector.field_72448_b * baseReachDistance;
    double lookVecZ = lookVector.field_72449_c * baseReachDistance;
    Vec3 reachIntrcp = entityPosition.func_72441_c(lookVecX, lookVecY, lookVecZ);
    newPointedEntity = null;
    Vec3 hitVec = null;
    AxisAlignedBB boundReach = viewEntity.field_70121_D.func_72321_a(lookVecX, lookVecY, lookVecZ);
    boundReach = boundReach.func_72314_b(1.0D, 1.0D, 1.0D);
    List<Entity> list = mc.field_71441_e.func_72839_b((Entity)viewEntity, boundReach);
    double realDistance = d1;
    for (int i = 0; i < list.size(); i++) {
      Entity entity = list.get(i);
      if (entity.func_70067_L()) {
        float borderSize = entity.func_70111_Y();
        AxisAlignedBB bounds = entity.field_70121_D.func_72314_b(borderSize, borderSize, borderSize);
        MovingObjectPosition obj = bounds.func_72327_a(entityPosition, reachIntrcp);
        if (bounds.func_72318_a(entityPosition)) {
          if (0.0D < realDistance || realDistance == 0.0D) {
            newPointedEntity = entity;
            hitVec = (obj == null) ? entityPosition : obj.field_72307_f;
            realDistance = 0.0D;
          } 
        } else if (obj != null) {
          double vecDistance = entityPosition.func_72438_d(obj.field_72307_f);
          if (vecDistance < realDistance || realDistance == 0.0D)
            if (entity == viewEntity.field_70154_o && !entity.canRiderInteract()) {
              if (realDistance == 0.0D) {
                newPointedEntity = entity;
                hitVec = obj.field_72307_f;
              } 
            } else {
              newPointedEntity = entity;
              hitVec = obj.field_72307_f;
              realDistance = vecDistance;
            }  
        } 
      } 
    } 
    double lastDist = 0.0D;
    if (newPointedEntity != null && flag && (
      lastDist = entityPosition.func_72438_d(hitVec)) > i.a()) {
      newPointedEntity = null;
      int hitX = (int)hitVec.field_72450_a;
      int hitY = (int)hitVec.field_72448_b;
      int hitZ = (int)hitVec.field_72449_c;
      mc.field_71476_x = new MovingObjectPosition(hitX, hitY, hitZ, 0, hitVec, false);
    } 
    if (lastDist != 0.0D || newPointedEntity != null)
      dis = lastDist; 
    if (newPointedEntity != null && (realDistance < d1 || mc.field_71476_x == null)) {
      Entity pointed = newPointedEntity;
      mc.field_71476_x = new MovingObjectPosition(pointed, hitVec);
      if (pointed instanceof EntityLivingBase || pointed instanceof net.minecraft.entity.item.EntityItemFrame)
        mc.field_147125_j = pointed; 
    } 
    return newPointedEntity;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\methods\ObjectTracer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */