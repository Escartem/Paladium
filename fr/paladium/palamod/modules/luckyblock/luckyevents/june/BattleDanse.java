package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.constants.LuckyBlockConstants;
import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.EntityDancer;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.ModuleLuckyEvent;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.packet.PacketLuckyEventHelios;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class BattleDanse extends ALuckyEvent implements IHeliosLuckyEventWidget {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityDancer dancer = new EntityDancer(player.field_70170_p, LuckyBlockConstants.LORN_DANCE_PATTERN, (EntityPlayer)player);
    dancer.start((EntityPlayer)player);
    PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("admin_radio"), player);
    ModuleLuckyEvent.getInstance().getNetwork().sendTo((IMessage)new PacketLuckyEventHelios(true, BattleDanse.class.getName()), player);
  }
  
  public String getName() {
    return "Battle de danse";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 700;
  }
  
  public String getTexture() {
    return "june/battle_danse";
  }
  
  public boolean isTimerType() {
    return false;
  }
  
  public String[] getDescription() {
    return new String[] { "Reproduit les mouvements des administrateurs", "pour gagner une récompense." };
  }
  
  public String getAction() {
    return "";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\BattleDanse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */