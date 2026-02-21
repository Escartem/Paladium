package fr.paladium.palamod.modules.factions.utils;

import fr.paladium.factions.api.entity.ILevelableEntity;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.core.faction.levels.LevelValue;
import fr.paladium.factions.core.player.Player;
import fr.paladium.factions.core.player.PlayerController;
import fr.paladium.factions.core.utils.ControlResult;
import java.util.UUID;
import java.util.function.DoubleConsumer;

public class JobXpUtils {
  public static void getMultiplier(UUID uuid, final DoubleConsumer result) {
    double multiplier = 1.0D;
    try {
      PlayerController controller = PlayerController.getInstance();
      Player player = controller.getLoadedPlayer(uuid);
      if (player == null) {
        result.accept(1.0D);
        return;
      } 
      IFaction faction = player.getFaction();
      if (faction == null) {
        result.accept(1.0D);
        return;
      } 
      ILevelableEntity handler = faction.getLevelEntity();
      handler.getLevelDoubleValueAsync(LevelValue.JOB_MULTIPLIER, new ControlResult<Double>() {
            public void onCallback() {
              if (!hasSucceeded()) {
                result.accept(1.0D);
                return;
              } 
              double tmpMultiplier = ((Double)getResult()).doubleValue();
              if (tmpMultiplier < 1.0D)
                tmpMultiplier = 1.0D; 
              result.accept(tmpMultiplier);
            }
          });
    } catch (Exception ignored) {
      result.accept(1.0D);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\faction\\utils\JobXpUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */