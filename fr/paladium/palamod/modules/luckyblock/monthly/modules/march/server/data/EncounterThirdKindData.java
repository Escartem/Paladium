package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data;

import fr.paladium.palamod.modules.luckyblock.monthly.utils.entity.npc.impl.EntityPlayerNPC;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;

public class EncounterThirdKindData {
  private final UUID playerUniqueId;
  
  private final UUID entityUniqueId;
  
  private int currentDialogId;
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public UUID getEntityUniqueId() {
    return this.entityUniqueId;
  }
  
  public int getCurrentDialogId() {
    return this.currentDialogId;
  }
  
  public EncounterThirdKindData(EntityPlayer player, EntityPlayerNPC npc) {
    this.playerUniqueId = player.func_110124_au();
    this.entityUniqueId = npc.func_110124_au();
    this.currentDialogId = 0;
  }
  
  public void increment() {
    this.currentDialogId++;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\data\EncounterThirdKindData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */