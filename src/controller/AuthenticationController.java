package controller;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AuthenticationController{
	
	private Connection cnn;
	private String sql;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public AuthenticationController() {
		cnn = DBConnection.initDB();
	}
	@FXML
	private BorderPane root;
	
	@FXML
    private TextField username;
	
	@FXML
    private PasswordField password;
	
	 @FXML
	 private Text errorMessage;

	
	@FXML
	void login(ActionEvent event) throws IOException {
		String hashtext;
		try {
			   MessageDigest md = MessageDigest.getInstance("SHA-1");

		        // digest() method is called
		        // to calculate message digest of the input string
		        // returned as array of byte
		        byte[] messageDigest = md.digest(password.getText().getBytes());

		        // Convert byte array into representation
		        BigInteger no = new BigInteger(1, messageDigest);

		        // Convert message digest into hex value
		        hashtext = no.toString(16);

		        // Add preceding 0s to make it 32 bit
		        while (hashtext.length() < 32) {
		            hashtext = "0" + hashtext;
		        }
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
		
		sql = "SELECT * FROM employees WHERE id = " + 1;
		try {

			pstmt = cnn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				if(rs.getString("email").equals(username.getText()) && rs.getString("password").equals(hashtext)) {
					Parent pane = (BorderPane)FXMLLoader.load(getClass().getResource("../view/Employee.fxml"));
					Scene employeePage = new Scene(pane, 800, 550);
					Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					app_stage.setScene(employeePage);
					app_stage.show();
				}else {
					errorMessage.setText("Incorrect username or password.");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	 @FXML
	 void logout(ActionEvent event) throws IOException {
		 Parent pane = (BorderPane)FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			Scene employeePage = new Scene(pane, 800, 550);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(employeePage);
			app_stage.show();
	 }
}
