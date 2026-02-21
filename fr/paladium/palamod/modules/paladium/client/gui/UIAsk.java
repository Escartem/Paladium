package fr.paladium.palamod.modules.paladium.client.gui;

import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.paladiumui.kit.popup.UIYesNoPopup;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

public class UIAsk extends UI {
  private final boolean closeable;
  
  public boolean isCloseable() {
    return this.closeable;
  }
  
  public UIAsk(boolean closeable) {
    this.closeable = closeable;
  }
  
  public void init() {
    Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation(SoundUtils.NOTE_PIANO.getSoundName()), 10.0F));
    BackgroundNode.create(936.0D, 598.0D)
      .attach(this);
    if (this.closeable)
      CloseButtonNode.create(1378.0D, 270.0D)
        .onClick((node, mouseX, mouseY, clickType) -> ZUI.close(this))
        .attach(this); 
    TextNode.create(545.0D, 254.0D)
      .text(Text.create("attention", PaladiumText.HEADER))
      .attach(this);
    TextNode.create(545.0D, 361.0D)
      .text(Text.create("un modérateur vous demande de vous rendre sur teamspeak.", PaladiumText.TITLE))
      .mode(TextMode.SPLIT)
      .width(750.0D)
      .attach(this);
    FlexNode.horizontal(545.0D, 441.0D, 38.0D)
      .margin(20.0D)
      .body(flex -> {
          ImageNode.create(0.0D, 0.0D, 38.0D, 38.0D).resource(ResourceConstant.HELP).attach(flex);
          TextNode.create(0.0D, flex.dh(2.0D)).text(Text.create("comment faire", PaladiumText.TITLE)).anchorY(Align.CENTER).attach(flex);
          RectNode.create(0.0D, flex.dh(2.0D) - 0.5D, 512.0D, 1.0D).color(Color.WHITE).attach(flex);
        }).attach(this);
    RectNode.create(545.0D, 512.0D, 831.0D, 295.0D)
      .color(Color.BLACK.copyAlpha(0.15F))
      .body(rect -> RectNode.create(0.0D, rect.ah(-8.0D), rect.getWidth(), 8.0D).color(ColorConstant.PRIMARY).attach(rect))
      
      .attach(this);
    FlexNode.vertical(586.0D, 532.0D, 749.0D)
      .margin(31.0D)
      .body(flex -> {
          TextInfo info = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 15.0F, Color.WHITE).letterSpacing(1.0F);
          TextInfo subInfo = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_REGULAR, 15.0F, Color.WHITE);
          FlexNode.vertical(0.0D, 0.0D, flex.getWidth()).margin(7.0D).body(()).attach(flex);
          FlexNode.vertical(0.0D, 0.0D, flex.getWidth()).margin(7.0D).body(()).attach(flex);
          FlexNode.vertical(0.0D, 0.0D, flex.getWidth()).margin(7.0D).body(()).attach(flex);
        }).attach(this);
  }
  
  public boolean close() {
    if (!this.closeable) {
      Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation(SoundUtils.BUTTON_CLICK.getSoundName()), 10.0F));
      (new Notification(Notification.NotificationType.WARNING, "Seul un membre du staff peut fermer cette interface", "Paladium")).send();
      return false;
    } 
    ZUI.open((UI)(new UIYesNoPopup("voulez-vous fermer le message ?", "Veuillez ne pas fermer ce message si vous ne vous êtes pas rendu sur TeamSpeak.")).yesCallback(() -> ZUI.close(this, true)));
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\UIAsk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */