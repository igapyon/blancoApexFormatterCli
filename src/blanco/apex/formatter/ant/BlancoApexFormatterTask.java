/*
 * Copyright 2016 Toshiki Iga
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package blanco.apex.formatter.ant;

import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import blanco.apex.formatter.cli.BlancoApexFormatterCli;
import blanco.apex.formatter.cli.BlancoApexFormatterCliSettings;

public class BlancoApexFormatterTask extends Task {
	protected final BlancoApexFormatterCliSettings settings = new BlancoApexFormatterCliSettings();

	public void setVerbose(final boolean verbose) {
		settings.setVerbose(verbose);
	}

	public void setInput(final String input) {
		settings.setInput(input);
	}

	public void setOutput(final String output) {
		settings.setOutput(output);
	}

	public void setSmashWhitespace(final boolean smashwhitespace) {
		settings.getFormatterSettings().setSmashWhitespace(smashwhitespace);

	}

	@Override
	public void execute() throws BuildException {
		if (BlancoApexFormatterCli.validate(settings) == false) {
			throw new BuildException("Parameter error");
		}

		try {
			BlancoApexFormatterCli.process(settings);
		} catch (IOException ex) {
			throw new BuildException(ex);
		}
	}
}
