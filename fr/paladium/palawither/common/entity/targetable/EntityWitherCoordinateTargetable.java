package fr.paladium.palawither.common.entity.targetable;

import fr.paladium.factions.api.FactionAPI;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.player.IPlayer;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle;
import fr.paladium.palawither.common.wither.base.EntityWitherBase;
import fr.paladium.palawither.common.wither.base.ai.FactionFriendlyWitherEntitySelector;
import fr.paladium.palawither.common.wither.base.projectile.EntityWitherProjectile;
import fr.paladium.palawither.common.wither.base.property.WitherProperties;
import java.util.UUID;
import lombok.NonNull;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public abstract class EntityWitherCoordinateTargetable extends EntityWitherBase implements IWitherCoordinateTargetable {
  public void setAdminMode(boolean adminMode) {
    this.adminMode = adminMode;
  }
  
  public void setFactionUUID(UUID factionUUID) {
    this.factionUUID = factionUUID;
  }
  
  private final IEntitySelector attackSelector = (IEntitySelector)new FactionFriendlyWitherEntitySelector(() -> this.factionUUID);
  
  private boolean adminMode;
  
  private UUID factionUUID;
  
  public IEntitySelector getAttackSelector() {
    return this.attackSelector;
  }
  
  public boolean isAdminMode() {
    return this.adminMode;
  }
  
  public UUID getFactionUUID() {
    return this.factionUUID;
  }
  
  public EntityWitherCoordinateTargetable(@NonNull World world) {
    super(world);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(23, Integer.valueOf(0));
    this.field_70180_af.func_75682_a(24, Integer.valueOf(0));
    this.field_70180_af.func_75682_a(25, Integer.valueOf(0));
    this.field_70180_af.func_75682_a(26, Byte.valueOf((byte)0));
  }
  
  public void func_70071_h_() {
    if (hasTargetLocation() && !isPathFinished()) {
      if (this.field_70715_bh.field_75782_a.size() > 0 || this.field_70714_bg.field_75782_a.size() > 0) {
        this.field_70714_bg.field_75782_a.clear();
        this.field_70715_bh.field_75782_a.clear();
      } 
      double dx = getTargetLocationX() - this.field_70165_t;
      double dy = getTargetLocationY() - this.field_70163_u;
      double dz = getTargetLocationZ() - this.field_70161_v;
      double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
      if (distance >= 0.5D) {
        this.field_70159_w = 0.0D;
        this.field_70181_x = 0.0D;
        this.field_70179_y = 0.0D;
        if (!this.field_70170_p.field_72995_K) {
          double speed = func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e();
          this.field_70159_w = dx / distance * speed;
          this.field_70181_x = dy / distance * speed / 2.0D;
          this.field_70179_y = dz / distance * speed;
        } 
        this.field_70177_z = (float)Math.toDegrees(Math.atan2(dz, dx)) - 90.0F;
      } else {
        setTargetLocation(null);
        setPathFinished(true);
        if (getProjectile() != null)
          addProperty(new Object[] { WitherProperties.projectile(getProjectile(), this.attackSelector) }); 
      } 
    } else {
      this.field_70159_w = 0.0D;
      this.field_70181_x = 0.0D;
      this.field_70179_y = 0.0D;
    } 
    super.func_70071_h_();
  }
  
  public void func_70624_b(EntityLivingBase entity) {
    if (!this.field_70170_p.field_72995_K && entity instanceof EntityPlayer && !this.attackSelector.func_82704_a((Entity)entity))
      return; 
    super.func_70624_b(entity);
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    this.adminMode = (nbt.func_74764_b("AdminMode") && nbt.func_74767_n("AdminMode"));
    if (!this.adminMode)
      this.factionUUID = nbt.func_74764_b("FactionUUID") ? UUID.fromString(nbt.func_74779_i("FactionUUID")) : null; 
    if (nbt.func_74764_b("Target")) {
      NBTTagCompound target = nbt.func_74775_l("Target");
      setTargetLocationX(target.func_74762_e("X"));
      setTargetLocationY(target.func_74762_e("Y"));
      setTargetLocationZ(target.func_74762_e("Z"));
      setPathFinished(target.func_74767_n("Finished"));
    } 
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74757_a("AdminMode", this.adminMode);
    if (this.factionUUID != null && !this.adminMode)
      nbt.func_74778_a("FactionUUID", UUIDUtils.toString(this.factionUUID)); 
    NBTTagCompound target = new NBTTagCompound();
    target.func_74768_a("X", getTargetLocationX());
    target.func_74768_a("Y", getTargetLocationY());
    target.func_74768_a("Z", getTargetLocationZ());
    target.func_74757_a("Finished", isPathFinished());
    nbt.func_74782_a("Target", (NBTBase)target);
  }
  
  public int getTargetLocationX() {
    return this.field_70180_af.func_75679_c(23);
  }
  
  public int getTargetLocationY() {
    return this.field_70180_af.func_75679_c(24);
  }
  
  public int getTargetLocationZ() {
    return this.field_70180_af.func_75679_c(25);
  }
  
  public boolean isPathFinished() {
    return (this.field_70180_af.func_75683_a(26) == 1);
  }
  
  public boolean hasTargetLocation() {
    return (getTargetLocationX() != 0 || getTargetLocationY() != 0 || getTargetLocationZ() != 0);
  }
  
  public void setTargetLocationX(int x) {
    this.field_70180_af.func_75692_b(23, Integer.valueOf(x));
  }
  
  public void setTargetLocationY(int y) {
    this.field_70180_af.func_75692_b(24, Integer.valueOf(Math.min(256, y)));
  }
  
  public void setTargetLocationZ(int z) {
    this.field_70180_af.func_75692_b(25, Integer.valueOf(z));
  }
  
  public void setPathFinished(boolean finished) {
    if (isPathFinished() && !finished)
      removeProperty("projectile"); 
    this.field_70180_af.func_75692_b(26, Byte.valueOf((byte)(finished ? 1 : 0)));
  }
  
  public boolean canSetTargetLocation(EntityPlayer player) {
    if (player == null)
      return false; 
    if (ForgeEnv.isIDE() || this.adminMode)
      return true; 
    IPlayer factionPlayer = FactionAPI.getInstance().getPlayer(player);
    if (factionPlayer == null) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous §cne §cfaites §cpas §cpartie §cde §cla faction §cqui §ca §cinvoqué §cce §cWither."));
      return false;
    } 
    IFaction faction = factionPlayer.getFaction();
    if (faction == null)
      return false; 
    if (this.factionUUID == null) {
      this.factionUUID = faction.getUuid();
      return true;
    } 
    if (!factionPlayer.isAdminMode() && (faction == null || !faction.getUuid().equals(this.factionUUID))) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous §cne §cfaites §cpas §cpartie §cde §cla faction §cqui §ca §cinvoqué §cce §cWither."));
      return false;
    } 
    return true;
  }
  
  public void setTargetLocation(DoubleLocation location) {
    setTargetLocationX((location == null) ? 0 : location.getBlockX());
    setTargetLocationY((location == null) ? 0 : location.getBlockY());
    setTargetLocationZ((location == null) ? 0 : location.getBlockZ());
    setPathFinished(false);
  }
  
  @NonNull
  public <T extends fr.paladium.palawither.common.wither.base.IWither> T onInvoke(@NonNull TileEntityWitherReceptacle tile, @NonNull EntityPlayer player, IFaction faction) {
    if (tile == null)
      throw new NullPointerException("tile is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (faction != null) {
      this.factionUUID = faction.getUuid();
    } else {
      this.adminMode = true;
      this.factionUUID = null;
    } 
    return (T)super.onInvoke(tile, player, faction);
  }
  
  public abstract Class<? extends EntityWitherProjectile> getProjectile();
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\entity\targetable\EntityWitherCoordinateTargetable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */