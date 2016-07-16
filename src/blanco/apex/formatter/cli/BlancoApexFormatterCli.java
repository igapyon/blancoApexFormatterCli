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
import blanco.apex.formatter.BlancoApexFormatterSettings;
import blanco.apex.parser.BlancoApexConstants;
import blanco.apex.syntaxparser.BlancoApexSyntaxConstants;

public class BlancoApexFormatterCli {
	public static void main(final String[] args) {
		final BlancoApexFormatterSettings settings = new BlancoApexFormatterSettings();

		showVersion();

		final Options options = getOptions();

		final CommandLineParser parser = new DefaultParser();
		try {
			final CommandLine cmd = parser.parse(options, args);

			if (cmd.hasOption("h")) {
				showUsage(options);
			}

			// main process

			final boolean isVerbose = cmd.hasOption("v");

			final String input = cmd.getOptionValue("i");
			if (isVerbose) {
				System.out.println("input: [" + input + "]");
			}

			final String output = cmd.getOptionValue("o");
			if (isVerbose) {
				System.out.println("output: [" + output + "]");
			}

			settings.setSmashWhitespace(cmd.hasOption("xsmashwhitespace"));
			if (isVerbose) {
				System.out.println("xsmashwhitespace: [" + settings.getSmashWhitespace() + "]");
			}

		} catch (ParseException ex) {
			System.err.println("Parse argument failed. Reason: " + ex.getMessage());
			showUsage(options);
		}
	}

	public static void showVersion() {
		System.err.println("BlancoApexFormatterCli");
		System.err.println("    formatter     : " + BlancoApexFormatterConstants.getVersion());
		System.err.println("    syntax parser : " + BlancoApexSyntaxConstants.getVersion());
		System.err.println("    lexical parser: " + BlancoApexConstants.getVersion());
	}

	public static void showUsage(final Options options) {
		final HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("BlancoApexFormatterCli", options);
	}

	public static Options getOptions() {
		final Options options = new Options();
		options.addOption(Option.builder("i").longOpt("input") //
				.required(true) //
				.hasArg(true).argName("inputdir")//
				.desc("input directory.").build());
		options.addOption(Option.builder("o").longOpt("output") //
				.required(true) //
				.hasArg(true).argName("outputdir")//
				.desc("output directory.").build());
		options.addOption(Option.builder("h").longOpt("help") //
				.required(false) //
				.hasArg(false) //
				.desc("show usage.").build());
		options.addOption(Option.builder("v").longOpt("verbose") //
				.required(false) //
				.hasArg(false) //
				.desc("run verbose mode.").build());

		options.addOption(Option.builder("xsmashwhitespace").required(false).hasArg(false) //
				.desc("format with whitespace squish.").build());

		return options;
	}
}
