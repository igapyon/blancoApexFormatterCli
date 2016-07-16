package blanco.apex.formatter.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class BlancoApexFormatterTask extends Task {
	@Override
	public void execute() throws BuildException {
		validateInput();
	}

	protected void validateInput() throws BuildException {
		throw new BuildException("set param a param.");
	}
}
