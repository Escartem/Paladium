package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class RenderEventHandler {
  @SubscribeEvent
  public void onRender(RenderGameOverlayEvent event) {
    Minecraft minecraft = Minecraft.func_71410_x();
    EntityClientPlayerMP entityClientPlayerMP = minecraft.field_71439_g;
    ItemStack helmetStack = ((EntityPlayer)entityClientPlayerMP).field_71071_by.field_70460_b[3];
    boolean hasEyePatch = (helmetStack != null && helmetStack.func_77973_b() instanceof fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemEyePatch);
    if (!entityClientPlayerMP.func_70644_a((Potion)PLuckyBlock.EYE_PATCH) && !hasEyePatch)
      return; 
    ScaledResolution resolution = new ScaledResolution(minecraft, minecraft.field_71443_c, minecraft.field_71440_d);
    GuiUtils.drawRect(0.0D, 0.0D, resolution.func_78326_a() / 2.0D, resolution.func_78328_b(), Color.BLACK);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\events\RenderEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */