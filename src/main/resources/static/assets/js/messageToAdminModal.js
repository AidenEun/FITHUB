const closeMessageToAdmin = document.querySelector(".messageToAdminClose");
const messageToAdminModalBox = document.querySelector('.message_To_Admin_modal_box');

closeMessageToAdmin.addEventListener("click", () => {
    messageToAdminModalBox.classList.remove("active");
    messageToAdminContent.value = '';
});

// 모달 외부 클릭 시 모달 창 닫기
messageToAdminModalBox.addEventListener('click', (e) => {
    if (e.target === messageToAdminModalBox) {
        messageToAdminModalBox.classList.remove("active");
        messageToAdminContent.value = '';
    }
});

$(".resetMessageToAdminButton").on("click", function (e) {
    e.preventDefault();
    messageToAdminContent.value = '';
});

document.querySelector('.cancelMessageToAdminButton').addEventListener('click', function() {
    messageToAdminModalBox.classList.remove("active");
    messageToAdminContent.value = '';
});

$(".messageToAdminModalOpen").on("click", function (e) {
    e.preventDefault();

    messageToAdminModalBox.classList.add("active");
});

document.querySelector('.confirmMessageToAdminButton').addEventListener('click', function() {
    const messageContent = document.querySelector('#messageToAdminContent').value;

    const data = {
        messageContent: messageContent
    };

    fetch('/adminmypage/messageToAdmin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            window.alert('문의 완료!!');
            location.reload();
        } else {
            window.alert('문의 실패. 다시 시도하세요.');
            location.reload();
        }
    })
    .catch(error => {
        window.alert('오류 발생: ' + error.message);
        location.reload();
    });

    messageContent.value = '';
    messageToAdminModalBox.classList.remove("active");
});
