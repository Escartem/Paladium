package fr.paladium.palarpg.module.stat.server.command;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.AStatCapability;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatCapabilityMutator;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatMutationType;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.TimedStatCapabilityMutator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class RPGStatsCommand {
  @SubCommand(command = "/rpg stats add <value> <type> <operation> <tick>", permission = "rpg.command.admin.use", sender = {SenderType.PLAYER})
  public void addMutator(CommandContext context, double value, EnumStats type, StatMutationType operation, int tick) {
    EntityPlayerMP player = context.getPlayer();
    RPGStatPlayerData statData = (RPGStatPlayerData)RPGPlayer.getData((Entity)player, "stats");
    if (statData == null)
      return; 
    AStatCapability<Object> capa = statData.getCapabilityByName(type);
    if (capa == null)
      return; 
    TimedStatCapabilityMutator<Object> mutator = (TimedStatCapabilityMutator<Object>)TimedStatCapabilityMutator.create().setTick(tick).setId("test_" + type + "_" + operation + "_" + tick + "_" + Math.random()).setSaved(false).setMutationType(operation).setValue(Double.valueOf(value));
    capa.addMutator((StatCapabilityMutator)mutator);
    statData.applyAndSync();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\server\command\RPGStatsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */