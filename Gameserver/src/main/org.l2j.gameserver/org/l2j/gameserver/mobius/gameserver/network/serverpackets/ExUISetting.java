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
package org.l2j.gameserver.mobius.gameserver.network.serverpackets;

import org.l2j.commons.network.PacketWriter;
import org.l2j.gameserver.mobius.gameserver.model.actor.instance.L2PcInstance;
import org.l2j.gameserver.mobius.gameserver.network.OutgoingPackets;

/**
 * @author Mobius
 */
public class ExUISetting implements IClientOutgoingPacket
{
	public static final String UI_KEY_MAPPING_VAR = "UI_KEY_MAPPING";
	public static final String SPLIT_VAR = "	";
	private final byte[] _uiKeyMapping;
	
	public ExUISetting(L2PcInstance player)
	{
		if (player.getVariables().hasVariable(UI_KEY_MAPPING_VAR))
		{
			_uiKeyMapping = player.getVariables().getByteArray(UI_KEY_MAPPING_VAR, SPLIT_VAR);
		}
		else
		{
			_uiKeyMapping = null;
		}
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.EX_UI_SETTING.writeId(packet);
		if (_uiKeyMapping != null)
		{
			packet.writeD(_uiKeyMapping.length);
			packet.writeB(_uiKeyMapping);
		}
		else
		{
			packet.writeD(0);
		}
		return true;
	}
}