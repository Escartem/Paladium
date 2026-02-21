package fr.paladium.palamod.modules.luckyblock.items.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.common.CommonModule;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketSoundDetector;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemSoundDetector extends Item implements ITooltipInformations {
  public ItemSoundDetector() {
    func_77655_b("sound_detector");
    func_111206_d("palamod:sound_detector");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      NBTTagCompound tag = itemStack.func_77978_p();
      if (tag == null)
        tag = new NBTTagCompound(); 
      long cooldown = 0L;
      if (tag != null && tag.func_74764_b("cooldown"))
        cooldown = tag.func_74763_f("cooldown"); 
      if (TimeUtil.now() - cooldown > 240L) {
        String currentWorld = CommonModule.getInstance().getConfig().getServerName().toUpperCase();
        Vec3 pos = null;
        switch (currentWorld) {
          case "EGOPOLIS":
            pos = Vec3.func_72443_a(-448.0D, 47.0D, -1208.0D);
            break;
          case "AELORIA":
            pos = Vec3.func_72443_a(651.0D, 51.0D, 1030.0D);
            break;
          case "XANOTH":
            pos = Vec3.func_72443_a(-1340.0D, 37.0D, -745.0D);
            break;
          case "KILMORDRA":
            pos = Vec3.func_72443_a(-441.0D, 38.0D, 1331.0D);
            break;
          case "RUNEGARD":
            pos = Vec3.func_72443_a(-404.0D, 48.0D, -1215.0D);
            break;
        } 
        if (pos != null) {
          PalaMod.getNetwork().sendTo((IMessage)new PacketSoundDetector(Double.valueOf(pos.field_72450_a), Double.valueOf(pos.field_72448_b), Double.valueOf(pos.field_72449_c)), (EntityPlayerMP)player);
        } else {
          PalaMod.getNetwork().sendTo((IMessage)new PacketSoundDetector(), (EntityPlayerMP)player);
        } 
        tag.func_74772_a("cooldown", TimeUtil.now());
        itemStack.func_77982_d(tag);
      } 
    } 
    return itemStack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Indique le lieu le plus bruyant du serveur et la source de son la plus proche.", "Temps de rechargement : 4 heures." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\june\ItemSoundDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */