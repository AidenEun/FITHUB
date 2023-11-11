const openMessageButtons = document.querySelectorAll(".messageOpen");
const closeMessage = document.querySelector(".messageClose");
const messageModalBox = document.querySelector('.message_modal_box');

closeMessage.addEventListener("click", () => {
    messageModalBox.classList.remove("active");
    messageContent.value = '';
    messageAdminFormBox.style.display = "none";
});

// 모달 외부 클릭 시 모달 창 닫기
messageModalBox.addEventListener('click', (e) => {
    if (e.target === messageModalBox) {
        messageModalBox.classList.remove("active");
        messageContent.value = '';
        messageAdminFormBox.style.display = "none";
    }
});

function resetModalContent() {
    messageContent.value = '';
}

$(".resetMessageButton").on("click", function (e) {
    e.preventDefault();
    messageContent.value = '';
});

function messageModal(e){
    e.preventDefault();

    var messageNum = $(this).closest('tr').find('.messageNum').text();
    var messageUserId = $(this).closest('tr').find('.messageUserId').text();

    messageModalBox.classList.add("active");
    document.querySelector('#messageUserId').value = messageUserId;
    sendMessageNumToModal(messageNum);
}

function sendMessageNumToModal(messageNum) {
    $.ajax({
        url: '/adminmypage/messageModal',
        method: 'POST',
        data: { messageNum: messageNum },
        success: function(data) {
            messageModalDom(data);
        },
        error: function(error) {
        }
    });
}

function messageModalDom(data) {
    var data = JSON.parse(data);
    var tableBody = $('#message-table');
    tableBody.empty();

    var row = $('<tr style="text-align: center;">');
    row.append('<td class="long_text">' + data.messageDTO.messageNum + '</td>');
    row.append('<td class="long_text"><a href="#" class="open">' + data.messageDTO.sendId + '</a></td>');
    row.append('<td class="long_text">' + data.messageDTO.sendDate + '</td>');
    row.append('<td class="long_text"><a href="#" class="messageFormOpen button">처리하기</a></td>');
    tableBody.append(row);

    var newRow = $('<tr style="text-align: center;">');
    newRow.append('<td class="long_text" style="font-weight: bold;">문의 내용</td>');
    newRow.append('<td class="word_wrap_test" colspan="3">' + data.messageDTO.messageContent + '</td>');
    tableBody.append(newRow);


    $('.open').on('click', profileModalOpen);
    $('.messageFormOpen').on('click', messageFormOpen);
}

document.querySelector('.confirmMessageButton').addEventListener('click', function() {
    alert("확인")
//    const signupNum = document.querySelector('#signupNumInput').value;
//
//    const data = {
//        signupNum: signupNum
//    };
//
//    fetch('/adminmypage/signUpConfirm', {
//        method: 'POST',
//        headers: {
//            'Content-Type': 'application/json'
//        },
//        body: JSON.stringify(data)
//    })
//    .then(response => {
//        if (response.ok) {
//            window.alert('트레이너 전환 승인 완료!!');
//            location.reload();
//        } else {
//            window.alert('트레이너 전환 승인 실패. 다시 시도하세요.');
//        }
//    })
//    .catch(error => {
//        window.alert('오류 발생: ' + error.message);
//    });
//
//    messageModalBox.classList.remove("active");
});

document.querySelector('.cancelMessageButton').addEventListener('click', function() {
    alert("취소");
//    const signupNum = document.querySelector('#signupNumInput').value;
//
//        const data = {
//            signupNum: signupNum
//        };
//
//        fetch('/adminmypage/cancelSignUp', {
//            method: 'POST',
//            headers: {
//                'Content-Type': 'application/json'
//            },
//            body: JSON.stringify(data)
//        })
//        .then(response => {
//            if (response.ok) {
//                window.alert('트레이너 거절 완료!!');
//                location.reload();
//            } else {
//                window.alert('트레이너 거절 실패. 다시 시도하세요.');
//            }
//        })
//        .catch(error => {
//            window.alert('오류 발생: ' + error.message);
//        });
//
//        messageModalBox.classList.remove("active");
});

function messageFormOpen(e){
    e.preventDefault();

    const messageAdminFormBox = document.getElementById("messageAdminFormBox");
    const messageFormOpen = document.querySelector('.messageFormOpen');

    messageAdminFormBox.style.display = "block";
    messageFormOpen.classList.add('primary');
    messageFormOpen.classList.add('disabled');
}
