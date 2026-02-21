package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientFreeze;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

class null implements Runnable {
  public void run() {
    try {
      player.func_70690_d(new PotionEffect(Potion.field_76421_d.func_76396_c(), 40));
      Thread.sleep(2000L);
      player.func_70690_d(new PotionEffect(Potion.field_76421_d.func_76396_c(), 40, 1));
      Thread.sleep(2000L);
      PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("low_battery"), player);
      PSpellsV2.network.sendTo((IMessage)new PacketClientFreeze(true), player);
      Thread.sleep(15000L);
      PSpellsV2.network.sendTo((IMessage)new PacketClientFreeze(false), player);
      PaladiumScheduler.INSTANCE.cancelTask(task.id);
    } catch (InterruptedException interruptedException) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\PlusBatterie$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */