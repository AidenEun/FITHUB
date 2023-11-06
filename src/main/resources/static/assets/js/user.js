const userhobby = [];
let flag = false;
let pwTest = [false,false,false,false,false]
function sendit(){
    const joinForm = document.joinForm;
    
    const userid = joinForm.userid;
    if(userid.value == ""){
        alert("아이디를 입력하세요!")
        userid.focus();
        return false;
    }
    if(userid.value.length < 5 || userid.value.length > 12){
        alert("아이디는 5자 이상 12자 이하로 입력하세요!");
        userid.focus();
        return false;
    }
    
    const result = document.getElementById("result");
    if(result.innerHTML == "&nbsp;"){
    	alert("아이디 중복검사를 진행해주세요!");
    	userid.focus();
    	return false;
    }
    if(result.innerHTML == "중복된 아이디가 있습니다!"){
    	alert("중복체크 통과 후 가입이 가능합니다!");
    	userid.focus();
    	return false;
    }
    
    const userpw = joinForm.userpw;
    
    for(let i=0;i<5;i++){
    	if(!pwTest[i]){
    		alert("비밀번호 확인을 다시하세요!");
    		userpw.focus();
    		return false;
    	}
    }
    const username = joinForm.username;
    if(username.value == ""){
        alert("이름을 입력하세요!");
        username.focus();
        return false;
    }
    const exp_name = /[가-힣]+$/;
    if(!exp_name.test(username.value)){
        alert("이름에는 한글만 입력하세요!");
        username.focus();
        return false;
    }
    const usergender = joinForm.usergender;
    if(!usergender[0].checked && !usergender[1].checked){
    	alert("성별을 선택하세요!");
    	return false;
    }
    
    const foreigner = joinForm.foreigner;
    if(!foreigner[0].checked && !foreigner[1].checked){
    	alert("국적을 선택하세요!");
    	return false;
    }
    
    const zipcode = joinForm.zipcode;
    if(zipcode.value == ""){
        alert("주소찾기를 진행해주세요!");
        sample6_execDaumPostcode();
        return false;
    }

    const addrdetail = joinForm.addrdetail;
    if(addrdetail.value == ""){
        alert("나머지 주소를 입력해주세요.")
        addrdetail.focus();
        return false;
    }
    console.log(userhobby);
    if(userhobby.length == 0){
        alert("취미는 적어도 1개 이상 입력해 주세요!");
        joinForm.hobby.focus();
        flag = true;
        return false;
    }
    const hobbyTag = joinForm.userhobby;
    hobbyTag.value = userhobby.join("\\");
    
    joinForm.submit();
    return true;
}
function pwcheck(){
    const userpw = document.joinForm.userpw;
    const userpw_re = document.joinForm.userpw_re;
    const pw_check = document.getElementById("pw_check");
    const reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~?!@-]).{4,}$/;
    const c = document.querySelectorAll(".pw_check span");
    if(userpw.value == 0){
    	for(let i=0;i<5;i++){
    		pwTest[i] = false;
    		c[i].classList = "";
    	}
    	return;
    }
    if(!reg.test(userpw.value)){
    	c[0].classList = "pcf";
    	pwTest[0] = false;
    }
    else{
    	c[0].classList = "pct";
    	pwTest[0] = true;
    }
    if(userpw.value.length < 8){
    	c[1].classList = "pcf";
    	pwTest[1] = false;
    }
    else{
    	c[1].classList = "pct";
    	pwTest[1] = true;
    }
    if(/(\w)\1\1\1/.test(userpw.value)){
    	c[2].classList = "pcf";
    	pwTest[2] = false;
    }
    else{
    	c[2].classList = "pct";
    	pwTest[2] = true;
    }
    if(!/^[a-zA-Z0-9~?!@-]*$/.test(userpw.value)){
    	c[3].classList = "pcf";
    	pwTest[3] = false;
    }
    else{
    	c[3].classList = "pct";
    	pwTest[3] = true;
    }
    if(userpw.value != userpw_re.value){
    	c[4].classList = "pcf";
    	pwTest[4] = false;
    }
    else{
    	c[4].classList = "pct";
    	pwTest[4] = true;
    }
}

// 비밀번호 입력란
const passwordInput = document.getElementById('userpw');

// 비밀번호 체크 요소
const pwCheck = document.querySelector('#page1 .pw_check');

// 비밀번호 입력란에 입력이 발생할 때 호출되는 함수
function onPasswordInput() {
    if (passwordInput.value.length > 0) {
        pwCheck.style.display = 'block';
    } else {
        pwCheck.style.display = 'none';
    }
}

// 비밀번호 입력란에 입력 감지 이벤트 추가
passwordInput.addEventListener('input', onPasswordInput);

function checkId(){
	const xhr = new XMLHttpRequest();
	const result = document.getElementById("result");
	const userid = document.joinForm.userid;
	if(userid.value == ""){
		alert("아이디를 입력하세요!")
		userid.focus();
		return false;
	}
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				let txt = xhr.responseText;
				txt = txt.trim();
				if(txt == 'O'){
					result.innerHTML = "사용할 수 있는 아이디입니다!";
					document.joinForm.userpw.focus();
				}
				else{
					result.innerHTML = "중복된 아이디가 있습니다!";
					userid.value = '';
					userid.focus();
				}
			}
		}
	}
	xhr.open("GET","/user/checkid?userid="+userid.value);
	xhr.send();
}
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}
function addHobby(){
	const joinForm = document.joinForm;
	const hobby_list = document.getElementsByClassName("hobby_list")[0];
	let hobby = joinForm.hobby;
	
	if(hobby.value == ""){
		alert("취미를 입력해 주세요.")
		return;
	}
	if(userhobby.indexOf(hobby.value) != -1){
		alert("중복된 취미입니다.")
		return;
	}
	if(!/^[가-힣a-zA-Z\s]+$/.test(hobby.value)){
		alert("정확한 취미를 입력해 주세요.")
		return;
	}
	if(userhobby.length >= 5){
		alert("취미는 5개 이하로 입력해 주세요.")
		return;
	}
	const inputHobby = document.createElement("span");
	inputHobby.classList = "userhobby";
	inputHobby.name = "userhobby";
	inputHobby.innerHTML = hobby.value;
	userhobby.push(hobby.value);
	
	const xBox = document.createElement("a")
	xBox.classList = "xBox";
	xBox.addEventListener('click',function(e){
		let deleteHobby = e.target.parentElement.innerText;
		for(let i in userhobby){
			if(userhobby[i] == deleteHobby){
				userhobby.splice(i,1);
				break;
			}
		}
		e.target.parentElement.remove();
		console.log(userhobby);
	})
	inputHobby.appendChild(xBox);
	
	hobby_list.appendChild(inputHobby);
	
	const hobbies = document.getElementsByClassName("userhobby");
	for(let i=0;i<hobbies.length;i++){
		hobbies[i].addEventListener('click',deleteHobby)
	}
	hobby.value="";
	hobby.focus();
	console.log(userhobby);
}
function hobbyKeyup(){
	if(flag){
		flag = !flag;
		return;
	}
	if(window.event.keyCode == 13){
		addHobby();
	}
}
function deleteHobby(e){
	let deleteHobby = e.target.innerText;
	for(let i in userhobby){
		if(userhobby[i] == deleteHobby){
			userhobby.splice(i,1);
			break;
		}
	}
	e.target.remove();
	console.log(userhobby);
}

function goToNextPage() {
    document.querySelectorAll('#page1').forEach(function(el) {
        el.style.display = 'none';
    });
    document.querySelectorAll('#page2').forEach(function(el) {
        el.style.display = 'block';
    });
}

function goToPrevPage() {
    document.querySelectorAll('#page2').forEach(function(el) {
        el.style.display = 'none';
    });
    document.querySelectorAll('#page1').forEach(function(el) {
        el.style.display = 'block';
    });
}
