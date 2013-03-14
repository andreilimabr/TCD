<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<div id="myCarousel" class="carousel slide">
    <!-- Carousel items -->
    <div class="carousel-inner">
	    <div class="active item">
	    	<img src="<c:url value='/resource/img/4.jpg'/>" style="width: 300px" />
	    	<div class="carousel-caption">
	    		<div class="row" style="margin-left: 10px">
	    			<div class="span4" >
			    			<h4>Destaque Especial</h4>
			    			<p>Guns n' Roses - Use Your illusion II
			    	</div>
		    		<div class="span3 offset2">
		    			<h1><p align="right">R$ 99,99</p></h1>	
		    		</div>
		    	</div>
	    	</div>
	    </div>
	    <div class="item">
	    	<img src="<c:url value='/resource/img/cover-metallica-ride_lightning.jpg'/>" style="width: 300px" />
	    	<div class="carousel-caption">
	    		<h4>Destaque Especial</h4>
	    		<p>Metallica - Ride Lightning
	    	</div>
	    </div>
	    <div class="item">
	    	<img src="<c:url value='/resource/img/slipknot.jpg'/>" style="width: 300px" />
	    	<div class="carousel-caption">
	    		<h4>Destaque Especial</h4>
	    		<p>Slipknot
	    	</div>
	    </div>
    </div>
    <!-- Carousel nav -->
    <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
    <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
   </div>