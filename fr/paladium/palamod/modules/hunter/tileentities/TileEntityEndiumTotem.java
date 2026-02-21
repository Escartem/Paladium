package fr.paladium.palamod.modules.hunter.tileentities;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.hunter.PHunter;
import fr.paladium.palamod.modules.hunter.networks.PacketRituel;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.util.FastUUID;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TileEntityEndiumTotem extends TileEntity {
  private boolean active;
  
  private int zombies;
  
  public boolean isActive() {
    return this.active;
  }
  
  public void setActive(boolean active) {
    this.active = active;
  }
  
  public int getZombies() {
    return this.zombies;
  }
  
  public void setZombies(int zombies) {
    this.zombies = zombies;
  }
  
  private List<UUID> players = new ArrayList<>();
  
  public boolean hasEndium;
  
  private boolean inAnimation;
  
  public List<UUID> getPlayers() {
    return this.players;
  }
  
  public void setPlayers(List<UUID> players) {
    this.players = players;
  }
  
  public void func_145845_h() {
    if (isActive()) {
      for (UUID uuid : this.players) {
        for (Object obj : this.field_145850_b.func_72872_a(EntityPlayerMP.class, 
            AxisAlignedBB.func_72330_a((this.field_145851_c - 10), (this.field_145848_d - 10), (this.field_145849_e - 10), (this.field_145851_c + 10), (this.field_145848_d + 10), (this.field_145849_e + 10)))) {
          EntityPlayer pl = (EntityPlayer)obj;
          if (pl.func_110124_au().equals(uuid) && 
            pl instanceof EntityPlayerMP) {
            EntityPlayerMP plmp = (EntityPlayerMP)pl;
            PHunter.network.sendTo((IMessage)new PacketRituel(this.zombies), plmp);
          } 
        } 
      } 
    } else {
      for (UUID uuid : this.players) {
        for (Object obj : this.field_145850_b.func_72872_a(EntityPlayerMP.class, 
            AxisAlignedBB.func_72330_a((this.field_145851_c - 10), (this.field_145848_d - 10), (this.field_145849_e - 10), (this.field_145851_c + 10), (this.field_145848_d + 10), (this.field_145849_e + 10)))) {
          EntityPlayer pl = (EntityPlayer)obj;
          if (pl.func_110124_au().equals(uuid) && 
            pl instanceof EntityPlayerMP) {
            EntityPlayerMP plmp = (EntityPlayerMP)pl;
            PHunter.network.sendTo((IMessage)new PacketRituel(-1), plmp);
          } 
        } 
      } 
    } 
    if (isActive() && !this.inAnimation && 
      this.zombies >= 25) {
      this.inAnimation = true;
      (new Thread(new Runnable() {
            public void run() {
              try {
                for (double d = 0.0D; d < 31.41592653589793D; d += 0.2D) {
                  Thread.sleep(30L);
                  EventUtils.spawnParticle(TileEntityEndiumTotem.this.field_145850_b, "instantSpell", TileEntityEndiumTotem.this.field_145851_c + 0.5D + Math.sin(d), TileEntityEndiumTotem.this.field_145848_d + d / 20.0D, TileEntityEndiumTotem.this.field_145849_e + 0.5D + 
                      Math.cos(d), 5, 0.0D);
                  EventUtils.spawnParticle(TileEntityEndiumTotem.this.field_145850_b, "reddust", TileEntityEndiumTotem.this.field_145851_c + 0.5D + Math.sin(d) + 3.0D, TileEntityEndiumTotem.this.field_145848_d + d / 20.0D + 1.0D, TileEntityEndiumTotem.this.field_145849_e + 0.5D + 
                      Math.cos(d), 5, 0.0D);
                  EventUtils.spawnParticle(TileEntityEndiumTotem.this.field_145850_b, "reddust", TileEntityEndiumTotem.this.field_145851_c + 0.5D + Math.sin(d) - 3.0D, TileEntityEndiumTotem.this.field_145848_d + d / 20.0D + 1.0D, TileEntityEndiumTotem.this.field_145849_e + 0.5D + 
                      Math.cos(d), 5, 0.0D);
                  EventUtils.spawnParticle(TileEntityEndiumTotem.this.field_145850_b, "reddust", TileEntityEndiumTotem.this.field_145851_c + 0.5D + Math.sin(d), TileEntityEndiumTotem.this.field_145848_d + d / 20.0D + 1.0D, TileEntityEndiumTotem.this.field_145849_e + 0.5D + 
                      Math.cos(d) + 3.0D, 5, 0.0D);
                  EventUtils.spawnParticle(TileEntityEndiumTotem.this.field_145850_b, "reddust", TileEntityEndiumTotem.this.field_145851_c + 0.5D + Math.sin(d), TileEntityEndiumTotem.this.field_145848_d + d / 20.0D + 1.0D, TileEntityEndiumTotem.this.field_145849_e + 
                      Math.cos(d) - 3.0D, 5, 0.0D);
                } 
                for (int i = 0; i < 10; i++) {
                  Thread.sleep(200L);
                  ((WorldServer)TileEntityEndiumTotem.this.field_145850_b).func_72942_c((Entity)new EntityLightningBolt(TileEntityEndiumTotem.this.field_145850_b, (TileEntityEndiumTotem.this.field_145851_c + 
                        TileEntityEndiumTotem.this.field_145850_b.field_73012_v.nextInt(8) - 4), TileEntityEndiumTotem.this.field_145848_d, (TileEntityEndiumTotem.this.field_145849_e + TileEntityEndiumTotem.this.field_145850_b.field_73012_v.nextInt(8) - 4)));
                } 
                AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a((TileEntityEndiumTotem.this.field_145851_c - 10), (TileEntityEndiumTotem.this.field_145848_d - 10), (TileEntityEndiumTotem.this.field_145849_e - 10), (TileEntityEndiumTotem.this.field_145851_c + 10), (TileEntityEndiumTotem.this.field_145848_d + 10), (TileEntityEndiumTotem.this.field_145849_e + 10));
                List players = TileEntityEndiumTotem.this.field_145850_b.func_72872_a(EntityLivingBase.class, scanAbove);
                for (Object obj : players) {
                  if (obj instanceof EntityLivingBase) {
                    EntityLivingBase pl = (EntityLivingBase)obj;
                    pl.field_70160_al = true;
                    float str = 0.6F;
                    pl.field_70159_w += (pl.field_70165_t - TileEntityEndiumTotem.this.field_145851_c > 0.0D) ? str : -str;
                    pl.field_70181_x += str;
                    pl.field_70179_y += (pl.field_70161_v - TileEntityEndiumTotem.this.field_145849_e > 0.0D) ? str : -str;
                    if (obj instanceof EntityPlayerMP) {
                      EntityPlayerMP p = (EntityPlayerMP)pl;
                      p.func_70606_j(0.5F);
                      p.field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)pl));
                    } 
                  } 
                } 
                if (TileEntityEndiumTotem.this.isActive())
                  TileEntityEndiumTotem.this.hasEndium = true; 
              } catch (Exception e) {
                e.printStackTrace();
              } 
            }
          })).start();
    } 
    if (this.hasEndium)
      EventUtils.spawnParticle(this.field_145850_b, "reddust", this.field_145851_c + 0.5D, this.field_145848_d + 1.5D, this.field_145849_e + 0.5D, 5, 0.3D); 
    super.func_145845_h();
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("active"))
      this.active = compound.func_74767_n("active"); 
    if (compound.func_74764_b("zombies"))
      this.zombies = compound.func_74762_e("zombies"); 
    if (compound.func_74764_b("players")) {
      NBTTagList list = compound.func_150295_c("players", 8);
      for (int i = 0; i < list.func_74745_c(); i++)
        this.players.add(FastUUID.parseUUID(list.func_150307_f(i))); 
    } 
    if (compound.func_74764_b("hasEndium"))
      this.hasEndium = compound.func_74767_n("hasEndium"); 
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    compound.func_74757_a("active", this.active);
    compound.func_74768_a("zombies", this.zombies);
    NBTTagList playersNbt = new NBTTagList();
    for (UUID uuid : this.players)
      playersNbt.func_74742_a((NBTBase)new NBTTagString(FastUUID.toString(uuid))); 
    compound.func_74782_a("players", (NBTBase)playersNbt);
    compound.func_74757_a("hasEndium", this.hasEndium);
    super.func_145841_b(compound);
  }
  
  public void addPlayer(EntityPlayer player) {
    if (player == null)
      return; 
    if (!this.players.contains(player.func_110124_au()))
      this.players.add(player.func_110124_au()); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\tileentities\TileEntityEndiumTotem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */