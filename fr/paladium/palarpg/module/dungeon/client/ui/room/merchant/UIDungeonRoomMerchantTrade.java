package fr.paladium.palarpg.module.dungeon.client.ui.room.merchant;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palarpg.module.dungeon.client.ui.room.merchant.node.DungeonRoomMerchantTradeCardNode;
import fr.paladium.palarpg.module.dungeon.common.entity.room.merchant.EntityDungeonMerchant;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.BaseTween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

@UIData(background = false)
public class UIDungeonRoomMerchantTrade extends UI {
  private final UIDungeonRoomMerchantTradeData data;
  
  private final TweenAnimator appearAnimator;
  
  private final TweenAnimator cardAppearAnimator;
  
  public UIDungeonRoomMerchantTrade(@NonNull UIDungeonRoomMerchantTradeData data) {
    if (data == null)
      throw new NullPointerException("data is marked non-null but is null"); 
    this.data = data;
    this.appearAnimator = TweenAnimator.create(0.0F);
    this.cardAppearAnimator = TweenAnimator.create(0.0F);
  }
  
  public void init() {
    this.appearAnimator.setValue(0.0F);
    this.cardAppearAnimator.setValue(0.0F);
    this.appearAnimator.sequence(1000.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
    this.cardAppearAnimator.sequence(1000.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
    ((ImageNode)ImageNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/room/merchant/background.png")).linear().blocking())
      .color(Color.WHITE.copyAlpha(0.0F))
      .onAnimate((node, animator, value) -> node.color(Color.WHITE.copyAlpha(value))))
      .animate(this.appearAnimator)
      .attach(this);
    ((TextNode)TextNode.create(960.0D, 37.0D)
      .text(Text.create(this.data.isMephisto() ? "le pacte de mephisto" : "le marché de mammon", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 58.0F, Color.decode("#F6F9E8").copyAlpha(0.0F))))
      .onAnimate((node, animator, value) -> node.text(Text.create(this.data.isMephisto() ? "le pacte de mephisto" : "le marché de mammon", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 58.0F, Color.decode("#F6F9E8").copyAlpha(value))))))
      .animate(this.appearAnimator)
      .anchorX(Align.CENTER)
      .attach(this);
    ((TextNode)TextNode.create(960.0D, 181.0D)
      .text(Text.create(this.data.isMephisto() ? "Passez un pacte avec le grand Mephisto pour devenir plus puissant. Mais attention tout à un prix et chaque choix à ses conséquences..." : "Mammon est là pour vous dépouill.. pour vous faire de super échanges. à vos risques et perils...", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 14.0F, Color.decode("#6A6A6A").copyAlpha(0.0F))).horizontalAlign(Align.CENTER))
      .mode(TextMode.SPLIT)
      .onAnimate((node, animator, value) -> node.text(Text.create(this.data.isMephisto() ? "Passez un pacte avec le grand Mephisto pour devenir plus puissant. Mais attention tout à un prix et chaque choix à ses conséquences..." : "Mammon est là pour vous dépouill.. pour vous faire de super échanges. à vos risques et perils...", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 14.0F, Color.decode("#6A6A6A").copyAlpha(value))).horizontalAlign(Align.CENTER))))
      .animate(this.appearAnimator)
      .width(738.0D)
      .anchorX(Align.CENTER)
      .attach(this);
    FlexNode.horizontal(960.0D, 272.0D, 601.0D)
      .margin(45.0D)
      .body(flex -> {
          for (int i = 0; i < (this.data.getTrades()).length; i++)
            ((DungeonRoomMerchantTradeCardNode)DungeonRoomMerchantTradeCardNode.create(0.0D, ((i == 0) ? 'ڤ' : ((i == 1) ? 'ь' : 'ո')) - flex.getY(), 525.0D, 601.0D).data(i, this.data).onAnimate(())).animate(this.cardAppearAnimator).attach(flex); 
        }).anchorX(Align.CENTER)
      .attach(this);
  }
  
  public boolean close() {
    if (this.appearAnimator.getValue() != 1.0F)
      return false; 
    this.appearAnimator.sequence(500.0F, 0.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
    this.cardAppearAnimator.sequence(500.0F, 0.0F, (TweenEquation)TweenEquations.QUART_INOUT).setCallback(tween -> ZUI.close(this, true))
      
      .start();
    return false;
  }
  
  public static class UIDungeonRoomMerchantTradeData {
    private final int entityId;
    
    private final EntityDungeonMerchant.IEntityDungeonMerchantTrade[] trades;
    
    public UIDungeonRoomMerchantTradeData(int entityId, EntityDungeonMerchant.IEntityDungeonMerchantTrade[] trades) {
      this.entityId = entityId;
      this.trades = trades;
    }
    
    public int getEntityId() {
      return this.entityId;
    }
    
    public EntityDungeonMerchant.IEntityDungeonMerchantTrade[] getTrades() {
      return this.trades;
    }
    
    public boolean isMephisto() {
      return (this.trades.length > 0 && this.trades[0] instanceof EntityDungeonMerchant.EntityDungeonMerchantMephistoTrade);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\room\merchant\UIDungeonRoomMerchantTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */