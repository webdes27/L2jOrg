package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.data.sql.impl.ClanTable;
import org.l2j.gameserver.model.ClanInfo;
import org.l2j.gameserver.model.L2Clan;
import org.l2j.gameserver.network.L2GameClient;
import org.l2j.gameserver.network.OutgoingPackets;
import org.l2j.gameserver.network.clientpackets.RequestAllyInfo;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;

/**
 * Sent in response to {@link RequestAllyInfo}, if applicable.<BR>
 *
 * @author afk5min
 */
public class AllianceInfo extends IClientOutgoingPacket {
    private final String _name;
    private final int _total;
    private final int _online;
    private final String _leaderC;
    private final String _leaderP;
    private final ClanInfo[] _allies;

    public AllianceInfo(int allianceId) {
        final L2Clan leader = ClanTable.getInstance().getClan(allianceId);
        _name = leader.getAllyName();
        _leaderC = leader.getName();
        _leaderP = leader.getLeaderName();

        final Collection<L2Clan> allies = ClanTable.getInstance().getClanAllies(allianceId);
        _allies = new ClanInfo[allies.size()];
        int idx = 0;
        int total = 0;
        int online = 0;
        for (L2Clan clan : allies) {
            final ClanInfo ci = new ClanInfo(clan);
            _allies[idx++] = ci;
            total += ci.getTotal();
            online += ci.getOnline();
        }

        _total = total;
        _online = online;
    }

    @Override
    public void writeImpl(L2GameClient client) {
        writeId(OutgoingPackets.ALLIANCE_INFO);

        writeString(_name);
        writeInt(_total);
        writeInt(_online);
        writeString(_leaderC);
        writeString(_leaderP);

        writeInt(_allies.length);
        for (ClanInfo aci : _allies) {
            writeString(aci.getClan().getName());
            writeInt(0x00);
            writeInt(aci.getClan().getLevel());
            writeString(aci.getClan().getLeaderName());
            writeInt(aci.getTotal());
            writeInt(aci.getOnline());
        }
    }



    public String getName() {
        return _name;
    }

    public int getTotal() {
        return _total;
    }

    public int getOnline() {
        return _online;
    }

    public String getLeaderC() {
        return _leaderC;
    }

    public String getLeaderP() {
        return _leaderP;
    }

    public ClanInfo[] getAllies() {
        return _allies;
    }
}