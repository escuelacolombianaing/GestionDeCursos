function ValidarInfoUsuario(mirol){
	$("#LblNombre").html("");
	 $.ajax({
        type: "GET",
        url: '/GestionDeCursos/Services/ValidarInfoUsu?mirol='+mirol,
        dataType: "json",
        success: function(data) {
            $.each(data, function(key, registro) {
			   $("#LblNombre").html(data.nombre);
			   $("#idemp").val(data.id_usu);
			  $("#idrol").val(data.rol);
            });
		
			  ListaCursosCertificados();
        },
        error: function(data) {
            location.href ="/GestionDeCursos/jsp/PageError.jsp";
        }
    });
	
	
}

function ValidarInfoUsuarioGes(mirol){
	$("#LblNombre").html("");
	 $.ajax({
        type: "GET",
        url: '/GestionDeCursos/Services/ValidarInfoUsu?mirol='+mirol,
        dataType: "json",
        success: function(data) {
            $.each(data, function(key, registro) {
			   $("#LblNombre").html(data.nombre);
			   $("#idemp").val(data.id_usu);
			  $("#idrol").val(data.rol);
            });
			
            
        },
        error: function(data) {
            location.href ="/GestionDeCursos/jsp/PageError.jsp";
        }
    });
	
	
}

function ValidarInfoUsuarioGesPro(mirol){
	$("#LblNombre").html("");
	 $.ajax({
        type: "GET",
        url: '/GestionDeCursos/Services/ValidarInfoUsu?mirol='+mirol,
        dataType: "json",
        success: function(data) {
            $.each(data, function(key, registro) {
			   $("#LblNombre").html(data.nombre);
			   $("#idemp").val(data.id_usu);
			  $("#idrol").val(data.rol);
            });
			
			
        },
        error: function(data) {
            location.href ="/GestionDeCursos/jsp/PageError.jsp";
        }
    });
	
	
}

function Salir(){
	 $.ajax({
        type: "GET",
        url: '/GestionDeCursos/Services/Salir',
        dataType: "json",
        success: function(data) {
			
        },
        error: function(data) {
    location.href ="https://tycho.escuelaing.edu.co/intraeci/Menu";
        }
    });
	location.href ="https://tycho.escuelaing.edu.co/intraeci/Menu";
	
}