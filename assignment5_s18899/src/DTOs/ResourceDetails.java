package DTOs;

public class ResourceDetails extends UserResource {

	private String _content;

	public ResourceDetails(int _id, String _name, String _content) {
		super(_id, _name);
		this._content = _content;
	}

	@Override
	public String toString() {
		return _content;
	}

	public String get_content() {
		return _content;
	}
}