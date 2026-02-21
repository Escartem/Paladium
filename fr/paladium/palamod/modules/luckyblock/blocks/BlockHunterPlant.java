package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.blocks.BaseBlockFlower;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityHunterPlant;
import java.util.Random;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockHunterPlant extends BaseBlockFlower implements ITileEntityProvider, ITooltipWiki {
  public BlockHunterPlant(String unlocalizedName) {
    super(unlocalizedName);
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntityHunterPlant();
  }
  
  public void func_149674_a(World worldObj, int xCoord, int yCoord, int zCoord, Random random) {
    TileEntityHunterPlant te = (TileEntityHunterPlant)worldObj.func_147438_o(xCoord, yCoord, zCoord);
    if (te != null) {
      if (te.ticks % 3000 == 2950) {
        EntityZombie entityZombie2;
        EntitySkeleton entitySkeleton;
        EntityCreeper entityCreeper;
        EntitySpider entitySpider;
        EntityZombie entityZombie1;
        Entity entity = null;
        switch (worldObj.field_73012_v.nextInt(4)) {
          case 0:
            entityZombie2 = new EntityZombie(worldObj);
            break;
          case 1:
            entitySkeleton = new EntitySkeleton(worldObj);
            break;
          case 2:
            entityCreeper = new EntityCreeper(worldObj);
            break;
          case 3:
            entitySpider = new EntitySpider(worldObj);
            break;
          default:
            entityZombie1 = new EntityZombie(worldObj);
            break;
        } 
        entityZombie1.func_70107_b(xCoord, yCoord, zCoord);
        worldObj.func_72838_d((Entity)entityZombie1);
      } 
      te.ticks += 50;
    } 
    super.func_149674_a(worldObj, xCoord, yCoord, zCoord, random);
  }
  
  public boolean func_149716_u() {
    return true;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.-lucky-block-en-paladium-et-en-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockHunterPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */