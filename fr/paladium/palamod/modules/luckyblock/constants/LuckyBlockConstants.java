package fr.paladium.palamod.modules.luckyblock.constants;

import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.enums.DanceDirection;
import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.objects.DancePattern;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LuckyBlockConstants {
  public static final int SOUTH = 0;
  
  public static final int WEST = 1;
  
  public static final int NORTH = 2;
  
  public static final int EAST = 3;
  
  public static final String LUCKY_BLOCK_TESTING_PERMISSION = "paladium.luckyblock.testing";
  
  public static final String EXPIRATION_MILLIS_NBT_FIELD = "expirationMillis";
  
  public static final Integer DANCER_NAME_ID = Integer.valueOf(14);
  
  public static final Integer DANCER_SKIN_ID = Integer.valueOf(15);
  
  public static final String TARGETED_ID_NBT_FIELD = "targetedPlayerId";
  
  public static final String DANCER_SPAWN_WORLD_FIELD = "spawnX";
  
  public static final String DANCER_SPAWN_X_FIELD = "spawnX";
  
  public static final String DANCER_SPAWN_Y_FIELD = "spawnY";
  
  public static final String DANCER_SPAWN_Z_FIELD = "spawnZ";
  
  public static final String DANCER_SPAWN_YAW_FIELD = "spawnYaw";
  
  public static final String DANCER_SPAWN_PITCH_FIELD = "spawnPitch";
  
  public static final String DANCER_NAME_NBT_FIELD = "name";
  
  public static final String DANCER_SKIN_NBT_FIELD = "skin";
  
  public static final long DANCE_TASK_DELAY = 0L;
  
  public static final long DANCE_TASK_PERIOD = TimeUnit.SECONDS.toMillis(1L);
  
  public static final int TIMER_DELAY = 10;
  
  public static final long DANCER_EXPIRATION_MILLIS = TimeUnit.MINUTES.toMillis(10L);
  
  public static final int MAX_DANCE_TRIES = 20;
  
  public static final DancePattern LORN_DANCE_PATTERN = DancePattern.builder()
    .username("§bLorn87")
    .skinPath("palamod:textures/entity/npc/lorn87.png")
    .directions(Arrays.asList(new DanceDirection[] { DanceDirection.FRONT, DanceDirection.BACK, DanceDirection.LEFT, DanceDirection.RIGHT, DanceDirection.LEFT, DanceDirection.RIGHT })).build();
  
  public static final DancePattern GOLDORAK_DANCE_PATTERN = DancePattern.builder()
    .username("§bGoldorak85")
    .skinPath("palamod:textures/entity/npc/goldorak85.png")
    .directions(Arrays.asList(new DanceDirection[] { DanceDirection.BACK, DanceDirection.JUMP, DanceDirection.FRONT, DanceDirection.JUMP, DanceDirection.LEFT, DanceDirection.RIGHT, DanceDirection.JUMP })).build();
  
  public static final DancePattern FUZE_DANCE_PATTERN = DancePattern.builder()
    .username("§bFuzeIII")
    .skinPath("palamod:textures/entity/npc/fuzeiii.png")
    .directions(Arrays.asList(new DanceDirection[] { DanceDirection.FRONT, DanceDirection.LEFT, DanceDirection.BACK, DanceDirection.JUMP, DanceDirection.JUMP, DanceDirection.RIGHT, DanceDirection.LEFT })).build();
  
  public static final List<DancePattern> PATTERNS = Arrays.asList(new DancePattern[] { LORN_DANCE_PATTERN, GOLDORAK_DANCE_PATTERN, FUZE_DANCE_PATTERN });
  
  public static final int FIRST_STRUCTURE_SIZE = 6;
  
  public static final int SECOND_STRUCTURE_SIZE = 8;
  
  public static final int THIRD_STRUCTURE_SIZE = 10;
  
  public static final long FIRST_STRUCTURE_EXPIRATION_MILLIS = TimeUnit.MINUTES.toMillis(1L);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\constants\LuckyBlockConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */