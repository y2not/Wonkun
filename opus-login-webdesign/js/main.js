$(".back_arr").click(function(){
	window.history.back();
});

$(".rbpop_box_next_btn, .rbpop_box_close_btn").click(function() {
	$(".rbpop_box").hide("100");
}); 
	
$("input:text[numberOnly]").on("keyup", function() { // text박스에 숫자만 입력가능
  $(this).val($(this).val().replace(/[^0-9]/g,""));
});

$('[type=text], [type=password]').on('focus',function(){ 
	$(this).prev().animate({opacity:1}, 300);
	$(this).css('border-bottom', '2px solid #0e7bfc');
}).on('focusout',function(){
	if($(this).val().length != 0){ // 값이 있을때 
		//console.log("1: " + $('[type=text], [type=password]').val().length);
		$(this).prev().animate({opacity:1}, 300);
		$(this).css('border-bottom', '2px solid #0e7bfc');
	}else{ // 값이 없을때
		//console.log("2: " + $('[type=text], [type=password]').val().length);
		$(this).prev().animate({opacity:0}, 100);
		$(this).css('border-bottom', '1px solid #b2b2b2');
	}
})

//welcome_back 코드
$(".input_email, .input_psw").keyup(function () {
	if ($("[name=input_email]").val().length != 0 && $("[name=input_psw]").val().length != 0) {
		$(".left_bg .left_bg_box2 .lbnext_btn").css({ 'border': '1px solid #4a00e0', 'color': '#ffffff', 'background': '#4a00e0' });
	} else {
		$(".left_bg .left_bg_box2 .lbnext_btn").css({ 'border': '1px solid #e8e8e8', color: '#e8e8e8', 'background': '#ffffff' });
	}
});