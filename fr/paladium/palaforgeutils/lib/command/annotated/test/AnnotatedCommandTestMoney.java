package fr.paladium.palaforgeutils.lib.command.annotated.test;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommandClass;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.command.annotated.test.admin.AnnotatedCommandTestMoneyAdmin;

@Command(command = {"/money", "/balance"}, description = "Gère le système d'argent", permission = "money.command", sender = {SenderType.ALL})
public class AnnotatedCommandTestMoney {
  @SubCommandClass
  private AnnotatedCommandTestMoneyAdmin admin;
  
  @SubCommand(command = "/money", description = "Affiche votre argent", sender = {SenderType.PLAYER})
  public void moneyCommand(CommandContext context) {
    context.send("Vous avez actuellement 1000$");
  }
  
  @SubCommand(command = "/money top", description = "Affiche le classement", priority = 1)
  public void moneyCommandTop(CommandContext context) {
    context.send("Classement des joueurs les plus riches :");
    context.send("1. Player1 : 1000$");
    context.send("2. Player2 : 500$");
    context.send("3. Player3 : 250$");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\test\AnnotatedCommandTestMoney.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */