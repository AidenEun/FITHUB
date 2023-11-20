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

function sendUserIdToModal(trainerNickname) {
    $.ajax({
        url: '/matching/profileModal',
        method: 'POST',
        data: { trainerNickname: trainerNickname },
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
            modalBox.find('.application_buttons').html(
                                                        '<p class="trainer_profile"><a href="/trainermypage/trainer_profile">프로필 자세히 보기</a></p>'
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
            modalBox.find('.img_area img').attr('src', '/images/profile_img.png');
            modalBox.find('.img_area a').attr('href', '#');
            modalBox.find('.img_area img').attr('alt', receiveId);
            modalBox.find('.name a').text(data.userDTO.userNickname + '(' + receiveId + ')');
            modalBox.find('.message').attr('alt', receiveId);
            modalBox.find('.application_buttons').html(
                                                        '<p class="trainer_profile"><a href="/trainermypage/trainer_profile">프로필 자세히 보기</a></p>'
                                                    );

            modalBox.find('.application_buttons').on('click', 'trainer_profile a', function(event) {
                event.preventDefault();
                window.location.href = $(this).attr('href');
            });



            // 함수를 통해 구독 상태를 확인하고, 이에 따라 버튼을 업데이트합니다.
            function checkSubscription(sendId, trainerId) {
                // AJAX를 사용하여 서버로부터 구독 상태 확인
                // 예시로서, 서버 응답이 "subscribed" 또는 "unsubscribed"로 가정합니다.
                $.ajax({
                    url: '/matching/subscribe_check',
                    method: 'POST',
                    data: { sendId: sendId, receiveId: receiveId },
                    success: function (response) {
                        updateSubscribeButton(response);
                    },
                    error: function (error) {
                        console.error('구독 상태를 가져오는 데 실패했습니다.', error);
                    }
                });
            }
            // 구독 상태에 따라 버튼을 업데이트합니다.
              function updateSubscribeButton(response) {
                            if (response === "subscribed") {
                                modalBox.find('.subscribeButton').html('<a href="#" class="subscribe"><img src="/images/subsIng.png" alt="구독O">구독 중</a>');
                            } else if(response === "unsubscribed"){
                                modalBox.find('.subscribeButton').html('<a href="#" class="subscribe"><img src="/images/subsCancel.jpg" alt="구독X">구독</a>');
                            } else{
                                console.log("데이터를 확인해주세요");
                            }
                        }
                        // 페이지 로드 시 구독 상태 확인 후 버튼 업데이트
                        checkSubscription();
                        // 클릭 이벤트를 사용하여 버튼을 토글합니다.
                         const subscribe = document.querySelector(".subscribe");
                        subscribe.addEventListener("click", function() {
                            // 클릭 시, 현재 상태에 따라 서버로 요청을 보내고, 버튼을 업데이트합니다.
                            $.ajax({
                                url: '/matching/subscribe_click',
                                method: 'POST',
                                data: { userId: userId, receiveId: receiveId },
                                success: function(response) {
                                    if (response === "subscribed") {
                                        alert("구독합니다.");
                                        isSubscribed = true;
                                    } else if (response === "unsubscribed") {
                                        alert("구독을 취소합니다.");
                                        isSubscribed = false;
                                    }
                                    updateSubscribeButton(); // 버튼 상태 업데이트
                                },
                                error: function(error) {
                                    console.error('구독 상태 변경에 실패했습니다.', error);
                                }
                            });
                        });


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
