package fr.paladium.palamod.modules.luckyblock.entity.easter;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.network.easter.PacketColoredParticle;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityGoldenChicken extends EntityChicken {
  private int lifeTimer = 0;
  
  private int lifeDuration = 0;
  
  private int lifeMaxDuration;
  
  public int getLifeMaxDuration() {
    return this.lifeMaxDuration;
  }
  
  public void setLifeMaxDuration(int lifeMaxDuration) {
    this.lifeMaxDuration = lifeMaxDuration;
  }
  
  public EntityGoldenChicken(World world) {
    super(world);
    this.lifeMaxDuration = 3;
    func_70105_a(2.0F, 2.0F);
    func_98055_j(2.0F);
    func_94058_c("§b");
  }
  
  public void func_70636_d() {
    this.field_70887_j = Integer.MAX_VALUE;
    super.func_70636_d();
    if (!this.field_70170_p.field_72995_K) {
      this.lifeTimer++;
      if (this.lifeTimer % 10 == 0) {
        int radius = 20;
        for (Object o : this.field_70170_p.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(this.field_70165_t - radius, 0.0D, this.field_70161_v - radius, this.field_70165_t + radius, 255.0D, this.field_70161_v + radius))) {
          if (o instanceof EntityPlayerMP) {
            EntityPlayerMP p = (EntityPlayerMP)o;
            PalaMod.getNetwork().sendTo((IMessage)new PacketColoredParticle(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.83137256F, 0.6862745F, 0.21568628F, 5.0F), p);
          } 
        } 
      } 
      if (this.lifeTimer >= 40) {
        this.lifeDuration++;
        ItemStack itemStack = new ItemStack(ItemsRegister.GOLDEN_EGG, 1, 0);
        PlayerUtils.dropItemStack((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, itemStack);
        if (this.lifeDuration >= this.lifeMaxDuration)
          func_70106_y(); 
        this.lifeTimer = 0;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\easter\EntityGoldenChicken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */