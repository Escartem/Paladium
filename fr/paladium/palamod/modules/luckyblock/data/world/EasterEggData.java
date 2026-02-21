package fr.paladium.palamod.modules.luckyblock.data.world;

import fr.paladium.common.CommonModule;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class EasterEggData {
  public void setCode(String code) {
    this.code = code;
  }
  
  public void setUnlockerUsername(String unlockerUsername) {
    this.unlockerUsername = unlockerUsername;
  }
  
  public String getCode() {
    return this.code;
  }
  
  public String getUnlockerUsername() {
    return this.unlockerUsername;
  }
  
  private String code = null;
  
  private String unlockerUsername = null;
  
  public void giveReward(EntityPlayerMP player, String input) {
    String serverName = CommonModule.getInstance().getConfig().getServerName().toLowerCase();
    ItemStack held = player.func_70694_bm();
    if (held == null || held.func_77973_b() == null || !held.func_77973_b().equals(ItemsRegister.EASTER_EGG)) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cVous devez tenir un §6Easter Egg §cpour utiliser un code." });
      return;
    } 
    if (this.code == null) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cAucun code n'est défini sur le serveur §6" + serverName + " §cVeuillez contacter un membre du staff si vous pensez qu'il s'agit d'une erreur." });
      return;
    } 
    if (this.unlockerUsername != null) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cCe code a déjà été utilisé par §6" + this.unlockerUsername + "§c." });
      return;
    } 
    if (!this.code.equalsIgnoreCase(input)) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cLe code que vous avez rentré est incorrect." });
      return;
    } 
    this.unlockerUsername = player.func_70005_c_();
    List<ItemStack> rewards = getRewards(serverName);
    if (rewards.isEmpty()) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cAucune récompense n'est définie pour le serveur §6" + serverName + " §cVeuillez contacter un membre du staff si vous pensez qu'il s'agit d'une erreur." });
      return;
    } 
    for (ItemStack reward : rewards)
      InventoryUtils.giveOrDropitems((EntityPlayer)player, reward); 
    MonthlyUtils.broadcast(new String[] { "§6" + player.func_70005_c_() + " §7a débloqué le code §6EasterEgg §7sur le serveur §6" + serverName + "§7." });
    MonthlyUtils.decrementCurrentItem((EntityPlayer)player, held);
    EasterEggDataManager.getInstance().saveConfig();
  }
  
  public List<ItemStack> getRewards(String serverName) {
    List<ItemStack> rewards = new ArrayList<>();
    switch (serverName) {
      case "egopolis":
      case "ptr":
        rewards.add(new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1));
        rewards.add(new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1));
        rewards.add(new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1));
        rewards.add(new ItemStack((Block)BlocksRegister.BLACK_LUCKY_BLOCK, 4));
        break;
      case "xanoth":
        rewards.add(new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1));
        rewards.add(new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1));
        rewards.add(new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1));
        rewards.add(new ItemStack(BlocksRegister.ENDIUM_BLOCK, 1));
        break;
      case "kilmordra":
        rewards.add(new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1));
        rewards.add(new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1));
        rewards.add(new ItemStack(ItemsRegister.POTION_JOB_TEN_MULTIPLIER, 1));
        break;
      case "runegard":
        rewards.add(new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1));
        rewards.add(new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1));
        rewards.add(new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1));
        rewards.add(new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1));
        rewards.add(new ItemStack(BlocksRegister.BLOCK_GPALADIUM, 5));
        break;
      case "aeloria":
        rewards.add(new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1));
        rewards.add(new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1));
        rewards.add(new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1));
        rewards.add(new ItemStack((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 4));
        break;
    } 
    return rewards;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\data\world\EasterEggData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */