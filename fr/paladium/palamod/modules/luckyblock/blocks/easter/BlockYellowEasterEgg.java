package fr.paladium.palamod.modules.luckyblock.blocks.easter;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.back2future.entities.EntityRabbit;
import fr.paladium.palamod.modules.back2future.entities.ModEntityList;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockBase;
import fr.paladium.palamod.modules.luckyblock.network.easter.PacketColoredParticle;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterEgg;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockYellowEasterEgg extends BlockBase implements ITooltipInformations {
  public BlockYellowEasterEgg() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("yellow_easter_egg");
    func_149658_d("palamod:easter_egg_yellow_item");
    func_149711_c(Float.MAX_VALUE);
    func_149752_b(Float.MAX_VALUE);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K && EventUtils.canInteract(player, x, y, z)) {
      PlayerUtils.dropItemStack((Entity)player, x, y, z, getRandomReward());
      world.func_147468_f(x, y, z);
      showParticles(world, x, y, z);
    } 
    return true;
  }
  
  public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {
    if (!world.field_72995_K && EventUtils.canInteract(player, x, y, z)) {
      ItemStack itemStack = player.func_70694_bm();
      if (itemStack != null && EnchantmentHelper.func_77506_a(Enchantment.field_77348_q.field_77352_x, itemStack) > 0)
        PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(BlocksRegister.YELLOW_EASTER_EGG)); 
      world.func_147468_f(x, y, z);
      showParticles(world, x, y, z);
    } 
  }
  
  private void showParticles(World world, int x, int y, int z) {
    int radius = 20;
    for (Object o : world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((x - radius), 0.0D, (z - radius), (x + radius), 255.0D, (z + radius)))) {
      if (o instanceof EntityPlayerMP) {
        EntityPlayerMP p = (EntityPlayerMP)o;
        PalaMod.getNetwork().sendTo((IMessage)new PacketColoredParticle(x + 0.5D, y + 0.5D, z + 0.5D, 1.0F, 1.0F, 0.0F, 2.0F), p);
      } 
    } 
  }
  
  private ItemStack getRandomReward() {
    int chance = ThreadLocalRandom.current().nextInt(1, 111);
    if (chance <= 25)
      return new ItemStack(Items.field_151110_aK); 
    if (chance <= 50)
      return new ItemStack(ItemsRegister.CHOCOLATE_EGG); 
    if (chance <= 65)
      return ModEntityList.getEggFor(EntityRabbit.class); 
    if (chance <= 80)
      return new ItemStack(ItemsRegister.PALADIUM_INGOT); 
    if (chance <= 90)
      return new ItemStack(BlocksRegister.BLOCK_PALADIUM); 
    if (chance <= 100)
      return new ItemStack(BlocksRegister.BLOCK_GPALADIUM); 
    return new ItemStack(ItemsRegister.EASTER_BUNNY_HELMET);
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderEasterEgg;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityEasterEgg(2);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Casse cet œuf pour obtenir une récompense." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\easter\BlockYellowEasterEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */