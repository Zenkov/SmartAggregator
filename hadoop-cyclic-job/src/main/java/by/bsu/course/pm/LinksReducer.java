package by.bsu.course.pm;

import by.bsu.course.pm.api.jdbc.DBStatementsExecutor;
import by.bsu.course.pm.api.jdbc.stmt.impl.InsertLinkStatement;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.logging.Logger;

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
    private static final Logger LOGGER = Logger.getLogger(LinksReducer.class.getName());

    public void reduce(Text key,
                       Iterable<IntWritable> values,
                       Context context)
            throws IOException, InterruptedException {

        DBStatementsExecutor dbStatementsExecutor = DBStatementsExecutor.getInstance();
        try {
            dbStatementsExecutor.executeStatement(new InsertLinkStatement(key.toString()));

        } catch (Exception e) {
            LOGGER.severe(String.format("%s: %s", e.getClass().getName(), e.getMessage()));
        }
    }
}
