package fr.paladium.palamod.modules.paladium.client.gui.palamenu.ban;

import com.google.common.collect.Queues;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.AnimatedResourceLocation;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class UIDinoGame extends UI {
  private static final ResourceLocation STATIC = new ResourceLocation("palamod", "textures/gui/ban/game/static.png");
  
  private static final ResourceLocation ANIMATED = new ResourceLocation("palamod", "textures/gui/ban/game/animated.png");
  
  private static final ResourceLocation GROUND = new ResourceLocation("palamod", "textures/gui/ban/game/ground.png");
  
  private static final ResourceLocation CACTUS = new ResourceLocation("palamod", "textures/gui/ban/game/cactus.png");
  
  private long score;
  
  private long maxScore;
  
  private long start;
  
  private long lastUpdate;
  
  private boolean running;
  
  private boolean failed;
  
  private Deque<Long> obstacles;
  
  private float motionY;
  
  private double posY;
  
  private double canvasX;
  
  private double canvasY;
  
  private double canvasWidth;
  
  private double backgroundOffset;
  
  private double canvasOffset;
  
  private long speed;
  
  public void func_73866_w_() {
    super.func_73866_w_();
    this.score = 0L;
    this.motionY = 0.0F;
    this.running = false;
    this.obstacles = Queues.newArrayDeque();
    this.canvasX = width(30.0F);
    this.canvasY = height(75.0F);
    this.canvasWidth = width(40.0F);
    this.backgroundOffset = 0.0D;
    this.canvasOffset = 0.0D;
    this.speed = 3000L;
  }
  
  protected void func_73869_a(char c, int keyCode) {
    super.func_73869_a(c, keyCode);
    if (keyCode == 57) {
      if (!this.running) {
        if (this.failed)
          func_73866_w_(); 
        this.running = true;
        this.failed = false;
        this.start = System.currentTimeMillis();
        this.lastUpdate = System.currentTimeMillis();
        this.obstacles.clear();
        return;
      } 
      if (this.posY == 0.0D)
        this.motionY = 1.0F; 
    } 
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    GuiUtils.drawRect(0.0D, 0.0D, this.field_146294_l, this.field_146295_m, Color.WHITE);
    if (this.running) {
      long diff = System.currentTimeMillis() - this.lastUpdate;
      this.backgroundOffset += 1.0D / this.speed * diff;
      this.canvasOffset += 1.0D / this.speed * diff;
      this.motionY -= 0.0033333334F * (float)diff;
      this.motionY = Math.min(1.0F, this.motionY);
      this.motionY = Math.max(-1.0F, this.motionY);
      this.posY += this.motionY;
      this.posY = Math.max(0.0D, this.posY);
    } 
    GL11.glPushMatrix();
    GL11.glEnable(3089);
    GuiUtils.scissor(this.field_146297_k, this.canvasX, 0.0D, this.canvasWidth, this.field_146295_m);
    if (this.backgroundOffset >= 1.0D)
      this.backgroundOffset = 0.0D; 
    GuiUtils.drawImageTransparent(this.canvasX - this.canvasWidth * this.backgroundOffset, this.canvasY, GROUND, this.canvasWidth, height(1.0F));
    GuiUtils.drawImageTransparent(this.canvasX + this.canvasWidth - this.canvasWidth * this.backgroundOffset, this.canvasY, GROUND, this.canvasWidth, height(1.0F));
    List<Long> toRemove = new ArrayList<>();
    for (Iterator<Long> iterator = this.obstacles.iterator(); iterator.hasNext(); ) {
      long obstacle = ((Long)iterator.next()).longValue();
      long diff = obstacle - this.start;
      float ratio = (float)diff / 3000.0F;
      double obstacleX = this.canvasX + this.canvasWidth * ratio - this.canvasWidth * this.canvasOffset;
      double obstalceSize = width(1.5F);
      GuiUtils.drawImageTransparent(obstacleX, this.canvasY - obstalceSize * 2.0D + width(0.5F), CACTUS, obstalceSize, obstalceSize * 2.0D, false);
      if (obstacleX + obstalceSize < this.canvasX)
        toRemove.add(Long.valueOf(obstacle)); 
    } 
    this.obstacles.removeAll(toRemove);
    GL11.glDisable(3089);
    GL11.glPopMatrix();
    GuiUtils.drawStringWithCustomFont(this.field_146297_k, String.valueOf(this.score), this.canvasX, height(50.0F), Color.BLACK, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 20);
    checkCollision();
    this.lastUpdate = System.currentTimeMillis();
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    double dinoSize = width(3.0F);
    boolean animated = (this.running && this.posY == 0.0D);
    if (animated) {
      AnimatedResourceLocation.from(ANIMATED).bind((int)this.score % 20);
      GuiUtils.drawRectWithNoBinding(this.canvasX + width(1.0F), this.canvasY - dinoSize + width(0.5F) - this.posY * dinoSize * 0.5D, dinoSize, dinoSize);
    } else {
      GuiUtils.drawImageTransparent(this.canvasX + width(1.0F), this.canvasY - dinoSize + width(0.5F) - this.posY * dinoSize * 0.5D, STATIC, dinoSize, dinoSize, false);
    } 
    if (this.failed) {
      GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, "VOUS AVEZ PERDU", width(50.0F), height(40.4F), Color.decode("#B91C0C"), Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 200);
      GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, "VOUS AVEZ PERDU", width(50.0F), height(40.0F), Color.decode("#EF3926"), Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 200);
    } else {
      GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, "§8appuyez sur §8[§cechap§8] pour quitter", width(50.0F), height(40.4F), Color.BLACK, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 20);
    } 
  }
  
  public void fixedUpdate() {
    if (!this.running)
      return; 
    this.score++;
    if (this.score > this.maxScore)
      this.maxScore = this.score; 
    if (this.obstacles.size() < 10) {
      long lastObstacle = System.currentTimeMillis() + 3000L;
      if (!this.obstacles.isEmpty())
        lastObstacle = ((Long)this.obstacles.getLast()).longValue(); 
      this.obstacles.add(Long.valueOf(lastObstacle + 1000L + (long)(Math.random() * 1500.0D)));
    } 
    if (this.score % 10L == 0L) {
      this.speed -= 30L;
      this.speed = Math.max(1300L, this.speed);
    } 
  }
  
  private void checkCollision() {
    double dinoSize = width(3.0F);
    double dinoX = this.canvasX + width(1.0F);
    if (this.posY >= 1.5D)
      return; 
    for (Iterator<Long> iterator = this.obstacles.iterator(); iterator.hasNext(); ) {
      long obstacle = ((Long)iterator.next()).longValue();
      long diff = obstacle - this.start;
      float ratio = (float)diff / 3000.0F;
      double obstacleX = this.canvasX + this.canvasWidth * ratio - this.canvasWidth * this.canvasOffset;
      double obstalceSize = width(1.5F);
      if ((obstacleX > dinoX && obstacleX < dinoX + dinoSize) || (obstacleX + obstalceSize > dinoX && obstacleX + obstalceSize < dinoX + dinoSize)) {
        this.running = false;
        this.failed = true;
        break;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\ban\UIDinoGame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */