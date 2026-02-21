package fr.paladium.palarpg.module.equipment.client.renderer;

import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGBasicItemData;
import fr.paladium.palarpg.module.equipment.common.loader.util.DisplayTransformProperty;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.file.AnimationFile;
import software.bernie.geckolib3.model.impl.loader.LindwormLoader;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType;
import software.bernie.geckolib3.model.impl.model.animation.impl.IdleLindwormAnimationType;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;

public class RPGItemRenderer implements IItemRenderer {
  private static final Map<IItemRenderer.ItemRenderType, String> TYPE_MAPPER = new HashMap<IItemRenderer.ItemRenderType, String>() {
    
    };
  
  private final LindwormModel<LindwormAnimatable> model;
  
  private final RPGBasicItemData data;
  
  public RPGItemRenderer(RPGBasicItemData data) {
    this.data = data;
    this.model = new LindwormModel(LindwormLoader.loadModel(data.getModelFile()), LindwormAnimatable::new);
    if (data.getAnimationFile() != null) {
      AnimationFile animationFile = LindwormLoader.loadAnimation(this.data.getAnimationFile());
      this.model.setAnimationFile(animationFile).setAnimatableBuilder((model, entity) -> new LindwormAnimatable(model, entity, 0.0F, new LindwormAnimationType[] { (LindwormAnimationType)new IdleLindwormAnimationType() }));
    } 
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    GL11.glPushMatrix();
    Minecraft.func_71410_x().func_110434_K().func_110577_a(this.data.getModelTexture());
    if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
      GL11.glRotated(-47.5D, 0.0D, 1.0D, 0.0D);
      GL11.glTranslated(0.0D, 1.25D, 1.0D);
    } else if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
      GL11.glRotated(45.0D, 0.0D, 1.0D, 0.0D);
      GL11.glRotated(290.0D, 1.0D, 0.0D, 0.0D);
      GL11.glTranslated(0.0D, -0.8275D, 0.0D);
      GL11.glTranslated(0.0D, 0.0D, 0.665D);
      GL11.glScaled(1.5D, 1.5D, 1.5D);
      GL11.glScaled(1.8181818181818181D, 1.8181818181818181D, 1.8181818181818181D);
    } else if (type == IItemRenderer.ItemRenderType.ENTITY) {
      GL11.glScaled(1.5D, 1.5D, 1.5D);
    } else if (type == IItemRenderer.ItemRenderType.INVENTORY) {
      GL11.glRotated(45.0D, 0.0D, 1.0D, 0.0D);
      GL11.glRotated(-30.0D, 1.0D, 0.0D, 0.0D);
      GL11.glScaled(1.5D, 1.5D, 1.5D);
    } 
    if (this.data.getDisplayProperties() != null) {
      DisplayTransformProperty.TransformProperty transform = (DisplayTransformProperty.TransformProperty)this.data.getDisplayProperties().getDisplay().get(TYPE_MAPPER.get(type));
      if (transform != null) {
        if (transform.getTranslation() != null)
          GL11.glTranslated(((Double)transform.getTranslation().get(0)).doubleValue() / 16.0D, ((Double)transform.getTranslation().get(1)).doubleValue() / 16.0D, ((Double)transform.getTranslation().get(2)).doubleValue() / 16.0D); 
        if (transform.getRotation() != null) {
          if (((Double)transform.getRotation().get(2)).doubleValue() != 0.0D)
            GL11.glRotated(((Double)transform.getRotation().get(2)).doubleValue(), 0.0D, 0.0D, 1.0D); 
          if (((Double)transform.getRotation().get(1)).doubleValue() != 0.0D)
            GL11.glRotated(((Double)transform.getRotation().get(1)).doubleValue(), 0.0D, 1.0D, 0.0D); 
          if (((Double)transform.getRotation().get(0)).doubleValue() != 0.0D)
            GL11.glRotated(((Double)transform.getRotation().get(0)).doubleValue(), 1.0D, 0.0D, 0.0D); 
        } 
        if (transform.getScale() != null)
          GL11.glScaled(((Double)transform.getScale().get(0)).doubleValue(), ((Double)transform.getScale().get(1)).doubleValue(), ((Double)transform.getScale().get(2)).doubleValue()); 
      } 
    } 
    LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, this.model);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\client\renderer\RPGItemRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */