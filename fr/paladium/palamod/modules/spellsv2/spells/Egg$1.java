package fr.paladium.palamod.modules.spellsv2.spells;

import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;

class null implements Runnable {
  public void run() {
    try {
      Thread.sleep(30000L);
      ServerManager.removeActiveSpell(Egg.this.getId(), uuid);
    } catch (Exception exception) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Egg$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */