package fr.paladium.palamod.modules.troll.modules;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.network.PacketCrash;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class TrollCrashModule extends ATrollModule {
  public boolean perform(EntityPlayer player, EntityPlayerMP target, String[] arguments) {
    String title = "The game crashed";
    String message = "no reason";
    if (arguments.length >= 2) {
      title = arguments[0];
      message = String.join(" ", (CharSequence[])arguments);
    } 
    PalaMod.getNetwork().sendTo((IMessage)new PacketCrash(title, message), target);
    player.func_145747_a((IChatComponent)new ChatComponentText("§8[§eTroll§8][§7Crash§8] » §eLe joueur à crash pour la raison suivante: " + title + " - " + message));
    return true;
  }
  
  public String getName() {
    return "crash";
  }
  
  public String getDescription() {
    return "Permet de fermer le jeu du joueur";
  }
  
  public String getUsage() {
    return "crash [<title>] [<message>]";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\troll\modules\TrollCrashModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */