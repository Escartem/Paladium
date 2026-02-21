package fr.paladium.palashop.provider.cosmetic.factory.impl.spray.common.entity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.SprayCosmeticFactory;
import io.netty.buffer.ByteBuf;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class EntitySprayCosmetic extends EntityLiving implements IEntityAdditionalSpawnData {
  private static final long LIFE_TIME = 20L;
  
  private transient ICosmetic cosmetic;
  
  private String id;
  
  private ForgeDirection direction;
  
  public void setCosmetic(ICosmetic cosmetic) {
    this.cosmetic = cosmetic;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public void setDirection(ForgeDirection direction) {
    this.direction = direction;
  }
  
  public ICosmetic getCosmetic() {
    return this.cosmetic;
  }
  
  public String getId() {
    return this.id;
  }
  
  public ForgeDirection getDirection() {
    return this.direction;
  }
  
  public EntitySprayCosmetic(@NonNull World world) {
    super(world);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    func_70606_j(1.0F);
    func_70105_a(0.99F, 0.99F);
  }
  
  public EntitySprayCosmetic(@NonNull World world, double x, double y, double z, @NonNull String id, @NonNull ForgeDirection direction) {
    this(world);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (direction == null)
      throw new NullPointerException("direction is marked non-null but is null"); 
    func_70107_b(x + 0.5D, y, z + 0.5D);
    this.id = id;
    this.direction = direction;
  }
  
  public void func_70071_h_() {
    if (this.id == null || this.direction == null) {
      func_70106_y();
      return;
    } 
    int blockX = MathHelper.func_76128_c(this.field_70165_t);
    int blockY = MathHelper.func_76128_c(this.field_70163_u);
    int blockZ = MathHelper.func_76128_c(this.field_70161_v);
    Block block = this.field_70170_p.func_147439_a(blockX, blockY, blockZ);
    if (block == null || block == Blocks.field_150350_a) {
      func_70106_y();
      return;
    } 
    if (this.cosmetic == null) {
      Optional<? extends ICosmetic> optionalCosmetic = SprayCosmeticFactory.getInstance().getCosmetic(this.id);
      if (!optionalCosmetic.isPresent()) {
        func_70106_y();
        return;
      } 
      this.cosmetic = optionalCosmetic.get();
    } 
    if (this.field_70173_aa > 400L)
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
    this.direction = ForgeDirection.values()[nbt.func_74762_e("direction")];
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74778_a("id", this.id);
    nbt.func_74768_a("direction", this.direction.ordinal());
  }
  
  public void writeSpawnData(ByteBuf buf) {
    if (this.id == null || this.direction == null)
      return; 
    ByteBufUtils.writeUTF8String(buf, this.id);
    buf.writeInt(this.direction.ordinal());
  }
  
  public void readSpawnData(ByteBuf buf) {
    if (!buf.isReadable()) {
      func_70106_y();
      return;
    } 
    this.id = ByteBufUtils.readUTF8String(buf);
    this.direction = ForgeDirection.values()[buf.readInt()];
  }
  
  public ItemStack getPickedResult(MovingObjectPosition target) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\spray\common\entity\EntitySprayCosmetic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */