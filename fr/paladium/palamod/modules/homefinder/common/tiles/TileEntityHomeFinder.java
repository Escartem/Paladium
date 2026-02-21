package fr.paladium.palamod.modules.homefinder.common.tiles;

import fr.paladium.common.CommonModule;
import fr.paladium.homemod.common.dto.Home;
import fr.paladium.homemod.server.manager.HomeManager;
import fr.paladium.palaforgeutils.lib.api.callback.IRetrofitCallback;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.homefinder.common.blocks.BlockHomeFinder;
import fr.paladium.palamod.modules.homefinder.common.data.DisconnectionData;
import fr.paladium.palamod.modules.homefinder.common.entities.EntityFakePlayer;
import fr.paladium.palamod.modules.homefinder.common.managers.DisconnectionDataManager;
import fr.paladium.palamod.modules.homefinder.common.managers.HomeFinderManager;
import fr.paladium.palamod.modules.homefinder.common.tiles.objects.LocationSaver;
import fr.paladium.palamod.modules.homefinder.common.tiles.objects.LocationType;
import fr.paladium.palamod.modules.homefinder.common.tiles.objects.SearchStatus;
import fr.paladium.palamod.modules.homefinder.common.utils.HUtils;
import fr.paladium.palamod.modules.homefinder.common.utils.Location;
import fr.paladium.palamod.modules.homefinder.common.utils.RadiusEnum;
import fr.paladium.palamod.util.FastUUID;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class TileEntityHomeFinder extends TileEntity {
  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }
  
  public void setFuel(int fuel) {
    this.fuel = fuel;
  }
  
  public void setLastFindiumBurnedMillis(long lastFindiumBurnedMillis) {
    this.lastFindiumBurnedMillis = lastFindiumBurnedMillis;
  }
  
  public void setCovered(boolean covered) {
    this.covered = covered;
  }
  
  public void setClaimed(boolean claimed) {
    this.claimed = claimed;
  }
  
  public void setBuilt(boolean built) {
    this.built = built;
  }
  
  public void setAuthorizedList(List<String> authorizedList) {
    this.authorizedList = authorizedList;
  }
  
  public void setLocationsList(List<LocationSaver> locationsList) {
    this.locationsList = locationsList;
  }
  
  public void setAxisAlignedBB(AxisAlignedBB axisAlignedBB) {
    this.axisAlignedBB = axisAlignedBB;
  }
  
  public void setSearchStatus(SearchStatus searchStatus) {
    this.searchStatus = searchStatus;
  }
  
  public String getOwnerName() {
    return this.ownerName;
  }
  
  public UUID getOwner() {
    return this.owner;
  }
  
  public int getFuel() {
    return this.fuel;
  }
  
  public long getLastFindiumBurnedMillis() {
    return this.lastFindiumBurnedMillis;
  }
  
  public boolean isCovered() {
    return this.covered;
  }
  
  public boolean isClaimed() {
    return this.claimed;
  }
  
  public boolean isBuilt() {
    return this.built;
  }
  
  public List<String> getAuthorizedList() {
    return this.authorizedList;
  }
  
  public List<LocationSaver> getLocationsList() {
    return this.locationsList;
  }
  
  public AxisAlignedBB getAxisAlignedBB() {
    return this.axisAlignedBB;
  }
  
  public SearchStatus getSearchStatus() {
    return this.searchStatus;
  }
  
  private int fuel = 0;
  
  private UUID owner = null;
  
  private long lastFindiumBurnedMillis = System.currentTimeMillis();
  
  private List<String> authorizedList = new ArrayList<>();
  
  private List<LocationSaver> locationsList = new ArrayList<>();
  
  private boolean covered = false;
  
  private boolean claimed = false;
  
  private boolean built = false;
  
  private SearchStatus searchStatus = SearchStatus.NONE;
  
  private String ownerName;
  
  private AxisAlignedBB axisAlignedBB;
  
  public void setOwner(EntityPlayer player) {
    this.owner = player.func_110124_au();
  }
  
  public boolean isOwner(EntityPlayer player) {
    if (this.owner == null)
      return false; 
    return this.owner.equals(player.func_110124_au());
  }
  
  public boolean canPlayerUseHome(EntityPlayer player) {
    if (!this.covered)
      return true; 
    if (isOwner(player))
      return true; 
    if (this.authorizedList.contains(player.getDisplayName()))
      return true; 
    return false;
  }
  
  public void pickupItems() {
    if (!this.built)
      return; 
    if (this.fuel >= 16)
      return; 
    if (this.axisAlignedBB == null)
      this
        
        .axisAlignedBB = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e).func_149668_a(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e).func_72314_b(0.0D, 0.5D, 0.0D); 
    List<EntityItem> items = this.field_145850_b.func_72872_a(EntityItem.class, this.axisAlignedBB);
    for (EntityItem item : items) {
      ItemStack stack = item.func_92059_d();
      if (stack.func_77973_b() == ItemsRegister.FINDIUM) {
        if (this.fuel + stack.field_77994_a > 16) {
          stack.field_77994_a = this.fuel + stack.field_77994_a - 16;
          this.fuel = 16;
          break;
        } 
        this.fuel += stack.field_77994_a;
        stack.field_77994_a = 0;
        item.func_70106_y();
        break;
      } 
    } 
    if (this.fuel >= 16) {
      this.field_145850_b.func_72942_c((Entity)new EntityLightningBolt(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e));
      this.fuel = 0;
      HomeFinderManager.getInstance().getExecutor().execute(this::findHomes);
    } 
  }
  
  public void updateFindiumTimer() {
    Block block = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
    if (!(block instanceof net.minecraft.block.BlockHopper))
      return; 
    TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
    if (!(tile instanceof TileEntityHopper))
      return; 
    TileEntityHopper hopper = (TileEntityHopper)tile;
    for (int i = 0; i < hopper.func_70302_i_(); i++) {
      ItemStack stack = hopper.func_70301_a(i);
      if (stack != null)
        if (stack.func_77973_b() == ItemsRegister.FINDIUM) {
          stack.field_77994_a--;
          if (stack.field_77994_a == 0)
            hopper.func_70299_a(i, null); 
          this.lastFindiumBurnedMillis = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1L);
          this.covered = true;
          break;
        }  
    } 
  }
  
  public void checkEntities() {
    int deathSize = 0;
    for (LocationSaver saved : this.locationsList) {
      if (saved.getFakePlayer() == null || (saved.getFakePlayer()).field_70128_L)
        deathSize++; 
    } 
    if (deathSize == this.locationsList.size()) {
      clearOldEntities();
      this.searchStatus = SearchStatus.LOCKED;
    } 
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    if (this.field_145850_b.field_72995_K)
      return; 
    if (this.searchStatus == SearchStatus.FIGHTING || this.searchStatus == SearchStatus.WAITING_SELECTION) {
      checkEntities();
      return;
    } 
    if (this.searchStatus == SearchStatus.NONE || this.searchStatus == SearchStatus.LOCKED)
      pickupItems(); 
    if (!this.covered && this.claimed && this.built && (this.searchStatus == SearchStatus.LOCKED || this.searchStatus == SearchStatus.NONE)) {
      updateFindiumTimer();
    } else if (System.currentTimeMillis() >= this.lastFindiumBurnedMillis) {
      this.covered = false;
    } 
  }
  
  public void convertFromDisconnection(int radius) {
    Location location = new Location(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    List<DisconnectionData> disconnectionDataList = DisconnectionDataManager.getInstance().getDisconnectionsOnRadius(this.field_145850_b, location, radius);
    for (DisconnectionData disconnectionData : disconnectionDataList) {
      EntityPlayer target = this.field_145850_b.func_72924_a(disconnectionData.getPlayerName());
      if (target != null)
        continue; 
      if (disconnectionData.getPlayerName().equals(this.ownerName))
        continue; 
      if (disconnectionData.isCancelled())
        continue; 
      this.locationsList.add(disconnectionData.toLocationSaver(this, this.field_145850_b));
    } 
  }
  
  public void convertFromBukkit(int radius, final Consumer<List<LocationSaver>> consumer) {
    try {
      HomeManager.getInstance().findHomesInRadius(CommonModule.getInstance().getConfig().getServerName(), this.field_145851_c, this.field_145848_d, this.field_145849_e, radius, new IRetrofitCallback<List<Home>>() {
            public void onSuccess(List<Home> homes) {
              List<LocationSaver> tmpLocations = new ArrayList<>();
              for (Home home : homes) {
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(home.getUserUniqueId());
                if (offlinePlayer == null || offlinePlayer.getName().equals(TileEntityHomeFinder.this.ownerName))
                  continue; 
                LocationSaver locationSaver = new LocationSaver(offlinePlayer.getName(), offlinePlayer.getUniqueId(), TileEntityHomeFinder.this, LocationType.HOME, new Location(home.getX(), home.getY(), home.getZ()), TileEntityHomeFinder.this.field_145850_b, home.getHomeName());
                tmpLocations.add(locationSaver);
              } 
              consumer.accept(tmpLocations);
            }
            
            public void onFail(List<Home> homes, Throwable throwable) {
              throwable.printStackTrace();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    } 
    consumer.accept(new ArrayList<>());
  }
  
  public void replaceLocation(LocationSaver saved) {
    DisconnectionDataManager manager = DisconnectionDataManager.getInstance();
    HomeFinderManager finderManager = HomeFinderManager.getInstance();
    if (saved.getLocationType() == LocationType.DISCONNECTION) {
      DisconnectionData data = manager.getDisconnection(this.field_145850_b, saved);
      if (data != null) {
        data.setCancelled(true);
        data.save(this.field_145850_b);
      } 
    } else if (saved.getLocationType() == LocationType.HOME) {
      try {
        if (saved.getHomeName() == null)
          return; 
        HomeManager.getInstance().deleteHome(saved.getUniqueId(), saved.getHomeName(), new IRetrofitCallback<Boolean>() {
              public void onSuccess(Boolean aBoolean) {}
              
              public void onFail(Boolean aBoolean, Throwable throwable) {}
            });
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Bukkit not found");
      } 
    } 
  }
  
  public void findHomes() {
    clearOldEntities();
    int radius = 0;
    if (!(func_145838_q() instanceof BlockHomeFinder))
      return; 
    radius = RadiusEnum.getRadiusFromFloor(((BlockHomeFinder)func_145838_q()).getTowerSize(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e));
    EntityPlayer player = this.field_145850_b.func_152378_a(this.owner);
    int finalRadius = radius;
    convertFromBukkit(radius, locations -> FMLServerScheduler.getInstance().add(new Runnable[] { () }));
  }
  
  public void spawnRandomPlayer(EntityPlayer player) {
    this.locationsList.add(new LocationSaver(player
          .func_70005_c_(), player
          .func_110124_au(), this, LocationType.DISCONNECTION, new Location(this.field_145851_c, this.field_145848_d, this.field_145849_e), this.field_145850_b));
    if (!this.field_145850_b.field_72995_K)
      ((LocationSaver)this.locationsList.get(0)).spawnFakePlayer(this.field_145850_b); 
  }
  
  public void clearOldEntities() {
    for (LocationSaver location : this.locationsList) {
      EntityFakePlayer fake = location.getFakePlayer();
      if (fake == null || fake.field_70128_L)
        continue; 
      location.getFakePlayer().func_70106_y();
    } 
    this.locationsList.clear();
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    if (this.owner != null)
      compound.func_74778_a("owner", FastUUID.toString(this.owner)); 
    if (this.ownerName != null && !this.ownerName.isEmpty())
      compound.func_74778_a("ownerName", this.ownerName); 
    compound.func_74768_a("fuel", this.fuel);
    compound.func_74772_a("lastFindiumBurnedMillis", this.lastFindiumBurnedMillis);
    compound.func_74757_a("covered", this.covered);
    compound.func_74757_a("claimed", this.claimed);
    compound.func_74757_a("built", this.built);
    compound.func_74768_a("searchStatus", this.searchStatus.ordinal());
    NBTTagList tagList = new NBTTagList();
    for (int i = 0; i < this.authorizedList.size(); i++) {
      String s = this.authorizedList.get(i);
      if (s != null) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.func_74778_a("authorized" + i, s);
        tagList.func_74742_a((NBTBase)tag);
      } 
    } 
    compound.func_74782_a("authorizedList", (NBTBase)tagList);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    String uuid = compound.func_74779_i("owner");
    if (uuid != null && !uuid.isEmpty())
      this.owner = UUID.fromString(uuid); 
    this.ownerName = compound.func_74779_i("ownerName");
    this.fuel = compound.func_74762_e("fuel");
    this.lastFindiumBurnedMillis = compound.func_74763_f("lastFindiumBurnedMillis");
    this.covered = compound.func_74767_n("covered");
    this.claimed = compound.func_74767_n("claimed");
    this.built = compound.func_74767_n("built");
    this.searchStatus = SearchStatus.values()[compound.func_74762_e("searchStatus")];
    List<String> authorized = new ArrayList<>();
    NBTTagList list = compound.func_150295_c("authorizedList", 10);
    for (int i = 0; i < list.func_74745_c(); i++) {
      NBTTagCompound tag = list.func_150305_b(i);
      authorized.add(tag.func_74779_i("authorized" + i));
    } 
    this.authorizedList = authorized;
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    func_145841_b(nbttagcompound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, nbttagcompound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
    this.field_145850_b.func_147458_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\common\tiles\TileEntityHomeFinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */