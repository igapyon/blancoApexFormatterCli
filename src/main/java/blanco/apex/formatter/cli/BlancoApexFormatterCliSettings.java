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
package blanco.apex.formatter.cli;

import java.io.File;

import blanco.apex.formatter.BlancoApexFormatterSettings;

public class BlancoApexFormatterCliSettings {
	protected boolean isVerbose = false;

	protected String input = null;

	protected String output = null;

	protected BlancoApexFormatterSettings formatterSettings = new BlancoApexFormatterSettings();

	///////
	//
	protected File inputFile = null;

	protected File outputFile = null;

	public BlancoApexFormatterSettings getFormatterSettings() {
		return formatterSettings;
	}

	public String getInput() {
		return input;
	}

	public File getInputFile() {
		if (inputFile == null) {
			inputFile = new File(input);
		}
		return inputFile;
	}

	public String getOutput() {
		return output;
	}

	public File getOutputFile() {
		if (outputFile == null) {
			outputFile = new File(output);
		}
		return outputFile;
	}

	public boolean getVerbose() {
		return isVerbose;
	}

	public void setFormatterSettings(BlancoApexFormatterSettings formatterSettings) {
		this.formatterSettings = formatterSettings;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public void setOutput(final String output) {
		this.output = output;
	}

	public void setVerbose(boolean isVerbose) {
		this.isVerbose = isVerbose;
	}
}
