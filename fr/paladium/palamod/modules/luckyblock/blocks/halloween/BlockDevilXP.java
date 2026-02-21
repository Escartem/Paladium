package fr.paladium.palamod.modules.luckyblock.blocks.halloween;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.security.SecureRandom;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockDevilXP extends Block {
  @SideOnly(Side.CLIENT)
  private IIcon icons_front;
  
  @SideOnly(Side.CLIENT)
  private IIcon icons_back;
  
  @SideOnly(Side.CLIENT)
  private IIcon icons_top;
  
  public BlockDevilXP() {
    super(Material.field_151573_f);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("devilxp");
    func_149711_c(0.5F);
    func_149715_a(1.0F);
    func_149658_d("palamod:devilxp/pumpkin");
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int meta, float what, float these, float are) {
    if (!world.field_72995_K && EventUtils.canInteract(player, x, y, z)) {
      world.func_147449_b(x, y, z, Blocks.field_150350_a);
      SecureRandom r = new SecureRandom();
      JobType job = JobType.values()[player.field_70170_p.field_73012_v.nextInt((JobType.values()).length)];
      if (r.nextBoolean()) {
        player.func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.GOLD + "[" + EnumChatFormatting.GREEN + "LuckyBlock" + EnumChatFormatting.GOLD + "]" + EnumChatFormatting.GOLD + " Tu viens de gagner un level en " + job
              
              .getName()));
        JobsPlayer.get((Entity)player).setExperience(job, JobExpUtils.getNeededXpForLvl(JobsPlayer.get((Entity)player).getLevel(job) + 1));
      } else {
        player.func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.GOLD + "[" + EnumChatFormatting.GREEN + "LuckyBlock" + EnumChatFormatting.GOLD + "]" + EnumChatFormatting.GOLD + " Tu viens de perdre un level en " + job
              
              .getName()));
        JobsPlayer.get((Entity)player).setExperience(job, -JobExpUtils.getNeededXpForLvl(JobsPlayer.get((Entity)player).getLevel(job) - 1));
      } 
    } 
    return true;
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons_top = register.func_94245_a(func_149641_N() + "_top");
    this.field_149761_L = register.func_94245_a(func_149641_N() + "_side");
    this.icons_front = register.func_94245_a(func_149641_N() + "_front");
    this.icons_back = register.func_94245_a(func_149641_N() + "_back");
    super.func_149651_a(register);
  }
  
  public IIcon func_149691_a(int side, int meta) {
    return (side == 1) ? this.icons_top : ((side == 0) ? this.icons_top : ((side == 2) ? this.icons_front : ((side == 3) ? this.icons_back : this.field_149761_L)));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\halloween\BlockDevilXP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */