package fr.paladium.palaforgeutils.lib.command.impl.palagive.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import java.util.ArrayList;
import java.util.Iterator;
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

public class ItemGiveGift extends Item {
  private static ItemGiveGift instance;
  
  public ItemGiveGift() {
    instance = this;
    func_77625_d(1);
    func_77655_b("give_gift");
    func_111206_d("palaforge-utils:give_gift");
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return stack; 
    List<ItemStack> rewards = getRewards(stack);
    if (rewards.isEmpty()) {
      SoundUtils.ITEM_BREAK.playSound((EntityPlayerMP)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, 1.0F);
      stack.field_77994_a--;
      return stack;
    } 
    boolean given = false;
    Iterator<ItemStack> iterator = rewards.iterator();
    while (iterator.hasNext()) {
      ItemStack reward = iterator.next();
      if (player.field_71071_by.func_70447_i() == -1) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] " + EnumChatFormatting.RED + "Votre inventaire est plein, certains objets n'ont pas pu être récupérés."));
        break;
      } 
      given = true;
      addItemStackToInventory(player.field_71071_by, reward.func_77946_l());
      iterator.remove();
    } 
    if (given) {
      SoundUtils.ITEM_PICKUP.playSound((EntityPlayerMP)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, 1.0F);
      player.field_71069_bz.func_75142_b();
    } 
    if (rewards.isEmpty()) {
      saveRewards(stack, rewards);
      SoundUtils.ITEM_BREAK.playSound((EntityPlayerMP)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, 1.0F);
      stack.field_77994_a--;
      return stack;
    } 
    return saveRewards(stack, rewards);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> lines, boolean advanced) {
    super.func_77624_a(stack, player, lines, advanced);
    lines.add("");
    List<ItemStack> rewards = getRewards(stack);
    if (rewards.isEmpty()) {
      lines.add("§8• §cCe cadeau est vide");
      return;
    } 
    for (ItemStack reward : rewards)
      lines.add("§b• §r§f" + reward.field_77994_a + " " + reward.func_82833_r() + "§r§f"); 
  }
  
  public static ItemStack create(@NonNull List<ItemStack> items) {
    if (items == null)
      throw new NullPointerException("items is marked non-null but is null"); 
    ItemStack stack = new ItemStack(getInstance());
    return saveRewards(stack, items);
  }
  
  private static ItemStack saveRewards(ItemStack stack, List<ItemStack> items) {
    NBTTagCompound nbt = (stack.func_77978_p() != null) ? stack.func_77978_p() : new NBTTagCompound();
    NBTTagList rewardsList = new NBTTagList();
    for (ItemStack item : items) {
      ItemStack itemCopy = item.func_77946_l();
      while (itemCopy.field_77994_a > itemCopy.func_77976_d()) {
        itemCopy.field_77994_a -= itemCopy.func_77976_d();
        ItemStack split = itemCopy.func_77946_l();
        split.field_77994_a = itemCopy.func_77976_d();
        NBTTagCompound splitCompound = new NBTTagCompound();
        split.func_77955_b(splitCompound);
        rewardsList.func_74742_a((NBTBase)splitCompound);
      } 
      NBTTagCompound itemCompound = new NBTTagCompound();
      itemCopy.func_77955_b(itemCompound);
      rewardsList.func_74742_a((NBTBase)itemCompound);
    } 
    nbt.func_74782_a("rewards", (NBTBase)rewardsList);
    stack.func_77982_d(nbt);
    return stack;
  }
  
  public String func_77653_i(ItemStack stack) {
    return "§r§bCadeau de Paladium§r§f";
  }
  
  public boolean addItemStackToInventory(InventoryPlayer inventory, ItemStack stack) {
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
  
  private static List<ItemStack> getRewards(@NonNull ItemStack stack) {
    if (stack == null)
      throw new NullPointerException("stack is marked non-null but is null"); 
    List<ItemStack> rewards = new ArrayList<>();
    NBTTagCompound nbt = stack.func_77978_p();
    if (nbt == null || !nbt.func_74764_b("rewards"))
      return rewards; 
    NBTTagList rewardsList = nbt.func_150295_c("rewards", 10);
    for (int i = 0; i < rewardsList.func_74745_c(); i++) {
      NBTTagCompound rewardCompound = rewardsList.func_150305_b(i);
      ItemStack item = ItemStack.func_77949_a(rewardCompound);
      if (item != null)
        rewards.add(item); 
    } 
    return rewards;
  }
  
  public static ItemGiveGift getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\impl\palagive\item\ItemGiveGift.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */