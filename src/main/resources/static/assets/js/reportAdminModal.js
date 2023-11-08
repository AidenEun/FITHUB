const openReportAdminButtons = document.querySelectorAll(".reportAdminOpen");
const closeReportAdmin = document.querySelector(".reportAdminClose");
const reportAdminModalBox = document.querySelector('.report_Admin_modal_box');

closeReportAdmin.addEventListener("click", () => {
    clearReportModalContent();
    reportAdminModalBox.classList.remove("active");
});

// 모달 외부 클릭 시 모달 창 닫기
reportAdminModalBox.addEventListener('click', (e) => {
    if (e.target === reportAdminModalBox) {
        clearReportModalContent();
        reportAdminModalBox.classList.remove("active");
    }
});

function reportAdminModal(e){
    e.preventDefault();
    var reportNum = $(this).closest('tr').find('.reportNum').text();

    reportAdminModalBox.classList.add("active");
    document.querySelector('#reportNumInput').value = reportNum;
    sendReportNumToModal(reportNum);
}

function sendReportNumToModal(reportNum) {
    $.ajax({
        url: '/adminmypage/reportAdminModal',
        method: 'POST',
        data: { reportNum: reportNum },
        success: function(data) {
            reportAdminModalDom(data);
        },
        error: function(error) {
        }
    });
}

function reportAdminModalDom(data) {
    var data = JSON.parse(data);
    var tableBody = $('#report-table');
    tableBody.empty();

    var row = $('<tr style="text-align: center;">');
    row.append('<td class="long_text"><a href="#">' + data.reportDTO.reportNum + '</a></td>');
    row.append('<td class="long_text"><a href="#" class="open">' + data.reportDTO.userId + '</a></td>');
    row.append('<td class="long_text"><a href="#">' + data.reportDTO.reportedUser + '</a></td>');
    row.append('<td class="long_text"><a href="#">' + data.reportDTO.boardCategory + '</a></td>');
    row.append('<td class="long_text"><a href="#">' + data.reportDTO.reportBoardnum + '</a></td>');
    row.append('<td class="long_text"><a href="#">' + data.reportDTO.reportDate + '</a></td>');
    tableBody.append(row);

    var newRow = $('<tr style="text-align: center;">');
    newRow.append('<td class="long_text" colspan="6"><div class="button fit" style="cursor: auto !important;">신고 내용 : ' + data.reportDTO.reportContent + '</div></td>');
    tableBody.append(newRow);
}

// 모달 내용 초기화 함수
function clearReportModalContent() {
    var tableBody = document.getElementById('report-table');
    tableBody.innerHTML = "";

    // 추가: 신고 내용을 담는 박스 초기화
    var reportContentBox = document.getElementById('report-content-box');
    if (reportContentBox) {
        reportContentBox.remove();
    }
}

document.querySelector('.confirmReportAdminButton').addEventListener('click', function() {
    const reportNum = document.querySelector('#reportNumInput').value;

    const data = {
        reportNum: reportNum
    };

    fetch('/adminmypage/reportConfirm', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            window.alert('신고 처리 완료!!');
            location.reload();
        } else {
            window.alert('신고 처리 실패. 다시 시도하세요.');
        }
    })
    .catch(error => {
        window.alert('오류 발생: ' + error.message);
    });

    clearReportModalContent();
    reportAdminModalBox.classList.remove("active");
});

// 취소 버튼을 클릭할 때 모달을 닫고 내용을 초기화하는 이벤트 핸들러
document.querySelector('.cancelReportAdminButton').addEventListener('click', function() {
    const reportNum = document.querySelector('#reportNumInput').value;

        const data = {
            reportNum: reportNum
        };

        fetch('/adminmypage/reportCancel', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (response.ok) {
                window.alert('신고 철회 완료!!');
                location.reload();
            } else {
                window.alert('신고 철회 실패. 다시 시도하세요.');
            }
        })
        .catch(error => {
            window.alert('오류 발생: ' + error.message);
        });

        clearReportModalContent();
        reportAdminModalBox.classList.remove("active");
});
