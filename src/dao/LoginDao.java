package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.User;
import common.DBManager;

public class LoginDao {

    public static User validate(String username, String password) {

        Connection con = null;
        PreparedStatement ps = null;
        User user = null;

        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement("select * from golddatabase.anagrafica_ddl where userid=? and password=? ");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setCognome(rs.getString("cognome"));
                user.setNome(rs.getString("nome"));
                user.setId(rs.getLong("idanagrafica_ddl"));
                user.setUsername(rs.getString("userid"));
                user.setEmail(rs.getString("email"));
                user.setTipoUtente(rs.getString("tipo_utente"));
            }
            return user;

        } catch (Exception e) {
            return null;
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                con.close();
            } catch (Exception e) {
            }
        }
    }
}
