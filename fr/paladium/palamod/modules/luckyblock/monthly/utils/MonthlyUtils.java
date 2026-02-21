package fr.paladium.palamod.modules.luckyblock.monthly.utils;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.factions.api.faction.IFactionClaim;
import fr.paladium.factions.core.faction.Faction;
import fr.paladium.factions.core.faction.FactionController;
import fr.paladium.factions.core.faction.territory.ClaimController;
import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.ModuleLuckyEvent;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.packet.PacketLuckyEventHelios;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class MonthlyUtils {
  public static final String LUCKY_BLOCK_PREFIX = "§6§lLuckyBlock §8» §r";
  
  public static final String LINE_SEPARATOR = "§8§m----------------------------------------";
  
  public static final int CORSAIR_BANNER_DAMAGE = 16;
  
  public static final int SEPTEMBER_BANNER_DAMAGE = 17;
  
  public static EntityPlayer getClosest(World world, int x, int y, int z) {
    return getClosest(world, x, y, z, 500.0D);
  }
  
  public static EntityPlayer getClosest(World world, int x, int y, int z, double radius) {
    return world.func_72977_a(x, y, z, radius);
  }
  
  public static void teleport(EntityPlayer entity, double x, double y, double z) {
    try {
      TeleportUtils.teleport(entity, x, y, z);
    } catch (Exception exception) {}
  }
  
  public static void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity) {
    int l = MathHelper.func_76128_c((entity.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    if (l == 0)
      world.func_72921_c(x, y, z, 2, 2); 
    if (l == 1)
      world.func_72921_c(x, y, z, 5, 2); 
    if (l == 2)
      world.func_72921_c(x, y, z, 3, 2); 
    if (l == 3)
      world.func_72921_c(x, y, z, 4, 2); 
  }
  
  public static void spawnParticle(World world, String particle, double x, double y, double z, int count, double vel) {
    if (world instanceof WorldServer) {
      WorldServer worldServer = (WorldServer)world;
      worldServer.func_147487_a(particle, x, y, z, count, 0.0D, 0.0D, 0.0D, vel);
    } 
  }
  
  public static float getRotation(int metadata) {
    switch (metadata) {
      case 2:
        return 0.0F;
      case 3:
        return 180.0F;
      case 4:
        return 1.0F;
      case 5:
        return -1.0F;
    } 
    return 0.0F;
  }
  
  public static void setDefaultDirection(World world, int x, int y, int z) {
    if (!world.field_72995_K) {
      Block block = world.func_147439_a(x, y, z - 1);
      Block block1 = world.func_147439_a(x, y, z + 1);
      Block block2 = world.func_147439_a(x - 1, y, z);
      Block block3 = world.func_147439_a(x + 1, y, z);
      byte b0 = 3;
      if (block.func_149730_j() && !block1.func_149730_j())
        b0 = 3; 
      if (block1.func_149730_j() && !block.func_149730_j())
        b0 = 2; 
      if (block2.func_149730_j() && !block3.func_149730_j())
        b0 = 5; 
      if (block3.func_149730_j() && !block2.func_149730_j())
        b0 = 4; 
      world.func_72921_c(x, y, z, b0, 2);
    } 
  }
  
  public static boolean isWarZone(int x, int z) {
    IFactionClaim claim = ClaimController.getInstance().getClaim(x >> 4, z >> 4);
    Faction warZone = FactionController.getInstance().getLoadedFaction("WarZone");
    if (claim == null || warZone == null)
      return false; 
    return claim.getFactionUuid().equals(warZone.getUuid());
  }
  
  public static EntityXPOrb spawnExperience(EntityPlayerMP playerMP, int amount) {
    return spawnExperience(playerMP.field_70170_p, playerMP.field_70165_t, playerMP.field_70163_u, playerMP.field_70161_v, amount);
  }
  
  public static EntityXPOrb spawnExperience(World world, double x, double y, double z, int amount) {
    EntityXPOrb orb = new EntityXPOrb(world, x, y, z, amount);
    world.func_72838_d((Entity)orb);
    return orb;
  }
  
  public static int getEnchantmentLevel(ItemStack stack, Enchantment enchantment) {
    if (stack == null || enchantment == null)
      return 0; 
    NBTTagList enchantmentTagList = stack.func_77986_q();
    if (enchantmentTagList == null)
      return 0; 
    for (int i = 0; i < enchantmentTagList.func_74745_c(); i++) {
      NBTTagCompound tagCompound = enchantmentTagList.func_150305_b(i);
      short id = tagCompound.func_74765_d("id");
      short level = tagCompound.func_74765_d("lvl");
      if (id == enchantment.field_77352_x)
        return level; 
    } 
    return 0;
  }
  
  public static ItemStack getEnchantedBook(int enchantmentId, int enchantmentLevel) {
    ItemStack book = new ItemStack((Item)Items.field_151134_bR);
    Map<Integer, Integer> enchants = new HashMap<>();
    enchants.put(Integer.valueOf(enchantmentId), Integer.valueOf(enchantmentLevel));
    EnchantmentHelper.func_82782_a(enchants, book);
    return book;
  }
  
  public static boolean removeEnchantments(ItemStack stack) {
    if (stack == null)
      return false; 
    NBTTagCompound tagCompound = stack.func_77978_p();
    if (tagCompound == null || !tagCompound.func_74764_b("ench"))
      return false; 
    tagCompound.func_82580_o("ench");
    return true;
  }
  
  public static int getEnchantmentSum(ItemStack stack) {
    if (stack == null)
      return 0; 
    NBTTagList enchantmentTagList = stack.func_77986_q();
    if (enchantmentTagList == null)
      return 0; 
    int sum = 0;
    for (int i = 0; i < enchantmentTagList.func_74745_c(); i++) {
      NBTTagCompound tagCompound = enchantmentTagList.func_150305_b(i);
      short level = tagCompound.func_74765_d("lvl");
      sum += level;
    } 
    return sum;
  }
  
  public static void playSoundAround(EntityPlayer player, String name) {
    playSoundAround(player, name, 12);
  }
  
  public static void decrementCurrentItem(EntityPlayer player, ItemStack stack) {
    stack.field_77994_a--;
    if (stack.field_77994_a <= 0) {
      player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c] = null;
      MinecraftForge.EVENT_BUS.post((Event)new PlayerDestroyItemEvent(player, stack));
    } 
  }
  
  public static void damageCurrentItem(EntityPlayer player, ItemStack stack) {
    damageCurrentItem(player, stack, 1);
  }
  
  public static void damageCurrentItem(EntityPlayer player, ItemStack stack, int damage) {
    if (!stack.func_77973_b().func_77645_m())
      return; 
    stack.func_77964_b(stack.func_77960_j() + damage);
    if (stack.func_77960_j() == stack.func_77958_k()) {
      World world = player.field_70170_p;
      world.func_72956_a((Entity)player, "random.break", 0.8F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
      stack.field_77994_a--;
      if (stack.field_77994_a <= 0) {
        player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c] = null;
        MinecraftForge.EVENT_BUS.post((Event)new PlayerDestroyItemEvent(player, stack));
      } 
    } 
  }
  
  public static ItemStack getHead(String username) {
    ItemStack head = new ItemStack(Items.field_151144_bL, 1, 3);
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74778_a("SkullOwner", username);
    head.func_77982_d(compound);
    return head;
  }
  
  public static int translateSecondsToTicks(int seconds) {
    return seconds * 20;
  }
  
  public static int translateMillisecondsToTicks(long milliseconds) {
    return translateSecondsToTicks((int)(milliseconds / 1000L));
  }
  
  public static void playSoundAround(EntityPlayer player, String name, int range) {
    playSoundAround(name, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, range);
  }
  
  public static void playSoundAround(String name, World world, int x, int y, int z, int range) {
    PalaMod.getNetwork().sendToAllAround((IMessage)new PacketPlayCustomSound(name), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, x, y, z, range));
  }
  
  public static boolean hasInvulnerabilityEffect(EntityLivingBase player) {
    return hasPotionEffect(player, (Potion)PLuckyBlock.INVULNERABILITY);
  }
  
  public static boolean hasParrotEffect(EntityLivingBase player) {
    return hasPotionEffect(player, (Potion)PLuckyBlock.PARROT);
  }
  
  public static boolean hasPotionEffect(EntityLivingBase entity, Potion potion) {
    return entity.func_82165_m(potion.field_76415_H);
  }
  
  public static void removeItemsFromInventory(EntityPlayer player, ItemStack itemStack, int amount) {
    int amountToRemove = amount;
    for (int slot = 0; slot < player.field_71071_by.func_70302_i_(); slot++) {
      ItemStack stack = player.field_71071_by.func_70301_a(slot);
      if (stack != null && stack.func_77969_a(itemStack) && ItemStack.func_77970_a(stack, itemStack)) {
        int stackSize = stack.field_77994_a;
        if (stackSize > amountToRemove) {
          stackSize -= amountToRemove;
          stack.field_77994_a = stackSize;
          break;
        } 
        amountToRemove -= stackSize;
        player.field_71071_by.func_146026_a(itemStack.func_77973_b());
      } 
    } 
    player.field_71071_by.func_70296_d();
  }
  
  public static boolean hasItemsInInventory(EntityPlayer player, ItemStack itemStack, int amount) {
    int amountToFind = amount;
    for (int slot = 0; slot < player.field_71071_by.func_70302_i_(); slot++) {
      ItemStack stack = player.field_71071_by.func_70301_a(slot);
      if (stack != null && stack.func_77969_a(itemStack) && ItemStack.func_77970_a(stack, itemStack)) {
        amountToFind -= stack.field_77994_a;
        if (amountToFind <= 0)
          return true; 
      } 
    } 
    return false;
  }
  
  public static void startTimedHeliosEvent(EntityPlayerMP player, Class<? extends IHeliosLuckyEventWidget> clasz, long duration) {
    startTimedHeliosEvent(player, clasz, duration, System.currentTimeMillis());
  }
  
  public static void startTimedHeliosEvent(EntityPlayerMP player, Class<? extends IHeliosLuckyEventWidget> clasz, long duration, long expiration) {
    ModuleLuckyEvent.getInstance().getNetwork().sendTo((IMessage)new PacketLuckyEventHelios(clasz
          .getName(), duration, expiration), player);
  }
  
  public static void startHeliosEvent(Class<? extends IHeliosLuckyEventWidget> clasz, EntityPlayerMP player) {
    ModuleLuckyEvent.getInstance().getNetwork()
      .sendTo((IMessage)new PacketLuckyEventHelios(true, clasz.getName()), player);
  }
  
  public static void stopHeliosEvent(EntityPlayerMP player, Class<? extends IHeliosLuckyEventWidget> clasz) {
    ModuleLuckyEvent.getInstance().getNetwork().sendTo((IMessage)new PacketLuckyEventHelios(false, clasz
          .getName()), player);
  }
  
  public static void stopSound(EntityPlayerMP player) {
    PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound(null), player);
  }
  
  public static void playSound(EntityPlayerMP player, String name) {
    PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound(name), player);
  }
  
  public static void sendMessage(EntityPlayer player, String... messages) {
    sendMessage(player, true, messages);
  }
  
  public static void sendMessage(EntityPlayer player, boolean prefix, String... messages) {
    if (player == null || messages == null)
      return; 
    int length = messages.length;
    if (length == 0)
      return; 
    if (prefix) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§6§lLuckyBlock §8» §r" + ChatColorUtils.color(messages[0])));
    } else {
      player.func_145747_a((IChatComponent)new ChatComponentText(ChatColorUtils.color(messages[0])));
    } 
    for (int i = 1; i < messages.length; i++)
      player.func_145747_a((IChatComponent)new ChatComponentText(ChatColorUtils.color(messages[i]))); 
  }
  
  @SideOnly(Side.CLIENT)
  public static void sendClientMessage(String... messages) {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    sendMessage((EntityPlayer)entityClientPlayerMP, messages);
  }
  
  public static String[] splitDescription(String description) {
    int i = 0, j = 0, k = 0;
    String[] descriptions = new String[0];
    for (char c : description.toCharArray()) {
      if (k >= 40 && 
        c == ' ') {
        descriptions = add(descriptions, description.substring(j, i));
        j = i + 1;
        k = 0;
      } 
      k++;
      i++;
    } 
    if (j < description.length())
      descriptions = add(descriptions, description.substring(j)); 
    return descriptions;
  }
  
  public static <T> T[] add(T[] array, T element) {
    Class<?> type;
    if (array != null) {
      type = array.getClass();
    } else {
      if (element == null)
        throw new IllegalArgumentException("Arguments cannot both be null"); 
      type = element.getClass();
    } 
    T[] newArray = (T[])copyArrayGrow1(array, type);
    newArray[newArray.length - 1] = element;
    return newArray;
  }
  
  private static Object copyArrayGrow1(Object array, Class<?> newArrayComponentType) {
    if (array != null) {
      int arrayLength = Array.getLength(array);
      Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
      System.arraycopy(array, 0, newArray, 0, arrayLength);
      return newArray;
    } 
    return Array.newInstance(newArrayComponentType, 1);
  }
  
  @SideOnly(Side.SERVER)
  public static void broadcast(String... messages) {
    for (WorldServer worldServer : (MinecraftServer.func_71276_C()).field_71305_c) {
      for (Object player : ((World)worldServer).field_73010_i)
        sendMessage((EntityPlayer)player, messages); 
    } 
  }
  
  @SideOnly(Side.SERVER)
  public static Entity getEntityByUniqueId(List<?> loadedEntityList, UUID uniqueId) {
    for (Object object : loadedEntityList) {
      if (object instanceof Entity) {
        Entity entity = (Entity)object;
        if (entity.func_110124_au().equals(uniqueId))
          return entity; 
      } 
    } 
    return null;
  }
  
  @SideOnly(Side.SERVER)
  public static EntityItem getItemEntityByUniqueId(World world, UUID uniqueId) {
    for (Object object : world.field_72996_f) {
      if (object instanceof EntityItem) {
        EntityItem entity = (EntityItem)object;
        if (entity.func_110124_au().equals(uniqueId))
          return entity; 
      } 
    } 
    return null;
  }
  
  @SideOnly(Side.SERVER)
  public static EntityLivingBase getLivingEntityByUniqueId(World world, UUID uniqueId) {
    for (Object object : world.field_72996_f) {
      if (object instanceof EntityLivingBase) {
        EntityLivingBase entity = (EntityLivingBase)object;
        if (entity.func_110124_au().equals(uniqueId))
          return entity; 
      } 
    } 
    return null;
  }
  
  @SideOnly(Side.SERVER)
  public static EntityPlayerMP getPlayer(UUID uuid) {
    return getPlayer(uuid, getOnlinePlayers());
  }
  
  @SideOnly(Side.SERVER)
  public static EntityPlayerMP getPlayer(UUID uuid, List<EntityPlayerMP> players) {
    if (uuid == null || players == null || players.isEmpty())
      return null; 
    return players.stream()
      .filter(player -> player.func_110124_au().equals(uuid))
      .findFirst()
      .orElse(null);
  }
  
  @SideOnly(Side.SERVER)
  public static List<EntityPlayerMP> getOnlinePlayers() {
    return (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b;
  }
  
  @SideOnly(Side.CLIENT)
  public static void openUI(GuiScreen gui) {
    Minecraft.func_71410_x().func_147108_a(gui);
  }
  
  public static int getRandomValue(Random random, int min, int max) {
    if (random == null)
      random = new Random(); 
    return random.nextInt(max - min + 1) + min;
  }
  
  public static void performExplosion(World world, int x, int y, int z) {
    float radius = 6.0F;
    int distance = 10;
    world.func_72876_a(null, (x - 10), y, z, 6.0F, true);
    world.func_72876_a(null, (x + 10), y, z, 6.0F, true);
    world.func_72876_a(null, x, y, (z + 10), 6.0F, true);
    world.func_72876_a(null, x, y, (z - 10), 6.0F, true);
  }
  
  public static void removeEnchantments(EntityPlayerMP player, ItemStack heldItem, Enchantment... enchantment) {
    if (heldItem == null || enchantment == null || enchantment.length == 0)
      return; 
    NBTTagList nbttaglist = heldItem.func_77986_q();
    if (nbttaglist != null) {
      for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
        short id = nbttaglist.func_150305_b(i).func_74765_d("id");
        for (Enchantment enchant : enchantment) {
          if (enchant != null)
            if (id == enchant.field_77352_x) {
              nbttaglist.func_74744_a(i);
              break;
            }  
        } 
        if (nbttaglist.func_74745_c() == 0)
          heldItem.func_77978_p().func_82580_o("ench"); 
      } 
      player.field_71069_bz.func_75142_b();
    } 
  }
  
  public static void removeEnchantment(EntityPlayerMP player, ItemStack heldItem, Enchantment enchantment) {
    if (heldItem == null || enchantment == null)
      return; 
    NBTTagList nbttaglist = heldItem.func_77986_q();
    if (nbttaglist != null) {
      for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
        short id = nbttaglist.func_150305_b(i).func_74765_d("id");
        if (id == enchantment.field_77352_x) {
          nbttaglist.func_74744_a(i);
          break;
        } 
        if (nbttaglist.func_74745_c() == 0)
          heldItem.func_77978_p().func_82580_o("ench"); 
      } 
      player.field_71069_bz.func_75142_b();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthl\\utils\MonthlyUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */