package fr.paladium.palawarzoneevent.module.capture.common.util;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.factions.api.FactionAPI;
import fr.paladium.factions.api.faction.FactionEloChangeReason;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.player.IPlayer;
import fr.paladium.helios.module.killcam.server.manager.KillCamManager;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palajobs.api.PalaJobsAPI;
import fr.paladium.palajobs.api.player.IJobsPlayer;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palawarzoneevent.module.capture.CaptureModule;
import fr.paladium.palawarzoneevent.module.capture.api.event.CaptureRewardEvent;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;

public class CapturePoint {
  public void setName(String name) {
    this.name = name;
  }
  
  public void setLocation(DoubleLocation location) {
    this.location = location;
  }
  
  public void setRadius(int radius) {
    this.radius = radius;
  }
  
  public void setRadiusType(CaptureRadiusShape radiusType) {
    this.radiusType = radiusType;
  }
  
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
  
  public void setRewards(List<CaptureReward> rewards) {
    this.rewards = rewards;
  }
  
  public void setCapturingProgress(double capturingProgress) {
    this.capturingProgress = capturingProgress;
  }
  
  public void setInterrupted(boolean interrupted) {
    this.interrupted = interrupted;
  }
  
  public void setCapturingFactionUUID(UUID capturingFactionUUID) {
    this.capturingFactionUUID = capturingFactionUUID;
  }
  
  public void setRewardTick(int rewardTick) {
    this.rewardTick = rewardTick;
  }
  
  public void setRewardCooldowns(Map<CaptureReward, Integer> rewardCooldowns) {
    this.rewardCooldowns = rewardCooldowns;
  }
  
  private static CapturePoint create() {
    return new CapturePoint();
  }
  
  private static final Random RANDOM = new Random();
  
  private String name;
  
  private DoubleLocation location;
  
  public String getName() {
    return this.name;
  }
  
  public DoubleLocation getLocation() {
    return this.location;
  }
  
  private int radius = 10;
  
  public int getRadius() {
    return this.radius;
  }
  
  private CaptureRadiusShape radiusType = CaptureRadiusShape.CIRCLE;
  
  public CaptureRadiusShape getRadiusType() {
    return this.radiusType;
  }
  
  private boolean enabled = false;
  
  public boolean isEnabled() {
    return this.enabled;
  }
  
  private List<CaptureReward> rewards = new ArrayList<>();
  
  public List<CaptureReward> getRewards() {
    return this.rewards;
  }
  
  private double capturingProgress = 0.0D;
  
  public double getCapturingProgress() {
    return this.capturingProgress;
  }
  
  private boolean interrupted = false;
  
  private UUID capturingFactionUUID = null;
  
  public UUID getCapturingFactionUUID() {
    return this.capturingFactionUUID;
  }
  
  private transient int rewardTick = 0;
  
  public int getRewardTick() {
    return this.rewardTick;
  }
  
  private transient Map<CaptureReward, Integer> rewardCooldowns = new HashMap<>();
  
  public Map<CaptureReward, Integer> getRewardCooldowns() {
    return this.rewardCooldowns;
  }
  
  private CapturePoint(String name, DoubleLocation location) {
    this.name = name;
    this.location = location;
  }
  
  public static CapturePoint of(@NonNull String name, @NonNull DoubleLocation location) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    if (location == null)
      throw new NullPointerException("location is marked non-null but is null"); 
    return new CapturePoint(name, location);
  }
  
  public boolean isInterrupted() {
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
      return this.interrupted; 
    List<EntityPlayer> players = getPlayerInZone();
    players.removeIf(player -> (player.field_71075_bZ.field_75098_d || KillCamManager.has(player)));
    if (players.isEmpty()) {
      this.interrupted = false;
      return this.interrupted;
    } 
    if (this.capturingProgress > 0.0D && this.capturingFactionUUID != null) {
      Set<UUID> factionsInZone = getAllFactionsInZone(players);
      this.interrupted = (factionsInZone.size() > 1);
      return this.interrupted;
    } 
    return this.interrupted = false;
  }
  
  public void updateProgress() {
    List<EntityPlayer> players = getPlayerInZone();
    players.removeIf(player -> player.field_71075_bZ.field_75098_d);
    if (players.isEmpty()) {
      if (this.capturingProgress > 0.0D) {
        this.capturingProgress--;
        CaptureModule.getServer().getConfig().save();
      } 
      if (this.capturingProgress == 0.0D && this.capturingFactionUUID != null) {
        this.capturingFactionUUID = null;
        CaptureModule.getServer().getConfig().save();
      } 
      return;
    } 
    IFaction capturingFaction = getFactionInZone(players);
    if (capturingFaction == null)
      return; 
    if (this.capturingFactionUUID == null && this.capturingProgress == 0.0D) {
      this.capturingFactionUUID = capturingFaction.getUuid();
      CaptureModule.getServer().getConfig().save();
      return;
    } 
    if (this.capturingFactionUUID != null && this.capturingFactionUUID.equals(capturingFaction.getUuid()) && this.capturingProgress < 100.0D) {
      this.capturingProgress++;
      if (this.capturingProgress == 100.0D) {
        this.rewardTick = 0;
        MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §r§aLa faction " + capturingFaction.getName() + " a capturé le point de capture §e" + this.name + "§a !"));
      } 
      return;
    } 
    if (this.capturingFactionUUID != null && !this.capturingFactionUUID.equals(capturingFaction.getUuid()) && this.capturingProgress > 0.0D) {
      this.capturingProgress--;
      if (this.capturingProgress == 0.0D) {
        this.capturingFactionUUID = null;
        CaptureModule.getServer().getConfig().save();
      } 
      return;
    } 
    this.rewardTick++;
    if (this.rewardTick % 60 == 0) {
      IFaction iFaction = FactionAPI.getInstance().getFaction(this.capturingFactionUUID);
      if (iFaction == null) {
        CaptureModule.logger.error("CapturePoint %s has a capturing faction UUID that does not exist: %s", new Object[] { this.name, this.capturingFactionUUID.toString() });
        return;
      } 
      players.removeIf(player -> {
            IPlayer iPlayer = FactionAPI.getInstance().getPlayer(player);
            return (iPlayer == null || !iPlayer.hasFaction() || !iPlayer.getFaction().getUuid().equals(this.capturingFactionUUID));
          });
      List<CaptureReward> awardableRewards = getAwardableRewards();
      List<CaptureReward> eloRewards = (List<CaptureReward>)awardableRewards.stream().filter(r -> (r.getType() == CaptureRewardType.ELO)).collect(Collectors.toList());
      eloRewards.forEach(captureReward -> {
            if (captureReward.getAmount() <= 0.0D) {
              CaptureModule.logger.error("CapturePoint %s has an invalid ELO reward amount: %s", new Object[] { this.name, Double.valueOf(captureReward.getAmount()) });
              return;
            } 
            iFaction.getLevelEntity().addElo((int)Math.floor(captureReward.getAmount()), FactionEloChangeReason.EVENT_CAPTURE);
            players.forEach(());
          });
      players.forEach(p -> givePersonnalReward(p, iFaction, awardableRewards));
      MinecraftForge.EVENT_BUS.post((Event)new CaptureRewardEvent(players, iFaction, this));
    } 
  }
  
  private List<CaptureReward> getAwardableRewards() {
    List<CaptureReward> awardableRewards = new ArrayList<>();
    this.rewards.forEach(captureReward -> {
          if (captureReward.getGivenEveryMinute() > 1) {
            if (!this.rewardCooldowns.containsKey(captureReward)) {
              this.rewardCooldowns.put(captureReward, Integer.valueOf(captureReward.getGivenEveryMinute()));
              return;
            } 
            int cooldown = ((Integer)this.rewardCooldowns.get(captureReward)).intValue();
            if (cooldown > 1) {
              this.rewardCooldowns.put(captureReward, Integer.valueOf(cooldown - 1));
              return;
            } 
            this.rewardCooldowns.put(captureReward, Integer.valueOf(captureReward.getGivenEveryMinute()));
          } 
          awardableRewards.add(captureReward);
        });
    return awardableRewards;
  }
  
  private void givePersonnalReward(final EntityPlayer player, IFaction faction, List<CaptureReward> awardableRewards) {
    awardableRewards.forEach(captureReward -> {
          JobType[] jobTypes;
          Optional<IJobsPlayer> optJobsPlayer;
          JobType jobType;
          JobType jType;
          Optional<IJobsPlayer> optJobPlayer;
          switch (captureReward.getType()) {
            case null:
              CresusManager.getInstance().depositPlayerAsync(player.func_110124_au(), captureReward.getAmount(), "EVENT_LARGAGE", new CresusCallback<CresusResponse>() {
                    public void onFail(CresusResponse response, Throwable throwable) {
                      player.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §r§cUne erreur est survenue lors du gain de money."));
                    }
                    
                    public void onSuccess(CresusResponse response) {
                      player.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §r§a" + response.amount + "$ ont été ajouté à votre solde."));
                    }
                  });
              break;
            case null:
              jobTypes = JobType.values();
              optJobsPlayer = PalaJobsAPI.inst().getJobsPlayer(player);
              if (!optJobsPlayer.isPresent()) {
                player.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §r§cUne erreur est survenue lors de l'ajout d'expérience de métier."));
                return;
              } 
              jobType = jobTypes[RANDOM.nextInt(jobTypes.length)];
              ((IJobsPlayer)optJobsPlayer.get()).addXp(jobType, ObjectiveType.WARZONE_EVENT, captureReward.getAmount(), player);
              player.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §r§aVous avez reçu " + captureReward.getAmount() + " points d'expérience pour le métier " + TTT.format(player, jobType.getName(), new Object[0]) + "."));
              break;
            case SQUARE:
            case CIRCLE:
            case null:
            case null:
              jType = getJobTypeFromRewardType(captureReward.getType());
              optJobPlayer = PalaJobsAPI.inst().getJobsPlayer(player);
              if (!optJobPlayer.isPresent()) {
                player.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §r§cUne erreur est survenue lors de l'ajout d'expérience de métier."));
                return;
              } 
              ((IJobsPlayer)optJobPlayer.get()).addXp(jType, ObjectiveType.WARZONE_EVENT, captureReward.getAmount(), player);
              player.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §r§aVous avez reçu " + captureReward.getAmount() + " points d'expérience pour le métier " + TTT.format(player, jType.getName(), new Object[0]) + "."));
              break;
          } 
        });
    List<CaptureReward> itemRewards = (List<CaptureReward>)awardableRewards.stream().filter(r -> (r.getType() == CaptureRewardType.ITEM)).collect(Collectors.toList());
    if (itemRewards.isEmpty())
      return; 
    ItemStack item = rollItemRewards(itemRewards);
    if (item == null)
      return; 
    InventoryUtils.giveOrDropitems(player, item);
  }
  
  public ItemStack rollItemRewards(List<CaptureReward> itemRewards) {
    if (itemRewards.isEmpty()) {
      CaptureModule.logger.error("No item rewards defined in the largage config!", new Object[0]);
      return null;
    } 
    List<CaptureReward> copyRewardList = new ArrayList<>(itemRewards);
    Collections.shuffle(copyRewardList, RANDOM);
    ItemStack item = null;
    int maxWeight = itemRewards.stream().mapToInt(CaptureReward::getWeight).sum();
    if (maxWeight <= 0)
      return null; 
    int numOfRoll = 1;
    for (int i = 0; i < 1; i++) {
      int weight = RANDOM.nextInt(maxWeight) + 1;
      int sumRolledItemWeight = 0;
      for (CaptureReward itemReward : copyRewardList) {
        sumRolledItemWeight += itemReward.getWeight();
        if (weight <= sumRolledItemWeight) {
          ItemStack itemStack = ItemUtils.parse(itemReward.getItem());
          if (itemStack == null) {
            CaptureModule.logger.error("Invalid item format: " + itemReward.getItem(), new Object[0]);
            break;
          } 
          item = itemStack;
          break;
        } 
      } 
    } 
    return item;
  }
  
  public IFaction getFactionInZone(List<EntityPlayer> players) {
    List<EntityPlayer> playersCopy = new ArrayList<>(players);
    playersCopy.removeIf(player -> {
          IPlayer iPlayer = FactionAPI.getInstance().getPlayer(player);
          return (iPlayer == null || !iPlayer.hasFaction());
        });
    if (playersCopy.isEmpty())
      return null; 
    IPlayer iPlayer = FactionAPI.getInstance().getPlayer(playersCopy.get(0));
    return iPlayer.getFaction();
  }
  
  public Set<UUID> getAllFactionsInZone(List<EntityPlayer> players) {
    Set<UUID> factions = new HashSet<>();
    players.removeIf(player -> {
          IPlayer iPlayer = FactionAPI.getInstance().getPlayer(player);
          if (iPlayer == null || !iPlayer.hasFaction())
            return true; 
          factions.add(iPlayer.getFaction().getUuid());
          return false;
        });
    return factions;
  }
  
  public List<EntityPlayer> getPlayerInZone() {
    List<EntityPlayer> playersInZone = new ArrayList<>();
    DoubleLocation cLoc = getLocation();
    double radius = getRadius();
    AxisAlignedBB captureZone = AxisAlignedBB.func_72330_a(cLoc.getX() - radius, cLoc.getY() - 3.0D, cLoc.getZ() - radius, cLoc.getX() + radius, cLoc.getY() + 5.0D, cLoc.getZ() + radius);
    PlayerSelector.AREA(captureZone).apply(player -> {
          double dx;
          double dz;
          if (!player.field_70122_E)
            return; 
          switch (getRadiusType()) {
            case SQUARE:
              playersInZone.add(player);
              return;
            case CIRCLE:
              dx = player.field_70165_t - cLoc.getX();
              dz = player.field_70161_v - cLoc.getZ();
              if (dx * dx + dz * dz <= radius * radius)
                playersInZone.add(player); 
              return;
          } 
          playersInZone.add(player);
        });
    return playersInZone;
  }
  
  private JobType getJobTypeFromRewardType(CaptureRewardType type) {
    switch (type) {
      case SQUARE:
        return JobType.ALCHEMIST;
      case CIRCLE:
        return JobType.FARMER;
      case null:
        return JobType.HUNTER;
      case null:
        return JobType.MINER;
    } 
    return null;
  }
  
  private CapturePoint() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\capture\commo\\util\CapturePoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */