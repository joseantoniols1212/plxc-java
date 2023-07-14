#!/bin/bash

name="PLC"
cup="java -jar $HOME/Desarrollo/plxc-java/LibreriaCup/java-cup-11b.jar"
jflex="$HOME/.local/bin/jflex"

# Compilamos los archivos
cd src
$jflex $name.flex
$cup $name.cup
javac -cp ../../LibreriaCup/java-cup-11b-runtime.jar: *.java

# Ejecutamos tests

for i in ../tests/*
do
  echo $i
  diff <(../ctd <(../plc $i)) <(../ctd <(java -cp ../../LibreriaCup/java-cup-11b-runtime.jar: PLC $i))
done

# Borramos archivos compilados
rm *.class ./*/*.class Yylex.java sym.java parser.java
cd ..
