package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.SeptemberCommonModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.GoodPayEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client.CSGoodPayPacket;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import net.minecraft.util.ResourceLocation;

public class GoodPayUI extends UI {
  private static final String TEXTURES_PATH = "palamod:textures/gui/LuckyBlock/september/game/";
  
  private static final String UI_TITLE = "La bonne paye";
  
  private static final long TICK_DELAY = TimeUnit.MILLISECONDS.toMillis(500L);
  
  private static final long CLOSE_UI_DELAY = TimeUnit.SECONDS.toMillis(5L);
  
  private static final int MAX_TICK_AMOUNT = 20;
  
  private static final Color BACKGROUND_COLOR = Color.decode("#0000004D");
  
  private static final ResourceLocation FIRST = new ResourceLocation("palamod:textures/gui/LuckyBlock/september/game/1.png");
  
  private static final ResourceLocation SECOND = new ResourceLocation("palamod:textures/gui/LuckyBlock/september/game/2.png");
  
  private static final ResourceLocation THIRD = new ResourceLocation("palamod:textures/gui/LuckyBlock/september/game/3.png");
  
  private static final ResourceLocation FOURTH = new ResourceLocation("palamod:textures/gui/LuckyBlock/september/game/4.png");
  
  private static final ResourceLocation FIFTH = new ResourceLocation("palamod:textures/gui/LuckyBlock/september/game/5.png");
  
  private static final ResourceLocation SIXTH = new ResourceLocation("palamod:textures/gui/LuckyBlock/september/game/6.png");
  
  private static final ResourceLocation LOOSER = new ResourceLocation("palamod:textures/gui/LuckyBlock/september/game/loose.png");
  
  private static final ResourceLocation WINNER = new ResourceLocation("palamod:textures/gui/LuckyBlock/september/game/win.png");
  
  private static final ResourceLocation CENTER = new ResourceLocation("palamod:textures/gui/LuckyBlock/september/game/center.png");
  
  private static final Color COLOR_TITLE = Color.decode("#EF3926");
  
  private static final Color COLOR_TITLE_SHADOW = Color.decode("#B91C0C");
  
  private int winNumber;
  
  private int currentNumber;
  
  private int tickAmount;
  
  private boolean started;
  
  private boolean finished;
  
  private boolean sent;
  
  private long lastTickMillis;
  
  private long finishMillis;
  
  private ResourceLocation currentResource;
  
  private ResourceLocation statusResource;
  
  public int getWinNumber() {
    return this.winNumber;
  }
  
  public int getCurrentNumber() {
    return this.currentNumber;
  }
  
  public int getTickAmount() {
    return this.tickAmount;
  }
  
  public boolean isStarted() {
    return this.started;
  }
  
  public boolean isFinished() {
    return this.finished;
  }
  
  public boolean isSent() {
    return this.sent;
  }
  
  public long getLastTickMillis() {
    return this.lastTickMillis;
  }
  
  public long getFinishMillis() {
    return this.finishMillis;
  }
  
  public ResourceLocation getCurrentResource() {
    return this.currentResource;
  }
  
  public ResourceLocation getStatusResource() {
    return this.statusResource;
  }
  
  public GoodPayUI(int winNumber) {
    this.currentNumber = 0;
    this.winNumber = winNumber;
    this.tickAmount = 0;
    this.currentResource = FIRST;
    this.statusResource = getWinOrLooseById();
    this.started = false;
    this.lastTickMillis = System.currentTimeMillis();
    this.sent = false;
  }
  
  public boolean canTick(long now) {
    return (now - this.lastTickMillis >= TICK_DELAY);
  }
  
  public boolean canClose(long now) {
    return (now - this.finishMillis >= CLOSE_UI_DELAY);
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
  }
  
  public void close() {
    this.field_146297_k.func_147108_a(null);
    this.field_146297_k.func_71381_h();
  }
  
  public void sendPacket() {
    SeptemberCommonModule.INSTANCE.getNetwork().sendToServer((IMessage)new CSGoodPayPacket());
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    super.preDraw(mouseX, mouseY, ticks);
    func_146276_q_();
    BackgroundHelper.createMinecraft(width(43.021F), height(76.481F));
    GuiUtils.drawStringWithCustomFont(this.field_146297_k, "La bonne paye", 
        
        width(30.677F), height(14.259F), COLOR_TITLE, Fonts.MINECRAFT_DUNGEONS_REGULAR
        .getFont(), 150, true, COLOR_TITLE_SHADOW);
    GuiUtils.drawImageTransparent(
        width(46.09375D), height(29.907408D), CENTER, 
        
        width(7.8125D), height(13.888889D), true);
    GuiUtils.drawImageTransparent(
        width(47.1875D), height(51.018517D), this.currentResource, 
        
        width(5.8333335D), height(10.37037D), true);
    double maxWidth = width(39.479168D) + width(29.739582D);
    double maxHeight = height(67.68519D) + height(16.574074D);
    GuiUtils.drawRect(
        width(29.739582D), height(67.68519D), maxWidth, maxHeight, new Color(0, 0, 0, 100));
    long now = System.currentTimeMillis();
    String text = this.started ? GoodPayEvent.REWARDS_MESSAGES[this.currentNumber] : "Cliquez pour commencer.";
    GuiUtils.drawSplittedString(this.field_146297_k, text, 
        
        width(50.0F), height(74.62963D), Color.WHITE, Fonts.MONTSERRAT_BOLD
        .getFont(), 40, width(40.0F), TextAlign.CENTER);
    if (this.finished) {
      GuiUtils.drawImageTransparent(
          width(46.09375D), height(29.907408D), this.statusResource, 
          
          width(7.8125D), height(13.888889D), false);
      if (!this.sent) {
        this.sent = true;
        sendPacket();
        return;
      } 
      if (canClose(now))
        close(); 
      return;
    } 
    if (!this.started || !canTick(now))
      return; 
    this.lastTickMillis = now;
    this.tickAmount++;
    int nextNumber = pickupRandomReward();
    while (nextNumber == this.currentNumber)
      nextNumber = pickupRandomReward(); 
    this.currentNumber = nextNumber;
    this.currentResource = getResourceById();
    SoundUtils.BUTTON_CLICK.playClientSound(this.field_146297_k.field_71439_g.field_70165_t, this.field_146297_k.field_71439_g.field_70163_u, this.field_146297_k.field_71439_g.field_70161_v, 1.0F, 1.0F);
    if (this.tickAmount == 20) {
      this.finished = true;
      this.finishMillis = now;
      this.currentNumber = this.winNumber;
      SoundUtils.LEVEL_UP.playClientSound(this.field_146297_k.field_71439_g.field_70165_t, this.field_146297_k.field_71439_g.field_70163_u, this.field_146297_k.field_71439_g.field_70161_v, 1.0F, 1.0F);
      return;
    } 
  }
  
  public ResourceLocation getResourceById() {
    switch (this.currentNumber) {
      case 1:
        return SECOND;
      case 2:
        return THIRD;
      case 3:
        return FOURTH;
      case 4:
        return FIFTH;
      case 5:
        return SIXTH;
    } 
    return FIRST;
  }
  
  private ResourceLocation getWinOrLooseById() {
    if (this.winNumber >= 3)
      return WINNER; 
    return LOOSER;
  }
  
  public int pickupRandomReward() {
    Random random = new Random();
    int rewardId = random.nextInt(6);
    return rewardId;
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    super.postDraw(mouseX, mouseY, ticks);
  }
  
  public void func_73869_a(char c, int keyCode) {
    this.started = true;
    if (keyCode == 1)
      return; 
    super.func_73869_a(c, keyCode);
  }
  
  protected void func_73864_a(int mouseX, int mouseY, int buttonID) {
    super.func_73864_a(mouseX, mouseY, buttonID);
    this.started = true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\clien\\uis\GoodPayUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */