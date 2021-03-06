/**
 * BetonQuest - advanced quests for Bukkit
 * Copyright (C) 2016  Jakub "Co0sh" Sapalski
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pl.betoncraft.betonquest.compatibility.quests;

import java.util.Map;

import org.bukkit.entity.Player;

import me.blackvein.quests.CustomReward;
import pl.betoncraft.betonquest.BetonQuest;
import pl.betoncraft.betonquest.utils.PlayerConverter;

/**
 * Fires a BetonQuest event as a quest reward.
 * 
 * @author Jakub Sapalski
 */
public class EventReward extends CustomReward {

	public EventReward() {
		setName("BetonQuest event");
		setAuthor("Co0sh");
		setRewardName("Event");
		addData("Event");
		addDescription("Event", "Specify BetonQuest event name (with the package, like: package.event)");
	}

	@Override
	public void giveReward(Player player, Map<String, Object> dataMap) {
		String playerID = PlayerConverter.getID(player);
		String event = dataMap.get("Event").toString();
		BetonQuest.event(playerID, event);
	}

}
