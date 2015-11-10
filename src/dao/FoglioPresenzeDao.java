package dao;

import bean.FoglioPresenze;
import bean.Giornata;
import bean.InfoListaContratti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import com.mysql.jdbc.Statement;

import common.DBManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FoglioPresenzeDao {

    public static Collection<InfoListaContratti> getFoglioRiepilogativo(String nome, String cognome, int idContratto, int anno) throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        InfoListaContratti foglio = new InfoListaContratti();
        ArrayList<InfoListaContratti> lista = new ArrayList<InfoListaContratti>();
        try {
            con = DBManager.getConnection();
            String query = "select ddl.cognome, ddl.nome, d.nome as 'nome_dip', d.cognome as 'cognome_dip', c.data_assunz, s.data_conferma_acli, s.mese, c.idcontratto from golddatabase.contratto c"
                    + " inner join golddatabase.anagrafica_ddl ddl on c.anagrafica_ddl_idanagrafica_ddl=ddl.idanagrafica_ddl "
                    + " inner join golddatabase.anagrafica_dipe d on c.anagrafica_dipe_idanagrafica_dipe=d.idanagrafica_dipe "
                    + " inner join golddatabase.situazione_presenze s on c.idcontratto=s.fk_contratto"
                    + " where s.data_conferma_acli is not null and s.anno='" + Integer.toString(anno) + "' and ((ddl.cognome='" + cognome + "' and ddl.nome like '%" + nome + "%') or (c.idcontratto='" + Integer.toString(idContratto) + "'))";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                foglio = new InfoListaContratti();
                foglio.setCognome(rs.getString("cognome"));
                foglio.setNome(rs.getString("nome"));
                foglio.setCognome_dip(rs.getString("cognome_dip"));
                foglio.setNomedip(rs.getString("nome_dip"));

                try {
                    Date date = formatter.parse(rs.getString("data_assunz"));
                    foglio.setData_assunzione(formatter2.format(date));
                } catch (ParseException e) {
                }

                try {
                    Date date = formatter.parse(rs.getString("data_conferma_acli"));
                    foglio.setData_confacli(formatter2.format(date));
                } catch (ParseException e) {
                }

                foglio.setAnno(anno);
                foglio.setMese(rs.getInt("mese"));

                lista.add(foglio);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                con.close();
            } catch (Exception e) {
            }
        }
        return lista;
    }

    public static Collection<String> getFestivitaByContratto(Long idContratto) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> festivita = new ArrayList<String>();

        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(" select CONCAT(giorno,'_', mese) from golddatabase.contratto c  join golddatabase.tab_citta_patrono p "
                    + " on c.tab_citta_patrono_idtab_citta_patrono=p.idtab_citta_patrono where c.idcontratto=" + idContratto + " ");
            rs = ps.executeQuery();
            while (rs.next()) {
                festivita.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                con.close();
            } catch (Exception e) {
            }
        }
        return festivita;

    }

    public static Collection<String> getFestivitaNazionali() throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> festivita = new ArrayList<String>();

        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(" select CONCAT(giorno,'_', mese) from golddatabase.festivita_nazionali ");
            rs = ps.executeQuery();
            while (rs.next()) {
                festivita.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                con.close();
            } catch (Exception e) {
            }
        }
        return festivita;

    }

    public static Long getNexIdFogliPresenze() throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long id = null;
        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(" select max(f.idfogli_presenze)+1 from golddatabase.foglio_presenze f ");
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                con.close();
            } catch (Exception e) {
            }
        }
        return id;
    }

    public static Long insertSituazionePresenze(Long idContratto, int mese, int anno) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        Long idSituazionePresenze = null;
        try {
            con = DBManager.getConnection();

            String query = "INSERT INTO golddatabase.situazione_presenze "
                    + " (mese,anno,stato,data_conferma_ddl,data_conferma_acli,cud,note_busta,osservazioni,allegato1,allegato2,allegato3,allegato4,allegato5,fk_contratto)"
                    + " VALUES (?,?,?,null,null,null,null,null,null,null,null,null,null,?) ";

            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, mese);
            ps.setInt(2, anno);
            ps.setString(3, "lavorazione");
            ps.setLong(4, idContratto);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idSituazionePresenze = rs.getLong(1);
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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
        return idSituazionePresenze;
    }

    public static void updateFerieMalattie(Long id, String beginFerie, String endFerie, String beginMal, String endMal, String beginAss, String endAss, Integer mese, Integer anno) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;

        try {
            con = DBManager.getConnection();

            if (id != null && mese != null && anno != null) {
                if (beginFerie != null && endFerie != null && !beginFerie.isEmpty() && !endFerie.isEmpty()) {
                    //FERIE
                    ps = con.prepareStatement(
                            " update golddatabase.foglio_presenze set ferie = 'F' where data_presenza BETWEEN  ? and ? and situazione_presenze_idsituazione_presenze = ? ");

                    ps.setString(1, anno + "-" + mese + "-" + beginFerie);
                    ps.setString(2, anno + "-" + mese + "-" + endFerie);
                    ps.setLong(3, id);

                    ps.executeUpdate();
                }

                if (beginMal != null && endMal != null && !beginMal.isEmpty() && !endMal.isEmpty()) {
                    //MALATTIE
                    ps2 = con.prepareStatement(
                            " update golddatabase.foglio_presenze set malattia = 'M' where data_presenza BETWEEN  ? and ? and situazione_presenze_idsituazione_presenze = ? ");

                    ps2.setString(1, anno + "-" + mese + "-" + beginMal);
                    ps2.setString(2, anno + "-" + mese + "-" + endMal);
                    ps2.setLong(3, id);

                    ps2.executeUpdate();
                }

//                **********************************
                if (beginAss != null && endAss != null && !beginAss.isEmpty() && !endAss.isEmpty()) {
                    //MALATTIE
                    ps3 = con.prepareStatement(
                            " update golddatabase.foglio_presenze set assenza = 'A' where data_presenza BETWEEN  ? and ? and situazione_presenze_idsituazione_presenze = ? ");

                    ps3.setString(1, anno + "-" + mese + "-" + beginAss);
                    ps3.setString(2, anno + "-" + mese + "-" + endAss);
                    ps3.setLong(3, id);

                    ps3.executeUpdate();
                }
//                **********************************
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                ps2.close();
            } catch (Exception e) {
            }
            try {
                ps3.close();
            } catch (Exception e) {
            }
            try {
                con.close();
            } catch (Exception e) {
            }
        }
    }

    public static void aggiornaDataConfermaDDL(Long idSituazionePresenze) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(" update golddatabase.situazione_presenze set data_conferma_ddl= current_date() where idsituazione_presenze = " + idSituazionePresenze + " ");

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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

    public static void aggiornaDataConfermaAcli(Long idSituazionePresenze) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(" update golddatabase.situazione_presenze set data_conferma_acli= current_date() where idsituazione_presenze = " + idSituazionePresenze + " ");

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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

    public static void aggiornaNoteFoglio(Long id, String noteBusta, String osservazioni) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(
                    " update golddatabase.situazione_presenze set note_busta='" + noteBusta + "', osservazioni='" + osservazioni + "' "
                    + " where idsituazione_presenze = " + id + " ");

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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

    public static void aggiornaStatoFoglio(Long id, String stato) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(
                    " update golddatabase.situazione_presenze set stato='" + stato + "' "
                    + " where idsituazione_presenze = " + id + " ");

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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

    public static FoglioPresenze getFoglioPresenze(int idContratto, int anno, int mese) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        FoglioPresenze foglio = new FoglioPresenze();
        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(
                    " select distinct s.*, c.idcontratto from golddatabase.contratto c "
                    + " join golddatabase.foglio_presenze f on c.idcontratto = f.contratto_idcontratto "
                    + " join golddatabase.situazione_presenze s on f.situazione_presenze_idsituazione_presenze=s.idsituazione_presenze"
                    + " where anno=" + anno + " and mese=" + mese + " and c.idcontratto=" + idContratto + " ");

            rs = ps.executeQuery();
            if (rs.next()) {
                foglio.setIdSituazione_presenze(rs.getLong("idsituazione_presenze"));
                foglio.setAnno(rs.getInt("anno"));
                foglio.setCud(rs.getInt("cud"));
                foglio.setData_conferma_acli(rs.getDate("data_conferma_acli"));
                foglio.setData_conferma_ddl(rs.getDate("data_conferma_ddl"));
                foglio.setMese(rs.getInt("mese"));
                foglio.setOsservazioni(rs.getString("osservazioni"));
                foglio.setNoteBusta(rs.getString("note_busta"));
                foglio.setStato(rs.getString("stato"));
                foglio.setIdcontratto(rs.getLong("idcontratto"));
                foglio.setAllegati(new String[]{rs.getString("allegato1"), rs.getString("allegato2"), rs.getString("allegato3")});

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                con.close();
            } catch (Exception e) {
            }
        }
        return foglio;
    }

    public static FoglioPresenze getFoglioPresenzeById(Long id) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        FoglioPresenze foglio = new FoglioPresenze();
        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(
                    " select distinct s.*, c.idcontratto from golddatabase.contratto c "
                    + " join golddatabase.foglio_presenze f on c.idcontratto = f.contratto_idcontratto "
                    + " join golddatabase.situazione_presenze s on f.situazione_presenze_idsituazione_presenze=s.idsituazione_presenze"
                    + " where idsituazione_presenze=" + id + "  ");

            rs = ps.executeQuery();
            if (rs.next()) {
                foglio.setIdSituazione_presenze(rs.getLong("idsituazione_presenze"));
                foglio.setAnno(rs.getInt("anno"));
                foglio.setCud(rs.getInt("cud"));
                foglio.setData_conferma_acli(rs.getDate("data_conferma_acli"));
                foglio.setData_conferma_ddl(rs.getDate("data_conferma_ddl"));
                foglio.setMese(rs.getInt("mese"));
                foglio.setOsservazioni(rs.getString("osservazioni"));
                foglio.setNoteBusta(rs.getString("note_busta"));
                foglio.setStato(rs.getString("stato"));
                foglio.setIdcontratto(rs.getLong("idcontratto"));
                foglio.setAllegati(new String[]{rs.getString("allegato1"), rs.getString("allegato2"), rs.getString("allegato3")});

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                con.close();
            } catch (Exception e) {
            }
        }
        return foglio;
    }

    public static Collection<Giornata> getGiornateFoglioPresenze(int idContratto, int anno, int mese) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Giornata giornata;
        LinkedList<Giornata> list = new LinkedList<Giornata>();
        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(
                    " select c.idcontratto, idfogli_presenze, lav_ord, lav_straord, ferie, perm_retrib, perm_non_retrib, malattia, assenza, situazione_presenze_idsituazione_presenze "
                    + " from golddatabase.contratto c "
                    + " join golddatabase.foglio_presenze f on f.contratto_idcontratto = c.idcontratto"
                    + " join golddatabase.situazione_presenze s on f.situazione_presenze_idsituazione_presenze=s.idsituazione_presenze"
                    + " where anno=? and mese=? and c.idcontratto=? "
                    + " order by idfogli_presenze ");

            ps.setInt(1, anno);
            ps.setInt(2, mese);
            ps.setInt(3, idContratto);

            rs = ps.executeQuery();
            while (rs.next()) {
                giornata = new Giornata();
                giornata.setIdfogli_presenze(rs.getLong("idfogli_presenze"));
                giornata.setLav_ord(rs.getString("lav_ord"));
                giornata.setLav_straord(rs.getString("lav_straord"));
                giornata.setFerie(rs.getString("ferie"));
                giornata.setPerm_retrib(rs.getString("perm_retrib"));
                giornata.setPerm_non_retrib(rs.getString("perm_non_retrib"));
                giornata.setMalattia(rs.getString("malattia"));
                giornata.setAssenza(rs.getString("assenza"));
                giornata.setIdsituazione_presenze(rs.getLong("situazione_presenze_idsituazione_presenze"));
                giornata.setIdcontratto(rs.getLong("idcontratto"));

                list.add(giornata);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                con.close();
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static Collection<Giornata> getGiornateByIdContratto(int idContratto) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Giornata giornata;
        LinkedList<Giornata> list = new LinkedList<Giornata>();
        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(
                    " select c.idcontratto, idfogli_presenze, lav_ord, lav_straord, ferie, perm_retrib, perm_non_retrib, "
                    + " malattia, assenza, situazione_presenze_idsituazione_presenze from golddatabase.contratto c "
                    + " left join golddatabase.foglio_presenze f "
                    + " on f.contratto_idcontratto = c.idcontratto "
                    + " where idcontratto = ? order by idfogli_presenze ");

            ps.setInt(1, idContratto);
            rs = ps.executeQuery();
            while (rs.next()) {
                giornata = new Giornata();
                giornata.setIdfogli_presenze(rs.getLong("idfogli_presenze"));
                giornata.setLav_ord(rs.getString("lav_ord"));
                giornata.setLav_straord(rs.getString("lav_straord"));
                giornata.setFerie(rs.getString("ferie"));
                giornata.setPerm_retrib(rs.getString("perm_retrib"));
                giornata.setPerm_non_retrib(rs.getString("perm_non_retrib"));
                giornata.setMalattia(rs.getString("malattia"));
                giornata.setAssenza(rs.getString("assenza"));
                giornata.setIdsituazione_presenze(rs.getLong("situazione_presenze_idsituazione_presenze"));
                giornata.setIdcontratto(rs.getLong("idcontratto"));

                list.add(giornata);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                con.close();
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static ArrayList<Long> getListIdSituazionePresenze(int mese, int anno) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Long> lista = new ArrayList<Long>();
        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(
                    " select idsituazione_presenze from golddatabase.situazione_presenze "
                    + " where mese = ? and anno = ? ");

            ps.setInt(1, mese);
            ps.setInt(2, anno);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getLong(1));
            }
            return lista;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
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

    public static void addGiornata(Giornata giornata) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(
                    "insert into foglio_presenze(lav_ord, lav_straord, ferie, perm_retrib, "
                    + " perm_non_retrib, malattia, assenza, contratto_idcontratto, situazione_presenze_idsituazione_presenze, data_presenza) "
                    + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

//          ps.setDate(3, new java.sql.Date(user.getDob().getTime()));
//            ps.setLong(1, giornata.getIdfogli_presenze());
            ps.setString(1, giornata.getLav_ord());
            ps.setString(2, giornata.getLav_straord());
            ps.setString(3, giornata.getFerie());
            ps.setString(4, giornata.getPerm_retrib());
            ps.setString(5, giornata.getPerm_non_retrib());
            ps.setString(6, giornata.getMalattia());
            ps.setString(7, giornata.getAssenza());
            ps.setLong(8, giornata.getIdcontratto());
            ps.setLong(9, giornata.getIdsituazione_presenze());
            ps.setDate(10, new java.sql.Date(giornata.getData_presenza().getTime()));
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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

    public static void insertOrUpdateGiornata(Giornata giornata) throws Exception {
        try {
//            if (getGiornata(giornata.getIdfogli_presenze()) == null) {
//                addGiornata(giornata);
//            } else {
            updateGiornataPresenza(giornata);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void updateGiornataPresenza(Giornata giornata) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(
                    " UPDATE foglio_presenze SET lav_ord=?, lav_straord=?, ferie=?, perm_retrib=?, "
                    + " perm_non_retrib=?, malattia=?, assenza=? WHERE idfogli_presenze=? ");

            ps.setString(1, giornata.getLav_ord());
            ps.setString(2, giornata.getLav_straord());
            ps.setString(3, giornata.getFerie());
            ps.setString(4, giornata.getPerm_retrib());
            ps.setString(5, giornata.getPerm_non_retrib());
            ps.setString(6, giornata.getMalattia());
            ps.setString(7, giornata.getAssenza());

            ps.setLong(8, giornata.getIdfogli_presenze());
//            ps.setLong(9, giornata.getIdcontratto());
//            ps.setLong(10, giornata.getIdsituazione_presenze());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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

    public static Giornata getGiornata(Long id) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Giornata day = null;
        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(
                    " select * from golddatabase.foglio_presenze "
                    + " where idfogli_presenze = ? ");

            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                day = new Giornata(rs.getLong("idfogli_presenze"), rs.getString("lav_ord"), rs.getString("lav_straord"), rs.getString("ferie"), rs.getString("perm_retrib"),
                        rs.getString("perm_non_retrib"), rs.getString("malattia"), rs.getString("assenza"), rs.getLong("contratto_idcontratto"), rs.getLong("situazione_presenze_idsituazione_presenze"));
            }
            return day;

        } catch (Exception e) {
            return null;
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
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

    public static Collection<Giornata> getGiornateByIdSituazionePresenze(Long idSituazionePresenze) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Giornata giornata;
        LinkedList<Giornata> list = new LinkedList<Giornata>();
        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(
                    " select * from golddatabase.foglio_presenze f "
                    + " where situazione_presenze_idsituazione_presenze = ? order by idfogli_presenze ");

            ps.setLong(1, idSituazionePresenze);
            rs = ps.executeQuery();
            while (rs.next()) {
                giornata = new Giornata();
                giornata.setIdfogli_presenze(rs.getLong("idfogli_presenze"));
                giornata.setLav_ord(rs.getString("lav_ord"));
                giornata.setLav_straord(rs.getString("lav_straord"));
                giornata.setFerie(rs.getString("ferie"));
                giornata.setPerm_retrib(rs.getString("perm_retrib"));
                giornata.setPerm_non_retrib(rs.getString("perm_non_retrib"));
                giornata.setMalattia(rs.getString("malattia"));
                giornata.setAssenza(rs.getString("assenza"));
                giornata.setIdsituazione_presenze(rs.getLong("situazione_presenze_idsituazione_presenze"));
                giornata.setIdcontratto(rs.getLong("contratto_idcontratto"));

                list.add(giornata);
            }

            return list;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
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
