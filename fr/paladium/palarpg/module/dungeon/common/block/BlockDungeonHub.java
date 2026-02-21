package fr.paladium.palarpg.module.dungeon.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palarpg.module.dungeon.client.render.block.TileEntityDungeonHubRenderer;
import fr.paladium.palarpg.module.dungeon.client.ui.hub.UIDungeonHub;
import fr.paladium.palarpg.module.dungeon.client.ui.popup.UIDungeonWarningPopup;
import fr.paladium.palarpg.module.dungeon.common.block.template.ABlockDungeon;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonHub;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.server.manager.DungeonManager;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import java.util.Optional;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockDungeonHub extends ABlockDungeon implements ITileEntityProvider {
  public BlockDungeonHub() {
    super("dungeon_hub", "hub");
    func_149658_d("palarpg:dungeon/hub/default");
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntityDungeonHub))
      return; 
    TileEntityDungeonHub tile = (TileEntityDungeonHub)tileEntity;
    int rotation = MathHelper.func_76128_c((entity.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    ForgeDirection direction = ForgeDirection.NORTH;
    switch (rotation) {
      case 0:
        direction = ForgeDirection.NORTH;
        break;
      case 1:
        direction = ForgeDirection.EAST;
        break;
      case 2:
        direction = ForgeDirection.SOUTH;
        break;
      case 3:
        direction = ForgeDirection.WEST;
        break;
    } 
    tile.setDirection(direction);
    tile.func_70296_d();
    world.func_147471_g(x, y, z);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K && player instanceof EntityPlayerMP) {
      Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(world);
      if (!optionalDungeonWorld.isPresent())
        return true; 
      boolean hasRPGArmor = false;
      for (int i = 0; i < 4; i++) {
        if (player.field_71071_by.field_70460_b[i] != null && IRPGItem.is(player.field_71071_by.field_70460_b[i])) {
          hasRPGArmor = true;
          break;
        } 
      } 
      DungeonWorld dungeonWorld = optionalDungeonWorld.get();
      dungeonWorld.updateMaxLevels();
      ZUIServer.open(UIDungeonHub.class, (EntityPlayerMP)player, new Object[] { new UIDungeonHub.UIDungeonHubData(dungeonWorld.isLeader((Entity)player), DungeonManager.getDungeons()) });
      if (!hasRPGArmor)
        ZUIServer.open(UIDungeonWarningPopup.class, (EntityPlayerMP)player, new Object[] { "vous n'avez pas d'armure", "Vous n'êtes pas équipé avec une §f§larmure de donjon§r, vous ne bénéficierez d’aucune protection contre les monstres.", Boolean.FALSE }); 
    } 
    return true;
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityDungeonHub();
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return TileEntityDungeonHubRenderer.RENDER_ID;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\block\BlockDungeonHub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */