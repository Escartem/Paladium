package fr.paladium.palarpg.module.dungeon.client.ui.choice.node;

import com.google.common.cache.CacheBuilder;
import fr.paladium.palarpg.module.dungeon.common.world.generator.DungeonGenerator;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.resource.ResourceBuilder;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public final class DungeonChoicePathNode extends Node {
  private static final ResourceBuilder ICON_BUILDER = ResourceBuilder.create().async().nearest().cache(CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build());
  
  private static final Resource BACKGROUND_TEXTURE = Resource.of(new ResourceLocation("palarpg", "textures/gui/choice/room_background.png")).linear();
  
  private static final Resource FOREGROUND_TEXTURE = Resource.of(new ResourceLocation("palarpg", "textures/gui/choice/room_foreground.png")).linear();
  
  public static final Color SELECTED_COLOR = Color.WHITE;
  
  public static final Color PREMIUM_COLOR = Color.decode("#FFC60C");
  
  public static final Color FINISHED_COLOR = Color.decode("#00E397");
  
  public static final Color AVAILABLE_COLOR = Color.decode("#E13429");
  
  public static final Color NOT_AVAILABLE_COLOR = Color.decode("#282828");
  
  private DungeonGenerator.DungeonRoomPath path;
  
  private boolean finished;
  
  private boolean selected;
  
  public DungeonGenerator.DungeonRoomPath getPath() {
    return this.path;
  }
  
  public boolean isFinished() {
    return this.finished;
  }
  
  public boolean isSelected() {
    return this.selected;
  }
  
  private DungeonChoicePathNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static DungeonChoicePathNode create(double x, double y, double width, double height) {
    return new DungeonChoicePathNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    if (this.path == null)
      throw new IllegalStateException("Path must be set before initializing the node."); 
    ((ImageNode)ImageNode.create(0.0D, 0.0D, w(), h())
      .resource(BACKGROUND_TEXTURE)
      .onUpdate(node -> {
          if (this.finished) {
            node.color(FINISHED_COLOR);
          } else if (this.selected) {
            node.color(SELECTED_COLOR);
          } else if (this.path.isPremium()) {
            node.color(PREMIUM_COLOR);
          } else if (!isEnabled()) {
            node.color(NOT_AVAILABLE_COLOR);
          } else {
            node.color(AVAILABLE_COLOR);
          } 
        })).attach(this);
    ImageNode.create(0.0D, 0.0D, w(), h())
      .resource(FOREGROUND_TEXTURE)
      .attach(this);
    ((ImageNode)ImageNode.create(0.0D, 0.0D, w(), h())
      .resource(ICON_BUILDER.of(new ResourceLocation("palarpg", "textures/gui/choice/icons/" + this.path.getType().name().toLowerCase() + ".png")))
      .onUpdate(node -> {
          if (this.finished) {
            node.color(FINISHED_COLOR);
          } else {
            node.color(Color.WHITE);
          } 
        })).attach(this);
  }
  
  @NonNull
  public DungeonChoicePathNode path(@NonNull DungeonGenerator.DungeonRoomPath path) {
    if (path == null)
      throw new NullPointerException("path is marked non-null but is null"); 
    this.path = path;
    return this;
  }
  
  @NonNull
  public DungeonChoicePathNode finished(boolean finished) {
    this.finished = finished;
    return this;
  }
  
  @NonNull
  public DungeonChoicePathNode selected(boolean selected) {
    this.selected = selected;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\choice\node\DungeonChoicePathNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */