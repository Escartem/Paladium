package fr.paladium.palaspawner.common.block;

import fr.paladium.blueprint.common.block.IBlockLinkedBluePrint;
import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palajobs.api.PalaJobsAPI;
import fr.paladium.palajobs.api.player.IJobsPlayer;
import fr.paladium.palaspawner.common.handler.SpawnerControllerGuiHandler;
import fr.paladium.palaspawner.common.spawner.blueprint.output.SpawnerBluePrintOutput;
import fr.paladium.palaspawner.common.tab.SpawnerTabs;
import fr.paladium.palaspawner.common.tile.TileEntitySpawnerController;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandSender;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSpawnerController extends Block implements ITileEntityProvider, IBlockLinkedBluePrint {
  private static final String NAME = "spawn_controller";
  
  public BlockSpawnerController() {
    super(Material.field_151576_e);
    func_149663_c("spawn_controller");
    func_149658_d("palaspawner:spawn_controller");
    func_149711_c(2.0F);
    func_149752_b(10.0F);
    func_149647_a((CreativeTabs)SpawnerTabs.getInstance());
  }
  
  public boolean func_149747_d(IBlockAccess world, int x, int y, int z, int side) {
    return false;
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return true; 
    TileEntitySpawnerController controller = getController(world, x, y, z);
    if (controller == null)
      return true; 
    SpawnerBluePrintOutput output = controller.getOutput();
    if (output == null || !output.isValid()) {
      ChatUtils.sendColoredMessage((ICommandSender)player, new String[] { "§8[§6Paladium§8] §cVotre Structure n'est pas valide." });
      return true;
    } 
    EntityPlayerMP playerMP = (EntityPlayerMP)player;
    controller.updateClientSide(playerMP);
    controller.putJobData(PalaJobsAPI.inst().getJobsPlayer((EntityPlayer)playerMP).orElseGet(() -> null));
    if (controller.getValid() != 1) {
      ChatUtils.sendColoredMessage((ICommandSender)player, new String[] { "§8[§6Paladium§8] §cVous ne pouvez avoir qu'un seul spawner controlleur par chunk." });
      return true;
    } 
    SpawnerControllerGuiHandler.open(playerMP, x, y, z);
    return true;
  }
  
  private TileEntitySpawnerController getController(World world, int x, int y, int z) {
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntitySpawnerController))
      return null; 
    return (TileEntitySpawnerController)tile;
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntitySpawnerController();
  }
  
  public String getLinkedBluePrintId() {
    return "first_spawner";
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
    TileEntity tileentity = world.func_147438_o(x, y, z);
    if (tileentity instanceof IInventory) {
      IInventory inv = (IInventory)tileentity;
      for (int i1 = 0; i1 < inv.func_70302_i_(); i1++) {
        ItemStack itemstack = inv.func_70301_a(i1);
        if (itemstack != null) {
          float f = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
          float f1 = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
          for (float f2 = world.field_73012_v.nextFloat() * 0.8F + 0.1F; itemstack.field_77994_a > 0; world.func_72838_d((Entity)entityitem)) {
            int j1 = world.field_73012_v.nextInt(21) + 10;
            if (j1 > itemstack.field_77994_a)
              j1 = itemstack.field_77994_a; 
            itemstack.field_77994_a -= j1;
            EntityItem entityitem = new EntityItem(world, (x + f), (y + f1), (z + f2), new ItemStack(itemstack.func_77973_b(), j1, itemstack.func_77960_j()));
            float f3 = 0.05F;
            entityitem.field_70159_w = ((float)world.field_73012_v.nextGaussian() * 0.05F);
            entityitem.field_70181_x = ((float)world.field_73012_v.nextGaussian() * 0.05F + 0.2F);
            entityitem.field_70179_y = ((float)world.field_73012_v.nextGaussian() * 0.05F);
            if (itemstack.func_77942_o())
              entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b()); 
          } 
        } 
      } 
      world.func_147453_f(x, y, z, block);
    } 
    super.func_149749_a(world, x, y, z, block, metadata);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\block\BlockSpawnerController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */