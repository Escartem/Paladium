package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.tasks;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.factions.server.utils.PlayerUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.StayHydratedEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.HydrationData;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets.SCHydrationPacket;
import java.util.HashSet;
import java.util.TimerTask;
import net.minecraft.entity.player.EntityPlayerMP;

public class HydrationTask extends TimerTask {
  public void run() {
    HashSet<HydrationData> datas = StayHydratedEvent.INSTANCE.getDatas();
    datas.removeIf(HydrationData::isExpired);
    datas.forEach(data -> {
          EntityPlayerMP player = PlayerUtils.getPlayer(data.getPlayerUniqueId());
          if (player != null) {
            data.dehydrate(player);
            PalaMod.network.sendTo((IMessage)new SCHydrationPacket(data), player);
          } 
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\server\tasks\HydrationTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */