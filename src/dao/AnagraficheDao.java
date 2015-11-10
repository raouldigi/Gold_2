/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.DatoreDiLavoro;
import bean.Dipendente;
import common.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Statement;

import static javassist.CtMethod.ConstParameter.string;

/**
 *
 * @author gminardi
 */
public class AnagraficheDao {

    public static boolean addDatoreLavoro(DatoreDiLavoro datoredilavoro) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(
                    "INSERT INTO anagrafica_ddl (nome, cognome, zona, indirizzo, telefono, cellulare, userid, password, email, tipo_utente) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, datoredilavoro.getNome());
            ps.setString(2, datoredilavoro.getCognome());
            ps.setString(3, datoredilavoro.getZona());
            ps.setString(4, datoredilavoro.getIndirizzo());
            ps.setString(5, datoredilavoro.getTelefono());
            ps.setString(6, datoredilavoro.getCellulare());
            ps.setString(7, datoredilavoro.getUserid());
            ps.setString(8, datoredilavoro.getPassword());
            ps.setString(9, datoredilavoro.getEmail());
            ps.setString(10, datoredilavoro.getTipo_utente());
//            ps.setDate(10, new java.sql.Date(giornata.getData_presenza().getTime()));
            return ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

    public static boolean updateDatoreLavoro(DatoreDiLavoro datoredilavoro) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(
                    "UPDATE anagrafica_ddl SET "
                    + "nome=?, cognome=?, zona=?, indirizzo=?, telefono=?, cellulare=?, userid=?, password=?, email=?, tipo_utente=? WHERE idanagrafica_ddl=?;");
            ps.setString(1, datoredilavoro.getNome());
            ps.setString(2, datoredilavoro.getCognome());
            ps.setString(3, datoredilavoro.getZona());
            ps.setString(4, datoredilavoro.getIndirizzo());
            ps.setString(5, datoredilavoro.getTelefono());
            ps.setString(6, datoredilavoro.getCellulare());
            ps.setString(7, datoredilavoro.getUserid());
            ps.setString(8, datoredilavoro.getPassword());
            ps.setString(9, datoredilavoro.getEmail());
            ps.setString(10, datoredilavoro.getTipo_utente());
            ps.setInt(11, datoredilavoro.getIdanagrafica_ddl());

//            ps.setDate(10, new java.sql.Date(giornata.getData_presenza().getTime()));
            return ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

    public static boolean addDipendente(String nome, String cognome, String indirizzo, String telefono, String cell) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(
                    "INSERT INTO anagrafica_dipe (nome, cognome,  indirizzo, telefono, cellulare) "
                    + "VALUES (?,?,?,?,?)");

            ps.setString(1, nome);
            ps.setString(2, cognome);
            ps.setString(3, indirizzo);
            ps.setString(4, telefono);
            ps.setString(5, cell);
//            ps.setDate(10, new java.sql.Date(giornata.getData_presenza().getTime()));
            return ps.execute();
//            int n=ps.executeUpdate();
//           if(n==0) return false;
//           return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

    public static boolean updateDipendente(String nome, String cognome, String indirizzo, String telefono, String cellulare, Integer idanagrafica_dipe) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(
                    "UPDATE anagrafica_dipe SET "
                    + "nome=?, cognome=?,indirizzo=?, telefono=?, cellulare=? WHERE idanagrafica_dipe=?;");
           
            ps.setString(1, nome);
            ps.setString(2, cognome);
            ps.setString(3, indirizzo);
            ps.setString(4, telefono);
            ps.setString(5, cellulare);
            ps.setInt(6, idanagrafica_dipe);
            
            return ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

    
    
    public static boolean addContratto(String nomedip, String cognomedip, String nomeddl, String cognomeddl, String indirizzolav,
            String cap, String city, Date assunzione, String riposo, String pduff, String tipo, String cat, String liv) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
//            int id_ddl = 1;
//            int id_dipe = 1;
            int id_city = -1;
            int id_ddl = -1;
            int id_dipe = -1;
            int id_tipoc = -1;
            int id_cat = -1;
            int id_liv = -1;
            id_city = getIdPatrono(city);
            id_ddl = getIdDdl(nomeddl, cognomeddl);
            id_dipe = getIdDipe(nomedip, cognomedip);
            id_tipoc = getIdTipoContratto(tipo);
            id_cat = getIdCategoria(cat);
            id_liv = getIdLivello(liv);

            //il metodo ps.execute() resituisce true se si verifica qualche errore quindi
            //ho invertito il controllo inoltre ho gestito le date dal metodo execute
            //della ContrattoAction.
            if (id_city < 0) {
                return true;
            }
            con = DBManager.getConnection();
            ps = con.prepareStatement(
                    "INSERT INTO contratto (indirizzo_lavoro, data_assunz, giorno_riposo, prospetto_duff, anagrafica_ddl_idanagrafica_ddl, anagrafica_dipe_idanagrafica_dipe,"
                    + "tab_tipo_contratto_idtab_tipo_contratto,tab_categoria_idtab_categoria, tab_livello_idtab_livello,tab_citta_patrono_idtab_citta_patrono) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, indirizzolav);
            ps.setDate(2, new java.sql.Date(assunzione.getTime()));
            //sto passando cessazione=null dalla action
//            if (cessazione == null) {
//                ps.setNull(3, java.sql.Types.DATE);
//            } else {
//                ps.setDate(3, new java.sql.Date(cessazione.getTime()));
//            }
            ps.setString(3, riposo);
            ps.setString(4, pduff);
            ps.setInt(5, id_ddl);
            ps.setInt(6, id_dipe);
            ps.setInt(7, id_tipoc);//tipo contratto
            ps.setInt(8, id_cat);//categoria
            ps.setInt(9, id_liv);//livello
            ps.setInt(10, id_city);

            return ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
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

    public static boolean updateContratto(String indirizzolav, String cap, String city, Date assunzione, Date data_cessaz, String riposo, String pduff, String tipo, String cat, String liv, String idcontratto) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
//            int id_ddl = 1;
//            int id_dipe = 1;
            int id_city = -1;
            int id_tipoc = -1;
            int id_cat = -1;
            int id_liv = -1;
            id_city = getIdPatrono(city);
            id_tipoc = getIdTipoContratto(tipo);
            id_cat = getIdCategoria(cat);
            id_liv = getIdLivello(liv);

            //il metodo ps.execute() resituisce true se si verifica qualche errore quindi
            //ho invertito il controllo inoltre ho gestito le date dal metodo execute
            //della ContrattoAction.
            if (id_city < 0) {
                return true;
            }
            con = DBManager.getConnection();
            ps = con.prepareStatement(
                    "UPDATE contratto SET"
                    + " indirizzo_lavoro=?, data_assunz=?, data_cessaz=?, giorno_riposo=?, prospetto_duff=?, "
                    + "tab_tipo_contratto_idtab_tipo_contratto=?,tab_categoria_idtab_categoria=?, tab_livello_idtab_livello=?,tab_citta_patrono_idtab_citta_patrono=? "
                    + "WHERE idcontratto=?");

            ps.setString(1, indirizzolav);
            ps.setDate(2, new java.sql.Date(assunzione.getTime()));
            //sto passando cessazione=null dalla action
            if (data_cessaz == null) {
                ps.setNull(3, java.sql.Types.DATE);
            } else {
                ps.setDate(3, new java.sql.Date(data_cessaz.getTime()));
            }
            ps.setString(4, riposo);
            ps.setString(5, pduff);
            ps.setInt(6, id_tipoc);//tipo contratto
            ps.setInt(7, id_cat);//categoria
            ps.setInt(8, id_liv);//livello
            ps.setInt(9, id_city);
            ps.setInt(10, Integer.parseInt(idcontratto));

            return ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
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

    private static int getIdPatrono(String c) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            int id = -1;
            con = DBManager.getConnection();
            ps = con.prepareStatement("select * from tab_citta_patrono where citta_lavoro = ?");
            ps.setString(1, c);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
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

    public ArrayList<String> getCitta() {
        Connection con = null;
        Statement ps = null;
        ResultSet rs = null;
        ArrayList<String> listaCitta = new ArrayList<String>();
        try {
            con = DBManager.getConnection();
            String query = "select citta_lavoro from tab_citta_patrono";
            ps = (Statement) con.createStatement();
            rs = ps.executeQuery(query);
            while (rs.next()) {
            	String citta;
            	citta = rs.getString(1);
            	listaCitta.add(citta);
            }
            return listaCitta;
        } catch (Exception e) {
            e.printStackTrace();
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

    private static int getIdDdl(String nome, String cognome) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            int id = -1;
            con = DBManager.getConnection();
            ps = con.prepareStatement("SELECT idanagrafica_ddl FROM anagrafica_ddl WHERE nome like ? AND cognome =?");
            ps.setString(1, '%' + nome + '%');
            ps.setString(2, cognome);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
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

    private static int getIdDipe(String nome, String cognome) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            int id = -1;
            con = DBManager.getConnection();
            ps = con.prepareStatement("SELECT idanagrafica_dipe FROM anagrafica_dipe WHERE nome like ? AND cognome =?");
            ps.setString(1, '%' + nome + '%');
            ps.setString(2, cognome);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
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

    private static int getIdTipoContratto(String c) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            int id = -1;
            con = DBManager.getConnection();
            ps = con.prepareStatement("SELECT idtab_tipo_contratto FROM tab_tipo_contratto WHERE tipo_contratto =?");
            ps.setString(1, c);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
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

    private static int getIdCategoria(String c) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            int id = -1;
            con = DBManager.getConnection();
            ps = con.prepareStatement("SELECT idtab_categoria FROM tab_categoria WHERE categoria=?");
            ps.setString(1, c);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
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

    private static int getIdLivello(String c) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            int id = -1;
            con = DBManager.getConnection();
            ps = con.prepareStatement("SELECT idtab_livello FROM tab_livello WHERE livello = ?");
            ps.setString(1, c);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
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
