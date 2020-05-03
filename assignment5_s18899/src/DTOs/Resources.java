package DTOs;

import java.util.LinkedList;
import java.util.List;

public final class Resources {

	private final List<UserResource> _res;

	public Resources() {
		_res = new LinkedList<>();
	}

	public void append(UserResource parameter) {
		_res.add(parameter);
	}

	public List<UserResource> get_res() {
        return _res;
    }

    @Override
	public String toString() {
		return _res.toString();
	}
}