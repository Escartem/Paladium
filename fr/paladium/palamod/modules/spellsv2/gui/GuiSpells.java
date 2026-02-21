package fr.paladium.palamod.modules.spellsv2.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.modules.spellsv2.ClientProxy;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.gui.buttons.GuiSpellButton;
import fr.paladium.palamod.modules.spellsv2.gui.buttons.GuiTexturedButton;
import fr.paladium.palamod.modules.spellsv2.gui.utils.GuiSpellUtils;
import fr.paladium.palamod.modules.spellsv2.network.server.PacketServerUpgradeSpell;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;
import fr.paladium.palamod.modules.spellsv2.utils.SpellSorter;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import java.awt.Color;
import java.util.Arrays;
import java.util.Comparator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiSpells extends GuiScreen {
  private Spells currentSpell = null;
  
  private int currentTiers = 1;
  
  private int scroll = 0;
  
  private int guiScale = -1;
  
  private boolean mustResize = false;
  
  private int descScroll = 0;
  
  private GuiTexturedButton prev;
  
  private GuiTexturedButton next;
  
  private boolean isScrolling;
  
  private long lastClick = -1L;
  
  public GuiSpells() {}
  
  public GuiSpells(int guiScale) {
    this.guiScale = guiScale;
    this.mustResize = true;
  }
  
  public GuiSpells(int id, int scroll, int tier) {
    this.currentSpell = Spells.values()[id];
    this.scroll = scroll;
    this.currentTiers = tier;
  }
  
  public void func_73866_w_() {
    ClientProxy.showHotbar = false;
    if (this.currentSpell == null) {
      this.currentSpell = Spells.values()[0];
      if (ClientManager.getSpell(this.currentSpell).isUnlock()) {
        this.currentTiers = ClientManager.getSpell(this.currentSpell).getTier();
      } else {
        this.currentTiers = 1;
      } 
    } 
    drawButtons();
  }
  
  private void drawButtons() {
    this.field_146292_n.clear();
    int pos = 0;
    Spells[] spells = Spells.values();
    Arrays.sort(spells, (Comparator<? super Spells>)new SpellSorter());
    int i;
    for (i = 0; i < spells.length; i++) {
      GuiSpellButton btn = new GuiSpellButton(spells[i].getSpell().getId(), width(19.84F), height(38.98F) + height(12.95F) * pos - this.scroll, width(19.95F), height(11.4F), spells[i]);
      this.field_146292_n.add(btn);
      if (height(38.98F) + height(12.95F) * pos - this.scroll > height(75.0F) || 
        height(38.98F) + height(12.95F) * pos - this.scroll + height(11.4F) < height(39.0F))
        btn.field_146124_l = false; 
      pos++;
    } 
    if (this.currentSpell != null) {
      for (i = 1; i <= this.currentSpell.getSpell().getMaxTiers(); i++)
        this.field_146292_n.add(new GuiTexturedButton(-i, width(46.46F + 3.44F * (i - 1)), 
              height(51.7F), width(2.92F), width(2.92F), (this.currentTiers == i) ? new ResourceLocation("palamod", "textures/gui/GuiSpells/" + i + ".png") : new ResourceLocation("palamod", "textures/gui/GuiSpells/" + i + "d.png"), new ResourceLocation("palamod", "textures/gui/GuiSpells/" + i + "h.png"), "Tier " + i)); 
      if (ClientManager.getSpell(this.currentSpell).getTier() < this.currentSpell.getSpell().getMaxTiers() && 
        ClientManager.getLevel() >= this.currentSpell.getSpell().getLevel() && 
        ClientManager.getPoints() >= this.currentSpell.getSpell().getCost()) {
        this.field_146292_n.add(new GuiTexturedButton(99, 
              width(54.69F), height(69.44F), width(11.51F), height(6.39F), 
              ClientManager.getSpell(this.currentSpell).isUnlock() ? new ResourceLocation("palamod", "textures/gui/GuiSpells/upgrade.png") : new ResourceLocation("palamod", "textures/gui/GuiSpells/unlock.png"), 
              
              ClientManager.getSpell(this.currentSpell).isUnlock() ? new ResourceLocation("palamod", "textures/gui/GuiSpells/upgradeh.png") : new ResourceLocation("palamod", "textures/gui/GuiSpells/unlockh.png"), (
              
              ClientManager.getSpell(this.currentSpell).isUnlock() ? "Améliorer " : "Débloquer ") + " pour " + this.currentSpell
              .getSpell().getCost() + " points"));
      } else {
        String tooltip = "";
        if (ClientManager.getSpell(this.currentSpell).isUnlock()) {
          if (ClientManager.getSpell(this.currentSpell).getTier() >= this.currentSpell.getSpell()
            .getMaxTiers()) {
            tooltip = "Vous ne pouvez plus améliorer ce sort.";
          } else if (this.currentSpell.getSpell().getCost() > ClientManager.getPoints()) {
            tooltip = "Vous n'avez pas assez de points";
          } 
        } else if (this.currentSpell.getSpell().getLevel() > ClientManager.getLevel()) {
          tooltip = "Vous ne possédez pas le niveau requis";
        } else if (this.currentSpell.getSpell().getCost() > ClientManager.getPoints()) {
          tooltip = "Vous n'avez pas assez de points";
        } 
        GuiTexturedButton btn = new GuiTexturedButton(99, width(54.69F), height(69.44F), width(11.51F), height(6.39F), ClientManager.getSpell(this.currentSpell).isUnlock() ? new ResourceLocation("palamod", "textures/gui/GuiSpells/upgraded.png") : new ResourceLocation("palamod", "textures/gui/GuiSpells/unlockd.png"), ClientManager.getSpell(this.currentSpell).isUnlock() ? new ResourceLocation("palamod", "textures/gui/GuiSpells/upgraded.png") : new ResourceLocation("palamod", "textures/gui/GuiSpells/unlockd.png"), tooltip);
        btn.field_146124_l = false;
        this.field_146292_n.add(btn);
      } 
      this.prev = new GuiTexturedButton(1000, width(70.0F), height(72.0F), width(1.5F), height(3.0F), new ResourceLocation("palamod", "textures/gui/GuiSpells/prev.png"), new ResourceLocation("palamod", "textures/gui/GuiSpells/prevh.png"), new ResourceLocation("palamod", "textures/gui/GuiSpells/prevd.png"));
      this.field_146292_n.add(this.prev);
      this.next = new GuiTexturedButton(1001, width(71.7F), height(72.0F), width(1.5F), height(3.0F), new ResourceLocation("palamod", "textures/gui/GuiSpells/next.png"), new ResourceLocation("palamod", "textures/gui/GuiSpells/nexth.png"), new ResourceLocation("palamod", "textures/gui/GuiSpells/nextd.png"));
      this.field_146292_n.add(this.next);
      if (this.descScroll <= 0)
        this.prev.field_146124_l = false; 
      int size = GuiSpellUtils.renderSplitTextSizeWithCustomFontWithShadow(this.currentSpell
          .getSpell().getDescription()[this.currentTiers - 1], width(46.8F), height(62.5F), 
          width(24.0F), (new Color(20, 20, 20, 217)).getRGB(), this.descScroll, height(70.0F), "BurbankBigCondensed-Bold", false);
      if (this.descScroll >= size)
        this.next.field_146124_l = false; 
    } 
  }
  
  public void func_146274_d() {
    float s = -Math.signum(Mouse.getEventDWheel());
    this.scroll = (int)(this.scroll + 5.0F * s);
    if (this.scroll < 0)
      this.scroll = 0; 
    if (height(38.98F) + height(12.95F) * ((Spells.values()).length - 1) - this.scroll + height(11.4F) < height(76.0F))
      this.scroll = height(38.98F) + height(12.95F) * ((Spells.values()).length - 1) + height(11.4F) - height(76.0F); 
    drawButtons();
    super.func_146274_d();
  }
  
  public void func_146281_b() {
    ClientProxy.showHotbar = true;
    if (this.mustResize && this.guiScale != -1)
      this.field_146297_k.field_71474_y.field_74335_Z = this.guiScale; 
    super.func_146281_b();
  }
  
  protected void func_146284_a(GuiButton button) {
    if (button.field_146127_k >= 0 && button.field_146127_k != 99 && button.field_146127_k < 1000) {
      this.currentSpell = Spells.values()[button.field_146127_k];
      if (ClientManager.getSpell(this.currentSpell).isUnlock()) {
        this.currentTiers = ClientManager.getSpell(this.currentSpell).getTier();
      } else {
        this.currentTiers = 1;
      } 
      drawButtons();
    } else if (button.field_146127_k != 99 && button.field_146127_k < 1000) {
      this.currentTiers = Math.abs(button.field_146127_k);
      drawButtons();
    } else if (button.field_146127_k == 99) {
      if (ClientManager.getPoints() >= this.currentSpell.getSpell().getCost() && ClientManager.getLevel() >= this.currentSpell.getSpell().getLevel())
        if (this.lastClick == -1L || System.currentTimeMillis() - this.lastClick > 400L) {
          Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("random.levelup"), 1.0F));
          this.currentTiers = ClientManager.getSpell(this.currentSpell).getTier() + 1;
          PSpellsV2.network.sendToServer((IMessage)new PacketServerUpgradeSpell(this.currentSpell.getSpell().getId()));
          this.lastClick = System.currentTimeMillis();
        } else {
          this.field_146297_k.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("§cVeuillez patienter avant de cliquer."));
        }  
    } else if (button.field_146127_k == 1000) {
      this.descScroll--;
      this.next.field_146124_l = true;
      if (this.descScroll < 0)
        this.descScroll = 0; 
      if (this.descScroll <= 0)
        this.prev.field_146124_l = false; 
    } else if (button.field_146127_k == 1001) {
      int size = GuiSpellUtils.renderSplitTextSizeWithCustomFontWithShadow(this.currentSpell.getSpell().getDescription()[this.currentTiers - 1], width(46.8F), height(62.5F), width(24.0F), (new Color(20, 20, 20, 217)).getRGB(), this.descScroll, height(70.0F), "BurbankBigCondensed-Bold", false);
      if (this.descScroll < size)
        this.descScroll++; 
      this.prev.field_146124_l = true;
    } 
    super.func_146284_a(button);
  }
  
  public boolean func_73868_f() {
    return false;
  }
  
  public void func_73863_a(int mouseX, int mouseY, float ticks) {
    if (this.field_146297_k.field_71474_y.field_74335_Z != 3) {
      this.guiScale = this.field_146297_k.field_71474_y.field_74335_Z;
      this.field_146297_k.field_71474_y.field_74335_Z = 3;
      this.field_146297_k.func_147108_a(new GuiSpells(this.guiScale));
    } 
    GuiUtils.drawImageTransparent(0.0D, 0.0D, new ResourceLocation("palamod", "textures/gui/GuiSpells/background.png"), this.field_146294_l, this.field_146295_m);
    GuiSpellUtils.drawCenteredCustomFont(String.format("%.1f", new Object[] { Double.valueOf(ClientManager.getPoints()) }) + " points", width(24.5F) - 1, 
        height(32.0F) - this.field_146289_q.field_78288_b / 2, new Color(98, 65, 50), true, "BurbankBigCondensed-Bold");
    this.field_146297_k.func_110434_K()
      .func_110577_a(new ResourceLocation("palamod", "textures/gui/GuiSpells/loading.png"));
    Gui.func_146110_a(width(28.96F), height(30.3F), 0.0F, 0.0F, 
        (int)(width(47.0F) * ClientManager.getLevel() / 80.0F), height(3.89F), width(47.0F), 
        height(3.89F));
    if (mouseX > width(28.96F) && mouseY > height(30.3F) && mouseX < width(76.62F) && mouseY < 
      height(34.19F))
      GuiSpellUtils.renderTooltip(new String[] { "Niveau " + ClientManager.getLevel() + "/80", "Le niveau des sorts est", "basé sur le niveaux des métiers" }, mouseX, mouseY); 
    GuiSpellUtils.drawCenteredCustomFont("Niveau " + ClientManager.getLevel(), width(53.0F) - 1, 
        height(32.0F) - this.field_146289_q.field_78288_b / 2 + 1 + 1, new Color(50, 50, 50, 50), true, "BurbankBigCondensed-Bold");
    GuiSpellUtils.drawCenteredCustomFont("Niveau " + ClientManager.getLevel(), width(53.0F), 
        height(32.0F) - this.field_146289_q.field_78288_b / 2 + 1, Color.WHITE, true, "BurbankBigCondensed-Bold");
    GuiUtils.drawImageTransparent(width(41.7F), (
        height(38.0F) + height(34.81F) * this.scroll / (height(38.98F) + 
        height(12.95F) * ((Spells.values()).length - 1) + height(11.4F) - height(76.0F))), new ResourceLocation("palamod", "textures/gui/scroll.png"), 
        width(2.1F), height(5.0F));
    if (this.currentSpell != null) {
      int size = GuiSpellUtils.renderSplitTextSizeWithCustomFontWithShadow(this.currentSpell
          .getSpell().getDescription()[this.currentTiers - 1], width(46.8F), height(62.5F), 
          width(24.0F), (new Color(20, 20, 20, 217)).getRGB(), this.descScroll, height(70.0F), "BurbankBigCondensed-Bold", false);
      if (this.descScroll >= size)
        this.next.field_146124_l = false; 
      GuiUtils.drawImageTransparent(width(47.135F), height(38.88F), new ResourceLocation("palamod", "textures/gui/spells/" + this.currentSpell
            
            .getSpell().getName() + ".png"), 
          width(6.56F), height(11.66F));
      GuiSpellUtils.drawStringCustomFont(this.currentSpell.getSpell().getName(), 
          width(47.135F) + width(6.56F) + 5, height(38.88F) + 1, new Color(98, 65, 50), true, "BurbankBigCondensed-Bold");
      GuiSpellUtils.drawStringCustomFont("Niveau " + this.currentSpell.getSpell().getLevel(), 
          width(47.135F) + width(6.56F) + 5, height(38.88F) + 1 + this.field_146289_q.field_78288_b, new Color(127, 85, 67), true, "BurbankBigCondensed-Bold");
      if (ClientManager.getSpell(this.currentSpell).isUnlock()) {
        GuiSpellUtils.drawCircleImage(width(52.0F), height(46.61F) + 2, 14, (new Color(50, 50, 50, 150)).getRGB());
        GuiSpellUtils.drawCenteredCustomFont("" + ClientManager.getSpell(this.currentSpell).getTier(), width(53.0F) + 1, height(47.5F) + 2, Color.WHITE, false, "BurbankBigCondensed-Bold");
      } else {
        GuiUtils.drawImageTransparent((width(53.0F) - 5), (height(47.5F) - 2), new ResourceLocation("palamod", "textures/gui/lock.png"), 10.0D, 10.0D);
      } 
      GuiSpellUtils.renderSplitTextWithCustomFontWithShadow(this.currentSpell
          .getSpell().getDescription()[this.currentTiers - 1], width(46.8F), height(62.5F), 
          width(24.0F), (new Color(20, 20, 20, 217)).getRGB(), this.descScroll, height(70.0F), "BurbankBigCondensed-Bold", false);
    } 
    GL11.glPushMatrix();
    ScaledResolution res = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
    int scale = res.func_78325_e();
    GL11.glEnable(3089);
    GL11.glScissor(0, height(23.0F) * scale, this.field_146294_l * scale, height(39.0F) * scale);
    super.func_73863_a(mouseX, mouseY, ticks);
    GL11.glDisable(3089);
    GL11.glPopMatrix();
  }
  
  protected void func_146273_a(int mouseX, int mouseY, int mouseButton, long time) {
    if (this.isScrolling) {
      float p = (mouseY - height(38.0F)) / height(34.81F);
      float maxscroll = (height(38.98F) + height(12.95F) * ((Spells.values()).length - 1) + height(11.4F) - height(76.0F));
      float s = maxscroll * p;
      if (s > maxscroll)
        s = maxscroll; 
      if (s < 0.0F)
        s = 0.0F; 
      this.scroll = (int)s;
    } 
    super.func_146273_a(mouseX, mouseY, mouseButton, time);
  }
  
  protected void func_73864_a(int mouseX, int mouseY, int mouseButton) {
    if (mouseX > width(41.7F) && mouseX < width(41.7F) + width(2.1F) && mouseY > height(38.0F) && mouseY < 
      height(38.0F) + height(34.81F) + height(5.0F)) {
      Minecraft.func_71410_x().func_147118_V().func_147682_a(
          (ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
      this.isScrolling = true;
    } 
    super.func_73864_a(mouseX, mouseY, mouseButton);
  }
  
  protected void func_146286_b(int mouseX, int mouseY, int button) {
    if (this.isScrolling)
      this.isScrolling = false; 
    super.func_146286_b(mouseX, mouseY, button);
  }
  
  private int width(float value) {
    return (int)(this.field_146294_l / 100.0F * value);
  }
  
  private int height(float value) {
    return (int)(this.field_146295_m / 100.0F * value);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\gui\GuiSpells.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */