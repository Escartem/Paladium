package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.ItemsRegister;
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
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class GoodFortuneEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "La bonne fortune";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 350;
  
  private static final String TEXTURE_PATH = "september/good_fortune";
  
  private static final String WIN_MESSAGE = "&eVous venez de recevoir votre &drécompense&e !";
  
  public static GoodFortuneEvent INSTANCE;
  
  private Random random;
  
  private Map<UUID, ItemStack> rewardsMap;
  
  public Map<UUID, ItemStack> getRewardsMap() {
    return this.rewardsMap;
  }
  
  public GoodFortuneEvent() {
    INSTANCE = this;
    this.rewardsMap = new HashMap<>();
    this.random = new Random();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    List<ItemStack> rewards = getRewards();
    ItemStack reward = pickupReward(player, rewards);
    PalaMod.network.sendTo((IMessage)new SCSummerSalePacket(rewards, reward, 1), player);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { LuckyRouletteCommand.getUsageMessage("fortune") });
  }
  
  public List<ItemStack> getRewards() {
    ItemStack first = new ItemStack(ItemsRegister.PALADIUM_PICKAXE);
    ItemStack second = new ItemStack(ItemsRegister.PALADIUM_PICKAXE);
    second.func_77966_a(Enchantment.field_77349_p, 5);
    second.func_77966_a(Enchantment.field_77348_q, 1);
    ItemStack third = new ItemStack(ItemsRegister.PALADIUM_PICKAXE);
    third.func_77966_a(Enchantment.field_77347_r, 3);
    ItemStack fourth = new ItemStack(ItemsRegister.PALADIUM_PICKAXE);
    fourth.func_77966_a(Enchantment.field_77349_p, 5);
    fourth.func_77966_a(Enchantment.field_77347_r, 3);
    ItemStack fifth = new ItemStack(ItemsRegister.PALADIUM_PICKAXE);
    fifth.func_77966_a(Enchantment.field_77349_p, 5);
    fifth.func_77966_a(Enchantment.field_77348_q, 1);
    fifth.func_77966_a(Enchantment.field_77347_r, 3);
    return Arrays.asList(new ItemStack[] { first, second, third, fourth, fifth });
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
  
  public String getName() {
    return "La bonne fortune";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 350;
  }
  
  public String getTexture() {
    return "september/good_fortune";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\GoodFortuneEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */