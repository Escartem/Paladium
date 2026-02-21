package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.ModuleLuckyEvent;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.packet.PacketLuckyEventHelios;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SuisBip extends ALuckyEvent implements IHeliosLuckyEventWidget {
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    int posX = (int)player.field_70165_t;
    int posY = (int)player.field_70163_u;
    int posZ = (int)player.field_70161_v;
    final Location spawnLocation = new Location((Entity)player);
    final List<Location> locationList = new ArrayList<>();
    int structureY = posY + 2;
    int tries = 3;
    boolean structureOk = false;
    do {
      structureY++;
      tries--;
      boolean state = EventUtils.spawnStructureOnAir(player.field_70170_p, posX, structureY, posZ, 5, 1, 20, Blocks.field_150348_b, player, locationList);
      if (!state)
        continue; 
      tries = 0;
      structureOk = true;
    } while (tries != 0);
    if (structureOk) {
      ModuleLuckyEvent.getInstance().getNetwork()
        .sendTo((IMessage)new PacketLuckyEventHelios(true, SuisBip.class.getName()), player);
      int posZ1 = posZ + 9;
      int posZ2 = posZ - 10;
      Location firstLocation = new Location(player.field_70170_p, posX, structureY, posZ1, 180.0F, spawnLocation.getPitch());
      final Location playerLocation = firstLocation.clone(0.0D, 1.0D, 0.0D);
      player.field_70170_p.func_147449_b((int)firstLocation.getX(), (int)firstLocation.getY(), (int)firstLocation.getZ(), Blocks.field_150451_bX);
      locationList.add(firstLocation);
      final Cuboid firstCenter = createSquare(firstLocation.getWorld(), (int)firstLocation.getX(), 
          (int)firstLocation.getY(), (int)firstLocation.getZ(), 2);
      Location secondLocation = new Location(player.field_70170_p, posX, structureY, posZ2);
      player.field_70170_p.func_147449_b((int)secondLocation.getX(), (int)secondLocation.getY(), 
          (int)secondLocation.getZ(), Blocks.field_150451_bX);
      locationList.add(secondLocation);
      final Cuboid secondCenter = createSquare(secondLocation.getWorld(), (int)secondLocation.getX(), 
          (int)secondLocation.getY(), (int)secondLocation.getZ(), 2);
      EventUtils.teleportPlayer(player, playerLocation);
      int structureY0 = structureY;
      final LuckyTask task = new LuckyTask();
      task
        
        .id = PaladiumScheduler.INSTANCE.runTaskAsyncLater(new Runnable() {
            public void run() {
              try {
                boolean fail = false;
                PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("leger_start"), player);
                Thread.sleep(8250L);
                EventUtils.teleportPlayer(player, playerLocation);
                for (int i = 0; i < 4; i++) {
                  if (i != 0)
                    PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("leger_bip"), player); 
                  Thread.sleep((7000 - i * 1000));
                  Cuboid center = (i % 2 == 0) ? secondCenter : firstCenter;
                  if (!SuisBip.this.isOnSquare(center, (EntityPlayer)player)) {
                    fail = true;
                    PlayerUtils.sendMessage((EntityPlayer)player, "§cVous avez perdu !");
                  } 
                } 
                PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("leger_end"), player);
                if (fail)
                  player.func_70690_d(new PotionEffect(Potion.field_76421_d.func_76396_c(), 2400, 1)); 
                ModuleLuckyEvent.getInstance().getNetwork().sendTo((IMessage)new PacketLuckyEventHelios(false, SuisBip.class.getName()), player);
                locationList.forEach(location -> player.field_70170_p.func_147468_f((int)location.getX(), (int)location.getY(), (int)location.getZ()));
                EventUtils.teleportPlayer(player, spawnLocation);
              } catch (InterruptedException interruptedException) {}
              PaladiumScheduler.INSTANCE.cancelTask(task.id);
            }
          }0L).getTaskId();
    } 
  }
  
  private boolean checkCoords(EntityPlayerMP player, int x, int y, int z) {
    return ((int)player.field_70165_t == x && (int)player.field_70163_u == y && (int)player.field_70161_v == z);
  }
  
  private boolean isOnSquare(Cuboid square, EntityPlayer player) {
    return square.contains(new Location((Entity)player));
  }
  
  private Cuboid createSquare(World world, int x, int y, int z, int size) {
    return new Cuboid((x - size), (y - 1), (z - size), (x + size), (y + 1), (z + size));
  }
  
  public String getName() {
    return "Suis le bip";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 180;
  }
  
  public String getTexture() {
    return "june/suis_bip";
  }
  
  public boolean isTimerType() {
    return false;
  }
  
  public String[] getDescription() {
    return new String[] { "Rejoins l’autre côté de la plateforme", "avant le bip pour ne pas", "subir de malus." };
  }
  
  public String getAction() {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\SuisBip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */