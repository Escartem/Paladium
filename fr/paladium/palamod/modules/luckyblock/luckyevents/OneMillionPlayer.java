package fr.paladium.palamod.modules.luckyblock.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.paladium.network.OpenGuiPacket;
import net.minecraft.entity.player.EntityPlayerMP;

public class OneMillionPlayer extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PalaMod.getNetwork().sendTo((IMessage)new OpenGuiPacket(PGuiRegistry.GUI_ONE_MILLION_PLAYER, true, x, y, z), player);
    CresusManager.getInstance().withdrawPlayerAsync(player.func_110124_au(), 5000.0D, "LuckyBlock -> onemillionplayer", new CresusCallback<CresusResponse>() {
          public void onSuccess(CresusResponse response) {}
          
          public void onFail(CresusResponse response, Throwable error) {}
        });
  }
  
  public String getName() {
    return "1 000 000 joueurs";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "onemillionplayer";
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\OneMillionPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */