package fr.paladium.palamod.modules.luckyblock.items.christmas;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPresent extends Item implements ITooltipWiki {
  public static Item[] presents = new Item[] { 
      ItemsRegister.CHUNK_ANALYSER, ItemsRegister.BLAZE_AND_STEEL, ItemsRegister.WEIGHTED_BOOTS, ItemsRegister.DISC_FUZEIII, ItemsRegister.GRAPPIN, ItemsRegister.GRAPPIN_HOOK, ItemsRegister.NAMETAG_FIREWORK, ItemsRegister.SPACE_FOOD, ItemsRegister.MAGIC_POTION, ItemsRegister.LASSO, 
      ItemsRegister.ROLLER, ItemsRegister.JETPACK, ItemsRegister.BIOME_PAINTER, ItemsRegister.LUCKY_PAINTING, ItemsRegister.PIG_HELMET, ItemsRegister.PIG_CHESTPLATE, ItemsRegister.PIG_LEGGINGS, ItemsRegister.PIG_BOOTS, ItemsRegister.CAVE_HELMET, ItemsRegister.INVISIBLE_HELMET, 
      ItemsRegister.INVISIBLE_CHESTPLATE, ItemsRegister.INVISIBLE_LEGGINGS, ItemsRegister.INVISIBLE_BOOTS, ItemsRegister.SPEED_APPLE, ItemsRegister.POULPOGUN, ItemsRegister.METER, ItemsRegister.ENDER_BAG, ItemsRegister.SMOKEBOMB_WHITE, ItemsRegister.SMOKEBOMB_ORANGE, ItemsRegister.SMOKEBOMB_MAGENTA, 
      ItemsRegister.SMOKEBOMB_LIGHT_BLUE, ItemsRegister.SMOKEBOMB_YELLOW, ItemsRegister.SMOKEBOMB_LIME, ItemsRegister.SMOKEBOMB_PINK, ItemsRegister.SMOKEBOMB_GRAY, ItemsRegister.SMOKEBOMB_LIGHT_GRAY, ItemsRegister.SMOKEBOMB_CYAN, ItemsRegister.SMOKEBOMB_PURPLE, ItemsRegister.SMOKEBOMB_BLUE, ItemsRegister.SMOKEBOMB_BROWN, 
      ItemsRegister.SMOKEBOMB_GREEN, ItemsRegister.SMOKEBOMB_RED, ItemsRegister.SMOKEBOMB_BLACK, ItemsRegister.DISC_HALLOWEEN, ItemsRegister.BROOM, ItemsRegister.GHOSTCAPE, ItemsRegister.PUMPKINSHEAR, ItemsRegister.SPIDERGLOVES, ItemsRegister.PUMPKINCRUSH, ItemsRegister.HALLOWEENSTONE, 
      ItemsRegister.SURVIVINGSTONE, ItemsRegister.HALLOWEEN_HELMET, ItemsRegister.HALLOWEEN_CHESTPLATE, ItemsRegister.HALLOWEEN_LEGGINGS, ItemsRegister.HALLOWEEN_BOOTS, ItemsRegister.HALLOWEEN_HAT, ItemsRegister.JOB_CANDY, ItemsRegister.HALLOWEEN_AXE, ItemsRegister.CHRISTMAS_STONE, ItemsRegister.PRESENT, 
      ItemsRegister.PRESENT_BAG };
  
  public ItemPresent() {
    func_77625_d(64);
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("present");
    func_111206_d("palamod:present");
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      int randomNumber = world.field_73012_v.nextInt(presents.length);
      PlayerUtil.addItemStackToInventory(new ItemStack(presents[randomNumber], 1), player.field_71071_by);
      item.field_77994_a--;
    } 
    return super.func_77659_a(item, world, player);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#5.-lucky-block-noel";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\christmas\ItemPresent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */