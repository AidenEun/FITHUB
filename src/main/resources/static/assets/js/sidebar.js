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

document.addEventListener('DOMContentLoaded', function () {
    const progressBar = document.querySelector('.progress-bar');
    const attBtn = document.querySelector('.att-btn');
    const pointDisplay = document.getElementById('pointDisplay');

    let filledGauges = 0; // 현재 출석 게이지가 찬 횟수
    let totalPoints = parseInt(pointDisplay.innerText); // 초기 포인트 설정
    let alreadyAttended = false; // 출석 여부 확인

    attBtn.addEventListener('click', function () {
        if (!alreadyAttended) {
            filledGauges++;
            progressBar.value = filledGauges;

            if (filledGauges === 7) {
                // 7회 출석 완료 시 포인트 지급
                totalPoints += 10; // 10 포인트로 테스트

                // 포인트 표시 갱신
                pointDisplay.innerText = totalPoints + 'p';

                // 출석 게이지 초기화
                setTimeout(function () {
                    progressBar.value = 0;
                    filledGauges = 0;
                }, 1000);

                // 축하 및 포인트 지급 알림 메시지
                alert('축하합니다! 포인트 10점을 획득하셨습니다!');
            } else {
                // 출석이 완료되었습니다. 알림 메시지
                alert('출석이 완료되었습니다.');
            }

            // 출석 여부 갱신
            alreadyAttended = true;
        } else {
            // 중복 출석 경고 메시지
            alert('오늘은 이미 출석을 완료하였습니다.');
        }
    });
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