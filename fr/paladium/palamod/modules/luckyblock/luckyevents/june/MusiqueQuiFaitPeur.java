package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.entity.june.EntityShark;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;

public class MusiqueQuiFaitPeur extends ALuckyEvent {
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    Vec3 spawnPos = EventUtils.getBlockInFront((EntityPlayer)player, 30.0D);
    final EntityShark entity = new EntityShark(player.field_70170_p);
    entity.func_70634_a(spawnPos.field_72450_a, player.field_70163_u + 1.0D, spawnPos.field_72449_c);
    entity.setCurrentTarget((Entity)player);
    entity.setShowOnlyFin(true);
    player.field_70170_p.func_72838_d((Entity)entity);
    final LuckyTask task = new LuckyTask();
    task
      
      .id = PaladiumScheduler.INSTANCE.runTaskAsyncLater(new Runnable() {
          public void run() {
            try {
              PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("jaws"), player);
              for (int i = 0; i < 58; i++) {
                Vec3 spawnPos = EventUtils.getBlockInFront((EntityPlayer)player, (30 - i / 2));
                entity.func_70634_a(spawnPos.field_72450_a, player.field_70163_u, spawnPos.field_72449_c);
                Thread.sleep(163L);
              } 
              entity.setShowOnlyFin(false);
              Thread.sleep(1000L);
              entity.func_70106_y();
              PlayerUtils.dropItemStackAtEntity((Entity)player, new ItemStack(BlocksRegister.SHARK_PLUSH));
            } catch (InterruptedException interruptedException) {}
            PaladiumScheduler.INSTANCE.cancelTask(task.id);
          }
        }0L).getTaskId();
  }
  
  public String getName() {
    return "La musique qui fait peur !";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "june/musique_qui_fait_peur";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\MusiqueQuiFaitPeur.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */