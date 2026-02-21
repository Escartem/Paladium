package fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.render.world;

import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.dto.CloakCosmeticClient;
import fr.paladium.zephyrui.lib.color.Color;
import lombok.NonNull;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class CloakCosmeticRenderer {
  private static final RenderPlayer RENDER_PLAYER = (RenderPlayer)RenderManager.field_78727_a.func_78715_a(EntityPlayer.class);
  
  public static void render(@NonNull EntityPlayer entity, @NonNull CloakCosmeticClient cosmetic, float partialTicks) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (!cosmetic.isLoaded())
      return; 
    double chasingPosX = entity.field_71091_bM + (entity.field_71094_bP - entity.field_71091_bM) * partialTicks - entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * partialTicks;
    double chasingPosY = entity.field_71096_bN + (entity.field_71095_bQ - entity.field_71096_bN) * partialTicks - entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * partialTicks;
    double chasingPosZ = entity.field_71097_bO + (entity.field_71085_bR - entity.field_71097_bO) * partialTicks - entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * partialTicks;
    float yawOffset = entity.field_70760_ar + (entity.field_70761_aq - entity.field_70760_ar) * partialTicks;
    float cameraYaw = entity.field_71107_bF + (entity.field_71109_bG - entity.field_71107_bF) * partialTicks;
    float yawOffsetRadians = yawOffset * 3.1415927F / 180.0F;
    double yawOffsetSin = MathHelper.func_76126_a(yawOffsetRadians);
    double yawOffsetCos = -MathHelper.func_76134_b(yawOffsetRadians);
    chasingPosY = ((float)chasingPosY * 10.0F);
    chasingPosY = Math.min(32.0D, Math.max(chasingPosY, -6.0D));
    float rotateX = (float)Math.max(0.0D, (chasingPosX * yawOffsetSin + chasingPosZ * yawOffsetCos) * 100.0D);
    float rotateYZ = (float)(chasingPosX * yawOffsetCos - chasingPosZ * yawOffsetSin) * 100.0F;
    chasingPosY += (MathHelper.func_76126_a((entity.field_70141_P + (entity.field_70140_Q - entity.field_70141_P) * partialTicks) * 6.0F) * 32.0F * cameraYaw);
    if (entity.func_70093_af())
      chasingPosY += 25.0D; 
    GL11.glPushMatrix();
    GL11.glTranslatef(0.0F, 0.0F, 0.125F);
    if (entity instanceof fr.paladium.palashop.common.utils.player.FakeEntityPlayerMP) {
      float animation = (float)Math.sin(6.283185307179586D * (System.currentTimeMillis() % 6000L) / 3000.0D);
      GL11.glRotatef(10.0F + animation * 2.0F, 1.0F, 0.0F, 0.0F);
    } else {
      GL11.glRotatef(Math.min(6.0F + rotateX / 2.0F + (float)chasingPosY, 75.0F), 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(rotateYZ / 2.0F, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(-rotateYZ / 2.0F, 0.0F, 1.0F, 0.0F);
    } 
    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
    Color.reset();
    cosmetic.getTexture().bind(() -> {
          ModelBiped model = RENDER_PLAYER.field_77109_a;
          model.func_78111_c(0.0625F);
        });
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\cloak\client\render\world\CloakCosmeticRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */