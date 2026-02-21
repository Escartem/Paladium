package fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.ironchest.IronChest;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TypeEffects;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import fr.paladium.palamod.modules.pillage.network.MessageHandler;
import fr.paladium.palamod.modules.pillage.network.packets.PacketCreateEffect;
import fr.paladium.palawither.common.utils.WitherUtils;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

public class WitherObsi implements ObjectEffect {
  public void applyEffect(World world, double x, double y, double z, int metadata, Entity entity) {
    int width = 3;
    int height = 6;
    int range = 10;
    AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(x - 10.0D, y - 10.0D, z - 10.0D, x + 10.0D, y + 10.0D, z + 10.0D);
    List<EntityLivingBase> entityWitherList = world.func_72872_a(EntityLivingBase.class, aabb);
    if (entityWitherList.isEmpty())
      return; 
    for (EntityLivingBase wither : entityWitherList) {
      if (WitherUtils.isWither((Entity)wither)) {
        for (int i = -3; i < 3; i++) {
          for (int j = 0; j < 6; j++) {
            for (int k = -3; k < 3; k++) {
              if (world.func_147437_c((int)wither.field_70165_t + i, (int)wither.field_70163_u + j, (int)wither.field_70161_v + k) && (
                i == -3 || i == 2 || j == 0 || j == 5 || k == -3 || k == 2))
                world.func_147465_d((int)wither.field_70165_t + i, (int)wither.field_70163_u + j, (int)wither.field_70161_v + k, Blocks.field_150343_Z, 1, 2); 
            } 
          } 
        } 
        MessageHandler.NETWORK.sendToAll((IMessage)new PacketCreateEffect((int)wither.field_70165_t, (int)wither.field_70163_u, (int)wither.field_70161_v, (byte)0));
        break;
      } 
    } 
  }
  
  @SubscribeEvent
  public void onHarvest(BlockEvent.HarvestDropsEvent event) {
    if (event.block == Blocks.field_150343_Z && event.blockMetadata == 1) {
      event.drops.clear();
      event.dropChance = 0.0F;
    } 
  }
  
  public ObjectEffect registerRecipes(Block block) {
    GameRegistry.addRecipe(new ItemStack(block, 2), new Object[] { "XXX", "YXY", "XXX", Character.valueOf('X'), Blocks.field_150343_Z, Character.valueOf('Y'), new ItemStack((Block)IronChest.ironChestBlock, 1, 2) });
    GameRegistry.addRecipe(new ItemStack(block, 2), new Object[] { "XXX", "YXY", "XXX", Character.valueOf('X'), BlocksRegister.HARDENED_OBSIDIAN, Character.valueOf('Y'), new ItemStack((Block)IronChest.ironChestBlock, 1, 2) });
    return this;
  }
  
  public String getName() {
    return "wither_obsi";
  }
  
  public String getDescription() {
    return "Emprisonne le wither et lui rend plus de 50% de ses hp";
  }
  
  public TypeEffects getTypeEffect() {
    return TypeEffects.WITHER_TNT;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\blocks\obsi\WitherObsi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */