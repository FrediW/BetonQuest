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
package pl.betoncraft.betonquest.compatibility.skillapi;

import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.player.PlayerData;

import pl.betoncraft.betonquest.InstructionParseException;
import pl.betoncraft.betonquest.api.Condition;
import pl.betoncraft.betonquest.utils.PlayerConverter;

/**
 * Checks if the player has specific class
 * 
 * @author Jakub Sapalski
 */
public class SkillAPIClassCondition extends Condition {

	private String className;
	private boolean exact = false;

	public SkillAPIClassCondition(String packName, String instructions) throws InstructionParseException {
		super(packName, instructions);
		String[] parts = instructions.split(" ");
		if (parts.length < 2) {
			throw new InstructionParseException("Not enough arguments");
		}
		className = parts[1];
		if (!SkillAPI.isClassRegistered(className)) {
			throw new InstructionParseException("Class '" + className + "' is not registered");
		}
		for (String part : parts) {
			if (part.equalsIgnoreCase("exact")) {
				exact = true;
			}
		}
	}

	@Override
	public boolean check(String playerID) {
		PlayerData data = SkillAPI.getPlayerData(PlayerConverter.getPlayer(playerID));
		if (exact) {
			if (data.isExactClass(SkillAPI.getClass(className)))
				return true;
		} else {
			if (data.isClass(SkillAPI.getClass(className)))
				return true;
		}
		return false;
	}

}
