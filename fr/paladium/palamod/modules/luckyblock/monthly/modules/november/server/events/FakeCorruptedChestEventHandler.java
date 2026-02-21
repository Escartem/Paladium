package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.server.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.cooldown.CooldownUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.entities.EntityMimic;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.CooldownData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.util.DurationConverter;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

public class FakeCorruptedChestEventHandler {
  private static final CooldownData COOLDOWN_DATA = CooldownData.builder()
    .name("fake_corrupted_chest")
    .duration(TimeUnit.MINUTES.toMillis(5L))
    .build();
  
  private static final String COOLDOWN_MESSAGE = "§cVous devez attendre %s avant de pouvoir poser un faux coffre corrompu !";
  
  @SubscribeEvent
  public void onPlaceBlock(BlockEvent.PlaceEvent event) {
    World world = event.world;
    int x = event.x, y = event.y, z = event.z;
    if (event.block == null)
      return; 
    if (event.block != BlocksRegister.FAKE_CORRUPTED_CHEST)
      return; 
    EntityPlayer player = event.player;
    if (CooldownUtils.isOnCooldown((Entity)player, COOLDOWN_DATA.getName())) {
      long now = System.currentTimeMillis();
      long expiration = CooldownUtils.getCooldown((Entity)player, COOLDOWN_DATA.getName());
      MonthlyUtils.sendMessage(player, new String[] { String.format("§cVous devez attendre %s avant de pouvoir poser un faux coffre corrompu !", new Object[] { DurationConverter.fromMillisToString(expiration - now) }) });
      event.setCanceled(true);
      return;
    } 
    CooldownUtils.setCooldown((Entity)player, COOLDOWN_DATA.getName(), COOLDOWN_DATA.getDuration());
    world.func_147468_f(x, y, z);
    EntityMimic mimic = new EntityMimic(world);
    mimic.func_70107_b(x + 0.5D, y, z + 0.5D);
    world.func_72838_d((Entity)mimic);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\server\events\FakeCorruptedChestEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */