var isChecked = false; 
var isEmailchecked = false;

$("#btncheckId").click(function(){
	
	if($("#username").val()==""){
		alert("아이디를 입력해 주세요");
		$("#username").focus();
		return false;
	}
	
	var data = {
			"username":$("#username").val(),
	}
	$.ajax({
		type:"post",
		url:"/idCheck",
		contentType:"application/json; charset=utf-8",
		data: JSON.stringify(data)
	})
	.done(function(resp){
		if(resp=="success"){
			isChecked = true; //true 일떄만 폼이 날라가도록 
			alert("사용 가능한 아이디 입니다.");
		} else {
			alert("중복된 아이디 입니다.")
		}
	})
	.fail(function(){
		alert("failed");
	})
})

$("#username").on("propertychange change keyup paste input", function() {
   //alert("changed!");
	isChecked = false;
});


$("#btncheckEmail").click(function(){
	
	if($("#email").val()==""){
		alert("이메일을 입력해 주세요");
		$("#email").focus();
		return false;
	}
	
	var data = {
			"email":$("#email").val(),
	}
	$.ajax({
		type:"post",
		url:"/emailCheck",
		contentType:"application/json; charset=utf-8",
		data: JSON.stringify(data)
	})
	.done(function(resp){
		if(resp=="success"){
			isEmailchecked = true; //true 일떄만 폼이 날라가도록 
			alert("사용 가능한 이메일 입니다.");
		} else {
			alert("중복된 이메일 입니다.")
		}
	})
	.fail(function(){
		alert("failed");
	})
})

$("#username").on("propertychange change keyup paste input", function() {
   //alert("changed!");
	isChecked = false;
});

$("#email").on("propertychange change keyup paste input", function() {
   //alert("changed!");
	isEmailchecked = false;
});



function register(event){
	event.preventDefault()
	//alert("")
	if (isChecked == false) {
		alert("중복 체크를 먼저 하세요!")
		return false;
	}
	$("#myForm").submit();
}


