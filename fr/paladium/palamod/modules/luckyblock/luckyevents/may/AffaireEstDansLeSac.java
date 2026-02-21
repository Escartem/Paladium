package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.utils.ChanceObject;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.utils.RandomObjectPicker;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.network.may.SCDealInBagPacket;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class AffaireEstDansLeSac extends ALuckyEvent {
  private static final String EVENT_NAME = "L'affaire est dans le sac";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 160;
  
  private static final String TEXTURE_PATH = "may/affaire_est_dans_le_sac";
  
  private static final String WIN_MESSAGE = "&eVous venez de recevoir votre &drécompense&e !";
  
  private static AffaireEstDansLeSac instance;
  
  private Map<UUID, ItemStack> rewardsMap;
  
  public static AffaireEstDansLeSac getInstance() {
    return instance;
  }
  
  public Map<UUID, ItemStack> getRewardsMap() {
    return this.rewardsMap;
  }
  
  public AffaireEstDansLeSac() {
    instance = this;
    this.rewardsMap = new HashMap<>();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    List<ItemStack> rewards = getRewards();
    ItemStack reward = pickupReward(player, rewards);
    PalaMod.network.sendTo((IMessage)new SCDealInBagPacket(rewards, reward), player);
  }
  
  public List<ItemStack> getRewards() {
    return Arrays.asList(new ItemStack[] { new ItemStack(ItemsRegister.HUNTER_BACKPACK, 1, 1), new ItemStack(ItemsRegister.HUNTER_BACKPACK, 1, 2), new ItemStack(ItemsRegister.PRESENT_BAG), new ItemStack(ItemsRegister.SLEEPING_BAG), new ItemStack(
            
            GameRegistry.findItem("customnpcs", "npcSatchel")), new ItemStack(
            GameRegistry.findItem("customnpcs", "npcBag")), new ItemStack(ItemsRegister.ARMOR_BAG), new ItemStack(ItemsRegister.HUNTER_BACKPACK, 1, 3) });
  }
  
  public ItemStack pickupReward(EntityPlayerMP player, List<ItemStack> rewards) {
    RandomObjectPicker<ItemStack> picker = new RandomObjectPicker(Arrays.asList(new ChanceObject[] { ChanceObject.of(25.0D, rewards.get(0)), 
            ChanceObject.of(24.0D, rewards.get(1)), 
            ChanceObject.of(15.0D, rewards.get(2)), 
            ChanceObject.of(10.0D, rewards.get(3)), 
            ChanceObject.of(8.0D, rewards.get(4)), 
            ChanceObject.of(7.0D, rewards.get(5)), 
            ChanceObject.of(5.0D, rewards.get(6)), 
            ChanceObject.of(1.0D, rewards.get(7)) }));
    ItemStack reward = (ItemStack)picker.pickRandomObject();
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
    return "L'affaire est dans le sac";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 160;
  }
  
  public String getTexture() {
    return "may/affaire_est_dans_le_sac";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\AffaireEstDansLeSac.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */