package fr.paladium.palaschematicmod.common.block;

import fr.paladium.palaschematicmod.client.ui.UIBlockPalaSchematic;
import fr.paladium.palaschematicmod.common.pojo.schematic.SchematicCopySession;
import fr.paladium.palaschematicmod.common.tileentity.TileEntityBlockPalaSchematic;
import fr.paladium.palaschematicmod.server.manager.PalaSchematicManager;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockPalaSchematic extends Block implements ITileEntityProvider {
  public BlockPalaSchematic() {
    super(Material.field_151576_e);
    func_149711_c(2.0F);
    func_149752_b(10.0F);
    func_149663_c("palaschematic");
    func_149658_d("palaschematic:palaschematic");
    func_149647_a(CreativeTabs.field_78027_g);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K || !(player instanceof EntityPlayerMP))
      return true; 
    SchematicCopySession session = PalaSchematicManager.getInstance().getOrCreateSession(player);
    if (session != null && session.firstPos != null && session.secondPos != null && player.func_70093_af()) {
      TileEntity tileEntity = world.func_147438_o(x, y, z);
      if (tileEntity instanceof TileEntityBlockPalaSchematic) {
        TileEntityBlockPalaSchematic palaSchematicTileEntity = (TileEntityBlockPalaSchematic)tileEntity;
        int relativeX = Math.min(session.firstPos.getX(), session.secondPos.getX()) - x;
        int relativeY = Math.min(session.firstPos.getY(), session.secondPos.getY()) - y;
        int relativeZ = Math.min(session.firstPos.getZ(), session.secondPos.getZ()) - z;
        int sizeX = Math.abs(session.firstPos.getX() - session.secondPos.getX());
        int sizeY = Math.abs(session.firstPos.getY() - session.secondPos.getY());
        int sizeZ = Math.abs(session.firstPos.getZ() - session.secondPos.getZ());
        palaSchematicTileEntity.update(palaSchematicTileEntity.getSchematicName(), relativeX, relativeY, relativeZ, sizeX, sizeY, sizeZ);
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6PalaSchematic§8] §aDatas mises à jour depuis la sélection."));
      } 
      return true;
    } 
    ZUIServer.open(UIBlockPalaSchematic.class, (EntityPlayerMP)player, new Object[] { Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z) });
    return true;
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityBlockPalaSchematic();
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntityBlockPalaSchematic))
      return; 
    TileEntityBlockPalaSchematic tile = (TileEntityBlockPalaSchematic)tileEntity;
    if (item.func_77942_o() && item.func_77978_p().func_74764_b("nbt"))
      tile.readAdditionalFromNBT(item.func_77978_p().func_74775_l("nbt")); 
    tile.func_70296_d();
  }
  
  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
    if (!player.func_70093_af())
      return super.getPickBlock(target, world, x, y, z, player); 
    ItemStack stack = new ItemStack(this);
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntityBlockPalaSchematic))
      return null; 
    TileEntityBlockPalaSchematic tile = (TileEntityBlockPalaSchematic)tileEntity;
    NBTTagCompound nbt = new NBTTagCompound();
    NBTTagCompound tileNBT = new NBTTagCompound();
    tile.writeAdditionalToNBT(tileNBT);
    nbt.func_74782_a("nbt", (NBTBase)tileNBT);
    stack.func_77982_d(nbt);
    stack.func_151001_c((tile.getSchematicName() == null || tile.getSchematicName().isEmpty()) ? (stack.func_82833_r() + " §d(+NBT)") : (stack.func_82833_r() + " §d(" + tile.getSchematicName() + ")"));
    return stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\common\block\BlockPalaSchematic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */