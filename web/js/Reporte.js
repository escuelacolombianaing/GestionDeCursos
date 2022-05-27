function exportTableToExcel(tableID){
    var downloadLink;
    var dataType = 'application/vnd.ms-excel';
    var tableSelect = document.getElementById(tableID);
    var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');
    
    // Specify file name
	filename='ReporteGeneral';
    filename = filename?filename+'.xls':'excel_data.xls';
    
    // Create download link element
    downloadLink = document.createElement("a");
    
    document.body.appendChild(downloadLink);
    
    if(navigator.msSaveOrOpenBlob){
        var blob = new Blob(['ufeff', tableHTML], {
            type: dataType
        });
        navigator.msSaveOrOpenBlob( blob, filename);
    }else{
        // Create a link to the file
        downloadLink.href = 'data:' + dataType + ', ' + tableHTML;
    
        // Setting the file name
        downloadLink.download = filename;
        
        //triggering the function
        downloadLink.click();
    }
}

function ListasRep() {
    
		$("#CboxCursos").html('');
		$("#CboxCursos").append('<option value="null">Seleccione</option>');
                $("#myInput").html('');
		$("#myInput").append('<option value="">Seleccione</option>');
		$("#CboxPublicoReporte").html('');
		$("#CboxPublicoReporte").append('<option value="null">Seleccione</option>');
		 $("#CboxEstadoReporte").html('');
		  $("#CboxEstadoReporte").append('<option value="null">Seleccione</option>');
		  $("#CboxUnidadReporte").html('');
		  $("#CboxUnidadReporte").append('<option value="null">Seleccione</option>');
		  $("#CboxTipoCursoReporte").html('');
		  $("#CboxTipoCursoReporte").append('<option value="null">Seleccione</option>');
    $.ajax({
        type: "GET",
        url: '/GestionDeCursos/Utils/Listas',
        dataType: "json",
        success: function(data) {
            $.each(data, function(key, registro) {
                var Opcion = parseInt(registro.tipo)
                switch (Opcion) {
                    case 1:
                        $("#CboxPublicoReporte").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                        break;
                    case 4:
                        $("#CboxEstadoReporte").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                        break;
                    case 6:
                        $("#CboxUnidadReporte").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                        break;
					case 8:						
						$("#CboxTipoCursoReporte").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
						break;
					case 10:
						$("#CboxCursos").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                                                $("#myInput").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
						break;
                }



            });
        },
        error: function(data) {
            alert('error');
        }
    });
}

function ListasRepPro() {
    var parametros = {
            "Profesor": $("#idemp").val(),
			"SegProfesor": $("#idemp").val()
        };
		console.log(parametros);
		$("#myInput").html('');
		$("#myInput").append('<option value="">Seleccione curso</option>');
		$("#CboxCursos").html('');
		$("#CboxCursos").append('<option value="null">Seleccione</option>');
		$("#CboxPublicoReporte").html('');
		$("#CboxPublicoReporte").append('<option value="null">Seleccione</option>');
		 $("#CboxEstadoReporte").html('');
		  $("#CboxEstadoReporte").append('<option value="null">Seleccione</option>');
		  $("#CboxUnidadReporte").html('');
		  $("#CboxUnidadReporte").append('<option value="null">Seleccione</option>');
		  $("#CboxTipoCursoReporte").html('');
		  $("#CboxTipoCursoReporte").append('<option value="null">Seleccione</option>');
    $.ajax({
		body: parametros, //datos que se envian a traves de ajax
        type: "POST",
        url: '/GestionDeCursos/Utils/Listas',
       dataType: 'json',
            data: JSON.stringify(parametros),
            contentType: "application/json; charset=utf-8",
        success: function(data) {
            $.each(data, function(key, registro) {
                var Opcion = parseInt(registro.tipo)
                switch (Opcion) {
                    case 1:
                        $("#CboxPublicoReporte").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                        break;
                    case 4:
                        $("#CboxEstadoReporte").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                        break;
                    case 6:
                        $("#CboxUnidadReporte").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                        break;
					case 8:						
						$("#CboxTipoCursoReporte").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
						break;
					case 10:
					
						$("#CboxCursos").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
						$("#myInput").append('<option value="' + registro.Especificacion + '">' + registro.Especificacion + '</option>');
						
						break;
                }



            });
        },
        error: function(data) {
            alert('error');
        }
    });
}


function ReporteGeneral(GesPro){
	
	$('#myTbodyReporte').html('')
	if(GesPro==1){
		
		var parametros = {
            "estado": document.getElementById("CboxEstadoReporte").value,
            "tipoCurso": document.getElementById("CboxTipoCursoReporte").value,
            "publico":document.getElementById("CboxPublicoReporte").value,
            "unidad": document.getElementById("CboxUnidadReporte").value,
            "NombreEstu":$("#TxtNomEstudiante").val(),
			"Profesor":$("#idemp").val(),
			"SegProfesor":$("#idemp").val(),
			"NombreCurso":document.getElementById("CboxCursos").value
        };
	}else{
		var parametros = {
            "estado": document.getElementById("CboxEstadoReporte").value,
            "tipoCurso": document.getElementById("CboxTipoCursoReporte").value,
            "publico":document.getElementById("CboxPublicoReporte").value,
            "unidad": document.getElementById("CboxUnidadReporte").value,
            "NombreEstu":$("#TxtNomEstudiante").val(),
			"NombreCurso":document.getElementById("CboxCursos").value
        };
		
	}
	 
        $.ajax({
            body: parametros, //datos que se envian a traves de ajax
            url: '/GestionDeCursos/Services/ReporteGeneral', //URL del servicio
            type: 'post', //método de envio
            dataType: 'json',
            data: JSON.stringify(parametros),
            contentType: "application/json; charset=utf-8",
             success: function(data) {
            $.each(data.Datos, function(key, registro) {
                $('#myTbodyReporte').append(' <tr>' +
						'<td><h6>' + registro.id + '</h6></td>' +
                        '<td><h6>' + registro.NombreCurso + '</h6></td>' +
                        '<td><h6>' + registro.valor + '</h6></td>' +
                        '<td><h6>' + registro.publico + '</h6></td>' +
                        '<td><h6>' + registro.periodo + '</h6></td>' +
                        '<td><h6>' + registro.inicio + '</h6></td>' +
						'<td><h6>' + registro.fin + '</h6></td>' +
						'<td><h6>' + registro.horas + '</h6></td>' +
                        '<td><h6>' + registro.cupoMax + '</h6></td>' +
						'<td><h6>' + registro.lugar + '</h6></td>' +
						'<td style="width:50px"><h6>' + registro.Dias + '</h6></td>' +
                        '<td><h6>' + registro.tipoCurso + '</h6></td>' +
                        '<td><h6>' + registro.linea + '</h6></td>' +
						'<td><h6>' + registro.estado + '</h6></td>' +
						'<td><h6>' + registro.modalidad + '</h6></td>' +
                        '<td><h6>' + registro.unidad + '</h6></td>' +
						'<td><h6>' + registro.profesor + '</h6></td>' +
						'<td><h6>' + registro.segundoProfesor + '</h6></td>' +
                        '<td><h6>' + registro.proExterno + '</h6></td>' +
                        '<td><h6>' + registro.descripcion + '</h6></td>' +
                        '<td><h6>' + registro.docInscr + '</h6></td>' +
                        '<td><h6>' + registro.estadoIns + '</h6></td>' +
                        '<td><h6>' + registro.fecreg + '</h6></td>' +
						'<td><h6>' + registro.nombreCompletoIns + '</h6></td>' +
						'<td><h6>' + registro.nota + '</h6></td>' +
                        '<td><h6>' + registro.idRegistro + '</h6></td>' +
						'<td><h6>' + registro.fechaRegistro + '</h6></td>' +
                        '</tr>');                 
            });
			
			
        },
        error: function(data) {
            alert('error '+data);
        }
        });
}