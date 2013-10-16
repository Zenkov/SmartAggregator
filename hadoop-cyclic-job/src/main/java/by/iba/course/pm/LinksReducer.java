package by.iba.course.pm;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: afilipko
 * Date: 10/16/13
 * Time: 10:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class LinksReducer
        extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable occurrencesOfWord = new IntWritable();

    public void reduce(Text key,
                       Iterable<IntWritable> values,
                       Context context)
            throws IOException, InterruptedException {


        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        occurrencesOfWord.set(sum);

        context.write(key, null);

        System.err.println(String.format("[reduce] word: (%s), count: (%d)", key, occurrencesOfWord.get()));
    }
}
