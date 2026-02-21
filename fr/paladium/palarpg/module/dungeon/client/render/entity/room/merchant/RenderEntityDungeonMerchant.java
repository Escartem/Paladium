package fr.paladium.palarpg.module.dungeon.client.render.entity.room.merchant;

import fr.paladium.palarpg.module.dungeon.common.entity.room.merchant.EntityDungeonMerchant;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiFunction;
import lombok.NonNull;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.model.impl.loader.LindwormLoader;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;

public class RenderEntityDungeonMerchant extends Render {
  private static final Set<UUID> INVISIBLE_SET = new HashSet<>();
  
  private static final ResourceLocation MAMMON_MODEL_LOCATION = new ResourceLocation("palarpg", "models/entities/dungeon/merchant/mammon/model.json");
  
  private static final ResourceLocation MAMMON_ANIMATION_LOCATION = new ResourceLocation("palarpg", "models/entities/dungeon/merchant/mammon/animation.json");
  
  private static final ResourceLocation MAMMON_TEXTURE_LOCATION = new ResourceLocation("palarpg", "models/entities/dungeon/merchant/mammon/texture.png");
  
  private static final ResourceLocation MEPHISTO_MODEL_LOCATION = new ResourceLocation("palarpg", "models/entities/dungeon/merchant/mephisto/model.json");
  
  private static final ResourceLocation MEPHISTO_ANIMATION_LOCATION = new ResourceLocation("palarpg", "models/entities/dungeon/merchant/mephisto/animation.json");
  
  private static final ResourceLocation MEPHISTO_TEXTURE_LOCATION = new ResourceLocation("palarpg", "models/entities/dungeon/merchant/mephisto/texture.png");
  
  private static RenderEntityDungeonMerchant instance;
  
  private static LindwormModel<LindwormAnimatable> mammonModel;
  
  private static LindwormModel<LindwormAnimatable> mephistoModel;
  
  public RenderEntityDungeonMerchant() {
    instance = this;
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float ticks) {
    if (!(entity instanceof EntityDungeonMerchant) || entity.field_70128_L || entity.func_82150_aj() || INVISIBLE_SET.contains(entity.func_110124_au()))
      return; 
    EntityDungeonMerchant merchant = (EntityDungeonMerchant)entity;
    LindwormModel<LindwormAnimatable> model = getModel(merchant);
    LindwormAnimatable animatable = (LindwormAnimatable)model.getOrCreateAnimatable(entity);
    if (animatable != null && animatable.getCurrentAnimation() == null)
      animatable.playAnimation("idle", true); 
    GL11.glPushMatrix();
    GL11.glPushAttrib(1048575);
    GL11.glAlphaFunc(516, 0.0F);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(3042);
    GL11.glEnable(2884);
    GL11.glTranslated(x, y, z);
    GL11.glRotated((180.0F - entity.field_70177_z), 0.0D, 1.0D, 0.0D);
    LindwormRenderer.renderModel(entity, model);
    GL11.glPopAttrib();
    GL11.glPopMatrix();
  }
  
  public ResourceLocation func_110775_a(Entity entity) {
    return null;
  }
  
  @NonNull
  public static RenderEntityDungeonMerchant getInstance() {
    return instance;
  }
  
  @NonNull
  public static LindwormAnimatable getAnimatable(@NonNull EntityDungeonMerchant merchant) {
    if (merchant == null)
      throw new NullPointerException("merchant is marked non-null but is null"); 
    return (LindwormAnimatable)getModel(merchant).getOrCreateAnimatable((Entity)merchant);
  }
  
  @NonNull
  private static LindwormModel<LindwormAnimatable> getModel(@NonNull EntityDungeonMerchant merchant) {
    if (merchant == null)
      throw new NullPointerException("merchant is marked non-null but is null"); 
    if (mammonModel == null)
      mammonModel = new LindwormModel(LindwormLoader.loadModel(MAMMON_MODEL_LOCATION), LindwormLoader.loadAnimation(MAMMON_ANIMATION_LOCATION), Resource.of(MAMMON_TEXTURE_LOCATION).nearest(), LindwormAnimatable::new); 
    if (mephistoModel == null)
      mephistoModel = new LindwormModel(LindwormLoader.loadModel(MEPHISTO_MODEL_LOCATION), LindwormLoader.loadAnimation(MEPHISTO_ANIMATION_LOCATION), Resource.of(MEPHISTO_TEXTURE_LOCATION).nearest(), LindwormAnimatable::new); 
    return merchant.isMephisto() ? mephistoModel : mammonModel;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\client\render\entity\room\merchant\RenderEntityDungeonMerchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */