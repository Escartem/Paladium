package fr.paladium.palaschematicmod.client.ui;

import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.textfield.IntegerFieldNode;
import fr.paladium.paladiumui.kit.textfield.TextFieldNode;
import fr.paladium.palaschematicmod.common.network.CSPacketExportBlockPalaSchematic;
import fr.paladium.palaschematicmod.common.network.CSPacketUpdateBlockPalaSchematic;
import fr.paladium.palaschematicmod.common.tileentity.TileEntityBlockPalaSchematic;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.textfield.TextFieldNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

@UIDataGuiscale(active = true)
public class UIBlockPalaSchematic extends UI {
  private final Integer x;
  
  private final Integer y;
  
  private final Integer z;
  
  private TileEntityBlockPalaSchematic tileEntity;
  
  private StringSignal schematicNameSignal;
  
  private IntegerSignal relativeXSignal;
  
  private IntegerSignal relativeYSignal;
  
  private IntegerSignal relativeZSignal;
  
  private IntegerSignal sizeXSignal;
  
  private IntegerSignal sizeYSignal;
  
  private IntegerSignal sizeZSignal;
  
  public UIBlockPalaSchematic(Integer x, Integer y, Integer z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void init() {
    if ((Minecraft.func_71410_x()).field_71441_e == null) {
      ZUI.close(this);
      return;
    } 
    TileEntity tileEntityAt = (Minecraft.func_71410_x()).field_71441_e.func_147438_o(this.x.intValue(), this.y.intValue(), this.z.intValue());
    if (!(tileEntityAt instanceof TileEntityBlockPalaSchematic)) {
      ZUI.close(this);
      return;
    } 
    this.tileEntity = (TileEntityBlockPalaSchematic)tileEntityAt;
    this.schematicNameSignal = new StringSignal((this.tileEntity.getSchematicName() == null) ? "" : this.tileEntity.getSchematicName());
    this.relativeXSignal = new IntegerSignal(this.tileEntity.getRelativeX());
    this.relativeYSignal = new IntegerSignal(this.tileEntity.getRelativeY());
    this.relativeZSignal = new IntegerSignal(this.tileEntity.getRelativeZ());
    this.sizeXSignal = new IntegerSignal(this.tileEntity.getSizeX());
    this.sizeYSignal = new IntegerSignal(this.tileEntity.getSizeY());
    this.sizeZSignal = new IntegerSignal(this.tileEntity.getSizeZ());
    TextNode.create(960.0D, 250.0D)
      .text(Text.create("PalaSchematic Block", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 25.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach(this);
    TextNode.create(660.0D, 370.0D)
      .text(Text.create("Schematic Name", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 18.0F, Color.WHITE.darker(0.3F))))
      .attach(this);
    TextFieldNode.create(660.0D, 400.0D, 600.0D, 30.0D)
      .text((String)this.schematicNameSignal.getOrDefault())
      .onChange((node, oldText, newText) -> this.schematicNameSignal.set(newText))
      .attach(this);
    TextNode.create(660.0D, 470.0D)
      .text(Text.create("Relative Position", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 18.0F, Color.WHITE.darker(0.3F))))
      .attach(this);
    IntegerFieldNode.create(660.0D, 500.0D, 190.0D, 30.0D)
      .text(String.valueOf(this.relativeXSignal.getOrDefault()))
      .onChange((node, oldText, newText) -> this.relativeXSignal.set(Integer.valueOf(Integer.parseInt(newText))))
      .attach(this);
    IntegerFieldNode.create(865.0D, 500.0D, 190.0D, 30.0D)
      .text(String.valueOf(this.relativeYSignal.getOrDefault()))
      .onChange((node, oldText, newText) -> this.relativeYSignal.set(Integer.valueOf(Integer.parseInt(newText))))
      .attach(this);
    IntegerFieldNode.create(1070.0D, 500.0D, 190.0D, 30.0D)
      .text(String.valueOf(this.relativeZSignal.getOrDefault()))
      .onChange((node, oldText, newText) -> this.relativeZSignal.set(Integer.valueOf(Integer.parseInt(newText))))
      .attach(this);
    TextNode.create(660.0D, 570.0D)
      .text(Text.create("Schematic Size", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 18.0F, Color.WHITE.darker(0.3F))))
      .attach(this);
    IntegerFieldNode.create(660.0D, 600.0D, 190.0D, 30.0D)
      .text(String.valueOf(this.sizeXSignal.getOrDefault()))
      .onChange((node, oldText, newText) -> this.sizeXSignal.set(Integer.valueOf(Integer.parseInt(newText))))
      .attach(this);
    IntegerFieldNode.create(865.0D, 600.0D, 190.0D, 30.0D)
      .text(String.valueOf(this.sizeYSignal.getOrDefault()))
      .onChange((node, oldText, newText) -> this.sizeYSignal.set(Integer.valueOf(Integer.parseInt(newText))))
      .attach(this);
    IntegerFieldNode.create(1070.0D, 600.0D, 190.0D, 30.0D)
      .text(String.valueOf(this.sizeZSignal.getOrDefault()))
      .onChange((node, oldText, newText) -> this.sizeZSignal.set(Integer.valueOf(Integer.parseInt(newText))))
      .attach(this);
    TextButtonNode.create(660.0D, 700.0D)
      .text("annuler")
      .width(190.0D)
      .onClick((node, mouseX, mouseY, clickType) -> ZUI.close(this))
      .attach(this);
    TextButtonNode.create(865.0D, 700.0D)
      .text("valider")
      .width(190.0D)
      .onClick((node, mouseX, mouseY, clickType) -> {
          (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("§8[§6PalaSchematic§8] §aSchematic mise à jour avec succès."));
          (new CSPacketUpdateBlockPalaSchematic(this.x.intValue(), this.y.intValue(), this.z.intValue(), (String)this.schematicNameSignal.getOrDefault(), ((Integer)this.relativeXSignal.getOrDefault()).intValue(), ((Integer)this.relativeYSignal.getOrDefault()).intValue(), ((Integer)this.relativeZSignal.getOrDefault()).intValue(), ((Integer)this.sizeXSignal.getOrDefault()).intValue(), ((Integer)this.sizeYSignal.getOrDefault()).intValue(), ((Integer)this.sizeZSignal.getOrDefault()).intValue())).send();
          ZUI.close(this);
        }).attach(this);
    TextButtonNode.create(1070.0D, 700.0D)
      .text("exporter")
      .width(190.0D)
      .onClick((node, mouseX, mouseY, clickType) -> {
          (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("§8[§6PalaSchematic§8] §aSchematic exportée avec succès."));
          (new CSPacketUpdateBlockPalaSchematic(this.x.intValue(), this.y.intValue(), this.z.intValue(), (String)this.schematicNameSignal.getOrDefault(), ((Integer)this.relativeXSignal.getOrDefault()).intValue(), ((Integer)this.relativeYSignal.getOrDefault()).intValue(), ((Integer)this.relativeZSignal.getOrDefault()).intValue(), ((Integer)this.sizeXSignal.getOrDefault()).intValue(), ((Integer)this.sizeYSignal.getOrDefault()).intValue(), ((Integer)this.sizeZSignal.getOrDefault()).intValue())).send();
          (new CSPacketExportBlockPalaSchematic(this.x.intValue(), this.y.intValue(), this.z.intValue())).send();
          ZUI.close(this);
        }).enabled(node -> (this.schematicNameSignal.getOrDefault() != null && !((String)this.schematicNameSignal.getOrDefault()).isEmpty() && ((Integer)this.sizeXSignal.getOrDefault()).intValue() > 0 && ((Integer)this.sizeYSignal.getOrDefault()).intValue() > 0 && ((Integer)this.sizeZSignal.getOrDefault()).intValue() > 0))
      .attach(this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\clien\\ui\UIBlockPalaSchematic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */