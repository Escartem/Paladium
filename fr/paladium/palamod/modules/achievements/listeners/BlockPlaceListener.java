package fr.paladium.palamod.modules.achievements.listeners;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.achievements.types.BlockPlaceAchievement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.world.BlockEvent;

public class BlockPlaceListener {
  @SubscribeEvent
  public void onPlaceBlock(BlockEvent.PlaceEvent event) {
    if (event.isCanceled() || event.player == null)
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.player;
    BlockPlaceAchievement.performCheck((EntityPlayer)player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\listeners\BlockPlaceListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */