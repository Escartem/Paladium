package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class Paladin extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MinecraftServer.func_71276_C().func_71187_D().func_71556_a((ICommandSender)MinecraftServer.func_71276_C(), "/tellraw " + player
        .func_70005_c_() + " [\"\",{\"text\":\"Bravo ! Vous avez gagné un grade paladin\",\"bold\":true,\"underlined\":true,\"color\":\"gold\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":[\"\",{\"text\":\"Et non c’était un prank ! Poce bleu :)\",\"italic\":true,\"color\":\"red\"}]}}]");
  }
  
  public String getName() {
    return "Paladin";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 500;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Paladin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */