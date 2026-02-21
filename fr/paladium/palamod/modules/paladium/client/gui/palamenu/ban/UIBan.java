package fr.paladium.palamod.modules.paladium.client.gui.palamenu.ban;

import com.google.gson.JsonObject;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.UIMainMenu;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import java.util.regex.Pattern;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class UIBan extends UI {
  private final BanData data;
  
  public UIBan(@NonNull BanData data) {
    if (data == null)
      throw new NullPointerException("data is marked non-null but is null"); 
    this.data = data;
  }
  
  public void init() {
    if (this.data == null || !this.data.isValid()) {
      ZUI.close(this, true);
      return;
    } 
    BackgroundNode.create()
      .attach(this);
    ImageNode.create(960.0D, 130.0D)
      .resource(this.data.isIp() ? Resource.of(new ResourceLocation("palamod", "textures/gui/ban/ip.png")).nearest() : Resource.of(new ResourceLocation("palamod", "textures/gui/ban/default.png")).nearest())
      .anchorX(Align.CENTER)
      .attach(this);
    FlexNode.vertical(640.0D, 397.0D, 640.0D)
      .align(Align.CENTER)
      .margin(50.0D)
      .body(flex -> {
          Color reasonColor = Color.decode("#EF9F26");
          Color timeColor = Color.decode("#2DC7FF");
          Color contestColor = Color.decode("#EF3926");
          if (this.data.getMessage() == null) {
            RectNode.create(0.0D, 0.0D, flex.getWidth(), 100.0D).color(Color.BLACK.copyAlpha(0.15F)).body(()).attach(flex);
          } else {
            RectNode.create(0.0D, 0.0D, flex.getWidth(), 130.0D).color(Color.BLACK.copyAlpha(0.15F)).body(()).attach(flex);
          } 
          RectNode.create(0.0D, 0.0D, flex.getWidth(), 100.0D).color(Color.BLACK.copyAlpha(0.15F)).body(()).attach(flex);
          RectNode.create(0.0D, 0.0D, flex.getWidth(), 159.0D).color(Color.BLACK.copyAlpha(0.15F)).body(()).attach(flex);
        }).attach(this);
    TextButtonNode.create(960.0D, 944.0D)
      .text("retourner au menu principal")
      .onClick((node, mouseX, mouseY, clickType) -> Minecraft.func_71410_x().func_147108_a((GuiScreen)new UIMainMenu()))
      .anchorX(Align.CENTER)
      .attach(this);
  }
  
  public void drawBackground(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(0.0D, 0.0D, getWidth(), getHeight(), ColorConstant.BACKGROUND);
  }
  
  public static class BanData {
    private final boolean ip;
    
    private final String reason;
    
    private final String message;
    
    private final String remaining;
    
    public BanData(boolean ip, String reason, String message, String remaining) {
      this.ip = ip;
      this.reason = reason;
      this.message = message;
      this.remaining = remaining;
    }
    
    public boolean isIp() {
      return this.ip;
    }
    
    public String getReason() {
      return this.reason;
    }
    
    public String getMessage() {
      return this.message;
    }
    
    public String getRemaining() {
      return this.remaining;
    }
    
    public boolean isValid() {
      return (this.reason != null && this.remaining != null);
    }
    
    public static BanData parse(JsonObject json) {
      if (json == null)
        return null; 
      return new BanData((json.has("ip") && json.get("ip").getAsBoolean()), 
          json.has("reason") ? json.get("reason").getAsString() : null, 
          json.has("message") ? json.get("message").getAsString() : null, 
          json.has("remaining") ? json.get("remaining").getAsString() : null);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\ban\UIBan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */