

# final Project

[Notion – The all-in-one workspace for your notes, tasks, wikis, and databases.](https://nice-ring-ae8.notion.site/8298a1641f6e4860bfe80d41025d1551)

[NAVER CLOUD PLATFORM (ncloud.com)](https://auth.ncloud.com/nsa/kdtk3)

- 네이버 클라우드 / 아이디 : group3-2 비밀번호 : multiteam3!
- ar tryon
  facial recognition
  face detection
  ar.js
  webAR
  BRFv4
  BRFv5

## 챗봇 만들기

1. 배송 현황 -> 배송 현황 페이지로 이동

```java
 
  BufferedReader in = new BufferedReader(
	                    new InputStreamReader(
	                            con.getInputStream(), "UTF-8"));
	            String decodedString;
	            String jsonString = "";
	            while ((decodedString = in.readLine()) != null) {
	                jsonString = decodedString;
	            }
	            
	            //받아온 값을 세팅하는 부분
JSONParser jsonparser = new JSONParser();
	            try {
	                JSONObject json = (JSONObject)jsonparser.parse(jsonString);
	                JSONArray bubblesArray = (JSONArray)json.get("bubbles");
	                JSONObject bubbles = (JSONObject)bubblesArray.get(0);
	                JSONObject data = (JSONObject)bubbles.get("data");
	                String description = "";
	                
	                description = (String)data.get("description");
	                if(description == null) {
	                	JSONObject cover = (JSONObject)data.get("cover");
	                	JSONObject data2 = (JSONObject)cover.get("data");
//	                	JSONArray contentTableArray = (JSONArray)data.get("contentTable");
//	                	JSONObject contentTable = (JSONObject)contentTableArray.get(0);
//	                	JSONObject data_button = (JSONObject)contentTable.get("data");
//	                	String title = (String)data_button.get("title");
//	                	System.out.println(title);

	                	String description2 = "";
	                	description2 =(String)data2.get("description"); 
	                	chatMessage = description2;
	                	
	                }
	                else {
	                	chatMessage = description;
	                }
	            } catch (Exception e) {
	                System.out.println("error");
	                e.printStackTrace();
	            }

	            in.close();
	        } else {  // 에러 발생
	            chatMessage = con.getResponseMessage();
	        }
	        return chatMessage;
```

- 수정할 것
  1. 줄 바꿈 하기
  2. 상담원 연결 수정되었는지 확인
  3. 글씨체 바꾸기
  
- 게시판 작성
  
  

[[spring\] 페이징 처리하기 (feat. Thymeleaf) :: 코딩 요리사의 레시피 (tistory.com)](https://pugyu.tistory.com/73)

https://ainiu.co.kr/board/notice/1/   쇼핑몰 참고

https://it-sunny-333.tistory.com/130  스프링 부트

https://srk911028.tistory.com/210  

https://po9357.github.io/spring/2019-05-21-Board_Write/

https://store.musinsa.com/app/cs/faq/8

```xml

		<!--    추가    -->
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-websocket</artifactId>
		</dependency>
		
		
		<dependency>
   			 <groupId>com.googlecode.json-simple</groupId>
    	     <artifactId>json-simple</artifactId>
    		 <version>1.1.1</version>
		</dependency>
		
		
		<dependency>
  			<groupId>org.webjars</groupId>
 		    <artifactId>webjars-locator-core</artifactId>
		</dependency>
		<dependency>
  			<groupId>org.webjars</groupId>
  			<artifactId>sockjs-client</artifactId>
  			<version>1.0.2</version>
		</dependency>
		<dependency>
  			<groupId>org.webjars</groupId>
  			<artifactId>stomp-websocket</artifactId>
  			<version>2.3.3</version>
		</dependency>
		<dependency>
  			<groupId>org.webjars</groupId>
 			<artifactId>bootstrap</artifactId>
  			<version>3.3.7</version>
		</dependency>
		<dependency>
  			<groupId>org.webjars</groupId>
  			<artifactId>jquery</artifactId>
  			<version>3.1.1-1</version>
		</dependency>
		
		<!-- JSON -->
		<dependency>
  			<groupId>org.json</groupId>
  			<artifactId>json</artifactId>
  			<version>20180813</version>
		</dependency>
```

DB

1. FAQ

```mysql
create table faq(
	faq_id int auto_increment primary key,
    faq_title varchar(200),
    faq_content varchar(1000),
    faq_category int
    );
    
insert into faq(faq_title,faq_content,faq_category) values('해외 배송이 가능한가요?','죄송합니다. 현재 해외 배송은 불가능합니다. 빠른 시일 안에 도입하도록 하겠습니다.',1);
insert into faq(faq_title,faq_content,faq_category) values('배송완료인데 아직 못받았어요.','택배사 또는 해당 지역의 택배 기사님 사정에 의해 실제 물품 전달일이 1~2일 지연될 수 있습니다.
주문 시 작성하신 수령 정보 및 배송 메시지를 확인 바랍니다.',1);
insert into faq(faq_title,faq_content,faq_category) values('제품을 수령했는데 파손되어 있어요.','수령 당시 택배 박스, 제품 포장재 등을 받으신 상태 그대로 보존하여 1:1문의글로 사진과 함께 접수 부탁드립니다.
상태 그대로를 보존하지 않으면 파손 사고 접수 시 확인이 어렵습니다.',1);
insert into faq(faq_title,faq_content,faq_category) values('사진과 제품의 실제 색상이 다른 것 같아요','온라인의 특성상 상품 이미지는 출력되는 모니터 환경에 따라 조금씩 차이가 있을 수 있으며 이는 판매자 사유에 의한 교환 및 환불 대상이 아닙니다.',2);
```

2. Notice

```mysql
create table notice(
	notice_id int auto_increment primary key,
	notice_title varchar(200), 
	notice_content blob, 
	notice_writer varchar(30), 
	notice_viewCnt int, 
	notice_date varchar(30)
    );
    
alter table qanda modify qna_category int;
```

## 토큰

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

enum('exchange/refund','undo','deliver','product','member','order','etc','report','event')



applcation

```html
spring.datasource.url=jdbc:mysql://localhost:3307/tryit?useUnicode=yes&characterEncoding=utf8&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=Multi1027!!

```

## 나이, 성별 인식

```javas
const video = document.getElementById("video");

Promise.all([
  faceapi.nets.tinyFaceDetector.loadFromUri("/models"),
  faceapi.nets.faceLandmark68Net.loadFromUri("/models"),
  /*faceapi.nets.faceRecognitionNet.loadFromUri("/models"),*/
  /*faceapi.nets.faceExpressionNet.loadFromUri("/models"),*/
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
  /*const canvas = faceapi.createCanvasFromMedia(video);
  document.body.append(canvas);*/
  //const displaySize = { width: video.width, height: video.height };
  /*faceapi.matchDimensions(canvas, displaySize);*/
  setInterval(async () => {
	
	const detections = await faceapi
      .detectAllFaces(video, new faceapi.TinyFaceDetectorOptions())
      .withFaceLandmarks()
	  .withAgeAndGender();

	detections.forEach(result=>{
		ageGender(result.age, result.gender);
	})

    //const resizedDetections = faceapi.resizeResults(detections, displaySize);
   /* canvas.getContext("2d").clearRect(0, 0, canvas.width, canvas.height);
    faceapi.draw.drawDetections(canvas, resizedDetections);
    faceapi.draw.drawFaceLandmarks(canvas, resizedDetections);*/




	//resizedDetections.forEach(result => {
       /* const { age, gender, genderProbability } = result*/
	    /*console.log(result.age);
		console.log(result.gender);*/
		
		//ageGender(result.age, result.gender);
		
        /*new faceapi.draw.DrawTextField(
          [
            `Age: ${faceapi.round(age, 0)} years`,
            `Gender: ${gender} (${faceapi.round(genderProbability)})`
          ],
          result.detection.box.bottomLeft
        ).draw(canvas)*/
     // })

	/*console.log(detections);
	console.log(detections.faceapi.age);*/
	
   /* faceapi.draw.drawFaceExpressions(canvas, resizedDetections);*/

  }, 100);
});


function ageGender(age,gender){
	
	$.ajax({
		type:"post",
		dataType : "text",
		async : false,
		url : "/ageGender",
		data : {age:age,gender:gender},
		success:function(){
			console.log("success");
		
		},
		
	});
	
	
}
```

slide-03.jpgslide-03.jpg

https://github.com/justadudewhohacks/face-api.js#models-age-and-gender-recognition
