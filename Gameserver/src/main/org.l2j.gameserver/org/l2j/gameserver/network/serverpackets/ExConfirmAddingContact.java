package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.network.L2GameClient;
import org.l2j.gameserver.network.OutgoingPackets;

import java.nio.ByteBuffer;

/**
 * @author mrTJO & UnAfraid
 */
public class ExConfirmAddingContact extends IClientOutgoingPacket {
    private final String _charName;
    private final boolean _added;

    public ExConfirmAddingContact(String charName, boolean added) {
        _charName = charName;
        _added = added;
    }

    @Override
    public void writeImpl(L2GameClient client) {
        writeId(OutgoingPackets.EX_AGIT_AUCTION_CMD);

        writeString(_charName);
        writeInt(_added ? 0x01 : 0x00);
    }

}
