package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.server.events.CorruptedChronoEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis.JobTransferorHomeUI;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCorruptedChrono extends Item implements ITooltipInformations {
  public static final String NAME = "corrupted_chrono";
  
  private static final String DESCRIPTION = "DESC.TXT : Peut être utilisé en effectuant un USE:RIGHT.CLICK. Le ENTITY:PLAYER sera ACTION:TELEPORT à l’endroit où il à ITEM:USE 30 secondes après son utilisation. DURABILITY : 30";
  
  public static final int DURABILITY = 500;
  
  public ItemCorruptedChrono() {
    func_77655_b("corrupted_chrono");
    func_111206_d("palamod:corrupted_chrono");
    func_77637_a(PLuckyBlock.TAB);
    func_77656_e(500);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer singlePlayer) {
    if (world.field_72995_K || LuckyBlockUtils.isInCombat(singlePlayer, true))
      return stack; 
    EntityPlayerMP player = (EntityPlayerMP)singlePlayer;
    CorruptedChronoEventHandler handler = CorruptedChronoEventHandler.INSTANCE;
    if (handler.isWaiting(player))
      return stack; 
    MonthlyUtils.damageCurrentItem((EntityPlayer)player, stack, 1);
    handler.use(player);
    return stack;
  }
  
  public boolean func_77616_k(ItemStack stack) {
    return false;
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public void openUI() {
    MonthlyUtils.openUI((GuiScreen)new JobTransferorHomeUI());
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("DESC.TXT : Peut être utilisé en effectuant un USE:RIGHT.CLICK. Le ENTITY:PLAYER sera ACTION:TELEPORT à l’endroit où il à ITEM:USE 30 secondes après son utilisation. DURABILITY : 30");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\items\ItemCorruptedChrono.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */