function ListaInscritos(){
     $('#myTbodyListInscritos').html("");
    var rol = $("#idrol").val();
    var idemp="";
    if(rol==1){
        idemp="";
    }else{
        idemp=$("#idemp").val();
    }
	 $.ajax({
        type: "GET",
        url: '/GestionDeCursos/Services/ListInscritos?Num_Doc='+idemp,
        dataType: "json",
        success: function(data) {
			
            $.each(data, function(key, registro) {
            			var horario="";
			if(registro.curso.LunesIni!="" || registro.curso.LunesFin!=""){
				horario='Lunes: '+registro.curso.LunesIni+'-'+registro.curso.LunesFin+'</br>';}
			if(registro.curso.MartesIni!="" || registro.curso.MartesFin!=""){
				horario=horario+'Martes: '+registro.curso.MartesIni+'-'+registro.curso.MartesFin+'</br>';}
			if(registro.curso.MiercolesIni!="" || registro.curso.MiercolesFin!=""){
				horario=horario+'Miércoles: '+registro.curso.MiercolesIni+'-'+registro.curso.MiercolesFin+'</br>';}
			if(registro.curso.JuevesIni!="" || registro.curso.JuevesFin!=""){
				horario=horario+'Jueves: '+registro.curso.JuevesIni+'-'+registro.curso.JuevesFin+'</br>';}
			if(registro.curso.ViernesIni!="" || registro.curso.ViernesFin!=""){
				horario=horario+'Viernes: '+registro.curso.ViernesIni+'-'+registro.curso.ViernesFin+'</br>';}
			if(registro.curso.SabadoIni!="" || registro.curso.SabadoFin!=""){
				horario=horario+'Sábado: '+registro.curso.SabadoIni+'-'+registro.curso.SabadoFin+'</br>';}
			if(registro.curso.DomingoIni!="" || registro.curso.DomingoFin!=""){
				horario=horario+'Domingo: '+registro.curso.DomingoIni+'-'+registro.curso.DomingoFin+'</br>';}
                
                $('#myTbodyListInscritos').append('<tr>' +
                        '<td ><h6>' + registro.curso.id+' - '+registro.curso.NombreCurso + '<input type="hidden" id="tdCurso'+registro.inscrito.id+'" value="'+registro.curso.id+'" /></h6></td>' +
                        '<td ><h6>' + registro.inscrito.nombre + '<input type="hidden" id="tdNomEst'+registro.inscrito.id+'" value="'+registro.inscrito.docInscr+'"/></h6></td>' +
                        '<td><h6>' +horario + '</h6></td>' +
                        '<td><h6>' + registro.curso.Lugar + '</h6></td>' +
                        '<td><h6>' + registro.curso.Inicio + '</h6></td>' +
                        '<td><h6>' + registro.curso.Fin + '</h6></td>' +
                       '<td style="margin:0;width:10%;padding:0;"><h6 style="margin:0;width:100%"><center>  <div class="form-group"><select required="" class="form-control" id="'+registro.inscrito.id+'" onchange="GestionDeNotas(this.id)"><option selected="" value="">Seleccione</option><option value="33">Aprobado</option><option value="34">No Aprobado</option><option value="35">Nunca asistió</option><option value="36">Abandonó</option></select></div></center></h6></td>' +
                        '</tr>');
				if(parseInt(registro.nota.nota)!=0){
					document.getElementById(registro.inscrito.id).value = parseInt(registro.nota.nota);
				}
						 
                 
            });
			$('#myTableListInscritos').DataTable({ "retrieve": true,"searching": false, "paging": false });
        },
        error: function(data) {
            alert('error '+data);
        }
    });
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

function myFunctionListaInscritos() {
	

    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInput");
    if(input=="Seleccione"){
        input="";
    }
    filter = input.value.toUpperCase();
    table = document.getElementById("myTableListInscritos");
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

function GestionDeNotas(idinscrito){
	if(document.getElementById(idinscrito).value!=""){
	   var parametros={
		   
		   
        "idCurso":document.getElementById("tdCurso"+idinscrito).value,
        "nota":document.getElementById(idinscrito).value,
        "idInscrito":document.getElementById("tdNomEst"+idinscrito).value,
		"idRegistro":$("#idemp").val(),
    };
	
       $.ajax({
            body: parametros, //datos que se envian a traves de ajax
            url: '/GestionDeCursos/Services/NotaDelInscrito', //URL del servicio
            type: 'post', //método de envio
            dataType: 'json',
            data: JSON.stringify(parametros),
            contentType: "application/json; charset=utf-8",
            success: function(response) { //una vez que el archivo recibe el request lo procesa y lo devuelve

				
                if (response.transaccion) {  
							 $("#DivCorrecto").html(" <div class='alert alert-success'><b>Correcto: </b>¡"+response.message+"!<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a></div>");
						
					document.getElementById('BtnAprobado').click();
                  
                   
                } else {

					$("#DivError").html(" <div class='alert alert-danger'><b>Error: </b>¡"+response.error+"!<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a></div>");
                    document.getElementById('BtnRechazado').click();
                }
            }
        });
		}
		return false;
}