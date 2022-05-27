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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestor decursos</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet/less" type="text/css" href="/GestionDeCursos/css/Hover.less">
        <script src="//cdnjs.cloudflare.com/ajax/libs/less.js/3.9.0/less.min.js" ></script>
        <script src="/GestionDeCursos/js/ListaDeCursos.js"></script>
        <script src="/GestionDeCursos/js/CrearYEditarCursos.js"></script>
        <script src="/GestionDeCursos/js/ListaDeInscritos.js"></script>
        <script src="/GestionDeCursos/js/ValidarInfoUsu.js"></script>
         <script src="/GestionDeCursos/js/Reporte.js"></script>
        <link rel="stylesheet" type="text/css" href="/GestionDeCursos/css/ModalWindowEffects/css/default.css" />
        <link rel="stylesheet" type="text/css" href="/GestionDeCursos/css/ModalWindowEffects/css/component.css" />
        <script src="/GestionDeCursos/css/ModalWindowEffects/js/modernizr.custom.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
        <style>
            body,html{ height:100%; margin:0;color:black}
        </style>
    </head>

    <body style="background-color: black;" onload="ValidarInfoUsuarioGesPro(3);">



        <input type="hidden"  id="BtnAprobado" class="md-trigger btn-primary" data-toggle="modal" data-target="#exampleModalCenter" data-modal="modal-4" data-toggle="modal" onclick="cerrar();" />
        <input type="hidden" id="BtnRechazado" class="md-trigger btn-primary" data-toggle="modal" data-target="#exampleModalCenter" data-modal="modal-4Rechazado" data-toggle="modal" onclick="cerrarr();" />



            <!-- INICIO MODAL REPORTES-->
            <div id="ModalReportes" class="modal fade">
                    <div class="col-xs-0 col-sm-0 col-md-2 col-lg-2"></div>
                <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                    <div class="modal-dialog" style="width: 90%;">
                        <div class="modal-content col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="background-color: transparent;text-transform: none" >Regresar</button>
                        <h2  class="text-center"><b>Reportes</b></h2><br>
                         <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"><br>
                                        <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3" >
                                            <div class="form-group">
                                                <label>Estado</label>
                                                <select class="form-control" id="CboxEstadoReporte">
                                                    <option value="null">Seleccione</option>
                                                </select>
                                            </div> 
                                        </div>
                             <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3" >
                                            <div class="form-group">
                                                <label>Tipo de Curso</label>
                                                <select class="form-control" id="CboxTipoCursoReporte">
                                                    <option value="null">Seleccione</option>
                                                </select>
                                            </div> 
                                        </div>
                              <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3" >
                                            <div class="form-group">
                                                <label>Público</label>
                                                <select class="form-control" id="CboxPublicoReporte">
                                                    <option value="null">Seleccione</option>
                                                </select>
                                            </div>
                                        </div>
                              
                              <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3" >
                                            <div class="form-group">
                                                <label>Unidad</label>
                                                <select class="form-control" id="CboxUnidadReporte">
                                                    <option value="null">Seleccione</option>
                                                </select>
                                            </div> 
                                        </div>
                              <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3" >
                                            <div class="form-group">
                                                <label>Estudiante</label>
                                                <input class="form-control" type="text" id="TxtNomEstudiante" placeholder="Nombre del estudiante">
                                            </div> 
                                        </div>
                              <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3" >
                                            <div class="form-group">
                                                <label>Curso</label>
                                                  <select class="form-control" id="CboxCursos">
                                        <option value="null">Seleccione</option>
                                    </select>
                                            </div> 
                                        </div>
                                    </div>     
                         <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <button onclick="exportTableToExcel('tblData');" class="btn" id="BtnExportar" value="Crear" style="background-color: #990000;color: white" >Exportar</button>
                        <button onclick="ReporteGeneral(1);" class="btn" id="BtnGenerar" value="Crear" style="background-color: #990000;color: white" >Generar</button>
                         </div>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="overflow-x: scroll"><br>
                                    <table id="tblData" class="table table-striped table-bordered" style="width:100%">
                                        
                                        <thead>
                                             <tr class="header">
                                                <th>Código</th>
                                                <th>Nombre del curso</th>
                                                <th>Valor</th>
                                                <th>Publico</th>
                                                <th>Periodo</th>
                                                <th>Inicio Curso</th>
                                                <th>Fin Curso</th>
                                                <th>No. Horas</th>
                                                <th>Cupo Max</th>
                                                <th>Lugar</th>
                                                <th style="width: 60px">Horario</th>
                                                <th>Tipo curso</th>
                                                <th>Línea</th>
                                                <th>Estado</th>
                                                <th>Modalidad</th>
                                                <th>Unidad</th>
                                                <th>Profesor principal</th>
                                                <th>Segundo profesor</th>
                                                <th>Profesor externo</th>
                                                <th>Descripción</th>
                                                <th>Documento inscrito</th>
                                                <th>Inscrito</th>
                                                <th>Fecha de inscripción</th>
                                                <th>Nombre completo</th>
                                                <th>Nota</th>
                                                <th>Quien califico</th>
                                                <th>Fecha Calificación</th>
                                        </tr>
                                        </thead>
                                        <tbody id="myTbodyReporte"></tbody>
                                    </table>

                                </div>
                            
                            <div class="modal-footer"> </div>
</table>
                    </div>
                    </div>
                </div>
            </div>
            <!-- INICIO MODAL REPORTES-->



            <!-- INICIO MODAL CATALOGO DE CURSOS -->
            <div id="ModalCatCursos" class="modal col-xs-12 col-sm-12 col-md-12 col-lg-12" style="overflow-y: auto">

                   <div class="md-modal md-effect-4" id="modal-4Rechazado" tabindex="-1" style="z-index: 2;background-color: transparent;position: fixed" >

                    <div class="md-content" style="background-color: transparent;">
                        transparent
                        <center>   
                            <img class="md-close" aria-hidden="true" class="img" id="Imgrechazado" src="../../Imagen/Iconos/cruzar.png" title="Catalogo" alt="Foto4"style="width: 35%;">   
                            <div style="color: red;display: block"><h4>¡RECHAZADO!</h4></div>
                        </center>



                    </div>
                </div>
                <div class="md-modal md-effect-4" id="modal-4" tabindex="-1" style="z-index: 2;background-color: transparent;position: fixed" >

                    <div class="md-content" style="background-color: transparent;">

                        <center>   
                            <img class="md-close" aria-hidden="true" class="img" id="ImgAprobado" src="../../Imagen/Iconos/garrapata.png" title="Catalogo" alt="Foto4"style="width: 35%;">   
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
                                <div id="DivCorrecto" style="display: none" class="alert alert-success"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a></div>
                                <div id="DivError" style="display: none" class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a></div>
                                <br>
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="overflow-x: scroll"><br>
                                    <select class="form-control" id="myInput" onchange="myFunctionListaInscritos()">
                                        <option value="">Seleccione curso</option>
                                    </select>
                                    
                                    <table id="myTableListInscritos" class="table table-striped table-bordered" style="width:100%">
                                        
                                        <thead>
                                            <tr class="header">
                                                <th>Nombre del curso</th>
                                                <th>Estudiante</th>
                                                <th>Horario</th>
                                                <th>Lugar</th>
                                                <th>Fec.Ini.</th>
                                                <th>Fec.Fin.</th>
                                                <th><center>Calificar</center></th>
                                        </tr>
                                        </thead>
                                        <tbody id="myTbodyListInscritos"></tbody>
                                        <tfoot>
                                            <tr>
                                                <th>Nombre del curso</th>
                                                <th>Estudiante</th>
                                                <th>Horario</th>
                                                <th>Lugar</th>
                                                <th>Fec.Ini.</th>
                                                <th>Fec.Fin.</th>
                                                <th><center>Calificar</center></th>
                                        </tr>
                                        </tfoot>
                                    </table>

                                </div>
                            
                            <div class="modal-footer"> </div>
                        </div>
                    </div>
                </div>
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
                        <b><span style="color:#990000"><a style="color: #990000" href="/GestionDeCursos/jsp/InicioProAdm.jsp">Inicio </a>/ Estas aquí</span></b>
                    </div>
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
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-bottom: 10%;margin-top: 15%;">
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6"style="margin-bottom: 10%">
<a style="text-decoration:none;color:#990000" href="#" data-toggle="modal" data-target="#ModalCatCursos" onclick="ListaInscritos();">
                                   
                            <div class="div-img" > <img class="img" src="/GestionDeCursos/Imagen/Iconos/CatalogoCursos.png" title="Catalogo" alt="Foto4"style="width: 35%;" onclick=" ListasRepPro(); ">
                               
                            </div>
 <div class="text"><br><b>Catálogo de cursos</b></div></a>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6"style="margin-bottom: 10%;">
<a style="text-decoration:none;color:#990000" href="#" data-toggle="modal" data-target="#ModalReportes">
                            <div class="div-img" >
                                <img class="img" src="/GestionDeCursos/Imagen/Iconos/Reportes.png" title="Reportes" alt="Foto4" style="width: 35%" onclick=" ListasRepPro(); ">
                                

                            </div>
    <div class="text"><br><b>Reportes</b></div></a>
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
        <script src="../../css/ModalWindowEffects/js/classie.js"></script>
        <script src="../../css/ModalWindowEffects/js/modalEffects.js"></script>

    </body>
</html>
