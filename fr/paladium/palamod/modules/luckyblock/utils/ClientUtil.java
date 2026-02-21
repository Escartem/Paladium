package fr.paladium.palamod.modules.luckyblock.utils;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;

public class ClientUtil {
  public static void setBlockIfAir(int x, int y, int z, Block block) {
    if ((Minecraft.func_71410_x()).field_71441_e != null && (Minecraft.func_71410_x()).field_71441_e.func_147437_c(x, y, z))
      (Minecraft.func_71410_x()).field_71441_e.func_147449_b(x, y, z, block); 
  }
  
  public static void updateBlock(int x, int y, int z) {
    (Minecraft.func_71410_x()).field_71441_e.func_147471_g(x, y, z);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\ClientUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */