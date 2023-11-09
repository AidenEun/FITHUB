const openSignUpButtons = document.querySelectorAll(".signUpOpen");
const closeSignUp = document.querySelector(".signUpClose");
const signUpModalBox = document.querySelector('.signUp_modal_box');

closeSignUp.addEventListener("click", () => {
//    clearSignUpModalContent();
    signUpModalBox.classList.remove("active");
});

// 모달 외부 클릭 시 모달 창 닫기
signUpModalBox.addEventListener('click', (e) => {
    if (e.target === signUpModalBox) {
//        clearSignUpModalContent();
        signUpModalBox.classList.remove("active");
    }
});

// 모달 내용 초기화 함수
function clearSignUpModalContent() {
//    var tableBody = document.getElementById('signUp-table');
//    tableBody.innerHTML = "";
//
//    var signUpContentBox = document.getElementById('signUp-content-box');
//    if (signUpContentBox) {
//        signUpContentBox.remove();
//    }
}

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
    alert("dom");
//    var data = JSON.parse(data);
//    var tableBody = $('#report-table');
//    tableBody.empty();
//
//    var row = $('<tr style="text-align: center;">');
//    row.append('<td class="long_text"><a href="#">' + data.reportDTO.reportNum + '</a></td>');
//    row.append('<td class="long_text"><a href="#" class="open">' + data.reportDTO.userId + '</a></td>');
//    row.append('<td class="long_text"><a href="#">' + data.reportDTO.reportedUser + '</a></td>');
//    row.append('<td class="long_text"><a href="#">' + data.reportDTO.boardCategory + '</a></td>');
//    row.append('<td class="long_text"><a href="#">' + data.reportDTO.reportBoardnum + '</a></td>');
//    row.append('<td class="long_text"><a href="#">' + data.reportDTO.reportDate + '</a></td>');
//    tableBody.append(row);
//
//    var newRow = $('<tr style="text-align: center;">');
//    newRow.append('<td class="long_text" colspan="6"><div class="button fit" style="cursor: auto !important;">신고 내용 : ' + data.reportDTO.reportContent + '</div></td>');
//    tableBody.append(newRow);
}

document.querySelector('.confirmButton').addEventListener('click', function() {
    alert("승인");
//    const reportNum = document.querySelector('#reportNumInput').value;
//
//    const data = {
//        reportNum: reportNum
//    };
//
//    fetch('/adminmypage/reportConfirm', {
//        method: 'POST',
//        headers: {
//            'Content-Type': 'application/json'
//        },
//        body: JSON.stringify(data)
//    })
//    .then(response => {
//        if (response.ok) {
//            window.alert('신고 처리 완료!!');
//            location.reload();
//        } else {
//            window.alert('신고 처리 실패. 다시 시도하세요.');
//        }
//    })
//    .catch(error => {
//        window.alert('오류 발생: ' + error.message);
//    });
//
//    clearReportModalContent();
//    reportAdminModalBox.classList.remove("active");
});

document.querySelector('.cancelButton').addEventListener('click', function() {
    alert("거절");
//    const reportNum = document.querySelector('#reportNumInput').value;
//
//        const data = {
//            reportNum: reportNum
//        };
//
//        fetch('/adminmypage/reportCancel', {
//            method: 'POST',
//            headers: {
//                'Content-Type': 'application/json'
//            },
//            body: JSON.stringify(data)
//        })
//        .then(response => {
//            if (response.ok) {
//                window.alert('신고 철회 완료!!');
//                location.reload();
//            } else {
//                window.alert('신고 철회 실패. 다시 시도하세요.');
//            }
//        })
//        .catch(error => {
//            window.alert('오류 발생: ' + error.message);
//        });
//
//        clearReportModalContent();
//        reportAdminModalBox.classList.remove("active");
});
