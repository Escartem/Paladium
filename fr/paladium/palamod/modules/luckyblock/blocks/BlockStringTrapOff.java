package fr.paladium.palamod.modules.luckyblock.blocks;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityStringTrapOff;
import fr.paladium.palamod.modules.paladium.network.OpenGuiPacket;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockStringTrapOff extends Block implements ITooltipWiki {
  public BlockStringTrapOff() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("string_trap_off");
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    func_149658_d("palamod:ficelle");
    func_149711_c(1.0F);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int X, float Y, float Z, float f) {
    TileEntity t = world.func_147438_o(x, y, z);
    if (t instanceof TileEntityStringTrapOff) {
      TileEntityStringTrapOff tile = (TileEntityStringTrapOff)t;
      if (!tile.isOn() && entityplayer.func_70093_af()) {
        if (tile.getOwner().equals(entityplayer.func_70005_c_())) {
          if (!world.field_72995_K)
            PalaMod.getNetwork().sendTo((IMessage)new OpenGuiPacket(PGuiRegistry.GUI_STRING_TRAP, true, x, y, z), (EntityPlayerMP)entityplayer); 
        } else if (!world.field_72995_K) {
          entityplayer.func_146105_b((IChatComponent)new ChatComponentText("§cVous n'êtes pas le propriétaire de ce piège."));
        } 
      } else if (entityplayer.func_71045_bC() != null && entityplayer
        .func_71045_bC().func_77973_b() == Items.field_151007_F) {
        if (!tile.isOn()) {
          if (!world.field_72995_K)
            entityplayer.func_146105_b((IChatComponent)new ChatComponentText("§7[§aON§7] §eVous venez d'enclencher le piège.")); 
          tile.setOn(true);
        } else if (!world.field_72995_K) {
          entityplayer
            .func_146105_b((IChatComponent)new ChatComponentText("§eLe piège est déjà enclenché."));
        } 
      } else if (entityplayer.func_70093_af()) {
        if (tile.isOn()) {
          if (!world.field_72995_K) {
            EntityItem entityitem = new EntityItem(world, x, y, z, new ItemStack(Items.field_151007_F));
            world.func_72838_d((Entity)entityitem);
            entityplayer.func_146105_b((IChatComponent)new ChatComponentText("§7[§cOFF§7] §eVous venez de désenclencher piège."));
          } 
          tile.setOn(false);
        } else if (!world.field_72995_K) {
          entityplayer.func_146105_b((IChatComponent)new ChatComponentText("§eLe piège n'est pas enclenché."));
        } 
      } else if (!world.field_72995_K) {
        help(entityplayer, tile);
      } 
    } 
    return true;
  }
  
  private void help(EntityPlayer p, TileEntityStringTrapOff tile) {
    p.func_146105_b((IChatComponent)new ChatComponentText(""));
    p.func_146105_b((IChatComponent)new ChatComponentText("§6Mode d'emploi:"));
    p.func_146105_b((IChatComponent)new ChatComponentText("§7Piège: " + (
          tile.isOn() ? "§aON" : "§cOFF")));
    p.func_146105_b((IChatComponent)new ChatComponentText("§7 - §aClique droit §7+ §bSneak + §5Main Vide §7> §aMettre à jour la WhiteList"));
    p.func_146105_b((IChatComponent)new ChatComponentText("§7 - §aClique droit §7+ §eFicelle §7> §aEnclencher le piège"));
    p.func_146105_b((IChatComponent)new ChatComponentText("§7 - §aClique droit §7+ §bSneak §7> §cDésenclencher le piège "));
    p.func_146105_b((IChatComponent)new ChatComponentText("§6Informations:"));
    p.func_146105_b((IChatComponent)new ChatComponentText("§7 - §aPropriétaire: §7" + tile.getOwner()));
    p.func_146105_b((IChatComponent)new ChatComponentText("§7 - §aWhitelist: §7" + (
          (tile.getWhitelist().length() > 0) ? tile.getWhitelist().replace(":", ", ").substring(1) : "")));
    p.func_146105_b((IChatComponent)new ChatComponentText(""));
  }
  
  public void func_149726_b(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_) {
    super.func_149726_b(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
    func_149930_e(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
  }
  
  private void func_149930_e(World p_149930_1_, int p_149930_2_, int p_149930_3_, int p_149930_4_) {
    if (!p_149930_1_.field_72995_K) {
      Block block = p_149930_1_.func_147439_a(p_149930_2_, p_149930_3_, p_149930_4_ - 1);
      Block block1 = p_149930_1_.func_147439_a(p_149930_2_, p_149930_3_, p_149930_4_ + 1);
      Block block2 = p_149930_1_.func_147439_a(p_149930_2_ - 1, p_149930_3_, p_149930_4_);
      Block block3 = p_149930_1_.func_147439_a(p_149930_2_ + 1, p_149930_3_, p_149930_4_);
      byte b0 = 3;
      if (block.func_149730_j() && !block1.func_149730_j())
        b0 = 3; 
      if (block1.func_149730_j() && !block.func_149730_j())
        b0 = 2; 
      if (block2.func_149730_j() && !block3.func_149730_j())
        b0 = 5; 
      if (block3.func_149730_j() && !block2.func_149730_j())
        b0 = 4; 
      p_149930_1_.func_72921_c(p_149930_2_, p_149930_3_, p_149930_4_, b0, 2);
    } 
  }
  
  public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
    int l = MathHelper.func_76128_c((p_149689_5_.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    if (l == 0)
      p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2); 
    if (l == 1)
      p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2); 
    if (l == 2)
      p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2); 
    if (l == 3)
      p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2); 
    if (p_149689_5_ instanceof EntityPlayer) {
      EntityPlayer p = (EntityPlayer)p_149689_5_;
      TileEntityStringTrapOff tile = (TileEntityStringTrapOff)p_149689_1_.func_147438_o(p_149689_2_, p_149689_3_, p_149689_4_);
      tile.setOwner(p.func_70005_c_());
    } 
  }
  
  public int func_149692_a(int metadata) {
    return metadata;
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    super.func_149681_a(world, x, y, z, meta, player);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> drops = new ArrayList<>();
    return drops;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderStringTrapOff;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityStringTrapOff();
  }
  
  public void func_149666_a(Item item, CreativeTabs tab, List list) {}
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.-lucky-block-en-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockStringTrapOff.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */