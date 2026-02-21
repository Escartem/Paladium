package fr.paladium.palamod.modules.luckyblock.items.easter;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.dialog.GiantFishDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ItemGiantFish extends Item implements ITooltipInformations {
  public static final String NAME = "giant_fish";
  
  public static final String DESCRIPTION = "S'il est utilisé, le poisson apparaît dans les airs et creuse en ligne droite dans un rayon de 15 cubes.";
  
  public ItemGiantFish() {
    func_77655_b("giant_fish");
    func_111206_d("palamod:giant_fish");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return itemStack; 
    Chunk chunk = world.func_72938_d(MathHelper.func_76128_c(player.field_70165_t), MathHelper.func_76128_c(player.field_70161_v));
    if (chunk == null || !chunk.field_76636_d) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§cLe chunk n'est pas chargé."));
      return itemStack;
    } 
    for (Object tileEntity : chunk.field_150816_i.values()) {
      if (tileEntity instanceof fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§cVous ne pouvez pas utiliser cet item dans un chunk contenant une Réceptacle de Wither."));
        return itemStack;
      } 
    } 
    GiantFishDialogManager.getInstance().sendDialog((EntityPlayerMP)player, null);
    return itemStack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("S'il est utilisé, le poisson apparaît dans les airs et creuse en ligne droite dans un rayon de 15 cubes.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\easter\ItemGiantFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */