package ro.utcn.pt.assign2.dao;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringJoiner;

public class TableDAO {
	public static ArrayList<Object> readAll(Class<?> classObject) {
		// retrieve data from database
		Connection dbConnection = DBConnector.getConnection();
		// query
		String tableName = classObject.getSimpleName();
		String queryStatement = "select * from " + tableName;
		PreparedStatement statement;
		ArrayList<Object> tableContents = new ArrayList<Object>();
		try {
			statement = dbConnection.prepareStatement(queryStatement);
			ResultSet result = statement.executeQuery();
			while (result.next()) {

				// reflection
				Field[] fields = classObject.getDeclaredFields();
				fields[0].setAccessible(true);
				Constructor<?> constructor = classObject.getDeclaredConstructor();
				Object modelObject = constructor.newInstance();

				// set the fields in the table
				int i = 1;
				for (Field field : fields) {
					field.setAccessible(true);
					field.set(modelObject, result.getObject(i));
					i++;
				}
				tableContents.add(modelObject);
			}
			return tableContents;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Object read(Class<?> classObject, String key, String keyValue) {
		// retrieve data from database
		Connection dbConnection = DBConnector.getConnection();
		// query
		String tableName = classObject.getSimpleName();
		String queryStatement = "select * from " + tableName+ " where "+ key+"="+keyValue;
		PreparedStatement statement;
		try {
			statement = dbConnection.prepareStatement(queryStatement);
			ResultSet result = statement.executeQuery();
			if (result.next()) {

				// reflection
				Field[] fields = classObject.getDeclaredFields();
				fields[0].setAccessible(true);
				Constructor<?> constructor = classObject.getDeclaredConstructor();
				Object table = constructor.newInstance();

				// set the fields in the table
				int i = 1;
				for (Field field : fields) {
					field.setAccessible(true);
					field.set(table, result.getObject(i));
					i++;
				}
				return table;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public static boolean insert(Object product) {

		Class<?> productClassObject = product.getClass();
		try {
			Field fields[] = productClassObject.getDeclaredFields();
			String tableName = productClassObject.getSimpleName();
			String insertQuery = constructInsertQuery(product, fields, tableName);

			Statement insertStmt;
			insertStmt = DBConnector.getConnection().createStatement();
			int nr = insertStmt.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);
			if (nr <= 0) {
				return false;
			}
		} catch (SecurityException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean update(Object table) {

		Class<?> productClassObject = table.getClass();

		try {
			Field fields[] = productClassObject.getDeclaredFields();
			String tableName = productClassObject.getSimpleName();
			fields[0].setAccessible(true);
			String updateQuery = constructUpdateQuery(table, fields, tableName,fields[0].get(table).toString());

			Statement updateStmt;
			updateStmt = DBConnector.getConnection().createStatement();
			int nr = updateStmt.executeUpdate(updateQuery, Statement.RETURN_GENERATED_KEYS);
			if (nr <= 0) {
				return false;
			}
		} catch (SecurityException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public static boolean delete(Object objectToBeDeleted){
		
		Class<?> classObject = objectToBeDeleted.getClass();

		try {
			Field fields[] = classObject.getDeclaredFields();
			String tableName = classObject.getSimpleName();
			fields[0].setAccessible(true);
			String keyName = fields[0].getName();
			String keyValue = fields[0].get(objectToBeDeleted).toString();
			Statement deleteStmt = DBConnector.getConnection().createStatement();
			String deleteQuery ="delete from "+tableName+" where ("+keyName +"="+keyValue+")";
			int nr = deleteStmt.executeUpdate(deleteQuery, Statement.RETURN_GENERATED_KEYS);
			if (nr <= 0) {
				return false;
			}
		} catch (SecurityException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;//returns true upon success
	}
	private static String constructInsertQuery(Object instance, Field[] fields, String tableName) {

		StringJoiner columnNames = new StringJoiner(",");
		for (int i = 1; i < fields.length; i++) {
			fields[i].setAccessible(true);
			columnNames.add(fields[i].getName());
		}
		StringJoiner columnValues = new StringJoiner("','");
		for (int i = 1; i < fields.length; i++) {
			try {
				columnValues.add(fields[i].get(instance).toString());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return null;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return null;
			}
		}
		String query = "insert into " + tableName + "(" + columnNames.toString() + ") values ('"
				+ columnValues.toString() + "');";
		return query;
	}

	public static String constructUpdateQuery(Object instance, Field[] fields, String tableName,String value) {

		StringJoiner updatedRows = new StringJoiner(",");
		try {
			for (int i = 1; i < fields.length; i++) {
				fields[i].setAccessible(true);
				String fieldName = fields[i].getName();
				String fieldValue;
				fieldValue = fields[i].get(instance).toString();
				updatedRows.add(fieldName + "='" + fieldValue + "'");
			}
			return "update " + tableName + " set " + updatedRows + " where " + fields[0].getName() + "=" + value;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
}
