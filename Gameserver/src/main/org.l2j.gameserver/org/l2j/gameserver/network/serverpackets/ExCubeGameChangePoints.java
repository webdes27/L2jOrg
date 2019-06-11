package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.network.L2GameClient;
import org.l2j.gameserver.network.OutgoingPackets;

import java.nio.ByteBuffer;

/**
 * @author mrTJO
 */
public class ExCubeGameChangePoints extends IClientOutgoingPacket {
    int _timeLeft;
    int _bluePoints;
    int _redPoints;

    /**
     * Change Client Point Counter
     *
     * @param timeLeft   Time Left before Minigame's End
     * @param bluePoints Current Blue Team Points
     * @param redPoints  Current Red Team Points
     */
    public ExCubeGameChangePoints(int timeLeft, int bluePoints, int redPoints) {
        _timeLeft = timeLeft;
        _bluePoints = bluePoints;
        _redPoints = redPoints;
    }

    @Override
    public void writeImpl(L2GameClient client) {
        writeId(OutgoingPackets.EX_BLOCK_UP_SET_STATE);

        writeInt(0x02);

        writeInt(_timeLeft);
        writeInt(_bluePoints);
        writeInt(_redPoints);
    }

}
