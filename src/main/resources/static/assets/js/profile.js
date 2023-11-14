const openButtons = document.querySelectorAll(".open");
const messageButtons = document.querySelectorAll(".message");
const close = document.querySelector(".close");
const backButtons = document.querySelectorAll(".back");
const modalBox = document.querySelector('.modal_box');
const MessageModalBox = document.querySelector('#userMessageModal'); // 메시지 모달 상자를 가져옵니다.

openButtons.forEach(openButton => {
    openButton.addEventListener("click", () => {
        modalBox.classList.add("active");
    });
});

messageButtons.forEach(messageButton => {
    messageButton.addEventListener("click", () => {
        // 메시지 버튼을 클릭하면 메시지 모달을 보여줍니다.
        MessageModalBox.style.display = "block";
    });
});

close.addEventListener("click", () => {
    modalBox.classList.remove("active");
    // 닫기 버튼을 클릭하면 메시지 모달을 숨깁니다.
    MessageModalBox.style.display = "none";
});

backButtons.forEach(backButton => {
    backButton.addEventListener("click", () => {
        userMessageModal.style.display = "none";
    });
});

function profileModalOpen(e){
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
            var modalBox = $('.modal_box');
            var receiveId = data.trainerDTO.trainerId
            modalBox.find('.img_area img').attr('src', '/images/profile_img.png');
            modalBox.find('.img_area a').attr('href', '#');
            modalBox.find('.img_area img').attr('alt', receiveId);
            modalBox.find('.name a').text(data.trainerDTO.trainerNickname + '(' + receiveId + ')');
            modalBox.find('.message').attr('alt', receiveId);


            MessageModalBox.find('.profile_userId a').attr('alt', receiveId);
            MessageModalBox.find('.loginUser_userId a').attr('alt', sendId);

            MessageModalBox.find('.send-message').on('click', function () {
            var contents = MessageModalBox.find('.contents').val();
            sendApplication(receiveId,sendId,contents);
            });


        }
        else if (data.hasOwnProperty("userDTO")) {
            var modalBox = $('.modal_box');
            var receiveId = data.userDTO.userId
            modalBox.find('.img_area img').attr('src', '/images/profile_img.png');
            modalBox.find('.img_area a').attr('href', '#');
            modalBox.find('.img_area img').attr('alt', receiveId);
            modalBox.find('.name a').text(data.userDTO.userNickname + '(' + receiveId + ')');
            modalBox.find('.message').attr('alt', receiveId);


            MessageModalBox.find('.profile_userId').attr('alt', receiveId);
            MessageModalBox.find('.loginUser_userId').attr('alt', sendId);

            MessageModalBox.find('.send-message').on('click', function () {
            var contents = MessageModalBox.find('.contents').val();
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
    var MessageModalBox = $('.modal_box');
    MessageModalBox.removeClass("active");
}