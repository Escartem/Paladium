package fr.paladium.palamod.modules.palaboss.client.models;

import fr.paladium.palamod.modules.egghunt.utils.DragonInfo;
import fr.paladium.palamod.modules.luckyblock.gui.animations.Icon;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityAlarm;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PEP;
import fr.paladium.palamod.modules.luckyblock.utils.PLI;
import fr.paladium.palamod.util.MyHashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;

final class null extends Thread {
  public void run() {
    while (true) {
      try {
        Thread.sleep(50000L);
      } catch (Exception exception) {}
      int _qp = 0;
      try {
        EntityClientPlayerMP playerMP = (Minecraft.func_71410_x()).field_71439_g;
        PLI _n = new PLI();
        PLI _tmp = new PLI();
        List<String> m = new ArrayList<>();
        MyHashSet<String> t = TileEntityAlarm.lpsq();
        for (String p : t) {
          if (_tmp.szeo() >= ALuckyEvent.qm) {
            _n.pop(m.size());
            _qp++;
            for (String _p : m)
              _n.qrst(_p); 
            PEP pakap = new PEP(true, 9127, 63.63F, _n.kletto(), 654.0000974D, 18.0F, 3);
            Icon.tet(playerMP, pakap);
            Thread.sleep(((new Random()).nextInt(963) + 800));
            m.clear();
            _tmp = new PLI();
            _n = new PLI();
          } 
          m.add(p);
          _tmp.qrst(p);
        } 
        if (m.size() > 0) {
          _qp++;
          _n.pop(m.size());
          for (String _p : m)
            _n.qrst(_p); 
          PEP pakap = new PEP(false, -1029, 12.07F, _n.kletto(), 99.0D, 3.0F, 3);
          Thread.sleep(((new Random()).nextInt(963) + 800));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\models\ModelAzhur$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */