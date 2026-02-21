package fr.paladium.palamod.modules.paladium.client.gui.palamenu;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.paladium.lib.apollon.Apollon;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import fr.paladium.lib.apollon.nodes.flex.FlexDirection;
import fr.paladium.lib.apollon.nodes.flex.FlexNode;
import fr.paladium.lib.apollon.notification.ANotification;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.palaforgeutils.lib.time.TimeUtils;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.ban.UIDinoGame;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.UIMainMenu;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class UIMaintenance extends UI {
  private static final ResourceLocation ARTY_TEXTURE = new ResourceLocation("palamod", "textures/gui/maintenance/arty.png");
  
  private static final ResourceLocation ARTY_SLEEP_TEXTURE = new ResourceLocation("palamod", "textures/gui/maintenance/sleep.png");
  
  private final String text;
  
  private int tick;
  
  private boolean closing;
  
  private double animation;
  
  private double animationTarget;
  
  private long cheatCodeStart = 0L;
  
  private String cheatCode = "";
  
  private boolean zzz;
  
  public UIMaintenance(String text) {
    super(null, "palamod:maintenance");
    this.text = text;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    Apollon.instance().getNotificationManager().getNotifications().forEach(notification -> Apollon.instance().getNotificationManager().removeNotification(notification));
    this.animationTarget = 1.0D;
    FlexNode iconsNode = new FlexNode(width(12.1875F), height(65.1851F), 0.0D, height(4.44F), FlexDirection.ROW);
    iconsNode.setMargin(width(3.64F));
    iconsNode.addChild((new TexturedNodeButton(0.0D, 0.0D, width(2.1875F), height(4.44F))).setTexture("palamod:textures/gui/maintenance/icons/discord").setCallback(n -> GuiUtils.openBrowser("https://www.discord.gg/paladium")));
    iconsNode.addChild((new TexturedNodeButton(0.0D, (height(4.44F) / 2.0F - height(3.796F) / 2.0F), width(1.875F), height(3.796F))).setTexture("palamod:textures/gui/maintenance/icons/tiktok").setCallback(n -> GuiUtils.openBrowser("https://www.tiktok.com/@paladium.pvp")));
    iconsNode.addChild((new TexturedNodeButton(0.0D, (height(4.44F) / 2.0F - height(3.61F) / 2.0F), width(2.5F), height(3.61F))).setTexture("palamod:textures/gui/maintenance/icons/twitter").setCallback(n -> GuiUtils.openBrowser("https://www.twitter.com/PaladiumPVP")));
    iconsNode.addChild((new TexturedNodeButton(0.0D, (height(4.44F) / 2.0F - height(3.33F) / 2.0F), width(2.656F), height(3.33F))).setTexture("palamod:textures/gui/maintenance/icons/youtube").setCallback(n -> GuiUtils.openBrowser("https://www.youtube.com/@PaladiumOff")));
    addNode((ANode)iconsNode);
    addNode((new TexturedNodeButton(width(16.09375F), height(74.6296F), width(13.125F), height(7.03F), "palamod:textures/gui/maintenance/close")).setCallback(n -> this.field_146297_k.func_71400_g()));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    this.animation = smoothValue(this.animation, this.animationTarget);
    drawBackground();
    int fontHeight = GuiUtils.getFontHeight(this.field_146297_k, Fonts.MONTSERRAT_MEDIUM.getFont(), 350);
    GL11.glEnable(3089);
    GuiUtils.scissor(this.field_146297_k, 0.0D, height(33.018F), this.field_146294_l, fontHeight);
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, "Serveur", width(22.55F), height(33.518F) + (1.0D - this.animation) * fontHeight, Color.WHITE, Fonts.MONTSERRAT_MEDIUM.getFont(), 350);
    GL11.glDisable(3089);
    GL11.glEnable(3089);
    GuiUtils.scissor(this.field_146297_k, 0.0D, height(41.5F), this.field_146294_l, fontHeight);
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, this.zzz ? "en §lmai...Zzz" : "en §lmaintenance", width(22.55F), height(42.0F) + (1.0D - this.animation) * fontHeight * 3.0D, Color.WHITE, Fonts.MONTSERRAT_MEDIUM.getFont(), 350);
    GL11.glDisable(3089);
    drawSplittedString(this.zzz ? "Zzz..." : ((this.text == null || this.text
        .isEmpty()) ? "Restez à l'affût des nouvelles" : this.text), 
        width(22.55F), height(55.925F), new Color(1.0F, 1.0F, 1.0F, (float)this.animation), Fonts.MONTSERRAT_MEDIUM.getFont(), 120, width(40.0F), TextAlign.CENTER);
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    GuiUtils.drawImageTransparent(0.0D, (1.0D - this.animation) * this.field_146295_m, this.zzz ? ARTY_SLEEP_TEXTURE : ARTY_TEXTURE, this.field_146294_l, this.field_146295_m);
    drawSplittedString("Paladium 2016 - " + 
        TimeUtils.nowZoned().getYear() + " - Tous droits réservés", 
        width(2.0F), height(95.0F), Color.white, Fonts.MONTSERRAT_MEDIUM.getFont(), 10, this.field_146294_l, TextAlign.LEFT);
    drawSplittedString("Copyright Mojang AB. Do not distribute! Thanks to Forge for the loader", 
        
        width(2.0F), height(97.0F), Color.white, Fonts.MONTSERRAT_MEDIUM.getFont(), 10, this.field_146294_l, TextAlign.LEFT);
    Apollon.instance().getNotificationManager().getNotifications().forEach(notification -> {
          if (notification.getRemainingTime() < 0L)
            Apollon.instance().getNotificationManager().removeNotification(notification); 
          notification.getManager().update((float)(System.currentTimeMillis() - notification.getLastUpdate()));
          notification.setLastUpdate(System.currentTimeMillis());
          notification.draw(Minecraft.func_71410_x());
        });
  }
  
  public void fixedUpdate() {
    super.fixedUpdate();
    this.tick++;
    if (!this.closing && this.tick % 600 == 0)
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
                if (!maintenance) {
                  this.animationTarget = 0.0D;
                  this.closing = true;
                } 
              } 
            } catch (Exception e) {
              e.printStackTrace();
            } 
          })).start(); 
    if (this.closing) {
      double diff = this.animationTarget - this.animation;
      double absDiff = Math.abs(diff);
      if (absDiff <= 0.001D) {
        Apollon.instance().getNotificationManager().getNotifications().forEach(notification -> Apollon.instance().getNotificationManager().removeNotification(notification));
        this.field_146297_k.func_147108_a((GuiScreen)new UIMainMenu());
      } 
    } 
  }
  
  protected void func_73869_a(char c, int keyCode) {
    super.func_73869_a(c, keyCode);
    if (keyCode == 57) {
      this.field_146297_k.func_147108_a((GuiScreen)new UIDinoGame());
      return;
    } 
    if (System.currentTimeMillis() - this.cheatCodeStart > 10000L) {
      this.cheatCode = "";
      this.cheatCodeStart = System.currentTimeMillis();
    } 
    this.cheatCode += c;
    if ("jeveuxjouer".equalsIgnoreCase(this.cheatCode)) {
      this.field_146297_k.func_147108_a((GuiScreen)new UIMainMenu(true));
      this.cheatCode = "";
      this.cheatCodeStart = System.currentTimeMillis();
    } else if ("zzz".equalsIgnoreCase(this.cheatCode)) {
      this.zzz = !this.zzz;
      this.cheatCode = "";
      this.cheatCodeStart = System.currentTimeMillis();
    } 
  }
  
  private double smoothValue(double value, double target) {
    int fps = 60;
    double diff = target - value;
    double absDiff = Math.abs(diff);
    double offset = 0.2D * absDiff / 3.0D;
    if (absDiff > 0.001D)
      value += (diff > 0.0D) ? offset : -offset; 
    return value;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\UIMaintenance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */