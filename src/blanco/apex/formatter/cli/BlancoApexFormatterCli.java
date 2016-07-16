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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import blanco.apex.formatter.BlancoApexFormatterConstants;
import blanco.apex.parser.BlancoApexConstants;
import blanco.apex.syntaxparser.BlancoApexSyntaxConstants;

public class BlancoApexFormatterCli {
	public static void main(final String[] args) {
		System.err.println("blancoApexFormatter: " + BlancoApexFormatterConstants.getVersion());
		System.err.println("     lexical parser: " + BlancoApexConstants.getVersion());
		System.err.println("      syntax parser: " + BlancoApexSyntaxConstants.getVersion());

		final Options options = new Options();
		options.addOption(Option.builder("i").required(true).hasArg(true) //
				.argName("inputdir") //
				.desc("input directory.").build());
		options.addOption(Option.builder("o").required(true).hasArg(true) //
				.argName("outputdir") //
				.desc("output directory.").build());
		options.addOption(Option.builder("h").required(false).hasArg(false) //
				.argName("help") //
				.desc("show usage.").build());

		final CommandLineParser parser = new DefaultParser();
		try {
			final CommandLine cmd = parser.parse(options, args);

			if (cmd.hasOption("h")) {
				showUsage(options);
			}
		} catch (ParseException ex) {
			System.err.println("Parse argument failed. Reason: " + ex.getMessage());
			showUsage(options);
		}
	}

	public static void showUsage(final Options options) {
		final HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("blancoApexFormatterCli", options);
	}
}