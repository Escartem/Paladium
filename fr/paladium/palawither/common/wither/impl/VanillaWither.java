package fr.paladium.palawither.common.wither.impl;

import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palawither.common.item.ItemWitherUpgrade;
import fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle;
import fr.paladium.palawither.common.utils.UniversalTimeUtils;
import fr.paladium.palawither.common.wither.base.IWither;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public final class VanillaWither implements IWither {
  public VanillaWither(World world, EntityWither entity) {
    this.world = world;
    this.entity = entity;
  }
  
  public VanillaWither(World world) {
    this.world = world;
  }
  
  private static final Map<String, VanillaWither> WITHER_MAP = new HashMap<>();
  
  private final transient World world;
  
  private transient EntityWither entity;
  
  public World getWorld() {
    return this.world;
  }
  
  @NonNull
  public VanillaWither onInvoke(@NonNull TileEntityWitherReceptacle tile, @NonNull EntityPlayer player, IFaction faction) {
    if (tile == null)
      throw new NullPointerException("tile is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (!(tile.func_145831_w()).field_72995_K)
      tile.func_145831_w().func_72908_a(tile.field_145851_c + 0.5D, tile.field_145848_d + 0.5D, tile.field_145849_e + 0.5D, "mob.wither.spawn", 0.2F, 1.0F); 
    return this;
  }
  
  @NonNull
  public VanillaWither onSpawn(@NonNull TileEntityWitherReceptacle tile) {
    if (tile == null)
      throw new NullPointerException("tile is marked non-null but is null"); 
    EntityLivingBase entityLivingBase = getEntity();
    this.world.func_72885_a((Entity)entityLivingBase, ((Entity)entityLivingBase).field_70165_t, ((Entity)entityLivingBase).field_70163_u + entityLivingBase.func_70047_e(), ((Entity)entityLivingBase).field_70161_v, 7.0F, false, this.world.func_82736_K().func_82766_b("mobGriefing"));
    if (!(tile.func_145831_w()).field_72995_K)
      tile.func_145831_w().func_72908_a(tile.field_145851_c + 0.5D, tile.field_145848_d + 0.5D, tile.field_145849_e + 0.5D, "mob.wither.spawn", 0.2F, 1.0F); 
    return this;
  }
  
  public boolean isArmored() {
    return (this.entity != null && this.entity.func_82205_o());
  }
  
  public String getDisplayName() {
    return "entity.WitherBoss.name";
  }
  
  public String getBarTexture() {
    return "palawither:textures/overlay/wither/foreground/wither.png";
  }
  
  public String getColor() {
    return "#202020";
  }
  
  public boolean hasUpgrade(@NonNull ItemWitherUpgrade upgrade) {
    if (upgrade == null)
      throw new NullPointerException("upgrade is marked non-null but is null"); 
    ItemWitherUpgrade existingUpgrade = getUpgrade();
    if (existingUpgrade == null)
      return false; 
    return existingUpgrade.equals(upgrade);
  }
  
  public boolean addUpgrade(@NonNull EntityPlayer player, @NonNull ItemWitherUpgrade upgrade) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (upgrade == null)
      throw new NullPointerException("upgrade is marked non-null but is null"); 
    if (getUpgrade() != null) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCe §cWither §cpossède §cdéjà §cune §camélioration §c!"));
      return false;
    } 
    setUpgrade(upgrade);
    return true;
  }
  
  public EntityLivingBase getEntity() {
    if (this.entity != null)
      return (EntityLivingBase)this.entity; 
    this.entity = new EntityWither(this.world);
    this.entity.func_70096_w().func_82709_a(21, 5);
    this.entity.func_70096_w().func_82709_a(22, 2);
    this.entity.func_70096_w().func_75692_b(21, null);
    this.entity.func_70096_w().func_75692_b(22, Integer.valueOf(0));
    WITHER_MAP.put(UUIDUtils.toString((Entity)this.entity), this);
    return (EntityLivingBase)this.entity;
  }
  
  private ItemWitherUpgrade getUpgrade() {
    if (this.entity == null || this.entity.func_70096_w() == null)
      return null; 
    try {
      ItemStack stack = this.entity.func_70096_w().func_82710_f(21);
      if (stack == null || stack.func_77973_b() == null || !(stack.func_77973_b() instanceof ItemWitherUpgrade))
        return null; 
      long upgradeTime = this.entity.func_70096_w().func_75679_c(22) * 1000L;
      if (upgradeTime + TimeUnit.HOURS.toMillis(1L) < UniversalTimeUtils.now()) {
        setUpgrade(null);
        return null;
      } 
      return (ItemWitherUpgrade)stack.func_77973_b();
    } catch (Exception e) {
      return null;
    } 
  }
  
  private void setUpgrade(ItemWitherUpgrade upgrade) {
    if (this.entity == null)
      return; 
    if (upgrade == null) {
      this.entity.func_70096_w().func_75692_b(21, null);
      this.entity.func_70096_w().func_75692_b(22, Integer.valueOf(0));
    } else {
      this.entity.func_70096_w().func_75692_b(21, new ItemStack((Item)upgrade));
      this.entity.func_70096_w().func_75692_b(22, Integer.valueOf((int)(UniversalTimeUtils.now() / 1000L)));
    } 
  }
  
  public static Optional<VanillaWither> getByEntity(@NonNull EntityWither entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    if (entity instanceof EntityWither)
      return Optional.ofNullable(WITHER_MAP.computeIfAbsent(UUIDUtils.toString((Entity)entity), uuid -> {
              entity.func_70096_w().func_82709_a(21, 5);
              entity.func_70096_w().func_82709_a(22, 2);
              entity.func_70096_w().func_75692_b(21, null);
              entity.func_70096_w().func_75692_b(22, Integer.valueOf(0));
              return new VanillaWither(entity.field_70170_p, entity);
            })); 
    return Optional.empty();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\impl\VanillaWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */