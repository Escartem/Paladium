package fr.paladium.palawarzoneevent.module.largage.server.manager;

import fr.paladium.chronos.server.api.async.ChronosCallback;
import fr.paladium.chronos.server.api.pojo.Planned;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palawarzoneevent.module.largage.LargageModule;
import fr.paladium.palawarzoneevent.module.largage.common.entity.EntityLargage;
import fr.paladium.palawarzoneevent.module.largage.server.config.LargageConfig;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

class null implements ChronosCallback<Planned> {
  public void onSuccess(Planned planned) {
    randomSpawnPoints.forEach(sp -> {
          EntityLargage entity = new EntityLargage(world, sp.getX(), y + LargageEventManager.access$300().nextInt(21), sp.getZ());
          Chunk chunk = world.func_72938_d((int)x, (int)z);
          if (!chunk.field_76636_d)
            world.func_72863_F().func_73158_c((int)x >> 4, (int)z >> 4); 
          world.func_72838_d((Entity)entity);
          LargageEventManager.access$400(LargageEventManager.this).add(entity.func_110124_au());
        });
    LargageEventManager.access$002(LargageEventManager.this, uuid);
    LargageEventManager.access$102(LargageEventManager.this, true);
    LargageEventManager.access$202(LargageEventManager.this, spawnPoint);
    LargageEventManager.this.sendLargageInfo();
  }
  
  public void onFail(Planned planned, Throwable throwable) {
    LargageModule.logger.error("Impossible de démarré l'évènement de largage " + UUIDUtils.toString(LargageEventManager.access$000(LargageEventManager.this)) + " !", new Object[] { throwable });
    MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §rUne erreur est survenue avec l'évènement largage, celui-ci est donc annulé."));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\server\manager\LargageEventManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */