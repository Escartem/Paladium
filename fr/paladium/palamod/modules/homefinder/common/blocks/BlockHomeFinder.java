package fr.paladium.palamod.modules.homefinder.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.homefinder.common.managers.HomeFinderManager;
import fr.paladium.palamod.modules.homefinder.common.tiles.TileEntityHomeFinder;
import fr.paladium.palamod.modules.homefinder.common.utils.HUtils;
import fr.paladium.palamod.modules.homefinder.common.utils.Location;
import fr.paladium.palamod.modules.homefinder.common.utils.RadiusEnum;
import fr.paladium.palamod.modules.pillage.common.blocks.BlockBase;
import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import fr.paladium.palamod.modules.smeltery.registerer.PSRegister_Items;
import fr.welsymc.guardiangolem.common.init.ItemInit;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHomeFinder extends BlockBase implements ITooltipWiki {
  IIcon[] iconsOff;
  
  IIcon[] iconsSearchingMode;
  
  IIcon[] iconsProtectionMode;
  
  IIcon bottom;
  
  public BlockHomeFinder(String unlocalizedName) {
    super(Material.field_151576_e, unlocalizedName);
    func_149658_d("palamod:pillage/home_finder/" + unlocalizedName);
    func_149711_c(4.0F);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return true; 
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntityHomeFinder))
      return true; 
    TileEntityHomeFinder homeFinder = (TileEntityHomeFinder)tileEntity;
    ItemStack stack = player.func_71045_bC();
    updateClientSide(player, homeFinder);
    if (homeFinder.getOwner() == null)
      try {
        homeFinder.setOwner(player);
        Location location = new Location(x, y, z);
        if (!HomeFinderManager.getInstance().getFinders().contains(location))
          HomeFinderManager.getInstance().getFinders().add(location); 
      } catch (Exception e) {
        e.printStackTrace();
      }  
    sendFinderMessage(player, homeFinder);
    if (!homeFinder.isOwner(player)) {
      HUtils.sendMessage(player, new String[] { "§8[§6HomeFinder§8]§r §cVous n'êtes pas le propriétaire de cette structure !" });
      return true;
    } 
    homeFinder.setOwnerName(player.getDisplayName());
    if (stack == null) {
      if (player.func_70093_af()) {
        homeFinder.setClaimed(!homeFinder.isClaimed());
        HUtils.sendMessage(player, new String[] { String.format("§8[§6HomeFinder§8]§r §eLa structure a été %s §eavec succès !", new Object[] { homeFinder.isClaimed() ? "§averrouillée" : "§cdéverrouillée" }) });
        updateClientSide(player, homeFinder);
        return true;
      } 
      return true;
    } 
    Item item = stack.func_77973_b();
    if (item.equals(PSRegister_Items.FASTSWORD_TITANE)) {
      if (homeFinder.isBuilt()) {
        HUtils.sendMessage(player, new String[] { "§8[§6HomeFinder§8]§r §ccette structure est déjà construite !" });
        return true;
      } 
      player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
      homeFinder.setBuilt(true);
      HUtils.sendMessage(player, new String[] { "§8[§6HomeFinder§8]§r §aLa structure a été construite avec succès !" });
      updateClientSide(player, homeFinder);
      return true;
    } 
    if (item == ItemInit.GUARDIAN_WHITELIST) {
      List<String> authorizedPlayers = new ArrayList<>();
      if (!stack.func_77942_o() || !stack.func_77978_p().func_74764_b("players")) {
        HUtils.sendMessage(player, new String[] { "§8[§6HomeFinder§8]§r §cIl n'y a aucun joueur dans la liste !" });
        return true;
      } 
      NBTTagList players = stack.func_77978_p().func_150295_c("players", 8);
      for (int i = 0; i < players.func_74745_c(); i++)
        authorizedPlayers.add(players.func_150307_f(i)); 
      if (authorizedPlayers.isEmpty()) {
        HUtils.sendMessage(player, new String[] { "§8[§6HomeFinder§8]§r §cIl n'y a aucun joueur dans la liste !" });
        return true;
      } 
      homeFinder.setAuthorizedList(authorizedPlayers);
      player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
      HUtils.sendMessage(player, new String[] { "§8[§6HomeFinder§8]§r §aLes joueurs ont été ajoutés avec succès !" });
      updateClientSide(player, homeFinder);
    } 
    return true;
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntityHomeFinder))
      return; 
    TileEntityHomeFinder homeFinder = (TileEntityHomeFinder)tileEntity;
    homeFinder.clearOldEntities();
    super.func_149749_a(world, x, y, z, block, meta);
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityHomeFinder();
  }
  
  public boolean hasTileEntity(int meta) {
    return true;
  }
  
  public void updateClientSide(EntityPlayer player, TileEntityHomeFinder homeFinder) {
    if (player instanceof EntityPlayerMP) {
      EntityPlayerMP playerMP = (EntityPlayerMP)player;
      Packet packet = homeFinder.func_145844_m();
      playerMP.field_71135_a.func_147359_a(packet);
      List<EntityPlayerMP> players = player.field_70170_p.func_72872_a(EntityPlayerMP.class, 
          AxisAlignedBB.func_72330_a(player.field_70165_t - 32.0D, player.field_70163_u - 32.0D, player.field_70161_v - 32.0D, player.field_70165_t + 32.0D, player.field_70163_u + 32.0D, player.field_70161_v + 32.0D));
      for (EntityPlayerMP playerArround : players) {
        EntityPlayerMP playerMPArround = playerArround;
        playerMPArround.field_71135_a.func_147359_a(packet);
      } 
    } 
  }
  
  public void sendFinderMessage(EntityPlayer player, TileEntityHomeFinder finder) {
    int level = getTowerSize(finder.func_145831_w(), finder.field_145851_c, finder.field_145848_d, finder.field_145849_e);
    int radius = RadiusEnum.getRadiusFromFloor(level);
    String status = (finder.isClaimed() && finder.isCovered()) ? ("§averrouillée §7(§6" + HUtils.formatTime(finder.getLastFindiumBurnedMillis() - System.currentTimeMillis()) + "§7)") : "§cnon-protégé";
    HUtils.sendMessage(player, new String[] { "§8§m--------------------------------------", "§ePropriétaire: §6" + (
          
          (finder.getOwnerName() == null) ? "Inconnu" : finder.getOwnerName()), "§eAutorisés: §6" + finder
          .getAuthorizedList().toString(), "§eNiveau: §6" + level, "§eRayon: §6" + radius, "§eFindium: §6" + finder
          
          .getFuel() + " / " + '\020', "§eStatut: §6" + status, "§8§m--------------------------------------" });
  }
  
  public int getTowerSize(World world, int x, int y, int z) {
    Block block = world.func_147439_a(x, y - 1, z);
    Block empoweredQuartz = PPRegisterer.PPBlocks.EMPOWERED_QUARTZ;
    if (!block.equals(empoweredQuartz))
      return 0; 
    int multiplier = 1;
    int yPos = y - 1;
    int size = 0;
    for (size = 0; size < 5; size++) {
      int minX = x - multiplier;
      int maxX = x + multiplier;
      int minZ = z - multiplier;
      int maxZ = z + multiplier;
      for (int xPos = minX; xPos <= maxX; xPos++) {
        for (int zPos = minZ; zPos <= maxZ; zPos++) {
          if (!world.func_147439_a(xPos, yPos, zPos).equals(empoweredQuartz))
            return size; 
        } 
      } 
      multiplier++;
      yPos--;
    } 
    return size;
  }
  
  public void register() {
    GameRegistry.registerBlock((Block)this, func_149739_a());
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegister) {
    this.bottom = iconRegister.func_94245_a(func_149641_N() + "_bottom");
    this.iconsOff = new IIcon[2];
    this.iconsOff[0] = iconRegister.func_94245_a(func_149641_N() + "_off_top");
    this.iconsOff[1] = iconRegister.func_94245_a(func_149641_N() + "_off_side");
    this.iconsSearchingMode = new IIcon[2];
    this.iconsSearchingMode[0] = iconRegister.func_94245_a(func_149641_N() + "_searching_top");
    this.iconsSearchingMode[1] = iconRegister.func_94245_a(func_149641_N() + "_searching_side");
    this.iconsProtectionMode = new IIcon[2];
    this.iconsProtectionMode[0] = iconRegister.func_94245_a(func_149641_N() + "_protection_top");
    this.iconsProtectionMode[1] = iconRegister.func_94245_a(func_149641_N() + "_protection_side");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int dir) {
    TileEntity te = world.func_147438_o(x, y, z);
    if (te instanceof TileEntityHomeFinder) {
      TileEntityHomeFinder teHomeFinder = (TileEntityHomeFinder)te;
      if (!teHomeFinder.isBuilt())
        return (dir == 0) ? this.bottom : ((dir == 1) ? this.iconsOff[0] : this.iconsOff[1]); 
      if (teHomeFinder.isClaimed() && teHomeFinder.isCovered())
        return (dir == 0) ? this.bottom : ((dir == 1) ? this.iconsSearchingMode[0] : this.iconsSearchingMode[1]); 
      return (dir == 0) ? this.bottom : ((dir == 1) ? this.iconsProtectionMode[0] : this.iconsProtectionMode[1]);
    } 
    return (dir == 0) ? this.bottom : ((dir == 1) ? this.iconsOff[0] : this.iconsOff[1]);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return (side == 0) ? this.bottom : ((side == 1) ? this.iconsOff[0] : this.iconsOff[1]);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#9.-home-remover-et-coordinate-jammer";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\common\blocks\BlockHomeFinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */