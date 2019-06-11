package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.model.actor.L2Character;
import org.l2j.gameserver.network.L2GameClient;
import org.l2j.gameserver.network.OutgoingPackets;

import java.nio.ByteBuffer;

public class ChangeMoveType extends IClientOutgoingPacket {
    public static final int WALK = 0;
    public static final int RUN = 1;

    private final int _charObjId;
    private final boolean _running;

    public ChangeMoveType(L2Character character) {
        _charObjId = character.getObjectId();
        _running = character.isRunning();
    }

    @Override
    public void writeImpl(L2GameClient client) {
        writeId(OutgoingPackets.CHANGE_MOVE_TYPE);

        writeInt(_charObjId);
        writeInt(_running ? RUN : WALK);
        writeInt(0); // c2
    }

}
