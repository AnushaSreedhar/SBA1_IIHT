package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.iiht.evaluation.coronokit.model.KitDetail;

public class KitDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public static final String INST_PRODUCT_QRY = "INSERT INTO kit (kitid,coronaKitId,productId,quantity,amount) VALUES (?,?,?,?,?)";
	public static final String UPDT_PRODUCT_QRY = "UPDATE products set productname=?,price=?,description=? WHERE productid=?";
	public static final String MAX_CORONAKITID_QRY = "SELECT MAX(coronaKitId) FROM kit;";
	public static final String MAX_KITID_QRY = "SELECT MAX(kitid) FROM kit;";

	public KitDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public Integer getNextCoronaKitId() throws Exception {
		int nextCoronaKitId = 1;
		try {
			this.connect();
			PreparedStatement ps = jdbcConnection.prepareStatement(MAX_CORONAKITID_QRY);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				nextCoronaKitId = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			this.disconnect();
			throw new Exception("Could not fetch corona id");
		}
		return nextCoronaKitId;
	}

	public int getNextKitId() throws Exception {
		int nextKitId = 1;
		try {
			this.connect();
			PreparedStatement ps = jdbcConnection.prepareStatement(MAX_KITID_QRY);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				nextKitId = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			this.disconnect();
			throw new Exception("Could not fetch the Kit Id");
		}
		return nextKitId;
	}

	public boolean addKit(KitDetail kitDtails) throws Exception {
		boolean isKitAdded = false;
		if (kitDtails != null) {
			try {
				this.connect();
				PreparedStatement ps = jdbcConnection.prepareStatement(INST_PRODUCT_QRY);
				ps.setInt(1, kitDtails.getId());
				ps.setInt(2, kitDtails.getCoronaKitId());
				ps.setInt(3, kitDtails.getProductId());
				ps.setInt(4, kitDtails.getQuantity());
				ps.setDouble(5, kitDtails.getAmount());
				isKitAdded = ps.executeUpdate() > 0;

			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception("Could not add product");
			}
		}
		return isKitAdded;
	}

}