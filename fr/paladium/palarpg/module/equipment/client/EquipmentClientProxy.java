package fr.paladium.palarpg.module.equipment.client;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.tooltip.CustomTooltip;
import fr.paladium.palarpg.module.equipment.client.listener.RPGArmorRenderListener;
import fr.paladium.palarpg.module.equipment.client.listener.RPGInventorySlotListener;
import fr.paladium.palarpg.module.equipment.client.listener.RPGItemTextureListener;
import fr.paladium.palarpg.module.equipment.common.EquipmentCommonProxy;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import java.util.function.Supplier;

public class EquipmentClientProxy extends EquipmentCommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { RPGItemTextureListener.class, RPGArmorRenderListener.class, RPGInventorySlotListener.class });
    CustomTooltip.register(IRPGItem::is, fr.paladium.palarpg.module.equipment.client.renderer.RPGTooltipNode::new);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\client\EquipmentClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */