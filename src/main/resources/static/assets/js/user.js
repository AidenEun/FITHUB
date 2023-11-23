let flag = false;
let pwTest = [false,false,false,false,false]
function sendit() {
    const joinForm = document.joinForm;

    const userId = joinForm.userId;
    if (userId.value == "") {
        alert("아이디를 입력하세요!")
        userId.focus();
        return false;
    }
    if (userId.value.length < 5 || userId.value.length > 12) {
        alert("아이디는 5자 이상 12자 이하로 입력하세요!");
        userId.focus();
        return false;
    }

    const result = document.getElementById("result");
    if (result.innerHTML == "&nbsp;") {
        alert("아이디 중복검사를 진행해주세요!");
        userId.focus();
        return false;
    }
    if (result.innerHTML == "중복된 아이디가 있습니다!") {
        alert("중복체크 통과 후 가입이 가능합니다!");
        userId.focus();
        return false;
    }

    const userPw = joinForm.userPw;

    for (let i = 0; i < 5; i++) {
        if (!pwTest[i]) {
            alert("비밀번호 확인을 다시하세요!");
            userPw.focus();
            return false;
        }
    }

    const userNickname = joinForm.userNickname;
    if (userNickname.value == "") {
        alert("닉네임을 입력하세요!");
        userNickname.focus();
        return false;
    }

    const nicknameResult = document.getElementById("nicknameResult");
    if (nicknameResult.innerHTML == "&nbsp;") {
        alert("닉네임 중복검사를 진행해주세요!");
        userNickname.focus();
        return false;
    }
    if (nicknameResult.innerHTML == "중복된 닉네임이 있습니다!") {
        alert("중복체크 통과 후 가입이 가능합니다!");
        userNickname.focus();
        return false;
    }

    const userName = joinForm.userName;
    if (userName.value == "") {
        alert("이름을 입력하세요!");
        userName.focus();
        return false;
    }
    const exp_name = /[가-힣]+$/;
    if (!exp_name.test(userName.value)) {
        alert("이름에는 한글만 입력하세요!");
        userName.focus();
        return false;
    }
    const userGender = joinForm.userGender;
    if (!userGender[0].checked && !userGender[1].checked) {
        alert("성별을 선택하세요!");
        return false;
    }

    const userBirth = joinForm.userBirth;
    if (userBirth.value == "") {
        alert("생년월일을 입력하세요!");
        userBirth.focus();
        return false;
    }

    const userTel = joinForm.userTel;
    if (userTel.value == "") {
        alert("전화번호를 입력하세요!");
        userTel.focus();
        return false;
    }

    const userMail = joinForm.userMail;
    if (!isValidEmail(userMail.value)) {
        alert("이메일 주소를 확인하세요!");
        userMail.focus();
        return false;
    }

    const userWeight = joinForm.userWeight;
    if (userWeight.value == "") {
        alert("몸무게를 입력하세요!");
        userWeight.focus();
        return false;
    }

    const userHeight = joinForm.userHeight;
    if (userHeight.value == "") {
        alert("키를 입력하세요!");
        userHeight.focus();
        return false;
    }

    const weightGoal = joinForm.weightGoal;
    if (weightGoal.value == "") {
        alert("목표 체중을 입력하세요!");
        weightGoal.focus();
        return false;
    }

    const caloriesGoal = joinForm.caloriesGoal;
    if (caloriesGoal.value == "") {
        alert("목표 칼로리를 입력하세요!");
        caloriesGoal.focus();
        return false;
    }
    const user = {
         userId: userId.value,
         userPw: userPw.value,
         userName: userName.value,
         userGender: userGender[0].checked ? "M" : "F",
         userMail: userMail.value,
         userNickname: userNickname.value,
         userTel: userTel.value,
         userBirth: userBirth.value,
         userWeight: userWeight.value,
         userHeight: userHeight.value,
         weightGoal: weightGoal.value,
         caloriesGoal: caloriesGoal.value
     };
    console.log(user)

    fetch("/user/join", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(user)
    })
    .then(response => response.json())
    .then((response) => {
        console.log(response);
        // 서버에서의 응답에 따른 처리
        if (response) {
            alert("가입이 완료되었습니다!");
            // 가입이 성공했을 때 추가적인 처리가 필요하다면 여기에 작성
            location.replace("/user/login")
        } else {
            alert("가입에 실패했습니다. 다시 시도해주세요.");
        }
    })
    .catch(error => {
        console.error("가입 요청 중 에러 발생:", error);
    });

//    joinForm.submit();
    return true;
}
function pwcheck(){
    const userPw = document.joinForm.userPw;
    const userPwRe = document.joinForm.userPwRe;
    const pwCheck = document.getElementById("pwCheck");
    const reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~?!@-]).{4,}$/;
    const c = document.querySelectorAll(".pwCheck span");
    if(userPw.value == 0){
    	for(let i=0;i<5;i++){
    		pwTest[i] = false;
    		c[i].classList = "";
    	}
    	return;
    }
    if(!reg.test(userPw.value)){
    	c[0].classList = "pcf";
    	pwTest[0] = false;
    }
    else{
    	c[0].classList = "pct";
    	pwTest[0] = true;
    }
    if(userPw.value.length < 8){
    	c[1].classList = "pcf";
    	pwTest[1] = false;
    }
    else{
    	c[1].classList = "pct";
    	pwTest[1] = true;
    }
    if(/(\w)\1\1\1/.test(userPw.value)){
    	c[2].classList = "pcf";
    	pwTest[2] = false;
    }
    else{
    	c[2].classList = "pct";
    	pwTest[2] = true;
    }
    if(!/^[a-zA-Z0-9~?!@-]*$/.test(userPw.value)){
    	c[3].classList = "pcf";
    	pwTest[3] = false;
    }
    else{
    	c[3].classList = "pct";
    	pwTest[3] = true;
    }
    if(userPw.value != userPwRe.value){
    	c[4].classList = "pcf";
    	pwTest[4] = false;
    }
    else{
    	c[4].classList = "pct";
    	pwTest[4] = true;
    }
}

// 비밀번호 입력란
const passwordInput = document.getElementById('userPw');

// 비밀번호 체크 요소
const pwCheck = document.querySelector('#page1 .pwCheck');

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

function isInvalidAdminId(id) {
    // 대소문자 무작위 조합으로 막기
    const mixedCaseAdminRegex = /a.*d.*m.*i.*n/i;

    // 대문자, 소문자 모두 막기
    const caseSensitiveAdminRegex = /admin/i;

    // 대소문자 무작위로 섞인 경우 막기
    const randomCaseAdminRegex = /a(?:(?![aA]).)*d(?:(?![dD]).)*m(?:(?![mM]).)*i(?:(?![iI]).)*n/i;

    // 특수문자 막기
    const specialCharRegex = /^[a-zA-Z0-9]*$/;

    // 특수문자 포함 또는 특수문자로만 구성된 경우 막기
    return mixedCaseAdminRegex.test(id) || caseSensitiveAdminRegex.test(id) || randomCaseAdminRegex.test(id) || !specialCharRegex.test(id) || /^[!@#$%^&*(),.?":{}|<>]*$/.test(id);
}

function isInvalidAdminNickname(nickname) {
    const mixedCaseAdminRegex = /a.*d.*m.*i.*n/i;
    const caseSensitiveAdminRegex = /admin/i;
    const randomCaseAdminRegex = /a(?:(?![aA]).)*d(?:(?![dD]).)*m(?:(?![mM]).)*i(?:(?![iI]).)*n/i;
    /*const specialCharRegex = /^[a-zA-Z0-9]*$/;*/

    return mixedCaseAdminRegex.test(nickname) || caseSensitiveAdminRegex.test(nickname) || randomCaseAdminRegex.test(nickname) || /^[!@#$%^&*(),.?\":{}|<>]*$/.test(nickname);
}

function checkId(){
    const xhr = new XMLHttpRequest();
    const result = document.getElementById("result");
    const userId = document.joinForm.userId;
    if(userId.value == ""){
        alert("아이디를 입력하세요!")
        userId.focus();
        return false;
    }

    const koreanRegex = /[ㄱ-ㅎㅏ-ㅣ가-힣]/;
    if (koreanRegex.test(userId.value)) {
        alert("아이디에 한글을 포함할 수 없습니다!");
        userId.value = '';
        userId.focus();
        return false;
    }

    if(isInvalidAdminId(userId.value)) {
        alert("사용할 수 없는 아이디입니다!");
        userId.value = '';
        userId.focus();
        return false;
    }

    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4){
            if(xhr.status == 200){
                let txt = xhr.responseText;
                txt = txt.trim();
                if(txt == 'O'){
                    alert("사용할 수 있는 아이디입니다!");
                    document.joinForm.userPw.focus();
                    // 중복 체크 성공 시 메시지 삭제
                    result.innerHTML = '';
                }
                else{
                    alert("중복된 아이디가 있습니다!");
                    userId.value = '';
                    userId.focus();
                }
            }
        }
    };
    xhr.open("GET","/user/checkid?userid="+userId.value);
    xhr.send();

    return false;
}

function isValidEmail(email) {
    // 수정된 간단한 형식의 이메일 주소 유효성 검사
    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    return emailRegex.test(email);
}

function checkNickname() {
    const xhr = new XMLHttpRequest();
    const nicknameResult = document.getElementById("nicknameResult");
    const userNickname = document.joinForm.userNickname;
    if(userNickname.value == ""){
        alert("닉네임을 입력하세요!")
        userNickname.focus();
        return false;
    }
    if(userNickname.value.includes("관리자")) {
        alert("사용할 수 없는 닉네임입니다!");
        userNickname.value = '';
        userNickname.focus();
        return false;
    }

    if(isInvalidAdminNickname(userNickname.value)) {
        alert("사용할 수 없는 닉네임입니다!");
        userNickname.value = '';
        userNickname.focus();
        return false;
    }

    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4){
            if(xhr.status == 200){
                let txt = xhr.responseText;
                txt = txt.trim();
                if(txt == 'O'){
                    /*result.innerHTML = "사용할 수 있는 아이디입니다!";*/
                    nicknameResult.innerHTML = '';
                    alert("사용할 수 있는 닉네임입니다!");
                    document.joinForm.userName.focus();
                }
                else{
                    /*result.innerHTML = "중복된 아이디가 있습니다!";*/
                    alert("중복된 닉네임이 있습니다!");
                    userNickname.value = '';
                    userNickname.focus();
                }
            }
        }
    };
    xhr.open("GET", "/user/nickname?usernickname=" + userNickname.value);
    xhr.send();

    return false;
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

function chkAll(source) {
     const checkboxes = document.querySelectorAll('input[name="cc"]');
     checkboxes.forEach(checkbox => {
         checkbox.checked = source.checked;
     });
 }
