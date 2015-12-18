package pathToNavCommands;

import endPoints.EndPoint;
import drone.Moveable;;

public class GoAheadCommand implements Command {
	
	EndPoint point;
	Moveable drone;
	
	public GoAheadCommand(EndPoint point) {
		this.point = point;
	}
	
	@Override
	public void execute() {
		this.drone.goAhead(this.point);
	}
	
	public void setDrone(Moveable d){
		this.drone =d;
	}

}
