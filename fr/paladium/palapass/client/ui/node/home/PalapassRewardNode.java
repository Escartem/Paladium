package fr.paladium.palapass.client.ui.node.home;

import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palapass.client.ui.UIPalapass;
import fr.paladium.palapass.client.ui.node.PalapassIconNode;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.pojo.reward.EnumRewardType;
import fr.paladium.palapass.common.pojo.reward.RewardLevel;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class PalapassRewardNode extends Node {
  private static final Map<RewardState, ResourceLocation> BACKGROUND_TEXTURES = new HashMap<>();
  
  private static final Map<RewardState, ResourceLocation> FOREGROUND_TEXTURES = new HashMap<>();
  
  private int index;
  
  private RewardLevel reward;
  
  private RewardState state;
  
  static {
    BACKGROUND_TEXTURES.put(RewardState.AVAILABLE, new ResourceLocation("palapass", "textures/gui/home/reward/available/background.png"));
    BACKGROUND_TEXTURES.put(RewardState.CLAIMED, new ResourceLocation("palapass", "textures/gui/home/reward/claimed/background.png"));
    BACKGROUND_TEXTURES.put(RewardState.FOCUSED, new ResourceLocation("palapass", "textures/gui/home/reward/focused/background.png"));
    BACKGROUND_TEXTURES.put(RewardState.FOCUSED_DISABLED, new ResourceLocation("palapass", "textures/gui/home/reward/focused_disabled/background.png"));
    BACKGROUND_TEXTURES.put(RewardState.FOCUSED_CLAIMED, new ResourceLocation("palapass", "textures/gui/home/reward/focused_claimed/background.png"));
    BACKGROUND_TEXTURES.put(RewardState.LOCKED, new ResourceLocation("palapass", "textures/gui/home/reward/locked/background.png"));
    FOREGROUND_TEXTURES.put(RewardState.CLAIMED, new ResourceLocation("palapass", "textures/gui/home/reward/claimed/foreground.png"));
    FOREGROUND_TEXTURES.put(RewardState.FOCUSED_CLAIMED, new ResourceLocation("palapass", "textures/gui/home/reward/claimed/foreground.png"));
  }
  
  protected PalapassRewardNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static PalapassRewardNode create(double x, double y, double width, double height) {
    return new PalapassRewardNode(x, y, width, height);
  }
  
  public void init(UI ui) {
    boolean premium = (this.reward.getType() == EnumRewardType.PREMIUM);
    TextNode.create(dw(2.0D), -60.0D)
      .text(Text.create(premium ? PalapassTranslateEnum.PREMIUM.textOrDefault("premium") : PalapassTranslateEnum.FREE.textOrDefault("gratuit"), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 20.0F).color(premium ? ColorConstant.PRIMARY : ColorConstant.LIGHT_GRAY).shadow().shadow(0.0F, 2.0F), Align.CENTER))
      .anchorX(Align.CENTER)
      .attach(this);
    ImageNode.create(0.0D, 0.0D)
      .resource(BACKGROUND_TEXTURES.get(this.state))
      .size(getWidth(), getHeight())
      .attach(this);
    double iconSize = dw(2.2D);
    PalapassIconNode.create(dw(2.0D) - iconSize / 2.0D, 63.0D, iconSize)
      .icon(this.reward.getIcon())
      .attach(this);
    TextNode.create(dw(2.0D), 63.0D + iconSize - 25.0D)
      .text(Text.create("x" + this.reward.getQuantity(), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 19.0F).color(Color.WHITE), Align.CENTER))
      .anchorX(Align.CENTER)
      .zlevel(50.0D)
      .attach(this);
    if (FOREGROUND_TEXTURES.containsKey(this.state))
      ImageNode.create(0.0D, 0.0D)
        .resource(FOREGROUND_TEXTURES.get(this.state))
        .linear(false)
        .size(getWidth(), getHeight())
        .zlevel(50.0D)
        .attach(this); 
    TextNode.create(dw(2.0D), ah((this.state == RewardState.FOCUSED || this.state == RewardState.FOCUSED_DISABLED || this.state == RewardState.FOCUSED_CLAIMED) ? -41.0D : -58.0D))
      .text(Text.create(Integer.valueOf(this.index + 1), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 19.0F, Color.WHITE), Align.CENTER))
      .anchorX(Align.CENTER)
      .attach(this);
    if (premium && !((UIPalapass)getUi()).getPlayerData().isPremium())
      ImageNode.create(aw(-50.0D), 27.0D)
        .resource(UIPalapass.LOCK_TEXTURE)
        .linear(false)
        .size(21.0D, 25.0D)
        .attach(this); 
  }
  
  public PalapassRewardNode data(int index, RewardLevel reward, RewardState state) {
    this.index = index;
    this.reward = reward;
    this.state = state;
    return this;
  }
  
  public enum RewardState {
    AVAILABLE, CLAIMED, FOCUSED, FOCUSED_DISABLED, FOCUSED_CLAIMED, LOCKED;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\clien\\ui\node\home\PalapassRewardNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */