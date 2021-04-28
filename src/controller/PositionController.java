package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.DBConnection;
import classess.Position;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

public class PositionController implements Initializable {
	private Stage stage;
	private Connection cnn;
	private String sql;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@FXML
	private TableView<MaintenancePosition> table;

	@FXML
	private TableColumn col_id;

	@FXML
	private TableColumn action;

	@FXML
	private TableColumn col_position;

	@FXML
	private TextField position;

	@FXML
	private int id;

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
			} else {
				errorMessage.setText("Please fill in the position");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public ObservableList<MaintenancePosition> getPosition() {
		sql = "SELECT * FROM positions";
		List<Position> positions = new ArrayList<Position>();
		try {
			pstmt = cnn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				positions.add(new Position(rs.getInt("id") + "", rs.getString("name")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<MaintenancePosition> test = new ArrayList(positions);
		ObservableList<MaintenancePosition> maintenancePosition = FXCollections.observableArrayList(test);

		return maintenancePosition;
	}

	public void displayData() {
		try {

			ObservableList<MaintenancePosition> data = getPosition();

			col_id.setMinWidth(20);
			col_position.setMinWidth(80);

			// col_id = new TableColumn("Col ID");
			col_id.setCellValueFactory(new PropertyValueFactory<>("id"));

			// col_position = new TableColumn("Col Position");
			col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
			table.setItems(data);
			table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			table.refresh();
		} catch (Exception e) {

		}

	}

	@FXML
	boolean deletePosition(ActionEvent event) {
		System.out.println(table.getSelectionModel().getSelectedItems().get(id));
		sql = "DELETE FROM positions WHERE id = " + table.getSelectionModel().getSelectedItems().get(id);
		try {
			pstmt = cnn.prepareStatement(sql);
			
			if (pstmt.executeUpdate() != 0) {

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		displayData();

	}
}
