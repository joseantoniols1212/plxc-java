#!/bin/bash

name="PLXC"
cup="java -jar $HOME/Desarrollo/plxc-java/LibreriaCup/java-cup-11b.jar"
jflex="$HOME/.local/bin/jflex"

# Compilamos los archivos
cd src
$jflex $name.flex
$cup $name.cup
javac -cp ../../LibreriaCup/java-cup-11b-runtime.jar: *.java

# Ejecutamos
java -cp ../../LibreriaCup/java-cup-11b-runtime.jar: $name "../$1" -d

# Limpiamos carpeta out
rm *.class ./*/*.class Yylex.java sym.java parser.java
cd ..
