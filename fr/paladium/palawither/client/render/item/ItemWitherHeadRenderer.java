package fr.paladium.palawither.client.render.item;

import java.util.function.BiFunction;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.model.impl.loader.LindwormLoader;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;

public class ItemWitherHeadRenderer implements IItemRenderer {
  private final ResourceLocation model;
  
  private final ResourceLocation texture;
  
  private final LindwormModel<LindwormAnimatable> headModel;
  
  public ItemWitherHeadRenderer(@NonNull ResourceLocation model, @NonNull ResourceLocation texture) {
    if (model == null)
      throw new NullPointerException("model is marked non-null but is null"); 
    if (texture == null)
      throw new NullPointerException("texture is marked non-null but is null"); 
    this.model = model;
    this.texture = texture;
    this.headModel = new LindwormModel(LindwormLoader.loadModel(this.model), LindwormAnimatable::new);
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    GL11.glPushMatrix();
    if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
      GL11.glScaled(0.5D, 0.5D, 0.5D);
      GL11.glTranslated(1.3D, 1.5D, 1.3D);
      GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D);
    } else if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
      GL11.glScaled(0.7D, 0.7D, 0.7D);
      GL11.glTranslated(0.7D, 0.0D, 0.7D);
      GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D);
    } else if (type == IItemRenderer.ItemRenderType.INVENTORY) {
      GL11.glEnable(3008);
      GL11.glScaled(0.7D, 0.7D, 0.7D);
      GL11.glTranslated(0.0D, -0.5D, 0.0D);
      GL11.glRotated(180.0D, 0.0D, 1.0D, 0.0D);
    } else if (type == IItemRenderer.ItemRenderType.ENTITY) {
      GL11.glScaled(0.6D, 0.6D, 0.6D);
    } 
    Minecraft.func_71410_x().func_110434_K().func_110577_a(this.texture);
    LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, this.headModel);
    if (type == IItemRenderer.ItemRenderType.INVENTORY)
      GL11.glDisable(3008); 
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\client\render\item\ItemWitherHeadRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */