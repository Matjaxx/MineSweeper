#!/bin/bash

# Compile the Java files
javac Main_Menu.java Map.java

# Run the Main_Menu class
if [ $? -eq 0 ]; then
    java Main_Menu
fi