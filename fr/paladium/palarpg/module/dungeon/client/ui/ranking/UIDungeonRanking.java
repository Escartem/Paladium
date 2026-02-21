package fr.paladium.palarpg.module.dungeon.client.ui.ranking;

import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palaforgeutils.lib.time.UniversalTimeUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundElementNode;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundNode;
import fr.paladium.palarpg.module.dungeon.client.ui.ranking.node.DungeonRankingGlobalNode;
import fr.paladium.palarpg.module.dungeon.client.ui.ranking.node.DungeonRankingWeeklyNode;
import fr.paladium.palarpg.module.dungeon.common.network.global.BBPacketRPGDungeonGlobalGetDungeons;
import fr.paladium.palarpg.module.dungeon.common.ranking.DungeonRankingData;
import fr.paladium.palarpg.module.dungeon.common.ranking.DungeonRankingDataList;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonConfig;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonRankingConfig;
import fr.paladium.palarpg.module.dungeon.server.manager.DungeonRankingManager;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.debug.UIDataDebug;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.head.HeadNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.vecmath.Vector4f;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

@UIData(background = false)
@UIDataDebug(hotreload = false)
public class UIDungeonRanking extends UI {
  private Page page;
  
  private DungeonRankingConfig config;
  
  private List<DungeonConfig> dungeons;
  
  public void init() {
    BackgroundNode.create()
      .attach(this);
    if (this.dungeons == null) {
      this.page = Page.MAIN;
      (new BBPacketRPGDungeonGlobalGetDungeons()).subscribe(reply -> DungeonRankingManager.getConfig().thenAccept(()))
        
        .send();
    } 
  }
  
  private void initMain() {
    if (this.page != Page.MAIN) {
      this.page = Page.MAIN;
      reload();
    } 
    TextNode.create(62.0D, 37.0D)
      .text(Text.create("classement", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 58.0F, Color.WHITE)))
      .attach(this);
    RectNode.create(62.0D, 174.0D, 1796.0D, 133.0D)
      .color(Color.decode("#171717"))
      .attach(this);
    TextNode.create(94.0D, 204.0D)
      .text(Text.create("classement hebdomadaire", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 24.0F, Color.WHITE)))
      .attach(this);
    ((TextNode)TextNode.create(94.0D, 235.0D)
      .text(Text.create("chargement", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 24.0F, Color.decode("#6A6A6A"))))
      .onUpdate(node -> DungeonRankingManager.getWeeklyResetDate().thenAccept(())))
      
      .attach(this);
    FlexNode.horizontal(528.0D, 209.0D, 57.0D)
      .margin(9.0D)
      .body(flex -> DungeonRankingManager.getWeekly(Integer.valueOf(0)).thenAccept(()))
      
      .attach(this);
    TextButtonNode.create(1601.0D, 211.0D)
      .text("voir")
      .onClick((node, mouseX, mouseY, clickType) -> {
          initWeekly();
          SoundUtils.CLICK.playClientSound(1.0F, 1.0F);
        }).size(224.0D, 54.0D)
      .attach(this);
    ContainerNode.create(28.0D, 0.0D, 1864.0D, 1080.0D)
      .overflow(OverflowProperty.SCROLL)
      .body(container -> FlexNode.horizontal(34.0D, 320.0D, 701.0D).margin(24.0D).body(()).attach(container))
      
      .attach(this);
  }
  
  private void initWeekly() {
    if (this.page != Page.WEEKLY) {
      this.page = Page.WEEKLY;
      reload();
    } 
    ImageNode.create(1817.0D, 70.0D, 35.0D, 35.0D)
      .resource(ResourceConstant.BACK)
      .linear(false)
      .onClick((node, mouseX, mouseY, clickType) -> {
          initMain();
          SoundUtils.CLICK.playClientSound(1.0F, 1.0F);
        }).attach(this);
    TextNode.create(62.0D, 37.0D)
      .text(Text.create("classement hebdomadaire", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 58.0F, Color.WHITE)))
      .attach(this);
    TextNode.create(1144.0D, 66.0D)
      .text(Text.create("fin dans", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 24.0F, Color.WHITE)))
      .attach(this);
    ((TextNode)TextNode.create(1144.0D, 102.0D)
      .text(Text.create("chargement", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 24.0F, Color.decode("#6A6A6A"))))
      .onUpdate(node -> DungeonRankingManager.getWeeklyResetDate().thenAccept(())))
      
      .attach(this);
    RectNode.create(70.0D, 171.0D, 1780.0D, 8.0D)
      .color(Color.decode("#1F1F1F"))
      .attach(this);
    RectNode.create(70.0D, 268.0D, 1780.0D, 8.0D)
      .color(Color.decode("#1F1F1F"))
      .attach(this);
    RectNode.create(62.0D, 180.0D, 8.0D, 89.0D)
      .color(Color.decode("#1F1F1F"))
      .attach(this);
    RectNode.create(1850.0D, 180.0D, 8.0D, 89.0D)
      .color(Color.decode("#1F1F1F"))
      .attach(this);
    RectNode.create(62.0D, 188.0D, 1796.0D, 8.0D)
      .color(BackgroundNode.BACKGROUND_COLOR)
      .attach(this);
    RectNode.create(62.0D, 252.0D, 1796.0D, 8.0D)
      .color(BackgroundNode.BACKGROUND_COLOR)
      .attach(this);
    RectNode.create(78.0D, 171.0D, 8.0D, 105.0D)
      .color(BackgroundNode.BACKGROUND_COLOR)
      .attach(this);
    RectNode.create(1834.0D, 171.0D, 8.0D, 105.0D)
      .color(BackgroundNode.BACKGROUND_COLOR)
      .attach(this);
    TextNode.create(152.0D, 209.0D)
      .text(Text.create("rang", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 20.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach(this);
    TextNode.create(260.0D, 209.0D)
      .text(Text.create("joueur", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 20.0F, Color.WHITE)))
      .attach(this);
    TextNode.create(1550.0D, 209.0D)
      .text(Text.create("récompenses", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 20.0F, Color.WHITE)))
      .anchorX(Align.END)
      .attach(this);
    TextNode.create(1592.0D, 209.0D)
      .text(Text.create("xp récoltée", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 20.0F, Color.WHITE)).horizontalAlign(Align.CENTER))
      .width(217.0D)
      .attach(this);
    FlexNode.horizontal(960.0D, 197.0D, 54.0D)
      .margin(43.0D)
      .align(Align.CENTER)
      .body(flex -> DungeonRankingManager.getWeeklyPlayer(UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g)).thenAccept(()))
      
      .anchorX(Align.CENTER)
      .attach(this);
    AtomicBoolean loading = new AtomicBoolean(false);
    AtomicReference<DungeonRankingData> previous = new AtomicReference<>(null);
    AtomicReference<DungeonRankingDataList> currentList = new AtomicReference<>(null);
    ContainerNode.create(0.0D, 298.0D, 1920.0D, 754.0D)
      .overflow(OverflowProperty.SCROLL)
      .scrollbar((ScrollbarNode)ScrollbarNode.create(1879.0D, 0.0D, 737.0D, 3.0D))
      .body(scroll -> FlexNode.vertical(scroll.dw(2.0D) - 899.0D, 0.0D, 1798.0D).margin(13.0D).body(()).attach(scroll))
      
      .onScrollEnd((node, scrollX, scrollY) -> {
          if (loading.get() || currentList.get() == null || ((DungeonRankingDataList)currentList.get()).isEnd())
            return; 
          loading.set(true);
          FlexNode flex = (FlexNode)node.getChild(0, FlexNode.class);
          DungeonRankingManager.getWeekly(Integer.valueOf(((DungeonRankingDataList)currentList.get()).getIndex() + 1)).thenAccept(());
        }).attach(this);
    RectNode.create(0.0D, 871.0D, 1920.0D, 181.0D)
      .color(Color.gradient(Color.decode("#0E0E0E").copyAlpha(0.0F), Color.decode("#0E0E0E"), new Vector4f(0.0F, 0.0F, 0.0F, 1.0F)))
      .attach(this);
  }
  
  private void initGlobal(@NonNull DungeonConfig dungeon) {
    if (dungeon == null)
      throw new NullPointerException("dungeon is marked non-null but is null"); 
    if (this.page != Page.GLOBAL) {
      this.page = Page.GLOBAL;
      reload();
    } 
    ImageNode.create(1817.0D, 70.0D, 35.0D, 35.0D)
      .resource(ResourceConstant.BACK)
      .linear(false)
      .onClick((node, mouseX, mouseY, clickType) -> {
          initMain();
          SoundUtils.CLICK.playClientSound(1.0F, 1.0F);
        }).attach(this);
    TextNode.create(62.0D, 37.0D)
      .text(Text.create("classement", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 58.0F, Color.WHITE)))
      .attach(this);
    BackgroundElementNode.create(575.0D, 65.0D, 443.0D, 64.0D)
      .color(dungeon.getColor())
      .shadowColor(dungeon.getShadowColor())
      .shadow(0.0F, 4.0F)
      .cornerSize(4.0D)
      .body(background -> {
          TextNode.create(36.0D, background.dh(2.0D)).text(Text.create(dungeon.getName(), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 32.0F, Color.WHITE))).anchorY(Align.CENTER).attach(background);
          ImageNode.create(background.aw(-23.0D), background.dh(2.0D)).resource(Resource.of(dungeon.getIconWhite()).nearest()).height(30.0D).anchorX(Align.END).anchorY(Align.CENTER).attach(background);
        }).attach(this);
    if (dungeon.getTag() != null && !dungeon.getTag().isEmpty()) {
      Text text = Text.create(dungeon.getTag(), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 14.0F, Color.WHITE));
      BackgroundElementNode.create(612.0D, 123.0D, text.aw(20.0D), 24.0D)
        .cornerSize(4.0D)
        .color(dungeon.getShadowColor())
        .body(element -> TextNode.create(element.dw(2.0D), element.dh(2.0D)).text(text).anchor(Align.CENTER).attach(element))
        
        .attach(this);
    } 
    RectNode.create(70.0D, 171.0D, 1780.0D, 8.0D)
      .color(Color.decode("#1F1F1F"))
      .attach(this);
    RectNode.create(70.0D, 268.0D, 1780.0D, 8.0D)
      .color(Color.decode("#1F1F1F"))
      .attach(this);
    RectNode.create(62.0D, 180.0D, 8.0D, 89.0D)
      .color(Color.decode("#1F1F1F"))
      .attach(this);
    RectNode.create(1850.0D, 180.0D, 8.0D, 89.0D)
      .color(Color.decode("#1F1F1F"))
      .attach(this);
    RectNode.create(62.0D, 188.0D, 1796.0D, 8.0D)
      .color(BackgroundNode.BACKGROUND_COLOR)
      .attach(this);
    RectNode.create(62.0D, 252.0D, 1796.0D, 8.0D)
      .color(BackgroundNode.BACKGROUND_COLOR)
      .attach(this);
    RectNode.create(78.0D, 171.0D, 8.0D, 105.0D)
      .color(BackgroundNode.BACKGROUND_COLOR)
      .attach(this);
    RectNode.create(1834.0D, 171.0D, 8.0D, 105.0D)
      .color(BackgroundNode.BACKGROUND_COLOR)
      .attach(this);
    TextNode.create(152.0D, 209.0D)
      .text(Text.create("rang", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 20.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach(this);
    TextNode.create(260.0D, 209.0D)
      .text(Text.create("joueur", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 20.0F, Color.WHITE)))
      .attach(this);
    TextNode.create(1809.0D, 209.0D)
      .text(Text.create("palier atteint", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 20.0F, Color.WHITE)))
      .anchorX(Align.END)
      .attach(this);
    FlexNode.horizontal(960.0D, 197.0D, 54.0D)
      .margin(43.0D)
      .align(Align.CENTER)
      .body(flex -> DungeonRankingManager.getWeeklyPlayer(UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g)).thenAccept(()))
      
      .anchorX(Align.CENTER)
      .attach(this);
    AtomicBoolean loading = new AtomicBoolean(false);
    AtomicReference<DungeonRankingDataList> currentList = new AtomicReference<>(null);
    ContainerNode.create(0.0D, 298.0D, 1920.0D, 750.0D)
      .overflow(OverflowProperty.SCROLL)
      .scrollbar((ScrollbarNode)ScrollbarNode.create(1879.0D, 0.0D, 737.0D, 3.0D))
      .body(scroll -> FlexNode.vertical(scroll.dw(2.0D) - 899.0D, 0.0D, 1798.0D).margin(13.0D).body(()).attach(scroll))
      
      .onScrollEnd((node, scrollX, scrollY) -> {
          if (loading.get() || currentList.get() == null || ((DungeonRankingDataList)currentList.get()).isEnd())
            return; 
          loading.set(true);
          FlexNode flex = (FlexNode)node.getChild(0, FlexNode.class);
          DungeonRankingManager.getGlobal(dungeon.getId(), Integer.valueOf(((DungeonRankingDataList)currentList.get()).getIndex() + 1)).thenAccept(());
        }).attach(this);
    RectNode.create(0.0D, 871.0D, 1920.0D, 181.0D)
      .color(Color.gradient(Color.decode("#0E0E0E").copyAlpha(0.0F), Color.decode("#0E0E0E"), new Vector4f(0.0F, 0.0F, 0.0F, 1.0F)))
      .attach(this);
  }
  
  public boolean close() {
    if (this.page != Page.MAIN) {
      initMain();
      return false;
    } 
    return true;
  }
  
  private enum Page {
    MAIN, WEEKLY, GLOBAL;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\ranking\UIDungeonRanking.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */