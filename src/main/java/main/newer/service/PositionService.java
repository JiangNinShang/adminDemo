package main.newer.service;

import java.util.List;

import main.newer.domain.Position;

public interface PositionService {
	public List<Position> getbypid(int pid);
}
