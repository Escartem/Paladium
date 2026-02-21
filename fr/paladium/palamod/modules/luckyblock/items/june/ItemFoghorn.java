package fr.paladium.palamod.modules.luckyblock.items.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.common.CommonModule;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.factions.core.player.Player;
import fr.paladium.factions.core.player.PlayerController;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.rabbitmq.packet.FoghornMessagePacket;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemFoghorn extends Item implements ITooltipInformations {
  public ItemFoghorn() {
    func_77655_b("foghorn");
    func_111206_d("palamod:foghorn");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77627_a(true);
    func_77656_e(5);
    setNoRepair();
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (!world.field_72995_K && 
      player instanceof EntityPlayerMP) {
      EntityPlayerMP playerMP = (EntityPlayerMP)player;
      NBTTagCompound tag = itemStack.func_77978_p();
      if (tag == null)
        tag = new NBTTagCompound(); 
      long cooldown = 0L;
      if (tag != null && tag.func_74764_b("cooldown"))
        cooldown = tag.func_74763_f("cooldown"); 
      if (TimeUtil.now() - cooldown > 43200L) {
        itemStack.func_77964_b(itemStack.func_77960_j() + 1);
        try {
          PlayerController controller = PlayerController.getInstance();
          Player player1 = controller.getLoadedPlayer(playerMP);
          if (player1 != null && player1.hasFaction()) {
            String currentWorld = CommonModule.getInstance().getConfig().getServerName();
            FoghornMessagePacket foghornMessagePacket = new FoghornMessagePacket(FastUUID.toString((Entity)player), currentWorld, playerMP.field_70165_t, playerMP.field_70163_u, playerMP.field_70161_v);
            foghornMessagePacket.send(PLuckyBlock.RABBIT);
            PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("foghorn"), playerMP);
          } 
        } catch (Exception exception) {}
        if (itemStack.func_77960_j() == itemStack.func_77958_k()) {
          world.func_72956_a((Entity)player, "random.break", 0.8F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
          itemStack.field_77994_a--;
        } 
        tag.func_74772_a("cooldown", TimeUtil.now());
        itemStack.func_77982_d(tag);
      } 
    } 
    return itemStack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Si vous utilisez l’objet, tous les membres de votre faction entendrons un son", "et pourront rejoindre votre position en cliquant sur le message qui leur sera envoyé." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\june\ItemFoghorn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */