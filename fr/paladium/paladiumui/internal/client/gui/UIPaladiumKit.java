package fr.paladium.paladiumui.internal.client.gui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.bookmark.BookmarkNode;
import fr.paladium.paladiumui.kit.button.HexaButtonNode;
import fr.paladium.paladiumui.kit.button.PriceButtonNode;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.button.impl.ArrowButtonNode;
import fr.paladium.paladiumui.kit.button.impl.BackButtonNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.button.impl.HelpButtonNode;
import fr.paladium.paladiumui.kit.button.impl.SearchButtonNode;
import fr.paladium.paladiumui.kit.chart.ChartNode;
import fr.paladium.paladiumui.kit.chart.RadarChartNode;
import fr.paladium.paladiumui.kit.checkbox.CheckboxNode;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.paladiumui.kit.popup.UIPopup;
import fr.paladium.paladiumui.kit.popup.UIYesNoPopup;
import fr.paladium.paladiumui.kit.progress.ProgressNode;
import fr.paladium.paladiumui.kit.selector.SelectorNode;
import fr.paladium.paladiumui.kit.slider.SliderNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.sw.SwitchNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.paladiumui.kit.textfield.SearchTextFieldNode;
import fr.paladium.paladiumui.kit.textfield.TextFieldNode;
import fr.paladium.paladiumui.kit.toggle.ToggleNode;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.chart.ChartNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.chart.RadarChartNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;

public class UIPaladiumKit extends UI {
  public void init() {
    BackgroundNode.create().attach(this);
    TextNode.create(45.0D, 50.0D).text(Text.create("Lorem Ipsum", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_THIN, 30.0F).color(Color.WHITE))).attach(this);
    TextNode.create(45.0D, 80.0D).text(Text.create("Lorem Ipsum", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_EXTRA_LIGHT, 30.0F).color(Color.WHITE))).attach(this);
    TextNode.create(45.0D, 110.0D).text(Text.create("Lorem Ipsum", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_LIGHT, 30.0F).color(Color.WHITE))).attach(this);
    TextNode.create(45.0D, 140.0D).text(Text.create("Lorem Ipsum", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_REGULAR, 30.0F).color(Color.WHITE))).attach(this);
    TextNode.create(45.0D, 170.0D).text(Text.create("Lorem Ipsum", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 30.0F).color(Color.WHITE))).attach(this);
    TextNode.create(45.0D, 200.0D).text(Text.create("Lorem Ipsum", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 30.0F).color(Color.WHITE))).attach(this);
    TextNode.create(45.0D, 230.0D).text(Text.create("Lorem Ipsum", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 30.0F).color(Color.WHITE))).attach(this);
    TextNode.create(45.0D, 260.0D).text(Text.create("Lorem Ipsum", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_EXTRA_BOLD, 30.0F).color(Color.WHITE))).attach(this);
    TextNode.create(45.0D, 290.0D).text(Text.create("Lorem Ipsum", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BLACK, 30.0F).color(Color.WHITE))).attach(this);
    TextNode.create(45.0D, 320.0D).text(Text.create("Lorem Ipsum", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 30.0F).color(Color.WHITE))).attach(this);
    TextNode.create(45.0D, 350.0D).text(Text.create("Lorem Ipsum", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 30.0F).color(Color.WHITE))).attach(this);
    TextNode.create(45.0D, 380.0D).text(Text.create("Lorem Ipsum", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 30.0F).color(Color.WHITE))).attach(this);
    PriceButtonNode.create(50.0D, 470.0D, 382.0D, 57.0D)
      .price(1000L)
      .title("en profiter")
      .description("50% de réduction")
      .attach(this);
    TextButtonNode.create(50.0D, 550.0D)
      .text("pop up")
      .onClick((node, mouseX, mouseY, clickType) -> ZUI.open((UI)new UIPopup("ceci est un titre")))
      .attach(this);
    TextButtonNode.create(245.0D, 550.0D)
      .text("yes-no")
      .icon(ResourceConstant.SEARCH)
      .iconMargin(10.0D)
      .iconSize(20.0D)
      .onClick((node, mouseX, mouseY, clickType) -> {
          UIYesNoPopup uIYesNoPopup;
          ZUI.open((UI)(uIYesNoPopup = (new UIYesNoPopup("ceci est un titre", "ceci est une très longue description qui ne devrait pas être affichée sur une seule ligne, mais bien sur plusieurs lignes")).yesCallback(()).noCallback(())));
          uIYesNoPopup.getData().setZlevel(10.0D);
        }).width(200.0D)
      .attach(this);
    TextButtonNode.create(50.0D, 620.0D)
      .text("button")
      .enabled(node -> false)
      .attach(this);
    TextButtonNode.create(245.0D, 620.0D)
      .text("button")
      .icon(ResourceConstant.SEARCH)
      .iconMargin(10.0D)
      .iconSize(20.0D)
      .iconPosition(TextButtonNode.IconPosition.LEFT)
      .width(200.0D)
      .enabled(node -> false)
      .attach(this);
    CloseButtonNode.create(50.0D, 700.0D).attach(this);
    SearchButtonNode.create(90.0D, 700.0D).attach(this);
    BackButtonNode.create(130.0D, 700.0D).attach(this);
    HelpButtonNode.create(170.0D, 700.0D).attach(this);
    ArrowButtonNode.Left.create(210.0D, 700.0D, 24.0D).enabled(node -> false).attach(this);
    ArrowButtonNode.Right.create(250.0D, 700.0D, 24.0D).attach(this);
    ImageNode.create(50.0D, 750.0D).resource(ResourceConstant.ARROW_DOWN).linear(false).width(25.0D).attach(this);
    ImageNode.create(90.0D, 750.0D).resource(ResourceConstant.ARROW_UP).linear(true).width(25.0D).attach(this);
    ImageNode.create(130.0D, 750.0D).resource(ResourceConstant.MONEY).linear(false).width(25.0D).attach(this);
    HexaButtonNode.create(50.0D, 800.0D).icon(ResourceConstant.ARROW_DOWN).attach(this);
    HexaButtonNode.create(110.0D, 796.0D).icon(ResourceConstant.ARROW_UP).border(true).attach(this);
    ProgressNode.create(50.0D, 870.0D, 300.0D).color(new Color(163, 57, 255)).progress(0.0F, 10.0F, 5.0F).attach(this);
    ProgressNode.create(50.0D, 938.0D, 300.0D).color(new Color(163, 57, 255)).progress(0.0F, 10.0F, 5.0F).height(30.0D).attach(this);
    ProgressNode.create(50.0D, 973.0D, 300.0D).color(new Color(163, 57, 255)).progress(0.0F, 10.0F, 5.0F).padding(5.0D).height(30.0D).attach(this);
    CheckboxNode.create(400.0D, 750.0D, 33.0D).attach(this);
    SliderNode.create(400.0D, 820.0D, 300.0D, 53.0D)
      .text("value: %value%")
      .valueSet(new LinkedHashSet(Arrays.asList((Object[])new Integer[] { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9) }, )), Integer.valueOf(1))
      .attach(this);
    ToggleNode.create(400.0D, 900.0D, 89.0D).state("on", "off").attach(this);
    ToggleNode.create(400.0D, 970.0D, 89.0D).state("on", "off").toggle(true).attach(this);
    ToggleNode.create(520.0D, 900.0D, 89.0D).backgroundColor(Color.decode("#751910"), Color.decode("#676767")).state("oui", "non").attach(this);
    ToggleNode.create(520.0D, 970.0D, 89.0D).backgroundColor(Color.decode("#751910"), Color.decode("#676767")).state("oui", "non").toggle(true).attach(this);
    ToggleNode.create(640.0D, 900.0D, 89.0D).state(ResourceConstant.SEARCH, ResourceConstant.SEARCH).attach(this);
    ToggleNode.create(640.0D, 970.0D, 89.0D).state(ResourceConstant.SEARCH, ResourceConstant.SEARCH).toggle(true).attach(this);
    SwitchNode.create(810.0D, 970.0D, 300.0D, 52.0D).state(new String[] { "state 1", "state 2" }).attach(this);
    BookmarkNode.create(572.0D, 25.0D).icon("!").attach(this);
    BookmarkNode.create(776.0D, 25.0D).icon("?").attach(this);
    BookmarkNode.create(980.0D, 25.0D).icon("#").attach(this);
    BookmarkNode.create(1184.0D, 25.0D).icon(ResourceConstant.ARROW_DOWN).attach(this);
    TextFieldNode.create(760.0D, 200.0D, 400.0D).attach(this);
    SearchTextFieldNode.create(760.0D, 300.0D, 400.0D).placeholder("rechercher...".toUpperCase()).attach(this);
    SelectorNode.create(760.0D, 400.0D, 400.0D)
      .body(selector -> {
          SelectorNode.SelectorItemNode.create().text("item 1").attach(selector);
          SelectorNode.SelectorItemNode.create().text("item 2").attach(selector);
          SelectorNode.SelectorItemNode.create().text("item 3").attach(selector);
        }).attach(this);
    TextNode.create(960.0D, 500.0D).text(Text.create("HEADER", PaladiumText.HEADER)).anchorX(Align.CENTER).attach(this);
    TextNode.create(960.0D, 610.0D).text(Text.create("TITLE", PaladiumText.TITLE)).anchorX(Align.CENTER).attach(this);
    TextNode.create(960.0D, 660.0D).text(Text.create("SUB_TITLE", PaladiumText.SUB_TITLE)).anchorX(Align.CENTER).attach(this);
    int slotSize = 48;
    int i;
    for (i = 0; i < 27; i++)
      SlotNode.create(744.0D + (48 * i % 9), (700 + i / 9 * 48), 48.0D, i + 9, (IInventory)(Minecraft.func_71410_x()).field_71439_g.field_71071_by).placeholder(ResourceConstant.FLAT_LOCK).attach(this); 
    for (i = 0; i < 9; i++)
      SlotNode.create(744.0D + (48 * i), 892.0D, 48.0D, i, (IInventory)(Minecraft.func_71410_x()).field_71439_g.field_71071_by).placeholder(Items.field_151082_bd).attach(this); 
    Random random = new Random();
    ChartNode.create(1200.0D, 200.0D, 646.0999999999999D, 241.49999999999997D)
      .axis(ChartNode.ChartAxis.x("date", new String[] { "13/03", "14/03", "15/03", "16/03", "17/03", "18/03", "19/03", "20/03" }), ChartNode.ChartAxis.y("experience").suffix("xp"))
      .data("mineur", 
        
        ChartNode.ColoredChartData.create()
        .color(Color.decode("#FF3939"))
        .add("13/03", Integer.valueOf(random.nextInt(10)))
        .add("14/03", Integer.valueOf(random.nextInt(10)))
        .add("15/03", Integer.valueOf(random.nextInt(10)))
        .add("16/03", Integer.valueOf(random.nextInt(10)))
        .add("17/03", Integer.valueOf(random.nextInt(10)))
        .add("18/03", Integer.valueOf(random.nextInt(10)))
        .add("19/03", Integer.valueOf(random.nextInt(10)))
        .add("20/03", Integer.valueOf(random.nextInt(10))))
      
      .data("chasseur", 
        
        ChartNode.ColoredChartData.create()
        .color(Color.decode("#3967FF"))
        .add("13/03", Integer.valueOf(random.nextInt(10)))
        .add("14/03", Integer.valueOf(random.nextInt(10)))
        .add("15/03", Integer.valueOf(random.nextInt(10)))
        .add("16/03", Integer.valueOf(random.nextInt(10)))
        .add("17/03", Integer.valueOf(random.nextInt(10)))
        .add("18/03", Integer.valueOf(random.nextInt(10)))
        .add("19/03", Integer.valueOf(random.nextInt(10)))
        .add("20/03", Integer.valueOf(random.nextInt(10))))
      
      .data("farmeur", 
        
        ChartNode.ColoredChartData.create()
        .color(Color.decode("#FFD101"))
        .add("13/03", Integer.valueOf(random.nextInt(10)))
        .add("14/03", Integer.valueOf(random.nextInt(10)))
        .add("15/03", Integer.valueOf(random.nextInt(10)))
        .add("16/03", Integer.valueOf(random.nextInt(10)))
        .add("17/03", Integer.valueOf(random.nextInt(10)))
        .add("18/03", Integer.valueOf(random.nextInt(10)))
        .add("19/03", Integer.valueOf(random.nextInt(10)))
        .add("20/03", Integer.valueOf(random.nextInt(10))))
      
      .data("alchimiste", 
        
        ChartNode.ColoredChartData.create()
        .color(Color.decode("#FC64C9"))
        .add("13/03", Integer.valueOf(random.nextInt(10)))
        .add("14/03", Integer.valueOf(random.nextInt(10)))
        .add("15/03", Integer.valueOf(random.nextInt(10)))
        .add("16/03", Integer.valueOf(random.nextInt(10)))
        .add("17/03", Integer.valueOf(random.nextInt(10)))
        .add("18/03", Integer.valueOf(random.nextInt(10)))
        .add("19/03", Integer.valueOf(random.nextInt(10)))
        .add("20/03", Integer.valueOf(random.nextInt(10))))
      
      .attach(this);
    ChartNode.create(1200.0D, 450.0D, 646.0999999999999D, 241.49999999999997D)
      .smooth(false)
      .axis(ChartNode.ChartAxis.x("date", new String[] { "13/03", "14/03", "15/03", "16/03", "17/03", "18/03", "19/03", "20/03" }), ChartNode.ChartAxis.y("experience").suffix("xp"))
      .data("mineur", 
        
        ChartNode.ColoredChartData.create()
        .color(Color.decode("#FF3939"))
        .add("13/03", Integer.valueOf(random.nextInt(10)))
        .add("14/03", Integer.valueOf(random.nextInt(10)))
        .add("15/03", Integer.valueOf(random.nextInt(10)))
        .add("16/03", Integer.valueOf(random.nextInt(10)))
        .add("17/03", Integer.valueOf(random.nextInt(10)))
        .add("18/03", Integer.valueOf(random.nextInt(10)))
        .add("19/03", Integer.valueOf(random.nextInt(10)))
        .add("20/03", Integer.valueOf(random.nextInt(10))))
      
      .data("chasseur", 
        
        ChartNode.ColoredChartData.create()
        .color(Color.decode("#3967FF"))
        .add("13/03", Integer.valueOf(random.nextInt(10)))
        .add("14/03", Integer.valueOf(random.nextInt(10)))
        .add("15/03", Integer.valueOf(random.nextInt(10)))
        .add("16/03", Integer.valueOf(random.nextInt(10)))
        .add("17/03", Integer.valueOf(random.nextInt(10)))
        .add("18/03", Integer.valueOf(random.nextInt(10)))
        .add("19/03", Integer.valueOf(random.nextInt(10)))
        .add("20/03", Integer.valueOf(random.nextInt(10))))
      
      .data("farmeur", 
        
        ChartNode.ColoredChartData.create()
        .color(Color.decode("#FFD101"))
        .add("13/03", Integer.valueOf(random.nextInt(10)))
        .add("14/03", Integer.valueOf(random.nextInt(10)))
        .add("15/03", Integer.valueOf(random.nextInt(10)))
        .add("16/03", Integer.valueOf(random.nextInt(10)))
        .add("17/03", Integer.valueOf(random.nextInt(10)))
        .add("18/03", Integer.valueOf(random.nextInt(10)))
        .add("19/03", Integer.valueOf(random.nextInt(10)))
        .add("20/03", Integer.valueOf(random.nextInt(10))))
      
      .data("alchimiste", 
        
        ChartNode.ColoredChartData.create()
        .color(Color.decode("#FC64C9"))
        .add("13/03", Integer.valueOf(random.nextInt(10)))
        .add("14/03", Integer.valueOf(random.nextInt(10)))
        .add("15/03", Integer.valueOf(random.nextInt(10)))
        .add("16/03", Integer.valueOf(random.nextInt(10)))
        .add("17/03", Integer.valueOf(random.nextInt(10)))
        .add("18/03", Integer.valueOf(random.nextInt(10)))
        .add("19/03", Integer.valueOf(random.nextInt(10)))
        .add("20/03", Integer.valueOf(random.nextInt(10))))
      
      .attach(this);
    RadarChartNode.create(1400.0D, 750.0D, 241.0D, 241.0D)
      .data((RadarChartNode.RadarChartData)RadarChartNode.RadarChartData.create("A", ResourceConstant.WARNING, Integer.valueOf((new Random()).nextInt(10))))
      .data((RadarChartNode.RadarChartData)RadarChartNode.RadarChartData.create("B", ResourceConstant.WARNING, Integer.valueOf((new Random()).nextInt(10))))
      .data((RadarChartNode.RadarChartData)RadarChartNode.RadarChartData.create("C", ResourceConstant.WARNING, Integer.valueOf((new Random()).nextInt(10))))
      .data((RadarChartNode.RadarChartData)RadarChartNode.RadarChartData.create("D", ResourceConstant.WARNING, Integer.valueOf((new Random()).nextInt(10))))
      .attach(this);
    keybind(() -> (new Notification(Notification.NotificationType.SUCCESS, "Zeldown vient de vous acheter pour 500$", "Market")).send(), new Integer[] { Integer.valueOf(49) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\internal\client\gui\UIPaladiumKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */