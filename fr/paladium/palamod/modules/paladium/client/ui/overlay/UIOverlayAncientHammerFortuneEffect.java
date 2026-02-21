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
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

@UIData(background = false, anchorX = Align.END, anchorY = Align.START)
@UIDataGuiscale(active = true)
@UIDataOverlay(active = true, render = @UIDataOverlayRender(post = true))
public class UIOverlayAncientHammerFortuneEffect extends UI {
  public void init() {
    ItemAncientHammerPlayerData pData = ItemAncientHammerPlayerData.me();
    if (pData == null) {
      ZUI.close(this);
      return;
    } 
    BackgroundNode.create(1702.0D, 20.0D, 200.0D, 60.0D)
      .radius(5.0D, 15.0D)
      .body(bg -> {
          TextNode.create(bg.dw(2.0D), bg.dh(2.0D) - 10.0D).text(Text.create("Effet Fortune activé", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 12.0F, Color.WHITE))).anchor(Align.CENTER).attach(bg);
          ((TextNode)TextNode.create(bg.dw(2.0D), bg.dh(2.0D) + 10.0D).text(Text.create((int)Math.max(0.0D, ((pData.getFortuneEffect() - UniversalTimeUtils.now()) / 1000L)) + " secondes restantes", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 12.0F, Color.WHITE))).onUpdate(())).anchor(Align.CENTER).attach(bg);
        }).anchorX(Align.END)
      .attach(this);
  }
  
  public void update() {
    ItemAncientHammerPlayerData pData = ItemAncientHammerPlayerData.me();
    if (pData == null) {
      ZUI.close(this);
      return;
    } 
    if (!pData.isFortuneEffectActive()) {
      ZUI.close(this);
      (Minecraft.func_71410_x()).field_71439_g.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §bL'effet de fortune a pris fin."));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\ui\overlay\UIOverlayAncientHammerFortuneEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */