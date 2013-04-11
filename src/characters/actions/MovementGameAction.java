package characters.actions;

import characters.GameCharacter;
import map.Map;
import utility.*;

public final class MovementGameAction extends AbstractGameAction {
	
	private final Position position;

	public MovementGameAction(GameCharacter subject, Direction there) {
		super(subject);
		position = DirectionProcessor.applyDirectionToPosition(subject.position, there);
		actionPointsLeft = Map.getPassageCost(position);
	}
	
	@Override
	public void execute() {
		if (!Map.someoneHere(position)) {
			if (Map.isCellPassable(position))
				Map.moveGameCharacter(performer, position);
		}
		else {
			performer.removeCurrentAction();
			performer.addAction(new HitGameAction(performer, position));
			performer.performAction();
		}
	}
}
