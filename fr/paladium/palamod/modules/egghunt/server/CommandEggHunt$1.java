package fr.paladium.palamod.modules.egghunt.server;

import fr.paladium.chronos.server.api.async.ChronosCallback;
import fr.paladium.chronos.server.api.pojo.Planned;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.modules.egghunt.common.entity.EntityCustomDragon;
import fr.paladium.palamod.modules.egghunt.utils.PERegisterer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

class null implements ChronosCallback<Planned> {
  public void onSuccess(Planned event) {
    PEggHunt.data.setActive(true);
    PEggHunt.data.setUuid(uuid);
    PEggHunt.data.setStartedAt(System.currentTimeMillis());
    EntityCustomDragon dragon = new EntityCustomDragon(world);
    dragon.func_70634_a(PEggHunt.config.getDragonPosition().getX(), PEggHunt.config.getDragonPosition().getY(), PEggHunt.config.getDragonPosition().getZ());
    dragon.setHome(PEggHunt.config.getDragonPosition().getX(), PEggHunt.config.getDragonPosition().getY(), PEggHunt.config.getDragonPosition().getZ());
    world.func_72838_d((Entity)dragon);
    world.func_147465_d(PEggHunt.config.getEggPosition().getX(), PEggHunt.config.getEggPosition().getY(), PEggHunt.config.getEggPosition().getZ(), PERegisterer.egg, 6, 2);
    MinecraftServer.func_71276_C().func_130014_f_().func_82739_e(1018, PEggHunt.config.getDragonPosition().getX(), PEggHunt.config.getDragonPosition().getY(), PEggHunt.config.getDragonPosition().getZ(), 0);
    MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§bLe dragon vient d'apparaitre en §e" + PEggHunt.config.getDragonPosition().format() + " §b! §8(/egg info)"));
  }
  
  public void onFail(Planned event, Throwable error) {
    player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cImpossible de faire apparaitre le dragon du egghunt !"));
    error.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\server\CommandEggHunt$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */