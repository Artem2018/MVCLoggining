package DTOs;

public final class User {

    private int _id;
    private String _userName;
    private String _userPassword;

    public User(int id, String userName, String userPassword) {
        _id = id;
        _userName = userName;
        _userPassword = userPassword;
    }

    public User(String userName, String userPassword) {
        this(0, userName, userPassword);
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getUserName() {
        return _userName;
    }

    public void setUserName(String _userName) {
        this._userName = _userName;
    }

    public String getUserPassword() {
        return _userPassword;
    }

    public void setUserPassword(String _userPassword) {
        this._userPassword = _userPassword;
    }
}