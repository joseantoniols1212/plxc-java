#!/bin/bash

name="PLXC"

cd ./src/
zip ../$name.zip ./$name.* ./visitor/* ./syntax/* ./symbolTable/*
