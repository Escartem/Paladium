package fr.paladium.palashop.provider.box.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palashop.client.ui.kit.constant.ShopRarityConstant;
import fr.paladium.palashop.provider.box.common.dto.box.BoxData;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;

public class ItemBoxGift extends Item {
  private static ItemBoxGift instance;
  
  public ItemBoxGift() {
    instance = this;
    func_77625_d(1);
    func_77655_b("box_gift");
    func_111206_d("palashop:box_gift");
  }
  
  @NonNull
  public static ItemStack create(@NonNull BoxData box, @NonNull List<ItemBoxGiftReward> items) {
    if (box == null)
      throw new NullPointerException("box is marked non-null but is null"); 
    if (items == null)
      throw new NullPointerException("items is marked non-null but is null"); 
    items.sort((o1, o2) -> Integer.compare(o2.getRarity().ordinal(), o1.getRarity().ordinal()));
    ItemStack stack = new ItemStack(getInstance());
    NBTTagCompound nbt = (stack.func_77978_p() != null) ? stack.func_77978_p() : new NBTTagCompound();
    nbt.func_74778_a("boxId", box.getId());
    nbt.func_74778_a("boxName", box.getName());
    NBTTagList rewardsList = new NBTTagList();
    for (ItemBoxGiftReward reward : items) {
      NBTTagCompound rewardCompound = new NBTTagCompound();
      reward.getItem().func_77955_b(rewardCompound);
      rewardCompound.func_74768_a("rarity", reward.getRarity().ordinal());
      rewardsList.func_74742_a((NBTBase)rewardCompound);
    } 
    nbt.func_74782_a("rewards", (NBTBase)rewardsList);
    stack.func_77982_d(nbt);
    return stack;
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return stack; 
    List<ItemBoxGiftReward> rewards = getRewards(stack);
    if (rewards.isEmpty()) {
      SoundUtils.ITEM_BREAK.playSound((EntityPlayerMP)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, 1.0F);
      stack.field_77994_a--;
      return stack;
    } 
    List<ItemBoxGiftReward> givenRewards = new ArrayList<>();
    for (int i = 0; i < rewards.size(); i++) {
      ItemBoxGiftReward reward = rewards.get(i);
      if (player.field_71071_by.func_70447_i() == -1)
        break; 
      addItemStackToInventory(player.field_71071_by, reward.getItem().func_77946_l());
      givenRewards.add(reward);
    } 
    if (givenRewards.size() < rewards.size())
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] " + EnumChatFormatting.RED + "Votre inventaire est plein, certains objets n'ont pas pu être récupérés.")); 
    if (!givenRewards.isEmpty()) {
      player.field_71069_bz.func_75142_b();
      rewards.removeAll(givenRewards);
      SoundUtils.ITEM_PICKUP.playSound((EntityPlayerMP)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, 1.0F);
    } 
    if (rewards.isEmpty()) {
      stack.field_77994_a--;
      return stack;
    } 
    return updateRewards(stack, rewards);
  }
  
  public String func_77653_i(ItemStack stack) {
    String boxName = getBoxName(stack);
    if (boxName != null && !boxName.isEmpty())
      return EnumChatFormatting.AQUA + "Cadeau " + boxName + " Box" + EnumChatFormatting.RESET; 
    return EnumChatFormatting.AQUA + "Cadeau vide" + EnumChatFormatting.RESET;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> lines, boolean advanced) {
    super.func_77624_a(stack, player, lines, advanced);
    List<ItemBoxGiftReward> rewards = getRewards(stack);
    if (rewards.isEmpty())
      return; 
    lines.add("");
    List<ItemStack> stackedRewards = new ArrayList<>();
    for (ItemBoxGiftReward reward : rewards) {
      ItemStack rewardItem = reward.getItem().func_77946_l();
      if (stackedRewards.stream().anyMatch(item -> ItemStack.func_77989_b(item, rewardItem)))
        continue; 
      String rarityName = ShopRarityConstant.getName(reward.getRarity());
      int count = rewards.stream().filter(r -> ItemStack.func_77989_b(r.getItem(), rewardItem)).map(ItemBoxGiftReward::getItem).mapToInt(item -> item.field_77994_a).sum();
      lines.add(ShopRarityConstant.getChatColor(reward.getRarity()) + "• " + count + " " + rewardItem.func_82833_r() + " " + EnumChatFormatting.GRAY + "(" + rarityName.toLowerCase() + ")");
      stackedRewards.add(rewardItem);
    } 
  }
  
  @NonNull
  private static List<ItemBoxGiftReward> getRewards(@NonNull ItemStack stack) {
    if (stack == null)
      throw new NullPointerException("stack is marked non-null but is null"); 
    List<ItemBoxGiftReward> rewards = new ArrayList<>();
    NBTTagCompound nbt = stack.func_77978_p();
    if (nbt == null || !nbt.func_74764_b("rewards"))
      return rewards; 
    NBTTagList rewardsList = nbt.func_150295_c("rewards", 10);
    for (int i = 0; i < rewardsList.func_74745_c(); i++) {
      NBTTagCompound rewardCompound = rewardsList.func_150305_b(i);
      ItemStack item = ItemStack.func_77949_a(rewardCompound);
      if (item != null) {
        ShopRarity rarity = ShopRarity.values()[rewardCompound.func_74762_e("rarity")];
        rewards.add(new ItemBoxGiftReward(item, rarity));
      } 
    } 
    return rewards;
  }
  
  private static String getBoxName(@NonNull ItemStack stack) {
    if (stack == null)
      throw new NullPointerException("stack is marked non-null but is null"); 
    NBTTagCompound nbt = stack.func_77978_p();
    return (nbt != null && nbt.func_74764_b("boxName")) ? nbt.func_74779_i("boxName") : null;
  }
  
  @NonNull
  private static ItemStack updateRewards(@NonNull ItemStack stack, @NonNull List<ItemBoxGiftReward> rewards) {
    if (stack == null)
      throw new NullPointerException("stack is marked non-null but is null"); 
    if (rewards == null)
      throw new NullPointerException("rewards is marked non-null but is null"); 
    NBTTagCompound nbt = (stack.func_77978_p() != null) ? stack.func_77978_p() : new NBTTagCompound();
    NBTTagList rewardsList = new NBTTagList();
    for (ItemBoxGiftReward reward : rewards) {
      NBTTagCompound rewardCompound = new NBTTagCompound();
      reward.getItem().func_77955_b(rewardCompound);
      rewardCompound.func_74768_a("rarity", reward.getRarity().ordinal());
      rewardsList.func_74742_a((NBTBase)rewardCompound);
    } 
    nbt.func_74782_a("rewards", (NBTBase)rewardsList);
    stack.func_77982_d(nbt);
    return stack;
  }
  
  private boolean addItemStackToInventory(InventoryPlayer inventory, ItemStack stack) {
    if (stack == null || stack.field_77994_a == 0 || stack.func_77973_b() == null)
      return false; 
    try {
      if (!stack.func_77951_h()) {
        int j;
        do {
          j = stack.field_77994_a;
          stack.field_77994_a = storePartialItemStack(inventory, stack);
        } while (stack.field_77994_a > 0 && stack.field_77994_a < j);
        if (stack.field_77994_a == j && inventory.field_70458_d.field_71075_bZ.field_75098_d) {
          stack.field_77994_a = 0;
          return true;
        } 
        return (stack.field_77994_a < j);
      } 
      int i = inventory.func_70447_i();
      if (i >= 0) {
        inventory.field_70462_a[i] = ItemStack.func_77944_b(stack);
        (inventory.field_70462_a[i]).field_77992_b = 5;
        stack.field_77994_a = 0;
        return true;
      } 
      if (inventory.field_70458_d.field_71075_bZ.field_75098_d) {
        stack.field_77994_a = 0;
        return true;
      } 
      return false;
    } catch (Throwable throwable) {
      CrashReport crashreport = CrashReport.func_85055_a(throwable, "Adding item to inventory");
      CrashReportCategory crashreportcategory = crashreport.func_85058_a("Item being added");
      crashreportcategory.func_71507_a("Item ID", Integer.valueOf(Item.func_150891_b(stack.func_77973_b())));
      crashreportcategory.func_71507_a("Item data", Integer.valueOf(stack.func_77960_j()));
      crashreportcategory.func_71500_a("Item name", () -> stack.func_82833_r());
      throw new ReportedException(crashreport);
    } 
  }
  
  private int storePartialItemStack(InventoryPlayer inventory, ItemStack stack) {
    Item item = stack.func_77973_b();
    int i = stack.field_77994_a;
    if (stack.func_77976_d() == 1) {
      int m = inventory.func_70447_i();
      if (m < 0)
        return i; 
      if (inventory.field_70462_a[m] == null)
        inventory.field_70462_a[m] = ItemStack.func_77944_b(stack); 
      return 0;
    } 
    int j = storeItemStack(inventory, stack);
    if (j < 0)
      j = inventory.func_70447_i(); 
    if (j < 0)
      return i; 
    if (inventory.field_70462_a[j] == null) {
      inventory.field_70462_a[j] = new ItemStack(item, 0, stack.func_77960_j());
      if (stack.func_77942_o())
        inventory.field_70462_a[j].func_77982_d((NBTTagCompound)stack.func_77978_p().func_74737_b()); 
    } 
    int k = i;
    if (i > inventory.field_70462_a[j].func_77976_d() - (inventory.field_70462_a[j]).field_77994_a)
      k = inventory.field_70462_a[j].func_77976_d() - (inventory.field_70462_a[j]).field_77994_a; 
    if (k > inventory.func_70297_j_() - (inventory.field_70462_a[j]).field_77994_a)
      k = inventory.func_70297_j_() - (inventory.field_70462_a[j]).field_77994_a; 
    if (k == 0)
      return i; 
    i -= k;
    (inventory.field_70462_a[j]).field_77994_a += k;
    (inventory.field_70462_a[j]).field_77992_b = 5;
    return i;
  }
  
  private int storeItemStack(InventoryPlayer inventory, ItemStack stack) {
    for (int i = 0; i < inventory.field_70462_a.length; i++) {
      if (inventory.field_70462_a[i] != null && inventory.field_70462_a[i].func_77973_b() == stack.func_77973_b() && inventory.field_70462_a[i].func_77985_e() && (inventory.field_70462_a[i]).field_77994_a < inventory.field_70462_a[i].func_77976_d() && (inventory.field_70462_a[i]).field_77994_a < inventory.func_70297_j_() && (!inventory.field_70462_a[i].func_77981_g() || inventory.field_70462_a[i].func_77960_j() == stack.func_77960_j()) && ItemStack.func_77970_a(inventory.field_70462_a[i], stack))
        return i; 
    } 
    return -1;
  }
  
  @NonNull
  public static ItemBoxGift getInstance() {
    return instance;
  }
  
  public static class ItemBoxGiftReward {
    private final ItemStack item;
    
    private final ShopRarity rarity;
    
    public ItemBoxGiftReward(ItemStack item, ShopRarity rarity) {
      this.item = item;
      this.rarity = rarity;
    }
    
    public ItemStack getItem() {
      return this.item;
    }
    
    public ShopRarity getRarity() {
      return this.rarity;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\item\ItemBoxGift.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */