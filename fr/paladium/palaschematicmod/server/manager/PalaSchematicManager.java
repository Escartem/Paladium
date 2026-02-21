package fr.paladium.palaschematicmod.server.manager;

import fr.paladium.palaschematicmod.common.pojo.data.BlockPos;
import fr.paladium.palaschematicmod.common.pojo.schematic.Schematic;
import fr.paladium.palaschematicmod.common.pojo.schematic.SchematicCopySession;
import fr.paladium.palaschematicmod.common.pojo.schematic.SchematicPasteSession;
import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedSchematic;
import fr.paladium.palaschematicmod.common.world.TimedSchematicWorldSavedData;
import fr.paladium.palaschematicmod.server.async.PalaSchematicQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class PalaSchematicManager {
  public static PalaSchematicManager instance;
  
  public static final PalaSchematicQueue SCHEMATIC_QUEUE = new PalaSchematicQueue();
  
  public Map<UUID, SchematicCopySession> cachedSchematicCopySession = new HashMap<>();
  
  public Map<UUID, SchematicCopySession> getCachedSchematicCopySession() {
    return this.cachedSchematicCopySession;
  }
  
  public Map<UUID, SchematicPasteSession> cacheSchematicPasteSession = new HashMap<>();
  
  public Map<UUID, SchematicPasteSession> getCacheSchematicPasteSession() {
    return this.cacheSchematicPasteSession;
  }
  
  public PalaSchematicManager() {
    instance = this;
  }
  
  public static PalaSchematicManager getInstance() {
    if (instance == null)
      new PalaSchematicManager(); 
    return instance;
  }
  
  public static void addTimedSchematic(World world, TimedSchematic timedSchematic) {
    TimedSchematicWorldSavedData instance = TimedSchematicWorldSavedData.get(world);
    instance.getTimedSchematics().add(timedSchematic);
    instance.save(world);
  }
  
  public void setPasteSession(EntityPlayer player, Schematic schematic) {
    UUID uuid = player.func_110124_au();
    SchematicPasteSession session = new SchematicPasteSession();
    session.pos = schematic.updateMinPoint().updateMaxPoint().updateCenter().getCenter();
    this.cacheSchematicPasteSession.put(uuid, session);
  }
  
  public SchematicPasteSession getPasteSession(EntityPlayer player) {
    UUID uuid = player.func_110124_au();
    if (this.cacheSchematicPasteSession.containsKey(uuid))
      return this.cacheSchematicPasteSession.get(uuid); 
    return null;
  }
  
  public SchematicCopySession getOrCreateSession(EntityPlayer player) {
    UUID uuid = player.func_110124_au();
    if (this.cachedSchematicCopySession.containsKey(uuid))
      return this.cachedSchematicCopySession.get(uuid); 
    this.cachedSchematicCopySession.put(uuid, new SchematicCopySession());
    return this.cachedSchematicCopySession.get(uuid);
  }
  
  public boolean setSchematicSession(EntityPlayer player, PlayerInteractEvent event, PlayerInteractEvent.Action action) {
    return setSchematicSession(player, new BlockPos(event.x, event.y, event.z), (action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) ? 1 : 2);
  }
  
  public boolean setSchematicSession(EntityPlayer player, BlockPos pos, int action) {
    SchematicCopySession copySession = getOrCreateSession(player);
    if (action == 1) {
      copySession.firstPos = pos;
    } else if (action == 2) {
      copySession.secondPos = pos;
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\server\manager\PalaSchematicManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */