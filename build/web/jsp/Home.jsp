<%-- 
    Document   : Home
    Created on : 13/02/2020, 11:08:10 AM
    Author     : javier.tambo
--%>

<%@page import="java.util.Properties"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ConexionBD.Listas"%>
<%@page import="Utils.Utilidades"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.math.BigInteger"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ConexionBD.Configuracion;"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            div{
                padding: 0;
                margin: 0;
            }
            button{
                background-color: #990000;
                color: white
            }
        </style>
        <script>

            function GestionDeCursos() {

                $("#myFormHome").bind("submit", function(ex) {
                    ex.preventDefault();
                    ex.stopImmediatePropagation();
                    document.getElementById("BtnEditar").html = "Enviando...";
                    document.getElementById("BtnEditar").disabled = true;
                    if ($("#TxtCodCurso").val() == '') {
                        $("#TxtCodCurso").val(0)
                    }
                    var parametros = {
                        "id": $("#TxtCodCurso").val(),
                        "NombreCurso": $("#TxtNomCurso").val(),
                        "Valor": $("#TxtValor").val(),
                        "Publico": document.getElementById("CboxPublico").value,
                        "Periodo": $("#TxtPeriodo").val(),
                        "Inicio": $("#DateInicio").val(),
                        "Fin": $("#DateFin").val(),
                        "Horas": $("#TxtHoras").val(),
                        "CupoMax": $("#TxtCupMax").val(),
                        "Lugar": $("#TxtLugar").val(),
                        "Desde": document.getElementById("CboxDesde").value,
                        "Hasta": document.getElementById("CboxHasta").value,
                        "HoraIni": $("#TxtHIni").val(),
                        "HoraFin": $("#TxtHFin").val(),
                        "Linea": document.getElementById("CboxLinea").value,
                        "Estado": document.getElementById("CboxEstado").value,
                        "Modalidad": document.getElementById("CboxModalidad").value,
                        "Unidad": document.getElementById("CboxUnidad").value,
                        "Profesor": document.getElementById("CboxProfesor").value,
                        "SegundoPro": document.getElementById("Cbox2doprofesor").value,
                        "ProExterno": $("#TxtProExterno").val(),
                        "Descripcion": $("#TxtDescripcion").val()
                    };

                    $.ajax({
                        body: parametros, //datos que se envian a traves de ajax
                        url: 'Services/GestionDeCursos', //URL del servicio
                        type: 'post', //método de envio
                        dataType: 'json',
                        data: JSON.stringify(parametros),
                        contentType: "application/json; charset=utf-8",
                        success: function(response) { //una vez que el archivo recibe el request lo procesa y lo devuelve

                            if (response.transaccion) {
                                $('#ModalExitoso').modal('show');
                                $("#Txtmessage").html(response.message);
                            } else {
                                $('#ModalError').modal('show');
                                $("#Txterror").html(response.message + response.error);
                            }
                            $("#Txterror").html(response.error);
                            document.getElementById("BtnCrear").value = "Crear";
                            document.getElementById("BtnCrear").disabled = false;
                        }
                    });
                });


            }


            function Listas() {
                Opciones();
                $.ajax({
                    type: "GET",
                    url: 'Utils/Listas',
                    dataType: "json",
                    success: function(data) {
                        console.log()
                        $.each(data, function(key, registro) {
                            var Opcion = parseInt(registro.tipo)
                            switch (Opcion) {
                                case 1:
                                    $("#CboxPublico").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                                    break;
                                case 2:
                                    $("#CboxLinea").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                                    break
                                case 3:
                                    $("#CboxModalidad").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                                    break;
                                case 4:
                                    $("#CboxEstado").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                                    break
                                case 5:
                                    $("#Cbox2doprofesor").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                                    $("#CboxProfesor").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                                    break;
                                case 6:
                                    $("#CboxUnidad").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                                    break
                                case 7:
                                    $("#CboxHasta").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                                    $("#CboxDesde").append('<option value=' + registro.Value + '>' + registro.Especificacion + '</option>');
                                    break
                            }



                        });
                    },
                    error: function(data) {
                        alert('error');
                    }
                });
            }
            window.onload = function() {
            };

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
            function InfoCurso()
            {


                if (document.getElementById("TxtCodCurso").value != "")
                    ;
                {
                    var parametros = {
                        "id": $("#TxtCodCurso").val(),
                    };
                    $.ajax({
                        body: parametros, //datos que se envian a traves de ajax
                        url: 'Services/InformacionCursos', //URL del servicio
                        type: 'post', //método de envio
                        dataType: 'json',
                        data: JSON.stringify(parametros),
                        contentType: "application/json; charset=utf-8",
                        success: function(response) { //una vez que el archivo recibe el request lo procesa y lo devuelve
                            $("#DivInfoExiste").html("");

                            if (response.response.informacion) {
                                document.getElementById("BtnEditar").disabled = false;
                                $("#TxtNomCurso").val(response.NombreCurso);
                                $("#TxtValor").val(response.Valor);
                                document.getElementById("CboxPublico").value = response.Publico;
                                $("#TxtPeriodo").val(response.Periodo);
                                $("#DateInicio").val(response.Inicio);
                                $("#DateFin").val(response.Fin);
                                $("#TxtHoras").val(response.Horas);
                                $("#TxtCupMax").val(response.CupoMax);
                                $("#TxtLugar").val(response.Lugar);
                                document.getElementById("CboxDesde").value = response.Desde;
                                document.getElementById("CboxHasta").value = response.Hasta;
                                $("#TxtHIni").val(response.HoraIni);
                                $("#TxtHFin").val(response.HoraFin);
                                document.getElementById("CboxLinea").value = response.Linea;
                                document.getElementById("CboxEstado").value = response.Estado;
                                document.getElementById("CboxModalidad").value = response.Modalidad;
                                document.getElementById("CboxUnidad").value = response.Unidad;
                                document.getElementById("CboxProfesor").value = response.Profesor;
                                document.getElementById("Cbox2doprofesor").value = response.SegundoPro;
                                $("#TxtProExterno").val(response.ProExterno);
                                $("#TxtDescripcion").val(response.Descripcion);
                            } else {
                                document.getElementById("BtnEditar").disabled = true;
                                document.getElementById("CboxPublico").selectedIndex = "0";
                                document.getElementById("CboxLinea").selectedIndex = "0";
                                document.getElementById("CboxModalidad").selectedIndex = "0";
                                document.getElementById("CboxEstado").selectedIndex = "0";
                                document.getElementById("CboxUnidad").selectedIndex = "0";
                                document.getElementById("CboxProfesor").selectedIndex = "0";
                                document.getElementById("Cbox2doprofesor").selectedIndex = "0";
                                $("#TxtNomCurso").val("");
                                $("#TxtValor").val("");
                                $("#TxtPeriodo").val("");
                                $("#DateInicio").val("");
                                $("#DateFin").val("");
                                $("#TxtHoras").val("");
                                $("#TxtCupMax").val("");
                                $("#TxtLugar").val("");
                                document.getElementById("CboxDesde").selectedIndex = "0";
                                document.getElementById("CboxHasta").selectedIndex = "0";
                                $("#TxtHIni").val("");
                                $("#TxtHFin").val("");
                                $("#TxtProExterno").val("");
                                $("#TxtDescripcion").val("");
                                $("#DivInfoExiste").html("NO SE ENCONTRO INFORMACIÓN CON ESTE CÓDIGO");

                            }
                            BloProExt();


                        }
                    });

                }


            }

            function BloProExt() {
                if (document.getElementById("Cbox2doprofesor").value != '25') {
                    document.getElementById("TxtProExterno").disabled = true;
                    document.getElementById("TxtProExterno").value = "";
                } else {
                    document.getElementById("TxtProExterno").disabled = false;
                    document.getElementById("TxtProExterno").required = true;

                }
                if (document.getElementById("CboxProfesor").value != '25') {
                    document.getElementById("TxtProExterno").disabled = true;
                    document.getElementById("TxtProExterno").value = "";
                } else {
                    if (document.getElementById("Cbox2doprofesor").value != '25') {
                        document.getElementById("TxtProExterno").disabled = true;
                        document.getElementById("TxtProExterno").value = "";
                    } else {
                        document.getElementById("TxtProExterno").disabled = false;
                        document.getElementById("TxtProExterno").required = true;
                    }
                }

            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestion De Cursos</title>        
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body style="background-color: black;" onload="Listas();">
        <%-- INICIO MODAL TRANSACCION CORRECTA--%>

        <!-- Modal / Ventana / Overlay en HTML -->
        <div id="ModalExitoso" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" style="color: #008542">¡TRANSSACCIÓN EXITOSA!</h4>
                    </div>
                    <div class="modal-body">
                        <p class="text-warning" id="Txtmessage"></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #990000;color: white">Aceptar</button>
                    </div>
                </div>
            </div>
        </div>

        <%-- FIN MODAL TRANSACCION CORRECTA--%>

        <%-- INICIO MODAL --%>

        <!-- Modal / Ventana / Overlay en HTML -->
        <div id="ModalError" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" style="color: red">¡ERROR!</h4>
                    </div>
                    <div class="modal-body">
                        <p class="text-warning"  id="Txterror"></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="parent.document.getElementById('myFormHome').reload();" style="background-color: #990000;color: white">Aceptar</button>
                    </div>
                </div>
            </div>
        </div>

        <%-- FIN MODAL --%>

        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-left: 4.3%;padding-right: 4.3%;height:100%;position: fixed;margin-bottom: 0;padding-bottom: 0;display:block;display:inline-block">




            <div class="col-xs-0 col-sm-0 col-md-1 col-lg-1">

            </div>

            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10" style="background-color: white;height:100%;overflow-y: auto;margin-bottom: 0;padding-bottom: 0;display:block;display:inline-block; overflow-x: hidden;">

                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"><br>
                    <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
                    <form method="POST" action="" id="myFormHome">
                        <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding: 0;margin: 0;width: 100%" >
                                <center><div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" style="padding: 0;margin: 0;" >
                                        <img src="Imagen/Logos-Escuela-OSIRIS.jpg" width="100%" height="100%"/></div>
                                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" style="padding: 0;margin: 0;" >
                                        <h2>Información del cursos</h2>
                                    </div>
                            </div>
                            <div class="row"> 
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <hr style="border-color: #990000">      
                                </div>

                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2" >
                                        <div class="form-group">
                                            <label>Opciones</label>
                                            <select class="form-control" id="CboxOpciones" onchange="Opciones();" >
                                                <option>Seleccione</option>
                                                <option value="0">Crear</option>
                                                <option selected="" value="1">Buscar</option>
                                            </select>
                                        </div> <br>
                                    </div>

                           
    </div>                  
                                
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <h2  class="text-center"><b>Información del cursos</b></h2><br><br>
                                </div>
                                <%-- MODULO 1 INICIO --%>
                                <div id="DivInfoExiste" style="color: red"></div>

<div class="panel-group" id="accordion">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding: 0">
                                    <hr style="border-color: #333333;margin: 0">   
                                    <h4 style="margin: 0;">
                                        <a style="color: #990000" data-toggle="collapse" data-parent="#accordion" href="#collapse1" >Información básica</a>
                                    </h4><br>
                                    <div id="collapse1" class="panel-collapse collapse">
                                        <div class="panel-body">

                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">  
                                                <div class="form-group">
                                                    <label>Código</label>
                                                    <input type="number" class="form-control" id="TxtCodCurso" onchange="InfoCurso();" placeholder="Código" min="0">
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">  
                                                <div class="form-group">
                                                    <label>Nombre</label>
                                                    <input required="" type="text" class="form-control" id="TxtNomCurso" placeholder="Ingrese el nombre del curso" maxlength="100">
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">  
                                                <div class="form-group">
                                                    <label>Valor</label>
                                                    <input required="" type="number" class="form-control" id="TxtValor" placeholder="Ingrese el valor" min="0">
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">  
                                                <div class="form-group">
                                                    <label>Público</label>
                                                    <select required="" class="form-control" id="CboxPublico">
                                                        <option selected="" value="">Seleccione</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>

                                    </div><hr style="border-color: #333333;margin: 0"> 

                                </div>  

                                <%-- MODULO 1 FIN --%>

                                <%-- MODULO 2 INICIO--%>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding: 0">
                                    <h4 style="margin: 0">
                                        <a style="color: #990000" data-toggle="collapse" data-parent="#accordion" href="#collapse2">Información del horario</a>
                                    </h4><br>
                                    <div id="collapse2" class="panel-collapse collapse">
                                        <div class="panel-body">

                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                                                <div class="form-group">
                                                    <label>Periodo</label>
                                                    <input maxlength="10" required="" type="number" class="form-control" id="TxtPeriodo" placeholder="Ingrese el periodo" min="0">
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                                                <div class="form-group">
                                                    <label>Inicio</label>
                                                    <input maxlength="10" required="" type="date" class="form-control" id="DateInicio">
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                                                <div class="form-group">
                                                    <label>Fin</label>
                                                    <input maxlength="10" required="" type="date" class="form-control" id="DateFin">
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                                                <div class="form-group">
                                                    <label>Horas</label>
                                                    <input required="" type="number" class="form-control" id="TxtHoras" placeholder="Ingrese el No. de horas" min="0">
                                                </div>
                                            </div>

                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                                                <div class="form-group">
                                                    <label>Desde</label>
                                                    <select required="" class="form-control" id="CboxDesde">
                                                        <option selected="" value="">Seleccione</option>
                                                        <option value="25">NO APLICA</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                                                <div class="form-group">
                                                    <label>Hasta</label>
                                                    <select required="" class="form-control" id="CboxHasta">
                                                        <option selected="" value="">Seleccione</option>
                                                        <option value="25">NO APLICA</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                                                <div class="form-group">
                                                    <label>Hora inicio</label>
                                                    <input maxlength="10" required="" type="time" class="form-control" id="TxtHIni" placeholder="Ingrese la hora de inicio">
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                                                <div class="form-group">
                                                    <label>Hora fin</label>
                                                    <input maxlength="10" required="" type="time" class="form-control" id="TxtHFin" placeholder="Ingrese la hora de fin">
                                                </div>
                                            </div>


                                        </div>
                                    </div><hr style="border-color: #333333;margin: 0"> 
                                </div>   
                                <%-- MODULO 2 FIN--%>

                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding: 0">
                                    <h4 class="control-label required" for="TxtCupMax" style="margin: 0">
                                        <a style="color: #990000" data-toggle="collapse" href="#collapse3">Información del tipo de curso</a>
                                    </h4><br>
                                    <div id="collapse3" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                                                <div class="form-group">
                                                    <label>Cupo máximo</label>
                                                    <input required="" type="number"  name="custom[name]" class="form-control" id="TxtCupMax" placeholder="Ingrese el cupo max" min="0">
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                                                <div class="form-group">
                                                    <label>Lugar</label>
                                                    <input maxlength="100" required="" type="text" class="form-control" id="TxtLugar" placeholder="Ingrese el lugar">
                                                </div>
                                            </div>			
                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                                                <div class="form-group">
                                                    <label>Línea</label>
                                                    <select class="form-control" id="CboxLinea" required="">
                                                        <option selected="" value="">Seleccione</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                                                <div class="form-group">
                                                    <label>Estado</label>
                                                    <select required="" class="form-control" id="CboxEstado">
                                                        <option selected="" value="">Seleccione</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                                                <div class="form-group">
                                                    <label>Modalidad</label>
                                                    <select required="" class="form-control" id="CboxModalidad">
                                                        <option selected="" value>Seleccione</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                                                <div class="form-group">
                                                    <label>Unidad</label>
                                                    <select required="" class="form-control" id="CboxUnidad">
                                                        <option selected="" value="">Seleccione</option>
                                                    </select>
                                                </div>
                                            </div>


                                        </div>
                                    </div><hr style="border-color: #333333;margin: 0"> 
                                </div>   

                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding: 0" >
                                    <h4 style="margin: 0">
                                        <a style="color: #990000" data-toggle="collapse" data-parent="#accordion" href="#collapse4">Información de profesor</a>
                                    </h4><br>
                                    <div id="collapse4" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                                                <div class="form-group">
                                                    <label>Profesor</label>
                                                    <select required="" class="form-control" id="CboxProfesor" onchange="BloProExt();">
                                                        <option selected="" value="">Seleccione</option>
                                                        <option value="25">NO APLICA</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                                                <div class="form-group">
                                                    <label>2do. Profesor</label>
                                                    <select required="" class="form-control" id="Cbox2doprofesor" required="" onchange="BloProExt();">
                                                        <option selected="" value="">Seleccione</option>
                                                        <option value="25">No aplica</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                                                <div class="form-group">
                                                    <label>Profesor externo</label>
                                                    <input maxlength="50" type="text" class="form-control" id="TxtProExterno" placeholder="Ingrese el profesor externo">
                                                </div>
                                            </div>
                                        </div>
                                    </div><hr style="border-color: #333333;margin: 0"> 
                                </div>

                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding: 0">
                                    <h4 style="margin: 0">
                                        <a style="color: #990000" data-toggle="collapse" data-parent="#accordion" href="#collapse5">Descripción</a>
                                    </h4>
                                    <div id="collapse5" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label>Descripción</label>
                                                <textarea maxlength="400" style="resize: vertical" type="text" class="form-control" id="TxtDescripcion" placeholder="Ingrese la descripción del curso(máximo 400 caracteres)" required="" rows="10"></textarea>
                                            </div>
                                        </div>
                                    </div><br>
                                </div>   
</div>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

                                </div>
                                <%-- MODULO 6 FIN--%>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="DivBtns"><br>
                                    <button class="btn" id="BtnCrear" value="Crear" style="background-color: #990000;color: white" onclick="GestionDeCursos();">Crear</button>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="DivBtnEditar"><br>
                                    <button class="btn" id="BtnEditar" value="Crear" style="background-color: #990000;color: white" onclick="GestionDeCursos();" >Editar</button>
                                </div>
                            </div>
                        </div>
                    </form> 
                    <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>

                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <hr style="border-color: #990000">      
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-bottom: 8%">
                        <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1" ></div>
                        <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10" >
                            <iframe id="IframeListCursos"  src="jsp/ListInscritos.jsp?Curso=1" style="border:0; overflow-y: auto;width: 100%; height: 500px;" >

                            </iframe>                   
                        </div>
                        <div class="col-xs-0 col-sm-0 col-md-1 col-lg-1" ></div>


                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"><br><br></div>
                    <div class="col-xs-1 col-sm-1 col-md-1 col-lg-12" style="background-color: #333333;margin-left:-15%;width: 120%;position: absolute; bottom: 0;margin-bottom: 0;text-align: center;color: white">
                        <br><p>Términos y condiciones * Política de uso de datos * Mapa del sitio<br>
                            © 2020 Todos los derechos reservados Escuela Colombiana de Ingeniería Julio Garavito</p><br>
                    </div>
                </div>
            </div>


        </div>    

    </body>
</html>