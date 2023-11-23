document.querySelectorAll(".menubtn").forEach(function (btn) {
    btn.addEventListener('click', function (e) {
        const clickbtnTd = e.target.closest('td');
        if (clickbtnTd.classList.contains('icon')) {
            console.log("icon 요소 포함");
            return;
        } else {
            console.log("icon요소 없음");
            document.querySelectorAll('td').forEach(function (otherTd) {
                if (otherTd.classList.contains('icon')) {
                    otherTd.classList.remove('icon');
                }
                clickbtnTd.classList.add('icon');
            })
            if (e.target.id === 'today') {
                menutodaybtn();
            } else if (e.target.id === 'chall') {
                menuchallbtn();
            }else if(e.target.id === 'diary'){
                moveDiary();
            }else if(e.target.id === 'shop'){
                moveShop();
            }

        }
    })
})

function menutodaybtn() {
    console.log("다이어리뷰 실행");
    const userid = document.getElementsByName("userid")[0].value;
    const todaydate = document.getElementById("todaydate").value;
        $.ajax({
            url: '/usermypage/todaydiary',
            method: 'POST',
            data: {
                userid: userid,
                todaydate: todaydate
            },
            dataType: 'json',
            success: function (data) {
                displaydiaryData(data.diaryDTO,data.todaydate);

            },
            error: function (error) {
                console.error('데이터를 가져오는 중 오류 발생' + error);
            }

        })

}

function displaydiaryData(diaryDTO,todaydate) {

    console.log("ajax성공")
    let navi_main_content = $("#navi_main_content");
    navi_main_content.empty();

    if (diaryDTO != null) {
        let table = $('<table style="margin-bottom: 10px;">');
        let row = $('<tr>');
        row.append('<td><span class="highlight">현재칼로리/목표칼로리</span></td>');
        row.append('<td><span class="highlight">오늘의 비교</span></td>');
        let secondRow = $('<tr>');

        secondRow.append('<td>' + diaryDTO.nowCal + 'kcal/' + diaryDTO.caloriesGoal + 'kcal</td>');
        secondRow.append('<td>'+ diaryDTO.todayKGGap+'kg</td>');
        table.append(row);
        table.append(secondRow);
        navi_main_content.append(table);

        let calArea = $("<div class='cal_area'>");

        let calTop=$("<div class='cal_top'>");
        calTop.append("<div class='foodCal' onclick='moveTodaydiary()'>아침<br>" + diaryDTO.bfCal + "kcal</div>");
        calTop.append("<div class='foodCal' onclick='moveTodaydiary()'>점심<br>" + diaryDTO.lunchCal + "kcal</div>");

        let calBottom =$("<div class='cal_bottom'>");
        calBottom.append("<div class='foodCal' onclick='moveTodaydiary()'>저녁<br>" + diaryDTO.dinnerCal + "kcal</div>");
        calBottom.append("<div class='foodCal' onclick='moveTodaydiary()'>간식<br>" + diaryDTO.snackCal + "kcal</div>");

        calArea.append(calTop);
        calArea.append(calBottom);
        navi_main_content.append(calArea);

    } else {
        let table = $('<table style="padding-top: 82px;">');
        let row = $('<tr>');
        row.append("<td style='vertical-align: bottom'>" + todaydate + "</td>");
        let secondRow = $("<tr>");
        secondRow.append("<td>오늘 작성한 다이어리가 없습니다!</td>");
        table.append(row);
        table.append(secondRow);
        navi_main_content.append(table);
        let newDiv = $("<div>").html('<a class="subColor" href="/usermypage/user_diary">다이어리 작성하러가기</a>');
        navi_main_content.append(newDiv);
    }

}


function menuchallbtn() {
    console.log("챌린지뷰 실행");
    const userid = document.getElementsByName("userid")[0].value;
    const todaydate = document.getElementById("todaydate").value;
    if (userid, todaydate) {
        $.ajax({
            url: '/challenge/myChallInfo',
            method: 'POST',
            data: {
                userid: userid,
                choicedate: todaydate
            },
            dataType: 'json',
            success: function (data) {
                displayMyChallData(data.myChallDTOList);

            },
            error: function (error) {
                console.error('데이터를 가져오는 중 오류 발생' + error);
            }

        })
    }
}

function displayMyChallData(myChallDTOList) {
    let navi_main_content = $("#navi_main_content");
    navi_main_content.empty();

    if (myChallDTOList != null && myChallDTOList.length > 0) {
        let table = $('<table style="margin-bottom: 10px;">');
        for (let i = 0; i < myChallDTOList.length; i++) {
            let row = $('<tr>');
            row.append('<td style="width: 15%;">' + myChallDTOList[i].challNum + '</td>');
            row.append('<td>' + myChallDTOList[i].challName + '</td>');
            table.append(row);
        }
        navi_main_content.append(table);
    } else {
        let row = $('<tr>');
        row.append('<td style="text-align: center;">진행 중인 챌린지가 없습니다.</td>');
        let table = $('<table style="padding-top: 82px;">');
        table.append(row);
        navi_main_content.append(table);
    }

    let newDiv = $("<div>").html('<a class="subColor" href="/challenge/list">챌린지 신청하기</a>');
    navi_main_content.append(newDiv);
}

function moveDiary() {
    let navi_main_content = $("#navi_main_content");
    navi_main_content.empty();
    let row = $('<tr>');
    row.append('<td style="text-align: center;">오늘은 또 무슨일이 생길까🎵</td>');
    let table = $('<table style="padding-top: 82px;">');
    table.append(row);
    navi_main_content.append(table);
    let newDiv = $("<div>").html('<a class="subColor" href="/usermypage/user_diary">일기장 보러가기</a>');
    navi_main_content.append(newDiv);
    location.href = "/usermypage/user_diary";
}


function moveShop() {
    let navi_main_content = $("#navi_main_content");
    navi_main_content.empty();
    let row = $('<tr>');
    row.append('<td style="text-align: center;">쇼핑하는 중🛒</td>');
    let table = $('<table style="padding-top: 82px;">');
    table.append(row);
    navi_main_content.append(table);
    let newDiv = $("<div>").html('<a class="subColor" href="/product/prod_list">쇼핑하러 가기</a>');
    navi_main_content.append(newDiv);
    location.href = "/product/prod_list";
}

function moveTodaydiary(){
    const todaydate = document.getElementById("todaydate").value;
    location.href = "/usermypage/diaryView?choicedate=" + todaydate;
}