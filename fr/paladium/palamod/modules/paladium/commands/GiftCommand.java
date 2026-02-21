package fr.paladium.palamod.modules.paladium.commands;

import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.network.data.PaladiumPlayer;
import fr.paladium.palamod.util.PlayerUtil;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class GiftCommand extends CommandBase {
  private static ConcurrentLinkedQueue<UUID> playerQueue = new ConcurrentLinkedQueue<>();
  
  public String func_71517_b() {
    return "gift";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "gift";
  }
  
  public void func_71515_b(ICommandSender sender, String[] arguments) {
    try {
      EntityPlayerMP player = CommandBase.func_71521_c(sender);
      if (playerQueue.contains(player.func_110124_au()))
        return; 
      playerQueue.add(player.func_110124_au());
      try {
        if (PaladiumPlayer.get((Entity)player) != null && !PaladiumPlayer.get((Entity)player).isCompensation() && (getTotalLevel(player) >= 5 || BukkitUtils.hasPermission(player.func_110124_au(), "palacore.keepxp") || BukkitUtils.hasPermission(player.func_110124_au(), "palamod.luckypass"))) {
          PaladiumPlayer.get((Entity)player).setCompensation(true);
          player.func_146105_b((IChatComponent)new ChatComponentText("§cVous avez reçu votre compensation! Bon jeu!"));
          PlayerUtil.addItemStackToInventory(new ItemStack((Block)BlocksRegister.HALLOWEEN_LUCKY_BLOCK, 4), player.field_71071_by);
          PlayerUtil.addItemStackToInventory(new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 8), player.field_71071_by);
          PlayerUtil.addItemStackToInventory(new ItemStack(ItemsRegister.JOB_CANDY, 1), player.field_71071_by);
        } 
      } catch (Exception exception) {}
    } catch (Exception exception) {}
  }
  
  public int getTotalLevel(EntityPlayerMP player) {
    AtomicInteger level = new AtomicInteger(0);
    JobsPlayer jobsPlayer = JobsPlayer.get((Entity)player);
    for (JobType type : JobType.values())
      level.addAndGet(jobsPlayer.getLevel(type)); 
    return level.get();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\commands\GiftCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */