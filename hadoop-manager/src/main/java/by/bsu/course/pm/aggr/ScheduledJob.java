package by.bsu.course.pm.aggr;

import by.bsu.course.pm.api.jdbc.DBStatementsExecutor;
import by.bsu.course.pm.api.jdbc.stmt.impl.GetLinksStatement;
import by.bsu.course.pm.api.jdbc.stmt.impl.UpdateLinksStatement;
import org.apache.hadoop.util.RunJar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Logger;

public class ScheduledJob extends TimerTask {
	private static final int INPUT_INDEX = 1;
	private static final int OUTPUT_INDEX = 2;
	private static final int HADOOP_EXECUTION_ERROR_CODE = 5000;
	private static final int SQL_SELECT_ERROR_CODE = 6000;
	private static final int SQL_UPDATE_ERROR_CODE = 7000;
	private static final int INPUT_FILE_CREATION_ERROR_CODE = 8000;
	private static final Logger LOGGER = Logger.getLogger(CentralisedExecutor.class.getName());
	private boolean inProgress = false;
	private final String [] args;

	public ScheduledJob(String[] args) {
		this.args = args;

	}

	@Override
	public void run() {
		if (inProgress) {
			return;
		}
		synchronized (this) {
			LOGGER.info("started new scheduled task");
			DBStatementsExecutor dbStatementsExecutor = DBStatementsExecutor.getInstance();
			List<String> links = getLinksToVisit(dbStatementsExecutor);

			if (links.isEmpty()) {
				return;
			}

			File inputTempFile = createInputFile(links);
			args[INPUT_INDEX] = inputTempFile.getAbsolutePath();
			args[OUTPUT_INDEX] = String.valueOf(System.currentTimeMillis());

			startJob();
			markLinksAsVisited(dbStatementsExecutor, links);
			inputTempFile.delete();
			inProgress = false;
		}
	}

	private List<String> getLinksToVisit(DBStatementsExecutor dbStatementsExecutor) {
		GetLinksStatement statement = new GetLinksStatement();
		try {
			dbStatementsExecutor.executeStatement(statement);
		} catch (SQLException e) {
			LOGGER.severe(String.format("%s: %s", e.getClass().getName(), e.getMessage()));
			System.exit(SQL_SELECT_ERROR_CODE);
		}

		List<String> links = statement.getResult();
		LOGGER.info(String.format("selected %d new links", links.size()));

		return links;
	}

	private File createInputFile(List<String> links) {
		File inputTempFile = null;
		try {
			inputTempFile = createFile(links);
			LOGGER.info(String.format("created file with links: %s", inputTempFile.getAbsolutePath()));
		} catch (IOException e) {
			LOGGER.severe(String.format("%s: %s", e.getClass().getName(), e.getMessage()));
			System.exit(INPUT_FILE_CREATION_ERROR_CODE);
		}
		return inputTempFile;
	}

	private File createFile(List<String> links) throws IOException {
		File temp = File.createTempFile("links", ".tmp");
		BufferedWriter out = new BufferedWriter(new FileWriter(temp));
		for (String s : links) {
			out.write(s);
			out.newLine();
		}
		out.flush();
		out.close();

		return temp;
	}

	private void startJob() {
		try {
			inProgress = true;
			RunJar.main(args);
			LOGGER.info(String.format("new hadoop job was stared"));
		} catch (Throwable t) {
			LOGGER.severe(String.format("%s: %s", t.getClass().getName(), t.getMessage()));
			System.exit(HADOOP_EXECUTION_ERROR_CODE);
		}
	}

	private void markLinksAsVisited(DBStatementsExecutor dbStatementsExecutor, List<String> links) {
		try {
			dbStatementsExecutor.executeStatement(new UpdateLinksStatement(links.toArray(new String[links.size()])));
			LOGGER.info(String.format("all %d links have been visited", links.size()));
		} catch (SQLException e) {
			LOGGER.severe(String.format("%s: %s", e.getClass().getName(), e.getMessage()));
			System.exit(SQL_UPDATE_ERROR_CODE);
		}
	}

}