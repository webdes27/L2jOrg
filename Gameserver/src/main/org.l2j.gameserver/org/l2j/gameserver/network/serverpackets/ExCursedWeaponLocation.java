package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.model.Location;
import org.l2j.gameserver.network.L2GameClient;
import org.l2j.gameserver.network.OutgoingPackets;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * Format: (ch) d[ddddd]
 *
 * @author -Wooden-
 */
public class ExCursedWeaponLocation extends IClientOutgoingPacket {
    private final List<CursedWeaponInfo> _cursedWeaponInfo;

    public ExCursedWeaponLocation(List<CursedWeaponInfo> cursedWeaponInfo) {
        _cursedWeaponInfo = cursedWeaponInfo;
    }

    @Override
    public void writeImpl(L2GameClient client) {
        writeId(OutgoingPackets.EX_CURSED_WEAPON_LOCATION);

        if (!_cursedWeaponInfo.isEmpty()) {
            writeInt(_cursedWeaponInfo.size());
            for (CursedWeaponInfo w : _cursedWeaponInfo) {
                writeInt(w.id);
                writeInt(w.activated);

                writeInt(w.pos.getX());
                writeInt(w.pos.getY());
                writeInt(w.pos.getZ());
            }
        } else {
            writeInt(0);
        }
    }


    public static class CursedWeaponInfo {
        public Location pos;
        public int id;
        public int activated; // 0 - not activated ? 1 - activated

        public CursedWeaponInfo(Location p, int ID, int status) {
            pos = p;
            id = ID;
            activated = status;
        }

    }
}
