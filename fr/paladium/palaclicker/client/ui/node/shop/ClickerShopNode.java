package fr.paladium.palaclicker.client.ui.node.shop;

import fr.paladium.palaclicker.client.ui.UIClicker;
import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.palaclicker.common.network.packet.shop.CSPacketClickerShopBuy;
import fr.paladium.palaclicker.server.config.shop.dto.ClickerShopItem;
import fr.paladium.palaclicker.server.config.shop.dto.ClickerShopType;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.popup.UIYesNoPopup;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palaforgeutils.lib.time.TimeUtils;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.MapSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.DoubleSignal;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ClickerShopNode extends Node {
  private static final ResourceLocation SLOT__TEXTURE = new ResourceLocation("palaclicker", "textures/gui/shop/slot.png");
  
  private static final Map<ClickerShopType, String> TEXT_BY_TYPE = new HashMap<>();
  
  private static final Map<ClickerShopType, ResourceLocation> TEXTURE_BY_TYPE = new HashMap<>();
  
  private int startIndex;
  
  private ClickerShopType type;
  
  private PlayerClickerData playerData;
  
  private TextNode timeNode;
  
  static {
    TEXT_BY_TYPE.put(ClickerShopType.WEEKLY, "clicker.gui.shop.weekly");
    TEXT_BY_TYPE.put(ClickerShopType.DAILY, "clicker.gui.shop.daily");
    TEXT_BY_TYPE.put(ClickerShopType.JOBS, "clicker.gui.shop.jobs");
    TEXTURE_BY_TYPE.put(ClickerShopType.WEEKLY, new ResourceLocation("palaclicker", "textures/gui/shop/weekly.png"));
    TEXTURE_BY_TYPE.put(ClickerShopType.DAILY, new ResourceLocation("palaclicker", "textures/gui/shop/daily.png"));
    TEXTURE_BY_TYPE.put(ClickerShopType.JOBS, new ResourceLocation("palaclicker", "textures/gui/shop/jobs.png"));
  }
  
  protected ClickerShopNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static ClickerShopNode create(double x, double y, double width, double height) {
    return new ClickerShopNode(x, y, width, height);
  }
  
  public void init(UI ui) {
    clearChildren();
    MapSignal<ClickerShopType, List<ClickerShopItem>> data = ((UIClicker)getUi()).getShopSignal();
    DoubleSignal pointsSignal = ((UIClicker)getUi()).getPointsSignal();
    this.playerData = ((UIClicker)getUi()).getPlayerData();
    if (!data.containsKey(this.type))
      return; 
    ImageNode.create(0.0D, 0.0D)
      .resource(TEXTURE_BY_TYPE.get(this.type))
      .linear(false)
      .size(getWidth(), getHeight())
      .attach(this);
    TextNode.create(dw(2.0D), 2.0D)
      .text(Text.create(TTT.format(TEXT_BY_TYPE.get(this.type), new Object[0]), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 17.0F).color(Color.WHITE).shadow().shadow(0.0F, 3.0F)))
      .anchorX(Align.CENTER)
      .attach(this);
    Calendar calendar = Calendar.getInstance();
    int dayOfMonth = calendar.get(5);
    int month = calendar.get(2);
    boolean aprilFool = (month == 3 && dayOfMonth == 1);
    FlexNode.horizontal(56.0D, 74.0D, 42.0D)
      .margin(15.0D)
      .body(flex -> {
          for (int i = 0; i < 4; i++) {
            int index = i;
            ClickerShopItem shopItem = ((List<ClickerShopItem>)data.get(this.type)).get(index + this.startIndex);
            int quantity = this.playerData.getShopQuantity(this.type, shopItem.getId());
            double price = shopItem.getPrice(quantity);
            ImageNode.create(0.0D, 0.0D).resource(SLOT__TEXTURE).linear(false).onInit(()).onClick(()).watch((Signal)pointsSignal).watch((Signal)data).attach(flex);
          } 
        }).attach(this);
    this
      
      .timeNode = (TextNode)TextNode.create(dw(2.0D), 190.0D).text(Text.create(TTT.format("clicker.gui.loading", new Object[0]), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 12.0F).color(Color.WHITE))).anchorX(Align.CENTER).attach(this);
  }
  
  public void update() {
    if (this.playerData == null || this.timeNode == null)
      return; 
    long now = TimeUtils.now();
    long start = ((Long)this.playerData.getShopLastUpdate().getOrDefault(this.type, Long.valueOf(0L))).longValue();
    long remaining = start + (this.type.getDuration() * 60 * 60) - now;
    Duration duration = Duration.ofSeconds(remaining);
    long days = duration.toDays();
    long hours = duration.toHours() % 24L;
    long minutes = duration.toMinutes() % 60L;
    String time = (days > 0L) ? String.format("%02dJ%02d", new Object[] { Long.valueOf(days), Long.valueOf(hours) }) : String.format("%02dH%02d", new Object[] { Long.valueOf(hours), Long.valueOf(minutes) });
    this.timeNode.getText().text(TTT.format("clicker.gui.shop.offer.end", new Object[] { time }));
  }
  
  public ClickerShopNode type(ClickerShopType type) {
    this.type = type;
    return this;
  }
  
  public ClickerShopNode startIndex(int startIndex) {
    this.startIndex = startIndex;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\clien\\ui\node\shop\ClickerShopNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */