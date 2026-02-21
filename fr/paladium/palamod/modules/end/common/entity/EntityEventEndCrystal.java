package fr.paladium.palamod.modules.end.common.entity;

import fr.paladium.helios.module.actionbar.ModuleActionBar;
import fr.paladium.helios.module.title.ModuleTitle;
import fr.paladium.helios.module.title.utils.MinecraftTitle;
import fr.paladium.palamod.modules.end.server.manager.EndManager;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityEventEndCrystal extends EntityEnderCrystal {
  public int field_70261_a;
  
  public long lifeTime;
  
  public int getInnerRotation() {
    return this.field_70261_a;
  }
  
  public long getLifeTime() {
    return this.lifeTime;
  }
  
  public EntityEventEndCrystal(World world) {
    super(world);
    this.field_70260_b = 1000;
    this.lifeTime = System.currentTimeMillis() + 600000L;
    this.field_98038_p = true;
  }
  
  public EntityEventEndCrystal(World world, double x, double y, double z) {
    this(world);
    func_70107_b(x, y, z);
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    if (this.field_70170_p.field_72995_K)
      return; 
    if (!EndManager.getInstance().getCrystals().contains(this) || System.currentTimeMillis() > this.lifeTime)
      func_70106_y(); 
  }
  
  public boolean func_70097_a(DamageSource source, float damage) {
    if (func_85032_ar())
      return false; 
    if (!this.field_70128_L && !this.field_70170_p.field_72995_K) {
      this.field_70260_b = (int)(this.field_70260_b - damage);
      if (this.field_70260_b <= 0) {
        for (Object playerObj : this.field_70170_p.func_72872_a(EntityPlayerMP.class, this.field_70121_D.func_72314_b(15.0D, 15.0D, 15.0D))) {
          if (!(playerObj instanceof EntityPlayerMP))
            continue; 
          EntityPlayerMP player = (EntityPlayerMP)playerObj;
          S29PacketSoundEffect packet = new S29PacketSoundEffect("random.explode", player.field_70165_t, player.field_70163_u, player.field_70161_v, 10.0F, 0.1F);
          player.field_71135_a.func_147359_a((Packet)packet);
          ModuleTitle.getInstance().sendTitle(new MinecraftTitle("§dEvent END", "§cPilier détruit !", 500L, 3000L, 500L), player);
        } 
        func_70106_y();
        EndManager.getInstance().checkCrystals();
      } else if (source.func_76364_f() instanceof EntityPlayerMP) {
        ModuleActionBar.getInstance().sendActionBar((EntityPlayerMP)source.func_76364_f(), "§c" + this.field_70260_b + " HP", 3000L);
      } 
    } 
    return true;
  }
  
  protected void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74768_a("innerRotation", this.field_70261_a);
    nbt.func_74772_a("lifeTime", this.lifeTime);
  }
  
  protected void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    this.field_70261_a = nbt.func_74762_e("innerRotation");
    this.lifeTime = nbt.func_74763_f("lifeTime");
  }
  
  public int hashCode() {
    return func_110124_au().hashCode();
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (!super.equals(obj) || getClass() != obj.getClass())
      return false; 
    EntityEventEndCrystal other = (EntityEventEndCrystal)obj;
    return func_110124_au().equals(other.func_110124_au());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\common\entity\EntityEventEndCrystal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */