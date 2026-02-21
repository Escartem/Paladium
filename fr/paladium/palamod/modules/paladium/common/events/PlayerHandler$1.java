package fr.paladium.palamod.modules.paladium.common.events;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.tutorial.common.event.DollarStoneDepositEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;

class null implements CresusCallback<CresusResponse> {
  public void onSuccess(CresusResponse arg0) {
    player.func_146105_b((IChatComponent)new ChatComponentText("§aVous venez de récupérer " + winningAmount + "$ avec votre DollarStone."));
    FMLServerScheduler.getInstance().add(new Runnable[] { () -> {
            DollarStoneDepositEvent event = new DollarStoneDepositEvent(player, arg0);
            MinecraftForge.EVENT_BUS.post((Event)event);
          } });
  }
  
  public void onFail(CresusResponse arg0, Throwable arg1) {
    player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Dollar Stone§8] §6Impossible de récupérer §e" + winningAmount + "$"));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\events\PlayerHandler$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */