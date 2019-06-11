package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.model.clan.entry.PledgeWaitingInfo;
import org.l2j.gameserver.network.L2GameClient;
import org.l2j.gameserver.network.OutgoingPackets;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * @author Sdw
 */
public class ExPledgeDraftListSearch extends IClientOutgoingPacket {
    final List<PledgeWaitingInfo> _pledgeRecruitList;

    public ExPledgeDraftListSearch(List<PledgeWaitingInfo> pledgeRecruitList) {
        _pledgeRecruitList = pledgeRecruitList;
    }

    @Override
    public void writeImpl(L2GameClient client) {
        writeId(OutgoingPackets.EX_PLEDGE_DRAFT_LIST_SEARCH);

        writeInt(_pledgeRecruitList.size());
        for (PledgeWaitingInfo prl : _pledgeRecruitList) {
            writeInt(prl.getPlayerId());
            writeString(prl.getPlayerName());
            writeInt(prl.getKarma());
            writeInt(prl.getPlayerClassId());
            writeInt(prl.getPlayerLvl());
        }
    }

}
