package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.dialogs;

import java.util.UUID;

public class IndexedDialog {
  private final UUID entityUniqueId;
  
  private final UUID playerUniqueId;
  
  private int index;
  
  private boolean declined;
  
  public void setIndex(int index) {
    this.index = index;
  }
  
  public void setDeclined(boolean declined) {
    this.declined = declined;
  }
  
  public UUID getEntityUniqueId() {
    return this.entityUniqueId;
  }
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public int getIndex() {
    return this.index;
  }
  
  public boolean isDeclined() {
    return this.declined;
  }
  
  public IndexedDialog(UUID playerUniqueId, UUID entityUniqueId) {
    this.playerUniqueId = playerUniqueId;
    this.entityUniqueId = entityUniqueId;
    this.index = 0;
    this.declined = true;
  }
  
  public void increment() {
    this.index++;
    this.declined = true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\dialogs\XavierDialogManager$IndexedDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */