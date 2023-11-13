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

const progressBar = document.querySelector('.progress-bar');
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

/*
document.addEventListener("DOMContentLoaded", function() {
    // 로그인 여부를 확인하는 조건
    const isLoggedIn = checkLoginStatus(); // 로그인 상태를 확인하는 함수로 실제 구현 필요

    if (isLoggedIn) {
        // 로그인한 경우
        const sideInfo = document.querySelector(".side_info");
        const sideNotLogin = document.querySelector(".side-not-login");

        // 로그인 뷰를 표시
        sideInfo.style.display = "block";

        // 로그인하지 않은 뷰를 숨김
        sideNotLogin.style.display = "none";
    } else {
        // 로그인하지 않은 경우
        const sideInfo = document.querySelector(".side_info");
        const sideNotLogin = document.querySelector(".side-not-login");

        // 로그인 뷰를 숨김
        sideInfo.style.display = "none";

        // 로그인하지 않은 뷰를 표시
        sideNotLogin.style.display = "block";
    }
});

// 실제 로그인 상태를 확인하는 함수 (예: 쿠키 또는 서버 요청)
function checkLoginStatus() {
    // 로그인 상태 확인 로직을 구현하고 true 또는 false 반환
    // 여기에서는 간단히 false를 반환하도록 설정되어 있습니다.
    return false; // 실제로는 로그인 상태를 확인하는 코드로 대체해야 합니다.
}*/