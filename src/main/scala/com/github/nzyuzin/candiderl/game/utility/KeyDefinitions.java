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

package com.github.nzyuzin.candiderl.game.utility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class KeyDefinitions {

    private KeyDefinitions() {
    }

    /**
     * Map of keys corresponding to directions
     */
    private static final Map<Character, Direction> DIRECTION_KEYS = new HashMap<>();

    public static final char STATUS_KEY = '@';

    public static final char INVENTORY_KEY = 'i';

    public static final char VIEW_MODE_KEY = 'X';

    public static final char EXAMINE_KEY = 'v';

    public static final char ESCAPE_KEY = (char) 0x1B;
    public static final char LF_KEY = (char) 0x0A;
    public static final char BACKSPACE_KEY = (char) 0x08;

    public static final char DROP_ITEM_KEY = 'd';
    public static final char PICKUP_ITEM_KEY = 'g';

    public static final char WIELD_ITEM_KEY = 'w';

    public static final char OPEN_DOOR_KEY = 'o';
    public static final char CLOSE_DOOR_KEY = 'c';

    static {
        DIRECTION_KEYS.put('j', Direction.SOUTH);
        DIRECTION_KEYS.put('k', Direction.NORTH);
        DIRECTION_KEYS.put('h', Direction.WEST);
        DIRECTION_KEYS.put('l', Direction.EAST);
        DIRECTION_KEYS.put('y', Direction.NORTHWEST);
        DIRECTION_KEYS.put('u', Direction.NORTHEAST);
        DIRECTION_KEYS.put('b', Direction.SOUTHWEST);
        DIRECTION_KEYS.put('n', Direction.SOUTHEAST);

        DIRECTION_KEYS.put('2', Direction.SOUTH);
        DIRECTION_KEYS.put('8', Direction.NORTH);
        DIRECTION_KEYS.put('4', Direction.WEST);
        DIRECTION_KEYS.put('6', Direction.EAST);
        DIRECTION_KEYS.put('7', Direction.NORTHWEST);
        DIRECTION_KEYS.put('9', Direction.NORTHEAST);
        DIRECTION_KEYS.put('1', Direction.SOUTHWEST);
        DIRECTION_KEYS.put('3', Direction.SOUTHEAST);
    }

    public static final char STAIRS_UPWARDS_KEY = '<';
    public static final char STAIRS_DOWNWARDS_KEY = '>';

    /**
     * EXIT_CHARS, EXIT_CODES - sets of char and int codes respectively which are meant to shutdown the game.
     */
    private static final Set<Character> EXIT_CHARS = new HashSet<>();

    static {
        EXIT_CHARS.add('q');
    }

    private static final Set<Character> SKIP_TURN_CHARS = new HashSet<>();

    static {
        SKIP_TURN_CHARS.add('s');
    }

    public static boolean isExitChar(char c) {
        return EXIT_CHARS.contains(c);
    }

    public static boolean isDirectionKey(char key) {
        return DIRECTION_KEYS.containsKey(key);
    }

    public static boolean isSkipTurnChar(char c) {
        return SKIP_TURN_CHARS.contains(c);
    }

    public static Direction getDirectionFromKey(char key) {
        if (!DIRECTION_KEYS.containsKey(key)) {
            throw new IllegalArgumentException(key + " is not a direction key");
        }
        return DIRECTION_KEYS.get(key);
    }

}
