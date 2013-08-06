public class Board {

	//The characteers available to play
	public enum Character {
		Jack_Mustard, Kassandra_Scarlett, Diana_White, Jacob_Green, Eleanor_Peacock, Victor_Plum
	}

	public Board() {

	}

	public String getOptions(Player player) {
		// Gets the location of the current players character
		Location location = player.getCharacter().getLocation();

		// Computes options of that location

		// TODO Finish Method
		String options = null;

		return options;
	}
}
