package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;

import common.DBManager;

public class UpdateFileDao {

    public static void insertUploadFile(Long idsituazione_presenze, String url, String campoAllegato) throws Exception {
        
        Connection con = null;
		PreparedStatement ps = null;
        
        try {
             con = DBManager.getConnection();

             ps = con.prepareStatement(
                    "update situazione_presenze set "+campoAllegato+" = '"+url+"' where idsituazione_presenze = "+idsituazione_presenze+" ");

            ps.executeUpdate();

        } catch (Exception e) {
        	e.printStackTrace();
        	throw e;
        } finally {
        	try {ps.close();} catch (Exception e) {}
        	try {con.close();} catch (Exception e) {}
        }
    }
}
