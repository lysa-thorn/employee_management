package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController {

	private Connection cnn;
	private String sql;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@FXML
	private BorderPane bp;

	@FXML
	private AnchorPane ap;

	@FXML
	private BorderPane root;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Text errorMessage;

	public MainController() {
		cnn = DBConnection.initDB();
	}

	@FXML
	void login(ActionEvent event) throws IOException {
		String hashtext;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] messageDigest = md.digest(password.getText().getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

		sql = "SELECT * FROM employees WHERE id = " + 1;
		try {

			pstmt = cnn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				if (rs.getString("email").equals(username.getText()) && rs.getString("password").equals(hashtext)) {
					Parent pane = (BorderPane) FXMLLoader.load(getClass().getResource("../view/Employee.fxml"));
					Scene employeePage = new Scene(pane, 800, 550);
					Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					app_stage.setScene(employeePage);
					app_stage.show();
		

				} else {
					errorMessage.setText("Incorrect username or password.");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void logout(ActionEvent event) throws IOException {
		Parent pane = (BorderPane) FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
		Scene employeePage = new Scene(pane, 800, 550);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(employeePage);
		app_stage.show();
	}

	@FXML
	void departmentPressed(ActionEvent event) {
		loadPage("Department");
	}

	@FXML
	void employeePressed(ActionEvent event) {
		bp.setCenter(ap);
	}

	@FXML
	void positionPressed(ActionEvent event) {
		
		loadPage("Position");
		
	}
	
	@FXML
	void createPosition(ActionEvent event) throws IOException {
		Parent pane = (BorderPane) FXMLLoader.load(getClass().getResource("../view/EmployeeCreation.fxml"));
		Scene scene = new Scene(pane, 550, 400);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void loadPage(String page) {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("../view/" + page + ".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bp.setCenter(root);
	}
	
}
