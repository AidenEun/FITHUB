const openButtons = document.querySelectorAll(".message");
const close = document.querySelector(".close");
const modalBox = document.querySelector('.modal_box');

messageButtons.forEach(messageButton => {
    openButton.addEventListener("click", () => {
        modalBox.classList.add("active");
    });
});

close.addEventListener("click", () => {
    modalBox.classList.remove("active");
});

function userMessageOpen(e){
    e.preventDefault();
    var userId = $(this).text();

    modalBox.classList.add("active");
    sendUserIdToModal(userId);
}

function sendUserIdToModal(userId) {
    $.ajax({
        url: '/adminmypage/profileModal',
        method: 'POST',
        data: { userId: userId },
        success: function(data) {
            modal(data);
        },
        error: function(error) {
        }
    });
}

function modal(data) {
    try {
        data = JSON.parse(data);
        var sendId = data.loginUser_userId;
        if (data.hasOwnProperty("trainerDTO")) {
        var receiveId = data.trainerDTO.trainerId
            var modalBox = $('.modal_box');
            modalBox.find('.profile_userId').attr('alt', receiveId);
            modalBox.find('.loginUser_userId').attr('alt', sendId);
             modalBox.find('.contents').attr('alt', contents)

          modalBox.find('.send-message').on('click', function () {
               sendApplication(receiveId,sendId,contents);
               });
        }
        else if (data.hasOwnProperty("userDTO")) {
        var receiveId = data.userDTO.userId
            var modalBox = $('.modal_box');
            modalBox.find('.profile_userId').attr('alt', receiveId);
            modalBox.find('.loginUser_userId').attr('alt', sendId);
            modalBox.find('.contents').attr('alt', contents)

        modalBox.find('.send-message').on('click', function () {
        sendApplication(receiveId,sendId,contents);
        });
        }
        else if (data.hasOwnProperty("noData")) {
            alert("없는 회원입니다!");
        }
    } catch (error) {
        console.error("올바른 데이터 형식이 아닙니다:", error);
    }
}
function sendApplication(receiveId, sendId, contents) {
    $.ajax({
        url: '/adminmypage/send_message',
               method: 'POST',
        data: { receiveId: receiveId, sendId: sendId, contents: contents},
        success: function (response) {
            handleApplicationResponse(response);
        },
        error: function (error) {
            console.error("Error submitting application:", error);
        }
    });
}

// 신청 결과 처리
function handleApplicationResponse(response) {
    if (response === "success") {
        // 성공적으로 신청된 경우
        alert("신청이 성공적으로 완료되었습니다.");
        // 여기에 성공 처리에 대한 추가적인 동작을 추가할 수 있습니다.
    } else if (response === "already_matched") {
        // 이미 매칭된 경우
        alert("이미 매칭된 상태입니다.");
        // 여기에 이미 매칭된 경우의 처리를 추가할 수 있습니다.
    } else {
        // 그 외의 경우
        alert("신청 중 오류가 발생했습니다.");
        // 여기에 오류 처리에 대한 추가적인 동작을 추가할 수 있습니다.
    }

    // 모달을 닫기
    closeModal();
}

// 모달 닫기
function closeModal() {
    var modalBox = $('.modal_box');
    modalBox.removeClass("active");
}