package fr.paladium.palamod.modules.luckyblock.blocks.easter;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterGift;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEasterGift extends Block implements ITooltipInformations {
  public BlockEasterGift() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("easter_gift");
    func_149658_d("palamod:easter_gift_item");
    func_149711_c(1.0F);
  }
  
  public void func_149689_a(final World world, final int x, final int y, final int z, final EntityLivingBase entityLivingBase, ItemStack item) {
    if (!world.field_72995_K && entityLivingBase instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)entityLivingBase;
      if (EventUtils.canInteract((EntityPlayer)player, x, y, z)) {
        TileEntityEasterGift tileEntity = (TileEntityEasterGift)world.func_147438_o(x, y, z);
        if (tileEntity != null) {
          final LuckyTask task = new LuckyTask();
          task
            
            .id = PaladiumScheduler.INSTANCE.runTaskLater(new Runnable() {
                public void run() {
                  int chance = ThreadLocalRandom.current().nextInt(1, 5);
                  ItemStack itemStack = null;
                  switch (chance) {
                    case 1:
                      itemStack = new ItemStack(ItemsRegister.CHOCOLATE_EGG, 8);
                      break;
                    case 2:
                      itemStack = new ItemStack(ItemsRegister.EASTER_BUNNY_CHESTPLATE);
                      break;
                    case 3:
                      itemStack = new ItemStack(ItemsRegister.EASTER_BUNNY_LEGGINGS);
                      break;
                    case 4:
                      itemStack = new ItemStack(ItemsRegister.EASTER_BUNNY_BOOTS);
                      break;
                  } 
                  if (itemStack != null)
                    PlayerUtils.dropItemStack((Entity)entityLivingBase, x, y, z, itemStack); 
                  world.func_147468_f(x, y, z);
                  PaladiumScheduler.INSTANCE.cancelTask(task.id);
                }
              }35L).getTaskId();
        } 
      } 
    } 
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderEasterGift;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityEasterGift(false);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Pose cet objet pour obtenir une récompense." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\easter\BlockEasterGift.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */