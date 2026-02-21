package fr.paladium.palamod.modules.luckyblock.blocks.june;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palajobs.utils.forge.player.PlayerUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.structures.StructureManager;
import fr.paladium.palamod.modules.luckyblock.structures.sound.SoundStructure;
import fr.paladium.palamod.modules.luckyblock.structures.sound.enums.SoundTurn;
import fr.paladium.palamod.modules.luckyblock.structures.sound.objects.SoundWave;
import fr.paladium.palamod.modules.luckyblock.structures.utils.LocatedBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityBrightSound;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBrightSound extends Block implements ITooltipInformations {
  private final IIcon[] icons = new IIcon[2];
  
  public BlockBrightSound() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("bright_sound");
    func_149658_d("palamod:bright_sound_off");
    func_149711_c(1.0F);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return activateClientSide(world, x, y, z); 
    Location blockLocation = new Location(world, x, y, z);
    Optional<SoundStructure> result = StructureManager.getInstance().getStructure(blockLocation);
    if (!result.isPresent())
      return activate(world, x, y, z); 
    SoundStructure structure = result.get();
    SoundWave currentWave = structure.getWave(structure.getCurrentWaveIndex());
    if (currentWave == null) {
      PlayerUtils.sendMessage(player, "§cIl n'y a pas de vague actuelle.");
      return false;
    } 
    if (!structure.isOwner(player)) {
      PlayerUtils.sendMessage(player, "§cVous n'êtes pas le propriétaire de cette structure.");
      return false;
    } 
    if (!structure.isCurrentWaveFinished(SoundTurn.BOT)) {
      PlayerUtils.sendMessage(player, "§cVous devez attendre la fin de la vague actuelle.");
      return false;
    } 
    if (structure.isCurrentWaveFinished(SoundTurn.PLAYER))
      return false; 
    LocatedBlock locatedBlock = structure.getLocatedBlock(blockLocation);
    if (locatedBlock == null) {
      PlayerUtils.sendMessage(player, "§cCe bloc n'est pas dans la structure.");
      return false;
    } 
    boolean ret = structure.sound(locatedBlock, currentWave, SoundTurn.PLAYER);
    if (!ret) {
      PlayerUtils.sendMessage(player, "§cVous avez échoué à jouer le son. le jeu va se réinitialiser.");
      PalaMod.getNetwork().sendToAllAround((IMessage)new PacketPlayCustomSound("failure"), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, x, y, z, 24.0D));
      structure.resetGame();
      return false;
    } 
    if (structure.isCurrentWaveFinished(SoundTurn.PLAYER)) {
      PlayerUtils.sendMessage(player, "§eVous avez réussi à jouer la vague. §7(" + (structure
          .getCurrentWaveIndex() + 1) + "/" + structure.getWaves().size() + ")");
      if (structure.hasNextWave()) {
        PlayerUtils.sendMessage(player, "§dLa prochaine vague va commencer.");
        structure.nextWave();
        return true;
      } 
      PlayerUtils.sendMessage(player, new String[] { "§aVous avez réussi à terminer l'épreuve. la structure va bientot se retirer.", "§aVous allez recevoir votre récompense dans quelques instants, veuillez rester ici" });
      structure.setExpirationMillis(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(5L));
      return true;
    } 
    PlayerUtils.sendMessage(player, "§aVous avez réussi à jouer une note. §7(" + currentWave.getPlayerIndex() + "/" + currentWave
        .getSoundAmount() + ")");
    return true;
  }
  
  public IIcon func_149673_e(IBlockAccess blockAccess, int x, int y, int z, int side) {
    TileEntity tileEntity = blockAccess.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntityBrightSound))
      return super.func_149673_e(blockAccess, x, y, z, side); 
    TileEntityBrightSound tileEntityBrightSound = (TileEntityBrightSound)tileEntity;
    return this.icons[Math.min(tileEntityBrightSound.func_145832_p(), this.icons.length - 1)];
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a("palamod:bright_sound_off");
    this.icons[1] = register.func_94245_a("palamod:bright_sound_on");
    this.field_149761_L = this.icons[0];
    super.func_149651_a(register);
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityBrightSound();
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Quand un joueur clique sur le bloc après qu’il a été", "posé le bloc s’allume et joue un son pendant une seconde." };
  }
  
  public boolean isState(World world, int x, int y, int z) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntityBrightSound))
      return false; 
    return ((TileEntityBrightSound)tileEntity).isState();
  }
  
  public boolean activateClientSide(World world, int x, int y, int z) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntityBrightSound))
      return false; 
    TileEntityBrightSound brightSound = (TileEntityBrightSound)tileEntity;
    if (brightSound.isState())
      return false; 
    brightSound.setOn();
    return true;
  }
  
  public boolean activate(World world, int x, int y, int z) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntityBrightSound))
      return false; 
    TileEntityBrightSound brightSound = (TileEntityBrightSound)tileEntity;
    if (brightSound.isState())
      return false; 
    brightSound.setOn();
    PalaMod.getNetwork().sendToAllAround((IMessage)new PacketPlayCustomSound("boop"), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, x, y, z, 24.0D));
    return true;
  }
  
  public boolean activateBypass(World world, int x, int y, int z) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntityBrightSound))
      return false; 
    TileEntityBrightSound brightSound = (TileEntityBrightSound)tileEntity;
    brightSound.setOff();
    brightSound.setOn();
    PalaMod.getNetwork().sendToAllAround((IMessage)new PacketPlayCustomSound("boop"), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, x, y, z, 24.0D));
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\june\BlockBrightSound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */