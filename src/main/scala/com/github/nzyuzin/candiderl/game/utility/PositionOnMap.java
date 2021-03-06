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

import com.github.nzyuzin.candiderl.game.characters.GameCharacter;
import com.github.nzyuzin.candiderl.game.map.Map;
import com.github.nzyuzin.candiderl.game.map.cells.MapCell;
import scala.Option;

public class PositionOnMap {
    private Position position;
    private Map map;

    public PositionOnMap(Position position, Map map) {
        this.position = position;
        this.map = map;
    }

    public Position getPosition() {
        return position;
    }

    public Map getMap() {
        return map;
    }

    public MapCell getMapCell() {
        return getMap().getCell(position);
    }

    public Option<GameCharacter> getGameCharacter() {
        return getMapCell().getGameCharacter();
    }

    public PositionOnMap apply(final Direction direction) {
        return new PositionOnMap(this.position.apply(direction), this.map);
    }

    public double distanceTo(PositionOnMap other) {
        if (!this.getMap().equals(other.getMap())) {
            return Double.MAX_VALUE;
        } else {
            return this.getPosition().distanceTo(other.getPosition());
        }
    }

    public PositionOnMap newPosition(final Position position) {
        return new PositionOnMap(position, this.map);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionOnMap that = (PositionOnMap) o;

        return map.equals(that.map) && position.equals(that.position);

    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (map != null ? map.hashCode() : 0);
        return result;
    }
}
