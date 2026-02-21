package fr.paladium.palamod.modules.luckyblock.blocks.easter;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockBase;
import fr.paladium.palamod.modules.luckyblock.network.easter.PacketColoredParticle;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterEgg;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockBlueEasterEgg extends BlockBase {
  public BlockBlueEasterEgg() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("blue_easter_egg");
    func_149658_d("palamod:easter_egg_blue_item");
    func_149711_c(Float.MAX_VALUE);
    func_149752_b(Float.MAX_VALUE);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K && EventUtils.canInteract(player, x, y, z))
      manageBlock(world, x, y, z, player); 
    return true;
  }
  
  public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {
    if (!world.field_72995_K && EventUtils.canInteract(player, x, y, z))
      manageBlock(world, x, y, z, player); 
  }
  
  private void manageBlock(World world, int x, int y, int z, EntityPlayer player) {
    String playerId = player.func_110124_au().toString();
    if (UsersManager.getBlueEasterEggCaught().containsKey(playerId)) {
      int number = ((Integer)UsersManager.getBlueEasterEggCaught().get(playerId)).intValue() + 1;
      UsersManager.getBlueEasterEggCaught().put(playerId, Integer.valueOf(number));
    } else {
      UsersManager.getBlueEasterEggCaught().put(playerId, Integer.valueOf(1));
    } 
    world.func_147468_f(x, y, z);
    int radius = 20;
    for (Object o : world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((x - radius), 0.0D, (z - radius), (x + radius), 255.0D, (z + radius)))) {
      if (o instanceof EntityPlayerMP) {
        EntityPlayerMP p = (EntityPlayerMP)o;
        PalaMod.getNetwork().sendTo((IMessage)new PacketColoredParticle(x + 0.5D, y + 0.5D, z + 0.5D, 0.0F, 0.7137255F, 0.9411765F, 2.0F), p);
      } 
    } 
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderEasterEgg;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityEasterEgg(1);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\easter\BlockBlueEasterEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */