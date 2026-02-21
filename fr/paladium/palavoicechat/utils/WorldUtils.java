package fr.paladium.palavoicechat.utils;

import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class WorldUtils {
  @Nullable
  public static EntityPlayer getPlayerByUUID(World world, UUID playerUUID) {
    for (Entity player : world.func_72910_y()) {
      if (player instanceof EntityPlayer && player.func_110124_au().equals(playerUUID))
        return (EntityPlayer)player; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicecha\\utils\WorldUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */