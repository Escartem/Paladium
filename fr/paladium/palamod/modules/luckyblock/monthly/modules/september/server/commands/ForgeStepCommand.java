package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.commands;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.PracticeMakesPerfectEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.BlackSmithData;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.BlackSmithStep;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.UUID;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ForgeStepCommand extends CommandBase {
  public static final String COMMAND_NAME = "forgestep";
  
  public static final String INFO_ARG = "info";
  
  public static final String NO_EVENT_MESSAGE = "&cVous ne participez pas à l'événement.";
  
  public static final String CANT_DO_MESSAGE = "&cVous ne pouvez pas faire cela maintenant.";
  
  public String func_71517_b() {
    return "forgestep";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "forgestep";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (!(sender instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)sender;
    UUID uniqueId = player.func_110124_au();
    PracticeMakesPerfectEvent instance = PracticeMakesPerfectEvent.INSTANCE;
    BlackSmithData data = instance.get(uniqueId);
    if (args.length == 1 && args[0].equalsIgnoreCase("info")) {
      info(player, instance);
      return;
    } 
    if (data == null) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous ne participez pas à l'événement." });
      return;
    } 
    if (!data.spawnAndFillChest(player)) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous ne pouvez pas faire cela maintenant." });
      return;
    } 
    data.startWave(player);
  }
  
  public void info(EntityPlayerMP player, PracticeMakesPerfectEvent instance) {
    int maxStep = instance.getSteps().size();
    int i = 0;
    for (BlackSmithStep step : instance.getSteps()) {
      i++;
      MonthlyUtils.sendMessage((EntityPlayer)player, false, new String[] { "§8§m----------------------------------------" });
      MonthlyUtils.sendMessage((EntityPlayer)player, false, new String[] { "&6Étape &e" + i + "/" + maxStep + " &7:" });
      for (int j = 1; j < (step.getMessages()).length - 1; j++) {
        MonthlyUtils.sendMessage((EntityPlayer)player, false, new String[] { step.getMessages()[j] });
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\commands\ForgeStepCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */