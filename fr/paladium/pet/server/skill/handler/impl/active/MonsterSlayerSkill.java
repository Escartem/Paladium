package fr.paladium.pet.server.skill.handler.impl.active;

import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.config.global.GlobalConfig;
import fr.paladium.pet.server.skill.handler.ASkillHandler;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class MonsterSlayerSkill extends ASkillHandler {
  public static final String ID = "monster_slayer";
  
  private static final float DAMAGE = 18.0F;
  
  public MonsterSlayerSkill() {
    super("monster_slayer");
  }
  
  public boolean perform(EntityPlayerMP player, PetPlayer pet) {
    double radius = getSkill().getPersonalValue(pet);
    if (radius <= 0.0D)
      return false; 
    World world = player.field_70170_p;
    AxisAlignedBB axisAlignedBB = AxisAlignedBB.func_72330_a(player.field_70165_t - radius, player.field_70163_u - radius, player.field_70161_v - radius, player.field_70165_t + radius, player.field_70163_u + radius, player.field_70161_v + radius);
    attackMobs(world, player, axisAlignedBB);
    return true;
  }
  
  private void attackMobs(World world, EntityPlayerMP player, AxisAlignedBB axisAlignedBB) {
    GlobalConfig config = GlobalConfig.get();
    List<EntityLivingBase> livings = world.func_72872_a(EntityLivingBase.class, axisAlignedBB);
    for (EntityLivingBase living : livings) {
      if (living instanceof EntityPlayerMP)
        continue; 
      String name = living.getClass().getSimpleName();
      if (!config.getWhitelistedDamageEntities().contains(name))
        continue; 
      living.func_70097_a(DamageSource.func_76365_a((EntityPlayer)player), 18.0F);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\impl\active\MonsterSlayerSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */