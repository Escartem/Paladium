package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks.BlockCorruptedBed;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBed;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ItemCorruptedBed extends ItemBed implements ITooltipInformations {
  public static final String NAME = "item_corrupted_bed";
  
  private static final String DESCRIPTION = "DESC.TXT : ITEM:BED que ne peut être ACTION:USE que dans le WORLD:NETHER. ACTION:EXPLODE si ACTION:USE dans le WORLD:OVERWORLD.";
  
  public ItemCorruptedBed() {
    func_77655_b("item_corrupted_bed");
    func_111206_d("palamod:item_corrupted_bed");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
    if (p_77648_3_.field_72995_K)
      return true; 
    if (p_77648_7_ != 1)
      return false; 
    p_77648_5_++;
    BlockCorruptedBed blockbed = (BlockCorruptedBed)BlocksRegister.CORRUPTED_BED;
    int i1 = MathHelper.func_76128_c((p_77648_2_.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    byte b0 = 0;
    byte b1 = 0;
    if (i1 == 0)
      b1 = 1; 
    if (i1 == 1)
      b0 = -1; 
    if (i1 == 2)
      b1 = -1; 
    if (i1 == 3)
      b0 = 1; 
    if (p_77648_2_.func_82247_a(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_) && p_77648_2_.func_82247_a(p_77648_4_ + b0, p_77648_5_, p_77648_6_ + b1, p_77648_7_, p_77648_1_)) {
      if (p_77648_3_.func_147437_c(p_77648_4_, p_77648_5_, p_77648_6_) && p_77648_3_.func_147437_c(p_77648_4_ + b0, p_77648_5_, p_77648_6_ + b1) && World.func_147466_a((IBlockAccess)p_77648_3_, p_77648_4_, p_77648_5_ - 1, p_77648_6_) && World.func_147466_a((IBlockAccess)p_77648_3_, p_77648_4_ + b0, p_77648_5_ - 1, p_77648_6_ + b1)) {
        p_77648_3_.func_147465_d(p_77648_4_, p_77648_5_, p_77648_6_, (Block)blockbed, i1, 3);
        if (p_77648_3_.func_147439_a(p_77648_4_, p_77648_5_, p_77648_6_) == blockbed)
          p_77648_3_.func_147465_d(p_77648_4_ + b0, p_77648_5_, p_77648_6_ + b1, (Block)blockbed, i1 + 8, 3); 
        p_77648_1_.field_77994_a--;
        return true;
      } 
      return false;
    } 
    return false;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("DESC.TXT : ITEM:BED que ne peut être ACTION:USE que dans le WORLD:NETHER. ACTION:EXPLODE si ACTION:USE dans le WORLD:OVERWORLD.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\items\ItemCorruptedBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */