package fr.paladium.palamod.modules.luckyblock.items.june;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.structures.utils.StructureUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.util.BooleanResponse;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ItemGoldRecord extends ItemRecord {
  public ItemGoldRecord() {
    super("gold_record");
    func_77655_b("gold_record");
    func_111206_d("palamod:gold_record");
    func_77637_a(PLuckyBlock.TAB);
    func_77627_a(true);
    func_77656_e(20);
    setNoRepair();
  }
  
  public boolean func_77648_a(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
    if (world.func_147439_a(x, y, z) instanceof net.minecraft.block.BlockJukebox && world.func_72805_g(x, y, z) == 0) {
      if (!world.field_72995_K) {
        BooleanResponse response = replaceBlocks(player, world, x, y, z);
        if (!response.isSuccessful()) {
          PlayerUtils.sendMessage(player, response.getMessage());
          return true;
        } 
        world.func_72956_a((Entity)player, "random.break", 0.8F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
        player.func_70062_b(0, null);
        PalaMod.getNetwork().sendToAllAround((IMessage)new PacketPlayCustomSound("gold_record"), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, x, y, z, 24.0D));
      } 
      return true;
    } 
    return false;
  }
  
  public BooleanResponse replaceBlocks(EntityPlayer player, World world, int x, int y, int z) {
    Cuboid cuboid = new Cuboid(world, (x - 2), (y - 1), (z - 2), (x + 2), (y - 1), (z + 2));
    List<Chunk> chunks = StructureUtils.getChunks(cuboid);
    if (StructureUtils.isClaimed(chunks))
      return BooleanResponse.builder()
        .success(false)
        .message("§cVous ne pouvez pas poser de disque d'or dans une zone claim.")
        .build(); 
    for (Location location : cuboid.getLocations()) {
      if (StructureUtils.isAirAtLocation(location))
        return BooleanResponse.builder()
          .success(false)
          .message("§cIl manque des blocks sous votre Jukebox. (laissez une zone de 5x5 en dessous)")
          .build(); 
      if (StructureUtils.isBlocksAtLocation(location, new Block[] { Blocks.field_150340_R }))
        return BooleanResponse.builder()
          .success(false)
          .message("§cIl y a déjà de l'or ici.")
          .build(); 
      if (StructureUtils.isBedrockAtLocation(location))
        return BooleanResponse.builder()
          .success(false)
          .message("§cVous ne pouvez pas poser de disque d'or sur de la bedrock.")
          .build(); 
      if (!EventUtils.canInteract(player, location.getX(), location.getY(), location.getZ()))
        return BooleanResponse.builder()
          .success(false)
          .message("§cVous ne pouvez pas poser de disque d'or dans une zone protégée.")
          .build(); 
    } 
    for (Location location : cuboid.getLocations())
      location.getWorld().func_147449_b((int)location.getX(), (int)location.getY(), (int)location.getZ(), Blocks.field_150340_R); 
    return BooleanResponse.builder()
      .success(true)
      .build();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\june\ItemGoldRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */