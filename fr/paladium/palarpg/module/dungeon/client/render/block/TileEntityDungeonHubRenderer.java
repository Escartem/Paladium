package fr.paladium.palarpg.module.dungeon.client.render.block;

import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.impl.dto.LindwormTransformProperty;
import software.bernie.geckolib3.model.impl.loader.LindwormLoader;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType;
import software.bernie.geckolib3.model.impl.model.animation.impl.IdleLindwormAnimationType;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;

public class TileEntityDungeonHubRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
  public static final int RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
  
  private final LindwormModel<LindwormAnimatable> model;
  
  public TileEntityDungeonHubRenderer() {
    this.model = new LindwormModel(LindwormLoader.loadModel(new ResourceLocation("palarpg", "models/blocks/dungeon/hub/model.json")), LindwormLoader.loadAnimation(new ResourceLocation("palarpg", "models/blocks/dungeon/hub/animation.json")), (model, entity) -> (new LindwormAnimatable(model, entity)).addAnimationType(new LindwormAnimationType[] { (LindwormAnimationType)new IdleLindwormAnimationType() }));
  }
  
  public void func_147496_a(World world) {}
  
  public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float ticks) {
    if (!(tileEntity instanceof fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonHub))
      return; 
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y, z + 0.5D);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(3042);
    GL11.glDisable(2884);
    if (Minecraft.func_71379_u()) {
      GL11.glShadeModel(7425);
    } else {
      GL11.glShadeModel(7424);
    } 
    ResourceLocation texture = null;
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.getClient();
    if (optionalDungeonWorld.isPresent()) {
      DungeonWorld dungeonWorld = optionalDungeonWorld.get();
      if (dungeonWorld.getDungeon() != null)
        texture = new ResourceLocation("palarpg", "textures/blocks/dungeon/hub/" + dungeonWorld.getDungeon().getId() + ".png"); 
    } 
    if (texture == null || !MCResource.of(texture).exists())
      texture = new ResourceLocation("palarpg", "textures/blocks/dungeon/hub/default.png"); 
    Resource.of(texture).nearest().bind(() -> {
          EntityLivingBase entityLivingBase = (Minecraft.func_71410_x()).field_71451_h;
          double vx = ((Entity)entityLivingBase).field_70169_q + (((Entity)entityLivingBase).field_70165_t - ((Entity)entityLivingBase).field_70169_q) * ticks;
          double vz = ((Entity)entityLivingBase).field_70166_s + (((Entity)entityLivingBase).field_70161_v - ((Entity)entityLivingBase).field_70166_s) * ticks;
          double ex = tileEntity.field_145851_c + 0.5D;
          double ez = tileEntity.field_145849_e + 0.5D;
          double dx = vx - ex;
          double dz = vz - ez;
          double rotateY = -Math.atan2(dz, dx) * 180.0D / Math.PI - 90.0D;
          LindwormRenderer.renderModel((Entity)entityLivingBase, this.model, new LindwormTransformProperty(), ());
          GL11.glRotated(rotateY, 0.0D, 1.0D, 0.0D);
          LindwormRenderer.renderModel((Entity)entityLivingBase, this.model, new LindwormTransformProperty(), ());
        });
    GL11.glPopMatrix();
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    GL11.glPushMatrix();
    if (type == IItemRenderer.ItemRenderType.INVENTORY) {
      GL11.glScaled(0.75D, 0.75D, 0.75D);
      GL11.glTranslated(0.5D, -0.2D, 0.5D);
      GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D);
      Resource.of(new ResourceLocation("palarpg", "textures/blocks/dungeon/hub/default.png")).nearest().bind(() -> LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, this.model));
      GL11.glTranslated(-0.5D, 0.2D, -0.5D);
    } else if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON || type == IItemRenderer.ItemRenderType.EQUIPPED) {
      GL11.glScaled(0.75D, 0.75D, 0.75D);
      GL11.glTranslated(0.75D, 0.25D, 0.75D);
      GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D);
      Resource.of(new ResourceLocation("palarpg", "textures/blocks/dungeon/hub/default.png")).nearest().bind(() -> LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, this.model));
      GL11.glTranslated(-0.75D, -0.25D, -0.75D);
    } else {
      GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D);
      Resource.of(new ResourceLocation("palarpg", "textures/blocks/dungeon/hub/default.png")).nearest().bind(() -> LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, this.model));
    } 
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\client\render\block\TileEntityDungeonHubRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */