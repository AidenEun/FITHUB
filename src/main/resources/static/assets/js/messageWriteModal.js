const closeMessageWrite = document.querySelector(".messageWriteClose");
const messageWriteModalBox = document.querySelector('.message_Write_modal_box');

closeMessageWrite.addEventListener("click", () => {
    messageWriteModalBox.classList.remove("active");
    messageWriteContent.value = '';
    messageReceiveId.value = '';
});

// 모달 외부 클릭 시 모달 창 닫기
messageWriteModalBox.addEventListener('click', (e) => {
    if (e.target === messageWriteModalBox) {
        messageWriteModalBox.classList.remove("active");
        messageWriteContent.value = '';
        messageReceiveId.value = '';
    }
});

$(".resetMessageWriteButton").on("click", function (e) {
    e.preventDefault();
    messageWriteContent.value = '';
    messageReceiveId.value = '';
});

document.querySelector('.cancelMessageWriteButton').addEventListener('click', function() {
    messageWriteModalBox.classList.remove("active");
    messageWriteContent.value = '';
    messageReceiveId.value = '';
});

$(".messageWriteModalOpen").on("click", function (e) {
    e.preventDefault();

    messageWriteModalBox.classList.add("active");
});

document.querySelector('.confirmMessageWriteButton').addEventListener('click', function() {
    const messageContent = document.querySelector('#messageWriteContent').value;
    const receiveId = document.querySelector('#messageReceiveId').value;

    const data = {
        messageContent: messageContent,
        receiveId: receiveId
    };

    fetch('/adminmypage/messageWrite', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            window.alert('쪽지 전송 완료!!');
            location.reload();
        } else {
            window.alert('쪽지 전송 실패. 다시 시도하세요.');
            location.reload();
        }
    })
    .catch(error => {
        window.alert('오류 발생: ' + error.message);
        location.reload();
    });

    messageWriteContent.value = '';
    messageReceiveId.value = '';
    messageWriteModalBox.classList.remove("active");
});