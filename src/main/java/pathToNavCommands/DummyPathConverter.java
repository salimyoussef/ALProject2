package pathToNavCommands;

import java.util.ArrayList;
import java.util.Iterator;

import endPoints.EndPoint;
import pathFinder.Path;

public class DummyPathConverter implements PathToComandStrategy {
  
  public DummyPathConverter () { }

@Override
public ArrayList<Command> convertPath(Path path) {
	ArrayList<Command> commands = new ArrayList<Command>();
	Iterator<EndPoint> iterator = path.iterator();
	while(iterator.hasNext()){
		commands.add(new GoAheadCommand(iterator.next()));
	}
	return commands;
}
  
}

