package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.commands;

import fr.paladium.common.CommonModule;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class HolidayCommand extends CommandBase {
  public static final String COMMAND_NAME = "vacances";
  
  public static final String NO_PERMISSION_MESSAGE = "&cVous n'avez pas la permission d'utiliser cette commande.";
  
  public static final String LOCATION_NOT_FOUND_MESSAGE = "&cAucune position n'a été définie pour cette structure.";
  
  public static final String CORRECT_SERVER_NAME = "EVENT";
  
  public static final String WRONG_SERVER_MESSAGE = "&cVous ne pouvez que effectuer cette commande sur le serveur: &6EVENT";
  
  public static final DoubleLocation LOCATION = new DoubleLocation(8934.0D, 56.0D, -1716.0D);
  
  public String func_71517_b() {
    return "vacances";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "vacances";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (!(sender instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)sender;
    String serverName = CommonModule.getInstance().getConfig().getServerName();
    if (!"EVENT".equalsIgnoreCase(serverName)) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous ne pouvez que effectuer cette commande sur le serveur: &6EVENT" });
      return;
    } 
    PlayerLuckyBlockProperties properties = PlayerLuckyBlockProperties.get((EntityPlayer)player);
    if (properties == null || !properties.isOwnHolidayCommand()) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous n'avez pas la permission d'utiliser cette commande." });
      return;
    } 
    if (LOCATION == null) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cAucune position n'a été définie pour cette structure." });
      return;
    } 
    LOCATION.teleportServer((EntityPlayer)player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\commands\HolidayCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */