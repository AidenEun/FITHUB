const openButtons = document.querySelectorAll(".open");
const close = document.querySelector(".close");
const modalBox = document.querySelector('.modal_box');

openButtons.forEach(openButton => {
    openButton.addEventListener("click", () => {
        modalBox.classList.add("active");
    });
});

close.addEventListener("click", () => {
    modalBox.classList.remove("active");
});

document.addEventListener("click", (event) => {
    // Check if the click is outside the modal
    if (!modalBox.contains(event.target)) {
        modalBox.classList.remove("active");
    }
});

function profileModalOpen(e){
    e.preventDefault();
    var userId = $(this).text();

    modalBox.classList.add("active");
    sendUserIdToModal(trainerNickname);
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
        if (data.hasOwnProperty("trainerDTO")) {
            var modalBox = $('.modal_box');
            modalBox.find('.img_area img').attr('src', '/images/profile_img.png');
            modalBox.find('.img_area a').attr('href', '#');
            modalBox.find('.img_area img').attr('alt', data.trainerDTO.trainerNickname);
            modalBox.find('.name a').text(data.trainerDTO.trainerNickname + '(' + data.trainerDTO.trainerId + ')');
        }
        else if (data.hasOwnProperty("userDTO")) {
            var modalBox = $('.modal_box');
            modalBox.find('.img_area img').attr('src', '/images/profile_img.png');
            modalBox.find('.img_area a').attr('href', '#');
            modalBox.find('.img_area img').attr('alt', data.userDTO.userNickname);
            modalBox.find('.name a').text(data.userDTO.userNickname + '(' + data.userDTO.userId + ')');
        }
        else if (data.hasOwnProperty("noData")) {
            alert("없는 회원입니다!");
        }
    } catch (error) {
        console.error("올바른 데이터 형식이 아닙니다:", error);
    }
}