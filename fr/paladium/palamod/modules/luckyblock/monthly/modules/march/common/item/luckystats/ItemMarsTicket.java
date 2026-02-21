package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.luckystats;

import fr.paladium.common.CommonModule;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.dialog.BlacksmithDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.util.FastUUID;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemMarsTicket extends Item {
  public static final String SERVER_NAME = "EVENT";
  
  public ItemMarsTicket() {
    func_77655_b("mars_ticket");
    func_111206_d("palamod:mars_ticket");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer globalPlayer) {
    if (world.field_72995_K)
      return itemStack; 
    if (itemStack.func_77942_o()) {
      NBTTagCompound nbt = itemStack.func_77978_p();
      String owner = nbt.func_74779_i("owner");
      if (owner != null && owner.equalsIgnoreCase(globalPlayer.func_110124_au().toString())) {
        if (isOnCorrectServer(globalPlayer)) {
          DoubleLocation location = new DoubleLocation(4885.0D, 76.0D, 4947.0D);
          location.teleportServer(globalPlayer);
          BlacksmithDialogManager.getInstance().sendDialog((EntityPlayerMP)globalPlayer, null);
          return itemStack;
        } 
      } else {
        MonthlyUtils.sendMessage(globalPlayer, new String[] { "§cVous n'êtes pas le propriétaire de ce ticket." });
        return itemStack;
      } 
    } else {
      NBTTagCompound nbt = new NBTTagCompound();
      nbt.func_74778_a("ownerName", globalPlayer.func_70005_c_());
      nbt.func_74778_a("owner", FastUUID.toString(globalPlayer.func_110124_au()));
      MonthlyUtils.sendMessage(globalPlayer, new String[] { "§eCe ticket est désormais à votre nom.", "§eUtilisez le à tout moment..." });
      itemStack.func_77982_d(nbt);
    } 
    return itemStack;
  }
  
  private boolean isOnCorrectServer(EntityPlayer player) {
    String serverName = CommonModule.getInstance().getConfig().getServerName();
    if (serverName == null || !serverName.equalsIgnoreCase("EVENT")) {
      MonthlyUtils.sendMessage(player, new String[] { "§eL'atelier se trouve sur le serveur Event, utilise le ticket là-bas pour être téléporté !" });
      return false;
    } 
    return true;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    if (item.func_77942_o()) {
      NBTTagCompound tagCompound = item.func_77978_p();
      String owner = tagCompound.func_74779_i("ownerName");
      list.add("§ePropriétaire: " + owner);
    } else {
      list.add("§cAucun propriétaire");
    } 
    super.func_77624_a(item, player, list, flag);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\item\luckystats\ItemMarsTicket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */