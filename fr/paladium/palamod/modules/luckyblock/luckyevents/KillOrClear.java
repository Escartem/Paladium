package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

public class KillOrClear extends ALuckyEvent {
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    UsersManager.getKillOrClear().add(player);
    PlayerUtils.sendMessage((EntityPlayer)player, "§cVeuillez choisir entre vous faire §ekill §cou §eclear§c, vous avez 20 secondes !");
    MinecraftServer.func_71276_C().func_71187_D().func_71556_a((ICommandSender)MinecraftServer.func_71276_C(), "/tellraw " + player
        .func_70005_c_() + " [\"\",{\"text\":\"-> KILL\",\"bold\":true,\"underlined\":true,\"color\":\"red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"kill\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":[\"\",{\"text\":\"Vous allez vous faire tuer\",\"italic\":true,\"color\":\"red\"}]}}]");
    MinecraftServer.func_71276_C().func_71187_D().func_71556_a((ICommandSender)MinecraftServer.func_71276_C(), "/tellraw " + player
        .func_70005_c_() + " [\"\",{\"text\":\"-> CLEAR\",\"bold\":true,\"underlined\":true,\"color\":\"blue\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"clear\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":[\"\",{\"text\":\"Vous allez vous faire clear\",\"italic\":true,\"color\":\"blue\"}]}}]");
    (new Thread(new Runnable() {
          public void run() {
            try {
              Thread.sleep(20000L);
              if (UsersManager.getKillOrClear().contains(player)) {
                for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
                  ItemStack item = player.field_71071_by.field_70462_a[i];
                  if (item != null && 
                    item.func_77973_b() != Item.func_150898_a((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK) && item
                    .func_77973_b() != Item.func_150898_a((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK))
                    player.field_71071_by.field_70462_a[i] = null; 
                } 
                player.func_70606_j(0.0F);
                player.func_70106_y();
              } 
            } catch (InterruptedException e) {
              e.printStackTrace();
            } 
          }
        })).start();
  }
  
  public String getName() {
    return "La mort ou...";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 500;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "kill_or_clear";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\KillOrClear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */