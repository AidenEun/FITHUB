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
//            modalBox.find('.img_area img').attr('src', '/images/profile_img.png');
//            modalBox.find('.img_area a').attr('href', '#');


            if (data.profile != null) {
                var ext = data.profile.orgName.split(".");
                var imageSrc = '/trainermypage/thumbnail?sysName=' + data.profile.sysName;
                if (ext.indexOf("jpg") !== -1 || ext.indexOf("jpeg") !== -1 || ext.indexOf("png") !== -1 || ext.indexOf("gif") !== -1 || ext.indexOf("webp") !== -1) {
                    modalBox.find('.img_area img').attr('src', imageSrc);
                }
                else{
                    modalBox.find('.img_area img').attr('src', "/images/profile_img.png");
                }
            }
            else{
               modalBox.find('.img_area img').attr('src',"/images/profile_img.png");
            }

            modalBox.find('.img_area img').attr('alt', receiveId);
            modalBox.find('.name a').text(data.trainerDTO.trainerNickname + '(' + receiveId + ')');
            modalBox.find('.message').attr('alt', receiveId);
            modalBox.find('.application_buttons').html(
                                                         '<p class="trainer_profile"><a href="/trainermypage/trainer_profile?trainerId=' + receiveId + '">프로필 자세히 보기</a></p>'
                                                    );

            modalBox.find('.profile_userId a').text(receiveId);

            modalBox.find('.loginUser_userId a').text(sendId);

            modalBox.find('.send-message').on('click', function () {
            var contents = modalBox.find('.contents').val();
            sendApplication(receiveId,sendId,contents);
            });


        }
        else if (data.hasOwnProperty("userDTO")) {
            var modalBox = $('.modal_box');
            var receiveId = data.userDTO.userId
//            modalBox.find('.img_area img').attr('src', '/images/profile_img.png');
//            modalBox.find('.img_area a').attr('href', '#');

            if (data.profile != null) {
                var ext = data.profile.orgName.split(".");
                var imageSrc = '/usermypage/thumbnail?sysName=' + data.profile.sysName;
                if (ext.indexOf("jpg") !== -1 || ext.indexOf("jpeg") !== -1 || ext.indexOf("png") !== -1 || ext.indexOf("gif") !== -1 || ext.indexOf("webp") !== -1) {
                    modalBox.find('.img_area img').attr('src', imageSrc);
                }
                else{
                    modalBox.find('.img_area img').attr('src', "/images/profile_img.png");
                }
            }
            else{
               modalBox.find('.img_area img').attr('src', "/images/profile_img.png");
            }

            modalBox.find('.img_area img').attr('alt', receiveId);
            modalBox.find('.name a').text(data.userDTO.userNickname + '(' + receiveId + ')');
            modalBox.find('.message').attr('alt', receiveId);
            modalBox.find('.application_buttons').html(
                                                        '<p class="trainer_profile"><a href="/trainermypage/trainer_profile?trainerId=' + receiveId + '">프로필 자세히 보기</a></p>'
                                                    );

            modalBox.find('.profile_userId a').text(receiveId);
            modalBox.find('.loginUser_userId a').text(sendId);

            modalBox.find('.send-message').on('click', function () {
            var contents = modalBox.find('.contents').val();
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
        url: '/matching/send_message',
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
        alert("쪽지보내기 완료.");
        // 여기에 성공 처리에 대한 추가적인 동작을 추가할 수 있습니다.
    } else if (response === "already_matched") {
        // 이미 매칭된 경우
        alert("이미 보내진 상태입니다.");
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

//모달 닫았을때 스크롤 올라가기 막음.
$(document).on('click','a[href="#"]',function (e){
    e.preventDefault();
});
