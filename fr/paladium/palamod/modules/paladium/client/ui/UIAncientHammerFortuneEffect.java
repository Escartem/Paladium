package fr.paladium.palamod.modules.paladium.client.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.network.CSPacketAncientHammerValidateFortuneUI;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class UIAncientHammerFortuneEffect extends UI {
  private static final TextInfo TITLE_TEXT_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 20.0F, Color.WHITE);
  
  private final TweenAnimator animator;
  
  private final double randomPos;
  
  private final long endTime = System.currentTimeMillis() + 15000L;
  
  public UIAncientHammerFortuneEffect() {
    this.animator = TweenAnimator.create().sequence(1500.0F, 1.0F);
    this.animator.getTimeline().repeatYoyo(1000, 0.0F);
    this.animator.start();
    this.randomPos = Math.min(0.9D, Math.max(0.1D, Math.random()));
  }
  
  public void init() {
    BackgroundNode.create(1085.0D, 510.0D)
      .body(bg -> {
          TextNode.create(bg.dw(2.0D), 10.0D).text(Text.create("Last chance !", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 53.0F, Color.decode("#FFB44B")).shadow(Color.decode("#E57832")))).anchorX(Align.CENTER).attach(bg);
          TextNode.create(bg.dw(2.0D), 140.0D).text(Text.create("Ne perd pas ta money !", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 35.0F, Color.WHITE).shadow())).anchorX(Align.CENTER).attach(bg);
          double totalWidth = TITLE_TEXT_INFO.getWidth("PRESSEZ") + 10.0D + 47.0D + TITLE_TEXT_INFO.getWidth("AU BON MOMENT POUR GAGNER") + 7.0D;
          ContainerNode.create(bg.dw(2.0D) - totalWidth / 2.0D, 195.0D, totalWidth, TITLE_TEXT_INFO.getHeight()).body(()).attach(bg);
          ((TextNode)TextNode.create(bg.dw(2.0D), 230.0D).text(Text.create("Perte dans 15 secondes".toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 18.0F, Color.decode("#EF3926")))).anchorX(Align.CENTER).onUpdate(())).attach(bg);
          RectNode.create(bg.dw(2.0D) - 500.0D, 360.0D, 1000.0D, 6.0D).color(Color.decode("#1A1A1A")).attach(bg);
          RectNode.create(bg.dw(2.0D) - 500.0D, 280.0D, 1000.0D, 80.0D).color(Color.decode("#323232")).body(()).attach(bg);
          RectNode.create(42.0D, 400.0D, 19.0D, 19.0D).color(Color.decode("#5ED42A")).attach(bg);
          TextNode.create(76.0D, 409.5D).text(Text.create("X1", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, Color.WHITE))).anchorY(Align.CENTER).attach(bg);
          TextNode.create(111.0D, 410.5D).text(Text.create("Vous gardez votre money".toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 10.0F, Color.WHITE))).anchorY(Align.CENTER).attach(bg);
        }).attach(this);
  }
  
  public void update() {
    if (System.currentTimeMillis() >= this.endTime) {
      ZUI.close(this, true);
      PalaMod.getNetHandler().sendToServer((IMessage)new CSPacketAncientHammerValidateFortuneUI(false));
    } 
  }
  
  public void keyPressed(char c, int keyCode, @NonNull InternalContext context) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (keyCode == 57) {
      ZUI.close(this, true);
      double cursorPos = 1000.0D * this.animator.getValue();
      double minX = 1000.0D * this.randomPos - 10.0D;
      double maxX = 1000.0D * this.randomPos + 10.0D;
      boolean success = (cursorPos >= minX && cursorPos <= maxX);
      PalaMod.getNetHandler().sendToServer((IMessage)new CSPacketAncientHammerValidateFortuneUI(success));
      SoundUtils sound = success ? SoundUtils.LEVEL_UP : SoundUtils.ANVIL_BREAK;
      sound.playClientSound(1.5F, 1.0F);
    } 
  }
  
  public boolean close() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\ui\UIAncientHammerFortuneEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */