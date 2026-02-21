package fr.paladium.palaspawner.server.subcommand.palaspawner.sub;

import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.SubCommandInfo;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.FreeSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.NumberSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandType;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.palaspawner.common.block.BlockEmptySpawner;
import fr.paladium.palaspawner.common.manager.SpawnerManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

@SubCommandInfo(name = "give", permission = "palaspawner.command.spawner.give", type = SubCommandType.STRING, senderTypes = {SenderType.PLAYER})
public class GiveSubCommand extends ASubCommand {
  protected void init() {
    super.init();
    String[] mobs = (String[])SpawnerManager.getInstance().getSpawnerEntities().keySet().toArray((Object[])new String[0]);
    ABaseSubCommand aBaseSubCommand = FreeSubCommand.create("(mobId)", mobs).build(this);
    NumberSubCommand.create("(souls)").build((ASubCommand)aBaseSubCommand, (sender, data) -> {
          String mobId = data.getFreeArg();
          ItemStack spawnerItem = BlockEmptySpawner.createSpawnerItemStack(mobId, data.getInteger());
          ChatUtils.sendColoredMessage(sender, new String[] { "§8[§6Paladium§8] §aGiving mob: " + mobId + " with " + data.getInteger() + " souls" });
          InventoryUtils.giveOrDropitems((EntityPlayer)sender, spawnerItem);
          return true;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\server\subcommand\palaspawner\sub\GiveSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */