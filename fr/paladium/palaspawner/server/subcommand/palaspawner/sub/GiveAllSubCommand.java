package fr.paladium.palaspawner.server.subcommand.palaspawner.sub;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.SubCommandInfo;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandType;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.palaspawner.common.block.BlockEmptySpawner;
import fr.paladium.palaspawner.common.manager.SpawnerManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

@SubCommandInfo(name = "give-all", permission = "palaspawner.command.spawner.give", type = SubCommandType.STRING, senderTypes = {SenderType.PLAYER})
public class GiveAllSubCommand extends ASubCommand {
  protected void init() {}
  
  protected boolean performCurrentNode(ICommandSender sender, CommandData data) {
    SpawnerManager.getInstance().getSpawnerEntities().keySet().forEach(mobId -> {
          ItemStack spawnerItem = BlockEmptySpawner.createSpawnerItemStack(mobId, 600);
          InventoryUtils.giveOrDropitems((EntityPlayer)sender, spawnerItem);
        });
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\server\subcommand\palaspawner\sub\GiveAllSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */