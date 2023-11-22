const openMatchingButtons = document.querySelectorAll(".matchingOpen");
const matchingClose = document.querySelector(".matchingClose");
const matchingModalBox = document.querySelector('.matching_modal_box');
// 이벤트 핸들러 등록
openMatchingButtons.forEach(openMatchingButton => {
    openMatchingButton.addEventListener("click", () => {
     matchingModalBox.classList.add("active");
    });
});

// 모달 닫기 버튼에 이벤트 핸들러 등록
matchingClose.addEventListener("click", () => {
   matchingModalBox.classList.remove("active");
});

// 모달 외부 클릭 시 닫기
document.addEventListener("click", () => {
    if (!matchingModalBox.contains(event.target)) {
        matchingModalBox.classList.remove("active");
    }
});

function matchingModalOpen(e) {
    e.preventDefault();
    var trainerId = $('#matchingTrainerId').html();

    matchingModalBox.classList.add("active");
    sendTrainerIdToModal(trainerId);
}

$(".matchingOpen").on("click", function (e) {
    e.preventDefault();
    var trainerId = $('#matchingTrainerId').html();
    var boardNum = document.getElementById('boardNum2').value;

    sendTrainerIdToModal(trainerId, boardNum);
    matchingModalBox.classList.add("active");
});

// AJAX로 데이터를 가져오고 모달 열기
function sendTrainerIdToModal(trainerId, boardNum) {
    $.ajax({
        url: '/matching/u_t_matchModal',
        method: 'POST',
        data: {
            trainerId: trainerId,
            boardNum: boardNum
         },
        success: function (data) {
         console.log(data);
            matchingModal(data);
        },
        error: function (error) {
        }
    });
}

// 모달에 데이터 적용
function matchingModal(data) {
    try {
        data = JSON.parse(data);
        var userId = data.userId;
        var trainerId = data.trainerMatchingBoardDTO.trainerId;
        var boardNum = data.boardNum;


        if (!data.hasOwnProperty("trainerMatchingBoardDTO")) {
            console.error("TrainerMatchingBoardDTO is undefined.");
            return; // TrainerMatchingBoardDTO가 없으면 종료
        }

        if (userId === trainerId) {
            var matchingModalBox = $('.matching_modal_box');
            // 사용자 ID와 트레이너 닉네임이 같을 경우 신청 주의사항 표시
            matchingModalBox.find('.name a').text(trainerId + '(' + userId + ')');
            matchingModalBox.find('.application_notice a').text('자신에게 신청할 수 없습니다.');
        } else {
            // 신청서가 이미 있는지 확인
                if (data.hasOwnProperty("utMatchingDTO") && data.utMatchingDTO.userId === userId && data.utMatchingDTO.trainerId === trainerId) {
                     var matchingModalBox = $('.matching_modal_box');
                    matchingModalBox.find('.application_notice a').text('이미 신청한 신청서입니다.');
                } else if (data.hasOwnProperty("noData")){
                     console.log("다시 로그인 해주세요. 지금 같은 상황이 반복될시 문의사항에 문의 주세요!");
                }
                else  {

                    var matchingModalBox = $('.matching_modal_box');

                                        matchingModalBox.find('.application_buttons').html(
                                            '<p class="application_notice">신청 주의사항:</p>' +
                                            '<ol>' +
                                            '   <li>사칭에 주의하세요: 매칭 상대방의 정체성을 확인하고 신뢰할 수 있는 정보를 교환하세요.</li>' +
                                            '   <li>불의의 사고 대비를 위해 예의주시하세요: 만남 전에 상대방과의 소통을 통해 명확한 규칙을 정하고, 불의의 사고가 발생할 경우 어떻게 대응할지 미리 준비하세요.</li>' +
                                            '   <li>사이트는 책임을 지지 않습니다: 플랫폼은 매칭 프로세스를 중개하고 있을 뿐, 실제로 발생하는 트레이닝 세션에 대한 책임은 사용자 본인들에게 있습니다.</li>' +
                                            '   <li>매칭 신청자와의 확실한 소통: 매칭을 신청한 상대방과는 세부 사항에 대한 확실한 소통이 필요합니다.</li>' +
                                            '   <li>불만이나 우려사항은 즉시 신고: 만약 매칭 중 불만이나 우려사항이 발생하면 플랫폼에서 제공하는 신고 기능을 이용하여 즉시 신고하세요.</li>' +
                                            '   <li>중재 및 해결 프로세스 이해: 플랫폼은 불가피한 경우를 대비하여 중재 및 해결 프로세스를 마련하고 있습니다.</li>' +
                                            '</ol>' +
                                            '<br>' +
                                            '<button class="apply_now">신청하기</button>'

                                        );

                                        // "신청하기" 버튼에 이벤트 핸들러 등록
                                        matchingModalBox.find('.apply_now').on('click', function () {
                                            sendApplication(userId, trainerId, boardNum);
                                            console.log(userId)
                                            console.log(trainerId)
                                            alert(boardNum);
                                        });

                }
        }

        matchingModalBox.addClass("active");
    } catch (error) {
        console.error("올바른 데이터 형식이 아닙니다:", error);
    }
};

// 서버에 신청 데이터 전송
// 서버에 신청 데이터 전송
function sendApplication(userId, trainerId, boardNum) {
    $.ajax({
        url: '/matching/apply',
        method: 'POST',
        data: {
            userId: userId,
            trainerId: trainerId,
            boardNum: boardNum
        },
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
    closeMatchingModal();
}

// 모달 닫기
function closeMatchingModal() {
    var matchingModalBox = $('.matching_modal_box');
    matchingModalBox.removeClass("active");
}