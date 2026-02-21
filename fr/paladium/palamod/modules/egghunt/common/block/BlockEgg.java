package fr.paladium.palamod.modules.egghunt.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palamod.modules.achievements.types.EggHuntActionAchievement;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.modules.egghunt.common.CommonEventHandler;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntData;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntPlayerEggInput;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntServerUUIDInput;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntStatus;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palamod.util.BlockUtils;
import fr.paladium.palamod.util.DurationConverter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import retrofit2.Response;

public class BlockEgg extends Block {
  private static SecureRandom random = new SecureRandom();
  
  private final IIcon[] icons;
  
  public BlockEgg() {
    super(Material.field_151566_D);
    this.icons = new IIcon[8];
    func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, 0.9375F);
    func_149722_s();
    func_149711_c(Float.MAX_VALUE);
    func_149752_b(6000000.0F);
    func_149672_a(Block.field_149780_i);
    func_149715_a(0.125F);
  }
  
  public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List<ItemStack> p_149666_3_) {
    for (int i = 0; i < 8; i++)
      p_149666_3_.add(new ItemStack(p_149666_1_, 1, i)); 
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (!world.field_72995_K) {
      if (player.func_70694_bm() != null) {
        player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§cVous devez avoir la main vide pour ramasser l'oeuf du dragon."));
        return true;
      } 
      EggHuntStatus status = PEggHunt.status;
      EggHuntData data = PEggHunt.data;
      EggHuntServerUUIDInput input = PEggHunt.serverInput;
      if (status == null || input == null || !data.isActive()) {
        player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§cCet oeuf semble venir d'une autre contrée, vous ne pouvez donc rien en faire."));
        BlockUtils.setBlockToAir(world, x, y, z);
        return true;
      } 
      if (status.getDragonDeathTime() <= -1L) {
        player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§cLa bête qui protège cet oeuf est encore en vie, trouvez la et tuez la afin de pouvoir prendre l'oeuf !"));
        return true;
      } 
      long diff = System.currentTimeMillis() - status.getDragonDeathTime();
      if (diff < (data.isEndEvent() ? 300000L : 600000L) && status.getDragonKiller() != null && !status.getDragonKiller().equalsIgnoreCase(player.func_70005_c_())) {
        player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§cVous n'êtes pas le courageux guerrier qui a fait trépasser le dragon, cet oeuf vous sera accéssible dans §e" + DurationConverter.fromMillisToString((data.isEndEvent() ? 300000L : 600000L) - diff)));
        return true;
      } 
      BlockUtils.setBlockToAir(world, x, y, z);
      BlockPos validPosition = new BlockPos((Entity)player);
      PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
            try {
              EggHuntPlayerEggInput playerInput = new EggHuntPlayerEggInput(player);
              PEggHunt.setOwner(playerInput, false, ());
            } catch (IOException e) {
              e.printStackTrace();
            } 
          }5L);
    } 
    return true;
  }
  
  public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
    return true;
  }
  
  public int func_149645_b() {
    return -27;
  }
  
  public void func_149651_a(IIconRegister re) {
    for (int i = 0; i < this.icons.length; i++)
      this.icons[i] = re.func_94245_a(func_149641_N() + "_" + i); 
  }
  
  public IIcon func_149691_a(int side, int meta) {
    return (meta >= 8) ? this.icons[0] : this.icons[meta];
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\common\block\BlockEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */