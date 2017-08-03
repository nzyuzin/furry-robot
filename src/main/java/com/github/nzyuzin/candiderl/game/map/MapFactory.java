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

package com.github.nzyuzin.candiderl.game.map;

import com.github.nzyuzin.candiderl.game.GameConfig;
import com.github.nzyuzin.candiderl.game.GameConstants;
import com.github.nzyuzin.candiderl.game.map.cells.Door;
import com.github.nzyuzin.candiderl.game.map.cells.Floor;
import com.github.nzyuzin.candiderl.game.map.cells.MapCell;
import com.github.nzyuzin.candiderl.game.map.cells.Wall;
import com.github.nzyuzin.candiderl.game.map.generator.DungeonGenerator;
import com.github.nzyuzin.candiderl.game.map.generator.EmptyMapGenerator;
import com.github.nzyuzin.candiderl.game.map.generator.MapGenerator;
import com.google.common.base.Preconditions;
import com.google.common.io.LineReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapFactory {

    private final int width;
    private final int height;
    private final MapGenerator mapGenerator;

    public MapFactory(int width, int height, MapGenerator mapGenerator) {
        this.width = width;
        this.height = height;
        this.mapGenerator = mapGenerator;
    }

    public MapFactory(MapGenerator mapGenerator) {
        this(GameConfig.MAP_WIDTH, GameConfig.MAP_HEIGHT, mapGenerator);
    }

    public MapFactory() {
        this(GameConfig.MAP_WIDTH, GameConfig.MAP_HEIGHT, new DungeonGenerator(3, 3));
    }

    public Map build() {
        if (GameConfig.BUILD_MAP_FROM_FILE) {
            try {
                return build(readMapFile());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read map file", e);
            }
        } else {
            return mapGenerator.generate(width, height);
        }
    }

    public static Map build(char[][] array) {
        MapFactory mapFactory = new MapFactory(array[0].length, array.length, new EmptyMapGenerator());
        Map map = mapFactory.build();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                char c = array[i][j];
                Preconditions.checkArgument(c == '#' || c == ' ' || c == '.' || c == '+',
                        "Map char array can only contain '#', '.', '+', and ' ' :: given \'" + c + "\'");
                MapCell cell = c == '#' ? Wall.getWall() : (c == '+' ? Door.getDoor() : Floor.getFloor());
                map.setCell(j, array.length - 1 - i, cell);
            }
        }
        return map;
    }

    private char[][] readMapFile() throws IOException {
        File mapFile = new File(GameConstants.MAP_FILENAME);
        LineReader lineReader = new LineReader(new FileReader(mapFile));
        ArrayList<char[]> result = new ArrayList<>();
        String line;
        while ((line = lineReader.readLine()) != null) {
            if (line.length() == 0) {
                break;
            }
            result.add(line.toCharArray());
        }
        return result.toArray(new char[result.size()][]);
    }

}
