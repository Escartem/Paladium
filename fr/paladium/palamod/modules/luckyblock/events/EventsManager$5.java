package fr.paladium.palamod.modules.luckyblock.events;

import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

class null implements Runnable {
  public void run() {
    e.player.func_70634_a(pos.getX(), pos.getY(), pos.getZ());
    e.player.func_146105_b((IChatComponent)new ChatComponentText("§eVous avez respawn à votre sac de couchage !"));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\EventsManager$5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */