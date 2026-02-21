package fr.paladium.palamod.modules.egghunt.common.item;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.achievements.types.EggHuntActionAchievement;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.modules.egghunt.network.SCPacketShowEggPos;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemDragonRoll extends Item {
  public ItemDragonRoll() {
    func_77656_e(2048);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (stack.func_77960_j() < stack.func_77958_k()) {
      if (!world.field_72995_K && player instanceof EntityPlayerMP) {
        if (PEggHunt.status == null) {
          player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§cRecherche de dragon en cours."));
          return stack;
        } 
        if (!PEggHunt.data.isActive()) {
          player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§cAucune chasse n'a lieu en ce moment sur ce serveur."));
          return stack;
        } 
        EggHuntActionAchievement.performCheck(player, 2);
        if (PEggHunt.status.getEggOwner() == null) {
          int x = (PEggHunt.status.getCurrentPosition() == null) ? PEggHunt.config.getEggPosition().getX() : PEggHunt.status.getCurrentPosition().getX();
          int y = (PEggHunt.status.getCurrentPosition() == null) ? PEggHunt.config.getEggPosition().getY() : PEggHunt.status.getCurrentPosition().getY();
          int z = (PEggHunt.status.getCurrentPosition() == null) ? PEggHunt.config.getEggPosition().getZ() : PEggHunt.status.getCurrentPosition().getZ();
          PEggHunt.network.sendTo((IMessage)new SCPacketShowEggPos(x, y, z), (EntityPlayerMP)player);
          return stack;
        } 
        PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
              EntityPlayer target = world.func_72924_a(PEggHunt.status.getEggOwner());
              int x = (target == null) ? PEggHunt.status.getCurrentPosition().getX() : (int)target.field_70165_t;
              int y = (target == null) ? PEggHunt.status.getCurrentPosition().getY() : (int)target.field_70163_u;
              int z = (target == null) ? PEggHunt.status.getCurrentPosition().getZ() : (int)target.field_70161_v;
              PEggHunt.network.sendTo((IMessage)new SCPacketShowEggPos(x, y, z), (EntityPlayerMP)player);
              if (target != null)
                player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§eLe joueur en possesion de l'oeuf est §c" + target.func_70005_c_() + " §8(" + x + ", " + y + ", " + z + ")")); 
            }0L);
      } 
      stack.func_77972_a(1, (EntityLivingBase)player);
    } 
    return stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\common\item\ItemDragonRoll.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */