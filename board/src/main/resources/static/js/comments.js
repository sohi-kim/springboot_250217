/**
 * 댓글 기능 api 실행
 */
const replyUl = document.getElementById("replyList")
getReplyList()   // 함수 실행

function getReplyList() {
   mref = document.getElementById("mref").value	
   //fetch 실행하기
   const url=`/api/comments/${mref}`
   fetch(url)
   .then((response) => {
       console.log('response:', response)
       return response.json()     
   })
   .then((data) => {
       console.log('data : ', data)
       printReplyList(data)
   })
   .catch((error) => {
       console.error('error:', error)
   })
}

// fetch 실행 결과 응답으로 아래 요소를 만들어서 기존 화면에 추가
function printReplyList(list) {   //댓글 목록을 ul 태그 안에 넣어 주는 함수
    let str = ''
    if(list && list.length > 0){
        list.forEach((dto) => {
        str+= ` <li class="list-group-item d-flex">
               <span class="col-5 myfc-1">${dto.writer}</span>
               <span class="col-6">${dto.regDate}</span>
               `
        // 로그인 구현하면 로그인 사용자와 글 작성자가 같을 때만 보이기
        str+=`<span class="col-1">
                   <i class="bi bi-trash" data-num="${dto.idx}"></i>
                </span>`
        
        str+=  `</li>
               <li class="list-group-item d-flex">
                 <textarea class="form-control myfs-9" disabled>${dto.content}</textarea>
               </li>`

        })  //for 끝
        replyUl.innerHTML = str
       
    } //if 끝
	else {
		 replyUl.innerHTML = '작성된 댓글이 없습니다.'
	}
}

document.getElementById('btnSave').addEventListener('click',commentSave)
function commentSave(){
	//값을 writer,content , mref 가져오기
	const mref = document.getElementById('mref').value
	const writer = document.getElementById('writer').value
	const content = document.getElementById('content').value
	const obj = {mref,writer, content}
	//fetch 실행
	const options = {
			method: 'POST',
			headers : {
				'Content-Type': 'application/json;charset=UTF-8'
			},
			body : JSON.stringify(obj) //jsonStr
	}
	const url ='/api/comments'
	fetch(url,options)
		.then(response => {
			console.log('response:',response)
			return response.json()
		})
		.then(data => {
			if(data===1){
				alert("작성한 댓글이 등록되었습니다.")
				document.getElementById('content').value=''
			}
		})
		.then(() =>  getReplyList())
		.catch(error => console.error("오류:",error))
}


































