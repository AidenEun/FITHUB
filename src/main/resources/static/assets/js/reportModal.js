const openReportButtons = document.querySelectorAll(".reportOpen");
const closeReport = document.querySelector(".reportClose");
const reportModalBox = document.querySelector('.report_modal_box');
const otherDescriptionTextarea = document.getElementById("otherDescription");
const radioOptions = document.querySelectorAll('input[type="radio"]');

openReportButtons.forEach(openReportButton => {
    openReportButton.addEventListener("click", () => {
        reportModalBox.classList.add("active");

        var reportedUserId = $('.writer').text();
        var boardNum = $('.boardNum').text();
        var boardCategory = $('.boardCategory').text();

        document.querySelector('#reportedUserId').value = reportedUserId;
        document.querySelector('#boardNum').value = boardNum;
        document.querySelector('#boardCategory').value = boardCategory;
    });
});

closeReport.addEventListener("click", () => {
    resetModalContent();
    reportModalBox.classList.remove("active");
});

// 모달 외부 클릭 시 모달 창 닫기
reportModalBox.addEventListener('click', (e) => {
    if (e.target === reportModalBox) {
        resetModalContent();
        reportModalBox.classList.remove("active");
    }
});

document.querySelectorAll('input[type="radio"]').forEach(function (radio) {
    radio.addEventListener('change', function () {
        var selectedReason = this.value;

        document.querySelectorAll('.description').forEach(function (desc) {
            desc.style.display = 'none';
        });

        // "기타"를 선택한 경우 텍스트 입력란 표시
        if (selectedReason === 'o7') {
            document.querySelector('#otherDescription').style.display = 'block';
        } else {
            var selectedDescription = document.querySelector('.description-' + selectedReason);
            if (selectedDescription) {
                selectedDescription.style.display = 'block';
            }
        }
    });
});

document.getElementById("otherDescription").addEventListener("click", function () {
    this.selectionStart = 0; // 커서를 텍스트 입력란의 처음으로 이동
    this.selectionEnd = this.value.length; // 선택 영역을 전체 내용으로 설정
    this.value = ""; // 내용을 비움
});

/*
function reportModal(e){
    e.preventDefault();
    var reportedUserId = $(this).closest('tr').find('.writer').text();
    var boardNum = $(this).closest('tr').find('.boardNum').text();
    var boardCategory = $(this).closest('tr').find('.boardCategory').text();

    document.querySelector('#reportedUser').value = reportedUser;
    document.querySelector('#boardNum').value = boardNum;
    document.querySelector('#boardCategory').value = boardCategory;

    reportModalBox.classList.add("active");
}
*/

function resetModalContent() {
    // 모달 창 내용 초기화
    radioOptions.forEach(function (radio) {
        radio.checked = false;
    });
    otherDescriptionTextarea.value = '';
    document.querySelectorAll('.description').forEach(function (desc) {
        desc.style.display = 'none';
    });
}

const cancelButton = document.querySelector(".cancelButton");
const confirmButton = document.querySelector(".confirmButton");

cancelButton.addEventListener("click", () => {
    resetModalContent();
    reportModalBox.classList.remove("active");
});

confirmButton.addEventListener("click", () => {
    const reportContentRadio = document.querySelector('input[name="reportReason"]:checked');
    let reportContent;

    if (reportContentRadio.value === 'o7') {
        // "기타"를 선택한 경우 텍스트 내용을 reportContent에 할당
        reportContent = otherDescriptionTextarea.value;
    } else {
        reportContent = reportContentRadio.value;
    }
    const reportedUser = document.querySelector('#reportedUserId').value;
    const reportBoardnum = document.querySelector('#boardNum').value;
    const boardCategory = document.querySelector('#boardCategory').value;

    const data = {
        reportContent: reportContent,
        reportedUser: reportedUser,
        reportBoardnum: reportBoardnum,
        boardCategory: boardCategory
    };

    fetch('/report', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            window.alert('신고 완료!!');
        } else {
            window.alert('신고 실패. 다시 시도하세요.');
        }
    })
    .catch(error => {
        window.alert('오류 발생: ' + error.message);
    });

    resetModalContent();
    reportModalBox.classList.remove("active");
});
