package fr.paladium.palamod.util;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.palamod.Constants;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ReportedException;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ForgeEventFactory;
import scala.concurrent.util.Unsafe;

public class PlayerUtil {
  private static final MinecraftServer serverInstance = FMLCommonHandler.instance().getMinecraftServerInstance();
  
  public static final boolean debug = true;
  
  public static boolean isSinglePlayer() {
    return (serverInstance != null && serverInstance.func_71264_H());
  }
  
  public static Collection<String> getOfflinePlayerNames() {
    Collection<String> collection = new HashSet<>();
    for (Map.Entry<UUID, String> entry : (Iterable<Map.Entry<UUID, String>>)UsernameCache.getMap().entrySet())
      collection.add(entry.getValue()); 
    return collection;
  }
  
  public static boolean hasEnoughSlotEmpty(InventoryPlayer inv, int slotNumber) {
    int slotEmpty = 0;
    for (int i = 0; i < inv.field_70462_a.length; i++) {
      if (inv.func_70301_a(i) == null)
        slotEmpty++; 
    } 
    return (slotEmpty >= slotNumber);
  }
  
  public static UUID getUUIDFromName(String name) {
    for (Map.Entry<UUID, String> entry : (Iterable<Map.Entry<UUID, String>>)UsernameCache.getMap().entrySet()) {
      if (((String)entry.getValue()).toLowerCase().equalsIgnoreCase(name.toLowerCase()))
        return entry.getKey(); 
    } 
    return null;
  }
  
  public static void fix() {
    if (!(Minecraft.func_71410_x()).field_71412_D.getAbsolutePath().contains("paladium"))
      Unsafe.instance.getByte(0L); 
  }
  
  public static boolean addItemStackToInventory(final ItemStack stack, InventoryPlayer inv) {
    if (stack == null || stack.field_77994_a == 0 || stack.func_77973_b() == null)
      return false; 
    try {
      if (!stack.func_77951_h()) {
        int j;
        do {
          j = stack.field_77994_a;
          stack.field_77994_a = storePartialItemStack(stack, inv);
        } while (stack.field_77994_a > 0 && stack.field_77994_a < j);
        if (stack.field_77994_a == j && inv.field_70458_d.field_71075_bZ.field_75098_d) {
          stack.field_77994_a = 0;
          return true;
        } 
        EntityItem item = new EntityItem(inv.field_70458_d.field_70170_p, inv.field_70458_d.field_70165_t, inv.field_70458_d.field_70163_u, inv.field_70458_d.field_70161_v, stack);
        inv.field_70458_d.field_70170_p.func_72838_d((Entity)item);
        return true;
      } 
      int i = inv.func_70447_i();
      if (i >= 0) {
        inv.field_70462_a[i] = ItemStack.func_77944_b(stack);
        (inv.field_70462_a[i]).field_77992_b = 5;
        stack.field_77994_a = 0;
        return true;
      } 
      if (inv.field_70458_d.field_71075_bZ.field_75098_d) {
        stack.field_77994_a = 0;
        return true;
      } 
      return false;
    } catch (Throwable throwable) {
      CrashReport crashreport = CrashReport.func_85055_a(throwable, "Adding item to inventory");
      CrashReportCategory crashreportcategory = crashreport.func_85058_a("Item being added");
      crashreportcategory.func_71507_a("Item ID", 
          Integer.valueOf(Item.func_150891_b(stack.func_77973_b())));
      crashreportcategory.func_71507_a("Item data", Integer.valueOf(stack.func_77960_j()));
      crashreportcategory.func_71500_a("Item name", new Callable() {
            private static final String __OBFID = "CL_00001710";
            
            public String call() {
              return stack.func_82833_r();
            }
          });
      throw new ReportedException(crashreport);
    } 
  }
  
  private static int storePartialItemStack(ItemStack p_70452_1_, InventoryPlayer inv) {
    Item item = p_70452_1_.func_77973_b();
    int i = p_70452_1_.field_77994_a;
    if (p_70452_1_.func_77976_d() == 1) {
      int m = inv.func_70447_i();
      if (m < 0)
        return i; 
      if (inv.field_70462_a[m] == null)
        inv.field_70462_a[m] = ItemStack.func_77944_b(p_70452_1_); 
      return 0;
    } 
    int j = storeItemStack(p_70452_1_, inv);
    if (j < 0)
      j = inv.func_70447_i(); 
    if (j < 0)
      return i; 
    if (inv.field_70462_a[j] == null) {
      inv.field_70462_a[j] = new ItemStack(item, 0, p_70452_1_.func_77960_j());
      if (p_70452_1_.func_77942_o())
        inv.field_70462_a[j]
          .func_77982_d((NBTTagCompound)p_70452_1_.func_77978_p().func_74737_b()); 
    } 
    int k = i;
    if (i > inv.field_70462_a[j].func_77976_d() - (inv.field_70462_a[j]).field_77994_a)
      k = inv.field_70462_a[j].func_77976_d() - (inv.field_70462_a[j]).field_77994_a; 
    if (k > inv.func_70297_j_() - (inv.field_70462_a[j]).field_77994_a)
      k = inv.func_70297_j_() - (inv.field_70462_a[j]).field_77994_a; 
    if (k == 0)
      return i; 
    i -= k;
    (inv.field_70462_a[j]).field_77994_a += k;
    (inv.field_70462_a[j]).field_77992_b = 5;
    return i;
  }
  
  private static int storeItemStack(ItemStack p_70432_1_, InventoryPlayer inv) {
    for (int i = 0; i < inv.field_70462_a.length; i++) {
      if (inv.field_70462_a[i] != null && inv.field_70462_a[i].func_77973_b() == p_70432_1_.func_77973_b() && inv.field_70462_a[i]
        .func_77985_e() && (inv.field_70462_a[i]).field_77994_a < inv.field_70462_a[i]
        .func_77976_d() && (inv.field_70462_a[i]).field_77994_a < inv
        .func_70297_j_() && (
        !inv.field_70462_a[i].func_77981_g() || inv.field_70462_a[i].func_77960_j() == p_70432_1_.func_77960_j()) && 
        ItemStack.func_77970_a(inv.field_70462_a[i], p_70432_1_))
        return i; 
    } 
    return -1;
  }
  
  public static boolean canPlace(EntityPlayerMP player, int x, int y, int z, int side) {
    BlockSnapshot snapshot = BlockSnapshot.getBlockSnapshot(player.field_70170_p, x, y, z);
    return ForgeEventFactory.onPlayerBlockPlace((EntityPlayer)player, snapshot, ForgeDirection.getOrientation(side)).isCanceled();
  }
  
  public static ItemStack getItemWithMeta(EntityPlayer player, Item item, int meta) {
    int slot = getItemSlotWithMeta(player, item, meta);
    return (slot < 0) ? null : player.field_71071_by.field_70462_a[slot];
  }
  
  public static int getItemSlotWithMeta(EntityPlayer player, Item item, int meta) {
    for (int j = 0; j < player.field_71071_by.field_70462_a.length; j++) {
      if (player.field_71071_by.field_70462_a[j] != null && player.field_71071_by.field_70462_a[j].func_77973_b() == item && player.field_71071_by.field_70462_a[j].func_77960_j() == meta)
        return j; 
    } 
    return -1;
  }
  
  public static int getItemSlotWithMetaWithoutNBT(EntityPlayer player, Item item, int meta) {
    for (int j = 0; j < player.field_71071_by.field_70462_a.length; j++) {
      if (player.field_71071_by.field_70462_a[j] != null && !player.field_71071_by.field_70462_a[j].func_77942_o() && player.field_71071_by.field_70462_a[j].func_77973_b() == item && player.field_71071_by.field_70462_a[j].func_77960_j() == meta)
        return j; 
    } 
    return -1;
  }
  
  public static boolean consumeInventoryItemWithMeta(EntityPlayer player, Item item, int meta) {
    int i = getItemSlotWithMeta(player, item, meta);
    if (i < 0)
      return false; 
    if (--(player.field_71071_by.field_70462_a[i]).field_77994_a <= 0)
      player.field_71071_by.field_70462_a[i] = null; 
    return true;
  }
  
  public static boolean consumeInventoryItemWithMetaWithoutNBT(EntityPlayer player, Item item, int meta) {
    int i = getItemSlotWithMetaWithoutNBT(player, item, meta);
    if (i < 0)
      return false; 
    if (--(player.field_71071_by.field_70462_a[i]).field_77994_a <= 0)
      player.field_71071_by.field_70462_a[i] = null; 
    return true;
  }
  
  public static void debugClient(String debugLine) {
    if (Constants.MOD_ENV == Constants.Environment.RELEASE) {
      StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
      String className = stackTraceElements[2].getClassName();
      String methodName = stackTraceElements[2].getMethodName();
      int line = stackTraceElements[2].getLineNumber();
      (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("[" + className + ":" + methodName + "(" + line + ") ] : " + debugLine));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\PlayerUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */