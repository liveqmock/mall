package com.hnfealean.sport.managers.area;

import java.util.List;

import com.hnfealean.sport.model.area.Zone;
import com.hnfealean.sport.pageset.PageModel;

public interface ZoneManager {
	public Zone getZoneById(int id);
	public boolean addZone(Zone zone);
	public PageModel searchZones(int pId,int countryId);
	public boolean updateZone(int id,Zone zone);
	public boolean delete(int id);
	public List getZones(int pId,int countryId);
	public List getTopZones();
}
