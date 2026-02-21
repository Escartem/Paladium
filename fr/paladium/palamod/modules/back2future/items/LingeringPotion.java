package fr.paladium.palamod.modules.back2future.items;

import com.google.common.collect.HashMultimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.dispenser.DispenserBehaviourLingeringPotion;
import fr.paladium.palamod.modules.back2future.entities.EntityLingeringPotion;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LingeringPotion extends ItemPotion implements IConfigurable {
  @SideOnly(Side.CLIENT)
  private IIcon bottle;
  
  public LingeringPotion() {
    func_111206_d("potion");
    func_77655_b(Utils.getUnlocalisedName("lingering_potion"));
    func_77637_a(Back2Future.enableLingeringPotions ? Back2Future.creativeTab : null);
    if (Back2Future.enableLingeringPotions)
      BlockDispenser.field_149943_a.func_82595_a(this, new DispenserBehaviourLingeringPotion()); 
  }
  
  public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player) {
    return stack;
  }
  
  public int func_77626_a(ItemStack stack) {
    return 0;
  }
  
  public EnumAction func_77661_b(ItemStack stack) {
    return EnumAction.none;
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (!player.field_71075_bZ.field_75098_d)
      stack.field_77994_a--; 
    world.func_72956_a((Entity)player, "random.bow", 0.5F, 0.4F / (field_77697_d
        .nextFloat() * 0.4F + 0.8F));
    if (!world.field_72995_K)
      world.func_72838_d((Entity)new EntityLingeringPotion(world, (EntityLivingBase)player, stack)); 
    return stack;
  }
  
  public List<PotionEffect> func_77834_f(int meta) {
    List<PotionEffect> effects = new ArrayList<>();
    List<PotionEffect> effects2 = super.func_77834_f(meta);
    if (effects2 != null && !effects2.isEmpty())
      for (PotionEffect effect : effects2) {
        PotionEffect e;
        if (Potion.field_76425_a[effect.func_76456_a()].func_76403_b()) {
          e = new PotionEffect(effect);
        } else {
          e = new PotionEffect(effect.func_76456_a(), effect.func_76459_b() / 4, effect.func_76458_c(), effect.func_82720_e());
          e.setCurativeItems(effect.getCurativeItems());
        } 
        effects.add(e);
      }  
    return effects;
  }
  
  public List<PotionEffect> func_77832_l(ItemStack stack) {
    if (stack.func_77942_o() && stack.func_77978_p().func_150297_b("CustomPotionEffects", 9)) {
      List<PotionEffect> list = new ArrayList<>();
      NBTTagList nbttaglist = stack.func_77978_p().func_150295_c("CustomPotionEffects", 10);
      for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
        NBTTagCompound nbt = nbttaglist.func_150305_b(i);
        PotionEffect potioneffect = PotionEffect.func_82722_b(nbt);
        if (potioneffect != null)
          list.add(potioneffect); 
      } 
      return list;
    } 
    return func_77834_f(stack.func_77960_j());
  }
  
  public String func_77653_i(ItemStack stack) {
    if (stack.func_77960_j() == 0)
      return StatCollector.func_74838_a("item.emptyPotion.name").trim(); 
    String s = StatCollector.func_74838_a("potion.prefix.lingering").trim() + " ";
    List<PotionEffect> list = func_77832_l(stack);
    if (list != null && !list.isEmpty()) {
      String str = ((PotionEffect)list.get(0)).func_76453_d();
      str = str + ".postfix";
      return s + StatCollector.func_74838_a(str).trim();
    } 
    String s1 = PotionHelper.func_77905_c(stack.func_77960_j());
    return StatCollector.func_74838_a(s1).trim() + " " + super
      .func_77653_i(stack);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean isComplex) {
    if (stack.func_77960_j() == 0)
      return; 
    List<PotionEffect> effects = func_77832_l(stack);
    HashMultimap<String, AttributeModifier> attributes = HashMultimap.create();
    if (effects == null || effects.isEmpty()) {
      String s = StatCollector.func_74838_a("potion.empty").trim();
      list.add(EnumChatFormatting.GRAY + s);
    } else {
      for (PotionEffect potioneffect : effects) {
        String s1 = StatCollector.func_74838_a(potioneffect.func_76453_d()).trim();
        Potion potion = Potion.field_76425_a[potioneffect.func_76456_a()];
        Map<IAttribute, AttributeModifier> map = potion.func_111186_k();
        if (map != null && map.size() > 0)
          for (Map.Entry<IAttribute, AttributeModifier> entry : map.entrySet()) {
            AttributeModifier attributemodifier = entry.getValue();
            AttributeModifier attributemodifier1 = new AttributeModifier(attributemodifier.func_111166_b(), potion.func_111183_a(potioneffect.func_76458_c(), attributemodifier), attributemodifier.func_111169_c());
            attributes.put(((IAttribute)entry.getKey()).func_111108_a(), attributemodifier1);
          }  
        if (potioneffect.func_76458_c() > 0)
          s1 = s1 + " " + StatCollector.func_74838_a("potion.potency." + potioneffect.func_76458_c()).trim(); 
        if (potioneffect.func_76459_b() > 20)
          s1 = s1 + " (" + Potion.func_76389_a(potioneffect) + ")"; 
        if (potion.func_76398_f()) {
          list.add(EnumChatFormatting.RED + s1);
          continue;
        } 
        list.add(EnumChatFormatting.GRAY + s1);
      } 
    } 
    if (!attributes.isEmpty()) {
      list.add("");
      list.add(EnumChatFormatting.DARK_PURPLE + 
          StatCollector.func_74838_a("potion.effects.whenDrank"));
      for (Map.Entry<String, AttributeModifier> entry1 : (Iterable<Map.Entry<String, AttributeModifier>>)attributes.entries()) {
        double d1;
        AttributeModifier attributemodifier2 = entry1.getValue();
        double d0 = attributemodifier2.func_111164_d();
        if (attributemodifier2.func_111169_c() != 1 && attributemodifier2.func_111169_c() != 2) {
          d1 = attributemodifier2.func_111164_d();
        } else {
          d1 = attributemodifier2.func_111164_d() * 100.0D;
        } 
        if (d0 > 0.0D) {
          list.add(EnumChatFormatting.BLUE + StatCollector.func_74837_a("attribute.modifier.plus." + attributemodifier2
                .func_111169_c(), new Object[] { ItemStack.field_111284_a
                  .format(d1), 
                  StatCollector.func_74838_a("attribute.name." + (String)entry1.getKey()) }));
          continue;
        } 
        if (d0 < 0.0D) {
          d1 *= -1.0D;
          list.add(EnumChatFormatting.RED + StatCollector.func_74837_a("attribute.modifier.take." + attributemodifier2
                .func_111169_c(), new Object[] { ItemStack.field_111284_a
                  .format(d1), 
                  StatCollector.func_74838_a("attribute.name." + (String)entry1.getKey()) }));
        } 
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void func_150895_a(Item item, CreativeTabs tab, List<ItemStack> list) {
    List<ItemStack> potions = new ArrayList<>();
    super.func_150895_a(item, tab, potions);
    for (ItemStack potion : potions) {
      if (!func_77831_g(potion.func_77960_j()))
        list.add(potion); 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int meta) {
    return this.bottle;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister reg) {
    super.func_94581_a(reg);
    this.bottle = reg.func_94245_a(func_111208_A() + "_bottle_lingering");
  }
  
  @SideOnly(Side.CLIENT)
  public boolean hasEffect(ItemStack stack, int pass) {
    return (super.hasEffect(stack, pass) && pass == 0);
  }
  
  public boolean isEnabled() {
    return Back2Future.enableLingeringPotions;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\LingeringPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */