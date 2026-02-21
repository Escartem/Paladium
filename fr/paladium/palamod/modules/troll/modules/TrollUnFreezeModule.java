package fr.paladium.palamod.modules.troll.modules;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientFreeze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class TrollUnFreezeModule extends ATrollModule {
  public boolean perform(EntityPlayer player, EntityPlayerMP target, String[] arguments) {
    PSpellsV2.network.sendTo((IMessage)new PacketClientFreeze(false), target);
    player.func_145747_a((IChatComponent)new ChatComponentText("§8[§eTroll§8][§7UnFreeze§8] » §cLe joueur §7" + target
          .func_70005_c_() + " §7à été unfreeze"));
    return true;
  }
  
  public String getName() {
    return "unfreeze";
  }
  
  public String getDescription() {
    return "Permet de rétablir les mouvements du joueur";
  }
  
  public String getUsage() {
    return "unfreeze";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\troll\modules\TrollUnFreezeModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */