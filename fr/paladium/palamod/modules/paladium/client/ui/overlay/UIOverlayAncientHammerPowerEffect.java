package fr.paladium.palamod.modules.paladium.client.ui.overlay;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.time.UniversalTimeUtils;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.data.ItemAncientHammerPlayerData;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.UIDataOverlay;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.render.UIDataOverlayRender;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;

@UIData(background = false, anchorX = Align.END, anchorY = Align.START)
@UIDataGuiscale(active = true)
@UIDataOverlay(active = true, render = @UIDataOverlayRender(post = true))
public class UIOverlayAncientHammerPowerEffect extends UI {
  public void init() {
    ItemAncientHammerPlayerData pData = ItemAncientHammerPlayerData.me();
    if (pData == null) {
      ZUI.close(this);
      return;
    } 
    BackgroundNode.create(1552.0D, 20.0D, 350.0D, 70.0D)
      .radius(5.0D, 15.0D)
      .body(bg -> {
          TextNode.create(bg.dw(2.0D), bg.dh(2.0D) - 20.0D).text(Text.create("Effet Power en cours", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 12.0F, Color.WHITE))).anchor(Align.CENTER).attach(bg);
          ((TextNode)TextNode.create(bg.dw(2.0D), bg.dh(2.0D)).text(Text.create("S'active dans " + (int)Math.max(0.0D, ((pData.getPowerEffect() - UniversalTimeUtils.now()) / 1000L)) + " secondes", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 12.0F, Color.WHITE))).onUpdate(())).anchor(Align.CENTER).attach(bg);
          TextNode.create(bg.dw(2.0D), bg.dh(2.0D) + 20.0D).text(Text.create("Activez manuellement avec un clic droit", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 12.0F, Color.WHITE))).anchor(Align.CENTER).attach(bg);
        }).anchorX(Align.END)
      .attach(this);
  }
  
  public void update() {
    ItemAncientHammerPlayerData pData = ItemAncientHammerPlayerData.me();
    if (pData == null) {
      ZUI.close(this);
      return;
    } 
    if (!pData.isPowerEffectActive())
      ZUI.close(this); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\ui\overlay\UIOverlayAncientHammerPowerEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */