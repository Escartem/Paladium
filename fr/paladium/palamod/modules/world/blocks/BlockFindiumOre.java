package fr.paladium.palamod.modules.world.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.utils.FindiumOreFX;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockFindiumOre extends BlockOre {
  public BlockFindiumOre(Material material, float hardness, String texture) {
    super(material, hardness, texture);
    func_149675_a(true);
  }
  
  public int func_149738_a(World worldIn) {
    return 30;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World worldIn, int x, int y, int z, Random r) {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    if (entityClientPlayerMP.func_70011_f(x, y, z) <= 5.0D)
      spawnParticles(worldIn, x, y, z); 
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return ItemsRegister.FINDIUM;
  }
  
  @SideOnly(Side.CLIENT)
  private void spawnParticles(World worldIn, int x, int y, int z) {
    Random random = worldIn.field_73012_v;
    double xOff = random.nextFloat();
    double yOff = random.nextFloat();
    double zOff = random.nextFloat();
    switch (random.nextInt(6)) {
      case 0:
        xOff = -0.01D;
        break;
      case 1:
        yOff = -0.01D;
        break;
      case 2:
        xOff = -0.01D;
        break;
      case 3:
        zOff = -0.01D;
        break;
      case 4:
        xOff = 1.01D;
        break;
      case 5:
        yOff = 1.01D;
        break;
      case 6:
        zOff = 1.01D;
        break;
    } 
    if (PalaMod.proxy.shouldAddParticles(random)) {
      FindiumOreFX fx = new FindiumOreFX(worldIn, x + xOff, y + yOff, z + zOff, 0.0F, 0.0F, 0.0F);
      (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)fx);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\world\blocks\BlockFindiumOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */