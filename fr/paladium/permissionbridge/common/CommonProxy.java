package fr.paladium.permissionbridge.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.command.annotated.parser.CommandValidatorParser;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palaforgeutils.lib.validator.Validators;
import fr.paladium.permissionbridge.common.packet.SCPacketSyncPermission;
import fr.paladium.permissionbridge.common.validator.PermissionValidator;

public abstract class CommonProxy extends AModProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    initNetwork("permissionbridge");
    PacketUtils.registerPacket(getNetwork(), SCPacketSyncPermission.class);
    Validators.register(PermissionValidator.class);
    CommandValidatorParser.register(PermissionValidator.PermissionValidatorException.class, "le joueur doit avoir la permission \"{permission}\"");
    CommandValidatorParser.register(PermissionValidator.NotPermissionValidatorException.class, "le joueur possède la permission \"{permission}\"");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\permissionbridge\common\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */