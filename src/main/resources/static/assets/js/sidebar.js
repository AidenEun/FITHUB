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
const numbers = progressBar.querySelectorAll('span');
const numCount = numbers.length;

// 프로그래스 바의 전체 너비
const progressBarWidth = progressBar.offsetWidth;

// 숫자 사이의 간격을 계산
const spacing = (progressBarWidth - (numCount * 20)) / (numCount - 1);

// 각 숫자에 간격을 적용
numbers.forEach((number, index) => {
    number.style.marginRight = `${index === numCount - 1 ? 0 : spacing}px`;
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