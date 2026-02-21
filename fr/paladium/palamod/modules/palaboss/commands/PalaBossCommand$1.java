package fr.paladium.palamod.modules.palaboss.commands;

import fr.paladium.chronos.server.api.async.ChronosCallback;
import fr.paladium.chronos.server.api.pojo.Planned;
import fr.paladium.palamod.modules.palaboss.PPalaBoss;
import fr.paladium.palamod.modules.palaboss.common.boss.BossAttributes;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import fr.paladium.palamod.modules.palaboss.common.utils.BossLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

class null implements ChronosCallback<Planned> {
  public void onSuccess(Planned event) {
    boss.field_98038_p = true;
    boss.func_70634_a(location.getX(), location.getY(), location.getZ());
    boss.setMain(true);
    world.func_72838_d((Entity)boss);
    PPalaBoss.serverBossData.setActive(true);
    PPalaBoss.serverBossData.setUuid(uuid);
    PPalaBoss.serverBossData.setStartedAt(System.currentTimeMillis());
    PPalaBoss.serverBossData.setBossName(bossAttr.getName());
    PPalaBoss.serverBossData.setLocation(location);
    MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §bLe boss §e" + bossAttr.getName() + " §bvient d'apparaitre en §e" + location.format() + " !"));
  }
  
  public void onFail(Planned event, Throwable error) {
    player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cImpossible de faire apparaitre §e" + bossAttr.getName()));
    error.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\commands\PalaBossCommand$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */