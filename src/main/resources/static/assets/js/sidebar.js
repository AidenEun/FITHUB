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