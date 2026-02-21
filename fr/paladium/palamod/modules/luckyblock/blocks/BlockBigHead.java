package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockBigHead extends Block {
  private IIcon[] icons = new IIcon[4];
  
  public BlockBigHead() {
    super(Material.field_151576_e);
    func_149663_c("big_head");
    func_149658_d("palamod:zeldown0");
    func_149711_c(0.0F);
    func_149672_a(field_149779_h);
    func_149647_a(PLuckyBlock.TAB);
  }
  
  public void func_149651_a(IIconRegister ir) {
    for (int i = 0; i < 4; i++)
      this.icons[i] = ir.func_94245_a("palamod:zeldown" + i); 
    super.func_149651_a(ir);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack items) {
    if (entity instanceof EntityPlayer)
      genBlock((EntityPlayer)entity, world, x, y, z); 
    super.func_149689_a(world, x, y, z, entity, items);
  }
  
  public boolean func_149742_c(World world, int x, int y, int z) {
    return (EventUtils.isAir(world, x + 1, y, z + 1) && EventUtils.isAir(world, x + 1, y + 1, z + 1) && 
      EventUtils.isAir(world, x, y + 1, z + 1) && EventUtils.isAir(world, x, y, z + 1) && 
      EventUtils.isAir(world, x, y, z) && EventUtils.isAir(world, x, y + 1, z) && 
      EventUtils.isAir(world, x + 1, y, z) && EventUtils.isAir(world, x + 1, y + 1, z));
  }
  
  public int func_149660_a(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
    return super.func_149660_a(world, x, y, z, side, hitX, hitY, hitZ, meta);
  }
  
  private void genBlock(EntityPlayer entity, World world, int x, int y, int z) {
    if (entity != null) {
      if (EventUtils.canInteract(entity, x + 1, y, z + 1) && 
        EventUtils.canInteract(entity, x + 1, y + 1, z + 1) && 
        EventUtils.canInteract(entity, x, y + 1, z + 1) && 
        EventUtils.canInteract(entity, x, y, z + 1) && EventUtils.canInteract(entity, x, y, z) && 
        EventUtils.canInteract(entity, x, y + 1, z) && EventUtils.canInteract(entity, x + 1, y, z) && 
        EventUtils.canInteract(entity, x + 1, y + 1, z) && 
        EventUtils.isAir(world, x + 1, y, z + 1) && 
        EventUtils.isAir(world, x + 1, y + 1, z + 1) && 
        EventUtils.isAir(world, x, y + 1, z + 1) && EventUtils.isAir(world, x, y, z + 1) && 
        EventUtils.isAir(world, x, y + 1, z) && EventUtils.isAir(world, x + 1, y, z) && 
        EventUtils.isAir(world, x + 1, y + 1, z)) {
        world.func_147449_b(x, y, z, BlocksRegister.BIG_HEAD);
        world.func_72921_c(x, y, z, 0, 4);
        world.func_147449_b(x, y + 1, z, BlocksRegister.BIG_HEAD);
        world.func_72921_c(x, y + 1, z, 1, 4);
        world.func_147449_b(x + 1, y, z, BlocksRegister.BIG_HEAD);
        world.func_72921_c(x + 1, y, z, 2, 4);
        world.func_147449_b(x + 1, y + 1, z, BlocksRegister.BIG_HEAD);
        world.func_72921_c(x + 1, y + 1, z, 3, 4);
        world.func_147449_b(x, y, z + 1, BlocksRegister.BIG_HEAD);
        world.func_72921_c(x, y, z + 1, 3, 4);
        world.func_147449_b(x, y + 1, z + 1, BlocksRegister.BIG_HEAD);
        world.func_72921_c(x, y + 1, z + 1, 2, 4);
        world.func_147449_b(x + 1, y, z + 1, BlocksRegister.BIG_HEAD);
        world.func_72921_c(x + 1, y, z + 1, 1, 4);
        world.func_147449_b(x + 1, y + 1, z + 1, BlocksRegister.BIG_HEAD);
        world.func_72921_c(x + 1, y + 1, z + 1, 0, 4);
      } 
    } else if (EventUtils.isAir(world, x + 1, y, z + 1) && EventUtils.isAir(world, x + 1, y + 1, z + 1) && 
      EventUtils.isAir(world, x, y + 1, z + 1) && EventUtils.isAir(world, x, y, z + 1) && 
      EventUtils.isAir(world, x, y + 1, z) && EventUtils.isAir(world, x + 1, y, z) && 
      EventUtils.isAir(world, x + 1, y + 1, z)) {
      world.func_147449_b(x, y, z, BlocksRegister.BIG_HEAD);
      world.func_72921_c(x, y, z, 0, 4);
      world.func_147449_b(x, y + 1, z, BlocksRegister.BIG_HEAD);
      world.func_72921_c(x, y + 1, z, 1, 4);
      world.func_147449_b(x + 1, y, z, BlocksRegister.BIG_HEAD);
      world.func_72921_c(x + 1, y, z, 2, 4);
      world.func_147449_b(x + 1, y + 1, z, BlocksRegister.BIG_HEAD);
      world.func_72921_c(x + 1, y + 1, z, 3, 4);
      world.func_147449_b(x, y, z + 1, BlocksRegister.BIG_HEAD);
      world.func_72921_c(x, y, z + 1, 3, 4);
      world.func_147449_b(x, y + 1, z + 1, BlocksRegister.BIG_HEAD);
      world.func_72921_c(x, y + 1, z + 1, 2, 4);
      world.func_147449_b(x + 1, y, z + 1, BlocksRegister.BIG_HEAD);
      world.func_72921_c(x + 1, y, z + 1, 1, 4);
      world.func_147449_b(x + 1, y + 1, z + 1, BlocksRegister.BIG_HEAD);
      world.func_72921_c(x + 1, y + 1, z + 1, 0, 4);
    } 
  }
  
  public IIcon func_149691_a(int side, int meta) {
    if (side == 3) {
      switch (meta) {
        case 0:
          return this.icons[3];
        case 1:
          return this.icons[2];
        case 2:
          return this.icons[1];
        case 3:
          return this.icons[0];
      } 
    } else if (side == 2) {
      switch (meta) {
        case 0:
          return this.icons[2];
        case 1:
          return this.icons[3];
        case 2:
          return this.icons[0];
        case 3:
          return this.icons[1];
      } 
    } else if (side == 1) {
      switch (meta) {
        case 0:
          return this.icons[2];
        case 1:
          return this.icons[1];
        case 2:
          return this.icons[0];
        case 3:
          return this.icons[3];
      } 
    } else if (side == 4) {
      switch (meta) {
        case 0:
          return this.icons[0];
        case 1:
          return this.icons[1];
        case 2:
          return this.icons[3];
        case 3:
          return this.icons[2];
      } 
    } else if (side == 5) {
      switch (meta) {
        case 0:
          return this.icons[1];
        case 1:
          return this.icons[0];
        case 2:
          return this.icons[2];
        case 3:
          return this.icons[3];
      } 
    } 
    return this.icons[0];
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  protected boolean func_149700_E() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockBigHead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */