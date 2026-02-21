package fr.paladium.palamod.modules.pvp.profile.gui;

import fr.paladium.paladiumui.kit.chart.RadarChartNode;
import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palamod.modules.pvp.dto.PvpStat;
import fr.paladium.palamod.modules.pvp.profile.dto.PlayerPvpData;
import fr.paladium.profile.client.gui.UIProfile;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.chart.RadarChartNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.MapSignal;
import net.minecraft.util.ResourceLocation;

public class UIProfilePvp extends UIProfile<PlayerPvpData> {
  private final MapSignal<String, PvpStat> mapStatSignal;
  
  public UIProfilePvp(AModule<?> module) {
    super(module);
    this.mapStatSignal = new MapSignal();
  }
  
  public void onPostInit() {
    PlayerPvpData data = (PlayerPvpData)getProfileData().getOrDefault();
    this.mapStatSignal.set(data.getStats());
    ((RadarChartNode)RadarChartNode.create(377.0D, 315.0D, 550.0D, 550.0D)
      .onInit(node -> {
          PvpStat stat = (PvpStat)this.mapStatSignal.get("global");
          if (stat == null)
            stat = new PvpStat("global"); 
          double damageDealtRatio = stat.getDamageDealt() / Math.max(1.0D, stat.getDamageTaken());
          double damageTakenRatio = stat.getDamageTaken() / Math.max(1.0D, stat.getDamageDealt());
          double killsRatio = (stat.getKills() / Math.max(1L, stat.getDeaths()));
          double deathsRatio = (stat.getDeaths() / Math.max(1L, stat.getKills()));
          node.getDataList().clear();
          node.data((RadarChartNode.RadarChartData)RadarChartNode.RadarChartData.create("Dommages Infligés / Dommages Subis", new ResourceLocation("palamod", "textures/gui/profile/pvp/combo.png"), Double.valueOf(Math.floor(damageDealtRatio * 100.0D) / 100.0D)));
          node.data((RadarChartNode.RadarChartData)RadarChartNode.RadarChartData.create("Morts / Tués", new ResourceLocation("palamod", "textures/gui/profile/pvp/death.png"), Double.valueOf(Math.floor(deathsRatio * 100.0D) / 100.0D)));
          node.data((RadarChartNode.RadarChartData)RadarChartNode.RadarChartData.create("Dommages Subis / Dommages Infligés", new ResourceLocation("palamod", "textures/gui/profile/pvp/killstreak.png"), Double.valueOf(Math.floor(damageTakenRatio * 100.0D) / 100.0D)));
          node.data((RadarChartNode.RadarChartData)RadarChartNode.RadarChartData.create("Tués / Morts", new ResourceLocation("palamod", "textures/gui/profile/pvp/kill.png"), Double.valueOf(Math.floor(killsRatio * 100.0D) / 100.0D)));
        })).watch((Signal)this.mapStatSignal)
      .attach((UI)this);
    TextNode.create(1343.0D, 353.0D)
      .text(Text.create("tués", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach((UI)this);
    TextNode.create(1470.0D, 353.0D)
      .text(Text.create("morts", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach((UI)this);
    TextNode.create(1593.0D, 353.0D)
      .text(Text.create("ratio", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach((UI)this);
    FlexNode.vertical(1105.0D, 445.0D, 549.0D)
      .margin(18.0D)
      .body(flex -> {
          String[] scopes = { "pvp", "koth", "totem" };
          for (String scope : scopes)
            RectNode.create(0.0D, 0.0D, flex.getWidth(), 78.0D).color(Color.BLACK.copyAlpha(0.2F)).body(()).attach(flex); 
        }).watch((Signal)this.mapStatSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach((UI)this);
    RectNode.create(1105.0D, 405.0D, 549.0D, 1.0D)
      .color(Color.WHITE)
      .attach((UI)this);
    RectNode.create(1280.0D, 333.0D, 1.0D, 383.0D)
      .color(Color.WHITE)
      .attach((UI)this);
    RectNode.create(1105.0D, 757.0D, 549.0D, 78.0D)
      .color(Color.BLACK.copyAlpha(0.2F))
      .body(rect -> {
          PvpStat stat = (PvpStat)this.mapStatSignal.get("global");
          if (stat == null)
            stat = new PvpStat("global"); 
          TextNode.create(25.0D, 28.0D).text(Text.create("série tués", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, Color.WHITE))).attach(rect);
          TextNode.create(238.0D, 18.0D).text(Text.create(String.valueOf(stat.getKillStreak()), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 20.0F, ColorConstant.PRIMARY))).anchorX(Align.CENTER).attach(rect);
          RectNode.create(300.0D, 15.0D, 1.0D, rect.ah(-30.0D)).color(Color.WHITE).attach(rect);
          TextNode.create(350.0D, 28.0D).text(Text.create("combo", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, Color.WHITE))).attach(rect);
          TextNode.create(488.0D, 18.0D).text(Text.create(String.valueOf(stat.getCombo()), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 20.0F, ColorConstant.PRIMARY))).anchorX(Align.CENTER).attach(rect);
        }).attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pvp\profile\gui\UIProfilePvp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */