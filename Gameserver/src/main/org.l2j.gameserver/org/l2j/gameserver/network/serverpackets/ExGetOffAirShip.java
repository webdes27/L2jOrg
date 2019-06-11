package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.model.actor.L2Character;
import org.l2j.gameserver.network.L2GameClient;
import org.l2j.gameserver.network.OutgoingPackets;

import java.nio.ByteBuffer;

public class ExGetOffAirShip extends IClientOutgoingPacket {
    private final int _playerId;
    private final int _airShipId;
    private final int _x;
    private final int _y;
    private final int _z;

    public ExGetOffAirShip(L2Character player, L2Character ship, int x, int y, int z) {
        _playerId = player.getObjectId();
        _airShipId = ship.getObjectId();
        _x = x;
        _y = y;
        _z = z;
    }

    @Override
    public void writeImpl(L2GameClient client) {
        writeId(OutgoingPackets.EX_GET_OFF_AIR_SHIP);

        writeInt(_playerId);
        writeInt(_airShipId);
        writeInt(_x);
        writeInt(_y);
        writeInt(_z);
    }

}
