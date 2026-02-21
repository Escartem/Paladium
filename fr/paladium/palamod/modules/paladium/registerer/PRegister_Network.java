package fr.paladium.palamod.modules.paladium.registerer;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.paladium.cheat.network.PacketNoClip;
import fr.paladium.palamod.modules.paladium.dummy.network.DamageMessage;
import fr.paladium.palamod.modules.paladium.dummy.network.SyncEquipmentMessage;
import fr.paladium.palamod.modules.paladium.network.CSPacketDisconnect;
import fr.paladium.palamod.modules.paladium.network.CSPacketInteraction;
import fr.paladium.palamod.modules.paladium.network.CSPacketRequestScanStickPlayers;
import fr.paladium.palamod.modules.paladium.network.OpenGuiPacket;
import fr.paladium.palamod.modules.paladium.network.PacketAskAction;
import fr.paladium.palamod.modules.paladium.network.PacketCloseAskGUI;
import fr.paladium.palamod.modules.paladium.network.PacketCrusherButton;
import fr.paladium.palamod.modules.paladium.network.PacketLegendaryEffect;
import fr.paladium.palamod.modules.paladium.network.PacketListPlayer;
import fr.paladium.palamod.modules.paladium.network.PacketOnlineDetector;
import fr.paladium.palamod.modules.paladium.network.PacketOpenAskGUI;
import fr.paladium.palamod.modules.paladium.network.PacketOpenGui;
import fr.paladium.palamod.modules.paladium.network.PacketPaladiumArmor;
import fr.paladium.palamod.modules.paladium.network.PacketSetStickModeration;
import fr.paladium.palamod.modules.paladium.network.PacketStickBaseCooldown;
import fr.paladium.palamod.modules.paladium.network.SCOpenQuestGui;
import fr.paladium.palamod.modules.paladium.network.SCPacketInteraction;
import fr.paladium.palamod.modules.paladium.utils.PacketAnnotationProcessor;

public class PRegister_Network {
  public static void init() {
    PalaMod.getNetwork().registerMessage(PacketCrusherButton.Handler.class, PacketCrusherButton.class, 100, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketOnlineDetector.Handler.class, PacketOnlineDetector.class, 101, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketOpenGui.Handler.class, PacketOpenGui.class, 102, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketPaladiumArmor.Handler.class, PacketPaladiumArmor.class, 103, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketLegendaryEffect.Handler.class, PacketLegendaryEffect.class, 104, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(DamageMessage.MessageHandlerClient.class, DamageMessage.class, 105, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(SyncEquipmentMessage.MessageHandlerClient.class, SyncEquipmentMessage.class, 106, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(OpenGuiPacket.Handler.class, OpenGuiPacket.class, 107, Side.SERVER);
    PalaMod.getNetwork().registerMessage(OpenGuiPacket.Handler.class, OpenGuiPacket.class, 108, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketSetStickModeration.Handler.class, PacketSetStickModeration.class, 109, Side.SERVER);
    PalaMod.getNetwork().registerMessage(CSPacketRequestScanStickPlayers.Handler.class, CSPacketRequestScanStickPlayers.class, 110, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketListPlayer.Handler.class, PacketListPlayer.class, 99, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketStickBaseCooldown.Handler.class, PacketStickBaseCooldown.class, 98, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketNoClip.Handler.class, PacketNoClip.class, 97, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketAskAction.Handler.class, PacketAskAction.class, 94, Side.SERVER);
    PalaMod.getNetwork().registerMessage(CSPacketDisconnect.Handler.class, CSPacketDisconnect.class, 93, Side.SERVER);
    PalaMod.getNetwork().registerMessage(SCOpenQuestGui.Handler.class, SCOpenQuestGui.class, 92, Side.CLIENT);
    PacketAnnotationProcessor.registerPacket(PacketOpenAskGUI.class);
    PacketAnnotationProcessor.registerPacket(PacketCloseAskGUI.class);
    PacketAnnotationProcessor.registerPacket(CSPacketInteraction.class);
    PacketAnnotationProcessor.registerPacket(SCPacketInteraction.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\registerer\PRegister_Network.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */