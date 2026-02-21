package fr.paladium.palarpg.server.command;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommandClass;
import fr.paladium.palarpg.module.dungeon.server.command.RPGDungeonCommand;
import fr.paladium.palarpg.module.entity.server.command.RPGEntitiesCommand;
import fr.paladium.palarpg.module.equipment.server.command.RPGEquipmentCommand;
import fr.paladium.palarpg.module.profile.server.command.RPGProfileCommand;
import fr.paladium.palarpg.module.stat.server.command.RPGStatsCommand;

@Command(command = {"/palarpg", "/rpg"}, description = "Commande de gestion du RPG", permission = "rpg.command.use", sender = {SenderType.PLAYER, SenderType.CONSOLE})
public class RPGCommand {
  @SubCommandClass
  private RPGStatsCommand statsSubCommand;
  
  @SubCommandClass
  private RPGProfileCommand profileSubCommand;
  
  @SubCommandClass
  private RPGDungeonCommand dungeonSubCommand;
  
  @SubCommandClass
  private RPGEntitiesCommand entitiesSubCommand;
  
  @SubCommandClass
  private RPGEquipmentCommand equipmentSubCommand;
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\server\command\RPGCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */