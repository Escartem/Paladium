package fr.paladium.palamod.modules.miner.dimminer.client.overlay;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palamod.modules.miner.PMiner;
import fr.paladium.palamod.modules.miner.dimminer.common.data.DimMinerPlayer;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.TextElement;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.UIDataOverlay;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.LongSignal;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.RandomStringUtils;

@UIData(background = false, anchorY = Align.START)
@UIDataOverlay(active = true)
public class UIDimMinerOverlay extends UI {
  public static final Resource EMPTY_TEXTURE = Resource.of(new ResourceLocation("palamod", "textures/gui/miner/overlay/empty.png")).nearest();
  
  public static final Resource FILL_TEXTURE = Resource.of(new ResourceLocation("palamod", "textures/gui/miner/overlay/fill.png")).nearest();
  
  public static final Resource FOREGROUND_TEXTURE = Resource.of(new ResourceLocation("palamod", "textures/gui/miner/overlay/foreground.png")).linear();
  
  private final Resource shadowTexture = Resource.of(new ResourceLocation("palamod", "textures/gui/miner/overlay/shadow.png")).linear();
  
  private final Resource iconTexture = Resource.of(new ResourceLocation("palamod", "textures/gui/miner/overlay/icon.png")).nearest();
  
  private final DimMinerPlayer player;
  
  private LongSignal pointsSignal;
  
  public UIDimMinerOverlay() {
    if (ZUI.isOpen(UIDimMinerOverlay.class))
      ZUI.close(ZUI.getUI(UIDimMinerOverlay.class)); 
    this.player = DimMinerPlayer.get();
  }
  
  public void init() {
    if (!isActive()) {
      ZUI.close(this);
      return;
    } 
    this.pointsSignal = new LongSignal(this.player.getPoints());
    ImageNode.create(0.0D, 0.0D, 1920.0D, 708.0D)
      .resource(this.shadowTexture)
      .color(Color.WHITE.copyAlpha(0.7F))
      .attach(this);
    ContainerNode.create(380.0D, 33.0D, 1160.0D, 182.0D)
      .body(container -> {
          ((TextNode)TextNode.create(container.dw(2.0D), 0.0D).text(Text.create("dimension mineur", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 32.0F, Color.decode("#CB3544")).shadow().shadow(0.0F, 4.0F))).onInit(()).onUpdate(())).anchorX(Align.CENTER).attach(container);
          ImageNode.create(0.0D, 83.0D, container.getWidth(), 44.0D).resource(EMPTY_TEXTURE).onUpdate(()).attach(container);
          ((ImageNode)ImageNode.create(0.0D, 83.0D, container.getWidth(), 44.0D).onInit(())).onUpdate(()).watch((Signal)this.pointsSignal).attach(container);
          ImageNode.create(0.0D, 83.0D, container.getWidth(), 44.0D).resource(FOREGROUND_TEXTURE).onUpdate(()).attach(container);
          ((TextNode)((TextNode)TextNode.create(0.0D, container.getHeight()).onInit(())).onUpdate(())).watch((Signal)this.pointsSignal).anchorY(Align.END).attach(container);
          ((TextNode)TextNode.create(container.getWidth(), container.getHeight()).onUpdate(())).anchorX(Align.END).anchorY(Align.END).attach(container);
        }).attach(this);
  }
  
  public void update() {
    if (!isActive()) {
      ZUI.close(this);
      return;
    } 
    if (this.pointsSignal != null)
      this.pointsSignal.set(Long.valueOf(this.player.getPoints())); 
  }
  
  private String obfuscatedText(String text) {
    if (this.player == null)
      return text; 
    Random random = new Random();
    String obfuscated = "";
    for (int i = 0; i < text.length(); i++) {
      char c = text.charAt(i);
      if (c == ' ' || c == ':') {
        obfuscated = obfuscated + c;
      } else {
        String randomChar = RandomStringUtils.randomAlphabetic(1);
        try {
          Integer.parseInt(String.valueOf(c));
          randomChar = RandomStringUtils.randomNumeric(1);
        } catch (Exception exception) {}
        obfuscated = obfuscated + ((random.nextInt(Math.max(1, (int)this.player.getPoints() / 5)) == 0) ? randomChar : (String)Character.valueOf(c));
      } 
    } 
    return obfuscated;
  }
  
  private boolean isActive() {
    return (this.player != null && PMiner.proxy.isMinerDimension());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\client\overlay\UIDimMinerOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */