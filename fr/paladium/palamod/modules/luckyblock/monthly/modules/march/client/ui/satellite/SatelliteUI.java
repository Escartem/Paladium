package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.ui.satellite;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.CommonMarch;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.cs.CSSatelliteHandlePacket;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.nio.IntBuffer;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class SatelliteUI extends UI {
  private static final ResourceLocation BACKGROUND = new ResourceLocation("palamod", "textures/ui/march/satellite/background.png");
  
  private static final String TITLE = "TEST SATTELITE";
  
  private static final String START = "DÉPART";
  
  private static final String STOP = "ARRIVÉE";
  
  private static final String WAITING_MESSAGE = "DÉBUT DANS %S SECONDES!";
  
  private static final String GO_MESSAGE = "GO!";
  
  private static final long START_DELAY = TimeUnit.SECONDS.toMillis(5L);
  
  private static final Color TITLE_COLOR = Color.decode("26EF2E");
  
  private static final Color TITLE_SHADOW_COLOR = Color.decode("1d3e28");
  
  private static final Color CORRECT_COLOR = new Color(255, 255, 255, 255);
  
  private static final TextInfo SUBTITLE_INFO = TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 25.0F).color(Color.WHITE);
  
  private final long startMillis = System.currentTimeMillis();
  
  private final StringSignal timerSignal = new StringSignal();
  
  private String startInformations;
  
  public void init() {
    updateSignal(getRemaining(System.currentTimeMillis()));
    ImageNode.create(311.0D, 186.0D)
      .resource(BACKGROUND)
      .size(1241.0D, 727.0D)
      .attach(this);
    TextNode.create(408.0D, 233.0D)
      .text(Text.create("TEST SATTELITE", PaladiumText.HEADER.copy().color(TITLE_COLOR).shadow(TITLE_SHADOW_COLOR)))
      .attach(this);
    TextNode.create(396.0D, 503.0D)
      .text(Text.create("DÉPART", SUBTITLE_INFO))
      .attach(this);
    TextNode.create(1343.0D, 503.0D)
      .text(Text.create("ARRIVÉE", SUBTITLE_INFO))
      .attach(this);
    ((TextNode)TextNode.create(960.0D, 800.0D)
      .text(Text.create(this.timerSignal.getOrDefault(), SUBTITLE_INFO))
      .anchorX(Align.CENTER)
      .watch((Signal)this.timerSignal)
      .onInit(node -> node.getText().text((String)this.timerSignal.getOrDefault())))
      
      .attach(this);
    resetMousePosition();
  }
  
  public boolean close() {
    return false;
  }
  
  public void postDraw(double mouseX, double mouseY, float ticks) {
    long now = System.currentTimeMillis();
    long remaining = getRemaining(now);
    updateSignal(remaining);
    if (!canStart(remaining)) {
      resetMousePosition();
      return;
    } 
    if (!isMouseAtStart(mouseX, mouseY) && !isMouseOnCorrectColor(mouseX, mouseY)) {
      handlePacket(false);
      return;
    } 
    if (isMouseAtStop(mouseX, mouseY))
      handlePacket(true); 
  }
  
  private void updateSignal(long remaining) {
    String output = null;
    if (canStart(remaining)) {
      output = "GO!";
    } else {
      output = String.format("DÉBUT DANS %S SECONDES!", new Object[] { Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(remaining)) });
    } 
    if (this.startInformations == null || !this.startInformations.equals(output)) {
      this.startInformations = output;
      this.timerSignal.set(output);
    } 
  }
  
  private boolean isMouseAtStart(double mouseX, double mouseY) {
    double startX = 406.0D;
    double startY = 562.0D;
    double startWidth = 100.0D;
    double startHeight = 100.0D;
    return (mouseX >= 406.0D && mouseX <= 506.0D && mouseY >= 562.0D && mouseY <= 662.0D);
  }
  
  private boolean isMouseAtStop(double mouseX, double mouseY) {
    double stopX = 1358.0D;
    double stopY = 562.0D;
    double stopWidth = 100.0D;
    double stopHeight = 100.0D;
    return (mouseX >= 1358.0D && mouseX <= 1458.0D && mouseY >= 562.0D && mouseY <= 662.0D);
  }
  
  private void resetMousePosition() {
    Minecraft mc = Minecraft.func_71410_x();
    int currentWidth = mc.field_71443_c;
    int currentHeight = mc.field_71440_d;
    double width = 1920.0D / currentWidth;
    double height = 1080.0D / currentHeight;
    double requiredX = 439.0D;
    double requiredY = 504.0D;
    double x = 439.0D / width;
    double y = 504.0D / height;
    Mouse.setCursorPosition((int)x, (int)y);
  }
  
  private boolean isMouseOnCorrectColor(double mouseX, double mouseY) {
    int x = Mouse.getX();
    int y = Mouse.getY();
    IntBuffer pixelBuffer = BufferUtils.createIntBuffer(4);
    int[] pixelValues = new int[4];
    GL11.glPixelStorei(3333, 1);
    GL11.glPixelStorei(3317, 1);
    GL11.glReadPixels(x, y, 1, 1, 6408, 33639, pixelBuffer);
    pixelBuffer.get(pixelValues);
    int red = pixelValues[0] >> 16 & 0xFF;
    int green = pixelValues[0] >> 8 & 0xFF;
    int blue = pixelValues[0] & 0xFF;
    int alpha = pixelValues[0] >> 24 & 0xFF;
    Color color = new Color(red, green, blue, alpha);
    return color.equals(CORRECT_COLOR);
  }
  
  private void handlePacket(boolean win) {
    CSSatelliteHandlePacket cSSatelliteHandlePacket = new CSSatelliteHandlePacket(win);
    CommonMarch.getInstance().getNetwork().sendToServer((IMessage)cSSatelliteHandlePacket);
    Minecraft.func_71410_x().func_147108_a(null);
  }
  
  private long getRemaining(long now) {
    return this.startMillis + START_DELAY - now;
  }
  
  private boolean canStart(long remaining) {
    return (remaining <= 0L);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\clien\\ui\satellite\SatelliteUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */