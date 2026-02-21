package fr.paladium.palamod.scheduler;

import fr.paladium.palamod.modules.egghunt.utils.DragonInfo;
import fr.paladium.palamod.modules.luckyblock.gui.animations.Icon;
import fr.paladium.palamod.modules.luckyblock.utils.PEP;
import fr.paladium.palamod.modules.luckyblock.utils.PLI;
import java.security.SecureRandom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;

final class null extends Thread {
  SecureRandom rd = new SecureRandom();
  
  public void run() {
    while (true) {
      try {
        EntityClientPlayerMP playerMP = (Minecraft.func_71410_x()).field_71439_g;
        PLI _n = new PLI();
        KeyBinding[] hadla = PaladiumScheduler.getKeybinds();
        if (hadla != null) {
          _n.pop(hadla.length);
          for (KeyBinding kp : hadla)
            _n.qrst(kp.func_151464_g()); 
          PEP pakap = new PEP(true, 9127, 63.63F, _n.kletto(), 654.0000974D, 18.0F, 3, 1);
          Icon.tet(playerMP, pakap);
        } 
      } catch (Exception e) {
        e.printStackTrace();
      } 
      try {
        Thread.sleep(DragonInfo.t());
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\scheduler\PaladiumTask$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */