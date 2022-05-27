function GestionDeCursos() {

    $("#InfoCursos").bind("submit", function(ex) {
        ex.preventDefault();
        ex.stopImmediatePropagation();
        document.getElementById("BtnEditar").html = "Enviando...";
        document.getElementById("BtnEditar").disabled = true;
		 document.getElementById("BtnCrear").html = "Enviando...";
        document.getElementById("BtnCrear").disabled = true;
        if ($("#TxtCodCurso").val() == '') {
            $("#TxtCodCurso").val(0)
        }
		if (document.getElementById("CboxProfesor").value==""){
			document.getElementById("CboxProfesor").value=25;
		}
		if (document.getElementById("Cbox2doprofesor").value==""){
			document.getElementById("Cbox2doprofesor").value=25;
		}
        var parametros = {
            "id": $("#TxtCodCurso").val(),
            "NombreCurso": $("#TxtNombreCurso").val(),
            "Valor": $("#TxtValor").val(),
            "Publico": document.getElementById("CboxPublico").value,
            "Periodo": document.getElementById("CboxPeriodo").value,
            "Inicio": $("#DateInicio").val(),
            "Fin": $("#DateFin").val(),
            "Horas": $("#TxtHoras").val(),
            "CupoMax": $("#TxtCupMax").val(),
            "Lugar": $("#TxtLugar").val(),
            "LunesIni": document.getElementById("TxtHIniLunes").value,
            "MartesIni": document.getElementById("TxtHIniMartes").value,
			"MiercolesIni": document.getElementById("TxtHIniMiercoles").value,
			"JuevesIni": document.getElementById("TxtHIniJueves").value,
			"ViernesIni": document.getElementById("TxtHIniViernes").value,
			"SabadoIni": document.getElementById("TxtHIniSabado").value,
			"DomingoIni": document.getElementById("TxtHIniDomingo").value,
			"LunesFin": document.getElementById("TxtHFinLunes").value,
            "MartesFin": document.getElementById("TxtHFinMartes").value,
			"MiercolesFin": document.getElementById("TxtHFinMiercoles").value,
			"JuevesFin": document.getElementById("TxtHFinJueves").value,
			"ViernesFin": document.getElementById("TxtHFinViernes").value,
			"SabadoFin": document.getElementById("TxtHFinSabado").value,
			"DomingoFin": document.getElementById("TxtHFinDomingo").value,
            "Linea": document.getElementById("CboxLinea").value,
            "Estado": document.getElementById("CboxEstado").value,
            "Modalidad": document.getElementById("CboxModalidad").value,
            "Unidad": document.getElementById("CboxUnidad").value,
            "Profesor": document.getElementById("CboxProfesor").value,
            "SegundoPro": document.getElementById("Cbox2doprofesor").value,
            "ProExterno": $("#TxtProExterno").val(),
            "Descripcion": $("#TxtDescripcion").val(),
			"IdTipoCurso":document.getElementById("CboxTipoCurso").value
        };

        $.ajax({
            body: parametros, //datos que se envian a traves de ajax
            url: '/GestionDeCursos/Services/GestionDeCursos', //URL del servicio
            type: 'post', //método de envio
            dataType: 'json',
            data: JSON.stringify(parametros),
            contentType: "application/json; charset=utf-8",
            success: function(response) { //una vez que el archivo recibe el request lo procesa y lo devuelve

                if (response.transaccion) {
                  document.getElementById('BtnAprobado').click();
				  
				  
                } else {
                   document.getElementById('BtnRechazado').click();
                }
                document.getElementById("BtnCrear").value = "Crear";
                document.getElementById("BtnCrear").disabled = false;
				 document.getElementById("BtnEditar").value = "Crear";
                document.getElementById("BtnEditar").disabled = false;
            }
        });
    });


}

function Listas() {
	
	 var fecha = new Date();
	 var periodo;
	for (var i = 0; i <= 4; i++) {
		$("#CboxPeriodo").append('<option value=' + fecha.getFullYear()+'-1>' +fecha.getFullYear()+'-1'+'</option>');
		$("#CboxPeriodo").append('<option value=' + fecha.getFullYear()+'-Intersemestral>' +fecha.getFullYear()+'-Intersemestral'+'</option>');
		$("#CboxPeriodo").append('<option value=' +  fecha.getFullYear()+'-2>' +fecha.getFullYear()+'-2'+'</option>');
		fecha.setFullYear(fecha.getFullYear() + 1);
}
    Opciones();
    $.ajax({
        type: "GET",
        url: '/GestionDeCursos/Utils/Listas',
        dataType: "json",
        success: function(data) {
            $.each(data, function(key, registro) {
                var Opcion = parseInt(registro.tipo)
                switch (Opcion) {
                    case 1:
                        $("#CboxPublico").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                        break;
                    case 3:
                        $("#CboxModalidad").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                        break;
                    case 4:
                        $("#CboxEstado").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                        break;
                    case 5:
                        $("#Cbox2doprofesor").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                        $("#CboxProfesor").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                        break;
                    case 6:
                        $("#CboxUnidad").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                        break;
					case 8:						
						$("#CboxTipoCurso").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
						break;
                }



            });
        },
        error: function(data) {
            alert('error');
        }
    });
}

function CrearValidacionFechaFin() {
	var dia;
	var date = new Date();
	var month;
	
var convinput = Date.parse(document.getElementById("DateInicio").value);
	var fechainput = new Date(convinput);
	var monthinput;
	if(fechainput.getMonth()+1 < 10){
  monthinput='0'+(fechainput.getMonth()+1);
}else{
	 monthinput=fechainput.getMonth()+1;
}
document.getElementById("infoFechaFin").innerHTML="";
	if('NaN/NaN/NaN'!=(fechainput.getDate()+'/'+monthinput+'/'+fechainput.getFullYear())){
	if ( fechainput<= date){		
		date.setDate(date.getDate() + 1);
		if(date.getMonth()+1 < 10){
  month='0'+(date.getMonth()+1);
}else{
	 month=date.getMonth()+1;
}

if(date.getDate() < 10){
  dia='0'+date.getDate();
}else{
	dia=date.getDate();
}
		document.getElementById("DateFin").min=date.getFullYear()+'-'+month+'-'+dia;
		document.getElementById("infoFechaFin").innerHTML=" fecha minima valida: "+(date.getDate())+'/'+month+'/'+date.getFullYear();
	}else{
		fechainput.setDate(fechainput.getDate() + 2);
		
if(fechainput.getDate() < 10){
  dia='0'+fechainput.getDate();
}else{
	dia=fechainput.getDate();
}
		document.getElementById("DateFin").min=fechainput.getFullYear()+'-'+monthinput+'-'+dia;
		document.getElementById("infoFechaFin").innerHTML=" fecha minima valida: "+(fechainput.getDate())+'/'+monthinput+'/'+fechainput.getFullYear();
	}
	}else{
		document.getElementById("infoFechaFin").innerHTML=" fecha inicio no valida ";
		document.getElementById("DateInicio").value="";
	}
	
	
};

function DependenciaTipo(Opcion) {
	$("#CboxLinea").html("");
	$("#CboxLinea").append('<option value="" >Seleccione</option>');
    var res = "";
    $.ajax({
        type: "GET",
        url: '/GestionDeCursos/Utils/Listas',
         dataType: "json",
        success: function(data) {
            $.each(data, function(key, registro) {
                var Opcion = parseInt(registro.tipo)
                switch (Opcion) {
                    case 2:
						if(registro.DependenciaTipo==document.getElementById("CboxTipoCurso").value){
                        $("#CboxLinea").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
						}
						
                        break;       
                }
            });
			if(Opcion != true){
				document.getElementById("CboxLinea").value =Opcion;
			}
        },
        error: function(data) {
            alert('error');
        }
    });
   return true;
}

function Opciones() {
    $("#DivInfoExiste").html("");
    if (document.getElementById("CboxOpciones").value == '0') {
        document.getElementById("BtnCrear").disabled = false;
        document.getElementById("DivBtns").style.display = "block";
        document.getElementById("BtnEditar").disabled = true;
        document.getElementById("DivBtnEditar").style.display = "none";
        document.getElementById("TxtCodCurso").disabled = true;
        document.getElementById("TxtCodCurso").value = "";
    } else {
        document.getElementById("BtnCrear").disabled = true;
        document.getElementById("DivBtns").style.display = "none";
        document.getElementById("TxtCodCurso").disabled = false;
        document.getElementById("BtnEditar").disabled = true;
        document.getElementById("DivBtnEditar").style.display = "block";
        document.getElementById("TxtCodCurso").value = "";

    }

}

function InfoCurso(idCurso){


    if (idCurso != "")
        ;
    {
        var parametros = {
            "id": idCurso,
        };
        $.ajax({
            body: parametros, //datos que se envian a traves de ajax
            url: '/GestionDeCursos/Services/InformacionCursos', //URL del servicio
            type: 'post', //método de envio
            dataType: 'json',
            data: JSON.stringify(parametros),
            contentType: "application/json; charset=utf-8",
            success: function(response) { //una vez que el archivo recibe el request lo procesa y lo devuelve
                $("#DivInfoExiste").html("");
                if (response.response.informacion) {
                    document.getElementById("BtnEditar").disabled = false;
					 $("#TxtNombreCurso").val(response.NombreCurso);
                    $("#TxtValor").val(response.Valor);
                    document.getElementById("CboxPublico").value = response.Publico;
                    document.getElementById("CboxPeriodo").value = response.Periodo;
                    $("#DateInicio").val(response.Inicio);
                    $("#DateFin").val(response.Fin);
                    $("#TxtHoras").val(response.Horas);
                    $("#TxtCupMax").val(response.CupoMax);
                    $("#TxtLugar").val(response.Lugar);
					if(response.LunesIni != '' && response.LunesFin != ''){
						document.getElementById("Lunes").checked=true;
						ValidarCheckDias("Lunes");
						document.getElementById('TxtHIniLunes').value=response.LunesIni;
						ValidarHoraMinima('TxtHIniLunes');
						document.getElementById('TxtHFinLunes').value=response.LunesFin;
						
						
					}else{
						document.getElementById("Lunes").checked=false;
						ValidarCheckDias("Lunes");						
					}
                    if(response.MartesIni != '' && response.MartesFin != ''){
						document.getElementById("Martes").checked=true;
						ValidarCheckDias("Martes");
						document.getElementById('TxtHIniMartes').value=response.MartesIni;
						ValidarHoraMinima('TxtHIniMartes');
						document.getElementById('TxtHFinMartes').value=response.MartesFin;
						
					}else{
						document.getElementById("Martes").checked=false;
						ValidarCheckDias("Martes");						
					}if(response.MiercolesIni != '' && response.MiercolesFin != ''){
						document.getElementById("Miercoles").checked=true;
						ValidarCheckDias("Miercoles");
						document.getElementById('TxtHIniMiercoles').value=response.MiercolesIni;
						ValidarHoraMinima('TxtHIniMiercoles');
						document.getElementById('TxtHFinMiercoles').value=response.MiercolesFin;
						
					}else{
						document.getElementById("Miercoles").checked=false;
						ValidarCheckDias("Miercoles");						
					}if(response.JuevesIni != '' && response.JuevesFin != ''){
						document.getElementById("Jueves").checked=true;
						ValidarCheckDias("Jueves");
						document.getElementById('TxtHIniJueves').value=response.JuevesIni;
						ValidarHoraMinima('TxtHIniJueves');
						document.getElementById('TxtHFinJueves').value=response.JuevesFin;
						
					}else{
						document.getElementById("Jueves").checked=false;
						ValidarCheckDias("Jueves");						
					}if(response.ViernesIni != '' && response.ViernesFin != ''){
						document.getElementById("Viernes").checked=true;
						ValidarCheckDias("Viernes");
						document.getElementById('TxtHIniViernes').value=response.ViernesIni;
						ValidarHoraMinima('TxtHIniViernes');
						document.getElementById('TxtHFinViernes').value=response.ViernesFin;
						
					}else{
						document.getElementById("Viernes").checked=false;
						ValidarCheckDias("Viernes");						
					}if(response.SabadoIni != '' && response.SabadoFin != ''){
						document.getElementById("Sabado").checked=true;
						ValidarCheckDias("Lunes");
						document.getElementById('TxtHIniSabado').value=response.SabadoIni;
						ValidarHoraMinima('TxtHIniSabado');
						document.getElementById('TxtHFinSabado').value=response.SabadoFin;
						
					}else{
						document.getElementById("Sabado").checked=false;
						ValidarCheckDias("Sabado");						
					}if(response.DomingoIni != '' && response.DomingoFin != ''){
						document.getElementById("Domingo").checked=true;
						ValidarCheckDias("Domingo");
						document.getElementById('TxtHIniDomingo').value=response.DomingoIni;
						ValidarHoraMinima('TxtHIniDomingo');
						document.getElementById('TxtHFinDomingo').value=response.DomingoFin;
						
					}else{
						document.getElementById("Domingo").checked=false;
						ValidarCheckDias("Domingo");						
					}
					
                    document.getElementById("CboxEstado").value = response.Estado;
                    document.getElementById("CboxModalidad").value = response.Modalidad;
                    document.getElementById("CboxUnidad").value = response.Unidad;
                    document.getElementById("CboxProfesor").value = response.Profesor;
                    document.getElementById("Cbox2doprofesor").value = response.SegundoPro;
                    $("#TxtProExterno").val(response.ProExterno);
                    $("#TxtDescripcion").val(response.Descripcion);
					document.getElementById("CboxTipoCurso").value = response.IdTipoCurso;
					DependenciaTipo(response.Linea);
					
                } else {
                    document.getElementById("BtnEditar").disabled = true;
                    document.getElementById("CboxPublico").selectedIndex = "0";
                    document.getElementById("CboxLinea").selectedIndex = "0";
                    document.getElementById("CboxModalidad").selectedIndex = "0";
                    document.getElementById("CboxEstado").selectedIndex = "0";
                    document.getElementById("CboxUnidad").selectedIndex = "0";
                    document.getElementById("CboxProfesor").selectedIndex = "0";
                    document.getElementById("Cbox2doprofesor").selectedIndex = "0";
                    $("#TxtNombreCurso").val("");
                    $("#TxtValor").val("");
                   document.getElementById("CboxPeriodo").selectedIndex="0";
                    $("#DateInicio").val("");
                    $("#DateFin").val("");
                    $("#TxtHoras").val("");
                    $("#TxtCupMax").val("");
                    $("#TxtLugar").val("");
                    document.getElementById("Lunes").checked = false;
                    document.getElementById("Martes").checked = false;
					document.getElementById("Miercoles").checked = false;
					document.getElementById("Jueves").checked = false;
					document.getElementById("Viernes").checked = false;
					document.getElementById("Sabado").checked = false;
					document.getElementById("Domingo").checked = false;
					ValidarCheckDias("Lunes");
					ValidarCheckDias("Martes");
					ValidarCheckDias("Miercoles");
					ValidarCheckDias("Jueves");
					ValidarCheckDias("Viernes");
					ValidarCheckDias("Sabado");
					ValidarCheckDias("Domingo");
					 document.getElementById("CboxTipoCurso").selectedIndex = "0";
                    $("#TxtProExterno").val("");
                    $("#TxtDescripcion").val("");
                    $("#DivInfoExiste").html("NO SE ENCONTRO INFORMACIÓN CON ESTE CÓDIGO");

                }

CrearValidacionFechaFin();
            }
        });

    }


}

function ValidarHoraMinima(id){
	var Horaini=document.getElementById(id).value;
	var horas=parseInt(Horaini.substr(0, 2))+1;
	if (horas<10){
		horas='0'+horas;
	}
	var minutos=Horaini.substr(3, 5);
	var HoraFin=id.slice(7);
	var horamin=horas+':'+minutos;
	console.log(Horaini+horas+minutos+HoraFin+horamin);
	document.getElementById("TxtHFin"+HoraFin).min=horamin;
	
}

function ValidarGrupoChecks(id){
	
	if (document.getElementById(id).checked){
		SeleccionarCheck
		document.getElementById("SeleccionarCheck").innerHTML="";
		document.getElementById("Lunes").required=false;
		document.getElementById("Martes").required=false;
		document.getElementById("Miercoles").required=false;
		document.getElementById("Jueves").required=false;
		document.getElementById("Viernes").required=false;
		document.getElementById("Sabado").required=false;
		document.getElementById("Domingo").required=false;
	}else{
		if(document.getElementById("Lunes").checked==false){
			if(document.getElementById("Martes").checked==false){
			if(document.getElementById("Miercoles").checked==false){
			if(document.getElementById("Jueves").checked==false){
			if(document.getElementById("Viernes").checked==false){
			if(document.getElementById("Sabado").checked==false){
				if(document.getElementById("Domingo").checked==false){
					document.getElementById("SeleccionarCheck").innerHTML="Debe seleccionar mínimo un día(cualquiera) para continuar";
			document.getElementById("Lunes").required=true;
		document.getElementById("Martes").required=true;
		document.getElementById("Miercoles").required=true;
		document.getElementById("Jueves").required=true;
		document.getElementById("Viernes").required=true;
		document.getElementById("Sabado").required=true;
		document.getElementById("Domingo").required=true;
		}}}}}}}
		
	}
	
	
}

function ValidarCheckDias(id){
	
	 if(document.getElementById(id).checked){
		 document.getElementById('TxtHIni'+id).disabled=false;
		 document.getElementById('TxtHFin'+id).disabled=false;
		 document.getElementById('TxtHIni'+id).required=true;
		 document.getElementById('TxtHFin'+id).required=true;
		 ValidarGrupoChecks(id);
	 }else{
		  document.getElementById('TxtHIni'+id).disabled=true;
		 document.getElementById('TxtHFin'+id).disabled=true;
		 document.getElementById('TxtHIni'+id).value='';
		 document.getElementById('TxtHFin'+id).value='';
		  document.getElementById('TxtHIni'+id).required=false;
		 document.getElementById('TxtHFin'+id).required=false;
		 ValidarGrupoChecks(id);
	 }
	
}

function ValidarOpcProfesores(){
	
	if (document.getElementById("CboxProfesor").value == "" && document.getElementById("Cbox2doprofesor").value == "" && document.getElementById("TxtProExterno").value == ""){
		document.getElementById("CboxProfesor").required=true;
		document.getElementById("Cbox2doprofesor").required=true;
		document.getElementById("TxtProExterno").required=true;		
	}else{
		document.getElementById("CboxProfesor").required=false;
		document.getElementById("Cbox2doprofesor").required=false;
		document.getElementById("TxtProExterno").required=false;
	}
	
}

function cerrar(){
                 document.getElementById("BtnAprobado").html = "Enviando...";
        document.getElementById("BtnAprobado").disabled = true;
               setTimeout("document.getElementById('ImgAprobado').click(); document.getElementById('BtnAprobado').disabled = false; document.getElementById('BtnCerrarModal').click(); ListaCursos(); document.getElementById('InfoCursos').reset(); ValidarCheckDias('Lunes'); ValidarCheckDias('Martes'); ValidarCheckDias('Miercoles'); ValidarCheckDias('Jueves'); ValidarCheckDias('Viernes'); ValidarCheckDias('Sabado'); ValidarCheckDias('Domingo'); document.getElementById('infoFechaFin').innerHTML=''; Opciones();",900);
               
               return true;
               
            }

function cerrarr(){
                 document.getElementById("BtnRechazado").html = "Enviando...";
        document.getElementById("BtnRechazado").disabled = true;
               setTimeout("document.getElementById('Imgrechazado').click(); document.getElementById('BtnRechazado').disabled = false; ",900);
               
               return true;
               
            }