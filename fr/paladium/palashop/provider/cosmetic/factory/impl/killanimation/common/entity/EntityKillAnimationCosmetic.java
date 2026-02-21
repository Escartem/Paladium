package fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.common.entity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.KillAnimationCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.server.dto.KillAnimationCosmetic;
import io.netty.buffer.ByteBuf;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityKillAnimationCosmetic extends EntityLiving implements IEntityAdditionalSpawnData {
  private String id;
  
  private String player;
  
  private long duration;
  
  private transient long loadedAt;
  
  private transient ICosmetic cosmetic;
  
  public void setId(String id) {
    this.id = id;
  }
  
  public void setPlayer(String player) {
    this.player = player;
  }
  
  public void setDuration(long duration) {
    this.duration = duration;
  }
  
  public void setLoadedAt(long loadedAt) {
    this.loadedAt = loadedAt;
  }
  
  public void setCosmetic(ICosmetic cosmetic) {
    this.cosmetic = cosmetic;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getPlayer() {
    return this.player;
  }
  
  public long getDuration() {
    return this.duration;
  }
  
  public long getLoadedAt() {
    return this.loadedAt;
  }
  
  public ICosmetic getCosmetic() {
    return this.cosmetic;
  }
  
  public EntityKillAnimationCosmetic(@NonNull World world) {
    super(world);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    func_70606_j(1.0F);
    func_70105_a(3.0F, 3.0F);
  }
  
  public EntityKillAnimationCosmetic(@NonNull World world, @NonNull KillAnimationCosmetic cosmetic, @NonNull Entity player, float yaw) {
    this(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, cosmetic.getId(), player.func_70005_c_(), ((KillAnimationCosmetic.KillCosmeticProperties)cosmetic.getProperties()).getDuration(), yaw);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
  }
  
  public EntityKillAnimationCosmetic(@NonNull World world, double x, double y, double z, @NonNull String id, @NonNull String player, long duration, float yaw) {
    this(world);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    func_70080_a(x, y, z, yaw, 0.0F);
    this.id = id;
    this.player = player;
    this.duration = duration;
  }
  
  public void func_70071_h_() {
    if (this.id == null) {
      func_70106_y();
      return;
    } 
    if (this.cosmetic == null) {
      Optional<? extends ICosmetic> optionalCosmetic = KillAnimationCosmeticFactory.getInstance().getCosmetic(this.id);
      if (!optionalCosmetic.isPresent()) {
        func_70106_y();
        return;
      } 
      this.cosmetic = optionalCosmetic.get();
      this.loadedAt = System.currentTimeMillis();
    } 
    if (this.cosmetic != null && this.loadedAt != 0L && System.currentTimeMillis() - this.loadedAt >= this.duration)
      func_70106_y(); 
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
    this.id = nbt.func_74779_i("id");
    this.player = nbt.func_74779_i("player");
    this.duration = nbt.func_74763_f("duration");
    this.field_70173_aa = nbt.func_74762_e("ticksExisted");
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74778_a("id", this.id);
    nbt.func_74778_a("player", this.player);
    nbt.func_74772_a("duration", this.duration);
  }
  
  public void writeSpawnData(ByteBuf buf) {
    if (this.id == null || this.id.isEmpty() || this.player == null || this.player.isEmpty())
      return; 
    ByteBufUtils.writeUTF8String(buf, this.id);
    ByteBufUtils.writeUTF8String(buf, this.player);
    buf.writeLong(this.duration);
    buf.writeInt(this.field_70173_aa);
  }
  
  public void readSpawnData(ByteBuf buf) {
    if (!buf.isReadable()) {
      func_70106_y();
      return;
    } 
    this.id = ByteBufUtils.readUTF8String(buf);
    this.player = ByteBufUtils.readUTF8String(buf);
    this.duration = buf.readLong();
    this.field_70173_aa = buf.readInt();
  }
  
  public ItemStack getPickedResult(MovingObjectPosition target) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killanimation\common\entity\EntityKillAnimationCosmetic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */