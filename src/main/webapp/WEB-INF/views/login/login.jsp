<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">
    
    <!-- 쿠키 값 조회해버리기 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="${cp}/js/js.cookie.js"></script>
    
    <script type="text/javascript">
//     	$(document).ready(function(){    // 맨 마지막에 실행되게 하는거메소드 // 문서로딩이 완료되고 나서 실행되는 부분
//     		console.log("document ready")
//     	});
		
// 		$(document).ready(function(){
// 		consol.log("remember" + $("#remember").html()); // 이건 지금 상태론 안된다. 왜냐면 #remember를 찾을수가없다 그래서 ready를 쓰는거임
// 		});
//     	console.log("after document ready")
    	//쿠키 이름에 해당하는 쿠키 값을 조회
    	
    	$(document).ready(function(){
    		//문서 로딩이 완료되고 나서 실행되는 부분
    		//remember checkbox
    		//1. remember cookie가 있는지? 있으면 값이 true 인지?
    		//1-1 remember가 true 이면 input id="remember" 체크박스를 체크
//     		console.log("ready");
    		
    		var remember = Cookies.get("remember");
    		if(remember == 'true'){
    			$("#remember").prop("checked", true);
    			$("#userId").val(Cookies.get("userId"));
    			$("#password").focus();
    		}
    		
    		//signin button 클릭시 실행되는 핸들러
    		$("#signinBtn").on("click", function(){
    			//만약에 remember 체크박스가 체크되어 있는경우
    			//사용자 아이디 값을 userId 쿠키로 저장
    			// true 값을 remember cookie 값으로 저장
    			
    			
//     			if($('#remember').is(":checked")){
//     				Cookies.set("userId", $("#userId").val(), {expires :30});
//     				Cookies.set("remember", "true", {expires :30});


    			//만약에 remember 체크박스가 해제되어 있는 경우
    			//userId, remember cookie 값을 삭제 
    			
//     			}else{
    				
//     				Cookies.remove("userId");
//     				Cookies.remove("remember");
//     			}
    			
    			//로그인 요청을 서버로 전송
    			$('#frm').submit();
				
    		});
    		
		});
	/*	
		//쿠키저장
		//exprires 받는 방식 설정 : 현재날짜로 부터 몇일동안 유효한지 일자(정수)
		function setCookie(cookieName, cookieValue, expires){
			var dt = new Date();
			dt.setDate(dt.getDate() + parseInt(expires));
			
			document.cookie = cookieName + "=" + cookieValue + "; path=/; expires=" + 
														dt.toGMTString();
		}
		//쿠키를 삭제
		function deleteCookie(cookieName){
			setCookie(cookieName, "", -5);
		}
    	
    	
    	
   		function getCookie(cookieName) {
		var cookieArray = document.cookie.split("; ");
		var cookieValue = "";
// 		for (String str : cookieArray) {
			for(i =0; i < cookieArray.length; i++){
				var str = cookieArray[i];
				if(str.startsWith(cookieName+"=")){
											
				var cookieStr = str.split("="); // 값을 뽑아 보내준다.
				cookieValue = cookieStr[1]; // [userId=brown]  [remember=true] [test=testValue] 이걸 한번더 짤랐으니깐
											//[userId][brown]  [remember][true] [test][testValue] 가 된니깐 1번째꺼 받는다.
				break;
			}
		}
		return cookieValue;
	}
	*/
    </script>
    
	
	
    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="${cp}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${cp}/css/signin.css" rel="stylesheet">
  </head>

  <body>
    <div class="container">

      <form id="frm" class="form-signin" action="${cp}/login" method="post">
      
        <h2 class="form-signin-heading">Please sign in ${cp}</h2>
      
        <label for="userId" class="sr-only">userId</label>
        <input name="userId" type="text" id="userId" class="form-control" placeholder="UserId"  required>
       
        <label for="inputPassword" class="sr-only">Password</label>
        <input name="password" type="password" id="password" class="form-control" placeholder="Password"  required>
       
        <div class="checkbox">
          <label>
            <input id="remember" name="remember" type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button id = "signinBtn" class="btn btn-lg btn-primary btn-block" type="button">Sign in</button>
      
      </form>

    </div> <!-- /container -->


  </body>
</html>