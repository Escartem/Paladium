package fr.paladium.palamod.mixins.block;

import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({BlockEnchantmentTable.class})
public abstract class IMixinEnchantTable extends BlockContainer {
  protected IMixinEnchantTable(Material p_i45386_1_) {
    super(p_i45386_1_);
  }
  
  public boolean func_149727_a(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (p_149727_1_.field_72995_K)
      return true; 
    float power = 0.0F;
    for (int j = -1; j <= 1; j++) {
      for (int k = -1; k <= 1; k++) {
        if ((j != 0 || k != 0) && p_149727_1_
          .func_147437_c(p_149727_2_ + k, p_149727_3_, p_149727_4_ + j) && p_149727_1_
          .func_147437_c(p_149727_2_ + k, p_149727_3_ + 1, p_149727_4_ + j)) {
          power += ForgeHooks.getEnchantPower(p_149727_1_, p_149727_2_ + k * 2, p_149727_3_, p_149727_4_ + j * 2);
          power += ForgeHooks.getEnchantPower(p_149727_1_, p_149727_2_ + k * 2, p_149727_3_ + 1, p_149727_4_ + j * 2);
          if (k != 0 && j != 0) {
            power += ForgeHooks.getEnchantPower(p_149727_1_, p_149727_2_ + k * 2, p_149727_3_, p_149727_4_ + j);
            power += ForgeHooks.getEnchantPower(p_149727_1_, p_149727_2_ + k * 2, p_149727_3_ + 1, p_149727_4_ + j);
            power += ForgeHooks.getEnchantPower(p_149727_1_, p_149727_2_ + k, p_149727_3_, p_149727_4_ + j * 2);
            power += ForgeHooks.getEnchantPower(p_149727_1_, p_149727_2_ + k, p_149727_3_ + 1, p_149727_4_ + j * 2);
          } 
        } 
      } 
    } 
    if (power > 60.0F) {
      p_149727_5_.openGui(PalaMod.instance, PGuiRegistry.GUI_ENCHANTMENT_TABLE, p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
    } else if (JobsBridge.getLevel(p_149727_5_, "ALCHEMIST") >= 15) {
      p_149727_5_.openGui(PalaMod.instance, PGuiRegistry.GUI_READABLE_ENCHANTMENT_TABLE, p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
    } else {
      TileEntityEnchantmentTable tileentityenchantmenttable = (TileEntityEnchantmentTable)p_149727_1_.func_147438_o(p_149727_2_, p_149727_3_, p_149727_4_);
      p_149727_5_.func_71002_c(p_149727_2_, p_149727_3_, p_149727_4_, 
          tileentityenchantmenttable.func_145921_b() ? tileentityenchantmenttable.func_145919_a() : null);
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\block\IMixinEnchantTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */