package fr.paladium.palamod.modules.pillage.common.items;

import fr.paladium.palamod.modules.pillage.common.blocks.effects.ObsiEffect;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TNTEffect;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;

public class ItemBlockMultipleTypes extends ItemBlockWithMetadata {
  public ItemBlockMultipleTypes(Block block) {
    super(block, block);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean p_77624_4_) {
    if (this.field_150939_a instanceof ObsiEffect || this.field_150939_a instanceof TNTEffect)
      if (Keyboard.isKeyDown(42)) {
        if (this.field_150939_a instanceof ObsiEffect) {
          tooltip.add(((ObsiEffect)this.field_150939_a).objectEffect.getDescription());
        } else {
          tooltip.add(((TNTEffect)this.field_150939_a).effect.getDescription());
        } 
      } else {
        tooltip.add("§4Appuyez sur SHIFT pour + d'info");
      }  
  }
  
  public String func_77667_c(ItemStack stack) {
    String suffix = (stack.func_77960_j() > 0) ? ("_" + stack.func_77960_j()) : "";
    return func_77658_a() + suffix;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\items\ItemBlockMultipleTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */