package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.model.VehiclePathPoint;
import org.l2j.gameserver.network.L2GameClient;
import org.l2j.gameserver.network.OutgoingPackets;

import java.nio.ByteBuffer;

import static java.util.Objects.nonNull;

public class ExAirShipTeleportList extends IClientOutgoingPacket {
    private final int _dockId;
    private final VehiclePathPoint[][] _teleports;
    private final int[] _fuelConsumption;

    public ExAirShipTeleportList(int dockId, VehiclePathPoint[][] teleports, int[] fuelConsumption) {
        _dockId = dockId;
        _teleports = teleports;
        _fuelConsumption = fuelConsumption;
    }

    @Override
    public void writeImpl(L2GameClient client) {
        writeId(OutgoingPackets.EX_AIR_SHIP_TELEPORT_LIST);

        writeInt(_dockId);
        if (_teleports != null) {
            writeInt(_teleports.length);

            for (int i = 0; i < _teleports.length; i++) {
                writeInt(i - 1);
                writeInt(_fuelConsumption[i]);
                final VehiclePathPoint[] path = _teleports[i];
                final VehiclePathPoint dst = path[path.length - 1];
                writeInt(dst.getX());
                writeInt(dst.getY());
                writeInt(dst.getZ());
            }
        } else {
            writeInt(0);
        }
    }

}
