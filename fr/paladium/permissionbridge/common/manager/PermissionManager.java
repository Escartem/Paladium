package fr.paladium.permissionbridge.common.manager;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.permissionbridge.common.data.PermissibleEntity;
import fr.paladium.permissionbridge.common.data.PermissionListData;
import fr.paladium.permissionbridge.common.packet.SCPacketSyncPermission;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.Bukkit;
import org.bukkit.permissions.PermissionAttachmentInfo;

public final class PermissionManager {
  public static final String PERMISSION_WILDCARD = "*";
  
  private static PermissionManager INSTANCE;
  
  private static boolean DEV_MODE;
  
  public Side getSide() {
    return this.side;
  }
  
  public List<String> getPermissions() {
    return this.permissions;
  }
  
  private final Side side = FMLCommonHandler.instance().getSide();
  
  private final List<String> permissions = new ArrayList<>();
  
  private PermissionManager() {
    String command = System.getProperty("sun.java.command");
    if (command != null && !command.isEmpty()) {
      String[] commandArgs = command.split(" ");
      if (commandArgs.length >= 1 && commandArgs[0].contains("Gradle"))
        DEV_MODE = true; 
    } 
  }
  
  public static PermissionManager inst() {
    if (INSTANCE == null)
      INSTANCE = new PermissionManager(); 
    return INSTANCE;
  }
  
  @SideOnly(Side.SERVER)
  public void sync(@NonNull EntityPlayerMP player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    (new SCPacketSyncPermission(PermissionListData.of(getPermissions(PermissibleEntity.from((Entity)player))))).send(player);
  }
  
  @NonNull
  public List<String> getPermissions(@NonNull PermissibleEntity permissible) {
    if (permissible == null)
      throw new NullPointerException("permissible is marked non-null but is null"); 
    if (this.side == Side.CLIENT)
      return this.permissions; 
    try {
      return (List<String>)Bukkit.getPlayer(permissible.getUuid()).getEffectivePermissions().stream().filter(PermissionAttachmentInfo::getValue).map(PermissionAttachmentInfo::getPermission).collect(Collectors.toList());
    } catch (NoClassDefFoundError|Exception e) {
      return new ArrayList<>();
    } 
  }
  
  @NonNull
  public List<String> getPermissions(@NonNull PermissibleEntity permissible, @NonNull String pattern) {
    if (permissible == null)
      throw new NullPointerException("permissible is marked non-null but is null"); 
    if (pattern == null)
      throw new NullPointerException("pattern is marked non-null but is null"); 
    return (List<String>)getPermissions(permissible).stream().filter(permission -> permission.startsWith(pattern)).collect(Collectors.toList());
  }
  
  @NonNull
  public <T> Optional<T> getPermission(@NonNull PermissibleEntity permissible, @NonNull String pattern, @NonNull Class<T> type) {
    if (permissible == null)
      throw new NullPointerException("permissible is marked non-null but is null"); 
    if (pattern == null)
      throw new NullPointerException("pattern is marked non-null but is null"); 
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    Stream<T> stream = getPermissions(permissible, pattern).stream().map(permission -> permission.replace(pattern, "")).map(value -> {
          if (type.isAssignableFrom(Integer.class) || type.isAssignableFrom(int.class))
            return Integer.valueOf(value); 
          if (type.isAssignableFrom(Double.class) || type.isAssignableFrom(double.class))
            return Double.valueOf(value); 
          if (type.isAssignableFrom(Float.class) || type.isAssignableFrom(float.class))
            return Float.valueOf(value); 
          if (type.isAssignableFrom(Long.class) || type.isAssignableFrom(long.class))
            return Long.valueOf(value); 
          if (type.isAssignableFrom(Short.class) || type.isAssignableFrom(short.class))
            return Short.valueOf(value); 
          if (type.isAssignableFrom(Byte.class) || type.isAssignableFrom(byte.class))
            return Byte.valueOf(value); 
          if (type.isAssignableFrom(Boolean.class) || type.isAssignableFrom(boolean.class))
            return ("yes".equalsIgnoreCase(value) || "allow".equalsIgnoreCase(value)) ? Boolean.TRUE : (("no".equalsIgnoreCase(value) || "deny".equalsIgnoreCase(value)) ? Boolean.FALSE : Boolean.valueOf(value)); 
          try {
            return type.cast(value);
          } catch (ClassCastException e) {
            return null;
          } 
        }).filter(value -> (value != null));
    if (Comparable.class.isAssignableFrom(type))
      return stream.max((o1, o2) -> ((Comparable<Object>)o1).compareTo(o2)); 
    return stream.findFirst();
  }
  
  public boolean hasPermission(@NonNull PermissibleEntity permissible, @NonNull String permission) {
    if (permissible == null)
      throw new NullPointerException("permissible is marked non-null but is null"); 
    if (permission == null)
      throw new NullPointerException("permission is marked non-null but is null"); 
    if (DEV_MODE)
      return true; 
    if (this.side == Side.CLIENT)
      return (this.permissions.contains("*") || this.permissions.contains(permission)); 
    try {
      return Bukkit.getPlayer(permissible.getUuid()).hasPermission(permission);
    } catch (NoClassDefFoundError|Exception e) {
      return false;
    } 
  }
  
  public boolean hasExactPermission(@NonNull PermissibleEntity permissible, @NonNull String permission) {
    if (permissible == null)
      throw new NullPointerException("permissible is marked non-null but is null"); 
    if (permission == null)
      throw new NullPointerException("permission is marked non-null but is null"); 
    if (DEV_MODE)
      return true; 
    return getPermissions(permissible).contains(permission);
  }
  
  public void set(@NonNull List<String> permissions) {
    if (permissions == null)
      throw new NullPointerException("permissions is marked non-null but is null"); 
    this.permissions.clear();
    this.permissions.addAll(permissions);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\permissionbridge\common\manager\PermissionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */