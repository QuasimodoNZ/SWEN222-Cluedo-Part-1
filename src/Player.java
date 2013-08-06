public class Player {
	private Character character;
	
	public Player(String character){
		this.character = new Character(character);
	}
	
	public Character getCharacter(){
		return character;
	}
}
