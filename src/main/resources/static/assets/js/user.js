let flag = false;
let pwTest = [false,false,false,false,false]
function sendit(){
    const joinForm = document.joinForm;

    const userid = joinForm.userId;
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
    const usergender = joinForm.userGender;
    if(!usergender[0].checked && !usergender[1].checked){
    	alert("성별을 선택하세요!");
    	return false;
    }
    const useremail = joinForm.useremail;
        if (!isValidEmail(useremail.value)) {
            alert("올바른 이메일 주소를 입력하세요!");
            useremail.focus();
            return false;
        }

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

// 확인: passwordInput이 존재하면 이벤트 리스너 추가
if (passwordInput) {
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
} else {
    console.error('passwordInput이 존재하지 않습니다.');
}

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

function isValidEmail(email) {
    // 간단한 형식의 이메일 주소 유효성 검사
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
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
