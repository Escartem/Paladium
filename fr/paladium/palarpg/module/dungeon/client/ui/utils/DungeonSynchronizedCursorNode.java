package fr.paladium.palarpg.module.dungeon.client.ui.utils;

import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.module.dungeon.common.network.ui.sync.BBPacketRPGDungeonSynchronizedUICursorMove;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.shader.impl.CircleShader;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class DungeonSynchronizedCursorNode extends Node {
  private static final Resource CROWN_TEXTURE = Resource.of(new ResourceLocation("palarpg", "textures/gui/utils/crown.png")).nearest();
  
  private static final Resource CURSOR_TEXTURE = Resource.of(new ResourceLocation("palarpg", "textures/gui/utils/cursor.png")).linear();
  
  private final List<Color> availableColors;
  
  private final Map<String, SynchronizedCursor> cursorMap;
  
  private DungeonSynchronizedCursorNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    this.cursorMap = new HashMap<>();
    this.availableColors = new ArrayList<>();
    this.availableColors.add(Color.decode("#FD2A44"));
    this.availableColors.add(Color.decode("#34D275"));
    this.availableColors.add(Color.decode("#006DF7"));
    this.availableColors.add(Color.decode("#A922CA"));
  }
  
  @NonNull
  public static DungeonSynchronizedCursorNode create() {
    return new DungeonSynchronizedCursorNode(0.0D, 0.0D, 0.0D, 0.0D);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    UI ui = getUi();
    for (SynchronizedCursor cursor : this.cursorMap.values()) {
      cursor.x = lerp(cursor.x, cursor.targetX);
      cursor.y = lerp(cursor.y, cursor.targetY);
      cursor.color.bind();
      DrawUtils.RESOURCE.drawImage(cursor.x, cursor.y, 20.0D, 21.0D, CURSOR_TEXTURE);
      Color.reset();
      float headX = (float)cursor.x + 15.0F;
      float headY = (float)cursor.y + 15.0F;
      float headSize = 32.0F;
      DrawUtils.SHAPE.drawCircle((headX + 16.0F), (headY + 16.0F), cursor.color, 17.5D);
      CircleShader.inst().bind(16.0F, headX + 16.0F, headY + 16.0F);
      DrawUtils.RESOURCE.drawImage(headX, headY, 32.0D, 32.0D, Resource.of("https://minotar.net/helm/" + cursor.player + "/32").nearest());
      CircleShader.inst().unbind();
      if (ui instanceof IDungeonSynchronizedUI && ((IDungeonSynchronizedUI)ui).isMain(cursor.player))
        DrawUtils.RESOURCE.drawImage((headX + 16.0F - 6.0F), (headY - 16.0F), 12.0D, 12.0D, CROWN_TEXTURE); 
    } 
  }
  
  public void update() {
    UI ui = getUi();
    String uuid = UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g);
    (new BBPacketRPGDungeonSynchronizedUICursorMove(uuid, ui.getClass().getName(), ui.getMouseX(), ui.getMouseY())).send();
  }
  
  @NonNull
  public DungeonSynchronizedCursorNode setCursor(@NonNull String player, double x, double y) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g).equals(player))
      return this; 
    SynchronizedCursor cursor = this.cursorMap.get(player);
    if (cursor == null)
      this.cursorMap.put(player, cursor = new SynchronizedCursor(player, this.availableColors.remove(0))); 
    cursor.targetX = x;
    cursor.targetY = y;
    return this;
  }
  
  private double lerp(double value, double target) {
    double fps = getUi().getFps();
    double diff = target - value;
    double absDiff = Math.abs(diff);
    double offset = 0.5D / ((fps == 0.0D) ? 1.0D : fps) / 60.0D * absDiff / 3.0D;
    if (absDiff > 0.5D) {
      value += (diff > 0.0D) ? offset : -offset;
    } else {
      value = target;
    } 
    return value;
  }
  
  private class SynchronizedCursor {
    private final String player;
    
    private final Color color;
    
    private double x;
    
    private double y;
    
    private double targetX;
    
    private double targetY;
    
    public SynchronizedCursor(String player, Color color) {
      this.player = player;
      this.color = color;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\u\\utils\DungeonSynchronizedCursorNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */