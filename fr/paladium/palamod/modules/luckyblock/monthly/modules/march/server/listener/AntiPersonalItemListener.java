package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.helios.utils.BlockPos;
import fr.paladium.palajobs.utils.forge.player.EnchantmentUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class AntiPersonalItemListener {
  @SubscribeEvent
  public void onPlayerInteract(EntityInteractEvent event) {
    if (event.entityPlayer == null)
      return; 
    EntityPlayer player = event.entityPlayer;
    if (player.field_71071_by.func_70448_g() == null)
      return; 
    ItemStack stack = player.field_71071_by.func_70448_g();
    if (!(stack.func_77973_b() instanceof fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.luckystats.ItemPersonalThruster))
      return; 
    if (EnchantmentUtils.containsEnchantment(stack, Enchantment.field_77347_r))
      EnchantmentHelper.func_82782_a(new HashMap<>(), stack); 
    if (event.target instanceof EntityPlayer) {
      EntityPlayer target = (EntityPlayer)event.target;
      stack.func_77972_a(1, (EntityLivingBase)event.entityPlayer);
      World world = target.field_70170_p;
      BlockPos position = new BlockPos(target.field_70165_t, target.field_70163_u + 1.0D, target.field_70161_v);
      BlockPos newPos = position;
      int y = position.getY();
      for (y = position.getY(); y <= world.func_72800_K(); y++) {
        BlockPos checkPos = new BlockPos(position.getX(), y, position.getZ());
        Block block = world.func_147439_a(checkPos.getX(), checkPos.getY(), checkPos.getZ());
        boolean isLiquid = block.func_149688_o().func_76224_d();
        if ((!world.func_147437_c(checkPos.getX(), checkPos.getY(), checkPos.getZ()) && !isLiquid) || y >= 300) {
          newPos = checkPos;
          break;
        } 
        newPos = checkPos;
      } 
      if (world.func_147437_c(newPos.getX(), newPos.getY(), newPos.getZ())) {
        newPos = new BlockPos(position.getX(), 257, position.getZ());
      } else {
        newPos = newPos.down();
      } 
      MonthlyUtils.teleport(target, newPos.getX() + 0.5D, newPos.getY(), newPos.getZ() + 0.5D);
      event.setCanceled(true);
      MonthlyUtils.sendMessage(target, new String[] { "§dPouf, un pistolet antipersonnel vous a propulsé en l'air !" });
      MonthlyUtils.sendMessage(event.entityPlayer, new String[] { "§dPouf, le joueur a été propulsé à la couche " + y + " !" });
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\listener\AntiPersonalItemListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */