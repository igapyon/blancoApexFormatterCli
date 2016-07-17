# blancoApexFormatterCli
command line interface of code formatter for Apex language written in Java.

## usage

### Ant task
Write ant taskdef like below.

```xml
	<taskdef name="apexformatter" classname="blanco.apex.formatter.ant.BlancoApexFormatterTask">
		<classpath>
			<pathelement location="./blancoApexFormatterCli.jar" />
			<pathelement location="./lib/blancoApexFormatter.jar" />
			<pathelement location="./lib/blancoApexSyntaxParser.jar" />
			<pathelement location="./lib/blancoApexParser.jar" />
			<pathelement location="./lib/apache/commons-cli-1.3.1.jar" />
			<pathelement location="./lib/apache/commons-io-2.5.jar" />
		</classpath>
	</taskdef>
```

Run ant task like below.

```xml
	<target name="doFormat">
		<apexformatter input="./test/data/apex/" output="./test/data/apex.output"
		               verbose="true" xsmashwhitespace="false" />
	</target>
```

### Command line

```
usage: BlancoApexFormatterCli
 -h,--help                   show usage.
 -i,--input <inputdir>       input directory.
 -o,--output <outputdir>     output directory.
 -v,--verbose                run verbose mode.
 -xbracket <true>            format bracket.
 -xcomma <true>              format comma.
 -xindent <true>             format indent.
 -xsemicolon <true>          format semicolon.
 -xsmashwhitespace <false>   format with whitespace smash (hard format).
 -xspecialchar <true>        format special char.
```

## LICENSE

```
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
```
