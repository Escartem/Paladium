package fr.paladium.palarpg.module.dungeon.common.block.tileentity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityDungeonChest extends TileEntity {
  private transient DungeonRoom room;
  
  public void setRoom(DungeonRoom room) {
    this.room = room;
  }
  
  public void setFinished(boolean finished) {
    this.finished = finished;
  }
  
  public void setDirection(ForgeDirection direction) {
    this.direction = direction;
  }
  
  public void setRarity(DungeonChestRarity rarity) {
    this.rarity = rarity;
  }
  
  public void setRewards(Map<String, RPGDungeonPlayerData.RPGDungeonItem[]> rewards) {
    this.rewards = rewards;
  }
  
  public DungeonRoom getRoom() {
    return this.room;
  }
  
  public boolean isFinished() {
    return this.finished;
  }
  
  public ForgeDirection getDirection() {
    return this.direction;
  }
  
  public DungeonChestRarity getRarity() {
    return this.rarity;
  }
  
  public Map<String, RPGDungeonPlayerData.RPGDungeonItem[]> getRewards() {
    return this.rewards;
  }
  
  private boolean finished = false;
  
  private ForgeDirection direction = ForgeDirection.NORTH;
  
  private DungeonChestRarity rarity = DungeonChestRarity.COMMON;
  
  private Map<String, RPGDungeonPlayerData.RPGDungeonItem[]> rewards = (Map)new HashMap<>();
  
  public boolean canUpdate() {
    return false;
  }
  
  public void func_145839_a(NBTTagCompound nbt) {
    WorldServer worldServer;
    super.func_145839_a(nbt);
    if (nbt.func_74764_b("finished"))
      this.finished = nbt.func_74767_n("finished"); 
    if (nbt.func_74764_b("direction"))
      this.direction = ForgeDirection.getOrientation(nbt.func_74762_e("direction")); 
    if (nbt.func_74764_b("rarity"))
      this.rarity = DungeonChestRarity.values()[nbt.func_74762_e("rarity")]; 
    if (nbt.func_74764_b("rewards")) {
      this.rewards = (Map)new HashMap<>();
      NBTTagList rewardList = nbt.func_150295_c("rewards", 10);
      for (int i = 0; i < rewardList.func_74745_c(); i++) {
        NBTTagCompound rewardTag = rewardList.func_150305_b(i);
        String player = rewardTag.func_74779_i("player");
        NBTTagList itemsTag = rewardTag.func_150295_c("items", 10);
        RPGDungeonPlayerData.RPGDungeonItem[] items = new RPGDungeonPlayerData.RPGDungeonItem[itemsTag.func_74745_c()];
        for (int j = 0; j < itemsTag.func_74745_c(); j++) {
          NBTTagCompound itemTag = itemsTag.func_150305_b(j);
          UUID uniqueId = UUIDUtils.from(itemTag.func_74779_i("uniqueId"));
          String type = itemTag.func_74779_i("type");
          ItemStack item = ItemStack.func_77949_a(itemTag.func_74775_l("item"));
          RPGItemRarity itemRarity = RPGItemRarity.values()[itemTag.func_74762_e("rarity")];
          items[j] = new RPGDungeonPlayerData.RPGDungeonItem(uniqueId, type, item, itemRarity);
        } 
        this.rewards.put(player, items);
      } 
    } 
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT || !nbt.func_74764_b("worldName") || !nbt.func_74764_b("roomIndex"))
      return; 
    World world = null;
    String worldName = nbt.func_74779_i("worldName");
    for (WorldServer worldServer1 : (MinecraftServer.func_71276_C()).field_71305_c) {
      if (worldServer1.func_72912_H().func_76065_j().equals(worldName)) {
        worldServer = worldServer1;
        break;
      } 
    } 
    if (worldServer == null)
      return; 
    Optional<DungeonWorld> optionalWorld = DungeonWorld.get((World)worldServer);
    if (!optionalWorld.isPresent())
      return; 
    int roomIndex = nbt.func_74762_e("roomIndex");
    if (roomIndex < 0 || roomIndex >= ((DungeonWorld)optionalWorld.get()).getRooms().size())
      return; 
    this.room = ((DungeonWorld)optionalWorld.get()).getRooms().get(roomIndex);
  }
  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74757_a("finished", (this.room != null && this.room.isFinished()));
    nbt.func_74768_a("direction", this.direction.ordinal());
    nbt.func_74768_a("rarity", this.rarity.ordinal());
    NBTTagList rewardList = new NBTTagList();
    for (Map.Entry<String, RPGDungeonPlayerData.RPGDungeonItem[]> entry : this.rewards.entrySet()) {
      NBTTagCompound rewardTag = new NBTTagCompound();
      rewardTag.func_74778_a("player", entry.getKey());
      NBTTagList itemsTag = new NBTTagList();
      for (RPGDungeonPlayerData.RPGDungeonItem reward : (RPGDungeonPlayerData.RPGDungeonItem[])entry.getValue()) {
        NBTTagCompound itemTag = new NBTTagCompound();
        NBTTagCompound itemCompound = new NBTTagCompound();
        reward.getItem().func_77955_b(itemCompound);
        itemTag.func_74778_a("uniqueId", UUIDUtils.toString(reward.getUniqueId()));
        itemTag.func_74778_a("type", reward.getType());
        itemTag.func_74782_a("item", (NBTBase)itemCompound);
        itemTag.func_74768_a("rarity", reward.getRarity().ordinal());
        itemsTag.func_74742_a((NBTBase)itemTag);
      } 
      rewardTag.func_74782_a("items", (NBTBase)itemsTag);
      rewardList.func_74742_a((NBTBase)rewardTag);
    } 
    nbt.func_74782_a("rewards", (NBTBase)rewardList);
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
      return; 
    if (this.room != null) {
      nbt.func_74778_a("worldName", this.room.getWorld().getWorld().func_72912_H().func_76065_j());
      nbt.func_74768_a("roomIndex", this.room.getWorld().getRooms().indexOf(this.room));
    } 
  }
  
  public Packet func_145844_m() {
    NBTTagCompound compound = new NBTTagCompound();
    func_145841_b(compound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, compound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
    this.field_145850_b.func_147458_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  @NonNull
  public TileEntityDungeonChest setRewards(@NonNull String player, @NonNull RPGDungeonPlayerData.RPGDungeonItem[] rewards) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (rewards == null)
      throw new NullPointerException("rewards is marked non-null but is null"); 
    this.rewards.put(player, rewards);
    func_70296_d();
    if (this.field_145850_b != null)
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e); 
    return this;
  }
  
  @NonNull
  public TileEntityDungeonChest removeRewards(@NonNull String player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    this.rewards.remove(player);
    func_70296_d();
    if (this.field_145850_b != null)
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e); 
    return this;
  }
  
  @NonNull
  public Optional<RPGDungeonPlayerData.RPGDungeonItem[]> getRewards(@NonNull String player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    return (Optional)Optional.ofNullable(this.rewards.get(player));
  }
  
  public float getRotation() {
    switch (this.direction) {
      case NORTH:
        return 180.0F;
      case EAST:
        return 270.0F;
      case SOUTH:
        return 0.0F;
      case WEST:
        return 90.0F;
    } 
    return 0.0F;
  }
  
  public enum DungeonChestRarity {
    COMMON("Commun"),
    RARE("Rare"),
    LEGENDARY("Légendaire");
    
    DungeonChestRarity(String name) {
      this.name = name;
    }
    
    private final String name;
    
    public String getName() {
      return this.name;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\block\tileentity\TileEntityDungeonChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */