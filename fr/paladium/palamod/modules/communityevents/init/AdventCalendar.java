package fr.paladium.palamod.modules.communityevents.init;

import fr.paladium.palacommunityevent.common.manager.AdventCalendarManager;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.smeltery.registerer.PSRegister_Items;
import fr.welsymc.guardiangolem.common.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AdventCalendar {
  public static void register() {
    AdventCalendarManager.register(2024, new ItemStack[][] { 
          { new ItemStack((Item)ItemsRegister.XP_BERRY, 4) }, { new ItemStack(BlocksRegister.BLOCK_AMETHYST, 24) }, { new ItemStack(Blocks.field_150340_R, 32) }, { new ItemStack(BlocksRegister.BLOCK_TITANE, 24) }, { new ItemStack(BlocksRegister.FLOWER_HARPAGOPHYTUM) }, { new ItemStack(ItemsRegister.DISC_NOEL) }, { new ItemStack(ItemsRegister.TRIXIUM, 64), new ItemStack(ItemsRegister.TRIXIUM, 64), new ItemStack(ItemsRegister.TRIXIUM, 64) }, { new ItemStack(ItemInit.COSMETIC_HAT_LEPRECHAUN) }, { new ItemStack(ItemInit.COSMETIC_PARTICLES_ENDER) }, { (new ItemStackBuilder(ItemsRegister.PALADIUM_SWORD))
            
            .enchant(Enchantment.field_77338_j, 5)
            .enchant(Enchantment.field_77347_r, 3)
            .enchant(Enchantment.field_77334_n, 2)
            .build() }, 
          { new ItemStack((Item)PSRegister_Items.MODIFIER_MOREUPGRADE) }, { new ItemStack((Block)BlocksRegister.CAULDRON_BLOCK, 12) }, { new ItemStack((Block)BlocksRegister.PALADIUM_CHEST) }, { new ItemStack(ItemInit.COSMETIC_SKIN_FUTURIST) }, { (new ItemStackBuilder(ItemsRegister.PALADIUM_GREEN_BOOTS))
            
            .enchant(Enchantment.field_77332_c, 4)
            .enchant(Enchantment.field_77347_r, 3)
            .build() }, { new ItemStack(ItemsRegister.CHRISTMAS_BOOTS) }, { (new ItemStackBuilder(ItemsRegister.PALADIUM_HELMET))
            
            .enchant(Enchantment.field_77332_c, 4)
            .enchant(Enchantment.field_77347_r, 3)
            .build(), (new ItemStackBuilder(ItemsRegister.PALADIUM_CHESTPLATE))
            
            .enchant(Enchantment.field_77332_c, 4)
            .enchant(Enchantment.field_77347_r, 3)
            .build(), (new ItemStackBuilder(ItemsRegister.PALADIUM_LEGGINGS))
            
            .enchant(Enchantment.field_77332_c, 4)
            .enchant(Enchantment.field_77347_r, 3)
            .build(), (new ItemStackBuilder(ItemsRegister.PALADIUM_BOOTS))
            
            .enchant(Enchantment.field_77332_c, 4)
            .enchant(Enchantment.field_77347_r, 3)
            .build() }, { new ItemStack(ItemsRegister.ENDIUM_FRAGMENT) }, { new ItemStack(ItemsRegister.CHRISTMAS_LEGGINGS) }, { new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 64) }, 
          { new ItemStack(ItemsRegister.CHRISTMAS_CHESTPLATE) }, { new ItemStack(ItemsRegister.COSMETIC_SKIN_NOEL) }, { new ItemStack(ItemsRegister.CHRISTMAS_HELMET) }, { new ItemStack(ItemsRegister.LEGENDARYSTONE_RANDOM) } });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\init\AdventCalendar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */