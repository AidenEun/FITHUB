const WriteOpen = document.querySelector(".writeOpen");
const writeClose = document.querySelector(".writeClose");
const noticeWriteModalBox = document.querySelector('.noticeWrite_modal_box');


$('.writeOpen').on('click',challNoticeBoardModal);

writeClose.addEventListener("click", () => {
    clearChallNoticeBoardModalContent();
    noticeWriteModalBox.classList.remove("active");
});

// 모달 외부 클릭 시 모달 창 닫기
noticeWriteModalBox.addEventListener('click', (e) => {
    if (e.target === noticeWriteModalBox) {
        clearChallNoticeBoardModalContent();
        noticeWriteModalBox.classList.remove("active");
    }
});


function challNoticeBoardModal(e){
    e.preventDefault();

    noticeWriteModalBox.classList.add("active");

}

// 모달 내용 초기화 함수
function clearChallNoticeBoardModalContent() {
    const challName = document.querySelector("#challName");
    if (challName !== null) {
        challName.value = "";
    }

    const challCategory = document.querySelector("#challCategory");
    if (challCategory !== null) {
        challCategory.value = 'challExer';
    }

    const challTerm = document.querySelector("#challTerm");
    if (challTerm !== null) {
        challTerm.value = '7';
    }

    const challContent = document.querySelector("#challContent");
    if (challContent !== null) {
        challContent.value = '';
    }
}


function writeSendit(){
const boardForm = document.boardForm;

		const challName = boardForm.challName;
		if(challName.value == ""){
			alert("제목을 입력하세요!");
			challName.focus();
			return false;
		}

		const challContent = boardForm.challContent;
		if(challContent.value == ""){
			alert("내용을 입력하세요!");
			challContent.focus();
			return false;
		}

    boardForm.submit();
}





