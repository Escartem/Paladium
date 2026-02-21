package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.welsymc.guardiangolem.common.entities.EntityGolem;
import fr.welsymc.guardiangolem.common.init.BlockInit;
import fr.welsymc.guardiangolem.common.init.ItemInit;
import fr.welsymc.guardiangolem.common.items.ItemGuardianStone;
import fr.welsymc.guardiangolem.common.tileentities.TileEntityGuardianBox;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Jackpot extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (EventUtils.canInteract((EntityPlayer)player, x, y, z)) {
      World world = player.field_70170_p;
      for (int xi = -1; xi <= 1; xi++) {
        for (int yi = 0; yi <= 2; yi++) {
          for (int zi = -1; zi <= 1; zi++)
            world.func_147449_b(x + xi, y + yi, z + zi, BlockInit.GUARDIAN_BOX_FRAME); 
        } 
      } 
      world.func_147449_b(x, y + 1, z, (Block)BlocksRegister.FLUIDS_ANGELICWATER);
      world.func_147449_b(x + 1, y + 1, z, BlockInit.GUARDIAN_BOX_BLOCK);
      TileEntity titleEntity = world.func_147438_o(x + 1, y + 1, z);
      if (titleEntity instanceof TileEntityGuardianBox)
        for (int i = 0; i <= 2; i++) {
          EntityGolem golem = new EntityGolem(world);
          golem.setOwner(player.func_110124_au().toString());
          golem.addUpgrade(3);
          golem.addUpgrade(30);
          golem.setLevel(12);
          ItemStack stone = ItemGuardianStone.getGuardianStone(golem);
          ItemStack itemStack = new ItemStack(ItemInit.MINI_GOLEM);
          itemStack.func_77982_d(stone.func_77978_p());
          ((TileEntityGuardianBox)titleEntity).func_70299_a(i, itemStack);
        }  
    } 
  }
  
  public String getName() {
    return "Jackpot";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 1300;
  }
  
  public String getTexture() {
    return "jackpot";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Jackpot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */