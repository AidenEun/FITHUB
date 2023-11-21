const writeOpen = document.querySelector(".writeOpen");
const writeClose = document.querySelector(".writeClose");
const noticeWriteModalBox = document.querySelector('.noticeWrite_modal_box');

const getOpen = document.querySelector(".getOpen");
const getClose = document.querySelector(".getClose");
const noticeGetModalBox = document.querySelector('.noticeGet_modal_box');


$('.writeOpen').on('click',challNoticeBoardModal);
$('.getOpen').on('click',challNoticeGetModal);

writeClose.addEventListener("click", () => {
    clearChallNoticeBoardModalContent();
    noticeWriteModalBox.classList.remove("active");
});

// 모달 외부 클릭 시 모달 창 닫기
noticeWriteModalBox.addEventListener('click', (e) => {
    if (e.target === noticeWriteModalBox) {
        clearChallNoticeBoardModalContent();
        noticeWriteModalBox.classList.remove("active");
    }
});

getClose.addEventListener("click", () => {
    clearChallNoticeGetModalContent();
    noticeGetModalBox.classList.remove("active");
});

// 모달 외부 클릭 시 모달 창 닫기
noticeGetModalBox.addEventListener('click', (e) => {
    if (e.target === noticeGetModalBox) {
        clearChallNoticeGetModalContent();
        noticeGetModalBox.classList.remove("active");
    }
});


function challNoticeBoardModal(e){
    e.preventDefault();

    noticeWriteModalBox.classList.add("active");

}
function challNoticeGetModal(e){
    e.preventDefault();
    var challNum = $(this).closest('tr').find('.challNum').val();
    noticeGetModalBox.classList.add("active");

    document.querySelector('#noticeNumInput').value = challNum;
    sendChallNumToModal(challNum);
}

function sendChallNumToModal(challNum) {
    $.ajax({
        url: '/challenge/challNoticeGet',
        method: 'POST',
        data: { challNum: challNum },
        success: function(data) {
            challNoticeBoardModalDom(data);
        },
        error: function(error) {
        }
    });
}

function challNoticeBoardModalDom(data) {
    var data = JSON.parse(data);
    var tableBody = $('#noticeGet-table');
    tableBody.empty();

    var row = $('<tr style="text-align: center;">');
    row.append('<td class="long_text">' + data.list.challNum + '</td>');
    row.append('<td class="long_text">' + data.list.challCategory + '</td>');
    row.append('<td class="long_text">' + data.list.challName + '</td>');
    row.append('<td class="long_text">' + data.list.challContent + '</td>');
    row.append('<td class="long_text">' + data.list.challTerm + '</td>');
    row.append('<td class="long_text">' + data.list.regdate + '</td>');
    tableBody.append(row);
}

// 모달 내용 초기화 함수
function clearChallNoticeGetModalContent() {
    var tableBody = document.getElementById('noticeGet-table');
    tableBody.innerHTML = "";

}

document.querySelector('.noticeGetConfirmButton').addEventListener('click', function() {
    const challNum = document.querySelector('#noticeNumInput').value;

    const data = {
        challNum: challNum
    };

    fetch('/challenge/noticeGetConfirm', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.text())
    .then(data => {
        if (data === 'success') {
          alert("챌린지가 추가되었습니다!")
        }
        else if (data === 'fail') {
          alert("이미 추가한 챌린지입니다!")
        }
        else {
          console.error('Unexpected response:', data);
        }
    })
    .catch(error => console.error('Error:', error));

    clearChallNoticeGetModalContent();
    noticeGetModalBox.classList.remove("active");
});

document.querySelector('.noticeGetDeleteButton').addEventListener('click', function() {
    const challNum = document.querySelector('#noticeNumInput').value;

    const data = {
        challNum: challNum
    };

    fetch('/challenge/noticeGetDelete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            window.alert('삭제 완료!!');
            location.reload();
        } else {
            window.alert('삭제 실패. 다시 시도하세요.');
        }
    })
    .catch(error => {
        window.alert('오류 발생: ' + error.message);
    });

    clearChallNoticeGetModalContent();
    noticeGetModalBox.classList.remove("active");
});


// 모달 내용 초기화 함수
function clearChallNoticeBoardModalContent() {
    const challName = document.querySelector("#challName");
    if (challName !== null) {
        challName.value = "";
    }

    const challCategory = document.querySelector("#challCategory");
    if (challCategory !== null) {
        challCategory.value = 'challExer';
    }

    const challTerm = document.querySelector("#challTerm");
    if (challTerm !== null) {
        challTerm.value = '7';
    }

    const challContent = document.querySelector("#challContent");
    if (challContent !== null) {
        challContent.value = '';
    }
}




function writeSendit(){
const boardForm = document.boardForm;

		const challName = boardForm.challName;
		if(challName.value == ""){
			alert("제목을 입력하세요!");
			challName.focus();
			return false;
		}

		const challContent = boardForm.challContent;
		if(challContent.value == ""){
			alert("내용을 입력하세요!");
			challContent.focus();
			return false;
		}

    boardForm.submit();
}
