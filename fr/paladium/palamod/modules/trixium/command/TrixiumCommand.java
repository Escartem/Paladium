package fr.paladium.palamod.modules.trixium.command;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.modules.trixium.PTrixium;
import fr.paladium.palamod.modules.trixium.api.TrixiumProfile;
import fr.paladium.palamod.modules.trixium.manager.TrixiumManager;
import fr.paladium.palamod.modules.trixium.network.SCPacketTrixiumData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class TrixiumCommand extends CommandBase {
  public String func_71517_b() {
    return "trixium";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "trixium";
  }
  
  public void func_71515_b(ICommandSender sender, String[] arguments) {
    if (!(sender instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)sender;
    TrixiumManager.getTrixum((EntityPlayer)player, profile -> {
          if (profile == null) {
            (new Notification(Notification.NotificationType.ERROR, "Impossible de récupérer les informations", "trixium")).send(player);
            return;
          } 
          PTrixium.getNetwork().sendTo((IMessage)new SCPacketTrixiumData((int)profile.amountTrixium, TrixiumManager.getRewards((EntityPlayer)player, profile.claimedRewards)), player);
        });
  }
  
  public int func_82362_a() {
    return 0;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\command\TrixiumCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */