import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Calendar;

public class LogGenerator extends MonitoringSystem {

	public static void main(String args[]) throws ParseException, IOException {

		if (args[0] == " ") {
			System.out.println("Please Enter Proper FilePath");
			return;
		}
		String dataPath = args[0];

		Path path = Paths.get(dataPath);

		Calendar calendar = Calendar.getInstance();

		calendar.set(2014, 9, 31, 0, 0, 0);
		long unix_timestamp = calendar.getTimeInMillis() / 1000 - 25200;// getting utc time from pst

		MonitoringSystem server[] = new MonitoringSystem[NUM_OF_SERVERS];

		File file = new File(path + "/log1.txt");

		FileWriter fstream = new FileWriter(file);
		BufferedWriter writer = null;
		writer = new BufferedWriter(fstream);
		writer.write("timestamp      IP               cpu_id usage");
		writer.newLine();

		/* Loop through every minute and log it to the file */
		for (int i = 0; i < MINUTES_IN_A_DAY; i++) {
			/* Flags to set ip addresses */
			int ip_3 = 1;
			int ip_4 = 1;
			int ip[] = new int[4];
			for (int j = 0; j < NUM_OF_SERVERS; j++) {
				/* Save Current time */
				server[j] = new MonitoringSystem();
				server[j].setUnix_timestamp(unix_timestamp);

				/* Save CPU_IDs */
				server[j].setCpu_1(0);
				server[j].setCpu_2(1);

				

				/* CPU usage random number */
				server[j].setCpu_usage_1((int) (Math.random() * 100));
				server[j].setCpu_usage_2((int) (Math.random() * 100));

				/* Assign IP addresses to each server */
				ip[0] = 192;
				ip[1] = 168;

				/* To restrict ip address to not go beyond 255 */
				if (ip_4 <= 255) {
					ip[2] = ip_3;
					ip[3] = ip_4++;
				} else {
					ip_4 = 1;
					ip_3++;
					ip[2] = ip_3;
					ip[3] = ip_4++;
				}

				String ipAddress = ip[0] + "." + ip[1] + "." + ip[2] + "." + ip[3];
				server[j].setIpAddress(ipAddress);
				/* Log the saved values to log file */
				writer.write(server[j].getUnix_timestamp() + "     " + server[j].getIpAddress() + "      " + "   "
						+ server[j].getCpu_1() + "    " + server[j].getCpu_usage_1());
				writer.newLine();
				writer.write(server[j].getUnix_timestamp() + "     " + server[j].getIpAddress() + "      " + "   "
						+ server[j].getCpu_2() + "    " + server[j].getCpu_usage_2());
				writer.newLine();

			} // End for

			unix_timestamp = unix_timestamp + 60;

		}
		writer.close();
		server = null;

		fstream.close();
		System.out.println("Done");

	}

}
