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

import blanco.apex.formatter.BlancoApexFormatterConstants;
import blanco.apex.parser.BlancoApexConstants;
import blanco.apex.syntaxparser.BlancoApexSyntaxConstants;

public class BlancoApexFormatterCli {
	public static void main(final String[] args) {
		System.err.println("blancoApexFormatter: " + BlancoApexFormatterConstants.getVersion());
		System.err.println("     lexical parser: " + BlancoApexConstants.getVersion());
		System.err.println("      syntax parser: " + BlancoApexSyntaxConstants.getVersion());
	}
}
