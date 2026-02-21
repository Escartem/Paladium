package fr.paladium.palamod.modules.paladium.common.items.eventcommu;

import com.google.common.collect.Multimap;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.items.ItemBaseAxe;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.apache.commons.lang3.text.WordUtils;

public class ItemHalloweenAxe extends ItemBaseAxe {
  private static final String NBT_IDENTIFIER_DUPLI_MODIFIER = "dupli_modifier";
  
  private static final DecimalFormat df = new DecimalFormat("0.00");
  
  public ItemHalloweenAxe() {
    super(PToolMaterial.paladium);
    func_77656_e(-1);
    func_111206_d("palamod:halloween_axe");
    func_77655_b("halloween_axe");
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public Multimap func_111205_h() {
    Multimap multimap = super.func_111205_h();
    multimap.removeAll(SharedMonsterAttributes.field_111264_e.func_111108_a());
    multimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(Item.field_111210_e, "Tool modifier", 1000000.0D, 0));
    return multimap;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    String string = WordUtils.wrap(I18n.func_135052_a("item.halloween_axe.desc", new Object[0]), 40);
    String[] F1 = string.split(System.lineSeparator());
    list.addAll(Arrays.asList(F1));
    if (item.func_77942_o()) {
      list.add("");
      df.setRoundingMode(RoundingMode.DOWN);
      list.add("§a+" + df.format(item.func_77978_p().func_74769_h("dupli_modifier")) + "% Chance de dupliquer une citrouille§f");
    } 
  }
  
  public float getDigSpeed(ItemStack stack, Block block, int meta) {
    float speed = super.getDigSpeed(stack, block, meta);
    if (block instanceof net.minecraft.block.BlockPumpkin)
      return 1000.0F; 
    return speed;
  }
  
  public void func_77663_a(ItemStack stack, World world, Entity player, int p_77663_4_, boolean hasInHand) {
    if (!stack.func_77942_o() && !world.field_72995_K) {
      stack.field_77990_d = new NBTTagCompound();
      double randomValue = 10.0D + 20.0D * world.field_73012_v.nextDouble();
      stack.field_77990_d.func_74780_a("dupli_modifier", randomValue);
    } 
  }
  
  public boolean func_150894_a(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase player) {
    if (block instanceof net.minecraft.block.BlockPumpkin && !world.field_72995_K && 
      stack.func_77942_o()) {
      double randomValue = 100.0D * world.field_73012_v.nextDouble();
      double chance = stack.field_77990_d.func_74769_h("dupli_modifier");
      if (chance >= randomValue) {
        EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(block, 1));
        world.func_72838_d((Entity)entityItem);
      } 
    } 
    return super.func_150894_a(stack, world, block, x, y, z, player);
  }
  
  public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_) {
    float speed = super.func_150893_a(p_150893_1_, p_150893_2_);
    if (p_150893_2_ instanceof net.minecraft.block.BlockPumpkin)
      return 100.0F; 
    return speed;
  }
  
  public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\eventcommu\ItemHalloweenAxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */