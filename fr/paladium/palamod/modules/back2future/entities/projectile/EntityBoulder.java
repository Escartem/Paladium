package fr.paladium.palamod.modules.back2future.entities.projectile;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palamod.modules.back2future.entities.EntityGolem;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.back2future.entities.player.MBExtendedPlayer;
import io.netty.buffer.ByteBuf;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBoulder extends EntityMobThrowable implements IEntityAdditionalSpawnData {
  private EntityGolem shooter;
  
  public ResourceLocation textureLoc;
  
  public int textureBlockID;
  
  public EntityBoulder(World world) {
    super(world);
  }
  
  public EntityBoulder(World world, EntityLivingBase entity) {
    super(world, entity);
  }
  
  public EntityBoulder(World world, double x, double y, double z) {
    super(world, x, y, z);
  }
  
  public EntityBoulder(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float wobble, float FrontToBack, float YOffset, float SideToSide, float Size1, float Size2) {
    super(world, shooter, target, velocity, wobble, FrontToBack, YOffset, SideToSide, Size1, Size2);
    this.shooter = (EntityGolem)shooter;
    try {
      this.textureBlockID = this.shooter.textureBlockID;
    } catch (Exception e) {
      func_70106_y();
      return;
    } 
  }
  
  public EntityBoulder(World world, EntityLivingBase shooter, BlockPos pos, float velocity, float MotionY) {
    super(world, shooter, pos, velocity, MotionY);
    this.shooter = (EntityGolem)shooter;
    try {
      this.textureBlockID = this.shooter.textureBlockID;
    } catch (Exception e) {
      func_70106_y();
      return;
    } 
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    collideWithEntities(this.field_70170_p
        .func_72872_a(EntityPlayer.class, this.field_70121_D));
    if (this.field_70170_p.field_72995_K)
      for (int x = 0; x < 15; x++)
        this.field_70170_p.func_72869_a("blockdust", this.field_70165_t, this.field_70163_u, this.field_70161_v, Math.random() / 5.0D, 
            Math.random() / 5.0D, Math.random() / 5.0D);  
  }
  
  private void collideWithEntities(List list) {
    Iterator<Entity> iterator = list.iterator();
    while (iterator.hasNext()) {
      Entity entity = iterator.next();
      if (entity.field_70172_ad == 0 && entity instanceof EntityPlayer) {
        (MBExtendedPlayer.get((EntityPlayer)entity)).knockdownTime = 60;
        entity.func_70097_a(DamageSource.func_76356_a((Entity)this, (Entity)func_85052_h()), getDamage());
      } 
    } 
    if (!list.isEmpty()) {
      for (int x = 0; x < 40; x++)
        this.field_70170_p.func_72869_a("blockdust", this.field_70165_t, this.field_70163_u, this.field_70161_v, Math.random(), 
            Math.random(), Math.random()); 
      func_85030_a("palamod:boulder_hit", 1.0F, 1.0F);
      func_70106_y();
    } 
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    ByteBufUtils.writeVarInt(buffer, this.textureBlockID, 4);
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    this.textureBlockID = ByteBufUtils.readVarInt(additionalData, 4);
    if (this.field_70170_p.field_72995_K) {
      Block iblockstate = Block.func_149729_e(this.textureBlockID);
      String string = iblockstate.func_149691_a(0, 0).func_94215_i() + ".png";
      String[] parts = string.split(":");
      if (parts.length == 1) {
        String[] partz = new String[2];
        partz[1] = parts[0];
        partz[0] = "minecraft";
        parts = partz;
      } 
      this.textureLoc = new ResourceLocation(parts[0] + ":textures/blocks/" + parts[1]);
    } 
  }
  
  public void func_70020_e(NBTTagCompound compound) {
    super.func_70020_e(compound);
    this.textureBlockID = compound.func_74762_e("blockID");
    if (this.field_70170_p.field_72995_K) {
      Block iblockstate = Block.func_149729_e(this.textureBlockID);
      String string = iblockstate.func_149691_a(0, 0).func_94215_i() + ".png";
      String[] parts = string.split(":");
      if (parts.length == 1) {
        String[] partz = new String[2];
        partz[1] = parts[0];
        partz[0] = "minecraft";
        parts = partz;
      } 
      this.textureLoc = new ResourceLocation(parts[0] + ":textures/blocks/" + parts[1]);
    } 
  }
  
  public void func_70109_d(NBTTagCompound compound) {
    super.func_70109_d(compound);
    compound.func_74768_a("blockID", this.textureBlockID);
  }
  
  protected void func_70184_a(MovingObjectPosition p_70184_1_) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\projectile\EntityBoulder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */