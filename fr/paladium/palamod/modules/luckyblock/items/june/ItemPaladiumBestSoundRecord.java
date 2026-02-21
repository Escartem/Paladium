package fr.paladium.palamod.modules.luckyblock.items.june;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.items.ItemDisc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemPaladiumBestSoundRecord extends ItemDisc {
  public ItemPaladiumBestSoundRecord() {
    super("paladium_best_sound");
    func_77655_b("paladium_best_sound");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public boolean func_77648_a(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
    if (world.func_147439_a(x, y, z) instanceof net.minecraft.block.BlockJukebox && world.func_72805_g(x, y, z) == 0 && 
      !world.field_72995_K) {
      String texturePackUrl = "https://download.paladium-pvp.fr/PaladiumBestSound.zip";
      S3FPacketCustomPayload packet = new S3FPacketCustomPayload("MC|RPack", texturePackUrl.getBytes());
      int radius = 5;
      for (Object o : world.func_72872_a(EntityPlayerMP.class, AxisAlignedBB.func_72330_a((x - radius), (y - radius), (z - radius), (x + radius), (y + radius), (z + radius)))) {
        if (o instanceof EntityPlayerMP) {
          EntityPlayerMP p = (EntityPlayerMP)o;
          p.field_71135_a.func_147359_a((Packet)packet);
        } 
      } 
    } 
    return super.func_77648_a(itemStack, player, world, x, y, z, par7, par8, par9, par10);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\june\ItemPaladiumBestSoundRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */