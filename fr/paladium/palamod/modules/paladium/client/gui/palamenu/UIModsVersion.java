package fr.paladium.palamod.modules.paladium.client.gui.palamenu;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.palaforgeutils.lib.time.TimeUtils;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.UIMainMenu;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import net.minecraft.client.gui.GuiScreen;

public class UIModsVersion extends UI {
  private int tick;
  
  private long cheatCodeStart = 0L;
  
  private String cheatCode = "";
  
  public UIModsVersion() {
    super(null, "palamod:modsversion");
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    addNode((new TexturedNodeButton(width(17.5F), height(65.55F), width(13.125F), height(7.03F), "palamod:textures/gui/modsversion/close")).setCallback(n -> this.field_146297_k.func_71400_g()));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    drawBackground();
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    drawSplittedString("Paladium 2016 - " + TimeUtils.nowZoned().getYear() + " - Tous droits réservés", width(98.0F), height(95.0F), Color.white, Fonts.MONTSERRAT_MEDIUM
        .getFont(), 10, this.field_146294_l, TextAlign.RIGHT);
    drawSplittedString("Copyright Mojang AB. Do not distribute! Thanks to Forge for the loader", 
        width(98.5F), height(97.0F), Color.white, Fonts.MONTSERRAT_MEDIUM.getFont(), 10, this.field_146294_l, TextAlign.RIGHT);
    drawSplittedString("v" + Constants.CDN_VERSION, 
        
        width(2.0F), height(97.0F), Color.white, Fonts.MONTSERRAT_MEDIUM.getFont(), 10, this.field_146294_l, TextAlign.LEFT);
  }
  
  public void fixedUpdate() {
    super.fixedUpdate();
    this.tick++;
    if (this.tick % 600 == 0)
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
                String version = result.get("version").getAsString();
                if (version.equals(Constants.CDN_VERSION))
                  this.field_146297_k.func_147108_a((GuiScreen)new UIMainMenu()); 
              } 
            } catch (Exception e) {
              e.printStackTrace();
            } 
          })).start(); 
  }
  
  protected void func_73869_a(char c, int keyCode) {
    super.func_73869_a(c, keyCode);
    if (System.currentTimeMillis() - this.cheatCodeStart > 10000L) {
      this.cheatCode = "";
      this.cheatCodeStart = System.currentTimeMillis();
    } 
    this.cheatCode += c;
    if (this.cheatCode.equalsIgnoreCase("jeveuxjouer")) {
      this.field_146297_k.func_147108_a((GuiScreen)new UIMainMenu(true));
      this.cheatCode = "";
      this.cheatCodeStart = System.currentTimeMillis();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\UIModsVersion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */