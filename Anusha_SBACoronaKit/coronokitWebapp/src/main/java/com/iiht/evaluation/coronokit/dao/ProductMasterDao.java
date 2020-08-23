package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.ProductMaster;

public class ProductMasterDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public static final String INST_PRODUCT_QRY = "INSERT INTO products (productid,productname,price,description) VALUES (?,?,?,?)";
	public static final String UPDT_PRODUCT_QRY = "UPDATE products set productname=?,price=?,description=? WHERE productid=?";
	public static final String DLT_PRODUCT_QRY = "DELETE FROM products WHERE productid=?";
	public static final String GET_PRODUCT_DTLS_QRY = "SELECT productid,productname,price,description FROM products WHERE productid=?";
	public static final String GET_ALL_PRODUCTS_QRY = "SELECT productid,productname,price,description FROM products";
	public static final String MAX_PRODUCTID_QRY = "SELECT MAX(productid) FROM products;";

	public ProductMasterDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	public List<ProductMaster> getAllProducts() throws Exception {
		List<ProductMaster> productList = new ArrayList<ProductMaster>();
		try {
			this.connect();
			PreparedStatement ps = jdbcConnection.prepareStatement(GET_ALL_PRODUCTS_QRY);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductMaster product = new ProductMaster(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				System.out.println(product.toString());
				productList.add(product);
			}

		} catch (SQLException e) {
			this.disconnect();
			throw new Exception("Failure in fetching list of products");
		}
		return productList;
	}

	public int getNextProductId() throws Exception {
		int nextProductId = 1001;
		try {
			this.connect();
			PreparedStatement ps = jdbcConnection.prepareStatement(MAX_PRODUCTID_QRY);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				nextProductId = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			this.disconnect();
			throw new Exception("Failure in fetching next Product Id");
		}
		return nextProductId;
	}

	public boolean addProduct(ProductMaster product) throws Exception {
		boolean isAdded = false;
		if (product != null) {
			try {
				this.connect();
				PreparedStatement ps = jdbcConnection.prepareStatement(INST_PRODUCT_QRY);
				ps.setInt(1, product.getId());
				ps.setString(2, product.getProductName());
				ps.setString(3, product.getCost());
				ps.setString(4, product.getProductDescription());
				isAdded = ps.executeUpdate() > 0;

			} catch (SQLException e) {
				throw new Exception("Failure in adding product");
			}
		}
		return isAdded;
	}

	public boolean deleteProduct(int productId) throws Exception {
		boolean isDeleted = false;
		try {
			this.connect();
			PreparedStatement ps = jdbcConnection.prepareStatement(DLT_PRODUCT_QRY);
			ps.setInt(1, productId);
			isDeleted = ps.executeUpdate() > 0;

		} catch (SQLException e) {
			throw new Exception("Failure in deleting product - " + productId);
		}
		return isDeleted;
	}

	public ProductMaster getProductById(int productId) throws Exception {
		ProductMaster product = null;
		try {
			this.connect();
			PreparedStatement ps = jdbcConnection.prepareStatement(GET_PRODUCT_DTLS_QRY);
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product = new ProductMaster(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}

		} catch (SQLException e) {
			throw new Exception("Failure in fetching details of products " + productId);
		}
		return product;
	}

	public boolean updateProduct(ProductMaster product) throws Exception {
		boolean isUpdated = false;
		if (product != null) {
			try {
				this.connect();
				PreparedStatement ps = jdbcConnection.prepareStatement(UPDT_PRODUCT_QRY);
				ps.setString(1, product.getProductName());
				ps.setString(2, product.getCost());
				ps.setString(3, product.getProductDescription());
				ps.setInt(4, product.getId());
				isUpdated = ps.executeUpdate() > 0;

			} catch (SQLException e) {
				throw new Exception("Failure in updating product");
			}
		}
		return isUpdated;

	}

//	  

}