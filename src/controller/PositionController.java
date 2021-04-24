package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.DBConnection;
import classess.Position;


public class PositionController {
	private Connection cnn;
	private String sql;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public PositionController() {
		cnn = DBConnection.initDB();
	}
	
	public ArrayList<Position> findAll() {
		sql = "SELECT * FROM positions";
		ArrayList<Position> positions = new ArrayList<>();
		try {
			pstmt = cnn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				positions.add(new Position(rs.getString("name"))

				);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return positions;
	}
}
