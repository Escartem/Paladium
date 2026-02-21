package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.paladium.common.logics.TileEntityTotem;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ILoveYouEarly extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (!EventUtils.canInteract((EntityPlayer)player, x, y, z))
      return; 
    player.field_70170_p.func_147449_b(x, y, z, (Block)BlocksRegister.Totem);
    TileEntity tile = player.field_70170_p.func_147438_o(x, y, z);
    if (tile == null)
      return; 
    if (!(tile instanceof TileEntityTotem))
      return; 
    TileEntityTotem totem = (TileEntityTotem)tile;
    totem.setFuel(new ItemStack(ItemsRegister.PALADIUM_INGOT, 64));
    PPalaDynamique.instance.addGenerated("LUCKYBLOCK", 64.0D);
    player.field_71135_a.func_147359_a(totem.func_145844_m());
  }
  
  public String getName() {
    return "Je t'aime tôt";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "je_t_aime_tot";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\ILoveYouEarly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */