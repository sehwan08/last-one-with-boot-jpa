let index = {
	init: function() {
		$("#btn-save").on("click", () => { //function(){} 대신 ()=>{}를 써서 전역변수 this를 바인딩
			this.save();
		});
		
		$("#btn-update").on("click", ()=>{ 
			this.update();
		});
	},
	save: function() { //this.save()의 save
		//alert("user.js의 save 함수 호출됨");
		
		if($("#title").val()==""){
			alert("제목을 입력하세요.");
			$("title").focus();
			return false;
		}
		
		/*console.log($("#content").val())*/
		
		
		if($("#content").val()==""){
			alert("내용을 입력하세요.");
			$("content").focus();
			return false;
		}
		
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		//console.log(data);
		$.ajax({
			//ajax 기본 호출은 비동이
			//오브젝트가 들어오는 곳
			//통신 수행
			type: "post",
			url: "/blog/blogForm",
			data: JSON.stringify(data),//HTTP BODY DATA
			contentType:  "application/json; charset=utf-8", //body데이터의 타입
			dataType: "json" //서버에서 받을 데이터 형식, 즉 json으로 던지고 서버를위해 자동 파싱 = JSON->JS
		}).done(function(resp) { //위의 데이터가 js로 바뀌고 파라미터로 사용 가능
			//통신이 정상이면 done
			alert("글쓰기 완료");
			//alert(resp)
			console.log(resp);
			location.href = "/blog/blogMain";
		}).fail(function(error) {
			//통신이 비정상이면 fail
			alert(JSON.stringify(error));
		});
	},
	
	update: function(){
			let id = $("#id").val();
			
			let data = {
					title: $("#title").val(),
					content: $("#content").val()
			};

			$.ajax({ 
				type: "PUT",
				url:"/api/board/"+id,
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(resp){
				alert("글수정이 완료되었습니다.");
				location.href = "/board/"+id
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
}
index.init();