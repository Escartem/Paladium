package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.items.block.ItemBlockGeneric;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class BlockStrippedOldLog extends BlockOldLog implements IConfigurable, ModBlocks.ISubBlocksBlock {
  public BlockStrippedOldLog() {
    func_149663_c(Utils.getUnlocalisedName("log_stripped"));
    func_149647_a(Back2Future.creativeTab);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister p_149651_1_) {
    this.field_150167_a = new net.minecraft.util.IIcon[field_150168_M.length];
    this.field_150166_b = new net.minecraft.util.IIcon[field_150168_M.length];
    for (int i = 0; i < this.field_150167_a.length; i++) {
      this.field_150167_a[i] = p_149651_1_.func_94245_a("stripped_" + field_150168_M[i] + "_log");
      this.field_150166_b[i] = p_149651_1_
        .func_94245_a("stripped_" + field_150168_M[i] + "_log_top");
    } 
  }
  
  public boolean isEnabled() {
    return true;
  }
  
  public Class<? extends ItemBlock> getItemBlockClass() {
    return (Class)ItemBlockGeneric.class;
  }
  
  public static void onPlayerInteract(PlayerInteractEvent event) {
    if (event.entityPlayer != null) {
      World world = event.entityPlayer.field_70170_p;
      if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && 
        world.func_147439_a(event.x, event.y, event.z) == Blocks.field_150364_r) {
        int logMeta = world.func_72805_g(event.x, event.y, event.z);
        ItemStack stack = event.entityPlayer.func_71045_bC();
        if (stack != null && stack.func_77973_b() instanceof net.minecraft.item.ItemAxe) {
          world.func_147465_d(event.x, event.y, event.z, ModBlocks.log_stripped, logMeta, 2);
          event.entityPlayer.func_71038_i();
          stack.func_77972_a(1, (EntityLivingBase)event.entityPlayer);
          world.func_72908_a((event.x + 0.5F), (event.y + 0.5F), (event.z + 0.5F), Block.field_149766_f
              .func_150498_e(), 1.0F, 0.8F);
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\BlockStrippedOldLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */