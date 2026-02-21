package fr.paladium.palawarzoneevent.module.warzone.client.renderer;

import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palawarzoneevent.module.warzone.common.block.tileentity.TileEntityWarzoneSign;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.function.BiFunction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.impl.loader.LindwormLoader;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;

public class TileEntityWarzoneSignRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
  public static final int RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
  
  private final GeoModel MODEL = LindwormLoader.loadModel((ResourceLocation)MCResource.of("palawarzoneevent", "geo/warzonesign.geo.json"));
  
  private final LindwormModel<LindwormAnimatable> SIGN_MODEL = new LindwormModel(this.MODEL, Resource.of((ResourceLocation)MCResource.of("palawarzoneevent", "textures/blocks/warzonesign/warzone_sign.png")).nearest(), LindwormAnimatable::new);
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    GL11.glPushMatrix();
    if (type == IItemRenderer.ItemRenderType.INVENTORY) {
      GL11.glScaled(0.45D, 0.45D, 0.45D);
      GL11.glTranslated(0.5D, -0.2D, 0.5D);
      GL11.glRotated(180.0D, 0.0D, 1.0D, 0.0D);
      LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, this.SIGN_MODEL);
    } else if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON || type == IItemRenderer.ItemRenderType.EQUIPPED) {
      GL11.glScaled(0.45D, 0.45D, 0.45D);
      GL11.glTranslated(1.0D, 0.25D, 1.0D);
      LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, this.SIGN_MODEL);
    } else {
      LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, this.SIGN_MODEL);
    } 
    GL11.glPopMatrix();
  }
  
  public void func_147500_a(TileEntity tile, double x, double y, double z, float partial) {
    if (!(tile instanceof TileEntityWarzoneSign))
      return; 
    TileEntityWarzoneSign sign = (TileEntityWarzoneSign)tile;
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
    GL11.glRotated(sign.getRotation(), 0.0D, 1.0D, 0.0D);
    LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, this.SIGN_MODEL);
    GL11.glTranslated(-(x + 0.5D), -(y + 0.5D), -(z + 0.5D));
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\client\renderer\TileEntityWarzoneSignRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */