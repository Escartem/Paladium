package fr.paladium.palawither.common.entity.targetable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.factions.api.type.FactionRelationshipType;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.java.hash.LongHash;
import fr.paladium.palaforgeutils.lib.location.BlockLocation;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palawither.common.entity.projectile.EntitySupremeWitherProjectile;
import fr.paladium.palawither.common.network.entity.SCPacketSupremeWitherExplode;
import fr.paladium.palawither.common.network.entity.SCPacketSupremeWitherSpawn;
import fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle;
import fr.paladium.palawither.common.utils.UniversalTimeUtils;
import fr.paladium.palawither.common.wither.base.IWither;
import fr.paladium.palawither.common.wither.base.projectile.EntityWitherProjectile;
import fr.paladium.palawither.common.wither.base.property.WitherProperties;
import fr.paladium.palawither.common.wither.base.property.impl.WitherExplosionProperty;
import fr.paladium.palawither.common.wither.condition.IWitherCondition;
import fr.paladium.palawither.common.wither.condition.impl.FactionCooldownWitherCondition;
import fr.paladium.palawither.common.wither.condition.impl.FactionLevelWitherCondition;
import fr.paladium.palawither.common.wither.condition.impl.FactionPlayerCountWitherCondition;
import fr.paladium.worldguardbridge.common.dto.flag.impl.StateFlag;
import fr.paladium.worldguardbridge.common.dto.flag.utils.FlagConstants;
import fr.paladium.worldguardbridge.common.manager.WGManager;
import fr.paladium.worldguardbridge.common.manager.utils.WGRegionList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;

public class EntitySupremeWither extends EntityWitherCoordinateTargetable {
  private static final int EXPLOSION_RADIUS = 12;
  
  private static final long LIFETIME = 18000L;
  
  private static final long PREDATOR_SPAWN = 2400L;
  
  private static final long LOADING_TIME = 43200000L;
  
  @SideOnly(Side.CLIENT)
  private transient SoundRecord sound;
  
  public SoundRecord getSound() {
    return this.sound;
  }
  
  public EntitySupremeWither(@NonNull World world) {
    super(world);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    func_70105_a(1.8F, 6.0F);
    addProperty(new Object[] { WitherProperties.armored(), WitherProperties.breakBlock(source -> Integer.valueOf((source.func_76346_g() instanceof EntityPlayer) ? 20 : 0)) });
    setInternalHealth(func_110138_aP());
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(24.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2D);
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(27, Byte.valueOf((byte)1));
    this.field_70180_af.func_75682_a(28, Long.toString(0L));
    this.field_70180_af.func_75682_a(29, Byte.valueOf((byte)0));
  }
  
  public void breakBlocks() {
    int floorX = MathHelper.func_76128_c(this.field_70165_t);
    int floorY = MathHelper.func_76128_c(this.field_70163_u);
    int floorZ = MathHelper.func_76128_c(this.field_70161_v);
    int width = Math.round(this.field_70130_N);
    int height = Math.round(this.field_70131_O);
    boolean broken = false;
    for (int ox = -width; ox <= width; ox++) {
      for (int oz = -width; oz <= width; oz++) {
        for (int oy = -1; oy <= height + 1; oy++) {
          int blockX = floorX + ox;
          int blockY = floorY + oy;
          int blockZ = floorZ + oz;
          broken = (breakBlock(blockX, blockY, blockZ) || broken);
        } 
      } 
    } 
    if (broken)
      this.field_70170_p.func_72889_a(null, 1012, floorX, floorY, floorZ, 0); 
  }
  
  public void func_70071_h_() {
    if (isLoading()) {
      setWasLoading(true);
      this.field_70173_aa = 0;
      this.field_70159_w = 0.0D;
      this.field_70181_x = 0.0D;
      this.field_70179_y = 0.0D;
      if (this.field_70170_p.field_72995_K && (this.sound == null || !this.sound.isPlaying()))
        this.sound = SoundRecord.create(getLoopSound()).category(SoundCategory.MOBS).volume(10.0F).position(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v)).attenuation(ISound.AttenuationType.LINEAR).play(); 
      if (!this.field_70170_p.field_72995_K && this.field_70170_p.field_73012_v.nextInt(20) == 0 && this.field_70170_p.field_73012_v.nextFloat() <= getLoadingProgress() * 2.0F) {
        this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "ambient.weather.thunder", 100.0F, 1.0F, false);
        this.field_70170_p.func_72942_c((Entity)new EntityLightningBolt(this.field_70170_p, this.field_70165_t + this.field_70170_p.field_73012_v.nextDouble() * 10.0D - 5.0D, this.field_70163_u, this.field_70161_v + this.field_70170_p.field_73012_v.nextDouble() * 10.0D - 5.0D));
      } 
      return;
    } 
    if (isDying()) {
      this.field_70173_aa = 18000;
      this.field_70159_w = 0.0D;
      this.field_70181_x = 0.0D;
      this.field_70179_y = 0.0D;
      return;
    } 
    super.func_70071_h_();
    if (this.field_70170_p.field_72995_K || this.field_70128_L)
      return; 
    if (wasLoading()) {
      setWasLoading(false);
      if (this.field_70170_p.field_72995_K && this.sound != null && this.sound.isPlaying()) {
        this.sound.stop();
        this.sound = null;
      } 
      (new SCPacketSupremeWitherSpawn(this)).sendToAll();
      if (!this.field_70170_p.field_72995_K) {
        WitherProperties.explosion(10, false, true).explode(this);
        for (int i = 0; i < 5; i++)
          this.field_70170_p.func_72942_c((Entity)new EntityLightningBolt(this.field_70170_p, this.field_70165_t + this.field_70170_p.field_73012_v.nextDouble() * 10.0D - 5.0D, this.field_70163_u, this.field_70161_v + this.field_70170_p.field_73012_v.nextDouble() * 10.0D - 5.0D)); 
      } 
    } 
    if (this.field_70173_aa > 0 && this.field_70173_aa % 2400L == 0L) {
      this.field_70170_p.func_72942_c((Entity)new EntityLightningBolt(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v));
      this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "ambient.weather.thunder", 100.0F, 1.0F, false);
      EntityPredatorWither predator = new EntityPredatorWither(this.field_70170_p);
      predator.getEntity().func_70634_a(this.field_70165_t + 0.5D, this.field_70163_u + 0.8D, this.field_70161_v + 0.5D);
      predator.setSpawn((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
      predator.setFactionUUID(getFactionUUID());
      if (hasTargetLocation())
        predator.setTargetLocation(new DoubleLocation(getTargetLocationX(), getTargetLocationY(), getTargetLocationZ())); 
      WitherExplosionProperty explosionProperty = (WitherExplosionProperty)getProperty("explosion");
      if (explosionProperty != null)
        explosionProperty.explode(this); 
      this.field_70170_p.func_72838_d((Entity)predator.getEntity());
    } 
    float healthProgress = this.field_70173_aa / 18000.0F;
    if (healthProgress == 1.0F) {
      explode();
      return;
    } 
    setInternalHealth((1.0F - healthProgress) * func_110138_aP());
  }
  
  public boolean func_70097_a(DamageSource source, float damage) {
    if (isLoading())
      return false; 
    return super.func_70097_a(source, damage);
  }
  
  protected boolean func_70085_c(EntityPlayer player) {
    if (player.field_71075_bZ.field_75098_d && player.func_70093_af()) {
      if (!this.field_70170_p.field_72995_K)
        if (isLoading()) {
          setLoadingEndTime(UniversalTimeUtils.now() + getRemainingLoadingTime() / 2L);
        } else {
          explode();
        }  
      return true;
    } 
    return false;
  }
  
  public void func_70606_j(float health) {}
  
  public boolean isArmored() {
    return false;
  }
  
  public boolean func_70058_J() {
    return false;
  }
  
  public boolean func_70072_I() {
    return false;
  }
  
  public boolean func_70090_H() {
    return false;
  }
  
  public boolean func_70648_aU() {
    return true;
  }
  
  public boolean func_96092_aw() {
    return false;
  }
  
  public void func_70044_A() {}
  
  public void func_145775_I() {}
  
  public boolean func_85032_ar() {
    return (!isLoading() && super.func_85032_ar());
  }
  
  public void func_70623_bb() {}
  
  public boolean func_70104_M() {
    return false;
  }
  
  public boolean func_82171_bF() {
    return false;
  }
  
  public void func_82167_n(Entity entity) {}
  
  public void func_85033_bc() {}
  
  public boolean func_70692_ba() {
    return false;
  }
  
  public boolean func_90999_ad() {
    return false;
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    setInternalHealth(nbt.func_74760_g("HealF"));
    setWasLoading(nbt.func_74767_n("WasLoading"));
    setLoadingEndTime(nbt.func_74764_b("LoadingEndTime") ? nbt.func_74763_f("LoadingEndTime") : 0L);
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74757_a("WasLoading", wasLoading());
    nbt.func_74772_a("LoadingEndTime", isLoading() ? getLoadingEndTime() : 0L);
  }
  
  private void setInternalHealth(float health) {
    super.func_70606_j(health);
  }
  
  private void explode() {
    if (isDying())
      return; 
    setDying(true);
    (new SCPacketSupremeWitherExplode(this)).sendToAll();
    (new Thread(() -> {
          try {
            Thread.sleep(2000L);
            FMLServerScheduler.getInstance().add(new Runnable[] { this::deathExplosion }, );
            Thread.sleep(5000L);
            FMLServerScheduler.getInstance().add(new Runnable[] { () }, );
          } catch (Exception exception) {}
        }"SupremeWither/" + UUIDUtils.toString((Entity)this) + "-Explode")).start();
  }
  
  private void deathExplosion() {
    for (int i = 0; i < 5; i++) {
      this.field_70170_p.func_72876_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 10.0F, false);
      this.field_70170_p.func_72942_c((Entity)new EntityLightningBolt(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v));
      this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "ambient.weather.thunder", 100.0F, 1.0F, false);
    } 
    Set<Long> chunkList = new HashSet<>();
    for (int x = -12; x <= 12; x++) {
      for (int y = -12; y <= 12; y++) {
        for (int z = -12; z <= 12; z++) {
          double distanceSquared = (x * x + y * y + z * z);
          if (distanceSquared <= 144.0D) {
            DoubleLocation location = new DoubleLocation(this.field_70165_t + x, this.field_70163_u + func_70047_e() + y, this.field_70161_v + z);
            if (location.getBlockY() > 0 && location.getBlockY() <= 255) {
              BlockLocation blockLoc = location.getBlockLocation(this.field_70170_p);
              Block block = blockLoc.getBlock();
              if (block != Blocks.field_150357_h && block != Blocks.field_150384_bq && block != Blocks.field_150378_br && block != Blocks.field_150483_bI)
                if (block instanceof net.minecraft.block.BlockAnvil || (!block.isAir((IBlockAccess)this.field_70170_p, blockLoc.getX(), blockLoc.getY(), blockLoc.getZ()) && block.canEntityDestroy((IBlockAccess)this.field_70170_p, blockLoc.getX(), blockLoc.getY(), blockLoc.getZ(), (Entity)this))) {
                  WGRegionList regionList = WGManager.inst().getRegionListAt(this.field_70170_p, blockLoc.getX(), blockLoc.getY(), blockLoc.getZ());
                  Optional<StateFlag> canBreakBlock = regionList.getFlag(FlagConstants.BLOCK_BREAK);
                  Optional<StateFlag> canDragonBreakBlock = regionList.getFlag(FlagConstants.ENDERDRAGON_BLOCK_DAMAGE);
                  if ((!canBreakBlock.isPresent() || !((StateFlag)canBreakBlock.get()).isDenied()) && (!canDragonBreakBlock.isPresent() || !((StateFlag)canDragonBreakBlock.get()).isDenied())) {
                    this.field_70170_p.func_147465_d(blockLoc.getX(), blockLoc.getY(), blockLoc.getZ(), Blocks.field_150350_a, 0, 4);
                    chunkList.add(Long.valueOf(LongHash.toLong(blockLoc.getX() >> 4, blockLoc.getZ() >> 4)));
                  } 
                }  
            } 
          } 
        } 
      } 
    } 
    PlayerManager playerManager = ((WorldServer)this.field_70170_p).func_73040_p();
    PlayerSelector.WORLD(this.field_70170_p).apply(player -> {
          List<Chunk> bulkList = new ArrayList<>();
          for (Long chunk : chunkList) {
            int chunkX = LongHash.msw(chunk.longValue());
            int chunkZ = LongHash.lsw(chunk.longValue());
            if (!playerManager.func_72694_a(player, chunkX, chunkZ))
              continue; 
            bulkList.add(this.field_70170_p.func_72964_e(chunkX, chunkZ));
          } 
          if (bulkList.isEmpty())
            return; 
          player.field_71135_a.func_147359_a((Packet)new S26PacketMapChunkBulk(bulkList));
        });
  }
  
  public boolean wasLoading() {
    return (this.field_70180_af.func_75683_a(27) == 1);
  }
  
  public void setWasLoading(boolean wasLoading) {
    this.field_70180_af.func_75692_b(27, Byte.valueOf((byte)(wasLoading ? 1 : 0)));
  }
  
  public long getLoadingEndTime() {
    return Long.parseLong(this.field_70180_af.func_75681_e(28));
  }
  
  public void setLoadingEndTime(long loadingEndTime) {
    this.field_70180_af.func_75692_b(28, Long.toString(loadingEndTime));
  }
  
  public boolean isDying() {
    return (this.field_70180_af.func_75683_a(29) == 1);
  }
  
  public void setDying(boolean dying) {
    this.field_70180_af.func_75692_b(29, Byte.valueOf((byte)(dying ? 1 : 0)));
  }
  
  public void startLoading() {
    setLoadingEndTime(UniversalTimeUtils.now() + 43200000L);
  }
  
  public boolean isLoading() {
    long loadingEndTime = getLoadingEndTime();
    return (loadingEndTime > 0L && loadingEndTime > UniversalTimeUtils.now());
  }
  
  public long getRemainingLoadingTime() {
    if (!isLoading())
      return 0L; 
    return getLoadingEndTime() - UniversalTimeUtils.now();
  }
  
  public float getLoadingProgress() {
    if (!isLoading())
      return 1.0F; 
    float progress = 1.0F - (float)(getLoadingEndTime() - UniversalTimeUtils.now()) / 4.32E7F;
    return MathHelper.func_76131_a(progress, 0.0F, 1.0F);
  }
  
  public Class<? extends EntityWitherProjectile> getProjectile() {
    return (Class)EntitySupremeWitherProjectile.class;
  }
  
  @NonNull
  public EntitySupremeWither onSpawn(@NonNull TileEntityWitherReceptacle tile) {
    if (tile == null)
      throw new NullPointerException("tile is marked non-null but is null"); 
    startLoading();
    return (EntitySupremeWither)super.onSpawn(tile);
  }
  
  public List<IWitherCondition> getConditions() {
    return Arrays.asList(new IWitherCondition[] { (IWitherCondition)FactionPlayerCountWitherCondition.min(FactionRelationshipType.ENEMY, 2), (IWitherCondition)FactionLevelWitherCondition.create(40), (IWitherCondition)FactionCooldownWitherCondition.create(TimeUnit.HOURS.toMillis(24L)) });
  }
  
  public String getBarTexture() {
    return "palawither:textures/overlay/wither/foreground/supreme.png";
  }
  
  public String getColor() {
    return "#FF7AEE";
  }
  
  public ResourceLocation getInvokeSound() {
    return (ResourceLocation)MCResource.of("palawither", "sounds/entities/supreme_wither/invoke.ogg");
  }
  
  public ResourceLocation getLoopSound() {
    return (ResourceLocation)MCResource.of("palawither", "sounds/entities/supreme_wither/loop.ogg");
  }
  
  public ResourceLocation getSpawnSound() {
    return null;
  }
  
  public long getCooldown() {
    return ForgeEnv.isIDE() ? 10L : 1200L;
  }
  
  public double getViewDistance() {
    return 480.0D;
  }
  
  public IWither.WitherInvokeAlert getAlert() {
    return IWither.WitherInvokeAlert.BROADCAST;
  }
  
  protected boolean hasVanillaBehavior() {
    return false;
  }
  
  public double getMaxDistance() {
    return 1000.0D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\entity\targetable\EntitySupremeWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */