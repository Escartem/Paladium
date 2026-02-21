package fr.paladium.permissionbridge.common.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import java.util.UUID;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class PermissibleEntity {
  private final UUID uuid;
  
  private PermissibleEntity(UUID uuid) {
    this.uuid = uuid;
  }
  
  public UUID getUuid() {
    return this.uuid;
  }
  
  @SideOnly(Side.CLIENT)
  @NonNull
  public static PermissibleEntity me() {
    return new PermissibleEntity(Minecraft.func_71410_x().func_110432_I().func_148256_e().getId());
  }
  
  @NonNull
  public static PermissibleEntity from(@NonNull Entity player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    return new PermissibleEntity(player.func_110124_au());
  }
  
  @NonNull
  public static PermissibleEntity from(@NonNull UUID uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    return new PermissibleEntity(uuid);
  }
  
  @NonNull
  public static PermissibleEntity from(@NonNull CharSequence uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    return new PermissibleEntity(UUIDUtils.parseUUID(uuid));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\permissionbridge\common\data\PermissibleEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */