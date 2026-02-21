package fr.paladium.palamod.modules.spellsv2.network.client;

import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;

class null implements Runnable {
  public void run() {
    try {
      Thread.sleep(10000L);
      ClientManager.setOmniscience(null);
    } catch (Exception exception) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientOpenFakeInventory$Handler$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */