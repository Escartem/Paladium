package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.IceCreamType;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets.SCSummerSalePacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.commands.LuckyRouletteCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SummerSalesEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Soldes d'été";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 350;
  
  private static final String TEXTURE_PATH = "august/summer_sales";
  
  private static final String WIN_MESSAGE = "&eVous venez de recevoir votre &drécompense&e !";
  
  public static SummerSalesEvent INSTANCE;
  
  private Random random;
  
  private Map<UUID, ItemStack> rewardsMap;
  
  public Map<UUID, ItemStack> getRewardsMap() {
    return this.rewardsMap;
  }
  
  public SummerSalesEvent() {
    INSTANCE = this;
    this.rewardsMap = new HashMap<>();
    this.random = new Random();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    List<ItemStack> rewards = getRewards();
    ItemStack reward = pickupReward(player, rewards);
    PalaMod.network.sendTo((IMessage)new SCSummerSalePacket(rewards, reward, 0), player);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { LuckyRouletteCommand.getUsageMessage("sales") });
  }
  
  public List<ItemStack> getRewards() {
    List<ItemStack> rewards = Arrays.asList(new ItemStack[] { buildIceCream(IceCreamType.BANANA, IceCreamType.CHOCOLATE), 
          buildIceCream(IceCreamType.VANILLA, IceCreamType.COFFEE), new ItemStack(BlocksRegister.VENTILATOR), new ItemStack(ItemsRegister.WEIGHTED_BOOTS), new ItemStack(ItemsRegister.LUCKY_PAINTING) });
    return rewards;
  }
  
  public ItemStack pickupReward(EntityPlayerMP player, List<ItemStack> rewards) {
    ItemStack reward = rewards.get(this.random.nextInt(rewards.size()));
    this.rewardsMap.put(player.func_110124_au(), reward);
    return reward;
  }
  
  public boolean hasWaitingReward(EntityPlayerMP player) {
    return this.rewardsMap.containsKey(player.func_110124_au());
  }
  
  public void giveReward(EntityPlayerMP player) {
    if (!this.rewardsMap.containsKey(player.func_110124_au()))
      return; 
    ItemUtils.spawnItemAtEntity((Entity)player, this.rewardsMap.get(player.func_110124_au()));
    this.rewardsMap.remove(player.func_110124_au());
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&eVous venez de recevoir votre &drécompense&e !" });
  }
  
  private ItemStack buildIceCream(IceCreamType firstFlavor, IceCreamType secondFlavor) {
    ItemStack stack;
    NBTTagCompound compound = new NBTTagCompound();
    if (secondFlavor == null) {
      stack = new ItemStack(ItemsRegister.ICE_CREAM, 1);
    } else {
      stack = new ItemStack(ItemsRegister.DOUBLE_ICE_CREAM, 1);
    } 
    if (firstFlavor != null) {
      compound.func_74778_a("firstFlavor", firstFlavor.getName());
      stack.func_77982_d(compound);
      stack.func_151001_c("Glace " + firstFlavor.getName());
    } 
    if (firstFlavor != null && secondFlavor != null) {
      compound.func_74778_a("secondFlavor", secondFlavor.getName());
      stack.func_77982_d(compound);
      stack.func_151001_c("Glace " + firstFlavor.getName() + " & " + secondFlavor.getName());
    } 
    return stack;
  }
  
  public String getName() {
    return "Soldes d'été";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 350;
  }
  
  public String getTexture() {
    return "august/summer_sales";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\SummerSalesEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */