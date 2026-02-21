package fr.paladium.palamod.modules.miner.dimminer.client.ui.entry;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.IconButtonNode;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.miner.PMiner;
import fr.paladium.palamod.modules.miner.dimminer.client.overlay.UIDimMinerOverlay;
import fr.paladium.palamod.modules.miner.dimminer.common.data.DimMinerPlayer;
import fr.paladium.palamod.modules.miner.dimminer.common.network.CSPacketDimMinerJoin;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.transition.Transition;
import fr.paladium.zephyrui.lib.ui.core.transition.impl.PopTransition;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import java.util.concurrent.TimeUnit;
import net.minecraft.util.ResourceLocation;

@UIData
public class UIDimMinerEntry extends UI {
  private static final long WAIT_TIME = 5000L;
  
  private final Resource BULLET_POINT_TEXTURE = Resource.of(new ResourceLocation("palamod", "textures/gui/miner/entry/bullet_point.png")).linear();
  
  private final DimMinerPlayer player;
  
  private final BooleanSignal waitSignal;
  
  public UIDimMinerEntry() {
    this.player = DimMinerPlayer.get();
    this.waitSignal = new BooleanSignal(false);
  }
  
  public void init() {
    setTransition((Transition)new PopTransition());
    if (this.player == null) {
      ZUI.close(this);
      return;
    } 
    BackgroundNode.create(929.0D, 657.0D)
      .attach(this);
    TextNode.create(960.0D, 223.0D)
      .text(Text.create("dimension mineur", PaladiumText.HEADER.copy().color(Color.decode("#CB3544")).shadow()))
      .anchorX(Align.CENTER)
      .attach(this);
    IconButtonNode.create(1380.0D, 235.0D, 14.0D)
      .icon(ResourceConstant.CLOSE)
      .onClick((node, mouseX, mouseY, clickType) -> ZUI.close(this))
      .attach(this);
    TextNode.create(960.0D, 348.0D)
      .text(Text.create("Plongez dans une dimension ou les minerais sont multipliés, uniquement reservée au joueurs aguéris.", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 20.0F, Color.WHITE.copyAlpha(0.8F)), Align.CENTER))
      .mode(TextMode.SPLIT)
      .anchorX(Align.CENTER)
      .width(723.0D)
      .attach(this);
    RectNode.create(560.0D, 438.0D, 800.0D, 154.0D)
      .color(Color.decode("#34343D").copyAlpha(0.4F))
      .body(rect -> {
          TextNode.create(20.0D, 20.0D).text(Text.create("Informations", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 27.0F, Color.WHITE))).attach(rect);
          TextNode.create(rect.aw(-20.0D), 20.0D).text(Text.create("Avant de rentrer dans la dimension, voici un récapitulatif de votre dimension.", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 10.0F, Color.WHITE.copyAlpha(0.8F)))).mode(TextMode.SPLIT).anchorX(Align.END).width(230.0D).attach(rect);
          FlexNode.horizontal(20.0D, 95.0D, 22.0D).align(Align.CENTER).margin(22.0D).body(()).attach(rect);
          FlexNode.horizontal(rect.aw(-20.0D), 95.0D, 22.0D).align(Align.CENTER).margin(22.0D).body(()).anchorX(Align.END).attach(rect);
        }).attach(this);
    FlexNode.horizontal(560.0D, 603.0D, 3.0D)
      .margin(13.0D)
      .body(flex -> {
          Color color = Color.decode("#CB3544");
          RectNode.create(0.0D, 0.0D, 35.0D, flex.getHeight()).color(color).attach(flex);
          RectNode.create(0.0D, 0.0D, 13.0D, flex.getHeight()).color(color).attach(flex);
          RectNode.create(0.0D, 0.0D, 35.0D, flex.getHeight()).color(color).attach(flex);
          RectNode.create(0.0D, 0.0D, 7.0D, flex.getHeight()).color(color).attach(flex);
          RectNode.create(0.0D, 0.0D, 23.0D, flex.getHeight()).color(color).attach(flex);
          RectNode.create(0.0D, 0.0D, 21.0D, flex.getHeight()).color(color).attach(flex);
          RectNode.create(0.0D, 0.0D, 375.0D, flex.getHeight()).color(color).attach(flex);
          RectNode.create(0.0D, 0.0D, 21.0D, flex.getHeight()).color(color).attach(flex);
          RectNode.create(0.0D, 0.0D, 23.0D, flex.getHeight()).color(color).attach(flex);
          RectNode.create(0.0D, 0.0D, 7.0D, flex.getHeight()).color(color).attach(flex);
          RectNode.create(0.0D, 0.0D, 35.0D, flex.getHeight()).color(color).attach(flex);
          RectNode.create(0.0D, 0.0D, 13.0D, flex.getHeight()).color(color).attach(flex);
          RectNode.create(0.0D, 0.0D, 35.0D, flex.getHeight()).color(color).attach(flex);
        }).attach(this);
    RectNode.create(560.0D, 618.0D, 800.0D, 162.0D)
      .color(Color.decode("#34343D").copyAlpha(0.4F))
      .body(rect -> {
          TextNode.create(20.0D, 20.0D).text(Text.create("Votre barre", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 27.0F, Color.WHITE))).attach(rect);
          TextNode.create(rect.aw(-20.0D), 20.0D).text(Text.create("Toutes les informations sur la durées et vos points restants y seront affichées.", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 10.0F, Color.WHITE.copyAlpha(0.8F)))).mode(TextMode.SPLIT).anchorX(Align.END).width(230.0D).attach(rect);
          ImageNode.create(20.0D, 92.0D, rect.aw(-40.0D), 29.0D).resource(UIDimMinerOverlay.EMPTY_TEXTURE).attach(rect);
          if (this.player.getPoints() > 0L)
            ImageNode.create(20.0D, 92.0D, rect.aw(-40.0D) * ((float)this.player.getPoints() / 1000.0F), 29.0D).resource(UIDimMinerOverlay.FILL_TEXTURE).attach(rect); 
          ImageNode.create(20.0D, 92.0D, rect.aw(-40.0D), 29.0D).resource(UIDimMinerOverlay.FOREGROUND_TEXTURE).attach(rect);
        }).attach(this);
    ((TextButtonNode)TextButtonNode.create(678.0D, 805.0D)
      .text(this.player.isActive() ? "Rejoindre la dimension" : "Créer la dimension")
      .onInit(node -> {
          node.text(node.isEnabled() ? (this.player.isActive() ? "Rejoindre la dimension" : "Créer la dimension") : "Connexion en cours...");
          node.width(564.0D);
        })).onClick((node, mouseX, mouseY, clickType) -> {
          this.waitSignal.set(Boolean.valueOf(true));
          PMiner.network.sendToServer((IMessage)new CSPacketDimMinerJoin());
          (new Thread((), "UIDimMinerEntry/WaitSignal")).start();
        }).enabled(node -> !((Boolean)this.waitSignal.getOrDefault()).booleanValue())
      .watch((Signal)this.waitSignal)
      .attach(this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\clien\\ui\entry\UIDimMinerEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */