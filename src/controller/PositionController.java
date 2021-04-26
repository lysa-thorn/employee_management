package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.DBConnection;
import classess.Position;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

public class PositionController {
	private Stage stage;
	private Connection cnn;
	private String sql;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@FXML
    private TextField position;
	
	@FXML
    private Text errorMessage;
	
	public PositionController() {
		cnn = DBConnection.initDB();
	}

	@FXML
	void createPosition(ActionEvent event) throws IOException {
		Parent pane = (BorderPane) FXMLLoader.load(getClass().getResource("../view/PositionCreation.fxml"));
		Scene scene = new Scene(pane, 450, 300);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	boolean storePosition(ActionEvent event) throws IOException {
		sql = "INSERT INTO positions (name) values (?)";

		try {
			if (!position.getText().equals("")) {
				pstmt = cnn.prepareStatement(sql);
				pstmt.setString(1, position.getText());
				
				if (pstmt.executeUpdate() != 0) {
					Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					app_stage.close();
					return true;
					
				}
			}else{
				errorMessage.setText("Please fill in the position");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
}
