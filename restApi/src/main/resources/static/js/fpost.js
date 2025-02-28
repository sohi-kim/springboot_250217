/**
 * 
 */
document.getElementById('btnSave').addEventListener('click',sendPost)

function sendPost() {
	const url = '/account'
	// 저장할 데이터를 객체로 만들기
	const obj = {
		userid : 'gilsoon2',
		username : '이길순',
		birth : '2010-01-01',
		gender : '여성',
		email : 'soonii@naver.com',
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
			if(response.status === 400){
				alert('userid 와 email 은 유일한 값으로 합니다.(무결성 위반)')
			}
			return response.json()
		})
		.then(data => {
			console.log("data: ",data)
		})
		.catch(error => console.error("오류 : ", error))
	
	
}





