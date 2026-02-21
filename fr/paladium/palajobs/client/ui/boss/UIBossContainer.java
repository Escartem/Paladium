package fr.paladium.palajobs.client.ui.boss;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.label.MinecraftSubTitleNodeLabel;
import fr.paladium.lib.apollon.nodes.label.MinecraftTitleNodeLabel;
import fr.paladium.lib.apollon.ui.UIContainer;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.core.container.BossInventory;
import fr.paladium.palajobs.core.container.ContainerBoss;
import fr.paladium.palajobs.core.packets.client.CSPacketOpenBossGui;
import fr.paladium.translate.common.texttotranslate.TTT;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class UIBossContainer extends UIContainer {
  public UIBossContainer() {
    super(null, "palajobs:boss/container", (Container)new ContainerBoss((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g, (PalaJobs.getClient()).bossItem));
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    addNode((ANode)new MinecraftTitleNodeLabel(width(31.4F), height(14.07F), TTT.format("gui.jobs.title", new Object[0])));
    addNode((ANode)new MinecraftSubTitleNodeLabel(width(31.4F), height(24.0F), TTT.format("gui.jobs.boss.subtitle", new Object[0])));
    addNode((ANode)(new UIMinecraftSlot((IInventory)new BossInventory(), 0, width(47.0F), height(30.77F), width(6.0D))).setPlaceholder((PalaJobs.getClient()).bossItem));
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        addNode((ANode)new UIMinecraftSlot((IInventory)this.field_146297_k.field_71439_g.field_71071_by, j + i * 9 + 9, (width(29.167F) + width(4.62F) * j), (height(50.61F) + height(8.42F) * i), width(3.96D))); 
    } 
    for (i = 0; i < 9; i++)
      addNode((ANode)new UIMinecraftSlot((IInventory)this.field_146297_k.field_71439_g.field_71071_by, i, (width(29.167F) + width(4.62F) * i), height(78.5F), width(3.96D))); 
    addNode((new MinecraftCloseNode(width(68.1F), height(13.0F))).setCallback(n -> PalaJobs.proxy.getNetwork().sendToServer((IMessage)new CSPacketOpenBossGui())));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    func_146276_q_();
    BackgroundHelper.createMinecraft(width(43.02F), height(76.481F));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\boss\UIBossContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */