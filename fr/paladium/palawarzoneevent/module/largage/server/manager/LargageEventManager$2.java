package fr.paladium.palawarzoneevent.module.largage.server.manager;

import fr.paladium.chronos.server.api.async.ChronosCallback;
import fr.paladium.chronos.server.api.pojo.Planned;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palawarzoneevent.module.largage.LargageModule;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

class null implements ChronosCallback<Planned> {
  public void onSuccess(Planned planned) {
    MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §rLes largages ont été détruit !"));
  }
  
  public void onFail(Planned planned, Throwable throwable) {
    LargageModule.logger.error("Impossible d'arrêter l'évènement de largage " + UUIDUtils.toString(LargageEventManager.access$000(LargageEventManager.this)) + " !", new Object[] { throwable });
    MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §rUne erreur est survenue lors de la fin de l'évement largage."));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\server\manager\LargageEventManager$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */