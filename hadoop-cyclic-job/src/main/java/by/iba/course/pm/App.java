package by.iba.course.pm;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * @Author: Andrew
 * @Date: 10/15/13
 */


public class App {
    public static void main(String[] args)
            throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 3) {
            System.err.println("Usage: wordcount <levelCount> <inputFile> <outputDir>");
            System.exit(2);
        }

        int n = Integer.parseInt(otherArgs[0]);
        for (int i = 0; i < n; i++) {
            Job job = new Job(conf, "wordcount");

            job.setJarByClass(App.class);
            job.setMapperClass(LinksMapper.class);

            job.setCombinerClass(LinksReducer.class);

            job.setReducerClass(LinksReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);
            FileInputFormat.addInputPath(job, new Path(otherArgs[1] + "_" + i));
            FileOutputFormat.setOutputPath(job, new Path(otherArgs[1] + "_" + (i + 1)));
            job.waitForCompletion(true);

        }

    }
}
