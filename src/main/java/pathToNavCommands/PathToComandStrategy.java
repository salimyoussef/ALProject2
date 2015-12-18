package pathToNavCommands;

import pathFinder.Path;
import java.util.ArrayList;

public interface PathToComandStrategy {
  public ArrayList<Command> convertPath(Path path);
}

