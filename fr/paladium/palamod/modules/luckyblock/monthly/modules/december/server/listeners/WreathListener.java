package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.server.listeners;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.PotionsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.tiles.TileEntityChristmasWreath;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.server.data.WreathData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class WreathListener {
  private static final String NO_DAMAGE_MESSAGE = "&eVous n'avez pas pris de dégâts!";
  
  private int tick;
  
  @SubscribeEvent
  public void tickEvent(TickEvent.ServerTickEvent event) {
    if (event.phase == TickEvent.Phase.START)
      return; 
    this.tick++;
    if (this.tick < 20)
      return; 
    this.tick = 0;
    TileEntityChristmasWreath.WREATHS.removeIf(wreathData -> {
          if (wreathData == null || wreathData.getLocation() == null || wreathData.getWreath() == null)
            return true; 
          Block block = wreathData.getLocation().getBlock();
          return (block != BlocksRegister.CHRISTMAS_WREATH);
        });
  }
  
  @SubscribeEvent
  public void onHurt(LivingHurtEvent event) {
    if (!(event.entity instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.entity;
    if (player.func_82165_m(PotionsRegister.INVINCIBILITY.getPotionId()))
      event.ammount = 0.0F; 
    World world = player.field_70170_p;
    Location location = new Location((Entity)player);
    TileEntityChristmasWreath wreath = getWreath(player, world, location);
    if (wreath == null)
      return; 
    if (wreath.use()) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&eVous n'avez pas pris de dégâts!" });
      event.ammount = 0.0F;
    } 
  }
  
  private TileEntityChristmasWreath getWreath(EntityPlayerMP player, World world, Location location) {
    TileEntityChristmasWreath wreath = null;
    for (WreathData data : TileEntityChristmasWreath.WREATHS) {
      Location wreathLocation = data.getLocation();
      if (wreathLocation.getDistance(location) < 50.0D) {
        wreath = data.getWreath();
        break;
      } 
    } 
    return wreath;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\server\listeners\WreathListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */