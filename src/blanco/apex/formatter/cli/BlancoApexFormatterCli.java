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
import java.io.IOException;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

import blanco.apex.formatter.BlancoApexFormatter;
import blanco.apex.formatter.BlancoApexFormatterConstants;
import blanco.apex.parser.BlancoApexConstants;
import blanco.apex.syntaxparser.BlancoApexSyntaxConstants;

public class BlancoApexFormatterCli {

	public static void main(final String[] args) throws IOException {
		final BlancoApexFormatterCliSettings settings = new BlancoApexFormatterCliSettings();

		showVersion();

		final Options options = getOptions();

		final CommandLineParser parser = new DefaultParser();
		try {
			final CommandLine cmd = parser.parse(options, args);

			if (cmd.hasOption("h")) {
				showUsage(options);
			}

			// main process

			settings.setVerbose(cmd.hasOption("v"));
			if (settings.getVerbose()) {
				System.out.println("verbose: [" + settings.getVerbose() + "]");
			}

			final String input = cmd.getOptionValue("i");
			if (settings.getVerbose()) {
				System.out.println("input: [" + input + "]");
			}

			final String output = cmd.getOptionValue("o");
			if (settings.getVerbose()) {
				System.out.println("output: [" + output + "]");
			}

			settings.getFormatterSettings()
					.setSmashWhitespace("true".equalsIgnoreCase(cmd.getOptionValue("xsmashwhitespace", "false")));
			if (settings.getVerbose()) {
				System.out.println("xsmashwhitespace: [" + settings.getFormatterSettings().getSmashWhitespace() + "]");
			}

			settings.getFormatterSettings()
					.setFormatComma("true".equalsIgnoreCase(cmd.getOptionValue("xcomma", "true")));
			if (settings.getVerbose()) {
				System.out.println("xcomma: [" + settings.getFormatterSettings().getFormatComma() + "]");
			}

			settings.getFormatterSettings()
					.setFormatSemicolon("true".equalsIgnoreCase(cmd.getOptionValue("xsemicolon", "true")));
			if (settings.getVerbose()) {
				System.out.println("xsemicolon: [" + settings.getFormatterSettings().getFormatSemicolon() + "]");
			}

			settings.getFormatterSettings()
					.setFormatIndent("true".equalsIgnoreCase(cmd.getOptionValue("xindent", "true")));
			if (settings.getVerbose()) {
				System.out.println("xindent: [" + settings.getFormatterSettings().getFormatIndent() + "]");
			}

			settings.getFormatterSettings()
					.setFormatSpecialChar("true".equalsIgnoreCase(cmd.getOptionValue("xspecialchar", "true")));
			if (settings.getVerbose()) {
				System.out.println("xspecialchar: [" + settings.getFormatterSettings().getFormatSpecialChar() + "]");
			}

			settings.getFormatterSettings()
					.setFormatBracket("true".equalsIgnoreCase(cmd.getOptionValue("xbracket", "true")));
			if (settings.getVerbose()) {
				System.out.println("xbracket: [" + settings.getFormatterSettings().getFormatBracket() + "]");
			}

			final File inputFile = new File(input);
			if (inputFile.exists() == false) {
				System.out
						.println("Error: specified input directory [" + inputFile.getAbsolutePath() + "] not exists.");
				return;
			}
			if (inputFile.isDirectory() == false) {
				System.out.println(
						"Error: specified input directory [" + inputFile.getAbsolutePath() + "] is not a directory.");
				return;
			}

			final File outputFile = new File(output);
			if (outputFile.exists() == false) {
				if (outputFile.mkdirs() == false) {
					System.out.println("Error: fail to create specified output directory ["
							+ outputFile.getAbsolutePath() + "] with error.");
					return;
				}
			}
			if (outputFile.isDirectory() == false) {
				System.out.println(
						"Error: specified output directory [" + outputFile.getAbsolutePath() + "] is not a directory.");
				return;
			}

			final List<File> fileList = (List<File>) FileUtils.listFiles(inputFile,
					FileFilterUtils.suffixFileFilter(".cls"), FileFilterUtils.trueFileFilter());

			for (final File readFile : fileList) {
				final String sourceFileString = FileUtils.readFileToString(readFile, "UTF-8");
				final String formattedFileString = new BlancoApexFormatter(settings.getFormatterSettings())
						.format(sourceFileString);

				final File targetFileCandidate = new File(outputFile, readFile.getName());
				if (targetFileCandidate.exists() == false) {
					// create.
					System.out.println("  create: " + targetFileCandidate.getAbsolutePath());
					FileUtils.writeStringToFile(targetFileCandidate, sourceFileString, "UTF-8");
				} else {
					// update.
					final String targetFileString = FileUtils.readFileToString(targetFileCandidate, "UTF-8");
					if (formattedFileString.equals(targetFileString)) {
						// no changes
						System.out.println("  none  : " + targetFileCandidate.getAbsolutePath());
					} else {
						// update
						System.out.println("  update: " + targetFileCandidate.getAbsolutePath());
						FileUtils.writeStringToFile(targetFileCandidate, sourceFileString, "UTF-8");
					}
				}
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

		/////////////////
		// lexical
		options.addOption(Option.builder("xsmashwhitespace").required(false) //
				.hasArg(true).argName("false") //
				.desc("format with whitespace smash (hard format).").build());

		options.addOption(Option.builder("xcomma").required(false) //
				.hasArg(true).argName("true") //
				.desc("format comma.").build());

		options.addOption(Option.builder("xsemicolon").required(false) //
				.hasArg(true).argName("true") //
				.desc("format semicolon.").build());

		/////////////////
		// syntax
		options.addOption(Option.builder("xindent").required(false) //
				.hasArg(true).argName("true") //
				.desc("format indent.").build());

		options.addOption(Option.builder("xspecialchar").required(false) //
				.hasArg(true).argName("true") //
				.desc("format special char.").build());

		options.addOption(Option.builder("xbracket").required(false) //
				.hasArg(true).argName("true") //
				.desc("format bracket.").build());

		return options;
	}
}
