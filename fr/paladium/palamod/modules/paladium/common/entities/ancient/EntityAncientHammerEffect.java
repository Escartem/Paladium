package fr.paladium.palamod.modules.paladium.common.entities.ancient;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.combattag.CombatTag;
import fr.paladium.helios.utils.TeleportUtils;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.blocks.BaseBlockCrops;
import fr.paladium.palamod.modules.apet.server.skill.handler.data.ItemMeta;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityCustomFallingBlock;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.data.ItemAncientHammerPlayerData;
import fr.paladium.palamod.modules.paladium.common.logics.TileEntityAncientHammerBlockCorrupted;
import fr.paladium.palawither.common.utils.WitherUtils;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import org.bukkit.Bukkit;

public class EntityAncientHammerEffect extends EntityLiving implements IEntityAdditionalSpawnData {
  private LegendaryStone.Effect effectType;
  
  private String player;
  
  private long duration;
  
  private boolean soundPlayed;
  
  public void setEffectType(LegendaryStone.Effect effectType) {
    this.effectType = effectType;
  }
  
  public void setPlayer(String player) {
    this.player = player;
  }
  
  public void setDuration(long duration) {
    this.duration = duration;
  }
  
  public void setSoundPlayed(boolean soundPlayed) {
    this.soundPlayed = soundPlayed;
  }
  
  public LegendaryStone.Effect getEffectType() {
    return this.effectType;
  }
  
  public String getPlayer() {
    return this.player;
  }
  
  public long getDuration() {
    return this.duration;
  }
  
  public boolean isSoundPlayed() {
    return this.soundPlayed;
  }
  
  public EntityAncientHammerEffect(World world) {
    super(world);
    func_70606_j(1.0F);
    func_70105_a(3.0F, 3.0F);
  }
  
  public EntityAncientHammerEffect(World world, double x, double y, double z, LegendaryStone.Effect effect, String player, float yaw) {
    this(world);
    func_70080_a(x, y, z, yaw, 0.0F);
    this.effectType = effect;
    this.player = player;
    this.duration = 2000L;
  }
  
  public EntityAncientHammerEffect(World world, LegendaryStone.Effect effect, EntityPlayer player, float yaw) {
    this(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, effect, player.func_70005_c_(), yaw);
  }
  
  public void func_70071_h_() {
    if (this.effectType == null)
      func_70106_y(); 
    if (!this.field_70170_p.field_72995_K && this.effectType != null && this.field_70173_aa == 28)
      playEffect(this.effectType); 
    if (this.field_70173_aa > this.duration / 1000L * 20L)
      func_70106_y(); 
    if (this.field_70170_p.field_72995_K && !this.soundPlayed)
      playSound(); 
  }
  
  private void playSound() {
    this.soundPlayed = true;
    if (this.effectType == null)
      return; 
    SoundRecord sound = SoundRecord.create((ResourceLocation)MCResource.of("palamod", "sounds/ancient_hammer/" + this.effectType.name().toLowerCase() + ".ogg")).category(SoundCategory.PLAYERS).volume(3.0F).build();
    sound.play();
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
  
  public boolean func_70097_a(DamageSource source, float damage) {
    return false;
  }
  
  public boolean func_70085_c(EntityPlayer player) {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_70067_L() {
    return false;
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    this.effectType = LegendaryStone.Effect.values()[nbt.func_74762_e("effectType")];
    this.player = nbt.func_74779_i("player");
    this.duration = nbt.func_74763_f("duration");
    this.field_70173_aa = nbt.func_74762_e("ticksExisted");
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74768_a("effectType", this.effectType.ordinal());
    nbt.func_74778_a("player", this.player);
    nbt.func_74772_a("duration", this.duration);
  }
  
  public void writeSpawnData(ByteBuf buf) {
    if (this.effectType == null || this.player == null || this.player.isEmpty())
      return; 
    buf.writeInt(this.effectType.ordinal());
    ByteBufUtils.writeUTF8String(buf, this.player);
    buf.writeLong(this.duration);
    buf.writeInt(this.field_70173_aa);
  }
  
  public void readSpawnData(ByteBuf buf) {
    if (!buf.isReadable()) {
      func_70106_y();
      return;
    } 
    this.effectType = LegendaryStone.Effect.values()[buf.readInt()];
    this.player = ByteBufUtils.readUTF8String(buf);
    this.duration = buf.readLong();
    this.field_70173_aa = buf.readInt();
  }
  
  public ItemStack getPickedResult(MovingObjectPosition target) {
    return null;
  }
  
  private void playEffect(LegendaryStone.Effect effect) {
    switch (effect) {
      case TELEPORTATION:
        playTeleportationEffect();
        break;
      case INVISIBILITY:
        playInvisibilityEffect();
        break;
      case POWER:
        playPowerEffect();
        break;
      case CHAOS:
        playChaosEffect();
        break;
      case JOBS:
        playJobsEffect();
        break;
    } 
  }
  
  private void playTeleportationEffect() {
    if (this.player == null || this.player.isEmpty())
      return; 
    EntityPlayerMP player = PlayerUtils.getPlayer(this.player);
    if (player == null)
      return; 
    World world = player.field_70170_p;
    List<EntityLivingBase> players = world.func_82733_a(EntityLivingBase.class, this.field_70121_D.func_72314_b(16.0D, 10.0D, 16.0D), new PlayerSelector(true));
    if (players == null || players.isEmpty() || players.size() < 2)
      return; 
    List<List<Double>> positions = new ArrayList<>();
    for (int i = 0; i < players.size(); i++) {
      EntityLivingBase p = players.get(i);
      positions.add(Arrays.asList(new Double[] { Double.valueOf(p.field_70165_t), Double.valueOf(p.field_70163_u), Double.valueOf(p.field_70161_v) }));
    } 
    Collections.shuffle(positions);
    WorldServer worldServer = (WorldServer)world;
    for (int j = 0; j < players.size(); j++) {
      EntityLivingBase p = players.get(j);
      worldServer.func_72956_a((Entity)p, SoundUtils.ENDERMAN_TELEPORT.getSoundName(), 1.5F, 1.0F);
      worldServer.func_147487_a("portal", p.field_70165_t, p.field_70163_u + p.func_70047_e(), p.field_70161_v, 50, 0.4D, 0.4D, 0.4D, 0.0D);
      int nextIndex = (j + 1) % players.size();
      List<Double> pos = positions.get(nextIndex);
      if (ForgeEnv.isIDE()) {
        p.func_70634_a(((Double)pos.get(0)).doubleValue(), ((Double)pos.get(1)).doubleValue(), ((Double)pos.get(2)).doubleValue());
      } else if (p instanceof EntityPlayerMP) {
        TeleportUtils.teleport((EntityPlayer)p, ((Double)pos.get(0)).doubleValue(), ((Double)pos.get(1)).doubleValue(), ((Double)pos.get(2)).doubleValue());
      } 
    } 
  }
  
  private void playInvisibilityEffect() {
    if (this.player == null || this.player.isEmpty())
      return; 
    EntityPlayerMP playerMP = PlayerUtils.getPlayer(this.player);
    if (playerMP == null)
      return; 
    ((WorldServer)playerMP.field_70170_p).func_147487_a("portal", playerMP.field_70165_t, playerMP.field_70163_u + playerMP.func_70047_e() / 2.0D, playerMP.field_70161_v, 50, 0.4D, 0.4D, 0.4D, 0.0D);
    AncientHammerFakePlayerEntity fakePlayer = new AncientHammerFakePlayerEntity(playerMP.field_70170_p, (EntityPlayer)playerMP);
    playerMP.field_70170_p.func_72838_d((Entity)fakePlayer);
    playerMP.func_82142_c(true);
    ItemAncientHammerPlayerData.get((EntityPlayer)playerMP).setInvisibilityEffect(true);
    (new Thread(() -> {
          try {
            Thread.sleep(30000L);
          } catch (InterruptedException interruptedException) {}
          if (fakePlayer.field_70128_L)
            return; 
          playerMP.func_82142_c(false);
          ItemAncientHammerPlayerData pData = ItemAncientHammerPlayerData.get((EntityPlayer)playerMP);
          if (pData != null)
            pData.setInvisibilityEffect(false); 
        }"AncientHammerFakePlayerEntity/SetInvisibleFalseThread")).start();
  }
  
  private void playPowerEffect() {
    if (this.player == null || this.player.isEmpty())
      return; 
    EntityPlayerMP player = PlayerUtils.getPlayer(this.player);
    if (player == null)
      return; 
    ItemAncientHammerPlayerData pData = ItemAncientHammerPlayerData.get((EntityPlayer)player);
    if (pData == null)
      return; 
    World world = player.field_70170_p;
    int count = 24;
    double radius = 2.0D;
    for (int j = 0; j < 24; j++) {
      double angle = Math.toRadians(15.0D * j);
      double x = 2.0D * Math.cos(angle) + this.field_70165_t;
      double z = 2.0D * Math.sin(angle) + this.field_70161_v;
      float fallingYaw = (float)Math.toDegrees(angle);
      BlockPos pos = new BlockPos(x, this.field_70163_u - 1.0D, z);
      EntityCustomFallingBlock falling = new EntityCustomFallingBlock(world, (Entity)player, x, this.field_70163_u - 1.0D, z, 0.20000000298023224D, fallingYaw, pos, 0);
      world.func_72838_d((Entity)falling);
    } 
    (new Thread(() -> {
          for (int i = 0; i < 5; i++) {
            try {
              Thread.sleep(100L);
            } catch (Exception exception) {}
            double r = 2.0D + i;
            FMLServerScheduler.getInstance().add(new Runnable[] { () }, );
          } 
        }"EntityAncientHammerEffect/playPowerEffect")).start();
    List<EntityPlayerMP> players = world.func_82733_a(EntityPlayerMP.class, this.field_70121_D.func_72314_b(6.0D, 10.0D, 6.0D), new PlayerSelector(false));
    if (!players.isEmpty())
      players.forEach(p -> {
            double dx = p.field_70165_t - this.field_70165_t;
            double dz = p.field_70161_v - this.field_70161_v;
            double dist = Math.sqrt(dx * dx + dz * dz);
            if (dist > 0.01D) {
              double knockbackStrength = 1.5D;
              p.func_70024_g(dx / dist * 1.5D, 0.5D, dz / dist * 1.5D);
              p.field_70133_I = true;
            } 
            if (pData.getPowerEffectDamage() <= 0.0F)
              return; 
            float currentHealth = p.func_110143_aJ();
            float damage = pData.getPowerEffectDamage();
            if (damage >= currentHealth) {
              damage = currentHealth - 0.5F;
            } else {
              damage = pData.getPowerEffectDamage();
            } 
            p.func_70097_a(DamageSource.func_76365_a((EntityPlayer)player).func_76348_h(), damage);
          }); 
    pData.disablePowerEffect();
  }
  
  private void playChaosEffect() {
    if (this.player == null || this.player.isEmpty())
      return; 
    EntityPlayerMP player = PlayerUtils.getPlayer(this.player);
    if (player == null)
      return; 
    World world = player.field_70170_p;
    int radius = 5;
    int radiusSquared = 25;
    for (int x = -5; x <= 5; x++) {
      for (int y = -5; y <= 5; y++) {
        for (int z = -5; z <= 5; z++) {
          if (x * x + y * y + z * z <= 25) {
            int blockX = (int)this.field_70165_t + x;
            int blockY = (int)this.field_70163_u + y;
            int blockZ = (int)this.field_70161_v + z;
            if (blockY > 0 && blockY < 255) {
              Block block = world.func_147439_a(blockX, blockY, blockZ);
              int blockMetadata = world.func_72805_g(blockX, blockY, blockZ);
              Block blockAbove = world.func_147439_a(blockX, blockY + 1, blockZ);
              if (block != null && !block.hasTileEntity(blockMetadata) && !block.isAir((IBlockAccess)world, blockX, blockY, blockZ) && block != Blocks.field_150357_h && block.func_149668_a(world, blockX, blockY, blockZ) != null)
                if (blockAbove != null && (blockAbove.isAir((IBlockAccess)world, blockX, blockY + 1, blockZ) || blockAbove.func_149668_a(world, blockX, blockY + 1, blockZ) == null)) {
                  world.func_147465_d(blockX, blockY, blockZ, BlocksRegister.CORRUPTED_BLOCK, 0, 3);
                  TileEntityAncientHammerBlockCorrupted tileEntity = (TileEntityAncientHammerBlockCorrupted)world.func_147438_o(blockX, blockY, blockZ);
                  tileEntity.setLifetime(60000L + world.field_73012_v.nextInt(5000));
                  tileEntity.setOriginBlock(block);
                  tileEntity.setOriginMeta(blockMetadata);
                  tileEntity.setProtectedPlayer(this.player);
                  tileEntity.func_70296_d();
                  world.func_147471_g(blockX, blockY, blockZ);
                  world.func_72926_e(2001, blockX, blockY, blockZ, Block.func_149682_b(block) + (blockMetadata << 12));
                }  
            } 
          } 
        } 
      } 
    } 
  }
  
  private void playJobsEffect() {
    if (this.player == null || this.player.isEmpty())
      return; 
    EntityPlayerMP playerMP = PlayerUtils.getPlayer(this.player);
    if (playerMP == null)
      return; 
    playerMP.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §rRécolte massive en cours..."));
    World world = playerMP.field_70170_p;
    Map<ItemMeta, Integer> itemCounts = new HashMap<>();
    (new Thread(() -> {
          for (int i = -16; i <= 16; i++) {
            for (int j = -16; j <= 16; j++) {
              for (int k = -16; k <= 16; k++) {
                int blockX = (int)this.field_70165_t + j;
                int blockY = (int)this.field_70163_u + i;
                int blockZ = (int)this.field_70161_v + k;
                if (blockY > 0 && blockY < 256) {
                  Block block = world.func_147439_a(blockX, blockY, blockZ);
                  int blockMetadata = world.func_72805_g(blockX, blockY, blockZ);
                  if (block != null && block != Blocks.field_150357_h && !block.isAir((IBlockAccess)world, blockX, blockY, blockZ) && !block.func_149688_o().func_76224_d())
                    FMLServerScheduler.getInstance().add(new Runnable[] { () }); 
                } 
              } 
            } 
            try {
              Thread.sleep(100L);
            } catch (Exception exception) {}
          } 
          FMLServerScheduler.getInstance().add(new Runnable[] { () });
          playerMP.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §rRécolte massive terminée !"));
        }"EntityAncientHammerEffect/playJobsEffect")).start();
    List<EntityLivingBase> mobs = world.func_82733_a(EntityLivingBase.class, this.field_70121_D.func_72314_b(16.0D, 16.0D, 16.0D), new MobSelector());
    if (mobs == null || mobs.isEmpty())
      return; 
    mobs.forEach(mob -> mob.func_70097_a(DamageSource.func_76365_a((EntityPlayer)playerMP).func_76348_h(), mob.func_110138_aP()));
  }
  
  private boolean breakCrop(Block block, int blockX, int blockY, int blockZ, EntityPlayerMP playerMP, Map<ItemMeta, Integer> itemCounts) {
    int meta = playerMP.field_70170_p.func_72805_g(blockX, blockY, blockZ);
    boolean maxStage = false;
    if (block instanceof net.minecraft.block.BlockMelon || block instanceof net.minecraft.block.BlockPumpkin) {
      BlockEvent.BreakEvent breakEvent = new BlockEvent.BreakEvent(blockX, blockY, blockZ, playerMP.field_70170_p, block, meta, (EntityPlayer)playerMP);
      if (MinecraftForge.EVENT_BUS.post((Event)breakEvent))
        return false; 
      extractItems(playerMP.field_70170_p, itemCounts, blockX, blockY, blockZ, block, meta);
      playerMP.field_70170_p.func_147468_f(blockX, blockY, blockZ);
      return true;
    } 
    if (block instanceof net.minecraft.block.BlockCrops && 
      meta >= 7)
      maxStage = true; 
    if (!maxStage && block instanceof BaseBlockCrops) {
      BaseBlockCrops crops = (BaseBlockCrops)block;
      if (meta >= crops.getStageMax() - 1)
        maxStage = true; 
    } 
    if (!maxStage)
      return false; 
    BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(blockX, blockY, blockZ, playerMP.field_70170_p, block, meta, (EntityPlayer)playerMP);
    if (MinecraftForge.EVENT_BUS.post((Event)event))
      return false; 
    extractItems(playerMP.field_70170_p, itemCounts, blockX, blockY, blockZ, block, meta);
    playerMP.field_70170_p.func_147468_f(blockX, blockY, blockZ);
    return true;
  }
  
  private boolean breakLog(Block block, int blockX, int blockY, int blockZ, EntityPlayerMP playerMP, Map<ItemMeta, Integer> itemCounts) {
    if (!(block instanceof fr.paladium.palamod.modules.world.blocks.BaseBlockLogSeve))
      return false; 
    int meta = playerMP.field_70170_p.func_72805_g(blockX, blockY, blockZ);
    BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(blockX, blockY, blockZ, playerMP.field_70170_p, block, meta, (EntityPlayer)playerMP);
    if (MinecraftForge.EVENT_BUS.post((Event)event))
      return false; 
    extractItems(playerMP.field_70170_p, itemCounts, blockX, blockY, blockZ, block, meta);
    playerMP.field_70170_p.func_147468_f(blockX, blockY, blockZ);
    return true;
  }
  
  private boolean breakOre(Block block, int blockX, int blockY, int blockZ, EntityPlayerMP playerMP, Map<ItemMeta, Integer> itemCounts) {
    if (!(block instanceof net.minecraft.block.BlockOre) && !(block instanceof fr.paladium.palamod.modules.world.blocks.BlockOre))
      return false; 
    int meta = playerMP.field_70170_p.func_72805_g(blockX, blockY, blockZ);
    BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(blockX, blockY, blockZ, playerMP.field_70170_p, block, meta, (EntityPlayer)playerMP);
    if (MinecraftForge.EVENT_BUS.post((Event)event))
      return false; 
    extractItems(playerMP.field_70170_p, itemCounts, blockX, blockY, blockZ, block, meta);
    playerMP.field_70170_p.func_147468_f(blockX, blockY, blockZ);
    return true;
  }
  
  private void extractItems(World world, Map<ItemMeta, Integer> itemCounts, int blockX, int blockY, int blockZ, Block block, int meta) {
    List<ItemStack> blockDrops = block.getDrops(world, blockX, blockY, blockZ, meta, 0);
    if (blockDrops != null)
      for (ItemStack drop : blockDrops) {
        ItemMeta key = ItemMeta.of(drop);
        int value = ((Integer)itemCounts.getOrDefault(key, Integer.valueOf(0))).intValue();
        itemCounts.put(key, Integer.valueOf(value + drop.field_77994_a));
      }  
  }
  
  private class PlayerSelector implements IEntitySelector {
    private final boolean checkCombat;
    
    public PlayerSelector(boolean checkCombat) {
      this.checkCombat = checkCombat;
    }
    
    public boolean func_82704_a(Entity entity) {
      if (ForgeEnv.isIDE()) {
        if (entity instanceof EntityPlayerMP) {
          EntityPlayerMP entityPlayerMP = (EntityPlayerMP)entity;
          return (!entityPlayerMP.field_71075_bZ.field_75098_d && !entityPlayerMP.func_70005_c_().equals(EntityAncientHammerEffect.this.player));
        } 
        return true;
      } 
      if (!(entity instanceof EntityPlayerMP))
        return false; 
      EntityPlayerMP playerMP = (EntityPlayerMP)entity;
      if (playerMP.field_71075_bZ.field_75098_d)
        return false; 
      if (this.checkCombat)
        try {
          if (!CombatTag.getInstance().getManager().isInCombat(Bukkit.getPlayer(playerMP.func_110124_au())))
            return false; 
        } catch (Exception exception) {} 
      return !playerMP.func_70005_c_().equals(EntityAncientHammerEffect.this.player);
    }
  }
  
  private class MobSelector implements IEntitySelector {
    private MobSelector() {}
    
    public boolean func_82704_a(Entity entity) {
      if (!(entity instanceof net.minecraft.entity.monster.EntityMob) && !(entity instanceof net.minecraft.entity.passive.EntityAnimal))
        return false; 
      EntityLivingBase mob = (EntityLivingBase)entity;
      if (mob.field_70128_L || mob.func_85032_ar() || mob instanceof fr.paladium.palajobs.core.entity.boss.AEntityJobBoss || mob instanceof fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase || WitherUtils.isWither((Entity)mob))
        return false; 
      return true;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\entities\ancient\EntityAncientHammerEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */