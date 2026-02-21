package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.structures.utils.StructureUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MusicPartyEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "La fête de la musique, c'est tous les jours avec les pirates !";
  
  private static final String TEXTURE_PATH = "july/music_party";
  
  private static final int RARITY = 350;
  
  private static final boolean IS_BAD = false;
  
  private static final String DRUM_SKIN_PATH = "palamod:textures/entity/npc/drum.png";
  
  private static final String ACCORDION_SKIN_PATH = "palamod:textures/entity/npc/accordion.png";
  
  private static final String GUITAR_SKIN_PATH = "palamod:textures/entity/npc/guitar.png";
  
  private static final int LOCATION_AMOUNT = 3;
  
  private static final int LOCATION_RADIUS = 3;
  
  private static final String SPAWN_MESSAGE = "&eLes musiciens viennent de faire leur apparition ! Ils vous donneront un objet à la fin de la fête !";
  
  private static final String SOUND_NAME = "musicians";
  
  private static final long DURATION = TimeUnit.MINUTES.toMillis(1L);
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    World world = player.field_70170_p;
    List<Location> locations = findSafeLocations(world, x, y - 2, z);
    Location firstLocation = locations.get(0);
    EntityNPC drum = new EntityNPC(world, "palamod:textures/entity/npc/drum.png", firstLocation.getX(), firstLocation.getY(), firstLocation.getZ(), DURATION, DURATION, new ItemStack(ItemsRegister.DRUM), true);
    Location secondLocation = locations.get(1);
    EntityNPC accordion = new EntityNPC(world, "palamod:textures/entity/npc/accordion.png", secondLocation.getX(), secondLocation.getY(), secondLocation.getZ(), DURATION, DURATION, new ItemStack(ItemsRegister.ACCORDION), true);
    Location thirdLocation = locations.get(2);
    EntityNPC guitar = new EntityNPC(world, "palamod:textures/entity/npc/guitar.png", thirdLocation.getX(), thirdLocation.getY(), thirdLocation.getZ(), DURATION, DURATION, new ItemStack(ItemsRegister.ELECTRIC_GUITAR), true);
    world.func_72838_d((Entity)drum);
    world.func_72838_d((Entity)accordion);
    world.func_72838_d((Entity)guitar);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&eLes musiciens viennent de faire leur apparition ! Ils vous donneront un objet à la fin de la fête !" });
    MonthlyUtils.playSoundAround("musicians", world, x, y, z, 50);
    dropItemLater(Arrays.asList(new EntityNPC[] { drum, accordion, guitar }));
  }
  
  private void dropItemLater(final List<EntityNPC> entities) {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
          public void run() {
            Random random = new Random();
            EntityNPC npc = entities.get(random.nextInt(entities.size()));
            if (npc == null || npc.field_70128_L)
              return; 
            ItemStack stack = npc.func_70694_bm();
            if (stack == null)
              return; 
            npc.func_70099_a(stack.func_77946_l(), 0.0F);
          }
        }DURATION - TimeUnit.SECONDS.toMillis(1L));
  }
  
  private List<Location> findSafeLocations(World world, int x, int y, int z) {
    List<Location> locations = new ArrayList<>();
    Cuboid cuboid = new Cuboid(world, (x - 3), y, (z - 3), (x + 3), y, (z + 3));
    while (locations.size() < 3) {
      Location location = getRandomLocation(cuboid);
      if (StructureUtils.isAirAtLocation(location)) {
        locations.add(location);
        continue;
      } 
      locations.add(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ()));
    } 
    return locations;
  }
  
  private Location getRandomLocation(Cuboid cuboid) {
    Random random = new Random();
    return cuboid.getLocations().get(random.nextInt(cuboid.getLocations().size()));
  }
  
  public String getName() {
    return "La fête de la musique, c'est tous les jours avec les pirates !";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 350;
  }
  
  public String getTexture() {
    return "july/music_party";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\MusicPartyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */