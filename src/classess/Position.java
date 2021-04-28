package classess;

import javafx.scene.control.Button;

public class Position {
	
	public String id;
	public String position;

	public Position(String id,String position){
		this.id = id;
		this.position = position;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		return id;
	}
	
	
	
}
