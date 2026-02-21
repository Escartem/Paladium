package fr.paladium.palawither.client.render.block;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.location.BlockLocation;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.model.impl.loader.LindwormLoader;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;

public class BlockWitherReceptacleRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
  public static final int RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
  
  private static final ResourceLocation PARTICLE_TEXTURES = new ResourceLocation("textures/particle/particles.png");
  
  private static final Set<BlockLocation> HIGHLIGHT_BLOCK_SET = new HashSet<>();
  
  private final double offset = 0.001D;
  
  private final Color color = new Color(255, 60, 60);
  
  private final Color overlayColor = this.color.copyAlpha(0.2F);
  
  private final Resource onResource = Resource.of(new ResourceLocation("palawither", "textures/blocks/wither_receptacle/on.png")).nearest();
  
  private final Resource offResource = Resource.of(new ResourceLocation("palawither", "textures/blocks/wither_receptacle/off.png")).nearest();
  
  private final LindwormModel<LindwormAnimatable> model = new LindwormModel(LindwormLoader.loadModel(new ResourceLocation("palawither", "models/blocks/wither_receptacle.json")), LindwormAnimatable::new);
  
  @SubscribeEvent
  public void onRender(RenderWorldLastEvent event) {
    HIGHLIGHT_BLOCK_SET.clear();
  }
  
  public void func_147500_a(TileEntity tile, double x, double y, double z, float ticks) {
    if (!(tile instanceof TileEntityWitherReceptacle))
      return; 
    EntityLivingBase entityLivingBase1 = (Minecraft.func_71410_x()).field_71451_h;
    TileEntityWitherReceptacle receptacle = (TileEntityWitherReceptacle)tile;
    EntityLivingBase wither = (receptacle.isActive() && receptacle.getWither() != null) ? receptacle.getWither().getEntity() : null;
    if (receptacle.isActive())
      renderInvocationParticles(receptacle, x, y, z, ticks, wither); 
    double health = receptacle.getHealth();
    double percent = health / 15000.0D;
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(3042);
    GL11.glDisable(2884);
    if (receptacle.isActive()) {
      GL11.glPushMatrix();
      GL11.glLineWidth(2.0F);
      GL11.glTranslated(x, y, z);
      World world = tile.func_145831_w();
      for (int i = -6; i <= 6; i++) {
        for (int j = -6; j <= 6; j++) {
          if (i * i + j * j <= 36) {
            int blockX = tile.field_145851_c + i;
            int blockZ = tile.field_145849_e + j;
            for (int k = -6; k < 6; k++) {
              int blockY = tile.field_145848_d + k;
              BlockLocation blockLocation = new BlockLocation(world, blockX, blockY, blockZ);
              if (HIGHLIGHT_BLOCK_SET.add(blockLocation)) {
                Block block = world.func_147439_a(blockX, blockY, blockZ);
                block.func_149719_a((IBlockAccess)world, blockX, blockY, blockZ);
                if (block.func_149688_o().func_76220_a() && block.func_149688_o().func_76218_k() && block.func_149645_b() == 0) {
                  GL11.glDisable(3553);
                  GL11.glDisable(2896);
                  GL11.glPushMatrix();
                  getClass();
                  GL11.glTranslated((blockX - tile.field_145851_c), (blockY - tile.field_145848_d + 1) + 0.001D, (blockZ - tile.field_145849_e));
                  GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                  DrawUtils.SHAPE.drawRect(0.0D, 0.0D, 1.0D, 1.0D, this.overlayColor);
                  GL11.glPopMatrix();
                  GL11.glPushMatrix();
                  getClass();
                  GL11.glTranslated((blockX - tile.field_145851_c), (blockY - tile.field_145848_d) - 0.001D, (blockZ - tile.field_145849_e));
                  GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                  DrawUtils.SHAPE.drawRect(0.0D, 0.0D, 1.0D, 1.0D, this.overlayColor);
                  GL11.glPopMatrix();
                  GL11.glPushMatrix();
                  getClass();
                  GL11.glTranslated((blockX - tile.field_145851_c), (blockY - tile.field_145848_d), (blockZ - tile.field_145849_e + 1) + 0.001D);
                  DrawUtils.SHAPE.drawRect(0.0D, 0.0D, 1.0D, 1.0D, this.overlayColor);
                  GL11.glPopMatrix();
                  GL11.glPushMatrix();
                  getClass();
                  GL11.glTranslated((blockX - tile.field_145851_c), (blockY - tile.field_145848_d), (blockZ - tile.field_145849_e) - 0.001D);
                  DrawUtils.SHAPE.drawRect(0.0D, 0.0D, 1.0D, 1.0D, this.overlayColor);
                  GL11.glPopMatrix();
                  GL11.glPushMatrix();
                  getClass();
                  GL11.glTranslated((blockX - tile.field_145851_c + 1) + 0.001D, (blockY - tile.field_145848_d), (blockZ - tile.field_145849_e + 1));
                  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                  DrawUtils.SHAPE.drawRect(0.0D, 0.0D, 1.0D, 1.0D, this.overlayColor);
                  GL11.glPopMatrix();
                  GL11.glPushMatrix();
                  getClass();
                  GL11.glTranslated((blockX - tile.field_145851_c) - 0.001D, (blockY - tile.field_145848_d), (blockZ - tile.field_145849_e + 1));
                  GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                  DrawUtils.SHAPE.drawRect(0.0D, 0.0D, 1.0D, 1.0D, this.overlayColor);
                  GL11.glPopMatrix();
                  GL11.glEnable(2896);
                  GL11.glEnable(3553);
                } 
              } 
            } 
          } 
        } 
      } 
      GL11.glPopMatrix();
    } 
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y, z + 0.5D);
    if (Minecraft.func_71379_u()) {
      GL11.glShadeModel(7425);
    } else {
      GL11.glShadeModel(7424);
    } 
    GL11.glRotated((tile.field_145847_g * -90 + 270), 0.0D, 1.0D, 0.0D);
    ((receptacle.isActive() && receptacle.isDecreasingCooldown()) ? this.onResource : this.offResource).bind(() -> LindwormRenderer.renderModel(viewer, this.model));
    GL11.glRotated(-(tile.field_145847_g * -90 + 270), 0.0D, 1.0D, 0.0D);
    GL11.glTranslated(0.0D, receptacle.isActive() ? (1.4D + (wither.field_70131_O * receptacle.getCooldownPercent())) : 1.4D, 0.0D);
    GL11.glScaled(0.01D, 0.01D, 0.01D);
    GL11.glRotatef(-RenderManager.field_78727_a.field_78735_i, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    if (receptacle.isActive()) {
      GL11.glDepthMask(false);
      OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
      GL11.glDisable(3553);
      OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
      GL11.glDisable(2896);
      DrawUtils.TEXT.drawText(-116.0D, -35.0D, Text.create(receptacle.isActive() ? (TTT.format(I18n.func_135052_a(receptacle.getWither().getDisplayName(), new Object[0]), new Object[0]) + ((receptacle.getCooldown() / 20 * 1000 > 0) ? (" (" + (!receptacle.isDecreasingCooldown() ? "pause" : DurationConverter.fromMillisToString((receptacle.getCooldown() / 20 * 1000))) + ")") : " (apparition)")) : tile.func_145838_q().func_149732_F(), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 15.0F, Color.WHITE).shadow(Color.BLACK).shadow(1.0F, 1.0F)));
      DrawUtils.SHAPE.drawRect(-120.0D, -4.0D, 241.0D, 21.0D, new Color(14, 14, 14));
      DrawUtils.SHAPE.drawRect(-116.0D, 0.0D, 233.0D, 13.0D, new Color(40, 40, 40));
      DrawUtils.SHAPE.drawRect(-116.0D, 0.0D, 233.0D * percent, 13.0D, new Color(255, 60, 60));
      DrawUtils.TEXT.drawText(-111.0D, -1.5D, Text.create(String.format("%.0f/%.0f", new Object[] { Double.valueOf(health), Double.valueOf(15000.0D) }), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 10.0F, Color.WHITE)));
      OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
      GL11.glEnable(3553);
      OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
      GL11.glEnable(2896);
      GL11.glDepthMask(true);
    } 
    GL11.glPopMatrix();
    if (receptacle.isActive()) {
      double size = receptacle.getCooldownPercent();
      GL11.glPushMatrix();
      GL11.glDisable(2896);
      GL11.glTranslated(x + 0.5D, y + 0.8D, z + 0.5D);
      GL11.glRotated((System.currentTimeMillis() / 10L % 360L), 0.0D, 1.0D, 0.0D);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      GL11.glScaled(-size, size, size);
      GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
      GL11.glTranslated(0.0D, wither.field_70129_M, 0.0D);
      RenderManager.field_78727_a.func_147940_a((Entity)wither, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
      GL11.glPopMatrix();
    } 
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    this.offResource.bind(() -> {
          if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glScaled(0.75D, 0.75D, 0.75D);
            GL11.glTranslated(0.5D, -0.2D, 0.5D);
            LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, this.model);
            GL11.glTranslated(-0.5D, 0.2D, -0.5D);
          } else if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON || type == IItemRenderer.ItemRenderType.EQUIPPED) {
            GL11.glScaled(0.75D, 0.75D, 0.75D);
            GL11.glTranslated(0.75D, 0.25D, 0.75D);
            LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, this.model);
            GL11.glTranslated(-0.75D, -0.25D, -0.75D);
          } else {
            LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, this.model);
          } 
        });
  }
  
  private void renderInvocationParticles(TileEntityWitherReceptacle receptacle, double x, double y, double z, float ticks, EntityLivingBase wither) {
    if (wither == null)
      return; 
    Minecraft.func_71410_x().func_110434_K().func_110577_a(PARTICLE_TEXTURES);
    long time = System.currentTimeMillis();
    double witherX = x + 0.5D;
    double witherY = y + 1.4D + (wither.field_70131_O * receptacle.getCooldownPercent());
    double witherZ = z + 0.5D;
    double baseRadius = 1.2D;
    double spiralHeight = 2.5D;
    GL11.glPushMatrix();
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glDepthMask(false);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glAlphaFunc(516, 0.003921569F);
    float f1 = ActiveRenderInfo.field_74588_d;
    float f2 = ActiveRenderInfo.field_74586_f;
    float f3 = ActiveRenderInfo.field_74587_g;
    float f4 = ActiveRenderInfo.field_74596_h;
    float f5 = ActiveRenderInfo.field_74589_e;
    float particleScale = 5.0F;
    for (int i = 0; i < 50.0F * receptacle.getCooldownPercent(); i++) {
      double particleTime = (time / 50.0D + (i * 20)) % 360.0D;
      double spiralAngle = Math.toRadians(particleTime * 3.0D);
      double heightProgress = particleTime / 360.0D % 1.0D;
      double radius = 1.2D * (1.0D - heightProgress * 0.7D);
      double particleX = witherX + Math.cos(spiralAngle) * radius;
      double particleY = y + 0.2D + heightProgress * 2.5D;
      double particleZ = witherZ + Math.sin(spiralAngle) * radius;
      float alpha = (float)(0.3D + heightProgress * 2.0D);
      GL11.glPushMatrix();
      GL11.glTranslated(particleX, particleY, particleZ);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
      double size = 0.1D + heightProgress * 0.05D;
      GL11.glScaled(size, size, size);
      int k = 4 + (int)(heightProgress * 3.0D);
      int m = k % 16;
      int n = k / 16;
      float f6 = m / 16.0F;
      float f7 = f6 + 0.0624375F;
      float f8 = n / 16.0F;
      float f9 = f8 + 0.0624375F;
      float f10 = 0.1F * particleScale;
      float f11 = -0.5F;
      float f12 = 0.0F;
      float f13 = -0.5F;
      Tessellator tessellator = Tessellator.field_78398_a;
      tessellator.func_78382_b();
      tessellator.func_78369_a(0.0F, 0.0F, 0.0F, alpha);
      tessellator.func_78374_a((-0.5F - f1 * f10 - f3 * f10), (0.0F - f5 * f10), (-0.5F - f2 * f10 - f4 * f10), f7, f9);
      tessellator.func_78374_a((-0.5F - f1 * f10 + f3 * f10), (0.0F + f5 * f10), (-0.5F - f2 * f10 + f4 * f10), f7, f8);
      tessellator.func_78374_a((-0.5F + f1 * f10 + f3 * f10), (0.0F + f5 * f10), (-0.5F + f2 * f10 + f4 * f10), f6, f8);
      tessellator.func_78374_a((-0.5F + f1 * f10 - f3 * f10), (0.0F - f5 * f10), (-0.5F + f2 * f10 - f4 * f10), f6, f9);
      tessellator.func_78381_a();
      GL11.glPopMatrix();
    } 
    particleScale = 5.0F;
    int particleTextureIndex = 4;
    int particleTextureIndexX = 4;
    int particleTextureIndexY = 0;
    for (int j = 0; j < 100.0F * receptacle.getCooldownPercent(); j++) {
      double energyTime = (time / 30.0D + (j * 45)) % 100.0D;
      double convergenceProgress = energyTime / 100.0D;
      if (convergenceProgress > 0.2D) {
        double adjustedProgress = (convergenceProgress - 0.2D) / 0.8D;
        double startAngle = Math.toRadians((j * 45) + time * 0.1D);
        double startRadius = 2.0D;
        double startX = witherX + Math.cos(startAngle) * 2.0D;
        double startY = y + 0.5D;
        double startZ = witherZ + Math.sin(startAngle) * 2.0D;
        double curveHeight = 1.0D * Math.sin(adjustedProgress * Math.PI);
        double currentX = startX + (witherX - startX) * adjustedProgress;
        double currentY = startY + (witherY - startY) * adjustedProgress + curveHeight;
        double currentZ = startZ + (witherZ - startZ) * adjustedProgress;
        float red = 0.8F;
        float green = 0.0F;
        float blue = 0.8F;
        float alpha = (float)(0.8D * (1.0D - adjustedProgress));
        GL11.glPushMatrix();
        GL11.glTranslated(currentX, currentY, currentZ);
        GL11.glColor4f(0.8F, 0.0F, 0.8F, alpha);
        double size = 0.08D + adjustedProgress * 0.04D;
        GL11.glScaled(size, size, size);
        float f6 = 0.25F;
        float f7 = 0.3124375F;
        float f8 = 0.0F;
        float f9 = 0.0624375F;
        float f10 = 0.1F * particleScale;
        float f11 = -0.5F;
        float f12 = 0.0F;
        float f13 = -0.5F;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78369_a(0.8F, 0.0F, 0.8F, alpha);
        tessellator.func_78374_a((-0.5F - f1 * f10 - f3 * f10), (0.0F - f5 * f10), (-0.5F - f2 * f10 - f4 * f10), 0.312437504529953D, 0.062437500804662704D);
        tessellator.func_78374_a((-0.5F - f1 * f10 + f3 * f10), (0.0F + f5 * f10), (-0.5F - f2 * f10 + f4 * f10), 0.312437504529953D, 0.0D);
        tessellator.func_78374_a((-0.5F + f1 * f10 + f3 * f10), (0.0F + f5 * f10), (-0.5F + f2 * f10 + f4 * f10), 0.25D, 0.0D);
        tessellator.func_78374_a((-0.5F + f1 * f10 - f3 * f10), (0.0F - f5 * f10), (-0.5F + f2 * f10 - f4 * f10), 0.25D, 0.062437500804662704D);
        tessellator.func_78381_a();
        GL11.glPopMatrix();
      } 
    } 
    GL11.glDisable(3042);
    GL11.glDepthMask(true);
    GL11.glAlphaFunc(516, 0.1F);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glPopMatrix();
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\client\render\block\BlockWitherReceptacleRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */