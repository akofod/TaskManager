package util;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DataParser {
	
	private static ArrayList<String> sqlList = new ArrayList<String>();
	private static String schemaName = "";
	static String jdbcURL = "";
	static String username = "";
	static String password = "";
	static Connection conn = null;
	
	public static void main(String args[]) {
		try {
			File xmlFile = new File("WebContent/XML/data.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			doc.getDocumentElement().normalize();
			
			createSchema(doc.getDocumentElement().getAttribute("name"));
			
			NodeList url = doc.getElementsByTagName("jdbcURL");
			jdbcURL = url.item(0).getTextContent();
			
			NodeList un = doc.getElementsByTagName("username");
			username = un.item(0).getTextContent();
			
			NodeList pw = doc.getElementsByTagName("password");
			password = pw.item(0).getTextContent();
			
			NodeList tables = doc.getElementsByTagName("table");
			for (int i = 0; i < tables.getLength(); i++) {
				Element eElement = null;
				Node table = tables.item(i);
				ArrayList<String> cStrings = new ArrayList<String>();
		 
				if (table.getNodeType() == Node.ELEMENT_NODE) {		 
					NodeList columns = table.getChildNodes();
					eElement = (Element) table;
					System.out.println(eElement.getAttribute("name"));
					
					for (int j = 0; j < columns.getLength(); j++) {
						Node column = columns.item(j);
						if (column != null) {
							String sql = createColumnString(column);
							if (sql != null) {
								cStrings.add(sql);
							}
						}
					}
				}
				
				createTable(eElement.getAttribute("name"), cStrings);
			}
			
			NodeList products = doc.getElementsByTagName("product");
			for (int i = 0; i < products.getLength(); i++) {
				Node product = products.item(i);
				sqlList.add(insertProduct(product));
			}
			
			runBatchSQL(sqlList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void createSchema(String schema) {
		schemaName = schema;
		String drop= "DROP SCHEMA IF EXISTS " + schema;
		String create = "CREATE SCHEMA " + schema;
		sqlList.add(drop);
		sqlList.add(create);
	}
	
	private static void createTable(String tableName, ArrayList<String> columns) {
		StringBuilder sb = new StringBuilder();
		sb.append("Create Table " + schemaName + "." + tableName + " (");
			for (int i = 0; i < columns.size(); i++) {
				if(columns.get(i) != null) {
					sb.append(columns.get(i));
					if (i != (columns.size() - 1)) {
						sb.append(", ");
					}
				}
			}
		sb.append(")");
		sqlList.add(sb.toString());
	}
	
	private static String createColumnString(Node cNode) {
		
		StringBuilder sb = new StringBuilder();
		
		if (cNode.getNodeType() == Node.ELEMENT_NODE && cNode != null){
			Element eElement = (Element) cNode;
			sb.append(eElement.getAttribute("name") + " ");
			
			if (eElement.getAttribute("type").equalsIgnoreCase("varchar")) {
				sb.append("VARCHAR");
				sb.append("(" + eElement.getAttribute("length") + ")");
			}
			else {
				sb.append(eElement.getAttribute("type"));
				if (eElement.hasAttribute("autoIncrement")) {
					sb.append(" AUTO_INCREMENT, PRIMARY KEY (" + eElement.getAttribute("name") + ")");
				}
			}
			return sb.toString();
		}
		return null;
	}
	
	private static String insertProduct(Node cNode) {
		StringBuilder sb = new StringBuilder();
		Element eElement = (Element) cNode;
		String name = eElement.getAttribute("name");
		String supplier = eElement.getAttribute("supplier");
		int qty = Integer.parseInt(eElement.getAttribute("quantity"));
		double cost = Double.parseDouble(eElement.getAttribute("cost"));
		double price = Double.parseDouble(eElement.getAttribute("price"));
		
		sb.append("insert into " + schemaName + ".product (name, Supplier, StockQTY, Price, Cost) " +
				"VALUES ('" + name + "', '" + supplier + "', " + qty + ", " + price + ", " + cost + ")");
		
		return sb.toString();
	}
	
	private static void runBatchSQL(ArrayList<String> sqlList) {
		openConnection();
		try {
			Statement stmt = conn.createStatement();
			System.out.println(jdbcURL + " : " + username + " : " + password);
			for(String sql : sqlList) {
				System.out.println(sql);
			}
			
			for (String sql : sqlList) {
				stmt.addBatch(sql);
			}
			
			stmt.executeBatch();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void openConnection() {
		try {
			Driver myDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(myDriver);
			conn = DriverManager.getConnection(jdbcURL, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
