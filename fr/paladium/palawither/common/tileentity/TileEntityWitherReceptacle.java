package fr.paladium.palawither.common.tileentity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.factions.api.FactionAPI;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.faction.IFactionPlayer;
import fr.paladium.factions.api.player.IPlayer;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.location.BlockLocation;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.palaforgeutils.lib.sound.SoundRecordServer;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palawither.common.event.WitherInvokeEvent;
import fr.paladium.palawither.common.item.ItemWitherGem;
import fr.paladium.palawither.common.network.RBPacketWitherSpawn;
import fr.paladium.palawither.common.network.SCPacketWitherData;
import fr.paladium.palawither.common.network.SCPacketWitherSpawn;
import fr.paladium.palawither.common.utils.WitherUtils;
import fr.paladium.palawither.common.wither.base.IWither;
import fr.paladium.palawither.common.wither.condition.IWitherCondition;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityWitherReceptacle extends TileEntity {
  public static final double MAX_HEALTH = 15000.0D;
  
  public double getHealth() {
    return this.health;
  }
  
  public ItemStack getGem() {
    return this.gem;
  }
  
  public int getCooldown() {
    return this.cooldown;
  }
  
  public int getInitialCooldown() {
    return this.initialCooldown;
  }
  
  public int getCancelTimer() {
    return this.cancelTimer;
  }
  
  public boolean isDecreasingCooldown() {
    return this.decreasingCooldown;
  }
  
  public long getLastTick() {
    return this.lastTick;
  }
  
  public IWither getWither() {
    return this.wither;
  }
  
  public IFaction getFaction() {
    return this.faction;
  }
  
  public List<UUID> getPlayers() {
    return this.players;
  }
  
  public SoundRecord getSound() {
    return this.sound;
  }
  
  private double health = 15000.0D;
  
  private ItemStack gem;
  
  private int cooldown;
  
  private int initialCooldown;
  
  private int cancelTimer;
  
  private boolean decreasingCooldown;
  
  private long lastTick;
  
  private transient IWither wither;
  
  private transient IFaction faction;
  
  private transient List<UUID> players;
  
  @SideOnly(Side.CLIENT)
  private transient SoundRecord sound;
  
  public void damage(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if ((func_145831_w()).field_72995_K || player.field_70170_p.field_72995_K || player.field_71075_bZ.field_75098_d || this.health <= 0.0D)
      return; 
    damage(player.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e());
  }
  
  public void damage(double damage) {
    if ((func_145831_w()).field_72995_K || this.health <= 0.0D)
      return; 
    this.health -= damage;
    func_145831_w().func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "mob.wither.hurt", 0.2F, 1.0F);
    if (this.health <= 0.0D) {
      if (isActive()) {
        func_145831_w().func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "mob.wither.death", 0.2F, 1.0F);
        func_145831_w().func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      } else {
        func_145831_w().func_147480_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, true);
      } 
      reset();
    } else {
      update();
    } 
  }
  
  public void setCooldown(int cooldown) {
    this.cooldown = cooldown;
    update();
  }
  
  public void set(@NonNull EntityPlayer player, @NonNull ItemStack item) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    if (!(item.func_77973_b() instanceof ItemWitherGem)) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous devez tenir une gemme de Wither pour invoquer un Wither."));
      return;
    } 
    if (isActive()) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cUne invocation de Wither est déjà en cours."));
      return;
    } 
    if (this.field_145848_d < 5) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous ne pouvez pas invoquer un Wither au niveau de la bedrock."));
      return;
    } 
    ItemWitherGem gem = (ItemWitherGem)item.func_77973_b();
    try {
      IPlayer factionPlayer = FactionAPI.getInstance().getPlayer(player);
      boolean adminMode = (factionPlayer != null) ? factionPlayer.isAdminMode() : ForgeEnv.isIDE();
      if ((factionPlayer == null || !factionPlayer.hasFaction()) && !adminMode) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous devez appartenir à une faction pour invoquer un Wither."));
        return;
      } 
      IFaction faction = (factionPlayer == null) ? null : factionPlayer.getFaction();
      if (faction == null && !ForgeEnv.isIDE() && !adminMode) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous devez appartenir à une faction pour invoquer un Wither."));
        return;
      } 
      IWither wither = gem.create(player.field_70170_p);
      if (wither == null) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cUne erreur est survenue lors de l'invocation du Wither."));
        return;
      } 
      if (!ForgeEnv.isIDE() && !adminMode)
        for (int ox = -1; ox <= 1; ox++) {
          for (int oz = -1; oz <= 1; oz++) {
            if (FactionAPI.getInstance().isClaimed((this.field_145851_c >> 4) + ox, (this.field_145849_e >> 4) + oz)) {
              if (ox == 0 && oz == 0) {
                player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous ne pouvez pas invoquer un Wither dans une zone claim"));
              } else {
                player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous ne pouvez pas invoquer un Wither dans une zone claim adjacente"));
              } 
              return;
            } 
          } 
        }  
      IWitherCondition.WitherConditionException exception = null;
      if (wither.getConditions() != null && !wither.getConditions().isEmpty())
        for (IWitherCondition condition : wither.getConditions()) {
          if (condition == null)
            continue; 
          try {
            condition.validate(func_145831_w(), this, player, item, wither);
          } catch (fr.paladium.palawither.common.wither.condition.IWitherCondition.WitherConditionException e) {
            exception = e;
            break;
          } 
        }  
      if (exception != null) {
        if (exception.isException())
          exception.printStackTrace(); 
        if (exception.getMessage() == null || exception.getMessage().isEmpty()) {
          player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous ne pouvez pas invoquer ce wither, une erreur est survenue."));
          return;
        } 
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous ne pouvez pas invoquer ce wither, " + exception.getMessage()));
        return;
      } 
      this.gem = item.func_77946_l();
      this.gem.field_77994_a = 1;
      this.wither = wither;
      this.faction = faction;
      if (!player.field_71075_bZ.field_75098_d) {
        item.field_77994_a--;
        if (item.field_77994_a <= 0) {
          player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
        } else {
          player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, item);
        } 
      } 
      this.wither.onInvoke(this, player, faction);
      MinecraftForge.EVENT_BUS.post((Event)new WitherInvokeEvent(player));
      switch (this.wither.getAlert()) {
        case LOCAL:
          (new SCPacketWitherSpawn(this.wither, this.wither.getAlert())).send(PlayerSelector.AREA(AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1)).func_72314_b(this.wither.getViewDistance(), this.wither.getViewDistance(), this.wither.getViewDistance())));
          break;
        case SERVER:
          (new SCPacketWitherSpawn(this.wither, this.wither.getAlert())).sendToAll();
          break;
        case BROADCAST:
          (new RBPacketWitherSpawn(this.wither)).broadcast();
          break;
      } 
      if (this.wither.getCooldown() <= 0L) {
        spawn();
      } else {
        this.cooldown = (int)Math.ceil(((float)this.wither.getCooldown() * 20.0F));
        this.initialCooldown = this.cooldown;
        update();
      } 
      SoundUtils.ITEM_BREAK.playSound((EntityPlayerMP)player, this.field_145851_c, this.field_145848_d, this.field_145849_e, 0.2F, 1.0F);
    } catch (Exception e) {
      e.printStackTrace();
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cUne erreur est survenue lors de l'invocation du Wither."));
      return;
    } 
  }
  
  public void reset() {
    this.health = 15000.0D;
    this.gem = null;
    this.wither = null;
    this.faction = null;
    this.cooldown = 0;
    this.initialCooldown = 0;
    this.decreasingCooldown = false;
    this.cancelTimer = 0;
    this.players = null;
    update();
  }
  
  public boolean isActive() {
    return (this.wither != null);
  }
  
  public float getCooldownPercent() {
    if (this.initialCooldown <= 0)
      return 1.0F; 
    return (this.initialCooldown - this.cooldown) / this.initialCooldown;
  }
  
  @NonNull
  public TileEntityWitherReceptacle update() {
    func_70296_d();
    if (func_145831_w() != null) {
      func_145831_w().func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      if (isActive() && !(func_145831_w()).field_72995_K && FMLCommonHandler.instance().getSide() == Side.SERVER)
        SCPacketWitherData.update(this); 
    } 
    return this;
  }
  
  public boolean canUpdate() {
    return true;
  }
  
  public void func_145845_h() {
    long deltaTime = System.currentTimeMillis() - this.lastTick;
    int ticks = (int)(deltaTime / 50L);
    if (ticks <= 0 || this.lastTick <= 0L)
      ticks = 1; 
    this.lastTick = System.currentTimeMillis();
    List<EntityLivingBase> withers = func_145831_w().func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((this.field_145851_c - 6), (this.field_145848_d - 6), (this.field_145849_e - 6), (this.field_145851_c + 7), (this.field_145848_d + 7), (this.field_145849_e + 7)));
    for (EntityLivingBase wither : withers) {
      if (!WitherUtils.isWither((Entity)wither) || wither.field_70173_aa % 20 != 0 || wither.func_110143_aJ() >= wither.func_110138_aP())
        continue; 
      if (!(func_145831_w()).field_72995_K) {
        wither.func_70691_i(2.0F);
        continue;
      } 
      for (int i = 0; i < 5; i++) {
        double mx = (func_145831_w()).field_73012_v.nextGaussian() * 0.02D;
        double my = (func_145831_w()).field_73012_v.nextGaussian() * 0.02D;
        double mz = (func_145831_w()).field_73012_v.nextGaussian() * 0.02D;
        this.field_145850_b.func_72869_a("heart", wither.field_70165_t + ((func_145831_w()).field_73012_v.nextFloat() * wither.field_70130_N * 2.0F) - wither.field_70130_N, wither.field_70163_u + 1.0D + ((func_145831_w()).field_73012_v.nextFloat() * wither.field_70131_O), wither.field_70161_v + ((func_145831_w()).field_73012_v.nextFloat() * wither.field_70130_N * 2.0F) - wither.field_70130_N, mx, my, mz);
      } 
    } 
    if (this.gem != null && this.wither == null && func_145831_w() != null)
      try {
        this.wither = ((ItemWitherGem)this.gem.func_77973_b()).create(func_145831_w());
      } catch (Exception e) {
        e.printStackTrace();
        reset();
        return;
      }  
    if ((func_145831_w()).field_72995_K && FMLCommonHandler.instance().getSide() == Side.CLIENT) {
      if (isActive() && this.wither != null && this.wither.getLoopSound() != null && (this.sound == null || !this.sound.isPlaying()))
        this.sound = SoundRecord.create(this.wither.getLoopSound()).category(SoundCategory.MOBS).volume(10.0F).position(this.field_145851_c, this.field_145848_d, this.field_145849_e).attenuation(ISound.AttenuationType.LINEAR).play(); 
      if (this.sound != null && this.sound.isPlaying() && (!isActive() || this.wither == null)) {
        this.sound.stop();
        this.sound = null;
      } 
    } 
    if (isActive() && this.wither != null) {
      this.wither.onLoading(this);
      if (!(func_145831_w()).field_72995_K && (func_145831_w()).field_73012_v.nextInt(1000) < 5)
        func_145831_w().func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "mob.wither.idle", 0.2F, 1.0F); 
    } 
    if (isActive() && !(func_145831_w()).field_72995_K) {
      if (!ForgeEnv.isIDE() && FactionAPI.getInstance().isClaimed(this.field_145851_c >> 4, this.field_145849_e >> 4)) {
        this.cancelTimer++;
        this.decreasingCooldown = false;
        if (this.cancelTimer >= 1200) {
          reset();
          func_145831_w().func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "mob.wither.death", 0.2F, 1.0F);
        } 
        update();
        return;
      } 
      if (!ForgeEnv.isIDE() && this.faction != null) {
        List<IFactionPlayer> onlinePlayers = this.faction.getMemberEntity().getOnlinePlayers();
        if (onlinePlayers == null || onlinePlayers.isEmpty()) {
          this.cancelTimer++;
          this.decreasingCooldown = false;
          if (this.cancelTimer >= 1200) {
            reset();
            func_145831_w().func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "mob.wither.death", 0.2F, 1.0F);
          } 
          update();
          return;
        } 
        List<UUID> players = new ArrayList<>();
        for (IFactionPlayer factionPlayer : onlinePlayers) {
          if (factionPlayer == null || factionPlayer.getPlayer() == null || !factionPlayer.isOnline())
            continue; 
          EntityPlayer player = func_145831_w().func_152378_a(factionPlayer.getPlayer().getUuid());
          if (player == null)
            continue; 
          BlockLocation playerLocation = (new BlockLocation((Entity)player)).add(0, -1, 0);
          int i = playerLocation.getX() - this.field_145851_c;
          int j = playerLocation.getZ() - this.field_145849_e;
          if (i * i + j * j > 36 || playerLocation.getY() - this.field_145848_d > 6 || playerLocation.getY() - this.field_145848_d < -6)
            continue; 
          players.add(factionPlayer.getPlayer().getUuid());
        } 
        if (this.players != null && !this.players.isEmpty())
          for (UUID oldPlayer : this.players) {
            boolean found = players.stream().anyMatch(newPlayer -> newPlayer.equals(oldPlayer));
            if (!found) {
              EntityPlayer player = func_145831_w().func_152378_a(oldPlayer);
              if (player != null) {
                player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous §cavez §cquitté §cla §czone §cdu §créceptacle §cde §cWither. §cSi §caucun §cmembre §cde §cvotre §cfaction §cn'est §cprésent §cdans §cles §c60 §csecondes, §cl'invocation §csera §cannulée."));
                SoundRecordServer.create("palawither", "sounds/error.ogg").volume(10.0F).play((EntityPlayerMP)player);
              } 
            } 
          }  
        this.players = players;
        if (this.players.isEmpty()) {
          this.cancelTimer++;
          this.decreasingCooldown = false;
          if (this.cancelTimer >= 1200) {
            reset();
            func_145831_w().func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "mob.wither.death", 0.2F, 1.0F);
          } 
          update();
          return;
        } 
      } 
      if (ForgeEnv.isIDE()) {
        List<EntityPlayer> onlinePlayers = (func_145831_w()).field_73010_i;
        if (onlinePlayers == null || onlinePlayers.isEmpty()) {
          this.cancelTimer++;
          this.decreasingCooldown = false;
          if (this.cancelTimer >= 1200) {
            reset();
            func_145831_w().func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "mob.wither.death", 0.2F, 1.0F);
          } 
          update();
          return;
        } 
        List<UUID> players = new ArrayList<>();
        for (EntityPlayer player : onlinePlayers) {
          if (player == null)
            continue; 
          BlockLocation playerLocation = (new BlockLocation((Entity)player)).add(0, -1, 0);
          int i = playerLocation.getX() - this.field_145851_c;
          int j = playerLocation.getZ() - this.field_145849_e;
          if (i * i + j * j > 36 || playerLocation.getY() - this.field_145848_d > 6 || playerLocation.getY() - this.field_145848_d < -6)
            continue; 
          players.add(player.func_110124_au());
        } 
        if (this.players != null && !this.players.isEmpty())
          for (UUID oldPlayer : this.players) {
            boolean found = players.stream().anyMatch(newPlayer -> newPlayer.equals(oldPlayer));
            if (!found) {
              EntityPlayer player = func_145831_w().func_152378_a(oldPlayer);
              if (player != null) {
                player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous §cavez §cquitté §cla §czone §cdu §créceptacle §cde §cWither. §cSi §caucun §cmembre §cde §cvotre §cfaction §cn'est §cprésent §cdans §cles §c60 §csecondes, §cl'invocation §csera §cannulée."));
                SoundRecordServer.create("palawither", "sounds/error.ogg").volume(1.0F).play((EntityPlayerMP)player);
              } 
            } 
          }  
        this.players = players;
        if (this.players.isEmpty()) {
          this.cancelTimer++;
          this.decreasingCooldown = false;
          if (this.cancelTimer >= 1200) {
            reset();
            func_145831_w().func_72908_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, "mob.wither.death", 0.2F, 1.0F);
          } 
          update();
          return;
        } 
      } 
      this.cancelTimer = 0;
      this.decreasingCooldown = true;
      this.cooldown -= ticks;
      if (this.cooldown <= 0) {
        spawn();
      } else {
        update();
      } 
    } 
  }
  
  private void spawn() {
    func_145831_w().func_72942_c((Entity)new EntityLightningBolt(func_145831_w(), this.field_145851_c, this.field_145848_d, this.field_145849_e));
    func_145831_w().func_72980_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, "ambient.weather.thunder", 100.0F, 1.0F, false);
    this.wither.getEntity().func_70634_a(this.field_145851_c + 0.5D, this.field_145848_d + 0.8D, this.field_145849_e + 0.5D);
    this.wither.onSpawn(this);
    if (this.wither.getSpawnSound() != null)
      SoundRecordServer.create(this.wither.getSpawnSound().toString()).category("mobs").volume(10.0F).play(PlayerSelector.AREA(AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1)).func_72314_b(this.wither.getViewDistance(), this.wither.getViewDistance(), this.wither.getViewDistance()))); 
    func_145831_w().func_72838_d((Entity)this.wither.getEntity());
    reset();
  }
  
  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.health = nbt.func_74764_b("health") ? nbt.func_74769_h("health") : 15000.0D;
    if (nbt.func_74764_b("gem")) {
      this.gem = ItemStack.func_77949_a(nbt.func_74775_l("gem"));
    } else {
      this.gem = null;
      this.wither = null;
    } 
    if (nbt.func_74764_b("faction") && FMLCommonHandler.instance().getSide() == Side.SERVER) {
      UUID factionId = UUIDUtils.from(nbt.func_74779_i("faction"));
      FactionAPI.getInstance().getFactionAsync(factionId)
        .thenAccept(faction -> this.faction = faction)
        .exceptionally(e -> {
            reset();
            return null;
          });
    } 
    this.cooldown = nbt.func_74764_b("cooldown") ? nbt.func_74762_e("cooldown") : 0;
    this.initialCooldown = nbt.func_74764_b("initialCooldown") ? nbt.func_74762_e("initialCooldown") : 0;
    this.decreasingCooldown = (nbt.func_74764_b("decreasingCooldown") && nbt.func_74767_n("decreasingCooldown"));
  }
  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74780_a("health", this.health);
    if (this.gem != null) {
      NBTTagCompound gemTag = new NBTTagCompound();
      this.gem.func_77955_b(gemTag);
      nbt.func_74782_a("gem", (NBTBase)gemTag);
    } 
    if (this.faction != null && FMLCommonHandler.instance().getSide() == Side.SERVER) {
      nbt.func_74778_a("faction", UUIDUtils.toString(this.faction.getUuid()));
    } else {
      nbt.func_82580_o("faction");
    } 
    nbt.func_74768_a("cooldown", this.cooldown);
    nbt.func_74768_a("initialCooldown", this.initialCooldown);
    nbt.func_74757_a("decreasingCooldown", this.decreasingCooldown);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound compound = new NBTTagCompound();
    func_145841_b(compound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, compound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
    func_145831_w().func_147458_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a((this.field_145851_c - 6), (this.field_145848_d - 6), (this.field_145849_e - 6), (this.field_145851_c + 7), (this.field_145848_d + 7), (this.field_145849_e + 7));
  }
  
  public void func_145843_s() {
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT && this.sound != null && this.sound.isPlaying()) {
      this.sound.stop();
      this.sound = null;
    } 
    super.func_145843_s();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\tileentity\TileEntityWitherReceptacle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */