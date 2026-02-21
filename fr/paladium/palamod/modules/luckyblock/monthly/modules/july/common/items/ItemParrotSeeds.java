package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents.ParrotEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemParrotSeeds extends Item implements ITooltipInformations {
  public static final String DESCRIPTION = "Permet d'invoquer ton nouveau meilleur ami pendant 10 minutes en effectuant un clic droit sur l’objet";
  
  public static final String NAME = "parrot_seeds";
  
  public ItemParrotSeeds() {
    func_77655_b("parrot_seeds");
    func_111206_d("palamod:parrot_seeds");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return stack; 
    ALuckyEvent event = LuckyEvents.PARROT.getEvent();
    if (event instanceof ParrotEvent) {
      ParrotEvent parrotEvent = (ParrotEvent)event;
      parrotEvent.startTask((EntityPlayerMP)player, false);
    } 
    stack.field_77994_a--;
    if (stack.field_77994_a <= 0)
      player.func_70062_b(0, null); 
    return stack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return MonthlyUtils.splitDescription("Permet d'invoquer ton nouveau meilleur ami pendant 10 minutes en effectuant un clic droit sur l’objet");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemParrotSeeds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */