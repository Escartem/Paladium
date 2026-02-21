package fr.paladium.palamod.modules.back2future.core.utils;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class TargetUtils {
  private static Random random;
  
  public static void tellPlayer(String msg) {
    try {
      (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.AQUA + "MB: " + EnumChatFormatting.GOLD + msg));
    } catch (Exception exception) {}
  }
  
  public static void tellPlayersInList(List<EntityPlayer> list, String msg) {
    try {
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i) instanceof EntityPlayer) {
          EntityPlayer player = list.get(i);
          player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.AQUA + "MB: " + EnumChatFormatting.GOLD + msg));
        } 
      } 
    } catch (Exception exception) {}
  }
  
  public static void betaMsg(Entity entity) {
    if (entity.field_70170_p.field_72995_K && entity.field_70173_aa == 1)
      tellPlayer("This monster is still a work in progress"); 
  }
  
  public static int getRanNum(int min, int max) {
    if (random == null)
      random = new Random(); 
    if (min >= max)
      return random.nextInt(min - max + 1) + max; 
    if (min == max)
      return min; 
    return random.nextInt(max - min + 1) + min;
  }
  
  public static boolean playerColided(Entity ent) {
    List list = ent.field_70170_p.func_72872_a(EntityPlayer.class, ent.func_70046_E());
    return list.isEmpty();
  }
  
  public static final boolean isTargetInFrontOf(Entity seeker, Entity target, float fov) {
    double dx = target.field_70165_t - seeker.field_70165_t;
    double dz;
    for (dz = target.field_70161_v - seeker.field_70161_v; dx * dx + dz * dz < 1.0E-4D; 
      dz = (Math.random() - Math.random()) * 0.01D)
      dx = (Math.random() - Math.random()) * 0.01D; 
    while (seeker.field_70177_z > 360.0F)
      seeker.field_70177_z -= 360.0F; 
    while (seeker.field_70177_z < -360.0F)
      seeker.field_70177_z += 360.0F; 
    float yaw = (float)(Math.atan2(dz, dx) * 180.0D / Math.PI) - seeker.field_70177_z;
    yaw -= 90.0F;
    while (yaw < -180.0F)
      yaw += 360.0F; 
    while (yaw >= 180.0F)
      yaw -= 360.0F; 
    return (yaw < fov && yaw > -fov);
  }
  
  public static final List getList(Entity entity, int width, int height) {
    return entity.field_70170_p.func_72839_b(entity, entity
        .func_70046_E().func_72314_b(width, height, width));
  }
  
  public static final List getSpecificList(Entity entity, Class targetClass, int width, int height) {
    return entity.field_70170_p.func_72872_a(targetClass, entity
        .func_70046_E().func_72314_b(width, height, width));
  }
  
  public static EntityPlayer findRandomVisablePlayer(Entity entity, int width, int height) {
    List<EntityPlayer> players = entity.field_70170_p.func_72872_a(EntityPlayer.class, entity
        .func_70046_E().func_72314_b(width, height, width));
    if (players.size() > 0) {
      if (random == null)
        random = new Random(); 
      return ((EntityLivingBase)entity).func_70685_l((Entity)players.get(random.nextInt(players.size()))) ? players
        .get(random.nextInt(players.size())) : null;
    } 
    return null;
  }
  
  public static EntityPlayer findNearestVisablePlayer(Entity entity, int dist) {
    EntityPlayer entityplayer = entity.field_70170_p.func_72890_a(entity, dist);
    return (entityplayer != null && ((EntityLivingBase)entity).func_70685_l((Entity)entityplayer)) ? entityplayer : null;
  }
  
  public static void addItemToInventory(EntityPlayer player, ItemStack stack) {
    if (!player.field_71071_by.func_70441_a(stack))
      player.func_71019_a(stack, false); 
  }
  
  public static boolean isBlockPresentPos(World worldIn, Block blockTarget, BlockPos pos1, BlockPos pos2) {
    List<BlockPos> blocks = Lists.newArrayList(BlockPos.getAllInBox(pos1, pos2));
    for (BlockPos pos : blocks) {
      if (worldIn.func_147439_a(pos.getX(), pos.getY(), pos.getZ()) == blockTarget)
        return true; 
    } 
    return false;
  }
  
  public static void dropLoot(Entity ent, String[] lootList) {
    try {
      if (lootList.length > 0)
        for (String string : lootList) {
          String[] split = string.split("\\|");
          String[] item = split[2].split("\\:");
          int qty = Integer.parseInt(split[1]);
          int chance = Integer.parseInt(split[0]);
          chance = (chance > 100) ? 100 : chance;
          int roll = getRanNum(1, 100);
          if (roll <= chance)
            ent.func_145779_a(GameRegistry.findItem(item[0], item[1]), qty); 
        }  
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public static void dropExp(Entity ent, int ammount) {
    while (ammount > 0) {
      int j = EntityXPOrb.func_70527_a(ammount);
      ammount -= j;
      ent.field_70170_p
        .func_72838_d((Entity)new EntityXPOrb(ent.field_70170_p, ent.field_70165_t, ent.field_70163_u, ent.field_70161_v, j));
    } 
  }
  
  public static int distToFloor(Entity ent, double x, double y, double z) {
    BlockPos blockpos = new BlockPos(x, y, z);
    int count = 0;
    while (!ent.field_70170_p.func_147439_a((int)x, (int)y, (int)z).func_149688_o().func_76230_c()) {
      blockpos = blockpos.down();
      count++;
    } 
    return count;
  }
  
  public static boolean canPosBeSeen(Entity ent, double x, double y, double z) {
    return (ent.field_70170_p.func_72933_a(Vec3.func_72443_a(ent.field_70165_t, ent.field_70163_u, ent.field_70161_v), 
        Vec3.func_72443_a(x, y, z)) == null);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\cor\\utils\TargetUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */