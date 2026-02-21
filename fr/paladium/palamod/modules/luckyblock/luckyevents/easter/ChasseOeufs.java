package fr.paladium.palamod.modules.luckyblock.luckyevents.easter;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ChasseOeufs extends ALuckyEvent implements IHeliosLuckyEventWidget {
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.startTimedHeliosEvent(player, getClass(), TimeUnit.SECONDS.toMillis(60L));
    final List<BlockPos> blockPosList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      BlockPos pos = generateEggCoordinates((EntityPlayer)player, player.field_70170_p, x, y, z);
      if (pos != null) {
        blockPosList.add(pos);
        player.field_70170_p.func_147449_b(pos.getX(), pos.getY(), pos.getZ(), BlocksRegister.BLUE_EASTER_EGG);
      } 
    } 
    final String playerId = player.func_110124_au().toString();
    UsersManager.getBlueEasterEggCaught().put(playerId, Integer.valueOf(0));
    final LuckyTask task = new LuckyTask();
    task
      
      .id = PaladiumScheduler.INSTANCE.runTaskAsyncLater(new Runnable() {
          public void run() {
            try {
              int cpt = 0;
              while (cpt != 60) {
                cpt++;
                Thread.sleep(1000L);
                if (((Integer)UsersManager.getBlueEasterEggCaught().get(playerId)).intValue() == 10)
                  cpt = 60; 
              } 
              for (BlockPos blockPos : blockPosList)
                player.field_70170_p.func_147468_f(blockPos.getX(), blockPos.getY(), blockPos.getZ()); 
              int eggCaught = ((Integer)UsersManager.getBlueEasterEggCaught().get(playerId)).intValue();
              switch (eggCaught) {
                case 0:
                  player.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 400, 2));
                  break;
                case 1:
                  player.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 400, 1));
                  break;
                case 2:
                  ServerManager.addFreeze(player, player.field_70165_t, player.field_70163_u, player.field_70161_v);
                  Thread.sleep(30000L);
                  ServerManager.removeFreeze(player);
                  break;
                case 3:
                  break;
                case 10:
                  PlayerUtils.dropItemStack((Entity)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, new ItemStack(BlocksRegister.BLOCK_PALADIUM, 2));
                  break;
                default:
                  PlayerUtils.dropItemStack((Entity)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, new ItemStack(ItemsRegister.PALADIUM_INGOT, eggCaught));
                  break;
              } 
              UsersManager.getBlueEasterEggCaught().put(playerId, Integer.valueOf(0));
              PaladiumScheduler.INSTANCE.cancelTask(task.id);
            } catch (InterruptedException interruptedException) {}
          }
        }0L).getTaskId();
  }
  
  private BlockPos generateEggCoordinates(EntityPlayer player, World world, int x, int y, int z) {
    int testCpt = 0;
    while (testCpt < 30) {
      BlockPos pos = EventUtils.getRandomCoordsWithinRadius(x, y, z, 40);
      for (int yOffset = -1; yOffset < 2; yOffset++) {
        pos.setY((pos.getY() + yOffset));
        if (world.func_147437_c(pos.getX(), pos.getY(), pos.getZ()) && !world.func_147437_c(pos.getX(), pos.getY() - 1, pos.getZ()) && EventUtils.canInteract(player, pos.getX(), pos.getY(), pos.getZ()))
          return pos; 
      } 
      testCpt++;
    } 
    return null;
  }
  
  public String getName() {
    return "Chasse aux œufs";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 150;
  }
  
  public String getTexture() {
    return "easter/chasse_oeufs";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Cliquez sur les œufs pour obtenir des récompenses" };
  }
  
  public String getAction() {
    return "Temps restant: ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\ChasseOeufs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */