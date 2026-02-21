package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.subcommand.satellite;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.CommonMarch;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.sc.SCOpenSatelliteUIPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.WellCalibratedEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class SatelliteSubCommand extends CommandBase {
  public static final String NAME = "satellite";
  
  public static final String DESCRIPTION = "gèrer le satellite";
  
  public String func_71517_b() {
    return "satellite";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "satellite";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (!(sender instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)sender;
    if (!WellCalibratedEvent.isWaitingPlayer(player.func_110124_au())) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cTu n'a pas de satellite à calibrer." });
      return;
    } 
    SCOpenSatelliteUIPacket sCOpenSatelliteUIPacket = new SCOpenSatelliteUIPacket();
    CommonMarch.getInstance().getNetwork().sendTo((IMessage)sCOpenSatelliteUIPacket, player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\subcommand\satellite\SatelliteSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */