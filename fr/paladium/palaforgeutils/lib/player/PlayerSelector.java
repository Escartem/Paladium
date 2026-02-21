package fr.paladium.palaforgeutils.lib.player;

import java.util.function.Consumer;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class PlayerSelector {
  private final TargetType type;
  
  private final Object[] args;
  
  public TargetType getType() {
    return this.type;
  }
  
  public Object[] getArgs() {
    return this.args;
  }
  
  private PlayerSelector(@NonNull TargetType type, Object... args) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    this.type = type;
    this.args = args;
  }
  
  @NonNull
  public static PlayerSelector PLAYER(@NonNull EntityPlayerMP player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    return new PlayerSelector(TargetType.PLAYER, new Object[] { player });
  }
  
  @NonNull
  public static PlayerSelector AREA(@NonNull AxisAlignedBB area) {
    if (area == null)
      throw new NullPointerException("area is marked non-null but is null"); 
    return new PlayerSelector(TargetType.AREA, new Object[] { area, MinecraftServer.func_71276_C().func_130014_f_() });
  }
  
  @NonNull
  public static PlayerSelector AREA(@NonNull AxisAlignedBB area, @NonNull World world) {
    if (area == null)
      throw new NullPointerException("area is marked non-null but is null"); 
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    return new PlayerSelector(TargetType.AREA, new Object[] { area, world });
  }
  
  @NonNull
  public static PlayerSelector WORLD() {
    return new PlayerSelector(TargetType.WORLD, new Object[] { MinecraftServer.func_71276_C().func_130014_f_() });
  }
  
  @NonNull
  public static PlayerSelector WORLD(@NonNull World world) {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    return new PlayerSelector(TargetType.WORLD, new Object[] { world });
  }
  
  @NonNull
  public static PlayerSelector ALL() {
    return new PlayerSelector(TargetType.ALL, new Object[0]);
  }
  
  public void apply(Consumer<EntityPlayerMP> consumer) {
    if (this.type == TargetType.PLAYER) {
      consumer.accept((EntityPlayerMP)this.args[0]);
    } else if (this.type == TargetType.AREA) {
      AxisAlignedBB area = (AxisAlignedBB)this.args[0];
      World world = (World)this.args[1];
      world.func_72872_a(EntityPlayerMP.class, area).forEach(consumer);
    } else if (this.type == TargetType.WORLD) {
      World world = (World)this.args[0];
      world.field_73010_i.forEach(consumer);
    } else if (this.type == TargetType.ALL) {
      (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b.forEach(consumer);
    } 
  }
  
  private enum TargetType {
    PLAYER, AREA, WORLD, ALL;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\player\PlayerSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */