document.addEventListener('DOMContentLoaded', function () {
    // 탭과 탭 뷰를 선택합니다.
    const tabs = document.querySelectorAll('.tab');
    const tabViews = document.querySelectorAll('.tab-view');

    // 도전과제 탭과 뷰를 선택합니다.
    const challTabs = document.querySelectorAll('.active-tab, .finish-tab');
    const challTabViews = document.querySelectorAll('.active-chall-listView, .finish-chall-listView');

    // 진행바, 출석 버튼, 포인트 표시를 선택합니다.
    const progressBar = document.querySelector('.progress-bar');
    const attBtn = document.querySelector('.att-btn');
    const pointDisplay = document.getElementById('pointDisplay');

    // 초기 출석 플래그 및 기타 변수를 설정합니다.
    let filledGauges = 0;
    let totalPoints = pointDisplay ? parseInt(pointDisplay.innerText) || 0 : 0;
    let alreadyAttended = false;

    // 탭 클릭 시 이벤트 리스너를 추가합니다.
    tabs.forEach((tab, index) => {
        tab.addEventListener('click', () => {
            // 모든 탭 뷰를 숨깁니다.
            tabViews.forEach((view) => {
                view.style.display = 'none';
            });

            // 클릭한 탭에 해당하는 탭 뷰만 보이게 합니다.
            tabViews[index].style.display = 'block';
        });
    });

    // 도전과제 탭 클릭 시 이벤트 리스너를 추가합니다.
    challTabs.forEach((challTab, index) => {
        challTab.addEventListener('click', () => {
            // 모든 도전과제 탭 뷰를 숨깁니다.
            challTabViews.forEach((view) => {
                view.style.display = 'none';
            });

            // 클릭한 도전과제 탭에 해당하는 뷰만 보이게 합니다.
            challTabViews[index].style.display = 'block';
        });
    });

    // 출석 버튼 클릭 시 이벤트 리스너를 추가합니다.
    /*attBtn.addEventListener('click', function () {
        if (!alreadyAttended) {
            filledGauges++;
            progressBar.value = filledGauges;

            if (filledGauges === 7) {
                totalPoints += 10;
                pointDisplay.innerText = totalPoints + 'p';

                setTimeout(function () {
                    progressBar.value = 0;
                    filledGauges = 0;
                }, 1000);

                alert('축하합니다! 포인트 10점을 획득하셨습니다!');
            } else {
                alert('출석이 완료되었습니다.');
            }

            alreadyAttended = true;
        } else {
            alert('오늘은 이미 출석을 완료하였습니다.');
        }
    });*/
    attBtn.addEventListener('click', function () {
        filledGauges++;
        progressBar.value = filledGauges;

        if (filledGauges === 7) {
            totalPoints += 10;
            pointDisplay.innerText = totalPoints + 'p';

            setTimeout(function () {
                progressBar.value = 0;
                filledGauges = 0;
            }, 1000);

            alert('축하합니다! 포인트 10점을 획득하셨습니다!');
        } else {
            alert('출석이 완료되었습니다.');
        }
    });
});