package fr.paladium.palamod.modules.luckyblock.monthly.commands;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.structures.AugustBiicodeStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.structures.LeadStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.structures.PoolStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.structures.QuickSandStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.structures.VentilatorStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.structures.AquariumStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.structures.BarrelStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.structures.JulyBiicodeStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.structures.RedCrossStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.structures.VigilanceStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.structure.MarchDigicodeStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.structures.SeptemberBiicodeStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.structures.AprilDigicodeStructure;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class StructureCommand extends CommandBase {
  public static final String COMMAND_NAME = "structure";
  
  public static final HashMap<UUID, Set<String>> WAITING_PLAYERS = new HashMap<>();
  
  public static void addWaitingPlayer(EntityPlayer player, String name) {
    if (!WAITING_PLAYERS.containsKey(player.func_110124_au()))
      WAITING_PLAYERS.put(player.func_110124_au(), new HashSet<>()); 
    if (getWaitingStructureName(player, name).isPresent())
      return; 
    ((Set<String>)WAITING_PLAYERS.get(player.func_110124_au())).add(name);
  }
  
  public static Optional<String> getWaitingStructureName(EntityPlayer player, String name) {
    if (!WAITING_PLAYERS.containsKey(player.func_110124_au()))
      return Optional.empty(); 
    return ((Set<String>)WAITING_PLAYERS.get(player.func_110124_au())).stream()
      .filter(structureName -> structureName.equalsIgnoreCase(name))
      .findFirst();
  }
  
  public static boolean isWaiting(EntityPlayer player, String structureName) {
    if (!WAITING_PLAYERS.containsKey(player.func_110124_au()))
      return false; 
    return getWaitingStructureName(player, structureName).isPresent();
  }
  
  public static AbstractStructure getStructureByName(EntityPlayer player, String name) {
    if (name.equalsIgnoreCase("vigie"))
      return (AbstractStructure)new VigilanceStructure(player); 
    if (name.equalsIgnoreCase("july"))
      return (AbstractStructure)new JulyBiicodeStructure(player); 
    if (name.equalsIgnoreCase("cross"))
      return (AbstractStructure)new RedCrossStructure(player); 
    if (name.equalsIgnoreCase("aquarium"))
      return (AbstractStructure)new AquariumStructure(player); 
    if (name.equalsIgnoreCase("barrel"))
      return (AbstractStructure)new BarrelStructure(player, false); 
    if (name.equalsIgnoreCase("barrel?"))
      return (AbstractStructure)new BarrelStructure(player, true); 
    if (name.equalsIgnoreCase("quicksand"))
      return (AbstractStructure)new QuickSandStructure(player); 
    if (name.equalsIgnoreCase("lead"))
      return (AbstractStructure)new LeadStructure(player); 
    if (name.equalsIgnoreCase("august"))
      return (AbstractStructure)new AugustBiicodeStructure(player); 
    if (name.equalsIgnoreCase("ventilator"))
      return (AbstractStructure)new VentilatorStructure(player); 
    if (name.equalsIgnoreCase("pool"))
      return (AbstractStructure)new PoolStructure(player); 
    if (name.equalsIgnoreCase("september"))
      return (AbstractStructure)new SeptemberBiicodeStructure(player); 
    if (name.equalsIgnoreCase("march"))
      return (AbstractStructure)new MarchDigicodeStructure(player); 
    if (name.equalsIgnoreCase("april"))
      return (AbstractStructure)new AprilDigicodeStructure(player); 
    return null;
  }
  
  public String func_71517_b() {
    return "structure";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "structure";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (!(sender instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)sender;
    if (player.field_70170_p.field_72995_K)
      return; 
    if (args.length == 0) {
      MonthlyUtils.sendMessage(player, new String[] { "§cUtilisation: /" + func_71517_b() + " <nom>" });
      return;
    } 
    if (!isWaiting(player, args[0])) {
      MonthlyUtils.sendMessage(player, new String[] { "§cVous n'êtes pas en attente d'un placement de structure." });
      return;
    } 
    AbstractStructure structure = getStructureByName(player, args[0]);
    if (structure == null) {
      MonthlyUtils.sendMessage(player, new String[] { "§cLa structure demandée n'existe pas." });
      return;
    } 
    if (!structure.canSpawn()) {
      MonthlyUtils.sendMessage(player, structure.getFailureMessage(args[0]));
      return;
    } 
    WAITING_PLAYERS.remove(player.func_110124_au());
    structure.spawn();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\commands\StructureCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */