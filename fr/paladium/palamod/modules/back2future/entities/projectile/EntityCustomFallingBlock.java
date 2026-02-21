package fr.paladium.palamod.modules.back2future.entities.projectile;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientFrozen;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.util.FastUUID;
import fr.paladium.palamod.util.IBlockState;
import io.netty.buffer.ByteBuf;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityCustomFallingBlock extends Entity implements IEntityAdditionalSpawnData {
  public IBlockState fallTile;
  
  public int fallTime;
  
  public boolean shouldDropItem = true;
  
  private boolean field_145808_f;
  
  private boolean hurtEntities;
  
  private int fallHurtMax = 40;
  
  private float fallHurtAmount = 2.0F;
  
  public NBTTagCompound tileEntityData;
  
  private static final String __OBFID = "CL_00001668";
  
  public BlockPos bPos;
  
  public int damage;
  
  public Entity shooter;
  
  public int duration = 20;
  
  public Type type;
  
  public int tier;
  
  public UUID owner;
  
  public int rotateY;
  
  public boolean top = false;
  
  public enum Type {
    NONE, NORMAL, SURVIVING, FREEZE;
  }
  
  public EntityCustomFallingBlock(World worldIn) {
    super(worldIn);
  }
  
  public EntityCustomFallingBlock(World worldIn, Entity shooter, double x, double y, double z, double mY, float yaw, BlockPos pos, Block b, int meta, Type type, int dmg, int duration, int rotate, boolean top) {
    super(worldIn);
    this.bPos = pos;
    this.fallTile = new IBlockState(b, meta);
    this.field_70156_m = true;
    func_70105_a(1.0F, 1.0F);
    func_70080_a(x, y, z, yaw, 0.0F);
    this.field_70159_w = 0.0D;
    this.field_70181_x = mY;
    this.field_70179_y = 0.0D;
    this.field_70169_q = x;
    this.field_70167_r = y;
    this.field_70166_s = z;
    this.field_70145_X = true;
    this.damage = dmg;
    this.type = type;
    this.shooter = shooter;
    this.duration = duration;
    this.rotateY = rotate;
    this.tier = 1;
    this.owner = shooter.func_110124_au();
  }
  
  public EntityCustomFallingBlock(World worldIn, Entity shooter, double x, double y, double z, double mY, float yaw, BlockPos pos, int dmg) {
    super(worldIn);
    this.bPos = pos;
    this
      .fallTile = new IBlockState(this.field_70170_p.func_147439_a(pos.getX(), pos.getY(), pos.getZ()), this.field_70170_p.func_147439_a(pos.getX(), pos.getY(), pos.getZ()).func_149643_k(this.field_70170_p, pos
          .getX(), pos.getY(), pos.getZ()));
    this.field_70156_m = true;
    func_70105_a(1.0F, 1.0F);
    func_70080_a(x, y, z, yaw, 0.0F);
    this.field_70159_w = 0.0D;
    this.field_70181_x = mY;
    this.field_70179_y = 0.0D;
    this.field_70169_q = x;
    this.field_70167_r = y;
    this.field_70166_s = z;
    this.field_70145_X = true;
    this.damage = dmg;
    this.shooter = shooter;
  }
  
  public EntityCustomFallingBlock(World worldIn, Entity shooter, double x, double y, double z, double mY, float yaw, BlockPos pos, Block b, int meta, int dmg, Type type, int tier, UUID owner) {
    super(worldIn);
    this.bPos = pos;
    this.fallTile = new IBlockState(b, meta);
    this.field_70156_m = true;
    func_70105_a((type == Type.FREEZE) ? 0.3F : 1.0F, (type == Type.FREEZE) ? 0.3F : 1.0F);
    func_70080_a(x, y, z, yaw, 0.0F);
    this.field_70159_w = 0.0D;
    this.field_70181_x = mY;
    this.field_70179_y = 0.0D;
    this.field_70169_q = x;
    this.field_70167_r = y;
    this.field_70166_s = z;
    this.field_70145_X = true;
    this.damage = dmg;
    this.shooter = shooter;
    this.type = type;
    this.rotateY = worldIn.field_73012_v.nextInt(150) + 75;
    this.tier = tier;
    this.owner = owner;
  }
  
  public void func_70071_h_() {
    this.field_70169_q = this.field_70165_t;
    this.field_70167_r = this.field_70163_u;
    this.field_70166_s = this.field_70161_v;
    if (this.type == Type.SURVIVING && !this.field_70170_p.field_72995_K)
      func_70107_b(this.shooter.field_70142_S, this.shooter.field_70137_T + (this.top ? true : false), this.shooter.field_70136_U); 
    if (this.type == Type.FREEZE || this.type == Type.SURVIVING) {
      this.field_70181_x = 0.0D;
    } else {
      this.field_70181_x -= 0.03999999910593033D;
    } 
    func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
    this.field_70159_w *= 0.9800000190734863D;
    this.field_70181_x *= 0.9800000190734863D;
    this.field_70179_y *= 0.9800000190734863D;
    if (this.field_70173_aa > this.duration)
      func_70106_y(); 
    if (this.type == Type.SURVIVING && !this.field_70170_p.field_72995_K && 
      !((EntityLivingBase)this.shooter).func_82165_m(PLuckyBlock.SURVIVING.field_76415_H))
      func_70106_y(); 
    collideWithEntities(this.field_70170_p.func_72839_b(this, this.field_70121_D
          .func_72314_b(0.5D, 0.5D, 0.5D)));
  }
  
  private void collideWithEntities(List list) {
    double d0 = (this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0D;
    double d1 = (this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0D;
    Iterator<Entity> iterator = list.iterator();
    while (iterator.hasNext()) {
      Entity entity = iterator.next();
      if (entity.func_110124_au().equals(this.owner))
        continue; 
      if (this.type == Type.NORMAL) {
        double d2 = entity.field_70165_t - d0;
        double d3 = entity.field_70161_v - d1;
        double d4 = d2 * d2 + d3 * d3;
        if (entity.field_70172_ad == 0 && !(entity instanceof EntityCustomFallingBlock) && !(entity instanceof fr.paladium.palamod.modules.back2future.entities.EntityGolem)) {
          entity.func_70024_g(d2 / d4 * 0.2D, 1.2D, d3 / d4 * 0.2D);
          entity.func_70097_a(DamageSource.field_76379_h, this.damage);
          entity.field_70172_ad = 10;
        } 
        continue;
      } 
      if (this.type == Type.FREEZE && !this.field_70170_p.field_72995_K && 
        entity instanceof EntityPlayerMP) {
        final EntityPlayerMP pl = (EntityPlayerMP)entity;
        try {
          if (!BukkitUtils.hasPermission(pl.func_110124_au(), "spell.freeze.bypass")) {
            EventUtils.spawnParticle(pl.field_70170_p, "instantSpell", pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, 50, 0.10000000149011612D);
            ServerManager.addFreeze(pl, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v);
            PalaMod.getNetwork().sendTo((IMessage)new PacketClientFrozen(true, this.tier), pl);
            (new Thread(new Runnable() {
                  public void run() {
                    try {
                      Thread.sleep((2000 * EntityCustomFallingBlock.this.tier));
                      ServerManager.removeFreeze(pl);
                      PalaMod.getNetwork().sendTo((IMessage)new PacketClientFrozen(false, EntityCustomFallingBlock.this.tier), pl);
                    } catch (Exception e) {
                      e.printStackTrace();
                    } 
                  }
                })).start();
          } 
        } catch (Exception e) {
          EventUtils.spawnParticle(pl.field_70170_p, "instantSpell", pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, 50, 0.10000000149011612D);
          ServerManager.addFreeze(pl, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v);
          PalaMod.getNetwork().sendTo((IMessage)new PacketClientFrozen(true, this.tier), pl);
          (new Thread(new Runnable() {
                public void run() {
                  try {
                    Thread.sleep((2000 * EntityCustomFallingBlock.this.tier));
                    ServerManager.removeFreeze(pl);
                    PalaMod.getNetwork().sendTo((IMessage)new PacketClientFrozen(false, EntityCustomFallingBlock.this.tier), pl);
                  } catch (Exception e) {
                    e.printStackTrace();
                  } 
                }
              })).start();
        } 
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public World getWorldObj() {
    return this.field_70170_p;
  }
  
  public IBlockState getBlock() {
    return this.fallTile;
  }
  
  protected void func_70088_a() {}
  
  protected void func_70014_b(NBTTagCompound tagCompound) {}
  
  protected void func_70037_a(NBTTagCompound tagCompund) {}
  
  public void writeSpawnData(ByteBuf buffer) {
    if (this.bPos != null) {
      buffer.writeInt(this.bPos.getX());
      buffer.writeInt(this.bPos.getY());
      buffer.writeInt(this.bPos.getZ());
      buffer.writeInt(Block.func_149682_b(this.fallTile.getBlock()));
      buffer.writeInt(this.fallTile.getMeta());
      buffer.writeDouble(this.field_70181_x);
      buffer.writeFloat(this.field_70177_z);
      buffer.writeInt(this.duration);
    } 
    if (this.type != null) {
      buffer.writeInt(this.type.ordinal());
      buffer.writeInt(this.tier);
      ByteBufUtils.writeUTF8String(buffer, FastUUID.toString(this.owner));
      buffer.writeInt(this.rotateY);
    } 
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (additionalData.isReadable()) {
      int x = additionalData.readInt();
      int y = additionalData.readInt();
      int z = additionalData.readInt();
      this.bPos = new BlockPos(x, y, z);
      this
        .fallTile = new IBlockState(Block.func_149729_e(additionalData.readInt()), additionalData.readInt());
      if (this.fallTile.getBlock() instanceof net.minecraft.block.BlockStaticLiquid) {
        func_70106_y();
        return;
      } 
      this.field_70181_x = additionalData.readDouble();
      this.field_70145_X = true;
      this.field_70177_z = additionalData.readFloat();
      this.duration = additionalData.readInt();
      if (additionalData.isReadable()) {
        this.type = Type.values()[additionalData.readInt()];
        this.tier = additionalData.readInt();
        this.owner = FastUUID.parseUUID(ByteBufUtils.readUTF8String(additionalData));
        this.rotateY = additionalData.readInt();
      } 
    } else {
      func_70106_y();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\projectile\EntityCustomFallingBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */