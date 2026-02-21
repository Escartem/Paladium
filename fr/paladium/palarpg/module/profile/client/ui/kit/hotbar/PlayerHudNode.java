package fr.paladium.palarpg.module.profile.client.ui.kit.hotbar;

import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import fr.paladium.palarpg.module.profile.client.ui.kit.shader.GrayscaleShader;
import fr.paladium.palarpg.module.profile.client.util.FakeEntityPlayerMP;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.impl.design.entity.EntityNode;
import lombok.NonNull;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public class PlayerHudNode extends EntityNode {
  private EntityLivingBase entity;
  
  public EntityLivingBase getEntity() {
    return this.entity;
  }
  
  private final double cornerSize = 12.0D;
  
  public double getCornerSize() {
    getClass();
    return 12.0D;
  }
  
  private final double shadowSize = 4.0D;
  
  public double getShadowSize() {
    getClass();
    return 4.0D;
  }
  
  private final Color backgroundColor = Color.decode("#282828");
  
  public Color getBackgroundColor() {
    return this.backgroundColor;
  }
  
  private final Color shadowColor = Color.decode("#0E0E0E");
  
  public Color getShadowColor() {
    return this.shadowColor;
  }
  
  private final TweenAnimator deathAnimator = TweenAnimator.create(0.0F);
  
  private DungeonPlayer dungeonPlayer;
  
  public TweenAnimator getDeathAnimator() {
    return this.deathAnimator;
  }
  
  public DungeonPlayer getDungeonPlayer() {
    return this.dungeonPlayer;
  }
  
  private boolean wasDead = false;
  
  public boolean isWasDead() {
    return this.wasDead;
  }
  
  protected PlayerHudNode(double x, double y, double size) {
    super(x, y, size, size);
  }
  
  public static PlayerHudNode create(double x, double y, double size) {
    return new PlayerHudNode(x, y, size);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    getClass();
    if (4.0D > 0.0D) {
      getClass();
      getClass();
      getClass();
      getClass();
      getClass();
      getClass();
      DrawUtils.SHAPE.drawRect(12.0D - 4.0D, -4.0D, w() - 12.0D * 2.0D + 4.0D * 2.0D, 12.0D, this.shadowColor);
      getClass();
      getClass();
      getClass();
      getClass();
      getClass();
      getClass();
      DrawUtils.SHAPE.drawRect(-4.0D, 12.0D - 4.0D, w() + 4.0D * 2.0D, h() - 12.0D * 2.0D + 4.0D * 2.0D, this.shadowColor);
      getClass();
      getClass();
      getClass();
      getClass();
      getClass();
      getClass();
      getClass();
      DrawUtils.SHAPE.drawRect(12.0D - 4.0D, h() - 12.0D + 4.0D, w() - 12.0D * 2.0D + 4.0D * 2.0D, 12.0D, this.shadowColor);
    } 
    this.deathAnimator.update();
    GL11.glPushAttrib(1048575);
    GL11.glClear(1024);
    GL11.glEnable(2960);
    GL11.glColorMask(true, true, true, true);
    GL11.glDepthMask(false);
    GL11.glStencilMask(255);
    GL11.glStencilFunc(519, 1, 255);
    GL11.glStencilOp(7680, 7680, 7681);
    getClass();
    getClass();
    getClass();
    DrawUtils.SHAPE.drawRect(12.0D, 0.0D, w() - 12.0D * 2.0D, 12.0D, this.backgroundColor);
    getClass();
    getClass();
    DrawUtils.SHAPE.drawRect(0.0D, 12.0D, w(), h() - 12.0D * 2.0D, this.backgroundColor);
    getClass();
    getClass();
    getClass();
    getClass();
    DrawUtils.SHAPE.drawRect(12.0D, h() - 12.0D, w() - 12.0D * 2.0D, 12.0D, this.backgroundColor);
    GL11.glColorMask(true, true, true, true);
    GL11.glDepthMask(true);
    GL11.glStencilMask(0);
    GL11.glStencilFunc(514, 1, 255);
    GL11.glStencilOp(7680, 7680, 7680);
    if (super.getEntity() == null || getSize() == 0.0D)
      return; 
    AxisAlignedBB boundingBox = (super.getEntity()).field_70121_D;
    double entityWidth = boundingBox.field_72336_d - boundingBox.field_72340_a;
    double entityHeight = boundingBox.field_72337_e - boundingBox.field_72338_b;
    double entityDepth = boundingBox.field_72334_f - boundingBox.field_72339_c;
    double biggestSize = Math.max(entityWidth, Math.max(entityHeight, entityDepth));
    double size = dw(entityWidth);
    if (entityHeight * size > getHeight())
      size = dh(entityHeight); 
    size *= getSize();
    float newYaw = getYaw();
    float newPitch = getPitch();
    if (isFollowMouse() && getUi().isOnTop()) {
      double centerX = getAbsoluteX() + getWidth() / 2.0D;
      double centerY = getAbsoluteY() + getHeight() / 2.0D;
      double deltaX = mouseX - centerX;
      double deltaY = mouseY - centerY;
      newYaw = Math.min(Math.max((float)(deltaX / getWidth() * -45.0D), -45.0F), 45.0F);
      newPitch = Math.min(Math.max((float)(deltaY / getHeight() * -45.0D), -45.0F), 45.0F);
    } 
    double drawX = getX() + getWidth() / 2.0D + getXOffset();
    double drawY = getY() + getHeight() / 2.0D + getYOffset() + entityHeight / 2.0D * size + dh(2.0D);
    double drawZ = biggestSize * size;
    GL11.glPushMatrix();
    GL11.glTranslated(drawX, getY() + getHeight() / 2.0D + getYOffset(), drawZ);
    GL11.glRotated(getRotationYaw(), 0.0D, 1.0D, 0.0D);
    GL11.glRotated(getRotationPitch(), 1.0D, 0.0D, 0.0D);
    GL11.glTranslated(-drawX, -(getY() + getHeight() / 2.0D + getYOffset()), -drawZ);
    GL11.glTranslated(0.0D, 0.0D, drawZ);
    GL11.glPushMatrix();
    GL11.glTranslated(drawX, drawY, 0.0D);
    GL11.glScaled(-size, size, size);
    GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
    GL11.glRotated(-Math.atan(newPitch / 40.0D) * 20.0D, 1.0D, 0.0D, 0.0D);
    GL11.glTranslated(0.0D, (super.getEntity()).field_70129_M, 0.0D);
    (super.getEntity()).field_70761_aq = (float)Math.atan(newYaw / 40.0D) * 20.0F;
    (super.getEntity()).field_70177_z = (float)Math.atan(newYaw / 40.0D) * 40.0F;
    (super.getEntity()).field_70125_A = (float)Math.atan(newPitch / 40.0D) * -20.0F;
    (super.getEntity()).field_70759_as = (super.getEntity()).field_70177_z;
    (super.getEntity()).field_70758_at = (super.getEntity()).field_70177_z;
    RenderManager.field_78727_a.field_78735_i = 180.0F;
    boolean djAlive = this.dungeonPlayer.isAlive();
    if (this.wasDead == djAlive) {
      this.wasDead = !djAlive;
      this.deathAnimator.clear();
      this.deathAnimator.setValue(this.wasDead ? 0.0F : 1.0F);
      this.deathAnimator.sequence(500.0F, this.wasDead ? 1.0F : 0.0F).start();
    } 
    GrayscaleShader.use(this.deathAnimator.getValue(), () -> RenderManager.field_78727_a.func_147940_a((Entity)super.getEntity(), 0.0D, 0.0D, 0.0D, 0.0F, 1.0F));
    GL11.glDisable(32826);
    OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
    GL11.glDisable(3553);
    OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
    GL11.glPopMatrix();
    GL11.glPopMatrix();
    getUi().setRenderPipelineLevel(getUi().getRenderPipelineLevel() + ((getPipeLineLevel() == -1.0D) ? (biggestSize * size * 2.0D) : getPipeLineLevel()));
    GL11.glDisable(2960);
    GL11.glClear(1024);
    GL11.glPopAttrib();
  }
  
  public PlayerHudNode dungeonPlayer(@NonNull DungeonPlayer djPlayer) {
    if (djPlayer == null)
      throw new NullPointerException("djPlayer is marked non-null but is null"); 
    this.dungeonPlayer = djPlayer;
    djPlayer.getOnlinePlayer().ifPresent(player -> entity((EntityLivingBase)new FakeEntityPlayerMP(player)));
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\clien\\ui\kit\hotbar\PlayerHudNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */