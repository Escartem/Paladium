package fr.paladium.palamod.modules.design.modules.unclaimfinder;

import fr.paladium.helios.client.utils.DrawingContext;
import fr.paladium.helios.module.utils.abstracts.AWidget;
import fr.paladium.helios.module.utils.annotations.Widget;
import fr.paladium.lib.apollon.fontV2.FontObj;
import fr.paladium.lib.apollon.fontV2.FontRenderer;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.alchimiste.common.init.EnchantMod;
import fr.paladium.palamod.modules.alchimiste.common.utils.WSEnchantUtils;
import fr.paladium.palamod.modules.paladium.common.items.ItemUnclaimFinder;
import fr.paladium.tutorial.common.network.packet.tutorial.cs.CSUnclaimFinderFoundPacket;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@Widget(type = RenderGameOverlayEvent.ElementType.TEXT, post = false)
public class WidgetUnclaimFinder extends AWidget {
  public void init(DrawingContext context) {
    setBoundingBox(94.0D, 50.0D, 5.0D, 8.0D);
  }
  
  public void draw(DrawingContext context) {
    ItemUnclaimFinder itemUnclaimFinder;
    double ratio = context.getHeight() / context.getWidth();
    setRelativeHeight(this.relativeWidth / ratio);
    Minecraft mc = context.getMinecraft();
    boolean shouldReturn = (mc.field_71439_g.func_70694_bm() == null || !(mc.field_71439_g.func_70694_bm().func_77973_b() instanceof ItemUnclaimFinder) || !mc.field_71441_e.field_72995_K);
    boolean useHelmetInsteadOfHand = false;
    String helmetUnclaimType = "";
    if (shouldReturn) {
      if (mc.field_71439_g.field_71071_by.func_70440_f(3) == null)
        return; 
      Item item1 = mc.field_71439_g.field_71071_by.func_70440_f(3).func_77973_b();
      if (!(item1 instanceof ItemArmor) || !Objects.equals(Integer.valueOf(((ItemArmor)item1).field_77881_a), Integer.valueOf(0)))
        return; 
      if (!WSEnchantUtils.hasEnchantment(mc.field_71439_g.field_71071_by.func_70440_f(3), EnchantMod.unclaimHelmet.field_77352_x))
        return; 
      NBTTagCompound tag = mc.field_71439_g.field_71071_by.func_70440_f(3).func_77942_o() ? mc.field_71439_g.field_71071_by.func_70440_f(3).func_77978_p() : new NBTTagCompound();
      if (tag == null)
        return; 
      if (!tag.func_74764_b("alchemistUnclaimType"))
        return; 
      shouldReturn = false;
      useHelmetInsteadOfHand = true;
      helmetUnclaimType = tag.func_74779_i("alchemistUnclaimType");
    } 
    if (shouldReturn)
      return; 
    preDraw(context, ModuleUnclaimFinder.getInstance());
    int type = 0;
    if (useHelmetInsteadOfHand) {
      if (helmetUnclaimType.equals("unclaimfinder")) {
        type = 0;
      } else if (helmetUnclaimType.equals("unclaimfinder_bleu")) {
        type = 3;
      } else if (helmetUnclaimType.equals("unclaimfinder_rouge")) {
        type = 2;
      } else if (helmetUnclaimType.equals("unclaimfinder_orange")) {
        type = 1;
      } 
    } else {
      type = ((ItemUnclaimFinder)mc.field_71439_g.func_70694_bm().func_77973_b()).getTypeId();
    } 
    Item item = null;
    if (type == 0) {
      itemUnclaimFinder = ItemsRegister.UNCLAIMFINDER;
    } else if (type == 1) {
      itemUnclaimFinder = ItemsRegister.UNCLAIMFINDER_ORANGE;
    } else if (type == 2) {
      itemUnclaimFinder = ItemsRegister.UNCLAIMFINDER_ROUGE;
    } else if (type == 3) {
      itemUnclaimFinder = ItemsRegister.UNCLAIMFINDER_BLEU;
    } 
    GuiUtils.renderScaledItemStackIntoGUI(new ItemStack((Item)itemUnclaimFinder), getX() + width(10.0D), getY(), (float)width(80.0D) / 16.0F);
    int percent = getChuckTiles(mc, type) + getEntityInventory(mc, type);
    String show = percent + "%";
    drawStringWithShadow(mc, show, getX() + getWidth() / 2.0D, getY() + height(85.0D), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 40);
    CSUnclaimFinderFoundPacket.sendPacket(percent);
  }
  
  private void drawStringWithShadow(Minecraft mc, String text, double x, double y, Color color, FontObj font, int fontSize) {
    float scaleFactor = Math.min(mc.field_71443_c, 1920) / 1920.0F;
    fontSize = (int)(fontSize * scaleFactor);
    FontRenderer fontrender = new FontRenderer(font.getFontRegular(), font.getFontBold());
    fontrender.drawString(text, color, (float)x - (GuiUtils.getStringWidth(mc, text, font, fontSize) / 2), (float)y, 10.0F, mc.field_71443_c / 1920.0F + fontSize / 80.0F, ModuleUnclaimFinder.getInstance().isTextShadow(), new Color(0, 0, 0, 150));
  }
  
  public int getChuckTiles(Minecraft mc, int radius) {
    int count = 0;
    int arpago = 0;
    int lucky = 0;
    for (int x = -radius; radius >= x; x++) {
      for (int z = -radius; radius >= z; z++) {
        count = (int)(count + (mc.field_71441_e.func_72964_e(mc.field_71439_g.field_70176_ah + x, mc.field_71439_g.field_70164_aj + z)).field_150816_i.values().stream().filter(value -> value instanceof IInventory).count());
        arpago = (int)(arpago + (mc.field_71441_e.func_72964_e(mc.field_71439_g.field_70176_ah + x, mc.field_71439_g.field_70164_aj + z)).field_150816_i.values().stream().filter(value -> value instanceof fr.paladium.palamod.modules.paladium.common.logics.TileHarpagophytumFlower).count());
        lucky = (int)(lucky + (mc.field_71441_e.func_72964_e(mc.field_71439_g.field_70176_ah + x, mc.field_71439_g.field_70164_aj + z)).field_150816_i.values().stream().filter(value -> value instanceof fr.paladium.palamod.modules.luckyblock.tileentity.TileLuckyFlower).count());
      } 
    } 
    if (arpago >= count && count > 0)
      arpago = count - 1; 
    return (lucky == 0) ? Math.max(0, count - arpago) : 1;
  }
  
  public int getEntityInventory(Minecraft mc, int radius) {
    int count = 0;
    EntityClientPlayerMP entityClientPlayerMP = mc.field_71439_g;
    WorldClient worldClient = mc.field_71441_e;
    for (int x = -radius; radius >= x; x++) {
      for (int z = -radius; radius >= z; z++) {
        List[] arrayOfList = (worldClient.func_72964_e(((EntityPlayer)entityClientPlayerMP).field_70176_ah + x, ((EntityPlayer)entityClientPlayerMP).field_70164_aj + z)).field_76645_j;
        for (List<?> entity : arrayOfList)
          count = (int)(count + entity.stream().filter(IInventory.class::isInstance).count()); 
      } 
    } 
    return count;
  }
  
  public boolean canDraw(DrawingContext context) {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\design\module\\unclaimfinder\WidgetUnclaimFinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */