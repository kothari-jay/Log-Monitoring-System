#!/bin/sh

#This is the query script. Used to Query CPU usage for particular time ranges.
#Validates the command
#Displays 'help' on usage
#Any invalid data or commands are handled and Proper usage are displayed on screen.


#Navigate to DATA_PATH.If empty directory is given prompt user to provide correct DATA PATH
if [ -z "$1" ] || [ "$1" == " " ];
then
	echo "Please enter proper command.Example-  ./query.sh DATA_PATH"
	exit
fi

echo initialization will take around 25 minutes. It will be creating cache folder containing result for each ipaddress and each cpuid
#creating cache directory to save time and usage of each cpu_id of each ipaddress

mkdir $1/cache
declare -i ip4=1
declare -i ip3=1
				#loop through all 1000 servers
				for ((i=1;i<1001;i=i+1))
				do
							#ip should be less than 255
							if((ip4>255))
							then
								ip4=1;
								#increment after 255
								ip3=$ip3+1
							fi
				ip="192.168.$ip3.$ip4"

					# echo working on $ip
					mkdir $1/cache/$ip

					#creating text files as per ip and cpuid from logs
				 	grep -w $ip $1/log1.txt | grep -w '  0 ' > $1/cache/$ip/0.txt
				 	grep -w $ip $1/log1.txt | grep -w '  1 ' > $1/cache/$ip/1.txt
											for((j=0;j<2;j=j+1))
											do
																		#format as per question requirement
																		awk '{sub($1, strftime ("%Y-%m-%d %H:%M", $0+25200), $1); print "("$1", "$4"%)"}' $1/cache/$ip/$j.txt	>> $1/cache/$ip/R$j.txt


											done
					ip4=$ip4+1

				done









#Move to proper DATA_PATH provide in command line
cd $1 || exit

#Valid commands
command1="QUERY"
command2="EXIT"

#while loop flag to continuously take user commands
again=1
while [ $again -eq 1 ]
do

	echo -n ">"
	read query

	#Split and store the fields given in query to respective variables
	IFS=' ' read command ip cpu_id s_date s_time d_date d_time <<< "$query"

	#ignore case.Accept both QUERY,query or EXIT or exit
	shopt -s nocasematch

	#if query
	if [[ "$command" == "$command1" ]];
	then
		#validate the fields given in command
		if [ -z "$command" ] || [ -z "$ip" ] || [ -z "$cpu_id" ] || [ -z "$s_date" ] || [ -z "$s_time" ] || [ -z "$d_date" ] || [ -z "$d_time" ];
		then
			echo "Please enter valid command.Type help to see command usage !"
			continue
		fi

		start_unixtime=$(date -ud "$s_date $s_time" "+%s")
		end_unixtime=$(date -ud "$d_date $d_time" "+%s")
		#checking endtime is greater then start time
		if (($start_unixtime>=$end_unixtime))
		then
			echo "Please enter  correct time. Type help to see command usage !"
			continue
		fi



		#fast fetching of results from cache folder
		result=$(sed -n  '/'"$s_date"' '"$s_time"'/,/'"$d_date"' '"$d_time"'/p' cache/"$ip"/R"$cpu_id".txt | tr '\n' ',' | rev | cut -c 2- | rev)

		#if nothing is found display message to user
		if [ -z "$result" ];
		then
			echo "No data found. Possible reasons could be either data not found for the given input or Invalid command."
			echo "Type Help to see the commands and format of query"
			break
		fi
		echo "CPU$cpu_id usage on $ip:"
		echo $result


				elif [[ "$command" == "$command2" ]];
				then
					again=0

	#HELP message to user
	else
		echo "COMMANDS are QUERY or EXIT only!!Please try again "
		echo "Example- QUERY IP CPU_ID start_time end_time"
		echo "IP in the ranges 192.168.1.1 to 1.255, 192.168.2.1 to 2.255, 192.168.3.1 to 3.255, 192.168.4.1 to 4.235"
		echo "CPU_ID is 0 or 1"
		echo "start_time  and end_time are in YYYY-MM-DD HH:MM and start_time < end_time "
		echo "DATA available only for 24 hrs of CPU USAGE for the day 2014-10-31"
	fi
done
echo ""
