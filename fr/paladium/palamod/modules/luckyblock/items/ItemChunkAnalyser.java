package fr.paladium.palamod.modules.luckyblock.items;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.paladium.network.OpenGuiPacket;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemChunkAnalyser extends Item implements ITooltipWiki {
  public ItemChunkAnalyser() {
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77656_e(10);
    func_77655_b("chunk_analyser");
    func_111206_d("palamod:chunk_analyser");
  }
  
  public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
    p_77624_3_.add("§bClic droit pour analyser le chunk");
    super.func_77624_a(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    item.func_77972_a(1, (EntityLivingBase)player);
    if (!world.field_72995_K && 
      player instanceof EntityPlayerMP) {
      EntityPlayerMP p = (EntityPlayerMP)player;
      PalaMod.network.sendTo((IMessage)new OpenGuiPacket(PGuiRegistry.GUI_CHUNK_ANALYSER, true), p);
    } 
    return item;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.-lucky-block-en-paladium-et-en-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemChunkAnalyser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */