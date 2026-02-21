package fr.paladium.palarpg.module.dungeon.common.playerdata;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.time.UniversalTimeUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.common.extended.playerdata.APlayerData;
import fr.paladium.palarpg.common.extended.playerdata.PlayerData;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonConfig;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelConfig;
import fr.paladium.palarpg.module.dungeon.server.manager.DungeonRankingManager;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

@PlayerData("dungeon")
public class RPGDungeonPlayerData extends APlayerData {
  public static final String ID = "dungeon";
  
  public Set<RPGDungeonItem> getBackpack() {
    return this.backpack;
  }
  
  public Map<String, String> getInvitationsMap() {
    return this.invitationsMap;
  }
  
  public Map<String, Integer> getUnlockedLevelMap() {
    return this.unlockedLevelMap;
  }
  
  public long getLastKeyringReward() {
    return this.lastKeyringReward;
  }
  
  public String getJoiningDungeonId() {
    return this.joiningDungeonId;
  }
  
  private final Set<RPGDungeonItem> backpack = new HashSet<>();
  
  private final Map<String, String> invitationsMap = new HashMap<>();
  
  private final Map<String, Integer> unlockedLevelMap = new HashMap<>();
  
  private long lastKeyringReward;
  
  private String joiningDungeonId;
  
  public void read(NBTTagCompound nbt, boolean saving) {
    this.backpack.clear();
    if (nbt.func_74764_b("backpack")) {
      NBTTagList list = nbt.func_150295_c("backpack", 10);
      for (int i = 0; i < list.func_74745_c(); i++) {
        NBTTagCompound itemNbt = list.func_150305_b(i);
        UUID uniqueId = UUIDUtils.from(itemNbt.func_74779_i("uniqueId"));
        ItemStack item = ItemStack.func_77949_a(itemNbt.func_74775_l("item"));
        RPGItemRarity rarity = RPGItemRarity.values()[itemNbt.func_74762_e("rarity")];
        if (item != null && rarity != null)
          this.backpack.add(new RPGDungeonItem(uniqueId, "default", item, rarity)); 
      } 
    } 
    this.invitationsMap.clear();
    if (nbt.func_74764_b("invitationsMap")) {
      NBTTagCompound map = nbt.func_74775_l("invitationsMap");
      for (Object key : map.func_150296_c()) {
        String keyString = key.toString();
        this.invitationsMap.put(keyString, map.func_74779_i(keyString));
      } 
    } 
    this.unlockedLevelMap.clear();
    if (nbt.func_74764_b("unlockedLevelMap")) {
      NBTTagCompound map = nbt.func_74775_l("unlockedLevelMap");
      for (Object key : map.func_150296_c()) {
        String keyString = key.toString();
        this.unlockedLevelMap.put(keyString, Integer.valueOf(map.func_74762_e(keyString)));
      } 
    } 
    this.lastKeyringReward = nbt.func_74764_b("lastKeyringReward") ? nbt.func_74763_f("lastKeyringReward") : 0L;
    this.joiningDungeonId = nbt.func_74764_b("joiningDungeonId") ? nbt.func_74779_i("joiningDungeonId") : null;
  }
  
  public void write(NBTTagCompound nbt, boolean saving) {
    NBTTagList nbtBackpack = new NBTTagList();
    for (RPGDungeonItem item : this.backpack) {
      NBTTagCompound itemNbt = new NBTTagCompound();
      itemNbt.func_74778_a("uniqueId", UUIDUtils.toString(item.getUniqueId()));
      itemNbt.func_74782_a("item", (NBTBase)item.getItem().func_77955_b(new NBTTagCompound()));
      itemNbt.func_74768_a("rarity", item.getRarity().ordinal());
      nbtBackpack.func_74742_a((NBTBase)itemNbt);
    } 
    nbt.func_74782_a("backpack", (NBTBase)nbtBackpack);
    NBTTagCompound nbtInvitationsMap = new NBTTagCompound();
    for (Map.Entry<String, String> entry : this.invitationsMap.entrySet())
      nbtInvitationsMap.func_74778_a(entry.getKey(), entry.getValue()); 
    nbt.func_74782_a("invitationsMap", (NBTBase)nbtInvitationsMap);
    NBTTagCompound nbtUnlockedLevelMap = new NBTTagCompound();
    for (Map.Entry<String, Integer> entry : this.unlockedLevelMap.entrySet())
      nbtUnlockedLevelMap.func_74768_a(entry.getKey(), ((Integer)entry.getValue()).intValue()); 
    nbt.func_74782_a("unlockedLevelMap", (NBTBase)nbtUnlockedLevelMap);
    nbt.func_74772_a("lastKeyringReward", this.lastKeyringReward);
    if (this.joiningDungeonId != null)
      nbt.func_74778_a("joiningDungeonId", this.joiningDungeonId); 
  }
  
  public int getBackpackSize() {
    if (!(getEntity() instanceof EntityPlayer))
      return 0; 
    int size = 0;
    EntityPlayer player = (EntityPlayer)getEntity();
    for (ItemStack item : player.field_71071_by.field_70462_a) {
      if (item != null && item.func_77973_b() != null) {
        Optional<IRPGItem> optionalRPGItem = IRPGItem.get(item);
        if (optionalRPGItem.isPresent()) {
          IRPGItem rpgItem = optionalRPGItem.get();
          Optional<Number> optionalSize = rpgItem.getTag("backpack");
          if (optionalSize.isPresent()) {
            RPGProfilePlayerData profile = (RPGProfilePlayerData)RPGPlayer.getData((Entity)player, "profile");
            if (profile != null && rpgItem.getItemData().getRequiredLevel() <= profile.getLevel())
              size = Math.max(size, ((Number)optionalSize.get()).intValue()); 
          } 
        } 
      } 
    } 
    return size;
  }
  
  public boolean hasItemInBackpack(@NonNull UUID uniqueId) {
    if (uniqueId == null)
      throw new NullPointerException("uniqueId is marked non-null but is null"); 
    return this.backpack.stream().anyMatch(item -> item.getUniqueId().equals(uniqueId));
  }
  
  @NonNull
  public Optional<RPGDungeonItem> getItemInBackpack(@NonNull UUID uniqueId) {
    if (uniqueId == null)
      throw new NullPointerException("uniqueId is marked non-null but is null"); 
    return this.backpack.stream().filter(item -> item.getUniqueId().equals(uniqueId)).findFirst();
  }
  
  public boolean isLevelUnlocked(@NonNull DungeonConfig dungeon, @NonNull DungeonLevelConfig level) {
    if (dungeon == null)
      throw new NullPointerException("dungeon is marked non-null but is null"); 
    if (level == null)
      throw new NullPointerException("level is marked non-null but is null"); 
    return (getUnlockedLevel(dungeon) >= level.getLevel());
  }
  
  public int getUnlockedLevel(@NonNull DungeonConfig dungeon) {
    if (dungeon == null)
      throw new NullPointerException("dungeon is marked non-null but is null"); 
    return ((Integer)this.unlockedLevelMap.getOrDefault(dungeon.getId(), Integer.valueOf(0))).intValue();
  }
  
  public boolean isJoiningDungeon(@NonNull String dungeonId) {
    if (dungeonId == null)
      throw new NullPointerException("dungeonId is marked non-null but is null"); 
    return (this.joiningDungeonId != null && this.joiningDungeonId.equals(dungeonId));
  }
  
  @NonNull
  public RPGDungeonPlayerData addItemToBackpack(@NonNull RPGDungeonItem item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    this.backpack.add(item);
    sync();
    return this;
  }
  
  @NonNull
  public RPGDungeonPlayerData removeItemFromBackpack(@NonNull UUID uniqueId) {
    if (uniqueId == null)
      throw new NullPointerException("uniqueId is marked non-null but is null"); 
    this.backpack.removeIf(item -> item.getUniqueId().equals(uniqueId));
    sync();
    return this;
  }
  
  @NonNull
  public RPGDungeonPlayerData unlockLevel(@NonNull DungeonConfig dungeon, @NonNull DungeonLevelConfig level) {
    if (dungeon == null)
      throw new NullPointerException("dungeon is marked non-null but is null"); 
    if (level == null)
      throw new NullPointerException("level is marked non-null but is null"); 
    this.unlockedLevelMap.put(dungeon.getId(), Integer.valueOf(Math.max(((Integer)this.unlockedLevelMap.getOrDefault(dungeon.getId(), Integer.valueOf(0))).intValue(), level.getLevel())));
    if (FMLCommonHandler.instance().getSide() == Side.SERVER && getEntity() instanceof EntityPlayer)
      DungeonRankingManager.setGlobal(dungeon.getId(), (EntityPlayer)getEntity(), ((Integer)this.unlockedLevelMap.get(dungeon.getId())).intValue() + 1); 
    sync();
    return this;
  }
  
  @NonNull
  public RPGDungeonPlayerData setLastKeyringReward() {
    this.lastKeyringReward = UniversalTimeUtils.now();
    return this;
  }
  
  @NonNull
  public RPGDungeonPlayerData setJoiningDungeon(String dungeonId) {
    this.joiningDungeonId = dungeonId;
    sync();
    return this;
  }
  
  public static class RPGDungeonItem {
    private final UUID uniqueId;
    
    private final String type;
    
    private final ItemStack item;
    
    private final RPGItemRarity rarity;
    
    public String toString() {
      return "RPGDungeonPlayerData.RPGDungeonItem(uniqueId=" + getUniqueId() + ", type=" + getType() + ", item=" + getItem() + ", rarity=" + getRarity() + ")";
    }
    
    public RPGDungeonItem(UUID uniqueId, String type, ItemStack item, RPGItemRarity rarity) {
      this.uniqueId = uniqueId;
      this.type = type;
      this.item = item;
      this.rarity = rarity;
    }
    
    public UUID getUniqueId() {
      return this.uniqueId;
    }
    
    public String getType() {
      return this.type;
    }
    
    public ItemStack getItem() {
      return this.item;
    }
    
    public RPGItemRarity getRarity() {
      return this.rarity;
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { this.uniqueId });
    }
    
    public boolean equals(Object obj) {
      if (this == obj)
        return true; 
      if (obj == null || getClass() != obj.getClass())
        return false; 
      RPGDungeonItem other = (RPGDungeonItem)obj;
      return Objects.equals(this.uniqueId, other.uniqueId);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\playerdata\RPGDungeonPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */