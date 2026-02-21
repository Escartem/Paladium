package fr.paladium.palavote.client.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.ButtonNode;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palavote.common.action.PalaVoteServerAction;
import fr.paladium.palavote.common.data.PalaVotePlayer;
import fr.paladium.palavote.server.config.PalaVoteConfig;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import javax.vecmath.Vector4f;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class UIPalaVote extends UI {
  private final PalaVoteServerAction.PalaVoteData voteData;
  
  public PalaVoteServerAction.PalaVoteData getVoteData() {
    return this.voteData;
  }
  
  public UIPalaVote(@NonNull PalaVoteServerAction.PalaVoteData voteData) {
    if (voteData == null)
      throw new NullPointerException("voteData is marked non-null but is null"); 
    this.voteData = voteData;
  }
  
  public void init() {
    PalaVotePlayer player = PalaVotePlayer.get((Entity)(Minecraft.func_71410_x()).field_71439_g);
    String vote = player.getVote(this.voteData.getId());
    boolean voted = (vote != null);
    BackgroundNode.create(1850.0D, 1010.0D)
      .body(background -> {
          double width = background.getWidth() / (this.voteData.getChoices()).length;
          FlexNode.horizontal(0.0D, 0.0D, background.getHeight()).body(()).attach(background);
          ImageNode.create(background.dw(2.0D), 0.0D).resource(Resource.of(new ResourceLocation("palavote", "textures/gui/foreground.png"))).anchorX(Align.CENTER).attach(background);
          ImageNode.create(background.dw(2.0D), -20.0D).resource(Resource.of(this.voteData.getImage())).height(250.0D).anchorX(Align.CENTER).attach(background);
          TextNode.create(background.dw(2.0D), 220.0D).text(Text.create(this.voteData.getDescription(), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 18.0F, Color.WHITE), Align.CENTER)).mode(TextMode.SPLIT).width(1045.0D).anchorX(Align.CENTER).attach(background);
        }).attach(this);
  }
  
  public void drawBackground(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(0.0D, 0.0D, getWidth(), getHeight(), ColorConstant.BACKGROUND);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavote\clien\\ui\UIPalaVote.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */