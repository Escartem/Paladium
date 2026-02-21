package fr.paladium.palamod.modules.paladium.common.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemBucketBase extends ItemBucket implements ITooltipWiki {
  public ItemBucketBase(Block block, String name, String texture) {
    super(block);
    func_77655_b(name);
    func_77625_d(1);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_111206_d("palamod:fluids/" + texture);
    BlockDispenser.field_149943_a.func_82595_a(Items.field_151133_ar, new BehaviorDefaultDispenseItem() {
          private final BehaviorDefaultDispenseItem field_150840_b = new BehaviorDefaultDispenseItem();
          
          public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            Item item;
            EnumFacing enumfacing = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
            World world = p_82487_1_.func_82618_k();
            int i = p_82487_1_.func_82623_d() + enumfacing.func_82601_c();
            int j = p_82487_1_.func_82622_e() + enumfacing.func_96559_d();
            int k = p_82487_1_.func_82621_f() + enumfacing.func_82599_e();
            Block block = world.func_147439_a(i, j, k);
            Material material = block.func_149688_o();
            int l = world.func_72805_g(i, j, k);
            if (block == BlocksRegister.FLUIDS_SULFURICWATER) {
              ItemBucketBase itemBucketBase = ItemsRegister.BUCKET_SULFURIC;
            } else if (block == BlocksRegister.FLUIDS_ANGELICWATER) {
              ItemBucketBase itemBucketBase = ItemsRegister.BUCKET_ANGELIC;
            } else if (Material.field_151586_h.equals(material) && l == 0) {
              item = Items.field_151131_as;
            } else {
              if (!Material.field_151587_i.equals(material) || l != 0)
                return super.func_82487_b(p_82487_1_, p_82487_2_); 
              item = Items.field_151129_at;
            } 
            world.func_147468_f(i, j, k);
            if (--p_82487_2_.field_77994_a == 0) {
              p_82487_2_.func_150996_a(item);
              p_82487_2_.field_77994_a = 1;
            } else if (((TileEntityDispenser)p_82487_1_.func_150835_j()).func_146019_a(new ItemStack(item)) < 0) {
              this.field_150840_b.func_82482_a(p_82487_1_, new ItemStack(item));
            } 
            return p_82487_2_;
          }
        });
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-la-construction#3.-les-sources";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemBucketBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */