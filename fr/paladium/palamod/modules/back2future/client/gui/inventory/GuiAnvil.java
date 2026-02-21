package fr.paladium.palamod.modules.back2future.client.gui.inventory;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.inventory.ContainerAnvil;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class GuiAnvil extends GuiRepair {
  public GuiAnvil(EntityPlayer player, World world, int x, int y, int z) {
    super(player.field_71071_by, world, x, y, z);
    ContainerAnvil container = new ContainerAnvil(player, world, x, y, z);
    ReflectionHelper.setPrivateValue(GuiRepair.class, this, container, new String[] { "field_147092_v" });
    this.field_147002_h = (Container)container;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\gui\inventory\GuiAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */