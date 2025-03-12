/**
 */
// submit 버튼을 실행할 때 처리되는 이벤트 함수
// check2.js 코드를 for문으로 바꿉니다.
function checkValues(){
	let isSubmit = true
	// css 선택자를 이용하여 검사해야할 '요소들을' 가져오기
	const test 
	= document.querySelectorAll(' div.mb-1 > input');
	console.log(test)   // 브라우저 개발자도구 콘솔에서 확인
	
	// test 결과를 역순으로 정렬
	const reverse = Array.from(test).reverse();
	
	// 반복 메소드 forEach 활용 - 하나씩 가져온 요소는 element 변수에 저장
	reverse.forEach( element => {
		element.nextElementSibling.innerHTML=''
		if(element.value === '' && element.name != 'gender'){
			//element (입력요소)의 다음 위치 형제 가져오기
			element.nextElementSibling.innerHTML='필수 입력 입니다.'
			element.focus()
			isSubmit=false
		}
		
	})


	return isSubmit
}