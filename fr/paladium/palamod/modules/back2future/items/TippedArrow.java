package fr.paladium.palamod.modules.back2future.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModItems;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.dispenser.DispenserBehaviourTippedArrow;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class TippedArrow extends Item implements IConfigurable {
  @SideOnly(Side.CLIENT)
  private IIcon tipIcon;
  
  public TippedArrow() {
    func_111206_d("tipped_arrow");
    func_77655_b(Utils.getUnlocalisedName("tipped_arrow"));
    func_77637_a(Back2Future.enableTippedArrows ? Back2Future.creativeTab : null);
    if (Back2Future.enableTippedArrows)
      BlockDispenser.field_149943_a.func_82595_a(this, new DispenserBehaviourTippedArrow()); 
  }
  
  public static PotionEffect getEffect(ItemStack stack) {
    if (stack.func_77942_o() && stack
      .func_77978_p().func_150297_b("Potion", 10)) {
      NBTTagCompound nbt = stack.func_77978_p().func_74775_l("Potion");
      return PotionEffect.func_82722_b(nbt);
    } 
    return null;
  }
  
  public static ItemStack setEffect(ItemStack stack, Potion potion, int duration, int potency) {
    stack.func_77982_d(new NBTTagCompound());
    NBTTagCompound nbt = new NBTTagCompound();
    stack.func_77978_p().func_74782_a("Potion", (NBTBase)nbt);
    PotionEffect effect = new PotionEffect(potion.func_76396_c(), potion.func_76403_b() ? 1 : duration, potency);
    effect.func_82719_a(nbt);
    return stack;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_150895_a(Item item, CreativeTabs tab, List<ItemStack> list) {
    List<ItemStack> potions = new ArrayList<>();
    ModItems.lingering_potion.func_150895_a(ModItems.lingering_potion, tab, potions);
    for (ItemStack potion : potions) {
      List<PotionEffect> effects = PotionHelper.func_77917_b(potion.func_77960_j(), false);
      if (effects != null && !effects.isEmpty())
        for (PotionEffect effect : effects)
          list.add(setEffect(new ItemStack(this), Potion.field_76425_a[effect.func_76456_a()], effect
                .func_76459_b() / 2, effect.func_76458_c()));  
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister reg) {
    this.field_77791_bV = reg.func_94245_a(func_111208_A() + "_base");
    this.tipIcon = reg.func_94245_a(func_111208_A() + "_head");
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77623_v() {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_82790_a(ItemStack stack, int pass) {
    PotionEffect effect = getEffect(stack);
    if (effect == null || effect.func_76456_a() < 0 || effect
      .func_76456_a() >= Potion.field_76425_a.length)
      return super.func_82790_a(stack, pass); 
    return (pass == 0) ? Potion.field_76425_a[effect.func_76456_a()].func_76401_j() : super
      .func_82790_a(stack, pass);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(ItemStack stack, int pass) {
    return (pass == 0) ? this.tipIcon : super.getIcon(stack, pass);
  }
  
  public String func_77667_c(ItemStack stack) {
    PotionEffect effect = getEffect(stack);
    if (effect == null || effect.func_76456_a() < 0 || effect
      .func_76456_a() >= Potion.field_76425_a.length)
      return super.func_77667_c(stack); 
    Potion potion = Potion.field_76425_a[effect.func_76456_a()];
    return "tipped_arrow." + potion.func_76393_a();
  }
  
  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack stack, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
    if (stack.func_77960_j() == 0) {
      PotionEffect potioneffect = getEffect(stack);
      if (potioneffect == null)
        return; 
      String s1 = StatCollector.func_74838_a(potioneffect.func_76453_d()).trim();
      Potion potion = Potion.field_76425_a[potioneffect.func_76456_a()];
      Map map = potion.func_111186_k();
      if (map != null && map.size() > 0) {
        Iterator<Map.Entry> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
          Map.Entry entry = iterator.next();
          AttributeModifier attributemodifier = (AttributeModifier)entry.getValue();
          AttributeModifier attributeModifier1 = new AttributeModifier(attributemodifier.func_111166_b(), potion.func_111183_a(potioneffect.func_76458_c(), attributemodifier), attributemodifier.func_111169_c());
        } 
      } 
      if (potioneffect.func_76458_c() > 0)
        s1 = s1 + " " + StatCollector.func_74838_a("potion.potency." + potioneffect.func_76458_c()).trim(); 
      if (potioneffect.func_76459_b() > 20)
        s1 = s1 + " (" + Potion.func_76389_a(potioneffect) + ")"; 
      if (potion.func_76398_f()) {
        p_77624_3_.add(EnumChatFormatting.RED + s1);
      } else {
        p_77624_3_.add(EnumChatFormatting.GRAY + s1);
      } 
    } 
  }
  
  public boolean isEnabled() {
    return Back2Future.enableTippedArrows;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\TippedArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */