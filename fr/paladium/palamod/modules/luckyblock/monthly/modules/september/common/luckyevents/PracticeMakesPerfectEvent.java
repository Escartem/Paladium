package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.BlackSmithData;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.BlackSmithStep;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.LuckyBridge;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class PracticeMakesPerfectEvent extends ALuckyEvent {
  public static final String EVENT_NAME = "C'est en forgeant qu'on devient forgeron";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 750;
  
  private static final String TEXTURE_PATH = "september/practice_makes_perfect";
  
  public static final String NBT_BLACKLISTED_TAG = "blacklisted";
  
  public static final String[] START_MESSAGES = new String[] { "&eEffectue la commande &6/forgestep &epour commencer l’épreuve du forgeron!", "&6/forgestep info &epour avoir des informations sur l’épreuve du forgeron!" };
  
  public static final String CONTINUE_MESSAGE = "&eEffectue la commande &6/forgestep &epour continuer l’épreuve du forgeron!";
  
  public static final String FAIL_MESSAGE = "&cTu as échoué l’épreuve du forgeron car tu as été trop lent!";
  
  public static final String WIN_MESSAGE = "&aTu as réussi l’épreuve du forgeron!";
  
  public static PracticeMakesPerfectEvent INSTANCE;
  
  private final List<BlackSmithStep> steps;
  
  private Map<UUID, BlackSmithData> datas;
  
  public List<BlackSmithStep> getSteps() {
    return this.steps;
  }
  
  public Map<UUID, BlackSmithData> getDatas() {
    return this.datas;
  }
  
  public PracticeMakesPerfectEvent() {
    INSTANCE = this;
    this.steps = Arrays.asList(new BlackSmithStep[] { BlackSmithStep.builder()
          .durationMillis(TimeUnit.SECONDS.toMillis(30L))
          .items(Arrays.asList(new ItemStack[] { new ItemStack(ItemsRegister.AMETHYST_INGOT, 3), new ItemStack(ItemsRegister.TITANE_INGOT, 3), new ItemStack(ItemsRegister.PALADIUM_INGOT, 7), new ItemStack(Items.field_151055_y, 6) })).craftList(Arrays.asList(new ItemStack[] { new ItemStack(ItemsRegister.GOD_PICKAXE) })).rewardList(Arrays.asList(new ItemStack[] { new ItemStack(BlocksRegister.ANVIL_AMETHYST, 1) })).messages(new String[] { "&eCraft: ", "&7- Pickaxe of the gods", "&epour gagner une &arécompense&e!" }).build(), 
          BlackSmithStep.builder()
          .durationMillis(TimeUnit.SECONDS.toMillis(25L))
          .items(Arrays.asList(new ItemStack[] { new ItemStack(Items.field_151008_G, 6), new ItemStack(Items.field_151116_aA, 7), new ItemStack(ItemsRegister.TITANE_INGOT, 4), new ItemStack(Items.field_151042_j, 2) })).craftList(Arrays.asList(new ItemStack[] { new ItemStack((Item)ItemsRegister.TRAVEL_JUMPCHEST), new ItemStack((Item)ItemsRegister.TRAVEL_LEGGINGS), new ItemStack((Item)ItemsRegister.TRAVEL_BOOTS) })).rewardList(Arrays.asList(new ItemStack[] { new ItemStack(BlocksRegister.ANVIL_TITANE, 1) })).messages(new String[] { "&eCraft: ", "&7- Plastron de saut", "&7- Jambières de voyage", "&7- Bottes de voyage", "&epour gagner une &arécompense&e!" }).build(), 
          BlackSmithStep.builder()
          .durationMillis(TimeUnit.SECONDS.toMillis(25L))
          .items(Arrays.asList(new ItemStack[] { new ItemStack(Items.field_151123_aH, 3), new ItemStack(Items.field_151045_i, 2), new ItemStack(ItemsRegister.TITANE_INGOT, 12), new ItemStack(Blocks.field_150410_aZ, 1), new ItemStack(Blocks.field_150325_L, 8), new ItemStack(ItemsRegister.PALADIUM_INGOT, 5) })).craftList(Arrays.asList(new ItemStack[] { new ItemStack((Item)ItemsRegister.TRAVEL_SLIMYHELMET), new ItemStack((Item)ItemsRegister.TRAVEL_SCUBAHELMET), new ItemStack((Item)ItemsRegister.TRAVEL_HOODHELMET) })).rewardList(Arrays.asList(new ItemStack[] { new ItemStack(BlocksRegister.ANVIL_PALADIUM, 1) })).messages(new String[] { "&eCraft: ", "&7- Casque en slime", "&7- Casque de plongée", "&7- Cagoule", "&epour gagner une &arécompense&e!" }).build() });
    this.datas = new HashMap<>();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    BlackSmithData data = this.datas.get(player.func_110124_au());
    if (data != null)
      return; 
    data = new BlackSmithData(player);
    MonthlyUtils.sendMessage((EntityPlayer)player, START_MESSAGES);
    this.datas.put(player.func_110124_au(), data);
  }
  
  @SubscribeEvent
  public void onCraftItem(PlayerEvent.ItemCraftedEvent event) {
    EntityPlayerMP playerMP = (EntityPlayerMP)event.player;
    ItemStack stack = event.crafting;
    if (playerMP == null || stack == null)
      return; 
    LuckyBridge.performPracticesCraft((EntityPlayer)playerMP, stack);
  }
  
  public BlackSmithData get(UUID uniqueId) {
    return this.datas.get(uniqueId);
  }
  
  public void cleanBlacklistedItems(EntityPlayer player, BlackSmithData data) {
    if (shouldCleanInventory(data)) {
      cleanInventory(player.field_71071_by.field_70462_a);
      player.field_71069_bz.func_75142_b();
    } 
  }
  
  private boolean shouldCleanInventory(BlackSmithData data) {
    if (data == null)
      return true; 
    return (data != null && !data.isWaveStarted() && !data.isExpired(System.currentTimeMillis()));
  }
  
  private void cleanInventory(ItemStack[] mainInventory) {
    for (int i = 0; i < mainInventory.length; i++) {
      ItemStack stack = mainInventory[i];
      if (stack != null && isBlackListed(stack))
        mainInventory[i] = null; 
    } 
  }
  
  public void remove(EntityPlayer player, UUID uniqueId, BlackSmithData data) {
    MonthlyUtils.sendMessage(player, new String[] { "&cTu as échoué l’épreuve du forgeron car tu as été trop lent!" });
    this.datas.remove(uniqueId);
    cleanBlacklistedItems(player, null);
  }
  
  public void win(EntityPlayer player, BlackSmithData data) {
    MonthlyUtils.sendMessage(player, new String[] { "&aTu as réussi l’épreuve du forgeron!" });
    this.datas.remove(player.func_110124_au());
    cleanBlacklistedItems(player, null);
  }
  
  public boolean isBlackListed(ItemStack stack) {
    NBTTagCompound compound = stack.func_77978_p();
    if (compound == null)
      return false; 
    if (!compound.func_74764_b("blacklisted"))
      return false; 
    return compound.func_74767_n("blacklisted");
  }
  
  public boolean isCraftingItem(BlackSmithData data, ItemStack stack) {
    if (!data.isWaveStarted())
      return false; 
    BlackSmithStep step = getStep(data.getCurrentWave());
    if (step == null)
      return false; 
    for (ItemStack itemStack : step.getCraftList()) {
      if (itemStack.func_77973_b() == stack.func_77973_b())
        return true; 
    } 
    return false;
  }
  
  public BlackSmithStep getStep(int step) {
    try {
      return this.steps.get(step - 1);
    } catch (IndexOutOfBoundsException e) {
      return null;
    } 
  }
  
  public void addBlackListedTag(ItemStack stack) {
    NBTTagCompound compound = stack.func_77978_p();
    if (compound == null)
      compound = new NBTTagCompound(); 
    compound.func_74757_a("blacklisted", true);
    stack.func_77982_d(compound);
  }
  
  public String getName() {
    return "C'est en forgeant qu'on devient forgeron";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 750;
  }
  
  public String getTexture() {
    return "september/practice_makes_perfect";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\PracticeMakesPerfectEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */