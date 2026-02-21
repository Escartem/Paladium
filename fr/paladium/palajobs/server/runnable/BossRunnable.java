package fr.paladium.palajobs.server.runnable;

import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.entity.boss.EntityJobAlchimistBoss;
import fr.paladium.palajobs.core.entity.boss.EntityJobFarmerBoss;
import fr.paladium.palajobs.core.entity.boss.EntityJobHunterBoss;
import fr.paladium.palajobs.core.entity.boss.EntityJobMinerBoss;
import fr.paladium.palajobs.core.pojo.boss.BossData;
import fr.paladium.palajobs.core.pojo.boss.BossState;
import fr.paladium.palajobs.core.utils.ChatUtils;
import fr.paladium.palajobs.core.utils.TimeUtil;
import fr.paladium.palajobs.server.config.ConfigManager;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.palajobs.utils.forge.location.BlockLocation;
import fr.paladium.translate.common.texttotranslate.TTT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class BossRunnable extends Thread {
  private static boolean warned;
  
  public BossRunnable() {
    super(() -> {
          while (true) {
            try {
              Thread.sleep(10000L);
              JobsManager.getInstance().fetchBossData();
              BossData newData = JobsManager.getInstance().getBossData();
              if (newData != null) {
                boolean isServerEvent = Server.is(new ServerType[] { ServerType.WARZONE }, );
                if (newData.state != BossState.SPAWNING)
                  warned = false; 
                if (!warned && newData.state == BossState.SPAWNING) {
                  warned = true;
                  ChatUtils.broadcastCenteredMessage("");
                  ChatUtils.broadcastCenteredMessage("§8§m---------------------------------");
                  ChatUtils.broadcastCenteredMessage("");
                  ChatUtils.broadcastCenteredTTTMessage("message.jobs.boss.spawning.line.1", new Object[0]);
                  for (Object playerObj : (MinecraftServer.func_71276_C().func_130014_f_()).field_73010_i) {
                    EntityPlayer player = (EntityPlayer)playerObj;
                    ChatUtils.sendCenteredTTTMessage(player, "message.jobs.boss.spawning.line.2", new Object[] { newData.type.getPrefix() + "" + TTT.format(player, newData.type.getName(), new Object[0]) });
                  } 
                  ChatUtils.broadcastCenteredMessage("");
                  ChatUtils.broadcastCenteredTTTMessage("message.jobs.boss.spawning.line.3", new Object[0]);
                  ChatUtils.broadcastCenteredTTTMessage("message.jobs.boss.spawning.line.4", new Object[0]);
                  ChatUtils.broadcastCenteredMessage("");
                  ChatUtils.broadcastCenteredMessage("§8§m---------------------------------");
                  ChatUtils.broadcastCenteredMessage("");
                  if (isServerEvent) {
                    EntityJobFarmerBoss entityJobFarmerBoss;
                    World world = MinecraftServer.func_71276_C().func_130014_f_();
                    EntityLivingBase entity = null;
                    if (newData.type == JobType.ALCHEMIST) {
                      EntityJobAlchimistBoss entityJobAlchimistBoss = new EntityJobAlchimistBoss(world);
                    } else if (newData.type == JobType.MINER) {
                      EntityJobMinerBoss entityJobMinerBoss = new EntityJobMinerBoss(world);
                    } else if (newData.type == JobType.HUNTER) {
                      EntityJobHunterBoss entityJobHunterBoss = new EntityJobHunterBoss(world);
                    } else if (newData.type == JobType.FARMER) {
                      entityJobFarmerBoss = new EntityJobFarmerBoss(world);
                    } 
                    BlockLocation location = (BlockLocation)ConfigManager.getInstance().getBosses().get(newData.type);
                    Chunk chunk = world.func_72938_d(location.x, location.z);
                    if (chunk == null || !chunk.field_76636_d || chunk.func_76621_g())
                      chunk = world.func_72863_F().func_73158_c(location.x >> 4, location.z >> 4); 
                    entityJobFarmerBoss.func_70634_a(location.x, location.y, location.z);
                    world.func_72838_d((Entity)entityJobFarmerBoss);
                    System.out.println("[PalaJobs][Boss] Spawning " + newData.type + " at " + ((EntityLivingBase)entityJobFarmerBoss).field_70165_t + ", " + ((EntityLivingBase)entityJobFarmerBoss).field_70163_u + ", " + ((EntityLivingBase)entityJobFarmerBoss).field_70161_v);
                  } 
                  continue;
                } 
                if (newData.state == BossState.SPAWNING && TimeUtil.now() >= newData.timestamp) {
                  (JobsManager.getInstance().getBossData()).state = BossState.SPAWNED;
                  ChatUtils.broadcastCenteredMessage("");
                  ChatUtils.broadcastCenteredMessage("§8§m---------------------------------");
                  ChatUtils.broadcastCenteredMessage("");
                  for (Object playerObj : (MinecraftServer.func_71276_C().func_130014_f_()).field_73010_i) {
                    EntityPlayer player = (EntityPlayer)playerObj;
                    ChatUtils.sendCenteredTTTMessage(player, "message.jobs.boss.spawned.line.1", new Object[] { newData.type.getPrefix() + "" + TTT.format(player, newData.type.getName(), new Object[0]) });
                  } 
                  ChatUtils.broadcastCenteredMessage("");
                  ChatUtils.broadcastCenteredTTTMessage("message.jobs.boss.spawned.line.2", new Object[0]);
                  ChatUtils.broadcastCenteredTTTMessage("message.jobs.boss.spawned.line.3", new Object[0]);
                  ChatUtils.broadcastCenteredMessage("");
                  ChatUtils.broadcastCenteredMessage("§8§m---------------------------------");
                  ChatUtils.broadcastCenteredMessage("");
                  if (isServerEvent) {
                    JobsManager.getInstance().getApi().spawn().execute();
                    JobsManager.getInstance().fetchBossData();
                  } 
                } 
              } 
            } catch (Exception e) {
              e.printStackTrace();
            } 
          } 
        }"PalaJobs/BossAPI");
    start();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\server\runnable\BossRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */