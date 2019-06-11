package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.network.L2GameClient;
import org.l2j.gameserver.network.OutgoingPackets;

import java.nio.ByteBuffer;

public final class SetSummonRemainTime extends IClientOutgoingPacket {
    private final int _maxTime;
    private final int _remainingTime;

    public SetSummonRemainTime(int maxTime, int remainingTime) {
        _remainingTime = remainingTime;
        _maxTime = maxTime;
    }

    @Override
    public void writeImpl(L2GameClient client) {
        writeId(OutgoingPackets.SET_SUMMON_REMAIN_TIME);

        writeInt(_maxTime);
        writeInt(_remainingTime);
    }

}
