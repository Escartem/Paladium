package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityATM;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockAtm extends Block implements ITooltipWiki {
  private IIcon[] icons = new IIcon[2];
  
  public BlockAtm() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("atm");
    func_149658_d("palamod:atm_front");
    func_149711_c(5.0F);
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a("palamod:atm_front");
    this.icons[1] = register.func_94245_a("palamod:atm_side");
    super.func_149651_a(register);
  }
  
  public IIcon func_149691_a(int side, int meta) {
    ForgeDirection dir = ForgeDirection.getOrientation(side);
    if (dir == ForgeDirection.NORTH)
      return this.icons[0]; 
    return this.icons[1];
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase ent, ItemStack item) {
    TileEntityATM tile = (TileEntityATM)world.func_147438_o(x, y, z);
    tile.setOwner(FastUUID.toString((Entity)ent));
    super.func_149689_a(world, x, y, z, ent, item);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, final EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (!world.field_72995_K) {
      TileEntityATM tile = (TileEntityATM)world.func_147438_o(x, y, z);
      if (tile.getOwner().equals(FastUUID.toString((Entity)player)))
        if (tile.getTicks() > 1200) {
          final double money = Math.ceil((tile.getTicks() / 1200)) * 3.0D;
          CresusManager.getInstance().depositPlayerAsync(player.func_110124_au(), money, "BlockATM onBlockActivated", new CresusCallback<CresusResponse>() {
                public void onSuccess(CresusResponse arg0) {
                  player.func_145747_a((IChatComponent)new ChatComponentText("§aVous avez récupéré §e" + money + "$."));
                }
                
                public void onFail(CresusResponse arg0, Throwable arg1) {
                  new ChatComponentText("§cUne erreur est survenue lors de la récupération de §e" + money + "$.");
                }
              });
          tile.setTicks(0);
        } else {
          player.func_145747_a((IChatComponent)new ChatComponentText("§cVous ne pouvez pas encore récupérer votre argent."));
        }  
    } 
    return super.func_149727_a(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityATM();
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.1.-luckystats-paladium-et-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockAtm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */