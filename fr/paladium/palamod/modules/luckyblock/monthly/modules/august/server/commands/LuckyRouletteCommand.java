package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.commands;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.SummerSalesEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets.SCSummerSalePacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.GoodFortuneEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.GoodPayEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.server.SCGoodPayPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.Arrays;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class LuckyRouletteCommand extends CommandBase {
  public static final String COMMAND_NAME = "luckyrewards";
  
  public static final String SALES_ARGUMENT = "sales";
  
  public static final String FORTUNE_ARGUMENT = "fortune";
  
  public static final String PAY_ARGUMENT = "pay";
  
  public static final String USAGE_MESSAGE = "&eSi l'interface ne s'est pas ouverte, faites &c/luckyrewards %s &epour récupèrer votre lot.";
  
  public static final String[] ARGS = new String[] { "sales", "fortune", "pay" };
  
  public static final String USAGES = String.format("&eUtilisation: &c/%s " + Arrays.toString((Object[])ARGS), new Object[] { "luckyrewards" });
  
  public static final String DONT_HAVE_LOT_MESSAGE = "&cVous n'avez pas de lot à récupèrer.";
  
  public static String getUsageMessage(String argument) {
    return String.format("&eSi l'interface ne s'est pas ouverte, faites &c/luckyrewards %s &epour récupèrer votre lot.", new Object[] { argument });
  }
  
  public String func_71517_b() {
    return "luckyrewards";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "luckyrewards";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (!(sender instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)sender;
    if (player.field_70170_p.field_72995_K)
      return; 
    if (args.length == 0) {
      MonthlyUtils.sendMessage(player, new String[] { USAGES });
      return;
    } 
    EntityPlayerMP playerMP = (EntityPlayerMP)player;
    String argument = args[0];
    if (argument.equalsIgnoreCase("sales")) {
      performSale(playerMP);
      return;
    } 
    if (argument.equalsIgnoreCase("fortune")) {
      performFortune(playerMP);
      return;
    } 
    if (argument.equalsIgnoreCase("pay")) {
      performPay(playerMP);
      return;
    } 
  }
  
  private void performPay(EntityPlayerMP playerMP) {
    if (!GoodPayEvent.INSTANCE.isWaitingReward(playerMP)) {
      MonthlyUtils.sendMessage((EntityPlayer)playerMP, new String[] { "&cVous n'avez pas de lot à récupèrer." });
      return;
    } 
    int rewardId = GoodPayEvent.INSTANCE.getRewardId(playerMP);
    PalaMod.network.sendTo((IMessage)new SCGoodPayPacket(rewardId), playerMP);
  }
  
  private void performFortune(EntityPlayerMP playerMP) {
    if (!GoodFortuneEvent.INSTANCE.hasWaitingReward(playerMP)) {
      MonthlyUtils.sendMessage((EntityPlayer)playerMP, new String[] { "&cVous n'avez pas de lot à récupèrer." });
      return;
    } 
    List<ItemStack> rewards = GoodFortuneEvent.INSTANCE.getRewards();
    ItemStack reward = GoodFortuneEvent.INSTANCE.pickupReward(playerMP, rewards);
    PalaMod.network.sendTo((IMessage)new SCSummerSalePacket(rewards, reward, 1), playerMP);
  }
  
  public void performSale(EntityPlayerMP playerMP) {
    if (!SummerSalesEvent.INSTANCE.hasWaitingReward(playerMP)) {
      MonthlyUtils.sendMessage((EntityPlayer)playerMP, new String[] { "&cVous n'avez pas de lot à récupèrer." });
      return;
    } 
    List<ItemStack> rewards = SummerSalesEvent.INSTANCE.getRewards();
    ItemStack reward = SummerSalesEvent.INSTANCE.pickupReward(playerMP, rewards);
    PalaMod.network.sendTo((IMessage)new SCSummerSalePacket(rewards, reward, 0), playerMP);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\server\commands\LuckyRouletteCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */