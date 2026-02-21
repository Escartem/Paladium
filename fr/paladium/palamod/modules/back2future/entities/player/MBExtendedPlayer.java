package fr.paladium.palamod.modules.back2future.entities.player;

import fr.paladium.palamod.modules.back2future.core.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class MBExtendedPlayer implements IExtendedEntityProperties {
  public static final String EXT_PROP_NAME = "MBExtendedPlayer";
  
  public EntityPlayer player;
  
  public int limboTime;
  
  public int preLimbo;
  
  public int limbo;
  
  public int knockdownTime;
  
  public int preKnockdown;
  
  public int knockdown;
  
  public static final int LIMBO_WATCHER = 27;
  
  public static final int KNOCKDOWN_WATCHER = 28;
  
  public MBExtendedPlayer(EntityPlayer player) {
    this.player = player;
    this.limbo = 0;
    this.preLimbo = 0;
    this.limboTime = 0;
    player.func_70096_w().func_75682_a(27, Integer.valueOf(0));
    player.func_70096_w().func_75682_a(28, Integer.valueOf(0));
    this.knockdownTime = 0;
  }
  
  public static final void register(EntityPlayer player) {
    player.registerExtendedProperties("MBExtendedPlayer", new MBExtendedPlayer(player));
  }
  
  public static final MBExtendedPlayer get(EntityPlayer player) {
    return (MBExtendedPlayer)player.getExtendedProperties("MBExtendedPlayer");
  }
  
  public void onUpdate() {
    if ((this.player.func_130014_f_()).field_72995_K) {
      this.limbo = this.player.func_70096_w().func_75679_c(27);
      if (this.preLimbo != this.limbo && this.player == (Minecraft.func_71410_x()).field_71439_g)
        if (this.limbo == 1) {
          ClientProxy.sobelShader();
          Minecraft.func_71410_x().func_147118_V().func_147690_c();
          this.player.func_85030_a("palamod:limbo", 20.0F, 1.0F);
        } else {
          ClientProxy.clearShader();
        }  
      this.preLimbo = this.limbo;
      this.knockdown = this.player.func_70096_w().func_75679_c(28);
      if (this.knockdown == 1 && this.player == (Minecraft.func_71410_x()).field_71439_g) {
        this.player.func_70110_aj();
        this.player.func_70016_h(0.0D, 0.0D, 0.0D);
        (Minecraft.func_71410_x()).field_71474_y.field_74320_O = 1;
      } else if (this.knockdown == 0 && this.preKnockdown != this.knockdown && this.player == 
        (Minecraft.func_71410_x()).field_71439_g) {
        (Minecraft.func_71410_x()).field_71474_y.field_74320_O = 0;
      } 
      this.preKnockdown = this.knockdown;
    } else {
      this.player.func_70096_w().func_75692_b(27, Integer.valueOf((this.limboTime > 0) ? 1 : 0));
      this.player.func_70096_w().func_75692_b(28, Integer.valueOf((this.knockdownTime > 0) ? 1 : 0));
      this.limbo = (this.limboTime > 0) ? 1 : 0;
    } 
    this.knockdownTime -= (this.knockdownTime > 0) ? 1 : 0;
    this.limboTime -= (this.limboTime > 0) ? 1 : 0;
  }
  
  public void saveNBTData(NBTTagCompound compound) {}
  
  public void loadNBTData(NBTTagCompound compound) {}
  
  public void init(Entity entity, World world) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\player\MBExtendedPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */