<%-- 
    Document   : Inicio
    Created on : 26/02/2020, 01:54:05 PM
    Author     : javier.tambo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
          <meta http-equiv="Expires" content="0">
  <meta http-equiv="Last-Modified" content="0">
  <meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
  <meta http-equiv="Pragma" content="no-cache">
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet/less" type="text/css" href="/GestionDeCursos/css/Hover.less">
        <script src="//cdnjs.cloudflare.com/ajax/libs/less.js/3.9.0/less.min.js" ></script>
        <script src="/GestionDeCursos/js/ListaDeCursos.js"></script>
        <script src="/GestionDeCursos/js/CrearYEditarCursos.js"></script>
        <script src="/GestionDeCursos/js/ValidarInfoUsu.js"></script>
        <link rel="stylesheet" type="text/css" href="/GestionDeCursos/css/ModalWindowEffects/css/default.css" />
        <link rel="stylesheet" type="text/css" href="/GestionDeCursos/css/ModalWindowEffects/css/component.css" />
        <script src="/GestionDeCursos/css/ModalWindowEffects/js/modernizr.custom.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.0/css/bootstrap.min.css"></script>
        <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
        <style>
            body,html{ height:100%; margin:0;color:black}

        </style>
    </head>

    <body style="background-color: black;" onload="ValidarInfoUsuario(2);">



        <input type="hidden"  id="BtnAprobado" class="md-trigger btn-primary" data-toggle="modal" data-target="#exampleModalCenter" data-modal="modal-4" data-toggle="modal" onclick="Aprobado(this.value);" />
        <input type="hidden" id="BtnRechazado" class="md-trigger btn-primary" data-toggle="modal" data-target="#exampleModalCenter" data-modal="modal-4Rechazado" data-toggle="modal" onclick="Rechazado(this.value);" />







        <!-- INICIO MODAL CATALOGO DE CURSOS -->
        <div id="ModalCertificados" class="modal fade" style="overflow-y: auto;">


            <%-- FIN MODAL TRANSACCION CORRECTA--%>
                <div class="col-xs-0 col-sm-0 col-md-2 col-lg-2"></div>
                <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                    <div class="modal-dialog" style="width: 90%;">
                        <div class="modal-content col-xs-12 col-sm-12 col-md-12 col-lg-12">

                            
<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="background-color: transparent;text-transform: none" >Regresar</button>

                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="overflow-x: scroll;width: 100%">
                                <h2  class="text-center"><b>Certificados de cursos</b></h2>
                                <br>
                                <input class="form-control" type="text" id="myInputCertificado" onkeyup="myFunctionCertificados()" placeholder="Busque el nombre del curso" title="Type in a name"><br>
                                <form method="POST" action="" id="myFormHome">
                                    <table id="myTableCertificados" class="table table-striped table-bordered" style="width:100%">

                                        <thead>
                                            <tr class="header">
                                                <th>Nombre del curso</th>
                                                <th>Profesor</th>
                                                <th>Horario</th>
                                                <th>Lugar</th>
                                                <th>Fec.Ini.</th>
                                                <th>Fec.Fin.</th>
                                                <th><center>Descargar</center></th>
                                        </tr>
                                        </thead>
                                        <tbody data-toggle="toggle" id="myTbodyCertificados"></tbody>
                                        <tfoot>
                                            <tr>
                                                <th>Nombre del curso</th>
                                                <th>Profesor</th>
                                                <th>Horario</th>
                                                <th>Lugar</th>
                                                <th>Fec.Ini.</th>
                                                <th>Fec.Fin.</th>
                                                <th><center>Descargar</center></th>
                                        </tr>
                                        </tfoot>
                                    </table>

                                </form> 
                            </div>

                            <div class="modal-footer"> </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-0 col-sm-0 col-md-2 col-lg-2"></div>
        </div>
        <!-- FIN MODAL CATALOGO DE CURSOS -->


        <!-- INICIO MODAL CATALOGO DE CURSOS -->
        <div id="ModalExitoso" class="modal fade" >


            <div class="modal-dialog">
                <div class="modal-content">


                    <div class="modal-body">
                        <div class="alert alert-warning" id="DivValidar">Confirmación para la inscripción del curso</div>
                        <div class="modal-footer">
                            <input type="button" id="BtnSuscripcion" class="btn btn-default" data-dismiss="modal" onclick="Inscribirse(this.value);" style="background-color: #990000;color: white" aria-hidden="true"/>
                            <button id="BtnCancelacion" class="btn btn-default" data-dismiss="modal" onclick="if (document.getElementById(this.value).checked) {
                document.getElementById(this.value).checked = false;
            } else {
                document.getElementById(this.value).checked = true;
            }" aria-hidden="true">Cancelar</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div id="ModalCatCursos" class="modal fade" style="overflow-y: auto;">

            <div class="md-modal md-effect-4" id="modal-4Rechazado" tabindex="-1" style="z-index: 2;background-color: transparent;position: fixed;" >

                <div class="md-content" style="background-color: transparent;">

                    <center>   
                        <img class="md-close" aria-hidden="true" class="img" id="Imgrechazado" src="/GestionDeCursos/Imagen/Iconos/cruzar.png" title="Catálogo" alt="Foto4"style="width: 35%;">   
                        <div style="color: red;display: block"><h4>¡RECHAZADO!</h4></div>
                    </center>



                </div>
            </div>

            <div class="md-modal md-effect-4" id="modal-4" tabindex="-1" style="z-index: 2;background-color: transparent;position: fixed" >


                <div class="md-content" style="background-color: transparent;">

                    <center>   
                        <img class="md-close" aria-hidden="true" class="img" id="ImgAprobado" src="/GestionDeCursos/Imagen/Iconos/garrapata.png" title="Catálogo" alt="Foto4"style="width: 35%;">   
                        <div style="color: #5cb85c;display: block"><h4>¡APROBADO!</h4></div>
                    </center>



                </div>
            </div> 
                <div class="col-xs-0 col-sm-0 col-md-2 col-lg-2"></div>
                <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                    <div class="modal-dialog" style="width: 90%;">
                        <div class="modal-content col-xs-12 col-sm-12 col-md-12 col-lg-12">




                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="background-color: transparent;text-transform: none" >Regresar</button>
                            <h2  class="text-center"><b>Catálogo de cursos</b></h2>
                            <div id="DivCorrecto" ></div>
                            <div id="DivError"></div>
                            <br>
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="overflow-x: scroll"><br>
                                <input class="form-control" type="text" id="myInputCatalogo" onkeyup="myFunctionCatalogo()" placeholder="Busque el nombre del curso" title="Type in a name"><br>
                                <form method="POST" action="" id="myFormHome">
                                    <table data-toggle="toggle" id="myTableCatalogo" class="table table-striped table-bordered" style="width:100%">

                                        <thead>
                                            <tr class="header">
                                                <th>Nombre del curso</th>
                                                <th>Profesor</th>
                                                <th>Horario</th>
                                                <th>Lugar</th>
                                                <th>Fec.Ini.</th>
                                                <th>Fec.Fin.</th>
                                                <th><center>Inscribir</center></th>
                                        </tr>
                                        </thead>
                                        <tbody id="myTbody" data-toggle="toggle"></tbody>
                                        <tfoot>
                                            <tr>
                                                <th>Nombre del curso</th>
                                                <th>Profesor</th>
                                                <th>Horario</th>
                                                <th>Lugar</th>
                                                <th>Fec.Ini.</th>
                                                <th>Fec.Fin.</th>
                                                <th><center>Inscribir</center></th>
                                        </tr>
                                        </tfoot>
                                    </table>

                                </form> 
                            </div>

                            <div class="modal-footer"> </div>
                        </div>
                    </div>
                </div>
        </div>            


    </center>
</div>
<!-- FIN MODAL CATALOGO DE CURSOS -->


<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-left: 4.3%;padding-right: 4.3%;position: absolute">




    <div class="col-xs-0 col-sm-0 col-md-1 col-lg-1">

    </div>

    <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10" style="background-color: white;height:100%;overflow-y: auto;margin-bottom: 0;padding: 0;overflow-x: hidden;">

        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding: 0">
            <div class="row" >
                <img class="img-responsive img-rounded" style="position: initial;width: 100%;padding: 0;margin: 0" src="/GestionDeCursos/Imagen/Generales/cabezote-interno.png"/>

            </div>


             <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"><br>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <label style="color: #990000" id="LblNombre"></label>
                    <input type="hidden" id="idemp"/>
                    <input type="hidden" id="idrol"/>
                </div>   
                 <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                   <a id="Salir" onclick="Salir();" href="#">Salir</a>
                </div>  
            </div>

            <center>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-bottom: 10%;margin-top: 15%">
                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"style="margin-bottom: 10%;">            
                        <a style="text-decoration:none;color:#990000" href="/GestionDeCursos/jsp/GestorDeCursos/GestorDeCursosPro.jsp">
                        <div class="div-img" >
                                <img class="img" src="/GestionDeCursos/Imagen/Iconos/Gestor.png" title="GestorDeCursos" alt="Foto4" style="width: 35%;">
                            
                        </div>                        
                            <div class="text"><br><b>Gestor de cursos</b></div></a>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"style="margin-bottom: 10%" onclick="ListaCursosInscribirse();">
<a style="text-decoration:none;color:#990000" data-toggle="modal" data-target="#ModalCatCursos" href="#" >
                        <div class="div-img" >
                                <img class="img" src="/GestionDeCursos/Imagen/Iconos/CatalogoCursos.png" title="Catalogo" alt="Foto4"style="width: 35%; margin: 0">
                           
                        </div>
 <div class="text"><br><b>Catálogo de cursos</b></div></a>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4"style="margin-bottom: 10%">
<a style="text-decoration:none;color:#990000" data-toggle="modal" data-target="#ModalCertificados"  href="#">
                        <div class="div-img" >
                                <img class="img" src="/GestionDeCursos/Imagen/Iconos/Certificados.png" title="Certificados" alt="Foto4" style="width: 35%">
                            
                        </div>
    <div class="text"><br><b>Certificados</b></div></a>
                    </div>

                </div>
            </center>

            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="background-color: #333333;width: 100%;position: static; bottom:0;margin-bottom: 0;text-align: center;color: white">
                <br><p>Términos y condiciones * Política de uso de datos * Mapa del sitio<br>
                    © 2020 Todos los derechos reservados Escuela Colombiana de Ingeniería Julio Garavito</p><br>
            </div>

        </div>
    </div>


</div>    
<script src="/GestionDeCursos/css/ModalWindowEffects/js/classie.js"></script>
<script src="/GestionDeCursos/css/ModalWindowEffects/js/modalEffects.js"></script>

</body>
</html>
