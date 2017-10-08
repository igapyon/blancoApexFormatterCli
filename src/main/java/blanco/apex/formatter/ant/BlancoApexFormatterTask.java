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

/**
 * Ant task entry point for apex code formatter.
 * 
 * @author Toshiki Iga
 */
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

    public void setXsmashwhitespace(final boolean smashwhitespace) {
        settings.getFormatterSettings().setSmashWhitespace(smashwhitespace);

    }

    public void setXcomma(final boolean arg) {
        settings.getFormatterSettings().setFormatComma(arg);
    }

    public void setXsemicolon(final boolean arg) {
        settings.getFormatterSettings().setFormatSemicolon(arg);
    }

    public void setXindent(final boolean arg) {
        settings.getFormatterSettings().setFormatIndent(arg);
    }

    public void setXspecialchar(final boolean arg) {
        settings.getFormatterSettings().setFormatSpecialChar(arg);
    }

    public void setXbracket(final boolean arg) {
        settings.getFormatterSettings().setFormatBracket(arg);
    }

    /**
     * Entry point of ant task.
     */
    @Override
    public void execute() throws BuildException {
        BlancoApexFormatterCli.showVersion();

        validate();

        if (BlancoApexFormatterCli.validate(settings) == false) {
            throw new BuildException("Parameter error");
        }

        try {
            BlancoApexFormatterCli.process(settings);
        } catch (IOException ex) {
            throw new BuildException(ex);
        }
    }

    /**
     * Validate input.
     */
    protected void validate() {
        if (settings.getInput() == null) {
            throw new BuildException("input attribute is required");
        }
        if (settings.getOutput() == null) {
            throw new BuildException("output attribute is required");
        }
    }
}
