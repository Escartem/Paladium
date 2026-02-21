package fr.paladium.palamod.modules.egghunt.runnable;

import fr.paladium.chronos.server.api.async.ChronosCallback;
import fr.paladium.chronos.server.api.pojo.Planned;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

class null implements ChronosCallback<Planned> {
  public void onSuccess(Planned event) {
    PEggHunt.data.setActive(false);
    MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§bFélicitation à §c" + player.func_70005_c_()));
    CresusManager.getInstance().depositPlayerAsync(player.func_110124_au(), 50000.0D, "EggHunt -> end 2h timer", new CresusCallback<CresusResponse>() {
          public void onSuccess(CresusResponse arg0) {}
          
          public void onFail(CresusResponse arg0, Throwable arg1) {}
        });
  }
  
  public void onFail(Planned event, Throwable error) {
    if (Constants.MOD_ENV != Constants.Environment.DEV)
      error.printStackTrace(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\runnable\EggHuntStatusThread$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */