package fr.paladium.palamod.modules.egghunt.server;

import fr.paladium.chronos.server.api.async.ChronosCallback;
import fr.paladium.chronos.server.api.pojo.Planned;
import fr.paladium.chronos.server.managers.ChronosManager;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.modules.egghunt.common.config.EggHuntConfig;
import fr.paladium.palamod.modules.egghunt.common.entity.EntityCustomDragon;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntCommonConfig;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntData;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntPlayerEggInput;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntPlayerNameInput;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntStatus;
import fr.paladium.palamod.modules.egghunt.utils.PERegisterer;
import fr.paladium.palamod.util.DurationConverter;
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

public class CommandEggHunt extends CommandBase {
  public String func_71517_b() {
    return "egghunt";
  }
  
  public String func_71518_a(ICommandSender p_71518_1_) {
    return "/egghunt";
  }
  
  public int func_82362_a() {
    return 0;
  }
  
  public boolean func_71519_b(ICommandSender p_71519_1_) {
    return true;
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (sender instanceof EntityPlayerMP) {
      final EntityPlayerMP player = (EntityPlayerMP)sender;
      if (args.length == 0) {
        reply(player, "§8/§eegghunt start §8[uuid]");
        reply(player, "§8/§eegghunt info");
        reply(player, "§8/§eegghunt reload");
      } else if ("start".equalsIgnoreCase(args[0])) {
        final String uuid = args[1];
        final World world = player.field_70170_p;
        Chunk chunk = world.func_72938_d(PEggHunt.config.getDragonPosition().getX(), PEggHunt.config.getDragonPosition().getZ());
        if (chunk.field_76636_d) {
          try {
            ApiServices.Http.getEggHuntService().setOwner(new EggHuntPlayerEggInput(null, null, null)).execute();
            ApiServices.Http.getEggHuntService().setKiller(new EggHuntPlayerNameInput(null, null)).execute();
          } catch (IOException e) {
            e.printStackTrace();
            return;
          } 
          ChronosManager.getInstance().startEventAsync(uuid, new ChronosCallback<Planned>() {
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
              });
        } 
      } else if ("info".equalsIgnoreCase(args[0])) {
        EggHuntStatus status = PEggHunt.status;
        EggHuntConfig config = PEggHunt.config;
        EggHuntData data = PEggHunt.data;
        player.func_145747_a((IChatComponent)new ChatComponentText("§8§m---------§8[§cEggHunt§8]§8§m---------"));
        if (status == null || data == null) {
          player.func_145747_a((IChatComponent)new ChatComponentText("§cActualisation en cours..."));
        } else if (data.isActive()) {
          player.func_145747_a((IChatComponent)new ChatComponentText("§8» §eEgg Default Position : §b" + config.getEggPosition().format()));
          player.func_145747_a((IChatComponent)new ChatComponentText("§8» §eEgg Last Position : §b" + ((status.getCurrentPosition() == null) ? "§cAucune" : status.getCurrentPosition().format())));
          player.func_145747_a((IChatComponent)new ChatComponentText("§8» §eDragon Position : §b" + config.getDragonPosition().format()));
          player.func_145747_a((IChatComponent)new ChatComponentText("§8» §eTueur : §b" + ((status.getDragonKiller() == null || status.getDragonKiller().isEmpty()) ? "§cAucun" : status.getDragonKiller())));
          player.func_145747_a((IChatComponent)new ChatComponentText("§8» §ePorteur : §b" + ((status.getEggOwner() == null || status.getEggOwner().isEmpty()) ? "§cAucun" : status.getEggOwner())));
          player.func_145747_a((IChatComponent)new ChatComponentText("§8» §eEn cours depuis : §b" + DurationConverter.fromMillisToString(System.currentTimeMillis() - data.getStartedAt())));
          player.func_145747_a((IChatComponent)new ChatComponentText("§8» §eFin dans : §b" + DurationConverter.fromMillisToString((PEggHunt.data.isEndEvent() ? 1200000L : 3600000L) - System.currentTimeMillis() - status.getDragonDeathTime())));
        } else {
          player.func_145747_a((IChatComponent)new ChatComponentText("§» §eProchaine Egg Position : §b" + config.getEggPosition().format()));
          player.func_145747_a((IChatComponent)new ChatComponentText("§» §eProchaine Dragon Position : §b" + config.getDragonPosition().format()));
        } 
        player.func_145747_a((IChatComponent)new ChatComponentText("§8§m---------§8[§cEggHunt§8]§8§m---------"));
      } else if ("reload".equalsIgnoreCase(args[0])) {
        try {
          Response<EggHuntCommonConfig> response = ApiServices.Http.getEggHuntService().getConfig().execute();
          if (!response.isSuccessful() || response.body() == null) {
            reply(player, "§cImpossible de charger la config à partir de l'API");
            return;
          } 
          PEggHunt.commonConfig = (EggHuntCommonConfig)response.body();
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
      Chunk chunk = world.func_72938_d(PEggHunt.config.getDragonPosition().getX(), PEggHunt.config.getDragonPosition().getZ());
      if (chunk.field_76636_d) {
        try {
          ApiServices.Http.getEggHuntService().setOwner(new EggHuntPlayerEggInput(null, null, null)).execute();
          ApiServices.Http.getEggHuntService().setKiller(new EggHuntPlayerNameInput(null, null)).execute();
        } catch (IOException e) {
          e.printStackTrace();
          return;
        } 
        PEggHunt.data.setActive(true);
        PEggHunt.data.setUuid(uuid);
        PEggHunt.data.setStartedAt(System.currentTimeMillis());
        EntityCustomDragon dragon = new EntityCustomDragon(world);
        dragon.func_70634_a(PEggHunt.config.getDragonPosition().getX(), PEggHunt.config.getDragonPosition().getY(), PEggHunt.config.getDragonPosition().getZ());
        dragon.setHome(PEggHunt.config.getDragonPosition().getX(), PEggHunt.config.getDragonPosition().getY(), PEggHunt.config.getDragonPosition().getZ());
        world.func_72838_d((Entity)dragon);
        world.func_147465_d(PEggHunt.config.getEggPosition().getX(), PEggHunt.config.getEggPosition().getY(), PEggHunt.config.getEggPosition().getZ(), PERegisterer.egg, 6, 2);
        MinecraftServer.func_71276_C().func_130014_f_().func_82739_e(1018, PEggHunt.config.getDragonPosition().getX(), PEggHunt.config.getDragonPosition().getY(), PEggHunt.config.getDragonPosition().getZ(), 0);
        MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§bLe dragon vient d'apparaitre en §e" + PEggHunt.config.getDragonPosition().format() + " §b!"));
      } 
    } 
  }
  
  private void reply(EntityPlayerMP player, String message) {
    player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§e" + message));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\server\CommandEggHunt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */