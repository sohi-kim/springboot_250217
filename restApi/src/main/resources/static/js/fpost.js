/**
 * 
 */
document.getElementById('btnSave').addEventListener('click',sendPost)

function sendPost() {
	const url = '/account'
	// 저장할 데이터를 객체로 만들기
	const obj = {
		userid : 'gilsoon',
		username : '홍길순',
		birth : '2000-01-01',
		gender : '여성',
		email : 'sooni@naver.com',
		password : '9999'
	}
	
	const options = {
		method: 'POST',
		headers : {
			'Content-Type': 'application/json;charset=UTF-8'
		},
		body : JSON.stringify(obj) //jsonStr
	}
	
	fetch(url,options)
		.then(response => {
			console.log("response : ", response)
			return response.json()
		})
		.then(data => {
			console.log("data: ",data)
		})
		.catch(error => console.error("오류 : ", error))
	
	
}





