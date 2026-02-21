package fr.paladium.palaforgeutils.lib.potion.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.potion.APotion;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class CustomItemPotion extends Item {
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
  
  public CustomItemPotion(APotion potion, PotionEffect effect) {
    this.potion = potion;
    this.effect = effect;
    func_77655_b("potion." + potion.getId() + "." + effect.func_76459_b() + "." + effect.func_76458_c());
    func_77625_d(1);
    func_77656_e(0);
    func_77637_a(CreativeTabs.field_78038_k);
  }
  
  public ItemStack func_77654_b(ItemStack item, World world, EntityPlayer player) {
    if (!player.field_71075_bZ.field_75098_d)
      item.field_77994_a--; 
    if (!world.field_72995_K) {
      this.potion.consume(player, this.effect);
      player.func_70690_d(new PotionEffect(this.effect));
    } 
    if (!player.field_71075_bZ.field_75098_d) {
      if (item.field_77994_a <= 0)
        return new ItemStack(Items.field_151069_bo); 
      player.field_71071_by.func_70441_a(new ItemStack(Items.field_151069_bo));
    } 
    return item;
  }
  
  public int func_77626_a(ItemStack item) {
    return 32;
  }
  
  public EnumAction func_77661_b(ItemStack item) {
    return EnumAction.drink;
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    player.func_71008_a(item, func_77626_a(item));
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
    this.iconItem = iconRegister.func_94245_a("potion_bottle_drinkable");
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


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\potion\item\CustomItemPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */