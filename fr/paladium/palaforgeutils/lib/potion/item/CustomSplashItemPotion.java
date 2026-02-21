package fr.paladium.palaforgeutils.lib.potion.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.potion.APotion;
import fr.paladium.palaforgeutils.lib.potion.entity.CustomEntityPotion;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class CustomSplashItemPotion extends Item {
  private APotion potion;
  
  private PotionEffect effect;
  
  private IIcon iconOverlay;
  
  private IIcon iconItem;
  
  public APotion getPotion() {
    return this.potion;
  }
  
  public PotionEffect getEffect() {
    return this.effect;
  }
  
  public IIcon getIconOverlay() {
    return this.iconOverlay;
  }
  
  public IIcon getIconItem() {
    return this.iconItem;
  }
  
  public CustomSplashItemPotion(APotion potion, PotionEffect effect) {
    this.potion = potion;
    this.effect = effect;
    func_77655_b("splash.potion." + potion.getId() + "." + effect.func_76459_b() + "." + effect.func_76458_c());
    func_77625_d(1);
    func_77656_e(0);
    func_77637_a(CreativeTabs.field_78038_k);
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!player.field_71075_bZ.field_75098_d)
      item.field_77994_a--; 
    world.func_72956_a((Entity)player, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
    if (!world.field_72995_K)
      world.func_72838_d((Entity)new CustomEntityPotion(world, (EntityLivingBase)player, item)); 
    return item;
  }
  
  public boolean func_77648_a(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_82790_a(ItemStack item, int pass) {
    return (pass > 0) ? 16777215 : this.potion.getColor().getRGB();
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77623_v() {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister iconRegister) {
    this.iconItem = iconRegister.func_94245_a("potion_bottle_splash");
    this.iconOverlay = iconRegister.func_94245_a("potion_overlay");
  }
  
  public IIcon func_77617_a(int damage) {
    return this.iconItem;
  }
  
  public IIcon func_77618_c(int damage, int pass) {
    return (pass == 0) ? this.iconOverlay : super.func_77618_c(damage, pass);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> lines, boolean flag) {
    String s1 = StatCollector.func_74838_a(this.effect.func_76453_d()).trim();
    Potion vanillaPotion = Potion.field_76425_a[this.effect.func_76456_a()];
    if (this.effect.func_76458_c() > 0)
      s1 = s1 + " " + StatCollector.func_74838_a("potion.potency." + this.effect.func_76458_c()).trim(); 
    if (this.effect.func_76459_b() > 20)
      s1 = s1 + " (" + Potion.func_76389_a(this.effect) + ")"; 
    if (vanillaPotion.func_76398_f()) {
      lines.add(EnumChatFormatting.RED + s1);
    } else {
      lines.add(EnumChatFormatting.GRAY + s1);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\potion\item\CustomSplashItemPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */