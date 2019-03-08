/*
 * This file is part of the L2J Mobius project.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2j.gameserver.mobius.gameserver.network.clientpackets;

import org.l2j.commons.network.PacketReader;
import org.l2j.gameserver.mobius.gameserver.data.xml.impl.EnchantItemData;
import org.l2j.gameserver.mobius.gameserver.model.actor.instance.L2PcInstance;
import org.l2j.gameserver.mobius.gameserver.model.actor.request.EnchantItemRequest;
import org.l2j.gameserver.mobius.gameserver.model.items.enchant.EnchantScroll;
import org.l2j.gameserver.mobius.gameserver.model.items.enchant.EnchantSupportItem;
import org.l2j.gameserver.mobius.gameserver.model.items.instance.L2ItemInstance;
import org.l2j.gameserver.mobius.gameserver.network.L2GameClient;
import org.l2j.gameserver.mobius.gameserver.network.SystemMessageId;
import org.l2j.gameserver.mobius.gameserver.network.serverpackets.ExPutEnchantSupportItemResult;

/**
 * @author KenM
 */
public class RequestExTryToPutEnchantSupportItem extends IClientIncomingPacket
{
	private int _supportObjectId;
	private int _enchantObjectId;
	
	@Override
	public void readImpl(ByteBuffer packet)
	{
		_supportObjectId = packet.getInt();
		_enchantObjectId = packet.getInt();
		return true;
	}
	
	@Override
	public void runImpl()
	{
		final L2PcInstance activeChar = client.getActiveChar();
		if (activeChar == null)
		{
			return;
		}
		
		final EnchantItemRequest request = activeChar.getRequest(EnchantItemRequest.class);
		if ((request == null) || request.isProcessing())
		{
			return;
		}
		
		request.setEnchantingItem(_enchantObjectId);
		request.setSupportItem(_supportObjectId);
		
		final L2ItemInstance item = request.getEnchantingItem();
		final L2ItemInstance scroll = request.getEnchantingScroll();
		final L2ItemInstance support = request.getSupportItem();
		if ((item == null) || (scroll == null) || (support == null))
		{
			// message may be custom
			activeChar.sendPacket(SystemMessageId.INAPPROPRIATE_ENCHANT_CONDITIONS);
			request.setEnchantingItem(L2PcInstance.ID_NONE);
			request.setSupportItem(L2PcInstance.ID_NONE);
			return;
		}
		
		final EnchantScroll scrollTemplate = EnchantItemData.getInstance().getEnchantScroll(scroll);
		final EnchantSupportItem supportTemplate = EnchantItemData.getInstance().getSupportItem(support);
		if ((scrollTemplate == null) || (supportTemplate == null) || !scrollTemplate.isValid(item, supportTemplate))
		{
			// message may be custom
			activeChar.sendPacket(SystemMessageId.INAPPROPRIATE_ENCHANT_CONDITIONS);
			request.setSupportItem(L2PcInstance.ID_NONE);
			activeChar.sendPacket(new ExPutEnchantSupportItemResult(0));
			return;
		}
		
		request.setSupportItem(support.getObjectId());
		request.setTimestamp(System.currentTimeMillis());
		activeChar.sendPacket(new ExPutEnchantSupportItemResult(_supportObjectId));
	}
}