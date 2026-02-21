package fr.paladium.palamod.modules.paladium.client.gui.palamenu;

import fr.paladium.common.CommonModule;
import fr.paladium.helios.client.gui.UIHelios;
import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.AnimatedResourceLocation;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palamod.modules.paladium.client.events.ClientEventHandler;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.buttons.InGameMenuNodeButton;
import fr.paladium.palashop.client.ui.impl.home.UIShopHomePage;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.StringUtils;

public class UIInGameMenu extends UI {
  private int fadeInBackground;
  
  private static final ResourceLocation PALADIUM_TEXTURE = new ResourceLocation("palamod", "textures/gui/ingame/paladium.png");
  
  private static final ResourceLocation SHOP_TEXTURE = new ResourceLocation("palamod:textures/gui/ingame/button/shop.png");
  
  private static final ResourceLocation SHOP_TEXTURE_HOVER = new ResourceLocation("palamod:textures/gui/ingame/button/shop_hover.png");
  
  private static final ResourceLocation SALE_TEXTURE = new ResourceLocation("palamod:textures/gui/ingame/button/sale_icon.png");
  
  private static final ResourceLocation NEW_TEXTURE = new ResourceLocation("palamod:textures/gui/ingame/button/new_icon.png");
  
  private ANode quit;
  
  public UIInGameMenu() {
    super(null, "ingame");
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    addNode((new TexturedNodeButton(width(40.0F), height(46.5F), width(20.0F), height(7.0F), "palamod:textures/gui/ingame/button/resume"))
        .setCallback(node -> {
            Minecraft.func_71410_x().func_147108_a(null);
            Minecraft.func_71410_x().func_71381_h();
          }));
    addNode((new TexturedNodeButton(width(40.0F), height(54.5F), width(20.0F), height(7.0F), "palamod:textures/gui/ingame/button/settings"))
        .setCallback(node -> Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiOptions((GuiScreen)this, (Minecraft.func_71410_x()).field_71474_y))));
    addNode((new AClickableNode(width(40.0F), height(62.5F), width(20.0F), height(7.0F)) {
          public void draw(Minecraft mc, int mouseX, int mouseY) {
            super.draw(mc, mouseX, mouseY);
            if (isHovered(mouseX, mouseY)) {
              GuiUtils.drawImageTransparent(this.x, this.y, UIInGameMenu.SHOP_TEXTURE_HOVER, this.width, this.height, false);
            } else {
              AnimatedResourceLocation.from(UIInGameMenu.SHOP_TEXTURE).bind(mc.field_71439_g.field_70173_aa);
              GuiUtils.drawRectWithNoBinding(this.x, this.y, this.width, this.height);
            } 
            GuiUtils.drawCenteredStringWithCustomFont(mc, "Boutique", this.x + width(50.0F), this.y + height(40.0F), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 30);
            GuiUtils.drawImageTransparent(this.x + width(94.0F), this.y - width(2.0F), UIInGameMenu.NEW_TEXTURE, width(25.0F), (width(25.0F) / 3.0F), false);
          }
        }).setCallback(node -> ZUI.open((UI)new UIShopHomePage(), true)));
    addNode(this
        .quit = (new TexturedNodeButton(width(40.0F), height(70.5F), width(20.0F), height(7.0F), "palamod:textures/gui/ingame/button/leave")).setCallback(node -> {
            if ((Minecraft.func_71410_x()).field_71441_e != null)
              (Minecraft.func_71410_x()).field_71441_e.func_72882_A(); 
            Minecraft.func_71410_x().func_71403_a((WorldClient)null);
            Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiMainMenu());
          }));
    if (ClientEventHandler.events != null && ClientEventHandler.events.containsKey("BOSS"))
      addNode((ANode)new InGameMenuNodeButton(0.0D, height(38.5F), width(5.5F), height(7.0F), "palamod:textures/gui/ingame/button/boss", StringUtils.capitalize((String)ClientEventHandler.events.get("BOSS"))) {
            public boolean isHovered(int mouseX, int mouseY) {
              return true;
            }
          }); 
    if (ClientEventHandler.events != null && ClientEventHandler.events.containsKey("EGGHUNT"))
      addNode((ANode)new InGameMenuNodeButton(0.0D, height(46.5F), width(5.5F), height(7.0F), "palamod:textures/gui/ingame/button/egghunt", StringUtils.capitalize((String)ClientEventHandler.events.get("EGGHUNT"))) {
            public boolean isHovered(int mouseX, int mouseY) {
              return true;
            }
          }); 
    addNode((new InGameMenuNodeButton(0.0D, height(78.5F), width(5.5F), height(7.0F), "palamod:textures/gui/ingame/button/mailbox", "Mailbox"))
        .setCallback(node -> {
            if ((Minecraft.func_71410_x()).field_71439_g != null)
              (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/mailbox"); 
          }));
    addNode((new InGameMenuNodeButton(0.0D, height(86.5F), width(5.5F), height(7.0F), "palamod:textures/gui/ingame/button/hud", "HUD"))
        .setCallback(node -> Minecraft.func_71410_x().func_147108_a((GuiScreen)new UIHelios(false))));
  }
  
  public void fixedUpdate() {
    long seconds = (CommonModule.getInstance().getCombatTag().getEnd() - System.currentTimeMillis() + CommonModule.getInstance().getCombatTag().getTimeOffset()) / 1000L;
    if (CommonModule.getInstance().getCombatTag().inFight() && this.quit != null) {
      this.quit.setEnabled(false);
      this.quit.getHovers().clear();
      this.quit.addHover("§eVeuillez attendre §c" + seconds + " seconde" + ((seconds > 1L) ? "s" : ""));
      this.quit.setHoverFont(Fonts.MONTSERRAT_BOLD.getFont());
    } else if (this.quit != null) {
      this.quit.setEnabled(true);
      this.quit.getHovers().clear();
    } 
    if (this.fadeInBackground < 5)
      this.fadeInBackground++; 
    super.fixedUpdate();
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    GuiUtils.drawRect(0.0D, 0.0D, this.field_146294_l, this.field_146295_m, new Color(10, 10, 10, this.fadeInBackground * 30));
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    GuiUtils.drawImageTransparent(width(5.0F), height(5.0F), PALADIUM_TEXTURE, width(14.6875F), height(7.31481F), false);
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, this.field_146297_k.field_71439_g.func_70005_c_(), (this.field_146294_l / 2), height(43.0F), Color.WHITE, Fonts.MONTSERRAT_MEDIUM.getFont(), 50);
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, Server.getServerName().toUpperCase(), (this.field_146294_l / 2), height(35.0F), Color.WHITE, Fonts.MONTSERRAT_EXTRABOLD.getFont(), 180);
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, this.field_146297_k.field_71439_g.field_71174_a.field_147303_b.size() + " Joueurs connectés", (this.field_146294_l / 2), height(40.0F), Color.WHITE, Fonts.MONTSERRAT_MEDIUM.getFont(), 50);
  }
  
  public boolean func_73868_f() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\UIInGameMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */