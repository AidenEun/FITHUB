const tabs = document.querySelectorAll('.tab');
const tabViews = document.querySelectorAll('.tab-view');

tabs.forEach((tab, index) => {
    tab.addEventListener('click', () => {
        // 모든 탭 뷰 숨기기
        tabViews.forEach((view) => {
            view.style.display = 'none';
        });

        // 클릭한 탭에 해당하는 탭 뷰만 보이게 하기
        tabViews[index].style.display = 'block';
    });
});

const challTabs = document.querySelectorAll('.active-tab, .finish-tab');
const challTabViews = document.querySelectorAll('.active-chall-listView, .finish-chall-listView');

challTabs.forEach((challTab, index) => {
    challTab.addEventListener('click', () => {
        // 모든 진행중, 종료 탭 뷰 숨기기
        challTabViews.forEach((view) => {
            view.style.display = 'none';
        });

        // 클릭한 진행중, 종료 탭에 해당하는 탭 뷰만 보이게 하기
        challTabViews[index].style.display = 'block';

    });
});

/*const progressBar = document.querySelector('.progress-bar');
const attBtn = document.querySelector('.att-btn');
let filledGauges = 0;

attBtn.addEventListener('click', () => {
    if (filledGauges < 7) {
        filledGauges++;
        progressBar.value = filledGauges;

        if (filledGauges === 7) {
            setTimeout(() => {
                progressBar.value = 0;
                filledGauges = 0;
            }, 1000); // 1000ms(1초) 후 초기화
        }
    }
});*/

const progressBar = document.querySelector('.progress-bar');
const attBtn = document.querySelector('.att-btn');
let isAttended = false;

attBtn.addEventListener('click', () => {
    if (!isAttended) {
        progressBar.value += 1;
        isAttended = true;

        if (progressBar.value === 7) {
            // 출석을 7회 완료하면 버튼 비활성화
            attBtn.disabled = true;

            setTimeout(() => {
                // 1초 후에 게이지와 출석 여부 초기화
                progressBar.value = 0;
                isAttended = false;

                // 버튼 다시 활성화
                attBtn.disabled = false;
            }, 1000);
        }
    } else {
        // 이미 출석했음을 알리는 경고 표시
        alert('오늘은 이미 출석 하셨습니다.');
    }
});

document.addEventListener("DOMContentLoaded", function() {
    // 모든 탭 요소와 컨텐츠 요소를 가져옵니다.
    const tabs = document.querySelectorAll("#meal-options > div");
    const contents = document.querySelectorAll("#tan-dan-ji-box > div");

    // 모든 컨텐츠를 숨깁니다.
    contents.forEach(content => {
        content.style.display = "none";
    });

    // 아침 탭에 해당하는 컨텐츠를 표시합니다.
    contents[0].style.display = "block";

    // 각 탭에 클릭 이벤트 리스너를 추가합니다.
    tabs.forEach((tab, index) => {
        tab.addEventListener("click", function() {
            // 모든 컨텐츠를 숨깁니다.
            contents.forEach(content => {
                content.style.display = "none";
            });

            // 클릭한 탭에 해당하는 컨텐츠를 표시합니다.
            contents[index].style.display = "block";
        });
    });
});