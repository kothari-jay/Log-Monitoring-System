import java.io.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class MonitoringSystem {

	public static final int MINUTES_IN_A_DAY = 1440;
	public static final int NUM_OF_SERVERS = 1000;

	private long unix_timestamp; // unix timestamp;
	private  String ipAddress;//ip address 
	private long cpu_1; // CPU 1 with id 0
	private long cpu_2; // CPU 2 with id 1
	private long cpu_usage_1; // % of CPU usage of CPU 1
	private long cpu_usage_2; // % of CPU usage of CPU 2
	
	public long getUnix_timestamp() {
		return unix_timestamp;
	}
	public void setUnix_timestamp(long unix_timestamp) {
		this.unix_timestamp = unix_timestamp;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public long getCpu_1() {
		return cpu_1;
	}
	public void setCpu_1(long cpu_1) {
		this.cpu_1 = cpu_1;
	}
	public long getCpu_2() {
		return cpu_2;
	}
	public void setCpu_2(long cpu_2) {
		this.cpu_2 = cpu_2;
	}
	public long getCpu_usage_1() {
		return cpu_usage_1;
	}
	public void setCpu_usage_1(long cpu_usage_1) {
		this.cpu_usage_1 = cpu_usage_1;
	}
	public long getCpu_usage_2() {
		return cpu_usage_2;
	}
	public void setCpu_usage_2(long cpu_usage_2) {
		this.cpu_usage_2 = cpu_usage_2;
	}
	public static int getMinutesInADay() {
		return MINUTES_IN_A_DAY;
	}
	public static int getNumOfServers() {
		return NUM_OF_SERVERS;
	}
	
	
}
