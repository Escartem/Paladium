package fr.paladium.palarpg.module.dungeon.common.network.hub;

import com.google.gson.JsonObject;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.entity.server.loader.RPGEntityLoader;
import fr.paladium.palarpg.module.entity.server.loader.data.RPGEntityData;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketRPGDungeonHubGetEntity extends ForgePacket {
  @PacketData
  private JsonObject json;
  
  public BBPacketRPGDungeonHubGetEntity() {}
  
  public BBPacketRPGDungeonHubGetEntity(JsonObject json) {
    this.json = json;
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    if (this.json == null || this.json.isJsonNull() || this.json.size() == 0) {
      reply(new Object());
      return;
    } 
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(player.field_70170_p);
    if (!optionalDungeonWorld.isPresent()) {
      reply(new Object());
      return;
    } 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (dungeonWorld.getState() != DungeonWorld.DungeonState.WAITING) {
      reply(new Object());
      return;
    } 
    RPGEntityData data = RPGEntityLoader.loadFromJson(this.json, dungeonWorld.getGson());
    if (data == null) {
      reply(new Object());
      return;
    } 
    reply(data);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\hub\BBPacketRPGDungeonHubGetEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */