package fr.paladium.palashop.provider.box.server.command;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.command.impl.palagive.manager.PalaGiveManager;
import fr.paladium.palaforgeutils.lib.validator.impl.minecraft.EntityValidator.Online;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.number.NumberValidator.Greater;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.string.StringValidator.NotEmpty;
import fr.paladium.palashop.provider.box.BoxProvider;
import fr.paladium.palashop.provider.box.common.dto.box.Box;
import fr.paladium.palashop.provider.box.common.entity.EntityBox;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

@Command(command = {"box", "boxes", "lootbox", "lootboxes", "palabox", "palaboxes"}, description = "Commande de gestion des boxes", permission = "palashop.command.box")
public class BoxCommand {
  @SubCommand(command = "box update", description = "Mettre à jour les boxes", permission = "palashop.command.box.update")
  public void update(CommandContext context) {
    (new Thread(() -> BoxProvider.getInstance().update().thenAccept(()).exceptionally(()), "BoxCommand/Update"))
      
      .start();
  }
  
  @SubCommand(command = "box list", description = "Afficher la liste des boxes", permission = "palashop.command.box.list")
  public void list(CommandContext context) {
    context.success("Liste des boxes :");
    if (BoxProvider.getServer().getBoxes().isEmpty()) {
      context.error("Aucune box disponible.");
      return;
    } 
    for (String boxId : BoxProvider.getServer().getBoxes().keySet())
      context.send("§8» §e" + boxId + " §8(§7" + ((Box)BoxProvider.getServer().getBoxes().get(boxId)).getData().getName() + "§r§8)"); 
  }
  
  @SubCommand(command = "box spawn <id>", description = "Faire apparaitre une box", permission = "palashop.command.box.spawn", sender = {SenderType.PLAYER})
  public void spawn(CommandContext context, @NotEmpty String id) {
    Optional<Box> optional = BoxProvider.getServer().getBox(id);
    if (!optional.isPresent()) {
      context.error("La box " + id + " n'existe pas.");
      return;
    } 
    Box box = optional.get();
    EntityPlayerMP entityPlayerMP = context.getPlayer();
    EntityBox entity = new EntityBox(entityPlayerMP.func_130014_f_(), box.getId());
    entity.func_70080_a(((EntityPlayer)entityPlayerMP).field_70165_t, ((EntityPlayer)entityPlayerMP).field_70163_u, ((EntityPlayer)entityPlayerMP).field_70161_v, ((EntityPlayer)entityPlayerMP).field_70177_z, ((EntityPlayer)entityPlayerMP).field_70125_A);
    entityPlayerMP.func_130014_f_().func_72838_d((Entity)entity);
    context.success("§rLa box " + box.getData().getName() + " §ra été placée.");
  }
  
  @SubCommand(command = "box give <player> <id> [<amount>]", description = "Donner la clé d'une box", permission = "palashop.command.box.give")
  public void give(CommandContext context, @Online EntityPlayer player, @NotEmpty String id, @Greater(0.0D) Optional<Integer> amount) {
    Optional<Box> optional = BoxProvider.getServer().getBox(id);
    if (!optional.isPresent()) {
      context.error("La box " + id + " n'existe pas.");
      return;
    } 
    Box box = optional.get();
    int amountValue = ((Integer)amount.orElse(Integer.valueOf(1))).intValue();
    if (amountValue <= 0) {
      context.error("Le montant doit être supérieur à 0.");
      return;
    } 
    if (box.getData().getItem() == null) {
      context.error("La box " + id + " n'a pas de clé associée.");
      return;
    } 
    ItemStack itemStack = new ItemStack(box.getData().getItem(), amountValue);
    PalaGiveManager.inst().give(player, itemStack);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\server\command\BoxCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */