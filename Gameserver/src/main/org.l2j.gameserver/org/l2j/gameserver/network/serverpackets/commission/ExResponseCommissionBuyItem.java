package org.l2j.gameserver.network.serverpackets.commission;

import org.l2j.gameserver.model.ItemInfo;
import org.l2j.gameserver.model.commission.CommissionItem;
import org.l2j.gameserver.network.L2GameClient;
import org.l2j.gameserver.network.OutgoingPackets;
import org.l2j.gameserver.network.serverpackets.IClientOutgoingPacket;

import java.nio.ByteBuffer;

/**
 * @author NosBit
 */
public class ExResponseCommissionBuyItem extends IClientOutgoingPacket {
    public static final ExResponseCommissionBuyItem FAILED = new ExResponseCommissionBuyItem(null);

    private final CommissionItem _commissionItem;

    public ExResponseCommissionBuyItem(CommissionItem commissionItem) {
        _commissionItem = commissionItem;
    }

    @Override
    public void writeImpl(L2GameClient client) {
        writeId(OutgoingPackets.EX_RESPONSE_COMMISSION_BUY_ITEM);

        writeInt(_commissionItem != null ? 1 : 0);
        if (_commissionItem != null) {
            final ItemInfo itemInfo = _commissionItem.getItemInfo();
            writeInt(itemInfo.getEnchantLevel());
            writeInt(itemInfo.getItem().getId());
            writeLong(itemInfo.getCount());
        }
    }

}
