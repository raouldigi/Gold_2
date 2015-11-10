<%@page import="bean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="menu_amministratore.jsp"></jsp:include>

    <script type="text/javascript">
        function openPopup(URL) {

            var windowName = new Date().getTime();
            window.name = windowName;
            window.open(URL + "&parentWindow=" + windowName, "Modifica Giornata",
                    "height = 500, width = 500, left=300, top=100");
            //window.open("popup.html?parentWindow="+windowName, "", stile);

            //window.name='win1';
            //window.alert(window.name);
            //window.open(URL+"&parentWindow="+windowName, "Modifica Giornata", "height = 500, width = 500, left=300, top=100");
        }

        function save() {
            var n1 = document.getElementById("n1");
            var osservazioni = document.getElementById("osservazioni");

            n1.value = osservazioni.value;

            var n2 = document.getElementById("n2");
            var noteBusta = document.getElementById("noteBusta");

            n2.value = noteBusta.value;

            document.getElementById("salvaForm").submit();
        }

        function saveNote(id1, id2) {
            var n1 = document.getElementById(id1);
            var osservazioni = document.getElementById("osservazioni");

            n1.value = osservazioni.value;

            var n2 = document.getElementById(id2);
            var noteBusta = document.getElementById("noteBusta");

            n2.value = noteBusta.value;

            /*  document.getElementById("salvaForm").submit(); */
        }

        function checkDate() {
            // var month = 0; // January
            var month = document.getElementById("meseCheck").value;
            var year = document.getElementById("annoCheck").value;
            var lastDay = new Date(year, month, 0).getDate();

            var e = document.getElementById("ferieDal");
            var ferieDal = e.options[e.selectedIndex].value;

            var e = document.getElementById("ferieAl");
            var ferieAl = e.options[e.selectedIndex].value;

            var e = document.getElementById("malattieDal");
            var malattieDal = e.options[e.selectedIndex].value;

            var e = document.getElementById("malattieAl");
            var malattieAl = e.options[e.selectedIndex].value;

            //************************************************************
            var e = document.getElementById("assenzeDal");
            var assenzeDal = e.options[e.selectedIndex].value;

            var e = document.getElementById("assenzeAl");
            var assenzeAl = e.options[e.selectedIndex].value;
            //***********************************************************

            if (ferieDal > ferieAl)
                alert("Verificare valori nei campi ferieDal e ferieAl!");
            else if (malattieDal > malattieAl)
                alert("Verificare valori nei campi malattieDal e malattieAl!");
            else if (assenzeDal > assenzeAl)
                alert("Verificare valori nei campi assenze Dal e assenze Al!");
            else if (ferieDal > lastDay || ferieAl > lastDay
                    || malattieDal > lastDay || malattieAl > lastDay
                    || assenzeDal > lastDay || assenzeAl > lastDay)
                alert("Il giorno inserito è maggiore dell'ultimo giorno del mese in questione!");
            else {
                document.getElementById("ferieForm").submit();
            }
        }

        function isRedDay() {
            // var month = 0; // January
            var x = document.getElementsByClassName("dayStyle");
            var i, j;
        var day    ;
        var month = '${mese}'    ;
            var year = '${anno}';

        //fare for ogli elemento in festivita e festivitaNaz --> colora ross    o
        //var fest = '${request.festivita.get(0)}';  // <-- prende il primo element    o
            var fest = '${request.festivitaNaz}' + ', ' + '${request.festivita}';

            var festArray = fest.replace("[", "").replace("]", "").replace("[", "")
                    .replace("]", "").split(",");

            for (i = 0; i < x.length; i++) {
                day = x[i].innerText;
                var d = new Date(year, month - 1, day).getDay();
                if (d == '0' || d == '6') {
                    x[i].style.backgroundColor = "red";

                }

                // per ogni festa controlla che il mese e il giorno coincida con il corrente day
                for (j = 0; j < festArray.length; j++) {
                    var g = festArray[j].split("_")[0].trim();
                    var m = festArray[j].split("_")[1].trim();

                    //console.info(g+" - "+m+" = " +day+" - "+month);
                    if (month.trim() == m && day.trim() == g) {
                        x[i].style.backgroundColor = "red";

                    }
                }

            }
        }
        function confInvia() {
            confirm("", function () {
                document.getElementById("inviaForm").submit();
            });
        }
        function cambiailtesto() {
            document.getElementById("osservazioni").innerHTML = "TUTTO REGOLARE";
        }
</script>

<!--<hr/>-->
<h1>&nbsp;Foglio Presenza DDL</h1>
<br />
<div>
    <b>LAVORATORE: </b>
    <s:property value="#request.dipendente.nome" />
    &nbsp;
    <s:property value="#request.dipendente.cognome" />
</div>
<br />
<div>
    <b>PROSPETTO MESE: </b>
    <s:property value="#request.mese" />
    &nbsp;/&nbsp;
    <s:property value="#request.anno" />
</div>
<br />

<s:if
    test="%{(#request.foglioPresenze.stato != 'inviato' && #request.foglioPresenze.stato != 'approvato')}">

</s:if>
<s:else>
    <br />
    <div>
        Stato del foglio presenze:
        <s:property value="#request.foglioPresenze.stato" />
        .
    </div>
    <script>

    </script>
</s:else>

<s:if
    test="%{(#request.foglioPresenze.stato != 'inviato' && #request.foglioPresenze.stato != 'approvato')}">
    <table border="0" cellpadding="1">
        <tbody>
            <tr>
                <td><form id="salvaForm" action="salvaFoglio">
                        <s:hidden id="accessType" name="accessType" value="%{accessType}" />
                        <input type="hidden" name="contratto"
                               value="<s:property value='#request.contratto'/>"> 
                        <input
                            type="hidden" name="mese"
                            value="<s:property value='#request.mese'/>"> 
                        <input
                            type="hidden" name="anno"
                            value="<s:property value='#request.anno'/>"> 
                        <input
                            type="hidden" name="osservazioni_foglio" id="n1"> 
                        <input
                            type="hidden" name="note_busta" id="n2"> 
                        <input
                            id="salvaButton" type="button" value="SALVA" onclick="save()">
                    </form></td>
                <td>
                    <form id="inviaForm" action="aggiornaStatoFoglio">
                        <s:hidden id="accessType" name="accessType" value="%{accessType}" />
                        <input type="hidden" name="contratto"
                               value="<s:property value='#request.contratto'/>"> <input
                               type="hidden" name="mese"
                               value="<s:property value='#request.mese'/>"> <input
                               type="hidden" name="anno"
                               value="<s:property value='#request.anno'/>"> <input
                               type="hidden" name="stato" value="inviato"> <input
                               type="button" value="INVIA" onclick="confInvia()">
                    </form>

                    <div id='confirm'>
                        <div style="top: 0px;" class='header'>
                            <span>Conferma</span>
                        </div>
                        <div class='message'>Confermi l'invio del Foglio Presenze?</div>
                        <div class='buttons' style="width: 100%">
                            <div class='no simplemodal-close'>No</div>
                            <div class='yes'>Sì</div>
                        </div>
                    </div>
                </td>
                <!-- <td><form action="">
                <s:hidden id="accessType" name="accessType" value="%{accessType}"/>
<input type="submit" value="STAMPA">                
</form></td> -->
            </tr>
        </tbody>
    </table>
</s:if>
<p></p>
<s:set var="startcount" value="%{#request.giornate.idfogli_presenze[0]}"></s:set>
<s:property value="startcount" />

<!--      Definisco i colori delle righe della tabellla ****************************************************************-->
<!--<style type="text/css">
    td.colore1 {
        background-color: #E8E8E8 ;
    }
    td.colore2 {
        background-color: #FFFFFF;
    }
</style>-->

<table style="border-bottom-color: gray; border-width: 2px;" border=1>
    <thead>
        <tr>
            <th></th>

            <!-- value=# per far sì che il link non apra un'altra pagina ma esegua il javascript openPopup(URL)

http://path_action/updateDay.action?idfogli_presenze=giornate[x].id
            -->

            <s:if
                test="%{(#request.foglioPresenze.stato != 'inviato' && #request.foglioPresenze.stato != 'approvato')}">
                <s:iterator value="%{#request.giornate}" var="giornata"
                            status="incr">
                    <th class="dayStyle" width="20px"
                        style="text-align: center; background-color: #666; color: white;"><a
                            style="color: white; font-weight: bold"
                            href="<s:url action="updateDay.action">
                                <s:param name="idfogli_presenze" value="#giornata.idfogli_presenze"> </s:param>
                                <s:param name="contratto" value="%{#request.contratto}"> </s:param>
                                <s:param name="anno" value="%{#request.anno}"> </s:param>
                                <s:param name="mese" value="%{#request.mese}"> </s:param>
                                <s:param name="accessType" value="%{accessType}"> </s:param>
                                <s:param name="giorno" value="%{#incr.index+1}" />
                            </s:url>">&nbsp;<s:property
                                value="%{#incr.index+1}" />&nbsp;
                        </a></th>
                    </s:iterator>
        <script>
            isRedDay();
        </script>
    </s:if>
    <s:else>
        <s:iterator value="%{#request.giornate}" var="giornata"
                    status="incr">
            <th class="dayStyle" width="20px"
                style="text-align: center; background-color: #666; color: white;"><s:property
                    value="%{#incr.index+1}" /></th>
            </s:iterator>
        <script>
            isRedDay();
        </script>
    </s:else>
</tr>
</thead>
<tbody>
    <tr>
        <td>Ferie</td>
        <s:iterator value="%{#request.giornate}" var="giornata">
            <td class="colore1"><s:property value="#giornata.ferie" /></td>
        </s:iterator>
    </tr>
    <tr>
        <td>Malattia</td>
        <s:iterator value="%{#request.giornate}" var="giornata">
            <td class="colore2"><s:property value="#giornata.malattia" /></td>
        </s:iterator>
    </tr>
    <tr>
        <td>Lavoro Ordinario</td>
        <!--s:iterator è un ciclo for come in FoglioPresenzeAction * giornata è la variabile locale per il ciclo-->
        <s:iterator value="%{#request.giornate}" var="giornata">
            <td class="colore1"><s:property value="#giornata.lav_ord" /></td>
            <%--  <%= request.getAttribute("giornate") %> --%>
        </s:iterator>
    </tr>
    <tr>
        <td>Lavoro Straordinario</td>
        <s:iterator value="%{#request.giornate}" var="giornata">
            <td class="colore2"><s:property value="#giornata.lav_straord" /></td>
        </s:iterator>
    </tr>
    <tr>
        <td>Permesso retribuito</td>
        <s:iterator value="%{#request.giornate}" var="giornata">
            <td class="colore1"><s:property value="#giornata.perm_retrib" /></td>
        </s:iterator>
    </tr>
    <tr>
        <td>Permesso non Retribuito</td>
        <s:iterator value="%{#request.giornate}" var="giornata">
            <td class="colore2"><s:property
                    value="#giornata.perm_non_retrib" /></td>
            </s:iterator>
    </tr>
    <tr>
        <td>Assenza non retribuita</td>
        <s:iterator value="%{#request.giornate}" var="giornata">
            <td class="colore1"><s:property value="#giornata.assenza" /></td>
        </s:iterator>
    </tr>

</tbody>
</table>

<s:if test="%{#request.dayTableVisibility == 'true' }">

    <s:form id="myForm" action="insertValueDay">
        <table id="dayTable"
               style="background-color: #ccffcc; border-width: 2px;" border=0>
            <thead>
                <tr>
                    <th style="color: blue"><b>MODIFICA LA GIORNATA del <s:property
                                value="giorno" />
                        </b><br> <br></th>
                </tr>
                <tr>
                    <th><s:label value="N. Ore o Tipologia"></s:label></th>
                    </tr>
                </thead>
                <tbody>

                <s:hidden name="contratto" value="%{#request.contratto}" />
                <s:hidden name="anno" value="%{#request.anno}" />
                <s:hidden name="mese" value="%{#request.mese}" />
                <s:hidden name="osservazioni_foglio" id="n51" />
                <s:hidden name="note_busta" id="n52" />
                <tr>
                    <td><s:set var="day" value="%{#request.day}"></s:set> <s:textfield
                            name="lav_ord" label="Lavoro Ordinario" value="%{#day.lav_ord}"></s:textfield>
                        </td>
                    </tr>

                    <tr>
                        <td><s:combobox label="Lavoro Straordinario"
                                name="lav_straord" headerKey="-1"
                                headerValue="seleziona tipo straordinario"
                                list="#@java.util.LinkedHashMap@{'FL':'FL', 'DL':'DL', 'RL':'RL'}"
                                value="%{#day.lav_straord}" /></td>
                </tr>

                <tr>
                    <td style="font-size: smaller"><b>FL=Festività Lavorate <br>DL=Domeniche
                            Lavorate<br>RL=Riposi Sett. Lavorati
                        </b></td>
                </tr>
                <tr>
                    <td><s:textfield name="perm_retrib"
                                 label="Permesso Retribuito" value="%{#day.perm_retrib}"></s:textfield></td>
                    </tr>
                    <tr>
                        <td><s:textfield name="perm_non_retrib"
                                 label="Permesso Non Retribuito" value="%{#day.perm_non_retrib}"></s:textfield></td>
                    </tr>
                    <tr style="display: none">
                        <td><s:textfield cssStyle="display:none;" name="idcontratto"
                                 value="%{#day.idcontratto}"></s:textfield>></td>
                    </tr>
                    <tr style="display: none">
                        <td><s:textfield cssStyle="display:none;"
                                 name="idsituazione_presenze"
                                 value="%{#day.idsituazione_presenze}"></s:textfield>></td>
                    </tr>
                    <tr style="display: none">
                        <td><s:textfield cssStyle="display:none;"
                                 name="idfogli_presenze" value="%{#day.idfogli_presenze}"></s:textfield>></td>
                    </tr>
                    <tr>
                        <td><br /> <s:hidden id="accessType" name="accessType"
                              value="%{accessType}" /> <s:submit  class="myOKButton"value="Inserisci Ore"
                              onclick="saveNote('n51','n52');"></s:submit></td>
                    </tr>
                </tbody>
            </table>
    </s:form>

</s:if>
<br />

<s:if
    test="%{(#request.foglioPresenze.stato != 'inviato' && #request.foglioPresenze.stato != 'approvato')}">
    <!-- <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>-->
    <td><s:form id="ferieForm" action="insertValueDay">
            <s:hidden id="accessType" name="accessType" value="%{accessType}" />
            <s:hidden name="ferie" value="ferie" />
            <s:hidden name="contratto" value="%{#request.contratto}" />
            <s:hidden id="annoCheck" name="anno" value="%{#request.anno}" />
            <s:hidden id="meseCheck" name="mese" value="%{#request.mese}" />
            <div>
                <b>AGGIORNA FERIE/MALATTIE/ASSENZE</b>
            </div>
            <div>
                <s:select label="FERIE - Dal" id="ferieDal" name="ferieDal"
                          list="#@java.util.LinkedHashMap@{'':'--','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10',
                          '11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20',
                          '21':'21','22':'22','23':'23','24':'24','25':'25','26':'26','27':'27','28':'28','29':'29','30':'30','31':'31'}" />
            </div>
            <div>
                <s:select label="FERIE - Al" id="ferieAl" name="ferieAl"
                          list="#@java.util.LinkedHashMap@{'':'--','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10',
                          '11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20',
                          '21':'21','22':'22','23':'23','24':'24','25':'25','26':'26','27':'27','28':'28','29':'29','30':'30','31':'31'}" />
            </div>
            <div>
                <s:select label="MALATTIE - Dal" id="malattieDal" name="malattieDal"
                          list="#@java.util.LinkedHashMap@{'':'--','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10',
                          '11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20',
                          '21':'21','22':'22','23':'23','24':'24','25':'25','26':'26','27':'27','28':'28','29':'29','30':'30','31':'31'}" />
            </div>
            <div>
                <s:select label="MALATTIE - Al" id="malattieAl" name="malattieAl"
                          list="#@java.util.LinkedHashMap@{'':'--','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10',
                          '11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20',
                          '21':'21','22':'22','23':'23','24':'24','25':'25','26':'26','27':'27','28':'28','29':'29','30':'30','31':'31'}" />
            </div>
            <div>
                <s:select label="ASSENZE - Dal" id="assenzeDal" name="assenzeDal"
                          list="#@java.util.LinkedHashMap@{'':'--','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10',
                          '11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20',
                          '21':'21','22':'22','23':'23','24':'24','25':'25','26':'26','27':'27','28':'28','29':'29','30':'30','31':'31'}" />
            </div>
            <div>
                <s:select label="ASSENZE - Al" id="assenzeAl" name="assenzeAl"
                          list="#@java.util.LinkedHashMap@{'':'--','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10',
                          '11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20',
                          '21':'21','22':'22','23':'23','24':'24','25':'25','26':'26','27':'27','28':'28','29':'29','30':'30','31':'31'}" />
            </div>
            <s:hidden name="osservazioni_foglio" id="n41" />
            <s:hidden name="note_busta" id="n42" />
        </s:form>
        <div>
            <br />
            <button value="Inserisci"
                    onclick="saveNote('n41', 'n42');
                                        checkDate();">Inserisci</button>
        </div></td>
    </s:if>

</tr>
</tbody>
</table>


<br />
<table style="width: 40%; border-bottom-color: gray; border-width: 1px;"
       border=0>
    <tr>
    <b>Osservazioni</b>
    <br />

    <s:if
        test="%{(#request.foglioPresenze.stato != 'inviato' && #request.foglioPresenze.stato != 'approvato')}">
        <td><textarea id="osservazioni" name="osservazioni" rows="4"
                      cols="60"><s:if
                    test="%{#request.foglioPresenze.osservazioni != 'null'}">
                    <s:property value="#request.foglioPresenze.osservazioni" />
                </s:if></textarea></td>
            </s:if>
            <s:else>
        <td><textarea readonly="true" id="osservazioni"
                      name="osservazioni" rows="4" cols="60"><s:if
                    test="%{#request.foglioPresenze.osservazioni != 'null'}">
                    <s:property value="#request.foglioPresenze.osservazioni" />
                </s:if></textarea></td>
            </s:else>
    <td><button onclick='cambiailtesto()'>TUTTO REGOLARE</button></td>
</td>
</tr>
</table>
<br />

<!--MOSTRA ALLEGATI ***************************************************************************************-->
<table
    style="width: 60%; background-color: #E8E8E8; border-bottom-color: #E8E8E8; border-width: 1px;"
    border=0>

    <tr>
        <td><c:choose>
                <c:when test="${request.foglioPresenze.allegati[0] != '< VUOTO >'}">
                    <div>
                        Allegato: <a target="_blank"
                                     href="download.action?fileToDownload=<s:property value="#request.foglioPresenze.allegati[0]"/>">
                            <%--<s:property value="#request.foglioPresenze.allegati[0]"/>--%>
                            ALLEGATO N.1
                        </a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                        Allegato:
                        <s:property value="#request.foglioPresenze.allegati[0]" />
                    </div>
                </c:otherwise>
            </c:choose></td>
        <td><c:choose>
                <c:when test="${request.foglioPresenze.allegati[1] != '< VUOTO >'}">
                        <!--<div>Allegato: <a target="_blank" href="./goldfileDir/<s:property value="#request.foglioPresenze.allegati[1]"/>" >-->
                    <div>
                        Allegato: <a target="_blank"
                                     href="download.action?fileToDownload=<s:property value="#request.foglioPresenze.allegati[1]"/>">
                            ALLEGATO N.2 </a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                        Allegato:
                        <s:property value="#request.foglioPresenze.allegati[1]" />
                    </div>
                </c:otherwise>
            </c:choose></td>
</table>

<!--</br>-->

<s:if
    test="%{(#request.foglioPresenze.stato != 'inviato' && #request.foglioPresenze.stato != 'approvato')}">
    <table
        style="width: 60%; border-bottom-color: #E8E8E8; border-width: 1px;"
        border=1>
        <tr>
            <td><s:form action="upload" method="post"
                    enctype="multipart/form-data">
                    <s:file name="file1" label="Allegato 1 "></s:file>
                    <s:submit value="Carica" align="right"
                              onclick="saveNote('n11','n21')"></s:submit>
                    <s:hidden id="accessType" name="accessType" value="%{accessType}" />
                    <s:hidden name="contratto" value="%{#request.contratto}" />
                    <s:hidden name="anno" value="%{#request.anno}" />
                    <s:hidden name="mese" value="%{#request.mese}" />
                    <s:hidden name="osservazioni_foglio" id="n11" />
                    <s:hidden name="note_busta" id="n21" />
                </s:form></td>
            <td><s:form action="upload" method="post"
                    enctype="multipart/form-data">
                    <s:file name="file2" label="Allegato 2 " />
                    <s:submit value="Carica" align="right"
                              onclick="saveNote('n12','n22')"></s:submit>
                    <s:hidden id="accessType" name="accessType" value="%{accessType}" />
                    <s:hidden name="contratto" value="%{#request.contratto}" />
                    <s:hidden name="anno" value="%{#request.anno}" />
                    <s:hidden name="mese" value="%{#request.mese}" />
                    <s:hidden name="osservazioni_foglio" id="n12" />
                    <s:hidden name="note_busta" id="n22" />
                </s:form></td>
        </tr>
    </table>
</s:if>
<br />
<hr />
<table
    style="width: 60%; background-color: #E8E8E8; border-bottom-color: #E8E8E8; border-width: 2px;"
    border=0>
    <tr>
        <th>BUSTA PAGA - Area riservata ufficio ACLI</th>
    </tr>
</table>
<table
    style="width: 60%; background-color: #ccffcc; border-bottom-color: #E8E8E8; border-width: 2px;"
    border=0>
    <tr>
        <td>Note <br /> <textarea readonly="true" id="noteBusta"
                                  name="noteBusta" rows="4" cols="60"><s:if
                    test="%{#request.foglioPresenze.noteBusta != 'null'}">
                    <s:property value="#request.foglioPresenze.noteBusta" />
                </s:if></textarea>
        </td>
        <td><c:choose>
                <c:when test="${request.foglioPresenze.allegati[2] != '< VUOTO >'}">
                    <div>
                        Busta Paga: <a target="_blank"
                                       href="download.action?fileToDownload=<s:property value="#request.foglioPresenze.allegati[2]"/>">
                            ALLEGATO BUSTA PAGA </a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                        Busta Paga:
                        <s:property value="#request.foglioPresenze.allegati[2]" />
                    </div>
                </c:otherwise>
            </c:choose></td>
    </tr>
</table>

<!--<p>Data: <%=new java.util.Date()%> </p>-->

<br />
<b>INFORMAZIONI</b>
<hr />
<style type="text/css">
    b {
        color: #006699;
    }
</style>
<table
    style="background: white; border-bottom-color: gray; border-width: 2px;"
    border=0>
    <tr>
        <td>Questo prospetto DEVE essere comunicato sempre al servizio
            lavoro domestico dal giorno 25 al giorno 31 del mese corrente. <br>È
            possibile comunicarlo nei seguenti modi:<br>
            <ol>
                <li><b>per fax: </b></li>
                <ul>
                    <li>Verona via Scrimiari, San Michele, Villafranca, San
                        Martino B.A., Borgo Roma - <b>045 5112269</b>
                    </li>
                    <li>San Massimo - <b>045 8919910</b></li>
                    <li>Bassa Veronese - <b>0442 620494</b></li>
                </ul>
                <br>

                <li><b>per e-mail:</b></li>
                <ul>
                    <li>Verona via Scrimiari, San Michele, Villafranca, San
                        Martino B.A., Borgo Roma - <b>mondocolf.vr00@acli.it</b>
                    </li>
                    <li>San Massimo - <b>mondocolf.vr05@acli.it</b></li>
                    <li>Bovolone, Legnago, S. Giovanni Lupatoto - <b>mondocolf.vr03@acli.it</b></li>
                </ul>
            </ol>
            <hr /> <strong>Il servizio lavoro domestico s’impegna a
                produrre il cedolino entro il giorno 10 del mese successivo.</strong> <br>
            <br> Per appuntamenti e informazioni chiamare i seguenti numeri:
            <ul>
                <li>Verona via Scrimiari, San Michele, Villafranca, San Martino
                    B.A., Borgo Roma - <b>045 8065572 </b>(orario: 9.00/12.00)
                </li>
                <li>San Massimo - <b>045 8904345</b> (da lun a giov: orari
                    d’ufficio)
                </li>
                <li>Bovolone, Legnago, S. Giovanni Lupatoto - <b>3497541745</b>(mar
                    9.00/12.00 - giov 14.30/17.00)
                </li>
                <h3>
                    <b>oppure contattare il centralino delle ACLI di Verona</b>: <b>045
                        / 8065512 - 550</b>
                </h3>
            </ul>
        </td>
    </tr>
</table>

<style>
    a:link {
        color: #336699;
        background-color: transparent;
        text-decoration: none;
    }

    a:visited {
        color: #336699;
        background-color: transparent;
        text-decoration: none;
    }

    a:hover {
        color: red;
        background-color: transparent;
        text-decoration: underline;
    }

    a:active {
        color: yellow;
        background-color: transparent;
        text-decoration: underline;
    }
</style>
<table style="background-color: white">
    <tbody>
        <tr>
            <td><a href="http://www.acliverona.it"><img
                        src="img/mouse.png" /></a></td>
            <td>
                <h2>
                    <a href="http://www.acliverona.it">Consulta il sito
                        www.acliverona.it</a>
            </td>
        </tr>
    </tbody>
</table>

<c:if test="${message != null}">
    <div id='infoDiv'>
        <div style="top: 0px;" class='header'>
            <span>&nbsp;Informazione</span>
        </div>
        <div class='message'></div>
        <div class='buttons' style="width: 100%">
            <div class='simplemodal-close'>OK</div>
        </div>
    </div>
    <script>
        infoPopup("File caricato correttamente.");
    </script>
</c:if>

<c:if test="${error_message != null}">
    <div id='errorDiv'>
        <div style="top: 0px;" class='error-popup'>
            <span>&nbsp;Errore</span>
        </div>
        <div class='message'></div>
        <div class='buttons' style="width: 100%">
            <div class='simplemodal-close'>OK</div>
        </div>
    </div>
    <script>
        errorPopup("Errore durante il caricamento del file: dimensione o/e estensione non permesse!!");
    </script>
</c:if>
