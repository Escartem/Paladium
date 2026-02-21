package fr.paladium.palamod.modules.luckyblock.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.PacketSendChatMessage;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class Reveal extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    (new Thread(() -> {
          try {
            for (int i = 0; i < 5; i++) {
              PlayerUtils.sendMessage((EntityPlayer)player, "§cVotre position sera révélée dans §4" + (5 - i) + " §csecondes");
              Thread.sleep(1000L);
            } 
            PalaMod.getNetwork().sendTo((IMessage)new PacketSendChatMessage("Hi ! Je me trouve en " + (int)player.field_70165_t + " " + (int)player.field_70163_u + " " + (int)player.field_70161_v), player);
          } catch (Exception e) {
            e.printStackTrace();
          } 
        })).start();
  }
  
  public String getName() {
    return "Silence, ça tourne";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 2000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "reveal";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Reveal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */