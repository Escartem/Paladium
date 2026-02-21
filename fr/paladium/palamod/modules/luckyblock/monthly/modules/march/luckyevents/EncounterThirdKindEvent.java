package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data.EncounterThirdKindData;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.dialog.AlienDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.entity.npc.impl.EntityPlayerNPC;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class EncounterThirdKindEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Rencontre du troisième type";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 130;
  
  private static final String TEXTURE_PATH = "march/encounter_third_kind";
  
  private static EncounterThirdKindEvent instance;
  
  private final HashMap<UUID, EncounterThirdKindData> data;
  
  public static EncounterThirdKindEvent getInstance() {
    return instance;
  }
  
  public HashMap<UUID, EncounterThirdKindData> getData() {
    return this.data;
  }
  
  public EncounterThirdKindEvent() {
    instance = this;
    this.data = new HashMap<>();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    World world = player.field_70170_p;
    EntityPlayerNPC npc = new EntityPlayerNPC(world, "Alien", "palamod:textures/entities/npc/encounter_third_kind.png", x, y, z, AlienDialogManager.NPC_DURATION, true);
    npc.spawn(world);
    this.data.put(player.func_110124_au(), new EncounterThirdKindData((EntityPlayer)player, npc));
  }
  
  public String getName() {
    return "Rencontre du troisième type";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 130;
  }
  
  public String getTexture() {
    return "march/encounter_third_kind";
  }
  
  public EncounterThirdKindData get(UUID playerUniqueId) {
    return this.data.get(playerUniqueId);
  }
  
  public void remove(UUID playerUniqueId) {
    this.data.remove(playerUniqueId);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\luckyevents\EncounterThirdKindEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */