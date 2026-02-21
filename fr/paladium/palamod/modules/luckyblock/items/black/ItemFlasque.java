package fr.paladium.palamod.modules.luckyblock.items.black;

import com.google.common.collect.HashMultimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemFlasque extends ItemPotion implements ITooltipWiki {
  private Map<Integer, List<?>> effectCache = new HashMap<>();
  
  @SideOnly(Side.CLIENT)
  private IIcon flasqueIcon;
  
  @SideOnly(Side.CLIENT)
  private IIcon overlayIcon;
  
  public ItemFlasque() {
    func_77656_e(6);
    func_77625_d(1);
    func_77627_a(false);
  }
  
  public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player) {
    if (!world.field_72995_K && stack.func_77942_o() && stack.field_77990_d.func_74764_b("empty") && 
      !stack.field_77990_d.func_74767_n("empty")) {
      if (!player.field_71075_bZ.field_75098_d && 
        func_77612_l() > getDamage(stack)) {
        if (func_77612_l() - getDamage(stack) == 1)
          setFlasqueEmpty(stack, true); 
        stack.func_77964_b(stack.func_77960_j() + 1);
      } 
      if (!world.field_72995_K) {
        List<?> list = func_77832_l(stack);
        if (list != null && !list.isEmpty())
          for (Object e : list)
            player.func_70690_d(new PotionEffect((PotionEffect)e));  
      } 
    } 
    return stack;
  }
  
  public void func_77622_d(ItemStack stack, World world, EntityPlayer player) {
    if (stack.func_77942_o())
      stack.field_77990_d = new NBTTagCompound(); 
    stack.field_77990_d.func_74757_a("empty", true);
    stack.field_77990_d.func_74768_a("potion_effects", 0);
  }
  
  public int func_77626_a(ItemStack p_77626_1_) {
    return 8;
  }
  
  public static int getFlasquePotionEffects(ItemStack flasque) {
    if (flasque.func_77942_o() && flasque.field_77990_d.func_74764_b("potion_effects"))
      return flasque.field_77990_d.func_74762_e("potion_effects"); 
    return 0;
  }
  
  public static void setFlasquePotionEffects(ItemStack flasque, int i) {
    if (!flasque.func_77942_o())
      flasque.field_77990_d = new NBTTagCompound(); 
    flasque.field_77990_d.func_74768_a("potion_effects", i);
  }
  
  public static void setFlasqueEmpty(ItemStack flasque, boolean b) {
    if (!flasque.func_77942_o())
      flasque.field_77990_d = new NBTTagCompound(); 
    flasque.field_77990_d.func_74757_a("empty", b);
    if (b) {
      flasque.field_77990_d.func_74768_a("potion_effects", 0);
    } else {
      flasque.func_77964_b(0);
    } 
  }
  
  public static boolean isEmpty(ItemStack flasque) {
    if (flasque.func_77942_o() && flasque.field_77990_d.func_74764_b("empty"))
      return flasque.field_77990_d.func_74767_n("empty"); 
    return false;
  }
  
  public List func_77832_l(ItemStack p_77832_1_) {
    int i = getFlasquePotionEffects(p_77832_1_);
    List<?> list = this.effectCache.get(Integer.valueOf(i));
    if (list == null) {
      list = PotionHelper.func_77917_b(i, false);
      this.effectCache.put(Integer.valueOf(i), list);
    } 
    return list;
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("empty"))
      if (!stack.field_77990_d.func_74767_n("empty")) {
        player.func_71008_a(stack, func_77626_a(stack));
      } else {
        MovingObjectPosition movingobjectposition = func_77621_a(world, player, true);
        if (movingobjectposition == null)
          return stack; 
        if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
          int i = movingobjectposition.field_72311_b;
          int j = movingobjectposition.field_72312_c;
          int k = movingobjectposition.field_72309_d;
          if (world.func_147439_a(i, j, k).func_149688_o() == Material.field_151586_h)
            setFlasqueEmpty(stack, false); 
        } 
        return stack;
      }  
    return stack;
  }
  
  public String func_77653_i(ItemStack p_77653_1_) {
    if (getFlasquePotionEffects(p_77653_1_) == 0)
      return StatCollector.func_74838_a("item.flasque.name").trim(); 
    String s = "";
    List<PotionEffect> list = func_77832_l(p_77653_1_);
    if (list != null && !list.isEmpty()) {
      String str = ((PotionEffect)list.get(0)).func_76453_d();
      str = str + ".postfix";
      return s + StatCollector.func_74838_a(str).trim();
    } 
    String s1 = PotionHelper.func_77905_c(getFlasquePotionEffects(p_77653_1_));
    return StatCollector.func_74838_a(s1).trim() + " " + super
      .func_77653_i(p_77653_1_);
  }
  
  public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List<ItemStack> p_150895_3_) {
    p_150895_3_.add(new ItemStack(p_150895_1_, 1, 0));
  }
  
  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
    if (getFlasquePotionEffects(p_77624_1_) != 0) {
      List list1 = func_77832_l(p_77624_1_);
      HashMultimap hashmultimap = HashMultimap.create();
      if (list1 != null && !list1.isEmpty()) {
        Iterator<PotionEffect> iterator1 = list1.iterator();
        while (iterator1.hasNext()) {
          PotionEffect potioneffect = iterator1.next();
          String s1 = StatCollector.func_74838_a(potioneffect.func_76453_d()).trim();
          Potion potion = Potion.field_76425_a[potioneffect.func_76456_a()];
          Map map = potion.func_111186_k();
          if (map != null && map.size() > 0) {
            Iterator<Map.Entry> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
              Map.Entry entry = iterator.next();
              AttributeModifier attributemodifier = (AttributeModifier)entry.getValue();
              AttributeModifier attributemodifier1 = new AttributeModifier(attributemodifier.func_111166_b(), potion.func_111183_a(potioneffect.func_76458_c(), attributemodifier), attributemodifier.func_111169_c());
              hashmultimap.put(((IAttribute)entry.getKey()).func_111108_a(), attributemodifier1);
            } 
          } 
          if (potioneffect.func_76458_c() > 0)
            s1 = s1 + " " + StatCollector.func_74838_a("potion.potency." + potioneffect.func_76458_c()).trim(); 
          if (potioneffect.func_76459_b() > 20)
            s1 = s1 + " (" + Potion.func_76389_a(potioneffect) + ")"; 
          if (potion.func_76398_f()) {
            p_77624_3_.add(EnumChatFormatting.RED + s1);
            continue;
          } 
          p_77624_3_.add(EnumChatFormatting.GRAY + s1);
        } 
      } else {
        String s = StatCollector.func_74838_a("potion.empty").trim();
        p_77624_3_.add(EnumChatFormatting.GRAY + s);
      } 
      if (!hashmultimap.isEmpty()) {
        p_77624_3_.add("");
        p_77624_3_.add(EnumChatFormatting.DARK_PURPLE + 
            StatCollector.func_74838_a("potion.effects.whenDrank"));
        Iterator<Map.Entry> iterator1 = hashmultimap.entries().iterator();
        while (iterator1.hasNext()) {
          double d1;
          Map.Entry entry1 = iterator1.next();
          AttributeModifier attributemodifier2 = (AttributeModifier)entry1.getValue();
          double d0 = attributemodifier2.func_111164_d();
          if (attributemodifier2.func_111169_c() != 1 && attributemodifier2.func_111169_c() != 2) {
            d1 = attributemodifier2.func_111164_d();
          } else {
            d1 = attributemodifier2.func_111164_d() * 100.0D;
          } 
          if (d0 > 0.0D) {
            p_77624_3_.add(EnumChatFormatting.BLUE + StatCollector.func_74837_a("attribute.modifier.plus." + attributemodifier2
                  .func_111169_c(), new Object[] { ItemStack.field_111284_a
                    .format(d1), 
                    StatCollector.func_74838_a("attribute.name." + (String)entry1.getKey()) }));
            continue;
          } 
          if (d0 < 0.0D) {
            d1 *= -1.0D;
            p_77624_3_.add(EnumChatFormatting.RED + StatCollector.func_74837_a("attribute.modifier.take." + attributemodifier2
                  .func_111169_c(), new Object[] { ItemStack.field_111284_a
                    .format(d1), 
                    StatCollector.func_74838_a("attribute.name." + (String)entry1.getKey()) }));
          } 
        } 
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister p_94581_1_) {
    this.flasqueIcon = p_94581_1_.func_94245_a("palamod:flasque");
    this.overlayIcon = p_94581_1_.func_94245_a("palamod:flasque_overlay");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int damage) {
    return this.flasqueIcon;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77618_c(int p_77618_1_, int p_77618_2_) {
    return (p_77618_2_ == 0) ? this.overlayIcon : func_77617_a(p_77618_1_);
  }
  
  public IIcon getIcon(ItemStack stack, int pass) {
    if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("empty") && stack.field_77990_d.func_74767_n("empty"))
      return func_77617_a(stack.func_77960_j()); 
    return func_77618_c(stack.func_77960_j(), pass);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77650_f(ItemStack stack) {
    return func_77617_a(getFlasquePotionEffects(stack));
  }
  
  @SideOnly(Side.CLIENT)
  public int func_82790_a(ItemStack p_82790_1_, int p_82790_2_) {
    return (p_82790_2_ > 0) ? 16777215 : 
      PotionHelper.func_77915_a(getFlasquePotionEffects(p_82790_1_), false);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#3.-lucky-block-noir";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\black\ItemFlasque.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */