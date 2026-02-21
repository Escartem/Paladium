package fr.paladium.palamod.modules.hunter.command;

import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import fr.paladium.palaforgeutils.lib.validator.impl.minecraft.EntityValidator.Online;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;

@Command(command = {"backpack"}, description = "Open your backpack")
public class BackpackCommand {
  private static final Map<UUID, UUID> ADMIN_MAP = new HashMap<>();
  
  @SubCommand(command = "backpack", permission = "palamod.command.backpack", description = "Open your backpack")
  public void openBackpack(CommandContext context) {
    context.getPlayer().openGui(PalaMod.instance, PGuiRegistry.GUI_BACKPACK, (context.getPlayer()).field_70170_p, 0, 0, 0);
  }
  
  @SubCommand(command = "backpack <player>", permission = "palamod.command.backpack.other", description = "Open other backpack")
  public void openBackpack(CommandContext context, @Online EntityPlayerMP player) {
    EntityPlayerMP sender = context.getPlayer();
    ADMIN_MAP.put(sender.func_110124_au(), player.func_110124_au());
    sender.openGui(PalaMod.instance, PGuiRegistry.GUI_ADMIN_BACKPACK, sender.field_70170_p, 0, 0, 0);
  }
  
  public static EntityPlayerMP getBackpackTarget(UUID uuid) {
    return PlayerUtils.getPlayer(ADMIN_MAP.remove(uuid));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\command\BackpackCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */