package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities.EntityPaperAirplane;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemPaperAirplane extends Item implements ITooltipInformations {
  public static final String NAME = "paper_airplane";
  
  public static final String DESCRIPTION = "Peut être lancé avec clic droit. Quand un joueur se trouve sur l’avion celui-ci avance en ligne droite jusqu’à rencontrer un obstacle ";
  
  public ItemPaperAirplane() {
    func_77655_b("paper_airplane");
    func_111206_d("palamod:paper_airplane");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer globalPlayer) {
    if (world.field_72995_K)
      return stack; 
    EntityPlayerMP player = (EntityPlayerMP)globalPlayer;
    if (!LuckyBlockUtils.canUseItem(player, true))
      return stack; 
    initNbt(stack);
    EntityPaperAirplane entity = new EntityPaperAirplane(world);
    entity.spawn((EntityPlayer)player, world, player.field_70165_t, player.field_70163_u + 1.0D, player.field_70161_v, getTotalTickMillis(stack));
    MonthlyUtils.decrementCurrentItem((EntityPlayer)player, stack);
    return stack;
  }
  
  public void initNbt(ItemStack stack) {
    NBTTagCompound compound = stack.func_77978_p();
    if (compound == null) {
      compound = new NBTTagCompound();
      stack.func_77982_d(compound);
    } 
    if (!compound.func_74764_b("totalTickMillis"))
      compound.func_74772_a("totalTickMillis", 0L); 
  }
  
  public long getTotalTickMillis(ItemStack stack) {
    NBTTagCompound compound = stack.func_77978_p();
    if (compound == null)
      return 0L; 
    return compound.func_74763_f("totalTickMillis");
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Peut être lancé avec clic droit. Quand un joueur se trouve sur l’avion celui-ci avance en ligne droite jusqu’à rencontrer un obstacle ");
  }
  
  public static ItemStack build(long totalTickMillis) {
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74772_a("totalTickMillis", totalTickMillis);
    ItemStack stack = new ItemStack(ItemsRegister.PAPER_AIRPLANE);
    stack.func_77982_d(compound);
    return stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\items\ItemPaperAirplane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */