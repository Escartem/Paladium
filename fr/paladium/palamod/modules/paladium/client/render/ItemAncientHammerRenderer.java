package fr.paladium.palamod.modules.paladium.client.render;

import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.ItemAncientHammer;
import fr.paladium.zephyrui.lib.opengl.GLHelper;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.function.BiFunction;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.impl.loader.LindwormLoader;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;

public class ItemAncientHammerRenderer implements IItemRenderer {
  private final GeoModel normalGeoModel = LindwormLoader.loadModel(new ResourceLocation("palamod", "textures/items/ancient_hammer/normal/model.json"));
  
  private final GeoModel evolveGeoModel = LindwormLoader.loadModel(new ResourceLocation("palamod", "textures/items/ancient_hammer/evolve/model.json"));
  
  private final LindwormModel<?> normalModel = new LindwormModel(this.normalGeoModel, software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable::new);
  
  private final LindwormModel<?> evolveModel = new LindwormModel(this.evolveGeoModel, software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable::new);
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    LegendaryStone.Effect effect = ItemAncientHammer.getEffect(item);
    Resource texture = (effect != null) ? Resource.of(new ResourceLocation("palamod", "textures/items/ancient_hammer/evolve/" + effect.getDisplayName() + ".png")).nearest() : Resource.of(new ResourceLocation("palamod", "textures/items/ancient_hammer/normal/texture.png")).nearest();
    LindwormModel<?> model = (effect != null) ? this.evolveModel : this.normalModel;
    GLHelper.push();
    texture.bindTextureOnly();
    switch (type) {
      case ENTITY:
        GLHelper.translate(0.0D, 0.0D, -0.5D);
        GLHelper.rotate(90.0D, 1.0D, 0.0D, 0.0D);
        GLHelper.rotate(90.0D, 0.0D, 1.0D, 0.0D);
        LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, model);
        break;
      case EQUIPPED:
        GLHelper.rotate(45.0D, 0.0D, 1.0D, 0.0D);
        GLHelper.rotate(-60.0D, 1.0D, 0.0D, 0.0D);
        GLHelper.translate(0.0D, -1.0D, 1.0D);
        GLHelper.scale(2.0D, 2.0D, 2.0D);
        if (data[1] instanceof EntityPlayer) {
          EntityPlayer player = (EntityPlayer)data[1];
          if (player.func_71052_bv() > 0 && item.func_77975_n() == EnumAction.block) {
            GLHelper.rotate(70.0D, 0.0D, 0.0D, 1.0D);
            GLHelper.rotate(75.0D, 0.0D, 1.0D, 0.0D);
            GLHelper.rotate(10.0D, 1.0D, 0.0D, 0.0D);
          } 
        } 
        LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, model);
        break;
      case EQUIPPED_FIRST_PERSON:
        GLHelper.rotate(-45.0D, 0.0D, 1.0D, 0.0D);
        GLHelper.translate(0.75D, 0.25D, 0.0D);
        GLHelper.scale(1.5D, 1.5D, 1.5D);
        LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, model);
        break;
      case INVENTORY:
        GLHelper.translate(-0.4D, -0.5D, 0.0D);
        GLHelper.rotate(45.0D, 0.0D, 1.0D, 0.0D);
        GLHelper.rotate(-30.0D, 1.0D, 0.0D, 0.0D);
        GLHelper.rotate(-90.0D, 0.0D, 1.0D, 0.0D);
        GLHelper.rotate(-45.0D, 1.0D, 0.0D, 0.0D);
        GLHelper.scale(0.6D, 0.6D, 0.6D);
        LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, model);
        break;
      default:
        LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, model);
        break;
    } 
    GLHelper.pop();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\ItemAncientHammerRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */