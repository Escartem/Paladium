package fr.paladium.palajobs.client.profile.gui.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.translate.common.texttotranslate.TTT;
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
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class ProfileRankNode extends Node {
  private static final Resource ALCHEMIST_ICON = Resource.of(new ResourceLocation("palajobs", "textures/gui/tokens/alchemist.png"));
  
  private static final Resource MINER_ICON = Resource.of(new ResourceLocation("palajobs", "textures/gui/tokens/miner.png"));
  
  private static final Resource HUNTER_ICON = Resource.of(new ResourceLocation("palajobs", "textures/gui/tokens/hunter.png"));
  
  private static final Resource FARMER_ICON = Resource.of(new ResourceLocation("palajobs", "textures/gui/tokens/farmer.png"));
  
  private static final Map<JobType, Resource> ICON_MAP = new HashMap<JobType, Resource>() {
    
    };
  
  private JobType job;
  
  private long rank;
  
  private int level;
  
  protected ProfileRankNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static ProfileRankNode create(double x, double y, double width, double height) {
    return new ProfileRankNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    RectNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .color(Color.BLACK.copyAlpha(0.1F))
      .body(rank -> {
          RectNode.create(rank.getWidth() - 112.0D, 0.0D, 112.0D, rank.getHeight()).color(Color.BLACK.copyAlpha(0.2F)).attach(rank);
          ImageNode.create(12.0D, rank.dh(2.0D) - 18.0D).resource(ICON_MAP.get(this.job)).height(36.0D).attach(rank);
          TextNode.create(63.0D, 4.0D).text(Text.create(TTT.format(this.job.getName(), new Object[0]), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 11.0F, Color.WHITE))).attach(rank);
          TextNode.create(63.0D, 15.0D).text(Text.create("lvl " + this.level, TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, Color.WHITE))).attach(rank);
          TextNode.create(214.0D, 15.0D).text(Text.create("rang".toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 15.0F, Color.WHITE))).attach(rank);
          TextNode.create(rank.getWidth() - 56.0D, 2.0D).text(Text.create((this.rank == -1L) ? "N/A" : Long.valueOf(this.rank), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 20.0F, Color.WHITE), Align.CENTER)).anchorX(Align.CENTER).attach(rank);
        }).attach(this);
  }
  
  public ProfileRankNode job(JobType job, long rank, int level) {
    this.job = job;
    this.rank = rank;
    this.level = level;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\profile\gui\node\ProfileRankNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */