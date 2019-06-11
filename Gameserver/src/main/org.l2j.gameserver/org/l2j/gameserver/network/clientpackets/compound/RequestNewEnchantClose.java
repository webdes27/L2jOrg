package org.l2j.gameserver.network.clientpackets.compound;

import org.l2j.gameserver.model.actor.instance.L2PcInstance;
import org.l2j.gameserver.model.actor.request.CompoundRequest;
import org.l2j.gameserver.network.clientpackets.IClientIncomingPacket;

import java.nio.ByteBuffer;

/**
 * @author UnAfraid
 */
public class RequestNewEnchantClose extends IClientIncomingPacket {
    @Override
    public void readImpl() {
    }

    @Override
    public void runImpl() {
        final L2PcInstance activeChar = client.getActiveChar();
        if (activeChar == null) {
            return;
        }

        activeChar.removeRequest(CompoundRequest.class);
    }
}
