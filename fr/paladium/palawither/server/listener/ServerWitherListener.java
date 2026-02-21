package fr.paladium.palawither.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palawither.common.utils.WitherUtils;
import fr.paladium.worldguardbridge.common.dto.flag.impl.StateFlag;
import fr.paladium.worldguardbridge.common.dto.flag.utils.FlagConstants;
import fr.paladium.worldguardbridge.common.manager.WGManager;
import fr.paladium.worldguardbridge.common.manager.utils.WGRegionList;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class ServerWitherListener {
  @SubscribeEvent
  public void onWitherDeath(LivingDeathEvent e) {
    EntityLivingBase entityLivingBase = e.entityLiving;
    if (!WitherUtils.isWither((Entity)entityLivingBase))
      return; 
    World worldObj = ((Entity)entityLivingBase).field_70170_p;
    int x = MathHelper.func_76128_c(((Entity)entityLivingBase).field_70165_t);
    int y = MathHelper.func_76128_c(((Entity)entityLivingBase).field_70163_u);
    int z = MathHelper.func_76128_c(((Entity)entityLivingBase).field_70161_v);
    boolean explode = false;
    for (int ox = -1; ox <= 1; ox++) {
      for (int oy = -1; oy <= 1; oy++) {
        for (int oz = -1; oz <= 1; oz++) {
          int ax = x + ox;
          int ay = y + oy;
          int az = z + oz;
          if (canDestroy((Entity)entityLivingBase, ax, ay, az))
            explode = (worldObj.func_147480_a(ax, ay, az, true) || explode); 
        } 
      } 
    } 
    if (explode)
      worldObj.func_72889_a(null, 1012, x, y, z, 0); 
  }
  
  private boolean canDestroy(@NonNull Entity entity, int x, int y, int z) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    World world = entity.field_70170_p;
    Block block = world.func_147439_a(x, y, z);
    if (block == Blocks.field_150357_h || block == Blocks.field_150384_bq || block == Blocks.field_150378_br || block == Blocks.field_150483_bI)
      return false; 
    if (!(block instanceof net.minecraft.block.BlockAnvil) && (block.isAir((IBlockAccess)world, x, y, z) || !block.canEntityDestroy((IBlockAccess)world, x, y, z, entity)))
      return false; 
    WGRegionList regionList = WGManager.inst().getRegionListAt(world, x, y, z);
    Optional<StateFlag> canBreakBlock = regionList.getFlag(FlagConstants.BLOCK_BREAK);
    Optional<StateFlag> canDragonBreakBlock = regionList.getFlag(FlagConstants.ENDERDRAGON_BLOCK_DAMAGE);
    if ((canBreakBlock.isPresent() && ((StateFlag)canBreakBlock.get()).isDenied()) || (canDragonBreakBlock.isPresent() && ((StateFlag)canDragonBreakBlock.get()).isDenied()))
      return false; 
    return (block.func_149688_o() != Material.field_151579_a);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\server\listener\ServerWitherListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */