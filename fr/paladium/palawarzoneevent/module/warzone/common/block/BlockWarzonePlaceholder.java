package fr.paladium.palawarzoneevent.module.warzone.common.block;

import fr.paladium.palawarzoneevent.module.warzone.client.ui.UIWarzone;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class BlockWarzonePlaceholder extends Block {
  protected BlockWarzonePlaceholder(CreativeTabs creativeTab) {
    super(Material.field_151575_d);
    func_149663_c("warzone_sign_placeholder");
    func_149711_c(-1.0F);
    func_149752_b(6000000.0F);
    func_149658_d("palawarzoneevent:transparent");
    func_149647_a(creativeTab);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (world.field_72995_K)
      return true; 
    ZUIServer.open(UIWarzone.class, (EntityPlayerMP)player);
    return true;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149637_q() {
    return false;
  }
  
  public boolean func_149721_r() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\common\block\BlockWarzonePlaceholder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */