package fr.paladium.palamod.modules.luckyblock.commands;

import fr.paladium.palajobs.utils.forge.player.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import java.util.Arrays;
import java.util.Optional;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LuckyBlockTestCommand extends CommandBase {
  public String func_71517_b() {
    return "lbtest";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "§cUsage: /lbtest <id>";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (!(sender instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)sender;
    if (player.field_70170_p.field_72995_K)
      return; 
    if (!hasPermission(player, "paladium.luckyblock.testing"))
      return; 
    if (args.length < 1) {
      PlayerUtils.sendMessage(player, new String[] { "§cUsage: /lbtest <id>" });
      return;
    } 
    String name = StringUtils.join((Object[])args, " ");
    ALuckyEvent event = getById(name);
    if (event == null) {
      PlayerUtils.sendMessage(player, new String[] { "§cThis event doesn't exist." });
      return;
    } 
    event.perform((EntityPlayerMP)player, 
        
        MathHelper.func_76128_c(player.field_70165_t), MathHelper.func_76128_c(player.field_70163_u), MathHelper.func_76128_c(player.field_70161_v));
  }
  
  private ALuckyEvent getById(String id) {
    Optional<LuckyEvents> event = Arrays.<LuckyEvents>stream(LuckyEvents.values()).filter(e -> e.getEvent().getName().equalsIgnoreCase(id)).findFirst();
    return event.<ALuckyEvent>map(LuckyEvents::getEvent).orElse(null);
  }
  
  private boolean hasPermission(EntityPlayer player, String permission) {
    try {
      Player bukkitPlayer = Bukkit.getPlayer(player.func_110124_au());
      return bukkitPlayer.hasPermission(permission);
    } catch (Exception e) {
      return true;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\commands\LuckyBlockTestCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */