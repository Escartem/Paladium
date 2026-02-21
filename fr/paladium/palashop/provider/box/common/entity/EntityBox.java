package fr.paladium.palashop.provider.box.common.entity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palaoracle.core.pojo.PalaOracleEvent;
import fr.paladium.palashop.provider.box.client.ui.UIShopBoxPage;
import fr.paladium.palashop.provider.box.client.utils.EntityBoxState;
import fr.paladium.palashop.provider.box.common.dto.box.Box;
import fr.paladium.palashop.provider.box.common.dto.box.BoxClient;
import fr.paladium.palashop.provider.box.common.dto.box.BoxData;
import fr.paladium.palashop.provider.box.common.dto.box.BoxReward;
import fr.paladium.palashop.provider.box.common.item.ItemBoxGift;
import fr.paladium.palashop.provider.box.common.manager.BoxManager;
import fr.paladium.palashop.provider.box.common.network.client.SCPacketBoxStart;
import fr.paladium.palashop.provider.box.common.network.client.SCPacketBoxStop;
import fr.paladium.palashop.provider.box.common.network.client.SCPacketBoxWait;
import fr.paladium.palashop.provider.item.dto.ItemShopItem;
import fr.paladium.palashop.server.shop.ShopManager;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.UsernameCache;

public class EntityBox extends EntityLiving implements IEntityAdditionalSpawnData {
  private final Set<UUID> watchers;
  
  private final Map<UUID, List<List<BoxReward>>> pendingRewards;
  
  private final List<List<EntityBoxBroadcastReward>> broadcastRewards;
  
  private String boxId;
  
  private BoxData boxData;
  
  private long lastAttack;
  
  private UUID active;
  
  private String activeName;
  
  private boolean fast;
  
  private int currentSpin;
  
  private long lastKeepAlive;
  
  @SideOnly(Side.CLIENT)
  private EntityBoxState clientState;
  
  public Set<UUID> getWatchers() {
    return this.watchers;
  }
  
  public Map<UUID, List<List<BoxReward>>> getPendingRewards() {
    return this.pendingRewards;
  }
  
  public List<List<EntityBoxBroadcastReward>> getBroadcastRewards() {
    return this.broadcastRewards;
  }
  
  public String getBoxId() {
    return this.boxId;
  }
  
  public BoxData getBoxData() {
    return this.boxData;
  }
  
  public long getLastAttack() {
    return this.lastAttack;
  }
  
  public UUID getActive() {
    return this.active;
  }
  
  public String getActiveName() {
    return this.activeName;
  }
  
  public boolean isFast() {
    return this.fast;
  }
  
  public int getCurrentSpin() {
    return this.currentSpin;
  }
  
  public long getLastKeepAlive() {
    return this.lastKeepAlive;
  }
  
  public EntityBoxState getClientState() {
    return this.clientState;
  }
  
  public EntityBox(@NonNull World world) {
    super(world);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    func_70606_j(1.0F);
    func_70105_a(3.0F, 3.0F);
    this.field_70158_ak = true;
    this.watchers = new HashSet<>();
    this.pendingRewards = new HashMap<>();
    this.broadcastRewards = new ArrayList<>();
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
      this.clientState = new EntityBoxState();
    } else {
      MinecraftForge.EVENT_BUS.register(this);
      FMLCommonHandler.instance().bus().register(this);
    } 
  }
  
  public EntityBox(@NonNull World world, @NonNull String boxId) {
    this(world);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    if (boxId == null)
      throw new NullPointerException("boxId is marked non-null but is null"); 
    this.boxId = boxId;
  }
  
  public boolean func_70085_c(EntityPlayer player) {
    if (this.boxData == null || this.field_70170_p.field_72995_K)
      return true; 
    if (isActive()) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCette box est déjà en cours d'ouverture par §b" + this.activeName + "§c."));
      SoundUtils.BLAZE_HIT.playSound((EntityPlayerMP)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 0.5F, 1.0F);
      return true;
    } 
    Optional<Box> optionalBox = this.boxData.getBox();
    if (!optionalBox.isPresent()) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCette box n'existe malheureusement plus."));
      SoundUtils.BLAZE_HIT.playSound((EntityPlayerMP)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 0.5F, 1.0F);
      return true;
    } 
    ZUIServer.open(UIShopBoxPage.class, (EntityPlayerMP)player, new Object[] { PacketSerialUtils.getGson().toJson(new BoxClient(optionalBox.get(), player)), Integer.valueOf(func_145782_y()) });
    return true;
  }
  
  public boolean func_70097_a(DamageSource source, float damage) {
    if (this.boxData == null || this.field_70170_p.field_72995_K)
      return false; 
    if (source.func_76364_f() instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)source.func_76364_f();
      if (player.field_71075_bZ.field_75098_d && player.func_70093_af()) {
        if (System.currentTimeMillis() - this.lastAttack < 1000L) {
          stop();
          func_70106_y();
        } else {
          this.lastAttack = System.currentTimeMillis();
          player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cTapez §cà §cnouveau §cpour §cdétruire §cla §cbox " + this.boxData.getName()));
          if (!this.pendingRewards.isEmpty()) {
            player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cAttention, la box est en cours d'ouverture ! §8(" + this.pendingRewards.size() + ")"));
            this.pendingRewards.forEach((uuid, rewards) -> {
                  EntityPlayer pendingPlayer = this.field_70170_p.func_152378_a(uuid);
                  String name = (pendingPlayer != null) ? pendingPlayer.getDisplayName() : UsernameCache.getLastKnownUsername(this.field_96093_i);
                  if (pendingPlayer != null)
                    player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §8- §b" + ((name == null) ? "Inconnu" : name))); 
                });
          } 
          SoundUtils.BLAZE_HIT.playSound((EntityPlayerMP)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 0.5F, 1.0F);
        } 
      } else {
        Optional<Box> optionalBox = this.boxData.getBox();
        if (!optionalBox.isPresent()) {
          player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCette box n'existe malheureusement plus."));
          SoundUtils.BLAZE_HIT.playSound((EntityPlayerMP)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 0.5F, 1.0F);
          return false;
        } 
        ZUIServer.open(UIShopBoxPage.class, (EntityPlayerMP)player, new Object[] { PacketSerialUtils.getGson().toJson(new BoxClient(optionalBox.get(), player)), Integer.valueOf(func_145782_y()) });
      } 
    } 
    return false;
  }
  
  @SideOnly(Side.SERVER)
  public boolean start(@NonNull EntityPlayer player, int count, boolean fast) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (this.boxData == null || this.field_70170_p.field_72995_K || count <= 0)
      return false; 
    Optional<Box> optionalBox = this.boxData.getBox();
    if (!optionalBox.isPresent())
      return false; 
    this.active = player.func_110124_au();
    this.activeName = player.func_70005_c_();
    this.fast = fast;
    this.lastKeepAlive = System.currentTimeMillis();
    this.currentSpin = 0;
    Box box = optionalBox.get();
    List<List<BoxReward>> pending = this.pendingRewards.getOrDefault(this.active, new LinkedList<>());
    int playerCount = box.getNumber(player).intValue();
    for (int i = 0; i < count; i++) {
      List<BoxReward> rewards = getRewards(box, playerCount);
      if (!rewards.isEmpty())
        pending.add(rewards); 
    } 
    int freeSpin = 0;
    int processedFreeSpin = 0;
    for (List<BoxReward> pendingRewards : pending)
      freeSpin = (int)(freeSpin + pendingRewards.stream().filter(reward -> (reward.getType() == BoxReward.Type.FREE_SPIN)).count()); 
    while (processedFreeSpin < freeSpin) {
      processedFreeSpin++;
      List<BoxReward> rewards = getRewards(box, playerCount);
      if (!rewards.isEmpty())
        pending.add(rewards); 
      freeSpin = 0;
      for (List<BoxReward> pendingRewards : pending)
        freeSpin = (int)(freeSpin + pendingRewards.stream().filter(reward -> (reward.getType() == BoxReward.Type.FREE_SPIN)).count()); 
    } 
    this.broadcastRewards.clear();
    for (List<BoxReward> rewardList : pending) {
      List<EntityBoxBroadcastReward> broadcastList = new ArrayList<>();
      for (BoxReward reward : rewardList)
        broadcastList.add(new EntityBoxBroadcastReward(reward)); 
      this.broadcastRewards.add(broadcastList);
    } 
    for (List<BoxReward> rewardList : pending) {
      for (BoxReward reward : rewardList) {
        if (reward.getType() != BoxReward.Type.SHOP_ITEM && reward.getType() != BoxReward.Type.FREE_SPIN)
          continue; 
        try {
          PalaOracleEvent oracleEvent = new PalaOracleEvent(UUIDUtils.toString((Entity)player), "[SHOP] Box reward");
          oracleEvent.addField("timestamp", Long.valueOf(System.currentTimeMillis()));
          oracleEvent.addField("boxId", box.getId());
          oracleEvent.addField("rewardType", reward.getType().name());
          oracleEvent.addField("rewardId", (reward.getType() == BoxReward.Type.SHOP_ITEM) ? reward.getShopItem().getUniqueId() : reward.getType().name());
          oracleEvent.addField("rewardName", (reward.getType() == BoxReward.Type.SHOP_ITEM) ? reward.getShopItem().getName() : reward.getType().name());
          oracleEvent.addField("rewardRarity", (reward.getType() == BoxReward.Type.SHOP_ITEM) ? reward.getShopItem().getRarity().name() : reward.getType().name());
          oracleEvent.addField("serverName", Server.getServerName());
          oracleEvent.addField("serverId", Server.getHostName());
          oracleEvent.capture();
        } catch (Exception e) {
          e.printStackTrace();
        } 
      } 
    } 
    this.pendingRewards.put(player.func_110124_au(), pending);
    SCPacketBoxStart packet = new SCPacketBoxStart(func_145782_y(), new SCPacketBoxStart.SCPacketBoxOpenData(this.activeName, this.fast, box.getRewards(), pending, this.currentSpin));
    this.watchers.add(player.func_110124_au());
    if (player instanceof EntityPlayerMP)
      packet.send((EntityPlayerMP)player); 
    PlayerSelector.AREA(this.field_70121_D.func_72314_b(256.0D, 256.0D, 256.0D), this.field_70170_p).apply(p -> {
          if (p.func_110124_au().equals(player.func_110124_au()))
            return; 
          packet.send(p);
          this.watchers.add(p.func_110124_au());
        });
    return true;
  }
  
  @SideOnly(Side.SERVER)
  public void next(int spinIndex) {
    if (this.boxData == null || !isActive())
      return; 
    Optional<Box> optionalBox = this.boxData.getBox();
    if (!optionalBox.isPresent())
      return; 
    Box box = optionalBox.get();
    List<List<BoxReward>> pending = this.pendingRewards.getOrDefault(this.active, new LinkedList<>());
    if (pending.isEmpty() || spinIndex < 0 || spinIndex >= pending.size())
      return; 
    this.currentSpin = spinIndex;
    SCPacketBoxStart packet = new SCPacketBoxStart(func_145782_y(), new SCPacketBoxStart.SCPacketBoxOpenData(this.activeName, this.fast, box.getRewards(), pending, this.currentSpin));
    for (UUID watcherUUID : this.watchers) {
      EntityPlayer watcher = this.field_70170_p.func_152378_a(watcherUUID);
      if (watcher instanceof EntityPlayerMP)
        packet.send((EntityPlayerMP)watcher); 
    } 
  }
  
  @SideOnly(Side.SERVER)
  public void stop() {
    if (this.boxData == null)
      return; 
    SCPacketBoxStop packet = new SCPacketBoxStop(func_145782_y(), true);
    for (UUID watcherUUID : this.watchers) {
      EntityPlayer watcher = this.field_70170_p.func_152378_a(watcherUUID);
      if (watcher instanceof EntityPlayerMP)
        packet.send((EntityPlayerMP)watcher); 
    } 
    if (this.active != null) {
      EntityPlayer activePlayer = this.field_70170_p.func_152378_a(this.active);
      if (activePlayer != null)
        give(activePlayer); 
    } 
    this.active = null;
    this.activeName = null;
    this.watchers.clear();
  }
  
  @SideOnly(Side.SERVER)
  public void stop(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (this.boxData == null || !isWatching(player))
      return; 
    if (this.active != null) {
      EntityPlayer activePlayer = this.field_70170_p.func_152378_a(this.active);
      if (activePlayer != null)
        give(activePlayer); 
    } 
    SCPacketBoxStop packet = new SCPacketBoxStop(func_145782_y(), false);
    for (UUID watcherUUID : this.watchers) {
      EntityPlayer watcher = this.field_70170_p.func_152378_a(watcherUUID);
      if (watcher instanceof EntityPlayerMP)
        packet.send((EntityPlayerMP)watcher); 
    } 
    this.active = null;
    this.activeName = null;
    this.watchers.clear();
  }
  
  @SideOnly(Side.SERVER)
  public void keepAlive() {
    if (this.boxData == null || !isActive())
      return; 
    this.lastKeepAlive = System.currentTimeMillis();
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  private List<BoxReward> getRewards(@NonNull Box box, int playerCount) {
    if (box == null)
      throw new NullPointerException("box is marked non-null but is null"); 
    List<BoxReward> rewards = new ArrayList<>();
    for (BoxReward reward : box.getRandomRewards(playerCount)) {
      if (reward.getType() == BoxReward.Type.SHOP_ITEM && reward.getShopItem().getRarity() != ShopRarity.COMMON && reward.getShopItem().getRarity() != ShopRarity.RARE) {
        rewards.add(new BoxReward(BoxReward.Type.BOOST_EPIC, Integer.valueOf(0), null));
        if (reward.getShopItem().getRarity() == ShopRarity.LEGENDARY)
          rewards.add(new BoxReward(BoxReward.Type.BOOST_LEGENDARY, Integer.valueOf(0), null)); 
      } 
      rewards.add(reward);
    } 
    return rewards;
  }
  
  @SideOnly(Side.SERVER)
  private void give(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (this.pendingRewards == null || this.pendingRewards.isEmpty() || this.boxData == null)
      return; 
    Optional<Box> optionalBox = this.boxData.getBox();
    if (!optionalBox.isPresent() || !this.pendingRewards.containsKey(player.func_110124_au()))
      return; 
    List<BoxReward> pending = (List<BoxReward>)((List)this.pendingRewards.remove(player.func_110124_au())).stream().flatMap(Collection::stream).collect(Collectors.toList());
    if (pending == null || pending.isEmpty())
      return; 
    Box box = optionalBox.get();
    List<IShopItem> rewards = new LinkedList<>();
    for (BoxReward pendingReward : pending) {
      if (pendingReward.getType() != BoxReward.Type.SHOP_ITEM)
        continue; 
      BoxReward reward = box.getShopReward(pendingReward.getShopItem().getUniqueId()).orElse(null);
      if (reward != null) {
        rewards.add(reward.getShopItem());
        continue;
      } 
      System.err.println("[PalaShop] Failed to find reward with ID: " + pendingReward + " in box: " + this.boxData.getName());
    } 
    if (rewards.isEmpty())
      return; 
    List<ItemBoxGift.ItemBoxGiftReward> giftRewards = new ArrayList<>();
    for (IShopItem reward : rewards) {
      if (reward instanceof ItemShopItem) {
        ItemShopItem item = (ItemShopItem)reward;
        if (item.getItemStack() != null)
          giftRewards.add(new ItemBoxGift.ItemBoxGiftReward(item.getItemStack().func_77946_l(), item.getRarity())); 
        continue;
      } 
      ShopManager.User.apply(player, reward);
    } 
    if (!giftRewards.isEmpty()) {
      player.field_71071_by.func_70441_a(ItemBoxGift.create(this.boxData, giftRewards));
      player.field_71069_bz.func_75142_b();
      SoundUtils.ITEM_PICKUP.playSound((EntityPlayerMP)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, 1.0F);
    } 
  }
  
  @SideOnly(Side.SERVER)
  private void updateWatchers() {
    Set<UUID> oldWatchers = new HashSet<>(this.watchers);
    Set<UUID> newWatchers = new HashSet<>();
    PlayerSelector.AREA(this.field_70121_D.func_72314_b(256.0D, 256.0D, 256.0D), this.field_70170_p).apply(p -> newWatchers.add(p.func_110124_au()));
    this.watchers.removeIf(uuid -> !newWatchers.contains(uuid));
    newWatchers.forEach(uuid -> {
          if (!this.watchers.contains(uuid))
            this.watchers.add(uuid); 
        });
    if (this.watchers.isEmpty()) {
      stop();
      return;
    } 
    if (isActive()) {
      Optional<Box> optionalBox = this.boxData.getBox();
      if (!optionalBox.isPresent() || !this.pendingRewards.containsKey(this.active))
        return; 
      List<List<BoxReward>> pending = this.pendingRewards.get(this.active);
      if (pending == null || pending.isEmpty())
        return; 
      Box box = optionalBox.get();
      SCPacketBoxWait packet = new SCPacketBoxWait(func_145782_y(), new SCPacketBoxWait.SCPacketBoxWaitData(this.activeName, this.fast, box.getRewards(), pending, this.currentSpin));
      for (UUID uuid : newWatchers) {
        if (!oldWatchers.contains(uuid)) {
          EntityPlayer watcher = this.field_70170_p.func_152378_a(uuid);
          if (watcher instanceof EntityPlayerMP)
            packet.send((EntityPlayerMP)watcher); 
        } 
      } 
    } 
  }
  
  public boolean isActive() {
    return (this.active != null);
  }
  
  public boolean isWatching(@NonNull UUID uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    return this.watchers.contains(uuid);
  }
  
  public boolean isWatching(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    return this.watchers.contains(player.func_110124_au());
  }
  
  public void func_70071_h_() {
    if (this.boxId == null)
      return; 
    if (this.boxData == null) {
      Optional<BoxData> optionalBox = BoxManager.getBox(this.boxId);
      if (!optionalBox.isPresent())
        return; 
      this.boxData = optionalBox.get();
    } 
    if (this.field_70170_p.field_72995_K || this.field_70173_aa % 20 != 0)
      return; 
    if (isActive() && System.currentTimeMillis() - this.lastKeepAlive > 60000L) {
      stop();
      return;
    } 
    updateWatchers();
    List<EntityPlayer> toGive = new ArrayList<>();
    for (UUID uuid : this.pendingRewards.keySet()) {
      if (this.active != null && this.active.equals(uuid))
        continue; 
      EntityPlayer player = this.field_70170_p.func_152378_a(uuid);
      if (player == null)
        continue; 
      toGive.add(player);
    } 
    toGive.forEach(this::give);
    toGive.clear();
  }
  
  public void func_110147_ax() {
    super.func_110147_ax();
    func_110140_aT().func_111151_a(SharedMonsterAttributes.field_111265_b).func_111128_a(0.0D);
    func_110140_aT().func_111151_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1.0D);
    func_110140_aT().func_111151_a(SharedMonsterAttributes.field_111266_c).func_111128_a(100.0D);
    func_110140_aT().func_111151_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
  }
  
  public boolean func_70650_aV() {
    return false;
  }
  
  public boolean func_70104_M() {
    return false;
  }
  
  public boolean func_96092_aw() {
    return false;
  }
  
  public boolean func_90999_ad() {
    return false;
  }
  
  public void func_70015_d(int value) {}
  
  public void func_70665_d(DamageSource source, float damage) {}
  
  public boolean func_70692_ba() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_70067_L() {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public ItemStack getPickedResult(MovingObjectPosition target) {
    if (this.boxData == null || this.boxData.getItem() == null)
      return null; 
    return new ItemStack(this.boxData.getItem());
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    this.boxId = nbt.func_74764_b("boxId") ? nbt.func_74779_i("boxId") : null;
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
      return; 
    if (nbt.func_74764_b("active")) {
      this.active = UUIDUtils.from(nbt.func_74779_i("active"));
      this.activeName = nbt.func_74779_i("activeName");
    } else {
      this.active = null;
      this.activeName = null;
    } 
    this.watchers.clear();
    if (nbt.func_74764_b("watchers")) {
      NBTTagList watcherList = nbt.func_150295_c("watchers", 8);
      for (int i = 0; i < watcherList.func_74745_c(); i++) {
        String uuidString = watcherList.func_150307_f(i);
        UUID uuid = UUIDUtils.from(uuidString);
        if (uuid != null)
          this.watchers.add(uuid); 
      } 
    } 
    this.pendingRewards.clear();
    if (nbt.func_74764_b("pendingRewards") && this.boxId != null && !this.boxId.isEmpty()) {
      Optional<BoxData> optionalBoxData = BoxManager.getBox(this.boxId);
      if (!optionalBoxData.isPresent()) {
        System.err.println("[PalaShop] Box with ID " + this.boxId + " not found when loading EntityBox, removing entity.");
        return;
      } 
      Optional<Box> optionalBox = ((BoxData)optionalBoxData.get()).getBox();
      if (!optionalBox.isPresent()) {
        System.err.println("[PalaShop] Box with ID " + this.boxId + " has no box data, removing entity.");
        return;
      } 
      NBTTagList pendingList = nbt.func_150295_c("pendingRewards", 10);
      for (int i = 0; i < pendingList.func_74745_c(); i++) {
        NBTTagCompound pendingEntry = pendingList.func_150305_b(i);
        UUID ownerId = UUIDUtils.from(pendingEntry.func_74779_i("owner"));
        List<List<BoxReward>> allRewardLists = new ArrayList<>();
        NBTTagList pendingEntryList = pendingEntry.func_150295_c("rewards", 9);
        for (int j = 0; j < pendingEntryList.func_74745_c(); j++) {
          NBTTagList rewardsList = (NBTTagList)pendingEntryList.func_74744_a(j);
          List<BoxReward> rewardList = new ArrayList<>();
          for (int k = 0; k < rewardsList.func_74745_c(); k++) {
            NBTTagCompound rewardTag = rewardsList.func_150305_b(k);
            BoxReward.Type type = BoxReward.Type.valueOf(rewardTag.func_74779_i("type"));
            if (type == BoxReward.Type.SHOP_ITEM) {
              String shopItemId = rewardTag.func_74779_i("shopItem");
              Optional<BoxReward> optionalReward = ((Box)optionalBox.get()).getShopReward(shopItemId);
              if (!optionalReward.isPresent()) {
                System.err.println("[PalaShop] Failed to find reward with ID: " + shopItemId + " in box: " + this.boxData.getName());
              } else {
                rewardList.add(optionalReward.get());
              } 
            } else {
              rewardList.add(new BoxReward(type, Integer.valueOf(0), null));
            } 
          } 
          allRewardLists.add(rewardList);
        } 
        this.pendingRewards.put(ownerId, allRewardLists);
      } 
    } 
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    if (this.boxId != null && !this.boxId.isEmpty())
      nbt.func_74778_a("boxId", this.boxId); 
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
      return; 
    if (this.active != null) {
      nbt.func_74778_a("active", UUIDUtils.toString(this.active));
      nbt.func_74778_a("activeName", (this.activeName != null) ? this.activeName : "");
    } 
    if (this.watchers != null && !this.watchers.isEmpty()) {
      NBTTagList watcherList = new NBTTagList();
      for (UUID watcher : this.watchers)
        watcherList.func_74742_a((NBTBase)new NBTTagString(UUIDUtils.toString(watcher))); 
      nbt.func_74782_a("watchers", (NBTBase)watcherList);
    } 
    if (this.pendingRewards != null) {
      NBTTagList pendingList = new NBTTagList();
      for (Map.Entry<UUID, List<List<BoxReward>>> entry : this.pendingRewards.entrySet()) {
        NBTTagCompound pendingEntry = new NBTTagCompound();
        pendingEntry.func_74778_a("owner", UUIDUtils.toString(entry.getKey()));
        NBTTagList pendingEntryList = new NBTTagList();
        for (List<BoxReward> rewardList : entry.getValue()) {
          NBTTagList pendingEntryRewards = new NBTTagList();
          for (BoxReward reward : rewardList) {
            NBTTagCompound rewardTag = new NBTTagCompound();
            rewardTag.func_74778_a("type", reward.getType().name());
            if (reward.getType() == BoxReward.Type.SHOP_ITEM)
              rewardTag.func_74778_a("shopItem", reward.getShopItem().getUniqueId()); 
            pendingEntryRewards.func_74742_a((NBTBase)rewardTag);
          } 
          pendingEntryList.func_74742_a((NBTBase)pendingEntryRewards);
        } 
        pendingEntry.func_74782_a("rewards", (NBTBase)pendingEntryList);
        pendingList.func_74742_a((NBTBase)pendingEntry);
      } 
      nbt.func_74782_a("pendingRewards", (NBTBase)pendingList);
    } 
  }
  
  public void writeSpawnData(ByteBuf buf) {
    if (this.boxId == null || this.boxId.isEmpty())
      return; 
    ByteBufUtils.writeUTF8String(buf, this.boxId);
  }
  
  public void readSpawnData(ByteBuf buf) {
    this.boxId = buf.isReadable() ? ByteBufUtils.readUTF8String(buf) : null;
  }
  
  @SubscribeEvent
  public void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
    updateWatchers();
  }
  
  @SubscribeEvent
  public void onLeave(PlayerEvent.PlayerLoggedOutEvent event) {
    if (!isWatching(event.player))
      return; 
    updateWatchers();
  }
  
  public class EntityBoxBroadcastReward {
    private final BoxReward reward;
    
    private boolean broadcasted;
    
    public void setBroadcasted(boolean broadcasted) {
      this.broadcasted = broadcasted;
    }
    
    public EntityBoxBroadcastReward(BoxReward reward) {
      this.reward = reward;
    }
    
    public BoxReward getReward() {
      return this.reward;
    }
    
    public boolean isBroadcasted() {
      return this.broadcasted;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\entity\EntityBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */