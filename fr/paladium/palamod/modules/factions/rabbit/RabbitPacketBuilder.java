package fr.paladium.palamod.modules.factions.rabbit;

public abstract class RabbitPacketBuilder implements RabbitPacketRoute {
  public void send(RabbitService service) {
    service.sendPacket(getPacketType(), getPacketQueue(), this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\factions\rabbit\RabbitPacketBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */