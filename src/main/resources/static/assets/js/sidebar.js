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

    console.log('.progress-bar:', progressBar);
    console.log('.att-btn:', attBtn);
    console.log('pointDisplay:', pointDisplay);

    if (!progressBar || !attBtn || !pointDisplay) {
        console.error('하나 이상의 요소를 찾을 수 없습니다.');
        return;
    }

    let filledGauges = 0;
    let totalPoints = pointDisplay ? parseInt(pointDisplay.innerText) || 0 : 0;

    attBtn.addEventListener('click', function () {
        filledGauges++;
        progressBar.value = filledGauges;

        if (filledGauges >= 7) {
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

/*document.addEventListener('DOMContentLoaded', function () {
    const progressBar = document.querySelector('.progress-bar');
    const attBtn = document.querySelector('.att-btn');
    const pointDisplay = document.getElementById('pointDisplay');

    console.log('.progress-bar:', progressBar);
    console.log('.att-btn:', attBtn);
    console.log('pointDisplay:', pointDisplay);

    if (!progressBar || !attBtn || !pointDisplay) {
        console.error('One or more elements not found.');
        return;
    }

    let filledGauges = 0;
    let totalPoints = pointDisplay ? parseInt(pointDisplay.innerText) || 0 : 0;
    let alreadyAttended = false;

    attBtn.addEventListener('click', function () {
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

    document.addEventListener("DOMContentLoaded", function () {
            const tabs = document.querySelectorAll("#meal-options > div");
            const contents = document.querySelectorAll("#tan-dan-ji-box > div");

            contents.forEach(content => {
                content.style.display = "none";
            });

            contents[0].style.display = "block";

            tabs.forEach((tab, index) => {
                tab.addEventListener("click", function () {
                    contents.forEach(content => {
                        content.style.display = "none";
                    });

                    contents[index].style.display = "block";
                });
            });
        });
    });