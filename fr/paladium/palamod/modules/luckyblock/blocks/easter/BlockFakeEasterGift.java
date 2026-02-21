package fr.paladium.palamod.modules.luckyblock.blocks.easter;

import fr.paladium.palamod.modules.luckyblock.entity.easter.EntityBlackHole;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterGift;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFakeEasterGift extends BlockEasterGift {
  public BlockFakeEasterGift() {
    func_149663_c("fake_easter_gift");
  }
  
  public void func_149689_a(final World world, final int x, final int y, final int z, final EntityLivingBase entityLivingBase, ItemStack item) {
    if (!world.field_72995_K && 
      entityLivingBase instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)entityLivingBase;
      if (EventUtils.canInteract((EntityPlayer)player, x, y, z)) {
        TileEntityEasterGift tileEntity = (TileEntityEasterGift)world.func_147438_o(x, y, z);
        if (tileEntity != null) {
          final LuckyTask task = new LuckyTask();
          task
            
            .id = PaladiumScheduler.INSTANCE.runTaskLater(new Runnable() {
                public void run() {
                  EntityPlayerMP player = (EntityPlayerMP)entityLivingBase;
                  world.func_147468_f(x, y, z);
                  EntityBlackHole entity = new EntityBlackHole(world, player);
                  entity.func_70107_b(x, y, z);
                  world.func_72838_d((Entity)entity);
                  PaladiumScheduler.INSTANCE.cancelTask(task.id);
                }
              }35L).getTaskId();
        } 
      } 
    } 
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityEasterGift(true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\easter\BlockFakeEasterGift.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */