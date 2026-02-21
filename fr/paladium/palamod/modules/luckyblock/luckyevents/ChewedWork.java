package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ChewedWork extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.SNAKE_VENOM, 1));
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.PARROT_FEATHER, 1));
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.TURTLE_SCALES, 1));
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.GOAT_SHOE, 1));
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(
          Item.func_150898_a(Blocks.field_150381_bn), 1));
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.WITHER_SKULLFRAGMENT, 1));
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.MEDUSE_HOOK, 1));
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.PANDA_DROOL, 1));
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.SNAIL_SHELL, 1));
    PlayerUtils.sendMessage((EntityPlayer)player, "§aEt hop! Travail mâché. Tu peux craft le totem de l'endium.");
  }
  
  public String getName() {
    return "Travail maché";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 3000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "travail_mache";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\ChewedWork.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */