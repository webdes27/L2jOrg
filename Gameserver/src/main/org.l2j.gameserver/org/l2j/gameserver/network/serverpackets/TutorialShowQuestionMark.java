package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.network.L2GameClient;
import org.l2j.gameserver.network.OutgoingPackets;

import java.nio.ByteBuffer;

/**
 * @author Mobius
 */
public final class TutorialShowQuestionMark extends IClientOutgoingPacket {
    private final int _markId;
    private final int _markType;

    public TutorialShowQuestionMark(int markId, int markType) {
        _markId = markId;
        _markType = markType;
    }

    @Override
    public void writeImpl(L2GameClient client) {
        writeId(OutgoingPackets.TUTORIAL_SHOW_QUESTION_MARK);

        writeByte((byte) _markType);
        writeInt(_markId);
    }

}