package fr.paladium.palamod.modules.luckyblock.network.luckypass;

import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerLuckyPassProperties implements IExtendedEntityProperties {
  public static final String PROP_NAME = "palamod_LP";
  
  private EntityPlayer entity;
  
  public EntityPlayer getEntity() {
    return this.entity;
  }
  
  public void setEntity(EntityPlayer entity) {
    this.entity = entity;
  }
  
  private boolean hasLuckyPass = false;
  
  public boolean isHasLuckyPass() {
    return this.hasLuckyPass;
  }
  
  public void setHasLuckyPass(boolean hasLuckyPass) {
    this.hasLuckyPass = hasLuckyPass;
  }
  
  private int[] wins = new int[3];
  
  private long winsDate;
  
  private long date;
  
  public int[] getWins() {
    return this.wins;
  }
  
  public void setWins(int[] wins) {
    this.wins = wins;
  }
  
  public long getWinsDate() {
    return this.winsDate;
  }
  
  public void setWinsDate(long winsDate) {
    this.winsDate = winsDate;
  }
  
  public long getDate() {
    return this.date;
  }
  
  public void setDate(long date) {
    this.date = date;
  }
  
  private boolean hasLuckyPassBypassNow = false;
  
  public boolean isHasLuckyPassBypassNow() {
    return this.hasLuckyPassBypassNow;
  }
  
  public void setHasLuckyPassBypassNow(boolean hasLuckyPassBypassNow) {
    this.hasLuckyPassBypassNow = hasLuckyPassBypassNow;
  }
  
  private boolean hasLuckyEventBypassNow = false;
  
  public boolean isHasLuckyEventBypassNow() {
    return this.hasLuckyEventBypassNow;
  }
  
  public void setHasLuckyEventBypassNow(boolean hasLuckyEventBypassNow) {
    this.hasLuckyEventBypassNow = hasLuckyEventBypassNow;
  }
  
  private boolean hasLuckyEventNoelReward = false;
  
  public boolean isHasLuckyEventNoelReward() {
    return this.hasLuckyEventNoelReward;
  }
  
  public void setHasLuckyEventNoelReward(boolean hasLuckyEventNoelReward) {
    this.hasLuckyEventNoelReward = hasLuckyEventNoelReward;
  }
  
  private boolean hasRemoveMoneyNow = false;
  
  public boolean isHasRemoveMoneyNow() {
    return this.hasRemoveMoneyNow;
  }
  
  public void setHasRemoveMoneyNow(boolean hasRemoveMoneyNow) {
    this.hasRemoveMoneyNow = hasRemoveMoneyNow;
  }
  
  private Entity entityVoyant = null;
  
  private BlockPos positionDeath;
  
  public Entity getEntityVoyant() {
    return this.entityVoyant;
  }
  
  public void setEntityVoyant(Entity entityVoyant) {
    this.entityVoyant = entityVoyant;
  }
  
  public BlockPos getPositionDeath() {
    return this.positionDeath;
  }
  
  public void setPositionDeath(BlockPos positionDeath) {
    this.positionDeath = positionDeath;
  }
  
  private boolean hasTombeDeath = false;
  
  private long time;
  
  private BlockPos positionRespawn;
  
  public boolean isHasTombeDeath() {
    return this.hasTombeDeath;
  }
  
  public void setHasTombeDeath(boolean hasTombeDeath) {
    this.hasTombeDeath = hasTombeDeath;
  }
  
  public long getTime() {
    return this.time;
  }
  
  public void setTime(long time) {
    this.time = time;
  }
  
  public BlockPos getPositionRespawn() {
    return this.positionRespawn;
  }
  
  public void setPositionRespawn(BlockPos positionRespawn) {
    this.positionRespawn = positionRespawn;
  }
  
  public PlayerLuckyPassProperties(EntityPlayer player) {
    this.entity = player;
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    compound.func_74757_a("hasLuckyPass", this.hasLuckyPass);
    compound.func_74783_a("wins", this.wins);
    compound.func_74772_a("winsDate", this.winsDate);
    compound.func_74757_a("hasTombeDeath", this.hasTombeDeath);
    compound.func_74772_a("date", this.date);
    if (this.positionDeath != null) {
      compound.func_74780_a("xDeath", this.positionDeath.getX());
      compound.func_74780_a("yDeath", this.positionDeath.getY());
      compound.func_74780_a("zDeath", this.positionDeath.getZ());
    } 
    if (this.positionRespawn != null) {
      compound.func_74780_a("xRespawn", this.positionRespawn.getX());
      compound.func_74780_a("yRespawn", this.positionRespawn.getY());
      compound.func_74780_a("zRespawn", this.positionRespawn.getZ());
    } 
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (compound.func_74764_b("hasLuckyPass")) {
      this.hasLuckyPass = compound.func_74767_n("hasLuckyPass");
    } else {
      this.hasLuckyPass = false;
    } 
    if (compound.func_74764_b("wins"))
      this.wins = compound.func_74759_k("wins"); 
    if (compound.func_74764_b("winsDate"))
      this.winsDate = compound.func_74763_f("winsDate"); 
    if (compound.func_74764_b("date"))
      this.date = compound.func_74763_f("date"); 
    if (compound.func_74764_b("hasTombeDeath"))
      this.hasTombeDeath = compound.func_74767_n("hasTombeDeath"); 
    if (compound.func_74764_b("xDeath") && compound.func_74764_b("yDeath") && compound.func_74764_b("zDeath"))
      this
        .positionDeath = new BlockPos(compound.func_74769_h("xDeath"), compound.func_74769_h("yDeath"), compound.func_74769_h("zDeath")); 
    if (compound.func_74764_b("xRespawn") && compound.func_74764_b("yRespawn") && compound.func_74764_b("zRespawn"))
      this
        .positionRespawn = new BlockPos(compound.func_74769_h("xRespawn"), compound.func_74769_h("yRespawn"), compound.func_74769_h("zRespawn")); 
  }
  
  public void entitySpawned() {
    if (this.entity != null)
      playerStartedTracking(this.entity); 
  }
  
  public void playerStartedTracking(EntityPlayer entityPlayer) {
    PalaMod.getNetwork().sendTo(new PlayerLuckyPassPropertiesSync(this), (EntityPlayerMP)entityPlayer);
  }
  
  public void init(Entity entity, World world) {
    this.entity = (EntityPlayer)entity;
  }
  
  public static final void register(EntityPlayer player) {
    player.registerExtendedProperties("palamod_LP", new PlayerLuckyPassProperties(player));
  }
  
  public static final PlayerLuckyPassProperties get(EntityPlayer player) {
    return (PlayerLuckyPassProperties)player.getExtendedProperties("palamod_LP");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\luckypass\PlayerLuckyPassProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */