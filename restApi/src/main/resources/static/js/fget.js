/**
 * 
 */
document.getElementById('btnGetList').addEventListener('click',getList)
document.getElementById('btnGetOne').addEventListener('click',getOne)


function getOne(){
	const userid = document.getElementById('search').value
	const url = `/account/${userid}`
	fetch(url)
		.then(response => {
			console.log("response : ", response)
			return response.json()	// 응답의 body를 역직렬화(객체화) 하여 다음 then 에 전달
		})
		.then(data => {
			console.log("data : ", data)
		})
		.catch(error => console.error("에러 : ", error) )
}


function getList(){
	const url = '/account'
	fetch(url)
		.then(response => {
			console.log("response : ", response)
			return response.json()	// 응답의 body를 역직렬화(객체화) 하여 다음 then 에 전달
		})
		.then(data => {
			console.log("data : ", data)
		})
		.catch(error => console.error("에러 : ", error) )
}
