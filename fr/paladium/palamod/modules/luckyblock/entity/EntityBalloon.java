package fr.paladium.palamod.modules.luckyblock.entity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.util.FastUUID;
import io.netty.buffer.ByteBuf;
import java.lang.reflect.Field;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.world.World;

public class EntityBalloon extends EntityFlying implements IEntityAdditionalSpawnData {
  public Entity owner;
  
  public Entity getOwner() {
    return this.owner;
  }
  
  public EntityBalloon(World world) {
    super(world);
  }
  
  public EntityBalloon(World world, Entity player, double x, double y, double z) {
    super(world);
    this.owner = player;
    func_70634_a(x, y, z);
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1.0D);
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
  }
  
  public void func_70071_h_() {
    this.field_70159_w = 0.0D;
    this.field_70181_x = 0.25D;
    this.field_70179_y = 0.0D;
    if (this.owner != null && func_70089_S() && this.owner.func_70068_e((Entity)this) > 300.0D)
      func_70106_y(); 
    if (this.field_70163_u >= 200.0D)
      func_70106_y(); 
    if (this.owner != null && func_70089_S()) {
      this.owner.field_70181_x = 0.25D;
      if (this.owner instanceof EntityPlayer) {
        EntityPlayer p = (EntityPlayer)this.owner;
        p.field_71075_bZ.field_75101_c = true;
        p.func_71016_p();
      } 
      if (this.owner instanceof EntityPlayerMP) {
        EntityPlayerMP p = (EntityPlayerMP)this.owner;
        try {
          Field f = p.field_71135_a.getClass().getDeclaredField("floatingTickCount");
          f.setAccessible(true);
          f.setInt(p.field_71135_a, 0);
        } catch (Exception e) {
          try {
            Field f = p.field_71135_a.getClass().getDeclaredField("field_147365_f");
            f.setAccessible(true);
            f.setInt(p.field_71135_a, 0);
          } catch (Exception exception) {}
        } 
        p.field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)p));
        ServerManager.addFreeze(p, p.field_70165_t, p.field_70163_u, p.field_70161_v);
      } 
    } else if (this.owner != null && !func_70089_S() && 
      this.owner instanceof EntityPlayerMP) {
      EntityPlayerMP p = (EntityPlayerMP)this.owner;
      ServerManager.removeFreeze(p);
      p.field_71075_bZ.field_75101_c = false;
      p.func_71016_p();
    } 
    super.func_70071_h_();
  }
  
  public void func_70106_y() {
    if (this.owner != null && 
      this.owner instanceof EntityPlayerMP) {
      EntityPlayerMP p = (EntityPlayerMP)this.owner;
      ServerManager.removeFreeze(p);
      p.field_71075_bZ.field_75101_c = false;
      p.func_71016_p();
    } 
    super.func_70106_y();
  }
  
  public void func_70037_a(NBTTagCompound compound) {
    if (compound.func_74764_b("owner"))
      for (Object obj : this.field_70170_p.field_72996_f) {
        Entity ent = (Entity)obj;
        if (FastUUID.toString(ent).equalsIgnoreCase(compound.func_74779_i("owner")))
          this.owner = ent; 
      }  
  }
  
  public void func_70014_b(NBTTagCompound compound) {
    if (this.owner != null)
      compound.func_74778_a("owner", FastUUID.toString(this.owner)); 
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    if (this.owner != null) {
      String[] uuid = FastUUID.toString(this.owner).split("-");
      buffer.writeInt(uuid.length);
      for (String str : uuid)
        ByteBufUtils.writeUTF8String(buffer, str); 
    } 
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (additionalData.isReadable()) {
      int size = additionalData.readInt();
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < size; i++)
        builder.append(ByteBufUtils.readUTF8String(additionalData) + "-"); 
      builder.deleteCharAt(builder.toString().length() - 1);
      for (Object obj : this.field_70170_p.field_72996_f) {
        Entity ent = (Entity)obj;
        if (FastUUID.toString(ent).equalsIgnoreCase(builder.toString()))
          this.owner = ent; 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityBalloon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */