import java.util.LinkedList;

import java.util.List;

public class Door {
	private String name;
	private List<Location> firstList;
	private List<Location> secondList;

	public Door(String n, List<Location> fl, List<Location> sl) {
		name = n;
		firstList = fl;
		for (Location l : firstList)
			l.addDoor(this);

		secondList = sl;
		for (Location l : secondList)
			l.addDoor(this);
	}

	/**
	 * Gets the first list of doors that this door is part of, there is two
	 * lists so that a bipartite relationship can be simulated between the entry
	 * and exit locations.
	 * 
	 * @return List<Location>
	 */
	public List<Location> getFirstList() {
		return firstList;
	}

	/**
	 * Gets the second list of doors that this door is part of, there is two
	 * lists so that a bipartite relationship can be simulated between the entry
	 * and exit locations.
	 * 
	 * @return List<Location>
	 */
	public List<Location> getSecondList() {
		return secondList;
	}

	/**
	 * returns the name of the door
	 */
	@Override
	public String toString() {
		return name;
	}
}
