package fr.paladium.palacommunityevent.server.commands;

import fr.paladium.palacommunityevent.common.extended.PalaCommunityEventEEP;
import fr.paladium.palacommunityevent.common.extended.data.CommunityEventData;
import fr.paladium.palacommunityevent.common.pojo.CommunityEvent;
import fr.paladium.palacommunityevent.server.managers.PalaCommunityEventManager;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palaforgeutils.lib.command.Command;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class PalaCommunityEventCommand extends Command {
  public boolean perform(ICommandSender sender, String[] args) {
    if (!(sender instanceof EntityPlayer))
      return true; 
    EntityPlayer p = (EntityPlayer)sender;
    if (p.field_70170_p.field_72995_K)
      return true; 
    boolean hasPermission = BukkitUtils.hasPermission(p.func_110124_au(), "palacommunityevent.admin");
    if (args.length == 2) {
      String arg1 = args[0];
      String arg2 = args[1];
      if (arg1.equalsIgnoreCase("gui")) {
        String event = arg2;
        CommunityEvent communityEvent = PalaCommunityEventManager.getInstance().getCommunityEventById(event);
        if (communityEvent == null) {
          p.func_146105_b((IChatComponent)new ChatComponentText("§cL'event communautaire §c§l" + event + " §cn'existe pas."));
          return true;
        } 
        return PalaCommunityEventManager.getInstance().openGui(p, communityEvent);
      } 
      if (arg1.equalsIgnoreCase("deposit")) {
        String event = arg2;
        CommunityEvent communityEvent = PalaCommunityEventManager.getInstance().getCommunityEventById(event);
        if (communityEvent == null) {
          p.func_146105_b((IChatComponent)new ChatComponentText("§cL'event communautaire §c§l" + event + " §cn'existe pas."));
          return true;
        } 
        return PalaCommunityEventManager.getInstance().openGuiDeposit(p, communityEvent);
      } 
      if (hasPermission && arg1.equalsIgnoreCase("reset")) {
        PalaCommunityEventEEP eep = PalaCommunityEventEEP.get((Entity)p);
        CommunityEventData data = eep.getDataOf(arg2);
        if (data == null) {
          p.func_146105_b((IChatComponent)new ChatComponentText("§cL'event communautaire §c§l" + arg2 + " §cn'existe pas."));
          return true;
        } 
        p.func_146105_b((IChatComponent)new ChatComponentText("§aVous avez bien reset votre event " + arg2 + "."));
        data.reset();
      } 
    } else if (args.length == 3) {
      String arg1 = args[0];
      String arg2 = args[1];
      String arg3 = args[2];
      if (arg1.equalsIgnoreCase("claim")) {
        PalaCommunityEventEEP eep = PalaCommunityEventEEP.get((Entity)p);
        CommunityEventData data = eep.getDataOf(arg2);
        if (data == null) {
          p.func_146105_b((IChatComponent)new ChatComponentText("§cL'event communautaire §c§l" + arg2 + " §cn'existe pas."));
          return true;
        } 
        data.claimCommonRewards(p, arg3);
      } 
    } 
    return true;
  }
  
  public String getCommandUsage(ICommandSender sender, String[] args) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\server\commands\PalaCommunityEventCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */