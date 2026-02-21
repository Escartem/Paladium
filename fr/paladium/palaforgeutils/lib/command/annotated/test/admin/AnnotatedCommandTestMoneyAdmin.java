package fr.paladium.palaforgeutils.lib.command.annotated.test.admin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.CommandParameter;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.CommandParameterTabComplete;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import fr.paladium.palaforgeutils.lib.validator.impl.minecraft.EntityValidator.Online;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.number.NumberValidator.GreaterOrEquals;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.number.NumberValidator.Positive;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;

public class AnnotatedCommandTestMoneyAdmin {
  @SubCommand(command = "/money <player>", description = "Affiche l'argent d'un joueur", permission = "money.command.admin")
  public void moneyCommandPlayer(CommandContext context, OfflinePlayer player) {
    context.send("Le joueur " + player.getName() + " a actuellement " + 'Ϩ' + "$");
  }
  
  @SubCommand(command = "/money give <player> <amount> [<reason>]", description = "Donne de l'argent à un joueur", permission = "money.command.admin")
  public void moneyCommandGive(CommandContext context, @Online EntityPlayer player, @Positive double amount, @CommandParameter(tabComplete = @CommandParameterTabComplete(method = "moneyCommandGiveTabComplete", side = Side.CLIENT)) Optional<String> reason) {
    context.send("Vous avez donné " + amount + "$ à " + player.func_70005_c_() + " (" + (String)reason.orElse("null") + ")");
  }
  
  @SubCommand(command = "/money set <player> <amount>", description = "Définit l'argent d'un joueur", permission = "money.command.admin")
  public void moneyCommandSet(CommandContext context, @Online EntityPlayer player, @GreaterOrEquals(0.0D) double amount) {
    context.send("Vous avez défini l'argent de " + player.func_70005_c_() + " à " + amount + "$");
  }
  
  @SideOnly(Side.CLIENT)
  public String[] moneyCommandGiveTabComplete(@NonNull CommandContext context) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    return new String[] { "@Console", "@" + context.getSender().func_70005_c_() };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\test\admin\AnnotatedCommandTestMoneyAdmin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */