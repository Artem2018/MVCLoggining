package repositories;


import DTOs.ResourceDetails;
import DTOs.User;
import DTOs.UserResource;
import DTOs.Resources;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements IUserRepository {

	private static final String TABLE_USER = "\"users\"";
	private static final String COLUMN_USER_ID = "id";
	private static final String COLUMN_USER_NAME = "firstName";
	private static final String COLUMN_USER_PASSWORD = "password";

	private static final String TABLE_RESOURCE = "\"resources\"";
	private static final String COLUMN_RESOURCE_ID = "id";
	private static final String COLUMN_RESOURCE_NAME = "name";
	private static final String COLUMN_RESOURCE_CONTENT = "content";

	private static final String TABLE_USER_RESOURCE = "\"users_resource\"";
	private static final String COLUMN_USER_RESOURCE_ID_USER = "id_user";
	private static final String COLUMN_USER_RESOURCE_ID_RESULT = "id_res";

	private final DataSource _dataSource;

	public UserRepository(DataSource dataSource) {
		_dataSource = dataSource;
	}


	private synchronized Connection connection() throws SQLException {
		return _dataSource.getConnection();
	}

	@Override
	public User login(String login, String password) throws SQLException {
		Connection connection = null;
		User user = null;
		try {
			connection = connection();
			PreparedStatement stmt = connection.prepareStatement(//
					"SELECT "//
							+ COLUMN_USER_ID + ", " + COLUMN_USER_NAME + ", " + COLUMN_USER_PASSWORD //
							+ " FROM " //
							+ TABLE_USER //
							+ " WHERE username like ? and password like ?" );
			stmt.setString(1,login);
			stmt.setString(2,password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(COLUMN_USER_ID);
				String userName = rs.getString(COLUMN_USER_NAME);
				String userPassword = rs.getString(COLUMN_USER_PASSWORD);
				user = new User(id, userName, userPassword);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close(); // return connection back to pool
			}
		}
		return user;
	}

	@Override
	public Resources resourceNames(User user) {
		Connection connection ;
		Resources resources = new Resources();
		try {
			connection = connection();
			PreparedStatement stmt = connection.prepareStatement(//
					"SELECT "//
							+  COLUMN_USER_RESOURCE_ID_RESULT  //
							+ " FROM " //
							+ TABLE_USER_RESOURCE //
							+ " WHERE id_user = ?" );
			stmt.setInt(1,user.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PreparedStatement stmt1 =connection.prepareStatement(
						//
						"SELECT "//
								+ COLUMN_RESOURCE_ID + ", " + COLUMN_RESOURCE_NAME  //
								+ " FROM " //
								+ TABLE_RESOURCE //
								+ " WHERE id = ?" );
				stmt1.setInt(1,rs.getInt(COLUMN_USER_RESOURCE_ID_RESULT));
				ResultSet rs1 = stmt1.executeQuery();
				while (rs1.next()){
					int id = rs1.getInt(COLUMN_RESOURCE_ID);
					String name = rs1.getString(COLUMN_RESOURCE_NAME);
					resources.append(new UserResource(id,name));
				}
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return resources;
	}

	@Override
	public ResourceDetails resourceDetail(int resourceId) throws SQLException {
		Connection connection = null;
		ResourceDetails resourceDetails = null;
		try {
			connection = connection();
			PreparedStatement stmt = connection.prepareStatement(//
					"SELECT "//
							+ COLUMN_RESOURCE_ID + ", " + COLUMN_RESOURCE_NAME + ", " + COLUMN_RESOURCE_CONTENT //
							+ " FROM " //
							+ TABLE_RESOURCE //
							+ " WHERE id = ?" );
			stmt.setInt(1,resourceId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				resourceDetails = new ResourceDetails(rs.getInt(COLUMN_RESOURCE_ID),
						rs.getString(COLUMN_RESOURCE_NAME),rs.getString(COLUMN_RESOURCE_CONTENT));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close(); // return connection back to pool
			}
		}
		return resourceDetails;
	}
}