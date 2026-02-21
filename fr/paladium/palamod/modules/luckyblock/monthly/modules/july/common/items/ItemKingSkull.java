package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemKingSkull extends Item implements ITooltipInformations {
  public static final String DESCRIPTION = "Creuse une zone de 16x16 autour de lui. Consomme 16 poudres d'os par utilisation";
  
  public static final String NAME = "king_skull";
  
  public static final String NBT_POWDER_FIELD = "powderAmount";
  
  private static final String CANT_USE_MESSAGE = "&cVous ne pouvez pas utiliser cet item ici où en combat !";
  
  private static final String SKULL_FULL_MESSAGE = "&cCe crâne est déjà plein !";
  
  private static final String SKULL_FILLED_MESSAGE = "&aVous venez d'ajouter une charge au crâne !";
  
  public static int REQUIRED_POWDER_AMOUNT = 16;
  
  public static int DURABILITY = 10;
  
  private final int requiredPowderAmount;
  
  private final int maxPowderAmount;
  
  private final String description;
  
  public ItemKingSkull(int durability, int requiredPowderAmount, String name, String description) {
    func_77655_b(name);
    func_111206_d("palamod:" + name);
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77627_a(true);
    setNoRepair();
    func_77656_e(durability);
    this.requiredPowderAmount = requiredPowderAmount;
    this.maxPowderAmount = durability * requiredPowderAmount;
    this.description = description;
  }
  
  public ItemKingSkull() {
    this(DURABILITY, REQUIRED_POWDER_AMOUNT, "king_skull", "Creuse une zone de 16x16 autour de lui. Consomme 16 poudres d'os par utilisation");
  }
  
  public boolean func_77616_k(ItemStack stack) {
    return false;
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return itemStack; 
    initNbt(itemStack);
    NBTTagCompound compound = itemStack.func_77978_p();
    int powderAmount = getPowderAmount(compound);
    if (player.func_70093_af()) {
      fillSkull(itemStack, compound, player);
      return itemStack;
    } 
    if (powderAmount < this.requiredPowderAmount) {
      MonthlyUtils.sendMessage(player, new String[] { "&cCe crâne doit contenir au minimum &6" + this.requiredPowderAmount + " poudres d'os &cpour être utilisé ! &7(&6Sneak + Click Droit pour le remplir&7)" });
      return itemStack;
    } 
    Cuboid cuboid = getCuboid(player);
    if (!canUseHere(cuboid, player)) {
      MonthlyUtils.sendMessage(player, new String[] { "&cVous ne pouvez pas utiliser cet item ici où en combat !" });
      return itemStack;
    } 
    cuboid.getLocations().forEach(location -> location.getWorld().func_147468_f((int)location.getX(), (int)location.getY(), (int)location.getZ()));
    compound.func_74768_a("powderAmount", powderAmount - this.requiredPowderAmount);
    itemStack.func_77982_d(compound);
    itemStack.func_77964_b(itemStack.func_77960_j() + 1);
    if (itemStack.func_77960_j() == itemStack.func_77958_k()) {
      world.func_72956_a((Entity)player, "random.break", 0.8F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
      itemStack.func_77972_a(1, (EntityLivingBase)player);
    } 
    return itemStack;
  }
  
  protected Cuboid getCuboid(EntityPlayer player) {
    return new Cuboid(new Location(player.field_70170_p, player.field_70165_t - 3.0D, player.field_70163_u - 3.0D, player.field_70161_v - 3.0D), new Location(player.field_70170_p, player.field_70165_t + 4.0D, player.field_70163_u + 4.0D, player.field_70161_v + 4.0D));
  }
  
  protected boolean canUseHere(Cuboid cuboid, EntityPlayer player) {
    if (LuckyBlockUtils.isInCombat(player))
      return false; 
    for (Location location : cuboid) {
      if (location.getY() <= 1.0D)
        return false; 
      if (!EventUtils.canInteract(player, location))
        return false; 
    } 
    return true;
  }
  
  private boolean hasBoneMeal(EntityPlayerMP player) {
    int count = 0;
    for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
      ItemStack stack = player.field_71071_by.func_70301_a(i);
      if (stack != null && stack.func_77973_b() == Items.field_151100_aR && stack.func_77960_j() == 15)
        count += stack.field_77994_a; 
    } 
    return (count >= this.requiredPowderAmount);
  }
  
  private void removeBoneMeal(EntityPlayerMP player) {
    int a = this.requiredPowderAmount;
    for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
      ItemStack it = player.field_71071_by.field_70462_a[i];
      if (it != null && a > 0 && 
        it.func_77969_a(new ItemStack(Items.field_151100_aR, 1, 15)) && 
        ItemStack.func_77970_a(it, new ItemStack(Items.field_151100_aR, 1, 15))) {
        int v = Math.min(Math.min(64, a), it.field_77994_a);
        it.field_77994_a -= v;
        if (it.field_77994_a <= 0) {
          player.field_71071_by.func_70299_a(i, null);
          player.field_71071_by.field_70459_e = true;
        } else {
          player.field_71071_by.func_70299_a(i, it.func_77946_l());
          player.field_71071_by.field_70459_e = true;
        } 
        a -= v;
      } 
    } 
  }
  
  private void fillSkull(ItemStack stack, NBTTagCompound compound, EntityPlayer player) {
    if (!hasBoneMeal((EntityPlayerMP)player)) {
      MonthlyUtils.sendMessage(player, new String[] { "&cVous devez avoir au minimum &6" + this.requiredPowderAmount + " poudres d'os &cpour utiliser cet item !" });
      return;
    } 
    int powderAmount = getPowderAmount(compound);
    if (powderAmount == this.maxPowderAmount) {
      MonthlyUtils.sendMessage(player, new String[] { "&cCe crâne est déjà plein !" });
      return;
    } 
    MonthlyUtils.sendMessage(player, new String[] { "&aVous venez d'ajouter une charge au crâne !" });
    removeBoneMeal((EntityPlayerMP)player);
    int newPowderAmount = Math.min(powderAmount + this.requiredPowderAmount, this.maxPowderAmount);
    compound.func_74768_a("powderAmount", newPowderAmount);
    stack.func_77982_d(compound);
  }
  
  private void initNbt(ItemStack stack) {
    if (stack.func_77978_p() == null) {
      NBTTagCompound nbt = new NBTTagCompound();
      nbt.func_74768_a("powderAmount", 0);
      stack.func_77982_d(nbt);
    } 
  }
  
  private int getPowderAmount(NBTTagCompound compound) {
    return compound.func_74762_e("powderAmount");
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return MonthlyUtils.splitDescription(this.description);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemKingSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */