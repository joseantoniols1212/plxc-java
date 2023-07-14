#!/bin/bash

name="PLC"

cd ./src/
zip ../$name.zip ./$name.* ./visitor/* ./syntax/*
