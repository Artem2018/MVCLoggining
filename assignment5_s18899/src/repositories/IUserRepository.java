package repositories;

import DTOs.ResourceDetails;
import DTOs.User;
import DTOs.Resources;

import java.sql.SQLException;

public interface IUserRepository {

	User login(String login, String password) throws SQLException;

	Resources resourceNames(User user);

	ResourceDetails resourceDetail(int resourceId) throws SQLException;


}