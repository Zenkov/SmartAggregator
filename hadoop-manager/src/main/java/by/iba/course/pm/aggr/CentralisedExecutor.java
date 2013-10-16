package by.iba.course.pm.aggr;


import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.RunJar;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Andrew
 * @Date: 10/14/13
 */


public class CentralisedExecutor {
	public static void main(String[] args) throws Throwable {

		List<String> arguments = new LinkedList<String>();

		//arguments.add("hadoop-cyclic-job.jar");
		arguments.add("hadoop-examples-1.2.1.jar");

		arguments.add("grep");
		arguments.add("CHANGES.txt");
		arguments.add("output2");
		arguments.add("'dfs[a-z.]+'");

		RunJar.main(arguments.toArray(new String[arguments.size()]));
	}
}
