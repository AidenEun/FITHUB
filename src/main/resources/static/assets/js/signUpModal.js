const openSignUpButtons = document.querySelectorAll(".signUpOpen");
const closeSignUp = document.querySelector(".signUpClose");
const signUpModalBox = document.querySelector('.signUp_modal_box');

closeSignUp.addEventListener("click", () => {
    signUpModalBox.classList.remove("active");
});

// 모달 외부 클릭 시 모달 창 닫기
signUpModalBox.addEventListener('click', (e) => {
    if (e.target === signUpModalBox) {
        signUpModalBox.classList.remove("active");
    }
});

function signUpModal(e){
    e.preventDefault();
    var signupNum = $(this).closest('tr').find('.signupNum').text();

    signUpModalBox.classList.add("active");
    document.querySelector('#signupNumInput').value = signupNum;
    sendSignUpNumToModal(signupNum);
}

function sendSignUpNumToModal(signupNum) {
    $.ajax({
        url: '/adminmypage/signUpModal',
        method: 'POST',
        data: { signupNum: signupNum },
        success: function(data) {
            signUpModalDom(data);
        },
        error: function(error) {
        }
    });
}

function signUpModalDom(data) {
    var data = JSON.parse(data);
    var tableBody = $('#signUp-table');
    tableBody.empty();

    var row = $('<tr style="text-align: center;">');
    row.append('<td class="long_text">' + data.signUpDTO.signupNum + '</td>');
    row.append('<td class="long_text"><a href="#" class="open">' + data.signUpDTO.userId + '</a></td>');
    row.append('<td class="long_text">' + data.signUpDTO.trainerPart + '</td>');
    row.append('<td class="long_text">' + data.signUpDTO.trainerCareer + '</td>');
    row.append('<td class="long_text">' + data.signUpDTO.trainerContent + '</td>');
    row.append('<td class="long_text">' + data.userDTO.userAge + '</td>');
    row.append('<td class="long_text">' + data.userDTO.userGender + '</td>');
    row.append('<td class="long_text">' + data.userDTO.userReportedcnt + '</td>');
    tableBody.append(row);

    var newRow = $('<tr style="text-align: center;">');
    var imageContainer = $('<td colspan="8" class="long_text imageContainer">');
    var innerDiv = $('<div>');

    // innerDiv에 CSS 스타일 추가
    innerDiv.css({
        'display': 'flex',
        'justify-content': 'space-between',
        'align-items': 'center',
        'width': '100%'
    });

    if (data.profileDTO != null && data.profileDTO.length > 0) {
        data.profileDTO.forEach(function (profileData, i) {
            if (i < 3 && profileData.orgName) {
                var ext = profileData.orgName.split(".");
                var imageSrc = '/trainermypage/thumbnail?sysName=' + profileData.sysName;
                if (ext.indexOf("jpg") !== -1 || ext.indexOf("jpeg") !== -1 || ext.indexOf("png") !== -1 || ext.indexOf("gif") !== -1 || ext.indexOf("webp") !== -1) {
                    innerDiv.append('<div class="signUpImageBox"><img class="signUpImage" src="' + imageSrc + '"></div>');
                }
            }
        });
        imageContainer.append(innerDiv);
    }
    else {
        newRow.append('<td class="long_text" colspan="8"><div class="button fit" style="cursor: auto !important;">등록된 경력 사진이 없습니다!!</div></td>')
    }
    newRow.append(imageContainer);
    tableBody.append(newRow);

    $('.open').on('click', profileModalOpen);
}

document.querySelector('.confirmSignUpButton').addEventListener('click', function() {
    const signupNum = document.querySelector('#signupNumInput').value;

    const data = {
        signupNum: signupNum
    };

    fetch('/adminmypage/signUpConfirm', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            window.alert('트레이너 전환 승인 완료!!');
            location.reload();
        } else {
            window.alert('트레이너 전환 승인 실패. 다시 시도하세요.');
        }
    })
    .catch(error => {
        window.alert('오류 발생: ' + error.message);
    });

    signUpModalBox.classList.remove("active");
});

document.querySelector('.cancelSignUpButton').addEventListener('click', function() {
    const signupNum = document.querySelector('#signupNumInput').value;

        const data = {
            signupNum: signupNum
        };

        fetch('/adminmypage/cancelSignUp', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (response.ok) {
                window.alert('트레이너 거절 완료!!');
                location.reload();
            } else {
                window.alert('트레이너 거절 실패. 다시 시도하세요.');
                location.reload();
            }
        })
        .catch(error => {
            window.alert('오류 발생: ' + error.message);
        });

        signUpModalBox.classList.remove("active");
});
