package fr.paladium.palamod.modules.miner.dimminer.common.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.time.TimeUtils;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.miner.dimminer.common.manager.DimMinerManager;
import fr.paladium.palamod.modules.world.PWorld;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public final class DimMinerPlayer extends ExtendedEntityProperties {
  public static final String PROP_NAME = "MinerDimensionPlayerData";
  
  public static final long DURATION = TimeUnit.DAYS.toSeconds(3L);
  
  public static final long TOTAL_POINTS = 1000L;
  
  private static final List<Block> MINED_BLOCK_LIST;
  
  private static final Map<Block, Long> POINTS_MAP = new LinkedHashMap<>();
  
  private DimMinerManager.DimMinerSection section;
  
  private PortalData portalData;
  
  private boolean inPortal;
  
  private int inPortalTimer;
  
  private long creationTime;
  
  private long points;
  
  private boolean expired;
  
  private long timePlayed;
  
  private long minedBlocks;
  
  private double jobExperience;
  
  private Map<String, Long> minedBlockMap;
  
  static {
    MINED_BLOCK_LIST = new LinkedList<>();
    MINED_BLOCK_LIST.add(BlocksRegister.ENDIUM_NUGGET_ORE);
    MINED_BLOCK_LIST.add(PWorld.PALADIUM_GREEN_ORE);
    MINED_BLOCK_LIST.add(PWorld.PALADIUM_ORE);
    MINED_BLOCK_LIST.add(BlocksRegister.INVOKER_ORE);
    MINED_BLOCK_LIST.add(PWorld.TRIXIUM_ORE);
    MINED_BLOCK_LIST.add(PWorld.FINDIUM_ORE);
    MINED_BLOCK_LIST.add(PWorld.AMETHYST_ORE);
    MINED_BLOCK_LIST.add(PWorld.TITANE_ORE);
    POINTS_MAP.put(PWorld.PALADIUM_ORE, Long.valueOf(1L));
    POINTS_MAP.put(PWorld.PALADIUM_GREEN_ORE, Long.valueOf(5L));
    POINTS_MAP.put(BlocksRegister.ENDIUM_NUGGET_ORE, Long.valueOf(501L));
  }
  
  public DimMinerManager.DimMinerSection getSection() {
    return this.section;
  }
  
  public PortalData getPortalData() {
    return this.portalData;
  }
  
  public boolean isInPortal() {
    return this.inPortal;
  }
  
  public int getInPortalTimer() {
    return this.inPortalTimer;
  }
  
  public long getCreationTime() {
    return this.creationTime;
  }
  
  public long getPoints() {
    return this.points;
  }
  
  public boolean isExpired() {
    return this.expired;
  }
  
  public long getTimePlayed() {
    return this.timePlayed;
  }
  
  public long getMinedBlocks() {
    return this.minedBlocks;
  }
  
  public double getJobExperience() {
    return this.jobExperience;
  }
  
  public Map<String, Long> getMinedBlockMap() {
    return this.minedBlockMap;
  }
  
  public DimMinerPlayer() {
    reset();
  }
  
  @SideOnly(Side.CLIENT)
  public static DimMinerPlayer get() {
    return get((Entity)(Minecraft.func_71410_x()).field_71439_g);
  }
  
  public static DimMinerPlayer get(@NonNull Entity entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    return (DimMinerPlayer)entity.getExtendedProperties("MinerDimensionPlayerData");
  }
  
  @NonNull
  public EntityPlayer getPlayer() {
    return (EntityPlayer)getEntity();
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound data = new NBTTagCompound();
    data.func_74768_a("SectionIndex", (this.section != null) ? this.section.getIndex() : -1);
    data.func_74782_a("PortalData", (this.portalData != null) ? (NBTBase)this.portalData.toNBT() : (NBTBase)new NBTTagCompound());
    data.func_74757_a("InPortal", this.inPortal);
    data.func_74768_a("InPortalTimer", this.inPortalTimer);
    data.func_74772_a("CreationTime", this.creationTime);
    data.func_74772_a("Points", this.points);
    data.func_74757_a("Expired", this.expired);
    data.func_74772_a("TimePlayed", this.timePlayed);
    data.func_74772_a("MinedBlocks", this.minedBlocks);
    data.func_74780_a("JobExperience", this.jobExperience);
    data.func_74782_a("MinedBlockMap", (NBTBase)new NBTTagCompound());
    this.minedBlockMap.forEach((key, value) -> data.func_74775_l("MinedBlockMap").func_74772_a(key, value.longValue()));
    compound.func_74782_a("MinerDimensionPlayerData", (NBTBase)data);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (!compound.func_74764_b("MinerDimensionPlayerData")) {
      reset();
      return;
    } 
    NBTTagCompound data = compound.func_74775_l("MinerDimensionPlayerData");
    this.section = (data.func_74762_e("SectionIndex") != -1) ? DimMinerManager.getSection(data.func_74762_e("SectionIndex")) : null;
    this.portalData = data.func_74764_b("PortalData") ? PortalData.fromNBT(data.func_74775_l("PortalData")) : null;
    this.inPortal = data.func_74767_n("InPortal");
    this.inPortalTimer = data.func_74762_e("InPortalTimer");
    this.creationTime = data.func_74763_f("CreationTime");
    this.points = data.func_74763_f("Points");
    this.expired = data.func_74767_n("Expired");
    this.timePlayed = data.func_74763_f("TimePlayed");
    this.minedBlocks = data.func_74763_f("MinedBlocks");
    this.jobExperience = data.func_74769_h("JobExperience");
    this.minedBlockMap = new HashMap<>();
    data.func_74775_l("MinedBlockMap").func_150296_c().forEach(key -> (Long)this.minedBlockMap.put((String)key, Long.valueOf(data.func_74775_l("MinedBlockMap").func_74763_f((String)key))));
  }
  
  public void onTick() {
    if (this.inPortalTimer > 0)
      setInPortalTimer(this.inPortalTimer - 1); 
  }
  
  public void reset() {
    this.section = null;
    this.portalData = null;
    this.inPortal = false;
    this.inPortalTimer = 0;
    this.creationTime = 0L;
    this.points = 0L;
    this.expired = false;
    this.timePlayed = 0L;
    this.minedBlocks = 0L;
    this.jobExperience = 0.0D;
    this.minedBlockMap = new HashMap<>();
    for (Block block : MINED_BLOCK_LIST)
      this.minedBlockMap.put(Block.field_149771_c.func_148750_c(block), Long.valueOf(0L)); 
  }
  
  public void create() {
    reset();
    this.creationTime = TimeUtils.now();
    this.points = 1000L;
    this.jobExperience = JobsPlayer.get((Entity)getPlayer()).getExperience(JobType.MINER);
    sync();
  }
  
  public void setSection(DimMinerManager.DimMinerSection section) {
    this.section = section;
    sync();
  }
  
  public void setPortalData(PortalData portalData) {
    this.portalData = portalData;
    sync();
  }
  
  public void setInPortal(boolean inPortal) {
    this.inPortal = inPortal;
    if (inPortal)
      this.inPortalTimer = 20; 
    sync();
  }
  
  public void setInPortalTimer(int inPortalTimer) {
    this.inPortalTimer = inPortalTimer;
    if (inPortalTimer <= 0)
      this.inPortal = false; 
    sync();
  }
  
  public void setPoints(long points) {
    this.points = points;
    sync();
  }
  
  public void breakBlock(@NonNull Block block) {
    if (block == null)
      throw new NullPointerException("block is marked non-null but is null"); 
    this.minedBlocks++;
    if (MINED_BLOCK_LIST.contains(block)) {
      String name = Block.field_149771_c.func_148750_c(block);
      this.minedBlockMap.put(name, Long.valueOf(((Long)this.minedBlockMap.getOrDefault(name, Long.valueOf(0L))).longValue() + 1L));
    } 
    if (POINTS_MAP.containsKey(block))
      this.points -= ((Long)POINTS_MAP.get(block)).longValue(); 
    sync();
  }
  
  public void setExpired(boolean expired) {
    this.expired = expired;
    sync();
  }
  
  public void incrementTimePlayed() {
    this.timePlayed++;
    sync();
  }
  
  public boolean isActive() {
    return (TimeUtils.now() - this.creationTime < DURATION && this.points > 0L && !this.expired);
  }
  
  public Duration getRemainingTime() {
    return Duration.ofSeconds(DURATION - TimeUtils.now() - this.creationTime);
  }
  
  public static long getPoints(@NonNull Block block) {
    if (block == null)
      throw new NullPointerException("block is marked non-null but is null"); 
    return ((Long)POINTS_MAP.getOrDefault(block, Long.valueOf(0L))).longValue();
  }
  
  public static class PortalData {
    private final String server;
    
    private final List<DoubleLocation> blocks;
    
    public PortalData(String server, List<DoubleLocation> blocks) {
      this.server = server;
      this.blocks = blocks;
    }
    
    public String getServer() {
      return this.server;
    }
    
    public List<DoubleLocation> getBlocks() {
      return this.blocks;
    }
    
    public PortalData(@NonNull List<DoubleLocation> blocks) throws UnknownHostException {
      if (blocks == null)
        throw new NullPointerException("blocks is marked non-null but is null"); 
      this.server = InetAddress.getLocalHost().getHostName();
      this.blocks = blocks;
    }
    
    @NonNull
    public NBTTagCompound toNBT() {
      NBTTagCompound compound = new NBTTagCompound();
      compound.func_74778_a("Server", this.server);
      NBTTagList list = new NBTTagList();
      for (DoubleLocation location : this.blocks) {
        NBTTagCompound block = new NBTTagCompound();
        block.func_74768_a("X", location.getBlockX());
        block.func_74768_a("Y", location.getBlockY());
        block.func_74768_a("Z", location.getBlockZ());
        list.func_74742_a((NBTBase)block);
      } 
      compound.func_74782_a("Blocks", (NBTBase)list);
      return compound;
    }
    
    public static PortalData fromNBT(@NonNull NBTTagCompound compound) {
      if (compound == null)
        throw new NullPointerException("compound is marked non-null but is null"); 
      if (!compound.func_74764_b("Server") || !compound.func_74764_b("Blocks"))
        return null; 
      String server = compound.func_74779_i("Server");
      List<DoubleLocation> blocks = new ArrayList<>();
      NBTTagList list = compound.func_150295_c("Blocks", 10);
      for (int i = 0; i < list.func_74745_c(); i++) {
        NBTTagCompound block = list.func_150305_b(i);
        blocks.add(new DoubleLocation(block.func_74762_e("X"), block.func_74762_e("Y"), block.func_74762_e("Z")));
      } 
      return new PortalData(server, blocks);
    }
    
    public boolean isSameServer() {
      try {
        return InetAddress.getLocalHost().getHostName().equals(this.server);
      } catch (UnknownHostException e) {
        return false;
      } 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\data\DimMinerPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */