package controller;



import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class MaintenancePosition {

	public SimpleStringProperty id;
	public SimpleStringProperty position;

	public MaintenancePosition(String id, String position) {
		this.id = new SimpleStringProperty(id);
		this.position = new SimpleStringProperty(position);
	}

	public String getId() {
		return this.id.get();
	}

	public String getPosition() {
		return this.position.get();
	}

	public void setPosition(String position) {
		this.position.set(position);
	}

	public void setId(String id) {
		this.id.set(id);
	}

	@Override
	public String toString() {
		return this.position.get();
	}

}
