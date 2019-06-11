package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.model.actor.instance.L2PcInstance;
import org.l2j.gameserver.network.L2GameClient;
import org.l2j.gameserver.network.OutgoingPackets;

import java.nio.ByteBuffer;

/**
 * @author Sdw
 */
public class ExAdenaInvenCount extends IClientOutgoingPacket {
    private final L2PcInstance _activeChar;

    public ExAdenaInvenCount(L2PcInstance cha) {
        _activeChar = cha;
    }

    @Override
    public void writeImpl(L2GameClient client) {
        writeId(OutgoingPackets.EX_ADENA_INVEN_COUNT);

        writeLong(_activeChar.getAdena());
        writeShort((short) _activeChar.getInventory().getSize());
    }

}