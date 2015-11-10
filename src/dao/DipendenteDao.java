package dao;

import bean.DatoreDiLavoro;
import bean.Dipendente;
import bean.InfoListaContratti;
import bean.InfoContratto;

//import com.opensymphony.xwork2.ActionSupport;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gminardi
 */
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

//import com.mysql.jdbc.Driver;
import common.DBManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DipendenteDao {

    public static Collection<Dipendente> getDipendenteList() throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

//            Estrae la lista di tutti i dipendenti dal db
        Dipendente dipendente;
        ArrayList<Dipendente> list = new ArrayList<Dipendente>();
        try {
            con = DBManager.getConnection();

            ps = con.prepareStatement(
                    " select * from golddatabase.anagrafica_dipe order by cognome ");

            rs = ps.executeQuery();
            while (rs.next()) {
                dipendente = new Dipendente();
                dipendente.setIdanagrafica_dipe(rs.getInt("idanagrafica_dipe"));
                dipendente.setNome(rs.getString("nome"));
                dipendente.setCognome(rs.getString("cognome"));
                dipendente.setIndirizzo(rs.getString("indirizzo"));
                dipendente.setTelefono(rs.getString("telefono"));
                dipendente.setCellulare(rs.getString("cellulare"));
//	dipendente.setEmail(rs.getString("email"));
                list.add(dipendente);
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

    public static Collection<DatoreDiLavoro> getDatoreList() throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

//            Estrae la lista di tutti i dipendenti dal db
        DatoreDiLavoro datoredilavoro;
        ArrayList<DatoreDiLavoro> list = new ArrayList<DatoreDiLavoro>();
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement("select * from golddatabase.anagrafica_ddl order by cognome");

            rs = ps.executeQuery();
            while (rs.next()) {
                datoredilavoro = new DatoreDiLavoro();
                datoredilavoro.setIdanagrafica_ddl(rs.getInt("idanagrafica_ddl"));
                datoredilavoro.setNome(rs.getString("nome"));
                datoredilavoro.setCognome(rs.getString("cognome"));
                datoredilavoro.setIndirizzo(rs.getString("indirizzo"));
                datoredilavoro.setZona(rs.getString("zona"));
                datoredilavoro.setTelefono(rs.getString("telefono"));
                datoredilavoro.setCellulare(rs.getString("cellulare"));
                datoredilavoro.setUserid(rs.getString("userid"));
                datoredilavoro.setPassword(rs.getString("password"));
                datoredilavoro.setEmail(rs.getString("email"));
                datoredilavoro.setTipo_utente(rs.getString("tipo_utente"));

                list.add(datoredilavoro);

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

    public Collection<InfoListaContratti> getListDatoreContratti() throws Exception {
        //dao per la lista dei datori e relativi dipendenti e contratti per il foglio riepilogativo
        //interrogo anche tab situazione_presenze in modo da prendere la lista che continene situaz presenze 

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        InfoListaContratti datoreContratti;
        ArrayList<InfoListaContratti> list = new ArrayList<InfoListaContratti>();
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement("select ddl.cognome, ddl.nome, d.nome as 'nome_dip', d.cognome as 'cognome_dip', c.data_assunz,c.idcontratto from golddatabase.contratto c "
                    + " inner join golddatabase.anagrafica_ddl ddl on c.anagrafica_ddl_idanagrafica_ddl=ddl.idanagrafica_ddl"
                    + " inner join golddatabase.anagrafica_dipe d on c.anagrafica_dipe_idanagrafica_dipe=d.idanagrafica_dipe"
                    + " inner join golddatabase.situazione_presenze s on c.idcontratto=s.fk_contratto" + " group by c.idcontratto order by ddl.cognome");

            rs = ps.executeQuery();
            while (rs.next()) {
                datoreContratti = new InfoListaContratti();
                datoreContratti.setCognome(rs.getString("ddl.cognome"));
                datoreContratti.setNome(rs.getString("ddl.nome"));
                datoreContratti.setCognome_dip(rs.getString("cognome_dip"));
                datoreContratti.setNomedip(rs.getString("nome_dip"));
                datoreContratti.setData_assunzione(rs.getString("data_assunz"));
                datoreContratti.setN_contratto(rs.getInt("idcontratto"));
                list.add(datoreContratti);
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

    public Collection<InfoListaContratti> getListContratti() throws Exception {
        //dao per la lista dei datori e relativi dipendenti e contratti sia attivi che cessati

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        InfoListaContratti datoreContratti;
        ArrayList<InfoListaContratti> list = new ArrayList<InfoListaContratti>();
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement("select ddl.cognome, ddl.nome, d.nome as 'nome_dip', d.cognome as 'cognome_dip', c.data_assunz, c.data_cessaz, c.idcontratto, c.prospetto_duff from golddatabase.contratto c "
                    + " inner join golddatabase.anagrafica_ddl ddl on c.anagrafica_ddl_idanagrafica_ddl=ddl.idanagrafica_ddl"
                    + " inner join golddatabase.anagrafica_dipe d on c.anagrafica_dipe_idanagrafica_dipe=d.idanagrafica_dipe order by ddl.cognome");

            rs = ps.executeQuery();
            while (rs.next()) {
                datoreContratti = new InfoListaContratti();
                datoreContratti.setCognome(rs.getString("ddl.cognome"));
                datoreContratti.setNome(rs.getString("ddl.nome"));
                datoreContratti.setCognome_dip(rs.getString("cognome_dip"));
                datoreContratti.setNomedip(rs.getString("nome_dip"));
                datoreContratti.setData_assunzione(rs.getString("data_assunz"));
               datoreContratti.setData_cessaz(rs.getString("data_cessaz"));
                datoreContratti.setN_contratto(rs.getInt("idcontratto"));
                datoreContratti.setProspetto_duff(rs.getString("prospetto_duff"));
                list.add(datoreContratti);
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
    
    public Collection<InfoListaContratti> getListContrattiProfilo() throws Exception {
        //dao per la lista dei datori e relativi dipendenti e contratti sia attivi che cessati

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        InfoListaContratti datoreContratti;
        ArrayList<InfoListaContratti> list = new ArrayList<InfoListaContratti>();
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement("select ddl.cognome, ddl.nome, d.nome as 'nome_dip', d.cognome as 'cognome_dip', c.data_assunz, c.data_cessaz, c.idcontratto, c.prospetto_duff from golddatabase.contratto c "
                    + " inner join golddatabase.anagrafica_ddl ddl on c.anagrafica_ddl_idanagrafica_ddl=ddl.idanagrafica_ddl"
                    + " inner join golddatabase.anagrafica_dipe d on c.anagrafica_dipe_idanagrafica_dipe=d.idanagrafica_dipe order by c.prospetto_duff desc");

            rs = ps.executeQuery();
            while (rs.next()) {
                datoreContratti = new InfoListaContratti();
                datoreContratti.setCognome(rs.getString("ddl.cognome"));
                datoreContratti.setNome(rs.getString("ddl.nome"));
                datoreContratti.setCognome_dip(rs.getString("cognome_dip"));
                datoreContratti.setNomedip(rs.getString("nome_dip"));
                datoreContratti.setData_assunzione(rs.getString("data_assunz"));
               datoreContratti.setData_cessaz(rs.getString("data_cessaz"));
                datoreContratti.setN_contratto(rs.getInt("idcontratto"));
                datoreContratti.setProspetto_duff(rs.getString("prospetto_duff"));
                list.add(datoreContratti);
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

    public InfoContratto getContrattobyID(String idcontratto) throws Exception {
        //dao per estrarre tutte le info di un contratto in base all'id contratto

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        InfoContratto Contr = new InfoContratto();
        try {
            con = DBManager.getConnection();
            ps = con.prepareStatement("select ddl.cognome, ddl.nome, d.nome as 'nome_dip', d.cognome as 'cognome_dip', c.indirizzo_lavoro as 'indirizzo_lavoro', c.data_assunz, c.data_cessaz, c.giorno_riposo, c.prospetto_duff, "
                    + "g.categoria, v.livello, t.tipo_contratto, p.citta_lavoro "
                    + "from golddatabase.contratto c "
                    + "inner join golddatabase.anagrafica_ddl ddl on c.anagrafica_ddl_idanagrafica_ddl=ddl.idanagrafica_ddl "
                    + "inner join golddatabase.anagrafica_dipe d on c.anagrafica_dipe_idanagrafica_dipe=d.idanagrafica_dipe "
                    + "inner join tab_categoria g on c.tab_categoria_idtab_categoria=g.idtab_categoria "
                    + "inner join tab_livello v on c.tab_livello_idtab_livello=v.idtab_livello "
                    + "inner join tab_tipo_contratto t on c.tab_tipo_contratto_idtab_tipo_contratto=t.idtab_tipo_contratto "
                    + "inner join tab_citta_patrono p on c.tab_citta_patrono_idtab_citta_patrono=p.idtab_citta_patrono "
                    + "where idcontratto=?");
            ps.setString(1, idcontratto);
            rs = ps.executeQuery();
            if (rs.next()) {
                Contr.setIdcontratto(idcontratto);
                Contr.setCognomeddl(rs.getString("ddl.cognome"));
                Contr.setNomeddl(rs.getString("ddl.nome"));
                Contr.setCognome(rs.getString("cognome_dip"));
                Contr.setNome(rs.getString("nome_dip"));
                Contr.setIndirizzolav(rs.getString("indirizzo_lavoro"));

                if (rs.getString("data_assunz") == null) {
                    Contr.setData_assunz(rs.getString("data_assunz"));
                } else {
                    try {
                        Date date = formatter.parse(rs.getString("data_assunz"));
                        Contr.setData_assunz(formatter2.format(date));
                    } catch (ParseException e) {
                    }
                }
                if (rs.getString("data_cessaz") == null) {
                    Contr.setData_cessaz(rs.getString("data_cessaz"));
                } else {
                    try {
                        Date date = formatter.parse(rs.getString("data_cessaz"));
                        Contr.setData_cessaz(formatter2.format(date));
                    } catch (ParseException e) {
                    }
                }
                Contr.setGiorno_riposo(rs.getInt("giorno_riposo"));
                Contr.setProspetto_duff(rs.getString("prospetto_duff"));
                Contr.setCategoria(rs.getString("categoria"));
                Contr.setLivello(rs.getString("livello"));
                Contr.setTipo_contratto(rs.getString("tipo_contratto"));
                Contr.setCitta(rs.getString("citta_lavoro"));
            }

            return Contr;

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

    public static Collection<Dipendente> getDipendenteContrattoList(boolean acli, String email) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

//            Estre la lista dei dipendenti che hanno un contratto associato. Possono esserci dipendenti con piu contratti.
        Dipendente dipendente;
        ArrayList<Dipendente> list = new ArrayList<Dipendente>();
        try {

            con = DBManager.getConnection();

            String stato = "";
            if (acli) {
                stato = " s.stato is not null and (stato = 'inviato' or stato = 'approvato') ";
            } else {
                stato = " 1=1 "; // (s.stato is not null and stato = 'lavorazione') or (stato is null)
                if (email != null) {
                    stato = stato + " and ddl.email= '" + email + "' ";
                }
            }

            StringBuffer query = new StringBuffer(" select d.*, c.idcontratto from golddatabase.contratto c "
                    + " inner join golddatabase.anagrafica_ddl ddl on c.anagrafica_ddl_idanagrafica_ddl=ddl.idanagrafica_ddl "
                    + " inner join golddatabase.anagrafica_dipe d on c.anagrafica_dipe_idanagrafica_dipe=d.idanagrafica_dipe "
                    + " left join golddatabase.foglio_presenze f on c.idcontratto = f.contratto_idcontratto "
                    + " left join golddatabase.situazione_presenze s on f.situazione_presenze_idsituazione_presenze=s.idsituazione_presenze "
                    + " where " + stato + " "
                    + " group by d.idanagrafica_dipe, c.idcontratto ");

            ps = con.prepareStatement(query.toString());

            rs = ps.executeQuery();
            while (rs.next()) {
                dipendente = new Dipendente();
                dipendente.setNome(rs.getString("nome"));
                dipendente.setCognome(rs.getString("cognome"));
                dipendente.setIndirizzo(rs.getString("indirizzo"));
                dipendente.setTelefono(rs.getString("telefono"));
                dipendente.setCellulare(rs.getString("cellulare"));
//	 dipendente.setEmail(rs.getString("email"));
                dipendente.setIdcontratto(rs.getInt("idcontratto"));

                list.add(dipendente);

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

    public static Collection<Dipendente> getDipendenteInfo(String idContratto) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Dipendente dipendente;
        ArrayList<Dipendente> list = new ArrayList<Dipendente>();
        try {

            con = DBManager.getConnection();

            StringBuffer query = new StringBuffer(" select d.*, c.idcontratto from golddatabase.contratto c "
                    + " left join golddatabase.anagrafica_dipe d on c.anagrafica_dipe_idanagrafica_dipe = d.idanagrafica_dipe"
                    + " where c.idcontratto = " + idContratto + " ");

            ps = con.prepareStatement(query.toString());

            rs = ps.executeQuery();
            while (rs.next()) {
                dipendente = new Dipendente();
                dipendente.setNome(rs.getString("nome"));
                dipendente.setCognome(rs.getString("cognome"));
                dipendente.setIndirizzo(rs.getString("indirizzo"));
                dipendente.setTelefono(rs.getString("telefono"));
                dipendente.setCellulare(rs.getString("cellulare"));
//	 dipendente.setEmail(rs.getString("email"));
                dipendente.setIdcontratto(rs.getInt("idcontratto"));

                list.add(dipendente);

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

    public Collection<InfoListaContratti> getDipendenteDatore(String email) throws Exception {

        return searchDipendenteDatore(null, null, null, null, null, null, null, email, null);
    }

    public Collection<InfoListaContratti> searchDipendenteDatore(String zone, String ddlSurname, String ddlName, String dipName, String dipSurname, String month, String year, String email, String prospuff) throws Exception {

        // boolean status = false;
        Connection con = null;
        PreparedStatement ps = null;
        InfoListaContratti dipendente;
        ArrayList<InfoListaContratti> list = new ArrayList<InfoListaContratti>();

        try {
            con = DBManager.getConnection();

            StringBuffer query = new StringBuffer("select ddl.cognome, ddl.nome, d.nome as 'nome_dip', d.cognome as 'cognome_dip', c.data_assunz, c.idcontratto, s.stato, s.mese, s.anno, c.prospetto_duff "
                    + " from golddatabase.contratto c "
                    + " inner join golddatabase.anagrafica_ddl ddl on c.anagrafica_ddl_idanagrafica_ddl=ddl.idanagrafica_ddl "
                    + " inner join golddatabase.anagrafica_dipe d on c.anagrafica_dipe_idanagrafica_dipe=d.idanagrafica_dipe "
                    + " inner join golddatabase.situazione_presenze s on c.idcontratto=s.fk_contratto"
                    + " where anno= (select max(sp.anno) from golddatabase.situazione_presenze sp where sp.fk_contratto= s.fk_contratto )"
                    + " and mese= (select max(sp.mese) from golddatabase.situazione_presenze sp where sp.fk_contratto= s.fk_contratto )");

            if (email != null && email.length() > 0) {
                query.append(" and ddl.email = '" + email + "' ");
            }
            if (ddlName != null && ddlName.length() > 0) {
                query.append(" and ddl.nome like ? ");
            }
            if (ddlSurname != null && ddlSurname.length() > 0) {
                query.append(" and ddl.cognome like ? ");
            }
            if (dipName != null && dipName.length() > 0) {
                query.append(" and d.nome like ? ");
            }
            if (dipSurname != null && dipSurname.length() > 0) {
                query.append(" and d.cognome like ? ");
            }
            if (month != null && month.length() > 0) {
                query.append(" and s.mese = ? ");
            }
            if (year != null && year.length() > 0) {
                query.append(" and s.anno = ?  ");
            }
            if (zone != null && zone.length() > 0) {
                query.append(" and ddl.zona like ? ");
            }

            query.append(" order by stato desc ");

            ps = con.prepareStatement(query.toString());

            int cont = 1;
            if (ddlName != null && ddlName.length() > 0) {
                ps.setString(cont, "%" + ddlName + "%");
                cont++;
            }
            if (ddlSurname != null && ddlSurname.length() > 0) {
                ps.setString(cont, "%" + ddlSurname + "%");
                cont++;
            }
            if (dipName != null && dipName.length() > 0) {
                ps.setString(cont, "%" + dipName + "%");
                cont++;
            }
            if (dipSurname != null && dipSurname.length() > 0) {
                ps.setString(cont, "%" + dipSurname + "%");
                cont++;
            }

            if (month != null && month.length() > 0) {
                ps.setInt(cont, Integer.parseInt(month));
                cont++;
            }
            if (year != null && year.length() > 0) {
                ps.setInt(cont, Integer.parseInt(year));
                cont++;
            }
            if (zone != null && zone.length() > 0) {
                ps.setString(cont, "%" + zone + "%");
                cont++;
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                dipendente = new InfoListaContratti();
                dipendente.setCognome(rs.getString("cognome"));
                dipendente.setNome(rs.getString("nome"));
                dipendente.setCognome_dip(rs.getString("cognome_dip"));
                dipendente.setNomedip(rs.getString("nome_dip"));
                dipendente.setData_assunzione(rs.getString("data_assunz"));
                dipendente.setN_contratto(rs.getInt("idcontratto"));
                dipendente.setMese(rs.getInt("mese"));
                dipendente.setAnno(rs.getInt("anno"));
                dipendente.setProspetto_duff(rs.getString("prospetto_duff"));
                dipendente.setStato(rs.getString("stato"));
                list.add(dipendente);
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
                con.close();
            } catch (Exception e) {
            }
        }
        return list;
    }
    
    //*******************************************************************************************************************************
        public Collection<InfoListaContratti> getLavorazioneSudo(String zone, String ddlSurname, String ddlName, String dipName, String dipSurname, String month, String year, String email, String prospuff) throws Exception {

        // boolean status = false;
        Connection con = null;
        PreparedStatement ps = null;
        InfoListaContratti dipendente;
        ArrayList<InfoListaContratti> list = new ArrayList<InfoListaContratti>();

        try {
            con = DBManager.getConnection();

            StringBuffer query = new StringBuffer("select ddl.cognome, ddl.nome, d.nome as 'nome_dip', d.cognome as 'cognome_dip', c.data_assunz, c.idcontratto, s.stato, s.mese, s.anno, c.prospetto_duff "
                    + " from golddatabase.contratto c "
                    + " inner join golddatabase.anagrafica_ddl ddl on c.anagrafica_ddl_idanagrafica_ddl=ddl.idanagrafica_ddl "
                    + " inner join golddatabase.anagrafica_dipe d on c.anagrafica_dipe_idanagrafica_dipe=d.idanagrafica_dipe "
                    + " inner join golddatabase.situazione_presenze s on c.idcontratto=s.fk_contratto"
                    + " where anno= (select max(s.anno)) and s.stato<>'approvato' ");

            if (email != null && email.length() > 0) {
                query.append(" and ddl.email = '" + email + "' ");
            }
            if (ddlName != null && ddlName.length() > 0) {
                query.append(" and ddl.nome like ? ");
            }
            if (ddlSurname != null && ddlSurname.length() > 0) {
                query.append(" and ddl.cognome like ? ");
            }
            if (dipName != null && dipName.length() > 0) {
                query.append(" and d.nome like ? ");
            }
            if (dipSurname != null && dipSurname.length() > 0) {
                query.append(" and d.cognome like ? ");
            }
            if (month != null && month.length() > 0) {
                query.append(" and s.mese = ? ");
            }
            if (year != null && year.length() > 0) {
                query.append(" and s.anno = ?  ");
            }
            if (zone != null && zone.length() > 0) {
                query.append(" and ddl.zona like ? ");
            }

            query.append(" order by stato, anno desc, mese ");

            ps = con.prepareStatement(query.toString());

            int cont = 1;
            if (ddlName != null && ddlName.length() > 0) {
                ps.setString(cont, "%" + ddlName + "%");
                cont++;
            }
            if (ddlSurname != null && ddlSurname.length() > 0) {
                ps.setString(cont, "%" + ddlSurname + "%");
                cont++;
            }
            if (dipName != null && dipName.length() > 0) {
                ps.setString(cont, "%" + dipName + "%");
                cont++;
            }
            if (dipSurname != null && dipSurname.length() > 0) {
                ps.setString(cont, "%" + dipSurname + "%");
                cont++;
            }

            if (month != null && month.length() > 0) {
                ps.setInt(cont, Integer.parseInt(month));
                cont++;
            }
            if (year != null && year.length() > 0) {
                ps.setInt(cont, Integer.parseInt(year));
                cont++;
            }
            if (zone != null && zone.length() > 0) {
                ps.setString(cont, "%" + zone + "%");
                cont++;
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                dipendente = new InfoListaContratti();
                dipendente.setCognome(rs.getString("cognome"));
                dipendente.setNome(rs.getString("nome"));
                dipendente.setCognome_dip(rs.getString("cognome_dip"));
                dipendente.setNomedip(rs.getString("nome_dip"));
                dipendente.setData_assunzione(rs.getString("data_assunz"));
                dipendente.setN_contratto(rs.getInt("idcontratto"));
                dipendente.setMese(rs.getInt("mese"));
                dipendente.setAnno(rs.getInt("anno"));
                dipendente.setProspetto_duff(rs.getString("prospetto_duff"));
                dipendente.setStato(rs.getString("stato"));
                list.add(dipendente);
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
                con.close();
            } catch (Exception e) {
            }
        }
        return list;
    }


}
