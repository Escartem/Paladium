package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSummerInstruction extends Item implements ITooltipInformations {
  public static final String NAME = "summer_instructions";
  
  public static final String DESCRIPTION = "Indique les mesures de sécurité à prendre en été. Peut être activé avec un clic droit";
  
  private static final String[] MESSAGE = new String[] { "&eSi vous êtes déshydraté, l'eau est votre amie sous toutes ses formes : en fiole, en bouteille ou même dans la mer.", "&eN'oubliez pas de mettre de la crème solaire, sinon vous pourriez subir un coup de soleil.", "&eSi votre bouteille d'eau est vide, recyclez-là en la cuisant dans un four ! (Sur Paladium, chez vous jetez là dans la poubelle appropriée)", "&eSi vous croisez un coffre fort pendant vos vacances, son code pourrait bien être 974." };
  
  public ItemSummerInstruction() {
    func_77655_b("summer_instructions");
    func_111206_d("palamod:summer_instructions");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (world.field_72995_K) {
      MonthlyUtils.sendMessage(player, MESSAGE);
      return itemStack;
    } 
    return itemStack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Indique les mesures de sécurité à prendre en été. Peut être activé avec un clic droit");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\items\ItemSummerInstruction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */