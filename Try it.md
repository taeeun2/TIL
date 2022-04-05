# Try it

1. AR.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<title>AR</title>
<meta charset='utf-8' />

<!-- 창 사이즈 최적화 -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />

<!-- script 넣기 -->
<script src='js/JeelizVTOWidget.js'></script>
<script defer
	src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>

<link href="https://fonts.googleapis.com/css?family=Roboto+Condensed"
	rel="stylesheet">

<!--  css 넣기 -->
<link rel='stylesheet' href='css/JeelizVTOWidget.css' />
<style>
.hi-slide {
	position: relative;
	width: 750px;
	height: 100px;
	margin: 10px auto 0;
	margin-bottom: -25px;
}

.hi-slide .hi-next, .hi-slide .hi-prev {
	position: absolute;
	top: 50%;
	width: 40px;
	height: 40px;
	margin-top: -40px;
	border-radius: 50px;
	line-height: 40px;
	text-align: center;
	cursor: pointer;
	background-color: #fff;
	color: black;
	transition: all 0.6s;
	font-size: 20px;
	font-weight: bold;
}

.hi-slide .hi-next:hover, .hi-slide .hi-prev:hover {
	opacity: 1;
	background-color: #fff;
}

.hi-slide .hi-prev {
	left: -60px;
}

.hi-slide .hi-prev::before {
	content: '<';
}

.hi-slide .hi-next {
	right: -60px;
}

.hi-slide .hi-next::before {
	content: '>';
}

.hi-slide>ul {
	list-style: none;
	position: relative;
	width: 750px;
	height: 292px;
	margin: 0;
	padding: 0;
}

.hi-slide>ul>li {
	overflow: hidden;
	position: absolute;
	z-index: 0;
	left: 377px;
	top: 146px;
	width: 0;
	height: 0;
	margin: 0;
	padding: 0;
	background-color: white;
	cursor: pointer;
}

.hi-slide>ul>li>img {
	width: 100%;
	height: 100%;
	background-position: center;
	object-fit: contain;
}
</style>
<script>


	let _isResized = false;
	function test_resizeCanvas() {
		
		// halves the height:
		let halfHeightPx = Math.round(window.innerHeight / 2).toString() + 'px';

		const domWidget = document.getElementById('JeelizVTOWidget');
		domWidget.style.maxHeight = (_isResized) ? 'none' : halfHeightPx;

		_isResized = !_isResized;
	}

	// entry point:
	function main() {
		var queryString = location.search.substring(1);
		JEELIZVTOWIDGET.start({
			sku : queryString,
			searchImageMask : "img/ar/target1.png",
			searchImageColor : 0xeeeeee,
			callbackReady : function() {
				console.log('INFO: JEELIZVTOWIDGET is ready :)');
		
				//추가
				var recommend = document.getElementById("recommend");
				recommend.style.display='block';
			},
			onError : function(errorLabel) { // 에러 잡기 시작
				alert('An error happened. errorLabel =' + errorLabel)
				switch (errorLabel) {
				case 'WEBCAM_UNAVAILABLE':
					break;

				case 'NOFILE':
					break;

				case 'WRONGFILEFORMAT':
					break;

				case 'INVALID_SKU':
					break;

				case 'FALLBACK_UNAVAILABLE':
					break;

				case 'PLACEHOLDER_NULL_WIDTH':
				case 'PLACEHOLDER_NULL_HEIGHT':
					break;

				case 'FATAL':
				default:
					break;
				} // 에러 잡기 종료
			} // end onError()
		}) // end JEELIZVTOWIDGET.start call
	} // end main()

	function load_modelBySKU() {
		const sku = prompt('Please enter a glasses model SKU:',
				'rayban_wayfarer_havane_marron');
		if (sku) {
			JEELIZVTOWIDGET.load(sku);
		}
	}
</script>
</head>
<body onload="main()">

	<div class='content' style="overflow: scroll;">
		<div class="slide hi-slide">
			<div class="hi-prev"></div>
			<div class="hi-next"></div>
			<ul>
				<li><img
					th:src="@{${'/img/products/single-product/preview/Carrera_1.jpg'}}"
					alt="Img 1"
					onclick="JEELIZVTOWIDGET.load('carrera_118S_black')" /></li>
				<li><img
					th:src="@{${'/img/products/single-product/preview/Rayban_25.jpg'}}"
					alt="Img 2"
					onclick="JEELIZVTOWIDGET.load('rayban_cockpit_noir_orange_gradient_flash')" /></li>
				<li><img
					th:src="@{${'/img/products/single-product/preview/Carrera_2.jpg'}}"
					alt="Img 3"
					onclick="JEELIZVTOWIDGET.load('carrera_119S_havana_blue')" /></li>
				<li><img
					th:src="@{${'/img/products/single-product/preview/Carrera_3.jpg'}}"
					alt="Img 4"
					onclick="JEELIZVTOWIDGET.load('carrera_6008_havane_green')" /></li>
				<li><img
					th:src="@{${'/img/products/single-product/preview/Catalyst_1.jpg'}}"
					alt="Img 5"
					onclick="JEELIZVTOWIDGET.load('catalyst_black_orange')" /></li>
				<li><img
					th:src="@{${'/img/products/single-product/preview/Rayban_2_2.jpg'}}"
					alt="Img 6"
					onclick="JEELIZVTOWIDGET.load('rayban_wayfarer_havane_vert')" /></li>
				<li><img
					th:src="@{${'/img/products/single-product/preview/Rayban_13.jpg'}}"
					alt="Img 7"
					onclick="JEELIZVTOWIDGET.load('rayban_clubmaster_noir_bleuGris')" /></li>
				<li><img
					th:src="@{${'/img/products/single-product/preview/Rayban_3.jpg'}}"
					alt="Img 8"
					onclick="JEELIZVTOWIDGET.load('rayban_wayfarer_denimOrange_orangeDegrade')" /></li>
				<li><img
					th:src="@{${'/img/products/single-product/preview/Rayban_12.jpg'}}"
					alt="Img 9"
					onclick="JEELIZVTOWIDGET.load('rayban_predator_noir_gris_mirroir')" /></li>
				<li><img
					th:src="@{${'/img/products/single-product/preview/Rayban_5.jpg'}}"
					alt="Img 10"
					onclick="JEELIZVTOWIDGET.load('rayban_aviator_gun_vert')" /></li>
				<li><img
					th:src="@{${'/img/products/single-product/preview/Rayban_6.jpg'}}"
					alt="Img 11"
					onclick="JEELIZVTOWIDGET.load('rayban_erika_marronArgent_marronVioletDegrade')" /></li>
				<li><img
					th:src="@{${'/img/products/single-product/preview/Rayban_7.jpg'}}"
					alt="Img 12"
					onclick="JEELIZVTOWIDGET.load('rayban_clubround_noir_cuivre_flash')" /></li>
				<li><img
					th:src="@{${'/img/products/single-product/preview/Rayban_8.jpg'}}"
					alt="Img 13"
					onclick="JEELIZVTOWIDGET.load('rayban_justin_noir_rougeMirroir')" /></li>
				<li><img
					th:src="@{${'/img/products/single-product/preview/Rayban_9.jpg'}}"
					alt="Img 14"
					onclick="JEELIZVTOWIDGET.load('rayban_clubround_noir_vertClassique_g15')" /></li>
				<li><img
					th:src="@{${'/img/products/single-product/preview/Rayban_10.jpg'}}"
					alt="Img 15"
					onclick="JEELIZVTOWIDGET.load('rayban_justin_noir_bleuMirroir')" /></li>
			</ul>
		</div>
		<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="js/jquery.hislide.js"></script>
		<script>
			$('.slide').hiSlide();
		</script>
		<!-- Please keep the same element IDs so that JEELIZVTOWIDGET can extract them from the DOM -->

		<!-- BEGIN JEELIZVTOWIDGET -->
		<!-- 
        div with id='JeelizVTOWidget' is the placeholder
        you need to size and position it according to where the VTO widget should be
        if you resize it, the widget will be automatically resized
      -->
		<div id='JeelizVTOWidget'>
			<!-- MAIN CANVAS: -->
			<!-- 
         canvas with id='JeelizVTOWidgetCanvas' is the canvas where the VTO widget will be rendered
         it should have CSS attribute position: absolute so that it can be resized without
         changing the total size of the placeholder
        -->
			<canvas id='JeelizVTOWidgetCanvas'></canvas>

			<div class='JeelizVTOWidgetControls JeelizVTOWidgetControlsTop'>
				<!-- ADJUST BUTTON: -->
				<button id='JeelizVTOWidgetAdjust'>
					<div class="buttonIcon">
						<i class="fas fa-arrows-alt"></i>
					</div>
					안경위치조정
				</button>

				<!-- RESIZE WIDGET BUTTON: -->
				<button id='buttonResizeCanvas' onclick='test_resizeCanvas();'>
					<div class="buttonIcon">
						<i class="fas fa-sync-alt"></i>
					</div>
					화면회전
				</button>
				
			</div>
			
			<!-- 버튼 추가 -->
			<div class='JeelizVTOWidgetControls JeelizVTOWidgetControlsBottom'>
				<button id ="recommend" onclick="location.href='http://localhost:8089/ageGender'">
						상품추천
				</button>
				
			</div>


				
			<!-- CHANGE MODEL BUTTONS: 
        <div class='JeelizVTOWidgetControls' id='JeelizVTOWidgetChangeModelContainer'>
          <button onclick="JEELIZVTOWIDGET.load('rayban_aviator_or_vertFlash')">Model 1</button>
          <button onclick="JEELIZVTOWIDGET.load('rayban_round_cuivre_pinkBrownDegrade')">Model 2</button>
          <button onclick="JEELIZVTOWIDGET.load_modelStandalone('glasses3D/glasses1.json')">Model 3</button>
          <button onclick="load_modelBySKU()">by SKU</button>
        </div>-->

			<!-- BEGIN ADJUST NOTICE -->
			<div id='JeelizVTOWidgetAdjustNotice'>
				안경의 위치를 조정하세요
				<button class='JeelizVTOWidgetBottomButton'
					id='JeelizVTOWidgetAdjustExit'>저장</button>
			</div>
			<!-- END AJUST NOTICE -->

			<!-- BEGIN LOADING WIDGET (not model) -->
			<div id='JeelizVTOWidgetLoading'>
				<div class='JeelizVTOWidgetLoadingText'>로딩중...</div>
			</div>
			<!-- END LOADING -->

		</div>

	</div>
</body>
</html>

```

2. ageGender.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Document</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  
  <script defer src="js/face-api.min.js"></script>
  <script defer src="js/script.js"></script>
  <style>
    body {
      margin: 0;
      padding: 0;
      width: 100vw;
      height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    /* canvas {
      position: absolute;
    } */
    /* 좌우반전 */
    video{       
		transform: rotateY(180deg);         
		-webkit-transform:rotateY(180deg); /* Safari and Chrome */         
		-moz-transform:rotateY(180deg); /* Firefox */
	}
	
	#center_message{
	  width : 100%;
	  height : 100%;
 	  font-weight:bold;
	  background-color:white;
	  position: absolute; 
 	  display: flex;
	  justify-content: center;
	  align-items: center;
	 
	}
  </style>
</head>
<body>


<video id="video" width="720" height="560" autoplay muted></video>
  <div id="center_message">
  	추천상품 검색중...
    <img src="http://localhost:8089/img/ar/Hourglass.gif">
  </div>


</body>
</html>
```

3. script.js

```js
const video = document.getElementById("video");

Promise.all([
  faceapi.nets.tinyFaceDetector.loadFromUri("/models"),
  faceapi.nets.faceLandmark68Net.loadFromUri("/models"),
  faceapi.nets.ageGenderNet.loadFromUri("/models"),
]).then(startVideo);

function startVideo() {
  navigator.mediaDevices
    .getUserMedia({ video: true })
    .then(function (stream) {
      video.srcObject = stream;
    })
    .catch(function (err) {
      console.log(err);
    });
}

video.addEventListener("playing", () => {
  
  setInterval(async () => {
	
	const detections = await faceapi
      .detectAllFaces(video, new faceapi.TinyFaceDetectorOptions())
      .withFaceLandmarks()
	  .withAgeAndGender();

	detections.forEach(result=>{
		//나이, 성별 값 받아와 controller로 보내는 함수 호출
		ageGender(result.age, result.gender, result.genderProbability);
	})


  }, 100);
});



var count=0;
var ageList = [];
var genderList = [];
var ProbabilityList = [];
var chooseGender = [];
var totalAge = 0;
var male = 0;
var female = 0;

function ageGender(age,gender,genderProbability){
	
	ageList.push(age);
	genderList.push(gender);
	ProbabilityList.push(genderProbability);
	count++;
	
	if(count>10){
		for(var i=0;i<count;i++){
			totalAge +=ageList[i];
			
			if(ProbabilityList[i]>0.80){
				chooseGender.push(genderList[i]);
			}
		}
		
		var age = totalAge/count;
		
		
		for(var i=0;i<chooseGender.length;i++){
			if(chooseGender[i]=="male")
				male++;
			else
				female++;
		}
		
		if(male>female)
			gender = "male";
		else
			gender = "female";
		
		sendAgeGender(age,gender);
		
	}
}


function sendAgeGender(age,gender){
	
	
		
	location.href="ar?age="+age+"&gender="+gender;
	reset();
}


function reset(){
	 count=0;
	 ageList = [];
	 genderList = [];
	 ProbabilityList = [];
	 chooseGender = [];
	 totalAge = 0;
	 male = 0;
	 female = 0;
 }
```

4. JeelizVTOWidget.css

```css
* {
  margin: 0;
}

html, body {
  height: 100%;
}

body {
  overflow: hidden;
  background-color: white;
  text-align: center;
  font-family: Helvetica Neu, Arial, sans-serif;
  margin: 0;
  padding: 0;
  color: #444;
  box-sizing: border-box;
}

button, select {
  cursor: pointer;
  background: rgba(0, 0, 0, 0.5);
  color: #ddd;
  font-weight: bold;
  font-variant: small-caps;  
  font-size: 16px;
  padding: 8px;
  min-width: 120px;
  border: none;
  margin: 0;
  -webkit-appearance: none;
}

button:hover {
  background: rgba(0, 0, 0, 0.8);
  color: white;
}

.content{
  /*height: 100vh;*/
  height: 100%;
  width: 100vw;
  display: flex;
  flex-direction: column;
}

.header {
  width: 100%;
  height: 70px;
  box-shadow: 0 0 3px black;
  background-color: rgb(12, 127, 194);
  color: white;
}
.headerTitle {
  font-size: 24pt;
  font-family: 'Roboto Condensed', sans-serif;
  font-weight: bold;
  margin-top: 12px;
}


#JeelizVTOWidget {
  flex-grow: 1;
  width: 100%;
  max-width: calc(100vh - 70px);
  margin-left: auto;
  margin-right: auto;
  position: relative;
}

#JeelizVTOWidgetCanvas {
  position: absolute;
  z-index: 1;
  left: 0;
  right: 0;
  margin: 0 auto;
}

.JeelizVTOWidgetControls {
  position: absolute;
  z-index: 2;
  width: 100%;
}
.buttonIcon {
  display: inline-block;
  margin-right: 8px;
}

.JeelizVTOWidgetControlsTop {
  top: 16px;
  height: 40px;
}
.JeelizVTOWidgetControlsTop > button {
   width: 45%;
   min-width: auto;
}

/*추가*/

.JeelizVTOWidgetControlsBottom{
	bottom:16px;
  	height: 40px;
}

.JeelizVTOWidgetControlsBottom > button {
   width: 80%;
   margin-left:65px;
}

/***********/


#JeelizVTOWidgetChangeModelContainer {
  bottom: 16px;
  height: 40px;  
}
#JeelizVTOWidgetChangeModelContainer > button {
  width: 20%;
  min-width: auto;
}

#JeelizVTOWidgetLoading {
  position: fixed;
  z-index: 4;
  cursor: wait;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0,0,0,0.5);
}
.JeelizVTOWidgetLoadingText {
  color: white;
  font-size: 32pt;
  margin-top: 40vh;
}

#JeelizVTOWidgetAdjustNotice {
  width: 100%;
  position: absolute;
  background-color: rgba(0,0,0,0.5);
  color: white;
  z-index: 3;
  font-size: 16px;
  bottom: 0;  
  justify-content: center;
  padding-top: 2em;
  padding-bottom: 2em;
  display: none;
}

#JeelizVTOWidgetAdjustExit {
  border: 1px solid white;
  background: transparent;
  position: absolute;
  right: 8px;
  bottom: 8px;
}

/* Hide everything, stuffs will be displayed by JeelizVTOWidget: */
#recommend{
 display: none;
}
#JeelizVTOWidgetAdjust {
  display: none;
}
#JeelizVTOWidgetChangeModelContainer {
  display: none;
}
#buttonResizeCanvas {
  display: none;
}
```





