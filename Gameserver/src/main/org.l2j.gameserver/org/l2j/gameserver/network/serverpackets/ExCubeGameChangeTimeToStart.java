package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.network.L2GameClient;
import org.l2j.gameserver.network.OutgoingPackets;

import java.nio.ByteBuffer;

/**
 * @author mrTJO
 */
public class ExCubeGameChangeTimeToStart extends IClientOutgoingPacket {
    int _seconds;

    /**
     * Update Minigame Waiting List Time to Start
     *
     * @param seconds
     */
    public ExCubeGameChangeTimeToStart(int seconds) {
        _seconds = seconds;
    }

    @Override
    public void writeImpl(L2GameClient client) {
        writeId(OutgoingPackets.EX_BLOCK_UP_SET_LIST);

        writeInt(0x03);

        writeInt(_seconds);
    }

}
