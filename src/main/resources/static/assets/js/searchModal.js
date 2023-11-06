const opensearchButtons = document.querySelectorAll(".opensearch");
const searchclose = document.querySelector(".searchclose");
const searchModalBox = document.querySelector('.searchModalBox');

opensearchButtons.forEach(opensearchButtons => {
    opensearchButtons.addEventListener("click", () => {
        const search_input = document.getElementById("search_bar");
        search_input.value = "";

        searchModalBox.classList.add("active");
    });
});

searchclose.addEventListener("click", () => {
    searchModalBox.classList.remove("active");
});


function calsearch() {

    const search_input = document.getElementById("search_bar");
    const search_keyword = search_input.value;


    // console.log(search_input);
    console.log(search_keyword);

    const category = document.querySelector('input[name="searchCategory"]:checked');

    if (!category) {
        alert("카테고리가 선택되지 않았습니다");
    } else {
        const category = document.querySelector('input[name="searchCategory"]:checked').value;
        if (category === "bf" || "lunch" || "dinner" || "snack") {
            console.log("음식으로 보내기");
            if (search_keyword) {
                $.ajax({
                    url: '/calorie/foodModal_search',
                    method: 'POST',
                    data: {
                        keyword: search_keyword
                    },
                    dataType: 'json',
                    success: function (data) {
                        displayData(data.foodList);
                    },
                    error: function (error) {
                        console.error('데이터를 가져오는 중 오류 발생' + error);
                    }

                })
            }
        } else {
            console.log("운동으로 보내기");
        }
    }


    function displayData(foodList) {
        var tableBody = $('#data-table');
        tableBody.empty();

        // 테이블 헤더를 찾아 제거
        tableBody.prev('thead').remove();

        console.log(foodList);
        if (foodList != null && foodList.length > 0) {
            var tableHead = $('<thead>');
            var tableHeadRow = $('<tr>');
            // tableHeadRow.append('<th>선택</th>');
            tableHeadRow.append('<th>음식명</th>');
            tableHeadRow.append('<th>칼로리</th>');
            tableHead.append(tableHeadRow);

            foodList.forEach(function (foodData) {
                var row = $('<tr class="foodData">');
                // row.append('<td>1</td>');
                row.append('<td>' + foodData.foodName + '</td>');
                row.append('<td>' + foodData.foodCalories + 'kcal</td>');
                tableBody.append(row);
            });
            // 테이블 헤더를 테이블 앞에 추가
            tableBody.before(tableHead);
        } else {
            var row = $('<tr>');
            row.append('<td colspan="2">검색된 결과가 없습니다</td>');
            tableBody.append(row);
        }

        const foodDatas = document.querySelectorAll('.foodData');

        foodDatas.forEach(function (tr) {
            tr.addEventListener("click", () => {
                if (tr.classList.contains("checked")) {
                    tr.classList.remove("checked");

                } else {
                    tr.classList.add("checked");

                }
            })
        });
    }
}

function sendResult() {
    const category = document.querySelector('input[name="searchCategory"]:checked').value;

    var checkedClass = document.querySelectorAll(".checked");

    var results = [];
    var calResults = 0;

    for (var i = 0; i < checkedClass.length; i++) {
        var tdFoodName = checkedClass[i].querySelector("td:nth-child(1)");
        var tdFoodCal = checkedClass[i].querySelector("td:nth-child(2)");
        var realFoodCal = tdFoodCal.innerHTML.slice(0, -4);

        if (tdFoodName) {
            results.push(tdFoodName.innerHTML);
            calResults += Number(realFoodCal);
            console.log(calResults);
        }

    }
    //추가 시 , 연결안되는 오류있음
    // if (category == "bf") {
    //
    //     document.getElementById("bf_result").innerHTML += results.join(",");
    //
    //     var orgString = document.getElementById("bfCal").value;
    //     const orgNumber = Number(orgString);
    //     document.getElementById("bfCal").value = orgNumber +calResults ;

    if (category == "bf") {

        var orgcontents = document.getElementById("bf_result");

        if (orgcontents.innerHTML.trim() !== "") {
            console.log(orgcontents.innerHTML);
            console.log("기존 내용 존재");

            orgcontents.innerHTML += "," + results.join(",");


        } else {
            console.log("기존 내용 없음");
            orgcontents.innerHTML += results.join(",");
        }

        var orgString = document.getElementById("bfCal").value;
        const orgNumber = Number(orgString);
        document.getElementById("bfCal").value = orgNumber + calResults;


    } else if (category == "lunch") {
        var orgcontents = document.getElementById("lunch_result");

        if (orgcontents.innerHTML.trim() !== "") {
            orgcontents.innerHTML += "," + results.join(",");

        } else {

            orgcontents.innerHTML += results.join(",");
        }

        var orgString = document.getElementById("lunchCal").value;
        const orgNumber = Number(orgString);
        document.getElementById("lunchCal").value = orgNumber + calResults;

    } else if (category == "dinner") {
        var orgcontents = document.getElementById("dinner_result");

        if (orgcontents.innerHTML.trim() !== "") {
            orgcontents.innerHTML += "," + results.join(",");

        } else {
            orgcontents.innerHTML += results.join(",");
        }
        var orgString = document.getElementById("dinnerCal").value;
        const orgNumber = Number(orgString);
        document.getElementById("dinnerCal").value = orgNumber + calResults;

    } else if (category == "snack") {
        var orgcontents = document.getElementById("snack_result");

        if (orgcontents.innerHTML.trim() !== "") {
            orgcontents.innerHTML += "," + results.join(",");

        } else {
            orgcontents.innerHTML += results.join(",");
        }

        var orgString = document.getElementById("snackCal").value;
        const orgNumber = Number(orgString);
        document.getElementById("snackCal").value = orgNumber + calResults;

    } else if (category == "exer") {

        var orgcontents = document.getElementById("exer_result");

        if (orgcontents.innerHTML.trim() !== "") {
            orgcontents.innerHTML += "," + results.join(",");

        } else {
            orgcontents.innerHTML += results.join(",");
        }
        var orgString = document.getElementById("exerCal").value;
        const orgNumber = Number(orgString);
        document.getElementById("exerCal").value = orgNumber + calResults;

    }


    var tableBody = $('#data-table');
    tableBody.empty();
    searchModalBox.classList.remove("active");

}



