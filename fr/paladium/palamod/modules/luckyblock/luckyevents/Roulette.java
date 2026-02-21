package fr.paladium.palamod.modules.luckyblock.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PlayerLuckyPassProperties;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.paladium.network.OpenGuiPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class Roulette extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerLuckyPassProperties.get((EntityPlayer)player).setHasLuckyEventBypassNow(true);
    PalaMod.getNetwork().sendTo((IMessage)new OpenGuiPacket(PGuiRegistry.GUI_LUCKY_PASS_BYPASS, true), player);
  }
  
  public String getName() {
    return "Roulette";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 800;
  }
  
  public String getTexture() {
    return "roulette";
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Roulette.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */