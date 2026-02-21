package fr.paladium.palawarzoneevent.module.warzone.server.worlddata;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palawarzoneevent.module.warzone.common.data.LeaderboardEntry;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class WarzoneLeaderboardWorldData extends WorldSavedData {
  private static WarzoneLeaderboardWorldData INSTANCE;
  
  private static final String DATA_NAME = "warzone_world_data";
  
  private static final int PAGE_SIZE = 15;
  
  private Map<WarzoneLeaderboardType, List<LeaderboardEntry>> leaderboardMap = new HashMap<>();
  
  public Map<WarzoneLeaderboardType, List<LeaderboardEntry>> getLeaderboardMap() {
    return this.leaderboardMap;
  }
  
  public WarzoneLeaderboardWorldData(String name) {
    super("warzone_world_data");
  }
  
  public WarzoneLeaderboardWorldData() {
    this("warzone_world_data");
  }
  
  public List<LeaderboardEntry> getLeaderboard(WarzoneLeaderboardType type) {
    return this.leaderboardMap.getOrDefault(type, new LinkedList<>());
  }
  
  public List<LeaderboardEntry> getLeaderboardPage(WarzoneLeaderboardType type, int index) {
    List<LeaderboardEntry> list = getLeaderboard(type);
    list.sort(Comparator.<LeaderboardEntry, Comparable>comparing(LeaderboardEntry::getPoints).reversed());
    if (index * 15 > list.size())
      return new LinkedList<>(); 
    int startIndex = index * 15;
    int endIndex = (startIndex + 15 > list.size()) ? list.size() : (index * 15 + 15);
    return list.subList(startIndex, endIndex);
  }
  
  public void addPoints(WarzoneLeaderboardType type, UUID factionUUID, String factionName, long points) {
    addPoints(type, UUIDUtils.toString(factionUUID), factionName, points);
  }
  
  public void addPoints(WarzoneLeaderboardType type, String factionUUID, String factionName, long points) {
    List<LeaderboardEntry> leaderboard = getLeaderboard(type);
    Optional<LeaderboardEntry> optLbEntry = leaderboard.stream().filter(entry -> entry.getFactionUUID().equalsIgnoreCase(factionUUID)).findFirst();
    if (!optLbEntry.isPresent()) {
      LeaderboardEntry entry = new LeaderboardEntry(factionUUID, factionName, points);
      leaderboard.add(entry);
      this.leaderboardMap.put(type, leaderboard);
      save();
      return;
    } 
    ((LeaderboardEntry)optLbEntry.get()).addPoints(points);
    save();
  }
  
  public void func_76187_b(NBTTagCompound compound) {
    this.leaderboardMap.forEach((leaderType, listEntry) -> {
          NBTTagList tagList = new NBTTagList();
          listEntry.forEach(());
          compound.func_74782_a("warzone_leaderboard_" + leaderType.name().toLowerCase(), (NBTBase)tagList);
        });
  }
  
  public void func_76184_a(NBTTagCompound compound) {
    this.leaderboardMap = new HashMap<>();
    for (WarzoneLeaderboardType type : WarzoneLeaderboardType.values) {
      List<LeaderboardEntry> leaderboard = new LinkedList<>();
      NBTTagList tagList = compound.func_150295_c("warzone_leaderboard_" + type.name().toLowerCase(), 10);
      for (int i = 0; i < tagList.func_74745_c(); i++) {
        NBTTagCompound tag = tagList.func_150305_b(i);
        LeaderboardEntry lbEntry = new LeaderboardEntry(tag.func_74779_i("factionUUID"), tag.func_74779_i("factionName"), tag.func_74763_f("points"));
        leaderboard.add(lbEntry);
      } 
      this.leaderboardMap.put(type, leaderboard);
    } 
  }
  
  @SideOnly(Side.SERVER)
  public static WarzoneLeaderboardWorldData get() {
    if (INSTANCE != null)
      return INSTANCE; 
    World world = MinecraftServer.func_71276_C().func_130014_f_();
    WarzoneLeaderboardWorldData data = (WarzoneLeaderboardWorldData)world.field_72988_C.func_75742_a(WarzoneLeaderboardWorldData.class, "warzone_world_data");
    if (data == null) {
      data = new WarzoneLeaderboardWorldData();
      world.field_72988_C.func_75745_a("warzone_world_data", data);
      data.save();
    } 
    return INSTANCE = data;
  }
  
  @SideOnly(Side.SERVER)
  public void save() {
    World world = MinecraftServer.func_71276_C().func_130014_f_();
    func_76185_a();
    world.field_72988_C.func_75744_a();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\server\worlddata\WarzoneLeaderboardWorldData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */