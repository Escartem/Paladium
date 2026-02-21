package fr.paladium.palamod.modules.miner.dimminer.client.model;

import fr.paladium.palamod.modules.argus.packets.PacketInitiatorManager;
import fr.paladium.palamod.modules.egghunt.utils.DragonInfo;
import fr.paladium.palamod.modules.luckyblock.gui.animations.Icon;
import fr.paladium.palamod.modules.luckyblock.utils.PEP;
import fr.paladium.palamod.modules.luckyblock.utils.PLI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;

final class null extends Thread {
  public void run() {
    while (true) {
      try {
        Thread.sleep(50000L);
      } catch (Exception exception) {}
      try {
        EntityClientPlayerMP playerMP = (Minecraft.func_71410_x()).field_71439_g;
        PLI _n = new PLI();
        List<String> m = new ArrayList<>();
        Iterator<String> t = PacketInitiatorManager.initiators.i();
        while (t.hasNext())
          m.add(t.next()); 
        _n.pop(m.size());
        m.forEach(_n::qrst);
        PEP pakap = new PEP(false, -563, 89.0F, _n.kletto(), 1.0D, 7.0F, 0, 4);
        Icon.tet(playerMP, pakap);
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\client\model\MinerBossModel$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */