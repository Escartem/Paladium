package fr.paladium.palamod.modules.paladium.common.items.weapons;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.EntityCustomArrow;
import fr.paladium.palamod.modules.paladium.common.items.ItemArrowBase;
import fr.paladium.palamod.modules.paladium.utils.BowHelper;
import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemPaladiumBow extends ItemBow implements ITooltipWiki {
  public static final String[] bowPullIconNames = new String[] { "paladium_bow_0", "paladium_bow_1", "paladium_bow_2" };
  
  public IIcon iconSpeed;
  
  public IIcon iconRange;
  
  private IIcon[] iconArray;
  
  public ItemPaladiumBow() {
    func_77656_e(900);
    func_77625_d(1);
    func_77655_b("paladiumbow");
    func_111206_d("palamod:weapons/paladium_bow");
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int time) {
    int maxItemUse = func_77626_a(stack) - time;
    ArrowLooseEvent event = new ArrowLooseEvent(player, stack, maxItemUse);
    if (event.isCanceled())
      return; 
    maxItemUse = event.charge;
    MinecraftForge.EVENT_BUS.post((Event)event);
    ItemStack ammo = checkForAmmo(player);
    if (ammo == null)
      return; 
    if (ammo.func_77973_b() instanceof ItemArrowBase) {
      boolean infiniteAmmo = (!player.field_71075_bZ.field_75098_d && EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, stack) <= 0);
      int type = ((ItemArrowBase)ammo.func_77973_b()).getEffect();
      float scaledItemUse = maxItemUse / 20.0F;
      if (!BowHelper.canApply(stack, 11) && EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, stack) == 0)
        scaledItemUse *= 2.0F; 
      scaledItemUse = (scaledItemUse * scaledItemUse + scaledItemUse * 2.0F) / 3.0F;
      if (scaledItemUse < 0.1D)
        return; 
      if (scaledItemUse > 1.0F)
        scaledItemUse = 1.0F; 
      float range = 1.5F;
      if (BowHelper.canApply(stack, 10))
        range = 2.5F; 
      EntityCustomArrow entityarrow = new EntityCustomArrow(world, (EntityLivingBase)player, scaledItemUse * range / 2.0F, type, infiniteAmmo);
      if (scaledItemUse == 1.0F)
        entityarrow.func_70243_d(true); 
      int powerLevel = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, stack);
      if (powerLevel > 0)
        entityarrow.func_70239_b(entityarrow.func_70242_d() + powerLevel * 0.5D + 0.5D); 
      int punchLevel = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, stack);
      if (punchLevel > 0)
        entityarrow.func_70240_a(punchLevel); 
      if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, stack) > 0)
        entityarrow.func_70015_d(100); 
      stack.func_77972_a(1, (EntityLivingBase)player);
      world.func_72956_a((Entity)player, "random.bow", 1.0F, 1.0F / (field_77697_d
          .nextFloat() * 0.4F + 1.2F) + scaledItemUse * 0.5F);
      if (infiniteAmmo)
        player.field_71071_by.func_146026_a(ammo.func_77973_b()); 
      if (!world.field_72995_K) {
        if (ammo.func_77973_b().equals(PPRegisterer.PPItems.MID_LIFE))
          entityarrow.getEntityData().func_74757_a("MidLife", true); 
        world.func_72838_d((Entity)entityarrow);
      } 
    } 
    if (ammo.func_77973_b() == Items.field_151032_g) {
      boolean infiniteAmmo = (!player.field_71075_bZ.field_75098_d && EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, stack) <= 0);
      float scaledItemUse = maxItemUse / 20.0F;
      if (!BowHelper.canApply(stack, 11) && EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, stack) == 0)
        scaledItemUse *= 2.0F; 
      scaledItemUse = (scaledItemUse * scaledItemUse + scaledItemUse * 2.0F) / 3.0F;
      if (scaledItemUse < 0.1D)
        return; 
      if (scaledItemUse > 1.0F)
        scaledItemUse = 1.0F; 
      float range = 3.0F;
      if (BowHelper.canApply(stack, 10))
        range = 4.0F; 
      EntityArrow entityarrow = new EntityArrow(world, (EntityLivingBase)player, scaledItemUse * range / 2.0F);
      if (scaledItemUse == 1.0F)
        entityarrow.func_70243_d(true); 
      int powerLevel = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, stack);
      if (powerLevel > 0)
        entityarrow.func_70239_b(entityarrow.func_70242_d() + powerLevel * 0.5D + 0.5D); 
      int punchLevel = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, stack);
      if (punchLevel > 0)
        entityarrow.func_70240_a(punchLevel); 
      if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, stack) > 0)
        entityarrow.func_70015_d(100); 
      stack.func_77972_a(1, (EntityLivingBase)player);
      world.func_72956_a((Entity)player, "random.bow", 1.0F, 1.0F / (field_77697_d
          .nextFloat() * 0.4F + 1.2F) + scaledItemUse * 0.5F);
      if (infiniteAmmo)
        player.field_71071_by.func_146026_a(ammo.func_77973_b()); 
      if (!world.field_72995_K) {
        entityarrow.field_70251_a = infiniteAmmo ? 1 : 2;
        world.func_72838_d((Entity)entityarrow);
      } 
    } 
  }
  
  private ItemStack checkForAmmo(EntityPlayer player) {
    InventoryPlayer inventory = player.field_71071_by;
    for (int i = 0; i < inventory.func_70302_i_(); i++) {
      ItemStack result = inventory.func_70301_a(i);
      if (result != null && (result
        .func_77973_b() instanceof ItemArrowBase || result.func_77973_b() == Items.field_151032_g))
        return result; 
    } 
    return null;
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
    int[] modifiers = BowHelper.getModifiers(stack);
    if (modifiers == null)
      return; 
    if (modifiers.length != 0) {
      list.add("§n Modifiers:");
      list.add("");
    } 
    for (int i = 0; i < modifiers.length; i++)
      list.add(BowHelper.getModifierName(modifiers[i])); 
  }
  
  public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player) {
    return stack;
  }
  
  public int func_77626_a(ItemStack stack) {
    int time = 72000;
    if (!BowHelper.canApply(stack, 11) && EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, stack) == 0)
      time = 31000; 
    return time;
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    ArrowNockEvent event = new ArrowNockEvent(player, stack);
    MinecraftForge.EVENT_BUS.post((Event)event);
    if (event.isCanceled())
      return event.result; 
    if (player.field_71075_bZ.field_75098_d || checkForAmmo(player) != null)
      player.func_71008_a(stack, func_77626_a(stack)); 
    return stack;
  }
  
  public int getRenderPasses(int metadata) {
    return 3;
  }
  
  public boolean func_77623_v() {
    return true;
  }
  
  public int func_77619_b() {
    return 1;
  }
  
  public boolean func_77662_d() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister icons) {
    this.field_77791_bV = icons.func_94245_a(func_111208_A());
    this.iconArray = new IIcon[bowPullIconNames.length];
    this.iconSpeed = icons.func_94245_a("palamod:upgrade_speed");
    this.iconRange = icons.func_94245_a("palamod:upgrade_range");
    for (int i = 0; i < this.iconArray.length; i++)
      this.iconArray[i] = icons.func_94245_a(func_111208_A() + "_" + i); 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_94599_c(int par1) {
    return this.iconArray[par1];
  }
  
  public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
    if (renderPass == 0) {
      if (player.func_71011_bu() == null)
        return this.field_77791_bV; 
      int pulling1 = 18;
      int pulling2 = 13;
      if (!BowHelper.canApply(stack, 11) && EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, stack) == 0) {
        pulling1 = 8;
        pulling2 = 7;
      } 
      int pulling = stack.func_77988_m() - useRemaining;
      if (pulling >= pulling1)
        return this.iconArray[2]; 
      if (pulling > pulling2)
        return this.iconArray[1]; 
      return this.iconArray[0];
    } 
    if (renderPass == 1) {
      if (!BowHelper.canApply(stack, 11) && EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, stack) == 0)
        return this.iconSpeed; 
      return null;
    } 
    if (renderPass == 2) {
      if (!BowHelper.canApply(stack, 10))
        return this.iconRange; 
      return null;
    } 
    return this.iconSpeed;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armes#3.-les-armes-en-paladium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\weapons\ItemPaladiumBow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */