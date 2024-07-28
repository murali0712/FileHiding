package dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.MyConnection;
import model.Data;

public class DataDAO {
	
	public static List<Data> getAllFiles(String email)throws SQLException{
		
		Connection conn= MyConnection.getConnection();
		PreparedStatement ps= conn.prepareStatement("select * from data where email=?");
		ps.setString(1,email);
		ResultSet rs= ps.executeQuery();
		List<Data> files =new ArrayList<>();
		
		while(rs.next()) {
			int id= rs.getInt(1);
			String name=  rs.getString(2);
			//String email=  rs.getString(3);
			String path=  rs.getString(3);
			files.add(new Data(id,name,path,email));
		}
	
		return files;
		
	}
	
	public static int hideFile(Data file)throws SQLException {
		Connection conn= MyConnection.getConnection();
		PreparedStatement ps= conn.prepareStatement("insert into data(fname,path,email,bin_data) values (?,?,?,?)");
		ps.setString(1,file.getfileName());
		ps.setString(2, file.getPath());
		ps.setString(3,file.getEmail() );
		File f= new File(file.getPath());
		int ans=0;
		try {
		FileReader fr= new FileReader(f);
		ps.setCharacterStream(4,fr,f.length());
		 ans= ps.executeUpdate();
		fr.close();
		f.delete();
		
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return ans;
		
	}
	
	
	public static void unhide(int id)throws SQLException, IOException{
		Connection conn= MyConnection.getConnection();
		PreparedStatement ps= conn.prepareStatement("Select path,bin_data from data where id= ?");
		ps.setInt(1, id);
		ResultSet rs= ps.executeQuery();
		rs.next();
		String path= rs.getString("path");
		Clob c= rs.getClob("bin_data");
		Reader r= c.getCharacterStream();
		FileWriter fw= new FileWriter(path);
		int i;
		while((i=r.read()) !=-1) {
			fw.write((char)i);
		}
		fw.close();
		ps= conn.prepareStatement("delete from data where id=?");
		ps.setInt(1,id);
		ps.executeUpdate();
		System.out.println("Successfully unhidden");
	}
 }
