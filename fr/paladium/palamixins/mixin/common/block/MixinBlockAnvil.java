package fr.paladium.palamixins.mixin.common.block;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamixins.event.BlockAnvilActivatedEvent;
import net.minecraft.block.BlockAnvil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({BlockAnvil.class})
public abstract class MixinBlockAnvil {
  @Overwrite
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    MinecraftForge.EVENT_BUS.post((Event)new BlockAnvilActivatedEvent(world, x, y, z, player, side, hitX, hitY, hitZ));
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\common\block\MixinBlockAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */