

function Inscribirse(idCurso) {
    var parametros = {
        "idCur": idCurso,
        "docInscr": $("#idemp").val(),
        "est": document.getElementById(idCurso).checked
    };
    $.ajax({
        body: parametros, //datos que se envian a traves de ajax
        url: '/GestionDeCursos/Services/NuevaInscripcion', //URL del servicio
        type: 'post', //método de envio
        dataType: 'json',
        data: JSON.stringify(parametros),
        contentType: "application/json; charset=utf-8",
        success: function(response) { //una vez que el archivo recibe el request lo procesa y lo devuelve


            if (response.transaccion) {
                if (document.getElementById(idCurso).checked) {
                    $("#DivCorrecto").html(" <div class='alert alert-success'><b>Correcto: </b>¡Se inscribio correctamente al curso!<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a></div>");

                } else {
                    $("#DivCorrecto").html(" <div class='alert alert-success'><b>Correcto: </b>¡Se cancelo la inscripción correctamente al curso!<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a></div>");

                }

                document.getElementById('BtnAprobado').value = idCurso;
                document.getElementById('BtnAprobado').click();


            } else {

                $("#DivError").html(" <div class='alert alert-danger'><b>Error: </b>¡" + response.error + "!<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a></div>");
                document.getElementById('BtnRechazado').value = idCurso;
                document.getElementById('BtnRechazado').click();
            }
        }
    });
    return false;
}

function InformacionListas(Numero) {
    var res = "";
    $.ajax({
        type: "GET",
        url: '/GestionDeCursos/Utils/Listas',
        dataType: "json",
        async: false,
    }).done(function(respuesta) {
        $.each(respuesta, function(key, registro) {

            if (Numero == registro.Value) {
                res = registro.Especificacion;
            }
        });
    });
    return res;
}

function ListaCursosCertificados() {
    $.ajax({
        type: "GET",
        url: '/GestionDeCursos/Services/ListCursosCert?idemp=' + $("#idemp").val(),
        dataType: "json",
        success: function(data) {
            $.each(data, function(key, registro) {


                if (registro.notacurso.nota == 33) {
                     var Profesores = "";
                if (parseInt(registro.infocurso.Profesor) != 25) {
                    Profesores = Profesores + 'Profesor: ' + registro.infocurso.Profesor + '</br>';
                }
                if (parseInt(registro.infocurso.SegundoPro) != 25) {
                    Profesores = Profesores + 'Profesor: ' + registro.infocurso.SegundoPro + '</br>';
                }
                if (registro.infocurso.ProExterno != '') {
                    Profesores = Profesores + 'Profesor: ' + registro.infocurso.ProExterno;
                }

                var horario = "";
                if (registro.infocurso.LunesIni != "" || registro.infocurso.LunesFin != "") {
                    horario = 'Lunes: ' + registro.infocurso.LunesIni + '-' + registro.infocurso.LunesFin + '</br>';
                }
                if (registro.infocurso.MartesIni != "" || registro.infocurso.MartesFin != "") {
                    horario = horario + 'Martes: ' + registro.infocurso.MartesIni + '-' + registro.infocurso.MartesFin + '</br>';
                }
                if (registro.infocurso.MiercolesIni != "" || registro.infocurso.MiercolesFin != "") {
                    horario = horario + 'Miércoles: ' + registro.infocurso.MiercolesIni + '-' + registro.infocurso.MiercolesFin + '</br>';
                }
                if (registro.infocurso.JuevesIni != "" || registro.infocurso.JuevesFin != "") {
                    horario = horario + 'Jueves: ' + registro.infocurso.JuevesIni + '-' + registro.infocurso.JuevesFin + '</br>';
                }
                if (registro.infocurso.ViernesIni != "" || registro.infocurso.ViernesFin != "") {
                    horario = horario + 'Viernes: ' + registro.infocurso.ViernesIni + '-' + registro.infocurso.ViernesFin + '</br>';
                }
                if (registro.infocurso.SabadoIni != "" || registro.infocurso.SabadoFin != "") {
                    horario = horario + 'Sábado: ' + registro.infocurso.SabadoIni + '-' + registro.infocurso.SabadoFin + '</br>';
                }
                if (registro.infocurso.DomingoIni != "" || registro.infocurso.DomingoFin != "") {
                    horario = horario + 'Domingo: ' + registro.infocurso.DomingoIni + '-' + registro.infocurso.DomingoFin + '</br>';
                }

                    $('#myTbodyCertificados').append(' <tr>' +
                            '<td><h6>' + registro.infocurso.NombreCurso + '</h6></td>' +
                            '<td><h6>' + Profesores + '</h6></td>' +
                            '<td><h6>' + horario + '</h6></td>' +
                            '<td><h6>' + registro.infocurso.Lugar + '</h6></td>' +
                            '<td><h6>' + registro.infocurso.Inicio + '</h6></td>' +
                            '<td><h6>' + registro.infocurso.Fin + '</h6></td>' +
                            '<td style="margin:0;width:10%;padding:0;"><h6 style="margin:0;width:100%"><center><a href="/GestionDeCursos/DescargarCertificado?num_doc='+$("#idemp").val()+'&id_curso='+registro.infocurso.id+'"><img class="img" src="/GestionDeCursos/Imagen/Iconos/descargar.png" title="GestorDeCursos" alt="Foto4" style="width: 40%;"></a></center></h6></td>' +
                            '</tr>');
                }




            });
            $('#myTableCertificados').DataTable({"searching": false, "paging": false});
        },
        error: function(data) {
            alert('error ' + data);
        }
    });

}

function ListaCursos() {
    $('#myTbody').html('');
    $.ajax({
        type: "GET",
        url: '/GestionDeCursos/Services/ListCursosGes',
        dataType: "json",
        success: function(data) {
            $.each(data, function(key, registro) {
                var Profesores = "";

                if (parseInt(registro.Profesor) != 25) {
                    Profesores = Profesores + 'Profesor: ' + registro.Profesor + '</br>';
                }
                if (parseInt(registro.SegundoPro) != 25) {
                    Profesores = Profesores + 'Profesor: ' + registro.SegundoPro + '</br>';
                }
                if (registro.ProExterno != '') {
                    Profesores = Profesores + 'Profesor: ' + registro.ProExterno;
                }

                var horario = "";
                if (registro.LunesIni != "" || registro.LunesFin != "") {
                    horario = 'Lunes: ' + registro.LunesIni + '-' + registro.LunesFin + '</br>';
                }
                if (registro.MartesIni != "" || registro.MartesFin != "") {
                    horario = horario + 'Martes: ' + registro.MartesIni + '-' + registro.MartesFin + '</br>';
                }
                if (registro.MiercolesIni != "" || registro.MiercolesFin != "") {
                    horario = horario + 'Miércoles: ' + registro.MiercolesIni + '-' + registro.MiercolesFin + '</br>';
                }
                if (registro.JuevesIni != "" || registro.JuevesFin != "") {
                    horario = horario + 'Jueves: ' + registro.JuevesIni + '-' + registro.JuevesFin + '</br>';
                }
                if (registro.ViernesIni != "" || registro.ViernesFin != "") {
                    horario = horario + 'Viernes: ' + registro.ViernesIni + '-' + registro.ViernesFin + '</br>';
                }
                if (registro.SabadoIni != "" || registro.SabadoFin != "") {
                    horario = horario + 'Sábado: ' + registro.SabadoIni + '-' + registro.SabadoFin + '</br>';
                }
                if (registro.DomingoIni != "" || registro.DomingoFin != "") {
                    horario = horario + 'Domingo: ' + registro.DomingoIni + '-' + registro.DomingoFin + '</br>';
                }

                $('#myTbody').append(' <tr>' +
                        '<td><h6>'  + registro.NombreCurso+ ' - ' + registro.id + '</h6></td>' +
                        '<td><h6>' + Profesores + '</h6></td>' +
                        '<td><h6>' + horario + '</h6></td>' +
                        '<td><h6>' + registro.Lugar + '</h6></td>' +
                        '<td><h6>' + registro.Inicio + '</h6></td>' +
                        '<td><h6>' + registro.Fin + '</h6></td>' +
                        '<td style="margin:0;width:10%;padding:0;"><h6 style="margin:0;width:100%"><center><a href="#" onclick=\'document.getElementById("TxtCodCurso").value=' + registro.id + '; document.getElementById("TxtCodCurso").onchange();\' data-toggle="modal" data-target="#ModalCrearEditar"><img class="img" src="/GestionDeCursos/Imagen/Iconos/Editar.png" title="GestorDeCursos" alt="Foto4" style="width: 40%;"></a></center></h6></td>' +
                        '</tr>');



            });
            $('#myTable').DataTable({"searching": false, "retrieve": true, "paging": false});
        },
        error: function(data) {
            alert('error ' + data);
        }
    });

}

function ListaCursosInscribirse() {
     $('#myTbody').html('');
     document.getElementById('myTbody').html='';
    var rol = "";
    var rolPrincipal = parseInt($("#idrol").val(), 10);
    switch (rolPrincipal) {
        case 1:
            rol = 1;
            break;
        case 2:
            rol = 2;
            break;
        case 3:
            rol = 1;
            break;
        case 4:
            rol = 1;

    }
    var json;
     $.ajax({
        type: "GET",
        url: '/GestionDeCursos/Services/ListCursos?idemp=' + $("#idemp").val(),
        dataType: "json",
        success: function(data) {
              data.forEach(function(data, index){

                var publico = "";

                switch (data.infocurso.Publico) {
                    case 1:
                        publico = 2;
                        break;
                    case 2:
                        publico = 1;
                        break;
                    case 3:
                        publico = 3;
                        break;
                }



                if (publico == rol || publico == 3 || rolPrincipal == 1) {
                    var Profesores = "";
                    if (parseInt(data.infocurso.Profesor) != '') {
                        Profesores = Profesores + 'Profesor: ' + data.infocurso.Profesor + '</br>';
                    }
                    if (parseInt(data.infocurso.SegundoPro) != '') {
                        Profesores = Profesores + 'Profesor: ' + data.infocurso.SegundoPro + '</br>';
                    }
                    if (data.infocurso.ProExterno != '') {
                        Profesores = Profesores + 'Profesor: ' + data.infocurso.ProExterno;
                    }


                    var horario = "";
                    if (data.infocurso.LunesIni != "" || data.infocurso.LunesFin != "") {
                        horario = 'Lunes: ' + data.infocurso.LunesIni + '-' + data.infocurso.LunesFin + '</br>';
                    }
                    if (data.infocurso.MartesIni != "" || data.infocurso.MartesFin != "") {
                        horario = horario + 'Martes: ' + data.infocurso.MartesIni + '-' + data.infocurso.MartesFin + '</br>';
                    }
                    if (data.infocurso.MiercolesIni != "" || data.infocurso.MiercolesFin != "") {
                        horario = horario + 'Miércoles: ' + data.infocurso.MiercolesIni + '-' + data.infocurso.MiercolesFin + '</br>';
                    }
                    if (data.infocurso.JuevesIni != "" || data.infocurso.JuevesFin != "") {
                        horario = horario + 'Jueves: ' + data.infocurso.JuevesIni + '-' + data.infocurso.JuevesFin + '</br>';
                    }
                    if (data.infocurso.ViernesIni != "" || data.infocurso.ViernesFin != "") {
                        horario = horario + 'Viernes: ' + data.infocurso.ViernesIni + '-' + data.infocurso.ViernesFin + '</br>';
                    }
                    if (data.infocurso.SabadoIni != "" || data.infocurso.SabadoFin != "") {
                        horario = horario + 'Sábado: ' + data.infocurso.SabadoIni + '-' + data.infocurso.SabadoFin + '</br>';
                    }
                    if (data.infocurso.DomingoIni != "" || data.infocurso.DomingoFin != "") {
                        horario = horario + 'Domingo: ' + data.infocurso.DomingoIni + '-' + data.infocurso.DomingoFin + '</br>';
                    }

                    $('#myTbody').append(' <tr>' +
                            '<td><h6>' + data.infocurso.NombreCurso + '</h6></td>' +
                            '<td><h6>' + Profesores + '</h6></td>' +
                            '<td><h6>' + horario + '</h6></td>' +
                            '<td><h6>' + data.infocurso.Lugar + '</h6></td>' +
                            '<td><h6>' + data.infocurso.Inicio + '</h6></td>' +
                            '<td><h6>' + data.infocurso.Fin + '</h6></td>' +
                            '<td id="tdDisponible' + data.infocurso.id + '"><h6><center><input data-width="150" type="checkbox" id="' + data.infocurso.id + '" onclick="Inscribirse(this.id);" /></center></h6></td>' +
                            '</tr>');
                    if (data.infocurso.Disponible == 0 && data.infocurso.est == false) {
                        $('#tdDisponible' + data.infocurso.id).html('</center>SIN CUPOS DISPONIBLES</center>');
                    } else if (data.notacurso.nota == 33) {
                        $('#tdDisponible' + data.infocurso.id).html('</center>CON CALIFICACIÓN</center>');
                    }else if (data.infocurso.est)
                    {
                        document.getElementById(data.infocurso.id).checked = true;
                    } else
                    {
                        document.getElementById(data.infocurso.id).checked = false;
                    }
		 
                }
   });
        }});	
   
    console.log('2'+json);
  

}

function myFunctionCertificados() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInputCertificado");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTableCertificados");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function myFunctionCatalogo() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInputCatalogo");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTableCatalogo");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function myFunction() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInputAdm");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function Aprobado(IdCurso){
	
				var Config="document.getElementById('ImgAprobado').click(); ";
			
               setTimeout(Config,900);
               
               return true;
               
            }
  
function Rechazado(){
               setTimeout("document.getElementById('Imgrechazado').click(); ",900);
               
               return true;
               
            }