package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.blocks.BaseBlockFlower;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileLuckyFlower;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LuckyBlockFlower extends BaseBlockFlower implements ITileEntityProvider, ITooltipWiki, ITooltipInformations {
  public LuckyBlockFlower(String unlocalizedName) {
    super(unlocalizedName);
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileLuckyFlower();
  }
  
  public boolean func_149716_u() {
    return true;
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
    if (item.func_77942_o() && !world.field_72995_K && 
      item.func_77978_p().func_74764_b("placedAt")) {
      long placedAt = item.func_77978_p().func_74763_f("placedAt");
      if (world.func_147438_o(x, y, z) instanceof TileLuckyFlower) {
        TileLuckyFlower te = (TileLuckyFlower)world.func_147438_o(x, y, z);
        te.func_145841_b(new NBTTagCompound());
        te.placedAt = placedAt;
      } 
    } 
    super.func_149689_a(world, x, y, z, entity, item);
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (!world.field_72995_K) {
      ItemStack item = new ItemStack(Item.func_150898_a(BlocksRegister.FLOWER_LUCKY));
      NBTTagCompound compound = new NBTTagCompound();
      if (world.func_147438_o(x, y, z) instanceof TileLuckyFlower) {
        TileLuckyFlower te = (TileLuckyFlower)world.func_147438_o(x, y, z);
        if (te != null)
          compound.func_74772_a("placedAt", te.placedAt); 
      } 
      item.func_77982_d(compound);
      PlayerUtils.dropItemStack((Entity)player, x, y, z, item);
    } 
    super.func_149681_a(world, x, y, z, meta, player);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> drops = new ArrayList<>();
    return drops;
  }
  
  protected void func_149642_a(World p_149642_1_, int p_149642_2_, int p_149642_3_, int p_149642_4_, ItemStack p_149642_5_) {}
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.-lucky-block-en-paladium-et-en-endium";
  }
  
  private boolean hasEnchantment(ItemStack item, Enchantment ench) {
    if (item == null)
      return false; 
    if (item.func_77986_q() != null && 
      EnchantmentHelper.func_82781_a(item).get(Integer.valueOf(ench.field_77352_x)) != null)
      return true; 
    return false;
  }
  
  protected ItemStack func_149644_j(int p_149644_1_) {
    return null;
  }
  
  public String[] getInformations(ItemStack item, EntityPlayer player, boolean advanced) {
    List<String> informations = new ArrayList<>();
    if (item.func_77978_p() == null || !item.func_77978_p().func_74764_b("placedAt")) {
      informations.add("Cette fleur n'a jamais été placée.");
    } else {
      long placedAt = item.func_77978_p().func_74763_f("placedAt");
      long diff = System.currentTimeMillis() - placedAt;
      long remaining = 259200000L - diff;
      if (remaining <= 0L) {
        informations.add("Cette fleur n'est plus valide.");
      } else {
        informations.add("Cette fleur a été placée il y a " + DurationConverter.fromMillisToString(diff));
        informations.add("Il reste " + DurationConverter.fromMillisToString(remaining) + " avant d'expirer.");
      } 
    } 
    return informations.<String>toArray(new String[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\LuckyBlockFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */