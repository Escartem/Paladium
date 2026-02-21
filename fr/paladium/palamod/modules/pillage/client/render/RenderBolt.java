package fr.paladium.palamod.modules.pillage.client.render;

import fr.paladium.palamod.modules.pillage.utils.PPillageUtils;
import java.util.Random;
import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3d;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class RenderBolt {
  public static void renderBoltBetween(Vector3d point1, Vector3d point2, double scale, double maxDeflection, int maxSegments, long boltSeed) {
    Tessellator tessellator = Tessellator.field_78398_a;
    Random random = new Random(boltSeed);
    GL11.glDisable(3553);
    GL11.glDisable(2896);
    GL11.glEnable(3042);
    OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 200.0F, 200.0F);
    GL11.glBlendFunc(770, 1);
    double distance = PPillageUtils.getDistanceAtoB(point1, point2);
    Vector3d dirVec = PPillageUtils.getDirectionVec(point1, point2);
    Vector3d invDir = new Vector3d(1.0D, 1.0D, 1.0D);
    invDir.sub((Tuple3d)dirVec);
    Vector3d[] vectors = new Vector3d[maxSegments / 2 + random.nextInt(maxSegments / 2)];
    vectors[0] = point1;
    vectors[vectors.length - 1] = point2;
    for (int i = 1; i < vectors.length - 1; i++) {
      double pos = i / vectors.length * distance;
      Vector3d point = (Vector3d)point1.clone();
      Vector3d dirVecClone = (Vector3d)dirVec.clone();
      dirVecClone.scale(pos);
      point.add((Tuple3d)dirVecClone);
      double randX = (-0.5D + random.nextDouble()) * maxDeflection * invDir.x;
      double randY = (-0.5D + random.nextDouble()) * maxDeflection * invDir.y;
      double randZ = (-0.5D + random.nextDouble()) * maxDeflection * invDir.z;
      point.add((Tuple3d)new Vector3d(randX, randY, randZ));
      vectors[i] = point;
    } 
    double rScale = scale * (0.5D + random.nextDouble() * 0.5D);
    for (int j = 1; j < vectors.length; j++)
      drawBoltSegment(tessellator, vectors[j - 1], vectors[j], (float)rScale); 
    GL11.glDisable(3042);
    GL11.glEnable(2896);
    GL11.glEnable(3553);
    GL11.glBlendFunc(770, 771);
  }
  
  private static void drawBoltSegment(Tessellator tessellator, Vector3d p1, Vector3d p2, float scale) {
    GL11.glPushMatrix();
    GL11.glTranslated(p1.x, p1.y, p1.z);
    double dist = PPillageUtils.getDistanceAtoB(p1, p2);
    float xd = (float)(p1.x - p2.x);
    float yd = (float)(p1.y - p2.y);
    float zd = (float)(p1.z - p2.z);
    double var7 = MathHelper.func_76133_a((xd * xd + zd * zd));
    float rotYaw = (float)(Math.atan2(xd, zd) * 180.0D / Math.PI);
    float rotPitch = (float)(Math.atan2(yd, var7) * 180.0D / Math.PI);
    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
    GL11.glRotatef(180.0F + rotYaw, 0.0F, 0.0F, -1.0F);
    GL11.glRotatef(rotPitch, 1.0F, 0.0F, 0.0F);
    tessellator.func_78371_b(5);
    tessellator.func_78369_a(0.35F, 0.65F, 0.9F, 0.3F);
    for (int i = 0; i <= 9; i++) {
      float f = (i + 1.0F) / 9.0F;
      float verX = MathHelper.func_76126_a((i % 3) * 3.1415927F * 2.0F / 3.0F) * f * scale;
      float verZ = MathHelper.func_76134_b((i % 3) * 3.1415927F * 2.0F / 3.0F) * f * scale;
      tessellator.func_78377_a(verX, dist, verZ);
      tessellator.func_78377_a(verX, 0.0D, verZ);
    } 
    tessellator.func_78381_a();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\client\render\RenderBolt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */