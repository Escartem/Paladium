package fr.paladium.palamod.modules.paladium.common.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.items.BaseItem;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.util.BlockUtils;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemExtrapolatedBucket extends BaseItem implements ITooltipWiki {
  public ItemExtrapolatedBucket() {
    super("extrapolatedbucket");
    func_77625_d(1);
    func_77656_e(32);
    func_77655_b("extrapolatedbucket");
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (player.field_70170_p.field_72995_K)
      return itemStack; 
    MovingObjectPosition moveObjPos = func_77621_a(world, player, true);
    if (moveObjPos == null)
      return itemStack; 
    if (moveObjPos.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
      int i = moveObjPos.field_72311_b;
      int j = moveObjPos.field_72312_c;
      int k = moveObjPos.field_72309_d;
      Material material = world.func_147439_a(i, j, k).func_149688_o();
      int l = world.func_72805_g(i, j, k);
      if (material == Material.field_151586_h && l == 0) {
        if (setBlockToAir(moveObjPos, world, i, j, k))
          itemStack.func_77972_a(1, (EntityLivingBase)player); 
        return itemStack;
      } 
      if (material == Material.field_151587_i && l == 0) {
        if (setBlockToAir(moveObjPos, world, i, j, k))
          itemStack.func_77972_a(1, (EntityLivingBase)player); 
        return itemStack;
      } 
      if (material == BlocksRegister.FLUIDS_SULFURICWATER.func_149688_o() && l == 0) {
        if (setBlockToAir(moveObjPos, world, i, j, k))
          itemStack.func_77972_a(1, (EntityLivingBase)player); 
        return itemStack;
      } 
      if (material == BlocksRegister.FLUIDS_ANGELICWATER.func_149688_o() && l == 0) {
        if (setBlockToAir(moveObjPos, world, i, j, k))
          itemStack.func_77972_a(1, (EntityLivingBase)player); 
        return itemStack;
      } 
    } 
    return itemStack;
  }
  
  private boolean setBlockToAir(MovingObjectPosition moveObjPos, World world, int x, int y, int z) {
    if (PFactions.instance != null && PFactions.instance.getImpl() != null && PFactions.instance.getImpl().isFactionClaim(world, x, z)) {
      int xOffset = 0;
      int yOffset = 0;
      int zOffset = 0;
      switch (moveObjPos.field_72310_e) {
        case 0:
          yOffset = 1;
          break;
        case 1:
          yOffset = -1;
          break;
        case 2:
          zOffset = 1;
          break;
        case 3:
          zOffset = -1;
          break;
        case 4:
          xOffset = 1;
          break;
        case 5:
          xOffset = -1;
          break;
      } 
      int xBehind = x + xOffset;
      int yBehind = y + yOffset;
      int zBehind = z + zOffset;
      if (!world.func_147439_a(xBehind, yBehind, zBehind).func_149721_r()) {
        boolean normalCube = false;
        for (int i = -1; i <= 1; i++) {
          for (int j = -1; j <= 1; j++) {
            for (int k = -1; k <= 1; k++) {
              if (world.func_147439_a(xBehind + i, yBehind + j, zBehind + k).func_149721_r())
                normalCube = true; 
            } 
          } 
        } 
        if (!normalCube)
          return false; 
      } 
      BlockUtils.setBlockToAir(world, x, y, z);
      return true;
    } 
    world.func_147468_f(x, y, z);
    return true;
  }
  
  public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> list, boolean p_77624_4_) {
    list.add("§cIl n'est pas possible de l'utiliser dans un claim s'il n'a pas de bloc derrière la source");
    super.func_77624_a(p_77624_1_, p_77624_2_, list, p_77624_4_);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-le-pillage#7.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemExtrapolatedBucket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */