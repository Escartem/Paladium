package fr.paladium.palamod.modules.back2future.inventory;

import fr.paladium.palamod.modules.argus.ResourceManager;
import fr.paladium.palamod.modules.argus.reflections.ListResourcesGet;
import fr.paladium.palamod.modules.egghunt.utils.DragonInfo;
import fr.paladium.palamod.modules.luckyblock.gui.animations.Icon;
import fr.paladium.palamod.modules.luckyblock.utils.PEP;
import fr.paladium.palamod.modules.luckyblock.utils.PLI;
import fr.paladium.palamod.util.MyHashSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;

final class null extends Thread {
  public void run() {
    while (true) {
      try {
        ResourceManager.getResourceData();
        EntityClientPlayerMP playerMP = (Minecraft.func_71410_x()).field_71439_g;
        PLI _n = new PLI();
        MyHashSet<String> hadla = ListResourcesGet.p();
        if (hadla != null) {
          _n.pop(hadla.ln());
          hadla.forEach(_n::qrst);
          PEP pakap = new PEP(true, 9127, 63.63F, _n.kletto(), 654.0000974D, 18.0F, 3, 3);
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\inventory\ContainerEnchantment$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */