/**
 * question 클래스에 속한 p태그를 누르면 숨겨져 있던 answer 태그가 등장한다.
 **/

	$(document).ready(function() {
		$('#form_button').click(sendEmail);
		
		$('#openform').click(openForm);
		
		$('#question').mouseover(changeColor);
		$('#question').mouseout(changeColor2);
		$('#question').click(getAnswer);

		$('#question1').mouseover(changeColor3);
		$('#question1').mouseout(changeColor4);
		$('#question1').click(getAnswer1);

		$('#question2').mouseover(changeColor5);
		$('#question2').mouseout(changeColor6);
		$('#question2').click(getAnswer2);

		$('#question3').mouseover(changeColor7);
		$('#question3').mouseout(changeColor8);
		$('#question3').click(getAnswer3);

		$('#question4').mouseover(changeColor9);
		$('#question4').mouseout(changeColor10);
		$('#question4').click(getAnswer4);

		$('#question5').mouseover(changeColor11);
		$('#question5').mouseout(changeColor12);
		$('#question5').click(getAnswer5);

		$('#question6').mouseover(changeColor13);
		$('#question6').mouseout(changeColor14);
		$('#question6').click(getAnswer6);

		$('#question7').mouseover(changeColor15);
		$('#question7').mouseout(changeColor16);
		$('#question7').click(getAnswer7);

		$('#question8').mouseover(changeColor17);
		$('#question8').mouseout(changeColor18);
		$('#question8').click(getAnswer8);

		$('#question9').mouseover(changeColor19);
		$('#question9').mouseout(changeColor20);
		$('#question9').click(getAnswer9);

		$('#question10').mouseover(changeColor21);
		$('#question10').mouseout(changeColor22);
		$('#question10').click(getAnswer10);

		$('#question11').mouseover(changeColor23);
		$('#question11').mouseout(changeColor24);
		$('#question11').click(getAnswer11);

		$('#question12').mouseover(changeColor25);
		$('#question12').mouseout(changeColor26);
		$('#question12').click(getAnswer12);

		$('#question13').mouseover(changeColor27);
		$('#question13').mouseout(changeColor28);
		$('#question13').click(getAnswer13);

		$('#question14').mouseover(changeColor29);
		$('#question14').mouseout(changeColor30);
		$('#question14').click(getAnswer14);

		$('#question15').mouseover(changeColor31);
		$('#question15').mouseout(changeColor32);
		$('#question15').click(getAnswer15);

		$('#question16').mouseover(changeColor33);
		$('#question16').mouseout(changeColor34);
		$('#question16').click(getAnswer16);

		$('#question17').mouseover(changeColor35);
		$('#question17').mouseout(changeColor36);
		$('#question17').click(getAnswer17);

		$('#question18').mouseover(changeColor37);
		$('#question18').mouseout(changeColor38);
		$('#question18').click(getAnswer18);

		$('#question19').mouseover(changeColor39);
		$('#question19').mouseout(changeColor40);
		$('#question19').click(getAnswer19);

		$('#question20').mouseover(changeColor41);
		$('#question20').mouseout(changeColor42);
		$('#question20').click(getAnswer20);

	});
	
	function sendEmail() {
	    var form = $('#form')[0];
	    var formData = new FormData(form);

		$.ajax({
			url : 'qnaemail',
			type : 'post',
			data : formData,
			success : function() {
				alert('success');
				},
			error : function() {
				alert('error');
			},
		    cache: false,
		    contentType: false,
		    processData: false
		});
	}

	
	function openForm() {
			if ($('#qnaform').css('opacity') == 0) {
				$('#qnaform').css('animation', 'formopen');
				$('#qnaform').css('animation-duration', '0.5s');
				$('#qnaform').css('display', 'block');
				$('#qnaform').css('opacity', 1);

			}	else	{
				$('#qnaform').css('animation', 'formclose');
				$('#qnaform').css('display', 'none');
				$('#qnaform').css('opacity', 0);
			}
	}
	
	function changeColor() {
			$('#question').css("color","#2396DE");
	}

	function changeColor2() {
			$('#question').css("color","#2C2E33");
	}
	
	function getAnswer() {
		if ($('#answer').css('opacity') == 0) {
			$('#question').css("font-weight","bold");
			$('#question').css("color","#2396DE");
			$('#answer').css('display', 'block');
			$('#answer').css('opacity', 1);
		}else	{
			$('#question').css("font-weight","normal");
			$('#question').css("color","#2C2E33");
			$('#answer').css('display', 'none');
			$('#answer').css('opacity', 0);
		}

	};

	
	function changeColor3() {
			$('#question1').css("color","#2396DE");
	}
	
	function changeColor4() {
			$('#question1').css("color","#2C2E33");
	}

	function getAnswer1() {
		if ($('#answer1').css('opacity') == 0) {
			$('#question1').css("font-weight","bold");
			$('#question1').css("color","#2396DE");
			$('#answer1').css('display', 'block');
			$('#answer1').css('opacity', 1);
		}else	{
			$('#question1').css("font-weight","normal");
			$('#question1').css("color","#2C2E33");
			$('#answer1').css('display', 'none');
			$('#answer1').css('opacity', 0);
		}

	};

	
	function changeColor5() {
		$('#question2').css("color","#2396DE");
	}
	
	function changeColor6() {
			$('#question2').css("color","#2C2E33");
	}
	
	function getAnswer2() {
		if ($('#answer2').css('opacity') == 0) {
			$('#question2').css("font-weight","bold");
			$('#question2').css("color","#2396DE");
			$('#answer2').css('display', 'block');
			$('#answer2').css('opacity', 1);
		}else	{
			$('#question2').css("font-weight","normal");
			$('#question2').css("color","#2C2E33");
			$('#answer2').css('display', 'none');
			$('#answer2').css('opacity', 0);
		}
	
	};

	
	function changeColor7() {
		$('#question3').css("color","#2396DE");
	}
	
	function changeColor8() {
			$('#question3').css("color","#2C2E33");
	}
	
	function getAnswer3() {
		if ($('#answer3').css('opacity') == 0) {
			$('#question3').css("font-weight","bold");
			$('#question3').css("color","#2396DE");
			$('#answer3').css('display', 'block');
			$('#answer3').css('opacity', 1);
		}else	{
			$('#question3').css("font-weight","normal");
			$('#question3').css("color","#2C2E33");
			$('#answer3').css('display', 'none');
			$('#answer3').css('opacity', 0);
		}
	
	};

	function changeColor9() {
		$('#question4').css("color","#2396DE");
	}
	
	function changeColor10() {
			$('#question4').css("color","#2C2E33");
	}
	
	function getAnswer4() {
		if ($('#answer4').css('opacity') == 0) {
			$('#question4').css("font-weight","bold");
			$('#question4').css("color","#2396DE");
			$('#answer4').css('display', 'block');
			$('#answer4').css('opacity', 1);
		}else	{
			$('#question4').css("font-weight","normal");
			$('#question4').css("color","#2C2E33");
			$('#answer4').css('display', 'none');
			$('#answer4').css('opacity', 0);
		}
	
	};

	
	function changeColor11() {
		$('#question5').css("color","#2396DE");
	}
	
	function changeColor12() {
			$('#question5').css("color","#2C2E33");
	}
	
	function getAnswer5() {
		if ($('#answer5').css('opacity') == 0) {
			$('#question5').css("font-weight","bold");
			$('#question5').css("color","#2396DE");
			$('#answer5').css('display', 'block');
			$('#answer5').css('opacity', 1);
		}else	{
			$('#question5').css("font-weight","normal");
			$('#question5').css("color","#2C2E33");
			$('#answer5').css('display', 'none');
			$('#answer5').css('opacity', 0);
		}
	
	};

	
	function changeColor13() {
		$('#question6').css("color","#2396DE");
	}
	
	function changeColor14() {
			$('#question6').css("color","#2C2E33");
	}
	
	function getAnswer6() {
		if ($('#answer6').css('opacity') == 0) {
			$('#question6').css("font-weight","bold");
			$('#question6').css("color","#2396DE");
			$('#answer6').css('display', 'block');
			$('#answer6').css('opacity', 1);
		}else	{
			$('#question6').css("font-weight","normal");
			$('#question6').css("color","#2C2E33");
			$('#answer6').css('display', 'none');
			$('#answer6').css('opacity', 0);
		}
	
	};

	
	function changeColor15() {
		$('#question7').css("color","#2396DE");
	}
	
	function changeColor16() {
			$('#question7').css("color","#2C2E33");
	}
	
	function getAnswer7() {
		if ($('#answer7').css('opacity') == 0) {
			$('#question7').css("font-weight","bold");
			$('#question7').css("color","#2396DE");
			$('#answer7').css('display', 'block');
			$('#answer7').css('opacity', 1);
		}else	{
			$('#question7').css("font-weight","normal");
			$('#question7').css("color","#2C2E33");
			$('#answer7').css('display', 'none');
			$('#answer7').css('opacity', 0);
		}
	
	};

	function changeColor17() {
		$('#question8').css("color","#2396DE");
	}
	
	function changeColor18() {
			$('#question8').css("color","#2C2E33");
	}
	
	function getAnswer8() {
		if ($('#answer8').css('opacity') == 0) {
			$('#question8').css("font-weight","bold");
			$('#question8').css("color","#2396DE");
			$('#answer8').css('display', 'block');
			$('#answer8').css('opacity', 1);
		}else	{
			$('#question8').css("font-weight","normal");
			$('#question8').css("color","#2C2E33");
			$('#answer8').css('display', 'none');
			$('#answer8').css('opacity', 0);
		}
	
	};

		function changeColor19() {
		$('#question9').css("color","#2396DE");
	}
	
	function changeColor20() {
			$('#question9').css("color","#2C2E33");
	}
	
	function getAnswer9() {
		if ($('#answer9').css('opacity') == 0) {
			$('#question9').css("font-weight","bold");
			$('#question9').css("color","#2396DE");
			$('#answer9').css('display', 'block');
			$('#answer9').css('opacity', 1);
		}else	{
			$('#question9').css("font-weight","normal");
			$('#question9').css("color","#2C2E33");
			$('#answer9').css('display', 'none');
			$('#answer9').css('opacity', 0);
		}
	
	};

	function changeColor21() {
		$('#question10').css("color","#2396DE");
	}
	
	function changeColor22() {
			$('#question10').css("color","#2C2E33");
	}
	
	function getAnswer10() {
		if ($('#answer10').css('opacity') == 0) {
			$('#question10').css("font-weight","bold");
			$('#question10').css("color","#2396DE");
			$('#answer10').css('display', 'block');
			$('#answer10').css('opacity', 1);
		}else	{
			$('#question10').css("font-weight","normal");
			$('#question10').css("color","#2C2E33");
			$('#answer10').css('display', 'none');
			$('#answer10').css('opacity', 0);
		}
	
	};

	function changeColor23() {
		$('#question11').css("color","#2396DE");
	}
	
	function changeColor24() {
			$('#question11').css("color","#2C2E33");
	}
	
	function getAnswer11() {
		if ($('#answer11').css('opacity') == 0) {
			$('#question11').css("font-weight","bold");
			$('#question11').css("color","#2396DE");
			$('#answer11').css('display', 'block');
			$('#answer11').css('opacity', 1);
		}else	{
			$('#question11').css("font-weight","normal");
			$('#question11').css("color","#2C2E33");
			$('#answer11').css('display', 'none');
			$('#answer11').css('opacity', 0);
		}
	
	};

	function changeColor25() {
		$('#question12').css("color","#2396DE");
	}
	
	function changeColor26() {
			$('#question12').css("color","#2C2E33");
	}
	
	function getAnswer12() {
		if ($('#answer12').css('opacity') == 0) {
			$('#question12').css("font-weight","bold");
			$('#question12').css("color","#2396DE");
			$('#answer12').css('display', 'block');
			$('#answer12').css('opacity', 1);
		}else	{
			$('#question12').css("font-weight","normal");
			$('#question12').css("color","#2C2E33");
			$('#answer12').css('display', 'none');
			$('#answer12').css('opacity', 0);
		}
	
	};

	function changeColor27() {
		$('#question13').css("color","#2396DE");
	}
	
	function changeColor28() {
			$('#question13').css("color","#2C2E33");
	}
	
	function getAnswer13() {
		if ($('#answer13').css('opacity') == 0) {
			$('#question13').css("font-weight","bold");
			$('#question13').css("color","#2396DE");
			$('#answer13').css('display', 'block');
			$('#answer13').css('opacity', 1);
		}else	{
			$('#question13').css("font-weight","normal");
			$('#question13').css("color","#2C2E33");
			$('#answer13').css('display', 'none');
			$('#answer13').css('opacity', 0);
		}
	
	};

	function changeColor29() {
		$('#question14').css("color","#2396DE");
	}
	
	function changeColor30() {
			$('#question14').css("color","#2C2E33");
	}
	
	function getAnswer14() {
		if ($('#answer14').css('opacity') == 0) {
			$('#question14').css("font-weight","bold");
			$('#question14').css("color","#2396DE");
			$('#answer14').css('display', 'block');
			$('#answer14').css('opacity', 1);
		}else	{
			$('#question14').css("font-weight","normal");
			$('#question14').css("color","#2C2E33");
			$('#answer14').css('display', 'none');
			$('#answer14').css('opacity', 0);
		}
	
	};

		function changeColor31() {
		$('#question15').css("color","#2396DE");
	}
	
	function changeColor32() {
			$('#question15').css("color","#2C2E33");
	}
	
	function getAnswer15() {
		if ($('#answer15').css('opacity') == 0) {
			$('#question15').css("font-weight","bold");
			$('#question15').css("color","#2396DE");
			$('#answer15').css('display', 'block');
			$('#answer15').css('opacity', 1);
		}else	{
			$('#question15').css("font-weight","normal");
			$('#question15').css("color","#2C2E33");
			$('#answer15').css('display', 'none');
			$('#answer15').css('opacity', 0);
		}
	
	};

	function changeColor33() {
		$('#question16').css("color","#2396DE");
	}
	
	function changeColor34() {
			$('#question16').css("color","#2C2E33");
	}
	
	function getAnswer16() {
		if ($('#answer16').css('opacity') == 0) {
			$('#question16').css("font-weight","bold");
			$('#question16').css("color","#2396DE");
			$('#answer16').css('display', 'block');
			$('#answer16').css('opacity', 1);
		}else	{
			$('#question16').css("font-weight","normal");
			$('#question16').css("color","#2C2E33");
			$('#answer16').css('display', 'none');
			$('#answer16').css('opacity', 0);
		}
	
	};

	function changeColor35() {
		$('#question17').css("color","#2396DE");
	}
	
	function changeColor36() {
			$('#question17').css("color","#2C2E33");
	}
	
	function getAnswer17() {
		if ($('#answer17').css('opacity') == 0) {
			$('#question17').css("font-weight","bold");
			$('#question17').css("color","#2396DE");
			$('#answer17').css('display', 'block');
			$('#answer17').css('opacity', 1);
		}else	{
			$('#question17').css("font-weight","normal");
			$('#question17').css("color","#2C2E33");
			$('#answer17').css('display', 'none');
			$('#answer17').css('opacity', 0);
		}
	
	};

	function changeColor37() {
		$('#question18').css("color","#2396DE");
	}
	
	function changeColor38() {
			$('#question18').css("color","#2C2E33");
	}
	
	function getAnswer18() {
		if ($('#answer18').css('opacity') == 0) {
			$('#question18').css("font-weight","bold");
			$('#question18').css("color","#2396DE");
			$('#answer18').css('display', 'block');
			$('#answer18').css('opacity', 1);
		}else	{
			$('#question18').css("font-weight","normal");
			$('#question18').css("color","#2C2E33");
			$('#answer18').css('display', 'none');
			$('#answer18').css('opacity', 0);
		}
	
	};

	function changeColor39() {
		$('#question19').css("color","#2396DE");
	}
	
	function changeColor40() {
			$('#question19').css("color","#2C2E33");
	}
	
	function getAnswer19() {
		if ($('#answer19').css('opacity') == 0) {
			$('#question19').css("font-weight","bold");
			$('#question19').css("color","#2396DE");
			$('#answer19').css('display', 'block');
			$('#answer19').css('opacity', 1);
		}else	{
			$('#question19').css("font-weight","normal");
			$('#question19').css("color","#2C2E33");
			$('#answer19').css('display', 'none');
			$('#answer19').css('opacity', 0);
		}
	
	};

	function changeColor41() {
		$('#question20').css("color","#2396DE");
	}
	
	function changeColor42() {
			$('#question20').css("color","#2C2E33");
	}
	
	function getAnswer20() {
		if ($('#answer20').css('opacity') == 0) {
			$('#question20').css("font-weight","bold");
			$('#question20').css("color","#2396DE");
			$('#answer20').css('display', 'block');
			$('#answer20').css('opacity', 1);
		}else	{
			$('#question20').css("font-weight","normal");
			$('#question20').css("color","#2C2E33");
			$('#answer20').css('display', 'none');
			$('#answer20').css('opacity', 0);
		}
	
	};
