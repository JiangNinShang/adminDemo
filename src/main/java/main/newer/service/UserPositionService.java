package main.newer.service;

import java.util.List;

import main.newer.domain.UserPosition;

public interface UserPositionService {
	public List<UserPosition> getbyuid(int uid);
}
