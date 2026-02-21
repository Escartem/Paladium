package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis.GuiChunkJobAnalyser;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemJobChunkAnalyzer extends Item implements ITooltipInformations {
  public static final String NAME = "job_chunk_analyzer";
  
  public static final String DESCRIPTION = "Clic droit pour analyser le chunk et voir la quantité d’expérience métier qui s’y cache";
  
  public ItemJobChunkAnalyzer() {
    func_77655_b("job_chunk_analyzer");
    func_111206_d("palamod:job_chunk_analyzer");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77656_e(10);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    MonthlyUtils.damageCurrentItem(player, stack);
    if (world.field_72995_K) {
      openUI();
      return stack;
    } 
    return stack;
  }
  
  @SideOnly(Side.CLIENT)
  public void openUI() {
    if ((Minecraft.func_71410_x()).field_71462_r != null)
      return; 
    Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiChunkJobAnalyser());
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Clic droit pour analyser le chunk et voir la quantité d’expérience métier qui s’y cache");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\items\ItemJobChunkAnalyzer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */