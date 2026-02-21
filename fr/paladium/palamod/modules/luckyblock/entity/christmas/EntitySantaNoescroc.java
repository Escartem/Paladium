package fr.paladium.palamod.modules.luckyblock.entity.christmas;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.luckyblock.network.christmas.PacketChristmasSantaNoescroc;
import fr.paladium.palamod.modules.paladium.network.OpenGuiPacket;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class EntitySantaNoescroc extends EntityAnimal {
  public EntitySantaNoescroc(World world) {
    super(world);
    func_70105_a(0.6F, 1.8F);
    this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
  }
  
  public EntitySantaNoescroc(World world, double x, double y, double z) {
    this(world);
    func_70634_a(x, y, z);
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
  }
  
  public EntityAgeable func_90011_a(EntityAgeable p_90011_1_) {
    return null;
  }
  
  public boolean func_70085_c(EntityPlayer player) {
    if (!player.field_70170_p.field_72995_K) {
      PalaMod.getNetwork().sendTo((IMessage)new OpenGuiPacket(PGuiRegistry.GUI_CHRISTMAS_SANTA_NOESCROC_TRADE, true), (EntityPlayerMP)player);
      PalaMod.getNetwork().sendTo((IMessage)new PacketChristmasSantaNoescroc(func_145782_y()), (EntityPlayerMP)player);
    } 
    return super.func_70085_c(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\christmas\EntitySantaNoescroc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */