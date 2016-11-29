/*
 *  This file is part of CandideRL.
 *
 *  CandideRL is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  CandideRL is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with CandideRL.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.nzyuzin.candiderl.game.characters.actions;

import com.github.nzyuzin.candiderl.game.characters.GameCharacter;
import com.github.nzyuzin.candiderl.game.events.Event;

import java.util.List;

abstract class AbstractGameAction implements GameAction {
    private final GameCharacter performer;
    private boolean executed;

    public AbstractGameAction(GameCharacter subject) {
        this.performer = subject;
    }

    protected GameCharacter getPerformer() {
        return performer;
    }

    public abstract boolean canBeExecuted();

    public final List<Event> execute() {
        if (!executed) {
            try {
                return doExecute();
            } finally {
                executed = true;
            }
        } else {
            throw new ActionAlreadyExecutedException();
        }
    }

    protected abstract List<Event> doExecute();
}
