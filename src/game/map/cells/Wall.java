/*
 * This file is part of CandideRL.
 *
 * CandideRL is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * CandideRL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CandideRL.  If not, see <http://www.gnu.org/licenses/>.
 */

package game.map.cells;

import game.utility.ColoredChar;
import game.utility.VisibleCharacters;

public class Wall extends AbstractMapCell {
    private final static Wall instance = new Wall();

    private Wall() {
        super("Wall",
                "A regular rock wall.",
                ColoredChar.getColoredChar(VisibleCharacters.WALL.getVisibleChar(), ColoredChar.YELLOW),
                false,
                false
        );
    }

    public static Wall getWall() {
        return instance;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Wall && super.equals(object);
    }
}