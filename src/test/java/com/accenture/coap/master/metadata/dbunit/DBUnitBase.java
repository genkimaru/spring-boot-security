package com.accenture.coap.master.metadata.dbunit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.AmbiguousTableNameException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class DBUnitBase {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DataSource dataSource;

	private IDatabaseConnection conn;

	private File tempFile;

	protected String[] table;

	public abstract void setConf();

	@Autowired
	EntityManagerFactory entityManagerFactory;
	public int BACKUP_STATUS = 3;

	@Before
	public void setup() throws Exception {
		setConf();
		// conn = new
		// DatabaseConnection(DataSourceUtils.getConnection(dataSource));

		// caution! 可以避免出现 unknown table XXX in information_schema 的错误，但是会失去事务性。
		conn = new MySqlConnection(dataSource.getConnection(), "coap_master");
		DatabaseConfig databaseConfig = conn.getConfig();
		databaseConfig.setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);
		databaseConfig.setProperty(DatabaseConfig.PROPERTY_ESCAPE_PATTERN, "`?`");
		BACKUP_STATUS = backupTables(table);
		// BACKUP_STATUS = backupAndcleanDB();
	}

	@After
	public void teardown() {
		if (BACKUP_STATUS == 1) {
			try {
				rollback();
				// logger.info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=Rollback Successful=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=Rollback Failed=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			}
		}
	}

	protected void setUpDataSet(String file) throws Exception {
		IDataSet dataset = new FlatXmlDataSet(new ClassPathResource(file).getFile());
		DatabaseOperation.CLEAN_INSERT.execute(conn, dataset);
	}

	protected void verifyDataSet(String file) throws Exception {
		IDataSet expected = new FlatXmlDataSet(new ClassPathResource(file).getFile());
		IDataSet dataset = conn.createDataSet();

		for (String tableName : expected.getTableNames()) {
			Assertion.assertEquals(expected.getTable(tableName), dataset.getTable(tableName));
		}
	}

	protected void verifyDataSet(String tableName, String sql, String expectFile) throws Exception {
		QueryDataSet actual = new QueryDataSet(conn);
		actual.addTable(tableName, sql);
		IDataSet expected = new FlatXmlDataSet(new ClassPathResource(expectFile).getFile());
		Assertion.assertEquals(expected, actual);
	}

	protected void clearTable(String... tableName) throws Exception {
		DefaultDataSet dataset = new DefaultDataSet();
		for (String t : tableName) {
			dataset.addTable(new DefaultTable(t));
		}
		DatabaseOperation.DELETE_ALL.execute(conn, dataset);
	}

	protected void verifyEmpty(String tableName) throws DataSetException, SQLException {
		Assert.assertEquals(0, conn.createDataSet().getTable(tableName).getRowCount());
	}
	/**
	 * 
	 * @param tableName
	 * @param file
	 * @throws Exception
	 * @deprecated 
	 */
	protected void cleanAndInsert(String tableName, String file) throws Exception {

		DefaultDataSet dataset = new DefaultDataSet();
		dataset.addTable(new DefaultTable(tableName));
		DatabaseOperation.DELETE_ALL.execute(conn, dataset);

		IDataSet origin = getDataSetFromFlatXmlDataSetBuilder(file);
		DatabaseOperation.INSERT.execute(conn, origin);
	}

	protected void cleanAndInsert(String file) throws Exception {
		IDataSet origin = getDataSetFromFlatXmlDataSetBuilder(file);
		DatabaseOperation.CLEAN_INSERT.execute(conn, origin);
	}

	protected IDataSet getDataSetFromFlatXmlDataSetBuilder(String file) throws Exception {
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		builder.setColumnSensing(true);
		return builder.build(new FileInputStream(new ClassPathResource(file).getFile()));
	}

	/**
	 * buckup all tables in the database
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws DataSetException
	 * 
	 * @throws Exception
	 */

	protected void backupDB() throws SQLException, DataSetException, IOException {
		IDataSet ds = conn.createDataSet();
		tempFile = File.createTempFile("temp", ".xml");
		FlatXmlDataSet.write(ds, new FileWriter(tempFile), "UTF-8");
	}

	/**
	 * 
	 * @param tableName
	 *            define one or more tables need buckup
	 * @throws Exception
	 */

	protected int backupTables(String... tableName) {

		try {
			QueryDataSet qds = new QueryDataSet(conn);
			for (String str : tableName) {
				qds.addTable(str);
			}
			tempFile = File.createTempFile("temp", ".xml");
			FlatXmlDataSet.write(qds, new FileWriter(tempFile), "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	/**
	 * restore the database or tables from the tempfile
	 * 
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws DatabaseUnitException
	 * @throws Exception
	 */
	protected void rollback() throws FileNotFoundException, DatabaseUnitException, SQLException {

		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		builder.setColumnSensing(true);
		IDataSet ds = builder.build(new FileInputStream(tempFile));
		// recover database
		DatabaseOperation.CLEAN_INSERT.execute(conn, ds);
	}

	protected int backupAndcleanDB() {

		try {
			backupDB();
		} catch (DataSetException | SQLException | IOException e) {
			e.printStackTrace();
			logger.info("=-=-=-=-=-=-=-=-=-=-=-=-=-=BackUp DB failed=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			return 0;
		}

		try {
			IDataSet ds = conn.createDataSet();
			DatabaseOperation.DELETE_ALL.execute(conn, ds);
		} catch (DatabaseUnitException | SQLException e) {
			e.printStackTrace();
			logger.info("=-=-=-=-=-=-=-=-=-=-=-=-=-=Clean DB failed=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			return 2;
		}
		return 1;
	}
}
