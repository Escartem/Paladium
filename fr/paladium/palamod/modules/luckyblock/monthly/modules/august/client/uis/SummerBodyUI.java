package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.uis;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.uis.nodes.SwitchButtonNode;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets.CSSummerBodyPacket;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import net.minecraft.util.ResourceLocation;

public class SummerBodyUI extends UI {
  private static final String TIME_REMAINING = "TEMPS RESTANT";
  
  private static final String CLICK = "CLIQUE!";
  
  private static final String TEXTURES_PATH = "palamod:textures/gui/LuckyBlock/august/";
  
  private static final long MAX_DURATION = TimeUnit.SECONDS.toMillis(30L);
  
  private static final long MAX_CLICK_INTERVAL = 250L;
  
  private static final int MAX_CLICKS = 100;
  
  private static final ResourceLocation STEVE = new ResourceLocation("palamod:textures/gui/LuckyBlock/august/steve_standing.png");
  
  private static final ResourceLocation STEVE_RUNNING = new ResourceLocation("palamod:textures/gui/LuckyBlock/august/steve_running.png");
  
  private static final ResourceLocation STEVE_RUNNING_2 = new ResourceLocation("palamod:textures/gui/LuckyBlock/august/steve_running_2.png");
  
  private static final ResourceLocation BACKGROUND = new ResourceLocation("palamod:textures/gui/LuckyBlock/august/background.png");
  
  private final DoubleLocation location;
  
  private final long expirationMillis;
  
  private int clicks;
  
  private long lastClickMillis;
  
  private ResourceLocation steveResource;
  
  public DoubleLocation getLocation() {
    return this.location;
  }
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
  
  public int getClicks() {
    return this.clicks;
  }
  
  public long getLastClickMillis() {
    return this.lastClickMillis;
  }
  
  public ResourceLocation getSteveResource() {
    return this.steveResource;
  }
  
  public SummerBodyUI(DoubleLocation location) {
    this.location = location;
    this.expirationMillis = System.currentTimeMillis() + MAX_DURATION;
    this.clicks = 0;
    this.lastClickMillis = 0L;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    SwitchButtonNode node = new SwitchButtonNode(width(46.25D), height(77.196295D), width(7.5000005D), height(9.444445D));
    addNode((ANode)node);
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    super.preDraw(mouseX, mouseY, ticks);
    long now = System.currentTimeMillis();
    float[] stevePosition = getStevePosition();
    this.steveResource = getSteveResource(now);
    func_146276_q_();
    BackgroundHelper.createMinecraft(width(43.021F), height(76.481F));
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, "TEMPS RESTANT", width(50.0F), height(21.759F), Color.WHITE, Fonts.PIXEL_NES.getFont(), 150);
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, getRemainingTime(now), width(50.0F), height(26.759F), Color.GRAY, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 400);
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, getRemainingTime(now), width(50.0F), height(25.759F), Color.WHITE, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 400);
    GuiUtils.drawImageTransparent(width(30.781F), height(45.371F), BACKGROUND, width(37.916668D), height(13.703704D), true);
    drawSteve(stevePosition[0], stevePosition[1], this.steveResource, now);
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, "CLIQUE!", width(50.0F), height(72.18519F), Color.WHITE, Fonts.PIXEL_NES.getFont(), 150);
    handleStatus(now);
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    super.postDraw(mouseX, mouseY, ticks);
  }
  
  public void func_73869_a(char c, int keyCode) {
    if (keyCode == 1)
      return; 
    super.func_73869_a(c, keyCode);
  }
  
  protected void func_73864_a(int mouseX, int mouseY, int buttonID) {
    super.func_73864_a(mouseX, mouseY, buttonID);
    List<SwitchButtonNode> switchButtonNodes = (List<SwitchButtonNode>)getNodes().stream().filter(node -> node instanceof SwitchButtonNode).map(node -> (SwitchButtonNode)node).collect(Collectors.toList());
    for (SwitchButtonNode switchButtonNode : switchButtonNodes) {
      if (switchButtonNode.switchType(mouseX, mouseY, buttonID)) {
        handleNodeClick(mouseX, mouseY);
        break;
      } 
    } 
  }
  
  public void handleStatus(long now) {
    if (this.clicks >= 100) {
      handlePacket(true);
    } else if (isExpired(now)) {
      handlePacket(false);
    } 
  }
  
  private void handlePacket(boolean success) {
    this.field_146297_k.func_147108_a(null);
    this.field_146297_k.func_71381_h();
    PalaMod.network.sendToServer((IMessage)new CSSummerBodyPacket(this.location, success));
  }
  
  private void handleNodeClick(int mouseX, int mouseY) {
    long now = System.currentTimeMillis();
    incrementClicks(now);
    if (this.steveResource == STEVE_RUNNING) {
      this.steveResource = STEVE_RUNNING_2;
    } else {
      this.steveResource = STEVE_RUNNING;
    } 
  }
  
  private float[] getStevePosition() {
    float minX = width(30.781F);
    float maxX = width(66.281F);
    float x = minX + (maxX - minX) * this.clicks / 100.0F;
    float y = height(51.371F);
    return new float[] { x, y };
  }
  
  private ResourceLocation getSteveResource(long now) {
    if (!isRunning(now) || this.steveResource == null)
      return STEVE; 
    return this.steveResource;
  }
  
  private void drawSteve(double x, double y, ResourceLocation resource, long now) {
    GuiUtils.drawImageTransparent(x, y, resource, width(2.3958333F), height(7.1296296F), true);
  }
  
  private String getRemainingTime(long now) {
    if (isExpired(now))
      return "00:00"; 
    long remainingMillis = getRemainingMillis(now);
    long seconds = TimeUnit.MILLISECONDS.toSeconds(remainingMillis);
    long minutes = 0L;
    return String.format("%02d:%02d", new Object[] { Long.valueOf(minutes), Long.valueOf(seconds) });
  }
  
  private void incrementClicks(long now) {
    if (this.clicks >= 100)
      return; 
    this.clicks++;
    this.lastClickMillis = now;
  }
  
  private boolean isRunning(long now) {
    return (this.lastClickMillis + 250L > now);
  }
  
  private boolean isExpired(long now) {
    return (getRemainingMillis(now) <= 0L);
  }
  
  private long getRemainingMillis(long now) {
    return this.expirationMillis - now;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\clien\\uis\SummerBodyUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */