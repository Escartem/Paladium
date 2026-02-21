package fr.paladium.palawither.client.render.item;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palawither.api.ItemsRegister;
import fr.paladium.palawither.common.item.target.ItemWitherTargetLaserSystem;
import fr.paladium.palawither.common.network.item.CSPacketWitherTargetLaserSystem;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.utils.align.Align;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.impl.dto.LindwormTransformProperty;
import software.bernie.geckolib3.model.impl.loader.LindwormLoader;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType;
import software.bernie.geckolib3.model.impl.model.animation.impl.IdleLindwormAnimationType;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;

public class ItemWitherTargetLaserSystemRenderer implements IItemRenderer {
  private final ResourceLocation texture;
  
  private final LindwormModel<LindwormAnimatable> model;
  
  private static final int MIN_Y = 1;
  
  private static final int MAX_Y = 300;
  
  private static final int MIN_DISTANCE = 5;
  
  private static final int MAX_DISTANCE = 1000;
  
  private int targetX;
  
  private int targetY;
  
  private int targetZ;
  
  private int targetDistance = 10;
  
  public ItemWitherTargetLaserSystemRenderer() {
    this.texture = new ResourceLocation("palawither", "textures/items/target/wither_target_laser_system/item.png");
    this.model = new LindwormModel(LindwormLoader.loadModel(new ResourceLocation("palawither", "models/items/target/wither_target_laser_system/model.json")), LindwormLoader.loadAnimation(new ResourceLocation("palawither", "models/items/target/wither_target_laser_system/animation.json")), (m, e) -> (new LindwormAnimatable(m, e)).addAnimationType(new LindwormAnimationType[] { (LindwormAnimationType)new IdleLindwormAnimationType(), new ActiveLindwormAnimationType() }));
    MinecraftForge.EVENT_BUS.register(this);
    FMLCommonHandler.instance().bus().register(this);
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    if (isHoldingTargetItem())
      calculateTargetBlock(); 
    GL11.glPushMatrix();
    if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
      GL11.glScaled(1.8D, 1.8D, 1.8D);
      GL11.glTranslated(-0.3D, 0.3D, 0.3D);
      GL11.glRotated(-50.0D, 0.0D, 1.0D, 0.0D);
    } else if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
      GL11.glScaled(2.5D, 2.5D, 2.5D);
      GL11.glTranslated(0.6D, 0.0D, 0.6D);
      GL11.glRotated(45.0D, 0.0D, 1.0D, 0.0D);
      GL11.glRotated(-65.0D, 1.0D, 0.0D, 0.0D);
    } else if (type == IItemRenderer.ItemRenderType.INVENTORY) {
      GL11.glEnable(3008);
      GL11.glScaled(1.8D, 1.8D, 1.8D);
      GL11.glTranslated(0.0D, -0.5D, 0.0D);
      GL11.glRotated(180.0D, 0.0D, 1.0D, 0.0D);
    } else if (type == IItemRenderer.ItemRenderType.ENTITY) {
      GL11.glScaled(1.2D, 1.2D, 1.2D);
    } 
    Minecraft.func_71410_x().func_110434_K().func_110577_a(this.texture);
    LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, this.model, new LindwormTransformProperty(), bone -> "coordinates".equalsIgnoreCase(bone.getName()) ? (
        
        (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON && isHoldingTargetItem())) : true);
    if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON && isHoldingTargetItem()) {
      int fontSize = 50;
      GL11.glTranslated(-0.1D, 0.595D, 0.2D);
      GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D);
      GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
      GL11.glScaled(0.02D, 0.02D, 0.02D);
      GL11.glScaled(0.05D, 0.05D, 0.05D);
      GL11.glDisable(2884);
      GL11.glDepthMask(false);
      DrawUtils.TEXT.drawText(0.0D, -50.0D, Text.create(Integer.valueOf(this.targetX), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 50.0F, Color.WHITE).shadow(Color.WHITE.darker(0.8F))).horizontalAlign(Align.END));
      DrawUtils.TEXT.drawText(0.0D, 0.0D, Text.create(Integer.valueOf(this.targetY), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 50.0F, Color.WHITE).shadow(Color.WHITE.darker(0.8F))).horizontalAlign(Align.END));
      DrawUtils.TEXT.drawText(0.0D, 50.0D, Text.create(Integer.valueOf(this.targetZ), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 50.0F, Color.WHITE).shadow(Color.WHITE.darker(0.8F))).horizontalAlign(Align.END));
      GL11.glDepthMask(true);
      GL11.glEnable(2884);
    } 
    if (type == IItemRenderer.ItemRenderType.INVENTORY)
      GL11.glDisable(3008); 
    GL11.glPopMatrix();
  }
  
  @SubscribeEvent
  public void onRenderWorldLast(RenderWorldLastEvent event) {
    if (!isHoldingTargetItem())
      return; 
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    if (entityClientPlayerMP == null)
      return; 
    double playerX = ((EntityPlayer)entityClientPlayerMP).field_70142_S + (((EntityPlayer)entityClientPlayerMP).field_70165_t - ((EntityPlayer)entityClientPlayerMP).field_70142_S) * event.partialTicks;
    double playerY = ((EntityPlayer)entityClientPlayerMP).field_70137_T + (((EntityPlayer)entityClientPlayerMP).field_70163_u - ((EntityPlayer)entityClientPlayerMP).field_70137_T) * event.partialTicks;
    double playerZ = ((EntityPlayer)entityClientPlayerMP).field_70136_U + (((EntityPlayer)entityClientPlayerMP).field_70161_v - ((EntityPlayer)entityClientPlayerMP).field_70136_U) * event.partialTicks;
    GL11.glPushMatrix();
    GL11.glPushAttrib(1048575);
    GL11.glTranslated(-playerX, -playerY, -playerZ);
    GL11.glDisable(3553);
    GL11.glDisable(2896);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glLineWidth(3.0F);
    GL11.glDisable(2929);
    renderTargetCrosshair();
    GL11.glEnable(2929);
    GL11.glEnable(2896);
    GL11.glEnable(3553);
    GL11.glDisable(3042);
    GL11.glPopAttrib();
    GL11.glPopMatrix();
  }
  
  private void renderTargetCrosshair() {
    Tessellator tessellator = Tessellator.field_78398_a;
    double x = this.targetX;
    double y = this.targetY;
    double z = this.targetZ;
    GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.4F);
    tessellator.func_78371_b(7);
    tessellator.func_78377_a(x, y, z);
    tessellator.func_78377_a(x, y + 1.0D, z);
    tessellator.func_78377_a(x + 1.0D, y + 1.0D, z);
    tessellator.func_78377_a(x + 1.0D, y, z);
    tessellator.func_78377_a(x + 1.0D, y, z);
    tessellator.func_78377_a(x + 1.0D, y + 1.0D, z);
    tessellator.func_78377_a(x + 1.0D, y + 1.0D, z + 1.0D);
    tessellator.func_78377_a(x + 1.0D, y, z + 1.0D);
    tessellator.func_78377_a(x + 1.0D, y, z + 1.0D);
    tessellator.func_78377_a(x + 1.0D, y + 1.0D, z + 1.0D);
    tessellator.func_78377_a(x, y + 1.0D, z + 1.0D);
    tessellator.func_78377_a(x, y, z + 1.0D);
    tessellator.func_78377_a(x, y, z + 1.0D);
    tessellator.func_78377_a(x, y + 1.0D, z + 1.0D);
    tessellator.func_78377_a(x, y + 1.0D, z);
    tessellator.func_78377_a(x, y, z);
    tessellator.func_78377_a(x, y + 1.0D, z);
    tessellator.func_78377_a(x, y + 1.0D, z + 1.0D);
    tessellator.func_78377_a(x + 1.0D, y + 1.0D, z + 1.0D);
    tessellator.func_78377_a(x + 1.0D, y + 1.0D, z);
    tessellator.func_78377_a(x, y, z + 1.0D);
    tessellator.func_78377_a(x, y, z);
    tessellator.func_78377_a(x + 1.0D, y, z);
    tessellator.func_78377_a(x + 1.0D, y, z + 1.0D);
    tessellator.func_78381_a();
    GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.8F);
    GL11.glLineWidth(2.0F);
    tessellator.func_78371_b(1);
    tessellator.func_78377_a(x, y, z);
    tessellator.func_78377_a(x + 1.0D, y, z);
    tessellator.func_78377_a(x + 1.0D, y, z);
    tessellator.func_78377_a(x + 1.0D, y + 1.0D, z);
    tessellator.func_78377_a(x + 1.0D, y + 1.0D, z);
    tessellator.func_78377_a(x, y + 1.0D, z);
    tessellator.func_78377_a(x, y + 1.0D, z);
    tessellator.func_78377_a(x, y, z);
    tessellator.func_78377_a(x, y, z + 1.0D);
    tessellator.func_78377_a(x + 1.0D, y, z + 1.0D);
    tessellator.func_78377_a(x + 1.0D, y, z + 1.0D);
    tessellator.func_78377_a(x + 1.0D, y + 1.0D, z + 1.0D);
    tessellator.func_78377_a(x + 1.0D, y + 1.0D, z + 1.0D);
    tessellator.func_78377_a(x, y + 1.0D, z + 1.0D);
    tessellator.func_78377_a(x, y + 1.0D, z + 1.0D);
    tessellator.func_78377_a(x, y, z + 1.0D);
    tessellator.func_78377_a(x, y, z);
    tessellator.func_78377_a(x, y, z + 1.0D);
    tessellator.func_78377_a(x + 1.0D, y, z);
    tessellator.func_78377_a(x + 1.0D, y, z + 1.0D);
    tessellator.func_78377_a(x + 1.0D, y + 1.0D, z);
    tessellator.func_78377_a(x + 1.0D, y + 1.0D, z + 1.0D);
    tessellator.func_78377_a(x, y + 1.0D, z);
    tessellator.func_78377_a(x, y + 1.0D, z + 1.0D);
    tessellator.func_78381_a();
  }
  
  private void calculateTargetBlock() {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    if (entityClientPlayerMP == null)
      return; 
    Vec3 lookVector = entityClientPlayerMP.func_70040_Z();
    double startX = ((EntityPlayer)entityClientPlayerMP).field_70165_t;
    double startY = ((EntityPlayer)entityClientPlayerMP).field_70163_u + entityClientPlayerMP.func_70047_e();
    double startZ = ((EntityPlayer)entityClientPlayerMP).field_70161_v;
    double endX = startX + lookVector.field_72450_a * this.targetDistance;
    double endY = startY + lookVector.field_72448_b * this.targetDistance;
    double endZ = startZ + lookVector.field_72449_c * this.targetDistance;
    if (endY < 1.0D) {
      double adjustedDistance = (1.0D - startY) / lookVector.field_72448_b;
      if (adjustedDistance > 0.0D && adjustedDistance < this.targetDistance) {
        endX = startX + lookVector.field_72450_a * adjustedDistance;
        endY = 1.0D;
        endZ = startZ + lookVector.field_72449_c * adjustedDistance;
        this.targetDistance = (int)adjustedDistance;
      } 
    } else if (endY > 300.0D) {
      double adjustedDistance = (300.0D - startY) / lookVector.field_72448_b;
      if (adjustedDistance > 0.0D && adjustedDistance < this.targetDistance) {
        endX = startX + lookVector.field_72450_a * adjustedDistance;
        endY = 300.0D;
        endZ = startZ + lookVector.field_72449_c * adjustedDistance;
        this.targetDistance = (int)adjustedDistance;
      } 
    } 
    this.targetX = MathHelper.func_76128_c(endX);
    this.targetY = Math.min(256, MathHelper.func_76128_c(endY));
    this.targetZ = MathHelper.func_76128_c(endZ);
  }
  
  @SubscribeEvent
  public void onKeyInput(InputEvent.KeyInputEvent event) {
    if (!isHoldingTargetItem())
      return; 
    if (Keyboard.isKeyDown(19)) {
      this.targetDistance = 10;
      this.targetX = 0;
      this.targetY = 0;
      this.targetZ = 0;
      Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("random.click"), 1.0F));
      return;
    } 
    if (Keyboard.isKeyDown(200)) {
      increaseDistance();
    } else if (Keyboard.isKeyDown(208)) {
      decreaseDistance();
    } 
  }
  
  @SubscribeEvent
  public void onMouseInput(MouseEvent event) {
    if (!isHoldingTargetItem())
      return; 
    if (Mouse.getEventButton() == 0 && Mouse.getEventButtonState()) {
      (new CSPacketWitherTargetLaserSystem(this.targetX, this.targetY, this.targetZ)).send();
      Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("random.pop"), 1.0F));
      event.setCanceled(true);
    } 
    if (event.dwheel > 0) {
      increaseDistance();
      event.setCanceled(true);
    } else if (event.dwheel < 0) {
      decreaseDistance();
      event.setCanceled(true);
    } 
  }
  
  @SubscribeEvent
  public void onRenderPlayer(RenderPlayerEvent.Pre event) {
    EntityPlayer player = event.entityPlayer;
    if (player == null || !isHoldingTargetItem(player))
      return; 
    event.renderer.field_77109_a.field_78118_o = true;
  }
  
  private boolean isHoldingTargetItem() {
    return isHoldingTargetItem((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
  }
  
  private boolean isHoldingTargetItem(EntityPlayer player) {
    if (player == null)
      return false; 
    ItemStack heldItem = player.func_70694_bm();
    if (heldItem == null || heldItem.func_77973_b() != ItemsRegister.WITHER_TARGET_LASER_SYSTEM)
      return false; 
    return ItemWitherTargetLaserSystem.isActive(heldItem);
  }
  
  private void increaseDistance() {
    if (this.targetDistance < 1000 && this.targetY < 300 && this.targetY > 1) {
      this.targetDistance = Math.min(this.targetDistance + 5, 1000);
      Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 5.0F));
    } else {
      Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("random.break"), 5.0F));
    } 
  }
  
  private void decreaseDistance() {
    if (this.targetDistance > 5) {
      this.targetDistance = Math.max(this.targetDistance - 5, 5);
      Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 5.0F));
    } else {
      Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("random.break"), 5.0F));
    } 
  }
  
  private class ActiveLindwormAnimationType extends LindwormAnimationType {
    public ActiveLindwormAnimationType() {
      super("active", 1, true, entity -> {
            if (!(entity instanceof EntityPlayer))
              return false; 
            EntityPlayer player = (EntityPlayer)entity;
            ItemStack heldItem = player.func_70694_bm();
            return (heldItem == null || heldItem.func_77973_b() != ItemsRegister.WITHER_TARGET_LASER_SYSTEM) ? false : ItemWitherTargetLaserSystem.isActive(heldItem);
          });
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\client\render\item\ItemWitherTargetLaserSystemRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */