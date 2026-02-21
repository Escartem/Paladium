package fr.paladium.palamod.modules.paladium.common.items.potion;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.EntitySplashPotion;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemSplashPotion extends Item {
  PotionEffect effect;
  
  private String[] type = new String[] { "SicknessPotion", "WebPotion", "CorruptedPotion" };
  
  private IIcon[] iconArray;
  
  public ItemSplashPotion(String name, String texturename, PotionEffect effect, int damageValue) {
    func_77625_d(1);
    func_77637_a((CreativeTabs)Registry.POTION_TAB);
    func_77627_a(true);
    this.effect = effect;
    func_77655_b(name);
    func_111206_d("palamod:potion/" + texturename);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (!world.field_72995_K)
      world.func_72838_d((Entity)new EntitySplashPotion(world, player, getDamage(stack))); 
    return stack;
  }
  
  public int func_77647_b(int metadata) {
    return metadata;
  }
  
  public String func_77667_c(ItemStack stack) {
    int metadata = stack.func_77960_j();
    if (metadata > this.type.length || metadata < 0)
      metadata = 0; 
    return "splashpotion." + this.type[metadata].toLowerCase();
  }
  
  public void func_94581_a(IIconRegister iconregister) {
    this.iconArray = new IIcon[this.type.length];
    for (int i = 0; i < this.type.length; i++)
      this.iconArray[i] = iconregister.func_94245_a("palamod:potion/" + this.type[i]); 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int metadata) {
    return (metadata < this.type.length && metadata >= 0) ? this.iconArray[metadata] : this.iconArray[0];
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\potion\ItemSplashPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */