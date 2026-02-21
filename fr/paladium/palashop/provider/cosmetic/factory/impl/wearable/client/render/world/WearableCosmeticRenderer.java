package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.render.world;

import fr.paladium.palamixins.accessor.client.model.ModelRendererAccessor;
import fr.paladium.palashop.client.ui.UIShopBase;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto.WearableCosmeticAnchor;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto.WearableCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.dto.WearableCosmetic;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import lombok.NonNull;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;

public class WearableCosmeticRenderer {
  private static final RenderPlayer RENDER_PLAYER = (RenderPlayer)RenderManager.field_78727_a.func_78715_a(EntityPlayer.class);
  
  public static void render(@NonNull Entity entity, @NonNull WearableCosmeticClient cosmetic, float partialTicks) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (!cosmetic.isLoaded())
      return; 
    GL11.glPushMatrix();
    GL11.glAlphaFunc(516, 0.0F);
    if (cosmetic.getType() == WearableCosmetic.WearableCosmeticType.SUIT) {
      for (WearableCosmeticAnchor anchor : WearableCosmeticAnchor.values()) {
        GL11.glPushMatrix();
        patchCosmeticTranslations(entity, anchor, partialTicks);
        renderCosmetic(entity, cosmetic, bone -> bone.name.equalsIgnoreCase(anchor.name().toLowerCase()));
        GL11.glPopMatrix();
      } 
    } else {
      Map<String, Object> properties = (cosmetic.getModel().getModel()).properties.getProperties();
      WearableCosmeticAnchor anchor = WearableCosmeticAnchor.from(cosmetic.getType());
      if (properties != null && !properties.isEmpty() && properties.containsKey("anchor"))
        anchor = WearableCosmeticAnchor.from(properties.get("anchor").toString()); 
      patchCosmeticTranslations(entity, anchor, partialTicks);
      renderCosmetic(entity, cosmetic, null);
    } 
    GL11.glAlphaFunc(516, 0.1F);
    GL11.glPopMatrix();
  }
  
  private static void renderCosmetic(@NonNull Entity entity, @NonNull WearableCosmeticClient cosmetic, Predicate<GeoBone> predicate) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    LindwormRenderer.renderModel(entity, cosmetic.getModel(), ((WearableCosmetic.WearableCosmeticProperties)cosmetic.getProperties()).getTransform(), predicate, ZUI.isOpen(UIShopBase.class));
  }
  
  private static void patchCosmeticTranslations(@NonNull Entity entity, @NonNull WearableCosmeticAnchor anchor, float partialTicks) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    if (anchor == null)
      throw new NullPointerException("anchor is marked non-null but is null"); 
    ModelRenderer modelRenderer = anchor.getModel(RENDER_PLAYER.field_77109_a);
    if (modelRenderer == null)
      return; 
    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
    if (modelRenderer instanceof ModelRendererAccessor) {
      List<ModelRenderer> parents = new ArrayList<>();
      ModelRenderer m = modelRenderer;
      while (m instanceof ModelRendererAccessor) {
        ModelRenderer p = ((ModelRendererAccessor)m).getParent();
        if (p == null)
          break; 
        parents.add(0, p);
        m = p;
      } 
      for (ModelRenderer pr : parents) {
        GL11.glTranslatef(-pr.field_82906_o, -pr.field_82908_p, pr.field_82907_q);
        GL11.glTranslatef(-pr.field_78800_c / 16.0F, -pr.field_78797_d / 16.0F, pr.field_78798_e / 16.0F);
        GL11.glRotated(Math.toDegrees(pr.field_78808_h), 0.0D, 0.0D, 1.0D);
        GL11.glRotated(-Math.toDegrees(pr.field_78796_g), 0.0D, 1.0D, 0.0D);
        GL11.glRotated(-Math.toDegrees(pr.field_78795_f), 1.0D, 0.0D, 0.0D);
        GL11.glTranslatef(pr.field_78800_c / 16.0F, pr.field_78797_d / 16.0F, -pr.field_78798_e / 16.0F);
      } 
    } 
    GL11.glTranslatef(-modelRenderer.field_82906_o, -modelRenderer.field_82908_p, modelRenderer.field_82907_q);
    GL11.glTranslatef(-modelRenderer.field_78800_c / 16.0F, -modelRenderer.field_78797_d / 16.0F, modelRenderer.field_78798_e / 16.0F);
    GL11.glRotated(Math.toDegrees(modelRenderer.field_78808_h), 0.0D, 0.0D, 1.0D);
    GL11.glRotated(-Math.toDegrees(modelRenderer.field_78796_g), 0.0D, 1.0D, 0.0D);
    GL11.glRotated(-Math.toDegrees(modelRenderer.field_78795_f), 1.0D, 0.0D, 0.0D);
    GL11.glTranslatef(modelRenderer.field_78800_c / 16.0F, modelRenderer.field_78797_d / 16.0F, -modelRenderer.field_78798_e / 16.0F);
    GL11.glTranslatef(0.0F, -1.5F, 0.0F);
    if (entity.func_70093_af())
      GL11.glTranslatef(-anchor.getSneakTranslationX() / 16.0F, -anchor.getSneakTranslationY() / 16.0F, anchor.getSneakTranslationZ() / 16.0F); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\client\render\world\WearableCosmeticRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */