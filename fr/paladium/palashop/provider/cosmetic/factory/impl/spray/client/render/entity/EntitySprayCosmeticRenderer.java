package fr.paladium.palashop.provider.cosmetic.factory.impl.spray.client.render.entity;

import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.client.dto.SprayCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.common.entity.EntitySprayCosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.server.dto.SprayCosmetic;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class EntitySprayCosmeticRenderer extends Render {
  public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float pitch) {
    if (!(entity instanceof EntitySprayCosmetic) || entity.field_70128_L)
      return; 
    EntitySprayCosmetic spray = (EntitySprayCosmetic)entity;
    if (!(spray.getCosmetic() instanceof SprayCosmeticClient))
      return; 
    SprayCosmeticClient cosmetic = (SprayCosmeticClient)spray.getCosmetic();
    if (cosmetic.getTexture() == null)
      return; 
    double offset = 0.001D;
    double translateX = -0.5D;
    double translateY = 0.0D;
    double translateZ = -0.5D;
    double rotateX = 0.0D;
    double rotateY = 0.0D;
    double rotateZ = 0.0D;
    if (spray.getDirection() == ForgeDirection.UP) {
      translateX++;
      translateY++;
      translateY += 0.001D;
      translateZ++;
      rotateX += 90.0D;
      rotateZ += 180.0D;
    } else if (spray.getDirection() == ForgeDirection.DOWN) {
      translateY -= 0.001D;
      translateZ++;
      rotateX -= 90.0D;
    } else if (spray.getDirection() == ForgeDirection.NORTH) {
      translateX++;
      translateY++;
      translateZ -= 0.001D;
      rotateZ += 180.0D;
    } else if (spray.getDirection() == ForgeDirection.SOUTH) {
      translateY++;
      translateZ++;
      translateZ += 0.001D;
      rotateY += 180.0D;
      rotateZ += 180.0D;
    } else if (spray.getDirection() == ForgeDirection.WEST) {
      translateY++;
      translateX -= 0.001D;
      rotateY += 90.0D;
      rotateZ += 180.0D;
    } else if (spray.getDirection() == ForgeDirection.EAST) {
      translateX++;
      translateX += 0.001D;
      translateY++;
      translateZ++;
      rotateY -= 90.0D;
      rotateZ += 180.0D;
    } 
    GL11.glPushMatrix();
    GL11.glPushAttrib(1048575);
    GL11.glAlphaFunc(516, 0.0F);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(3042);
    GL11.glEnable(2884);
    GL11.glDisable(2896);
    OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
    GL11.glDisable(3553);
    OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
    GL11.glTranslated(x, y, z);
    GL11.glTranslated(translateX, translateY, translateZ);
    GL11.glRotated(rotateX, 1.0D, 0.0D, 0.0D);
    GL11.glRotated(rotateY, 0.0D, 1.0D, 0.0D);
    GL11.glRotated(rotateZ, 0.0D, 0.0D, 1.0D);
    GL11.glTranslated(0.5D, 0.5D, 0.5D);
    GL11.glRotatef(entity.field_70177_z, 0.0F, 0.0F, 1.0F);
    GL11.glTranslated(-0.5D, -0.5D, -0.5D);
    DrawUtils.RESOURCE.drawImage(-(((SprayCosmetic.SprayCosmeticProperties)cosmetic.getProperties()).getWidth() - 1.0D) / 2.0D, -(((SprayCosmetic.SprayCosmeticProperties)cosmetic.getProperties()).getHeight() - 1.0D) / 2.0D, ((SprayCosmetic.SprayCosmeticProperties)cosmetic.getProperties()).getWidth(), ((SprayCosmetic.SprayCosmeticProperties)cosmetic.getProperties()).getHeight(), cosmetic.getTexture());
    GL11.glAlphaFunc(516, 0.1F);
    GL11.glPopAttrib();
    GL11.glPopMatrix();
  }
  
  public ResourceLocation func_110775_a(Entity entity) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\spray\client\render\entity\EntitySprayCosmeticRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */