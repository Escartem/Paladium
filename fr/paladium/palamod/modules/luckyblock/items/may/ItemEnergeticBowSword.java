package fr.paladium.palamod.modules.luckyblock.items.may;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemEnergeticBowSword extends ItemSword implements ITooltipInformations {
  public static final String[] bowPullIconNameArray = new String[] { "pulling_0", "pulling_1", "pulling_2" };
  
  @SideOnly(Side.CLIENT)
  private IIcon[] iconArray;
  
  public ItemEnergeticBowSword() {
    super(PToolMaterial.titane);
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("energetic_bow_sword");
    func_111206_d("palamod:energetic_bow_sword");
  }
  
  public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int itemInUseCount) {
    int j = func_77626_a(stack) - itemInUseCount;
    ArrowLooseEvent event = new ArrowLooseEvent(player, stack, j);
    MinecraftForge.EVENT_BUS.post((Event)event);
    if (event.isCanceled())
      return; 
    j = event.charge;
    boolean flag = (player.field_71075_bZ.field_75098_d || EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, stack) > 0);
    if (flag || player.field_71071_by.func_146028_b(Items.field_151032_g)) {
      float f = j / 20.0F;
      f = (f * f + f * 2.0F) / 3.0F;
      if (f < 0.1D)
        return; 
      if (f > 1.0F)
        f = 1.0F; 
      EntityArrow entityarrow = new EntityArrow(world, (EntityLivingBase)player, f * 2.0F);
      if (f == 1.0F)
        entityarrow.func_70243_d(true); 
      int k = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, stack);
      if (k > 0)
        entityarrow.func_70239_b(entityarrow.func_70242_d() + k * 0.5D + 0.5D); 
      int l = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, stack);
      if (l > 0)
        entityarrow.func_70240_a(l); 
      if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, stack) > 0)
        entityarrow.func_70015_d(100); 
      stack.func_77972_a(1, (EntityLivingBase)player);
      world.func_72956_a((Entity)player, "random.bow", 1.0F, 1.0F / (field_77697_d.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
      if (flag) {
        entityarrow.field_70251_a = 2;
      } else {
        player.field_71071_by.func_146026_a(Items.field_151032_g);
      } 
      if (!world.field_72995_K)
        world.func_72838_d((Entity)entityarrow); 
    } 
  }
  
  public ItemStack func_77654_b(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
    return p_77654_1_;
  }
  
  public int func_77626_a(ItemStack p_77626_1_) {
    return 72000;
  }
  
  public EnumAction func_77661_b(ItemStack p_77661_1_) {
    return EnumAction.bow;
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    ArrowNockEvent event = new ArrowNockEvent(player, stack);
    MinecraftForge.EVENT_BUS.post((Event)event);
    if (event.isCanceled())
      return event.result; 
    if (player.field_71075_bZ.field_75098_d || player.field_71071_by.func_146028_b(Items.field_151032_g))
      player.func_71008_a(stack, func_77626_a(stack)); 
    return stack;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister p_94581_1_) {
    this.field_77791_bV = p_94581_1_.func_94245_a(func_111208_A() + "_standby");
    this.iconArray = new IIcon[bowPullIconNameArray.length];
    for (int i = 0; i < this.iconArray.length; i++)
      this.iconArray[i] = p_94581_1_.func_94245_a(func_111208_A() + "_" + bowPullIconNameArray[i]); 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getItemIconForUseDuration(int p_94599_1_) {
    return this.iconArray[p_94599_1_];
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Ce que la technologie fait de mieux." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\may\ItemEnergeticBowSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */