<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="kr">
<head>
<meta charset="UTF-8">
<title>http://www.blueb.co.kr</title>
<link href="/ogeul/css/page.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/ogeul/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/ogeul/js/jquery.vide.js"></script>
<style>
@import url(http://fonts.googleapis.com/earlyaccess/nanummyeongjo.css);
</style>
<script type="text/javascript">
	$(window).scroll(function() {
	  
	  // selectors
	  var $window = $(window),
	      $body = $('body'),
	      $panel = $('.panel');
	  
	  // Change 33% earlier than scroll position so colour is there when you arrive.
	  var scroll = $window.scrollTop() + ($window.height() / 3);
	 
	  $panel.each(function () {
	    var $this = $(this);
	    
	    // if position is within range of this panel.
	    // So position of (position of top of div <= scroll position) && (position of bottom of div > scroll position).
	    // Remember we set the scroll to 33% earlier in scroll var.
	    if ($this.position().top <= scroll && $this.position().top + $this.height() > scroll) {
	          
	      // Remove all classes on body with color-
	      $body.removeClass(function (index, css) {
	        return (css.match (/(^|\s)color-\S+/g) || []).join(' ');
	      });
	       
	      // Add class of currently active div
	      $body.addClass('color-' + $(this).data('color'));
	    }
	  });
	  
	}).scroll();

	/*
	var overlay= document.getElementById('overlay');
	
	var video= document.getElementById('v');
	video.addEventListener('progress', function() {
		var show= video.currentTime>=5 && video.currentTime<10;
		overlay.style.visibility = show? 'visible' : 'visible';
		}, false);
	*/
</script>
</head>
<body>
<div id="video_bg" style="width:100%; height:110%;"
    data-vide-bg="mp4: /ogeul/videos/ogeulvideo.mp4, poster: ./bg.jpg"
    data-vide-options="loop: true, position: 0% 50%"> 
   <!--
   <div style="text-align:center; padding-top:170px;">
      <a href='#'><img id="img" src='images/ogeulmain.png' alt="" align="center" /></a>
   </div>
   -->
</div>

<div class="panel" data-color="violet">
  <!--<h2>Violet panel</h2>-->
</div>
<div class="panel" data-color="indigo">
  <!--<h2>Indigo panel</h2>-->
</div>
<div class="panel" data-color="blue">  
  <!--<h2>Blue panel</h2>-->
</div>
<div class="panel" data-color="green">
  <!--<h2>Green panel</h2>-->
</div>
<div class="panel" data-color="yellow">
  <!--<h2>Yellow panel</h2>-->
</div>
<div class="panel" data-color="orange">
  <!--<h2>Orange panel</h2>-->
</div>
<div class="panel" data-color="red">
  <!--<h2>Red panel</h2>-->
</div>
 
<div class="panel" data-color="white">
	<div>
		<!--
		<video id="v" preload="auto" autoplay="true" loop="loop" muted="muted" volume="0">
			<source src="videos/ogeulvideo.mp4"> 
		</video> 
		-->
		<link href="https://fonts.googleapis.com/css?family=Rajdhani" rel="stylesheet">
		
		<div id="overlay" style="font-family: 'Rajdhani', sans-serif;">
	 		<table border="1" height="100%">
		 		<tr>
		 			<td style="font-size: 110px;">5geul</td>
		 		</tr>
		 		<tr>
		 			<td align="right">:   오감을 자극하는 글</td>
		 		</tr>
		 	</table>
		</div>
		<!--
		<div id="overlay" style="position: absolute; left: 670px; top: 580px; font-size: 25px;font-family: 'Nanum Myeongjo', serif;">
			:   사람들의 오감을 자극하는 글
		</div>
		-->
  		<!--<img src="images/linkicon2.png" width="260" height="260" style="position:absolute;  left: 850px; top: 250px;">--> 
  	</div>
</div>
<div class="panel" data-color="violet">
  <!--<h2>Violet panel</h2>-->
</div>
<div class="panel" data-color="indigo">
  <!--<h2>Indigo panel</h2>-->
</div>
<div class="panel" data-color="blue">  
  <!--<h2>Blue panel</h2>-->
</div>
<div class="panel" data-color="green">
  <!--<h2>Green panel</h2>-->
</div>
<div class="panel" data-color="yellow">
  <!--<h2>Yellow panel</h2>-->
</div>
<div class="panel" data-color="orange">
  <!--<h2>Orange panel</h2>-->
</div>
<div class="panel" data-color="red">
  <!--<h2>Red panel</h2>-->
</div>
</body>
</html>
