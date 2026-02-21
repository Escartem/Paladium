package fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu;

import com.google.common.base.Strings;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.paladium.lib.apollon.animation.AnimatedObject;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.lib.aurelienribon.tweenengine.TweenEquation;
import fr.paladium.lib.aurelienribon.tweenengine.TweenEquations;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.time.TimeUtils;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.UIMaintenance;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.UIModsVersion;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.ban.UIDinoGame;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.node.MainMenuSelectionNode;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.utils.MainMenuBackgroundElement;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.utils.connector.ServerConnector;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.utils.connector.ServerSession;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.preloader.UIPreLoader;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.LoadingScreenRenderer;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class UIMainMenu extends UI {
  public void setFrameCount(int frameCount) {
    this.frameCount = frameCount;
  }
  
  public void setArrowXAnimatedValue(AnimatedObject arrowXAnimatedValue) {
    this.arrowXAnimatedValue = arrowXAnimatedValue;
  }
  
  public void setArrowYAnimatedValue(AnimatedObject arrowYAnimatedValue) {
    this.arrowYAnimatedValue = arrowYAnimatedValue;
  }
  
  public void setLastAnimatedObjectUpdate(long lastAnimatedObjectUpdate) {
    this.lastAnimatedObjectUpdate = lastAnimatedObjectUpdate;
  }
  
  public void setPlayNodeAnimation(int playNodeAnimation) {
    this.playNodeAnimation = playNodeAnimation;
  }
  
  public void setPlayNode(MainMenuSelectionNode playNode) {
    this.playNode = playNode;
  }
  
  public void setTick(int tick) {
    this.tick = tick;
  }
  
  public void setSession(ServerSession session) {
    this.session = session;
  }
  
  public void setBypass(boolean bypass) {
    this.bypass = bypass;
  }
  
  private static final ResourceLocation PLAY_BACKGROUND_TEXTURE = new ResourceLocation("palamod", "textures/gui/main/background/play.jpg");
  
  private static final ResourceLocation NEWS_BACKGROUND_TEXTURE = new ResourceLocation("palamod", "textures/gui/main/background/news.jpg");
  
  private static final ResourceLocation SHOP_BACKGROUND_TEXTURE = new ResourceLocation("palamod", "textures/gui/main/background/shop.jpg");
  
  private static final ResourceLocation SETTINGS_BACKGROUND_TEXTURE = new ResourceLocation("palamod", "textures/gui/main/background/settings.jpg");
  
  private static final ResourceLocation QUIT_BACKGROUND_TEXTURE = new ResourceLocation("palamod", "textures/gui/main/background/quit.jpg");
  
  private static final ResourceLocation PALADIUM_ICON = new ResourceLocation("palamod", "textures/gui/main/icons/paladium.png");
  
  private static final ResourceLocation ARROW_ICON = new ResourceLocation("palamod", "textures/gui/main/icons/arrow.png");
  
  public static boolean loaded = false;
  
  private static TweenAnimator animator;
  
  private int frameCount;
  
  private AnimatedObject arrowXAnimatedValue;
  
  private AnimatedObject arrowYAnimatedValue;
  
  private long lastAnimatedObjectUpdate;
  
  public int getFrameCount() {
    return this.frameCount;
  }
  
  public AnimatedObject getArrowXAnimatedValue() {
    return this.arrowXAnimatedValue;
  }
  
  public AnimatedObject getArrowYAnimatedValue() {
    return this.arrowYAnimatedValue;
  }
  
  public long getLastAnimatedObjectUpdate() {
    return this.lastAnimatedObjectUpdate;
  }
  
  private final List<MainMenuBackgroundElement> backgroundList = new ArrayList<>();
  
  private int playNodeAnimation;
  
  private MainMenuSelectionNode playNode;
  
  private int tick;
  
  private ServerSession session;
  
  private boolean connected;
  
  private boolean bypass;
  
  public List<MainMenuBackgroundElement> getBackgroundList() {
    return this.backgroundList;
  }
  
  public int getPlayNodeAnimation() {
    return this.playNodeAnimation;
  }
  
  public MainMenuSelectionNode getPlayNode() {
    return this.playNode;
  }
  
  public int getTick() {
    return this.tick;
  }
  
  public ServerSession getSession() {
    return this.session;
  }
  
  public boolean isConnected() {
    return this.connected;
  }
  
  public boolean isBypass() {
    return this.bypass;
  }
  
  public UIMainMenu(boolean bypass) {
    this.bypass = true;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    if (!loaded) {
      ZUI.open((UI)new UIPreLoader());
      return;
    } 
    if (animator == null)
      animator = TweenAnimator.create(1.0F); 
    if (checkMaintenance())
      return; 
    boolean isDev = (Constants.MOD_ENV == Constants.Environment.DEV);
    this.arrowYAnimatedValue = new AnimatedObject();
    this.arrowXAnimatedValue = new AnimatedObject();
    this.lastAnimatedObjectUpdate = System.currentTimeMillis();
    addNode((ANode)(this.playNode = new MainMenuSelectionNode(width(7.08F), height(30.74F), width(16.0F), height(13.8F), "jouer", PLAY_BACKGROUND_TEXTURE, 350, true)));
    if (this.session != null && this.session.isActive()) {
      this.playNodeAnimation = 0;
      this.playNode.setEnabled(false);
      this.playNode.setText("connexion");
      this.field_146297_k.field_71461_s = null;
      this.session.setCallback(s -> {
            if ("failed".equalsIgnoreCase(s) || "cancelled".equalsIgnoreCase(s)) {
              this.playNode.setEnabled(true);
              if ("failed".equalsIgnoreCase(s)) {
                this.playNode.setText("réessayer");
              } else {
                this.playNode.setText("jouer");
              } 
            } 
          });
    } 
    this.playNode.setCallback(n -> {
          if (isDev) {
            this.field_146297_k.field_71461_s = new LoadingScreenRenderer(this.field_146297_k);
            this.field_146297_k.func_147108_a((GuiScreen)new GuiSelectWorld((GuiScreen)this));
          } else {
            this.playNodeAnimation = 0;
            n.setEnabled(false);
            ((MainMenuSelectionNode)n).setText("connexion");
            this.field_146297_k.field_71461_s = null;
            this.session = ServerConnector.connect("mc.paladium-pvp.fr", 25565, ());
          } 
          this.arrowXAnimatedValue.createSequence().push(150.0F, width(4.5F), (TweenEquation)TweenEquations.easeInOutQuart).push(150.0F, width(4.0F), (TweenEquation)TweenEquations.easeInOutQuart).start();
        });
    addNode((new MainMenuSelectionNode(width(7.08F), height(50.739998F), width(16.0F), height(7.0F), "news", NEWS_BACKGROUND_TEXTURE, 150, false))
        .setCallback(n -> GuiUtils.openBrowser("https://paladium-pvp.fr/articles")));
    addNode((new MainMenuSelectionNode(width(7.08F), height(58.739998F), width(16.0F), height(7.0F), "boutique", SHOP_BACKGROUND_TEXTURE, 150, false))
        .setCallback(n -> GuiUtils.openBrowser("https://store.paladium-pvp.fr")));
    addNode((new MainMenuSelectionNode(width(7.08F), height(66.74F), width(16.0F), height(7.0F), "options", SETTINGS_BACKGROUND_TEXTURE, 150, false))
        .setCallback(n -> this.field_146297_k.func_147108_a((GuiScreen)new GuiOptions((GuiScreen)this, this.field_146297_k.field_71474_y))));
    addNode((new MainMenuSelectionNode(width(7.08F), height(74.74F), width(16.0F), height(7.0F), "quitter", QUIT_BACKGROUND_TEXTURE, 150, false))
        .setCallback(n -> this.field_146297_k.func_71400_g()));
    if (isDev)
      addNode((new MainMenuSelectionNode(width(7.08F), height(42.739998F), width(16.0F), height(7.0F), "multijoueur", PLAY_BACKGROUND_TEXTURE, 150, false))
          .setCallback(n -> this.field_146297_k.func_147108_a((GuiScreen)new GuiMultiplayer((GuiScreen)this)))); 
    this.arrowXAnimatedValue.setValue(width(4.0F));
    this.arrowYAnimatedValue.setValue(height(30.74F) + height(4.1400003F));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    GuiUtils.drawImageTransparent(0.0D, 0.0D, PLAY_BACKGROUND_TEXTURE, this.field_146294_l, this.field_146295_m, false);
    if (!this.connected)
      for (MainMenuBackgroundElement element : this.backgroundList) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, element.getAnimationValue());
        GuiUtils.drawImageTransparent(0.0D, 0.0D, element.getTexture(), this.field_146294_l, this.field_146295_m, false);
      }  
    Color.WHITE.bind();
    long now = System.currentTimeMillis();
    if (this.arrowXAnimatedValue.getTimeline() != null)
      this.arrowXAnimatedValue.getTimeline().update((float)(now - this.lastAnimatedObjectUpdate)); 
    if (this.arrowYAnimatedValue.getTimeline() != null)
      this.arrowYAnimatedValue.getTimeline().update((float)(now - this.lastAnimatedObjectUpdate)); 
    this.lastAnimatedObjectUpdate = now;
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    drawSplittedString("Paladium 2016 - " + TimeUtils.nowZoned().getYear() + " - Tous droits réservés", width(99.0F), height(95.0F), Color.WHITE, Fonts.PIXEL_NES.getFont(), 0, this.field_146294_l, TextAlign.RIGHT);
    drawSplittedString("Copyright Mojang AB. Do not distribute! Thanks to Forge for the loader", width(99.0F), height(97.0F), Color.WHITE, Fonts.PIXEL_NES.getFont(), 0, this.field_146294_l, TextAlign.RIGHT);
    drawSplittedString("version " + Constants.CDN_VERSION, width(99.0F), height(3.0F), new Color(26, 39, 55), Fonts.PIXEL_NES.getFont(), 0, this.field_146294_l, TextAlign.RIGHT);
    drawSplittedString("adventure", width(99.0F), height(5.0F), new Color(26, 39, 55), Fonts.PIXEL_NES.getFont(), 0, this.field_146294_l, TextAlign.RIGHT);
    GuiUtils.drawImageTransparent(width(3.75F), height(5.0F), PALADIUM_ICON, width(3.5F), width(3.5F));
    GuiUtils.drawImageTransparent(this.arrowXAnimatedValue.getValue(), this.arrowYAnimatedValue.getValue(), ARROW_ICON, width(2.0F), width(2.0F));
    if (this.frameCount == 10)
      animator.sequence(1000.0F, 0.0F, (TweenEquation)TweenEquations.QUART_INOUT).start(); 
    animator.update();
    if (animator.getValue() > 0.0F) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, animator.getValue());
      double width = this.field_146297_k.field_71443_c;
      double height = this.field_146297_k.field_71440_d;
      double scaledWidth = width(100.0D);
      double scaledHeight = height(100.0D);
      double ratio = 1.7777777777777777D;
      double currentRatio = width / height;
      double resourceWidth = 1920.0D;
      double resourceHeight = 1080.0D;
      if (currentRatio >= 1.7777777777777777D) {
        double imageHeight = scaledWidth * 1080.0D / 1920.0D;
        DrawUtils.RESOURCE.drawImage(0.0D, scaledHeight / 2.0D - imageHeight / 2.0D, scaledWidth, imageHeight, UIPreLoader.BACKGROUND_TEXTURE.linear());
      } else {
        double imageWidth = scaledHeight * 1920.0D / 1080.0D;
        DrawUtils.RESOURCE.drawImage(scaledWidth / 2.0D - imageWidth / 2.0D, 0.0D, imageWidth, scaledHeight, UIPreLoader.BACKGROUND_TEXTURE.linear());
      } 
    } 
    this.frameCount++;
  }
  
  public void fixedUpdate() {
    super.fixedUpdate();
    this.tick++;
    if (this.tick % 600 == 0)
      (new Thread(() -> checkMaintenance()))
        
        .start(); 
    if (this.playNode != null && this.session != null && this.session.isActive() && this.tick % 5 == 0 && !this.connected) {
      this.playNode.setText("connexion" + Strings.repeat(".", this.playNodeAnimation));
      this.playNodeAnimation++;
      if (this.playNodeAnimation > 3)
        this.playNodeAnimation = 0; 
    } 
  }
  
  protected void func_73869_a(char c, int keyCode) {
    super.func_73869_a(c, keyCode);
    if (keyCode == 57)
      this.field_146297_k.func_147108_a((GuiScreen)new UIDinoGame()); 
  }
  
  public void func_73876_c() {
    super.func_73876_c();
    if (this.session != null)
      this.session.update(); 
  }
  
  public void func_146281_b() {
    if (this.session != null && this.field_146297_k.field_71441_e == null)
      this.session.cancel(); 
  }
  
  public void setFocused(ResourceLocation texture, float position) {
    this.backgroundList.add(new MainMenuBackgroundElement(texture));
    if (this.backgroundList.size() > 3)
      this.backgroundList.remove(0); 
    this.arrowYAnimatedValue
      .createSequence()
      .push(300.0F, position, (TweenEquation)TweenEquations.easeInOutQuart)
      .start();
  }
  
  public void setConnected(boolean connected) {
    this.connected = connected;
    getNodes().forEach(node -> node.setEnabled(false));
    this.playNode.setText("vérification");
    this.playNodeAnimation = 0;
  }
  
  private boolean checkMaintenance() {
    if (this.bypass || ForgeEnv.isDev() || Constants.MOD_ENV == Constants.Environment.DEV)
      return false; 
    try {
      URL url = new URL("https://download.paladium-pvp.fr/maintenance.json");
      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Accept", "application/json");
      connection.setDoOutput(true);
      try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
        StringBuilder response = new StringBuilder();
        String responseLine = null;
        while ((responseLine = br.readLine()) != null)
          response.append(responseLine.trim()); 
        JsonObject result = (new JsonParser()).parse(response.toString()).getAsJsonObject();
        boolean maintenance = (result.get("maintenance").getAsBoolean() && Constants.MOD_ENV != Constants.Environment.DEV);
        if (maintenance && this.field_146297_k.field_71462_r instanceof UIMainMenu) {
          this.field_146297_k.func_147108_a((GuiScreen)new UIMaintenance(result.get("description").getAsString()));
          return true;
        } 
        if (Constants.MOD_ENV != Constants.Environment.DEV) {
          String version = result.get("version").getAsString();
          if (!Constants.CDN_VERSION.equals(version) && Constants.MOD_ENV != Constants.Environment.DEV) {
            this.field_146297_k.func_147108_a((GuiScreen)new UIModsVersion());
            return true;
          } 
        } 
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return false;
  }
  
  public UIMainMenu() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\mainmenu\UIMainMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */