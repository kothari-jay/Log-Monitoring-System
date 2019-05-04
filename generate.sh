#!/bin/sh
# This is generator script.
#Creates output directory.
#Compiles Source file,Executes the LogGenerator code  and genrates log1.txt

# Check if the command line is empty or not
if [ ! -z "$1" ]&& [ "$1" != " " ];then

#create directory specified by the user
mkdir $1

#Compile Java Files
javac MonitoringSystem.java
javac LogGenerator.java

echo "Compile Successfull"

java LogGenerator $1

echo "Logs generatad in $1 directory"
else
echo "Please Enter Valid Data path.Example. ./generate.sh output_dir"
fi
