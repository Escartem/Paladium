package fr.paladium.palarpg.module.dungeon.common.entity.room.merchant;

import com.eliotlash.molang.MolangException;
import com.eliotlash.molang.MolangParser;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.dungeon.client.ui.room.merchant.UIDungeonRoomMerchantTrade;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonChest;
import fr.paladium.palarpg.module.dungeon.common.event.DungeonWorldUpdateEvent;
import fr.paladium.palarpg.module.dungeon.common.network.room.merchant.SCPacketRPGDungeonRoomMerchantAnimation;
import fr.paladium.palarpg.module.dungeon.common.network.room.merchant.SCPacketRPGDungeonRoomMerchantOpen;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelLootsConfig;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemType;
import fr.paladium.palarpg.module.equipment.common.loader.cache.RPGItemsCache;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.AStatCapability;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatCapabilityMutator;
import fr.paladium.palarpg.module.stat.server.manager.StatsManager;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class EntityDungeonMerchant extends Entity implements IEntityAdditionalSpawnData {
  public void setMephisto(boolean mephisto) {
    this.mephisto = mephisto;
  }
  
  private static final List<EntityDungeonMerchantMephistoTrade> MEPHISTO_TRADES = new ArrayList<>();
  
  static {
    MEPHISTO_TRADES.add(new EntityDungeonMerchantMephistoTrade(new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre les dégâts I", EnumStats.MAX_HEALTH, 0.25D), new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre les dégâts I", EnumStats.DAMAGE, 0.5D)));
    MEPHISTO_TRADES.add(new EntityDungeonMerchantMephistoTrade(new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre la résistance I", EnumStats.MAX_HEALTH, 0.25D), new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre la résistance I", EnumStats.RESISTANCE, 0.5D)));
    MEPHISTO_TRADES.add(new EntityDungeonMerchantMephistoTrade(new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre les dégâts critiques I", EnumStats.MAX_HEALTH, 0.25D), new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre les dégâts critiques I", EnumStats.CRITICAL_DAMAGE, 0.5D)));
    MEPHISTO_TRADES.add(new EntityDungeonMerchantMephistoTrade(new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre la chance critique I", EnumStats.MAX_HEALTH, 0.25D), new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre la chance critique I", EnumStats.CRITICAL_CHANCE, 0.5D)));
    MEPHISTO_TRADES.add(new EntityDungeonMerchantMephistoTrade(new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre le butin I", EnumStats.MAX_HEALTH, 0.25D), new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre le butin I", EnumStats.BONUS_LOOT, 0.2D)));
    MEPHISTO_TRADES.add(new EntityDungeonMerchantMephistoTrade(new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre les dégâts II", EnumStats.MAX_HEALTH, 0.5D), new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre les dégâts II", EnumStats.DAMAGE, 1.0D)));
    MEPHISTO_TRADES.add(new EntityDungeonMerchantMephistoTrade(new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre la résistance II", EnumStats.MAX_HEALTH, 0.5D), new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre la résistance II", EnumStats.RESISTANCE, 1.0D)));
    MEPHISTO_TRADES.add(new EntityDungeonMerchantMephistoTrade(new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre les dégâts critiques II", EnumStats.MAX_HEALTH, 0.5D), new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre les dégâts critiques II", EnumStats.CRITICAL_DAMAGE, 1.0D)));
    MEPHISTO_TRADES.add(new EntityDungeonMerchantMephistoTrade(new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre la chance critique II", EnumStats.MAX_HEALTH, 0.5D), new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre la chance critique II", EnumStats.CRITICAL_CHANCE, 1.0D)));
    MEPHISTO_TRADES.add(new EntityDungeonMerchantMephistoTrade(new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre le butin II", EnumStats.MAX_HEALTH, 0.5D), new EntityDungeonMerchantMephistoTrade.EntityDungeonMerchantMephistoTradePart("la vie contre le butin II", EnumStats.BONUS_LOOT, 0.4D)));
  }
  
  private static final Map<RPGItemRarity, Integer> MAMMON_WEIGHTS = new HashMap<>();
  
  private final Set<String> traded;
  
  private final Map<String, IEntityDungeonMerchantTrade[]> items;
  
  private boolean mephisto;
  
  public Set<String> getTraded() {
    return this.traded;
  }
  
  public Map<String, IEntityDungeonMerchantTrade[]> getItems() {
    return this.items;
  }
  
  public boolean isMephisto() {
    return this.mephisto;
  }
  
  public EntityDungeonMerchant(World world) {
    super(world);
    MAMMON_WEIGHTS.put(RPGItemRarity.COMMON, Integer.valueOf(50));
    MAMMON_WEIGHTS.put(RPGItemRarity.RARE, Integer.valueOf(30));
    MAMMON_WEIGHTS.put(RPGItemRarity.EPIC, Integer.valueOf(15));
    MAMMON_WEIGHTS.put(RPGItemRarity.LEGENDARY, Integer.valueOf(5));
    this.traded = new HashSet<>();
    this.items = (Map)new HashMap<>();
    func_70105_a(0.6F, 1.8F);
    this.field_70178_ae = true;
    MinecraftForge.EVENT_BUS.register(this);
    FMLCommonHandler.instance().bus().register(this);
  }
  
  protected void func_70088_a() {}
  
  public boolean func_130002_c(EntityPlayer player) {
    if (player.field_70170_p.field_72995_K || !(player instanceof EntityPlayerMP))
      return false; 
    EntityPlayerMP playerMP = (EntityPlayerMP)player;
    (new SCPacketRPGDungeonRoomMerchantAnimation(func_145782_y(), "interact")).send(playerMP);
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(this.field_70170_p);
    if (!optionalDungeonWorld.isPresent()) {
      playerMP.field_71135_a.func_147359_a((Packet)new S29PacketSoundEffect("mob.villager.no", this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0F, 1.0F));
      (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue", this.mephisto ? "mephisto" : "mammon")).send(playerMP);
      return false;
    } 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    Optional<DungeonPlayer> optionalDungeonPlayer = dungeonWorld.getPlayer(UUIDUtils.toString((Entity)playerMP));
    if (!optionalDungeonPlayer.isPresent()) {
      playerMP.field_71135_a.func_147359_a((Packet)new S29PacketSoundEffect("mob.villager.no", this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0F, 1.0F));
      (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue", this.mephisto ? "mephisto" : "mammon")).send(playerMP);
      return false;
    } 
    DungeonPlayer dungeonPlayer = optionalDungeonPlayer.get();
    if (!dungeonPlayer.isAlive())
      return false; 
    if (hasTraded(player)) {
      playerMP.field_71135_a.func_147359_a((Packet)new S29PacketSoundEffect("mob.villager.no", this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0F, 1.0F));
      (new Notification(Notification.NotificationType.INFO, "Ce marchant n'a plus rien à vous vendre", this.mephisto ? "mephisto" : "mammon")).send(playerMP);
      return false;
    } 
    IEntityDungeonMerchantTrade[] trades = getItems(player);
    if (trades == null || trades.length == 0) {
      playerMP.field_71135_a.func_147359_a((Packet)new S29PacketSoundEffect("mob.villager.no", this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0F, 1.0F));
      (new Notification(this.mephisto ? Notification.NotificationType.ERROR : Notification.NotificationType.INFO, this.mephisto ? "Une erreur est survenue" : "Vous n'avez rien à échanger dans votre sac à dos", this.mephisto ? "mephisto" : "mammon")).send(playerMP);
      return false;
    } 
    (new SCPacketRPGDungeonRoomMerchantOpen(this.mephisto, new UIDungeonRoomMerchantTrade.UIDungeonRoomMerchantTradeData(func_145782_y(), trades))).send(playerMP);
    playerMP.field_71135_a.func_147359_a((Packet)new S29PacketSoundEffect("mob.villager.haggle", this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0F, 1.0F));
    return false;
  }
  
  public boolean func_70097_a(DamageSource source, float damage) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(this.field_70170_p);
    if (optionalDungeonWorld.isPresent())
      return false; 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (!(source.func_76364_f() instanceof EntityPlayer))
      return false; 
    EntityPlayer player = (EntityPlayer)source.func_76364_f();
    Optional<DungeonPlayer> optionalDungeonPlayer = dungeonWorld.getPlayer(UUIDUtils.toString((Entity)player));
    if (!optionalDungeonPlayer.isPresent())
      return false; 
    DungeonPlayer dungeonPlayer = optionalDungeonPlayer.get();
    if (!dungeonPlayer.isAlive())
      return false; 
    this.field_70170_p.func_72956_a(this, "mob.villager.no", 1.0F, 1.0F);
    return false;
  }
  
  protected void func_70037_a(NBTTagCompound nbt) {
    if (nbt.func_74764_b("Mephisto"))
      this.mephisto = nbt.func_74767_n("Mephisto"); 
    if (nbt.func_74764_b("Traded")) {
      this.traded.clear();
      NBTTagList tradedList = nbt.func_150295_c("Traded", 8);
      for (int i = 0; i < tradedList.func_74745_c(); i++)
        this.traded.add(tradedList.func_150307_f(i)); 
    } 
    if (nbt.func_74764_b("Items")) {
      this.items.clear();
      NBTTagList itemsList = nbt.func_150295_c("Items", 10);
      for (int i = 0; i < itemsList.func_74745_c(); i++) {
        NBTTagCompound itemNbt = itemsList.func_150305_b(i);
        String uuid = itemNbt.func_74779_i("Player");
        NBTTagList tradesNbt = itemNbt.func_150295_c("Trades", 10);
        IEntityDungeonMerchantTrade[] trades = new IEntityDungeonMerchantTrade[tradesNbt.func_74745_c()];
        for (int j = 0; j < tradesNbt.func_74745_c(); j++) {
          NBTTagCompound tradeNbt = tradesNbt.func_150305_b(j);
          if (this.mephisto) {
            EntityDungeonMerchantMephistoTrade trade = new EntityDungeonMerchantMephistoTrade();
            trade.readFromNBT(tradeNbt);
            trades[j] = trade;
          } else {
            EntityDungeonMerchantMammonTrade trade = new EntityDungeonMerchantMammonTrade();
            trade.readFromNBT(tradeNbt);
            trades[j] = trade;
          } 
        } 
        this.items.put(uuid, trades);
      } 
    } 
  }
  
  protected void func_70014_b(NBTTagCompound nbt) {
    nbt.func_74757_a("Mephisto", this.mephisto);
    NBTTagList tradedList = new NBTTagList();
    for (String uuid : this.traded)
      tradedList.func_74742_a((NBTBase)new NBTTagString(uuid)); 
    nbt.func_74782_a("Traded", (NBTBase)tradedList);
    NBTTagList itemsList = new NBTTagList();
    for (Map.Entry<String, IEntityDungeonMerchantTrade[]> entry : this.items.entrySet()) {
      NBTTagCompound itemNbt = new NBTTagCompound();
      itemNbt.func_74778_a("Player", entry.getKey());
      NBTTagList tradesNbt = new NBTTagList();
      for (IEntityDungeonMerchantTrade trade : (IEntityDungeonMerchantTrade[])entry.getValue()) {
        NBTTagCompound tradeNbt = new NBTTagCompound();
        trade.writeToNBT(tradeNbt);
        tradesNbt.func_74742_a((NBTBase)tradeNbt);
      } 
      itemNbt.func_74782_a("Trades", (NBTBase)tradesNbt);
      itemsList.func_74742_a((NBTBase)itemNbt);
    } 
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    this.mephisto = additionalData.readBoolean();
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    buffer.writeBoolean(this.mephisto);
  }
  
  public boolean func_70067_L() {
    return true;
  }
  
  public boolean func_70104_M() {
    return false;
  }
  
  @SideOnly(Side.SERVER)
  public boolean hasTraded(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    return this.traded.contains(UUIDUtils.toString((Entity)player));
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public EntityDungeonMerchant setTraded(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    this.traded.add(UUIDUtils.toString((Entity)player));
    return this;
  }
  
  @SideOnly(Side.SERVER)
  public IEntityDungeonMerchantTrade[] getItems(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(this.field_70170_p);
    if (!optionalDungeonWorld.isPresent())
      return null; 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (dungeonWorld.getState() != DungeonWorld.DungeonState.STARTED || !dungeonWorld.getRoom().isPresent())
      return null; 
    String uuid = UUIDUtils.toString((Entity)player);
    if (!this.items.containsKey(uuid)) {
      IEntityDungeonMerchantTrade[] trades = this.mephisto ? generateMephisto(player) : generateMammon(player);
      if (trades == null)
        return null; 
      this.items.put(uuid, trades);
    } 
    return this.items.get(uuid);
  }
  
  private IEntityDungeonMerchantTrade[] generateMephisto(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    IEntityDungeonMerchantTrade[] trades = new IEntityDungeonMerchantTrade[3];
    List<IEntityDungeonMerchantTrade> availableTrades = new ArrayList<>((Collection)MEPHISTO_TRADES);
    if (availableTrades.size() < 3)
      return null; 
    for (int i = 0; i < trades.length; i++) {
      IEntityDungeonMerchantTrade trade = availableTrades.get(this.field_70170_p.field_73012_v.nextInt(availableTrades.size()));
      availableTrades.remove(trade);
      trades[i] = trade;
    } 
    return trades;
  }
  
  private IEntityDungeonMerchantTrade[] generateMammon(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    RPGDungeonPlayerData playerData = (RPGDungeonPlayerData)RPGPlayer.getData((Entity)player, "dungeon");
    if (playerData == null || playerData.getBackpack() == null || playerData.getBackpack().isEmpty())
      return null; 
    int totalWeight = 0;
    Map<RPGDungeonPlayerData.RPGDungeonItem, Integer> backpackItems = new HashMap<>();
    for (RPGDungeonPlayerData.RPGDungeonItem item : playerData.getBackpack()) {
      if (!MAMMON_WEIGHTS.containsKey(item.getRarity()))
        continue; 
      int weight = ((Integer)MAMMON_WEIGHTS.get(item.getRarity())).intValue();
      backpackItems.put(item, Integer.valueOf(weight));
      totalWeight += weight;
    } 
    if (backpackItems.isEmpty())
      return null; 
    DungeonWorld dungeonWorld = DungeonWorld.get(this.field_70170_p).get();
    MolangParser parser = dungeonWorld.getParser();
    DungeonRoom room = dungeonWorld.getRoom().get();
    if (!room.hasLoot())
      return null; 
    List<IEntityDungeonMerchantTrade> availableTrades = new ArrayList<>();
    int i;
    for (i = 0; i < 19; i++) {
      RPGDungeonPlayerData.RPGDungeonItem input = null;
      int random = this.field_70170_p.field_73012_v.nextInt(totalWeight);
      for (RPGDungeonPlayerData.RPGDungeonItem item : backpackItems.keySet()) {
        random -= ((Integer)backpackItems.get(item)).intValue();
        if (random <= 0) {
          input = item;
          break;
        } 
      } 
      if (input != null) {
        RPGDungeonPlayerData.RPGDungeonItem output = null;
        double bonusLoot = 0.0D;
        try {
          bonusLoot = parser.parseJson(((DungeonLevelLootsConfig.DungeonLevelLootsChestElementConfig)room.getLoots().getChests().get(TileEntityDungeonChest.DungeonChestRarity.COMMON)).getBonus()).get();
        } catch (MolangException e) {
          e.printStackTrace();
        } 
        double minRewardsWeight = Double.MAX_VALUE;
        List<DungeonLevelLootsConfig.DungeonLevelLootsElementConfig> loots = room.getLoots().getDefaultLoots();
        if (!loots.isEmpty()) {
          Collections.shuffle(loots);
          Map<DungeonLevelLootsConfig.DungeonLevelLootsElementConfig, Double> weights = new HashMap<>();
          for (DungeonLevelLootsConfig.DungeonLevelLootsElementConfig loot : loots) {
            try {
              double weight = parser.parseJson(loot.getWeight()).get();
              weights.put(loot, Double.valueOf(weight));
              if (weight < minRewardsWeight)
                minRewardsWeight = weight; 
            } catch (MolangException e) {
              e.printStackTrace();
            } 
          } 
          if (!weights.isEmpty()) {
            double totalRewardsWeight = 0.0D;
            double playerBonusLoot = Math.max(1.0D, bonusLoot + StatsManager.BONUS_LOOT.getBonusLoot((EntityLivingBase)player, "default"));
            Map<DungeonLevelLootsConfig.DungeonLevelLootsElementConfig, Double> adjustedWeights = new HashMap<>();
            for (DungeonLevelLootsConfig.DungeonLevelLootsElementConfig loot : weights.keySet()) {
              double weight = ((Double)weights.get(loot)).doubleValue();
              double bonus = minRewardsWeight / weight;
              double adjustedWeight = weight * (1.0D + (playerBonusLoot - 1.0D) * bonus);
              totalRewardsWeight += adjustedWeight;
              adjustedWeights.put(loot, Double.valueOf(adjustedWeight));
            } 
            double currentWeight = 0.0D;
            double randomWeight = this.field_70170_p.field_73012_v.nextDouble() * totalRewardsWeight;
            for (Map.Entry<DungeonLevelLootsConfig.DungeonLevelLootsElementConfig, Double> entry : adjustedWeights.entrySet()) {
              currentWeight += ((Double)entry.getValue()).doubleValue();
              if (currentWeight >= randomWeight) {
                try {
                  output = new RPGDungeonPlayerData.RPGDungeonItem(UUID.randomUUID(), "default", ((DungeonLevelLootsConfig.DungeonLevelLootsElementConfig)entry.getKey()).create(parser), ((DungeonLevelLootsConfig.DungeonLevelLootsElementConfig)entry.getKey()).getRarity());
                } catch (Exception e) {
                  output = new RPGDungeonPlayerData.RPGDungeonItem(UUID.randomUUID(), "default", ((DungeonLevelLootsConfig.DungeonLevelLootsElementConfig)entry.getKey()).create(), ((DungeonLevelLootsConfig.DungeonLevelLootsElementConfig)entry.getKey()).getRarity());
                  continue;
                } 
                output.getItem().func_77973_b().func_77622_d(output.getItem(), this.field_70170_p, player);
                break;
              } 
            } 
            if (output != null)
              availableTrades.add(new EntityDungeonMerchantMammonTrade(input, output)); 
          } 
        } 
      } 
    } 
    for (i = 0; i < 1; i++) {
      RPGDungeonPlayerData.RPGDungeonItem input = null;
      int random = this.field_70170_p.field_73012_v.nextInt(totalWeight);
      for (RPGDungeonPlayerData.RPGDungeonItem item : backpackItems.keySet()) {
        random -= ((Integer)backpackItems.get(item)).intValue();
        if (random <= 0) {
          input = item;
          break;
        } 
      } 
      if (input != null) {
        RPGDungeonPlayerData.RPGDungeonItem output = null;
        List<IRPGItem> items = RPGItemsCache.getItems(RPGItemType.CONSUMABLE);
        if (!items.isEmpty()) {
          IRPGItem item = items.get(this.field_70170_p.field_73012_v.nextInt(items.size()));
          if (item != null) {
            output = new RPGDungeonPlayerData.RPGDungeonItem(UUID.randomUUID(), "default", new ItemStack((Item)item), item.getRPGRarity());
            availableTrades.add(new EntityDungeonMerchantMammonTrade(input, output));
          } 
        } 
      } 
    } 
    if (availableTrades.size() < 3)
      return null; 
    IEntityDungeonMerchantTrade[] trades = new IEntityDungeonMerchantTrade[3];
    for (int j = 0; j < trades.length; j++) {
      IEntityDungeonMerchantTrade trade = availableTrades.get(this.field_70170_p.field_73012_v.nextInt(availableTrades.size()));
      availableTrades.remove(trade);
      trades[j] = trade;
    } 
    return trades;
  }
  
  @SubscribeEvent
  @SideOnly(Side.SERVER)
  public void onDungeonWorldUpdate(DungeonWorldUpdateEvent.Post event) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(this.field_70170_p);
    if (!optionalDungeonWorld.isPresent())
      return; 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (event.getWorld().getUniqueId() != dungeonWorld.getUniqueId() || (event.getWorld().getState() != DungeonWorld.DungeonState.STARTING && event.getWorld().getState() != DungeonWorld.DungeonState.FINISHED))
      return; 
    for (EntityPlayer player : event.getWorld().getOnlinePlayers()) {
      RPGStatPlayerData playerData = (RPGStatPlayerData)RPGPlayer.getData((Entity)player, "stats");
      if (playerData == null)
        return; 
      for (AStatCapability<?> capability : (Iterable<AStatCapability<?>>)playerData.getCapabilities())
        capability.removeMutator(mutator -> (mutator.getId() != null && mutator.getId().startsWith("DUNGEON_MERCHANT"))); 
      playerData.applyAndSync();
    } 
  }
  
  public static class EntityDungeonMerchantMephistoTrade implements IEntityDungeonMerchantTrade {
    private EntityDungeonMerchantMephistoTradePart input;
    
    private EntityDungeonMerchantMephistoTradePart output;
    
    public EntityDungeonMerchantMephistoTrade() {}
    
    public EntityDungeonMerchantMephistoTrade(EntityDungeonMerchantMephistoTradePart input, EntityDungeonMerchantMephistoTradePart output) {
      this.input = input;
      this.output = output;
    }
    
    public void readFromNBT(NBTTagCompound nbt) {
      NBTTagCompound nbtInput = nbt.func_74775_l("Input");
      this.input = new EntityDungeonMerchantMephistoTradePart(nbtInput.func_74779_i("name"), EnumStats.valueOf(nbtInput.func_74779_i("stat")), nbtInput.func_74769_h("value"));
      NBTTagCompound nbtOutput = nbt.func_74775_l("Output");
      this.output = new EntityDungeonMerchantMephistoTradePart(nbtInput.func_74779_i("name"), EnumStats.valueOf(nbtOutput.func_74779_i("stat")), nbtOutput.func_74769_h("value"));
    }
    
    public void writeToNBT(NBTTagCompound nbt) {
      NBTTagCompound nbtInput = new NBTTagCompound();
      nbtInput.func_74778_a("name", this.input.getName());
      nbtInput.func_74778_a("stat", this.input.getStat().name());
      nbtInput.func_74780_a("value", this.input.getValue());
      nbt.func_74782_a("Input", (NBTBase)nbtInput);
      NBTTagCompound nbtOutput = new NBTTagCompound();
      nbtOutput.func_74778_a("name", this.output.getName());
      nbtOutput.func_74778_a("stat", this.output.getStat().name());
      nbtOutput.func_74780_a("value", this.output.getValue());
      nbt.func_74782_a("Output", (NBTBase)nbtOutput);
    }
    
    public EntityDungeonMerchantMephistoTradePart getInput() {
      return this.input;
    }
    
    public EntityDungeonMerchantMephistoTradePart getOutput() {
      return this.output;
    }
    
    public static class EntityDungeonMerchantMephistoTradePart {
      private final String name;
      
      private final EnumStats stat;
      
      private final double value;
      
      public EntityDungeonMerchantMephistoTradePart(String name, EnumStats stat, double value) {
        this.name = name;
        this.stat = stat;
        this.value = value;
      }
      
      public String getName() {
        return this.name;
      }
      
      public EnumStats getStat() {
        return this.stat;
      }
      
      public double getValue() {
        return this.value;
      }
    }
  }
  
  public static class EntityDungeonMerchantMephistoTradePart {
    private final String name;
    
    private final EnumStats stat;
    
    private final double value;
    
    public EntityDungeonMerchantMephistoTradePart(String name, EnumStats stat, double value) {
      this.name = name;
      this.stat = stat;
      this.value = value;
    }
    
    public String getName() {
      return this.name;
    }
    
    public EnumStats getStat() {
      return this.stat;
    }
    
    public double getValue() {
      return this.value;
    }
  }
  
  public static class EntityDungeonMerchantMammonTrade implements IEntityDungeonMerchantTrade {
    private RPGDungeonPlayerData.RPGDungeonItem input;
    
    private RPGDungeonPlayerData.RPGDungeonItem output;
    
    public String toString() {
      return "EntityDungeonMerchant.EntityDungeonMerchantMammonTrade(input=" + getInput() + ", output=" + getOutput() + ")";
    }
    
    public EntityDungeonMerchantMammonTrade() {}
    
    public EntityDungeonMerchantMammonTrade(RPGDungeonPlayerData.RPGDungeonItem input, RPGDungeonPlayerData.RPGDungeonItem output) {
      this.input = input;
      this.output = output;
    }
    
    public RPGDungeonPlayerData.RPGDungeonItem getInput() {
      return this.input;
    }
    
    public RPGDungeonPlayerData.RPGDungeonItem getOutput() {
      return this.output;
    }
    
    public void readFromNBT(NBTTagCompound nbt) {
      NBTTagCompound nbtInput = nbt.func_74775_l("Input");
      UUID uniqueId = UUIDUtils.from(nbtInput.func_74779_i("uniqueId"));
      String type = nbtInput.func_74779_i("type");
      ItemStack item = ItemStack.func_77949_a(nbtInput.func_74775_l("item"));
      RPGItemRarity rarity = RPGItemRarity.values()[nbtInput.func_74762_e("rarity")];
      this.input = new RPGDungeonPlayerData.RPGDungeonItem(uniqueId, type, item, rarity);
      NBTTagCompound nbtOutput = nbt.func_74775_l("Output");
      UUID uniqueIdOut = UUIDUtils.from(nbtOutput.func_74779_i("uniqueId"));
      String typeOut = nbtOutput.func_74779_i("type");
      ItemStack itemOut = ItemStack.func_77949_a(nbtOutput.func_74775_l("item"));
      RPGItemRarity rarityOut = RPGItemRarity.values()[nbtOutput.func_74762_e("rarity")];
      this.output = new RPGDungeonPlayerData.RPGDungeonItem(uniqueIdOut, typeOut, itemOut, rarityOut);
    }
    
    public void writeToNBT(NBTTagCompound nbt) {
      NBTTagCompound nbtInput = new NBTTagCompound();
      nbtInput.func_74778_a("uniqueId", UUIDUtils.toString(this.input.getUniqueId()));
      nbtInput.func_74778_a("type", this.input.getType());
      nbtInput.func_74782_a("item", (NBTBase)this.input.getItem().func_77955_b(new NBTTagCompound()));
      nbtInput.func_74768_a("rarity", this.input.getRarity().ordinal());
      nbt.func_74782_a("Input", (NBTBase)nbtInput);
      NBTTagCompound nbtOutput = new NBTTagCompound();
      nbtOutput.func_74778_a("uniqueId", UUIDUtils.toString(this.output.getUniqueId()));
      nbtOutput.func_74778_a("type", this.output.getType());
      nbtOutput.func_74782_a("item", (NBTBase)this.output.getItem().func_77955_b(new NBTTagCompound()));
      nbtOutput.func_74768_a("rarity", this.output.getRarity().ordinal());
      nbt.func_74782_a("Output", (NBTBase)nbtOutput);
    }
  }
  
  public static interface IEntityDungeonMerchantTrade {
    void readFromNBT(NBTTagCompound param1NBTTagCompound);
    
    void writeToNBT(NBTTagCompound param1NBTTagCompound);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\entity\room\merchant\EntityDungeonMerchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */