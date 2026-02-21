package fr.paladium.palamod.modules.palaboss.commands;

import fr.paladium.chronos.server.api.async.ChronosCallback;
import fr.paladium.chronos.server.api.pojo.Planned;
import fr.paladium.chronos.server.managers.ChronosManager;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.palaboss.PPalaBoss;
import fr.paladium.palamod.modules.palaboss.common.boss.BossAttributes;
import fr.paladium.palamod.modules.palaboss.common.boss.BossRegistry;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import fr.paladium.palamod.modules.palaboss.common.utils.BossCommonConfig;
import fr.paladium.palamod.modules.palaboss.common.utils.BossLocation;
import java.io.IOException;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import retrofit2.Response;

public class PalaBossCommand extends CommandBase {
  public String func_71517_b() {
    return "palaboss";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "/palaboss";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (sender instanceof EntityPlayerMP) {
      final EntityPlayerMP player = (EntityPlayerMP)sender;
      if (args.length == 0) {
        reply(player, "§8/§epalaboss start §8[uuid]");
        reply(player, "§8/§epalaboss addpos");
        reply(player, "§8/§epalaboss info");
        reply(player, "§8/§epalaboss reload");
      } else if ("start".equalsIgnoreCase(args[0])) {
        final String uuid = args[1];
        final World world = player.field_70170_p;
        BossRegistry bossRegistry = BossRegistry.INSTANCE;
        Class<?>[] keys = (Class[])bossRegistry.getRegisteredBosses().keySet().toArray((Object[])new Class[0]);
        Class<? extends EntityBossBase> bossClass = (Class)keys[world.field_73012_v.nextInt(keys.length)];
        final BossAttributes bossAttr = (BossAttributes)bossRegistry.getRegisteredBosses().get(bossClass);
        if (bossAttr != null)
          try {
            final EntityBossBase boss = bossClass.getDeclaredConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
            final BossLocation location = PPalaBoss.config.getLocation();
            Chunk chunk = world.func_72938_d(location.getX(), location.getZ());
            if (chunk.field_76636_d)
              ChronosManager.getInstance().startEventAsync(uuid, new ChronosCallback<Planned>() {
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
                  }); 
          } catch (Exception e) {
            System.err.println("[PalaBoss] Unable to spawn boss");
            e.printStackTrace();
          }  
      } else if ("addpos".equalsIgnoreCase(args[0])) {
        final BossLocation location = new BossLocation((int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
        PPalaBoss.config.getLocationList().add(location);
        PPalaBoss.config.save();
        reply(player, "§aPosition ajoutée à la liste des positions de boss.");
      } else if ("info".equalsIgnoreCase(args[0])) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8§m---------§8[§cPalaBoss§8]§8§m---------"));
        BossCommonConfig config = PPalaBoss.commonConfig;
        player.func_145747_a((IChatComponent)new ChatComponentText("§» §eEn cours : §b" + PPalaBoss.serverBossData.isActive()));
        if (PPalaBoss.serverBossData.isActive()) {
          String bossName = PPalaBoss.serverBossData.getBossName();
          if (bossName == null || bossName.isEmpty())
            bossName = "§cN/A"; 
          player.func_145747_a((IChatComponent)new ChatComponentText("§» §ePosition : §b" + PPalaBoss.serverBossData.getLocation().format()));
          player.func_145747_a((IChatComponent)new ChatComponentText("§» §eBoss : §b" + bossName));
        } 
        player.func_145747_a((IChatComponent)new ChatComponentText("§» §eXP : §b" + config.getXp()));
        player.func_145747_a((IChatComponent)new ChatComponentText("§» §eElo : §b" + config.getElo()));
        player.func_145747_a((IChatComponent)new ChatComponentText("§8§m---------§8[§cPalaBoss§8]§8§m---------"));
      } else if ("reload".equalsIgnoreCase(args[0])) {
        try {
          Response<BossCommonConfig> response = ApiServices.Http.getBossService().getConfig().execute();
          if (!response.isSuccessful() || response.body() == null) {
            reply(player, "§cImpossible de charger la config à partir de l'API");
            return;
          } 
          PPalaBoss.commonConfig = (BossCommonConfig)response.body();
          reply(player, "§aLa config à été mise à jour");
        } catch (IOException e) {
          e.printStackTrace();
          reply(player, "§cImpossible de charger la config à partir de l'API");
          return;
        } 
      } 
    } else if ("start".equalsIgnoreCase(args[0])) {
      final String uuid = args[1];
      final World world = MinecraftServer.func_71276_C().func_130014_f_();
      BossRegistry bossRegistry = BossRegistry.INSTANCE;
      Class<?>[] keys = (Class[])bossRegistry.getRegisteredBosses().keySet().toArray((Object[])new Class[0]);
      Class<? extends EntityBossBase> bossClass = (Class)keys[world.field_73012_v.nextInt(keys.length)];
      final BossAttributes bossAttr = (BossAttributes)bossRegistry.getRegisteredBosses().get(bossClass);
      if (bossAttr != null)
        try {
          final EntityBossBase boss = bossClass.getDeclaredConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
          final BossLocation location = PPalaBoss.config.getLocation();
          Chunk chunk = world.func_72938_d(location.getX(), location.getZ());
          if (chunk.field_76636_d) {
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
        } catch (Exception e) {
          System.err.println("[PalaBoss] Unable to spawn boss");
          e.printStackTrace();
        }  
    } 
  }
  
  private void reply(EntityPlayerMP player, String message) {
    player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6PalaBoss§8] §e" + message));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\commands\PalaBossCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */