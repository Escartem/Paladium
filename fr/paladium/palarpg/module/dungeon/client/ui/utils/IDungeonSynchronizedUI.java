package fr.paladium.palarpg.module.dungeon.client.ui.utils;

import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public interface IDungeonSynchronizedUI {
  String getMain();
  
  DungeonSynchronizedCursorNode getCursorNode();
  
  void onRemoteClosed();
  
  void onRemoteMousePressed(double paramDouble1, double paramDouble2, @NonNull ClickType paramClickType);
  
  void onRemoteMouseDragged(double paramDouble1, double paramDouble2, @NonNull ClickType paramClickType, long paramLong);
  
  void onRemoteMouseReleased(double paramDouble1, double paramDouble2, @NonNull ClickType paramClickType);
  
  void onRemoteMouseScrolled(double paramDouble1, double paramDouble2, int paramInt);
  
  void onRemoteKeyPressed(char paramChar, int paramInt);
  
  boolean canRemoteClose();
  
  boolean canRemoteMousePress(double paramDouble1, double paramDouble2, @NonNull ClickType paramClickType);
  
  boolean canRemoteMouseDrag(double paramDouble1, double paramDouble2, @NonNull ClickType paramClickType, long paramLong);
  
  boolean canRemoteMouseRelease(double paramDouble1, double paramDouble2, @NonNull ClickType paramClickType);
  
  boolean canRemoteMouseScroll(double paramDouble1, double paramDouble2, int paramInt);
  
  boolean canRemoteKeyPress(char paramChar, int paramInt);
  
  default boolean isMain() {
    return (getMain() != null && (Minecraft.func_71410_x()).field_71439_g != null && getMain().equals(UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g)));
  }
  
  default boolean isMain(@NonNull String main) {
    if (main == null)
      throw new NullPointerException("main is marked non-null but is null"); 
    return (getMain() != null && getMain().equals(main));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\u\\utils\IDungeonSynchronizedUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */