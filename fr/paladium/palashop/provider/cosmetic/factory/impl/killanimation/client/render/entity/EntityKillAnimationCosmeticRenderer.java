package fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.render.entity;

import fr.paladium.palashop.client.ui.UIShopBase;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.dto.KillAnimationCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.common.entity.EntityKillAnimationCosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.server.dto.KillAnimationCosmetic;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.resource.ResourceBuilder;
import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;

public class EntityKillAnimationCosmeticRenderer extends Render {
  private static final ResourceBuilder BUILDER = ResourceBuilder.create().async().nearest();
  
  public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float pitch) {
    if (!(entity instanceof EntityKillAnimationCosmetic) || entity.field_70128_L)
      return; 
    EntityKillAnimationCosmetic kill = (EntityKillAnimationCosmetic)entity;
    if (!(kill.getCosmetic() instanceof KillAnimationCosmeticClient))
      return; 
    KillAnimationCosmeticClient cosmetic = (KillAnimationCosmeticClient)kill.getCosmetic();
    if (!cosmetic.isLoaded() || cosmetic.getKillModel().getTexture() == null)
      return; 
    float distance = (float)(Minecraft.func_71410_x()).field_71439_g.func_70068_e(entity);
    float opacity = (Minecraft.func_71410_x()).field_71474_y.field_74325_U ? 1.0F : ((distance < 4.0F) ? 0.0F : ((distance - 4.0F) / 8.0F));
    GL11.glPushMatrix();
    GL11.glAlphaFunc(516, 0.0F);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(3042);
    GL11.glEnable(2884);
    GL11.glTranslated(x, y, z);
    GL11.glRotated(entity.field_70177_z, 0.0D, 1.0D, 0.0D);
    LindwormRenderer.renderModel(entity, cosmetic.getKillModel(), ((KillAnimationCosmetic.KillCosmeticProperties)cosmetic.getProperties()).getTransform(), Color.WHITE.copyAlpha(opacity), ZUI.isOpen(UIShopBase.class));
    if (cosmetic.getPlayerModel() != null) {
      ResourceLocation resourceLocation = AbstractClientPlayer.field_110314_b;
      resourceLocation = AbstractClientPlayer.func_110311_f(kill.getPlayer());
      downloadImageSkin(resourceLocation, kill.getPlayer());
      BUILDER.of(resourceLocation).nearest().bind(() -> LindwormRenderer.renderModel(entity, cosmetic.getPlayerModel(), ((KillAnimationCosmetic.KillCosmeticProperties)cosmetic.getProperties()).getTransform(), Color.WHITE.copyAlpha(opacity), ZUI.isOpen(UIShopBase.class)));
    } 
    GL11.glAlphaFunc(516, 0.1F);
    GL11.glPopMatrix();
  }
  
  public ResourceLocation func_110775_a(Entity entity) {
    return null;
  }
  
  private void downloadImageSkin(ResourceLocation texture, String name) {
    TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
    Object object = texturemanager.func_110581_b(texture);
    if (object == null) {
      object = new ThreadDownloadImageData((File)null, String.format("https://minotar.net/skin/%s", new Object[] { StringUtils.func_76338_a(name) }), AbstractClientPlayer.field_110314_b, (IImageBuffer)new ImageBufferDownload());
      texturemanager.func_110579_a(texture, (ITextureObject)object);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killanimation\client\render\entity\EntityKillAnimationCosmeticRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */