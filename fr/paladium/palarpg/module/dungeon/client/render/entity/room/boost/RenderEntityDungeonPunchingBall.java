package fr.paladium.palarpg.module.dungeon.client.render.entity.room.boost;

import fr.paladium.palarpg.module.dungeon.common.entity.room.boost.EntityDungeonPunchingBall;
import fr.paladium.zephyrui.lib.resource.Resource;
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

public class RenderEntityDungeonPunchingBall extends Render {
  private static final ResourceLocation MODEL_LOCATION = new ResourceLocation("palarpg", "models/entities/dungeon/punchingball/model.json");
  
  private static final ResourceLocation ANIMATION_LOCATION = new ResourceLocation("palarpg", "models/entities/dungeon/punchingball/animation.json");
  
  private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("palarpg", "models/entities/dungeon/punchingball/texture.png");
  
  private static LindwormModel<LindwormAnimatable> model;
  
  public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float ticks) {
    if (!(entity instanceof EntityDungeonPunchingBall) || entity.field_70128_L)
      return; 
    EntityDungeonPunchingBall punchingBall = (EntityDungeonPunchingBall)entity;
    if (punchingBall.getHealth() <= 0)
      return; 
    GL11.glPushMatrix();
    GL11.glPushAttrib(1048575);
    GL11.glAlphaFunc(516, 0.0F);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(3042);
    GL11.glEnable(2884);
    GL11.glTranslated(x, y, z);
    GL11.glRotated((180.0F - entity.field_70177_z), 0.0D, 1.0D, 0.0D);
    LindwormRenderer.renderModel(entity, getModel());
    GL11.glPopAttrib();
    GL11.glPopMatrix();
  }
  
  public ResourceLocation func_110775_a(Entity entity) {
    return null;
  }
  
  @NonNull
  public static LindwormAnimatable getAnimatable(@NonNull EntityDungeonPunchingBall entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    return (LindwormAnimatable)getModel().getOrCreateAnimatable((Entity)entity);
  }
  
  @NonNull
  private static LindwormModel<LindwormAnimatable> getModel() {
    if (model == null)
      model = new LindwormModel(LindwormLoader.loadModel(MODEL_LOCATION), LindwormLoader.loadAnimation(ANIMATION_LOCATION), Resource.of(TEXTURE_LOCATION).nearest(), LindwormAnimatable::new); 
    return model;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\client\render\entity\room\boost\RenderEntityDungeonPunchingBall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */