package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import classess.Position;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EmployeeController implements Initializable {
	private Connection cnn;
	private String sql;
	private PreparedStatement pstmt;

	@FXML
	private TextField first_name;

	@FXML
	private TextField last_name;

	@FXML
	private TextField email;
	
	@FXML
	private TextField gender;

	@FXML
	private ComboBox<String> departmentSelection;

	@FXML
	private ComboBox<String> genderSelection;

	@FXML
	private ComboBox<Position> positionSelections;

	PositionController positioncrt = new PositionController();

	@FXML
    boolean storeEmployee(ActionEvent event) {
	
    	sql = "INSERT INTO employee (first_name, last_name, gender, email, department_id, position_id) values (?,?,?,?,?,?)";
    	
//		try {
//				pstmt = cnn.prepareStatement(sql);
//				pstmt.setString(1, first_name.getText());
//				pstmt.setString(2, last_name.getText());
//				pstmt.setString(3, genderSelection.getValue());
//				pstmt.setString(4, email.getText());
//				
//				
//
//				if (pstmt.executeUpdate() != 0) {
//					Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//					app_stage.close();
//					return true;
//				}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
		return false;

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female", "Other");
		genderSelection.setItems(genders);

		ObservableList<Position> positions = FXCollections.observableArrayList();
		//positions.addAll(positioncrt.getPosition());
		positionSelections.setItems(positions);

	}

}
