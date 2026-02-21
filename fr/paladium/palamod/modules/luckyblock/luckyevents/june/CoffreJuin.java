package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityDigicode;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;

public class CoffreJuin extends ALuckyEvent {
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    final int posX = (int)player.field_70165_t;
    final int posY = (int)player.field_70163_u;
    final int posZ = (int)player.field_70161_v;
    EventUtils.spawnStructure(player.field_70170_p, posX + 4, posY - 1, posZ, 7, 1, 7, BlocksRegister.TIMED_BEDROCK, player);
    EventUtils.spawnStructure(player.field_70170_p, posX + 4, posY + 2, posZ, 5, 5, 5, Blocks.field_150350_a, player);
    EventUtils.spawnStructure(player.field_70170_p, posX + 7, posY + 3, posZ, 1, 6, 7, BlocksRegister.TIMED_BEDROCK, player);
    EventUtils.spawnStructure(player.field_70170_p, posX + 4, posY + 3, posZ + 3, 7, 6, 1, BlocksRegister.TIMED_BEDROCK, player);
    EventUtils.spawnStructure(player.field_70170_p, posX + 4, posY + 3, posZ - 3, 7, 6, 1, BlocksRegister.TIMED_BEDROCK, player);
    EventUtils.spawnStructure(player.field_70170_p, posX + 4, posY + 5, posZ, 7, 1, 7, BlocksRegister.TIMED_BEDROCK, player);
    EventUtils.spawnStructure(player.field_70170_p, posX + 1, posY + 4, posZ, 1, 4, 7, BlocksRegister.TIMED_BEDROCK, player);
    EventUtils.spawnStructure(player.field_70170_p, posX + 1, posY + 1, posZ - 2, 1, 1, 3, BlocksRegister.TIMED_BEDROCK, player);
    EventUtils.spawnStructure(player.field_70170_p, posX + 1, posY, posZ, 1, 1, 7, BlocksRegister.TIMED_BEDROCK, player);
    EventUtils.spawnStructure(player.field_70170_p, posX + 1, posY + 1, posZ + 1, 1, 1, 3, BlocksRegister.DIGICODE, player);
    TileEntity tile = player.field_70170_p.func_147438_o(posX + 1, posY + 1, posZ);
    if (tile != null && tile instanceof TileEntityDigicode) {
      TileEntityDigicode digicodeTile = (TileEntityDigicode)tile;
      digicodeTile.setMaster();
      digicodeTile.setCode(1);
      digicodeTile.forceDiplayNumber(1);
    } 
    tile = player.field_70170_p.func_147438_o(posX + 1, posY + 1, posZ + 1);
    if (tile != null && tile instanceof TileEntityDigicode) {
      TileEntityDigicode digicodeTile = (TileEntityDigicode)tile;
      digicodeTile.setCode(2);
      digicodeTile.forceDiplayNumber(1);
    } 
    tile = player.field_70170_p.func_147438_o(posX + 1, posY + 1, posZ + 2);
    if (tile != null && tile instanceof TileEntityDigicode) {
      TileEntityDigicode digicodeTile = (TileEntityDigicode)tile;
      digicodeTile.setCode(8);
      digicodeTile.forceDiplayNumber(1);
    } 
    final LuckyTask task = new LuckyTask();
    task
      
      .id = PaladiumScheduler.INSTANCE.runTaskAsyncLater(new Runnable() {
          public void run() {
            try {
              boolean isOpened = false;
              int cpt = 0;
              while (cpt != 600) {
                cpt++;
                Thread.sleep(500L);
                if (((TileEntityDigicode)player.field_70170_p.func_147438_o(posX + 1, posY + 1, posZ)).generatePower()) {
                  cpt = 600;
                  isOpened = true;
                } 
              } 
              if (isOpened) {
                EventUtils.spawnStructure(player.field_70170_p, posX + 1, posY, posZ - 2, 1, 1, 1, Blocks.field_150350_a, player);
                EventUtils.spawnStructure(player.field_70170_p, posX + 1, posY + 1, posZ - 2, 1, 1, 1, Blocks.field_150350_a, player);
                player.field_70170_p.func_147449_b(posX + 4, posY, posZ, (Block)Blocks.field_150486_ae);
                TileEntityChest chest = new TileEntityChest();
                chest.func_70299_a(0, new ItemStack(ItemsRegister.TRUMPET));
                chest.func_70299_a(1, new ItemStack(BlocksRegister.BRIGHT_SOUND, 5));
                chest.func_70299_a(2, new ItemStack(BlocksRegister.BLOCK_PALADIUM, 3));
                chest.func_70299_a(3, new ItemStack(Blocks.field_150340_R, 10));
                chest.func_70299_a(4, new ItemStack(BlocksRegister.BLOCK_GPALADIUM, 2));
                player.func_130014_f_().func_147455_a(posX + 4, posY, posZ, (TileEntity)chest);
                Thread.sleep(60000L);
              } 
              EventUtils.spawnStructure(player.field_70170_p, posX + 4, posY - 1, posZ, 7, 1, 7, Blocks.field_150350_a, player);
              EventUtils.spawnStructure(player.field_70170_p, posX + 4, posY + 2, posZ, 5, 5, 5, Blocks.field_150350_a, player);
              EventUtils.spawnStructure(player.field_70170_p, posX + 7, posY + 3, posZ, 1, 6, 7, Blocks.field_150350_a, player);
              EventUtils.spawnStructure(player.field_70170_p, posX + 4, posY + 3, posZ + 3, 7, 6, 1, Blocks.field_150350_a, player);
              EventUtils.spawnStructure(player.field_70170_p, posX + 4, posY + 3, posZ - 3, 7, 6, 1, Blocks.field_150350_a, player);
              EventUtils.spawnStructure(player.field_70170_p, posX + 4, posY + 5, posZ, 7, 1, 7, Blocks.field_150350_a, player);
              EventUtils.spawnStructure(player.field_70170_p, posX + 1, posY + 4, posZ, 1, 4, 7, Blocks.field_150350_a, player);
              EventUtils.spawnStructure(player.field_70170_p, posX + 1, posY + 1, posZ - 2, 1, 1, 3, Blocks.field_150350_a, player);
              EventUtils.spawnStructure(player.field_70170_p, posX + 1, posY, posZ, 1, 1, 7, Blocks.field_150350_a, player);
              EventUtils.spawnStructure(player.field_70170_p, posX + 1, posY + 1, posZ + 1, 1, 1, 3, Blocks.field_150350_a, player);
              if (!isOpened) {
                Thread.sleep(500L);
                player.field_70170_p.func_72876_a((Entity)player, (posX + 5), posY, posZ, 10.0F, true);
              } 
            } catch (InterruptedException interruptedException) {}
            PaladiumScheduler.INSTANCE.cancelTask(task.id);
          }
        }0L).getTaskId();
  }
  
  public String getName() {
    return "Coffre de juin";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 180;
  }
  
  public String getTexture() {
    return "june/coffre_juin";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\CoffreJuin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */