package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.alchimiste.common.init.EnchantMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import fr.paladium.palamod.modules.paladium.network.data.PaladiumPlayer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemPerfectHelmet extends ItemArmor {
  public static final String NAME = "perfect_helmet";
  
  public static final int ENCHANTMENTS_SIZE = 8;
  
  public ItemPerfectHelmet() {
    super(PArmorMaterial.paladium, 0, 0);
    func_77655_b("perfect_helmet");
    func_111206_d("palamod:paladium_helmet");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
    super.onArmorTick(world, player, stack);
    if (!world.field_72995_K && EnchantmentHelper.func_82781_a(stack).size() < 8)
      EnchantmentHelper.func_82782_a(getEnchantments(), stack); 
    if (player.field_70163_u > 0.0D) {
      player.field_70143_R = 0.0F;
      if (world.field_72995_K) {
        int l = MathHelper.func_76128_c(player.field_70165_t);
        int i1 = MathHelper.func_76128_c(player.field_70163_u + 1.0D);
        int j1 = MathHelper.func_76128_c(player.field_70161_v);
        if (world.func_147439_a(l, i1, j1).func_149688_o().func_76220_a())
          if (GameSettings.func_100015_a((Minecraft.func_71410_x()).field_71474_y.field_74314_A)) {
            player.field_70181_x = 0.1D;
          } else {
            player.field_70181_x *= 0.6D;
          }  
      } 
    } 
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    String texture = "paladium_armor";
    if (func_82812_d() == PArmorMaterial.paladium) {
      int damage = 0;
      try {
        damage = PaladiumPlayer.get(entity).getPaladiumArmorType();
      } catch (Exception exception) {}
      if (!Objects.equals("paladium_armor", "paladium_green_armor") && 
        !Objects.equals("paladium_armor", "halloween_armor"))
        switch (damage % 12) {
          case 1:
            texture = "paladium_custom";
            break;
          case 2:
            texture = "paladium_custom_2";
            break;
          case 3:
            texture = "paladium_custom_3";
            break;
          case 4:
            texture = "paladium_custom_4";
            break;
          case 5:
            texture = "paladium_custom_5";
            break;
          case 6:
            texture = "paladium_custom_6";
            break;
          case 7:
            texture = "paladium_custom_7";
            break;
          case 8:
            texture = "paladium_custom_8";
            break;
          case 9:
            texture = "paladium_custom_9";
            break;
          case 10:
            texture = "paladium_custom_10";
            break;
          case 11:
            texture = "paladium_custom_11";
            break;
        }  
    } 
    if (slot == 2)
      return "palamod:textures/models/" + texture + "_2.png"; 
    return "palamod:textures/models/" + texture + "_1.png";
  }
  
  public static ItemStack create() {
    ItemStack stack = new ItemStack(ItemsRegister.PERFECT_HELMET);
    EnchantmentHelper.func_82782_a(getEnchantments(), stack);
    return stack;
  }
  
  public static Map<Integer, Integer> getEnchantments() {
    return new HashMap<Integer, Integer>() {
      
      };
  }
  
  public void func_77622_d(ItemStack itemStack, World world, EntityPlayer player) {
    super.func_77622_d(itemStack, world, player);
    itemStack.func_77966_a(Enchantment.field_77341_i, 1);
    itemStack.func_77966_a(Enchantment.field_77340_h, 3);
    itemStack.func_77966_a(Enchantment.field_77332_c, 4);
    itemStack.func_77966_a(Enchantment.field_77329_d, 4);
    itemStack.func_77966_a(Enchantment.field_77327_f, 4);
    itemStack.func_77966_a(Enchantment.field_77328_g, 4);
    itemStack.func_77966_a(Enchantment.field_77347_r, 3);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\items\ItemPerfectHelmet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */