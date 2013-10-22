package by.bsu.course.pm.aggr;


import java.util.Timer;

/**
 * @Author: Andrew
 * @Date: 10/14/13
 */


public class CentralisedExecutor {
	private static final int DELAY_BETWEEN_EXECUTIONS = 1000 * 60;
	private static final String HADOOP_ARTIFACT_DEFAULT_NAME = "hadoop-cyclic-job.jar";

	public static void main(String[] args) {
		String hadoopArtifactName;
		if (args != null && args.length > 0) {
			hadoopArtifactName = args[0];
		} else {
			hadoopArtifactName = HADOOP_ARTIFACT_DEFAULT_NAME;
		}

		String[] hadoopJobArgs = new String[]{hadoopArtifactName, "input", "output"};

		Timer time = new Timer();
		time.schedule(new ScheduledJob(hadoopJobArgs), 0, DELAY_BETWEEN_EXECUTIONS);
	}

}
