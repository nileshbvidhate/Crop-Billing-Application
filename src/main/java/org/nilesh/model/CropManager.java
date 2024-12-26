package org.nilesh.model;

public class CropManager {

	public static int getCropIdByName(String name) {
		if (name.equalsIgnoreCase("Onion")) {
			return 1;
		} else if (name.equalsIgnoreCase("Tomato")) {
			return 2;
		} else if (name.equalsIgnoreCase("Potato")) {
			return 3;
		} else if (name.equalsIgnoreCase("Wheat")) {
			return 4;
		} else if (name.equalsIgnoreCase("Ginger")) {
			return 5;
		} else {
			return -1; // Return -1 for unknown crop
		}
	}

}
