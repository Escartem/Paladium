package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityUltraSafeChest;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockUltraSafeChest extends BlockMegaSafeChest {
  public BlockUltraSafeChest(String unlocalizedName) {
    super(unlocalizedName);
    this.texture = "safechest/ultrasafe_front";
    func_149658_d("palamod:safechest/ultrasafe_front");
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntityUltraSafeChest();
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a("palamod:safechest/ultrasafe_front");
    this.icons[1] = register.func_94245_a("palamod:safechest/ultrasafe_side");
    this.field_149761_L = register.func_94245_a(func_149641_N());
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.-lucky-block-en-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockUltraSafeChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */