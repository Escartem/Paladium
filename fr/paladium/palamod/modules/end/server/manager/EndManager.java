package fr.paladium.palamod.modules.end.server.manager;

import fr.paladium.factions.api.faction.FactionEloChangeReason;
import fr.paladium.factions.core.player.Player;
import fr.paladium.factions.core.player.PlayerController;
import fr.paladium.helios.module.title.ModuleTitle;
import fr.paladium.helios.module.title.utils.MinecraftTitle;
import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.back2future.ModItems;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.modules.egghunt.common.entity.EntityCustomDragon;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntPlayerEggInput;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntPlayerNameInput;
import fr.paladium.palamod.modules.egghunt.utils.PERegisterer;
import fr.paladium.palamod.modules.end.PEnd;
import fr.paladium.palamod.modules.end.common.entity.EntityEventEndCrystal;
import fr.paladium.palamod.modules.end.server.config.EndConfig;
import fr.paladium.palamod.modules.end.server.proxy.ServerProxy;
import fr.paladium.palamod.modules.end.server.utils.ChatUtils;
import fr.paladium.palamod.modules.end.server.utils.EndData;
import fr.paladium.palamod.modules.end.server.utils.EndState;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palamod.util.BlockLocation;
import fr.paladium.worldguardbridge.common.dto.flag.impl.StateFlag;
import fr.paladium.worldguardbridge.common.manager.WGManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class EndManager {
  private static EndManager instance;
  
  private final List<EntityEventEndCrystal> crystals;
  
  private final Map<EntityPlayer, Integer> points;
  
  private EntityCustomDragon dragon;
  
  public EndManager() {
    instance = this;
    this.crystals = new ArrayList<>();
    this.points = new ConcurrentHashMap<>();
  }
  
  public void start(String uuid) {
    ServerProxy proxy = PEnd.getServer();
    EndData data = proxy.getData();
    EndConfig config = proxy.getConfig();
    World world = MinecraftServer.func_71276_C().func_130014_f_();
    data.setUuid(uuid);
    data.setState(EndState.PROCESS);
    this.dragon = null;
    this.points.clear();
    this.crystals.clear();
    for (BlockLocation location : config.crystals) {
      EntityEventEndCrystal entity = new EntityEventEndCrystal(world, location.getX() + 0.5D, location.getY(), location.getZ() + 0.5D);
      this.crystals.add(entity);
      Chunk chunk = world.func_72938_d(location.getX(), location.getZ());
      if (!chunk.field_76636_d)
        world.func_72863_F().func_73158_c(location.getX() >> 4, location.getZ() >> 4); 
      entity.field_98038_p = true;
      if (!world.func_72838_d((Entity)entity)) {
        this.crystals.remove(entity);
        throw new RuntimeException("Failed to spawn End Crystal at " + location.getX() + ", " + location.getY() + ", " + location.getZ() + ". Please check the chunk is loaded and the position is valid.");
      } 
    } 
    setBlocks(world, config.hole, Blocks.field_150384_bq);
    setBlocks(world, config.portal, Blocks.field_150350_a);
    setBlocks(world, config.endportal, Blocks.field_150350_a);
    setBlocks(world, config.egg, Blocks.field_150350_a);
    PEnd.getServer().getData().setState(EndState.CRYSTALS);
    ChatComponentText text = new ChatComponentText("§8[§dEnd§8] §eLe portail de l'end vient de s'ouvrir dans la WarZone !");
    ChatStyle style = new ChatStyle();
    style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§bCliquez pour vous téléporter au §d/warp end")));
    style.func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/warp end"));
    text.func_150255_a(style);
    MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)text);
  }
  
  public void startEgghunt(EntityPlayer killer) {
    ServerProxy proxy = PEnd.getServer();
    EndConfig config = proxy.getConfig();
    World world = MinecraftServer.func_71276_C().func_130014_f_();
    proxy.getData().setState(EndState.EGGHUNT);
    BlockEndPortal.field_149948_a = true;
    setBlocks(world, config.egg, PERegisterer.egg, 7);
    setBlocks(world, config.endportal, Blocks.field_150384_bq);
    PEggHunt.data.setEndEvent(true);
    PEggHunt.data.setActive(true);
    PEggHunt.data.setStartedAt(System.currentTimeMillis());
    PEggHunt.data.setUuid(getUUID());
    PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
          try {
            if (killer != null) {
              ApiServices.Http.getEggHuntService().setKiller(new EggHuntPlayerNameInput(killer)).execute();
            } else {
              ApiServices.Http.getEggHuntService().setKiller(new EggHuntPlayerNameInput("", null)).execute();
            } 
          } catch (IOException e) {
            e.printStackTrace();
          } 
        }0L);
    ChatComponentText text = new ChatComponentText("§8[§dEnd§8] §eLa chasse commence !");
    ChatStyle style = new ChatStyle();
    style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§bCliquez pour vous téléporter au §d/warp end")));
    style.func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/warp end"));
    text.func_150255_a(style);
    MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)text);
  }
  
  public void stop(EntityPlayer winner) {
    PEggHunt.status = null;
    PEggHunt.data.setActive(false);
    PEggHunt.data.setEndEvent(false);
    PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
          try {
            ApiServices.Http.getEggHuntService().setOwner(new EggHuntPlayerEggInput(null, null, null)).execute();
          } catch (IOException e) {
            e.printStackTrace();
          } 
        }0L);
    ServerProxy proxy = PEnd.getServer();
    EndData data = proxy.getData();
    EndConfig config = proxy.getConfig();
    World world = MinecraftServer.func_71276_C().func_130014_f_();
    data.setUuid(null);
    data.setState(EndState.NONE);
    for (EntityEventEndCrystal entity : this.crystals) {
      if (entity == null)
        continue; 
      entity.func_70106_y();
    } 
    this.crystals.clear();
    if (this.dragon != null) {
      this.dragon.func_70106_y();
      this.dragon = null;
    } 
    setBlocks(world, config.hole, Blocks.field_150377_bs);
    setBlocks(world, config.portal, Blocks.field_150350_a);
    setBlocks(world, config.endportal, Blocks.field_150350_a);
    setBlocks(world, config.egg, Blocks.field_150350_a);
    for (Object playerObj : (MinecraftServer.func_71276_C().func_71218_a(1)).field_73010_i) {
      if (!(playerObj instanceof EntityPlayer))
        continue; 
      EntityPlayer player = (EntityPlayer)playerObj;
      if (!inEnd(player))
        continue; 
      teleportToWorld((Entity)player);
    } 
    ChatComponentText text = new ChatComponentText("§8[§dEnd§8] §cL'évènement touche à sa fin !");
    MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)text);
    ChatUtils.broadcastCenteredMessage("");
    ChatUtils.broadcastCenteredMessage("§8§m---------------------------------");
    ChatUtils.broadcastCenteredMessage("");
    ChatUtils.broadcastCenteredMessage("§d§lEnd Event");
    ChatUtils.broadcastCenteredMessage("");
    if (winner != null) {
      Player p = PlayerController.getInstance().getLoadedPlayer(winner.func_110124_au());
      if (p != null && p.hasFaction() && p.getFaction() != null)
        p.getFaction().getLevelEntity().addElo(300, FactionEloChangeReason.UNKNOWN); 
      ChatUtils.broadcastCenteredMessage("§8Chasse à l'oeuf");
      ChatUtils.broadcastCenteredMessage("");
      ChatUtils.broadcastCenteredMessage("§aGagnant §8» §6" + winner.func_70005_c_());
      ChatUtils.broadcastCenteredMessage("");
      ChatUtils.broadcastCenteredMessage("");
      ChatUtils.broadcastCenteredMessage("&8Classement chorus");
      ChatUtils.broadcastCenteredMessage("");
    } 
    AtomicInteger index = new AtomicInteger(0);
    AtomicInteger minScore = new AtomicInteger(0);
    this.points
      .entrySet()
      .stream()
      .filter(entry -> (entry.getKey() != null && ((Integer)entry.getValue()).intValue() >= 0))
      .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
      .limit(10L)
      .forEachOrdered(entry -> {
          int i = index.getAndIncrement();
          if (i == 0)
            minScore.set((int)(((Integer)entry.getValue()).intValue() / 10.0F)); 
          if (((Integer)entry.getValue()).intValue() >= minScore.get()) {
            Player p = PlayerController.getInstance().getLoadedPlayer(((EntityPlayer)entry.getKey()).func_110124_au());
            if (p != null && p.hasFaction() && p.getFaction() != null) {
              int elo = (i == 0) ? 200 : ((i == 1) ? 150 : ((i == 2) ? 100 : ((i <= 6) ? 25 : 5)));
              p.getFaction().getLevelEntity().addElo(elo, FactionEloChangeReason.UNKNOWN);
            } 
            String pos = (i == 0) ? "§c1er" : ((i == 1) ? "§62e" : ("§e" + (i + 1) + "e"));
            ChatUtils.broadcastCenteredMessage(pos + " §7- §6" + ((EntityPlayer)entry.getKey()).func_70005_c_());
          } 
        });
    ChatUtils.broadcastCenteredMessage("");
    ChatUtils.broadcastCenteredMessage("§8§m---------------------------------");
    ChatUtils.broadcastCenteredMessage("");
  }
  
  public void checkCrystals() {
    ServerProxy proxy = PEnd.getServer();
    EndData data = proxy.getData();
    EndConfig config = proxy.getConfig();
    World world = MinecraftServer.func_71276_C().func_130014_f_();
    if (data.getState() != EndState.CRYSTALS)
      return; 
    for (EntityEventEndCrystal entity : this.crystals) {
      if (entity != null && !entity.field_70128_L && entity.field_70260_b > 0) {
        if (!world.field_72996_f.contains(entity)) {
          world.func_72900_e((Entity)entity);
          Chunk chunk1 = world.func_72938_d(MathHelper.func_76128_c(entity.field_70165_t), MathHelper.func_76128_c(entity.field_70161_v));
          if (!chunk1.field_76636_d) {
            System.err.println("[End] Force loading chunk for End Crystal at " + entity.field_70165_t + ", " + entity.field_70163_u + ", " + entity.field_70161_v + ".");
            world.func_72863_F().func_73158_c(chunk1.field_76635_g, chunk1.field_76647_h);
          } 
          entity.field_98038_p = true;
          if (!world.func_72838_d((Entity)entity))
            System.err.println("[End] Unable to spawn End Crystal at " + entity.field_70165_t + ", " + entity.field_70163_u + ", " + entity.field_70161_v + ". Please check the chunk is loaded and the position is valid."); 
        } 
        return;
      } 
    } 
    setBlocks(world, config.portal, Blocks.field_150384_bq);
    data.setState(EndState.DRAGON);
    BlockLocation dragonPos = config.dragon.get(0);
    if (!(world.func_72938_d(dragonPos.getX(), dragonPos.getZ())).field_76636_d)
      world.func_72863_F().func_73158_c(dragonPos.getX() >> 4, dragonPos.getZ() >> 4); 
    EntityCustomDragon dragon = new EntityCustomDragon(world);
    dragon.setHome(dragonPos.getX(), dragonPos.getY(), dragonPos.getZ());
    dragon.field_98038_p = true;
    Chunk chunk = world.func_72938_d(dragonPos.getX(), dragonPos.getZ());
    if (!chunk.field_76636_d)
      world.func_72863_F().func_73158_c(dragonPos.getX() >> 4, dragonPos.getZ() >> 4); 
    getInstance().setDragon(dragon);
    if (!world.func_72838_d((Entity)dragon)) {
      System.err.println("[End] Unable to spawn the End Dragon at " + dragonPos.getX() + ", " + dragonPos.getY() + ", " + dragonPos.getZ() + ". Please check the chunk is loaded and the position is valid.");
      getInstance().stop(null);
      return;
    } 
    ChatComponentText text = new ChatComponentText("§8[§dEnd§8] §eLes gardiens du portail ont été vaincu, sautez dans le portail pour aller affronter le dragon ou récolter des corus !");
    ChatStyle style = new ChatStyle();
    style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§bCliquez pour vous téléporter au §d/warp end")));
    style.func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/warp end"));
    text.func_150255_a(style);
    MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)text);
    for (Object playerObj : world.field_73010_i) {
      if (!(playerObj instanceof EntityPlayerMP))
        continue; 
      EntityPlayerMP player = (EntityPlayerMP)playerObj;
      S29PacketSoundEffect packet = new S29PacketSoundEffect("portal.portal", player.field_70165_t, player.field_70163_u, player.field_70161_v, 10.0F, 0.3F);
      player.field_71135_a.func_147359_a((Packet)packet);
      ModuleTitle.getInstance().sendTitle(new MinecraftTitle("§dEvent END", "§eLe portail est ouvert", 500L, 3000L, 500L), player);
    } 
  }
  
  public void teleportToEnd(Entity entity) {
    if (!(entity instanceof EntityPlayerMP))
      return; 
    ServerProxy proxy = PEnd.getServer();
    EndConfig config = proxy.getConfig();
    EntityPlayerMP player = (EntityPlayerMP)entity;
    for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
      if (player.field_71071_by.field_70462_a[i] != null)
        if (player.field_71071_by.field_70462_a[i].func_77973_b() == ModItems.chorus_fruit)
          player.field_71071_by.field_70462_a[i] = null;  
    } 
    player.field_71071_by.func_70296_d();
    BlockLocation location = config.spawns.get(player.field_70170_p.field_73012_v.nextInt(config.spawns.size()));
    double x = location.getX() + 0.5D;
    double y = location.getY() + 1.0D;
    double z = location.getZ() + 0.5D;
    TeleportUtils.teleport((EntityPlayer)player, x, y, z);
    EndState state = getState();
    if (state == EndState.DRAGON) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§dEnd§8] §eBienvenue dans l'end, tuez le dragon !"));
    } else {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§dEnd§8] §eBienvenue dans l'end, récoltez un maximum de chorus fruits !"));
    } 
    S29PacketSoundEffect packet = new S29PacketSoundEffect("random.levelup", player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, 1.0F);
    player.field_71135_a.func_147359_a((Packet)packet);
  }
  
  public void teleportToWorld(Entity entity) {
    if (!(entity instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)entity;
    BlockLocation location = new BlockLocation((player.field_70170_p.func_72861_E()).field_71574_a, (player.field_70170_p.func_72861_E()).field_71572_b, (player.field_70170_p.func_72861_E()).field_71573_c);
    double x = location.getX() + 0.5D;
    double y = location.getY() + 1.0D;
    double z = location.getZ() + 0.5D;
    TeleportUtils.teleport((EntityPlayer)player, x, y, z);
    S29PacketSoundEffect packet = new S29PacketSoundEffect("random.levelup", player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, 1.0F);
    player.field_71135_a.func_147359_a((Packet)packet);
  }
  
  public int increasePoints(EntityPlayer player, int value) {
    int point = 0;
    if (this.points.containsKey(player))
      point = ((Integer)this.points.get(player)).intValue(); 
    point += value;
    this.points.put(player, Integer.valueOf(point));
    return point;
  }
  
  public void setBlocks(World world, List<BlockLocation> blocks, Block block) {
    for (BlockLocation pos : blocks) {
      Chunk chunk = world.func_72938_d(pos.getX(), pos.getZ());
      if (!chunk.field_76636_d)
        world.func_72863_F().func_73158_c(pos.getX() >> 4, pos.getZ() >> 4); 
      world.func_147449_b(pos.getX(), pos.getY(), pos.getZ(), block);
    } 
  }
  
  public void setBlocks(World world, List<BlockLocation> blocks, Block block, int meta) {
    PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
          for (BlockLocation pos : blocks) {
            Chunk chunk = world.func_72938_d(pos.getX(), pos.getZ());
            if (chunk.field_76636_d) {
              world.func_147465_d(pos.getX(), pos.getY(), pos.getZ(), block, meta, 2);
              continue;
            } 
            System.out.println("[End] Unable to set block at " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + " to " + block.func_149732_F());
          } 
        }0L);
  }
  
  public boolean inEnd(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    return inEnd(player);
  }
  
  public boolean inEnd(@NonNull World world, double x, double y, double z) {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    Optional<StateFlag> optional = WGManager.inst().getRegionListAt(world, x, y, z).getFlag(PEnd.EVENT_END_FLAG);
    return (optional.isPresent() && ((StateFlag)optional.get()).isAllowed());
  }
  
  public String getUUID() {
    return PEnd.getServer().getData().getUuid();
  }
  
  public EntityCustomDragon getDragon() {
    return this.dragon;
  }
  
  public void setDragon(EntityCustomDragon dragon) {
    this.dragon = dragon;
  }
  
  public EndState getState() {
    return PEnd.getServer().getData().getState();
  }
  
  public boolean isActive() {
    return (PEnd.getServer().getData().getUuid() != null);
  }
  
  public static EndManager getInstance() {
    if (instance == null)
      return new EndManager(); 
    return instance;
  }
  
  public List<EntityEventEndCrystal> getCrystals() {
    return this.crystals;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\server\manager\EndManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */