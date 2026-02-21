package fr.paladium.palamod.modules.troll.modules;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientFreeze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class TrollFreezeModule extends ATrollModule {
  public boolean perform(EntityPlayer player, EntityPlayerMP target, String[] arguments) {
    PSpellsV2.network.sendTo((IMessage)new PacketClientFreeze(true), target);
    player.func_145747_a((IChatComponent)new ChatComponentText("§8[§eTroll§8][§7Freeze§8] » §aLe joueur §7" + target
          .func_70005_c_() + " §7à été freeze"));
    return true;
  }
  
  public String getName() {
    return "freeze";
  }
  
  public String getDescription() {
    return "Permet d'immobiliser le joueur sans qu'il ne le sache";
  }
  
  public String getUsage() {
    return "freeze";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\troll\modules\TrollFreezeModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */