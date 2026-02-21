package fr.paladium.palamod.modules.paladium.client.gui.palamenu.preloader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.time.TimeUtils;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.UIMaintenance;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.UIModsVersion;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.UIMainMenu;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.utils.connector.ServerConnector;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.utils.connector.ServerSession;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class UIPreLoader extends UI {
  public static final Resource BACKGROUND_TEXTURE = Resource.of(new ResourceLocation("palamod", "textures/gui/preloader/background.png"));
  
  private static final Resource LOGO_TEXTURE = Resource.of(new ResourceLocation("palamod", "textures/gui/preloader/logo.png"));
  
  private ServerSession session;
  
  private TweenAnimator animator;
  
  private int frameCount;
  
  public void init() {
    this.animator = TweenAnimator.create(0.0F);
  }
  
  public void keyPressed(char c, int keyCode, @NonNull InternalContext context) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    context.cancel(() -> {
          if (this.frameCount < 10 || this.animator.getValue() == 0.0F)
            return; 
          start();
        });
  }
  
  public void mouseReleased(double mouseX, double mouseY, ClickType clickType, @NonNull InternalContext context) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    context.cancel(() -> {
          if (this.frameCount < 10 || this.animator.getValue() == 0.0F)
            return; 
          start();
        });
  }
  
  public void postDraw(double mouseX, double mouseY, float ticks) {
    if (this.frameCount == 10)
      this.animator.sequence(1000.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).start(); 
    this.animator.update();
    Color color = new Color(1.0F, 1.0F, 1.0F, this.animator.getValue());
    color.bind();
    DrawUtils.RESOURCE.drawImage(290.0D, 310.0D, 1340.0D, 304.0D, LOGO_TEXTURE.linear());
    TextInfo textInfo = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 15.0F).color(color);
    DrawUtils.TEXT.drawText(960.0D, 999.0D, Text.create("Paladium 2016 - " + TimeUtils.nowZoned().getYear() + " - Tous droits réservés", textInfo, Align.CENTER));
    DrawUtils.TEXT.drawText(960.0D, 1028.0D, Text.create("Copyright Mojang AB. Do not distribute! Thanks to Forge for the loader", textInfo, Align.CENTER));
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    float opacity = Math.min(this.animator.getValue(), (float)Math.sin(System.currentTimeMillis() / 1000.0D) / 2.0F + 0.5F);
    DrawUtils.TEXT.drawText(960.0D, 736.0D, Text.create((this.session != null && this.session.isActive()) ? "connexion en cours" : "appuie sur une touche", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 33.0F, new Color(1.0F, 1.0F, 1.0F, opacity)).shadow().shadow(0.0F, 4.0F), Align.CENTER));
    this.frameCount++;
  }
  
  public void drawBackground(double mouseX, double mouseY, float ticks) {
    double width = getWidth();
    double height = getHeight();
    ScaledResolution resolution = new ScaledResolution(Minecraft.func_71410_x(), (int)width, (int)height);
    double scaledWidth = resolution.func_78327_c();
    double scaledHeight = resolution.func_78324_d();
    double ratio = 1.7777777777777777D;
    double currentRatio = width / height;
    double resourceWidth = 1920.0D;
    double resourceHeight = 1080.0D;
    if (currentRatio >= 1.7777777777777777D) {
      double imageHeight = scaledWidth * 1080.0D / 1920.0D;
      DrawUtils.RESOURCE.drawImage(0.0D, scaledHeight / 2.0D - imageHeight / 2.0D, scaledWidth, imageHeight, BACKGROUND_TEXTURE.linear());
    } else {
      double imageWidth = scaledHeight * 1920.0D / 1080.0D;
      DrawUtils.RESOURCE.drawImage(scaledWidth / 2.0D - imageWidth / 2.0D, 0.0D, imageWidth, scaledHeight, BACKGROUND_TEXTURE.linear());
    } 
  }
  
  private void start() {
    UIMainMenu.loaded = true;
    checkStatus().whenComplete((success, error) -> {
          if (error != null) {
            Minecraft.func_71410_x().func_147108_a((GuiScreen)new UIMainMenu());
            error.printStackTrace();
            return;
          } 
          if (!success.booleanValue())
            return; 
          String ip = (Constants.MOD_ENV == Constants.Environment.DEV) ? "localhost" : "mc.paladium-pvp.fr";
          int port = (Constants.MOD_ENV == Constants.Environment.DEV) ? 25565 : 25565;
          this.session = ServerConnector.connect(ip, port, ());
          this.animator.sequence(1000.0F, 0.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
        });
  }
  
  public void update() {
    if (this.session != null)
      this.session.update(); 
  }
  
  public boolean close() {
    if (this.session != null && (Minecraft.func_71410_x()).field_71441_e == null)
      this.session.cancel(); 
    return super.close();
  }
  
  private CompletableFuture<Boolean> checkStatus() {
    if (ForgeEnv.isIDE())
      return CompletableFuture.completedFuture(Boolean.valueOf(true)); 
    Minecraft mc = Minecraft.func_71410_x();
    CompletableFuture<Boolean> future = new CompletableFuture<>();
    (new Thread(() -> {
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
              boolean maintenance = result.get("maintenance").getAsBoolean();
              if (maintenance) {
                mc.func_147108_a((GuiScreen)new UIMaintenance(result.get("description").getAsString()));
                future.complete(Boolean.valueOf(false));
                return;
              } 
              if (Constants.MOD_ENV != Constants.Environment.DEV) {
                String version = result.get("version").getAsString();
                if (!Constants.CDN_VERSION.equals(version)) {
                  mc.func_147108_a((GuiScreen)new UIModsVersion());
                  future.complete(Boolean.valueOf(false));
                  return;
                } 
              } 
              future.complete(Boolean.valueOf(true));
            } 
          } catch (Exception e) {
            future.completeExceptionally(e);
          } 
        }"UIPreLoader/Maintenance")).start();
    return future;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\preloader\UIPreLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */