package DTOs;

public class UserResource {
	private int _id;
	private String _name;

	public UserResource(int _id, String _name) {
		this._id = _id;
		this._name = _name;
	}

	public String toString(){
		return "id: " + _id +  " name: " + _name;
	}

	public int get_id() {
		return _id;
	}

	public String get_name() {
		return _name;
	}
}