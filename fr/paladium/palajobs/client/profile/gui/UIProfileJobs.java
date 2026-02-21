package fr.paladium.palajobs.client.profile.gui;

import fr.paladium.paladiumui.kit.chart.ChartNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.selector.SelectorNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palajobs.client.profile.gui.node.ProfileJobNode;
import fr.paladium.palajobs.client.profile.gui.node.ProfileRankNode;
import fr.paladium.palajobs.core.profile.dto.PlayerJob;
import fr.paladium.palajobs.core.profile.dto.PlayerJobData;
import fr.paladium.profile.client.gui.UIProfile;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.chart.ChartNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.selector.SelectorNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.format.FormatUtils;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class UIProfileJobs extends UIProfile<PlayerJobData> {
  private StringSignal chartSortSignal;
  
  public UIProfileJobs(AModule<?> module) {
    super(module);
  }
  
  public void onPostInit() {
    PlayerJobData data = (PlayerJobData)getProfileData().getOrDefault();
    PlayerJob miner = (data.getCurrent().getMiner() == null) ? new PlayerJob(0, 0.0D) : data.getCurrent().getMiner();
    PlayerJob farmer = (data.getCurrent().getFarmer() == null) ? new PlayerJob(0, 0.0D) : data.getCurrent().getFarmer();
    PlayerJob hunter = (data.getCurrent().getHunter() == null) ? new PlayerJob(0, 0.0D) : data.getCurrent().getHunter();
    PlayerJob alchemist = (data.getCurrent().getAlchemist() == null) ? new PlayerJob(0, 0.0D) : data.getCurrent().getAlchemist();
    RectNode.create(271.0D, 277.0D, 435.0D, 345.0D)
      .color(new Color(27, 26, 31))
      .body(rect -> {
          TextNode.create(113.0D, 10.0D).text(Text.create(TTT.format(JobType.MINER.getName(), new Object[0]), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, Color.WHITE), Align.CENTER)).anchorX(Align.CENTER).attach(rect);
          ProfileJobNode.create(53.0D, 43.0D, 120.0D).job(JobType.MINER, miner.getLevel(), miner.getXp()).attach(rect);
          TextNode.create(322.0D, 10.0D).text(Text.create(TTT.format(JobType.FARMER.getName(), new Object[0]), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, Color.WHITE), Align.CENTER)).anchorX(Align.CENTER).attach(rect);
          ProfileJobNode.create(262.0D, 43.0D, 120.0D).job(JobType.FARMER, farmer.getLevel(), farmer.getXp()).attach(rect);
          TextNode.create(113.0D, 174.0D).text(Text.create(TTT.format(JobType.HUNTER.getName(), new Object[0]), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, Color.WHITE), Align.CENTER)).anchorX(Align.CENTER).attach(rect);
          ProfileJobNode.create(53.0D, 207.0D, 120.0D).job(JobType.HUNTER, hunter.getLevel(), hunter.getXp()).attach(rect);
          TextNode.create(322.0D, 174.0D).text(Text.create(TTT.format(JobType.ALCHEMIST.getName(), new Object[0]), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, Color.WHITE), Align.CENTER)).anchorX(Align.CENTER).attach(rect);
          ProfileJobNode.create(262.0D, 207.0D, 120.0D).job(JobType.ALCHEMIST, alchemist.getLevel(), alchemist.getXp()).attach(rect);
        }).attach((UI)this);
    RectNode.create(271.0D, 645.0D, 435.0D, 307.0D)
      .color(new Color(27, 26, 31))
      .body(rect -> {
          TextNode.create(30.0D, 17.0D).text(Text.create("classement métiers", PaladiumText.TITLE)).attach(rect);
          ProfileRankNode.create(26.0D, 85.0D, 384.0D, 45.0D).job(JobType.MINER, data.getRank().getMiner(), miner.getLevel()).attach(rect);
          ProfileRankNode.create(26.0D, 138.0D, 384.0D, 45.0D).job(JobType.FARMER, data.getRank().getFarmer(), farmer.getLevel()).attach(rect);
          ProfileRankNode.create(26.0D, 190.0D, 384.0D, 45.0D).job(JobType.HUNTER, data.getRank().getHunter(), hunter.getLevel()).attach(rect);
          ProfileRankNode.create(26.0D, 242.0D, 384.0D, 45.0D).job(JobType.ALCHEMIST, data.getRank().getAlchemist(), alchemist.getLevel()).attach(rect);
        }).attach((UI)this);
    RectNode.create(729.0D, 277.0D, 923.0D, 675.0D)
      .color(new Color(27, 26, 31))
      .body(rect -> {
          TextNode.create(29.0D, 36.0D).text(Text.create("statistiques", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, Color.WHITE))).attach(rect);
          SelectorNode.create(609.0D, 21.0D, 287.0D).onChange(()).body(()).attach(rect);
          ContainerNode.create(0.0D, 330.0D, rect.getWidth(), 345.0D).body(()).watch((Signal)this.chartSortSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(rect);
        }).attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\profile\gui\UIProfileJobs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */